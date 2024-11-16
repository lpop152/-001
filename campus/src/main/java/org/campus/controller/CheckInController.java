package org.campus.controller;

import java.util.Date;
import java.util.List;

import org.campus.pojo.User;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckInController {

	 /**
	  * 
	  * @param sid
	  * @param time
	  * @return
	  */
	 public boolean checkIn(Integer sid,Date time) {
		 return true;
	 }
	 /**
	  * 一个辅导员获得当前未打卡同学
	  * 若是-1,就是统计全部
	  * @param aid
	  * @return
	  */
	 public Integer getNoCheckIn(Integer aid) {
		 return 0;
	 }
	 /**
	  * 未打卡学生名单
	  * @param aid
	  * @return
	  */
	 public List<User> getNoCheckInStudent(Integer aid){
		 return null;
	 }
}
