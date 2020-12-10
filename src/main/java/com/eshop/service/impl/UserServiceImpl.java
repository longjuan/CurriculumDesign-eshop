package com.eshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.dao.UserDao;
import com.eshop.entity.User;
import com.eshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User checkUserByNaP(User user) {
		return userDao.checkUserByNaP(user);
	}

	@Override
	public boolean addUser(User user) {
		if(userDao.numOfUsername(user.getUsername()) != 0) {
			return false;
		}
		return userDao.addUser(user) > 0;
	}

}
