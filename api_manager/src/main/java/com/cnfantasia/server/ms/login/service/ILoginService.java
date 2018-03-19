package com.cnfantasia.server.ms.login.service;

import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.ms.login.entity.SimpleLoginInfo;

public interface ILoginService {
	
	/**
	 * 登录
	 * @return 登录结果信息
	 */
	public OmsUser login(SimpleLoginInfo simpleLoginInfo);
	
}
