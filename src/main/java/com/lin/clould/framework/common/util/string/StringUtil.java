package com.lin.clould.framework.common.util.string;


public class StringUtil {
	
	public StringUtil()	{}
	
	
	/**
	 * 문자열(String)의 값이 null또는 비었는지("")의 여부를 체크.
	 * 
	 * @param str 체크할 문자열
	 * @return 문자열에 값이 존재하면 false, null이거나 비었으면("") true를 반환한다.
	 */
	public static boolean isEmpty(String str){
	      return str == null || str.trim().length() == 0 || "null".equals(str);    
	}
	
	/**
	 * 
	 * @param str 체크할 문자열
	 * @return 문자열에 값이 존재하면 문자열 반환, null이거나 비었으면 "" 를 반환한다.
	 */
	public static String nvl(String str){
	    if(str == null || str.trim().length() == 0 || "null".equals(str)){
	    	return "";
	    }else{
	    	return str;
	    }
	      
	}

}