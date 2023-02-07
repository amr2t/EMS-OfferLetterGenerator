package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, UUID> {
	public Optional<CartItem> findById(UUID id);

	public List<CartItem> findAllByProduct(Product tempProduct);

}
