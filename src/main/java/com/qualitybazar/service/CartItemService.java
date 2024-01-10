package com.qualitybazar.service;

import com.qualitybazar.exception.CartItemException;
import com.qualitybazar.exception.UserException;
import com.qualitybazar.model.Cart;
import com.qualitybazar.model.CartItem;
import com.qualitybazar.model.Product;

public interface CartItemService {
	
	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId,Long id,CartItem cartItem)throws CartItemException,UserException;
	
	public CartItem isCartItemExist(Cart cart,Product product,String size,Long userId);
	
	public void removeCartItem(Long userId,Long cartItemId)throws CartItemException,UserException;
	
	public CartItem findCartItemById(Long cartItemId)throws CartItemException;
	

}
