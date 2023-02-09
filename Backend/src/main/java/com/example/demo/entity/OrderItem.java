package com.example.demo.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderItem {
		
		@Id
		@GeneratedValue(strategy=GenerationType.UUID)
		private UUID id;
		
		private String prodHeadline;
		private String prodDescription;
		private String prodImageUrl;
		private int prodPrice;
		private int prodQuantity;
		@ManyToOne
		private CustomerOrder fromOrder;
		
		
		
		public UUID getId() {
			return id;
		}
		public void setId(UUID id) {
			this.id = id;
		}
		
		@JsonBackReference
		public CustomerOrder getFromOrder() {
			return fromOrder;
		}
		public void setFromOrder(CustomerOrder fromOrder) {
			this.fromOrder = fromOrder;
		}
		
		public int getProdQuantity() {
			return prodQuantity;
		}
		public void setProdQuantity(int prodQuantity) {
			this.prodQuantity = prodQuantity;
		}
		public int getProdPrice() {
			return prodPrice;
		}
		public void setProdPrice(int prodPrice) {
			this.prodPrice = prodPrice;
		}
		public String getProdImageUrl() {
			return prodImageUrl;
		}
		public void setProdImageUrl(String prodImageUrl) {
			this.prodImageUrl = prodImageUrl;
		}
		public String getProdDescription() {
			return prodDescription;
		}
		public void setProdDescription(String prodDescription) {
			this.prodDescription = prodDescription;
		}
		public String getProdHeadline() {
			return prodHeadline;
		}
		public void setProdHeadline(String prodHeadline) {
			this.prodHeadline = prodHeadline;
		}
		@Override
		public String toString() {
			return "OrderItem [id=" + id + ", fromOrder=" + fromOrder + ", prodHeadline=" + prodHeadline
					+ ", prodDescription=" + prodDescription + ", prodImageUrl=" + prodImageUrl + ", prodPrice="
					+ prodPrice + ", prodQuantity=" + prodQuantity + "]";
		}
		
		
}
