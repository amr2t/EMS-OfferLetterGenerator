package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import com.example.demo.dto.AuthRequest;
import com.example.demo.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public User register(@RequestBody User user) throws Exception {
		return Uservice.saveNewUser(user);
	}
	
	@PostMapping("/login")
	public User login(@RequestBody User user) throws Exception {
		return Uservice.getOldUser(user);
	}

	
	@GetMapping("{id}/getCart")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Cart getCart(@PathVariable("id") UUID id){
		return Uservice.getUserCart(id);
	}
	
	@PostMapping("/addProduct")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Product addProduct(@RequestBody Product product) throws Exception {
		return Uservice.addProduct(product);
	}
	
	@GetMapping("/getProducts")

	public List<Product> fetchAllProducts(){
		return Uservice.findAllProduct();
	}
	
	@GetMapping("/getUsers")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<User> findAllUser(){
		return Uservice.findAllUsers();
	}
	
	@GetMapping("/{id}/getUser")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public User findUser(@PathVariable("id")UUID id) {
		return Uservice.getUser(id);
	}
	
	@GetMapping("/{id}/getProduct")
	public Product findProduct(@PathVariable("id")UUID id) throws Exception {
		return Uservice.getProduct(id);
	}
	
	@PutMapping("/{id}/updateUser")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public User updateUser(@PathVariable("id")UUID id,@RequestBody User user) throws Exception {
		Uservice.updateUser(id,user);
		return Uservice.getUser(id);
	}
	
	@PutMapping("/{id}/updateProduct")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Product updateProduct(@PathVariable("id")UUID id,@RequestBody Product product) throws Exception{
		return Uservice.updateProduct(id,product);
	}
	
	@DeleteMapping("/{id}/deleteUser")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String deleteUser(@PathVariable("id")UUID id) throws Exception {
		return Uservice.deleteFromUser(id);
	}
	
	@DeleteMapping("/{id}/deleteProduct")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String deleteProduct(@PathVariable("id")UUID id) throws Exception {
		return Uservice.deleteFromProduct(id);
	}
	
	@GetMapping("/{id}/addToCart/{prodid}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Cart addToCart(@PathVariable("id") UUID id,@PathVariable("prodid") UUID productId) 
			throws Exception {
		return Uservice.addProductToCart(id,productId);
	}
	
	@GetMapping("/{id}/deleteFromCart/{prodid}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String deleteFromCart(@PathVariable("id") UUID id,@PathVariable("prodid") UUID productId) 
			throws Exception {
		return Uservice.deleteProductFromCart(id,productId);
	}
	
	@GetMapping("/{id}/paymentSuccessful")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public CustomerOrder paymentSuccessful(@PathVariable("id")UUID id) throws Exception {
		return Uservice.fetchOrderDetails(id);
	}
	
	@GetMapping("/{id}/getAllOrders")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<CustomerOrder> getUserOrders(@PathVariable("id")UUID id) throws Exception{
		return Uservice.fetchAllOrders(id);
	}
	
	@GetMapping("/getOrders")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<CustomerOrder> getAllOrders() throws Exception{
		return Uservice.getAllOrders();
	}

	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if(authentication.isAuthenticated()){
			return jwtService.generateToken(authRequest.getUsername());
		}else {
			throw new UsernameNotFoundException("invalid user request !");
		}

	}

}
