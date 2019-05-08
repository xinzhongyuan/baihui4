package com.tensquare.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @ Author     ：xzy
 * @ Date       ：Created in 19:02 2019/5/6
 */
@EnableConfigServer //开启配置服务
@SpringBootApplication
public class ConfigServerApplication {


        public static void main(String[] args) {
            SpringApplication.run(ConfigServerApplication.class, args);
    }
}
