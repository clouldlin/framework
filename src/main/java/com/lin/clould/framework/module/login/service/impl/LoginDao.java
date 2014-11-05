package com.lin.clould.framework.module.login.service.impl;

import com.lin.clould.framework.common.database.ibatis.CommonDao;

public class LoginDao extends CommonDao {

	private LoginDao() {
		
	}

	private static LoginDao dao;

	public static LoginDao getInstance() {
		if (dao == null) {
			dao = new LoginDao();
		}
		return dao;
	}

	public LoginVO selectUserById(String id) throws Exception {
		
		LoginVO loginVO = null; 
		selectByPk(id, loginVO);
		
		return loginVO;
	}

}