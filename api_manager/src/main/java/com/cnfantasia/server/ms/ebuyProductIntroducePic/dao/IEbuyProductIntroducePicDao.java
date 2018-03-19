package com.cnfantasia.server.ms.ebuyProductIntroducePic.dao;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.dao.IEbuyProductIntroducePicBaseDao;

public interface IEbuyProductIntroducePicDao extends IEbuyProductIntroducePicBaseDao {
	
	/**
	 * 根据产品id删除介绍图
	 * @param productId
	 * @return
	 */
	public int deleteByProductIdLogic(BigInteger productId);
}
