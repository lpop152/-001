package org.campus.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.campus.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedissonService implements IRedissonService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 保存手机号码和验证码到Redis
    @Override
    public Boolean saveCodeToRedis(String tokenID,String telephone, String code) {
//         try {

//            redisTemplate.multi(); // 开始事务

            redisTemplate.opsForHash().put(tokenID, "code", code);
            redisTemplate.opsForHash().put(tokenID, "telephone", telephone);

            redisTemplate.expire(tokenID, 5, TimeUnit.MINUTES); // 设置过期时间为5分钟

//            redisTemplate.exec(); // 提交事务

            return true;
//        } catch (Exception e) {
//            redisTemplate.discard(); // 回滚事务
//            e.printStackTrace();
//            return false;
//        }
    }

    // 获取Redis中的Map对象
    @Override
    public Map<String, Object> getCodeFromRedis(String tokenID) {
        // 检查键是否存在
        if (Boolean.FALSE.equals(redisTemplate.hasKey(tokenID))) {
            System.out.println("tokenID不存在：" + tokenID);
            return new HashMap<>();
        }

        // 获取哈希表
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(tokenID);


        // 将Map<Object, Object> 转为Map<String, Object>
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<Object, Object> entry : entries.entrySet()) {
            result.put(entry.getKey().toString(), entry.getValue());
        }
        System.out.println("在Redis中获取转化为Map<String, Object>：" + result);
        return result;
    }

    // 匹配成功后删除Redis中的验证码
    @Override
    public void deleteCodeFromRedis(String tokenID) {
        redisTemplate.delete(tokenID);
    }

    // 保存User对象到Redis
    public void saveUserToRedis(String tokenID, User user) {
        if (tokenID == null || user == null) {
            return;
        }
        try {
            // 将User对象转换为JSON字符串
            String userJson = objectMapper.writeValueAsString(user);

            // 创建一个Map，键为"user"，值为User对象的JSON字符串
            Map<String, String> userMap = new HashMap<>();
            userMap.put("user", userJson);

            // 将Map存储到Redis中
            redisTemplate.opsForHash().putAll(tokenID, userMap);

            // 设置过期时间
            redisTemplate.expire(tokenID, 1, TimeUnit.HOURS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 从Redis中获取User对象
    public User getUserFromRedis(String tokenID) {
        if (tokenID == null) {
            return null;
        }
        // 从Redis中获取Map对象
        Map<Object, Object> userMap = redisTemplate.opsForHash().entries(tokenID);
        if (userMap.isEmpty()) {
            return null;
        }

        // 获取"user"键对应的值
        String userJson = (String) userMap.get("user");
        if (userJson == null) {
            return null;
        }

        try {
            // 将JSON字符串转换为User对象
            return objectMapper.readValue(userJson, User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
