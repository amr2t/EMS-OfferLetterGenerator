package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CartRepo;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;

@Service
public class CartService {
	
	@Autowired
	private CartRepo repo;
	
	@Autowired
	private CartItemService CIservice;
	
	public Cart saveCart(Cart tempCart) {
		return repo.save(tempCart);
	}

	public CartItem findItem(UUID id, Product product) throws Exception {
		Cart tempCart = repo.findById(id).orElse(null);
		List<CartItem> list = tempCart.getItem();
		
		CartItem tempCartItem = list.stream().filter((i)->{
			return i.getProduct()==product;
		}).findAny().orElse(null);
		
		
		if(tempCartItem==null) {
			throw new Exception("Item not present in cart");
		}
		return tempCartItem;
	}
	
	
	public boolean fetchItem(UUID id,Product tempProd) {
		Cart tempCart = repo.findById(id).orElse(null);
		List<CartItem> list = tempCart.getItem();
		CartItem tempCartItem = list.stream().filter((i)->{
			return i.getProduct()==tempProd;
		}).findAny().orElse(null);
		if(tempCartItem==null)	return false;
		else	return true;
		
	}
//	public String findItemFromCart(int id, Product tempProduct) throws Exception{
//		CartItem tempCartItem = findItem(id,tempProduct);
//		
//	}

//	private CartItem findItemByCardId(int id, Product tempProduct) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public void deleteItemFromCart(UUID id,CartItem tempCartItem) {
		repo.findById(id).orElse(null).getItem().remove(tempCartItem);
		
//		Cart tempCart = repo.findById(id);
//		List<CartItem> list = tempCart.getItem();
//		
//		list.remove(tempCartItem);
//		tempCart.setItem(list);
//		repo.deleteById(id);
		
		
	}

	public String deleteAllItemFromCart(UUID id) throws Exception {
		Cart tempCart = repo.findById(id).orElse(null);
		List<CartItem> list = tempCart.getItem();
		list.forEach(i->{
			try {
				CIservice.deleteCartItem(i.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		repo.deleteById(id);
		String success = "Successfully deleted";
		return "\""+success+"\"";
	}
	
	public String deleteOnlyItemFromCart(UUID id) throws Exception {
		Cart tempCart = repo.findById(id).orElse(null);
		List<CartItem> list = tempCart.getItem();
		list.forEach(i->{
			try {
				CIservice.deleteCartItem(i.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
//		repo.deleteById(id);
		String success = "Successfully deleted";
		return "\""+success+"\"";
	}

	public Cart findById(UUID id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}
}
