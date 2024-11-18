package org.campus.controller;

import java.util.Date;
import java.util.List;

import org.campus.pojo.User;
import org.campus.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkIn")
public class CheckInController {
    @Autowired
    private CheckInService checkInService;

    /**
     * 学生打卡
     *
     * @param sid
     * @param time
     * @return
     */
    @PostMapping("/checkIn")
    public boolean checkIn(Integer sid, Date time) {
        return checkInService.checkIn(sid, time);
    }

    /**
     * 一个辅导员获得当前未打卡同学,一共有多少人
     * 若是-1,就是统计全部（打卡和未打卡，都统计）
     *
     *
     * @param aid checkInStatus -1 统计全部
     * @return
     */
    @GetMapping("/getNoCheckIn")
    public Integer getNoCheckIn(@RequestParam Integer aid, @RequestParam(defaultValue = "0") Integer checkInStatus) {
        System.out.println("Aid: " + aid + ", CheckInStatus: " + checkInStatus);
        return checkInService.getNoCheckIn(aid, checkInStatus);
    }

    /**
     * 根据辅导员aid，获取未打卡学生名单
     *
     * @param aid
     * @return
     */

    @GetMapping("/getNoCheckInStudent/{aid}")
    public List<User> getNoCheckInStudent(@PathVariable Integer aid) {
        System.out.println(aid);
        return checkInService.getNoCheckInStudent(aid);
    }
}
