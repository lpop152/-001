package org.campus.controller;


import java.util.List;

import org.campus.pojo.Result;
import org.campus.pojo.User;
import org.campus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private IUserService userService;

	//根据班级ID、初始页码和一页信息数实现动态分页
	@PostMapping("/class")
	public Result<Page<User>> getUsersByClassId(
			@RequestParam("classId") String classId,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Page<User> users =userService.getUsersByClassId(classId, page, size);
		if (users!=null){
			return new Result<>("SUCCESS", "获取分页成功", users);
		}else {
			return new Result<>("FAIL", "获取失败", null);
		}
	}


	//根据_id查询学生信息
	@PostMapping("/getStudentById")
	public Result<User> getUserById(@RequestParam("id") String _id) {
		User user=userService.getUserById(_id);
		if (user!=null){
			return new Result<>("SUCCESS", "查询成功", user);
		}else {
			return new Result<>("FAIL", "查询失败", null);
		}
	}


	//根据名字查找学生信息
	@PostMapping("/getStudentsByName")
	public Result<List<User>> getStudentByName(@RequestParam("name") String name){
		List<User> users=userService.getStudentByName(name);
		if (users!=null){
			return new Result<>("SUCCESS", "查询成功", users);
		}else {
			return new Result<>("FAIL", "查询失败", null);
		}
	}


	//修改某一个学生状态
	@PostMapping("/editStatus")
	public Result<String> editStatus(@RequestParam("id") String _id, @RequestParam("status") int status) {
		if(userService.editStatus(_id,status)) {
			return new Result<>("SUCCESS", "修改成功", null);
		}else {
			return new Result<>("SUCCESS", "修改失败", null);
		}
	}
}
