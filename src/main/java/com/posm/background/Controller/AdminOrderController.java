package com.posm.background.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.posm.bean.OrderItem;
import com.posm.bean.Orders;
import com.posm.bean.Product;
import com.posm.service.OrderItemService;
import com.posm.service.OrdersService;
import com.posm.util.AJAXResult;
import com.posm.util.Page;
@Controller
@RequestMapping("/adminorder")
public class AdminOrderController {
	private Logger log=Logger.getLogger(AdminOrderController.class);
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrderItemService orderItemService;
	
	@RequestMapping("/ordermain")
	public String ordermain() {
		return "order/ordermain";
	}
	
	//分页
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String queryText, Integer pageno, Integer pagesize ) {	
		AJAXResult result = new AJAXResult();
		try {
			// 分页查询
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", (pageno-1)*pagesize);
			map.put("size", pagesize);
			map.put("queryText", queryText);
			List<Orders> orders = ordersService.pageQueryData(map);
			// 当前页码			
			// 总的数据条数
			int totalsize = ordersService.pageQueryCount(map);
			// 最大页码（总页码）
			int totalno = 0;
			if ( totalsize % pagesize == 0 ) {
				totalno = totalsize / pagesize;
			} else {
				totalno = totalsize / pagesize + 1;
			}
			// 分页对象
			Page<Orders> list = new Page<Orders>();
			list.setDatas(orders);
			list.setTotalno(totalno);
			list.setTotalsize(totalsize);
			list.setPageno(pageno);
			result.setData(list);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/detailed")
	public String detailde(Integer oid,Model model) {
		List<OrderItem> list=orderItemService.detailed(oid);
		model.addAttribute("list", list);
		return "order/orderdetailed";
	}
	
}
