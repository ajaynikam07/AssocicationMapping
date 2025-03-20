package com.ajay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajay.entity.Person;

public interface IPersonRepo extends JpaRepository<Person,Integer>
{

}
