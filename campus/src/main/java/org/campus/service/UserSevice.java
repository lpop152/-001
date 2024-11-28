package org.campus.service;

import org.campus.dao.IUserDao;
import org.campus.pojo.User;
import org.campus.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserSevice implements IUserService {
	@Autowired
    IUserDao dao;
	@Autowired
	RandomUtil randomUtil;
	@Override
	public User login(String telephone) {
		return dao.login(telephone);
	}

	//通过电话获取用户信息
	@Override
	public User getUserByTelephone(String telephone) {
		return dao.getUserByTelephone(telephone);
	}

	//通过电话判断用户是否存在
	@Override
	public boolean isExist(String telephone) {
		return dao.isExist(telephone);
	}

	//学生根据班级分页
	@Override
	public Page<User> getUsersByClassId(String classId, int page, int size) {
		Pageable pageable= PageRequest.of(page, size);
		return dao.findUsersByClassId(classId, pageable);
	}

	//根据_id查询用户
	@Override
	public User getUserById(String _id) {
		return dao.getUserById(_id);
	}

	//根据姓名查询学生信息
	@Override
	public List<User> getStudentByName(String name) {
		return dao.getStudentByName(name);
	}

	//通过_id修改学生状态
	@Override
	public boolean editStatus(String id,int status) {
		return dao.editStatus(id,status);
	}

	//生成六位验证码
	@Override
	public String generateVerificationCode() {
		return randomUtil.getSixBitRandom();
	}
}
