package com.posm.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posm.bean.Cart;
import com.posm.dao.CartDao;
import com.posm.service.CartService;
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDao cartDao;

	@Override
	public Integer add(Cart cart) {
		return cartDao.add(cart);
	}
	@Override
	public Integer update(Cart cart) {
		return cartDao.update(cart);
	}
	@Override
	public List<Cart> getCart(Integer uid) {
		return cartDao.getCart(uid);
	}
	@Override
	public Integer jia(Integer uid, Integer pid, double shop_price) {
		return cartDao.jia(uid, pid, shop_price);
	}
	@Override
	public Integer jian(Integer uid, Integer pid, double shop_price) {
		return cartDao.jian(uid, pid, shop_price);
	}
	@Override
	public Integer delete(Integer uid, Integer pid) {
		return cartDao.delete(uid, pid);
	}
	@Override
	public List<Cart> getOrdersCart(Map<String,Object> map) {
		return cartDao.getOrdersCart(map);
	}
	@Override
	public Integer deletePiliang(Map<String, Object> map) {
		return cartDao.deletePiliang(map);
	}
	

}
