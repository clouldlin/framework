package com.lin.clould.framework.common.invoker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.lin.clould.framework.common.annotation.RequestParam;
import com.lin.clould.framework.common.annotation.SessionCertification;
import com.lin.clould.framework.common.constant.CommandConstant;
import com.lin.clould.framework.common.util.ParameterUtil;
import com.lin.clould.framework.common.util.session.SessionUtil;
import com.lin.clould.framework.common.view.View;

public class ProxyInvoker {
	
	private static Logger logger = Logger.getLogger(ProxyInvoker.class); 
	
	private Method method;
	private Object obj;
	
	public ProxyInvoker(Method method, Object obj) {
		
		this.method = method;
		this.obj = obj;
	}
	
	public void invoke(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Class<?>[] parameterTypes = method.getParameterTypes();
		Object[] parameterArr = new Object[parameterTypes.length];
		
		// 가변 배열 파라미터 전달 index
		int idx = 0;
		
		
		// 사용자 정의나 Map 계열 파라미터 처리 
		for (int i = 0; i < parameterTypes.length; i++) {
			
			System.out.println("parameterTypes Check : " + parameterTypes[i]);
			
			String checkParameterTypeClass = parameterTypes[i].toString();
			String userParameterDefinedClass = checkParameterTypeClass.substring(checkParameterTypeClass.indexOf(" ") + 1);
			System.out.println("userParameterDefinedClass :" + userParameterDefinedClass);
			
			// vo 클래스 파라미터 check
			// ex) class com.lin.clould.anno.user.vo.UserVO 파싱 처리
			boolean isUserDefinedClass = CommandConstant.findClassNameList.contains(userParameterDefinedClass);

			if(isUserDefinedClass){
				System.out.println("사용자 정의 파라미터 처리!");
				parameterArr[i] = new ParameterUtil().makeVO(request, userParameterDefinedClass);
				continue;
			}
			
			if("java.util.HashMap".equals(userParameterDefinedClass) || "java.util.Map".equals(userParameterDefinedClass) ){
				Map<String, String[]> hashMap = request.getParameterMap();
				parameterArr[i] = hashMap;
			}
			
			if("javax.servlet.http.HttpServletRequest".equals(userParameterDefinedClass)){
				parameterArr[i] = request;
			}
			
			if("javax.servlet.http.HttpServletResponse".equals(userParameterDefinedClass)){
				parameterArr[i] = response;
			}
			
		}
		
		// 사용자정의 자료형은 이미 체크를 해서 해당 순번에 데이터 값을 전달했으니, Annotation 파라미터만 체크하면 된다.
		// Object[] parameterArr의 null 인덱스를 체크하자.
		
		// 어노테이션 파라미터 처리
		Annotation[][] annotations = method.getParameterAnnotations();
		
		for (Annotation[] tempAnnotations : annotations) {
			
			System.out.println(Arrays.toString(tempAnnotations));
			
			for (Annotation annotation : tempAnnotations) { // 하나의 파라미터에 여러개의 annotition이 붙을 수 있기 때문에.
				
				if(parameterArr[idx] == null){
					if(annotation.annotationType() == RequestParam.class){
						String value = ((RequestParam)annotation).value();
						System.out.println("RequestParam Value : " + value);
						
						if(value.equals(RequestParam.SESSION)){
							parameterArr[idx] = request.getSession();
						}else{
							// 파라미터 타입이 Integer라면, 파싱단계가 추가될 것이다.
							parameterArr[idx] = request.getParameter(value);
						}
					}
				}
			}
			
			idx++;
		} 
		
		System.out.println("parameterArr : " + Arrays.toString(parameterArr));
		
		// SessionCertification.clsss check
		if(method.isAnnotationPresent(SessionCertification.class)){ 
			if(!SessionUtil.sessionCheck(request) ){
				SessionCertification sessionCertificationAnno = method.getAnnotation(SessionCertification.class);
				System.out.println(sessionCertificationAnno.message());
				
				request.setAttribute("message", sessionCertificationAnno.message());
				response.sendRedirect("/framework/framework/login/login.do");
				return;
				//RequestDispatcher dispatcher = request.getRequestDispatcher("/framework/framework/login/login.do");
				//dispatcher.forward(request, response);
			}
		}
		
		View resutlObj = (View)method.invoke(obj, parameterArr);
		logger.info("리턴값 :" + resutlObj.toString());
		
		// 리다이렉트
		if(resutlObj.isRedirect() == true){
			response.sendRedirect(resutlObj.getNextPage());
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/" + resutlObj.getNextPage() + ".jsp");
		dispatcher.forward(request, response);
		// 파라미터 처리 끝
		
		/*
		// 리턴타입 처리
		Result result = method.getAnnotation(Result.class);
		
		if(result == null){
			RequestDispatcher dispatcher = request.getRequestDispatcher(method.getName() + ".jsp");
			dispatcher.forward(request, reponse);
			return;
		}
		
		// 리턴값
		Object resutlObj = method.invoke(obj, parameterArr);
		
		logger.info("리턴값 :" + resutlObj.toString());
		
		if( method.isAnnotationPresent(ResponseMapping.class)){
			ResponseMapping responseResult = method.getAnnotation(ResponseMapping.class);
			request.setAttribute(responseResult.value(), resutlObj);
		}
		
		System.out.println("Reult Page : " + result.value());
		
		if(result.redirect() == false){
			RequestDispatcher dispatcher = request.getRequestDispatcher(result.value());
			
			dispatcher.forward(request, reponse);
		}
		*/
	}
	
}
