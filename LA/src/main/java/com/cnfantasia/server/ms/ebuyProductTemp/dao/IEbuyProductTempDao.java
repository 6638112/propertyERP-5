package com.cnfantasia.server.ms.ebuyProductTemp.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.ebuyProductTemp.dao.IEbuyProductTempBaseDao;
import com.cnfantasia.server.domainbase.ebuyProductTemp.entity.EbuyProductTemp;
import com.cnfantasia.server.ms.ebuyProductTemp.entity.EbuyProductTempEntity;

public interface IEbuyProductTempDao extends IEbuyProductTempBaseDao {

	int getProductList_forCount(Map<String, Object> paramMap);

	List<EbuyProductTemp> getProductList_forPage(Map<String, Object> paramMap);

	EbuyProductTempEntity getProductDetail_byId(String id);

	EbuyProductTempEntity getProductTempEntityById(String ptId);

	/**
	 * 更新上下架状态
	 * 
	 * @param srcIdlist
	 *            原商城（海吉星）中的ID集
	 */
	int updateShelfStatus(List<String> srcIdlist);
}
