package com.qualitybazar.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qualitybazar.exception.OrderException;
import com.qualitybazar.model.Address;
import com.qualitybazar.model.Cart;
import com.qualitybazar.model.CartItem;
import com.qualitybazar.model.Order;
import com.qualitybazar.model.OrderItem;
import com.qualitybazar.model.User;
import com.qualitybazar.repository.AddressRepository;
import com.qualitybazar.repository.CartRepository;
import com.qualitybazar.repository.OrderItemRepository;
import com.qualitybazar.repository.OrderRepository;
import com.qualitybazar.repository.UserRepository;

@Service
public class OrderServiceImplementation implements OrderService {
	
	
	private OrderRepository orderRepository;
	private CartRepository cartRepository;
	private CartService cartService;
	private ProductService productService;
	private AddressRepository addressRepository;
	private OrderItemService orderItemService;
	private OrderItemRepository orderItemRepository;
	private UserRepository userRepository;
	
	
	public OrderServiceImplementation(CartRepository cartRepository,CartService cartService,ProductService productService,
			OrderRepository orderRepository,AddressRepository addressRepository,OrderItemService orderItemService,OrderItemRepository orderItemRepository,
			UserRepository userRepository) {
		this.cartRepository=cartRepository;
		this.cartService=cartService;
		this.productService=productService;
		this.orderRepository=orderRepository;
		this.addressRepository=addressRepository;
		this.orderItemService=orderItemService;
		this.orderItemRepository=orderItemRepository;
		this.userRepository=userRepository;
		
	}

	@Override
	public Order createOrder(User user, Address shippingAddress) {
		shippingAddress.setUser(user);
		Address address=addressRepository.save(shippingAddress);
		user.getAddress().add(address);
		userRepository.save(user);
		
		Cart cart=cartService.findUserCart(user.getId());
		List<OrderItem>orderItems=new ArrayList<>();
		
		for(CartItem item:cart.getCartItems()) {
			OrderItem orderItem=new OrderItem();
			
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());
			
			OrderItem createdOrderItem=orderItemRepository.save(orderItem);
			
			orderItems.add(createdOrderItem);
		}
		
		
		
		Order createdOrder=new Order();
		createdOrder.setUser(user);
		createdOrder.setOrderItems(orderItems);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		createdOrder.setDisocunte(cart.getDiscounte());
		createdOrder.setTotalItem(cart.getTotalItem());
		
		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.getPaymentDetails().setStatus("Pending");;
		createdOrder.setCreatedAt(LocalDateTime.now());
		
		Order savedOrder=orderRepository.save(createdOrder);
		
		for(OrderItem item:orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
			
		}
		return savedOrder;
	}

	@Override
	public Order findOrderById(Long orderId) throws OrderException {
       Optional<Order> opt=orderRepository.findById(orderId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new OrderException("order not exist with this id"+orderId);
	}

	@Override
	public List<Order> usersOrderHistory(Long userId) {
		List<Order>orders=orderRepository.getUsersOrders(userId);
		return orders;
	}

	@Override
	public Order placedOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("PLACED");
		order.getPaymentDetails().setStatus("Completed");;
		return order;
	}

	@Override
	public Order confirmedOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("CONFIRMED");
		
		return orderRepository.save(order);
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("SHIPPED");
		
		return orderRepository.save(order);
	}

	@Override
	public Order deliveredOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("DELIVERED");
		
		return orderRepository.save(order);
	}

	@Override
	public Order cancledOrder(Long longId) throws OrderException {
		Order order=findOrderById(longId);
		order.setOrderStatus("CANCELLED");
		
		return orderRepository.save(order);
	}

	@Override
	public void deleteOrder(Long orderId)throws OrderException {
     Order order=findOrderById(orderId);
		
      orderRepository.deleteById(orderId);
		
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	
	

}
