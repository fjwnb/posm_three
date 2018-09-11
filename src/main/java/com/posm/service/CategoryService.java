package com.posm.service;

import java.util.List;
import java.util.Map;

import com.posm.bean.Category;

public interface CategoryService {
	public Category ajax(String name);
	public Integer add(String name);
	public List<Category> pageQueryData(Map<String, Object> map);
	public int pageQueryCount(Map<String, Object> map);
	public Category getByid(Integer id);
	public int update(Category category);
	public int delete(Integer id);
	public List<Category> getAllcategory();
}
