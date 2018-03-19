/**   
* Filename:    EbuyDao.java   
* @version:    1.0  
* Create at:   2014年5月16日 上午1:15:01   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuy.entity.EbuyBuyCarEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryAddressEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryMethodEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryOrderEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyOrderEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductRecommendEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductSpecEntity;
import com.cnfantasia.server.api.ebuy.entity.ProductIdQtyEntity;
import com.cnfantasia.server.api.limitBuy.entity.LimitBuyId_PrdtQty;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.entity.EbuyBuyCarHasTEbuyProduct;
import com.cnfantasia.server.domainbase.ebuyDeliveryAddress.entity.EbuyDeliveryAddress;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;
import com.cnfantasia.server.domainbase.ebuyRefundOrder.entity.EbuyRefundOrder;
import com.cnfantasia.server.domainbase.ebuyRefundOrderProduct.entity.EbuyRefundOrderProduct;

/**
 * Filename:    EbuyDao.java
 * @version:    1.0.0
 * Create at:   2014年5月16日 上午1:15:01
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月16日       shiyl             1.0             1.0 Version
 */
public class EbuyDao extends AbstractBaseDao implements IEbuyDao{
	
	@Override
	public List<EbuyProductType> selectProductTypeList(Integer pointType,Long wlAppType, Integer version) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		tmpMap.put("version", version);
		return sqlSession.selectList("ebuy.select_ProductType_List_All",tmpMap);
	}
	
	@Override
	public List<EbuyProductEntity> selectProductList(String searchKey,BigInteger productTypeId,PageModel pageModel,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("productTypeId", productTypeId);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		tmpMap.put("searchKey", searchKey);
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, "ebuy.select_ebuyProduct_withPage", "ebuy.select_ebuyProduct_count");
	}
	
	@Override
	public EbuyProductEntity selectProductById(BigInteger productId,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("productId", productId);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.selectOne("ebuy.select_ebuyProduct_bySeqId", tmpMap);
	}
	
//	@Override
//	public List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> selectProductAuths(BigInteger productId) {
//		return sqlSession.selectList("ebuy.select_ebuyAuthList_byProductId", productId);
//	}
	
	@Override
	public List<EbuyDeliveryMethod> selectEbuyDeliveryMethodList(BigInteger productId) {
		return sqlSession.selectList("ebuy.select_ebuyDeliveryMethodList_byProductId", productId);
	}
	@Override
	public List<EbuyDeliveryMethod> selectEbuyDeliveryMethodListFromMerchant(BigInteger productId,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("productId", productId);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.selectList("ebuy.select_ebuyDeliveryMethodListFromMerchant_byProductId", tmpMap);
	}
	
	@Override
	public List<EbuyDeliveryMethodEntity> selectEbuyDeliveryMethodListByMerId(BigInteger merchantId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("merchantId", merchantId);
		return sqlSession.selectList("ebuy.select_EbuyDeliveryMethodList_ByMerId", tmpMap);
	}
	
	@Override
	public List<EbuyProductRecommendEntity> selectRecommendProducts(Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.selectList("ebuy.select_RecommendProducts",tmpMap);
	}

	@Override
	public List<EbuyDeliveryAddressEntity> selectEbuyDeliveryAddressList(BigInteger userId, PageModel pageModel) {
		EbuyDeliveryAddress ebuyDeliveryAddress = new EbuyDeliveryAddress();
		ebuyDeliveryAddress.settUserFId(userId);
		String pageSqlKey= "ebuy.select_selectEbuyReceiveAddressList_withPage";
		String countSqlKey="ebuy.select_selectEbuyReceiveAddressList_count";
		List<EbuyDeliveryAddressEntity> resList = PageQueryUtil.selectPageList(sqlSession, ebuyDeliveryAddress, pageModel, pageSqlKey, countSqlKey);
		return resList;
	}
	@Override
	public EbuyDeliveryAddressEntity selectEbuyDeliveryAddressDetail(BigInteger deliveryAddressId, BigInteger userId) {
		EbuyDeliveryAddress ebuyDeliveryAddress = new EbuyDeliveryAddress();
		ebuyDeliveryAddress.settUserFId(userId);
		ebuyDeliveryAddress.setId(deliveryAddressId);
		return sqlSession.selectOne("ebuy.select_selectEbuyReceiveAddressDetail_ByIdUserId",MapConverter.toMap(ebuyDeliveryAddress));
	}
	@Override
	public EbuyDeliveryAddressEntity selectEbuyReceiveAddressDefault(BigInteger userId) {
		EbuyDeliveryAddress ebuyDeliveryAddress = new EbuyDeliveryAddress();
		ebuyDeliveryAddress.settUserFId(userId);
		ebuyDeliveryAddress.setIsdefault(EbuyDict.EbuyDeliveryAddress_ISDEFAULT.TRUE);
		return sqlSession.selectOne("ebuy.select_selectEbuyReceiveAddressDefault_ByUserId",MapConverter.toMap(ebuyDeliveryAddress));
	}

	@Override
	public int deleteEbuyDeliveryAddressByIdUserIdLogic(BigInteger deliveryAddressId, BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("deliveryAddressId", deliveryAddressId);
		tmpMap.put("userId", userId);
		return sqlSession.update("ebuy.delete_EbuyDeliveryAddress_ByIdUserId_Logic",tmpMap);
	}

	@Override
	public int setDefaultDeliveryAddress(BigInteger deliveryAddressId, BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("deliveryAddressId", deliveryAddressId);
		tmpMap.put("userId", userId);
		return sqlSession.update("ebuy.update_EbuyReceiveAddress_setDefaultId", tmpMap);
		
	}

	@Override
	public EbuyOrderEntity getEbuyOrderEntityDetail(BigInteger userId, BigInteger orderId,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("orderId", orderId);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
//		EbuyOrder ebuyOrder = new EbuyOrder();
//		ebuyOrder.setBuyerId(userId);
//		ebuyOrder.setId(orderId);
		return sqlSession.selectOne("ebuy.select_EbuyOrderEntity_Detail_ByIdUserId", tmpMap);
	}

	@Override
	public List<EbuyOrderEntity> selectOrderList(BigInteger userId, PageModel pageModel,Integer pointType,Long wlAppType) {
		List<EbuyOrderEntity> resList = selectOrderList(userId, null, null, pageModel,pointType,wlAppType);
		return resList;
	}

	@Override
	public List<EbuyOrderEntity> selectOrderList(BigInteger userId, List<Integer> _YesorderStatus,List<Integer> _NoorderStatus, PageModel pageModel,Integer pointType,Long wlAppType) {
//		EbuyOrder ebuyOrder = new EbuyOrder();
//		ebuyOrder.setBuyerId(userId);
//		Map<String,Object> qryMap = MapConverter.toMap(ebuyOrder);
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("_YesorderStatus", _YesorderStatus);
		tmpMap.put("_NoorderStatus", _NoorderStatus);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		String pageSqlKey= "ebuy.select_selectEbuyOrderList_withPage";
		String countSqlKey="ebuy.select_selectEbuyOrderList_count";
		List<EbuyOrderEntity> resList = PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
		return resList;
	}

	@Override
	public List<EbuyBuyCarEntity> selectBuyCarDetail(BigInteger userId,Integer userType,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.selectList("ebuy.select_BuyCar_Detail", tmpMap);
	}
	
	@Override
	public List<Integer> selectBuyCarProductTotalCount(BigInteger userId,Integer userType,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.selectList("ebuy.select_BuyCarProduct_TotalCount", tmpMap);
	}
	
	@Override
	public List<EbuyBuyCarEntity> selectBuyCarPartDetailByProdIds(BigInteger userId,Integer userType, List<ProductIdQtyEntity> productIdQtyList,Integer pointType,Long wlAppType) {
		if(productIdQtyList==null||productIdQtyList.size()<=0){return null;}
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("productIdQtyList", productIdQtyList);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.selectList("ebuy.select_BuyCarPart_Detail_ByProdIds", tmpMap);
	}
//	@Override
//	public List<EbuyBuyCarEntity> selectBuyCarAllDetail(BigInteger userId,Integer userType,Integer pointType,Long wlAppType) {
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("userId", userId);
//		tmpMap.put("userType", userType);
//		tmpMap.put("pointType", pointType);
//		tmpMap.put("wlAppType", wlAppType);
//		return sqlSession.selectList("ebuy.select_BuyCar_All_Detail", tmpMap);
//	}
	
	@Override
	public int removeProdFromBuyCarLogicOld(BigInteger userId,Integer userType, BigInteger productId,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("productId", productId);
//		tmpMap.put("pointType", pointType);//暂不使用
//		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.update("ebuy.remove_Prod_From_BuyCar_Logic_Old", tmpMap);
	}
	@Override
	public int removeProdFromBuyCarLogic(BigInteger userId,Integer userType, BigInteger productId,Integer pointType,Long wlAppType,BigInteger productSpecId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("productId", productId);
//		tmpMap.put("pointType", pointType);//暂不使用
//		tmpMap.put("wlAppType", wlAppType);
		tmpMap.put("productSpecId", productSpecId);
		return sqlSession.update("ebuy.remove_Prod_From_BuyCar_Logic", tmpMap);
	}
	
	@Override
	public int removeProdFromBuyCarBatchLogicOld(BigInteger userId,Integer userType,List<BigInteger> productIdList,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("productIdList", productIdList);
//	tmpMap.put("pointType", pointType);//暂不使用
//		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.update("ebuy.remove_Prod_From_BuyCar_Batch_Logic_Old", tmpMap);
	}
	@Override
	public int removeProdFromBuyCarBatchLogic(BigInteger userId,Integer userType, List<ProductIdQtyEntity> productIdQtyList,Integer pointType,Long wlAppType) {
		if(productIdQtyList==null||productIdQtyList.size()<=0){return 0;}
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("productIdQtyList", productIdQtyList);
//	tmpMap.put("pointType", pointType);//暂不使用
//		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.update("ebuy.remove_Prod_From_BuyCar_Batch_Logic", tmpMap);
	}
	@Override
	public int removeProdFromBuyCarAllLogic(BigInteger userId,Integer userType,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
//	tmpMap.put("pointType", pointType);//暂不使用
//		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.update("ebuy.remove_Prod_From_BuyCar_AllLogic", tmpMap);
	}
	
	@Override
	public int removeProdFromBuyCarNewUser(BigInteger userId,Integer userType,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
//	tmpMap.put("pointType", pointType);//暂不使用
//		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.update("ebuy.remove_Prod_From_BuyCar_NewUser", tmpMap);
	}
	
//	@Override
//	public int releaseProductLeftCount(BigInteger productId, Long toReleaseCount,Integer pointType,Long wlAppType) {
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("productId", productId);
//		tmpMap.put("toReleaseCount", toReleaseCount);
//		tmpMap.put("pointType", pointType);
//		tmpMap.put("wlAppType", wlAppType);
//		return sqlSession.update("ebuy.release_ProductLeft_Count", tmpMap);
//	}

	@Override
	public int lockProductLeftCountByOrderId(BigInteger orderId,BigInteger buyerId,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("orderId", orderId);
		tmpMap.put("buyerId", buyerId);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.update("ebuy.lock_Product_LeftCount_ByOrderId", tmpMap);
	}

	@Override
	public int releaseProductLeftCountByOrderId(BigInteger orderId,BigInteger buyerId,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("orderId", orderId);
		tmpMap.put("buyerId", buyerId);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.update("ebuy.release_Product_LeftCount_ByOrderId", tmpMap);
	}

	@Override
	public int addProductSellCountByOrderId(BigInteger orderId, BigInteger buyerId,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("orderId", orderId);
		tmpMap.put("buyerId", buyerId);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.update("ebuy.add_Product_SellCount_ByOrderId", tmpMap);
	}

	@Override
	public List<EbuyProductEntity> selectEbuyProductListByProdIds(List<BigInteger> productIds,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("productIds", productIds);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.selectList("ebuy.select_EbuyProductList_ByProdIds", tmpMap);
	}
	
	@Override
	public List<EbuyProductSpecEntity> selectEbuyProductSpecEntityListBySpecIds(List<BigInteger> specIds,
			Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("specIds", specIds);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.selectList("ebuy.select_EbuyProductSpec_EntityList_BySpecIds", tmpMap);
	}
	
	@Override
	public List<EbuyProductEntity> selectMerchantByProductIds(List<ProductIdQtyEntity> productIdQtyList,Integer pointType,Long wlAppType) {
		if(productIdQtyList==null||productIdQtyList.size()<=0){
			return null;
		}
		List<BigInteger> productIds = new ArrayList<BigInteger>();
		for(ProductIdQtyEntity productIdQtyEntity:productIdQtyList){
			productIds.add(productIdQtyEntity.getProductId());
		}
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("productIds", productIds);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.selectList("ebuy.select_Merchant_ByProductIds", tmpMap);
	}

	@Override
	public int deleteEbuyDeliveryOrderAndProductLogicByOrderId(BigInteger orderId,BigInteger buyerId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("orderId", orderId);
		tmpMap.put("buyerId", buyerId);
		return sqlSession.update("ebuy.delete_EbuyDeliveryOrder_AndProduct_Logic_ByOrderId", tmpMap);
	}

	@Override
	public String selectAllProductLastUpdTime(BigInteger productTypeId,PageModel pageModel,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("productTypeId", productTypeId);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
//		tmpMap.put("_begin", pageModel.begin);
//		tmpMap.put("_length", pageModel.length);
		return sqlSession.selectOne("ebuy.select_All_Product_LastUpdTime",tmpMap);
	}

	@Override
	public List<EbuyBuyCarHasTEbuyProduct> selectEbuyBuyCarHasTEbuyProductListByProductIdFamily(BigInteger userId, Integer userType,
			BigInteger productId, Integer pointType,Long wlAppType,BigInteger productSpecId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("productId", productId);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		tmpMap.put("productSpecId", productSpecId);
		return sqlSession.selectList("ebuy.select_EbuyBuyCarHasTEbuyProduct_List_ByProductId_Family", tmpMap);
	}

	@Override
	public Long selectHaveBuyCountByProductIdFamily(BigInteger userId, Integer userType, BigInteger productId, Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("productId", productId);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.selectOne("ebuy.select_HaveBuyCount_ByProductId_Family", tmpMap);
	}

	@Override
	public void deleteZeroCountBuyDataEbuyCarHasProductLogic(BigInteger userId, Integer userType,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		sqlSession.update("ebuy.delete_ZeroCount_BuyData_EbuyCarHasProduct_Logic", tmpMap);
	}
	
	
	@Override
	public BigInteger selectProductLeftCount(BigInteger productId, BigInteger productSpecId,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("productId", productId);
		tmpMap.put("productSpecId", productSpecId);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.selectOne("ebuy.select_Product_LeftCount", tmpMap);
	}

	@Override
	public List<EbuyBuyCarHasTEbuyProduct> selectEbuyBuyCarHasTEbuyProductByBuyCarId(BigInteger buyCarId,
			BigInteger productId, BigInteger productSpecId,Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("buyCarId", buyCarId);
		tmpMap.put("productId", productId);
		tmpMap.put("productSpecId", productSpecId);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.selectList("ebuy.select_EbuyBuyCarHasTEbuyProduct_By_BuyCarId", tmpMap);
	}

	@Override
	public Integer upadteOrderIsClientPay(BigInteger userId, BigInteger orderId, Integer pointType,Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("orderId", orderId);
		tmpMap.put("userId", userId);
//		tmpMap.put("pointType", pointType);
//		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.update("ebuy.upadte_Order_IsClientPay", tmpMap);
	}
	
	@Override
	public List<EbuyProductParameters> selectEbuyProductParametersByCondition(Map<String,Object> paramMap){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		return sqlSession.selectList("ebuy.select_ebuyProductParameters",paramMap);
	}

	@Override
	public EbuyDeliveryOrderEntity getebuyDeliveryOrderEntityById(Map<String, Object> paramMap) {
		return sqlSession.selectOne("ebuy.select_EbuyDeliveryOrder_by_id", paramMap);
	}

	@Override
	public List<EbuyRefundOrderProduct> getRefundProductList(BigInteger refundOrderId) {
		return sqlSession.selectList("ebuy.selectRefundProductbyRefundId", refundOrderId);
	}

	@Override
	public EbuyRefundOrder getRefundOrderbydelId(BigInteger deliveryOrderId) {
		return sqlSession.selectOne("ebuy.selectRefundOrderbyRefundId", deliveryOrderId);
	}

	@Override
	public BigInteger selectLimitBuyLeftCount(BigInteger productId, BigInteger productSpecId, Integer pointType,
			Long wlAppType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("productId", productId);
		tmpMap.put("productSpecId", productSpecId);
		tmpMap.put("pointType", pointType);
		tmpMap.put("wlAppType", wlAppType);
		return sqlSession.selectOne("ebuy.select_limitBuy_leftCount", tmpMap);
	}

	@Override
	public List<LimitBuyId_PrdtQty> select_limitBuyId_prdtQty(Map<String, Object> paramMap) {
		return sqlSession.selectList("ebuy.select_limitBuyId_prdtQty", paramMap);
	}

	@Override
	public Integer updEbuyHandlerAdressAreaNull(BigInteger handlerAddressId) {
		return sqlSession.update("ebuy.updEbuyHandlerAdressAreaNull", handlerAddressId);
	}

	@Override
	public List<EbuyProductEntity> getOrderProductList(BigInteger orderId) {
		return sqlSession.selectList("ebuy.getOrderProductList", orderId);
	}

}
