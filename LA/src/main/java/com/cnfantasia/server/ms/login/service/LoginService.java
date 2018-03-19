package com.cnfantasia.server.ms.login.service;

import com.cnfantasia.server.ms.login.dao.ILoginDao;
import com.cnfantasia.server.ms.login.entity.SimpleLoginInfo;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;

public class LoginService implements ILoginService{
	private ILoginDao loginDao;
	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	public OmsUser login(SimpleLoginInfo simpleLoginInfo) {
		String number = simpleLoginInfo.getNumber();
		String password = simpleLoginInfo.getPassword();
		OmsUser resUser = loginDao.selectOmsUser(number, password);
		return resUser;
	}
	
	
}
