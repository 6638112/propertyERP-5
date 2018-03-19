package com.cnfantasia.server.common.validation;

import java.util.Map;

/**
 * 类名：Regulateable  <br />
 *
 * 功能：可校验接口定义
 *
 */
public interface Regulateable {

	/**
	 * 
	 * 功能：获得校验规则 <br/>
	 */
	Map<String, Object> getRegulations() throws Exception;
}
