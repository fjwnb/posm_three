package com.posm.background.Controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.posm.bean.User;
import com.posm.service.UserService;
import com.posm.util.AJAXResult;
@Controller
@RequestMapping("/admin")
public class adminController {
	private Logger log=Logger.getLogger(adminController.class);
	@Autowired
	private UserService userService;
	@RequestMapping("/adminlogin")
	public String login() {
		//return "adminlogin";
		return "adLogin";
	}
	@ResponseBody
	@RequestMapping("/doAJAXLogin")
	public Object loginAjax(String username,String password,HttpSession session)throws Exception {
		String result="";
		try {
			User user=userService.userLogin(username, password);
			if(user==null) {
				result="false";
			}else if(user.getId()!=1){
				result="no";
			}else {
				session.setAttribute("admin", user);
				result="true";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result="false";
		}
		System.err.println(result);
		return result;
	}
	@RequestMapping("/backpagemain")
	public String backpagemain() {
		return "backpagemain";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		return "redirect:/admin/adminlogin";
	}
}
