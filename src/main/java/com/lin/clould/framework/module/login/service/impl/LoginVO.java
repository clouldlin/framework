package com.lin.clould.framework.module.login.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LoginVO implements Serializable {

	private static final long serialVersionUID = -5409865580055887284L;
	
	private String id;
	private String pw;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "LoginVO [id=" + id + ", pw=" + pw + ", name=" + name + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pw == null) ? 0 : pw.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginVO other = (LoginVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pw == null) {
			if (other.pw != null)
				return false;
		} else if (!pw.equals(other.pw))
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		
		Map<LoginVO, String> map = new HashMap<LoginVO, String>(); 
		
		LoginVO login = new LoginVO();
		login.setId("heo");
		login.setName("허정도");
		login.setPw("1111");
		
		map.put(login, "value");
		
		// 해쉬코드 메소드를 생략하면 동일한 값의 객체를 찾지 못한다.
		LoginVO login_hashcode = new LoginVO();
		login_hashcode.setId("heo");
		login_hashcode.setName("허정도");
		login_hashcode.setPw("1111");
		
		/*
			참조 : http://jnylove.tistory.com/182
			
			오버라이드 하지 않으면, 기본적으로 Heap에 있는 각 객체마다 서로 다른 값을 가지는 유일한 정수값을 반환한다.
		  	Class에서 hashCode() method를 오버라이드 하지 않으면 절대로 그 유형의 두 객체가 같은 것으로 간주될 수 없다.
		 */
		System.out.println(login.hashCode());
		System.out.println(login_hashcode.hashCode());

		System.out.println(login.equals(login_hashcode));
		System.out.println(map.get(login_hashcode));
		
		/*
		 	boolean					:	(value ? 1:0) 
		 	byte, char, short, int  :	(int) vlaue
		 	float					:	Float.floatToInBits(value)	
		 	double					:	Double.doubleToLongBits(value)
		 	String 및 기타 객체		:	"test".hashCode()
		 */
	}
	
}
