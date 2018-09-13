package com.posm.dao;

import java.util.List;
import java.util.Map;

import com.posm.bean.Orders;
public interface OrdersDao {
	public Integer add(Orders orders);
	
	public List<Orders> pageQueryData(Map<String, Object> map);

	public Integer pageQueryCount(Map<String, Object> map);
}
