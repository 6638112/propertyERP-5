package com.cnfantasia.server.api.access.codec;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.access.codec.ake.MessageInfo.Message.KeyStore;
import com.cnfantasia.server.api.access.codec.ake.MessageInfo.Message.KeyStoreList;
import com.cnfantasia.server.api.access.codec.ake.bean.base.DataDetailObj;

public class SocketBeanUtil {

    public static <T> T convert2Bean(final Class<T> type, final Map map)
            throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
        final BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        final T obj = type.newInstance(); // 创建对象
        final PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            final PropertyDescriptor descriptor = propertyDescriptors[i];
            final String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                final Object value = map.get(propertyName);

                final Object[] args = new Object[1];
                args[0] = value;

                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }

    public static List<KeyStore> convert2KeyStore(final Object bean) {
        final List<KeyStore> list = new ArrayList<KeyStore>();
        try {
            final Map<String, String> map = convert2Map(bean);
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                final KeyStore.Builder builder = KeyStore.newBuilder();
                builder.setKey(entry.getKey());
                builder.setValue(entry.getValue());
                list.add(builder.build());
            }
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        } catch (final InvocationTargetException e) {
            e.printStackTrace();
        } catch (final IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Map<String, String> convert2Map(final Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        final Class<?> type = bean.getClass();
        final Map<String, String> returnMap = new HashMap<String, String>();
        final BeanInfo beanInfo = Introspector.getBeanInfo(type);

        final PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            final PropertyDescriptor descriptor = propertyDescriptors[i];
            final String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                final Method readMethod = descriptor.getReadMethod();
                final Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    if (result instanceof Date) {
                        returnMap.put(propertyName, new SimpleDateFormat("yyyyMMddHHmmss").format(result));
                    } else {
                        returnMap.put(propertyName, result.toString());
                    }
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    public static <T> T convertKeyStore(final Class<T> type, final List<KeyStore> srclist)
            throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
        final List<KeyStore> copy = new ArrayList<KeyStore>(srclist);

        final BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        final T obj = type.newInstance(); // 创建对象
        final PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            final PropertyDescriptor descriptor = propertyDescriptors[i];
            final String propertyName = descriptor.getName();

            for (final KeyStore ks : copy) {
                if (propertyName.equalsIgnoreCase(ks.getKey())) {
                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                    Object value = ks.getValue();

                    if (descriptor.getPropertyType().equals(Boolean.class)) {
                        value = Boolean.valueOf(Boolean.parseBoolean(value.toString()));
                    } else if (descriptor.getPropertyType().equals(Byte.class)) {
                        value = Byte.valueOf(Byte.parseByte(value.toString()));
                    } else if (descriptor.getPropertyType().equals(Integer.class)) {
                        value = Integer.valueOf(Integer.parseInt(value.toString()));
                    } else if (descriptor.getPropertyType().equals(String.class)) {
                        value = value.toString();
                    } else if (descriptor.getPropertyType().equals(Long.class)) {
                        value = Long.valueOf(Long.parseLong(value.toString()));
                    } else if (descriptor.getPropertyType().equals(Date.class)) {
                        try {
                            value = new SimpleDateFormat("yyyyMMddHHmmss").parse(value.toString());
                        } catch (final ParseException e) {
                            e.printStackTrace();
                        }
                    } else if (descriptor.getPropertyType().equals(Enum.class)) {
                        try {
                            final Class clazz = Class.forName(descriptor.getPropertyType().getName());
                            value = Enum.valueOf(clazz, value.toString());
                        } catch (final ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (descriptor.getPropertyType().equals(Float.class)) {
                        value = Float.valueOf(Float.parseFloat(value.toString()));
                    } else if (descriptor.getPropertyType().equals(BigDecimal.class)) {
                        value = new BigDecimal(value.toString());
                    } else if (DataDetailObj.class.isAssignableFrom(descriptor.getPropertyType())) {
                        final Class clazz = descriptor.getReadMethod().getReturnType();
                        value = convert2Bean(clazz, (Map) value);
                    } else if (descriptor.getPropertyType().equals(List.class)) {
                        final ParameterizedType parameterizedType = (ParameterizedType) descriptor.getReadMethod().getGenericReturnType();
                        final Type[] types = parameterizedType.getActualTypeArguments();
                        final Class clazz = (Class) types[0];
                        final List tmpList = new ArrayList<Object>();
                        for (final KeyStoreList ksl : ks.getKeyStoreListList()) {
                            tmpList.add(convertKeyStore(clazz, ksl.getKeyStoreList()));
                        }
                        value = tmpList;
                    } else if (descriptor.getPropertyType().equals(byte[].class)) {
                        value = ks.getByteValue().toByteArray();
                    } else {
                        System.out.println("不认识的类型 : " + descriptor);
                    }

                    final Object[] args = new Object[1];
                    args[0] = value;
                    descriptor.getWriteMethod().invoke(obj, args);
                    copy.remove(ks);
                    break;
                }
            }
        }
        return obj;
    }
}
