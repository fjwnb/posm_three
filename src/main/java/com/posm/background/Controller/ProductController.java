package com.posm.background.Controller;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.posm.bean.Category;
import com.posm.bean.Product;
import com.posm.service.CategoryService;
import com.posm.service.ProductService;
import com.posm.util.AJAXResult;
import com.posm.util.BaseController;
import com.posm.util.Page;

@Controller
@RequestMapping("product")
public class ProductController extends BaseController{
	private Logger log=Logger.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@RequestMapping("/productmain")
	public String addProduct() {
		return "product/productmain";
	}
	@RequestMapping("/addproduct")
	public String addproduct() {
		return "product/addProduct";
	}
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery( String queryText, Integer pageno, Integer pagesize ) {	
		AJAXResult result = new AJAXResult();
		try {
			// ��ҳ��ѯ
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", (pageno-1)*pagesize);
			map.put("size", pagesize);
			map.put("queryText", queryText);
			List<Product> products = productService.pageQueryData( map );
			// ��ǰҳ��			
			// �ܵ���������
			int totalsize = productService.pageQueryCount( map );
			// ���ҳ�루��ҳ�룩
			int totalno = 0;
			if ( totalsize % pagesize == 0 ) {
				totalno = totalsize / pagesize;
			} else {
				totalno = totalsize / pagesize + 1;
			}
			// ��ҳ����
			Page<Product> product = new Page<Product>();
			product.setDatas(products);
			product.setTotalno(totalno);
			product.setTotalsize(totalsize);
			product.setPageno(pageno);
			result.setData(product);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/ajaxcategory")
	public Object ajaxcategory() {
		AJAXResult ajaxResult=new AJAXResult();
		List<Category> list=categoryService.getAllcategory();
		ajaxResult.setData(list);
		return ajaxResult;
	}
	
	@RequestMapping("/upload")  
	public String upload(Product product,HttpServletRequest request) throws Exception{  
	      //�������ݿ��·��  
	      String sqlPath = null;   
	      //�����ļ�����ı���·��  
	      String localPath="F:\\POSMimages\\";  
	      //���� �ļ���  
	      String filename=null;    
	      if(!product.getFile().isEmpty()){    
	          //����uuid��Ϊ�ļ�����
	          String uuid = UUID.randomUUID().toString().replaceAll("-","");    
	          //����ļ����ͣ������ж��������ͼƬ����ֹ�ϴ���    
	          String contentType=product.getFile().getOriginalFilename();   
	          //����ļ���׺��   
	          String suffixName=contentType.substring(contentType.indexOf(".")+1); 
	          //�õ� �ļ���  
	          filename=uuid+"."+suffixName;  
	          
	          //�ļ�����·��  
	          product.getFile().transferTo(new File(localPath+filename));    
	      }  
	      //��ͼƬ�����·�����������ݿ�  
	      sqlPath = "/images/"+filename;
	      product.setImage(sqlPath);  
	      productService.addProcut(product);
	      return "redirect:/product/productmain";  
	}  
	@ResponseBody
	@RequestMapping("/flagonproduct")
	public Object flagonproduct(Integer id,Integer flag) {
		AJAXResult ajaxResult=new AJAXResult();
		if(productService.flagproduct(id,flag)>0) {
			ajaxResult.setSuccess(true);
		}else {
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	@ResponseBody
	@RequestMapping("/flagoffproduct")
	public Object flagoffproduct(Integer id,Integer flag) {
		AJAXResult ajaxResult=new AJAXResult();
		if(productService.flagproduct(id,flag)>0) {
			ajaxResult.setSuccess(true);
		}else {
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	
	@ResponseBody
	@RequestMapping("ajaxget")
	public Object Ajaxget(Integer id) {
		AJAXResult ajaxResult=new AJAXResult();
		Product product=productService.getByid(id);
		ajaxResult.setData(product);
		return ajaxResult;
	}
	@ResponseBody
	@RequestMapping("/ajaxproductedit")
	public Object ajaxproductedit(Product product)throws Exception {
		AJAXResult ajaxResult=new AJAXResult();
		if(productService.update(product)>0) {
			ajaxResult.setSuccess(true);
		}else {
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	/**
	 * ɾ������
	 */
	@ResponseBody
	@RequestMapping("/productdelete")
	public Object delete(Integer id) {
		AJAXResult ajaxResult=new AJAXResult();
		if(productService.deleteByid(id)>0) {
			ajaxResult.setSuccess(true);
		}else {
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
}
