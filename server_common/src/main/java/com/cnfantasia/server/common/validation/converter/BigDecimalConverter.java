package com.cnfantasia.server.common.validation.converter;

import java.math.BigDecimal;

/**
 * 类名：BigDecimalConverter  <br />
 *
 * 功能：BigDecimal转换器
 *
 */
public class BigDecimalConverter extends NumberConverter {
	
	/**
	 * 功能: <br/>
	 * 
	 */
	@Override
	protected Number parse(Object original, String format, Object config) throws Exception {
		return new BigDecimal(super.parse(original, format, config).toString());
	}
}
