package com.eshop.entity;

import org.springframework.stereotype.Component;

@Component
public class Product {
	private Integer id;
	private String pname;
	private Integer price;
	private Integer stock;
	private String saleusername;
	private String parameter;
	private String img1;
	private String img2;
	private String img3;
	private Integer version;
	
	public Product() {
		super();
	}

	public Product(Integer id, String pname, Integer price, Integer stock, String saleusername, String parameter,
			String img1, String img2, String img3, Integer version) {
		super();
		this.id = id;
		this.pname = pname;
		this.price = price;
		this.stock = stock;
		this.saleusername = saleusername;
		this.parameter = parameter;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.version = version;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getSaleusername() {
		return saleusername;
	}

	public void setSaleusername(String saleusername) {
		this.saleusername = saleusername;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImg3() {
		return img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", pname=" + pname + ", price=" + price + ", stock=" + stock + ", saleusername="
				+ saleusername + ", parameter=" + parameter + ", img1=" + img1 + ", img2=" + img2 + ", img3=" + img3
				+ ", version=" + version + "]";
	}
	
}
