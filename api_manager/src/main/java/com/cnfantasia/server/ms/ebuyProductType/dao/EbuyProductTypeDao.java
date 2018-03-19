package com.cnfantasia.server.ms.ebuyProductType.dao;

import java.util.List;

import com.cnfantasia.server.domainbase.ebuyProductType.dao.EbuyProductTypeBaseDao;
import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;

public class EbuyProductTypeDao extends EbuyProductTypeBaseDao implements IEbuyProductTypeDao {

	@Override
	public List<EbuyProductType> getEbuyProductTypes(List<String> types) {
		return sqlSession.selectList("ebuyProductType.select_ebuy_product_types", types);
	}


}
