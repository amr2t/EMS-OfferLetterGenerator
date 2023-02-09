package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerOrderRepo;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CustomerOrder;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.User;

@Service
public class CustomerOrderService {
	
	@Autowired
	private CustomerOrderRepo repo;
	
	@Autowired
	private OrderItemService OIservice;
	
	int count;

	public CustomerOrder saveOrder(Cart tempCart,User tempUser) throws Exception {
		
//		List<CustomerOrder> orderList = tempUser.getOrderList();
//		if(orderList==null) {
//			orderList = new ArrayList<CustomerOrder>();
//		}
//		
//		CustomerOrder tempCustomerOrder = new CustomerOrder();
//		
//		tempCustomerOrder.setFromUser(tempUser);
//		tempCustomerOrder.setOrderDate(new Date());
//		
//		List<OrderItem> tempOrderItemList = OIservice.saveNewItems(tempCustomerOrder,tempCart);
		
		
		
		
		CustomerOrder tempCustomerOrder = new CustomerOrder();
		tempCustomerOrder.setFromUser(tempUser);
		tempCustomerOrder.setOrderDate(new Date());
		
		List<CustomerOrder> list = tempUser.getOrderList();
		if(list==null) {
			list = new ArrayList<CustomerOrder>();
		}
		list.add(tempCustomerOrder);
		
		List<OrderItem> orderList = new ArrayList<OrderItem>();
		tempCart.getItem().forEach((i)->{
			
			OrderItem tempObj = new OrderItem();
			tempObj.setProdHeadline(i.getProduct().getHeadline());
			tempObj.setProdDescription(i.getProduct().getDescription());
			tempObj.setProdImageUrl(i.getProduct().getImageurl());
			tempObj.setProdPrice(i.getProduct().getPrice());
			
			if(i.getQuantity()>i.getProduct().getStock()) {
				try {
					throw new Exception("You have added quantity more than stock");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}else {
				tempObj.setProdQuantity(i.getQuantity());
				count += tempObj.getProdPrice()*tempObj.getProdQuantity();
			}
//			tempObj.setFromOrder(tempCustomerOrder);
			orderList.add(OIservice.save(tempObj));
			
		});
		tempCustomerOrder.setOrderItems(orderList);
		tempCustomerOrder.setTotalPay(count);
		
//		tempCustomerOrder.setFromUser(tempUser);
		CustomerOrder tempCustomerOrdertwo = repo.save(tempCustomerOrder);
		orderList.forEach((i)->{
			OIservice.updateItem(tempCustomerOrdertwo,i);
		});
		count=0;
		return tempCustomerOrdertwo;
	}

	public List<CustomerOrder> getAllOrders() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
}
