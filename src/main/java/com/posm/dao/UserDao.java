package com.posm.dao;

import org.apache.ibatis.annotations.Param;

import com.posm.bean.User;

public interface UserDao {
	public User userLogin(@Param("username")String username,@Param("password")String password);
}
