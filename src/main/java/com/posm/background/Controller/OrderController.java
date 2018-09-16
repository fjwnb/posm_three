package com.posm.background.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.posm.bean.Address;
import com.posm.bean.Cart;
import com.posm.bean.OrderItem;
import com.posm.bean.Orders;
import com.posm.bean.Product;
import com.posm.service.AddressService;
import com.posm.service.CartService;
import com.posm.service.OrderItemService;
import com.posm.service.OrdersService;
import com.posm.service.ProductService;
import com.posm.serviceImpl.OrdersServiceImpl;
import com.posm.util.AJAXResult;
import com.posm.util.Coding;
@Controller
@RequestMapping("/order")
public class OrderController {
	private Logger log=Logger.getLogger(OrderController.class);
	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private AddressService addressService;
	//�û���ѡ���ﳵ��Ʒ�Ժ���ת����ҳ��
	@RequestMapping("/toorder")
	public String order(Integer[] pid,Integer uid,Model model,HttpSession session) {
		//ÿ�ν��������pids Ҳ�������û�ѡ�е���ȷ���µ�ҳ��������
		session.removeAttribute("pids");
		//���û�ѡ�е���Ʒid����session��
		session.setAttribute("pids", pid);
		session.setAttribute("uid", uid);
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("pids", pid);
		map.put("uid", uid);
		List<Cart> list=cartService.getOrdersCart(map);
		double total=0d;
		for (Cart cart : list) {
			cart.setProduct(productService.getByid(cart.getPid()));
			total+=cart.getSubtotal();
		}
		model.addAttribute("total", total);
		model.addAttribute("list", list);
		return "store/Order";
	}
	//�û����㶩��
	@ResponseBody
	@RequestMapping("/settleorder")
	public Object settleOrder(Integer aid,double total,HttpSession session) {
		/*AJAXResult ajaxResult=new AJAXResult();*/
		Map<String, Object> result=new HashMap<String,Object>();
		try {
			
			Integer uid=(Integer) session.getAttribute("uid");
			Integer[] pids=(Integer[]) session.getAttribute("pids");
			
			Orders orders=new Orders();
			orders.setAid(aid);
			orders.setTotal(total);
			orders.setUid(uid);
			orders.setOrderTime(new Date());
			orders.setCoding(Coding.getCodingBytime());
			ordersService.add(orders);//���涩��
			Integer oid=orders.getId();
			
			Map<String, Object> map=new HashMap<String,Object>();
			map.put("pids", pids);
			map.put("uid",uid);
			List<Cart> list=cartService.getOrdersCart(map);
			for (Cart cart : list) {
				OrderItem orderItem=new OrderItem();
				orderItem.setOid(oid);
				orderItem.setCount(cart.getNum());
				orderItem.setPid(cart.getPid());
				orderItem.setSubtotal(cart.getSubtotal());
				orderItemService.add(orderItem);
			}
			//����ȫ����� ɾ���Ѿ����ﳵ�����µ��˵���Ʒ
			cartService.deletePiliang(map);
			result.put("success", true);
			/*ajaxResult.setSuccess(true);*/
			//��Ҫ��ʾ��һЩ���ݷŵ��ɹ�ҳ��
			Address address=addressService.getByid(aid);
			result.put("tel", address.getTel());
			result.put("name", address.getName());
			result.put("address", address.getAddress());
			result.put("total", total);
			result.put("coding", orders.getCoding());
		} catch (Exception e) {
			result.put("success", false);
		}	
		return result;
	}
	@RequestMapping("/success")
	public String success(String tel,String name,String address,double total,
			String coding,Model model) {
		model.addAttribute("tel", tel);
		model.addAttribute("name", name);
		model.addAttribute("address", address);
		model.addAttribute("total", total);
		model.addAttribute("coding", coding);
		return "store/Success";
	}
	
	
}
