package com.cnfantasia.server.ms.ebuyProductType.dao;

import java.util.List;

import com.cnfantasia.server.domainbase.ebuyProductType.dao.IEbuyProductTypeBaseDao;
import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;

public interface IEbuyProductTypeDao extends IEbuyProductTypeBaseDao {

	public List<EbuyProductType> getEbuyProductTypes(List<String> types);
}
