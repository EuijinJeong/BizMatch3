package com.ktdsuniversity.edu.bizmatch.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

//멤버변수, List<VO> , List<String>... List<VO.멤버변수들>

public abstract class XssUtil {

	private static final List<Class<?>> GENERAL_TYPES = List.of(byte.class, short.class, int.class, long.class, double.class, float.class, boolean.class, String.class, List.class, Map.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T clean(T vo) {
		
		if (vo == null) {
			return vo;
		}
		
		if (vo.getClass() == String.class) {
			return sanitize(vo);
		}
		
		if (vo instanceof List list) {
			if (list != null && !list.isEmpty()) {
				return (T) sanitizeList(list);
			}
		}
		
		Field[] fieldArr = vo.getClass().getDeclaredFields(); 
		for (Field field : fieldArr) {
			field.setAccessible(true);
			if ( !GENERAL_TYPES.contains( field.getClass() )) {
				try {
					clean(field.get(vo));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			
			if (field.getType() == List.class) {
				try {
					List list = (List) field.get(vo);
					if (list != null && !list.isEmpty()) {
						sanitizeList(list);
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			
			if (field.getType() == String.class) {
				String fieldName = field.getName();
				String getterName = "get" + fieldName.substring(0, 1).toUpperCase() +  fieldName.substring(1);
				String setterName = "set" + fieldName.substring(0, 1).toUpperCase() +  fieldName.substring(1);
				
				try {
					Method getter = vo.getClass().getDeclaredMethod(getterName);
					if (isGetter(getter)) {
						String value = (String) getter.invoke(vo);
						value = sanitize(value);
						
						Method setter = vo.getClass().getDeclaredMethod(setterName, String.class);
						if (isSetter(setter)) {
							setter.invoke(vo, value);
						}
					}
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		
		return vo;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked" })
	private static List sanitizeList(List items) {
		for (int i = 0; i < items.size(); i++) { 
			items.set(i, sanitize(items.get(i)));
		}
		
		return items;
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T sanitize(T value) {
		if (value instanceof String string) {
			return (T) string.replace("<", "&lt;").replace(">", "&gt;");
		}
		return null;
	}
	
	private static boolean isGetter(Method method) {
		return method.getName().startsWith("get") && method.getReturnType() == String.class && method.getParameterCount() == 0;
	}
	
	private static boolean isSetter(Method method) {
		return method.getName().startsWith("set") && method.getReturnType() == void.class && method.getParameterCount() == 1 && method.getParameterTypes()[0] == String.class;
	}
	
}
