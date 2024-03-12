package com.ithWind.config.filter;

import com.ithWind.common.UserContext;
import com.ithWind.domain.dto.UserInfo;
import com.ithWind.toolkit.JwtTokenUtil;
import com.ruoyi.common.exception.ServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import io.reactivex.rxjava3.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,@NonNull FilterChain chain)
            throws ServletException, IOException {
        String TOKEN_HEADER = "Authorization";
        String header = request.getHeader(TOKEN_HEADER);
        String TOKEN_PREFIX = "Bearer ";
        String requestURI = request.getRequestURI();
        // 如果是登录接口，直接放过
        if (requestURI.startsWith("/user/login") || requestURI.startsWith("/user/register") ||
                requestURI.startsWith("/user/film") || requestURI.startsWith("/user/cinema")) {
            chain.doFilter(request, response);
            return;
        }
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            String token = header.replace(TOKEN_PREFIX, "");
            try {
                // 验证并解析JWT
                Claims claims = JwtTokenUtil.parseToken(token);

                if (claims != null) {
                    // 从JWT中获取用户名和其他信息
                    String username = claims.getSubject();
                    UserContext.setCurrentUser(new UserInfo(username));
                }

            } catch (SignatureException e) {
                // JWT签名异常，可能是Token被篡改
                throw new ServiceException("Invalid JWT signature: " + e.getMessage());
            } catch (Exception e) {
                // 其他JWT验证异常
                throw new ServiceException("JWT validation failed: " + e.getMessage());
            }
        }
        chain.doFilter(request, response);
    }
}