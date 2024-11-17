package org.campus.controller;

import org.campus.pojo.User;
import org.campus.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	IUserService userService;
	//实现用户登录
	@GetMapping("/login")
	public User login(@RequestParam String telephone) {
		User user=userService.login(telephone);
		return user;
	}

}
