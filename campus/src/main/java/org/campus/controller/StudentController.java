package org.campus.controller;

import java.util.List;


import org.campus.pojo.User;

public class StudentController {

	/**
	 * 根据班级Id获得一个班级同学，分页查找
	 * @param classId
	 * @param start
	 * @param size
	 * @return
	 */
	public List<User> getStudentsByClass(Integer classId,Integer start,Integer size){
		return null;
	}
	/**
	 * 根据ID找到一个同学
	 * @param id
	 * @return
	 */
	public User getStudentById(Integer id) {
		return null;
	}
    /**
     * 修个一个同学状态
     * @param id
     * @param status
     */
	public void editStatus(Integer id,Integer status) {
		
	}
	/**
	 * 根据名字查找同学
	 * @param name
	 * @return
	 */
	public List<User> getStudentByName(String name){
		return null;
	}
}
