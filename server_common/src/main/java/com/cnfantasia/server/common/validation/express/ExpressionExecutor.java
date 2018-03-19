package com.cnfantasia.server.common.validation.express;

/**
 * 类名：RegulationExecutor  <br />
 *
 * 功能：表达式执行器
 */
public interface ExpressionExecutor {

	/**
	 * 
	 * 功能：规章执行器 <br/>
	 */
	Object execute(String field, Object value, Object expression) throws Exception;
}
