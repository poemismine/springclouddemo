package com.example.dao;

import com.example.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: tjy
 * @date: 2021/12/27 17:33
 */
@Mapper
public interface PaymentDao {
    Integer addPayment(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
