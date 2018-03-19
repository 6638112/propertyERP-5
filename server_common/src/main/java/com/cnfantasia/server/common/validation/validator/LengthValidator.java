package com.cnfantasia.server.common.validation.validator;

import java.util.Collection;
import java.util.Map;

import com.cnfantasia.server.common.exception.SystemException;
import com.cnfantasia.server.common.exception.ValidationException;
import com.cnfantasia.server.common.utils.StringUtils;


/**
 * 类名：LengthValidator  <br />
 *
 * 功能：长度校验器
 */
public class LengthValidator extends AbstractValidator {
	
	// 最大长度，-1为不限
	private int max = -1;
	
	// 最小长度，-1为不限
	private int min = -1;
	
	// 按字符比较
	private boolean byChar = false;
	
	// 编码方式
	private String encoding = "UTF-8";
	
	/**
	 * 构造方法
	 */
	public LengthValidator() {
		super("validation.length");
	}

	/**
	 * 功能: <br/>
	 */
	@Override
	protected Object doValidate(String field, Object value, String message, Object config) throws Exception {
		int _max = max;
		int _min = min;
		boolean _byChar = byChar;
		String _encoding = encoding;
		
		if (null != config) {
			// 重写了配置
			String[] cfgs = ((String) config).split(",");
			
			int len = cfgs.length;
			
			if (len > 0) {
				if (! StringUtils.isEmpty(cfgs[0])) {
					_min = Integer.parseInt(cfgs[0]);
				}
			}
			
			if (len > 1) {
				if (! StringUtils.isEmpty(cfgs[1])) {
					_max = Integer.parseInt(cfgs[1]);
				}
			}
			
			if (len > 2) {
				if (! StringUtils.isEmpty(cfgs[2])) {
					_byChar = Boolean.parseBoolean(cfgs[2]);
				}
			}
			
			if (len > 3) {
				if (! StringUtils.isEmpty(cfgs[3])) {
					_encoding = cfgs[3];
				}
			}
		}
		
		// 根据配置，计算长度
		int length = parseLength(value, _byChar, _encoding);
		
		if (logger.isDebugEnabled()) {
			logger.debug("parse length is : " + length);
		}
		
		// 执行长度检查
		if (_min <= length && (_max == -1 || length <= _max)) {
			return value;
		}
		
		// 报错
		throw new ValidationException(null != message ? message : this.message, new Object[] { field });
	}
	
	/**
	 * 功能： <br/>
	 */
	protected int parseLength(Object value, boolean byChar, String encoding) throws Exception {
		int length = 0;
		
		if (null != value) {
			if (value instanceof String) {
				if (byChar) {
					length = ((String) value).length();
				} else {
					length = ((String) value).getBytes(encoding).length;
				}
			} else if (value instanceof Object[]) {
				length = ((Object[]) value).length;
			} else if (value instanceof Collection) {
				length = ((Collection<?>) value).size();
			} else if (value instanceof Map) {
				length = ((Map<?, ?>) value).size();
			} else {
				throw new SystemException("system.error.not.support", new Object[] { value.getClass().getName() });
			}
		}
		
		return length;
	}

	/**
	 */
	public int getMax() {
		return max;
	}

	/**
	 */
	public void setMax(int max) {
		this.max = max;
	}

	/**
	 */
	public int getMin() {
		return min;
	}

	/**
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 */
	public boolean isByChar() {
		return byChar;
	}

	/**
	 */
	public void setByChar(boolean byChar) {
		this.byChar = byChar;
	}

	/**
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}
