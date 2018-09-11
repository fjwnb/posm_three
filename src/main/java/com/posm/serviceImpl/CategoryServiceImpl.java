package com.posm.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posm.bean.Category;
import com.posm.dao.CategoryDao;
import com.posm.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public Category ajax(String name) {
		return categoryDao.ajax(name);
	}

	@Override
	public Integer add(String name) {
		return categoryDao.add(name);
	}

	@Override
	public List<Category> pageQueryData(Map<String, Object> map) {
		return categoryDao.pageQueryData(map);
	}

	@Override
	public int pageQueryCount(Map<String, Object> map) {
		return categoryDao.pageQueryCount(map);
	}

	@Override
	public Category getByid(Integer id) {
		return categoryDao.getByid(id);
	}

	@Override
	public int update(Category category) {
		return categoryDao.update(category);
	}

	@Override
	public int delete(Integer id) {
		return categoryDao.delete(id);
	}

	@Override
	public List<Category> getAllcategory() {
		return categoryDao.getAllcategory();
	}
}
