package com.lin.clould.common.property;

import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {
	public Properties prop;

	public PropertyFileReader() {
	}
	
	private InputStream getInputStreamOfPropertyFiles(String property) {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(property);
		return inputStream;
	}
	
	public void initializePropertyObject(String property) {
		prop = new Properties();
		InputStream inputStream = getInputStreamOfPropertyFiles(property);
		
		try {
			prop.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}