package com.posm.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posm.bean.User;
import com.posm.dao.UserDao;
import com.posm.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public User userLogin(String username, String password) {
		return userDao.userLogin(username, password);
	}

}
