package org.campus.dao;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.campus.pojo.User;

import java.util.List;


@Repository
public interface IUserDao {

	//通过电话进行登录，生成token
	User login(String telephone);

	//通过电话查询一个用户信息
	User getUserByTelephone(String telephone);

	//通过班级id分页获取学生信息
	Page<User> findUsersByClassId(Integer classId, int page, int size);

	//通过id获取一个学生信息
	User getUserById(Integer id);

	//通过姓名查找学生信息
	List<User> getStudentByName(String name);

	//通过id修改学生状态
	boolean editStatus(Integer id,Integer status);

}
