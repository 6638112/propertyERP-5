package com.cnfantasia.server.common.validation.resolver;

import com.cnfantasia.server.common.validation.regulation.Regulation;

/**
 * 类名：IOCRegulationResolver  <br />
 *
 * 功能：基于spring ioc容器的解析
 *
 */
public class IOCRegulationResolver extends AbstractIocResolver implements RegulationResolver {

	/**
	 * 功能: <br/>
	 * 
	 */
	//@Override
	public Regulation resolve(String name) throws Exception {
		return resolveBean(name, Regulation.class);
	}
}
