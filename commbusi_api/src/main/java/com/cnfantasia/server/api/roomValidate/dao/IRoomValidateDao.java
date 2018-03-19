package com.cnfantasia.server.api.roomValidate.dao;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.roomValidate.dao.IRoomValidateBaseDao;

public interface IRoomValidateDao extends IRoomValidateBaseDao{

	/**
	 * 查询用户已经提交的记录数
	 * 
	 * @param userId
	 * @return
	 */
	public int selectRecordNum(BigInteger userId);
}
