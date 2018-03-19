package com.cnfantasia.server.ms.ebuyProductParameters.dao;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyProductParameters.dao.EbuyProductParametersBaseDao;

public class EbuyProductParametersDao extends EbuyProductParametersBaseDao implements IEbuyProductParametersDao {
	
	/**
	 * 根据产品id彻底删除参数
	 * @param productId
	 * @return
	 */
	@Override
	public int deleteByEPId(BigInteger productId) {
		return sqlSession.delete("ebuyProductParameters.delete_by_product_id", productId);
	}

}
