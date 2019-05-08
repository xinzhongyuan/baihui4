package com.tensquare.sms;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ Author     ：xzy
 * @ Date       ：Created in 18:19 2019/4/29
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {


    @Autowired
    private SmsUtil smsUtil;
    @Value("${aliyun.sms.template_code}")
    private String template_code;//模板编号
    @Value("${aliyun.sms.sign_name}")
    private String sign_name;//签名
    @RabbitHandler
    public void sendSms(Map<String, String> map) throws  Exception {
        System.out.println("手机号：" + map.get("phonenum"));
        System.out.println("验证码：" + map.get("code"));
        //{"code":123456}
        smsUtil.sendSms(map.get("phonenum"),template_code,sign_name,"{\"code\":"+map.get("code")+"}");
    }
}
