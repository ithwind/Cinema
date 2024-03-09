package com.ithWind.toolkit;

import com.ithWind.domain.req.LoginReq;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class JwtTokenUtil {

    private static final String SECRET_KEY = "IthWind"; // 用于签名的密钥，实际环境中应更加安全地存储和管理
    private static final long EXPIRATION_TIME_IN_MILLIS = 86400000L; // 令牌有效期为24小时

    public static String generateToken(LoginReq req) {
        Map<String, Object> claims = new HashMap<>();
        String username = req.getUsername();
        claims.put("username", username);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME_IN_MILLIS);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username) // 可以设置主题（subject），通常是用户名或其他唯一标识
                .setIssuedAt(now) // 设置签发时间
                .setExpiration(expiryDate) // 设置过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()) // 使用指定算法和密钥进行签名
                .compact(); // 创建并返回紧凑的JWT字符串
    }
    public static Claims parseToken(String jwtToken) throws SignatureException {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes()) // 使用与生成时相同的密钥进行解析
                    .parseClaimsJws(jwtToken) // 解析为包含声明的JWS（Json Web Signature）
                    .getBody(); // 获取JWT的有效载荷部分（payload），即声明集合
        } catch (Exception e) {
            throw new SignatureException("Invalid JWT token");
        }
    }

    public static String getUsernameFromToken(String token) throws SignatureException {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }
}
