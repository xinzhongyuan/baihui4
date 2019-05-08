package com.tensqure.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import util.JwtUtil;

/**
 * @ Author     ：xzy
 * @ Date       ：Created in 7:21 2019/5/6
 */
@EnableZuulProxy
@SpringBootApplication
public class ZullApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZullApplication.class, args);
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }









}
