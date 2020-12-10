package com.eshop.service;

import org.springframework.stereotype.Service;

import com.eshop.entity.User;

@Service
public interface UserService {
	public User checkUserByNaP(User user);
	public boolean addUser(User user);
}
