package com.cnfantasia.server.common.validation.regulation;

import java.util.Map;

/**
 * 类名：Regulation  <br />
 *
 * 功能：规章定义
 */
public interface Regulation {

	/**
	 * 
	 * 功能：按规定进行调节处理 <br/>
	 */
	Object regulate(String field, Object value, Map<String, String> config) throws Exception;
}
