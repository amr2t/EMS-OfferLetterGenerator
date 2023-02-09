package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderItemRepo;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CustomerOrder;
import com.example.demo.entity.OrderItem;

@Service
public class OrderItemService {
	
	@Autowired
	private OrderItemRepo repo;
	
	public OrderItem save(OrderItem tempObj) {
		return repo.save(tempObj);
	}
	public void updateItem(CustomerOrder tempCustomerOrdertwo, OrderItem i) {
		repo.findById(i.getId()).orElse(null).setFromOrder(tempCustomerOrdertwo);
	}

//	public List<OrderItem> saveNewItems(CustomerOrder tempCustomerOrder,Cart tempCart) {
//		List<OrderItem> tempOrderItemList = tempCustomerOrder.getOrderItems();
//		if(tempOrderItemList == null) {
//			tempOrderItemList = new ArrayList<OrderItem>();
//		}
//		
//		tempCart.getItem().forEach((i)->{
//			OrderItem tempOrderItem = new OrderItem();
//			tempOrderItem.setFromOrder(tempCustomerOrder);
//			tempOrderItem.setProdDescription(i.getProduct().getDescription());
//			tempOrderItem.setProdHeadline(i.getProduct().getHeadline());
//			tempOrderItem.setProdImageUrl(i.getProduct().getImageurl());
//			tempOrderItem.setProdPrice(i.getProduct().getPrice());
//			tempOrderItem.setProdQuantity(i.getQuantity());
//			
////			tempOrderItemList.add(save(tempOrderItem));
//		});
//		return tempOrderItemList;
//	}


}
