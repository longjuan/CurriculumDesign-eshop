package com.eshop.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Order {
	private String username;
	private String orderid;
	private Date date;
	private Integer tprice;
	private List<OrderProduct> products;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<OrderProduct> getProducts() {
		return products;
	}
	public void setProducts(List<OrderProduct> products) {
		this.products = products;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public Integer getTprice() {
		return tprice;
	}
	public void setTprice(Integer tprice) {
		this.tprice = tprice;
	}
	public Order(String username, String orderid, Date date, Integer tprice, List<OrderProduct> products) {
		super();
		this.username = username;
		this.orderid = orderid;
		this.date = date;
		this.tprice = tprice;
		this.products = products;
	}
	public Order() {
		super();
	}
	public Order(List<CartProduct> list,String username) {
		this.username = username;
		this.orderid = ""+(int)username.charAt(0)+System.currentTimeMillis()+username.length();
		this.date = new Date();
		this.products = new ArrayList<OrderProduct>();
		this.tprice = 0;
		for (CartProduct cartProduct : list) {
			products.add(new OrderProduct(cartProduct, this.orderid));
			this.tprice += cartProduct.getProduct().getPrice()*cartProduct.getQuantity();
		}
	}
	@Override
	public String toString() {
		return "Order [username=" + username + ", orderid=" + orderid + ", date=" + date + ", tprice=" + tprice
				+ ", products=" + products + "]";
	}
	
	
}
