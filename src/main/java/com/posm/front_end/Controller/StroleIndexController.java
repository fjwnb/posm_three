package com.posm.front_end.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.posm.bean.Category;
import com.posm.bean.Product;
import com.posm.service.CategoryService;
import com.posm.service.ProductService;
import com.posm.util.BaseController;
import com.posm.util.Store;
@Controller
@RequestMapping("/store")
public class StroleIndexController extends BaseController{
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	/**
	 * ��ת��ǰ̨�̳�ҳ��
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		//��ҳչʾ����
		List<Category> categoryLists=categoryService.getAllcategory();
		model.addAttribute("categoryLists", categoryLists);
		//��ҳչʾ������Ʒ
		List<Product> listHost=productService.getAllHot();
		List<Product> list1=new ArrayList<Product>();
		for (Product p : listHost) {
			list1.add(p);
			if(list1.size()>=4) {
				break;
			}
		}
		model.addAttribute("list1", list1);
		//չʾ��ֵ��Ʒ
		List<Product> listcheap=productService.getAllCheap();
		List<Product> list2=new ArrayList<Product>();
		for (Product p : listcheap) {
			list2.add(p);
			if(list2.size()>=4) {
				break;
			}
		}
		model.addAttribute("list2", list2);
		return "store/storeIndex";
	}
	/**
	 * ���շ������
	 */
	@RequestMapping("/queryStore")
	public String queryStore(Store store,Model model) {
		List<Product> list=productService.getAll(store);
		model.addAttribute("stores", list);
		return "store/storeDetail";
	}
	@RequestMapping("/getByid")
	public String getByid(Integer id,Model model) {
		Product p=productService.getByid(id);
		List<Product> list=productService.getBycid(p.getCid());
		//չʾ��ֵ��Ʒ
		List<Product> list1=new ArrayList<Product>();
		for (Product pro : list) {
			if(pro.getFlag()==1) {
				list1.add(pro);
				if(list1.size()>=4) {
					break;
				}
			}	
		}
		model.addAttribute("p", p);
		model.addAttribute("list1", list1);
		return "store/storePreview";
	}
}
