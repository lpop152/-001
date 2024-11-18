package org.campus.service;

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
		return dao.login(telephone);
	}
}
