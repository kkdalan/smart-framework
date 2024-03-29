package org.smart4j.framework.util.support;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClassSupport {

	private ClassSupport() {}
	
	static final Set<Class<?>> SUPPORTED_CLASSES = new HashSet<Class<?>>();

	static {
		Class<?>[] classes = {
				boolean.class, Boolean.class,
				short.class, Short.class,
				int.class, Integer.class,
				long.class, Long.class,
				float.class, Float.class,
				double.class, Double.class,
				String.class,
				Date.class,
				Timestamp.class,
				BigDecimal.class
		};
		SUPPORTED_CLASSES.addAll(Arrays.asList(classes));
	}
	
	static boolean isSupportedSQLObject(Class<?> clazz) {
		return clazz.isEnum() || SUPPORTED_CLASSES.contains(clazz);
	}
	
	public static Map<String,Method> findPublicGetters(Class<?> clazz){
		Map<String,Method> map = new HashMap<String,Method>();
		Method[] methods = clazz.getMethods();
		for(Method method : methods) {
			if(Modifier.isStatic(method.getModifiers())) { continue; }
			if(method.getParameterTypes().length != 0) { continue; }
			if(method.getName().equals("getClass")) { continue; }

			Class<?> returnType = method.getReturnType();
			if(void.class.equals(returnType)) { continue; }
			if(!isSupportedSQLObject(returnType)) { continue; }
			if(isBooleanGetter(method)) { 
				map.put(getGetterName(method),method);
				continue; 
			}
			if(!method.getName().startsWith("get")) { continue; }
			if(method.getName().length() < 4) { continue; }
			
			map.put(getGetterName(method),method);
		}
		return map;
	}
	
	public static boolean isBooleanSetter(Method method) {
		Class<?> returnType = method.getReturnType();
		return (returnType.equals(boolean.class) || returnType.equals(Boolean.class)) 
				&& method.getName().startsWith("is") 
				&& method.getName().length() > 2;
	}
	
	public static Map<String,Method> findPublicSetters(Class<?> clazz){
		Map<String,Method> map = new HashMap<String,Method>();
		Method[] methods = clazz.getMethods();
		for(Method method : methods) {
			if(Modifier.isStatic(method.getModifiers())) { continue; }
			if(!void.class.equals(method.getReturnType())) { continue; }
			if(!method.getName().startsWith("set")) { continue; }
			if(method.getName().length() < 4) { continue; }
			if(method.getParameterTypes().length != 1) { continue; }
			if(!isSupportedSQLObject(method.getParameterTypes()[0])) { continue; }

			map.put(getSetterName(method),method);
		}
		return map;
	}
	
	public static boolean isBooleanGetter(Method method) {
		Class<?> returnType = method.getReturnType();
		return (returnType.equals(boolean.class) || returnType.equals(Boolean.class)) 
				&& method.getName().startsWith("is") 
				&& method.getName().length() > 2;
	}
	
	public static String getGetterName(Method getter) {
		String name = getter.getName();
		if(name.startsWith("is")) {
			name = name.substring(2);
		}else {
			name = name.substring(3);
		}
		String propertyName = Character.toLowerCase(name.charAt(0)) + name.substring(1);
		return propertyName;
	}
	
	public static String getSetterName(Method setter) {
		String name = setter.getName().substring(3);
		String propertyName = Character.toLowerCase(name.charAt(0)) + name.substring(1);
		return propertyName;
	}
	
	public static Field[] findFields(Class<?> clazz) {
		return clazz.getDeclaredFields();
	}

}
