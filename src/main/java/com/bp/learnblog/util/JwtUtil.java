package com.bp.learnblog.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * Jwt校验工具方法
 *
 * @author DH
 */
@Slf4j
public class JwtUtil {
    /**
     * 定义token有效时间（12小时）
     */
    private static final long EXPIRE_TIME = 1000 * 60 * 60 * 12;

    /**
     * 根据用户名密码生成token签名
     * @param username 用户名
     * @param secret 密码
     * @return String|null
     */
    public static String signToken(String username, String secret) {
        try {
            // 当前时间加过期时间
            Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 密码加密
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(expireDate)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("username-{} and secret-{} signed token faithfully, {}", username, secret, e.getMessage());
            return null;
        }
    }

    /**
     * 校验token和用户名密码是否一致
     *
     * @param token token令牌
     * @param username 用户名
     * @param secret 密码
     * @return boolean
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT decodedJwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("User token verify failed, {}", e.getMessage());
            return false;
        }
    }

    /**
     * 根据token解析获取用户名信息
     *
     * @param token token令牌
     * @return String|null
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT decodedJwt = JWT.decode(token);
            return decodedJwt.getClaim("username").asString();
        } catch (Exception e) {
            log.error("Token-> {} decoded for username faithfully, {}", token, e.getMessage());
            return null;
        }
    }
}
