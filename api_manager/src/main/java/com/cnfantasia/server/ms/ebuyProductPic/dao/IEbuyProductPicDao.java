package com.cnfantasia.server.ms.ebuyProductPic.dao;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyProductPic.dao.IEbuyProductPicBaseDao;

public interface IEbuyProductPicDao extends IEbuyProductPicBaseDao {

	/**
	 * 根据t_ebuy_product表id（逻辑）删除
	 * @param productId
	 * @return
	 */
	public int deleteByProductIdLogic(BigInteger productId);
}
