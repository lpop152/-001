package org.campus.controller;

import org.campus.pojo.User;
import org.campus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService userService;

	//实现用户登录
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestParam String telephone) {
		System.out.println(telephone);
		Map<String, Object> response = new HashMap<>();
		User user=userService.login(telephone);
		if (user != null) {
			if (user.getRoleType()==1){
				response.put("status", "success");
				response.put("token",user.getToken());
				response.put("role", user.getRoleType());
			}else if(user.getRoleType()==2){
				response.put("status", "success");
				response.put("token",user.getToken());
				response.put("role", user.getRoleType());
			}else if(user.getRoleType()==3){
				response.put("status", "success");
				response.put("token",user.getToken());
				response.put("role", user.getRoleType());
			}else if(user.getRoleType()==4){
				response.put("status", "success");
				response.put("token",user.getToken());
				response.put("role", user.getRoleType());
			}
		} else {
			response.put("status", "error");
			response.put("message", "用户名不存在");
		}
		return ResponseEntity.ok(response); // 确保返回的是 JSON 响应
	}
}
