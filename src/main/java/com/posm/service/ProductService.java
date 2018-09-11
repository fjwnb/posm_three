package com.posm.service;


import java.util.List;
import java.util.Map;

import com.posm.bean.Product;
import com.posm.util.Store;

public interface ProductService {
	public List<Product> getBycid(Integer cid);

	public List<Product> pageQueryData(Map<String, Object> map);

	public int pageQueryCount(Map<String, Object> map);

	public Integer addProcut(Product product);

	public Integer flagproduct(Integer id,Integer flag);

	public Product getByid(Integer id);

	public Integer update(Product product);
	//�������������Ʒ
	public List<Product> getAllHot();
	//������г�ֵ��Ʒ
	public List<Product> getAllCheap();

	public List<Product> getAll(Store store);

	public Integer deleteByid(Integer id);
}
