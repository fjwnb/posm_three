package com.posm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.posm.bean.OrderItem;

public interface OrderItemDao {
	public Integer add(OrderItem orderItem);
	public List<OrderItem> detailed(@Param("oid")Integer oid);
}
