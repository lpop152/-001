package org.campus.service;

import org.campus.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
	//登录并生成token
	public User login(String telephone);
	//根据电话获取用户信息
	public User getUserByTelephone(String telephone);
	//根据班级id分页获取学生信息
	public Page<User> getUsersByClassId(Integer classId, int page, int size);

	//根据id获取用户信息
	public User getUserById(Integer id);

	//根据姓名获取学生信息
	public List<User> getStudentByName(String name);

	//通过id修改学生状态
	public boolean editStatus(Integer id,Integer status);
}
