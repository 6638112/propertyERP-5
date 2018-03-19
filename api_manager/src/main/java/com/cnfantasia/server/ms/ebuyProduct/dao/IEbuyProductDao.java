package com.cnfantasia.server.ms.ebuyProduct.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.ebuyProduct.dao.IEbuyProductBaseDao;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.ms.ebuyProduct.entity.EbuyProductEntity;
import com.cnfantasia.server.ms.ebuyProduct.entity.EbuyProductImport;
import com.cnfantasia.server.ms.ebuyProduct.entity.JfqStoreProductTypeInfoEntity;

public interface IEbuyProductDao extends IEbuyProductBaseDao {

	int getProductList_forCount(Map<String, Object> paramMap);

	List<EbuyProductEntity> getProductList_forPage(Map<String, Object> paramMap);
	void insertProductaudit(Map<String, Object> paramMap);
	Integer selectProductAudit(Object obj);
	void updateProductAudit(Map<String, Object> paramMap);
	public int getShlefCount(Map<String, Object> paramMap);
	public List<Map<String, Object>> getShlefAppType(Map<String, Object> paramMap);
	
	/**
	 * 查询APP平台上t_ebuy_product表id与t_ebuy_product_shelf表id相同的数量
	 * 
	 * @param productId
	 * @return
	 */
	public int queryCountForEpIdEqShelfIdOnApp(BigInteger productId);
	
	/**
	 * 商品autocomplete框后台实现
	 * 
	 * @param gbName
	 * @return
	 */
	public List<Map<String, Object>> epFilter(String epName);

	public boolean isProductOwner(BigInteger userId, BigInteger ebuyProductId);

	/**
	 * 根据Excel中的数据，找到商品列表
	 * @author wenfq 2017-08-14
	 * @param epImportList
	 * @param storeId
	 * @return
	 */
	List<EbuyProduct> getProductList_forImport(List<EbuyProductImport> epImportList, BigInteger storeId);

	int upToShelfProductAfterImported(List<EbuyProductImport> epImportList);
	
	public List<JfqStoreProductTypeInfoEntity> selectExistedJfqStoreProductTypeInfo(Map<String, Object> paramMap);
}
