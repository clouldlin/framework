package com.lin.clould.framework.module.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lin.clould.framework.common.annotation.Controller;
import com.lin.clould.framework.common.annotation.RequestMapping;
import com.lin.clould.framework.common.view.View;

@Controller("login")
public class LoginController {
	
	@RequestMapping("login.do")
	public View main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("module", "login");
		
		return new View("login/login");
	}
}
