package com.jxjy.exam.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动引导类
 *
 * @author yusheng
 */
@SpringBootApplication(scanBasePackages = {"com.jxjy.exam"})
@EnableScheduling
@MapperScan(basePackages = {"com.jxjy.exam.mapper"})
@EnableSwagger2
@EnableRetry
public class JxjyExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(JxjyExamApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(10000);
        factory.setConnectTimeout(15000);
        return factory;
    }
}