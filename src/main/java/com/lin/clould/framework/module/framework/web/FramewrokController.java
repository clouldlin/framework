package com.lin.clould.framework.module.framework.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lin.clould.framework.common.annotation.Controller;
import com.lin.clould.framework.common.annotation.RequestMapping;
import com.lin.clould.framework.common.view.View;

@Controller("framework")
public class FramewrokController {
	
	@RequestMapping("version.do")
	public View version(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("version", "My-Annotation FrameWrok 0.0.2");
		
		return new View("/jsp/framework/version.jsp");
	}
}
