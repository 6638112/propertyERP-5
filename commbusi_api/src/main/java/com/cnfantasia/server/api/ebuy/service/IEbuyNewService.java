package com.cnfantasia.server.api.ebuy.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuy.entity.EbuyFamilyFavor;
import com.cnfantasia.server.api.ebuy.entity.EbuyFightGroups;
import com.cnfantasia.server.api.ebuy.entity.EbuyHomeProd;
import com.cnfantasia.server.api.ebuy.entity.EbuyProdForLst;
import com.cnfantasia.server.api.ebuy.entity.EbuyProdForLstSales;
import com.cnfantasia.server.api.ebuy.entity.EbuyProdType;
import com.cnfantasia.server.api.ebuy.entity.EbuySalesPromotion;
import com.cnfantasia.server.api.ebuy.entity.EbuyStore;
import com.cnfantasia.server.api.ebuy.entity.ThemeAdvEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;

public interface IEbuyNewService {
	
	public void cleanCache();
	
	public EbuyHomeProd getEbuyHomeProd(int homeType, BigInteger groupBuildId, int prodCount, int sex);
	
	public List<EbuyProdForLst> getEbuyHomeProdMoreList(Map<String, Object> paramMap, PageModel pageModel);

	List<EbuyHomeProd> getEbuyHomeProdList(int homeType, BigInteger groupBuildId, int prodCount, int sex);
	
	public List<EbuyStore> getEbuyStoreList(Map<String, Object> paramMap, PageModel pageModel);

	public EbuyStore getEbuyStoreInfo(BigInteger ebuySupplyMerchantId);
	
	public List<EbuyProdForLstSales> getProdList(Map<String, Object> paramMap, PageModel pageModel);
	
	public List<EbuyFamilyFavor> getEbuyFamilyFavorList(Map<String, Object> paramMap);
	
	public Long getProductIdByShelfId(Long shelfId);
	
	public List<EbuyProdType> getStoreProdTypes(Map<String, Object> paramMap);
	
	public List<EbuyProdForLst> searchHomeProdListByPage(Map<String, Object> paramMap, PageModel pageModel);

	//确认收货
	public void confirmReceived(BigInteger userId, BigInteger orderId, BigInteger deliveryOrderId);
	
	public EbuySalesPromotion getNewUserType(Map<String, Object> paramMap);
	
	public BigInteger selectCityIdByName(Map<String, Object> paramMap);

	public List<EbuyProdForLstSales> selectAdvertiseThemeProduct(BigInteger advId);

	/**
	 * 查询拼购商品列表
	 * @param fightStatus 0 未开始 1 进行中
	 * @return
	 */
	public List<EbuyFightGroups> getFightProducts(String fightStatus, BigInteger gbId, PageModel pageModel);

	/**
	 * 查最热销拼购
	 * @param gbId 小区ID
	 * @return
     */
	public EbuyFightGroups getHotFightProductSelling(BigInteger gbId);

	ThemeAdvEntity getThemeAdvDetail(BigInteger advId, Integer appType);

	/**
	 * 查询商品活动说明
	 * @param shelfId shelfId 或者 dredgeProductId
	 * @param type 1 超市，2 维修
	 * @return
	 */
	String getEbuyThemeDescByShelfId(BigInteger shelfId, Integer type);

	/**
	 * 获得店铺的爆款商品
	 * @param storeId 店铺id
	 * @return
	 */
	public List<EbuyProduct> getHotSaleProduct4Store(BigInteger storeId);

	/**
	 * 支付前的对订单中的商品进行检查
	 * @author wenfq
	 * @param orderId
	 * @return 检查通过，返回"通过校验", 否则返回"XX商品已过期，请重新下单购买"
	 */
	public String checkOrderPrdtBeforePay(BigInteger orderId);
}
