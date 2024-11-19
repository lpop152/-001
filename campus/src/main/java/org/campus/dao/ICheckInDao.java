package org.campus.dao;

import org.campus.pojo.CheckIn;
import org.campus.pojo.User;

import java.util.List;

public interface ICheckInDao {
    boolean checkIn(CheckIn checkIn);

    Integer getAllCheckIn(Integer aid);

    Integer getNoCheckIn(Integer aid);

    List<User> getNoCheckInStudent(Integer aid);



}
