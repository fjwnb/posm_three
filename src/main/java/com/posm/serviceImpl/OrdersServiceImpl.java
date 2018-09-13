package com.posm.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posm.bean.Orders;
import com.posm.dao.OrdersDao;
import com.posm.service.OrdersService;
@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersDao ordersDao;
	@Override
	public Integer add(Orders orders) {
		return ordersDao.add(orders);
	}
	@Override
	public List<Orders> pageQueryData(Map<String, Object> map) {
		return ordersDao.pageQueryData(map);
	}
	@Override
	public Integer pageQueryCount(Map<String, Object> map) {
		return ordersDao.pageQueryCount(map);
	}

}
