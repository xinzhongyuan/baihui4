package com.tensquare.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ Author     ：xzy
 * @ Date       ：Created in 15:22 2019/5/5
 */


@FeignClient(value = "tensquare-base",fallback = LabelClientImpl.class)
public interface LabelClient {
    @GetMapping("/label/{id}")
    public Result findById(@PathVariable("id") String id);
}
