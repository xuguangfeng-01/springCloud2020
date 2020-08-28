package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entitles.CommonResult;
import com.atguigu.springcloud.entitles.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int res = paymentService.create(payment);
        log.info("*****新增结果为:" + res );
        if(res > 0){
            return new CommonResult(200,"ok",res);
        }else{
            return new CommonResult(500,"error",res);
        }
    }

    @GetMapping(value = "/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id")Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果为(port:" + port +"): "+ payment );
        if(payment != null){
            return new CommonResult(200,"success,port:" + port,payment);
        }else{
            return new CommonResult(500,"error:" + id);
        }
    }

    /**
    * @Description: 列表查询
    * @author:      xuguangfeng
    * @date:        2020/8/26 9:29
    */
    @GetMapping(value = "/payment/getPaymentList")
    public CommonResult getPaymentList(){
        List<Payment> paymentList = paymentService.getPaymentList();
        log.info("***查询结果为: " + paymentList);
        if(paymentList != null){
            return new CommonResult(200,"success",paymentList);
        }else{
            return new CommonResult(500,"error:");
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service :services){
            log.info("service : "+ service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance serviceInstance: instances) {
            log.info( serviceInstance.getInstanceId()+ "^^" + serviceInstance.getServiceId()+ "^^" + serviceInstance.getHost()+ "^^" + serviceInstance.getPort()+ "^^" + serviceInstance.getUri());
        }
        return this.discoveryClient;
    }

}
