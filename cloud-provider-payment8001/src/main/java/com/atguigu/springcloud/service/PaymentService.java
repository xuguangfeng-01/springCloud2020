package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entitles.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    /**
    * @Description: 新增
    * @author:      xuguangfeng
    * @date:        2020/8/25 13:47
    */
    public int create(Payment payment);

    /**
    * @Description: 查询
    * @author:      xuguangfeng
    * @date:        2020/8/25 13:47
    */
    public Payment getPaymentById(@Param("id") Long id);
}
