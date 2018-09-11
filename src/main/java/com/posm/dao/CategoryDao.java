package com.posm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.posm.bean.Category;

public interface CategoryDao {
	public Category ajax(@Param("name")String name);
	public Integer add(@Param("name")String name);
	public List<Category> pageQueryData(Map<String, Object> map);
	public int pageQueryCount(Map<String, Object> map);
	public Category getByid(@Param("id")Integer id);
	public int update(Category category);
	public int delete(@Param("id")Integer id);
	public List<Category> getAllcategory();
}
