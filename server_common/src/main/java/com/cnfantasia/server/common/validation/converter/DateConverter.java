package com.cnfantasia.server.common.validation.converter;

import java.util.Date;

import com.cnfantasia.server.common.utils.FrameDateUtil;

/**
 * 类名：DateConverter  <br />
 *
 * 功能：日期格式转换器
 *
 */
public class DateConverter extends AbstractFormatConverter<Date> {
	
	/**
	 * 构造方法
	 */
	public DateConverter() {
		// 默认格式
		super("yyyyMMddHHmmss");
	}

	/**
	 * 功能: <br/>
	 * 
	 */
	@Override
	protected Date parse(Object original, String format, Object config) throws Exception {
		// 转换为日期
		return FrameDateUtil.parse((String) original, format);
	}
}
