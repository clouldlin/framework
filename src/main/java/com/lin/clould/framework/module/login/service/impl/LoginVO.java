package com.lin.clould.framework.module.login.service.impl;

import java.io.Serializable;

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
	
}