package com.cnfantasia.server.api.login.callable;

import java.math.BigInteger;

/**
 * 注册成功后，添加额外信息接口
 * 
 * @author wenfq 2015-08-10
 *
 */
public interface IAddOtherInfoAfterRegister {
	/**
	 * 添加额外信息
	 * @param userId 用户id
	 * @return true成功，false失败
	 */
	public boolean addOtherInfo(BigInteger userId);
}
