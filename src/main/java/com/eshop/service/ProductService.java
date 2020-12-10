package com.eshop.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.eshop.entity.Product;

@Service
public interface ProductService {
	public Map<String, Object> serch(String q,int offset,int size);
	public Product getDetail(int id);
	public List<Product> getBySaleusername(String saleusername);
	public boolean deleteMyProduct(String saleusername,int id);
	public boolean addMyProduct(Product product);
	
}
