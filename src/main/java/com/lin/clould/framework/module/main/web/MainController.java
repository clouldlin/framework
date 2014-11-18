package com.lin.clould.framework.module.main.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lin.clould.framework.common.annotation.Controller;
import com.lin.clould.framework.common.annotation.RequestMapping;
import com.lin.clould.framework.common.util.string.StringUtil;
import com.lin.clould.framework.common.view.View;
import com.lin.clould.framework.module.main.service.MainService;
import com.lin.clould.framework.module.main.service.impl.MainServiceImpl;

@Controller("main")
public class MainController {
	
	MainService mainService = new MainServiceImpl();
	
	@RequestMapping("main.do")
	public View main(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("firstIndex", StringUtil.isEmpty(request.getParameter("pageIndex"))? "0": (Integer.parseInt(request.getParameter("pageIndex")) - 1) * 5);
		paramMap.put("lastIndex", StringUtil.isEmpty(request.getParameter("pageIndex"))? "5": Integer.parseInt(request.getParameter("pageIndex")) * 5);
		
		int noticeTotalCount = mainService.mainNoticeListTotalCount(paramMap);
		List<?> noticeList = mainService.mainNoticeList(paramMap);
		
		request.setAttribute("module", "main");
		request.setAttribute("noticeTotalCount", noticeTotalCount);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("noticePage", request.getParameter("pageIndex"));
		
		return new View("main/main");
	}
}
