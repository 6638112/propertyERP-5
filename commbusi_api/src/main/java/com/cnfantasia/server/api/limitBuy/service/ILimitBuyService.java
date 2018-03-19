package com.cnfantasia.server.api.limitBuy.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuy.entity.EbuyStore;
import com.cnfantasia.server.api.limitBuy.entity.LimitBuyInfo;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;

/**
 * @ClassName: ILimitBuyService
 * @Date: 2016-12-28 13:25
 * @Auther: kangduo
 * @Description: ()
 */
public interface ILimitBuyService {
    public List<LimitBuyInfo> getLimitBuyListByGbId(BigInteger gbId, Integer appType, Integer positionType, PageModel pageModel);

    public LimitBuyInfo getLimitBuyInfo(BigInteger limitBuyId, Integer appType);

    /**
     * 确实限时购付款
     * @param limitBuyId
     * @param buyNum
     * @param hbAmount
     * @param couponId
     * @param address
     * @param linkName
     * @param linkPhone
     * @param isSelfGet 是否到店自提，1是，0不是
     * @return orderid订单id，shouldPay需求支付金额
     */
    public Map<String, Object> confirmPay(BigInteger limitBuyId, Integer buyNum, Long hbAmount, BigInteger couponId, String address,
                                          String linkName, String linkPhone, int isSelfGet, BigInteger gbId);

    public List<LimitBuyInfo> getLimitBuyListByMerchant(BigInteger merchantId, Integer appType, Integer status, PageModel pageModel);

    public List<EbuyStore> getLimitBuyListStore(BigInteger gbId, Integer appType, Integer positionType, Integer status, PageModel pageModel);

	public int qryBuyCountWithActivtyIdForUser(Map<String, Object> param);

	int deleteLimitActivityByOrderId(BigInteger orderId);
	
	public List<EbuyProduct> getEbuyProductByCondition(Map<String,Object> paramMap);
	
    List<EbuyProduct> getHotSaleProduct(BigInteger storeId);

    EbuyDeliveryMethod getEbuyDeliveryMethod(BigInteger merchantId, Long productAmount);

    /**
     * 活动开启后，商品售价变为限时促销价；<p>
     * 限时购活动结束或限时购商品卖完（即库存为0）时，还原商品的价格
     * @author wenfq
     * @return 
     */
	Integer synchronizeProductPrice();

	void saveLimitBuyRecord(Integer buyNum, LimitBuyInfo limitBuyInfo, BigInteger userId, BigInteger orderId);
}
