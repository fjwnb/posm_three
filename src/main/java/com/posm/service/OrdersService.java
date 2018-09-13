package com.posm.service;

import java.util.List;
import java.util.Map;

import com.posm.bean.Orders;

public interface OrdersService {
	public Integer add(Orders orders);

	public List<Orders> pageQueryData(Map<String, Object> map);

	public Integer pageQueryCount(Map<String, Object> map);
}
