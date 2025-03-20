package com.ajay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajay.entity.PhoneNo;

public interface IPhoneNoRepo extends JpaRepository<PhoneNo, Integer> {

}
