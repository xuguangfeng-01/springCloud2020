package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entitles.CommonResult;
import com.atguigu.springcloud.entitles.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    public static final String PAYMENT_URL = "http://cloud-payment-service/";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"payment/create",payment,CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){
        return restTemplate.getForObject(PAYMENT_URL+"payment/getPaymentById/"+id,CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/getPaymentList")
    public CommonResult<List<Payment>> getPaymentList(){
        return restTemplate.getForObject(PAYMENT_URL+"payment/getPaymentList",CommonResult.class);
    }
}
