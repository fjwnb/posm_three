package com.posm.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posm.bean.Product;
import com.posm.dao.ProductDao;
import com.posm.service.ProductService;
import com.posm.util.Store;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Override
	public List<Product> getBycid(Integer cid) {
		return productDao.getBycid(cid);
	}
	@Override
	public List<Product> pageQueryData(Map<String, Object> map) {
		return productDao.pageQueryData(map);
	}
	@Override
	public int pageQueryCount(Map<String, Object> map) {
		return productDao.pageQueryCount(map);
	}
	@Override
	public Integer addProcut(Product product) {
		return productDao.addProcut(product);
		
	}
	@Override
	public Integer flagproduct(Integer id,Integer flag) {
		return productDao.flagproduct(id,flag);
	}
	@Override
	public Product getByid(Integer id) {
		return productDao.getByid(id);
	}
	@Override
	public Integer update(Product product) {
		return productDao.update(product);
	}
	@Override
	public List<Product> getAllHot() {
		return productDao.getAllHot();
	}
	@Override
	public List<Product> getAllCheap() {
		return productDao.getAllCheap();
	}
	@Override
	public List<Product> getAll(Store store) {
		return productDao.getAll(store);
	}
	@Override
	public Integer deleteByid(Integer id) {
		return productDao.deleteByid(id);
	}

}
