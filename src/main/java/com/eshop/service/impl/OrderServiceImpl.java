package com.eshop.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.dao.OrderDao;
import com.eshop.dao.ProductDao;
import com.eshop.entity.CartProduct;
import com.eshop.entity.Order;
import com.eshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private OrderDao orderdDao;

	@Override
	public boolean placeOrder(List<CartProduct> cart) {
		for (CartProduct cartProduct : cart) {
			if(cartProduct.getProduct()==null || cartProduct.getQuantity()>cartProduct.getProduct().getStock()) {
				return false;
			}
		}
		Map<String, Object> map;
		int i = 0;
		boolean flag = true;
		for (; i < cart.size(); i++) {
			CartProduct cartProduct = cart.get(i);
			map = new HashMap<String, Object>();
			map.put("last", cartProduct.getProduct().getStock()-cartProduct.getQuantity());
			map.put("id", cartProduct.getProduct().getId());
			map.put("version", cartProduct.getProduct().getVersion());
			map.put("quantity", cartProduct.getQuantity());
			if(productDao.reduceInventory(map) <= 0) {
				flag = false;
				break;
			}
		}
		if(!flag) {
			int end = i;
			new Thread(()->{
				for (int j = 0; j < end; j++) {
					productDao.rollback(cart.get(j));
				}
			}).start();
		}
		return flag;
	}

	@Override
	public void saveOrder(Order order) {
		orderdDao.saveOrder(order);
		order.getProducts().forEach(a->orderdDao.saveOrderProducts(a));
	}

	@Override
	public List<Order> getOrder(String username) {
		List<Order> order = orderdDao.getOrder(username);
		order.forEach(a->a.setProducts(orderdDao.getOrderProduct(a.getOrderid())));
		Collections.reverse(order);
		return order;
	}

}
