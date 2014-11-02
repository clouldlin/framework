package com.lin.clould.framework.common.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class AnnottionPackageScan {
	
	public static List<String> findClassesWithPackageName(String packageName) throws Exception {

		Enumeration<URL> en = AnnottionPackageScan.class.getClassLoader().getResources(packageName.replace(".", "/"));

		List<String> findClassFileNames = new ArrayList<String>();

		while (en.hasMoreElements()) {
			URL url = en.nextElement();
			File file = new File(url.toURI());
			findClassFileNames = findClassFile(findClassFileNames, packageName, file);
			
			System.out.println("Found Class : " + findClassFileNames.toString());
		}
		
		return findClassFileNames;
	}
	
	
	public static List<String> findClassFile(List<String> findClassFileNames, String packageName, File dir) {
		
		for (File f : dir.listFiles()) {
			
			if (f.isFile()) {
				findClassFileNames.add(packageName +  "." + f.getName().substring(0, f.getName().lastIndexOf(".")));
			}
			
			if (f.isDirectory()) {
				String subPackageName = packageName + "." + f.getName();
				
				findClassFile(findClassFileNames, subPackageName, f);
			}
		}
		
		return findClassFileNames;
	}
}