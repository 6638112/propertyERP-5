package com.cnfantasia.server.common.validation.converter;

import java.lang.reflect.Array;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 类名：AbstractTypeConverter  <br />
 *
 * 功能：类型转换基础实现
 *
 */
public abstract class AbstractTypeConverter implements TypeConverter {
	
	// 日志记录器
	protected final Log logger = LogFactory.getLog(getClass());
	
	// 类型
	protected Class<?> type;

	/**
	 * 功能: <br/>
	 * 
	 */
	//@Override
	public Object convert(Object original, Object config) throws Exception {
		Object value = original;
		
		if (null != original) {
			if (original instanceof Object[]) {
				
				// 强转为数组
				Object[] datas = (Object[]) original;
				
				// 创建对应类型的空数组
				Object[] _values = (Object[]) Array.newInstance(type, datas.length);
				
				// 通过循环，将数组中的每个值执行类型转换逻辑
				for (int i = 0, len = datas.length; i < len; i++) {
					Object tmp = datas[i];
					
					// 执行格式转换
					_values[i] = type.isInstance(tmp) ? tmp : doConvert(tmp, config);
				}
				
				// 付给变量，用于返回操作
				value = _values;
			} else {
				value = type.isInstance(original) ? original : doConvert(original, config);
			}
		}
		
		// 返回转换后的值
		return value;
	}
	
	/**
	 * 功能：供子类实现具体转换逻辑 <br/>
	 *
	 */
	protected abstract Object doConvert(Object original, Object config) throws Exception;

	/**
	 * 
	 */
	public Class<?> getType() {
		return type;
	}

	/**
	 * 
	 */
	public void setType(Class<?> type) {
		this.type = type;
	}
}
