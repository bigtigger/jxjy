package com.jxjy.exam.service.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yusheng
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.jxjy.exam"})
@MapperScan(basePackages = "com.jxjy.exam.mapper")
public class TestConfig {
}
