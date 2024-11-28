package org.campus.controller;

import org.campus.pojo.Result;
import org.campus.service.RedissonService;
import org.campus.service.UserSevice;
import org.campus.pojo.User;
import org.campus.utils.SendSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

// 如果用户第一次登陆，通过index请求，生成token，然后通过token以及电话号码获取验证码，然后通过token、验证码和电话号码登录
// 如果不是第一次登陆那么进行检查(check)，查看token是否过期，过期就重新生成token以及用户信息返回给前端，没有过期就还可以进行相关操作
// 客户端每次访问的请求头都要带上token,token请求参数的话看需求。
// 请求头格式为Key(Authorization):Value(Bearer token)
// index和getVerificationCode,login请求都不被拦截,(check请求也不被拦截，因为check就相当于变向的拦截器了。它可以生成新的token，只是不能直接拦截掉)
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserSevice userService;
	@Autowired
	RedissonService redissonService;

	//实现用户登录
	@PostMapping("/login")
	public Result<User> login(@RequestParam("tokenID") String tokenID,@RequestParam("telephone") String telephone, @RequestParam("code") String code) {
		Result<User> result=new Result<>();
		// 从Redis中获取Map对象
		Map<String,Object> redisMap= redissonService.getCodeFromRedis(tokenID);
		//从Map<String,Object>对象中获取验证码
		String redisCode= (String) redisMap.get("code");
		String phone= (String) redisMap.get("telephone");
		System.out.println("redisCode:"+redisCode);
		if (redisCode == null) {
			result.setStatus("FAIL");
			result.setMessage("验证码已过期");
			result.setData(null);
			return result;
		}
		//如果手机号码和验证码都匹配，则登录成功，否则登录失败
		if (Objects.equals(code, redisCode)&&Objects.equals(phone, telephone)) {
			// 验证码匹配则删除Redis中的验证码
			redissonService.deleteCodeFromRedis(tokenID);
			// mongodb中有对应手机号则有user没有手机号则为空
			User user = userService.login(telephone);
			if (user != null) {
				//将信息存入Redis中
				redissonService.saveUserToRedis(tokenID,user);
				result.setStatus("SUCCESS");
				result.setMessage(String.valueOf(user.getRoleType()));
				result.setData(user);
			} else {
				result.setStatus("FAIL");
				result.setMessage("用户名不存在");
				result.setData(null);
			}
		} else {
			result.setStatus("FAIL");
			result.setMessage("验证码手机号不匹配");
			result.setData(null);
		}

		return result;
	}

	// 用index生成的token来获取验证码
	@GetMapping("/getVerificationCode")
	public Result<String> sendCode(@RequestParam("telephone") String telephone,@RequestParam("tokenID") String tokenID) {
		// 检查手机号码是否存在于MongoDB中
		boolean exists = userService.isExist(telephone);
		if (!exists) {
			return new Result<>("FAIL","手机号不存在",null);
		}
		// 生成6位随机验证码
		String code = userService.generateVerificationCode();

		// 保存电话和验证码到Redis中
		Boolean isSaved = redissonService.saveCodeToRedis(tokenID,telephone,code);

		if (isSaved) {
			// 转成HashMap
			Map<String, String> param = new HashMap<>();
			param.put("code", code);

			// 发送验证码
			boolean res = SendSmsUtil.sendCode(telephone, param);

			if (res) {
				// 验证码发送成功
				return new Result<>("SUCCESS","验证码已发送",null);
			} else {
				// 验证码发送失败
				return new Result<>("FAIL","验证码发送失败",null);
			}
		} else {
			// 在5分钟内已经发送过验证码
			return new Result<>("FAIL","请勿频繁请求验证码，5分钟后可再次尝试",null);
		}
	}
	// 客户端用户打开系统时候： 若客户端没有保存token，说明是第一次登录，要显示登录页，客户端访问这个获取token,而且保存下来
	@GetMapping("/index")
	public Result<String> index(){
		String token= UUID.randomUUID().toString();
		return new Result<>("SUCCESS","token成功生成",token);
	}

	// 若客户端已经有token，说明用户已经登录过，就不要显示登录页，直接显示首页，但是，要访问这个check，确认一下token是否过期
	@PostMapping("/check")
	public Map<String,Object> check(@RequestParam("token") String token,@RequestParam("telephone") String telephone){
		Map<String,Object> map=new HashMap<>();
		User user=redissonService.getUserFromRedis(token);  //去redis取出用户对象
		if(user==null) {                   //取不出来，说明token已经过期了
			String newToken= UUID.randomUUID().toString();  //重新生成token
			User nuser=userService.login(telephone);         //根据电话号码查找用户
			redissonService.saveUserToRedis(newToken, nuser);  //保存用户到redis
			map.put("result", -1);
			map.put("desc", "原有token已经过期，生成新的token");
			map.put("token",newToken);             //要把新token返回给客户端，客户端替换旧的token
			map.put("user", nuser);                //用户信息返回给客户端 ，这个并不是必须的
		}else {
			map.put("result", 0);
			map.put("desc", "原有token未过期，依然可以用");
		}
		return map;
	}
}
