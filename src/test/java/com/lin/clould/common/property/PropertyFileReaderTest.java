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

	private static final String PROPERTY_DATABASE_CENTOS_DATABASE_PROPERTIES = "property/database/maria_database.properties";
	
	private PropertyFileReader property = new PropertyFileReader();
	
	@Test
	public void nothing(){
		
	}
	
	@Test
	public void centos_database_property_file_read(){
		
		property.initializePropertyObject(PROPERTY_DATABASE_CENTOS_DATABASE_PROPERTIES);
		
		assertThat(property.prop.getProperty(DATABASE), is("maria"));
		assertThat(property.prop.getProperty(DATABASE_DRIVER), is("org.mariadb.jdbc.Driver"));
		assertThat(property.prop.getProperty(DATABASE_URL), is("jdbc:mariadb://14.63.171.133:3306/lin"));
		assertThat(property.prop.getProperty(DATABASE_USERNAME), is("lin"));
		assertThat(property.prop.getProperty(DATABASE_PASSWORD), is("lin"));
		
	}
	
}
