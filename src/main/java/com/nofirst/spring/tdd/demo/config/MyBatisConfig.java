package com.nofirst.spring.tdd.demo.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan({"com.nofirst.spring.tdd.demo.mbg.mapper", "com.nofirst.spring.tdd.demo.dao"})
public class MyBatisConfig {
}


