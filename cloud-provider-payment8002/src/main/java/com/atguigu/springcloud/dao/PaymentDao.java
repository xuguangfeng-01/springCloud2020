package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entitles.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

    public List<Payment> getPaymentList();
}
