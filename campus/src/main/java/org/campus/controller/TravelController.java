package org.campus.controller;

import java.util.List;
import java.util.Map;


import org.campus.pojo.Travel;
import org.campus.pojo.User;

public class TravelController {
	
	/**
	 * 一个学生填写一个出行报备
	 * @param tra
	 */
	public void addTravel(Travel tra) {
		
	}
	/**
	 * 一个辅导员统计一下直到当前，未填写的学生人数
	 * @param aid
	 * @return
	 */
	public Integer getNoComplete(Integer aid) {
		return null;
	}
	/**
	 * 未填写学生名单
	 * @param aid
	 * @return
	 */
	public List<User> getNoCompleteStudent(Integer aid){
		return null;
	}
	/**
	 * 统计一下各种类型人数，留校多少人，回家多少人，旅游多少人
	 * @param aid
	 * @return
	 */
	public Map<String, Integer> getNumberofType(Integer aid){
		return null;
	}
     /**
      * 返回学校要打卡
      * @param tid
      */
	public void   goBack (Integer tid) {
		
	}
	/**
	 * 未返校打卡学生人数
	 * @param aid
	 * @return
	 */
	public Integer noBack(Integer aid) {
		return null;
	}
	/**
	 * 未返校打卡名单
	 * @param aid
	 * @return
	 */
	public List<User> noBackStudent(Integer aid){
		return null;
	}
}
