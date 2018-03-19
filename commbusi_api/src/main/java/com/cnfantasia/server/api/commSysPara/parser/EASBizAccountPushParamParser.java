package com.cnfantasia.server.api.commSysPara.parser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.revenueApplyPush.bean.EASBizAccountParam;

/**
 * 报销单 可配置参数
 * @author wenfq 2016-07-01
 *
 */
public class EASBizAccountPushParamParser extends AbstractSysParamParser {
	private Log logger = LogFactory.getLog(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	protected EASBizAccountParam doParse(String sysParamValue) {
		String[] params = sysParamValue.split(";");
		String applierCompany = params[0]; // 报销人公司
		String applierDept = params[1]; // 报销人部门
		String applier = params[2]; // 报销人
		String position = params[3]; // 职位
		String payCompany = params[4]; // 费用支付公司
		String prior = params[5]; //优先级
		EASBizAccountParam easBizAccountParam = new EASBizAccountParam(applierCompany, applierDept, applier, position, payCompany, prior);
		logger.debug("easBizAccountParam:"+easBizAccountParam);
		return easBizAccountParam;
	}

	@Override
	protected String getSysParamKey() {
		return SysParamKey.EASBizAccountPushParam;
	}

}
