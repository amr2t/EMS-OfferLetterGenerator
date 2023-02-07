package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CartItemRepo;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;

@Service
public class CartItemService {
	
	@Autowired
	private CartItemRepo repo;
	
	@Autowired
	private CartService Cservice;

	public CartItem saveCartItem(CartItem tempCartItem) {
		return repo.save(tempCartItem);
	}

	public String deleteCartItem(UUID id) throws Exception {
		CartItem tempCartItem = repo.findById(id).orElse(null);
		if(tempCartItem==null) {
			throw new Exception("Product is not present in the cart");
		}
		repo.deleteById(id);
		String success = "Successfully Deleted";
		return "\""+success+"\"";
	}

	public String deleteAllItemByProduct(Product tempProduct) {
		// TODO Auto-generated method stub
		List<CartItem> itemList = repo.findAllByProduct(tempProduct);
		
		for(int i=0;i<itemList.size();i++){
			UUID tempCartId = itemList.get(i).getFromCart().getId();
			try {
				deleteCartItem(itemList.get(i).getId());
				Cservice.deleteItemFromCart(tempCartId,itemList.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			Cservice.deleteItemFromCart(tempCartId, item);
		};
		String success = "Successfully deleted";
		return "\""+success+"\"";
		
	}

	

	
}
