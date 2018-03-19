package com.cnfantasia.server.common.validation.converter;

/**
 * 类名：IntegerConverter  <br />
 *
 * 功能：整型转换器
 *
 */
public class IntegerConverter extends NumberConverter {
	
	/**
	 * 构造方法
	 */
	public IntegerConverter() {
		super("########");
	}

	/**
	 * 功能: <br/>
	 * 
	 */
	@Override
	protected Number parse(Object original, String format, Object config) throws Exception {
		Number number = super.parse(original, format, config);
		
		// 转换为整型
		return Integer.valueOf(number.toString());
	}
}
