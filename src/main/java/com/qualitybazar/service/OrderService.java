package com.qualitybazar.service;

import java.util.List;

import com.qualitybazar.exception.OrderException;
import com.qualitybazar.model.Address;
import com.qualitybazar.model.Order;
import com.qualitybazar.model.User;

public interface OrderService {
	
	public Order createOrder(User user,Address shippingAddress);
	
	public Order findOrderById(Long orderId)throws OrderException;
	
	public List<Order> usersOrderHistory(Long userId);
	
	public Order placedOrder(Long orderId)throws OrderException;
	
	public Order confirmedOrder(Long orderId)throws OrderException;
	
	public Order shippedOrder(Long orderId)throws OrderException;
	
	public Order deliveredOrder(Long orderId)throws OrderException;
	
	public Order cancledOrder(Long longId)throws OrderException;

	public void deleteOrder(Long orderId)throws OrderException;

	public List<Order>getAllOrders();

	
	
	

}
