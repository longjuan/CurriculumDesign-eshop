package com.eshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.eshop.entity.Order;
import com.eshop.entity.OrderProduct;

@Repository
public interface OrderDao {
	@Insert("insert into `order` (orderid,username,date,tprice) values (#{orderid},#{username},#{date},#{tprice})")
	public int saveOrder(Order order);
	
	@Insert("insert into `orderproduct` (orderid,pname,quantity,price,img) values (#{orderid},#{pname},#{quantity},#{price},#{img})")
	public int saveOrderProducts(OrderProduct orderProducts);
	
	@Select("select * from `order` where username = #{username}")
	public List<Order> getOrder(String username);
	
	@Select("select * from `orderproduct` where orderid = #{orderid}")
	public List<OrderProduct> getOrderProduct(String orderid);
}
