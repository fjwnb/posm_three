package com.posm.front_end.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.posm.background.Controller.ProductController;
import com.posm.bean.Address;
import com.posm.bean.User;
import com.posm.service.AddressService;
import com.posm.util.AJAXResult;

@Controller
@RequestMapping("/address")
public class AddressController {
	private Logger log=Logger.getLogger(AddressController.class);
	@Autowired
	private AddressService addressService;
	@ResponseBody
	@RequestMapping("/getaddress")
	private Object getAddress(Integer uid) {
		List<Address> list=addressService.getByuid(uid);
		return list;
	}
	@ResponseBody
	@RequestMapping("/add")
	private Object add(Address address,HttpSession session) {
		User user=(User) session.getAttribute("user");
		AJAXResult result=new AJAXResult();
		address.setUid(user.getId());
		try {
			if(addressService.add(address)>0) {
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			result.setSuccess(false);
		}
		return result;
	}
	
}
