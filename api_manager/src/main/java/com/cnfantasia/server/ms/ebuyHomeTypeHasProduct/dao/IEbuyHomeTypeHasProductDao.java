package com.cnfantasia.server.ms.ebuyHomeTypeHasProduct.dao;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyHomeTypeHasProduct.dao.IEbuyHomeTypeHasProductBaseDao;

public interface IEbuyHomeTypeHasProductDao extends IEbuyHomeTypeHasProductBaseDao {

	/**
	 * 根据产品id删除（逻辑删除）
	 * @param productId
	 * @return
	 */
	public int deleteByProductIdLogic(BigInteger productId);
}
