package com.cnfantasia.server.common.validation.resolver;

import java.util.Map;

import com.cnfantasia.server.common.validation.converter.TypeConverter;

/**
 * 类名：MappingTypeConverterResolver  <br />
 *
 * 功能：基于名称映射的类型转换对象解析器
 */
public class MappingTypeConverterResolver implements TypeConverterResolver {
	
	// 名称映射转换器
	private Map<String, TypeConverter> mapping;

	/**
	 * 功能: <br/>
	 * 
	 */
	//@Override
	public TypeConverter resolve(String name) throws Exception {
		return mapping.get(name);
	}

	/**
	 * 
	 */
	public void setMapping(Map<String, TypeConverter> mapping) {
		this.mapping = mapping;
	}

}
