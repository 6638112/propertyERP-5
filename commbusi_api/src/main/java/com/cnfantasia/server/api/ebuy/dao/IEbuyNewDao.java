package com.cnfantasia.server.api.ebuy.dao;

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

public interface IEbuyNewDao {
	
	public void cleanCache();

	public List<EbuyHomeProd> getEbuyHomeProdList(Map<String, Object> paramMap);
	
	public List<EbuyProdForLst> getEbuyHomeProdMoreList(Map<String, Object> paramMap, PageModel pageModel);
	
	public List<EbuyStore> getEbuyStoreList(Map<String, Object> paramMap, PageModel pageModel);
	
	public List<EbuyProdForLstSales> getProdList(Map<String, Object> paramMap, PageModel pageModel);
	
	public List<EbuyFamilyFavor> getEbuyFamilyFavorList(Map<String, Object> paramMap);

	public List<EbuyProdForLst> getFamilyFavorMoreList();	
	
	public Long getProductIdByShelfId(Long shelfId);
	
	public List<EbuyProdType> getStoreProdTypes(Map<String, Object> paramMap);
	
	public List<EbuyProdForLst> searchHomeProdListByPage(Map<String, Object> paramMap, PageModel pageModel);
	
	public EbuySalesPromotion getNewUserType(Map<String, Object> paramMap);
	
	public BigInteger selectCityIdByName(Map<String, Object> paramMap);

	public List<EbuyProdForLstSales> selectAdvertiseThemeProduct(BigInteger advId);

	public List<EbuyFightGroups> getFightProducts(Map<String, Object> param);

	public EbuyFightGroups getHotFightProductSelling(Map<String, Object> param);

	EbuyStore getEbuyStoreInfo(BigInteger ebuySupplyMerchantId);

	ThemeAdvEntity getThemeDredgeAdvDetail(BigInteger advId);

	ThemeAdvEntity getThemeEbuyAdvDetail(BigInteger advId, Integer appType);

	String getEbuyThemeDescByShelfId(BigInteger shelfId, Integer type);

	public String selectExpiredProductName(BigInteger orderId);
}
