package com.cnfantasia.server.common.validation.resolver;

import com.cnfantasia.server.common.validation.regulation.Regulation;

/**
 * 类名：RegulationResolver  <br />
 *
 * 功能：规章解析器
 */
public interface RegulationResolver {

	/**
	 * 
	 * 功能：解析出规章对象 <br/>
	 */
	Regulation resolve(String name) throws Exception;
}
