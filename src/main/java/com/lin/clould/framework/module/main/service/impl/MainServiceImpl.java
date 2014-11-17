package com.lin.clould.framework.module.main.service.impl;

import java.util.List;
import java.util.Map;

import com.lin.clould.framework.module.main.service.MainService;

public class MainServiceImpl implements MainService {

	MainDAO mainDAO = new MainDAO();
	
	@Override
	public List<?> mainNoticeList(Map<String, String> paramMap) throws Exception {
		return mainDAO.mainNoticeList(paramMap);
	}

	@Override
	public Integer mainNoticeListTotalCount(Map<String, String> paramMap) throws Exception {
		return mainDAO.mainNoticeListTotalCount(paramMap);
	}

}
