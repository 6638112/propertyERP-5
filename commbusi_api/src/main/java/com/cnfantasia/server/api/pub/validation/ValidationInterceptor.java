package com.cnfantasia.server.api.pub.validation;

import com.cnfantasia.server.business.pub.validation.AbstractValidationInterceptor;
import com.cnfantasia.server.common.validation.express.ExpressionExecutor;

/**
 *	校验拦截器
 */
public class ValidationInterceptor extends AbstractValidationInterceptor {
	
	//表达式执行器
	private ExpressionExecutor executor;
	public void setExecutor(ExpressionExecutor executor) {
		this.executor = executor;
	}

	@Override
	public ExpressionExecutor obtainExpressionExecutor() {
		return executor;
	}
}