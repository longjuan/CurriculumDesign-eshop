package com.eshop.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.eshop.entity.User;

@Repository
public interface UserDao {
	@Select("select username,usertype from user where username=#{username} and password=#{password}")
	public User checkUserByNaP(User user);
	
	@Insert("insert into user (username,password,usertype) values (#{username},#{password},#{usertype})")
	public int addUser(User user);
	
	@Select("select count(username) from user where username=#{username}")
	public int numOfUsername(String username);
}
