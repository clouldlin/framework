package com.lin.clould.framework.common.invoker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lin.clould.framework.common.annotation.RequestParam;
import com.lin.clould.framework.common.annotation.ResponseMapping;
import com.lin.clould.framework.common.annotation.Result;
import com.lin.clould.framework.common.constant.CommandConstant;
import com.lin.clould.framework.common.util.ParameterUtil;

public class ProxyInvoker {
	
	private Method method;
	private Object obj;
	
	public ProxyInvoker(Method method, Object obj) {
		
		this.method = method;
		this.obj = obj;
	}
	
	public void invoke(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		
//		System.out.println(method);
//		System.out.println(obj);
		
// 		method.invoke(obj, args); // 파라미터를 어떻게 처리할 것인가?
		
//		// block + ctrl + /		
//		Annotation[][] annotations = method.getParameterAnnotations();
//		
//		// 1차적으로 메소드의 파라미터를 가져온다.
//		for (Annotation[] tempAnnotations : annotations) {
//			System.out.println(Arrays.toString(tempAnnotations));
//			// 2차로 파라미터마다 어노테이션을 가져온다. 파라미터당 어노테이션을 여러개 걸 수 있기 때무에.
//			for (Annotation annotation : tempAnnotations) {
//				if(annotation.annotationType() == RequestParam.class){
//					String value = ((RequestParam)annotation).value();
//					System.out.println("RequestParam Value : " + value);
//				}
//			}
//		}
		
		Class<?>[] parameterTypes = method.getParameterTypes();
		Object[] parameterArr = new Object[parameterTypes.length];
		
		// 가변 배열 파라미터 전달 index
		int idx = 0;
		
		
		// 사용자 정의나 Map 계열 파라미터 처리 
		for (int i = 0; i < parameterTypes.length; i++) {
			
			System.out.println("parameterTypes Check : " + parameterTypes[i]);
			
			String checkParameterTypeClass = parameterTypes[i].toString();
			String userParameterDefinedClass = checkParameterTypeClass.substring(checkParameterTypeClass.indexOf(" ") + 1);
			
			
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
		
		// 파라미터 처리 끝
		
		// 리턴타입 처리
		Result result = method.getAnnotation(Result.class);
		
		if(result == null){
			RequestDispatcher dispatcher = request.getRequestDispatcher(method.getName() + ".jsp");
			dispatcher.forward(request, reponse);
			return;
		}
		
		// 리턴값
		Object resutlObj = method.invoke(obj, parameterArr);
		
		if( method.isAnnotationPresent(ResponseMapping.class)){
			ResponseMapping responseResult = method.getAnnotation(ResponseMapping.class);
			request.setAttribute(responseResult.value(), resutlObj);
		}
		
		System.out.println("Reult Page : " + result.value());
		
		if(result.redirect() == false){
			RequestDispatcher dispatcher = request.getRequestDispatcher(result.value());
			
			dispatcher.forward(request, reponse);
		}
		
	}
	
}
