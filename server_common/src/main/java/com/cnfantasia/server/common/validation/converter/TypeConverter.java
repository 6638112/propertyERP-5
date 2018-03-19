package com.cnfantasia.server.common.validation.converter;

/**
 * 类名：TypeConverter  <br />
 *
 * 功能：类型转换器定义
 *
 */
public interface TypeConverter {

	/**
	 * 
	 * 功能：执行类型转换 <br/>
	 *
	 */
	//Object convert(Object original, Map<String, Object> config) throws Exception;
	Object convert(Object original, Object config) throws Exception;
}
