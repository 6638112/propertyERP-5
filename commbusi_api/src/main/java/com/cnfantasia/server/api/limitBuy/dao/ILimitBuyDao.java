package com.cnfantasia.server.api.limitBuy.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryMethodEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyStore;
import com.cnfantasia.server.api.limitBuy.entity.LimitBuyInfo;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;

/**
 * @ClassName: ILimitBuyDao
 * @Date: 2016-12-28 13:25
 * @Auther: kangduo
 * @Description: ()
 */
public interface ILimitBuyDao {
    public List<LimitBuyInfo> getLimitBuyListByGbId(Map<String, Object> param);

    public LimitBuyInfo getLimitBuyInfo(Map<String, Object> param);

    public List<LimitBuyInfo> getLimitBuyListByMerchant(BigInteger merchantId, Integer appType, Integer limitBuyStatus, PageModel pageModel);

    public List<EbuyStore> getLimitBuyListStore(Map<String, Object> param);

    /**
     * 查询用户在限时购中购买的数量
     * @param param
     * @return 数量合计
     */
	int qryBuyCountWithActivtyIdForUser(Map<String, Object> param);

	int deleteLimitActivityByOrderId(BigInteger orderId);

	
	public List<EbuyProduct> getEbuyProductByCondition(Map<String,Object> paramMap);
	
    List<EbuyProduct> getHotSaleProduct(BigInteger storeId);

    List<EbuyDeliveryMethodEntity> selectEbuyDeliveryMethodListByMerId(BigInteger merchantId);

	public Integer synchronizeProductPrice();
}
