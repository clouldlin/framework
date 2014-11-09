package com.lin.clould.framework.common.util.number;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberUtil {
	
	/*
	 	자바의 64비트 정수형인 long 형으로도 겨우 9223372036854775807 (구백이십이경...)까지의 숫자만 나타낼 수 있습니다.
	 	그 이상의 정수가 필요하다면 BigInteger 클래스를 사용해야 합니다. 
	 	BigInteger 는 숫자의 크기에 제한이 없습니다. 사실상 무한대의 정수를 저장할 수 있습니다. 
	  	
	  	(연산자)
	  		- 더하기 : add()
			- 빼기   : subtract()	
			- 곱하기 : multiply()
			- 나누기 : divide()
	 */
	private static int bigIntegerExample() {
		
		// 불필요한 인스턴스를 만들지 말고 이미 정의돼 있는 값을 활용하자.
		// BigInteger biZero = new BigInteger("0");
		BigInteger biZero = BigInteger.ZERO;
		// BigInteger biOne = BigInteger.valueOf(1);
		BigInteger biOne = BigInteger.ONE;
		BigInteger biTen = BigInteger.TEN;
		
		BigInteger biTest1 = new BigInteger("1000000");
		BigInteger biTest2 = BigInteger.valueOf(20000000);
		
		return biZero.intValue() + biOne.intValue() + biTen.intValue() + biTest1.intValue() + biTest2.intValue();
	}
	
	/*
	  	BigDecimal는 리소스를 많이 점유하지만, 정확한 소수점 계산에 유용하다.
	 */
	private static BigDecimal bigDecimalExample() {
		
		BigDecimal bdOne = BigDecimal.ONE;
		BigDecimal bdOne1 = new BigDecimal("1.00");
		
		// BigDecimal의 값을 비교할 때는 equals보다 compareTo를 활용하자.
		System.out.println("bdOne.equals(bdOne1) : " + bdOne.equals(bdOne1));
		System.out.println("bdOne.compareTo(bdOne1) : " + (bdOne.compareTo(bdOne1) == 0));
		
		BigDecimal bdValue1 = new BigDecimal("4.7");
		BigDecimal bdValue2 = BigDecimal.valueOf(0.4);
		
		// BigDecimal 게산을 할 때는 연산자가 아닌 add(더하기), subtract(빼기), multiply(곱하기), divide(나누기) 등의 메서드를 사용한다.
		return bdValue1.add(bdValue2);
	}
	
	private static void isNan(){
		
		// 어떠한 이유로 숫자가 아닌 값(NaN: Not a Number)을 할당했다고 가정
		Double errorValue = Double.NaN;
		
		// 오류를 검증하기 위해 오류 값을 Double.NaN과 비교
		if(Double.isNaN(errorValue)){
		//if(errorValue == Double.NaN){
			System.out.println("오류 발생");
		}else{
			System.out.println("정상 처리");
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(NumberUtil.bigIntegerExample());
		System.out.println(NumberUtil.bigDecimalExample());
		NumberUtil.isNan();
	}

}
