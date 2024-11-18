package org.campus.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    /**
     * token过期时间
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    /**
     * token秘钥
     */
    private static final String TOKEN_SECRET = "asrgerhae";
    public static String sign(String username, String loginTime) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("loginName", username)
                    .withClaim("loginTime", loginTime)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否正确
     * @param token 需要校验的token
     * @return 校验是否成功
     */
    public static boolean verify(String token){
        try {
            //设置签名的加密算法：HMAC256
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}


