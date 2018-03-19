/**   
* Filename:    IEbuyService.java   
* @version:    1.0  
* Create at:   2014年5月16日 上午12:40:33   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyBuyCarEntityFamilyGroup;
import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryAddressEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryOrderEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyExtandBuyParam;
import com.cnfantasia.server.api.ebuy.entity.EbuyIdentifyInfo;
import com.cnfantasia.server.api.ebuy.entity.EbuyOrderBuyInfo;
import com.cnfantasia.server.api.ebuy.entity.EbuyOrderEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductRecommendEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductWithCurrProductSpec;
import com.cnfantasia.server.api.ebuy.entity.MerchantIdDeliveryType;
import com.cnfantasia.server.api.ebuy.entity.ProductIdQtyEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;
import com.cnfantasia.server.domainbase.ebuyRefundOrder.entity.EbuyRefundOrder;
import com.cnfantasia.server.domainbase.ebuyRefundOrderProduct.entity.EbuyRefundOrderProduct;

/**
 * Filename:    IEbuyService.java
 * @version:    1.0.0
 * Create at:   2014年5月16日 上午12:40:33
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月16日       shiyl             1.0             1.0 Version
 */
public interface IEbuyService {
	/**
	 * 查询商品类别列表
	 * @return
	 */
	List<EbuyProductType> getProductTypeList(Integer pointType, Long wlAppType, Integer version);
	
	/**
	 * 查询商品列表
	 * @param productTypeId 商品类别
	 * @param pageModel
	 * @return
	 */
	List<EbuyProductEntity> getProductList(String searchKey, BigInteger productTypeId, PageModel pageModel, Integer pointType, Long wlAppType);
	/**
	 * 根据Id查询商品详情
	 * @param productId
	 * @return
	 */
	EbuyProductEntity getProductById(BigInteger userId, BigInteger productId, Integer pointType, Long wlAppType);
	
	/**
	 * 查看商品参数
	 * @param productId
	 * @return
	 */
	List<EbuyProductParameters> getProductParameters(BigInteger productId);
	/**
	 * 查询商品评论
	 * @param productId
	 * @param pageModel
	 * @return
	 */
	List<CommentsEntity> getProductComments(BigInteger productId, PageModel pageModel);
//	/**
//	 * 根据商品Id查询支持的认证列表信息
//	 * @param productId
//	 * @return
//	 */
//	public List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> getProductAuths(BigInteger productId);
	
	/**
	 * 根据商品Id查询其支持的配送方式
	 * @param productId
	 * @return
	 */
	List<EbuyDeliveryMethod> getEbuyDeliveryMethodList(BigInteger productId, Integer pointType, Long wlAppType);
	
	/**
	 * 查询商品是否被收藏
	 * @param userId
	 * @param productId
	 * @return
	 */
	boolean isProductCollected(BigInteger userId, BigInteger productId);
	
//	/**
//	 * 根据商品Id查询图片列表
//	 */
//	public List<EbuyProductPic> getEbuyProductPicList(BigInteger productId);
	/**
	 * 查询推荐的产品列表
	 */
	List<EbuyProductRecommendEntity> getRecommendProducts(Integer pointType, Long wlAppType);
	/**
	 * 查询收货地址列表
	 */
	List<EbuyDeliveryAddressEntity> getEbuyDeliveryAddressList(BigInteger userId, PageModel pageModel);
	/**
	 * 提交订单，返回订单详情
	 * @param deliveryAddressId
	 * @param productId
	 * @param productQty
	 * @param productSpecId 产品规格Id 可为空
	 * @param merchantIdDeliveryTypes 配置类型：2是自提，1是其它需要计算运费的
	 *            配送方式
	 * @return
	 */
	EbuyOrderEntity submitOrder(BigInteger userId, BigInteger deliveryAddressId, BigInteger gbId, BigInteger productId
			, Long productQty, BigInteger productSpecId, EbuyExtandBuyParam ebuyExtandBuyParam
			/*,BigInteger deliveryMethodId*/, Long hbMoney, Integer pointType, Long wlAppType, String invoice_companyName, String invoice_productTypeName
			, Set<BigInteger> couponIdList, Integer jfqSubType, Long subChannelId, EbuyIdentifyInfo idInfo, List<MerchantIdDeliveryType> merchantIdDeliveryTypes);
	
	/**
	 * 提交订单 包含多个商品
	 *  @param userId
	 *            用户Id
	 * @param deliveryAddressId
	 *            配送地址Id
	 * @param productIdQtyList
 *            商品列表
	 * @param invoice_companyName
	 * @param invoice_productTypeName
	 * @param merchantIdDeliveryTypes 配置类型：2是自提，1是其它需要计算运费的
	 */
	EbuyOrderEntity submitOrderMulti(BigInteger userId, BigInteger deliveryAddressId, BigInteger gbId,
									 List<ProductIdQtyEntity> productIdQtyList, Long hbMoney, Integer pointType, Long wlAppType, String invoice_companyName,
									 String invoice_productTypeName, Set<BigInteger> couponIdList, Integer jfqSubType, Long subChannelId, EbuyIdentifyInfo idInfo, List<MerchantIdDeliveryType> merchantIdDeliveryTypes);
	/**
	 * 多个商品生成一个订单，同时包含使用粮票的信息
	 * @param userId
	 * @param deliveryAddressId
	 * @param ebuyOrderBuyInfo
	 * @param hbMoney 使用的粮票金额
	 * @param merchantIdDeliveryTypes 配置类型：2是自提，1是其它需要计算运费的
	 *            配送方式
	 * @return
	 */
	EbuyOrderEntity submitOrderMulti(BigInteger userId, BigInteger deliveryAddressId, BigInteger gbId,
									 EbuyOrderBuyInfo ebuyOrderBuyInfo/*, BigInteger deliveryMethodId*/, Long hbMoney, Integer pointType, Long wlAppType, String invoice_companyName,
									 String invoice_productTypeName, Set<BigInteger> couponIdList, Integer jfqSubType, Long subChannelId, EbuyIdentifyInfo idInfo, List<MerchantIdDeliveryType> merchantIdDeliveryTypes);
	/**
	 * 根据用户，订单Id查询订单详情
	 * @param userId
	 * @param orderId
	 * @return
	 */
	EbuyOrderEntity getEbuyOrderEntityDetail(BigInteger userId, BigInteger orderId, Integer pointType, Long wlAppType);
	/**
	 * 根据用户，查询订单列表
	 * @param userId
	 * @return
	 */
	List<EbuyOrderEntity> getOrderList(BigInteger userId, PageModel pageModel, Integer pointType, Long wlAppType);
	/**
	 * 查询订单列表
	 * @param userId 用户Id
	 * @param _YesorderStatus 需要的订单状态
	 * @param _NoorderStatus 不需要的订单状态
	 * @param pageModel 分页信息
	 * @return
	 */
	List<EbuyOrderEntity> getOrderList(BigInteger userId, List<Integer> _YesorderStatus, List<Integer> _NoorderStatus, PageModel pageModel, Integer pointType, Long wlAppType);
	/**
	 * 更改订单状态为已删除
	 * @param userId 用户Id
	 * @param orderId 订单Id
	 */
	void updateOrder2Delete(BigInteger userId, BigInteger orderId, Integer pointType, Long wlAppType);
	/**
	 * 更改订单状态为确认收货
	 * @param userId 用户Id
	 * @param orderId 订单Id
	 */
	void updateOrder2Received(BigInteger userId, BigInteger orderId, Integer pointType, Long wlAppType);
	
	/**
	 * 增加收货地址
	 * @param userId 用户Id
	 * @param targetType *收货地址类别{"1":"门牌方式","2":"普通输入"}
	 * @param userName *用户名称
	 * @param userPhone *用户手机号
	 * @param isDefault 是否设置为默认收货地址
	 * @param targetId 目标收货地址Id
	 * @param groupBuildingId 小区Id
	 * @param addressDetail 小区后的地址信息
	 * @param addressArea 区域信息
	 */
	EbuyDeliveryAddressEntity addDeliveryAddress(BigInteger userId, Integer targetType, String userName, String userPhone, Integer isDefault, BigInteger targetId,
												 BigInteger groupBuildingId, String addressDetail, String addressArea, String noWriteRoom, String gbName, BigInteger blockId);
	
	/**
	 * 编辑收货地址
	 * @param userId 用户Id
	 * @param targetType *收货地址类别{"1":"门牌方式","2":"普通输入"}
	 * @param userName *用户名称
	 * @param userPhone *用户手机号
	 * @param isDefault 是否设置为默认收货地址
	 * @param targetId 目标收货地址Id
	 * @param groupBuildingId 小区Id
	 * @param addressDetail 小区后的地址信息
	 * @param addressArea 区域信息
	 */
	EbuyDeliveryAddressEntity updateDeliveryAddress(BigInteger deliveryAddressId, BigInteger userId, Integer targetType,
													String userName, String userPhone, Integer isDefault, BigInteger targetId,
													BigInteger groupBuildingId, String addressDetail, String addressArea,
													String noWriteRoom, String gbName, BigInteger blockId);
	/**
	 * 根据Id查询详情
	 * @param deliveryAddressId
	 * @param userId
	 * @return
	 */
	EbuyDeliveryAddressEntity getDeliveryAddressDetail(BigInteger deliveryAddressId, BigInteger userId);
	/**
	 * 根据用户Id查询默认收货地址
	 * @param userId
	 * @return
	 */
	EbuyDeliveryAddressEntity qryDeliveryAddressDetailDefault(BigInteger userId);
	
	/**
	 * 删除收货地址
	 * @param deliveryAddressId
	 * @param userId
	 * @return
	 */
	void deleteDeliveryAddress(BigInteger deliveryAddressId, BigInteger userId);
	
	/**
	 * 设置默认收货地址
	 * @param deliveryAddressId
	 * @param userId
	 */
	void setDefaultDeliveryAddress(BigInteger deliveryAddressId, BigInteger userId);
	/**
	 * 没有收货地址时，将默认门牌信息转存到收货地址中
	 */
	void copyDefaultRoom2DeliveryAddress(BigInteger userId);
	
	/**
	 * 加入购物车
	 * @param userId
	 * @param productId
	 * @param productQty 为负表示减少购买数量
	 */
	EbuyBuyCarEntityFamilyGroup add2BuyCar(BigInteger userId, Integer userType, BigInteger productId, Long productQty, Integer pointType, Long wlAppType, BigInteger productSpecId);
	/**
	 * 查询购物车详细信息
	 * @param userId
	 * @return
	 */
	EbuyBuyCarEntityFamilyGroup getBuyCarDetail(BigInteger userId, Integer userType, Integer pointType, Long wlAppType);
//	/**
//	 * 查询家庭购物车列表
//	 * @param userId
//	 * @param userType
//	 * @param pointType
//	 * @return
//	 */
//	public List<EbuyBuyCarEntity> getBuyCarDetailFamilyGroup(BigInteger userId,Integer userType,Integer pointType,Long wlAppType);
	
	/**
	 * 查询购物车的部分商品信息
	 * @param userId
	 * @param productIds 商品Ids
	 * @return
	 */
	EbuyBuyCarEntityFamilyGroup getBuyCarPartDetailByProdIds(BigInteger userId, Integer userType, List<ProductIdQtyEntity> productIdQtyList, Integer pointType, Long wlAppType);
	EbuyBuyCarEntityFamilyGroup getBuyCarAllDetail(BigInteger userId, Integer userType, Integer pointType, Long wlAppType);
	/**
	 * 将商品从购物车中删除
	 * @param userId
	 * @param productId
	 */
	EbuyBuyCarEntityFamilyGroup removeProdFromBuyCarOld(BigInteger userId, Integer userType, BigInteger productId, Integer pointType, Long wlAppType);
	EbuyBuyCarEntityFamilyGroup removeProdFromBuyCar(BigInteger userId, Integer userType, BigInteger productId, Integer pointType, Long wlAppType, BigInteger productSpecId);
	
	EbuyBuyCarEntityFamilyGroup removeProdFromBuyCarBatchLogicOld(BigInteger userId, Integer userType, List<BigInteger> productIdList, Integer pointType, Long wlAppType);
	EbuyBuyCarEntityFamilyGroup removeProdFromBuyCarBatchLogic(BigInteger userId, Integer userType, List<ProductIdQtyEntity> productIdQtyList, Integer pointType, Long wlAppType);
	
	EbuyBuyCarEntityFamilyGroup removeProdFromBuyCarAllLogic(BigInteger userId, Integer userType, Integer pointType, Long wlAppType);
	
	int removeProdFromBuyCarNewUser(BigInteger userId, Integer userType, Integer pointType, Long wlAppType);
	/**
	 * 查询购物车商品总数
	 * @param userId
	 * @return
	 */
	Integer getBuyCarProductTotalCount(BigInteger userId, Integer userType, Integer pointType, Long wlAppType) ;
//	/**
//	 * 校验并锁定商品数量
//	 * @param productId
//	 * @param productQty
//	 */
//	public void checkLeftCountAndLock(BigInteger productId,Long productQty);
//	/**
//	 * 释放库存
//	 * @param productId
//	 * @param toReleaseCount
//	 */
//	public void releaseLeftCount(BigInteger productId,Long toReleaseCount);
	/**
	 * 通过订单Id，将对应的商品的库存数锁定
	 * @param orderId
	 * @return
	 */
	int lockProductLeftCountByOrderId(BigInteger orderId, BigInteger buyerId, Integer pointType, Long wlAppType);
	/**
	 * 通过订单Id，将对应的商品的库存数释放
	 * @param orderId
	 * @return
	 */
	int releaseProductLeftCountByOrderId(BigInteger orderId, BigInteger buyerId, Integer pointType, Long wlAppType);
	/**
	 * 通过订单Id更新商品销量
	 * @param orderId
	 * @param buyerId
	 * @return
	 */
	int addProductSellCountByOrderId(BigInteger orderId, BigInteger buyerId, Integer pointType, Long wlAppType);
	
//	/**
//	 * 通过配送方式Id,商品金额查询所需运费
//	 * @param deliveryMethodId
//	 * @param productAmount
//	 * @return
//	 */
//	public EbuyDeliveryMethod fetchEbuyDeliveryMethod(BigInteger deliveryMethodId, Long productAmount);
	
	/**
	 * 根据购买的商品信息获取对应的配送方式信息, 第一项为配送信息，第二项目为与供应商结算信息
	 * @param merchantId 供应商Id
	 * @param deliveryMethodId 不为空是 直接返回 对应配送信息，否则根据条件判断
	 * @param productIdQtyEntityList
	 * @param detailMap 不为空直接使用,为空时重新去数据库获取
	 * @param pointType
	 * @return
	 */
	List fetchEbuyDeliveryMethod(BigInteger merchantId, BigInteger deliveryMethodId, List<ProductIdQtyEntity> productIdQtyEntityList, Map<ProductIdQtyEntity, EbuyProductWithCurrProductSpec> detailMap, Integer pointType, Long wlAppType);
	
	/**
	 * 查询所有商品相关信息的最新更新时间
	 * @return
	 */
	String fetchAllProductLastUpdTime(String searchKey, BigInteger productTypeId, PageModel pageModel, Integer pointType, Long wlAppType);
	
	/**
	 * 提交话费订单
	 * @return
	 */
	EbuyOrderEntity confirmPhoneChargeOrder(BigInteger userId, BigInteger productId, BigInteger productSpecId, String phoneNum, Integer pointType, Long wlAppType, Integer jfqSubType, Long subChannelId);
	/**
	 * 提交现金券订单
	 * @return
	 */
	EbuyOrderEntity confirmCashCoupon(BigInteger userId, BigInteger productId, BigInteger productSpecId, Integer pointType, Long wlAppType, Integer jfqSubType, Long subChannelId);
	/**
	 * 提交普通商品订单
	 * @return
	 */
	EbuyOrderEntity confirmCommProduct(BigInteger userId, BigInteger deliveryAddressId, BigInteger productId, BigInteger productSpecId,
									   Integer pointType, Long wlAppType, Integer jfqSubType, Long subChannelId);
	
	/**
	 * 根据用户提交购买的商品信息查询对应的完整信息
	 * @param productIdQtyList
	 * @return
	 */
	List<EbuyProductWithCurrProductSpec> getEbuyProductWithCurrProductSpecList(List<ProductIdQtyEntity> productIdQtyList, Integer pointType, Long wlAppType);

	/**
	 * 客户端标记订单已支付成功
	 * @param userId
	 * @param orderId
	 * @param pointType
	 */
	void markOrderIsClientPay(BigInteger userId, BigInteger orderId, Integer pointType, Long wlAppType);

	EbuyDeliveryOrderEntity getebuyDeliveryOrderEntityById(Map<String, Object> paramMap);
	
	//根据退款单id获取退款商品
	List<EbuyRefundOrderProduct> getRefundProductList(BigInteger refundOrderId);
	EbuyRefundOrder getRefundOrderbydelId(BigInteger deliveryOrderId);

	List<EbuyProductEntity> getOrderProductList(BigInteger orderId);
	
}
