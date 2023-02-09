package com.example.demo.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CustomerOrder;

@Repository
public interface CustomerOrderRepo extends JpaRepository<CustomerOrder,UUID> {

}
