package com.cnfantasia.server.ms.ebuyProductTemp.dao;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.page.TotalCountGetter;
import com.cnfantasia.server.domainbase.ebuyProductTemp.dao.EbuyProductTempBaseDao;
import com.cnfantasia.server.domainbase.ebuyProductTemp.entity.EbuyProductTemp;
import com.cnfantasia.server.ms.ebuyProductTemp.entity.EbuyProductTempEntity;

public class EbuyProductTempDao extends EbuyProductTempBaseDao implements IEbuyProductTempDao {

	@Override
	public int getProductList_forCount(Map<String, Object> paramMap) {
		paramMap.put(QUERY_TYPE_IF_DIM, true);
		return TotalCountGetter.getTotalCount(sqlSession, "ebuyProductTemp.select_ebuyProductTemp", paramMap);
	}

	@Override
	public List<EbuyProductTemp> getProductList_forPage(Map<String, Object> paramMap) {
		if (paramMap != null) {
			paramMap.put(QUERY_TYPE_IF_DIM, true);
		}
		return sqlSession.selectList("ebuyProductTemp.select_ebuyProductTemp", paramMap);
	}

	@Override
	public EbuyProductTempEntity getProductDetail_byId(String id) {
		return sqlSession.selectOne("ebuyProductTemp.select_detail_byPrdtId", id);
	}

	@Override
	public EbuyProductTempEntity getProductTempEntityById(String ptId) {
		return sqlSession.selectOne("ebuyProductTemp.select_detail_byPrdtId", ptId);
	}

	@Override
	public int updateShelfStatus(List<String> srcIdlist) {
		return sqlSession.update("ebuyProductTemp.updateShelfStatus", srcIdlist);
	}
}
