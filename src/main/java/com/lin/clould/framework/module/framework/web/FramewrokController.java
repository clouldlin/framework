package com.lin.clould.framework.module.framework.web;

import com.lin.clould.framework.common.annotation.Controller;
import com.lin.clould.framework.common.annotation.RequestMapping;
import com.lin.clould.framework.common.annotation.ResponseMapping;
import com.lin.clould.framework.common.annotation.Result;

@Controller("/framework/")
public class FramewrokController {
	
	@RequestMapping("version.do")
	@Result(value = "/jsp/framework/version.jsp")
	public @ResponseMapping("version") String version() throws Exception {
		return "MyAnnotion Framework 0.0.1.RELEASE";
	}
}
