package com.posm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.posm.bean.Cart;

public interface CartDao {
	//�����Ʒ�����ﳵ
	public Integer add(Cart cart);
	//������Ʒ����ʱ������С�ƺ�����
	public Integer update(Cart cart);
	//�õ����ﳵ
	public List<Cart> getCart(@Param("uid")Integer uid);
	public Integer jia(@Param("uid")Integer uid,@Param("pid")Integer pid,
			@Param("shop_price")double shop_price);
	public Integer jian(@Param("uid")Integer uid,@Param("pid")Integer pid,
			@Param("shop_price")double shop_price);
	public Integer delete(@Param("uid")Integer uid,@Param("pid")Integer pid);
	
	public List<Cart> getOrdersCart(Map<String,Object> map);
	//���û�����������궩��ʱ������ɾ�����ﳵ�Ѿ�����Ķ���
	public Integer deletePiliang(Map<String,Object> map);
}
