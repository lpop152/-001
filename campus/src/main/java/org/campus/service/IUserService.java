package org.campus.service;

import org.campus.pojo.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {

	//登录并生成token
	public User login(String telephone);

	//通过电话号码判断用户是否存在
	public boolean isExist(String telephone);

	//根据电话获取用户信息
	public User getUserByTelephone(String telephone);

	//根据班级id分页获取学生信息
	public Page<User> getUsersByClassId(String classId, int page, int size);
	//根据id获取用户信息
	public User getUserById(String id);

	//根据姓名获取学生信息
	public List<User> getStudentByName(String name);

	//通过_id修改学生状态
	public boolean editStatus(String id,int status);

	//生成六位数验证码
	public String generateVerificationCode();

}
