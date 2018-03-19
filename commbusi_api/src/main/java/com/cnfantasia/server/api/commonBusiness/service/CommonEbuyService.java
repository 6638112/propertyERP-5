/**   
* Filename:    CommonEbuyService.java   
* @version:    1.0  
* Create at:   2014年6月30日 上午6:39:20   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.dao.ICommonEbuyDao;
import com.cnfantasia.server.api.commonBusiness.dao.ICommonRedenvelopeDao;
import com.cnfantasia.server.api.commonBusiness.entity.PayCouponDetailCommonEntity;
import com.cnfantasia.server.api.commonBusiness.entity.PayCouponEntity;
import com.cnfantasia.server.api.commonBusiness.entity.PayRedEnvelopeBackMoneyEntity;
import com.cnfantasia.server.api.coupon.constant.CouponTypeConstant;
import com.cnfantasia.server.api.coupon.constant.CouponUseTypeConstant;
import com.cnfantasia.server.api.coupon.service.couponSender.AbstractCouponSender;
import com.cnfantasia.server.api.couponArea.contant.UserCouponStatus;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.dredge.service.DredgePushService;
import com.cnfantasia.server.api.ebuy.dao.IGroupPurchaseDao;
import com.cnfantasia.server.api.ebuy.entity.EbuyFilmTicket;
import com.cnfantasia.server.api.ebuy.service.IEbuyFilmTicketService;
import com.cnfantasia.server.api.ebuyorder.dao.EbuyorderDao;
import com.cnfantasia.server.api.ebuyorder.entity.OrderBuyInfo;
import com.cnfantasia.server.api.ebuyorder.service.IEbuyorderService;
import com.cnfantasia.server.api.homeMessage.constant.HomeMessageDict;
import com.cnfantasia.server.api.homeMessage.service.IHomeMessageService;
import com.cnfantasia.server.api.payment.constant.EbuyConstant;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.payment.serviceUntran.IPayConfigService;
import com.cnfantasia.server.api.payment.serviceUntran.ThirdCarPayNotify;
import com.cnfantasia.server.api.payment.util.PayConfigUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.api.surpriseGift.service.ISurpriseGiftService;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.Order4HJX;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.coupon.service.ICouponBaseService;
import com.cnfantasia.server.domainbase.couponProduct.entity.CouponProduct;
import com.cnfantasia.server.domainbase.couponProduct.service.ICouponProductBaseService;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.dredgeBill.service.IDredgeBillBaseService;
import com.cnfantasia.server.domainbase.ebuyBuyCar.dao.IEbuyBuyCarBaseDao;
import com.cnfantasia.server.domainbase.ebuyBuyCar.entity.EbuyBuyCar;
import com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.dao.IEbuyBuyCarHasTEbuyProductBaseDao;
import com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.entity.EbuyBuyCarHasTEbuyProduct;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.service.IEbuyDeliveryOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.dao.EbuyDeliveryPushRecorderBaseDao;
import com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.entity.EbuyDeliveryPushRecorder;
import com.cnfantasia.server.domainbase.ebuyOrder.dao.IEbuyOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.entity.EbuyOrderHasCoupon;
import com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.service.IEbuyOrderHasCouponBaseService;
import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.entity.EbuyOrderHasTEbuyProduct;
import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.service.IEbuyOrderHasTEbuyProductBaseService;
import com.cnfantasia.server.domainbase.payCoupon.dao.IPayCouponBaseDao;
import com.cnfantasia.server.domainbase.payCoupon.entity.PayCoupon;
import com.cnfantasia.server.domainbase.payRedEnvelope.dao.IPayRedEnvelopeBaseDao;
import com.cnfantasia.server.domainbase.payRedEnvelope.entity.PayRedEnvelope;
import com.cnfantasia.server.domainbase.payRedEnvelopeDetail.dao.IPayRedEnvelopeDetailBaseDao;
import com.cnfantasia.server.domainbase.payRedEnvelopeDetail.entity.PayRedEnvelopeDetail;
import com.cnfantasia.server.domainbase.surpriseGiftUseDetail.dao.ISurpriseGiftUseDetailBaseDao;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;
import com.cnfantasia.server.domainbase.userCoupon.service.IUserCouponBaseService;
import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;

/**
 * Filename:    CommonEbuyService.java
 * @version:    1.0.0
 * Create at:   2014年6月30日 上午6:39:20
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月30日       shiyl             1.0             1.0 Version
 */
@SuppressWarnings("deprecation")
public class CommonEbuyService implements ICommonEbuyService{
	private Log logger = LogFactory.getLog(getClass());
	
	private ICommonEbuyDao commonEbuyDao;
	public void setCommonEbuyDao(ICommonEbuyDao commonEbuyDao) {
		this.commonEbuyDao = commonEbuyDao;
	}
	
	private ICommonRedenvelopeDao commonRedenvelopeDao;
	public void setCommonRedenvelopeDao(ICommonRedenvelopeDao commonRedenvelopeDao) {
		this.commonRedenvelopeDao = commonRedenvelopeDao;
	}

	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private IEbuyOrderBaseDao ebuyOrderBaseDao;
	public void setEbuyOrderBaseDao(IEbuyOrderBaseDao ebuyOrderBaseDao) {
		this.ebuyOrderBaseDao = ebuyOrderBaseDao;
	}

	private AbstractCouponSender cashCouponSender;
	public void setCashCouponSender(AbstractCouponSender cashCouponSender) {
		this.cashCouponSender = cashCouponSender;
	}

	private IPayCouponBaseDao payCouponBaseDao;
	public void setPayCouponBaseDao(IPayCouponBaseDao payCouponBaseDao) {
		this.payCouponBaseDao = payCouponBaseDao;
	}
	
	private IPayRedEnvelopeDetailBaseDao payRedEnvelopeDetailBaseDao;
	public void setPayRedEnvelopeDetailBaseDao(IPayRedEnvelopeDetailBaseDao payRedEnvelopeDetailBaseDao) {
		this.payRedEnvelopeDetailBaseDao = payRedEnvelopeDetailBaseDao;
	}
	private IPayRedEnvelopeBaseDao payRedEnvelopeBaseDao;
	public void setPayRedEnvelopeBaseDao(IPayRedEnvelopeBaseDao payRedEnvelopeBaseDao) {
		this.payRedEnvelopeBaseDao = payRedEnvelopeBaseDao;
	}
	
	private IEbuyBuyCarHasTEbuyProductBaseDao ebuyBuyCarHasTEbuyProductBaseDao;
	public void setEbuyBuyCarHasTEbuyProductBaseDao(IEbuyBuyCarHasTEbuyProductBaseDao ebuyBuyCarHasTEbuyProductBaseDao) {
		this.ebuyBuyCarHasTEbuyProductBaseDao = ebuyBuyCarHasTEbuyProductBaseDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	//购物车
	private IEbuyBuyCarBaseDao ebuyBuyCarBaseDao;
	public void setEbuyBuyCarBaseDao(IEbuyBuyCarBaseDao ebuyBuyCarBaseDao) {
		this.ebuyBuyCarBaseDao = ebuyBuyCarBaseDao;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}

	private ISurpriseGiftService surpriseGiftService;
	public void setSurpriseGiftService(ISurpriseGiftService surpriseGiftService) {
		this.surpriseGiftService = surpriseGiftService;
	}
	
	private ISurpriseGiftUseDetailBaseDao surpriseGiftUseDetailBaseDao;
	public void setSurpriseGiftUseDetailBaseDao(ISurpriseGiftUseDetailBaseDao surpriseGiftUseDetailBaseDao) {
		this.surpriseGiftUseDetailBaseDao = surpriseGiftUseDetailBaseDao;
	}
	
	private IPayConfigService payConfigService;
	public void setPayConfigService(IPayConfigService payConfigService) {
		this.payConfigService = payConfigService;
	}
	
	private IGroupPurchaseDao groupPurchaseDao;
	public void setGroupPurchaseDao(IGroupPurchaseDao groupPurchaseDao) {
		this.groupPurchaseDao = groupPurchaseDao;
	}

	private IUserCouponBaseService userCouponBaseService;
	public void setUserCouponBaseService(IUserCouponBaseService userCouponBaseService) {
		this.userCouponBaseService = userCouponBaseService;
	}

	private ICouponBaseService couponBaseService;
	public void setCouponBaseService(ICouponBaseService couponBaseService) {
		this.couponBaseService = couponBaseService;
	}

	private IEbuyOrderHasCouponBaseService ebuyOrderHasCouponBaseService;
	public void setEbuyOrderHasCouponBaseService(IEbuyOrderHasCouponBaseService ebuyOrderHasCouponBaseService) {
		this.ebuyOrderHasCouponBaseService = ebuyOrderHasCouponBaseService;
	}

	private IEbuyFilmTicketService ebuyFilmTicketService;
	public void setEbuyFilmTicketService(IEbuyFilmTicketService ebuyFilmTicketService) {
		this.ebuyFilmTicketService = ebuyFilmTicketService;
	}

	private IEbuyorderService ebuyorderService;
	public void setEbuyorderService(IEbuyorderService ebuyorderService) {
		this.ebuyorderService = ebuyorderService;
	}

	private IEbuyDeliveryOrderBaseService ebuyDeliveryOrderBaseService;
	public void setEbuyDeliveryOrderBaseService(IEbuyDeliveryOrderBaseService ebuyDeliveryOrderBaseService) {
		this.ebuyDeliveryOrderBaseService = ebuyDeliveryOrderBaseService;
	}

	private IDredgeBillBaseService dredgeBillBaseService;
	public void setDredgeBillBaseService(IDredgeBillBaseService dredgeBillBaseService) {
		this.dredgeBillBaseService = dredgeBillBaseService;
	}

	private IHomeMessageService homeMessageService;
	public void setHomeMessageService(IHomeMessageService homeMessageService) {
		this.homeMessageService = homeMessageService;
	}

	private IEbuyOrderHasTEbuyProductBaseService ebuyOrderHasTEbuyProductBaseService;
	public void setEbuyOrderHasTEbuyProductBaseService(IEbuyOrderHasTEbuyProductBaseService ebuyOrderHasTEbuyProductBaseService) {
		this.ebuyOrderHasTEbuyProductBaseService = ebuyOrderHasTEbuyProductBaseService;
	}

	private ICouponProductBaseService couponProductBaseService;
	public void setCouponProductBaseService(ICouponProductBaseService couponProductBaseService) {
		this.couponProductBaseService = couponProductBaseService;
	}

	@Resource
	private DredgePushService dredgePushService;
	@Resource
	private ThirdCarPayNotify thirdCarPayNotify;

	@Override
	public synchronized Long updateOrderByCopounList(BigInteger userId,BigInteger orderId,Set<BigInteger> couponIdList,
													 Integer jfqSubType, Map<BigInteger, Long> merchantFeeMap) {

		//基本的数据条件校验
		if(couponIdList==null||couponIdList.size()<=0){
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByCopounList.couponIdList.empty");
		}
		EbuyOrder ebuyOrder = ebuyOrderBaseDao.selectEbuyOrderBySeqId(orderId);
		if(ebuyOrder==null){
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByCopounList.ebuyOrder.isNull");
		}

		//校验消费券必须是属于当前用户的且有效, 实际消费券用户只能用一个
		List<UserCoupon> selectedCouponList = new ArrayList<UserCoupon>();
		for (BigInteger couponId : couponIdList) {
			UserCoupon userCoupon = userCouponBaseService.getUserCouponBySeqId(couponId);
			selectedCouponList.add(userCoupon);
			if (!userCoupon.gettUserFId().equals(userId) || !userCoupon.getStatus().equals(UserCouponStatus.VALID)) {
				throw new BusinessRuntimeException("CommonEbuyService.updateOrderByCopounList.couponIdList.hasUnKnown");
			}
		}

		UserCoupon usedUserCoupon = selectedCouponList.get(0);
		BigInteger couponId = usedUserCoupon.gettCouponFId();
		Coupon usedCoupon = couponBaseService.getCouponBySeqId(couponId);
		//取得最大优惠金额（分）
		Long realCouponAmount = null;
		if (usedCoupon.getCouponType().equals(CouponTypeConstant.CASHCOUPON)) {
			Long maxDiscountMoney = (long) (usedCoupon.getDiscountMoney() * usedCoupon.getMaxDiscountPercent());//此时单位为分
			if (usedCoupon.getUseType().equals(CouponUseTypeConstant.SUPPLY_MERCHANT) && merchantFeeMap != null) {
				BigInteger supplyMerchantId = usedCoupon.getSupplyMerchantId();
				Long deliveryOrderFee = merchantFeeMap.get(supplyMerchantId);
				maxDiscountMoney = Math.min(maxDiscountMoney, deliveryOrderFee);
				realCouponAmount = Math.min(ebuyOrder.getAmount(), maxDiscountMoney);
			} else if (usedCoupon.getUseType().equals(CouponUseTypeConstant.COMMUNITY_PRODUCT)) {
				EbuyOrderHasTEbuyProduct hasTEbuyProduct = new EbuyOrderHasTEbuyProduct();
				hasTEbuyProduct.settEbuyOrderFId(orderId);
				List<EbuyOrderHasTEbuyProduct> hasTEbuyProductList = ebuyOrderHasTEbuyProductBaseService
						.getEbuyOrderHasTEbuyProductByCondition(MapConverter.toMap(hasTEbuyProduct));
				CouponProduct cp = new CouponProduct();
				cp.setCouponId(couponId);
				List<CouponProduct> cpList = couponProductBaseService.getCouponProductByCondition(MapConverter.toMap(cp));
				long totalProductMoney = 0;
				if (!DataUtil.isEmpty(hasTEbuyProductList) && !DataUtil.isEmpty(cpList)) {
					List<BigInteger> shelfIdList = new ArrayList<>(cpList.size());
					for (CouponProduct couponProduct : cpList) {
						shelfIdList.add(couponProduct.getShelfId());
					}

					for (EbuyOrderHasTEbuyProduct ebuyOrderHasTEbuyProduct : hasTEbuyProductList) {
						if (shelfIdList.contains(ebuyOrderHasTEbuyProduct.gettEbuyProductFId())) {
							totalProductMoney += ebuyOrderHasTEbuyProduct.getProductPrice() * ebuyOrderHasTEbuyProduct.getProductQty();
						}
					}
				}
				realCouponAmount = Math.min(maxDiscountMoney, totalProductMoney);
			} else {
				realCouponAmount = Math.min(ebuyOrder.getAmount(), maxDiscountMoney);
			}
		} else if (usedCoupon.getCouponType().equals(CouponTypeConstant.DISCOUNTCOUPON)) {
			int maxDiscountPercent = usedCoupon.getMaxDiscountPercent();
			int discountValue = usedCoupon.getDiscountValue();
			int realDiscountPercent = Math.min(maxDiscountPercent, 100 - discountValue * 10);
			realCouponAmount = ebuyOrder.getAmount() * realDiscountPercent / 100;
			if (usedCoupon.getUseType().equals(CouponUseTypeConstant.SUPPLY_MERCHANT)) {
				BigInteger supplyMerchantId = usedCoupon.getSupplyMerchantId();
				//todo 定向店铺券折扣规则未定，折扣券未开通
			}
		}
		//更新用户消费券为已使用
		usedUserCoupon.setStatus(UserCouponStatus.USED);
		userCouponBaseService.updateUserCoupon(usedUserCoupon);
		//记录订单使用的消费券到表t_ebuy_order_has_coupon
		EbuyOrderHasCoupon ebuyOrderHasCoupon = new EbuyOrderHasCoupon();
		ebuyOrderHasCoupon.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_has_coupon));
		ebuyOrderHasCoupon.settEbuyOrderFId(orderId);
		ebuyOrderHasCoupon.setAmount(realCouponAmount);
		ebuyOrderHasCoupon.settUserCouponFId(usedUserCoupon.getId());
		ebuyOrderHasCouponBaseService.insertEbuyOrderHasCoupon(ebuyOrderHasCoupon);

		//更新订单金额相关信息
		EbuyOrder ebuyOrderUpd = new EbuyOrder();
		ebuyOrderUpd.setId(ebuyOrder.getId());
		ebuyOrderUpd.setAmount(ebuyOrder.getAmount()- realCouponAmount);
		ebuyOrderUpd.setTotalCouponAmount(realCouponAmount);
		int ebuyOrderUpdResCount = ebuyOrderBaseDao.updateEbuyOrder(ebuyOrderUpd);
		if(ebuyOrderUpdResCount<=0){
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByCopounList.updateEbuyOrder.failed");
		}
		return ebuyOrderUpd.getAmount();
	}
	
	@Override
	public synchronized Long  updateOrderByHb(BigInteger userId,BigInteger orderId, Long hbMoney) {
		if (hbMoney == null || hbMoney <= 0) {//金额为0或者负数，则直接返回
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.hbMoney.lessOrEqual0");
		}
		EbuyOrder ebuyOrder = ebuyOrderBaseDao.selectEbuyOrderBySeqId(orderId);
		if(ebuyOrder==null){
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.ebuyOrder.isNull");
		}
		//校验粮票金额是否大于订单金额
		if (hbMoney > ebuyOrder.getAmount()) {//粮票金额不能大于订单总额
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.hbMoney.biggerThan.orderAmount");
		}

		//校验余额是否充足
		EbuyorderDao ebuyorderDao = (EbuyorderDao) CnfantasiaCommbusiUtil.getBeanManager("ebuyorderDao");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId", orderId);
		List<BigInteger> merChantIdList = ebuyorderDao.selectMerchantIdListByOrderId(paramMap);
		int type = merChantIdList.contains(new BigInteger(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Experience_Store_Id))) ? 1 : 0;
		// type=1时，表示包含体验店的商品，此时只能用体验店专用粮票 added by wenfq 2019-09-19
		Long hbBalance = commonRedenvelopeDao.selectTotalHbBalance(userId, type);
		if(type == 1 && hbBalance <= hbMoney) {//体验店的票不够，还可以用全国的券来抵
			hbBalance += commonRedenvelopeDao.selectTotalHbBalance(userId, 0);
		}

		if(hbBalance==null||hbMoney.compareTo(hbBalance)>0){
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.hbBalance.notEnough.error",new Object[]{PriceUtil.div100(hbBalance)});
		}

		{
			//粮票使用百分比判断 syl-add-2015-11-10 15:13:19
			Double payPercent = payConfigService.getPayConfigHbPercent(ebuyOrder.getType());
			Long maxPercentMoney = PayConfigUtil.calculateMaxCouponAmount(ebuyOrder.getAmount(), payPercent);
			if(hbMoney.compareTo(maxPercentMoney)>0){
				throw new BusinessRuntimeException("payConfigService.hbPercentUse.toMuch",new Object[]{PriceUtil.div100(maxPercentMoney)});
			}
		}
		
		//查询所有可用的粮票，分析使用哪些粮票
		List<PayRedEnvelope> payRedEnvelopeList = null;
		if(type == 1 ) {
			if (merChantIdList.size() == 1) {
				payRedEnvelopeList = commonRedenvelopeDao.selectPayRedEnvelopeAvailable(userId, 1);
				if(payRedEnvelopeList != null) {//体验店粮票没有时，也能用全国粮票
					payRedEnvelopeList.addAll(commonRedenvelopeDao.selectPayRedEnvelopeAvailable(userId, 0));
				}else{
					payRedEnvelopeList = commonRedenvelopeDao.selectPayRedEnvelopeAvailable(userId, 0);
				}
			}
			if (payRedEnvelopeList == null || payRedEnvelopeList.size() == 0 //体验店的粮票用完了
					|| merChantIdList.size() > 1) { //订单里有既有体验店商品也有非体验店的，只能用全国的粮票
				payRedEnvelopeList = commonRedenvelopeDao.selectPayRedEnvelopeAvailable(userId, 0);
			}
		}else {
			payRedEnvelopeList = commonRedenvelopeDao.selectPayRedEnvelopeAvailable(userId, 0);
		}

		Long collectMoney = 0L;
		List<PayRedEnvelope> toUpdatePayRedEnvelopeList = new ArrayList<PayRedEnvelope>();//待更新的粮票信息
		Map<PayRedEnvelope, Long> payRedEnvelopeUsedMoney = new HashMap<PayRedEnvelope, Long>();//每个粮票对应消费的金额 key为toUpdatePayRedEnvelopeList的取值
		for(PayRedEnvelope tmpPayRed:payRedEnvelopeList){
			PayRedEnvelope updPayRedEnvelope = new PayRedEnvelope();
			toUpdatePayRedEnvelopeList.add(updPayRedEnvelope);
			
			updPayRedEnvelope.setId(tmpPayRed.getId());
			if(collectMoney+tmpPayRed.getAmountAvaible()>=hbMoney){
				if (collectMoney + tmpPayRed.getAmountAvaible() > hbMoney) {//当前粮票使用了一部分
					Long partMoney = hbMoney-collectMoney;
					collectMoney+=partMoney;
					updPayRedEnvelope.setAmountAvaible(tmpPayRed.getAmountAvaible()-partMoney);
					payRedEnvelopeUsedMoney.put(updPayRedEnvelope, partMoney);
				} else {//当前粮票刚好用完
					collectMoney+=tmpPayRed.getAmountAvaible();
					updPayRedEnvelope.setAmountAvaible(0L);
//					updPayRedEnvelope.setStatus(DictConstants.PayRedEnvelope_Status.USE_OUT);
					payRedEnvelopeUsedMoney.put(updPayRedEnvelope, tmpPayRed.getAmountAvaible());
				}
				break;
			} else {//还不够
				collectMoney+=tmpPayRed.getAmountAvaible();
				updPayRedEnvelope.setAmountAvaible(0L);
//				updPayRedEnvelope.setStatus(DictConstants.PayRedEnvelope_Status.USE_OUT);
				payRedEnvelopeUsedMoney.put(updPayRedEnvelope, tmpPayRed.getAmountAvaible());
			}
		}
		if(collectMoney<hbMoney){
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.hbBalance.notEnough02.error",new Object[]{PriceUtil.div100(collectMoney)});
		}else if(collectMoney>hbMoney){
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.hbBalance.collectMoney.error",new Object[]{PriceUtil.div100(collectMoney)});
		}
		//更新对应的粮票信息
		int updRes = payRedEnvelopeBaseDao.updatePayRedEnvelopeBatch(toUpdatePayRedEnvelopeList);
		if(updRes<=0){
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.updatePayRedEnvelopeBatch.failed");
		}
		{
			//给订单增加优惠信息
			List<PayRedEnvelopeDetail> payRedEnvelopeDetailAddBatchList = new ArrayList<PayRedEnvelopeDetail>();
			String consumTime = dualDao.getNowTime();
			//t_pay_coupon
			BigInteger payCouponAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_coupon);
			PayCoupon payCoupon = new PayCoupon();
			payCoupon.setId(payCouponAddId);
			payCoupon.settEbuyOrderFId(orderId);
			payCoupon.setType(EbuyDict.PayCoupon_Type.Red_Envelope);//优惠信息，粮票
			Long payCouponAnountTotal = 0L;
			List<BigInteger> payRedEnvelopeDetailAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_red_envelope_detail,toUpdatePayRedEnvelopeList.size());
			for(int i=0;i<toUpdatePayRedEnvelopeList.size();i++){
				PayRedEnvelope payRedEnvelope = toUpdatePayRedEnvelopeList.get(i);
				BigInteger payRedEnvelopeDetailAddId = payRedEnvelopeDetailAddIdList.get(i);
				PayRedEnvelopeDetail payRedEnvelopeDetail = new PayRedEnvelopeDetail();
				payRedEnvelopeDetail.setAnount(payRedEnvelopeUsedMoney.get(payRedEnvelope));//从统计记录中获取
				payRedEnvelopeDetail.setConsumTime(consumTime);
				payRedEnvelopeDetail.setId(payRedEnvelopeDetailAddId);
				payRedEnvelopeDetail.setPayRedEnvelopeId(payRedEnvelope.getId());//粮票Id
				payRedEnvelopeDetail.settPayCouponFId(payCouponAddId);//优惠信息Id
				payRedEnvelopeDetail.setUserId(userId);
				payRedEnvelopeDetailAddBatchList.add(payRedEnvelopeDetail);
				
				payCouponAnountTotal += payRedEnvelopeUsedMoney.get(payRedEnvelope);//统计优惠总金额
			}
			payCoupon.setAmount(payCouponAnountTotal);//设置该优惠项的总金额
			
			//新增t_pay_coupon
			List<PayCoupon> payCouponAddBatchList = new ArrayList<PayCoupon>();
			payCouponAddBatchList.add(payCoupon);
			int resCount01=payCouponBaseDao.insertPayCouponBatch(payCouponAddBatchList);
			if(resCount01!=payCouponAddBatchList.size()){
				throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.insertPayCouponBatch.resCount.notEqual");
			}
			//批量新增t_pay_red_envelope_detail
			int resCount02=payRedEnvelopeDetailBaseDao.insertPayRedEnvelopeDetailBatch(payRedEnvelopeDetailAddBatchList);
			if(resCount02!=payRedEnvelopeDetailAddBatchList.size()){
				throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.insertPayRedEnvelopeDetailBatch.resCount.notEqual");
			}
		}
		//更新订单金额相关信息
		EbuyOrder ebuyOrderUpd = new EbuyOrder();
		ebuyOrderUpd.setId(ebuyOrder.getId());
		ebuyOrderUpd.setAmount(ebuyOrder.getAmount()-hbMoney);
		ebuyOrderUpd.setTotalCouponAmount(hbMoney);
		int ebuyOrderUpdResCount = ebuyOrderBaseDao.updateEbuyOrder(ebuyOrderUpd);
		if(ebuyOrderUpdResCount<=0){
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.updateEbuyOrder.failed");
		}
		return ebuyOrderUpd.getAmount();
	}
	
	@Override
	public synchronized Long  updateOrderByHb02(BigInteger userId,BigInteger orderId, Long hbMoney) {
		if (hbMoney == null || hbMoney <= 0) {//金额为0或者负数，则直接返回
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.hbMoney.lessOrEqual0");
		}
		EbuyOrder ebuyOrder = ebuyOrderBaseDao.selectEbuyOrderBySeqId(orderId);
		if(ebuyOrder==null){
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.ebuyOrder.isNull");
		}
		//校验粮票金额是否大于订单金额
		if (hbMoney > ebuyOrder.getAmount()) {//粮票金额不能大于订单总额
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.hbMoney.biggerThan.orderAmount");
		}
		
		{
			//给订单增加优惠信息
			//t_pay_coupon
			BigInteger payCouponAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_coupon);
			PayCoupon payCoupon = new PayCoupon();
			payCoupon.setId(payCouponAddId);
			payCoupon.settEbuyOrderFId(orderId);
			payCoupon.setType(EbuyDict.PayCoupon_Type.Red_Envelope);//优惠信息，粮票
			payCoupon.setAmount(hbMoney);//设置该优惠项的总金额
			
			//新增t_pay_coupon
			List<PayCoupon> payCouponAddBatchList = new ArrayList<PayCoupon>();
			payCouponAddBatchList.add(payCoupon);
			int resCount01=payCouponBaseDao.insertPayCouponBatch(payCouponAddBatchList);
			if(resCount01!=payCouponAddBatchList.size()){
				throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.insertPayCouponBatch.resCount.notEqual");
			}
		}
		//更新订单金额相关信息
		EbuyOrder ebuyOrderUpd = new EbuyOrder();
		ebuyOrderUpd.setId(ebuyOrder.getId());
		ebuyOrderUpd.setAmount(ebuyOrder.getAmount()-hbMoney);
		ebuyOrderUpd.setTotalCouponAmount(hbMoney);
		int ebuyOrderUpdResCount = ebuyOrderBaseDao.updateEbuyOrder(ebuyOrderUpd);
		if(ebuyOrderUpdResCount<=0){
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.updateEbuyOrder.failed");
		}
		return ebuyOrderUpd.getAmount();
	}


	@Override
	public Long backHbByOrderId(BigInteger userId, BigInteger orderId) {
		//订单金额
		EbuyOrder ebuyOrder = ebuyOrderBaseDao.selectEbuyOrderBySeqId(orderId);
		Long totalCouponAmount=ebuyOrder.getTotalCouponAmount();
		//查询订单使用的优惠信息
		List<PayCouponEntity> payCouponList = commonEbuyDao.selectPayCouponListByOrderId(orderId);

		EbuyOrderHasCoupon orderHasCoupon = new EbuyOrderHasCoupon();
		orderHasCoupon.settEbuyOrderFId(orderId);
		List<EbuyOrderHasCoupon> coupons = ebuyOrderHasCouponBaseService.getEbuyOrderHasCouponByCondition(MapConverter.toMap(orderHasCoupon));

		Long backCoupon = 0L;
		if(totalCouponAmount!=null&&totalCouponAmount.compareTo(0L)>0&&(payCouponList!=null&&payCouponList.size()>0 || coupons!=null&&coupons.size()>0)){
			List<BigInteger> payCouponDelIds = new ArrayList<BigInteger>();//待删除的优惠信息Ids
			
			List<BigInteger> payRedEnvelopeDetailDelIds = new ArrayList<BigInteger>();//待删除的优惠详情Ids
			List<PayRedEnvelopeBackMoneyEntity> payRedEnvelopeBackList = new ArrayList<PayRedEnvelopeBackMoneyEntity>();//待退回的粮票信息 增量金额
			
			List<BigInteger> surpriseGiftUseDetailDetailDelIds = new ArrayList<BigInteger>();//待删除的优惠券详情Ids
			List<BigInteger> surpriseGiftIdList = new ArrayList<BigInteger>();//使用的意外惊喜列表Ids
			
			for(PayCouponEntity tmpPayCoupon:payCouponList){
				payCouponDelIds.add(tmpPayCoupon.getId());
				backCoupon+=tmpPayCoupon.getAmount();
				List<PayCouponDetailCommonEntity> detailList = tmpPayCoupon.getPayCouponDetailCommonEntityList();
				for(PayCouponDetailCommonEntity tmpDetail:detailList){
					if (tmpPayCoupon.getType() != null && EbuyDict.PayCoupon_Type.Red_Envelope.compareTo(tmpPayCoupon.getType()) == 0) {//粮票优惠
						payRedEnvelopeDetailDelIds.add(tmpDetail.getId());
						PayRedEnvelopeBackMoneyEntity payRedEnvelopeBack = new PayRedEnvelopeBackMoneyEntity();
						payRedEnvelopeBack.setId(new BigInteger(tmpDetail.getOtherInfo().toString()));
						payRedEnvelopeBack.setBackMmoney(tmpDetail.getAnount());
						payRedEnvelopeBackList.add(payRedEnvelopeBack);
					} else if (tmpPayCoupon.getType() != null && EbuyDict.PayCoupon_Type.CouponTicket.compareTo(tmpPayCoupon.getType()) == 0) {//优惠券方式
						surpriseGiftUseDetailDetailDelIds.add(tmpDetail.getId());
						surpriseGiftIdList.add(new BigInteger(tmpDetail.getOtherInfo().toString()));
					}
				}
			}

			//消费券节省金额
			long userCouponSavedMoney = 0;
			for (EbuyOrderHasCoupon coupon : coupons) {
				userCouponSavedMoney += coupon.getAmount();
			}

			long totalCoupon = backCoupon + userCouponSavedMoney;
			if (totalCoupon == 0 || totalCouponAmount.compareTo(totalCoupon) != 0) {//校验优惠金额是否匹配
				throw new BusinessRuntimeException("CommonEbuyService.backHbByOrderId.payCoupon.notEqual");
			}
			{//粮票使用信息的退回
				if (payRedEnvelopeDetailDelIds.size() > 0) {//批量逻辑删除t_pay_red_envelope_detail
					int payRedEnvelopeDetailDelCount=payRedEnvelopeDetailBaseDao.deletePayRedEnvelopeDetailLogicBatch(payRedEnvelopeDetailDelIds);
//					if(payRedEnvelopeDetailDelCount!=payRedEnvelopeDetailDelIds.size()){
					if(payRedEnvelopeDetailDelCount<=0){
						throw new BusinessRuntimeException("CommonEbuyService.backHbByOrderId.deletePayRedEnvelopeDetail.failed");
					}
				}
				if (payRedEnvelopeBackList.size() > 0) {//退回粮票
					int backCount = commonEbuyDao.backMoney2HbBatch(payRedEnvelopeBackList);
					if(backCount!=payRedEnvelopeBackList.size()){
						throw new BusinessRuntimeException("CommonEbuyService.backHbByOrderId.backMoney2Hb.failed");
					}
				}
			}
			
			{//优惠券使用信息的退回
				if(surpriseGiftUseDetailDetailDelIds.size()>0){
					int resCount=surpriseGiftUseDetailBaseDao.deleteSurpriseGiftUseDetailLogicBatch(surpriseGiftUseDetailDetailDelIds);
					if(resCount<=0){
						throw new BusinessRuntimeException("CommonEbuyService.backHbByOrderId.deleteSurpriseGiftUseDetail.failed");
					}
				}
				if (surpriseGiftIdList.size() > 0) {//批量退回优惠券信息
					surpriseGiftService.backSurpriseGiftBatch(surpriseGiftIdList);
				}
			}
			
			{//批量逻辑删除t_pay_coupon
				if (payCouponDelIds.size() > 0) {
					int payCouponDelCount=payCouponBaseDao.deletePayCouponLogicBatch(payCouponDelIds);
//				if(payCouponDelCount!=payCouponDelIds.size()){
					if(payCouponDelCount<=0){
						throw new BusinessRuntimeException("CommonEbuyService.backHbByOrderId.deletePayCoupon.failed");
					}
				}
			}

			{//更改订单金额 优惠金额
				EbuyOrder ebuyOrderUpd = new EbuyOrder();
				ebuyOrderUpd.setId(ebuyOrder.getId());
				ebuyOrderUpd.setAmount(ebuyOrder.getAmount() + backCoupon);//订单应付金额+退回金额
				ebuyOrderUpd.setTotalCouponAmount(totalCouponAmount - backCoupon);//优惠金额-退回金额
				ebuyOrderUpd.setBuyerId(userId);//只有购买者才可以取消订单
				int orderUpdRes = ebuyOrderBaseDao.updateEbuyOrder(ebuyOrderUpd);
				if(orderUpdRes<=0){
					throw new BusinessRuntimeException("CommonEbuyService.backHbByOrderId.updateEbuyOrder.failed");
				}
			}
			
		}else{
			if(	(totalCouponAmount==null||totalCouponAmount.compareTo(0L)==0)
					&&(payCouponList!=null&&payCouponList.size()>0 || coupons!=null&&coupons.size()>0)){
				//订单没有总优惠金额，却有多余的优惠信息记录
				throw new BusinessRuntimeException("CommonEbuyService.backHbByOrderId.payCoupon.unknown");
			}else if(totalCouponAmount!=null&&totalCouponAmount.compareTo(0L)>0&&
					((payCouponList==null||payCouponList.size()<=0)&&(coupons==null||coupons.size()<=0))){
				//订单有总优惠金额，但没有优惠信息记录
				throw new BusinessRuntimeException("CommonEbuyService.backHbByOrderId.payCoupon.isLost");
			}else{
				
			}
		}
		return totalCouponAmount;
	}


	@Override
	public List<PayCouponEntity> getPayCouponListByOrderId(BigInteger orderId) {
		return commonEbuyDao.selectPayCouponListByOrderId(orderId);
	}
	
	/**
	 * 异步推送
	 * 
	 * @author wenfq 2015-03-06
	 * 
	 */
	class AsyOrderPusher implements Runnable {
		BigInteger orderId;
		Integer payMethod;

		public AsyOrderPusher(BigInteger orderId, Integer payMethod) {
			this.orderId = orderId;
			this.payMethod = payMethod;
		}

		@Override
		public void run() {
			try {
				pushOrder2SplMerchat(orderId, EbuyConstant.EbuySupplyMerchant_Id.Spl_Merchant_HJX_Id);
			} catch (Exception e) {
				logger.error("CommonEbuyService.paySuccessOperateComm.pushOrder2SplMerchat cause exception:orderId is:" + orderId + ",payMethod is:"
						+ payMethod, e);
			}
		}

	}

	@Override
	public void paySuccessOperateComm(BigInteger orderId,Integer payMethod) {
		/*try {
			new Thread(new AsyOrderPusher(orderId, payMethod)).start();
		} catch (Exception e) {
			logger.error("CommonEbuyService.paySuccessOperateComm.pushOrder2SplMerchat cause exception:orderId is:"+orderId+",payMethod is:"+payMethod, e);
		}*/

		EbuyOrder orderDetail = ebuyOrderBaseDao.selectEbuyOrderBySeqId(orderId);

		//维修的是先用，确认付款后将消费券失效处理
		if (orderDetail.getType() != null 
				&& (orderDetail.getType().compareTo(EbuyDict.EbuyOrder_Type.Dredge_Bill) == 0
				||orderDetail.getType().compareTo(EbuyDict.EbuyOrder_Type.CarKey_Bill) == 0)) {
			//查询已使用消费券
			EbuyOrderHasCoupon orderHasCoupon = new EbuyOrderHasCoupon();
			orderHasCoupon.settEbuyOrderFId(orderId);
			List<EbuyOrderHasCoupon> usedCoupons = ebuyOrderHasCouponBaseService.getEbuyOrderHasCouponByCondition(MapConverter.toMap(orderHasCoupon));
			if (!DataUtil.isEmpty(usedCoupons)) {
				UserCoupon usedUserCoupon = userCouponBaseService.getUserCouponBySeqId(usedCoupons.get(0).gettUserCouponFId());
				//消费券如果已失效，则不给通过
				if (!UserCouponStatus.VALID.equals(usedUserCoupon.getStatus())) {
					throw new BusinessRuntimeException("CommonEbuyService.updateOrderByCopounList.couponIdList.hasUnKnown");
				} else {
					usedUserCoupon.setStatus(UserCouponStatus.USED);
					userCouponBaseService.updateUserCoupon(usedUserCoupon);
				}
			}
		}
		
		String payTime = dualDao.getNowTime();
		{//更改订单状态为已支付
			EbuyOrder ebuyOrderUpd = new EbuyOrder();
			ebuyOrderUpd.setId(orderId);
			ebuyOrderUpd.setSys0DelState(0);// V324需求：如果订单被逻辑删除，这里重新标为未删除 added by wenfq 2016-04-19
			ebuyOrderUpd.setPayStatus(EbuyDict.EbuyOrder_PayStatus.Pay_Success);//支付成功
			if (orderDetail.getType() != null && orderDetail.getType().compareTo(EbuyDict.EbuyOrder_Type.Flash_Deal_Activity) == 0) {
				ebuyOrderUpd.setStatus(EbuyDict.EbuyOrder_Status.YiWanCheng);//已完成
			} else {
				ebuyOrderUpd.setStatus(EbuyDict.EbuyOrder_Status.DaiFaHuo);//待发货
			}
			ebuyOrderUpd.setPayTime(payTime);//设定支付成功时间
			ebuyOrderUpd.setPayMethod(payMethod + "");//syl-add-2015-3-19 16:28:24
			int res = ebuyOrderBaseDao.updateEbuyOrder(ebuyOrderUpd);
			if (res <= 0) {
				throw new BusinessRuntimeException("CommonEbuyService.paySuccessOperateComm.updateEbuyOrder.failed");
			}
		}

		//送消费券
		cashCouponSender.sendCoupons(orderId);

		try {
			//超市会发送短信
			if (orderDetail.getType() != null 
					&& (orderDetail.getType().compareTo(EbuyDict.EbuyOrder_Type.EBuy_Product) == 0
					|| orderDetail.getType().compareTo(EbuyDict.EbuyOrder_Type.Limit_Buy_Activity) == 0)) {
				//当订单里有电影票商品时，会分配电影券给此订单，并发送短信通知。
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("orderId", orderId);

				List<EbuyFilmTicket> tickets = ebuyFilmTicketService.getFilmTickList(paramMap);
				if (!DataUtil.isEmpty(tickets)) {
					ebuyFilmTicketService.updateFilmTicketLockToUsed(orderId.longValue());
					if (!StringUtils.isEmpty(orderDetail.getDelivPhone())) {
						int size = tickets.size();
						StringBuilder sb = new StringBuilder();
						for (EbuyFilmTicket ticket : tickets) {
							sb.append(ticket.getIdentifyCode()).append("，");
						}
						ShortMsgUtil.sendMessage(orderDetail.getDelivPhone(), "filmTicket_paid", size, sb.toString());
					}
				}

				//提醒供应商发货短信
				List<OrderBuyInfo> infos = ebuyorderService.getOrderBuyInfoByOrderId(orderId);
				String now = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date());
				for (OrderBuyInfo info : infos) {
					if (!StringUtils.isEmpty(info.getUserMobile()) && !StringUtils.isEmpty(info.getSupplyMerchantTel())) {
						ShortMsgUtil.sendMessage(info.getSupplyMerchantTel(), "supplier_after_paid", info.getUserMobile(), now, info.getProductCount());
					}
				}
				
				//自提的，给用户发自提短信通知
				List<OrderBuyInfo> zitiInfos = ebuyorderService.getZiTiOrderBuyInfoByOrderId(orderId);
				for (OrderBuyInfo info : zitiInfos) {
					if(!StringUtils.isEmpty(info.getZiTiAddress())){//给用户发送自提地址  Added by wenfq 2017-03-14
						ShortMsgUtil.sendMessage(info.getUserMobile(), "send_ziTi_address",info.getUserId()+"", info.getZiTiAddress());
					}
				}

				//2017中秋活动，若有购买大闸蟹和月饼的，给推荐人发短信提醒 added by wenfq
				OrderBuyInfo midAutumnInfo = ebuyorderService.getMidAutumnOrderBuyInfoByOrderId(orderId);
				if(midAutumnInfo != null){
					ShortMsgUtil.sendMessage(midAutumnInfo.getUserMobile(), "notify_recommender", midAutumnInfo.getSupplyMerchantName(), midAutumnInfo.getOrderNo());
				}
			}
		} catch (Exception e) {
			logger.info("付款完成发送短信错误：" + e.getMessage());
		}

		if (orderDetail.getType() != null && orderDetail.getType().compareTo(EbuyDict.EbuyOrder_Type.Dredge_Bill) == 0) {//维修要改运单状态
			EbuyDeliveryOrder deliveryOrder = new EbuyDeliveryOrder();
			deliveryOrder.settEbuyOrderFId(orderId);
			List<EbuyDeliveryOrder> ebuyDeliveryOrders = ebuyDeliveryOrderBaseService.getEbuyDeliveryOrderByCondition(MapConverter.toMap(deliveryOrder));
			if (!DataUtil.isEmpty(ebuyDeliveryOrders)) {
				for (EbuyDeliveryOrder ebuyDeliveryOrder : ebuyDeliveryOrders) {
					ebuyDeliveryOrder.setStatus(EbuyDict.EbuyDeliveryOrder_Status.QueRenShowHuo);
				}
				ebuyDeliveryOrderBaseService.updateEbuyDeliveryOrderBatch(ebuyDeliveryOrders);
			}
			DredgeBill db = new DredgeBill();
			db.settEbuyOrderFId(orderId);
			List<DredgeBill> dbList = dredgeBillBaseService.getDredgeBillByCondition(MapConverter.toMap(db));
			if (!DataUtil.isEmpty(dbList)) {
				db = dbList.get(0);
				UserHasHomeMessage message = new UserHasHomeMessage();
				message.setMessageCode(HomeMessageDict.MessageCode.DREDGE_SERVICE_ALERT);
				message.setResouceId(db.getId());
				homeMessageService.generateCommonMsg(message);
				if (db.getBillType() == DredgeConstant.DredgeBillType.Dredge_Pay_First) {
					//发推送消息
					dredgePushService.submitBillSuccessMsg(db.getId());
				}
			}
		}

		if (orderDetail.getType() != null && orderDetail.getType().compareTo(EbuyDict.EbuyOrder_Type.Property_Bill) == 0) {//物业账单的
			BigInteger tmpUserId = orderDetail.getBuyerId();
			BigInteger tmpRoomId = orderDetail.getCurrRoomId();
			if(tmpUserId!=null&&tmpRoomId!=null){
				try {
					commonRoomService.addValidateSuccessInfo(tmpUserId, tmpRoomId, RoomDict.RoomValidte_SourceType.PayBill_Auto_Add, "缴费自动审核通过");//增加验证成功的信息
				} catch (Exception e) {
					logger.error("commonEbuyService.paySuccessOperateComm.addValidateSuccessInfo cause exception,order id is:"+orderId+",tmpUserId is:"+tmpUserId+",tmpRoomId is :"+tmpRoomId, e);
				}
			}
		}
		
		if (orderDetail.getType() != null && orderDetail.getType().compareTo(EbuyDict.EbuyOrder_Type.CarKey_Bill) == 0) {//车禁临停车缴费通知
			// 如果是临停车缴费，则发送消息到“万人插”
			thirdCarPayNotify.dealNotify(orderId, orderDetail.getOrderNo(), payTime);
		}
		
		if(orderDetail.getType() != null && orderDetail.getType().compareTo(EbuyDict.EbuyOrder_Type.EBuy_Product) == 0) {
			//减少拼购库存
			checkLeftCount(null,1L,1,orderId);
			//如果有社区店的商品，则同步库存至店里, 这里是放至redis队列，然后在job里消费
			String storeId = sysParamManager.getSysParaValue(SysParamKey.Experience_Store_Id);
			EbuyOrderHasTEbuyProduct hasProduct = new EbuyOrderHasTEbuyProduct();
			hasProduct.setSupplyMerchantId(new BigInteger(storeId));
			hasProduct.settEbuyOrderFId(orderId);
			int storeDelivCount = ebuyOrderHasTEbuyProductBaseService.getEbuyOrderHasTEbuyProductCount(MapConverter.toMap(hasProduct));
			logger.info("含有体验店商品的数量为：" + storeDelivCount);
			if (storeDelivCount > 0) {
				logger.info("向redis里记录待推送乐掌柜的订单");
				RedisCacheHandler.lpush(RedisCachePrefix.SYN_STORE_PRODUCT, orderId.toString());
				logger.info("向redis里记录待推送乐掌柜的订单, 值为：" + orderId);
			}
		}
		
		Boolean isNotifyAdmin = Boolean.valueOf(sysParamManager.getSysParaValue(SysParamKey.PAY_SUCC_NOTIFY_SWITCH));
		String notifyPhoneStr = null;
		if (isNotifyAdmin) {//短信通知管理员
			try {
				String operationDesc = "";
				if (orderDetail.getType().compareTo(EbuyDict.EbuyOrder_Type.EBuy_Product) == 0) {// 订单类型电商
					Integer pointType = orderDetail.getPointType();
					if(pointType!=null&&EbuyDict.PointType.POINT_PRODUCT.compareTo(pointType)==0){
						operationDesc = "购买积分商品,使用" + orderDetail.getAmountPoint() + "积分";
						notifyPhoneStr = sysParamManager.getSysParaValue(SysParamKey.PAY_SUCC_NOTIFY_PHONE_POINT);
					}else{
						notifyPhoneStr = sysParamManager.getSysParaValue(SysParamKey.PAY_SUCC_NOTIFY_PHONE_EBUY);
						operationDesc = "购买电商商品";
					}
				} else if (orderDetail.getType().compareTo(EbuyDict.EbuyOrder_Type.Property_Bill) == 0) {// 订单类型物业账单
					notifyPhoneStr = sysParamManager.getSysParaValue(SysParamKey.PAY_SUCC_NOTIFY_PHONE_PAYBILL);
					operationDesc = "缴纳物业费";
				} else if (orderDetail.getType().compareTo(EbuyDict.EbuyOrder_Type.Dredge_Bill) == 0) {// 订单类型--疏通账单
					notifyPhoneStr = sysParamManager.getSysParaValue(SysParamKey.PAY_SUCC_NOTIFY_PHONE_DredgeBill);
					operationDesc = "缴纳疏通服务费";
				}else if(orderDetail.getType().compareTo(EbuyDict.EbuyOrder_Type.CarKey_Bill) == 0){
					notifyPhoneStr = sysParamManager.getSysParaValue(SysParamKey.PAY_SUCC_NOTIFY_PHONE_CarKeyBill);
					operationDesc = "车禁缴费";
				}else if(orderDetail.getType().compareTo(EbuyDict.EbuyOrder_Type.DoorKey_Bill) == 0){
					notifyPhoneStr = sysParamManager.getSysParaValue(SysParamKey.PAY_SUCC_NOTIFY_PHONE_CarKeyBill);
					operationDesc = "门禁缴费";
				}
				if(!StringUtils.isEmpty(notifyPhoneStr)){
					ShortMsgUtil.sendMessages(new ArrayList<>(Arrays.asList(notifyPhoneStr.split(";"))), "order_pay", orderDetail.getBuyerId()+"", payTime, operationDesc, PriceUtil.div100(orderDetail.getAmount()), orderDetail.getOrderNo());
					logger.info("订单" + orderDetail.getOrderNo() + "支付成功，短信通知管理员的响应 = true");
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}
	
	@Override
	public EbuyOrder selectEbuyOrderByOrderNo(String orderNo) {
		List<EbuyOrder> resList = null;
		if(!StringUtils.isEmpty(orderNo)){
			EbuyOrder ebuyOrderQry = new EbuyOrder();
			ebuyOrderQry.setOrderNo(orderNo);
			resList = ebuyOrderBaseDao.selectEbuyOrderByCondition(MapConverter.toMap(ebuyOrderQry), false);
			if(resList.isEmpty()){//已删除的订单，支付回调也要保证成功
				ebuyOrderQry.setSys0DelState(1);
				resList = ebuyOrderBaseDao.selectEbuyOrderByCondition(MapConverter.toMap(ebuyOrderQry), false);
			}
		}
		if(resList==null||resList.size()<=0){
			throw new BusinessRuntimeException("CommonEbuyService.selectEbuyOrderByOrderNo.resList.null.error",new Object[]{orderNo});
		}else if(resList.size()==1){
			return resList.get(0);
		}else{
			throw new BusinessRuntimeException("CommonEbuyService.selectEbuyOrderByOrderNo.resList.moreThanOne.error",new Object[]{orderNo});
		}
	}
	@Override
	public void collectEbuyBuyCar(BigInteger fromUser,Integer fromType,BigInteger toUser,Integer toType){
		//查询之前的购物车商品
		List<EbuyBuyCarHasTEbuyProduct> tmpUserListData = commonEbuyDao.selectEbuyBuyCarHasTEbuyProductList(fromUser,fromType);
		List<EbuyBuyCarHasTEbuyProduct> registListData = commonEbuyDao.selectEbuyBuyCarHasTEbuyProductList(toUser, toType);
		//分析之前存在且现在也存在的商品Id
		List<BigInteger> bothOfRegistIds = new ArrayList<BigInteger>();
		if(registListData!=null&&tmpUserListData!=null){
			for(EbuyBuyCarHasTEbuyProduct regData:registListData){
				for(EbuyBuyCarHasTEbuyProduct tmpUserData:tmpUserListData){
					if(regData.gettEbuyProductFId().compareTo(tmpUserData.gettEbuyProductFId())==0){
						bothOfRegistIds.add(regData.getId());
					}
				}
			}
		}
		//转移商品
		commonEbuyDao.convertEbuyBuyCarProdcts(fromUser, fromType, toUser,toType);
		//删除之前重复的商品
		if(bothOfRegistIds.size()>0){
			ebuyBuyCarHasTEbuyProductBaseDao.deleteEbuyBuyCarHasTEbuyProductLogicBatch(bothOfRegistIds);
//			int delCount = ebuyBuyCarHasTEbuyProductBaseDao.deleteEbuyBuyCarHasTEbuyProductLogicBatch(bothOfRegistIds);
//			if(delCount!=bothOfRegistIds.size()){
//				throw new BusinessRuntimeException("login.collectEbuyBuyCar.deleteEbuyBuyCarHasTEbuyProductLogicBatch.failed");
//			}
		}
	}


	@Override
	public BigInteger checkAndCreateEbuyBuyCar(BigInteger userId,Integer userType) {
		BigInteger buyCarId = null;
		List<EbuyBuyCar> currEbuyBuyCarList = null;
		if(userId!=null&&userType!=null){
			EbuyBuyCar qryEbuyBuyCar = new EbuyBuyCar();
			qryEbuyBuyCar.setUserId(userId);
			qryEbuyBuyCar.setUserType(userType);
			currEbuyBuyCarList = ebuyBuyCarBaseDao.selectEbuyBuyCarByCondition(MapConverter.toMap(qryEbuyBuyCar), false);
		}
		if (currEbuyBuyCarList == null || currEbuyBuyCarList.size() <= 0) {//是否存在
			//不存在则新增
			BigInteger addEbuyBuyCarId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_buy_car);
			EbuyBuyCar addEbuyBuyCar = new EbuyBuyCar();
			addEbuyBuyCar.setId(addEbuyBuyCarId);
			addEbuyBuyCar.setCreateTime(dualDao.getNowTime());
			addEbuyBuyCar.setUserId(userId);
			addEbuyBuyCar.setUserType(userType);
			int res = ebuyBuyCarBaseDao.insertEbuyBuyCar(addEbuyBuyCar);
			if(res<=0){
				throw new BusinessRuntimeException("EbuyService.add2BuyCar.insertEbuyBuyCar.failed");
			}
			buyCarId = addEbuyBuyCarId;
		}else if(currEbuyBuyCarList.size()>1){
			List<BigInteger> tmpDelIds = new ArrayList<BigInteger>();
			for (int i = 1; i < currEbuyBuyCarList.size(); i++) {//从1开始
				tmpDelIds.add(currEbuyBuyCarList.get(i).getId());
			}
			ebuyBuyCarBaseDao.deleteEbuyBuyCarLogicBatch(tmpDelIds);
			logger.error("EbuyService.add2BuyCar.currEbuyBuyCarList.count.morethan1.error");
//			throw new BusinessRuntimeException("EbuyService.add2BuyCar.currEbuyBuyCarList.count.morethan1.error");
			buyCarId = currEbuyBuyCarList.get(0).getId();
		}else{
			buyCarId = currEbuyBuyCarList.get(0).getId();
		}
		return buyCarId;
	}

	EbuyDeliveryPushRecorderBaseDao ebuyDeliveryPushRecorderBaseDao;

	public void setEbuyDeliveryPushRecorderBaseDao(EbuyDeliveryPushRecorderBaseDao ebuyDeliveryPushRecorderBaseDao) {
		this.ebuyDeliveryPushRecorderBaseDao = ebuyDeliveryPushRecorderBaseDao;
	}

	/**
	 * 推送订单到海吉星
	 * 
	 * @param order
	 *            海吉星所需的订单信息
	 * @return responseString 对方服务器返回的结果串
	 */
	private String pushOrder2HJX(Order4HJX order) {
		CloseableHttpClient client = HttpClients.createDefault();

		// 创建参数队列 
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair(SysParamKey.HJX_Source_tag, sysParamManager.getSysParaValue(SysParamKey.HJX_Source_tag)));
		formparams.add(new BasicNameValuePair(SysParamKey.HJX_Version, sysParamManager.getSysParaValue(SysParamKey.HJX_Version)));
		formparams.add(new BasicNameValuePair("order", JSON.toJSONString(order)));

		StringBuilder paramString = new StringBuilder();
		paramString.append(SysParamKey.HJX_Source_tag + "=" + sysParamManager.getSysParaValue(SysParamKey.HJX_Source_tag) + "|");
		paramString.append(SysParamKey.HJX_Version + "=" + sysParamManager.getSysParaValue(SysParamKey.HJX_Version) + "|");
		paramString.append("order=" + JSON.toJSONString(order) + "|");
		paramString.append(sysParamManager.getSysParaValue(SysParamKey.HJX_Salt));
		logger.info("push params is: " + paramString.toString());
		String signature = Md5Util.MD5(paramString.toString()); //签名
		formparams.add(new BasicNameValuePair("signature", signature));

		//String unifiedorderURL = "http://119.136.31.227:86/api/3rd/place_order_v1.php";
		//String unifiedorderURL = "http://www.hjxmall.com/api/3rd/place_order_v1.php";
		HttpPost httpPost = new HttpPost(sysParamManager.getSysParaValue(SysParamKey.OrderPushURL));

		HttpEntity entity;
		String responseString = null;
		try {
			entity = new UrlEncodedFormEntity(formparams, HTTP.UTF_8);
			httpPost.setEntity(entity);
			CloseableHttpResponse hcResponse = client.execute(httpPost);
			HttpEntity responseEntity = hcResponse.getEntity();
			responseString = EntityUtils.toString(responseEntity, HTTP.UTF_8);
			logger.info("response from hjx is: " + responseString);
			//{"code":"0","order_tag":"100058180","sub_order_tags":["1501271146560000091202"], "description":"成功！"}
			//{"code":"-1","description":"重复订单！"}
			client.close();
			hcResponse.close();
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			responseString = "推送过程有异常，订单推送失败";
		}
		return responseString;
	}

	@Override
	public synchronized void pushOrder2SplMerchat(BigInteger orderId, BigInteger splMerchantId) {
		Order4HJX orderInfo = commonEbuyDao.preparOrder4SplMerchat(orderId, splMerchantId);
		if (orderInfo == null) {
			return;
		}
		{//推送给海吉星之前，先查下是否推送成功，若成功则不再推送，避免出现重复推送
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pushContent", "source_order_tag\":\"" + orderInfo.getSource_order_tag());
			paramMap.put("tEbuyDeliveryOrderFId", orderInfo.getEdoId());
			paramMap.put("ispushSuccess", 1);
			if (ebuyDeliveryPushRecorderBaseDao.selectEbuyDeliveryPushRecorderCount(paramMap, true) > 0) {//已成功推送
				return;
			}
		}

		String responseString = pushOrder2HJX(orderInfo);

		EbuyDeliveryPushRecorder ebuyDeliveryPushRecorder = new EbuyDeliveryPushRecorder();
		ebuyDeliveryPushRecorder.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_delivery_push_recorder));
		ebuyDeliveryPushRecorder.setIspushSuccess(JSON.parseObject(responseString).getIntValue("code") == 0 ? 1 : 0);
		ebuyDeliveryPushRecorder.setPushContent(JSON.toJSONString(orderInfo));
		ebuyDeliveryPushRecorder.setPushResult(responseString);
		ebuyDeliveryPushRecorder.settEbuySupplyMerchantFId(EbuyConstant.EbuySupplyMerchant_Id.Spl_Merchant_HJX_Id);
		ebuyDeliveryPushRecorder.settEbuyDeliveryOrderFId(new BigInteger(orderInfo.getEdoId()));
		ebuyDeliveryPushRecorderBaseDao.insertEbuyDeliveryPushRecorder(ebuyDeliveryPushRecorder);
	}
	
	//减少拼购库存
	private synchronized void checkLeftCount(BigInteger productId,long count,Integer pointType,
			BigInteger orderId){
		Map<String, Object> proMap = new HashMap<String, Object>();
		proMap.put("orderId", orderId);
		proMap.put("fightProductCount", count);
		groupPurchaseDao.updateFightCounts(proMap);
	}

}
