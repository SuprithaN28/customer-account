package com.Assignment.CustomerAccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Assignment.CustomerAccount.entity.PaymentDetails;


public interface PaymentRepository extends JpaRepository<PaymentDetails, Integer> {

}
