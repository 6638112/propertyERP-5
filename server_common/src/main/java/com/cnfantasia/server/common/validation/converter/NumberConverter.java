package com.cnfantasia.server.common.validation.converter;

import java.text.DecimalFormat;

/**
 * 类名：NumberConverter  <br />
 *
 * 功能：数字类型转换
 *
 */
public class NumberConverter extends AbstractFormatConverter<Number> {
	
	/**
	 * 构造方法
	 */
	public NumberConverter() {
		this("#.##");
	}
	
	/**
	 * 构造方法
	 */
	public NumberConverter(String format) {
		super(format);
	}

	/**
	 * 功能: <br/>
	 * 
	 */
	@Override
	protected Number parse(Object original, String format, Object config) throws Exception {
		// 转换为数字类型
		return new DecimalFormat(format).parse(String.valueOf(original));
	}
}
