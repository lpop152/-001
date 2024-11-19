package org.campus.dao;


import org.campus.pojo.CheckIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.campus.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CheckInDao implements ICheckInDao {
    private static final Logger log = LoggerFactory.getLogger(CheckInDao.class);
    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 学生打卡记录
     * @param checkIn
     * @return
     */
    @Override
    public boolean checkIn(CheckIn checkIn) {
        try {
            mongoTemplate.save(checkIn);
            return true;
        }catch (Exception e){
            log.info("打卡失败{}",e);
            return false;
        }

    }

    @Override
    public Integer getAllCheckIn(Integer aid) {
        Query query = new Query(Criteria.where("aid").is(aid));
        long count = mongoTemplate.count(query, User.class);
        log.info("获取所有打卡学生数量成功{}", count);
        return (int)count;
    }
    @Override
    public Integer getNoCheckIn(Integer aid) {
        Query query = new Query(Criteria.where("aid").is(aid).and("isCheckedIn").is(0));
        long  count = mongoTemplate.count(query, User.class);
        log.info("获取未签到学生数量成功: {}", count);
        return (int) count;
    }

    /**
     * 根据辅导员aid，获取未签到学生详细信息
     * @param aid
     * @return
     */
    @Override
    public List<User> getNoCheckInStudent(Integer aid) {
        Query query = new Query(Criteria.where("aid").is(aid).and("isCheckedIn").is(0));
        List<User> users = mongoTemplate.find(query, User.class);
        log.info("获取未签到学生成功{}",users);
        return users;
    }
}
