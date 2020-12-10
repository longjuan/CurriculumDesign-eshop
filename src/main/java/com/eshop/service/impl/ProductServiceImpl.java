package com.eshop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.dao.ProductDao;
import com.eshop.entity.Product;
import com.eshop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Override
	public Map<String, Object> serch(String q, int offset, int size) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("q", q);
		map.put("offset", offset);
		map.put("size", size);
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("products", productDao.serch(map));
		result.put("num", productDao.getNum(map));
		return result;

	}

	@Override
	public Product getDetail(int id) {
		return productDao.getDetail(id);
	}

	@Override
	public List<Product> getBySaleusername(String saleusername) {
		return productDao.getBySaleusername(saleusername);
	}

	@Override
	public boolean deleteMyProduct(String saleusername, int id) {
		Product product = productDao.getDetail(id);
		if(product==null || !product.getSaleusername().equals(saleusername)) {
			return false;
		}
		return productDao.deleteProduct(id)>0;
	}

	@Override
	public boolean addMyProduct(Product product) {
		return productDao.addProduct(product)>0;
	}
	
}
