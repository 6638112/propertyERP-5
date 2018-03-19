package com.cnfantasia.server.ms.cookieInfo.service;

import java.math.BigInteger;

import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.cookieInfo.entity.CookieInfo;
import com.cnfantasia.server.domainbase.cookieInfo.service.CookieInfoBaseService;

public class CookieInfoService extends CookieInfoBaseService implements ICookieInfoService {
	private IUuidManager uuidManager;

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	@Override
	public int insertCookieInfo(CookieInfo cookieInfo) {
		BigInteger id = uuidManager.getNextUuidBigInteger(SEQConstants.t_cookie_info);
		cookieInfo.setId(id);
		return super.insertCookieInfo(cookieInfo);
	}
}
