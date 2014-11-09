package com.lin.clould.framework.common.util.string;


public class StringUtil {
	
	public StringUtil()	{}
	
	public static boolean isEmpty(String value){
		return nullCheck(value);    
	}
	
	public static String nvl(String str){
		if(nullCheck(str))
	    	return "";
	    else
	    	return str;
	}
	
	private static boolean nullCheck(String value) {
		
		// Boolean bool = new Boolean(true); 
		// Boolena 객체를 인스턴스화하는 것은 불필요한 작업이다. true 또는 false 밖에 없으며,
		// 이미 이 값이 Boolean 객체 내에 정의돼 있으므로 객체를 인스턴스화하거나 valueOf() 메서드를 사용힐 필요가 없다.
		
		Boolean bool = null;

		if(value==null || value.equals(""))
			bool = Boolean.TRUE;
		else
			bool =  Boolean.FALSE;
		
		return bool;
			
		// case-1)
		// return value==null && value.equals(""); 
		// value 값이 null이 아닐 때만 value.equals("")이 실행돼야 하는데,
		// value가 null일 때 value.equals("")가 실행되어 NullPointerException이 발생한다.
		// 이 같은 상황에서 올바른 논리연산자는 &&가 아닌 || 이어야 한다.
		
		// case-2)
		// return value != null || !value.equals("");
		// value값이 null이더라도 !value.equals("")이 실행되어 NullPointerException이 발생한다.
		// 이와 같은 상황에서 올바른 논리연산자는 ||가 이닌 &&여야 한다.
		
		// case-3) 
		// return value==null || value.equals(""); 처럼 equals 메서드를 호출하기 이전에 null값을 확인해서
		// value가 null이면 equals 메서드를 실행하는 것ㅇ르 방지해야 한다.
		
		// return value==null || value.equals(""); // null이면 true값
		// return value!=null && !value.equals(""); // null이 아니면 true값
	}
	
}