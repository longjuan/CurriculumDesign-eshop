package com.eshop.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopCartDao {
	@Select("select cartproduct from shopcart where username=#{username}")
	public String getCartProduct(String username);
	
	@Insert("insert into shopcart (username,cartproduct) values (#{username},#{json})")
	public int saveCart(Map<String, String> map);
	
	@Delete("delete from shopcart where username=#{username}")
	public int delectCart(String username);
}
