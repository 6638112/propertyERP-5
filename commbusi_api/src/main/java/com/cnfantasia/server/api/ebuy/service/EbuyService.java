/**
 * Filename:    EbuyService.java   
 * @version:    1.0  
 * Create at:   2014年5月16日 上午12:40:10   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年5月16日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.ebuy.service;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.comments.service.ICommentsService;
import com.cnfantasia.server.api.commonBusiness.constant.CommonModuleDict;
import com.cnfantasia.server.api.commonBusiness.service.ICommonEbuyService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.coupon.constant.CouponUseTypeConstant;
import com.cnfantasia.server.api.ebuy.dao.IEbuyDao;
import com.cnfantasia.server.api.ebuy.dao.IEbuyNewDao;
import com.cnfantasia.server.api.ebuy.entity.EbuyBuyCarEntityFamilyGroup;
import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryAddressEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryMethodEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryOrderEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyExtandBuyParam;
import com.cnfantasia.server.api.ebuy.entity.EbuyFlowRecharge;
import com.cnfantasia.server.api.ebuy.entity.EbuyIdentifyInfo;
import com.cnfantasia.server.api.ebuy.entity.EbuyOrderBuyInfo;
import com.cnfantasia.server.api.ebuy.entity.EbuyOrderEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductRecommendEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductShelf;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductSpecEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductWithCurrProductSpec;
import com.cnfantasia.server.api.ebuy.entity.EbuySupplyMerchantEntity;
import com.cnfantasia.server.api.ebuy.entity.MerchantIdDeliveryType;
import com.cnfantasia.server.api.ebuy.entity.ProductIdQtyEntity;
import com.cnfantasia.server.api.ebuy.entity.SimpleDelivAddress;
import com.cnfantasia.server.api.ebuy.util.DeliveryNoGenerator;
import com.cnfantasia.server.api.ebuy.util.EbuyProductBuyInfoUtil;
import com.cnfantasia.server.api.ebuy.util.FlowRechargePool;
import com.cnfantasia.server.api.ebuy.util.OrderNoGenerator;
import com.cnfantasia.server.api.ebuyorder.service.EbuyMerchantService;
import com.cnfantasia.server.api.ebuyorder.util.RechargePackageUtils;
import com.cnfantasia.server.api.limitBuy.entity.LimitBuyId_PrdtQty;
import com.cnfantasia.server.api.limitBuy.entity.LimitBuyInfo;
import com.cnfantasia.server.api.limitBuy.service.ILimitBuyService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.payment.constant.EbuyConstant;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.point.constant.PointConstant;
import com.cnfantasia.server.api.point.service.IPointService;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.redpoint.service.IRedpointService;
import com.cnfantasia.server.api.room.entity.RoomEntity;
import com.cnfantasia.server.api.userCoupon.service.IUserCouponService;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.collections.dao.ICollectionsBaseDao;
import com.cnfantasia.server.domainbase.collections.entity.Collections;
import com.cnfantasia.server.domainbase.comments.dao.ICommentsBaseDao;
import com.cnfantasia.server.domainbase.comments.entity.Comments;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.coupon.service.ICouponBaseService;
import com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.dao.IEbuyBuyCarHasTEbuyProductBaseDao;
import com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.entity.EbuyBuyCarHasTEbuyProduct;
import com.cnfantasia.server.domainbase.ebuyDeliveryAddress.dao.IEbuyDeliveryAddressBaseDao;
import com.cnfantasia.server.domainbase.ebuyDeliveryAddress.entity.EbuyDeliveryAddress;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.dao.IEbuyDeliveryMethodBaseDao;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.dao.IEbuyDeliveryOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.dao.IEbuyDeliveryOrderProductBaseDao;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.entity.EbuyDeliveryOrderProduct;
import com.cnfantasia.server.domainbase.ebuyHandleAddress.dao.IEbuyHandleAddressBaseDao;
import com.cnfantasia.server.domainbase.ebuyHandleAddress.entity.EbuyHandleAddress;
import com.cnfantasia.server.domainbase.ebuyOrder.dao.IEbuyOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrderDeliveryType.dao.EbuyOrderDeliveryTypeBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrderDeliveryType.entity.EbuyOrderDeliveryType;
import com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.entity.EbuyOrderHasCoupon;
import com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.service.IEbuyOrderHasCouponBaseService;
import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.dao.IEbuyOrderHasTEbuyProductBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.entity.EbuyOrderHasTEbuyProduct;
import com.cnfantasia.server.domainbase.ebuyOrderProductExtParam.dao.IEbuyOrderProductExtParamBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrderProductExtParam.entity.EbuyOrderProductExtParam;
import com.cnfantasia.server.domainbase.ebuyOrderProductHasCode.dao.IEbuyOrderProductHasCodeBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrderProductHasCode.entity.EbuyOrderProductHasCode;
import com.cnfantasia.server.domainbase.ebuyProduct.dao.IEbuyProductBaseDao;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProductConversionCode.entity.EbuyProductConversionCode;
import com.cnfantasia.server.domainbase.ebuyProductParameters.dao.IEbuyProductParametersBaseDao;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductSpec.entity.EbuyProductSpec;
import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;
import com.cnfantasia.server.domainbase.ebuyRefundOrder.entity.EbuyRefundOrder;
import com.cnfantasia.server.domainbase.ebuyRefundOrderProduct.entity.EbuyRefundOrderProduct;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.IEbuySupplyMerchantBaseService;
import com.cnfantasia.server.domainbase.supplyMerchantDeliveryFeeSettlement.entity.SupplyMerchantDeliveryFeeSettlement;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;
import com.cnfantasia.server.domainbase.userCoupon.service.IUserCouponBaseService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Filename: EbuyService.java
 * 
 * @version: 1.0.0 Create at: 2014年5月16日 上午12:40:10 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年5月16日 shiyl 1.0 1.0 Version
 */
@Service
public class EbuyService implements IEbuyService {
	private Log logger = LogFactory.getLog(getClass());
	
	private IEbuyFilmTicketService ebuyFilmTicketService;
	
	private IEbuyFlowRechargeService ebuyFlowRechargeService;
	
	private EbuyIdentifyInfoService ebuyIdentifyInfoService;
	
	private IEbuyNewDao ebuyNewDao;

	private ICouponBaseService couponBaseService;

	private IUserCouponBaseService userCouponBaseService;
	@Resource
	private EbuyOrderDeliveryTypeBaseDao ebuyOrderDeliveryTypeBaseDao;
	@Resource
    IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService;
	@Resource 
	ILimitBuyService limitBuyService;

	public void setUserCouponBaseService(IUserCouponBaseService userCouponBaseService) {
		this.userCouponBaseService = userCouponBaseService;
	}

	public void setCouponBaseService(ICouponBaseService couponBaseService) {
		this.couponBaseService = couponBaseService;
	}

	private EbuyMerchantService ebuyMerchantService;
	public void setEbuyMerchantService(EbuyMerchantService ebuyMerchantService) {
		this.ebuyMerchantService = ebuyMerchantService;
	}

	private IEbuyDao ebuyDao;
	public void setEbuyDao(IEbuyDao ebuyDao) {
		this.ebuyDao = ebuyDao;
	}

	// 评论
	private ICommentsService commentsService;
	public void setCommentsService(ICommentsService commentsService) {
		this.commentsService = commentsService;
	}

	private ICommentsBaseDao commentsBaseDao;
	public void setCommentsBaseDao(ICommentsBaseDao commentsBaseDao) {
		this.commentsBaseDao = commentsBaseDao;
	}

	// 产品参数
	@SuppressWarnings("unused")
	private IEbuyProductParametersBaseDao ebuyProductParametersBaseDao;
	public void setEbuyProductParametersBaseDao(IEbuyProductParametersBaseDao ebuyProductParametersBaseDao) {
		this.ebuyProductParametersBaseDao = ebuyProductParametersBaseDao;
	}

	// 收藏
	private ICollectionsBaseDao collectionsBaseDao;
	public void setCollectionsBaseDao(ICollectionsBaseDao collectionsBaseDao) {
		this.collectionsBaseDao = collectionsBaseDao;
	}

	// // 产品图片
//	private IEbuyProductPicBaseDao ebuyProductPicBaseDao;
//	public void setEbuyProductPicBaseDao(IEbuyProductPicBaseDao ebuyProductPicBaseDao) {
//		this.ebuyProductPicBaseDao = ebuyProductPicBaseDao;
//	}

	// 收货地址，手动输入
	private IEbuyHandleAddressBaseDao ebuyHandleAddressBaseDao;
	public void setEbuyHandleAddressBaseDao(IEbuyHandleAddressBaseDao ebuyHandleAddressBaseDao) {
		this.ebuyHandleAddressBaseDao = ebuyHandleAddressBaseDao;
	}

	// 配送地址
	private IEbuyDeliveryAddressBaseDao ebuyDeliveryAddressBaseDao;
	public void setEbuyDeliveryAddressBaseDao(IEbuyDeliveryAddressBaseDao ebuyDeliveryAddressBaseDao) {
		this.ebuyDeliveryAddressBaseDao = ebuyDeliveryAddressBaseDao;
	}

	// // 历史记录
//	private ICommHistoryBaseDao commHistoryBaseDao;
//	public void setCommHistoryBaseDao(ICommHistoryBaseDao commHistoryBaseDao) {
//		this.commHistoryBaseDao = commHistoryBaseDao;
//	}

	// 时间
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	//CommonRoom
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private ICommonEbuyService commonEbuyService;
	public void setCommonEbuyService(ICommonEbuyService commonEbuyService) {
		this.commonEbuyService = commonEbuyService;
	}

	// 用户
	private IUserBaseDao userBaseDao;
	public void setUserBaseDao(IUserBaseDao userBaseDao) {
		this.userBaseDao = userBaseDao;
	}

	// 配送方式
	private IEbuyDeliveryMethodBaseDao ebuyDeliveryMethodBaseDao;
	public void setEbuyDeliveryMethodBaseDao(IEbuyDeliveryMethodBaseDao ebuyDeliveryMethodBaseDao) {
		this.ebuyDeliveryMethodBaseDao = ebuyDeliveryMethodBaseDao;
	}

	// 订单管理
	private IEbuyOrderBaseDao ebuyOrderBaseDao;
	public void setEbuyOrderBaseDao(IEbuyOrderBaseDao ebuyOrderBaseDao) {
		this.ebuyOrderBaseDao = ebuyOrderBaseDao;
	}
	
	// 支付记录
//	private IEbuyPayRecordBaseDao ebuyPayRecordBaseDao;
//	public void setEbuyPayRecordBaseDao(IEbuyPayRecordBaseDao ebuyPayRecordBaseDao) {
//		this.ebuyPayRecordBaseDao = ebuyPayRecordBaseDao;
//	}
	
	// //购物车
//	private IEbuyBuyCarBaseDao ebuyBuyCarBaseDao;
//	public void setEbuyBuyCarBaseDao(IEbuyBuyCarBaseDao ebuyBuyCarBaseDao) {
//		this.ebuyBuyCarBaseDao = ebuyBuyCarBaseDao;
//	}
	
	// 商品购物车关系
	private IEbuyBuyCarHasTEbuyProductBaseDao ebuyBuyCarHasTEbuyProductBaseDao;
	public void setEbuyBuyCarHasTEbuyProductBaseDao(IEbuyBuyCarHasTEbuyProductBaseDao ebuyBuyCarHasTEbuyProductBaseDao) {
		this.ebuyBuyCarHasTEbuyProductBaseDao = ebuyBuyCarHasTEbuyProductBaseDao;
	}
	
	// 商品
	private IEbuyProductBaseDao ebuyProductBaseDao;
	public void setEbuyProductBaseDao(IEbuyProductBaseDao ebuyProductBaseDao) {
		this.ebuyProductBaseDao = ebuyProductBaseDao;
	}
	
	// 订单商品关系
	private IEbuyOrderHasTEbuyProductBaseDao ebuyOrderHasTEbuyProductBaseDao;
	public void setEbuyOrderHasTEbuyProductBaseDao(IEbuyOrderHasTEbuyProductBaseDao ebuyOrderHasTEbuyProductBaseDao) {
		this.ebuyOrderHasTEbuyProductBaseDao = ebuyOrderHasTEbuyProductBaseDao;
	}
	
	// 配送单
	private IEbuyDeliveryOrderBaseDao ebuyDeliveryOrderBaseDao;
	public void setEbuyDeliveryOrderBaseDao(IEbuyDeliveryOrderBaseDao ebuyDeliveryOrderBaseDao) {
		this.ebuyDeliveryOrderBaseDao = ebuyDeliveryOrderBaseDao;
	}
	
	// 配送单商品关系
	private IEbuyDeliveryOrderProductBaseDao ebuyDeliveryOrderProductBaseDao;
	public void setEbuyDeliveryOrderProductBaseDao(IEbuyDeliveryOrderProductBaseDao ebuyDeliveryOrderProductBaseDao) {
		this.ebuyDeliveryOrderProductBaseDao = ebuyDeliveryOrderProductBaseDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IPointService pointService;
	public void setPointService(IPointService pointService) {
		this.pointService = pointService;
	}
	
	private IEbuyOrderProductExtParamBaseDao ebuyOrderProductExtParamBaseDao;
	public void setEbuyOrderProductExtParamBaseDao(IEbuyOrderProductExtParamBaseDao ebuyOrderProductExtParamBaseDao) {
		this.ebuyOrderProductExtParamBaseDao = ebuyOrderProductExtParamBaseDao;
	}
	
	private IEbuyProducConversionCodeService ebuyProducConversionCodeService;
	public void setEbuyProducConversionCodeService(IEbuyProducConversionCodeService ebuyProducConversionCodeService) {
		this.ebuyProducConversionCodeService = ebuyProducConversionCodeService;
	}
	
	private IEbuyOrderProductHasCodeBaseDao ebuyOrderProductHasCodeBaseDao;
	public void setEbuyOrderProductHasCodeBaseDao(IEbuyOrderProductHasCodeBaseDao ebuyOrderProductHasCodeBaseDao) {
		this.ebuyOrderProductHasCodeBaseDao = ebuyOrderProductHasCodeBaseDao;
	}
	
	private IRedpointService redpointService;
	public void setRedpointService(IRedpointService redpointService) {
		this.redpointService = redpointService;
	}

	private IEbuyOrderHasCouponBaseService ebuyOrderHasCouponBaseService;
	public void setEbuyOrderHasCouponBaseService(IEbuyOrderHasCouponBaseService ebuyOrderHasCouponBaseService) {
		this.ebuyOrderHasCouponBaseService = ebuyOrderHasCouponBaseService;
	}

	private IUserCouponService userCouponService;
	public void setUserCouponService(IUserCouponService userCouponService) {
		this.userCouponService = userCouponService;
	}

	@Override
	public List<EbuyProductType> getProductTypeList(Integer pointType,Long wlAppType, Integer version) {
		return ebuyDao.selectProductTypeList(pointType,wlAppType, version);
	}
	
	@Override
	public List<EbuyProductEntity> getProductList(String searchKey,BigInteger productTypeId, PageModel pageModel,Integer pointType,Long wlAppType) {
		return ebuyDao.selectProductList(searchKey,productTypeId, pageModel,pointType,wlAppType);
	}
	
	@Override
	public EbuyProductEntity getProductById(BigInteger userId, BigInteger productId,Integer pointType,Long wlAppType) {
		// 认证信息、产品图片信息、产品参数信息、产品规格信息
		EbuyProductEntity ebuyProductEntity = ebuyDao.selectProductById(productId,pointType,wlAppType);
		if (ebuyProductEntity == null) {
			throw new BusinessRuntimeException("ebuy.getProductById.notexist.failed");
		}
		// 评价总数
		Comments qryCountComments = new Comments();
		qryCountComments.setTargetId(productId);
		qryCountComments.setTargetType(CommonModuleDict.CommonModule_TargetType.T_EBUY_PRODUCT);
		int commCount = commentsBaseDao.selectCommentsCount(MapConverter.toMap(qryCountComments), false);
		ebuyProductEntity.setCommentTotalCount(commCount);
		// 第一条评论内容
		Comments firstContentDetail = commentsService.getFirstComentDetail(productId,
				CommonModuleDict.CommonModule_TargetType.T_EBUY_PRODUCT);
		if(firstContentDetail!=null){
			ebuyProductEntity.setFirstComentContent(firstContentDetail.getContent());
		}
		
		// 是否被收藏
		boolean isCollected =false;
		if(!StringUtils.isEmpty(userId)){
			isCollected= isProductCollected(userId, productId);
		}
		ebuyProductEntity.setIsCollected(isCollected);
		
		return ebuyProductEntity;
	}

	@Override
	public List<EbuyProductParameters> getProductParameters(BigInteger productId) {
		EbuyProductParameters ebuyProductParameters = new EbuyProductParameters();
		ebuyProductParameters.settEbuyProductFId(productId);
		return ebuyDao.selectEbuyProductParametersByCondition(MapConverter.toMap(ebuyProductParameters));
	}

	@Override
	public List<CommentsEntity> getProductComments(BigInteger productId, PageModel pageModel) {
		Comments comments = new Comments();
		comments.setTargetId(productId);
		comments.setTargetType(CommonModuleDict.CommonModule_TargetType.T_EBUY_PRODUCT);
		return commentsService.getCommentsList(CommonModuleDict.CommonModule_TargetType.T_EBUY_PRODUCT, productId, pageModel);
	}

	@Override
	public List<EbuyDeliveryMethod> getEbuyDeliveryMethodList(BigInteger productId,Integer pointType,Long wlAppType) {
		List<EbuyDeliveryMethod> productDelvMethList = ebuyDao.selectEbuyDeliveryMethodList(productId);
		// 如果商品没有对应的配送方式，则从供应商支持的配送方式去获取
		if(productDelvMethList==null||productDelvMethList.size()<=0){
			productDelvMethList=ebuyDao.selectEbuyDeliveryMethodListFromMerchant(productId,pointType,wlAppType);
		}
		return productDelvMethList;
	}

	@Override
	public boolean isProductCollected(BigInteger userId, BigInteger productId) {
		Collections collections = new Collections();
		collections.setUserId(userId);
		collections.setTargetId(productId);
		collections.setTargetType(CommonModuleDict.CommonModule_TargetType.T_EBUY_PRODUCT);
		int res = collectionsBaseDao.selectCollectionsCount(MapConverter.toMap(collections), false);
		return res > 0;
	}

	@Override
	public List<EbuyProductRecommendEntity> getRecommendProducts(Integer pointType,Long wlAppType) {
		return ebuyDao.selectRecommendProducts(pointType,wlAppType);
	}

	@Override
	public List<EbuyDeliveryAddressEntity> getEbuyDeliveryAddressList(BigInteger userId, PageModel pageModel) {
		List<EbuyDeliveryAddressEntity> resList =  ebuyDao.selectEbuyDeliveryAddressList(userId, pageModel);
		/*if (resList == null || resList.size() <= 0) {// 没有门牌
			copyDefaultRoom2DeliveryAddress(userId);
			resList =  ebuyDao.selectEbuyDeliveryAddressList(userId, pageModel);
		}*/
		return resList;
	}

	@Override
	public EbuyOrderEntity submitOrder(BigInteger userId,BigInteger deliveryAddressId, BigInteger gbId, BigInteger productId,
									   Long productQty,BigInteger productSpecId ,EbuyExtandBuyParam ebuyExtandBuyParam,
			/*BigInteger deliveryMethodId,*/Long hbMoney, Integer pointType,Long wlAppType, String invoice_companyName, String invoice_productTypeName
			,Set<BigInteger> couponIdList,Integer jfqSubType,Long subChannelId, EbuyIdentifyInfo idInfo, List<MerchantIdDeliveryType> merchantIdDeliveryTypes) {
	List<ProductIdQtyEntity> productIdQtyList = new ArrayList<ProductIdQtyEntity>();
		ProductIdQtyEntity tmp = new ProductIdQtyEntity(productId, productQty,productSpecId,ebuyExtandBuyParam);
		productIdQtyList.add(tmp);
		return submitOrderMulti(userId, deliveryAddressId, gbId, productIdQtyList/*, deliveryMethodId*/, hbMoney, pointType,wlAppType, invoice_companyName,
				invoice_productTypeName,couponIdList,jfqSubType,subChannelId, idInfo,merchantIdDeliveryTypes);
	}

	@Override
	public EbuyOrderEntity submitOrderMulti(BigInteger userId, BigInteger deliveryAddressId, BigInteger gbId,
											List<ProductIdQtyEntity> productIdQtyList/*, BigInteger deliveryMethodId*/, Long hbMoney, Integer pointType, Long wlAppType, String invoice_companyName,
											String invoice_productTypeName, Set<BigInteger> couponIdList, Integer jfqSubType, Long subChannelId, EbuyIdentifyInfo idInfo, List<MerchantIdDeliveryType> merchantIdDeliveryTypes) {
		EbuyOrderBuyInfo ebuyOrderBuyInfo = new EbuyOrderBuyInfo(productIdQtyList);
		return submitOrderMulti(userId, deliveryAddressId, gbId, ebuyOrderBuyInfo/*, deliveryMethodId*/, hbMoney, pointType,wlAppType, invoice_companyName,
				invoice_productTypeName,couponIdList,jfqSubType,subChannelId, idInfo, merchantIdDeliveryTypes);
	}
	
//	@Override
//	public List<EbuyOrderEntity> submitOrderMultiGroupByMerchant(BigInteger userId, BigInteger deliveryAddressId,
//			List<ProductIdQtyEntity> productIdQtyListAll, BigInteger deliveryMethodId) {
	// //将商品Id根据供应商分组
//		Map<EbuySupplyMerchantEntity,List<ProductIdQtyEntity>> resMap = productIdQtyEntityListGroupByMerchant(productIdQtyListAll);
//		List<EbuyOrderEntity> resList = new ArrayList<EbuyOrderEntity>();
	// //根据分组的供应商生成多个订单
//		for(Map.Entry<EbuySupplyMerchantEntity,List<ProductIdQtyEntity>> tmpEntry:resMap.entrySet()){
	// //hbMoney赋值为null表示暂不使用粮票
//			EbuyOrderEntity ebuyOrderEntity=submitOrderMulti(userId, deliveryAddressId, tmpEntry.getValue(), deliveryMethodId,null);
//			resList.add(ebuyOrderEntity);
//		}
//		return resList;
//	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public EbuyOrderEntity submitOrderMulti(BigInteger userId, BigInteger deliveryAddressId, BigInteger gbId,
			EbuyOrderBuyInfo ebuyOrderBuyInfo/*, BigInteger deliveryMethodIdTmp*/, Long hbMoney, Integer pointType,Long wlAppType, String invoice_companyName,
			String invoice_productTypeName,Set<BigInteger> couponIdList,Integer jfqSubType,Long subChannelId, EbuyIdentifyInfo idInfo,List<MerchantIdDeliveryType> merchantIdDeliveryTypes) {
		if(logger.isDebugEnabled()){
			logger.debug("start to submitOrderMulti,userId is "+userId+",deliveryAddressId is"
					+deliveryAddressId+/*",deliveryMethodId is"+deliveryMethodIdTmp+*/",hbMoney is"+hbMoney+",pointType is "+pointType+",wlAppType is:"+wlAppType+",invoice_companyName is:"+invoice_companyName
					+",invoice_productTypeName is:"+invoice_productTypeName+",couponIdList is:"+couponIdList==null?null:JSON.toJSONString(couponIdList)+",jfqSubType is:"+jfqSubType+",subChannelId is:"+subChannelId);
				logger.debug("product info is :"+JSON.toJSONString(ebuyOrderBuyInfo));
		}
		// syl-add 2015-4-16 18:43:33校验支付优惠信息
		if(hbMoney!=null&&hbMoney>0&&couponIdList!=null&&couponIdList.size()>0){
			throw new BusinessRuntimeException("ebuy.submitOrder.coupon.multi.error");
		}

		//供应商自提设置Map
		Map<BigInteger, Integer> merchantIdDeliveryTypeMap = new HashMap<BigInteger, Integer>();
		if(merchantIdDeliveryTypes != null){
			for (MerchantIdDeliveryType merchantIdDeliveryType : merchantIdDeliveryTypes) {
				if(merchantIdDeliveryType.getDeliveryType() == 1)
					merchantIdDeliveryTypeMap.put(merchantIdDeliveryType.getMerchantId(), 1);
			}
		}
		
		Integer userType = LoginDict.UserRegistOrTmp.REGIST_USER;// 用户类型为注册用户
		BigInteger ebuyOrderAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order);
		// 查询当前购买的商品信息
		List<EbuyProductWithCurrProductSpec> currBuyProductInfoList = getEbuyProductWithCurrProductSpecList(ebuyOrderBuyInfo.getProductIdQtyList(), pointType,wlAppType);
		if(currBuyProductInfoList==null||currBuyProductInfoList.size()!=ebuyOrderBuyInfo.getProductIdQtyList().size()){
			throw new ValidateRuntimeException("ebuy.submitOrder.getProductById.isnull.error");
		}
		// 组合商品基本信息和商品购买信息
//		Map<ProductIdQtyEntity,EbuyProductWithCurrProductSpec> detailMap = ebuyOrderBuyInfo.fetchProductDetailMap(currBuyProductInfoList);
		Map<ProductIdQtyEntity,EbuyProductWithCurrProductSpec> detailMap = EbuyProductBuyInfoUtil.fetchProductDetailMap(ebuyOrderBuyInfo.getProductIdQtyList(), currBuyProductInfoList);
		// 校验库存
		StringBuffer errInfo = new StringBuffer();
		for(Map.Entry<ProductIdQtyEntity,EbuyProductWithCurrProductSpec> tmpEntry:detailMap.entrySet()){
			EbuyProductWithCurrProductSpec currBuyProductInfo = tmpEntry.getValue();
			ProductIdQtyEntity productIdQtyEntity = tmpEntry.getKey();
			if(productIdQtyEntity.getProductQty().compareTo(currBuyProductInfo.getLeftCount().longValue())>0){
				errInfo.append(currBuyProductInfo.getName());
				errInfo.append("(");
				errInfo.append(productIdQtyEntity.getProductQty());
				errInfo.append(">");
				errInfo.append(currBuyProductInfo.getLeftCount());
				errInfo.append(");");
			}
		}
		if(errInfo.lastIndexOf(";")!=-1){
			throw new BusinessRuntimeException("ebuyService.submitOrderMulti.leftCount.notEnouth",new Object[]{errInfo});
		}
		
		boolean isFlowRecharge = false;
		// 查询商品金额总和
		Long productAmount = 0L;
		Long productAmountPoint = 0L;// 积分总额
		for(Map.Entry<ProductIdQtyEntity,EbuyProductWithCurrProductSpec> tmpEntry:detailMap.entrySet()){
			EbuyProductWithCurrProductSpec currBuyProductInfo = tmpEntry.getValue();
			ProductIdQtyEntity productIdQtyEntity = tmpEntry.getKey();
			
			if(currBuyProductInfo.getFilmTicketNum() != null && currBuyProductInfo.getFilmTicketNum() == -1 && productIdQtyEntity.getPrice() != null) {
				// 充值流量的价格是从运营商传过来的。一个商品对应不同的流量和不同的价格
				productAmount = (RechargePackageUtils.getRechargePrice(productIdQtyEntity.getPhone(), productIdQtyEntity.getFlow()).multiply(BigDecimal.valueOf(100L))).longValue();
				
				EbuyFlowRecharge flowRecharge = new EbuyFlowRecharge();
				flowRecharge.setOrderId(ebuyOrderAddId.longValue());
				flowRecharge.setProductId(productIdQtyEntity.getProductId().longValue());
				flowRecharge.setPhone(productIdQtyEntity.getPhone());
				if(productIdQtyEntity.getFlow().contains("HF")) {
					flowRecharge.setName(currBuyProductInfo.getName().replace("流量", "话费") + " " + productIdQtyEntity.getFlow() + "元");
				} else {
					flowRecharge.setName(currBuyProductInfo.getName() + " " + productIdQtyEntity.getFlow() + "M");
				}
				
				flowRecharge.setName(flowRecharge.getName().replace("YD", "移动")
						.replace("LT", "联通").replace("DX", "电信").replace("HF", "")
						+ "\n手机号码：" + flowRecharge.getPhone());
				flowRecharge.setPrice(RechargePackageUtils.getRechargePrice(productIdQtyEntity.getPhone(), productIdQtyEntity.getFlow()));//从缓存里面取价格，防止被篡改价格
				flowRecharge.setFlow(productIdQtyEntity.getFlow());
				ebuyFlowRechargeService.saveFlowRecharge(flowRecharge);
				isFlowRecharge = true;
			} else {
				productAmount += currBuyProductInfo.getPriceDiscount()
						* productIdQtyEntity.getProductQty();// 乘以商品数量
			}
			productAmountPoint += currBuyProductInfo.getPricePoint()
					* productIdQtyEntity.getProductQty();// 乘以商品数量
		}
		// 获取商品的配送方式 按供应商分组
		Map<BigInteger, List> deliveryMethodMap = fetchEbuyDeliveryMethodBatch(ebuyOrderBuyInfo, detailMap,pointType,wlAppType);
		// 查询配送地址
		EbuyDeliveryAddressEntity ebuyDeliveryAddressEntity = null;
		if(deliveryAddressId!=null){
			ebuyDeliveryAddressEntity = getDeliveryAddressDetail(deliveryAddressId, userId);
			if(ebuyDeliveryAddressEntity==null){
				throw new ValidateRuntimeException("ebuy.submitOrder.getDeliveryAddressDetail.isnull.error");
			}
		}
		// 组装订单信息
		String nowTime = dualDao.getNowTime();
		
		Long totalDeliveryFee = 0L;// 总配送费
		ArrayList<EbuyOrderDeliveryType> ebuyOrderDeliveryTypeAddNewList = new ArrayList<EbuyOrderDeliveryType>();
		for (Map.Entry<BigInteger, List> tmpEntry : deliveryMethodMap.entrySet()) {
			if(merchantIdDeliveryTypeMap.keySet().contains(tmpEntry.getKey())){
				EbuyOrderDeliveryType e = new EbuyOrderDeliveryType();
				e.setDeliveryType(1);
				e.settEbuyOrderFId(ebuyOrderAddId);
				e.settEbuySupplyMerchant(tmpEntry.getKey());
				ebuyOrderDeliveryTypeAddNewList.add(e);
				continue;
			}

			List delivInfo = tmpEntry.getValue();
			totalDeliveryFee += ((EbuyDeliveryMethod) (delivInfo.get(0))).getFee();
		}

		Long amount = productAmount + totalDeliveryFee;// 商品价格*商品数量+总配送费
		Long amountPoint = productAmountPoint;// 所需积分
		EbuyOrder ebuyOrder = new EbuyOrder();
		ebuyOrder.setSubChannel(subChannelId == null ? null : subChannelId + "");
		ebuyOrder.setType(EbuyDict.EbuyOrder_Type.EBuy_Product);// 订单类型：电商商品
		ebuyOrder.setPointType(pointType);
		ebuyOrder.setTotalDeliveryFee(totalDeliveryFee);// 总配送费
		ebuyOrder.setAmount(amount);// 商品价格*商品数量+总配送费
		ebuyOrder.setAmountPoint(amountPoint);
		ebuyOrder.setId(ebuyOrderAddId);
		ebuyOrder.setBuyerId(userId);
		BigInteger roomId = userBaseDao.selectUserBySeqId(userId).getDefaultRoomId();
		ebuyOrder.setCurrRoomId(roomId);
		if (new BigInteger("-1").equals(gbId)) {
			Map<String, Object> roomAddressIdByRoom = commonRoomService.getRoomAddressIdByRoom(roomId);
			gbId = new BigInteger(roomAddressIdByRoom.get("gbId").toString());
		}
		ebuyOrder.setGbId(gbId);
		ebuyOrder.setBuyTime(nowTime);
		ebuyOrder.setCreater(userId);
		// {//拷贝配送方式,由于目前配送方式时针对供应商，所以此处不保存配送方式信息
//				ebuyOrder.setDelivmName(ebuyDeliveryMethod.getName());
//				ebuyOrder.setDelivmFee(ebuyDeliveryMethod.getFee());
//				ebuyOrder.setDelivmDesc(ebuyDeliveryMethod.getDesc());
//			}
		ebuyOrder.setOrderNo(OrderNoGenerator.getOrderNo(ebuyOrderAddId));
		ebuyOrder.setPayMethod(null);
		ebuyOrder.setPayTime(null);
		ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.DaiFuKuan);
		ebuyOrder.setPayStatus(EbuyDict.EbuyOrder_PayStatus.Not_Pay);// 支付状态为待支付
		ebuyOrder.setDelivStatus(EbuyDict.EbuyOrder_DelivStatus.Not_Deliv);// 发货状态为未发货
		if (ebuyDeliveryAddressEntity != null) {// 拷贝配送地址 配送地址可以为空，例如缴纳话费的时候
			ebuyOrder.setDelivPeopleName(ebuyDeliveryAddressEntity.getPeopleName());
			ebuyOrder.setDelivPhone(ebuyDeliveryAddressEntity.getPhone());
			ebuyOrder.setDelivTargetType(ebuyDeliveryAddressEntity.getTargetType());
			SimpleDelivAddress simpleDelivAddress = ebuyDeliveryAddressEntity.getSimpleDelivAddress();
			if (simpleDelivAddress == null) {
				throw new ValidateRuntimeException("ebuy.submitOrder.simpleDelivAddress.isnull");
			}
			ebuyOrder.setDelivAddressArea(simpleDelivAddress.getAddressArea());
			ebuyOrder.setDelivAddressDetail(ebuyDeliveryAddressEntity.getAddressDetail());
			if (simpleDelivAddress.getTargetId() == null) {
				ebuyOrder.setDelivTargetId(ebuyDeliveryAddressEntity.gettRoomFId());
			} else {
				ebuyOrder.setDelivTargetId(simpleDelivAddress.getTargetId());
			}
		}

		ebuyOrder.setInvoiceCompanyName(invoice_companyName);
		ebuyOrder.setInvoiceProductTypeName(invoice_productTypeName);

		// 新增订单
		int res = ebuyOrderBaseDao.insertEbuyOrder(ebuyOrder);
		if (res <= 0) {
			throw new BusinessRuntimeException("ebuy.submitOrder.insertEbuyOrder.error");
		}

		//用户设置哪些供应商要自提
		CnfantasiaCommbusiUtil.newStandardListForId(ebuyOrderDeliveryTypeAddNewList, SEQConstants.t_ebuy_order_delivery_type);
		ebuyOrderDeliveryTypeBaseDao.insertEbuyOrderDeliveryTypeBatch(ebuyOrderDeliveryTypeAddNewList);

		// 批量新增 订单商品关系
		List<EbuyOrderHasTEbuyProduct> ebuyOrderHasTEbuyProductAddList = new ArrayList<EbuyOrderHasTEbuyProduct>();
		{
			List<BigInteger> ebuyOrderHasTEbuyProductAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_has_t_ebuy_product,detailMap.entrySet().size());
			// 兑换码信息存储
			List<EbuyOrderProductHasCode> ebuyOrderProductHasCodeAddList = new ArrayList<EbuyOrderProductHasCode>();
			// 购买时的商品扩展信息 key 订单商品关系Id,value 商品购买信息
			Map<BigInteger,ProductIdQtyEntity> extendProductParamMap = new HashMap<BigInteger, ProductIdQtyEntity>();
			int i=-1;
			for(Map.Entry<ProductIdQtyEntity,EbuyProductWithCurrProductSpec> tmoEntry:detailMap.entrySet()){
				i++;
				EbuyProductWithCurrProductSpec currBuyProductInfo = tmoEntry.getValue();
				ProductIdQtyEntity productIdQtyEntity = tmoEntry.getKey();
				BigInteger ebuyOrderHasTEbuyProductAddId = ebuyOrderHasTEbuyProductAddIdList.get(i);
				EbuyOrderHasTEbuyProduct ebuyOrderHasTEbuyProduct  = new EbuyOrderHasTEbuyProduct();
				ebuyOrderHasTEbuyProduct.setId(ebuyOrderHasTEbuyProductAddId);
				ebuyOrderHasTEbuyProduct.settEbuyOrderFId(ebuyOrderAddId);
				ebuyOrderHasTEbuyProduct.settEbuyProductFId(productIdQtyEntity.getProductId());
				ebuyOrderHasTEbuyProduct.settEbuyProductSpecFId(currBuyProductInfo.getProductSpecId());// 商品规格Id
				ebuyOrderHasTEbuyProduct.setProductQty(productIdQtyEntity.getProductQty().longValue());
				if(isFlowRecharge) {
					ebuyOrderHasTEbuyProduct.setProductPrice(productAmount);// 商品折扣价 实际现金
					ebuyOrderHasTEbuyProduct.setPurchasePrice(RechargePackageUtils.getRechargeSalePrice(productIdQtyEntity.getPhone(), productIdQtyEntity.getFlow()).multiply(BigDecimal.valueOf(100L)).longValue());
					//短信采购价补上
				} else {
					ebuyOrderHasTEbuyProduct.setProductPrice(currBuyProductInfo.getPriceDiscount());// 商品折扣价 实际现金
					if(currBuyProductInfo.doEntity().getPurchasePrice() == null || currBuyProductInfo.doEntity().getPurchasePrice() == 0) {
						ebuyOrderHasTEbuyProduct.setProductPrice(currBuyProductInfo.getPriceDiscount());// 商品折扣价 实际现金
					} else {
						ebuyOrderHasTEbuyProduct.setPurchasePrice(currBuyProductInfo.doEntity().getPurchasePrice());
					}
				}
				
				ebuyOrderHasTEbuyProduct.setProductPricePoint(currBuyProductInfo.getPricePoint());// 所需积分取值
				{// 由于目前配送方式时针对供应商，所以此处不保存配送方式信息
					// ebuyOrderHasTEbuyProduct.setDeliveryRealFee(ebuyDeliveryMethod.getFee());//配送方式费用
					// ebuyOrderHasTEbuyProduct.setDeliveryId(ebuyDeliveryMethod.getId());//配送方式Id
				}
				ebuyOrderHasTEbuyProduct.setSupplyMerchantId(currBuyProductInfo.gettSupplyMerchantFId());// 供应商Id(冗余，索引，性能)
				if(currBuyProductInfo.getShelfProduct() != null) {
					ebuyOrderHasTEbuyProduct.setOpType(currBuyProductInfo.getShelfProduct().getOpType());
				}
				
				{// 兑换码信息
					Integer specialProductType = currBuyProductInfo.getSpecialProductType();
					if (specialProductType.compareTo(EbuyDict.Product_SpecialType.MoneyTicket) == 0) {// 现金券获取
						BigInteger productId = currBuyProductInfo.getProductId();
						BigInteger productSpecId = currBuyProductInfo.getProductSpecId();
						EbuyProductConversionCode tmpCodeData = ebuyProducConversionCodeService.fetchConversionCodeAndMarkAsLocked(productId, productSpecId);
						EbuyOrderProductHasCode toAddRela = new EbuyOrderProductHasCode();
						toAddRela.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_product_has_code));
						toAddRela.setOutTime(tmpCodeData.getOutTime());
						toAddRela.setCodeValue(tmpCodeData.getValue());
						toAddRela.settEbuyOrderHasTEbuyProductFId(ebuyOrderHasTEbuyProductAddId);
						toAddRela.settEbuyProductConversionCodeFId(tmpCodeData.getId());
						ebuyOrderProductHasCodeAddList.add(toAddRela);
					}
				}
				ebuyOrderHasTEbuyProductAddList.add(ebuyOrderHasTEbuyProduct);
				extendProductParamMap.put(ebuyOrderHasTEbuyProductAddId, productIdQtyEntity);// 存储附件购买信息
			}
			{
				int hasProductRes = ebuyOrderHasTEbuyProductBaseDao.insertEbuyOrderHasTEbuyProductBatch(ebuyOrderHasTEbuyProductAddList);
				if(hasProductRes != ebuyOrderHasTEbuyProductAddList.size()){
					throw new BusinessRuntimeException("ebuy.submitOrder.insertEbuyOrderHasTEbuyProduct.error");
				}
			}
			if(ebuyOrderProductHasCodeAddList!=null&&ebuyOrderProductHasCodeAddList.size()>0){
				Integer resCount = ebuyOrderProductHasCodeBaseDao.insertEbuyOrderProductHasCodeBatch(ebuyOrderProductHasCodeAddList);
				if(resCount!=ebuyOrderProductHasCodeAddList.size()){
					throw new BusinessRuntimeException("ebuy.submitOrder.insertEbuyOrderProductHasCode.error");
				}
			}
			{// 充话费的手机号 QQ扩展信息存储
				List<EbuyOrderProductExtParam> productExtParamList = new ArrayList<EbuyOrderProductExtParam>();
				for(BigInteger ebuyOrderHasTEbuyProductId:extendProductParamMap.keySet()){
					ProductIdQtyEntity currBuyInfo = extendProductParamMap.get(ebuyOrderHasTEbuyProductId);
					EbuyExtandBuyParam tmpEbuyExtandBuyParam = currBuyInfo.getEbuyExtandBuyParam();
					if(tmpEbuyExtandBuyParam!=null){
						EbuyOrderProductExtParam tmpParam = new EbuyOrderProductExtParam();
//						tmpParam.setId(id);
						tmpParam.setPhoneNum(tmpEbuyExtandBuyParam.getPhoneNum());
						tmpParam.setQqNum(tmpEbuyExtandBuyParam.getQqNum());
						tmpParam.settEbuyOrderHasTEbuyProductFId(ebuyOrderHasTEbuyProductId);
						productExtParamList.add(tmpParam);
					}
				}
				if(productExtParamList!=null&&productExtParamList.size()>0){
					// 初始化新增的Id
					List<BigInteger> productExtParamAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_product_ext_param, productExtParamList.size());
					for(int kk=0;kk<productExtParamList.size();kk++){
						productExtParamList.get(kk).setId(productExtParamAddIdList.get(kk));
					}
					Integer resCount = ebuyOrderProductExtParamBaseDao.insertEbuyOrderProductExtParamBatch(productExtParamList);
					if(resCount!=productExtParamList.size()){
						throw new BusinessRuntimeException("ebuy.submitOrder.insertEbuyOrderProductExtParamBatch.error");
					}
				}
			}
		}

		// 锁定库存（减少f_left_count）
		lockProductLeftCountByOrderId(ebuyOrderAddId, userId,pointType,wlAppType);
		// 减少限时购里的库存
		lockLimitBuyLeftCountBuyOrderId(ebuyOrderAddId, userId);
		
		if (pointType.compareTo(EbuyDict.PointType.POINT_PRODUCT) == 0) {// 积分商品
																			// 不通过购物车购买!!!
			
		}else if(!isFlowRecharge){
			// 移除部分购物车商品信息,syl-upd-2015-2-9 10:48:33 单独调用方法目的是使用不同的错误码
			removeProdFromBuyCar_ForEbuyOrder(userId,userType,ebuyOrderBuyInfo.getProductIdQtyList(),pointType,wlAppType);
		}

		//订单分组并计算运单总价
		Map<BigInteger,List<EbuyOrderHasTEbuyProduct>> ebuyOrderHasTEbuyProductGroup = new HashMap<BigInteger, List<EbuyOrderHasTEbuyProduct>>();
		Map<BigInteger, Long> merchantFeeMap = new HashMap<BigInteger, Long>();
		for (EbuyOrderHasTEbuyProduct ebuyOrderHasTEbuyProduct : ebuyOrderHasTEbuyProductAddList) {// 分组
			// key=供应商Id,value=订单商品关系
			BigInteger suplyMerId = ebuyOrderHasTEbuyProduct.getSupplyMerchantId();
			if(ebuyOrderHasTEbuyProductGroup.containsKey(suplyMerId)){
				ebuyOrderHasTEbuyProductGroup.get(suplyMerId).add(ebuyOrderHasTEbuyProduct);
				merchantFeeMap.put(suplyMerId, merchantFeeMap.get(suplyMerId) + ebuyOrderHasTEbuyProduct.getProductPrice() * ebuyOrderHasTEbuyProduct.getProductQty());
			}else{
				List<EbuyOrderHasTEbuyProduct> tmpList = new ArrayList<EbuyOrderHasTEbuyProduct>();
				tmpList.add(ebuyOrderHasTEbuyProduct);
				ebuyOrderHasTEbuyProductGroup.put(suplyMerId, tmpList);
				merchantFeeMap.put(suplyMerId, ebuyOrderHasTEbuyProduct.getProductPrice() * ebuyOrderHasTEbuyProduct.getProductQty());
			}
		}

		for (BigInteger merchantId : deliveryMethodMap.keySet()) {
			Long fee = ((EbuyDeliveryMethod) deliveryMethodMap.get(merchantId).get(0)).getFee();
			if(!merchantIdDeliveryTypeMap.keySet().contains(merchantId)){
				merchantFeeMap.put(merchantId, merchantFeeMap.get(merchantId) + fee);
			}
		}

		
		// 目前暂不考虑 商品同时需要现金和积分的支付情况 根据pointType 决定使用的支付方式
		if (pointType.compareTo(EbuyDict.PointType.POINT_PRODUCT) == 0) {// 积分商品的积分支付
			EbuyOrder orderDetail = ebuyOrderBaseDao.selectEbuyOrderBySeqId(ebuyOrderAddId);
			// 校验积分支付的订单不能有现金金额
			Long amountValue = orderDetail.getAmount();
			if(amountValue!=null&&amountValue>0){
				throw new BusinessRuntimeException("ebuyService.submitOrder.pointPay.",new Object[]{amountValue});
			}
			Long pointValue = orderDetail.getAmountPoint();
			if(pointValue==null||pointValue<0){
				throw new BusinessRuntimeException("ebuyService.submitOrder.pointPay.pointValue.isNull",new Object[]{pointValue});
			}
			// 用户扣除积分
			pointService.costPoint(userId, PointConstant.PointCostType.BuyPointProduct_Comm, pointValue);
			// 更新订单支付状态
			commonEbuyService.paySuccessOperateComm(ebuyOrderAddId,EbuyDict.EbuyPay_PayMethod.PurePoint);
			ebuyFilmTicketService.processTicketForSubmitOrder(ebuyOrderAddId.longValue());
			FlowRechargePool.addOrderIdForRecharge(ebuyOrderAddId.toString());
		} else {// 电商支付
				// 校验 电商支付的订单不能有积分
			EbuyOrder orderDetail = ebuyOrderBaseDao.selectEbuyOrderBySeqId(ebuyOrderAddId);
			Long pointValue = orderDetail.getAmountPoint();
			if(pointValue!=null&&pointValue>0){
				throw new BusinessRuntimeException("ebuyService.submitOrder.hbPay.hasPointValue",new Object[]{pointValue});
			}
			
			// 粮票处理--syl-add 2014-7-3 14:34:58
			if (hbMoney != null && hbMoney.compareTo(0L) > 0) {// 粮票使用且大于0
				logger.debug("start updateOrderByHb ...");
				Long orderLeftAmount=commonEbuyService.updateOrderByHb(userId, ebuyOrderAddId, hbMoney);
				logger.debug("updateOrderByHb success...");
				if (orderLeftAmount != null && orderLeftAmount.compareTo(0L) == 0) {// 剩余应付金额直接设置订单状态为支付成功
					// 直接设定订单状态为成功
					logger.info("start to use hbMoney total.");
					commonEbuyService.paySuccessOperateComm(ebuyOrderAddId,EbuyDict.EbuyPay_PayMethod.PureRedEnvelope);
					ApplicationContextBothUtil.getMicroblogQueueService().hbPayFree(userId, commonRoomService.getGroupBuildingIdByUserId(userId));
					ebuyFilmTicketService.processTicketForSubmitOrder(ebuyOrderAddId.longValue());
					FlowRechargePool.addOrderIdForRecharge(ebuyOrderAddId.toString());
					logger.info("use hbMoney only success.");
				}
			} else if (couponIdList != null && couponIdList.size() > 0) {// 使用优惠券
				logger.debug("start updateOrderByCopounList ...");
				Long orderLeftAmount=commonEbuyService.updateOrderByCopounList(userId, ebuyOrderAddId, couponIdList,jfqSubType, merchantFeeMap);
				logger.debug("updateOrderByCopounList success...");
				if (orderLeftAmount != null && orderLeftAmount.compareTo(0L) == 0) {// 剩余应付金额直接设置订单状态为支付成功
					// 直接设定订单状态为成功
					logger.info("start to use couponIdList total.");
					commonEbuyService.paySuccessOperateComm(ebuyOrderAddId,EbuyDict.EbuyPay_PayMethod.PureSupriseGiftList);
					ebuyFilmTicketService.processTicketForSubmitOrder(ebuyOrderAddId.longValue());
					FlowRechargePool.addOrderIdForRecharge(ebuyOrderAddId.toString());
					logger.info("use couponIdList only success.");
				}
			}
		}


		Coupon usedCoupon = null;
		if (couponIdList != null && couponIdList.size() > 0) {
			UserCoupon userCoupon = userCouponBaseService.getUserCouponBySeqId(new ArrayList<BigInteger>(couponIdList).get(0));
			usedCoupon = couponBaseService.getCouponBySeqId(userCoupon.gettCouponFId());
		}

		{// 设置送货单信息，按供应商拆分
			ebuyOrder = ebuyOrderBaseDao.selectEbuyOrderBySeqId(ebuyOrderAddId);
			List<EbuyDeliveryOrder> ebuyDeliveryOrderAddList = new ArrayList<EbuyDeliveryOrder>();// 要新增的配送单
			List<EbuyDeliveryOrderProduct> ebuyDeliveryOrderProductAddList = new ArrayList<EbuyDeliveryOrderProduct>();// 要新增的配送单列表

			List<BigInteger> ebuyDeliveryOrderAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_delivery_order,ebuyOrderHasTEbuyProductGroup.entrySet().size());
			int m=-1;

			Long resCoupon = ebuyOrder.getTotalCouponAmount() == null ? 0L : ebuyOrder.getTotalCouponAmount();
			Long totalOrderCouponAmout = ebuyOrder.getTotalCouponAmount() == null ? 0L : ebuyOrder.getTotalCouponAmount();
			for(Map.Entry<BigInteger,List<EbuyOrderHasTEbuyProduct>> empEntry:ebuyOrderHasTEbuyProductGroup.entrySet()){
				m++;
				BigInteger supplyMerchantId = empEntry.getKey();
				List<EbuyOrderHasTEbuyProduct> ebuyOrderEbuyProductList = empEntry.getValue();
				EbuyDeliveryMethod ebuyDeliveryMethod = (EbuyDeliveryMethod) deliveryMethodMap.get(supplyMerchantId).get(0);
				SupplyMerchantDeliveryFeeSettlement feeSettlement = (SupplyMerchantDeliveryFeeSettlement) deliveryMethodMap.get(supplyMerchantId).get(1);
				BigInteger ebuyDeliveryOrderAddId = ebuyDeliveryOrderAddIdList.get(m);
				EbuyDeliveryOrder ebuyDeliveryOrder = new EbuyDeliveryOrder();
				ebuyDeliveryOrder.setId(ebuyDeliveryOrderAddId);
				ebuyDeliveryOrder.setBuyerId(userId);
				//运单总费用 = 商品钱 + 运费
				Long singleMerchantFee = merchantFeeMap.get(supplyMerchantId);

				if (usedCoupon != null && usedCoupon.getUseType().equals(CouponUseTypeConstant.SUPPLY_MERCHANT)) {
					if (usedCoupon.getSupplyMerchantId().equals(supplyMerchantId)) {
						ebuyDeliveryOrder.setAmount(singleMerchantFee - totalOrderCouponAmout);
						ebuyDeliveryOrder.setTotalCoupon(totalOrderCouponAmout);
					} else {
						ebuyDeliveryOrder.setAmount(singleMerchantFee);
						ebuyDeliveryOrder.setTotalCoupon(0L);
					}
				} else {
					if (ebuyOrderHasTEbuyProductGroup.entrySet().size() == 1) { //只有一个运单直接取订单的
						ebuyDeliveryOrder.setAmount(ebuyOrder.getAmount());
						ebuyDeliveryOrder.setTotalCoupon(totalOrderCouponAmout);
					} else if (m != ebuyOrderHasTEbuyProductGroup.entrySet().size() - 1) {//不是最后一单，按比例算
						Long totalCoupon = singleMerchantFee * totalOrderCouponAmout / (ebuyOrder.getAmount() + totalOrderCouponAmout);
						resCoupon = resCoupon - totalCoupon;
						ebuyDeliveryOrder.setAmount(singleMerchantFee - totalCoupon);
						ebuyDeliveryOrder.setTotalCoupon(totalCoupon);
					} else {//最后一单用减法
						ebuyDeliveryOrder.setAmount(singleMerchantFee - resCoupon);
						ebuyDeliveryOrder.setTotalCoupon(resCoupon);
					}
				}

				ebuyDeliveryOrder.setCreateTime(nowTime);
				ebuyDeliveryOrder.setDeliveryId(ebuyDeliveryMethod.getId());
				if (merchantIdDeliveryTypeMap.keySet().contains(supplyMerchantId)) {
					ebuyDeliveryOrder.setDeliveryRealFee(0L);
					ebuyDeliveryOrder.setUserDeliveryType(2);
				} else {
					ebuyDeliveryOrder.setDeliveryRealFee(ebuyDeliveryMethod.getFee());
					ebuyDeliveryOrder.setUserDeliveryType(1);
				}

				if (feeSettlement != null) {
					ebuyDeliveryOrder.setDeliverySettlementFee(feeSettlement.getSettlementFee());
				}
				ebuyDeliveryOrder.setDeliveryNo(DeliveryNoGenerator.getDeliveryNo(ebuyDeliveryOrderAddId, supplyMerchantId));
				ebuyDeliveryOrder.setStatus(EbuyDict.EbuyDeliveryOrder_Status.NotStart);// 未启动状态
				ebuyDeliveryOrder.settEbuyOrderFId(ebuyOrderAddId);
				ebuyDeliveryOrder.settSupplyMerchantFId(supplyMerchantId);
				ebuyDeliveryOrder.setRevenueStatus(0);//新增是否计算收益
				ebuyDeliveryOrder.setPushStatus(EbuyDict.DeliveryOrderpush_Status.NotStart);//配送单推送状态0未发送
				ebuyDeliveryOrderAddList.add(ebuyDeliveryOrder);// 增加配送单

				for(int j=0;j<ebuyOrderEbuyProductList.size();j++){
					EbuyOrderHasTEbuyProduct ebuyOrderHasTEbuyProductTmp=ebuyOrderEbuyProductList.get(j);
					EbuyDeliveryOrderProduct ebuyDeliveryOrderProduct = new EbuyDeliveryOrderProduct();
					ebuyDeliveryOrderProduct.settEbuyDeliveryOrderFId(ebuyDeliveryOrderAddId);
					ebuyDeliveryOrderProduct.settEbuyOrderFId(ebuyOrderAddId);
					ebuyDeliveryOrderProduct.settEbuyOrderHasTEbuyProductFId(ebuyOrderHasTEbuyProductTmp.getId());
					ebuyDeliveryOrderProduct.settEbuyProductFId(ebuyOrderHasTEbuyProductTmp.gettEbuyProductFId());
					ebuyDeliveryOrderProduct.setSupplyMerchantId(supplyMerchantId);
					ebuyDeliveryOrderProduct.setOpType(ebuyOrderHasTEbuyProductTmp.getOpType());
					ebuyDeliveryOrderProductAddList.add(ebuyDeliveryOrderProduct);
				}
			}
			if(ebuyDeliveryOrderProductAddList!=null&&ebuyDeliveryOrderProductAddList.size()>0){
				List<BigInteger> ebuyDeliveryOrderProductAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_delivery_order_product,ebuyDeliveryOrderProductAddList.size());
				for(int mm=0;mm<ebuyDeliveryOrderProductAddList.size();mm++){
					ebuyDeliveryOrderProductAddList.get(mm).setId(ebuyDeliveryOrderProductAddIdList.get(mm));
				}
			}

			{// 批量新增配送单
				int res01=ebuyDeliveryOrderBaseDao.insertEbuyDeliveryOrderBatch(ebuyDeliveryOrderAddList);
				if(res01!=ebuyDeliveryOrderAddList.size()){
					throw new BusinessRuntimeException("ebuy.submitOrder.insertEbuyDeliveryOrderBatch.error");
				}
			}
			{// 批量新增配送单的商品信息
				int res02=ebuyDeliveryOrderProductBaseDao.insertEbuyDeliveryOrderProductBatch(ebuyDeliveryOrderProductAddList);
				if(res02!=ebuyDeliveryOrderProductAddList.size()){
					throw new BusinessRuntimeException("ebuy.submitOrder.insertEbuyDeliveryOrderProductBatch.error");
				}
			}

		}
		
		//保存海淘商品身份证信息
		if(idInfo != null) {
			idInfo.setOrderId(ebuyOrderAddId.longValue());
			ebuyIdentifyInfoService.insertIdentifyInfo(idInfo);
		}
		
		// 返回当前订单详情
		EbuyOrderEntity currEbuyOrderEntity=ebuyDao.getEbuyOrderEntityDetail(userId, ebuyOrderAddId,pointType,wlAppType);
		if(logger.isDebugEnabled()){
			try {
				logger.debug("Well done,submitOrderMulti success,orde info is:"+JSON.toJSONString(currEbuyOrderEntity));
			} catch (Exception e) {
				logger.debug("JSON.toJSONString(currEbuyOrderEntity) cause exception", e);
			}
		}

		return currEbuyOrderEntity;
	}

	private void lockLimitBuyLeftCountBuyOrderId(BigInteger ebuyOrderAddId, BigInteger userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderId", ebuyOrderAddId);
		paramMap.put("userId", userId);
		List<LimitBuyId_PrdtQty> limitBuyId_PrdtQtyList = ebuyDao.select_limitBuyId_prdtQty(paramMap);
		for (LimitBuyId_PrdtQty limitBuyId_PrdtQty : limitBuyId_PrdtQtyList) {
			LimitBuyInfo limitBuyInfo = limitBuyService.getLimitBuyInfo(limitBuyId_PrdtQty.getLimitBuyId(), 1);
			limitBuyService.saveLimitBuyRecord(limitBuyId_PrdtQty.getPrdtQty(), limitBuyInfo, userId, ebuyOrderAddId);
		}
	}

	private Map<BigInteger, List> fetchEbuyDeliveryMethodBatch(EbuyOrderBuyInfo ebuyOrderBuyInfo,Map<ProductIdQtyEntity,EbuyProductWithCurrProductSpec> detailMap,Integer pointType,Long wlAppType){
		Map<EbuySupplyMerchantEntity,List<ProductIdQtyEntity>> buyMap = productIdQtyEntityListGroupByMerchant(ebuyOrderBuyInfo,pointType,wlAppType);
		Map<BigInteger, List> resMap = new HashMap<BigInteger, List>();
		for(Map.Entry<EbuySupplyMerchantEntity,List<ProductIdQtyEntity>> tmpEntry:buyMap.entrySet()){
			EbuySupplyMerchantEntity ebuySupplyMerchantEntity = tmpEntry.getKey();
			List<ProductIdQtyEntity> productIdQtyEntityList = tmpEntry.getValue();
			BigInteger deliveryMethodId = null;// 设置为空,再次获取
			List deliveryInfo = fetchEbuyDeliveryMethod(ebuySupplyMerchantEntity.getId(),deliveryMethodId, productIdQtyEntityList, detailMap, pointType,wlAppType);
			resMap.put(ebuySupplyMerchantEntity.getId(), deliveryInfo);
		}
		return resMap;
	}

	@Override
	public List fetchEbuyDeliveryMethod(BigInteger merchantId,BigInteger deliveryMethodId
			,List<ProductIdQtyEntity> productIdQtyEntityList
			,Map<ProductIdQtyEntity,EbuyProductWithCurrProductSpec> detailMap
			,Integer pointType,Long wlAppType){
		// 查询配送方式信息
		EbuyDeliveryMethod ebuyDeliveryMethod = null;
		if(deliveryMethodId == null && detailMap != null) {
			for(Map.Entry<ProductIdQtyEntity,EbuyProductWithCurrProductSpec> entry : detailMap.entrySet()) {
				if(entry.getValue().gettSupplyMerchantFId().equals(merchantId)) {
					EbuyProductShelf prodShelf = entry.getValue().getShelfProduct();
					if(prodShelf != null && prodShelf.getOpType() != null && prodShelf.getOpType() == 1) {
						deliveryMethodId = EbuyConstant.EbuyDelivery_Id.FREE_FEE_NEW_USER;
						break;
					}
				}
			}
		}
		SupplyMerchantDeliveryFeeSettlement feeSettlement = null;
		if(deliveryMethodId==null){
			// 通过价格判断配送方式
			if (pointType.compareTo(EbuyDict.PointType.POINT_PRODUCT) == 0) {// 积分商品默认免运费
				deliveryMethodId = EbuyConstant.EbuyDelivery_Id.PointProduct_FreeFee_Supply;
			}else{
				if(detailMap==null){
					List<EbuyProductWithCurrProductSpec> ebuyProductWithSpecList = getEbuyProductWithCurrProductSpecList(productIdQtyEntityList, pointType,wlAppType);
					detailMap = EbuyProductBuyInfoUtil.fetchProductDetailMap(productIdQtyEntityList, ebuyProductWithSpecList);
				}
				Long productAmount = 0L;
				for(ProductIdQtyEntity tmpProductIdQtyEntity:productIdQtyEntityList){
					productAmount += detailMap.get(tmpProductIdQtyEntity).getPriceDiscount()
							* tmpProductIdQtyEntity.getProductQty();
				}
				// syl-add 2015-4-23 13:01:46 根据供应商Id查询配所有送方式列表
				List<EbuyDeliveryMethodEntity> currMerEbuyDeliveryMethodList = ebuyDao.selectEbuyDeliveryMethodListByMerId(merchantId);
				if(currMerEbuyDeliveryMethodList==null||currMerEbuyDeliveryMethodList.size()<=0){
					logger.info("ebuy.fetchEbuyDeliveryMethod.deliveryMethodList.empty,merchantId is:"+merchantId+"productIdQtyEntityList is:"+JSON.toJSONString(productIdQtyEntityList)+",pointType is:"+pointType+",wlAppType is:"+wlAppType);
					throw new ValidateRuntimeException("ebuy.fetchEbuyDeliveryMethod.deliveryMethodList.empty",new Object[]{merchantId});
				}
				// 通过价格和供应商判断配送方式
				for(EbuyDeliveryMethodEntity tmpEbuyDeliveryMethodEntity:currMerEbuyDeliveryMethodList){
					if(tmpEbuyDeliveryMethodEntity.fetchIsContain(productAmount)){
						deliveryMethodId = tmpEbuyDeliveryMethodEntity.getId();
						break;
					}
				}
				//通过价格查询与供应商的结算邮费
				feeSettlement = ebuyMerchantService.getMerchantSettleFeeByAmountAndMerchantId(merchantId, productAmount);
			}
		}
		// 根据Id查询记录详情
		ebuyDeliveryMethod = ebuyDeliveryMethodBaseDao.selectEbuyDeliveryMethodBySeqId(deliveryMethodId);
		if(ebuyDeliveryMethod==null){
			throw new ValidateRuntimeException("ebuy.submitOrder.selectEbuyDeliveryMethodBySeqId.isnull.error");
		}
		List res = new ArrayList();
		res.add(ebuyDeliveryMethod);
		res.add(feeSettlement);
		return res;
	}
	
	
	/**
	 * 将商品Id根据供应商分组
	 * 
	 * @return
	 */
	private Map<EbuySupplyMerchantEntity,List<ProductIdQtyEntity>> productIdQtyEntityListGroupByMerchant(EbuyOrderBuyInfo ebuyOrderBuyInfo,Integer pointType,Long wlAppType){
		List<EbuyProductEntity> prodEntityList = ebuyDao.selectMerchantByProductIds(ebuyOrderBuyInfo.getProductIdQtyList(),pointType,wlAppType);
		List<ProductIdQtyEntity> productIdQtyListAll = ebuyOrderBuyInfo.getProductIdQtyList();
		Map<EbuySupplyMerchantEntity,List<ProductIdQtyEntity>> resMap = new HashMap<EbuySupplyMerchantEntity, List<ProductIdQtyEntity>>();
		for(EbuyProductEntity tmpEntity:prodEntityList){
			EbuySupplyMerchantEntity merchant = tmpEntity.getEbuySupplyMerchantEntity();
			ProductIdQtyEntity currEqual = null;
			for(ProductIdQtyEntity productIdQtyEntity:productIdQtyListAll){
				if(productIdQtyEntity.getProductId().compareTo(tmpEntity.getId())==0){
					currEqual = productIdQtyEntity;
					break;
				}
			}
			if(currEqual==null){
				throw new BusinessRuntimeException("ebuyService.submitOrderMultiGroupByMerchant.prodEntityList.caueLose");
			}
			if(resMap.get(merchant)!=null){
				resMap.get(merchant).add(currEqual);
			}else{
				List<ProductIdQtyEntity> tmpList = new ArrayList<ProductIdQtyEntity>();
				tmpList.add(currEqual);
				resMap.put(merchant, tmpList);
			}
		}
		return resMap;
	}
	
//	/**
	// * 将商品Id根据供应商分组
//	 * @return
//	 */
//	private Map<EbuySupplyMerchantEntity,List<ProductIdQtyEntity>> productIdQtyEntityListGroupByMerchant(List<ProductIdQtyEntity> productIdQtyListAll){
//		EbuyOrderBuyInfo ebuyOrderBuyInfo = new EbuyOrderBuyInfo(productIdQtyListAll);
//		return productIdQtyEntityListGroupByMerchant(ebuyOrderBuyInfo);
//	}
	
	@Override
	public EbuyDeliveryAddressEntity addDeliveryAddress(BigInteger userId, Integer targetType, String userName, String userPhone,
			Integer isDefault, BigInteger targetId, BigInteger groupBuildingId, String addressDetail, String addressArea, String noWriteRoom, String gbName, BigInteger blockId) {
		BigInteger deliveryAddressAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_delivery_address);
//		String nowTime = dualDao.getNowTime();
		EbuyDeliveryAddress ebuyDeliveryAddress = new EbuyDeliveryAddress();
		ebuyDeliveryAddress.setId(deliveryAddressAddId);
		ebuyDeliveryAddress.setIsdefault(isDefault);
		ebuyDeliveryAddress.setPeopleName(userName);
		ebuyDeliveryAddress.setPhone(userPhone);
		ebuyDeliveryAddress.setTargetType(targetType);
		ebuyDeliveryAddress.settUserFId(userId);
//		ebuyDeliveryAddress.setCreateTime(nowTime);
		// 判断目标类型
		if (EbuyDict.DELIVERY_ADDRESS_TYPE.ROOM.compareTo(targetType) == 0) {// 门牌方式
			ebuyDeliveryAddress.settEbuyHandleAddressFId(null);
			ebuyDeliveryAddress.settRoomFId(targetId);// 此时targetId即为roomId
			{// 判断该收货地址是否已被添加
				EbuyDeliveryAddress ebuyDeliveryAddressQry = new EbuyDeliveryAddress();
				ebuyDeliveryAddressQry.setTargetType(targetType);
				ebuyDeliveryAddressQry.settUserFId(userId);
				ebuyDeliveryAddressQry.settRoomFId(targetId);
				int countRes = ebuyDeliveryAddressBaseDao.selectEbuyDeliveryAddressCount(MapConverter.toMap(ebuyDeliveryAddressQry), false);
				if(countRes>0){
					throw new BusinessRuntimeException("ebuyService.addDeliveryAddress.roomAddress.isExist.failed");
				}
			}
		} else if (EbuyDict.DELIVERY_ADDRESS_TYPE.HANDLE_ADDRESS
				.compareTo(targetType) == 0) {// 手动录入
			BigInteger addressHandleAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_handle_address);
			EbuyHandleAddress ebuyHandleAddress = new EbuyHandleAddress();
			ebuyHandleAddress.setId(addressHandleAddId);
			ebuyHandleAddress.setBlockId(blockId);
			ebuyHandleAddress.setGbName(gbName);
			// 判断是直接输入还是依赖于groupBuilding
			if (groupBuildingId == null) {
				ebuyHandleAddress.setAddressArea(addressArea == null ? "" : addressArea);
				ebuyHandleAddress.setAddressDetail(addressDetail);
			} else {
				ebuyHandleAddress.settGroupBuildingFId(groupBuildingId);
				ebuyHandleAddress.setAddressDetail(addressDetail);
			}
			// 新增手动输入地址
			int res = ebuyHandleAddressBaseDao.insertEbuyHandleAddress(ebuyHandleAddress);
			if (res <= 0) {
				throw new BusinessRuntimeException("ebuyService.addDeliveryAddress.insertEbuyHandleAddress.error");
			}
			ebuyDeliveryAddress.settEbuyHandleAddressFId(addressHandleAddId);
			if(!"yes".equals(noWriteRoom)){
				ebuyDeliveryAddress.settRoomFId(null);
			}
			{// 判断是否已经添加对应收货地址
				EbuyDeliveryAddress ebuyDeliveryAddressQry = new EbuyDeliveryAddress();
				ebuyDeliveryAddressQry.setTargetType(targetType);
				ebuyDeliveryAddressQry.settUserFId(userId);
				ebuyDeliveryAddressQry.settEbuyHandleAddressFId(addressHandleAddId);
				int countRes = ebuyDeliveryAddressBaseDao.selectEbuyDeliveryAddressCount(MapConverter.toMap(ebuyDeliveryAddressQry), false);
				if(countRes>0){
					throw new BusinessRuntimeException("ebuyService.addDeliveryAddress.handleAddress.isExist.failed");
				}
			}
		} else {
			throw new ValidateRuntimeException("ebuy.addDeliveryAddress.targetType.unkonwn.error");
		}
		
		// 新增收货地址
		int res = ebuyDeliveryAddressBaseDao.insertEbuyDeliveryAddress(ebuyDeliveryAddress);
		if (res <= 0) {
			throw new BusinessRuntimeException("ebuyService.addDeliveryAddress.insertEbuyDeliveryAddress.error");
		}
		// 设置默认收货地址
		if (isDefault != null && EbuyDict.EbuyDeliveryAddress_ISDEFAULT.TRUE.compareTo(isDefault) == 0) {
			ebuyDao.setDefaultDeliveryAddress(deliveryAddressAddId, userId);
		}
		// 查询返回最新的
		return getDeliveryAddressDetail(deliveryAddressAddId, userId);
	}

	@Override
	public EbuyDeliveryAddressEntity updateDeliveryAddress(BigInteger deliveryAddressId, BigInteger userId, Integer targetType,
			String userName, String userPhone, Integer isDefault, BigInteger targetId, BigInteger groupBuildingId,
			String addressDetail, String addressArea, String noWriteRoom, String gbName, BigInteger blockId) {
//		String nowTime = dualDao.getNowTime();
		EbuyDeliveryAddress ebuyDeliveryAddress = new EbuyDeliveryAddress();
		ebuyDeliveryAddress.setId(deliveryAddressId);
		ebuyDeliveryAddress.setIsdefault(isDefault);
		ebuyDeliveryAddress.setPeopleName(userName);
		ebuyDeliveryAddress.setPhone(userPhone);
		ebuyDeliveryAddress.setTargetType(targetType);
		ebuyDeliveryAddress.settUserFId(userId);
//		ebuyDeliveryAddress.setUpdateTime(nowTime);
		// 判断目标类型
		if (EbuyDict.DELIVERY_ADDRESS_TYPE.ROOM.compareTo(targetType) == 0) {// 门牌方式
			ebuyDeliveryAddress.settEbuyHandleAddressFId(null);
			ebuyDeliveryAddress.settRoomFId(targetId);// 此时targetId即为roomId
		} else if (EbuyDict.DELIVERY_ADDRESS_TYPE.HANDLE_ADDRESS
				.compareTo(targetType) == 0) {// 手动录入
			// 获取查询对应记录的Id
			EbuyDeliveryAddress ebuyDeliveryAddressOld = ebuyDeliveryAddressBaseDao
					.selectEbuyDeliveryAddressBySeqId(deliveryAddressId);
			BigInteger addressHandleUpdId = ebuyDeliveryAddressOld.gettEbuyHandleAddressFId();
			EbuyHandleAddress ebuyHandleAddress = new EbuyHandleAddress();
			ebuyHandleAddress.setGbName(gbName);
			ebuyHandleAddress.setBlockId(blockId);
			// 判断是直接输入还是依赖于groubBuilding
			if (groupBuildingId == null) {
				ebuyHandleAddress.setAddressArea(addressArea == null ? "" : addressArea);
				ebuyHandleAddress.setAddressDetail(addressDetail);
			} else {
				ebuyHandleAddress.settGroupBuildingFId(groupBuildingId);
				ebuyHandleAddress.setAddressDetail(addressDetail);
			}
			if (addressHandleUpdId == null) {// 新增手动输入地址
				addressHandleUpdId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_handle_address);
				ebuyHandleAddress.setId(addressHandleUpdId);
				int res = ebuyHandleAddressBaseDao.insertEbuyHandleAddress(ebuyHandleAddress);
				if (res <= 0) {
					throw new BusinessRuntimeException("ebuyService.updateDeliveryAddress.insertEbuyHandleAddress.error");
				}
			} else {// 更新手动输入地址
				ebuyHandleAddress.setId(addressHandleUpdId);
				int res = ebuyHandleAddressBaseDao.updateEbuyHandleAddress(ebuyHandleAddress);
				if (res <= 0) {
					throw new BusinessRuntimeException("ebuyService.updateDeliveryAddress.updateEbuyHandleAddress.error");
				}
				if (StringUtils.isEmpty(addressArea)) {
					ebuyDao.updEbuyHandlerAdressAreaNull(addressHandleUpdId);
				}
			}
			ebuyDeliveryAddress.settEbuyHandleAddressFId(addressHandleUpdId);
			if(!"yes".equals(noWriteRoom)){
				ebuyDeliveryAddress.settRoomFId(null);
			}
		} else {
			throw new ValidateRuntimeException("ebuy.updateDeliveryAddress.targetType.unkonwn.error");
		}
		// 更新收货地址
		int res = ebuyDeliveryAddressBaseDao.updateEbuyDeliveryAddress(ebuyDeliveryAddress);
		if (res <= 0) {
			throw new BusinessRuntimeException("ebuyService.updateDeliveryAddress.updateEbuyDeliveryAddress.error");
		}
		// 设置默认收货地址
		if (isDefault != null && EbuyDict.EbuyDeliveryAddress_ISDEFAULT.TRUE.compareTo(isDefault) == 0) {
			ebuyDao.setDefaultDeliveryAddress(deliveryAddressId, userId);
		}
		// 查询返回最新的
		EbuyDeliveryAddressEntity newRes= getDeliveryAddressDetail(deliveryAddressId, userId);
		return newRes;
	}

	@Override
	public EbuyDeliveryAddressEntity getDeliveryAddressDetail(BigInteger deliveryAddressId, BigInteger userId) {
		return ebuyDao.selectEbuyDeliveryAddressDetail(deliveryAddressId, userId);
	}

	@Override
	public void deleteDeliveryAddress(BigInteger deliveryAddressId, BigInteger userId) {
		// 查询当前记录详情
		EbuyDeliveryAddress deliveryAddress = ebuyDeliveryAddressBaseDao.selectEbuyDeliveryAddressBySeqId(deliveryAddressId);
		// 判断是否为默认收货地址
		if (deliveryAddress.getIsdefault() != null
				&& EbuyDict.EbuyDeliveryAddress_ISDEFAULT.TRUE.compareTo(deliveryAddress.getIsdefault()) == 0) {
			throw new BusinessRuntimeException("ebuyService.deleteDeliveryAddress.isDefault.error");
		}
		
//		BigInteger commHistoryAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_comm_history);
//		CommHistory commHistory = new CommHistory();
//		commHistory.setId(commHistoryAddId);
//		commHistory.setActionType(DictConstants.CommonHistory_ACTION_TYPE.DELETE);
//		commHistory.setContent(JSON.toJSONString(deliveryAddress));
//		commHistory.setRecordId(deliveryAddress.getId());
//		commHistory.setTableName(SEQConstants.t_ebuy_delivery_address);
		// // 创建历史备份
//		commHistoryBaseDao.insertCommHistory(commHistory);
		// 执行删除
		int res = ebuyDao.deleteEbuyDeliveryAddressByIdUserIdLogic(deliveryAddressId, userId);
		if (res <= 0) {
			throw new BusinessRuntimeException("ebuyService.deleteDeliveryAddress.deleteEbuyDeliveryAddress.error");
		}
	}

	@Override
	public void setDefaultDeliveryAddress(BigInteger deliveryAddressId, BigInteger userId) {
		ebuyDao.setDefaultDeliveryAddress(deliveryAddressId, userId);
	}

	@Override
	public EbuyDeliveryAddressEntity qryDeliveryAddressDetailDefault(BigInteger userId) {
		EbuyDeliveryAddressEntity resEntity = ebuyDao.selectEbuyReceiveAddressDefault(userId);
		/*if(resEntity==null){
			// 检查是否都是非默认门牌
			EbuyDeliveryAddress ebuyDeliveryAddressQry = new EbuyDeliveryAddress();
			ebuyDeliveryAddressQry.settUserFId(userId);
			List<EbuyDeliveryAddress> notDefaultList = ebuyDeliveryAddressBaseDao.selectEbuyDeliveryAddressByCondition(MapConverter.toMap(ebuyDeliveryAddressQry), false);
			if(notDefaultList!=null&&notDefaultList.size()>0){
				setDefaultDeliveryAddress(notDefaultList.get(0).getId(), userId);
			}else{
				copyDefaultRoom2DeliveryAddress(userId);
			}
			resEntity = ebuyDao.selectEbuyReceiveAddressDefault(userId);
		}*/
		return resEntity;
	}

	@Override
	public void copyDefaultRoom2DeliveryAddress(BigInteger userId) {
		// 查询默认门牌
		RoomEntity roomEntity = commonRoomService.getDefaultRoomInfo(userId);
		if(roomEntity==null||roomEntity.checkIsEmptyRoom()){
			throw new BusinessRuntimeException("ebuyService.copyDefaultRoom2DeliveryAddress.getDefaultRoomInfo.isEmpty.error");
		}else{
			User user = userBaseDao.selectUserBySeqId(userId);
			// 新增默认收货地址
			Integer targetType = EbuyDict.DELIVERY_ADDRESS_TYPE.ROOM;
			String userName = user.getRealName();
			String userPhone = user.getMobile();
			Integer isDefault = EbuyDict.EbuyDeliveryAddress_ISDEFAULT.TRUE;
			BigInteger targetId = roomEntity.getId();
			String gbName = roomEntity.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().getName();
			BigInteger blockId = roomEntity.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().getAddressBlockEntity().getId();
			addDeliveryAddress(userId, targetType, userName, userPhone, isDefault, targetId, null, null, null, null, gbName, blockId);
		}
	}

	@Override
	public EbuyOrderEntity getEbuyOrderEntityDetail(BigInteger userId, BigInteger orderId,Integer pointType,Long wlAppType) {
		// 返回当前订单详情
		EbuyOrderEntity currEbuyOrderEntity=ebuyDao.getEbuyOrderEntityDetail(userId, orderId,pointType,wlAppType);
		return currEbuyOrderEntity;
	}

	@Override
	public List<EbuyOrderEntity> getOrderList(BigInteger userId,PageModel pageModel,Integer pointType,Long wlAppType) {
		return ebuyDao.selectOrderList(userId, pageModel,pointType,wlAppType);
	}

	@Override
	public List<EbuyOrderEntity> getOrderList(BigInteger userId, List<Integer> _YesorderStatus,
			List<Integer> _NoorderStatus, PageModel pageModel,Integer pointType,Long wlAppType) {
		return ebuyDao.selectOrderList(userId, _YesorderStatus, _NoorderStatus, pageModel,pointType,wlAppType);
	}

	@Override
	public void updateOrder2Delete(BigInteger userId, BigInteger orderId,Integer pointType,Long wlAppType) {
		// 查询当前订单状态
		EbuyOrder ebuyOrderDetail =ebuyOrderBaseDao.selectEbuyOrderBySeqId(orderId);
		if (EbuyDict.EbuyOrder_Status.DaiFuKuan.compareTo(ebuyOrderDetail
				.getStatus()) == 0) {// 若为代付款，则可以删除
			{
				ebuyDao.deleteEbuyDeliveryOrderAndProductLogicByOrderId(orderId, userId);
			}
			// 退回粮票、优惠券信息
			Long totalCouponAmount=commonEbuyService.backHbByOrderId(userId, orderId);
			{// 释放库存（f_left_count）
				releaseProductLeftCountByOrderId(orderId, userId,pointType,wlAppType);
			}
			{//消费券返还
				EbuyOrderHasCoupon orderHasCoupon = new EbuyOrderHasCoupon();
				orderHasCoupon.settEbuyOrderFId(orderId);
				List<EbuyOrderHasCoupon> coupons = ebuyOrderHasCouponBaseService
						.getEbuyOrderHasCouponByCondition(MapConverter.toMap(orderHasCoupon));
				if (!(coupons == null || coupons.size() == 0)) {
					List<BigInteger> userCouponIds = new ArrayList<BigInteger>();
					for (EbuyOrderHasCoupon coupon : coupons) {
						userCouponIds.add(coupon.gettUserCouponFId());
					}
					//先返还有效，再过期处理过期券
					userCouponService.updateUserCouponValidBatchByIds(userCouponIds);
					Map param = new HashMap();
					param.put("userId", userId);
					userCouponService.updateUserCouponInvalidBatch(param);
				}
			}
			{// 标记订单为已删除
				EbuyOrder ebuyOrder = new EbuyOrder();
				ebuyOrder.setId(orderId);
				ebuyOrder.setBuyerId(userId);
				ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.YiShanChu);
				//syl-upd 2014-7-14 14:26:05
				if (ebuyOrderDetail.getTotalCouponAmount() != null
						&& totalCouponAmount != null) {// 更新订单金额
					ebuyOrder.setTotalCouponAmount(ebuyOrderDetail.getTotalCouponAmount()-totalCouponAmount);
					ebuyOrder.setAmount(ebuyOrderDetail.getAmount()+totalCouponAmount);
				}else if(ebuyOrderDetail.getTotalCouponAmount()!=null&&totalCouponAmount==null||ebuyOrderDetail.getTotalCouponAmount()==null&&totalCouponAmount!=null){
//					throw new BusinessRuntimeException(errorCode);
				}
				
				int res = ebuyOrderBaseDao.updateEbuyOrder(ebuyOrder);
				if(res<=0){
					throw new BusinessRuntimeException("ebuyService.updateOrder2Cancel.failed");
				}
			}
		}else{
			throw new BusinessRuntimeException("ebuyService.updateOrder2Cancel.currOrder.statusIsNotDaiFuKuan.error");
		}
	}
	@Override
	public void updateOrder2Received(BigInteger userId, BigInteger orderId,Integer pointType,Long wlAppType) {
		// 查询当前订单状态
		EbuyOrder ebuyOrderDetail =ebuyOrderBaseDao.selectEbuyOrderBySeqId(orderId);
		if (EbuyDict.EbuyOrder_Status.DaiShouHuo.compareTo(ebuyOrderDetail.getStatus()) == 0 // 若为已发货，则可以确认收货
				|| EbuyDict.EbuyOrder_Status.DaiFaHuo.intValue() == ebuyOrderDetail.getStatus()) {//待发货，也可以确认收货 Added by wenfq 20170316
			EbuyOrder ebuyOrder = new EbuyOrder();
			ebuyOrder.setId(orderId);
			ebuyOrder.setBuyerId(userId);
			ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.DaiPingJia);
			int res = ebuyOrderBaseDao.updateEbuyOrder(ebuyOrder);
			if(res<=0){
				throw new BusinessRuntimeException("ebuyService.updateOrder2Received.failed");
			}
			{// 增加已售数量
				addProductSellCountByOrderId(orderId, userId,pointType,wlAppType);
			}
		}else{
			throw new BusinessRuntimeException("ebuyService.updateOrder2Received.currOrder.statusIsNotDaiShouHuo.error");
		}
	}

	@Override
	public synchronized EbuyBuyCarEntityFamilyGroup add2BuyCar(BigInteger userId,Integer userType, BigInteger productId, Long productQty,Integer pointType,Long wlAppType,BigInteger productSpecId) {
		// 1.移除购物车的商品 增强处理,可以移除家庭下的购物车数据
		if(productQty<0){
			Long productQtyTmp = -productQty;
			// 查询家庭的所有购物车商品数量列表 当前用户的排序在前
			List<EbuyBuyCarHasTEbuyProduct> currEbuyCarProdList = ebuyDao.selectEbuyBuyCarHasTEbuyProductListByProductIdFamily(userId, userType, productId, pointType,wlAppType,productSpecId);
			Long collectCount = 0L;
			List<EbuyBuyCarHasTEbuyProduct> toUpdateEbuyCarProdList = new ArrayList<EbuyBuyCarHasTEbuyProduct>();// 待更新的商品购买信息
			Map<EbuyBuyCarHasTEbuyProduct, Long> ebuyCarProdMovedCount = new HashMap<EbuyBuyCarHasTEbuyProduct, Long>();// 每个商品对应移除的数量
																														// key为toUpdatePayRedEnvelopeList的取值
			for(EbuyBuyCarHasTEbuyProduct tmpPayRed:currEbuyCarProdList){
				EbuyBuyCarHasTEbuyProduct updEbuyCarProd = new EbuyBuyCarHasTEbuyProduct();
				toUpdateEbuyCarProdList.add(updEbuyCarProd);
				
				updEbuyCarProd.setId(tmpPayRed.getId());
				if(collectCount+tmpPayRed.getProductQty()>=productQtyTmp){
					if (collectCount + tmpPayRed.getProductQty() > productQtyTmp) {// 当前移除了一部分商品
						Long partMoney = productQtyTmp-collectCount;
						collectCount+=partMoney;
						updEbuyCarProd.setProductQty(tmpPayRed.getProductQty()-partMoney);
						ebuyCarProdMovedCount.put(updEbuyCarProd, partMoney);
					} else {// 当前移除数量刚好用完
						collectCount+=tmpPayRed.getProductQty();
						updEbuyCarProd.setProductQty(0L);// 购买数据置0
						ebuyCarProdMovedCount.put(updEbuyCarProd, tmpPayRed.getProductQty());
					}
					break;
				} else {// 还不够
					collectCount+=tmpPayRed.getProductQty();
					updEbuyCarProd.setProductQty(0L);// 购买数据置0
					ebuyCarProdMovedCount.put(updEbuyCarProd, tmpPayRed.getProductQty());
				}
			}
			if(collectCount<productQtyTmp){
				// 不够移除 移除全部购买信息
			} else if (collectCount > productQtyTmp) {// 算法统计错误
				throw new BusinessRuntimeException("EbuyService.add2BuyCar.collectCount.error",new Object[]{PriceUtil.div100(collectCount)});
			}
			// 更新对应的购买信息
			int updRes = ebuyBuyCarHasTEbuyProductBaseDao.updateEbuyBuyCarHasTEbuyProductBatch(toUpdateEbuyCarProdList);
			if(updRes<=0){
				throw new BusinessRuntimeException("EbuyService.add2BuyCar.updateEbuyBuyCarHasTEbuyProductBatch.failed");
			}
		} else {// 2.加入到购物车,判断购物车是否已经存在该商品
			Long haveBuyCount = ebuyDao.selectHaveBuyCountByProductIdFamily(userId, userType, productId, pointType,wlAppType);
			if(haveBuyCount==null){haveBuyCount=0L;}
			checkLeftCount(productId, productQty+haveBuyCount,productSpecId, pointType,wlAppType);
			// 查询购物车Id
			BigInteger buyCarId = commonEbuyService.checkAndCreateEbuyBuyCar(userId, userType);
			EbuyBuyCarHasTEbuyProduct qryEbuyBuyCarHasTEbuyProduct = new EbuyBuyCarHasTEbuyProduct();
			qryEbuyBuyCarHasTEbuyProduct.settEbuyBuyCarFId(buyCarId);
			qryEbuyBuyCarHasTEbuyProduct.settEbuyProductFId(productId);
			qryEbuyBuyCarHasTEbuyProduct.settEbuyProductSpecFId(productSpecId);
			List<EbuyBuyCarHasTEbuyProduct> prodList = ebuyDao.selectEbuyBuyCarHasTEbuyProductByBuyCarId(buyCarId, productId, productSpecId, pointType,wlAppType);
			if(prodList==null||prodList.size()<=0){
				if(productQty<0){
					productQty = 0L;
//					throw new BusinessRuntimeException("EbuyService.add2BuyCar.addPrdFirst.productQty.lessthan0.error");
				}else{
					checkLeftCount(productId, productQty,productSpecId, pointType,wlAppType);
				}
				// 新增商品
				BigInteger addEbuyBuyCarHasTEbuyProductId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_buy_car_has_t_ebuy_product);
				EbuyBuyCarHasTEbuyProduct addEbuyBuyCarHasTEbuyProduct = new EbuyBuyCarHasTEbuyProduct();
				addEbuyBuyCarHasTEbuyProduct.setCreateTime(dualDao.getNowTime());
				addEbuyBuyCarHasTEbuyProduct.setId(addEbuyBuyCarHasTEbuyProductId);
				addEbuyBuyCarHasTEbuyProduct.setProductQty(productQty);
				addEbuyBuyCarHasTEbuyProduct.settEbuyBuyCarFId(buyCarId);
				addEbuyBuyCarHasTEbuyProduct.settEbuyProductFId(productId);
				addEbuyBuyCarHasTEbuyProduct.settEbuyProductSpecFId(productSpecId);
				int res = ebuyBuyCarHasTEbuyProductBaseDao.insertEbuyBuyCarHasTEbuyProduct(addEbuyBuyCarHasTEbuyProduct);
				if(res<=0){
					throw new BusinessRuntimeException("EbuyService.add2BuyCar.insertEbuyBuyCarHasTEbuyProduct.failed");
				}
			}else if(prodList.size()>=1){
				EbuyBuyCarHasTEbuyProduct index0Data = prodList.get(0);
				Long currProdBuyCount=index0Data.getProductQty()+productQty;
				if(prodList.size()>1){
					List<BigInteger> toDelIdList = new ArrayList<BigInteger>();
					for(int i=1;i<prodList.size();i++){
						EbuyBuyCarHasTEbuyProduct tmpIData = prodList.get(i);
						currProdBuyCount += tmpIData.getProductQty();// 将后续数据合并
						toDelIdList.add(tmpIData.getId());
					}
					ebuyBuyCarHasTEbuyProductBaseDao.deleteEbuyBuyCarHasTEbuyProductLogicBatch(toDelIdList);
				}
				{// 处理第一个
					if(currProdBuyCount<0){
						currProdBuyCount=0L;
					}else{
						checkLeftCount(productId, currProdBuyCount,productSpecId, pointType,wlAppType);
					}
					// 更新商品
					EbuyBuyCarHasTEbuyProduct updEbuyBuyCarHasTEbuyProduct = new EbuyBuyCarHasTEbuyProduct();
					updEbuyBuyCarHasTEbuyProduct.setId(index0Data.getId());
					updEbuyBuyCarHasTEbuyProduct.setProductQty(currProdBuyCount);
					int res = ebuyBuyCarHasTEbuyProductBaseDao.updateEbuyBuyCarHasTEbuyProduct(updEbuyBuyCarHasTEbuyProduct);
					if(res<=0){
						throw new BusinessRuntimeException("EbuyService.add2BuyCar.updateEbuyBuyCarHasTEbuyProduct.failed");
					}
				}
//				throw new BusinessRuntimeException("EbuyService.add2BuyCar.prodList.count.morethan1.error");
			}/*else{}*/
			
		}
/*	家庭购物没有了，此处可以优化注释掉 added by wenfq 2017-07-11
		{// 添加红点提醒
			List<BasicSourceEntity> sourceList = null;
			FutureTask<Boolean> task = new FutureTask<Boolean>(new RedpointAddRunnableForFamily(redpointService, userId, RedpointConstant.RedpointContent_ModelCode.FML_SHOPPING, sourceList));
			new Thread(task).start();
		}*/
		// 3.查询当前购物车信息
		//return getBuyCarDetail(userId,userType,pointType,wlAppType);   added by wenfq 优化代码，注释此段查询，因为他非常慢 2017-07-11
		return null;
	}
//	public EbuyBuyCarEntity add2BuyCarTmpUser(String deviceId,Long deviceType, BigInteger productId, Long productQty) {
	// //获取临时用户信息
//		UserTmp userTmp = commonDeviceService.checkAndCreate(deviceId, deviceType);
	// //1.查询购物车Id
//		BigInteger buyCarId = null;
//		EbuyBuyCarTmp qryEbuyBuyCarTmp = new EbuyBuyCarTmp();
//		qryEbuyBuyCarTmp.settUserTmpFId(userTmp.getId());
//		List<EbuyBuyCarTmp> currEbuyBuyCarTmpList = ebuyBuyCarTmpBaseDao.selectEbuyBuyCarTmpByCondition(MapConverter.toMap(qryEbuyBuyCarTmp), false);
	// if(currEbuyBuyCarTmpList==null||currEbuyBuyCarTmpList.size()<=0){//是否存在
	// //不存在则新增
//			BigInteger addEbuyBuyCarId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_buy_car_tmp);
//			EbuyBuyCarTmp addEbuyBuyCarTmp = new EbuyBuyCarTmp();
//			addEbuyBuyCarTmp.setId(addEbuyBuyCarId);
//			addEbuyBuyCarTmp.setCreateTime(dualDao.getNowTime());
//			addEbuyBuyCarTmp.settUserTmpFId(userTmp.getId());
//			int res = ebuyBuyCarTmpBaseDao.insertEbuyBuyCarTmp(addEbuyBuyCarTmp);
//			if(res<=0){
//				throw new BusinessRuntimeException("EbuyService.add2BuyCarTmpUser.insertEbuyBuyCarTmp.failed");
//			}
//			buyCarId = addEbuyBuyCarId;
//		}else if(currEbuyBuyCarTmpList.size()>1){
//			throw new BusinessRuntimeException("EbuyService.add2BuyCarTmpUser.currEbuyBuyCarList.count.morethan1.error");
//		}else{
//			buyCarId = currEbuyBuyCarTmpList.get(0).getId();
//		}
//		
	// //2.判断购物车是否已经存在该商品
//		{
//			EbuyBuyCarTmpHasTEbuyProduct qryEbuyBuyCarTmpHasTEbuyProduct = new EbuyBuyCarTmpHasTEbuyProduct();
//			qryEbuyBuyCarTmpHasTEbuyProduct.settEbuyBuyCarTmpFId(buyCarId);
//			qryEbuyBuyCarTmpHasTEbuyProduct.settEbuyProductFId(productId);
//			List<EbuyBuyCarTmpHasTEbuyProduct> prodList = ebuyBuyCarTmpHasTEbuyProductBaseDao.selectEbuyBuyCarTmpHasTEbuyProductByCondition(MapConverter.toMap(qryEbuyBuyCarTmpHasTEbuyProduct), false);
//			if(prodList==null||prodList.size()<=0){
//				if(productQty<0){
//					productQty = 0L;
////					throw new BusinessRuntimeException("EbuyService.add2BuyCar.addPrdFirst.productQty.lessthan0.error");
//				}else{
//					checkLeftCount(productId, productQty);
//				}
	// //新增商品
//				BigInteger addEbuyBuyCarTmpHasTEbuyProductId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_buy_car_tmp_has_t_ebuy_product);
//				EbuyBuyCarTmpHasTEbuyProduct addEbuyBuyCarTmpHasTEbuyProduct = new EbuyBuyCarTmpHasTEbuyProduct();
//				addEbuyBuyCarTmpHasTEbuyProduct.setCreateTime(dualDao.getNowTime());
//				addEbuyBuyCarTmpHasTEbuyProduct.setId(addEbuyBuyCarTmpHasTEbuyProductId);
//				addEbuyBuyCarTmpHasTEbuyProduct.setProductQty(productQty);
//				addEbuyBuyCarTmpHasTEbuyProduct.settEbuyBuyCarTmpFId(buyCarId);
//				addEbuyBuyCarTmpHasTEbuyProduct.settEbuyProductFId(productId);
//				int res = ebuyBuyCarTmpHasTEbuyProductBaseDao.insertEbuyBuyCarTmpHasTEbuyProduct(addEbuyBuyCarTmpHasTEbuyProduct);
//				if(res<=0){
//					throw new BusinessRuntimeException("EbuyService.add2BuyCarTmpUser.insertEbuyBuyCarTmpHasTEbuyProduct.failed");
//				}
//			}else if(prodList.size()>1){
//				throw new BusinessRuntimeException("EbuyService.add2BuyCarTmpUser.prodList.count.morethan1.error");
//			}else{
//				Long currProdBuyCount=prodList.get(0).getProductQty()+productQty;
//				if(currProdBuyCount<0){
//					currProdBuyCount=0L;
//				}else{
//					checkLeftCount(productId, currProdBuyCount);
//				}
	// //更新商品
//				EbuyBuyCarTmpHasTEbuyProduct updEbuyBuyCarTmpHasTEbuyProduct = new EbuyBuyCarTmpHasTEbuyProduct();
//				updEbuyBuyCarTmpHasTEbuyProduct.setId(prodList.get(0).getId());
//				updEbuyBuyCarTmpHasTEbuyProduct.setProductQty(currProdBuyCount);
//				int res = ebuyBuyCarTmpHasTEbuyProductBaseDao.updateEbuyBuyCarTmpHasTEbuyProduct(updEbuyBuyCarTmpHasTEbuyProduct);
//				if(res<=0){
//					throw new BusinessRuntimeException("EbuyService.add2BuyCarTmpUser.updateEbuyBuyCarTmpHasTEbuyProduct.failed");
//				}
//			}
//			
//		}
	// //3.查询当前购物车信息
//		EbuyBuyCarEntity ebuyBuyCarEntity = getBuyCarDetail(userId);
//		return ebuyBuyCarEntity;
//	}

	@Override
	public EbuyBuyCarEntityFamilyGroup getBuyCarDetail(BigInteger userId,Integer userType,Integer pointType,Long wlAppType) {
		// ebuyDao.deleteZeroCountBuyDataEbuyCarHasProductLogic(userId,
		// userType,pointType,wlAppType);// 移除购物车购买数量为零的商品 syl-del 2015-4-20
		// 09:58:49
		EbuyBuyCarEntityFamilyGroup resEbuyBuyCarEntityFamilyGroup =  new EbuyBuyCarEntityFamilyGroup(ebuyDao.selectBuyCarDetail(userId,userType,pointType,wlAppType));
		return resEbuyBuyCarEntityFamilyGroup;
	}
	
	@Override
	public EbuyBuyCarEntityFamilyGroup getBuyCarAllDetail(BigInteger userId,Integer userType,Integer pointType,Long wlAppType) {
//		return new EbuyBuyCarEntityFamilyGroup(ebuyDao.selectBuyCarAllDetail(userId,userType,pointType));
		return new EbuyBuyCarEntityFamilyGroup(ebuyDao.selectBuyCarDetail(userId,userType,pointType,wlAppType));
	}
	
	@Override
	public EbuyBuyCarEntityFamilyGroup getBuyCarPartDetailByProdIds(BigInteger userId,Integer userType, List<ProductIdQtyEntity> productIdQtyList,Integer pointType,Long wlAppType) {
		return new EbuyBuyCarEntityFamilyGroup(ebuyDao.selectBuyCarPartDetailByProdIds(userId,userType, productIdQtyList,pointType,wlAppType));
	}
	
	@Override
	public EbuyBuyCarEntityFamilyGroup removeProdFromBuyCarOld(BigInteger userId,Integer userType,BigInteger productId,Integer pointType,Long wlAppType) {
		int res = ebuyDao.removeProdFromBuyCarLogicOld(userId,userType,productId,pointType,wlAppType);
		if(res<=0){
			throw new BusinessRuntimeException("EbuyService.removeProdFromBuyCarOld.failed");
		}
		return getBuyCarDetail(userId,userType,pointType,wlAppType);
	}
	@Override
	public EbuyBuyCarEntityFamilyGroup removeProdFromBuyCar(BigInteger userId,Integer userType,BigInteger productId,Integer pointType,Long wlAppType,BigInteger productSpecId) {
		int res = ebuyDao.removeProdFromBuyCarLogic(userId,userType,productId,pointType,wlAppType,productSpecId);
		if(res<=0){
			throw new BusinessRuntimeException("EbuyService.removeProdFromBuyCar.failed");
		}
		return getBuyCarDetail(userId,userType,pointType,wlAppType);
	}
	@Override
	public EbuyBuyCarEntityFamilyGroup removeProdFromBuyCarBatchLogicOld(BigInteger userId,Integer userType, List<BigInteger> productIdList,Integer pointType,Long wlAppType){
		int res = ebuyDao.removeProdFromBuyCarBatchLogicOld(userId,userType,productIdList,pointType,wlAppType);
		if(res<=0){
			throw new BusinessRuntimeException("EbuyService.removeProdFromBuyCarBatchLogicOld.failed");
		}
		return getBuyCarDetail(userId,userType,pointType,wlAppType);
	}
	@Override
	public EbuyBuyCarEntityFamilyGroup removeProdFromBuyCarBatchLogic(BigInteger userId,Integer userType, List<ProductIdQtyEntity> productIdQtyList,Integer pointType,Long wlAppType){
		int res = ebuyDao.removeProdFromBuyCarBatchLogic(userId,userType,productIdQtyList,pointType,wlAppType);
		if(res<=0){
			throw new BusinessRuntimeException("EbuyService.removeProdFromBuyCarBatchLogic.failed");
		}
		return getBuyCarDetail(userId,userType,pointType,wlAppType);
	}
	
	/**
	 * 生成订单时移除购物车的处理
	 */
	private void removeProdFromBuyCar_ForEbuyOrder(BigInteger userId,Integer userType, List<ProductIdQtyEntity> productIdQtyList,Integer pointType,Long wlAppType){
		int res = ebuyDao.removeProdFromBuyCarBatchLogic(userId,userType,productIdQtyList,pointType,wlAppType);
		if(res<=0){
			// 这时抛出“您的家人已购买咯~”提示，由于现在没有家庭购物概念了，所以不再抛出该异常 added by wenfq 2017-09-12
			// throw new BusinessRuntimeException("EbuyService.removeProdFromBuyCar.forEbuyOrder.failed");
		}
	}
	
	@Override
	public EbuyBuyCarEntityFamilyGroup removeProdFromBuyCarAllLogic(BigInteger userId,Integer userType,Integer pointType,Long wlAppType){
		ebuyDao.removeProdFromBuyCarAllLogic(userId,userType,pointType,wlAppType);
		return getBuyCarDetail(userId,userType,pointType,wlAppType);
	}
	
	@Override
	public int removeProdFromBuyCarNewUser(BigInteger userId,Integer userType,Integer pointType,Long wlAppType) {
		return ebuyDao.removeProdFromBuyCarNewUser(userId, userType, pointType, wlAppType);
	}
	
	@Override
	public Integer getBuyCarProductTotalCount(BigInteger userId,Integer userType,Integer pointType,Long wlAppType) {
		List<Integer> totalCountList = ebuyDao.selectBuyCarProductTotalCount(userId,userType,pointType,wlAppType);
		Integer resCount = 0;
		for(Integer tmpInt:totalCountList){
			if(tmpInt!=null){resCount+=tmpInt;}
		}
		return resCount;
	}
	
	/**
	 * 校验库存
	 * 
	 * @param productId
	 * @param productQty
	 */
	private Long checkLeftCount(BigInteger productId,Long productQty,BigInteger productSpecId,Integer pointType,Long wlAppType){
		Long prodId = ebuyNewDao.getProductIdByShelfId(productId.longValue());
		EbuyProduct product = ebuyProductBaseDao.selectEbuyProductBySeqId(BigInteger.valueOf(prodId.longValue()));
		if(product==null){
			throw new BusinessRuntimeException("ebuyService.checkLeftCount.product.isNull");
		}// 商品是否下架

		
		{
		/**
		 * 如果待添加的商品正在限时促销中，则添加的库存不能超过限时促销的库存
		 * Added by wenfq 2017-08-31
		 */
			BigInteger limitBuyLeftCount = ebuyDao.selectLimitBuyLeftCount(productId, productSpecId, pointType,
					wlAppType);

			if (limitBuyLeftCount != null && limitBuyLeftCount.compareTo(BigInteger.ZERO) > 0
					&& productQty.compareTo(limitBuyLeftCount.longValue()) > 0) {
				throw new BusinessRuntimeException("ebuyService.checkLeftCount.not.enough",
						new Object[] { product.getName(), limitBuyLeftCount });
			}
		}
		
		BigInteger leftCount = ebuyDao.selectProductLeftCount(productId, productSpecId,pointType,wlAppType);
		if(leftCount==null||productQty.compareTo(leftCount.longValue())>0){
			throw new BusinessRuntimeException("ebuyService.checkLeftCount.not.enough",new Object[]{product.getName(),product.getLeftCount()});
		}
		return leftCount.longValue();
	}
	
//	/**
	// * 校验并锁定商品数量
//	 * @param productId
//	 * @param productQty
//	 */
//	public synchronized void checkLeftCountAndLock(BigInteger productId,Long productQty){
//		Long leftCount = checkLeftCount(productId, productQty);
//		BigInteger resCount = new BigInteger((leftCount-productQty)+"");
//		EbuyProduct updEbuyProduct = new EbuyProduct(); 
//		updEbuyProduct.setId(productId);
//		updEbuyProduct.setLeftCount(resCount);
//		int res = ebuyProductBaseDao.updateEbuyProduct(MapConverter.toMap(updEbuyProduct));
//		if(res<=0){
//			throw new BusinessRuntimeException("ebuyService.checkLeftCountAndLock.lock.failed");
//		}
//	}
//	/**
	// * 释放库存
//	 * @param productId
//	 * @param toReleaseCount
//	 */
//	public synchronized void releaseLeftCount(BigInteger productId,Long toReleaseCount){
//		int res = ebuyDao.releaseProductLeftCount(productId, toReleaseCount);
//		if(res<=0){
//			throw new BusinessRuntimeException("ebuyService.releaseLeftCount.failed");
//		}
//	}

	@Override
	public synchronized int lockProductLeftCountByOrderId(BigInteger orderId, BigInteger buyerId,Integer pointType,Long wlAppType) {
		int res =  ebuyDao.lockProductLeftCountByOrderId(orderId, buyerId,pointType,wlAppType);
		if(res<=0){
			throw new BusinessRuntimeException("ebuyService.lockProductLeftCountByOrderId.failed");
		}
		return res;
	}

	@Override
	public synchronized int releaseProductLeftCountByOrderId(BigInteger orderId, BigInteger buyerId,Integer pointType,Long wlAppType) {
		int res =  ebuyDao.releaseProductLeftCountByOrderId(orderId, buyerId,pointType,wlAppType);
		if(res<=0){
			throw new BusinessRuntimeException("ebuyService.releaseProductLeftCountByOrderId.failed");
		}
		return res;
	}

	@Override
	public synchronized int addProductSellCountByOrderId(BigInteger orderId, BigInteger buyerId,Integer pointType,Long wlAppType) {
		int res =  ebuyDao.addProductSellCountByOrderId(orderId, buyerId,pointType,wlAppType);
		if(res<=0){
			throw new BusinessRuntimeException("ebuyService.addProductSellCountByOrderId.failed");
		}
		return res;
	}

	@Override
	public String fetchAllProductLastUpdTime(String searchKey,BigInteger productTypeId,PageModel pageModel,Integer pointType,Long wlAppType) {
		String res = null;
		if(StringUtils.isEmpty(searchKey)&&pageModel.begin==0){
			res = ebuyDao.selectAllProductLastUpdTime(productTypeId, pageModel, pointType, wlAppType);
		}
		return res;
	}
	
	@Override
	public List<EbuyProductWithCurrProductSpec> getEbuyProductWithCurrProductSpecList(
			List<ProductIdQtyEntity> productIdQtyList,Integer pointType,Long wlAppType) {
		List<EbuyProductWithCurrProductSpec> resList = new ArrayList<EbuyProductWithCurrProductSpec>();
		if(productIdQtyList!=null&&productIdQtyList.size()>0){
			List<BigInteger> productSpecIdList = new ArrayList<BigInteger>();
			List<BigInteger> productIdList = new ArrayList<BigInteger>();
			for(ProductIdQtyEntity tmpProductIdQtyEntity:productIdQtyList){
				if (tmpProductIdQtyEntity.getProductSpecId() != null) {// 率先使用规格
					productSpecIdList.add(tmpProductIdQtyEntity.getProductSpecId());
				}else if(tmpProductIdQtyEntity.getProductId()!=null){
					productIdList.add(tmpProductIdQtyEntity.getProductId());
				}
			}
			// 分别查询对应的商品信息或者规格信息
			List<EbuyProductSpecEntity> ebuyProductSpecEntityList = null;
			List<EbuyProductEntity> ebuyProductList = null;
			if(productSpecIdList!=null&&productSpecIdList.size()>0){
				ebuyProductSpecEntityList = ebuyDao.selectEbuyProductSpecEntityListBySpecIds(productSpecIdList, pointType,wlAppType);
			}
			if(productIdList!=null&&productIdList.size()>0){
				ebuyProductList = ebuyDao.selectEbuyProductListByProdIds(productIdList, pointType,wlAppType);
			}
			// 存入结果数据
			if(ebuyProductSpecEntityList!=null&&ebuyProductSpecEntityList.size()>0){
				for(EbuyProductSpecEntity specEntity:ebuyProductSpecEntityList){
					EbuyProductWithCurrProductSpec tmp = new EbuyProductWithCurrProductSpec(specEntity.getEbuyProduct(), specEntity);
					resList.add(tmp);
				}
			}
			if(ebuyProductList!=null&&ebuyProductList.size()>0){
				for(EbuyProductEntity tmpEbuyProduct:ebuyProductList){
					EbuyProductWithCurrProductSpec tmp = new EbuyProductWithCurrProductSpec(tmpEbuyProduct, null);
					resList.add(tmp);
				}
			}
		}
		// 结果返回
		return resList;
	}
	
	@Override
	public EbuyOrderEntity confirmPhoneChargeOrder(BigInteger userId, BigInteger productId, BigInteger productSpecId,String phoneNum, Integer pointType,Long wlAppType,Integer jfqSubType,Long subChannelId) {
		Long productQty = 1L;
		BigInteger deliveryAddressId = null;
		logger.debug("userId is "+userId+";deliveryAddressId is "+deliveryAddressId+";productId is "+productId+";productSpecId is "+productSpecId+";productQty is "+productQty+";pointType is "+pointType);
		// 1.校验商品信息
		EbuyProductSpec currEbuyProductSpec = null;
		EbuyProductEntity ebuyProductEntity = ebuyDao.selectProductById(productId,pointType,wlAppType);
		{
			if(ebuyProductEntity==null){
				throw new BusinessRuntimeException("ebuyService.confirmOrder.product.notExist");
			}else{
				if(ebuyProductEntity.getStatus().compareTo(EbuyDict.Product_Status.OFF_DOWN)==0){
					throw new BusinessRuntimeException("ebuyService.confirmOrder.product.isDown");
				}
				List<EbuyProductSpec> ebuyProductSpecList = ebuyProductEntity.getEbuyProductSpecList();
				if(ebuyProductSpecList!=null&&ebuyProductSpecList.size()>0){
					for(EbuyProductSpec tmpEbuyProductSpec:ebuyProductSpecList){
						if(tmpEbuyProductSpec.getId().compareTo(productSpecId)==0){
							currEbuyProductSpec = tmpEbuyProductSpec;
							break;
						}
					}
				}
			}
			if(currEbuyProductSpec==null){
				throw new BusinessRuntimeException("ebuyService.confirmOrder.productSpec.notEixst");
			}
		}
		// 2.后续业务处理
		Long hbMoney = null;
		EbuyExtandBuyParam ebuyExtandBuyParam = new EbuyExtandBuyParam();
		ebuyExtandBuyParam.setPhoneNum(phoneNum);
		return submitOrder(userId, deliveryAddressId, null, productId, productQty, productSpecId, ebuyExtandBuyParam, hbMoney, pointType,wlAppType, null, null, null,jfqSubType,subChannelId, null, null);
	}

	@Override
	public EbuyOrderEntity confirmCashCoupon(BigInteger userId, BigInteger productId, BigInteger productSpecId,
			Integer pointType,Long wlAppType,Integer jfqSubType,Long subChannelId) {
		Long productQty = 1L;
		BigInteger deliveryAddressId = null;
		logger.debug("userId is "+userId+";deliveryAddressId is "+deliveryAddressId+";productId is "+productId+";productSpecId is "+productSpecId+";productQty is "+productQty+";pointType is "+pointType);
		Long hbMoney = null;
		EbuyExtandBuyParam ebuyExtandBuyParam = null;
		return submitOrder(userId, deliveryAddressId, null, productId, productQty, productSpecId, ebuyExtandBuyParam, hbMoney, pointType,wlAppType, null, null, null,jfqSubType,subChannelId, null, null);
	}

	@Override
	public EbuyOrderEntity confirmCommProduct(BigInteger userId,BigInteger deliveryAddressId, BigInteger productId, BigInteger productSpecId,
			Integer pointType,Long wlAppType,Integer jfqSubType,Long subChannelId) {
		Long productQty = 1L;
		logger.debug("userId is "+userId+";deliveryAddressId is "+deliveryAddressId+";productId is "+productId+";productSpecId is "+productSpecId+";productQty is "+productQty+";pointType is "+pointType);
		Long hbMoney = null;
		EbuyExtandBuyParam ebuyExtandBuyParam = null;
		return submitOrder(userId, deliveryAddressId, null, productId, productQty, productSpecId, ebuyExtandBuyParam, hbMoney, pointType,wlAppType, null, null, null,jfqSubType,subChannelId, null, null);
	}

	@Override
	public void markOrderIsClientPay(BigInteger userId, BigInteger orderId, Integer pointType,Long wlAppType) {
		Integer resCount = ebuyDao.upadteOrderIsClientPay(userId,orderId,pointType,wlAppType);
		if(resCount==null||resCount<=0){
			logger.info("EbuyService.markOrderIsClientPay.upadteOrderIsClientPay.failed,resCount is"+resCount);
			throw new BusinessRuntimeException("EbuyService.markOrderIsClientPay.upadteOrderIsClientPay.failed");
		}
	}

	public void setEbuyFilmTicketService(
			IEbuyFilmTicketService ebuyFilmTicketService) {
		this.ebuyFilmTicketService = ebuyFilmTicketService;
	}

	public void setEbuyFlowRechargeService(
			IEbuyFlowRechargeService ebuyFlowRechargeService) {
		this.ebuyFlowRechargeService = ebuyFlowRechargeService;
	}

	public void setEbuyIdentifyInfoService(
			EbuyIdentifyInfoService ebuyIdentifyInfoService) {
		this.ebuyIdentifyInfoService = ebuyIdentifyInfoService;
	}

	public void setEbuyNewDao(IEbuyNewDao ebuyNewDao) {
		this.ebuyNewDao = ebuyNewDao;
	}

	@Override
	public EbuyDeliveryOrderEntity getebuyDeliveryOrderEntityById(Map<String, Object> paramMap) {
		return ebuyDao.getebuyDeliveryOrderEntityById(paramMap);
	}

	@Override
	public List<EbuyRefundOrderProduct> getRefundProductList(BigInteger refundOrderId) {
		return ebuyDao.getRefundProductList(refundOrderId);
	}

	@Override
	public EbuyRefundOrder getRefundOrderbydelId(BigInteger deliveryOrderId) {
		return ebuyDao.getRefundOrderbydelId(deliveryOrderId);
	}

	@Override
	public List<EbuyProductEntity> getOrderProductList(BigInteger orderId) {
		return ebuyDao.getOrderProductList(orderId);
	}

}
