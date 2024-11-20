package org.campus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.campus.pojo.User;
import org.campus.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IUserService userService;
	//根据班级ID查找学生并分页

	@GetMapping("/class")
	public Page<User> getUsersByClassId(
			@RequestParam("classId") Integer classId,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		logger.debug("Received request for classId: {}, page: {}, size: {}", classId, page, size);
		return userService.getUsersByClassId(classId, page, size);
	}

	//根据id查询学生信息
	@PostMapping("/getStudentById")
	public User getUserById(@RequestParam Integer id) {
		return userService.getUserById(id);
	}


	//根据名字查找学生信息
	@PostMapping("/getStudentsByName")
	public List<User> getStudentByName(@RequestParam String name){
		return userService.getStudentByName(name);
	}


	//修改某一个学生状态
	@PostMapping("/editStatus")
	public ResponseEntity<Map<String, Object>> editStatus(Integer id, Integer status) {
		Map<String, Object> response = new HashMap<>();
		if(userService.editStatus(id,status)) {
			response.put("status", "success");
		}else {
			response.put("status", "failure");
		}
		return ResponseEntity.ok(response);
	}
}
