package com.lin.clould.common.property;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PropertyFileReaderTest {

	private static final String DATABASE = "database";
	private static final String DATABASE_DRIVER = "driver";
	private static final String DATABASE_URL = "url";
	private static final String DATABASE_PASSWORD = "password";
	private static final String DATABASE_USERNAME = "username";

	private static final String PROPERTY_DATABASE_CENTOS_DATABASE_PROPERTIES = "property/database/centos_database.properties";
	
	private PropertyFileReader property = new PropertyFileReader();
	
	@Test
	public void nothing(){
		
	}
	
	@Test
	public void centos_database_property_file_read(){
		
		property.initializePropertyObject(PROPERTY_DATABASE_CENTOS_DATABASE_PROPERTIES);
		
		assertThat(property.prop.getProperty(DATABASE), is("oracle"));
		assertThat(property.prop.getProperty(DATABASE_DRIVER), is("oracle.jdbc.driver.OracleDriver"));
		assertThat(property.prop.getProperty(DATABASE_URL), is("jdbc:oracle:thin:@192.168.31.128:1521:orcl"));
		assertThat(property.prop.getProperty(DATABASE_USERNAME), is("heo"));
		assertThat(property.prop.getProperty(DATABASE_PASSWORD), is("heo"));
		
	}
	
}
