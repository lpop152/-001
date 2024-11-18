package org.campus.service;

import org.campus.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
	
	public User login(String telephone);

}
