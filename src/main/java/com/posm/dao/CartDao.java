package com.posm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.posm.bean.Cart;

public interface CartDao {
	//添加商品到购物车
	public Integer add(Cart cart);
	//当该商品存在时用来加小计和数量
	public Integer update(Cart cart);
	//得到购物车
	public List<Cart> getCart(@Param("uid")Integer uid);
	public Integer jia(@Param("uid")Integer uid,@Param("pid")Integer pid,
			@Param("shop_price")double shop_price);
	public Integer jian(@Param("uid")Integer uid,@Param("pid")Integer pid,
			@Param("shop_price")double shop_price);
	public Integer delete(@Param("uid")Integer uid,@Param("pid")Integer pid);
	
	public List<Cart> getOrdersCart(Map<String,Object> map);
	//在用户购买完结算完订单时，批量删除购物车已经结算的东西
	public Integer deletePiliang(Map<String,Object> map);
}
