package com.lin.clould.common.database.ibatis;

import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapClientManager {

	private static String PATH_SQL_MPA_CONFIG_XML = "context/database/ibatis/sql-map-config.xml";
	private static String ENCODING = "UTF-8";
	private static SqlMapClient sqlMap;

	private SqlMapClientManager(){
		
	}
	
    public static SqlMapClient getSqlMapClient() {
    	if (sqlMap == null){
    		 try {
         		Charset charset = Charset.forName(ENCODING);
        	 	Resources.setCharset(charset);
        	 	String resource = PATH_SQL_MPA_CONFIG_XML;
                Reader reader = Resources.getResourceAsReader(resource);
                sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
    		 } catch (Exception e) {
                 e.printStackTrace();
                 throw new RuntimeException(e);
    		 }
    	}else{
			
		}
		return sqlMap;
    }
}