package com.lin.clould.common.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import com.lin.clould.common.property.PropertyFileReader;

public class DatabaseConnectionTest {
	
	private static final String DATABASE = "database";
	private static final String DATABASE_DRIVER = "driver";
	private static final String DATABASE_URL = "url";
	private static final String DATABASE_PASSWORD = "password";
	private static final String DATABASE_USERNAME = "username";

	private static final String PROPERTY_DATABASE_CENTOS_DATABASE_PROPERTIES = "property/database/maria_database.properties";
	
	private PropertyFileReader getDatabasePropertyObject(String property_file) {
		PropertyFileReader property = new PropertyFileReader();
		property.initializePropertyObject(property_file);
		return property;
	}
	
	private void database_connection_load(String database, String database_driver, String database_url, String database_username, String database_password) throws ClassNotFoundException, SQLException {
		Class.forName(database_driver);
		
		selected_database_and_getConnection(database, database_url, database_username, database_password);
	}

	private void selected_database_and_getConnection(String database, String database_url, String database_username, String database_password) throws SQLException {
		Connection conn;
		if (database.equals("maria")) conn = DriverManager.getConnection(database_url, database_username, database_password);
	}
	
	@Test
	public void nothing(){
		
	}
	
	@Test
	public void centos_server_database_canConnection() throws Exception{
		PropertyFileReader property = getDatabasePropertyObject(PROPERTY_DATABASE_CENTOS_DATABASE_PROPERTIES);	
		
		String database = property.prop.getProperty(DATABASE);
		String database_driver = property.prop.getProperty(DATABASE_DRIVER);
		String database_url = property.prop.getProperty(DATABASE_URL);
		String database_username = property.prop.getProperty(DATABASE_USERNAME);
		String database_password = property.prop.getProperty(DATABASE_PASSWORD);
		
		database_connection_load(database, database_driver, database_url,	database_username, database_password);
	}

}