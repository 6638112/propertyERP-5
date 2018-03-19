package com.cnfantasia.server.ms.ebuyProductTemp.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.ebuyProductTemp.entity.EbuyProductTemp;
import com.cnfantasia.server.domainbase.ebuyProductTemp.service.IEbuyProductTempBaseService;

public interface IEbuyProductTempService extends IEbuyProductTempBaseService {

	int getProductList_forCount(Map<String, Object> paramMap);

	List<EbuyProductTemp> getProductList_forPage(Map<String, Object> paramMap);

	int sync(String ptId);
}