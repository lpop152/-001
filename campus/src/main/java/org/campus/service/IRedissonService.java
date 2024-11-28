package org.campus.service;

import org.campus.pojo.User;

import java.util.Map;

//Redis数据库的操作
public interface IRedissonService {

    //保存验证码到Redis
    Boolean saveCodeToRedis(String tokenID,String telephone, String code);

    //从Redis中获取Map对象
    Map<String, Object> getCodeFromRedis(String tokenID);

    //删除Redis中的验证码
    void deleteCodeFromRedis(String telephone);

    //保存user对象信息到Redis
    void saveUserToRedis(String tokenID, User user);

    //从Redis中获取user对象
    User getUserFromRedis(String tokenID);

}
