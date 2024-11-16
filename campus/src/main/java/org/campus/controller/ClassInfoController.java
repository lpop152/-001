package org.campus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.campus.pojo.ClassInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassInfoController {
	
	/**
	 * 返回年级信息
	 * @return
	 */
	@RequestMapping("/grade")
	public List<ClassInfo> getGrade(){
		return null;
	}
	/**
	 * 一个年级下专业
	 * @param gradeId
	 * @return
	 */
	public List<ClassInfo> getMajor(Integer gradeId){
		return null;
	}
    /**
     * 一个专业下的班级
     * @param majorId
     * @return
     */
    public List<ClassInfo> getClass(Integer majorId){
    	return null;
    }
    
    public ClassInfo getClassById(Integer id) {
    
    	return null;
    }
   
}
