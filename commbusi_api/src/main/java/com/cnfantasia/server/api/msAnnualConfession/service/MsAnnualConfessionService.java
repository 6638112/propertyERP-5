package com.cnfantasia.server.api.msAnnualConfession.service;

import java.math.BigInteger;

import com.cnfantasia.server.api.msAnnualConfession.dao.IMsAnnualConfessionDao;
import com.cnfantasia.server.api.msAnnualConfession.entity.MsAnnualConfessionDto;

/**
 * 表白活动
 * 
 * @author liyulin
 * @version 1.0 2016年8月4日 下午1:12:02
 */
public class MsAnnualConfessionService implements IMsAnnualConfessionService {
	
	private IMsAnnualConfessionDao msAnnualConfessionDao;
	
	public void setMsAnnualConfessionDao(
			IMsAnnualConfessionDao msAnnualConfessionDao) {
		this.msAnnualConfessionDao = msAnnualConfessionDao;
	}


	/**
	 * 根据userId查询表白活动信息
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public MsAnnualConfessionDto getAnnualConfessionByUserId(BigInteger userId) {
		return msAnnualConfessionDao.getAnnualConfessionByUserId(userId);
	}

}
