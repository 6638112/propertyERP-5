package com.cnfantasia.server.ms.ebuyProductIntroducePic.dao;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.dao.EbuyProductIntroducePicBaseDao;

public class EbuyProductIntroducePicDao extends EbuyProductIntroducePicBaseDao implements IEbuyProductIntroducePicDao {

	/**
	 * 根据产品id删除介绍图
	 * @param productId
	 * @return
	 */
	@Override
	public int deleteByProductIdLogic(BigInteger productId) {
		return sqlSession.update("ebuyProductIntroducePic.delete_by_product_id", productId);
	}

}
