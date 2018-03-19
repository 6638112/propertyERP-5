package com.cnfantasia.server.ms.ebuyProduct.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.page.TotalCountGetter;
import com.cnfantasia.server.domainbase.ebuyProduct.dao.EbuyProductBaseDao;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.ms.ebuyProduct.entity.EbuyProductEntity;
import com.cnfantasia.server.ms.ebuyProduct.entity.EbuyProductImport;
import com.cnfantasia.server.ms.ebuyProduct.entity.JfqStoreProductTypeInfoEntity;

public class EbuyProductDao extends EbuyProductBaseDao implements IEbuyProductDao {

	@Override
	public int getProductList_forCount(Map<String, Object> paramMap) {
		paramMap.put(QUERY_TYPE_IF_DIM, true);
		if(paramMap.get("state")!=null && paramMap.get("state").toString().equals("shelf")){// 货架管理
			return TotalCountGetter.getTotalCount(sqlSession, "ebuyProduct.select_ebuyProduct_shelf", paramMap);
		} else if(paramMap.get("state")!=null && paramMap.get("state").toString().equals("list")){// 商品列表
			return TotalCountGetter.getTotalCount(sqlSession, "ebuyProduct.select_ebuyProduct_list", paramMap);
		} else {// 上架管理
			return TotalCountGetter.getTotalCount(sqlSession, "ebuyProduct.select_ebuyProduct_up", paramMap);
		}
	}

	@Override
	public List<EbuyProductEntity> getProductList_forPage(Map<String, Object> paramMap) {
		if (paramMap != null) {
			paramMap.put(QUERY_TYPE_IF_DIM, true);
		}
		if(paramMap.get("state")!=null && paramMap.get("state").toString().equals("shelf")){// 货架管理
			return sqlSession.selectList("ebuyProduct.select_ebuyProduct_shelf", paramMap);
		} else if(paramMap.get("state")!=null && paramMap.get("state").toString().equals("list")){// 商品列表
			return sqlSession.selectList("ebuyProduct.select_ebuyProduct_list", paramMap);
		} else {// 上架管理
			return sqlSession.selectList("ebuyProduct.select_ebuyProduct_up", paramMap);
		}
	}

	@Override
	public void insertProductaudit(Map<String, Object> paramMap) {
		sqlSession.selectList("ebuyProduct.insertebuyproductaudit", paramMap);
	}

	@Override
	public Integer selectProductAudit(Object obj) {
		return sqlSession.selectOne("ebuyProduct.select_product_audit_msg", obj);
	}

	@Override
	public void updateProductAudit(Map<String, Object> paramMap) {
		sqlSession.update("ebuyProduct.updateProductAudit", paramMap);
	}
	
	@Override
	public int getShlefCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("ebuyProduct.select_shelf_count", paramMap);
	}
	
	@Override
	public List<Map<String, Object>> getShlefAppType(Map<String, Object> paramMap) {
		return sqlSession.selectList("ebuyProduct.select_shelf_app_type", paramMap);
	}

	/**
	 * 查询APP平台上t_ebuy_product表id与t_ebuy_product_shelf表id相同的数量
	 * 
	 * @param productId
	 * @return
	 */
	@Override
	public int queryCountForEpIdEqShelfIdOnApp(BigInteger productId){
		return sqlSession.selectOne("ebuyProduct.select_count_for_epid_eq_shelfid_on_app", productId);
	}
	
	/**
	 * 商品autocomplete框后台实现
	 * 
	 * @param gbName
	 * @return
	 */
	@Override
	public List<Map<String, Object>> epFilter(String epName){
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("epName", epName);
		return sqlSession.selectList("ebuyProduct.ebuy_product_filter", tmpMap);
	}

	@Override
	public boolean isProductOwner(BigInteger userId, BigInteger ebuyProductId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("ebuyProductId", ebuyProductId);
		int count = sqlSession.selectOne("ebuyProduct.isProductOwner",param);
		return count > 0;
	}

	@Override
	public List<EbuyProduct> getProductList_forImport(List<EbuyProductImport> epImportList, BigInteger storeId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("storeId", storeId);
		param.put("epiList", epImportList);
		return sqlSession.selectList("ebuyProduct.getProductList_forImport", param);
	}

	@Override
	public int upToShelfProductAfterImported(List<EbuyProductImport> epImportList) {
		return sqlSession.update("ebuyProduct.upToShelfProductAfterImported", epImportList);
	}
	
	@Override
	public List<JfqStoreProductTypeInfoEntity> selectExistedJfqStoreProductTypeInfo(Map<String, Object> paramMap){
		return sqlSession.selectList("ebuyProduct.selectExistedJfqStoreProductTypeInfo", paramMap);
	}
}
