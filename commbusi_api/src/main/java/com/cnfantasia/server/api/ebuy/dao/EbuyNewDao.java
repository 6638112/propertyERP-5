package com.cnfantasia.server.api.ebuy.dao;

import java.math.BigInteger;
import java.util.HashMap;
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
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;

public class EbuyNewDao extends AbstractBaseDao implements IEbuyNewDao{
	
	@Override
	public List<EbuyHomeProd> getEbuyHomeProdList(Map<String, Object> paramMap) {
		return sqlSession.selectList("ebuyNew.select_ebuyHomeProdList", paramMap);
	}
	
	public List<EbuyProdForLst> getEbuyHomeProdMoreList(Map<String, Object> paramMap, PageModel pageModel) {
		return PageQueryUtil.selectPageList(sqlSession, paramMap, pageModel, "ebuyNew.select_ebuyHomeProdMoreList", "ebuyNew.select_ebuyHomeProdMoreCount");
	}
	
	public List<EbuyStore> getEbuyStoreList(Map<String, Object> paramMap, PageModel pageModel) {
		return PageQueryUtil.selectPageList(sqlSession, paramMap, pageModel, "ebuyNew.select_ebuyStoreList", "ebuyNew.select_ebuyStoreCount");
	}
	
	public List<EbuyProdForLstSales> getProdList(Map<String, Object> paramMap, PageModel pageModel) {
		return PageQueryUtil.selectPageList(sqlSession, paramMap, pageModel, "ebuyNew.select_ebuyProdList", "ebuyNew.select_ebuyProdCount");
	}
	
	public List<EbuyFamilyFavor> getEbuyFamilyFavorList(Map<String, Object> paramMap) {
		return sqlSession.selectList("ebuyNew.select_ebuyFamilyFavorList", paramMap);
	}
	
	public List<EbuyProdForLst> getFamilyFavorMoreList() {
		return sqlSession.selectList("ebuyNew.select_ebuyFamilyFavorProdList");
	}
	
	public Long getProductIdByShelfId(Long shelfId) {
		return (Long) sqlSession.selectOne("ebuyNew.select_productIdByShelfId", shelfId);
	}
	
	public List<EbuyProdForLst> searchHomeProdListByPage(Map<String, Object> paramMap, PageModel pageModel) {
		return PageQueryUtil.selectPageList(sqlSession, paramMap, pageModel, "ebuyNew.searchHomeProdListByPage", "ebuyNew.searchHomeProdListCount");
	}
	
	public void cleanCache() {
		sqlSession.selectOne("ebuyNew.cleanCache");
	}

	@Override
	public List<EbuyProdType> getStoreProdTypes(Map<String, Object> paramMap) {
		return sqlSession.selectList("ebuyNew.getStoreProdTypes", paramMap);
	}
	
	public EbuySalesPromotion getNewUserType(Map<String, Object> paramMap) {
		return sqlSession.selectOne("ebuyNew.getNewUserType", paramMap);
	}
	
	public BigInteger selectCityIdByName(Map<String, Object> paramMap) {
		return (BigInteger) sqlSession.selectOne("ebuyNew.selectCityIdByName", paramMap);
	}

	public List<EbuyProdForLstSales> selectAdvertiseThemeProduct(BigInteger advId) {
		return sqlSession.selectList("ebuyNew.selectAdvertiseThemeProduct", advId);
	}

	public List<EbuyFightGroups> getFightProducts(Map<String, Object> param) {
		return sqlSession.selectList("ebuyNew.getFightProducts", param);
	}

	public EbuyFightGroups getHotFightProductSelling(Map<String, Object> param) {
		return sqlSession.selectOne("ebuyNew.getHotFightProductSelling", param);
	}

	@Override
	public EbuyStore getEbuyStoreInfo(BigInteger ebuySupplyMerchantId) {
		return sqlSession.selectOne("ebuyNew.getEbuyStoreInfo", ebuySupplyMerchantId);
	}

	@Override
	public ThemeAdvEntity getThemeDredgeAdvDetail(BigInteger advId) {
		return sqlSession.selectOne("ebuyNew.getThemeDredgeAdvDetail", advId);
	}

	@Override
	public ThemeAdvEntity getThemeEbuyAdvDetail(BigInteger advId, Integer appType) {
		Map<String, Object> para = new HashMap<>();
		para.put("advId", advId);
		para.put("appType", appType);
		return sqlSession.selectOne("ebuyNew.getThemeEbuyAdvDetail", para);
	}

	@Override
	public String getEbuyThemeDescByShelfId(BigInteger shelfId, Integer type) {
		Map<String, Object> param = new HashMap<>();
		param.put("shelfId", shelfId);
		param.put("type", type);
		return sqlSession.selectOne("ebuyNew.getEbuyThemeDescByShelfId", param);
	}

	@Override
	public String selectExpiredProductName(BigInteger orderId) {
		return sqlSession.selectOne("ebuyNew.selectExpiredProductName", orderId);
	}

}
