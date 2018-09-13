package com.posm.background.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.posm.bean.Category;
import com.posm.bean.Product;
import com.posm.service.CategoryService;
import com.posm.service.ProductService;
import com.posm.util.AJAXResult;
import com.posm.util.Page;

@Controller
@RequestMapping("/category")
public class CategoryController {
	private Logger log=Logger.getLogger(CategoryController.class);
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@RequestMapping("/addcategory")
	public String addCategory() {
		return "category/addCategory";
	}
	
	@RequestMapping("/findcategory")
	public String findCategory() {
		return "category/findcategory";
	}
	
	@RequestMapping("/editcategory")
	public String editCategory() {
		return "category/editcategory";
	}
	
	
	/**
	 * 
	 * ��ת���޸�ҳ��
	 */
	@RequestMapping("/deletecategory")
	public String deleteCategory() {
		return "category/deletecategory";
	}
	
	
	/**
	 * ��ת�����������ҳ��
	 * @return
	 */
	@RequestMapping("/categorymain")
	public String addProduct() {
		return "category/categorymain";
	}
	/**
	 * ��ajaxΪ��ҳ����ʾ����
	 * @param queryText
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery( String queryText, Integer pageno, Integer pagesize ) {	
		AJAXResult result = new AJAXResult();
		try {
			// ��ҳ��ѯ
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", (pageno-1)*pagesize);
			map.put("size", pagesize);
			map.put("queryText",queryText);
			List<Category> categorys = categoryService.pageQueryData( map );
			// ��ǰҳ��			
			// �ܵ���������
			int totalsize = categoryService.pageQueryCount( map );
			// ���ҳ�루��ҳ�룩
			int totalno = 0;
			if ( totalsize % pagesize == 0 ) {
				totalno = totalsize / pagesize;
			} else {
				totalno = totalsize / pagesize + 1;
			}
			// ��ҳ����
			Page<Category> category = new Page<Category>();
			category.setDatas(categorys);
			category.setTotalno(totalno);
			category.setTotalsize(totalsize);
			category.setPageno(pageno);
			
			result.setData(category);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * Ϊ���ʱ��֤�Ƿ����ݿ���ڸ÷���,������
	 */
	@ResponseBody
	@RequestMapping("/ajaxcategory")
	public Object ajaxcategory(String name)throws Exception {
		String result=null;
		Category category=categoryService.ajax(name);
		if(category!=null) {
			result="exist";
		}else {
			if(categoryService.add(name)>0) {
				result="true";
			}else {
				result="false";
			}
		}
		return result;
	}
	/**
	 * ��ajaxɾ��
	 */
	@ResponseBody
	@RequestMapping("/ajaxcategorydelete")
	public Object ajaxcategorydelete(Integer id)throws Exception {
		String result=null;
		List<Product> product=productService.getBycid(id);
		if(product.size()>0) {
			result="erro";
		}else {
			if(categoryService.delete(id)>0) {
				result="true";
			}else {
				result="false";
			}
		}
		return result;
	}
	/***
	 * Ϊ�޸ķ���������ֵ
	 */
	@ResponseBody
	@RequestMapping("ajaxget")
	public Object Ajaxget(Integer id) {
		AJAXResult ajaxResult=new AJAXResult();
		Category category=categoryService.getByid(id);
		ajaxResult.setData(category);
		return ajaxResult;
	}
	/**
	 * �޸ĺ󱣴�����
	 * @param category
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/ajaxcategoryedit")
	public Object ajaxcategoryedit(Category category)throws Exception {
		String result=null;
		Category cat=categoryService.ajax(category.getName());
		if(cat!=null) {
			result="exist";
		}else {
			if(categoryService.update(category)>0) {
				result="true";
			}else {
				result="false";
			}
		}
		return result;
	}
}
