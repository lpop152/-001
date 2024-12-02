package org.campus.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.campus.pojo.ClassInfo;
import org.campus.pojo.Classes;
import org.campus.pojo.Major;
import org.campus.pojo.Result;
import org.campus.service.IClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/classInfo")
public class ClassInfoController {
	@Autowired
	private IClassInfoService classInfoService;
	
	/**
	 * 返回年级信息，全部年级
	 * @return
	 */
	@GetMapping("/grade")
	public Result getGrade(){
		return new Result<>("SUCCESS",null,classInfoService.getGrade());
	}
	/**
	 * 一个年级下专业
	 *
	 * @param grade
	 * @return
	 */
	@GetMapping("/major/{grade}")
	public Result getMajor(@PathVariable String grade){
		System.out.println("查询年级:"+grade);
		return new Result<>("SUCCESS",null,classInfoService.getMajor(grade));
	}
    /**
     * 一个专业下的班级
     * @param major
     * @return
     */
	@GetMapping("/class")
    public Result getClass(@RequestParam String major){
		log.info("查询专业:"+major);
    	return new Result<>("SUCCESS",null,classInfoService.getClass(major));
    }

	/**
	 * 根据id获取班级信息
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/getClassById/{id}")
    public Result getClassById(@PathVariable Integer id) {
		log.info("查询班级id:"+id);
    	return new Result<>("SUCCESS",null,classInfoService.getClassById(id));
    }
   
}
