package org.campus.service;

import java.util.Map;

import org.campus.dao.IUserDao;
import org.campus.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserSevice implements IUserService {
	@Qualifier("userDao")
	@Autowired
    IUserDao dao;
	@Override
	public User login(String telephone) {
		User user=dao.login(telephone);
		return user;
	}
}
