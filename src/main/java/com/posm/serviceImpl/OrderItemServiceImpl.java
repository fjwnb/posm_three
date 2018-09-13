package com.posm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posm.bean.OrderItem;
import com.posm.dao.OrderItemDao;
import com.posm.service.OrderItemService;
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
	private OrderItemDao orderItemDao;
	@Override
	public Integer add(OrderItem orderItem) {
		return orderItemDao.add(orderItem);
	}
	@Override
	public List<OrderItem> detailed(Integer oid) {
		return orderItemDao.detailed(oid);
	}

}
