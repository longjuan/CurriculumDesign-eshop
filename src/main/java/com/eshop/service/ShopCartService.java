package com.eshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eshop.entity.CartProduct;

@Service
public interface ShopCartService {
	public List<CartProduct> addCartProduct(int id,int quantity,List<CartProduct> cart);
	public List<CartProduct> getDBCart(String username);
	public List<CartProduct> fillInfo(List<CartProduct> cart);
	public List<CartProduct> delectCartProduct(int id, List<CartProduct> cart);
	public void saveCart(String username,List<CartProduct> cart);
}
