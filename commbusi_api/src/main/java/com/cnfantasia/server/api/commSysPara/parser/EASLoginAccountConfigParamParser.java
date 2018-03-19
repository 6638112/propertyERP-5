package com.cnfantasia.server.api.commSysPara.parser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.revenueApplyPush.bean.EASLoginAccountConfigParam;

public class EASLoginAccountConfigParamParser extends AbstractSysParamParser  {
	private Log logger = LogFactory.getLog(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	protected EASLoginAccountConfigParam doParse(String sysParamValue) {
		String[] params = sysParamValue.split(";");
		String userName = params[0]; ;//用户名
		String password = params[1];//用户密码
		String dataCenter = params[2];//数据中
		EASLoginAccountConfigParam easLoginAccountConfigParam = new EASLoginAccountConfigParam(userName, password, dataCenter);
		logger.debug("easBizAccountParam:"+easLoginAccountConfigParam);
		return easLoginAccountConfigParam;
	}

	@Override
	protected String getSysParamKey() {
		return SysParamKey.EAS_login_account;
	}

}
