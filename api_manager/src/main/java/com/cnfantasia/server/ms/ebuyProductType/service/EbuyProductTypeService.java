package com.cnfantasia.server.ms.ebuyProductType.service;

import java.util.List;

import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;
import com.cnfantasia.server.domainbase.ebuyProductType.service.EbuyProductTypeBaseService;
import com.cnfantasia.server.ms.ebuyProductType.dao.IEbuyProductTypeDao;

public class EbuyProductTypeService extends EbuyProductTypeBaseService implements IEbuyProductTypeService {

	private IEbuyProductTypeDao ebuyProductTypeDao;
	
	public void setEbuyProductTypeDao(IEbuyProductTypeDao ebuyProductTypeDao) {
		this.ebuyProductTypeDao = ebuyProductTypeDao;
	}

	@Override
	public List<EbuyProductType> getEbuyProductTypes(List<String> types) {
		return ebuyProductTypeDao.getEbuyProductTypes(types);
	}

}
