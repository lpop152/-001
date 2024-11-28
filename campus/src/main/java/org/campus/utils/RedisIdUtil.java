package org.campus.utils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
//生成唯一id工具类，暂时没有用
@Component
public class RedisIdUtil {

    private static final String ID_KEY = "myIdKey";
    private static final JedisPool jedisPool;

    static {
        // 初始化 Jedis 连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 设置 Redis 服务器地址、端口和密码
        String host = "localhost";
        int port = 6379;
        String password = "123456"; // 替换为你的 Redis 密码

        // 创建 JedisPool 实例时提供密码
        jedisPool = new JedisPool(poolConfig, host, port, Protocol.DEFAULT_TIMEOUT, password);
    }

    /**
     * 生成唯一的 ID
     * @return 生成的唯一 ID
     */
    public long generateId() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.incr(ID_KEY);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate unique ID", e);
        }
    }

    /**
     * 关闭 Jedis 连接池
     */
    public void close() {
        jedisPool.close();
    }
}
