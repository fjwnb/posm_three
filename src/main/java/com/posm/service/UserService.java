package com.posm.service;

import com.posm.bean.User;
public interface UserService {
	public User userLogin(String username,String password);
	public User Ajaxget(String username);
	public Integer add(User user);
	
}
