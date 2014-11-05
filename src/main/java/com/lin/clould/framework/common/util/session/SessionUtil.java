package com.lin.clould.framework.common.util.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	
	public SessionUtil() {}
	
	/**
	 * 
	 * 세션 체크
	 * 
	 * @param request
	 * @param response
	 * @return boolean
	 */
	public static boolean sessionCheck(HttpServletRequest request){
		
		boolean chk = false;
		
		/*
		 *  HttpSession session = request.getSession(); // 세션 생성
   			request.getSession(false);
			  - (true)   세션이 없으면 새로 생성한다
		                   세션이 있으면 기존 세션을 리턴한다.
		      - (false) 세션이 없으면 null을 리턴한다.
		                   세션이 있으면 기존 세션을 리턴한다.
				
		   session.setMaxInactiveInterval()  -- 세션 유효시간 설정
		   session.setAttribute(arg0, arg1)  -- 세션에 값을 셋팅한다.
		   session.getAttribute(arg0)        -- 세션에 값을 가져온다.
		   session.removeAttribute(arg0)     -- 세션 값 제거
		   session.invalidate()              -- 세션을 무효화 시킨다.(로그아웃 처리)
		 * 
		 */
		
		HttpSession session = request.getSession(false); // false 를 주었으므로 셰션이 없을 경우 null을 반환한다.
		
		if(session == null){
			chk = false;
		}else{
			if( session.getAttribute("person.sessionInfo") != null){
				chk = true;
			}else{
				chk = false;
			}
		}
		return chk;
	}

	public static Object getSessionInfo(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session.getAttribute("person.sessionInfo");
	}
	
}