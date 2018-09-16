package com.posm.front_end.Controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.posm.bean.User;
import com.posm.service.UserService;
import com.posm.util.AJAXResult;
import com.posm.util.BaseController;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	private Logger log=Logger.getLogger(UserController.class);
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
		return "redirect:/store/index";
	}
	@RequestMapping("/register")
	public String register() {
		return "userregister";
	}
	@ResponseBody
	@RequestMapping("/add")
	public Object AjaxandAdd(User user) {
		String ressult="";
		User u=userService.Ajaxget(user.getUsername());
		if(u!=null) {
			ressult="have";
		}else {
			if(userService.add(user)>0) {
				ressult="true";
			}else {
				ressult="false";
			}
		}
		return ressult;
	}
	
}
