package com.eshop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.dao.ProductDao;
import com.eshop.dao.ShopCartDao;
import com.eshop.entity.CartProduct;
import com.eshop.service.ShopCartService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class ShopCartServiceImpl implements ShopCartService {
	@Autowired
	private ShopCartDao shopCartDao;
	@Autowired
	private ProductDao productDao;

	@Override
	public List<CartProduct> addCartProduct(int id, int quantity, List<CartProduct> cart) {
		long count = cart.stream()
				.filter(a->a.getId().intValue()==id)
				.peek(a->a.setQuantity(a.getQuantity()+quantity)).count();
		if (count==0) {
			cart.add(new CartProduct(id,quantity,null));
		}
		return cart;
	}

	@Override
	public List<CartProduct> getDBCart(String username) {
		String json = shopCartDao.getCartProduct(username);
		ArrayList<CartProduct> ret;
		if(json == null) {
			ret = new ArrayList<CartProduct>();
		}else {
			ret = new Gson().fromJson(json, new TypeToken<ArrayList<CartProduct>>(){}.getType());
		}
		return ret;
	}

	@Override
	public List<CartProduct> fillInfo(List<CartProduct> cart) {
		cart.stream().forEach(a->a.setProduct(productDao.getBaseInfo(a.getId())));
		return cart;
	}

	@Override
	public List<CartProduct> delectCartProduct(int id, List<CartProduct> cart) {
		return cart.stream().filter(a->a.getId().intValue()!=id)
				.collect(Collectors.toList());
	}

	@Override
	public void saveCart(String username, List<CartProduct> cart) {
		cart.forEach(a->a.simplify());
		Map<String, String> map = new HashMap<String, String>();
		String json = new Gson().toJson(cart);
		map.put("json", json);
		map.put("username", username);
		shopCartDao.delectCart(username);
		shopCartDao.saveCart(map);
	}

}
