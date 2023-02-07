package com.example.demo.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;
	@ManyToOne
	private Cart fromCart;
	@OneToOne
	private Product product;
	private int quantity;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	@JsonBackReference
	public Cart getFromCart() {
		return fromCart;
	}
	public void setFromCart(Cart tempCart) {
		this.fromCart = tempCart;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product tempProd) {
		this.product = tempProd;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "CartItem [id=" + id + ", fromCart=" + fromCart + ", product=" + product + ", quantity=" + quantity
				+ "]";
	}
	
	
}
