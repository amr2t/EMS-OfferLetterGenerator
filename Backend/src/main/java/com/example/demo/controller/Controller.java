package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import com.example.demo.dto.AuthRequest;
import com.example.demo.service.JwtService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.fasterxml.jackson.databind.util.JSONPObject;

@RestController
public class Controller {
	
	@Autowired
	private UserService Uservice;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
//	@CrossOrigin("http://localhost:4200")
	public User register(@RequestBody User user) throws Exception {
		return Uservice.saveNewUser(user);
	}
	
	@PostMapping("/signin")
//	@CrossOrigin("http://localhost:4200")
	public User login(@RequestBody User user) throws Exception {
		return Uservice.getOldUser(user);
	}
	
	@PostMapping("/getId")
//	@CrossOrigin("http://localhost:4200")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public User getDetails(@RequestBody User user) {
		return Uservice.getUserDetails(user);
	}

	
	@GetMapping("/{id}/getCart")
//	@CrossOrigin("http://localhost:4200")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Cart getCart(@PathVariable("id") UUID id){
		return Uservice.getUserCart(id);
	}
	
	@PostMapping("/addProduct")
//	@CrossOrigin("http://localhost:4200")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Product addProduct(@RequestBody Product product) throws Exception {
		return Uservice.addProduct(product);
	}
	
	@GetMapping("/getProducts")
//	@CrossOrigin("http://localhost:4200")
	public List<Product> fetchAllProducts(){
		return Uservice.findAllProduct();
	}
	
	@GetMapping("/getUsers")
//	@CrossOrigin("http://localhost:4200")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<User> findAllUser(){
		return Uservice.findAllUsers();
	}
	
	@GetMapping("/{id}/getUser")
//	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public User findUser(@PathVariable("id")UUID id) {
		return Uservice.getUser(id);
	}
	
	@GetMapping("/{id}/getProduct")
//	@CrossOrigin("http://localhost:4200")
	public Product findProduct(@PathVariable("id")UUID id) throws Exception {
		return Uservice.getProduct(id);
	}
	
	@PutMapping("/{id}/updateUser")
//	@CrossOrigin("http://localhost:4200")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public User updateUser(@PathVariable("id")UUID id,@RequestBody User user) throws Exception {
		Uservice.updateUser(id,user);
		return Uservice.getUser(id);
	}
	
	@PutMapping("/{id}/updateProduct")
//	@CrossOrigin("http://localhost:4200")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Product updateProduct(@PathVariable("id")UUID id,@RequestBody Product product) throws Exception{
		return Uservice.updateProduct(id,product);
	}
	
	@DeleteMapping("/{id}/deleteUser")
//	@CrossOrigin("http://localhost:4200")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String deleteUser(@PathVariable("id")UUID id) throws Exception {
		return Uservice.deleteFromUser(id);
	}
	
	@DeleteMapping("/{id}/deleteProduct")
//	@CrossOrigin("http://localhost:4200")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String deleteProduct(@PathVariable("id")UUID id) throws Exception {
		return Uservice.deleteFromProduct(id);
	}
	
	@GetMapping("/{id}/addToCart/{prodid}")
//	@CrossOrigin("http://localhost:4200")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Cart addToCart(@PathVariable("id") UUID id,@PathVariable("prodid") UUID productId) 
			throws Exception {
		return Uservice.addProductToCart(id,productId);
	}
	
	@GetMapping("/{id}/deleteFromCart/{prodid}")
//	@CrossOrigin("http://localhost:4200")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String deleteFromCart(@PathVariable("id") UUID id,@PathVariable("prodid") UUID productId) 
			throws Exception {
		return Uservice.deleteProductFromCart(id,productId);
	}
	
	@GetMapping("/{id}/paymentSuccessful")
//	@CrossOrigin("http://localhost:4200")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public CustomerOrder paymentSuccessful(@PathVariable("id")UUID id) throws Exception {
		return Uservice.fetchOrderDetails(id);
	}
	
	@GetMapping("/{id}/getAllOrders")
//	@CrossOrigin("http://localhost:4200")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<CustomerOrder> getUserOrders(@PathVariable("id")UUID id) throws Exception{
		return Uservice.fetchAllOrders(id);
	}
	
	@GetMapping("/getOrders")
//	@CrossOrigin("http://localhost:4200")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<CustomerOrder> getAllOrders() throws Exception{
		return Uservice.getAllOrders();
	}

	@PostMapping("/authenticate")
//	@CrossOrigin("http://localhost:4200")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws Exception{
		
		User tempUser = new User(); 
		tempUser.setEmail(authRequest.getUsername());
		tempUser.setPassword(authRequest.getPassword());
		User tempUsertwo = Uservice.getOldUser(tempUser);
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		JSONObject tempJson=new JSONObject();
		String temp = null;
		if(authentication.isAuthenticated()){
			temp = jwtService.generateToken(authRequest.getUsername());
			tempJson.put("token", temp);
			tempJson.put("userId", tempUsertwo.getId());
			tempJson.put("roles", tempUsertwo.getRoles());
			tempJson.put("name", tempUsertwo.getName());
			tempJson.put("email", tempUsertwo.getEmail());
			tempJson.put("address", tempUsertwo.getAddress());
			tempJson.put("phone", tempUsertwo.getPhone());
			return tempJson.toString();
//			final String token =  jwtService.generateToken(authRequest.getUsername());
//			return ResponseEntity.ok(new JwtResponse(token));
//			return "\""+jwtService.generateToken(authRequest.getUsername())+"\"";
		}else {
			throw new UsernameNotFoundException("invalid user request !");
		}

	}

}
