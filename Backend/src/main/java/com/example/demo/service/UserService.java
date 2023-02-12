package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepo;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.CustomerOrder;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;

@Service
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private ProductService Pservice;
	
	@Autowired
	private CartItemService CIservice;
	
	@Autowired
	private CartService Cservice;
	
	@Autowired
	private CustomerOrderService COservice;
	
	public User saveNewUser(User user) throws Exception{
		User tempObj = repo.findByEmail(user.getEmail());
		if(tempObj!=null) {
			throw new Exception("This user already exists ");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if(user.getCart()==null) {
			Cart tempCart = new Cart();
			Cservice.saveCart(tempCart);
			user.setCart(tempCart);
		}
		return repo.save(user);
	}

	public User getOldUser(User user) throws Exception{
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User tempObj = repo.findByEmail(user.getEmail());
		if(tempObj==null) {
			throw new Exception("User didn't exists");
		}
//		else if(!user.getPassword().equals(tempObj.getPassword())) {
		else if(!passwordEncoder.matches(user.getPassword(), tempObj.getPassword())) {
			throw new Exception("Bad credentials");
		}

		return tempObj;
	}

	public Cart getUserCart(UUID id) {
		User tempUser = repo.findById(id).orElse(null);
		Cart tempObj = tempUser.getCart();
		return tempObj;
	}

	public Product addProduct(Product product) throws Exception{
		return Pservice.addProduct(product);
	}

	public Cart addProductToCart(UUID id, UUID productId) throws Exception{		
		User tempUser = repo.findById(id).orElse(null);
		if(tempUser==null) {
			throw new Exception("User did't exists");
		}
		Product tempProd = Pservice.findById(productId);
		if(tempProd==null) {
			throw new Exception("Product not found");
		}
		
		if(Cservice.fetchItem(tempUser.getCart().getId(), tempProd)) {
			throw new Exception("Product already exists in cart");
		}
		Cart tempCart = tempUser.getCart();
		CartItem tempCartItem = new CartItem();
		tempCartItem.setFromCart(tempCart);
		tempCartItem.setProduct(tempProd);
		tempCartItem.setQuantity(1);
		List<CartItem> tempCartItemList = tempCart.getItem();
		if(tempCartItemList==null) {
			tempCartItemList = new ArrayList<CartItem>();
		}
		tempCartItemList.add(tempCartItem);
		tempCart.setItem(tempCartItemList);
//		Cservice.saveCart(tempCart);
		CIservice.saveCartItem(tempCartItem);
		tempUser.setCart(tempCart);
//		Cservice.findById(tempCart.getId()).getItem().add(tempCartItem);
		return (repo.findById(id).orElse(null)).getCart();
	}

	public List<Product> findAllProduct() {
		return Pservice.findAllProducts();
	}

	public List<User> findAllUsers() {
		return repo.findAll();
	}

	public String deleteFromUser(UUID id) throws Exception {
		User tempUser = repo.findById(id).orElse(null);
		if(tempUser==null) {
			throw new Exception("User does not exists");
		}
		repo.deleteById(id);
		Cservice.deleteAllItemFromCart(tempUser.getCart().getId());
		String success = "Successfully Deleted";
		return "\""+success+"\"";
	}

	public String deleteFromProduct(UUID id) throws Exception{
		return Pservice.deleteFromProduct(id);
	}

	public User getUser(UUID id) {
		return repo.findById(id).orElse(null);
	}

	public Product getProduct(UUID id) throws Exception {
		return Pservice.findProduct(id);
	}

	public User updateUser(UUID id, User user) throws Exception {
		User tempUser = repo.findById(id).orElse(null);
		int temp;
		if(tempUser==null) {
			throw new Exception("User not present");
		}
		if(user.getName()!=null) {
			temp=repo.updateName(id,user.getName());
			System.out.println(repo.findById(id));
		}
		if(user.getAddress()!=null) {
			temp=repo.updateAddress(id,user.getAddress());
			System.out.println(repo.findById(id));
		}
		if(user.getPhone()!=0L) {
			temp=repo.updatePhone(id,user.getPhone());
			System.out.println(repo.findById(id));
		}
		if(user.getPassword()!=null) {
			temp=repo.updatePassword(id,user.getPassword());
			System.out.println(repo.findById(id));
		}
		if(user.getRoles()!=null) {
			temp=repo.updateRoles(id,user.getRoles());
			System.out.println(repo.findById(id));
		}
//		tempUser = repo.findById(id);
		return fetchById(id);
	}

	private User fetchById(UUID id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	public Product updateProduct(UUID id, Product product) throws Exception {
		return Pservice.updateProduct(id,product);
	}

	public String deleteProductFromCart(UUID id, UUID productId) throws Exception {
		User tempUser = repo.findById(id).orElse(null);
		if(tempUser == null) {
			throw new Exception("User didn't exists");
		}
		Product tempProduct = Pservice.findById(productId);
		if(tempProduct == null) {
			throw new Exception("Product didn't exists");
		}
		
		CartItem tempCartItem = Cservice.findItem(tempUser.getCart().getId(),tempProduct);
		
		
		CIservice.deleteCartItem(tempCartItem.getId());
		Cservice.deleteItemFromCart(tempUser.getCart().getId(),tempCartItem);
		String success = "Successfully Deleted";
		return "\""+success+"\"";
	}

	public CustomerOrder fetchOrderDetails(UUID id) throws Exception{
		User tempUser = repo.findById(id).orElse(null);
		Cart tempCart = tempUser.getCart();
		if(tempCart == null) {
			throw new Exception("No item exists in Cart");
		}
		CustomerOrder tempCustomerOrder = COservice.saveOrder(tempCart,tempUser);
//		List<CustomerOrder> list = COservice.saveOrder(tempCart,tempUser);
		repo.findById(id).orElse(null).getOrderList().add(tempCustomerOrder);
		Cservice.deleteOnlyItemFromCart(tempUser.getCart().getId());
		repo.findById(id).orElse(null).setCart(new Cart());
		return tempCustomerOrder;
	}

	public List<CustomerOrder> fetchAllOrders(UUID id) throws Exception {
		User tempUser = repo.findById(id).orElse(null);
		if(tempUser == null) {
			throw new Exception("User didn't exists");
		}
		return tempUser.getOrderList();
	}

	public List<CustomerOrder> getAllOrders() {
		
		return COservice.getAllOrders();
	}

	public User getUserDetails(User user) {
		String email=user.getEmail();
		
		return repo.findByEmail(email);
	}
}
