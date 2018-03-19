package com.cnfantasia.server.ms.ebuyProductParameters.dao;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyProductParameters.dao.IEbuyProductParametersBaseDao;

public interface IEbuyProductParametersDao extends IEbuyProductParametersBaseDao {

	/**
	 * 根据产品id彻底删除参数
	 * @param productId
	 * @return
	 */
	public int deleteByEPId(BigInteger productId);
}
