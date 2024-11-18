package org.campus.controller;

import java.util.Map;

import org.campus.pojo.User;
import org.campus.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	IUserService userService;

	@Autowired
    private MongoTemplate mongoTemplate;
	/**
	 *
	  *删除了不必要的参数，只要telephone
	  *
	 **/

	 @GetMapping("/addTelephone")
    public User addTelephone(@RequestParam String telephone) {
        User user = new User();
        user.setTelephone(telephone);  // 设置电话号码

        mongoTemplate.save(user);  // 插入数据到 MongoDB 的 "user" 集合
        return user;  // 返回插入的数据（包括自动生成的 id 字段）
    }

	@RequestMapping("/login")

	public User login(String telephone) {
		User user=userService.login(telephone);
		log.info(user.toString());
		return user;
	}

}
