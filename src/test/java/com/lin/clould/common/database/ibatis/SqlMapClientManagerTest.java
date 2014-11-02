package com.lin.clould.common.database.ibatis;

import org.junit.Test;

import com.ibatis.sqlmap.client.SqlMapClient;


public class SqlMapClientManagerTest {
	
	private SqlMapClient sqlMap;

	@Test
	public void nothing(){
		
	}
	
	@Test
	public void canCreateObject(){
		sqlMap = SqlMapClientManager.getSqlMapClient();
	}
}