package com.tensquare.qa.client;

import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：xzy
 * @ Date       ：Created in 6:53 2019/5/6
 */
@Component
public class LabelClientImpl  implements  LabelClient {
    @Override
    public Result findById(String id) {
        return new Result(true, StatusCode.OK,"熔断器启动了");
    }
}
