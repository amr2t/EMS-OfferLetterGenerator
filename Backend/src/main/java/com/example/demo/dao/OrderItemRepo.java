package com.example.demo.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, UUID> {

}
