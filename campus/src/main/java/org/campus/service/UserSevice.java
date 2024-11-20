package org.campus.service;

import org.campus.dao.IUserDao;
import org.campus.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserSevice implements IUserService {
	@Autowired
	@Qualifier("userDao")
    IUserDao dao;
	@Override
	public User login(String telephone) {
		return dao.login(telephone);
	}

	@Override
	public User getUserByTelephone(String telephone) {
		return dao.getUserByTelephone(telephone);
	}

	//学生根据班级分页
	@Override
	public Page<User> getUsersByClassId(Integer classId, int page, int size) {
		System.out.println(classId);
		return dao.findUsersByClassId(classId, page, size);
	}


	//根据id查询用户
	@Override
	public User getUserById(Integer id) {
		return dao.getUserById(id);
	}


	//根据姓名查询学生信息
	@Override
	public List<User> getStudentByName(String name) {
		return dao.getStudentByName(name);
	}


	//通过id修改学生状态
	@Override
	public boolean editStatus(Integer id,Integer status) {
		return dao.editStatus(id,status);
	}
}
