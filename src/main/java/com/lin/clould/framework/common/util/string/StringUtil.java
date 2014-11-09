package com.lin.clould.framework.common.util.string;


public class StringUtil {
	
	public StringUtil()	{}
	
	/*
	 	private StringBuffer strBuffer = new StringBuffer(); // 멤버변수로는 사용하지 않는다. (메모리 누수 발생)
	 	strBuffer.append("test").append("test1"); 메서드 체인이 발생하지 않도록, append를 연속해서 사용하지 않는다.
	 */
	
	public static boolean isEmpty(String value){
		return nullCheck(value);    
	}
	
	public static String nvl(String str){
		if(nullCheck(str))
	    	return "";
	    else
	    	return str;
	}
	
	// 대소문자를 포함한 문자열 비교
	public static boolean wrongCaseCompare(String str,String compareStr){
		
		/*
		 
		 // 모든 글자를 대문자로 변환한 후 비교
		 if(str.toUpperCase().equals(compareStr.toUpperCase())){
		 	System.out.println("str과 compareStr는 다르다.");
		 }else{
		 	System.out.println("str과 compareStr는 다르다.");
		 }
		  
		 or
		 
		 // 모든 글자를 소문자로 변환한 후 비교
		 if(str.toLowerCase().equals(compareStr.toLowerCase())){
		  	System.out.println("str과 compareStr는 다르다.");
		 }else{
		 	System.out.println("str과 compareStr는 다르다.");
		 }
		  
		 */
		
		return str.equalsIgnoreCase(compareStr);
	}
	
	private static boolean nullCheck(String value) {
		
		// Boolean bool = new Boolean(true); 
		// Boolena 객체를 인스턴스화하는 것은 불필요한 작업이다. true 또는 false 밖에 없으며,
		// 이미 이 값이 Boolean 객체 내에 정의돼 있으므로 객체를 인스턴스화하거나 valueOf() 메서드를 사용힐 필요가 없다.
		/*
		Boolean bool = null;

		if(value==null || value.equals(""))
			bool = Boolean.TRUE;
		else
			bool =  Boolean.FALSE;
		
		return bool;
		*/	
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
		
		return value==null || value.equals("") || isBlank(value); // null이면 true값, boolean 값의 반환은 단순하게 처리한다.
		// return value!=null && !value.equals(""); // null이 아니면 true값
	}


	/*
	    ex)	str.trim().length() == 0
	  	 
	  	// trim과 length 메서드를 이용한 방식은 불필요한 인스턴스가 생성될 뿐만 아니라,
	  	// isWhiteSpace 메서드보다 정확도가 떨어진다.
	 */
	private static boolean isBlank(final String str) {
		int strLen;
		boolean result = true;
		if(str == null || str.length() == 0){
			result = true;
		}else{
			strLen = str.length();
			for(int i = 0; i <strLen; i++){
				if(!Character.isWhitespace(str.charAt(i))){
					result = false;
					break;
				}
			}
		}
		return result;
	}
	
}