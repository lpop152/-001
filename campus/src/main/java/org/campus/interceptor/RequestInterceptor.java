package org.campus.interceptor;

import org.campus.pojo.User;
import org.campus.service.RedissonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
    @Autowired
    private RedissonService redissonService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String authorizationHeader = request.getHeader("Authorization");

        String token = null;
        User user = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);

            // 从 Redis 中获取用户信息
            user = redissonService.getUserFromRedis(token);

            if (user == null) {
                logger.warn("Token is invalid or does not exist in Redis");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Invalid token\"}");
                return false;
            }
        }

        if (user != null) {
            //如果存在则放行
            return true;
        }

        // 如果令牌无效或不存在，返回401 Unauthorized
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Unauthorized\"}");
        return false;
    }
}
