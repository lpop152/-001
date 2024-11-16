package org.campus.controller;

import java.util.Map;

import org.campus.pojo.User;
import org.campus.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	IUserService userService;
	/**
	 * 
	 * @param username
	 * @param pass
	 * @return
	 */
	
	@RequestMapping("/login")

	public User login(String telephone) {
		User user=userService.login(telephone);
		log.info(user.toString());
		return user;
	}

}
