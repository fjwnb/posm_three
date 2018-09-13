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
			// 分页查询
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", (pageno-1)*pagesize);
			map.put("size", pagesize);
			map.put("queryText", queryText);
			List<Product> products = productService.pageQueryData( map );
			// 当前页码			
			// 总的数据条数
			int totalsize = productService.pageQueryCount( map );
			// 最大页码（总页码）
			int totalno = 0;
			if ( totalsize % pagesize == 0 ) {
				totalno = totalsize / pagesize;
			} else {
				totalno = totalsize / pagesize + 1;
			}
			// 分页对象
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
	      //保存数据库的路径  
	      String sqlPath = null;   
	      //定义文件保存的本地路径  
	      String localPath="F:\\POSMimages\\";  
	      //定义 文件名  
	      String filename=null;    
	      if(!product.getFile().isEmpty()){    
	          //生成uuid作为文件名称
	          String uuid = UUID.randomUUID().toString().replaceAll("-","");    
	          //获得文件类型（可以判断如果不是图片，禁止上传）    
	          String contentType=product.getFile().getOriginalFilename();   
	          //获得文件后缀名   
	          String suffixName=contentType.substring(contentType.indexOf(".")+1); 
	          //得到 文件名  
	          filename=uuid+"."+suffixName;  
	          
	          //文件保存路径  
	          product.getFile().transferTo(new File(localPath+filename));    
	      }  
	      //把图片的相对路径保存至数据库  
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
	 * 删除方法
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
