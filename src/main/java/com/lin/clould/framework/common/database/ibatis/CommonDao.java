package com.lin.clould.framework.common.database.ibatis;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lin.clould.common.database.ibatis.SqlMapClientManager;

public class CommonDao {

	private SqlMapClient sqlMap;

	public CommonDao() {
		sqlMap = SqlMapClientManager.getSqlMapClient();
	}

	public Object insert(String queryId, Object parameterObject) throws Exception {
		return sqlMap.insert(queryId, parameterObject);
	}

	public int update(String queryId, Object parameterObject) throws Exception {
		return sqlMap.update(queryId, parameterObject);
	}

	public int delete(String queryId, Object parameterObject) throws Exception {
		return sqlMap.delete(queryId, parameterObject);
	}

	public Object selectByPk(String queryId, Object parameterObject) throws Exception {
		return sqlMap.queryForObject(queryId, parameterObject);
	}

	public List list(String queryId, Object parameterObject) throws Exception {
		return sqlMap.queryForList(queryId, parameterObject);
	}

	public List listWithPaging(String queryId, Object parameterObject,	int pageIndex, int pageSize) throws Exception {
		int skipResults = pageIndex * pageSize;
		int maxResults = pageIndex * pageSize + pageSize;
		return sqlMap.queryForList(queryId, parameterObject, skipResults, maxResults);
	}
}