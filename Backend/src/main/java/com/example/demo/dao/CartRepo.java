package com.example.demo.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, UUID> {
	public Optional<Cart> findById(UUID id);
}
