package com.cnfantasia.server.ms.ebuyHomeTypeHasProduct.dao;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyHomeTypeHasProduct.dao.EbuyHomeTypeHasProductBaseDao;

public class EbuyHomeTypeHasProductDao extends EbuyHomeTypeHasProductBaseDao implements IEbuyHomeTypeHasProductDao {

	/**
	 * 根据产品id删除（逻辑删除）
	 * @param productId
	 * @return
	 */
	@Override
	public int deleteByProductIdLogic(BigInteger productId) {
		return sqlSession.update("ebuyHomeTypeHasProduct.delete_by_product_id", productId);
	}

}
