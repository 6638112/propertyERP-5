package com.cnfantasia.server.ms.ebuyProductShelf.dao;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyProductShelf.dao.IEbuyProductShelfBaseDao;

public interface IEbuyProductShelfDao extends IEbuyProductShelfBaseDao {

	/**
	 * 根据t_ebuy_product表id逻辑删除
	 * @param productId
	 * @return
	 */
	public int deleteByProductIdLogic(BigInteger productId);
}
