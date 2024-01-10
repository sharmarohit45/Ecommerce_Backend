package com.qualitybazar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qualitybazar.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{

}
