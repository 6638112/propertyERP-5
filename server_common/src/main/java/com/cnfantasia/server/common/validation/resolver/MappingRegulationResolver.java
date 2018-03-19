package com.cnfantasia.server.common.validation.resolver;

import java.util.Map;

import com.cnfantasia.server.common.validation.regulation.Regulation;

/**
 * 类名：MappingRegulationResolver  <br />
 *
 * 功能：基于映射的规则解析器
 */
public class MappingRegulationResolver implements RegulationResolver {
	
	// 规则映射
	private Map<String, Regulation> mapping;

	/**
	 */
	@Override
	public Regulation resolve(String name) throws Exception {
		return mapping.get(name);
	}

	/**
	 */
	public void setMapping(Map<String, Regulation> mapping) {
		this.mapping = mapping;
	}
}
