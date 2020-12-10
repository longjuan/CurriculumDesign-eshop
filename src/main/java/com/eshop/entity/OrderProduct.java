package com.eshop.entity;

import org.springframework.stereotype.Component;

@Component
public class OrderProduct {
	private String pname;
	private Integer quantity;
	private Integer price;
	private String img;
	private String orderid;
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public OrderProduct(String pname, Integer quantity, Integer price, String img, String orderid) {
		super();
		this.pname = pname;
		this.quantity = quantity;
		this.price = price;
		this.img = img;
		this.orderid = orderid;
	}
	public OrderProduct() {
		super();
	}
	public OrderProduct(CartProduct p,String orderid) {
		this.pname = p.getProduct().getPname();
		this.quantity = p.getQuantity();
		this.price = p.getProduct().getPrice();
		this.img = p.getProduct().getImg1();
		this.orderid = orderid;
	}
	@Override
	public String toString() {
		return "OrderProduct [pname=" + pname + ", quantity=" + quantity + ", price=" + price + ", img=" + img
				+ ", orderid=" + orderid + "]";
	}
	
}
