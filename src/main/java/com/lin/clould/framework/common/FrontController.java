package com.lin.clould.framework.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.lin.clould.framework.common.annotation.Controller;
import com.lin.clould.framework.common.annotation.RequestMapping;
import com.lin.clould.framework.common.constant.CommandConstant;
import com.lin.clould.framework.common.invoker.ProxyInvoker;
import com.lin.clould.framework.common.util.AnnottionPackageScan;

public class FrontController extends HttpServlet {
	
	private static final long serialVersionUID = 6196450769868311233L;
	
	private static Logger logger = Logger.getLogger(FrontController.class); 
	
	private Map<String, ProxyInvoker> proxyInvokerMap = new HashMap<String, ProxyInvoker>();
	
    public FrontController() {
        super();
    }
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		String targetPackage = config.getInitParameter("package-scan");
		
		try {
			CommandConstant.findClassNameList = AnnottionPackageScan.findClassesWithPackageName(targetPackage);
			
			for(String className : CommandConstant.findClassNameList){
				Class<?> clazz = Class.forName(className);
				
				if(clazz.isAnnotationPresent(Controller.class) == false){
					continue;
				}
				
				// 컨트롤러 객체를 생성해 두었음.
				Object obj = clazz.newInstance();
				Controller ControllerAnnotationClass = clazz.getAnnotation(Controller.class);
				
				controllerUrlMapping(clazz, obj, ControllerAnnotationClass.value());
			}
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}

    private void controllerUrlMapping(Class<?> clazz, Object obj, String controller_value) {
    	
    	Method[] methods = clazz.getMethods();
    	
    	for(Method m : methods){
    		
    		if(m.isAnnotationPresent(RequestMapping.class) == false){
    			continue;
    		}
    		
    		RequestMapping RequestMappingAnnotationClass = m.getAnnotation(RequestMapping.class);
    		System.out.println(obj.toString() + " : " + controller_value + "" + RequestMappingAnnotationClass.value());
    		
    		proxyInvokerMap.put("/" + controller_value + "/" + RequestMappingAnnotationClass.value(), new ProxyInvoker(m, obj));
    	}
		
	}

	@Override
    protected void service(HttpServletRequest request, HttpServletResponse reponse) {
		
    	String requestURI = request.getPathInfo();
    	
    	ProxyInvoker invoker = proxyInvokerMap.get(requestURI);
    	
		try {
			invoker.invoke(request, reponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }


}
