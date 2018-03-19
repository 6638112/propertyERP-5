package com.cnfantasia.server.business.pub.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

public class MapResultHandler implements ResultHandler {
	@SuppressWarnings("rawtypes")
	private final Map mappedResults = new HashMap();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void handleResult(ResultContext context) {
		Map map = (Map) context.getResultObject();
		mappedResults.put(map.get("key"), map.get("value")); // xml配置里面的property的值，对应的列  
	}

	@SuppressWarnings("rawtypes")
	public Map getMappedResults() {
		return mappedResults;
	}
}
