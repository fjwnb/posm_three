package com.posm.service;

import java.util.List;
import java.util.Map;

import com.posm.bean.Cart;

public interface CartService {
	public Integer add(Cart cart);
	public Integer update(Cart cart);
	public List<Cart> getCart(Integer uid);
	public Integer jia(Integer uid,Integer pid,double shop_price);
	public Integer jian(Integer uid,Integer pid,double shop_price);
	public Integer delete(Integer uid,Integer pid);
	public List<Cart> getOrdersCart(Map<String,Object> map);
	//在用户购买完结算完订单时，批量删除购物车已经结算的东西
	public Integer deletePiliang(Map<String,Object> map);
}
