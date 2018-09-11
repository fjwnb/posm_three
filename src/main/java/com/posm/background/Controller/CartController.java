package com.posm.background.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.posm.Shopp.Cart;
import com.posm.Shopp.CartItem;
import com.posm.bean.Product;
import com.posm.service.ProductService;
import com.posm.util.AJAXResult;
@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private ProductService productService;
	private Cart cart;
	   //获取购物车
    public Cart getCart(HttpSession session){
        cart = (Cart)session.getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
    
    @ResponseBody
    @RequestMapping("/addcart")
    public Object addCart(Integer id,@RequestParam(value="count",required=false
    ,defaultValue="1")Integer count,HttpSession session ) {
    	  //查询商品信息
        Product product = productService.getByid(id);
        //创建一个购物项的对象
        CartItem cartItem = new CartItem();
        cartItem.setCount(count);
        cartItem.setProduct(product);
        cart = getCart(session);
        cart.addCart(cartItem);
    	return "true";
    }
    
    @ResponseBody
    @RequestMapping("/getcart")
    public Object getCarttoPage(HttpSession session ) {
        cart = getCart(session);       
    	return cart;
    }
    @RequestMapping("/tocart")
    public String toCart(Model model,HttpSession session) {
    	cart = getCart(session);
    	model.addAttribute("cart", cart.getCartItems());
    	return "store/Cart";
    }
    
  //移除某个购物项
    @ResponseBody
    @RequestMapping("/removecart")
    public Object removeCart(Integer id,HttpSession session)throws Exception{
    	AJAXResult ajaxResult=new AJAXResult();
    	try {
    		 cart = getCart(session);
    	        cart.removeCart(id);
    	        ajaxResult.setSuccess(true);
		} catch (Exception e) {
			 ajaxResult.setSuccess(false);
		}
       
        return ajaxResult;
    }

    //清空购物车
    public String clearCart(HttpSession session){
        cart = getCart(session);
        cart.clearCart();
        return "clearCartSuccess";
    }
   
	
}
