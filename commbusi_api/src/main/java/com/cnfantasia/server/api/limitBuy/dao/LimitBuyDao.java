package com.cnfantasia.server.api.limitBuy.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryMethodEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyStore;
import com.cnfantasia.server.api.limitBuy.entity.LimitBuyInfo;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;

/**
 * @ClassName: LimitBuyDao
 * @Date: 2016-12-28 13:25
 * @Auther: kangduo
 * @Description: (限时抢购dao层)
 */
public class LimitBuyDao extends AbstractBaseDao implements ILimitBuyDao {
    public List<LimitBuyInfo> getLimitBuyListByGbId(Map<String, Object> param) {
        return sqlSession.selectList("limitBuy.getLimitBuyListByGbId", param);
    }

    public LimitBuyInfo getLimitBuyInfo(Map<String, Object> param) {
        return sqlSession.selectOne("limitBuy.getLimitBuyInfo", param);
    }

    @Override
    public List<LimitBuyInfo> getLimitBuyListByMerchant(BigInteger merchantId, Integer appType, Integer limitBuyStatus,  PageModel pageModel) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("merchantId", merchantId);
        param.put("appType", appType);
        param.put("status", limitBuyStatus);
        if (pageModel != null) {
            param.put("_begin", pageModel.getBegin());
            param.put("_length", pageModel.getLength());
        }
        return sqlSession.selectList("limitBuy.getLimitBuyListByMerchant", param);
    }

    @Override
    public List<EbuyStore> getLimitBuyListStore(Map<String, Object> param) {
        return sqlSession.selectList("limitBuy.getLimitBuyListWithMerchant", param);
    }
    
    @Override
    public int qryBuyCountWithActivtyIdForUser(Map<String, Object> param) {
    	return sqlSession.selectOne("limitBuy.selectBuyCountWithActivtyIdForUser", param);
    }
    
    @Override
    public int deleteLimitActivityByOrderId(BigInteger orderId) {
    	return sqlSession.update("limitBuy.deleteLimitActivityByOrderId", orderId);
    }

    @Override
    public List<EbuyProduct> getHotSaleProduct(BigInteger storeId) {
        return sqlSession.selectList("limitBuy.getHotSaleProductByStoreId", storeId);
    }

    @Override
	public List<EbuyProduct> getEbuyProductByCondition(Map<String,Object> paramMap){
    	return sqlSession.selectList("limitBuy.getEbuyProductByCondition",paramMap);
	}
    
    @Override
    public List<EbuyDeliveryMethodEntity> selectEbuyDeliveryMethodListByMerId(BigInteger merchantId) {
        Map<String,Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("merchantId", merchantId);
        return sqlSession.selectList("limitBuy.select_EbuyDeliveryMethodList_ByMerId", tmpMap);
    }

	@Override
	public Integer synchronizeProductPrice() {
		int updCount = 0;
		updCount += sqlSession.update("limitBuy.synchronizeProductPrice1");
		updCount += sqlSession.update("limitBuy.synchronizeProductPrice2");
        return updCount;
	}
}
