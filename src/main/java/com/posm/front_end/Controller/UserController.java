package com.posm.front_end.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.posm.bean.User;
import com.posm.service.UserService;
import com.posm.util.AJAXResult;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/login")
	public String login() {
		return "userlogin";
	}
	@ResponseBody
	@RequestMapping("/doAJAXLogin")
	public Object doAJAXLogin(String username,String password,HttpSession session)throws Exception {
		AJAXResult ajaxResult=new AJAXResult();
		User user=userService.userLogin(username, password);
		if(user!=null) {
			ajaxResult.setSuccess(true);
			session.setAttribute("user", user);
		}else {
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	@RequestMapping("/outlogin")
	public String logout(HttpSession session){
		session.removeAttribute("user");
		session.removeAttribute("cart");
		return "store/storeIndex";
	}
}
