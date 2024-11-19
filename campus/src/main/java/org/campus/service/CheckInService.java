package org.campus.service;

import org.campus.dao.ICheckInDao;
import org.campus.pojo.CheckIn;
import org.campus.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CheckInService implements ICheckInService{
    @Autowired
    private ICheckInDao checkInDao;

    @Override
    public boolean checkIn(CheckIn checkIn) {
        return checkInDao.checkIn(checkIn);
    }

    /**
     * 一个辅导员获得当前未打卡同学,一共有多少人
     * 若是-1,就是统计全部（打卡和未打卡，都统计）
     * @param aid
     * @return
     */
    @Override
    public Integer getNoCheckIn(Integer aid, Integer checkInStatus) {
        if(checkInStatus ==-1){
            return checkInDao.getAllCheckIn(aid);
        }else {
            return checkInDao.getNoCheckIn(aid);
        }
    }

    @Override
    public List<User> getNoCheckInStudent(Integer aid) {
        return checkInDao.getNoCheckInStudent(aid);
    }
}
