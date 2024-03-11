package com.ithWind.config;

import com.ithWind.config.filter.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private JwtTokenFilter jwtTokenVerifier;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 允许所有人访问/user/login，无需认证
        http
                .authorizeRequests()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/user/film/list").permitAll()
                // 其他请求需要身份验证
                .anyRequest().authenticated()
                // 关闭CSRF防护（实际项目中请按需开启）
                .and().csrf().disable()
                .httpBasic().disable();
        // 添加JWT过滤器，仅对需要验证Token的路径生效
        http.addFilterBefore(jwtTokenVerifier, FilterSecurityInterceptor.class);
    }
}
