package com.eshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eshop.entity.CartProduct;
import com.eshop.entity.Order;

@Service
public interface OrderService {
	public boolean placeOrder(List<CartProduct> cart);
	public void saveOrder(Order order);
	public List<Order> getOrder(String username);
}
