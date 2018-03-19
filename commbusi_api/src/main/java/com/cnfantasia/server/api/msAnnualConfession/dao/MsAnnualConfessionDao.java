package com.cnfantasia.server.api.msAnnualConfession.dao;

import java.math.BigInteger;

import com.cnfantasia.server.api.msAnnualConfession.entity.MsAnnualConfessionDto;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;

/**
 * 表白活动
 * 
 * @author liyulin
 * @version 1.0 2016年8月4日 下午1:15:16
 */
public class MsAnnualConfessionDao extends AbstractBaseDao implements IMsAnnualConfessionDao {
	
	/**
	 * 根据userId查询表白活动信息
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public MsAnnualConfessionDto getAnnualConfessionByUserId(BigInteger userId) {
		return sqlSession.selectOne("msAnnualConfession.get_AnnualConfession_with_userId", userId);
	}

}
