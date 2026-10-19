package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT 令牌操作工具类
 * 提供生成和解析 JWT 的方法，使用固定秘钥，默认过期时间 12 小时
 */
public class JwtUtils {

    // 固定秘钥（与测试类一致）
    private static final String SECRET_KEY = "aXRoZWltYQ==";

    // 过期时间：12小时（单位：毫秒）
    private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000; // 43,200,000 ms

    /**
     * 生成 JWT 令牌
     * @param dataMap 需要存入令牌的声明数据（如用户ID、用户名等）
     * @return JWT 字符串
     */
    public static String generateToken(Map<String, Object> dataMap) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .addClaims(dataMap)
                .setExpiration(expiration)
                .compact();
    }

    /**
     * 解析 JWT 令牌，获取声明数据
     * @param token JWT 字符串
     * @return Claims 对象（包含所有声明）
     * @throws io.jsonwebtoken.JwtException 如果令牌无效或已过期
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}