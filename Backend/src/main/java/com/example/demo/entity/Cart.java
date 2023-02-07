package com.example.demo.entity;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;
	
	@OneToMany(mappedBy="fromCart")
	private List<CartItem> item;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	@JsonManagedReference
	public List<CartItem> getItem() {
		return item;
	}
	public void setItem(List<CartItem> item) {
		this.item = item;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", item=" + item + "]";
	}
	
	
}
