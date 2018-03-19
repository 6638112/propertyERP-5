package com.cnfantasia.server.ms.ebuyProductPic.dao;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyProductPic.dao.EbuyProductPicBaseDao;

public class EbuyProductPicDao extends EbuyProductPicBaseDao implements IEbuyProductPicDao {

	@Override
	public int deleteByProductIdLogic(BigInteger productId) {
		return sqlSession.update("ebuyProductPic.delete_by_product_id", productId);
	}

}
