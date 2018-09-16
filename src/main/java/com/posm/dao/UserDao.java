package com.posm.dao;

import org.apache.ibatis.annotations.Param;

import com.posm.bean.User;

public interface UserDao {
	public User userLogin(@Param("username")String username,@Param("password")String password);
	public User Ajaxget(@Param("username")String username);
	public Integer add(User user);
}
