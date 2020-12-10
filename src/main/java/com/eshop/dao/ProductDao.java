package com.eshop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.eshop.entity.CartProduct;
import com.eshop.entity.Product;

@Repository
public interface ProductDao {
	@Select("select id,pname,price,stock,saleusername,img1,version from product where match(pname) against(#{q}) limit #{offset},#{size}")
	public List<Product> serch(Map<String, Object> qAndPage);
	
	@Select("SELECT count( pname ) AS item FROM product WHERE MATCH (pname) against (#{q})")
	public int getNum(Map<String, Object> q);
	
	@Select("select * from product where id=#{id}")
	public Product getDetail(int id);
	
	@Select("select id,pname,price,stock,img1,version from product where id=#{id}")
	public Product getBaseInfo(int id);
	
	@Update("UPDATE product SET stock=#{last},version=#{version}+1 WHERE id = #{id} AND version = #{version}")
	public int reduceInventory(Map<String, Object> map);
	
	@Update("UPDATE product SET stock = ( SELECT b.stock FROM ( SELECT a.stock FROM product a where id = #{id}) b ) + #{quantity} WHERE id = #{id}")
	public int rollback(CartProduct cartProduct);
	
	@Select("select id,pname,price,stock,img1,version from product where saleusername=#{saleusername}")
	public List<Product> getBySaleusername(String saleusername);
	
	@Delete("delete from product where id=#{id}")
	public int deleteProduct(int id);
	
	@Insert("insert into product (id,pname,price,stock,saleusername,parameter,img1,img2,img3,version) values (null,#{pname},#{price},#{stock},#{saleusername},#{parameter},#{img1},#{img2},#{img3},1)")
	public int addProduct(Product product);
}
