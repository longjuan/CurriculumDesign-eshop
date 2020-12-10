package com.eshop.entity;

import org.springframework.stereotype.Component;

@Component
public class CartProduct {
	private Integer id;
	private Integer quantity;
	private Product product;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public CartProduct(Integer id, Integer quantity, Product product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.product = product;
	}
	public CartProduct() {
		super();
	}
	@Override
	public String toString() {
		return "CartProduct [id=" + id + ", quantity=" + quantity + ", product=" + product + "]";
	}
	public void simplify() {
		this.product = null;
	}
	
}
