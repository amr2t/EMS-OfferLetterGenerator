package com.example.demo.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, UUID> {
	public Optional<Product> findById(UUID id);

	@Transactional
	@Modifying
	@Query("update Product p set p.headline = :headline where p.id = :id")
	public void updateHeadline(@Param("id")UUID id,@Param("headline") String headline);
	
	@Transactional
	@Modifying
	@Query("update Product p set p.description = :description where p.id = :id")
	public void updateDescription(@Param("id")UUID id,@Param("description") String description);
	
	@Transactional
	@Modifying
	@Query("update Product p set p.stock = :stock where p.id = :id")
	public void updateStock(@Param("id")UUID id,@Param("stock") int stock);
	
	@Transactional
	@Modifying
	@Query("update Product p set p.price = :price where p.id = :id")
	public void updatePrice(@Param("id")UUID id,@Param("price") int price);
	
	@Transactional
	@Modifying
	@Query("update Product p set p.imageurl = :imageurl where p.id = :id")
	public void updateImageurl(@Param("id")UUID id,@Param("imageurl") String imageurl);

	public Product findByHeadline(String headline);
}
