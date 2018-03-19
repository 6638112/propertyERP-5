package com.cnfantasia.server.business.pub.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MapConverter {
//	public static void main(String[] args) {
//		Message msg = new Message();
//		msg.setTime("123");
//		System.out.println(ConvertObjToMap(msg).get("time"));
//	}
	private static Log logger  = LogFactory.getLog(MapConverter.class);
	public static Map<String,Object> convertBean(Object obj) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (obj == null)
			return null;
		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				try {
					Field f = obj.getClass().getDeclaredField(fields[i].getName());
					f.setAccessible(true);
					Object o = f.get(obj);
					reMap.put(fields[i].getName(), o);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} catch (SecurityException e) {
			logger.debug(e.getMessage());
		}
		return reMap;
	}
	
	/**
   * 将一个 JavaBean 对象转化为一个  Map
   * @param bean 要转化的JavaBean 对象
   * @return 转化出来的  Map 对象
   * @throws IntrospectionException 如果分析类属性失败
   * @throws IllegalAccessException 如果实例化 JavaBean 失败
   * @throws InvocationTargetException 如果调用属性的 setter 方法失败
   */
	public static Map<String,Object> toMap(Object bean){
  	try {
  		Class<?> type = bean.getClass();
      Map<String,Object> returnMap = new HashMap<String,Object>();
      BeanInfo beanInfo = Introspector.getBeanInfo(type);

      PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
      for (int i = 0; i< propertyDescriptors.length; i++) {
          PropertyDescriptor descriptor = propertyDescriptors[i];
          String propertyName = descriptor.getName();
          if (!propertyName.equals("class")) {
              Method readMethod = descriptor.getReadMethod();
              Object result = readMethod.invoke(bean, new Object[0]);
              if (result != null) {
                  returnMap.put(propertyName, result);
              } else {
                  returnMap.put(propertyName, null);
              }
          }
      }
      return returnMap;
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
    return null;
  }
}
