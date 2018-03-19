package com.cnfantasia.server.common.validation.express;

import java.util.Map;
import java.util.Map.Entry;

import com.cnfantasia.server.common.exception.SystemException;
import com.cnfantasia.server.common.validation.regulation.Regulation;
import com.cnfantasia.server.common.validation.resolver.RegulationResolver;

/**
 * 类名：AbstractExpressionExecutor  <br />
 *
 * 功能：抽象表达式实现
 */
public abstract class AbstractExpressionExecutor implements ExpressionExecutor {
	
	// 规章对象解析器
	protected RegulationResolver regulationResolver;

	/**
	 */
	//@Override
	public Object execute(String field, Object value, Object expression) throws Exception {
		if (null != expression) {
			// 解析出表达式
			Map<String, Map<String, String>> expMap = parseExpression(expression);
			
			if (null != expMap && ! expMap.isEmpty()) {
				for (Entry<String, Map<String, String>> entry : expMap.entrySet()) {
					// 取得规章对象
					Regulation regulation = resolveRegulation(entry.getKey());
					
					if (null == regulation) {
						throw new SystemException("regulation.resolve.result.is.null", new Object[] { entry.getKey() });
					}
					
					// 交给规章对象进行处理
					value = regulation.regulate(field, value, entry.getValue());
				}
			}
		}
		
		// 返回处理后的值
		return value;
	}
	
	/**
	 * 
	 */
	protected abstract Map<String, Map<String, String>> parseExpression(Object expression) throws Exception;
	
	/**
	 */
	protected Regulation resolveRegulation(String name) throws Exception {
		// 这里的异常不做处理，直接报系统异常
		return regulationResolver.resolve(name);
	}

	/**
	 */
	public void setRegulationResolver(RegulationResolver regulationResolver) {
		this.regulationResolver = regulationResolver;
	}
}
