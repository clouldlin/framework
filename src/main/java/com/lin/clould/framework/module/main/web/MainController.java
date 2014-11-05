package com.lin.clould.framework.module.main.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lin.clould.framework.common.annotation.Controller;
import com.lin.clould.framework.common.annotation.RequestMapping;
import com.lin.clould.framework.common.view.View;

@Controller("main")
public class MainController {
	
	@RequestMapping("main.do")
	public View main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("main", "main");
		
		return new View("/jsp/main/main.jsp");
	}
}
