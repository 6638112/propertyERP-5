package com.cnfantasia.server.ms.ebuyProductType.service;

import java.util.List;

import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;
import com.cnfantasia.server.domainbase.ebuyProductType.service.IEbuyProductTypeBaseService;

public interface IEbuyProductTypeService extends IEbuyProductTypeBaseService {

	public List<EbuyProductType> getEbuyProductTypes(List<String> types);
}
