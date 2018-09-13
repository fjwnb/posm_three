package com.posm.background.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.posm.Shopp.CartItem;
import com.posm.bean.Cart;
import com.posm.bean.Product;
import com.posm.bean.User;
import com.posm.service.CartService;
import com.posm.service.ProductService;
import com.posm.util.AJAXResult;
@Controller
@RequestMapping("/cart")
public class CartController {
	private Logger log=Logger.getLogger(CartController.class);
	@Autowired
	private ProductService productService;
	@Autowired
	private CartService cartService;
	private List<Cart> list;
	public List<Cart> getCart(HttpSession session) {
		User user=(User) session.getAttribute("user");
		list=cartService.getCart(user.getId());
		for (Cart cart : list) {
			cart.setProduct(productService.getByid(cart.getPid()));
		}
		return list;
	}
	@ResponseBody
	@RequestMapping("/addcart")
	public Object addCart(Integer pid,@RequestParam(value="count",required=false
	 ,defaultValue="1")Integer count,HttpSession session ){
			User user=(User) session.getAttribute("user");
			 int uid=user.getId();
			 List<Cart> list=cartService.getCart(uid);
			 boolean has=true;
			 for (Cart cart : list) {
				if(cart.getPid()==pid) {
					has=false;
					break;
				}
			}
			Cart ca=new Cart();
			Product product = productService.getByid(pid);
			ca.setNum(count);
			ca.setPid(pid);
			ca.setUid(uid);
			ca.setSubtotal(count*product.getShop_price());
			if(has) {
			 		cartService.add(ca);
			}else {
				cartService.update(ca);
			}	
		return "true";
	}
	
	@ResponseBody
	@RequestMapping("/getcart")
	public Map<String, Object> getCarttoPage(HttpSession session ) {
		Map<String, Object> map=new HashMap<String,Object>();
		User user=(User) session.getAttribute("user");
		if(user!=null) {
			int uid=user.getId();
			List<Cart> list=cartService.getCart(uid);
			int nums=0;
			double total=0d;
			for (Cart cart : list) {
				Product product=productService.getByid(cart.getPid());
				cart.setProduct(product);
				nums+=cart.getNum();
				total+=cart.getSubtotal();
			}
			map.put("total", total);
			map.put("cartItems", list);
			map.put("bl", true);
		}else {
			map.put("bl", false);
		}	
	    return map;  
	}
	@RequestMapping("/tocart")
    public String toCart(Model model,HttpSession session) {
		User user=(User) session.getAttribute("user");
		int uid=user.getId();
		List<Cart> list=cartService.getCart(uid);
		for (Cart cart : list) {
			Product product=productService.getByid(cart.getPid());
			cart.setProduct(product);				
		}
    	model.addAttribute("cart", list);
    	return "store/Cart";
    }
	@ResponseBody
	@RequestMapping("/jian")
	public Object jian(Integer pid,double shop_price,HttpSession session) {
		User user=(User) session.getAttribute("user");
		int uid=user.getId();
		cartService.jian(uid, pid,shop_price);
		return true;
	}
	@ResponseBody
	@RequestMapping("/jia")
	public Object jia(Integer pid,double shop_price,HttpSession session) {
		User user=(User) session.getAttribute("user");
		int uid=user.getId();
		cartService.jia(uid, pid,shop_price);
		return true;
	}
	//ÒÆ³ýÄ³¸ö¹ºÎïÏî
    @ResponseBody
    @RequestMapping("/removecart")
    public Object removeCart(Integer pid,HttpSession session)throws Exception{
    	AJAXResult ajaxResult=new AJAXResult();
    	
    	try {
    		User user=(User) session.getAttribute("user");
    		int uid=user.getId();
    		if(cartService.delete(uid, pid)>0) {
    			ajaxResult.setSuccess(true);
    		}else {
    			 ajaxResult.setSuccess(false);
    		}
		} catch (Exception e) {
			 ajaxResult.setSuccess(false);
		}  
        return ajaxResult;
    }
   
}
