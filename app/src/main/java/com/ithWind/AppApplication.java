package com.ithWind;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.ruoyi.system.service", "com.ithWind.domain.*", "com.ruoyi.common.*"})
@MapperScan("com.ruoyi.system.mapper")
@MapperScan("com.ithWind.domain.mapper")
@ComponentScan("com.ithWind.config")
@ComponentScan("com.ithWind.controller")
@ComponentScan("com.ithWind.service")
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}
