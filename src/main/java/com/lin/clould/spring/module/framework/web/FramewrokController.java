package com.lin.clould.spring.module.framework.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lin.clould.spring.common.controller.CommonAbstarctController;
import com.lin.clould.spring.module.framework.service.FrameworkService;

@Controller
@RequestMapping("/framework/")
public class FramewrokController extends CommonAbstarctController{

	@Resource(name="frameworkService")
	FrameworkService frameworkService;

	@RequestMapping("version.do")
	public String version(Map<String,String> commandMap, ModelMap model, HttpServletRequest request) throws Exception{
		model.addAttribute("version", "Spring Framework 3.0.5.RELEASE");
		return "framework/version";
	}

}
