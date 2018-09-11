package com.posm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.posm.bean.Product;
import com.posm.util.Store;

public interface ProductDao {
	public List<Product> getBycid(@Param("cid")Integer cid);

	public List<Product> pageQueryData(Map<String, Object> map);

	public int pageQueryCount(Map<String, Object> map);

	public Integer addProcut(Product product);

	public Integer flagproduct(@Param("id")Integer id,@Param("flag")Integer flag);

	public Product getByid(@Param("id")Integer id);

	public Integer update(Product product);
	//获得所有热门商品
	public List<Product> getAllHot();
	//获得所有超值商品
	public List<Product> getAllCheap();
	public List<Product> getAll(Store store);

	public Integer deleteByid(@Param("id")Integer id);
}
