package com.lin.clould.framework.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ParameterUtil {

	public Object makeVO(HttpServletRequest request, String className) throws Exception {

		Class<?> clazz = Class.forName(className);

		Object result = clazz.newInstance();

		Map<String, String[]> paramMap = request.getParameterMap();

		BeanInfo info = Introspector.getBeanInfo(clazz);

		PropertyDescriptor[] props = info.getPropertyDescriptors();

		for (PropertyDescriptor prop : props) {

			String propName = prop.getName();
			Class<?> propType = prop.getPropertyType();

			Method setMethod = prop.getWriteMethod();

			if (setMethod == null) {
				continue;
			}

			if (paramMap.containsKey(propName) == false) {
				continue;
			}

			invokeSetMethod(paramMap, result, propName, propType, setMethod);

		}
		return result;
	}

	private void invokeSetMethod(Map<String, String[]> paramMap, Object result,	String propName, Class<?> propType, Method setMethod) throws Exception {

		Object data = null;

		if (propType == java.lang.String.class) {

			data = paramMap.get(propName)[0];

		} else if (propType == int.class || propType == Integer.class) {

			data = Integer.parseInt(paramMap.get(propName)[0]);

		} else if (propType == double.class || propType == Double.class) {

			data = Double.parseDouble(paramMap.get(propName)[0]);

		} else if (propType == int[].class) {

			int[] tempArr = new int[paramMap.get(propName).length];

			for (int i = 0; i < tempArr.length; i++) {
				tempArr[i] = Integer.parseInt(paramMap.get(propName)[i]);
			}

			data = tempArr;
		}

		setMethod.invoke(result, data);

	}
}