package com.posm.service;

import java.util.List;

import com.posm.bean.OrderItem;

public interface OrderItemService {
	public Integer add(OrderItem orderItem);
	public List<OrderItem> detailed(Integer oid);
}
