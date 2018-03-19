package com.cnfantasia.server.common.validation.validator;

import com.cnfantasia.server.common.exception.SystemException;
import com.cnfantasia.server.common.validation.converter.TypeConverter;
import com.cnfantasia.server.common.validation.resolver.TypeConverterResolver;

/**
 * 类名：TypeConverterValidator  <br />
 *
 * 功能：用于类型转换器接入的校验器实现
 *
 */
public class TypeConverterValidator extends AbstractValidator {
	
	// 类型转换对象解析器
	private TypeConverterResolver resolver;
	
	/**
	 * 构造方法
	 */
	public TypeConverterValidator() {
		super("validation.type");
	}

	/**
	 * 功能: <br/>
	 * 
	 */
	@Override
	protected Object doValidate(String field, Object value, String message, Object config) throws Exception {
		String[] cfs = ((String) config).split("\\:");
		
		// 解析出类型转换器
		TypeConverter converter = resolveTypeConverter(cfs[0]);
		
		if (null == converter) {
			throw new SystemException("converter.resolve.result.is.null", new Object[] { cfs[0] });
		}
		
		// 交给转换器执行转换处理
		return converter.convert(value, cfs.length == 2 ? cfs[1] : null);
	}
	
	/**
	 * 
	 * 功能： <br/>
	 */
	protected TypeConverter resolveTypeConverter(String name) throws Exception {
		return resolver.resolve(name);
	}

	/**
	 */
	public void setResolver(TypeConverterResolver resolver) {
		this.resolver = resolver;
	}
}
