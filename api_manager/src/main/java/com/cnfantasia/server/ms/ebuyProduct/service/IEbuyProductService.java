package com.cnfantasia.server.ms.ebuyProduct.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Row;

import com.cnfantasia.server.api.bankCollection.entity.ResultMsg;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.ebuyHomeTypeHasProduct.entity.EbuyHomeTypeHasProduct;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProductDto;
import com.cnfantasia.server.domainbase.ebuyProduct.service.IEbuyProductBaseService;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.entity.EbuyProductIntroducePic;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductPic.entity.EbuyProductPic;
import com.cnfantasia.server.domainbase.ebuyProductShelf.entity.EbuyProductShelf;
import com.cnfantasia.server.ms.ebuyProduct.entity.EbuyProductEntity;
import com.cnfantasia.server.ms.ebuyProduct.entity.JfqStoreProductTypeInfoEntity;

public interface IEbuyProductService extends IEbuyProductBaseService {

	int getProductList_forCount(Map<String, Object> paramMap);

	List<EbuyProductEntity> getProductList_forPage(Map<String, Object> paramMap);
	
	List<EbuyProductParameters> getEbuyProductParameters(Map<String,Object> paramMap,boolean isDim);
	
	void addEbuyProductAudit(Map<String,Object> paramMap);
	
	/**
	 * 货架管理-编辑
	 * @param ebuyProduct
	 * @param ebuyProductShelf
	 * @param productTypeId 货架ID
	 * @param ebuyProductParameters
	 * @param ebuyProductIntroducePics
	 * @param ebuyProductPicList 
	 * @param ebuyHomeTypeHasProduct
	 * @return
	 */
	public boolean updateProductById(EbuyProduct ebuyProduct, EbuyProductShelf ebuyProductShelf, BigInteger productTypeId, List<EbuyProductParameters> ebuyProductParameters, 
			List<EbuyProductIntroducePic> ebuyProductIntroducePics, List<EbuyProductPic> ebuyProductPicList, EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct);
	
	/**
	 * 单品录入
	 * @param ebuyProducts
	 * @param ebuyProductPics
	 * @param ebuyProductIntroducePics
	 * @param ebuyHomeTypeHasProduct
	 * @param ebuyProductShelf
	 * @return
	 */
	public boolean addEbuyProduct(EbuyProductDto ebuyProductDto);
	
	/**
	 * 商品列表-更新
	 * 
	 * @param ebuyProduct
	 * @param ebuyProductPics
	 * @param ebuyProductIntroducePics
	 * @param ebuyHomeTypeHasProduct
	 * @param ebuyProductShelfList
	 * @return
	 */
	public boolean updateEbuyProduct(List<EbuyProductParameters> ebuyProductParameterses,EbuyProduct ebuyProduct,
									 List<EbuyProductPic> ebuyProductPics, List<EbuyProductIntroducePic> ebuyProductIntroducePics,
									 EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct, List<EbuyProductShelf> ebuyProductShelfList);
	
	/**
	 * 上架管理-上架/批量上架
	 * @param ebuyProducts
	 * @param updateEbuyProductShelfs
	 * @param insertEbuyProductShelfs
	 */
	public void upShelf(List<EbuyProduct> ebuyProducts, List<EbuyProductShelf> updateEbuyProductShelfs, List<EbuyProductShelf> insertEbuyProductShelfs);
	
	/**
	 * 货架管理-批量下架
	 * @param ebuyProducts
	 * @param ebuyProductShelfs
	 * @return
	 */
	public boolean downShelf(List<EbuyProduct> ebuyProducts, List<EbuyProductShelf> ebuyProductShelfs);
	
	public int getShlefCount(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> getShlefAppType(Map<String, Object> paramMap);
	
	/**
	 * 货架管理-更新价格
	 * 
	 * @param ebuyProducts
	 * @param ebuyProductShelfs
	 * @return
	 */
	public boolean updateShelfPrice(List<EbuyProduct> ebuyProducts, List<EbuyProductShelf> ebuyProductShelfs);
	
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
	
	/**
	 * 商品管理-上、下架
	 * 
	 * @param ebuyProducts
	 * @param ebuyProductShelfs
	 * @return
	 */
	public boolean onOffProduct(List<EbuyProduct> ebuyProducts, List<EbuyProductShelf> ebuyProductShelfs);

	/**
	 * 查询用户是否有商品权限
	 * @param userId 用户ID
	 * @param ebuyProductId 商品ID
     * @return
     */
	public boolean isProductOwner(BigInteger userId, BigInteger ebuyProductId);

	/**
	 * 体验店商品导入
	 * @author wenfq 2017-08-14
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	public JsonResponse saveBatchImport4JFQStore(HttpServletRequest request) throws IOException;
	
	public ResultMsg checkTitle(Row row);
	
	public List<JfqStoreProductTypeInfoEntity> selectExistedJfqStoreProductTypeInfo(Map<String, Object> paramMap);
}
