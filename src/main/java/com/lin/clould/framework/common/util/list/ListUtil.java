package com.lin.clould.framework.common.util.list;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
	
	private static String[] listToArrayExample() {
		
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		
		// 아래와 같이 대상 배열의 자료형과 정확힌 길이를 전달한다.
		String[] array = (String[])list.toArray(new String[list.size()]);
		return array;
	}
	
	public static void main(String[] args) {
		String[] array = listToArrayExample();
		for (String value : array) {
			System.out.println(value);
		}
	}
	
}
