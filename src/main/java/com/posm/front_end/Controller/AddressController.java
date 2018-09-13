package com.posm.front_end.Controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.posm.background.Controller.ProductController;
import com.posm.bean.Address;
import com.posm.service.AddressService;

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
	
}
