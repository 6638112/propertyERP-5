/**   
* Filename:    IEbuyDao.java   
* @version:    1.0  
* Create at:   2014年5月16日 上午1:13:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.dao;

import java.math.BigInteger;
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
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.entity.EbuyBuyCarHasTEbuyProduct;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;
import com.cnfantasia.server.domainbase.ebuyRefundOrder.entity.EbuyRefundOrder;
import com.cnfantasia.server.domainbase.ebuyRefundOrderProduct.entity.EbuyRefundOrderProduct;

/**
 * Filename:    IEbuyDao.java
 * @version:    1.0.0
 * Create at:   2014年5月16日 上午1:13:44
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月16日       shiyl             1.0             1.0 Version
 */
public interface IEbuyDao {
	
	/**
	 * 查询商品类别列表
	 * @return
	 */
	public List<EbuyProductType> selectProductTypeList(Integer pointType,Long wlAppType, Integer version);
	
	/**
	 * 分页查询商品信息
	 * @param productTypeId 商品类别
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductEntity> selectProductList(String searchKey,BigInteger productTypeId,PageModel pageModel,Integer pointType,Long wlAppType);
	/**
	 * 根据商品Id查询详情
	 * @param productId
	 * @return
	 */
	public EbuyProductEntity selectProductById(BigInteger productId,Integer pointType,Long wlAppType);
	
//	/**
//	 * 根据商品Id查询支持的认证列表信息
//	 */
//	public List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> selectProductAuths(BigInteger productId);
	
	/**
	 * 根据商品Id查询其支持的配送方式(商品支持的)
	 * @param productId
	 * @return
	 */
	public List<EbuyDeliveryMethod> selectEbuyDeliveryMethodList(BigInteger productId);
	/**
	 * 根据商品Id查询其支持的配送方式(商品对应的供应商支持的)
	 * @param productId
	 * @return
	 */
	public List<EbuyDeliveryMethod> selectEbuyDeliveryMethodListFromMerchant(BigInteger productId,Integer pointType,Long wlAppType);
	
	/**
	 * 根据供应商Id查询支持的配送方式
	 * @param merchantId
	 * @return
	 */
	public List<EbuyDeliveryMethodEntity> selectEbuyDeliveryMethodListByMerId(BigInteger merchantId);
	
	/**
	 * 查询推荐的产品列表
	 */
	public List<EbuyProductRecommendEntity> selectRecommendProducts(Integer pointType,Long wlAppType);
	/**
	 * 查询收货地址列表
	 */
	public List<EbuyDeliveryAddressEntity> selectEbuyDeliveryAddressList(BigInteger userId,PageModel pageModel);
	/**
	 * 根据Id查询详情
	 * @param deliveryAddressId
	 * @param userId
	 * @return
	 */
	public EbuyDeliveryAddressEntity selectEbuyDeliveryAddressDetail(BigInteger deliveryAddressId, BigInteger userId);
	/**
	 * 根据Id删除
	 * @param deliveryAddressId
	 * @param userId
	 * @return
	 */
	public int deleteEbuyDeliveryAddressByIdUserIdLogic(BigInteger deliveryAddressId, BigInteger userId);
	/**
	 * 设置默认收货地址
	 * @param deliveryAddressId
	 * @param userId
	 */
	public int setDefaultDeliveryAddress(BigInteger deliveryAddressId,BigInteger userId);
	/**
	 * 根据用户Id查询默认收货地址
	 * @param userId
	 * @return
	 */
	public EbuyDeliveryAddressEntity selectEbuyReceiveAddressDefault(BigInteger userId);
	/**
	 * 根据用户，订单Id查询订单详情
	 * @param userId
	 * @param orderId
	 * @return
	 */
	public EbuyOrderEntity getEbuyOrderEntityDetail(BigInteger userId,BigInteger orderId,Integer pointType,Long wlAppType);
	/**
	 * 根据用户，查询订单列表
	 * @param userId
	 * @return
	 */
	public List<EbuyOrderEntity> selectOrderList(BigInteger userId,PageModel pageModel,Integer pointType,Long wlAppType);
	/**
	 * 查询订单列表
	 * @param userId 用户Id
	 * @param _YesorderStatus 需要的订单状态
	 * @param _NoorderStatus 不需要的订单状态
	 * @param pageModel 分页信息
	 * @return
	 */
	public List<EbuyOrderEntity> selectOrderList(BigInteger userId, List<Integer> _YesorderStatus,List<Integer> _NoorderStatus, PageModel pageModel,Integer pointType,Long wlAppType);
	
//	/**
//	 * 往购物车增加商品
//	 * @param userId 用户id
//	 * @param productId 商品Id
//	 * @param productQty 需要增加的商品数量
//	 */
//	public int appendProduct2BuyCar(BigInteger userId,BigInteger productId,Long productQty);
	/**
	 * 查询购物车详细信息
	 * @param userId
	 * @return
	 */
	public List<EbuyBuyCarEntity> selectBuyCarDetail(BigInteger userId,Integer userType,Integer pointType,Long wlAppType);
//	/**
//	 * 查询购物车详细信息,家庭分组
//	 * @param userId
//	 * @return
//	 */
//	public List<EbuyBuyCarEntity> selectBuyCarDetailFamilyGroup(BigInteger userId,Integer userType,Integer pointType,Long wlAppType);
	
	/**
	 * 查询购物车商品总数
	 * @param userId
	 * @return
	 */
	public List<Integer> selectBuyCarProductTotalCount(BigInteger userId,Integer userType,Integer pointType,Long wlAppType);
	
	/**
	 * 查询购物车的部分商品信息
	 * @param userId
	 * @param productIds 商品Ids
	 * @return
	 */
	public List<EbuyBuyCarEntity> selectBuyCarPartDetailByProdIds(BigInteger userId,Integer userType,List<ProductIdQtyEntity> productIdQtyList,Integer pointType,Long wlAppType);
//	public List<EbuyBuyCarEntity> selectBuyCarAllDetail(BigInteger userId,Integer userType,Integer pointType,Long wlAppType);
	
	/**
	 * 将商品从购物车中删除
	 * @param userId
	 * @param productId
	 */
	public int removeProdFromBuyCarLogicOld(BigInteger userId,Integer userType,BigInteger productId,Integer pointType,Long wlAppType);
	public int removeProdFromBuyCarLogic(BigInteger userId,Integer userType,BigInteger productId,Integer pointType,Long wlAppType,BigInteger productSpecId);
	public int removeProdFromBuyCarBatchLogicOld(BigInteger userId,Integer userType, List<BigInteger> productIdList,Integer pointType,Long wlAppType);
	public int removeProdFromBuyCarBatchLogic(BigInteger userId,Integer userType, List<ProductIdQtyEntity> productIdQtyList,Integer pointType,Long wlAppType);
	public int removeProdFromBuyCarAllLogic(BigInteger userId,Integer userType,Integer pointType,Long wlAppType);
	
	public int removeProdFromBuyCarNewUser(BigInteger userId,Integer userType,Integer pointType,Long wlAppType);
//	/**
//	 * 释放库存
//	 * @param productId
//	 * @param toReleaseCount
//	 */
//	public int releaseProductLeftCount(BigInteger productId,Long toReleaseCount,Integer pointType,Long wlAppType);
	/**
	 * 通过订单Id，将对应的商品的库存数锁定
	 * @param orderId
	 * @return
	 */
	public int lockProductLeftCountByOrderId(BigInteger orderId,BigInteger buyerId,Integer pointType,Long wlAppType);
	/**
	 * 通过订单Id，将对应的商品的库存数释放
	 * @param orderId
	 * @return
	 */
	public int releaseProductLeftCountByOrderId(BigInteger orderId,BigInteger buyerId,Integer pointType,Long wlAppType);
	
	/**
	 * 通过订单Id更新商品销量
	 * @param orderId
	 * @param buyerId
	 * @return
	 */
	public int addProductSellCountByOrderId(BigInteger orderId,BigInteger buyerId,Integer pointType,Long wlAppType);
	
	/**
	 * 根据商品Id批量查询商品信息
	 */
	public List<EbuyProductEntity> selectEbuyProductListByProdIds(List<BigInteger> productIds,Integer pointType,Long wlAppType);
	
	/**
	 * 根据商品规格Id批量查询商品信息
	 */
	public List<EbuyProductSpecEntity> selectEbuyProductSpecEntityListBySpecIds(List<BigInteger> specIds,Integer pointType,Long wlAppType);
	
	/**
	 * 根据商品Id批量查询对应供应商信息
	 */
	public List<EbuyProductEntity> selectMerchantByProductIds(List<ProductIdQtyEntity> productIdQtyList,Integer pointType,Long wlAppType);
	
	/**
	 * 根据订单Id,删除送货单及送货单商品信息
	 * @param orderId
	 * @param buyerId
	 * @return
	 */
	public int deleteEbuyDeliveryOrderAndProductLogicByOrderId(BigInteger orderId,BigInteger buyerId);
	
	/**
	 * 查询所有商品相关信息的最新更新时间
	 * @return
	 */
	public String selectAllProductLastUpdTime(BigInteger productTypeId,PageModel pageModel,Integer pointType,Long wlAppType);
	
	/**
	 * 查询用户及家庭成员的购物车对应购买的商品的列表信息
	 * @param userId
	 * @return
	 */
	public List<EbuyBuyCarHasTEbuyProduct> selectEbuyBuyCarHasTEbuyProductListByProductIdFamily(BigInteger userId,Integer userType, BigInteger productId,Integer pointType,Long wlAppType,BigInteger productSpecId);
	
	/**
	 * 查询用户家庭已经购买的商品数量
	 * @param userId
	 * @param userType
	 * @param productId
	 * @param pointType
	 * @return
	 */
	public Long selectHaveBuyCountByProductIdFamily(BigInteger userId,Integer userType, BigInteger productId,Integer pointType,Long wlAppType);
	
	/**
	 * 将购物车中商品数量为0的商品逻辑删除掉
	 * @param userId
	 * @param userType
	 * @param pointType
	 */
	public void deleteZeroCountBuyDataEbuyCarHasProductLogic(BigInteger userId,Integer userType,Integer pointType,Long wlAppType);
	
	/**
	 * 查询商品剩余库存
	 * @param productId
	 * @param productSpecId
	 * @return
	 */
	public BigInteger selectProductLeftCount(BigInteger productId,BigInteger productSpecId,Integer pointType,Long wlAppType);
	

	/**
	 * 查询购物车当前对应商品的购买信息
	 * @param buyCarId
	 * @param productId
	 * @param productSpecId
	 * @return
	 */
	public List<EbuyBuyCarHasTEbuyProduct> selectEbuyBuyCarHasTEbuyProductByBuyCarId(BigInteger buyCarId,
			BigInteger productId, BigInteger productSpecId,Integer pointType,Long wlAppType);

	/**
	 * 客户端标记订单为已支付状态
	 * @param userId
	 * @param orderId
	 * @param pointType
	 * @return
	 */
	public Integer upadteOrderIsClientPay(BigInteger userId, BigInteger orderId, Integer pointType,Long wlAppType);

	List<EbuyProductParameters> selectEbuyProductParametersByCondition(Map<String, Object> paramMap);
	
	public EbuyDeliveryOrderEntity getebuyDeliveryOrderEntityById(Map<String, Object> paramMap);
	
	public List<EbuyRefundOrderProduct> getRefundProductList(BigInteger refundOrderId);
	
	public EbuyRefundOrder getRefundOrderbydelId(BigInteger deliveryOrderId);

	/**
	 * 查找商品是否在限时购当时，若在限时购中，返回限时购的库存，否则返回0
	 * @param productId
	 * @param productSpecId
	 * @param pointType
	 * @param wlAppType
	 * @return
	 */
	public BigInteger selectLimitBuyLeftCount(BigInteger productId, BigInteger productSpecId, Integer pointType,
			Long wlAppType);

	public List<LimitBuyId_PrdtQty> select_limitBuyId_prdtQty(Map<String, Object> paramMap);

	Integer updEbuyHandlerAdressAreaNull(BigInteger handlerAddressId);

	List<EbuyProductEntity> getOrderProductList(BigInteger orderId);
}
