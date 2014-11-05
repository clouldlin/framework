package com.lin.clould.framework.common.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.junit.Test;

public class AnnottionPackageScanTest {
	
	private static List<String> findClassesWithPackageName(String packageName) throws Exception {

		Enumeration<URL> en = AnnottionPackageScan.class.getClassLoader().getResources(packageName.replace(".", "/"));

		List<String> findClassFileNames = new ArrayList<String>();

		while (en.hasMoreElements()) {
			URL url = en.nextElement();
			System.out.println(url.toURI());
			File file = new File(url.toURI());
			findClassFileNames = findClassFile(findClassFileNames, packageName, file);
			
			System.out.println("Found Class : " + findClassFileNames.toString());
		}
		
		return findClassFileNames;
	}
	
	
	private static List<String> findClassFile(List<String> findClassFileNames, String packageName, File dir) {
		
		for (File f : dir.listFiles()) {
			
			if (isClassFile(f) ) {
				findClassFileNames.add(packageName +  "." + f.getName().substring(0, f.getName().lastIndexOf(".")));
			}
			
			if (f.isDirectory()) {
				String subPackageName = packageName + "." + f.getName();
				
				findClassFile(findClassFileNames, subPackageName, f);
			}
		}
		
		return findClassFileNames;
	}


	private static boolean isClassFile(File f) {
		return f.isFile() && f.getName().endsWith("class");
	}
	
	@Test
	public void nothing(){
		
	}
	
	@Test
	public void canCreateObject() throws Exception{
		List<String> controllers = findClassesWithPackageName("com.lin.clould.framework.module");
		
		
		
	}
	
}