package com.cnfantasia.server.common.validation.resolver;

import com.cnfantasia.server.common.validation.converter.TypeConverter;

/**
 * 类名：TypeConverterResolver  <br />
 *
 * 功能：类型转换器解析器
 */
public interface TypeConverterResolver {

	/**
	 * 
	 * 功能：解析出类型转换器 <br/>
	 *
	 */
	TypeConverter resolve(String name) throws Exception;
}
