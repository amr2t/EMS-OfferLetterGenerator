package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CustomerOrder;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
public class Controller {
	
	@Autowired
	private UserService Uservice;
	
	@PostMapping("/register")
	public User register(@RequestBody User user) throws Exception {
		return Uservice.saveNewUser(user);
	}
	
	@PostMapping("/login")
	public User login(@RequestBody User user) throws Exception {
		return Uservice.getOldUser(user);
	}
	
	
	@GetMapping("{id}/getCart")
	public Cart getCart(@PathVariable("id") UUID id){
		return Uservice.getUserCart(id);
	}
	
	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product) throws Exception {
		return Uservice.addProduct(product);
	}
	
	@GetMapping("/getProducts")
	public List<Product> fetchAllProducts(){
		return Uservice.findAllProduct();
	}
	
	@GetMapping("/getUsers")
	public List<User> findAllUser(){
		return Uservice.findAllUsers();
	}
	
	@GetMapping("/{id}/getUser")
	public User findUser(@PathVariable("id")UUID id) {
		return Uservice.getUser(id);
	}
	
	@GetMapping("/{id}/getProduct")
	public Product findProduct(@PathVariable("id")UUID id) throws Exception {
		return Uservice.getProduct(id);
	}
	
	@PutMapping("/{id}/updateUser")
	public User updateUser(@PathVariable("id")UUID id,@RequestBody User user) throws Exception {
		Uservice.updateUser(id,user);
		return Uservice.getUser(id);
	}
	
	@PutMapping("/{id}/updateProduct")
	public Product updateProduct(@PathVariable("id")UUID id,@RequestBody Product product) throws Exception{
		return Uservice.updateProduct(id,product);
	}
	
	@DeleteMapping("/{id}/deleteUser")
	public String deleteUser(@PathVariable("id")UUID id) throws Exception {
		return Uservice.deleteFromUser(id);
	}
	
	@DeleteMapping("/{id}/deleteProduct")
	public String deleteProduct(@PathVariable("id")UUID id) throws Exception {
		return Uservice.deleteFromProduct(id);
	}
	
	@GetMapping("/{id}/addToCart/{prodid}")
	public Cart addToCart(@PathVariable("id") UUID id,@PathVariable("prodid") UUID productId) 
			throws Exception {
		return Uservice.addProductToCart(id,productId);
	}
	
	@GetMapping("/{id}/deleteFromCart/{prodid}")
	public String deleteFromCart(@PathVariable("id") UUID id,@PathVariable("prodid") UUID productId) 
			throws Exception {
		return Uservice.deleteProductFromCart(id,productId);
	}
	
	@GetMapping("/{id}/paymentSuccessful")
	public CustomerOrder paymentSuccessful(@PathVariable("id")UUID id) throws Exception {
		return Uservice.fetchOrderDetails(id);
	}
	
	@GetMapping("/{id}/getAllOrders")
	public List<CustomerOrder> getUserOrders(@PathVariable("id")UUID id) throws Exception{
		return Uservice.fetchAllOrders(id);
	}
	
	@GetMapping("/getOrders")
	public List<CustomerOrder> getAllOrders() throws Exception{
		return Uservice.getAllOrders();
	}
}
