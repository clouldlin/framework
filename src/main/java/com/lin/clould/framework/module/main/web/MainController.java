package com.lin.clould.framework.module.main.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lin.clould.framework.common.annotation.Controller;
import com.lin.clould.framework.common.annotation.RequestMapping;
import com.lin.clould.framework.common.view.View;
import com.lin.clould.framework.module.main.service.MainService;
import com.lin.clould.framework.module.main.service.impl.MainServiceImpl;

@Controller("main")
public class MainController {
	
	MainService mainService = new MainServiceImpl();
	
	@RequestMapping("main.do")
	public View main(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("firstIndex", "0");
		paramMap.put("lastIndex", "10");
		
		int noticeCount = mainService.mainNoticeListTotalCount(paramMap);
		
		request.setAttribute("module", "main");
		request.setAttribute("noticeCount", noticeCount);
		
		return new View("main/main");
	}
}
