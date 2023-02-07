package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductRepo;
import com.example.demo.entity.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo repo;
	
	@Autowired
	private CartItemService CIservice;

	public Product addProduct(Product product) throws Exception {
		Product tempProduct = repo.findByHeadline(product.getHeadline());
		if(tempProduct!=null) {
			throw new Exception("Product with this headline already exists");
		}
		return repo.save(product);
	}

	public Product findById(UUID productId) {
		return repo.findById(productId).orElse(null);
	}

	public List<Product> findAllProducts() {
		return repo.findAll();
	}

	public String deleteFromProduct(UUID id) throws Exception{
		Product tempProduct = repo.findById(id).orElse(null);
		if(tempProduct==null) {
			throw new Exception("Product does not exists");
		}
		CIservice.deleteAllItemByProduct(tempProduct);
		repo.deleteById(id);
		
		String success = "Successfully deleted product ";
		return "\""+success+"\"";
	}

	public Product findProduct(UUID id) throws Exception{
		Product tempProduct = repo.findById(id).orElse(null);
		if(tempProduct==null) {
			throw new Exception("Product not found ");
		}
		return tempProduct;
	}

	public Product updateProduct(UUID id, Product product) throws Exception {
		Product tempProduct = repo.findById(id).orElse(null);
		if(tempProduct==null) {
			throw new Exception("Product not exists ");
		}
//		product.setId(id);
		if(product.getHeadline()!=null) {
			repo.updateHeadline(id,product.getHeadline());
//			product.setHeadline(tempProduct.getHeadline());
		}
		if(product.getDescription()!=null) {
			repo.updateDescription(id,product.getDescription());
//			product.setDescription(tempProduct.getDescription());
		}
		if(product.getClass()!=null) {
			repo.updateStock(id,product.getStock());
//			product.setStock(tempProduct.getStock());
		}
		if(product.getPrice()!=0) {
			repo.updatePrice(id,product.getPrice());
//			product.setPrice(tempProduct.getPrice());
		}
		if(product.getImageurl()!=null) {
			repo.updateImageurl(id,product.getImageurl());
//			product.setImageurl(tempProduct.getImageurl());
		}
		
//		deleteFromProduct(id);
		tempProduct = repo.findById(id).orElse(null);
		return repo.findById(id).orElse(null);
		
	}
	
	
}
