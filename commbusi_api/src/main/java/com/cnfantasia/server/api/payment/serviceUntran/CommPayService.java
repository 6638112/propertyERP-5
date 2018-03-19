/**   
* Filename:    CommPayService.java   
* @version:    1.0  
* Create at:   2014年12月9日 上午3:08:19   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.payment.serviceUntran;

import com.cnfantasia.server.api.access.service.AccessService;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.carMsgTask.dao.ICarMsgDao;
import com.cnfantasia.server.api.commonBusiness.service.ICommonEbuyService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRedenvelopeService;
import com.cnfantasia.server.api.dredge.dao.DredgeDao;
import com.cnfantasia.server.api.dredge.service.DredgeService;
import com.cnfantasia.server.api.ebuy.dao.IEbuyDao;
import com.cnfantasia.server.api.ebuy.entity.EbuyOrderEntity;
import com.cnfantasia.server.api.ebuy.service.IEbuyFilmTicketService;
import com.cnfantasia.server.api.ebuy.util.FlowRechargePool;
import com.cnfantasia.server.api.flashDealActivity.constant.FlashDealActivityDict;
import com.cnfantasia.server.api.homeMessage.constant.HomeMessageDict;
import com.cnfantasia.server.api.homeMessage.service.IHomeMessageService;
import com.cnfantasia.server.api.livingPay.dao.LivingPayDao;
import com.cnfantasia.server.api.livingPay.service.LivingPayService;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.payment.entity.OrderPayInfo;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyDict;
import com.cnfantasia.server.api.plotproperty.entity.PayBillEntity;
import com.cnfantasia.server.api.plotproperty.entity.PlotpropertyOrderEntity;
import com.cnfantasia.server.api.plotproperty.service.IPlotpropertyService;
import com.cnfantasia.server.api.plotproperty.util.PayBillShowUtil;
import com.cnfantasia.server.api.propertycard.service.PropertyCardService;
import com.cnfantasia.server.api.room.entity.RealRoomEntity;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.room.service.IRoomService;
import com.cnfantasia.server.api.room.util.RoomEntityConvertUtil;
import com.cnfantasia.server.api.roomVerifyPayment.dao.IRoomVerifyPaymentDao;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.commbusi.plotproperty.entity.BusinessMonthYearFactory;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.devicePayCount.entity.DevicePayCount;
import com.cnfantasia.server.domainbase.devicePayCount.service.IDevicePayCountBaseService;
import com.cnfantasia.server.domainbase.ebuyOrder.dao.IEbuyOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.dao.IEbuyOrderHasTPayBillBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.entity.EbuyOrderHasTPayBill;
import com.cnfantasia.server.domainbase.ebuyOrderRelation.dao.IEbuyOrderRelationBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrderRelation.entity.EbuyOrderRelation;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.dao.IEbuySupplyMerchantBaseDao;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.flashDealActivity.entity.FlashDealActivity;
import com.cnfantasia.server.domainbase.flashDealActivity.service.IFlashDealActivityBaseService;
import com.cnfantasia.server.domainbase.flashDealBuyRecord.entity.FlashDealBuyRecord;
import com.cnfantasia.server.domainbase.flashDealBuyRecord.service.IFlashDealBuyRecordBaseService;
import com.cnfantasia.server.domainbase.payBill.dao.IPayBillBaseDao;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payCoupon.entity.PayCoupon;
import com.cnfantasia.server.domainbase.payCoupon.service.IPayCouponBaseService;
import com.cnfantasia.server.domainbase.propertyCompany.dao.IPropertyCompanyBaseDao;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.domainbase.propertyRechargePrefer.dao.IPropertyRechargePreferBaseDao;
import com.cnfantasia.server.domainbase.propertyRechargePrefer.entity.PropertyRechargePrefer;
import com.cnfantasia.server.domainbase.roomVerifyPayment.entity.RoomVerifyPayment;
import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;
import com.cnfantasia.server.domainbase.userPayCount.entity.UserPayCount;
import com.cnfantasia.server.domainbase.userPayCount.service.IUserPayCountBaseService;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Filename:    CommPayService.java
 * @version:    1.0.0
 * Create at:   2014年12月9日 上午3:08:19
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月9日       shiyl             1.0             1.0 Version
 */
public class CommPayService implements ICommPayService{
	@Resource
	private IEbuyDao ebuyDao;
	@Resource
	private IEbuyFilmTicketService ebuyFilmTicketService;
	@Resource
	private IPlotpropertyService plotpropertyService;
	@Resource
	private IEbuyOrderBaseDao ebuyOrderBaseDao;
	@Resource
	private ICommonEbuyService commonEbuyService;
	@Resource
	private DredgeService dredgeService;
	@Resource
	private DredgeDao dredgeDao;
	@Resource
	private LivingPayDao livingPayDao;
	@Resource
	private AccessService accessService;
	@Resource
	private PropertyCardService propertyCardService;
	@Resource
	private IRoomVerifyPaymentDao roomVerifyPaymentDao;
	@Resource
	private IPayBillBaseDao payBillBaseDao;
	@Resource
	private IEbuySupplyMerchantBaseDao ebuySupplyMerchantBaseDao;
	@Resource
	private IPropertyCompanyBaseDao propertyCompanyBaseDao;
	@Resource
    private IRoomService roomService;
	@Resource
	private IDevicePayCountBaseService devicePayCountBaseService;
	@Resource
	private IUserPayCountBaseService userPayCountBaseService;
	@Resource
	private IUuidManager uuidManager;
	@Resource
	private IEbuyOrderHasTPayBillBaseDao ebuyOrderHasTPayBillBaseDao;
	@Resource
	private IFlashDealBuyRecordBaseService flashDealBuyRecordBaseService;
	@Resource
	private IFlashDealActivityBaseService flashDealActivityBaseService;
	@Resource
	private ICommonRedenvelopeService commonRedenvelopeService;
	@Resource
	private IPayCouponBaseService payCouponBaseService;
	@Resource
	private ICarMsgDao carMsgDao;
	@Resource
	private IHomeMessageService homeMessageService;
	@Resource
	private IEbuyOrderRelationBaseDao ebuyOrderRelationBaseDao;
	@Resource
	private IPropertyRechargePreferBaseDao propertyRechargePreferBaseDao;
	@Resource
	LivingPayService livingPayService;

	private Log logger = LogFactory.getLog(getClass());

	public OrderPayInfo getOrderPayInfoById(BigInteger userId,BigInteger orderId){
		EbuyOrder orderBaseInfo = ebuyOrderBaseDao.selectEbuyOrderBySeqId(orderId);
		if (orderBaseInfo == null) {
			throw new BusinessRuntimeException("EbuyPayService.weixinPrePay.order.isNull");
		}
		if(orderBaseInfo.getClientPayStatus()!=null&&EbuyDict.EbuyOrder_ClientPayStatus.Client_Pay_Success.compareTo(orderBaseInfo.getClientPayStatus())==0){
			throw new BusinessRuntimeException("CommPayService.getOrderPayInfoById.order.clientPay");
		}
		
		StringBuilder productInfo = new StringBuilder();
		Long totalAmount = null;
		String outTradeNo = null;
		if (orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.EBuy_Product) == 0) {// 订单类型电商
			throw new BusinessRuntimeException("EbuyPayService.weixinPrePay.order.isStop");

			/*EbuyOrderEntity ebuyOrderEntity = ebuyDao.getEbuyOrderEntityDetail(userId, orderId,null,null);
			if (ebuyOrderEntity == null) {
				throw new BusinessRuntimeException("EbuyPayService.weixinPrePay.order.isNull");
			}
			// 购物格式：{超市名称}商品名称[购物费]，例如：{佳佳乐}矿泉水1升[购物费]
			String payNoteForEbuy = getPayNoteForEbuy(ebuyOrderEntity, orderBaseInfo);
			productInfo.append(payNoteForEbuy);

			totalAmount = ebuyOrderEntity.getAmount();
			outTradeNo = ebuyOrderEntity.getOrderNo();

			// 电影票处理，预支付时锁定购买的电影票。30分钟内未付款成功可以被其它人购买。
			ebuyFilmTicketService.processTicketForPreSubmit(orderId.longValue());*/
		} else if (orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.Property_Bill) == 0) {// 订单类型物业账单
			PlotpropertyOrderEntity plotpropertyOrder = plotpropertyService.getPlotpropertyOrderDetail(userId, orderId);
			if (plotpropertyOrder == null) {
				throw new BusinessRuntimeException("PropertyPayService.weixinPrePay.order.isNull");
			}
			PayBillEntity payBillEntity = plotpropertyOrder.getPayBillEntity();
			if (payBillEntity == null) {
				throw new BusinessRuntimeException("PropertyPayService.weixinPrePay.payBill.isNull");
			}
			RealRoomEntity realRoomEntity = plotpropertyOrder.getPayBillEntity().getRealRoomEntity();
			if (realRoomEntity == null
					|| realRoomEntity.getBuildingEntity() == null
					|| realRoomEntity.getBuildingEntity().getGroupBuildingEntity() == null
					|| realRoomEntity.getBuildingEntity().getGroupBuildingEntity().getAddressBlockEntity() == null
					|| realRoomEntity.getBuildingEntity().getGroupBuildingEntity().getAddressBlockEntity().getAddressCityEntity() == null) {
				throw new BusinessRuntimeException("PropertyPayService.PrePay.realRoomInfo.lost");
			}

			// 缴物业费格式：{物业公司名称}小区楼栋单元门牌，缴费期间[物业费]
			// 缴其他代收费格式：{物业公司名称}小区楼栋单元门牌，缴费期间[其他代收费]
			String payNoteForWy = getPayNoteForWy(payBillEntity, realRoomEntity, false);
			productInfo.append(payNoteForWy);
			
			totalAmount = plotpropertyOrder.getAmount();
			outTradeNo = plotpropertyOrder.getOrderNo();
		} else if (orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.Dredge_Bill) == 0) {// 订单类型--疏通服务
			// 维修格式：{师傅名称}服务类型[维修费]
			String payNoteForRepair = dredgeDao.qryProductInfoByOrderId(orderId);
			productInfo.append(payNoteForRepair);
			totalAmount = orderBaseInfo.getAmount();
			outTradeNo = orderBaseInfo.getOrderNo();
		}else if (orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.Living_Fee_Bill) == 0) {// 订单类型--生活缴费
			/*// 生活缴费格式：缴费类型[生活缴费]
			String livingPayInfo = livingPayDao.qryProductInfoByOrderId(orderId);
			productInfo.append(livingPayInfo);
			totalAmount = orderBaseInfo.getAmount();
			outTradeNo = orderBaseInfo.getOrderNo();*/
			throw new BusinessRuntimeException("EbuyPayService.weixinPrePay.order.isNull");
		} else if(orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.CarKey_Bill) == 0){// 订单类型--车禁缴费
			// {物业公司名称}车牌，缴费期间[停车费]
			String payNoteForParking = accessService.getPayNoteWithCarByOrderId(orderBaseInfo.getId());
			productInfo.append(payNoteForParking);
			totalAmount = orderBaseInfo.getAmount();
			outTradeNo = orderBaseInfo.getOrderNo();
		} else if(orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.DoorKey_Bill) == 0){// 订单类型--门禁缴费
			String gbName = accessService.getGbNameByOrderId(orderBaseInfo.getId());
			productInfo.append(gbName).append("门禁缴费");
			totalAmount = orderBaseInfo.getAmount();
			outTradeNo = orderBaseInfo.getOrderNo();
		} else if(orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.Property_Card_Bill) == 0){// 订单类型--物业划扣卡
			/*// 物业划扣卡：{物业公司名称}小区楼栋单元门牌[物业代扣卡]
			{
				RoomEntityWithValidate rewv = roomService.getDefaultRoomInfo(userId);
			    RealRoomEntity realRoomEntity = rewv.getRealRoomEntity();
			    String payNoteForCardBill = getPayNoteForWy(null, realRoomEntity, true);
			    productInfo.append(payNoteForCardBill);
			}
			totalAmount = orderBaseInfo.getAmount();
			outTradeNo = orderBaseInfo.getOrderNo();*/
			throw new BusinessRuntimeException("EbuyPayService.weixinPrePay.order.isNull");
		} else if(orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.Flash_Deal_Activity) == 0){// 订单类型--一元夺宝
			FlashDealBuyRecord record = new FlashDealBuyRecord();
			record.settEbuyOrderFId(orderId);
			List<FlashDealBuyRecord> recordList = flashDealBuyRecordBaseService.getFlashDealBuyRecordByCondition(MapConverter.toMap(record));
			FlashDealActivity activity = flashDealActivityBaseService.getFlashDealActivityBySeqId(recordList.get(0).gettFlashDealActivityFId());
			productInfo.append(activity.getTitle());

			totalAmount = orderBaseInfo.getAmount();
			outTradeNo = orderBaseInfo.getOrderNo();
		} else if (orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.Limit_Buy_Activity) == 0) {
			EbuyOrderEntity ebuyOrderEntity = ebuyDao.getEbuyOrderEntityDetail(userId, orderId,null,null);
			if (ebuyOrderEntity == null) {
				throw new BusinessRuntimeException("EbuyPayService.weixinPrePay.order.isNull");
			}
			String payNoteForEbuy = getPayNoteForEbuy(ebuyOrderEntity, orderBaseInfo);
			productInfo.append("[限时抢购]").append(payNoteForEbuy);

			totalAmount = orderBaseInfo.getAmount();
			outTradeNo = orderBaseInfo.getOrderNo();
		}  else if (orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.Total_Property_Bill) == 0) {// 物业缴费总账单
			// 缴物业费格式：{物业公司名称}小区楼栋单元门牌{[2016年3月-2016年6月物业费][2017年3月水电费][粤B1234B停车费][粤B1235A停车费]}
			EbuyOrder ebuyOrder = ebuyOrderBaseDao.selectEbuyOrderBySeqId(orderId);
			totalAmount = ebuyOrder.getAmount();
			outTradeNo = ebuyOrder.getOrderNo();
			
			List<String> orderInfos = plotpropertyService.getPlotpropertyTotalOrderDetail(orderId);
			productInfo.append(plotpropertyService.getOrderAddress(ebuyOrder.getCurrRoomId())).append("{");
			if(orderInfos!=null && orderInfos.size()>0){
				for(String orderInfo:orderInfos){
					productInfo.append("[").append(orderInfo).append("]");
				}
			}
			productInfo.append("}");
		} else {
			throw new BusinessRuntimeException("CommPayService.orderType.undefined", new Object[]{orderBaseInfo.getType()});
		}
		
		String payNote = productInfo.toString().replaceAll(" ", "");
		payNote = dealPayBodyLength(payNote);
		String productDetail = payNote;
		OrderPayInfo resOrderPayInfo = new OrderPayInfo(orderId, payNote, totalAmount, outTradeNo, productDetail, orderBaseInfo.getType());
		return resOrderPayInfo;
		//throw new BusinessRuntimeException("非常抱歉，支付业务已关闭");
	}
	
    //TODO 支付失败，若使用了折扣，则将对应的折扣由已使用标记为未使用
	@Override
    @Transactional(propagation= Propagation.NESTED)
	public void paySuccessOperate(BigInteger orderId,Integer payMethod) {
		EbuyOrder ebuyOrder = ebuyOrderBaseDao.selectEbuyOrderBySeqId(orderId);// 查询订单基本信息

		//往redis放入支付宝支付成功回调时的订单号，用于返回客户端此时服务端的支付回调状态；过期时间60秒
		RedisCacheHandler.set(orderId.toString(), orderId.toString(), 60);
		
		synchronized (orderId) {
			String ebuyOrderIdKey = RedisCachePrefix.Ebuy_Order_Id + orderId.toString();
			if(RedisCacheHandler.get(ebuyOrderIdKey) != null){
				// 2秒之内回调过一次，直接返回，避免多次回调发生的问题
				return;
			}
			RedisCacheHandler.set(ebuyOrderIdKey, orderId.toString(), 10);
		}
		if(ebuyOrder.getType().compareTo(EbuyDict.EbuyOrder_Type.Total_Property_Bill) == 0){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("parentId", orderId);
			List<EbuyOrderRelation> ebuyOrderRelations = ebuyOrderRelationBaseDao.selectEbuyOrderRelationByCondition(paramMap, false);
			for(EbuyOrderRelation ebuyOrderRelation : ebuyOrderRelations){
				dealSubOrder(ebuyOrderRelation.getSubId(), null, payMethod);
			}
			
			dealParentOrder(ebuyOrder, payMethod);

			//更新选择周期数据详细信息  物业费起始时间
			plotpropertyService.updateAlterPeriodDataDetailAfterPaySuccess(orderId);
		} else {
			dealSubOrder(orderId, ebuyOrder, payMethod);
		}
		
	}

	/**
	 * 更新粮票
	 * @param orderId
	 * @param userId
     */
	private void updateJFB(BigInteger orderId, BigInteger userId) {
		//实际支付粮票 = 订单总优惠金额 - 账单随机立减金额；
		HashMap<String, Object> paraMap02 = new HashMap<String, Object>();
		paraMap02.put("tEbuyOrderFId",orderId);
		List<PayCoupon> payCouponList = payCouponBaseService.getPayCouponByCondition(paraMap02);
		if(payCouponList != null && payCouponList.size() > 0) {//使用粮票了才进行粮票信息更新
			PayCoupon payCoupon = payCouponList.get(0);
			Integer resCount = commonRedenvelopeService.updateRedenvelopeByUserId(userId, payCoupon.getAmount(), orderId);
			if (resCount == null || resCount <= 0) {
				throw new BusinessRuntimeException("粮票更新失败！");
			}
		}
	}
	
	/**
	 * 获取支付备注（电商商品）
	 *     购物格式：{超市名称}商品名称[购物费]，例如：{佳佳乐}矿泉水1升[购物费]
	 * @param ebuyOrderEntity
	 * @param orderBaseInfo
	 * @return
	 */
	private String getPayNoteForEbuy(EbuyOrderEntity ebuyOrderEntity, EbuyOrder orderBaseInfo){
		StringBuilder payNote = new StringBuilder();
		
		EbuyProduct ebuyProductEntity = ebuyOrderEntity.getEbuyOrderHasTEbuyProductEntity_ProductList().get(0).getEbuyProductEntity();
		BigInteger supplyMerchantId = ebuyProductEntity.gettSupplyMerchantFId();
		if(supplyMerchantId!=null){
			EbuySupplyMerchant ebuySupplyMerchant = ebuySupplyMerchantBaseDao.selectEbuySupplyMerchantBySeqId(supplyMerchantId);
			if(ebuySupplyMerchant!=null){
				payNote.append("{").append(ebuySupplyMerchant.getName()).append("}");
			}
		}
		payNote.append(ebuyProductEntity.getName());
		String amount_yuan = new DecimalFormat("0.00").format(orderBaseInfo.getAmount()*1.0/100);
		payNote.append("[￥ ").append(amount_yuan).append("]");
		
		return payNote.toString();
	}
	
	/**
	 * 获取支付备注（物业缴费、其它代收费用、代扣卡缴费）
	 *    缴物业费格式：        {物业公司名称}小区楼栋单元门牌，缴费期间[物业费]
     *    缴其他代收费格式：{物业公司名称}小区楼栋单元门牌，缴费期间[其他代收费]
	 *    物业划扣卡：            {物业公司名称}小区楼栋单元门牌[物业代扣卡]
	 * 
	 * @param payBillEntity
	 * @param realRoomEntity
	 * @param isPropertyCardBill 是否是代扣卡缴费
	 * @return
	 */
	private String getPayNoteForWy(PayBillEntity payBillEntity, RealRoomEntity realRoomEntity, boolean isPropertyCardBill){
		StringBuilder payNote = new StringBuilder();
		
		BigInteger propertyCompanyId = realRoomEntity.getBuildingEntity().getGroupBuildingEntity().gettPropertyCompanyFId();
		if(propertyCompanyId!=null){
			PropertyCompany propertyCompany = propertyCompanyBaseDao.selectPropertyCompanyBySeqId(propertyCompanyId);
			if(propertyCompany!=null){
				payNote.append("{").append(propertyCompany.getName()).append("}");
			}
		}
		
		payNote.append(realRoomEntity.getBuildingEntity().getGroupBuildingEntity().getName());
		payNote.append(RoomEntityConvertUtil.getBuildingShowName(realRoomEntity.getBuildingEntity()));
		payNote.append(RoomEntityConvertUtil.getRealRoomShowName(realRoomEntity));
		if(isPropertyCardBill){
			payNote.append("[物业代扣卡]");
		} else {
			payNote.append(",");
			
			if(payBillEntity.getIsPropFee().compareTo(PlotpropertyDict.PayBillType_IsPropFee.YES)==0
					&&payBillEntity.getPaytimeType().compareTo(PlotpropertyDict.PayBillType_PaytimeType.MONTH)==0){
				payNote.append(DateUtil.strFormate(PayBillShowUtil.getBillShowMonth(payBillEntity), DateUtil.formatSecond.get(), DateUtil.formatOnlyYear.get()));
				payNote.append("年");
				payNote.append(DateUtil.strFormate(PayBillShowUtil.getBillShowMonth(payBillEntity), DateUtil.formatSecond.get(), DateUtil.formatOnlyMonth.get()));
				payNote.append("月[物业费]");
			}else{
				IBusinessMonthYear bmy = BusinessMonthYearFactory.createByPayBill(payBillEntity, realRoomEntity.getBuildingEntity().getGroupBuildingEntity());
				payNote.append(bmy.getBillMonth().getPeriodDesc());
				payNote.append(payBillEntity.getBillTypeName());
				payNote.append("[其他代收费]");
			}
		}
		
		return  payNote.toString();
	}
	
	/**
	 * 字符长度处理
	 * @param s
	 * @return
	 */
	private String dealPayBodyLength(String s){
		int limit = 40;
		if(s!=null && s.length()>=limit){
			return s.substring(0, limit-3) + "...";
		}
		return s;
	}
	
	/**
	 * 一键（物业）缴费订单处理
	 * @param ebuyOrder
	 * @param payMethod
	 */
	private void dealParentOrder(EbuyOrder ebuyOrder, Integer payMethod){
		String nowTime = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
		ebuyOrder.setSys0UpdTime(nowTime);
		ebuyOrder.setPayStatus(EbuyDict.EbuyOrder_PayStatus.Pay_Success);//支付成功
		ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.YiWanCheng);//已完成
		ebuyOrder.setPayTime(nowTime);//设定支付成功时间
		ebuyOrder.setPayMethod(String.valueOf(payMethod));
		
		int res = ebuyOrderBaseDao.updateEbuyOrder(ebuyOrder);
		if (res <= 0) {
			throw new BusinessRuntimeException("CommonEbuyService.paySuccessOperateComm.updateEbuyOrder.failed");
		}
	}
	
	/**
	 * 子账单（非物业总账单）处理
	 * 
	 * @param subOrderId
	 * @param orderBaseInfo
	 * @param payMethod
	 * @param isSubOrder
	 */
	private void dealSubOrder(BigInteger subOrderId, EbuyOrder orderBaseInfo, Integer payMethod){
		if(orderBaseInfo==null){
			orderBaseInfo = ebuyOrderBaseDao.selectEbuyOrderBySeqId(subOrderId);// 查询订单基本信息
		}
		if (orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.EBuy_Product) == 0) {// 订单类型电商
			commonEbuyService.paySuccessOperateComm(subOrderId, payMethod);
			//把订单号放入充值流量的缓存中，查看是否需要进行充值
			FlowRechargePool.addOrderIdForRecharge(subOrderId.toString());
		} else if (orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.Property_Bill) == 0) {// 订单类型物业账单
			// 若支付成功，回填 f_is_pay和f_succ_pay_amount 和 f_payment_way
			//查询账单订单关系
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("tEbuyOrderFId",subOrderId);
			EbuyOrderHasTPayBill ebuyOrderHasTPayBill = ebuyOrderHasTPayBillBaseDao.selectEbuyOrderHasTPayBillByCondition(paraMap,false).get(0);
			PlotpropertyOrderEntity plotpropertyOrder = plotpropertyService.getPlotpropertyOrderDetailByOrderIdAndBillId(subOrderId, ebuyOrderHasTPayBill.gettPayBillFId());
			if (plotpropertyOrder == null) {
				throw new BusinessRuntimeException("PropertyPayService.paySuccessOpt.order.isNull");
			}
			if (plotpropertyOrder.getEbuyOrderHasTPayBill() == null) {
				throw new BusinessRuntimeException("PropertyPayService.paySuccessOpt.orderHasTPayBill.isNull");
			}
			PayBillEntity payBillEntity = plotpropertyOrder.getPayBillEntity();
			if (payBillEntity == null) {
				throw new BusinessRuntimeException("PropertyPayService.paySuccessOpt.payBill.isNull");
			}
			Long hbAmount = 0L;
			if(plotpropertyOrder.getTotalCouponAmount()!=null){//基础账单费用只有粮票优惠
				hbAmount = plotpropertyOrder.getTotalCouponAmount();
			}
			BigInteger userId = plotpropertyOrder.getBuyerId();
			{// 更新账单信息
				Integer resCount = plotpropertyService.updatePayBillInfoSuccessByApp(payBillEntity.getId(), plotpropertyOrder
						.getAmount(), plotpropertyOrder.getEbuyOrderHasTPayBill().getDiscount(), plotpropertyOrder
						.getEbuyOrderHasTPayBill().getPrizeRecordId(),hbAmount,userId,payBillEntity.getCycleType());
				if (resCount == null || resCount <= 0) {
					throw new BusinessRuntimeException("PropertyPayService.paySuccessOpt.payBill.updPayBillInfo.failed");
				}
			}
			{// 更新t_room_verify_payment缴费状态
				PayBill payBill = payBillBaseDao.selectPayBillBySeqId(payBillEntity.getId());
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("realRoomId", payBill.gettRealRoomFId());

				List<RoomVerifyPayment> roomVerifyPayments = roomVerifyPaymentDao.selectRoomVerifyPaymentsWithNoPay(paramMap);
				if(roomVerifyPayments!=null && roomVerifyPayments.size()>0){
					for(RoomVerifyPayment roomVerifyPayment:roomVerifyPayments){
						roomVerifyPayment.setPayState(1);
						roomVerifyPayment.setPayTime(payBill.getSys0UpdTime());
					}
					roomVerifyPaymentDao.updateRoomVerifyPaymentBatch(roomVerifyPayments);
				}
			}
			updatePropertyRechargePrefer(userId, payBillEntity);

			//更新粮票信息
			updateJFB(subOrderId, userId);

			commonEbuyService.paySuccessOperateComm(subOrderId, payMethod);

			//更新设备和用户的缴费次数
			try {
				new UpdateUserAndDevicePayNum(plotpropertyOrder.getBuyerId(), plotpropertyOrder.getDeviceId(), userPayCountBaseService, devicePayCountBaseService,uuidManager).start();
			} catch (Exception e) {
				logger.info("更新设备和用户的缴费次数异常:" + e.getMessage());
			}
		} else if (orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.Dredge_Bill) == 0) {// 订单类型--疏通服务账单
			dredgeService.updateDredgeBillAfterPaySuccess(subOrderId);
			commonEbuyService.paySuccessOperateComm(subOrderId, payMethod);
		} else if (orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.Living_Fee_Bill) == 0){
			livingPayService.updateDredgeBillAfterPaySuccess(subOrderId);
			commonEbuyService.paySuccessOperateComm(subOrderId, payMethod);
		} else if(orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.CarKey_Bill) == 0){// 订单类型--车禁缴费
			accessService.updateCarKeyBill(subOrderId);
			commonEbuyService.paySuccessOperateComm(subOrderId, payMethod);
			//更新车禁首页消息
			Integer carTimeoutCount = carMsgDao.qryTimeoutInMonthCount(orderBaseInfo.getBuyerId());
			if (carTimeoutCount > 0) {
				UserHasHomeMessage message = new UserHasHomeMessage();
				message.setMessageCode(HomeMessageDict.MessageCode.CAR_TIMEOUT_ALERT);
				message.setContent("您有月卡即将到期");
				message.setExtInfo(carTimeoutCount + "张月卡即将到期");
				message.setUserId(orderBaseInfo.getBuyerId());
				message.settRoomFId(orderBaseInfo.getCurrRoomId());
				homeMessageService.generateCommonMsg(message);
			} else {
				UserHasHomeMessage message = new UserHasHomeMessage();
				message.setMessageCode(HomeMessageDict.MessageCode.CAR_TIMEOUT_ALERT);
				message.setUserId(orderBaseInfo.getBuyerId());
				message.settRoomFId(orderBaseInfo.getCurrRoomId());
				List<UserHasHomeMessage> messageList = new ArrayList<>();
				messageList.add(message);
				homeMessageService.delUserHomeMsgByParam(messageList);
			}
		}else if(orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.DoorKey_Bill) == 0){// 订单类型--门禁缴费
			accessService.updateDoorKeyBill(subOrderId);
			commonEbuyService.paySuccessOperateComm(subOrderId, payMethod);
		}else if(orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.Property_Card_Bill) == 0){
			propertyCardService.updateBillAfterPaySuccess(subOrderId);
			commonEbuyService.paySuccessOperateComm(subOrderId, payMethod);
		} else if(orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.Flash_Deal_Activity) == 0){//一元夺宝
			//更新购买记录
			FlashDealBuyRecord record = new FlashDealBuyRecord();
			record.settEbuyOrderFId(subOrderId);
			List<FlashDealBuyRecord> recordList = flashDealBuyRecordBaseService.getFlashDealBuyRecordByCondition(MapConverter.toMap(record));
			if (!DataUtil.isEmpty(recordList)) {
				for (FlashDealBuyRecord flashDealBuyRecord : recordList) {
					flashDealBuyRecord.setRecordStatus(FlashDealActivityDict.BuyRecord.Alread_Pay);
				}
				flashDealBuyRecordBaseService.updateFlashDealBuyRecordBatch(recordList);
			}

			//更新粮票信息
			updateJFB(subOrderId, orderBaseInfo.getBuyerId());
			commonEbuyService.paySuccessOperateComm(subOrderId, payMethod);
		} else if(orderBaseInfo.getType().compareTo(EbuyDict.EbuyOrder_Type.Limit_Buy_Activity) == 0){//限时抢购
			commonEbuyService.paySuccessOperateComm(subOrderId, payMethod);
		}
	}
	
	/**
	 * 更新物业充值的随机立减
	 * @param userId
	 * @param payBillEntity
	 */
	private void updatePropertyRechargePrefer(BigInteger userId, PayBillEntity payBillEntity) {
		if(payBillEntity.gettGroupBuildingBillCycleConfigFId()!=null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tGbbccId", payBillEntity.gettGroupBuildingBillCycleConfigFId());
			paramMap.put("tUserId", userId);
			paramMap.put("tRealRoomId", payBillEntity.gettRealRoomFId());
			paramMap.put("amount", payBillEntity.getAmount());
			
			List<PropertyRechargePrefer> propertyRechargePreferList = propertyRechargePreferBaseDao.selectPropertyRechargePreferByCondition(paramMap, false);
			if (propertyRechargePreferList != null && propertyRechargePreferList.size() > 0) {
				PropertyRechargePrefer propertyRechargePrefer = propertyRechargePreferList.get(0);
				propertyRechargePrefer.settPaybillId(payBillEntity.getId());
				propertyRechargePrefer.setPayState(1);
				
				propertyRechargePreferBaseDao.updatePropertyRechargePrefer(propertyRechargePrefer);
			}
		}
	}
}

class UpdateUserAndDevicePayNum extends Thread {

	private BigInteger userId;
	private String deviceId;
	private IUserPayCountBaseService userPayCountBaseService;
	private IDevicePayCountBaseService devicePayCountBaseService;
	private IUuidManager uuidManager;

	public UpdateUserAndDevicePayNum(BigInteger userId, String deviceId,IUserPayCountBaseService userPayCountBaseService,IDevicePayCountBaseService devicePayCountBaseService,IUuidManager uuidManager) {
		this.userId = userId;
		this.deviceId = deviceId;
		this.userPayCountBaseService = userPayCountBaseService;
		this.devicePayCountBaseService = devicePayCountBaseService;
		this.uuidManager = uuidManager;
	}

	@Override
	public void run() {
		Map<String,Object> userMap = new HashMap<String, Object>();
		userMap.put("tUserId",userId);
		List<UserPayCount> userList = userPayCountBaseService.getUserPayCountByCondition(userMap);
		if (userList!= null && userList.size() > 0) {
			UserPayCount userPayCount = userList.get(0);
			userPayCount.setNum(userPayCount.getNum() + 1);
			userPayCount.setSys0UpdTime(DateUtils.getCurrentDate());
			userPayCountBaseService.updateUserPayCount(userPayCount);
		} else {
			UserPayCount userPayCount = new UserPayCount();
			userPayCount.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_user_pay_count));
			userPayCount.settUserId(userId);
			userPayCount.setNum(1);
			userPayCount.setSys0AddTime(DateUtils.getCurrentDate());
			userPayCountBaseService.insertUserPayCount(userPayCount);
		}

		Map<String,Object> deviceMap = new HashMap<String, Object>();
		deviceMap.put("deviceId",deviceId);
		List<DevicePayCount> deviceList = devicePayCountBaseService.getDevicePayCountByCondition(deviceMap);
		if (deviceList != null && deviceList.size() > 0) {
			DevicePayCount devicePayCount = deviceList.get(0);
			devicePayCount.setNum(devicePayCount.getNum() + 1);
			devicePayCount.setSys0UpdTime(DateUtils.getCurrentDate());
			devicePayCountBaseService.updateDevicePayCount(devicePayCount);
		} else {
			DevicePayCount devicePayCount = new DevicePayCount();
			devicePayCount.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_device_pay_count));
			devicePayCount.setDeviceId(deviceId);
			devicePayCount.setNum(1);
			devicePayCount.setSys0AddTime(DateUtils.getCurrentDate());
			devicePayCountBaseService.insertDevicePayCount(devicePayCount);
		}
	}
}
