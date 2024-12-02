package org.campus.controller;


import java.util.List;


import lombok.extern.slf4j.Slf4j;

import org.campus.pojo.Result;
import org.campus.pojo.User;
import org.campus.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/checkIn")
public class CheckInController {
    @Autowired
    private CheckInService checkInService;


    /**
     * 学生打卡
     *
     * @param sno
     * @return
     */
    @PostMapping("/save")
    public Result checkIn(@RequestParam String sno, @RequestParam Double longitude, @RequestParam Double latitude) {
        log.info("学生打卡，sid: {};经度：{}；纬度：{}", sno, longitude, latitude);
        String result = checkInService.checkIn(sno, longitude, latitude);
        return new Result<>("SUCCESS", "", result);
    }

    /**
     * 辅导员查看当前应该打卡学生人数   前端显示22/39（已打卡/应打卡）
     *
     * @param aid
     * @return
     */
    @GetMapping("/getAllCheckIn")
    public Result getAllCheckIn(@RequestParam Integer aid) {
        log.info("辅导员aid: {}", aid);
        if (aid == null) {
            return new Result<>("FAIL", "辅导员id不能为空", null);
        }
        Integer shouldCheckIn = checkInService.getAllCheckIn(aid);
        return new Result<>("SUCCESS", "应该打卡学生人数", shouldCheckIn);
    }

    /**
     * 辅导员获得当前未打卡同学,一共有多少人
     * @param aid
     * @return
     */
    @GetMapping("/getNoCheckIn")
    public Result getNoCheckIn(@RequestParam Integer aid) {
        log.info("辅导员aid: {}", aid);
        if (aid == null) {
            return new Result<>("FAIL", "辅导员id不能为空", null);
        }
        Integer noCheckIn = checkInService.getNoCheckIn(aid);
        return new Result<>("SUCCESS", "未打卡学生人数", noCheckIn);
    }

    /**
     * 根据辅导员aid，获取未打卡学生名单
     *
     * @param aid
     * @return
     */

    @GetMapping("/getNoCheckInStudent/{aid}")
    public Result getNoCheckInStudent(@PathVariable Integer aid) {
        if (aid == null) {
            return new Result<>("FAIL", "辅导员id不能为空", null);
        }
        List<User> noCheckInList = checkInService.getNoCheckInStudent(aid);
        return new Result<>("SUCCESS", "未打卡学生名单", noCheckInList);
    }
}
