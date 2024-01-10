package com.qualitybazar.service;

import com.qualitybazar.exception.ProductException;
import com.qualitybazar.model.Cart;
import com.qualitybazar.model.User;
import com.qualitybazar.request.AddItemRequest;

public interface CartService {
	
	
	
	public Cart createCart(User user);
	
	public String addCartItem(Long userId,AddItemRequest req)throws ProductException;
	
	public Cart findUserCart(Long userId);
	
	
	

}
