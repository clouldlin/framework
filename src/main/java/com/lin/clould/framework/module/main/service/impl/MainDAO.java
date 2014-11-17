package com.lin.clould.framework.module.main.service.impl;

import java.util.List;
import java.util.Map;

import com.lin.clould.framework.common.database.ibatis.CommonDao;

public class MainDAO extends CommonDao {
	
	public List<?> mainNoticeList(Map<String, String> paramMap) throws Exception {
		return list("BoardSQL001.mainNoticeList", paramMap);
	}

	public Integer mainNoticeListTotalCount(Map<String, String> paramMap) throws Exception {
		return (Integer) selectByPk("BoardSQL001.mainNoticeListTotalCount",paramMap);
	}

}
