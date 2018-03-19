package com.cnfantasia.server.ms.login.service;

import java.io.UnsupportedEncodingException;

import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.ms.login.dao.ILoginDao;
import com.cnfantasia.server.ms.login.entity.SimpleLoginInfo;

public class LoginService implements ILoginService{
	private ILoginDao loginDao;
	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	public OmsUser login(SimpleLoginInfo simpleLoginInfo) {
		String number = simpleLoginInfo.getNumber();
		try {
			number = new String(number.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String password = simpleLoginInfo.getPassword();
		String md5PassWord = Md5Util.MD5(password.toUpperCase());
		OmsUser resUser = loginDao.selectOmsUser(number, md5PassWord);
		return resUser;
	}
}
