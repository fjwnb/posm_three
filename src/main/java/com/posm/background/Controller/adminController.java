package com.posm.background.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.posm.bean.User;
import com.posm.service.UserService;
import com.posm.util.AJAXResult;

@Controller
@RequestMapping("/admin")
public class adminController {
	@Autowired
	private UserService userService;
	@RequestMapping("/adminlogin")
	public String login() {
		return "adminlogin";
	}
	@ResponseBody
	@RequestMapping("/doAJAXLogin")
	public Object loginAjax(String username,String password,HttpSession session)throws Exception {
		AJAXResult result=new AJAXResult();
		try {
			User user=userService.userLogin(username, password);
			if(user==null) {
				result.setSuccess(false);
			}else {
				session.setAttribute("admin", user);
				result.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	@RequestMapping("/backpagemain")
	public String backpagemain() {
		return "backpagemain";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/adminlogin";
	}
}
