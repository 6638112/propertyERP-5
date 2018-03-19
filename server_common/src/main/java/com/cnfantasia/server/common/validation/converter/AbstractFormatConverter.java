package com.cnfantasia.server.common.validation.converter;

import com.cnfantasia.server.common.exception.ConversionException;


/**
 * 类名：AbstractFormatConverter  <br />
 *
 * 功能：基于格式的转换器基础实现
 */
public abstract class AbstractFormatConverter<T> extends AbstractTypeConverter {
	
	// 格式
	private String format;
	
	/**
	 * 构造方法
	 */
	public AbstractFormatConverter() {
	}
	
	/**
	 * 构造方法
	 */
	public AbstractFormatConverter(String format) {
		this.format = format;
	}

	/**
	 * 功能: <br/>
	 * 
	 */
	@Override
	protected Object doConvert(Object original, Object config) throws Exception {
		String _format = null != config ? (String) config : null;
		if (null == _format) {
			_format = format;
		}
		
		try {
			return parse(original, _format, config);
		} catch (Exception e) {
			logger.error("format parse error!", e);
			
			// 格式非法
			throw new ConversionException("parse.by.format.failer", new Object[] { _format });
		}
	}
	
	/**
	 * 
	 * 功能：供子类实现解析 <br/>
	 *
	 * @author xtwin <br/>
	 * @version 2013-12-17 上午11:54:16 <br/>
	 */
	protected abstract T parse(Object original, String format, Object config) throws Exception;

	/**
	 * @version 2013-12-17-上午11:53:24
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @version 2013-12-17-上午11:53:24
	 */
	public void setFormat(String format) {
		this.format = format;
	}
}
