package com.cnfantasia.server.common.validation.converter;

import com.cnfantasia.server.common.exception.ConversionException;


/**
 * 类名：BooleanConverter  <br />
 *
 * 功能：布尔型转换器
 *
 */
public class BooleanConverter extends AbstractTypeConverter {

	/**
	 * 功能: <br/>
	 * 
	 */
	@Override
	protected Object doConvert(Object original, Object config) throws Exception {
		String tmp = String.valueOf(original).toLowerCase();
		
		Boolean value = null;
		if (tmp.matches("true|yes|y|1")) {
			value = Boolean.TRUE;
		} else if (tmp.matches("false|no|n|0")) {
			value = Boolean.FALSE;
		} else {
			throw new ConversionException("not.valid.boolean.value", new Object[] { tmp });
		}
		
		return value;
	}

}
