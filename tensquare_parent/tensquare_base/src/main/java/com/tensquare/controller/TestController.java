package com.tensquare.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：xzy
 * @ Date       ：Created in 12:09 2019/5/7
 */
@RestController
@RefreshScope
@ConfigurationProperties("sms")
public class TestController {

    private String  ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @GetMapping("/ip")
    public String getResult(){
        return  ip;
    }

}
