package org.campus.dao;

import org.campus.pojo.User;
import org.campus.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Repository("userDao")
public class UserDao implements IUserDao{
	@Autowired
    MongoTemplate mongoTemplate;

	//用户登录
	@Override
	public User login(String telephone){
		Query query = new Query(Criteria.where("telephone").is(telephone));
		//查询
		User user=mongoTemplate.findOne(query, User.class);
		System.out.println(user);
		// 获取当前时间并转换为字符串
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String currentTime = now.format(formatter);
		// 打印当前时间
		System.out.println("当前时间: " + currentTime);
		assert user != null;
		user.setLoginTime(currentTime);

		String token = TokenUtil.sign(user.getName(),user.getLoginTime());
		user.setToken(token);
		System.out.println(user);
//		//断言token不为空，并以用户名作为key，存入redis
//		assert token != null;
//		redisTemplate.opsForValue().set(user.getTelephone(),token);
		return user;
	}
}

