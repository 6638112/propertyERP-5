package com.cnfantasia.server.ms.ebuyProductShelf.dao;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyProductShelf.dao.EbuyProductShelfBaseDao;

public class EbuyProductShelfDao extends EbuyProductShelfBaseDao implements IEbuyProductShelfDao {

	@Override
	public int deleteByProductIdLogic(BigInteger productId) {
		return sqlSession.update("ebuyProductShelf.delete_by_product_id", productId);
	}

}
