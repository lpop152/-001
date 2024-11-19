package org.campus.service;

import org.campus.pojo.CheckIn;
import org.campus.pojo.User;

import java.util.Date;
import java.util.List;

public interface ICheckInService {
    boolean checkIn(CheckIn checkIn);

    Integer getNoCheckIn(Integer aid, Integer checkInStatus);

    List<User> getNoCheckInStudent(Integer aid);



}
