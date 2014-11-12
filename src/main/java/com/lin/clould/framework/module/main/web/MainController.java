package com.lin.clould.framework.module.main.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lin.clould.framework.common.annotation.Controller;
import com.lin.clould.framework.common.annotation.RequestMapping;
import com.lin.clould.framework.common.annotation.SessionCertification;
import com.lin.clould.framework.common.view.View;

@Controller("main")
public class MainController {
	
	@RequestMapping("main.do")
	//@SessionCertification(message ="사용자 정보가 존재하지 않습니다. 로그인을 해주세요")
	public View main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("module", "main");
		
		return new View("main/main");
	}
}
