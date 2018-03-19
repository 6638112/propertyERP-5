package com.cnfantasia.server.api.propertycard.service;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.dao.ICommonRoomDao;
import com.cnfantasia.server.api.ebuy.util.OrderNoGenerator;
import com.cnfantasia.server.api.homeMessage.service.IHomeMessageService;
import com.cnfantasia.server.api.login.dao.ILoginDao;
import com.cnfantasia.server.api.login.entity.LoginNoEntity;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyDict;
import com.cnfantasia.server.api.plotproperty.entity.PayBillEntity;
import com.cnfantasia.server.api.propertyAccountInfo.dao.IPropertyAccountInfoDao;
import com.cnfantasia.server.api.propertyAccountInfo.entity.PropertyAccountInfoEntity;
import com.cnfantasia.server.api.propertycard.constant.PropertyCardConstant;
import com.cnfantasia.server.api.propertycard.dao.PropertyCardDao;
import com.cnfantasia.server.api.propertycard.entity.RealRoomConfig;
import com.cnfantasia.server.api.propertycard.entity.RealRoomShowNameAndPayEnd;
import com.cnfantasia.server.api.propertycard.entity.TransLog;
import com.cnfantasia.server.api.propertycard.entity.UserHasCardWithGroupBuildingNames;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.room.entity.BuildingEntity;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.RealRoomEntity;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.roomVerifyPayment.dao.IRoomVerifyPaymentDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.commbusi.alterPeriod.dao.IAlterPeriodDao;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.HttpUtil;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.RandomUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyOrder.dao.IEbuyOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.payBill.dao.PayBillBaseDao;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payBillType.dao.IPayBillTypeBaseDao;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.propertyAccountInfo.entity.PropertyAccountInfo;
import com.cnfantasia.server.domainbase.propertyCard.dao.PropertyCardBaseDao;
import com.cnfantasia.server.domainbase.propertyCard.entity.PropertyCard;
import com.cnfantasia.server.domainbase.propertyCardDeductionDetail.dao.IPropertyCardDeductionDetailBaseDao;
import com.cnfantasia.server.domainbase.propertyCardDeductionDetail.entity.PropertyCardDeductionDetail;
import com.cnfantasia.server.domainbase.propertyCardDiscountTerm.dao.PropertyCardDiscountTermBaseDao;
import com.cnfantasia.server.domainbase.propertyCardDiscountTerm.entity.PropertyCardDiscountTerm;
import com.cnfantasia.server.domainbase.propertyCardTransactionFlowing.dao.PropertyCardTransactionFlowingBaseDao;
import com.cnfantasia.server.domainbase.propertyCardTransactionFlowing.entity.PropertyCardTransactionFlowing;
import com.cnfantasia.server.domainbase.propertyFeeDetail.dao.IPropertyFeeDetailBaseDao;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.domainbase.realRoom.dao.IRealRoomBaseDao;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.roomVerifyPayment.entity.RoomVerifyPayment;
import com.cnfantasia.server.domainbase.smsMq.entity.SmsMq;
import com.cnfantasia.server.domainbase.userHasPropertyCard.dao.UserHasPropertyCardBaseDao;
import com.cnfantasia.server.domainbase.userHasPropertyCard.entity.UserHasPropertyCard;
import com.cnfantasia.server.domainbase.userSetRealroomDeductionConfig.dao.UserSetRealroomDeductionConfigBaseDao;
import com.cnfantasia.server.domainbase.userSetRealroomDeductionConfig.entity.UserSetRealroomDeductionConfig;
import com.cnfantasia.server.ms.payBill.constant.PayBillDict;
import com.cnfantasia.server.notification.dao.NotificationDao;
import com.cnfantasia.server.notification.mq.constant.SMSMQConstant;
import com.propertySoftwareDock.base.dao.PropertySoftwareDockDao;
import com.propertySoftwareDock.base.entity.RealRoomSoftwareInfo;
import com.propertySoftwareDock.base.service.IPropertySoftwareDockService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

public class PropertyCardService {
	
	private static final String Fail_SMS_Template = "尊敬的【%s】住户，本期【%s】扣款不成功，原因：卡内余额不足，为保证扣款成功，请于【%s】前登陆解放区【%s 】进行充值。";
	
	private static final String Success_SMS_Template = "尊敬的【%s】住户，本期【%s】已扣款成功，扣款金额：【%.2f】元，卡内余额：%.2f元，感谢您的支持。";
	
    @Resource
    IUuidManager uuidManager;

    @Resource
    PropertyCardTransactionFlowingBaseDao propertyCardTransactionFlowingBaseDao;
	
	@Resource 
	NotificationDao notificationDao;

    @Resource
    IEbuyOrderBaseDao ebuyOrderBaseDao;

    @Resource
    PropertyCardBaseDao propertyCardBaseDao;

    @Resource
    UserHasPropertyCardBaseDao userHasPropertyCardBaseDao;

    @Resource
    IPropertyAccountInfoDao propertyAccountInfoDao;

    @Resource
    PropertyCardDiscountTermBaseDao propertyCardDiscountTermBaseDao;

    @Resource
    UserSetRealroomDeductionConfigBaseDao userSetRealroomDeductionConfigBaseDao;

    @Resource
    PayBillBaseDao payBillBaseDao;

    @Resource
    private PropertyCardDao propertyCardDao;
    
    @Resource
    ICommonRoomDao commonRoomDao;

	@Resource
	private IHomeMessageService homeMessageService;

    private Log logger = LogFactory.getLog(getClass());

    @Resource
    private IRoomVerifyPaymentDao roomVerifyPaymentDao;
    
    @Resource
    IPropertyCardDeductionDetailBaseDao propertyCardDeductionDetailBaseDao;

	@Resource
	PropertySoftwareDockDao propertySoftwareDockDao;
	
	@Resource
	IAlterPeriodDao alterPeriodDao;
	
	@Resource
	IPayBillTypeBaseDao payBillTypeBaseDao;

	@Resource
	private IPropertyFeeDetailBaseDao propertyFeeDetailBaseDao;
	
	@Resource
	IRealRoomBaseDao realRoomBaseDao;
	
	/**
	 * 单个小区触发划扣
	 * @param gbId 小区Id
	 */
	public void triggerDeducntion4GroupBuilding(BigInteger gbId){
		List<UserSetRealroomDeductionConfig>  userSetRealroomDeductionConfigList = propertyCardDao.qryDeductionConfigList(gbId);
		for(UserSetRealroomDeductionConfig dc: userSetRealroomDeductionConfigList){
			triggerDeducntion4SingleRoom(dc.gettRealRoomFId(), dc.gettUserFId());
		}
	}
	
	/**
	 * 所有小区触发划扣
	 * 如果当前日期是25号（可配），则全部买了代扣卡并有账单在缴费区内划扣
	 * 如果当前日期不是25号（可配），则划扣缴费日期截止日与当前时间差2天的账单
	 * @param
	 */
	@Transactional(propagation= Propagation.SUPPORTS)
	public void triggerDeducntion4GroupBuilding(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		Integer deducationDate = CnfantasiaCommbusiUtil.getSysParaValueInt(SysParamKey.PropertyCardDeducationDate, 25);
		//若是指定划扣日，要划扣所有房间；否则，只划扣过期前1天的账单下开启划扣的房间
		if(day == deducationDate) {
			paramMap.put("isAll", true);
		}
		
		List<UserSetRealroomDeductionConfig>  userSetRealroomDeductionConfigList = propertyCardDao.qryDeductionConfigAllList(paramMap);
		for(UserSetRealroomDeductionConfig dc: userSetRealroomDeductionConfigList){
			triggerDeducntion4SingleRoom(dc.gettRealRoomFId(), dc.gettUserFId());
		}
	}
	
	/**
	 * 自动验证门牌信息
	 */
	private void addValidateSuccessInfo(BigInteger realRoomId, BigInteger userId){
		try {
			HttpUtil httpUtil = new HttpUtil();
			httpUtil.addParameter("realRoomId", realRoomId+"");
			httpUtil.addParameter("userId", userId+"");
			String retStr = httpUtil.post(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Last_Api_BaseUrl) + "roomValidate/applyAutoValidate.json", 10000, "UTF-8");
			logger.info(retStr);
			logger.info("物业代扣卡划扣成功后，门牌自动验证通过成功！");
		} catch(Exception e) {
			logger.error("物业代扣卡划扣成功后，门牌自动验证通过失败！");
			logger.error(e.getMessage(), e);
		}
	}
	
	@Transactional(propagation= Propagation.REQUIRES_NEW)
	public void triggerDeducntion4SingleRoom(BigInteger realRoomId, BigInteger userId){
		this.triggerDeducntion4SingleRoom(null, realRoomId, userId);
	}
	
	/**
	 * 单个门牌触发划扣
	 * @param pci 已更新了余额的pci，若传入null，则需要去DB中取一次
	 * @param realRoomId 门牌id
	 * @param userId 用户id
	 */
	@Transactional
	public void triggerDeducntion4SingleRoom(PropertyAccountInfo propertyAccountInfo, BigInteger realRoomId, BigInteger userId){
		if (propertyAccountInfo == null) {
			PropertyAccountInfo propertyAccountInfoQry = new PropertyAccountInfo();
			propertyAccountInfoQry.settUserFId(userId);
			List<PropertyAccountInfo> propertyAccountInfoList = propertyAccountInfoDao
					.selectPropertyAccountInfoByCondition(MapConverter.toMap(propertyAccountInfoQry), false);
			if (propertyAccountInfoList.isEmpty()) {// 还没有买卡
				return;
			} else {
				propertyAccountInfo = propertyAccountInfoList.get(0);
			}
		}
		
		if (propertyAccountInfo.getBalanceAmt() <= 0)
			return; //因为还没有金额可以划扣
		
		//1处理 待缴 账单，不再区分月度和周期了 
		List<PayBill> payBillMonthList = propertyCardDao.qryPayBillByRealRoomId(realRoomId, true);
		if(payBillMonthList.size()>0){
			List<PayBill> unPaidPayBillMonthList = propertyCardDao.qryUnPaidPayBillByPayBillList(payBillMonthList, true);
			unPaidPayBillMonthList.addAll(payBillMonthList);
			for(PayBill pb : unPaidPayBillMonthList){
				try {//单个账单划扣失败不要影响下个账单划扣，所以需要捕获异常 added by wenfq 2017-12-22
					if (pb.getFinanceStatus() == null || pb.getFinanceStatus() == 0) { //没有物业宝抵扣
						if (propertyAccountInfo.getBalanceAmt() >= pb.getAmount()) {
							updateData4Deducntion(propertyAccountInfo, pb, userId);
							addValidateSuccessInfo(realRoomId, userId);
						} else {
							sendFailSMS(userId, propertyAccountInfo, pb);
						}
					} else if (pb.getFinanceStatus() == 1) {//有物业宝抵扣
						if (propertyAccountInfo.getBalanceAmt() >= (pb.getAmount() - pb.getDeduPrice())) {
							updateData4Deducntion(propertyAccountInfo, pb, userId);
							addValidateSuccessInfo(realRoomId, userId);
						} else {
							sendFailSMS(userId, propertyAccountInfo, pb);
						}
					}
				} catch (Exception e) {
					logger.error("property card deduction error... ");
					logger.error(e.getMessage(), e);
				}
			}
		}
		
/*		//2再处理周期账单
		List<PayBill> payBillPeriodList = propertyCardDao.qryPayBillPeriodByRealRoomId(realRoomId);
		for(PayBill pb: payBillPeriodList){
			// 优先划扣物业费
			if(pb.getPaytimeType() == PlotpropertyDict.PayBillType_IsPropFee.YES.intValue()){
				if (propertyAccountInfo.getBalanceAmt() >= pb.getAmount()) {
					updateData4Deducntion(propertyAccountInfo, pb, userId);
					addValidateSuccessInfo(realRoomId, userId);
				} else {
					sendFailSMS(userId, propertyAccountInfo, pb);
				}
			}
		}
		for(PayBill pb: payBillPeriodList){
			// 其次划扣其它费用
			if(pb.getPaytimeType() == PlotpropertyDict.PayBillType_IsPropFee.NO_notMR.intValue()){
				if (propertyAccountInfo.getBalanceAmt() >= pb.getAmount()) {
					updateData4Deducntion(propertyAccountInfo, pb, userId);
					addValidateSuccessInfo(realRoomId, userId);
				}else{
					sendFailSMS(userId, propertyAccountInfo, pb);
				}
			}
		}
 */
		
		//处理与第三方软件对接的账单划扣  added by wenfq 2016-11-24
		try{
			RealRoomSoftwareInfo realRoomSoftwareInfo = propertySoftwareDockDao.getRealRoomSoftwareInfo(realRoomId);
			if (realRoomSoftwareInfo != null) {
				if (!StringUtils.isEmpty(realRoomSoftwareInfo.getServiceClassName())) {
					IPropertySoftwareDockService propertySoftwareDockService = (IPropertySoftwareDockService) CnfantasiaCommbusiUtil.getBeanManager(realRoomSoftwareInfo.getServiceClassName());
					PayBillEntity payBill = propertySoftwareDockService.getPropertyFeeItems(realRoomId);
					if (payBill != null) {
						if (propertyAccountInfo.getBalanceAmt() >= payBill.getAmount()) {
							createPayBill(payBill, realRoomSoftwareInfo.getGbId(), realRoomId);
							createPropertyFeeDetail(payBill, payBill.getPropertyFeeDetailList());
							updateData4Deducntion(propertyAccountInfo, payBill, userId);
							addValidateSuccessInfo(realRoomId, userId);
						} else {
							sendFailSMS(userId, propertyAccountInfo, payBill);
						}
					}
				}
			}
		}catch (Exception e){
			logger.error("处理物业代扣卡划扣有异常");
			logger.error(e.getMessage(), e);
		}
	}

	/**
     * 确认付款, 支付前生成生支付订单，即获得orderId，并扣减粮票或锁定优惠券，然后APP调用预支付接口进行支付
     *
     * @param propertyCardId 物业代扣卡id
     * @param notifyPhone    通知手机号
     * @return orderId订单Id
     */
    @Transactional
    public BigInteger confirmPay(BigInteger propertyCardId, String notifyPhone, Integer subChannelId) {
        PropertyCard propertyCard = propertyCardBaseDao.selectPropertyCardBySeqId(propertyCardId);
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();

        BigInteger orderId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order);
        EbuyOrder ebuyOrder = new EbuyOrder();
        ebuyOrder.setType(EbuyDict.EbuyOrder_Type.Property_Card_Bill);
        ebuyOrder.setId(orderId);
        ebuyOrder.setBuyerId(userId);
        ebuyOrder.setCurrRoomId(UserContext.getCurrLoginNo().getUserEntity().getDefaultRoomId());
        ebuyOrder.setBuyTime(DateUtils.getCurrentDate());
        ebuyOrder.setCreater(userId);
        ebuyOrder.setOrderNo(OrderNoGenerator.getOrderNo(orderId));
        ebuyOrder.setPayMethod(null);
        ebuyOrder.setPayTime(null);
        ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.DaiFuKuan);
        ebuyOrder.setPayStatus(EbuyDict.EbuyOrder_PayStatus.Not_Pay);//支付状态为待支付
        ebuyOrder.setDelivStatus(EbuyDict.EbuyOrder_DelivStatus.Not_Deliv);//发货状态为未发货
        ebuyOrder.setTotalDeliveryFee(0L);//总配送费
        ebuyOrder.setAmount(propertyCard.getRealPayAmt()); //应付金额
        ebuyOrder.setTotalCouponAmount(0L); //优惠金额
        ebuyOrder.setDelivPhone(notifyPhone);
        ebuyOrder.setSubChannel(subChannelId == null ? null : subChannelId + "");

        {//借用配送的两个字段，后续生成交易流水和购买记录时使用
        	ebuyOrder.setDelivTargetId(propertyCardId);
            // UserContext.getUser().getLoginNoEntity() 若新增过门牌，默认门牌地址这样获取还是老的，不再这样使用，需要用loginDao从数据库中再取一次
        	//RoomEntityWithValidate rewv = UserContext.getUser().getLoginNoEntity().getUserEntity().getDefaultRoomEntity();
            
            ILoginDao loginDao = (ILoginDao) CnfantasiaCommbusiUtil.getBeanManager("loginDao");
            LoginNoEntity loginNoEntity = loginDao.selectLoginNoEntityByAccountSupportBind(userId+"", 2L);
            RoomEntityWithValidate  rewv = loginNoEntity.getUserEntity().getDefaultRoomEntity();
            
            BuildingEntity buildingEntity = rewv.getRealRoomEntity().getBuildingEntity();
            GroupBuildingEntity gb = buildingEntity.getGroupBuildingEntity();
            RealRoomEntity realRoomEntity = rewv.getRealRoomEntity();
            StringBuilder note = new StringBuilder();
            if(gb!=null && !StringUtils.isEmpty(gb.getName())){
            	note.append(gb.getName()).append("-");
            }
            if(org.apache.commons.lang.StringUtils.isNotBlank(buildingEntity.getName())){
            	note.append(buildingEntity.getName()).append("-");
            }
            if(realRoomEntity!=null && !StringUtils.isEmpty(realRoomEntity.getNum())){
            	note.append(realRoomEntity.getNum()).append("-");
            }
            note.append(PriceUtil.div100(propertyCard.getCardAmount())).append("元代扣卡充值");
            
            ebuyOrder.setDelivAddressDetail(note.toString());
        }

        // 新增订单
        int res = ebuyOrderBaseDao.insertEbuyOrder(ebuyOrder);
        if (res <= 0) {
            throw new BusinessRuntimeException("Plotproperty.confirmPayBill.insertEbuyOrder.error");
        }

        return ebuyOrder.getId();
    }

    /**
     * 支付成功后更新相关单据状态
     */
    @Transactional
    public void updateBillAfterPaySuccess(BigInteger orderId) {
        EbuyOrder ebuyOrder = ebuyOrderBaseDao.selectEbuyOrderBySeqId(orderId);
        PropertyCard propertyCard = propertyCardBaseDao.selectPropertyCardBySeqId(ebuyOrder.getDelivTargetId());

        BigInteger userId = ebuyOrder.getBuyerId();
        String notifyPhone = ebuyOrder.getDelivPhone();

        UserHasPropertyCard userHasPropertyCard = new UserHasPropertyCard();
        {//插入购买记录
            userHasPropertyCard.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_property_card));
            userHasPropertyCard.settPropertyCardFId(propertyCard.getId());
            userHasPropertyCard.setBuyTime(DateUtils.getCurrentDate());
            userHasPropertyCard.setCardAmount(propertyCard.getCardAmount());
            userHasPropertyCard.setBalanceAmt(propertyCard.getCardAmount());
            userHasPropertyCard.setDiscountAmt(propertyCard.getDiscountAmt());
            userHasPropertyCard.setRealPayAmt(propertyCard.getRealPayAmt());
            userHasPropertyCard.settUserFId(userId);
            userHasPropertyCard.settEbuyOrderFId(orderId);
            userHasPropertyCard.setOrderNo(ebuyOrder.getOrderNo());
            userHasPropertyCardBaseDao.insertUserHasPropertyCard(userHasPropertyCard);
        }
        {//插入交易流水表
            PropertyCardTransactionFlowing propertyCardTransactionFlowing = new PropertyCardTransactionFlowing();
            propertyCardTransactionFlowing.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_property_card_transaction_flowing));
            propertyCardTransactionFlowing.setDescription(ebuyOrder.getDelivAddressDetail()); //在前面已经生成好了
            propertyCardTransactionFlowing.setTransAmt(propertyCard.getRealPayAmt());
            {//单号加上单号：yyyyMMdd+userId+3位随机整数
                String number = DateUtil.formatMinuteTogether.get().format(new java.util.Date()) + "" + userId;
                number = number + RandomUtils.createRandom(true, 3);
                propertyCardTransactionFlowing.setTransNo(number);
            }
            propertyCardTransactionFlowing.setTransTime(DateUtils.getCurrentDate());
            propertyCardTransactionFlowing.setTransType(PropertyCardConstant.TransactionFlowing.TransType_Charge);
            propertyCardTransactionFlowing.settUserFId(userId);

            propertyCardTransactionFlowing.settEbuyOrderFId(orderId);
            propertyCardTransactionFlowingBaseDao.insertPropertyCardTransactionFlowing(propertyCardTransactionFlowing);
        }

        {
        	PropertyAccountInfo pci = chargePropertyCard(propertyCard, userId, notifyPhone, userHasPropertyCard);

			//每次购买，且有配置划扣小区，即触发一次当前门牌划扣异步处理
			BigInteger defaultRealRoomId = commonRoomDao.selectDefaultRoomByUserId(userId).gettRealRoomFId();
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tUserFId",userId);
			paramMap.put("tRealRoomFId",defaultRealRoomId);
			paramMap.put("openState",1);//开启划扣配置
			List<UserSetRealroomDeductionConfig> userSetRealroomDeductionConfigList = userSetRealroomDeductionConfigBaseDao.selectUserSetRealroomDeductionConfigByCondition(paramMap,false);
			if(!userSetRealroomDeductionConfigList.isEmpty()) {//判断默认门牌是否开启划扣配置
				try {
					new DeductionAfterPayThread(pci, defaultRealRoomId, userId).start();
				} catch(Exception e) {
					logger.error("物业代扣卡划扣失败！");
					logger.error(e.getMessage(), e);
				}
			}
        }
    }

    /**
     * 充值成功，回写用户物业账户表  TODO 可能有并发情况：管理员在后台手工划扣时，用户刚好买代扣卡，概率应该比较小
     * @param propertyCard
     * @param userId
     * @param notifyPhone
     * @param userHasPropertyCard
     * @return 
     */
	private PropertyAccountInfo chargePropertyCard(PropertyCard propertyCard, BigInteger userId, String notifyPhone,
			UserHasPropertyCard userHasPropertyCard) {
		PropertyAccountInfo propertyAccountInfoQry = new PropertyAccountInfo();
		propertyAccountInfoQry.settUserFId(userId);
		List<PropertyAccountInfo> propertyAccountInfoList = propertyAccountInfoDao.selectPropertyAccountInfoByCondition(MapConverter.toMap(propertyAccountInfoQry), false);

		if (propertyAccountInfoList.isEmpty()) {//首充
		    List<PropertyCardDiscountTerm> propertyCardDiscountTermList = propertyCardDiscountTermBaseDao.selectPropertyCardDiscountTermByCondition(null, false);
		    if (propertyCardDiscountTermList.isEmpty()) {//没有活动时
		       // throw new BusinessRuntimeException("com.cnfantasia.server.api.propertycard.service.PropertyCardService.updateBillAfterPaySuccess: should has one PropertyCardDiscountTerm");
		    	PropertyAccountInfo propertyAccountInfoNew = new PropertyAccountInfo();
		    	propertyAccountInfoNew.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_property_account_info));
		    	propertyAccountInfoNew.settUserFId(userId);
		    	propertyAccountInfoNew.setIsfirstcharge(1);
		    	propertyAccountInfoNew.setNotifyPhone(notifyPhone);
		    	propertyAccountInfoNew.setBalanceAmt(propertyCard.getCardAmount());
		    	propertyAccountInfoDao.insertPropertyAccountInfo(propertyAccountInfoNew);
		    } else {//有活动时
		       // throw new BusinessRuntimeException("com.cnfantasia.server.api.propertycard.service.PropertyCardService.updateBillAfterPaySuccess: can not more than one PropertyCardDiscountTerm");
		    	PropertyCardDiscountTerm propertyCardDiscountTerm = propertyCardDiscountTermList.get(0); //已经是倒序排列，取最后一条
		    	PropertyAccountInfo propertyAccountInfoNew = new PropertyAccountInfo();
		    	propertyAccountInfoNew.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_property_account_info));
		    	propertyAccountInfoNew.settUserFId(userId);
		    	propertyAccountInfoNew.setIsfirstcharge(1);
		    	propertyAccountInfoNew.setNotifyPhone(notifyPhone);
		    	
		    	if (propertyCard.getCardAmount() >= propertyCardDiscountTerm.getGoalAmt()*100) {//注意两边的单位，前者是分，后者是元
		    		//处理首充满100送15情况
		    		long sendAmt = propertyCardDiscountTerm.getSendAmt()*100;// propertyCardDiscountTerm.getSendAmt()单位是元
					propertyAccountInfoNew.setBalanceAmt(propertyCard.getCardAmount() + sendAmt); 
		    		userHasPropertyCard.setDiscountAmt(userHasPropertyCard.getDiscountAmt() + sendAmt);
		    		userHasPropertyCard.setBalanceAmt(userHasPropertyCard.getBalanceAmt() + sendAmt);
		    		userHasPropertyCard.setCardAmount(userHasPropertyCard.getCardAmount() + sendAmt);
		    		userHasPropertyCard.setSys0UpdTime(null);
		    		userHasPropertyCardBaseDao.updateUserHasPropertyCard(userHasPropertyCard);
		    	} else {
		    		propertyAccountInfoNew.setBalanceAmt(propertyCard.getCardAmount());
		    	}
		    	propertyAccountInfoDao.insertPropertyAccountInfo(propertyAccountInfoNew);
		    	
		    	return propertyAccountInfoNew;
		    }
		} else {//非首次充值
		    PropertyAccountInfo propertyAccountInfo = propertyAccountInfoList.get(0);
		    propertyAccountInfo.setBalanceAmt(propertyCard.getCardAmount() + propertyAccountInfo.getBalanceAmt());
		    propertyAccountInfo.setNotifyPhone(notifyPhone);
		    
		    PropertyAccountInfoEntity propertyAccountInfoEntity = new PropertyAccountInfoEntity(propertyAccountInfo);
			propertyAccountInfoEntity.setOldUpdateTime(propertyAccountInfo.getSys0UpdTime());
			propertyAccountInfoEntity.setSys0UpdTime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
		    boolean valid = propertyAccountInfoDao.updatePropertyAccountInfoWithLock(propertyAccountInfoEntity);
		    if(!valid){
		    	new BusinessRuntimeException("PropertyCardService.updateBillAfterPaySuccess. 操作异常，数据脏读！");
		    }
		    
		    return propertyAccountInfo;
		}
		
		return null;
	}
    
    /**
     * 查询指定用户的代扣卡交易流水
     *
     * @param userId    用户ID
     * @param pageModel 分页信息
     * @return
     */
    public List<TransLog> getTransLogListByUserId(BigInteger userId, PageModel pageModel) {
        Integer totalCount = null;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tUserFId", userId);
        if (null == pageModel.isCount || pageModel.isCount) {
            totalCount = propertyCardTransactionFlowingBaseDao.selectPropertyCardTransactionFlowingCount(paramMap, false);
        }
        pageModel.validate(totalCount);
        List<TransLog> logs = propertyCardDao.getTransLogListByUserId(userId, pageModel);
        pageModel.freshPageModel(logs.size(),totalCount);
        return logs;
    }

    /**
     * 用户保存代扣配置
     * @param configs 配置情况
     */
    public void saveDeductionConfig(List<RealRoomConfig> configs) {
        BigInteger defalutBigInteger = new BigInteger("-1");
        BigInteger userId = UserContext.getOperIdBigInteger();
        List<RealRoomConfig> insertRealRoomConfig = new ArrayList<RealRoomConfig>();
        List<UserSetRealroomDeductionConfig> updateConfigs = new ArrayList<UserSetRealroomDeductionConfig>();
        List<UserSetRealroomDeductionConfig> insertConfigs = new ArrayList<UserSetRealroomDeductionConfig>();
        UserSetRealroomDeductionConfig userConfig = null;
        for (RealRoomConfig config : configs) {
            if (config.getConfigId().compareTo(defalutBigInteger) == 0) {
                insertRealRoomConfig.add(config);
            } else {
                userConfig = new UserSetRealroomDeductionConfig();
                userConfig.setId(config.getConfigId());
                userConfig.setOpenState(config.getIsOpenService());
                userConfig.settRealRoomFId(config.getRealRoomId());
                userConfig.settUserFId(userId);
                updateConfigs.add(userConfig);
            }
        }
        int insertSize = insertRealRoomConfig.size();
        if (insertSize > 0) {
            List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_set_realroom_deduction_config, insertSize);
            for (int i = 0; i < insertSize; i++) {
                RealRoomConfig realRoomConfig = insertRealRoomConfig.get(i);
                userConfig = new UserSetRealroomDeductionConfig();
                userConfig.setId(ids.get(i));
                userConfig.setOpenState(realRoomConfig.getIsOpenService());
                userConfig.settRealRoomFId(realRoomConfig.getRealRoomId());
                userConfig.settUserFId(userId);
                insertConfigs.add(userConfig);
            }
            userSetRealroomDeductionConfigBaseDao.insertUserSetRealroomDeductionConfigBatch(insertConfigs);
        }
		if (updateConfigs.size() > 0) {
			userSetRealroomDeductionConfigBaseDao.updateUserSetRealroomDeductionConfigBatch(updateConfigs);
		} 
		
		new TriggerThread(userId, updateConfigs, insertConfigs).start();
    }

	public void triggerAfterSaveConfig(BigInteger userId, List<UserSetRealroomDeductionConfig> updateConfigs, List<UserSetRealroomDeductionConfig> insertConfigs) {
		//5、首次充值成功，跳转到代扣功能配置页，点击返回保存扣款配置，并且即触发当期物业费扣款
		PropertyCardTransactionFlowing propertyCardTransactionFlowingQry = new PropertyCardTransactionFlowing();
		propertyCardTransactionFlowingQry.settUserFId(userId);
		propertyCardTransactionFlowingQry.setTransType(PropertyCardConstant.TransactionFlowing.TransType_Charge);
		propertyCardTransactionFlowingQry.setSys0DelState(0);		
		if(propertyCardTransactionFlowingBaseDao.selectPropertyCardTransactionFlowingCount(MapConverter.toMap(propertyCardTransactionFlowingQry), false) == 1){
			//首次充值后，并配置划扣时，默认门牌若配置了自动划扣，启动一次自动划扣
			BigInteger defaultRealRoomId = commonRoomDao.selectDefaultRoomByUserId(userId).gettRealRoomFId();
			for (int i = 0; i < insertConfigs.size(); i++) {
				if (insertConfigs.get(i).gettRealRoomFId().longValue() == defaultRealRoomId.longValue() 
						&& insertConfigs.get(i).getOpenState() == 1) {
					triggerDeducntion4SingleRoom(defaultRealRoomId, userId);
				}
			}
			for (int i = 0; i < updateConfigs.size(); i++) {//有可能是先保存配置，再首充，后面再保存配置，也需要划扣
				if (updateConfigs.get(i).gettRealRoomFId().longValue() == defaultRealRoomId.longValue() 
						&& updateConfigs.get(i).getOpenState() == 1) {
					triggerDeducntion4SingleRoom(defaultRealRoomId, userId);
				}
			}
		}
	}

	/**
	 * 划扣失败短信通知
	 * @param userId
	 * @param propertyAccountInfo
	 * @param pb
	 */
	private void sendFailSMS(BigInteger userId, PropertyAccountInfo propertyAccountInfo, PayBill pb) {
		RealRoomShowNameAndPayEnd roomShowNameAndPayEnd = propertyCardDao.select_realRoom_showName_byRealRoomId(pb.gettRealRoomFId());
		String payEndDate = "";
		if(pb.getPaytimeType() == PlotpropertyDict.PayBillType_PaytimeType.MONTH.intValue()){
			payEndDate = roomShowNameAndPayEnd.getPayEnd_MMdd();
		}else{
			payEndDate = DateUtils.convertDateToStr(DateUtils.convertStrToDate(pb.getPayDayEnd()), "MM月dd日");
		}
		SmsMq smsMq = new SmsMq();
		smsMq.setSrcType(SMSMQConstant.SMSMQ_Status_SrcType_PropertyCard);
		smsMq.setSrcId(new BigInteger("-1"));
		smsMq.setSendStatus(SMSMQConstant.SMSMQ_Status_NotSend);
		smsMq.setMobile(propertyAccountInfo.getNotifyPhone());
		//"尊敬的【%s】住户，本期【%s】扣款不成功，原因：卡内余额不足，为保证扣款成功，请与【%s】前进行充值。";
		String shortUrl = CnfantasiaCommbusiUtil.getSysParaValue("shorturl_home");
		smsMq.setContent(String.format(Fail_SMS_Template, roomShowNameAndPayEnd.getRealRoomShowName(), pb.getBillTypeName(), payEndDate, shortUrl));
		smsMq.setSys0AddUser(userId);
		notificationDao.insertSmsMq(smsMq);
	}

	/**
	 * 划扣成功后更新数据到数据库
	 * @param propertyAccountInfo 用户账余额
	 * @param pb 账单
	 */
	private void updateData4Deducntion(PropertyAccountInfo propertyAccountInfo, PayBill pb, BigInteger userId) {
		long deducntionAmt = 0L;
		if(pb.getFinanceStatus()!=null && pb.getFinanceStatus() == 1){//物业宝抵扣了一部分，剩下部分用费用用代扣卡来划扣
			deducntionAmt = pb.getAmount() - pb.getDeduPrice();
		}else{
			deducntionAmt = pb.getAmount();
		}
		PayBill pbUpd = new PayBill();
		pbUpd.setId(pb.getId());
		pbUpd.setIsPay(1);
		pbUpd.setPayTime(DateUtils.getCurrentDate());
		pbUpd.setPaymentWay(PayBillDict.PaymentWay.Property_Card_Deduction);
		pbUpd.setSuccPayAmount(deducntionAmt);
		pbUpd.setSys0UpdUser(userId);
		
		payBillBaseDao.updatePayBill(pbUpd);
		
		// 更新t_room_verify_payment的缴费状态
		{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tRealRoomId", pb.gettRealRoomFId());
			paramMap.put("payState", 1);
			int count = roomVerifyPaymentDao.selectRoomVerifyPaymentCount(paramMap, false);
			if(count==0){
				RoomVerifyPayment roomVerifyPayment = new RoomVerifyPayment();
				roomVerifyPayment.settRealRoomId(pb.gettRealRoomFId());
				roomVerifyPayment.setPayState(1);
				roomVerifyPayment.setPayTime(DateUtils.getCurrentDate());
				roomVerifyPaymentDao.updateRoomVerifyPayment(roomVerifyPayment);
			}
		}
		
		propertyAccountInfo.setBalanceAmt(propertyAccountInfo.getBalanceAmt() - deducntionAmt);
		propertyAccountInfoDao.updatePropertyAccountInfo(propertyAccountInfo);
		
		PropertyCardTransactionFlowing propertyCardTransactionFlowing = new PropertyCardTransactionFlowing();
		propertyCardTransactionFlowing.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_property_card_transaction_flowing));
		
		if(pb.getPaytimeType() == PlotpropertyDict.PayBillType_PaytimeType.MONTH.intValue()){
			propertyCardTransactionFlowing.setDescription(DateUtils.convertDateToStr(DateUtils.convertStrToDate(pb.getBillMonth()), "yyyy年MM月") + pb.getBillTypeName());
		}else if(pb.getPaytimeType() == PlotpropertyDict.PayBillType_PaytimeType.PEORID.intValue()){
			String billMonthDesc = DateUtils.convertDateToStr(DateUtils.convertStrToDate(pb.getBillMonthStart()), "yyyy年MM月") + "~" + DateUtils.convertDateToStr(DateUtils.convertStrToDate(pb.getBillMonthEnd()), "yyyy年MM月");
			propertyCardTransactionFlowing.setDescription(billMonthDesc + pb.getBillTypeName());
		}
		
		{//单号加上单号：yyyyMMdd+userId+3位随机整数
			String number = DateUtil.formatMinuteTogether.get().format(new java.util.Date()) + "" + userId;
			number = number + RandomUtils.createRandom(true, 3);
			propertyCardTransactionFlowing.setTransNo(number);
		}
		propertyCardTransactionFlowing.setTransAmt(deducntionAmt);
		propertyCardTransactionFlowing.setTransTime(DateUtils.getCurrentDate());
		propertyCardTransactionFlowing.setTransType(PropertyCardConstant.TransactionFlowing.TransType_HuaKou);
		propertyCardTransactionFlowing.settUserFId(userId);
		propertyCardTransactionFlowing.settPayBillFId(pb.getId());
		propertyCardTransactionFlowingBaseDao.insertPropertyCardTransactionFlowing(propertyCardTransactionFlowing);
		
		saveDeductionDetailData(pb, userId, deducntionAmt);
		
		sendSuccessSMS(propertyAccountInfo, pb, userId, propertyCardTransactionFlowing);

		/*//更新首页消息
		UserHasHomeMessage message = new UserHasHomeMessage();
		message.setMessageCode(HomeMessageDict.MessageCode.PROPERTY_PAYBILL);
		message.setResouceId(pb.getId());
		List<UserHasHomeMessage> messageList = new ArrayList<>(1);
		messageList.add(message);
		homeMessageService.delUserHomeMsgByParam(messageList);*/
	}

	/**
	 * 记录划扣详情 Added by Wenfq 2016-08-11
	 * @param pb
	 * @param userId
	 * @param deducntionAmt 
	 */
	private void saveDeductionDetailData(PayBill pb, BigInteger userId, long deducntionAmt) {
		//PB.amount先用代扣卡的优惠补贴，再用实缴，并记录余额
		UserHasPropertyCard userHasPropertyCardQry = new UserHasPropertyCard(); 
		userHasPropertyCardQry.settUserFId(userId);
		List<UserHasPropertyCard> userHasPropertyCardList = userHasPropertyCardBaseDao.selectUserHasPropertyCardByCondition(MapConverter.toMap(userHasPropertyCardQry), false);
		
		List<UserHasPropertyCard> userHasPropertyCardUpdList = new ArrayList<UserHasPropertyCard>();
		List<PropertyCardDeductionDetail> propertyCardDeductionDetailList = new ArrayList<PropertyCardDeductionDetail>(); //待插入的划扣明细
		for (int i = userHasPropertyCardList.size() - 1; i >= 0; i--) {
			UserHasPropertyCard userHasPropertyCard = userHasPropertyCardList.get(i);
			if (userHasPropertyCard.getBalanceAmt() == null || userHasPropertyCard.getBalanceAmt() <= 0)
				continue;

			//可用补贴部分
			long availablePtbtAmt = Math.max(userHasPropertyCard.getBalanceAmt() + userHasPropertyCard.getDiscountAmt() - userHasPropertyCard.getCardAmount(), 0);
			//本次使用补贴部分
			long thisUsePtbtAmt = Math.min(deducntionAmt, availablePtbtAmt);
			
			if (deducntionAmt <= thisUsePtbtAmt) {//补贴足够
				PropertyCardDeductionDetail propertyCardDeductionDetail = new PropertyCardDeductionDetail(); 
				propertyCardDeductionDetail.settPayBillFId(pb.getId());
				propertyCardDeductionDetail.settUserHasPropertyCardFId(userHasPropertyCard.getId());
				propertyCardDeductionDetail.settEbuyOrderFId(userHasPropertyCard.gettEbuyOrderFId());
				propertyCardDeductionDetail.setOrderNo(userHasPropertyCard.getOrderNo());
				
				propertyCardDeductionDetail.setPayBillAmt(pb.getAmount());
				propertyCardDeductionDetail.setPtbtAmt(thisUsePtbtAmt);
				propertyCardDeductionDetail.setRealPayAmt(0L);		
				
				userHasPropertyCard.setBalanceAmt(userHasPropertyCard.getBalanceAmt() - deducntionAmt);/*卡里还剩下多少可用余额*/
				userHasPropertyCard.setSys0UpdTime(null);
				
				propertyCardDeductionDetailList.add(propertyCardDeductionDetail);
				userHasPropertyCardUpdList.add(userHasPropertyCard);
				break;
			}else if(userHasPropertyCard.getBalanceAmt() >= deducntionAmt){//补贴不够，但余额足够
				PropertyCardDeductionDetail propertyCardDeductionDetail = new PropertyCardDeductionDetail(); 
				propertyCardDeductionDetail.settPayBillFId(pb.getId());
				propertyCardDeductionDetail.settUserHasPropertyCardFId(userHasPropertyCard.getId());
				propertyCardDeductionDetail.settEbuyOrderFId(userHasPropertyCard.gettEbuyOrderFId());
				propertyCardDeductionDetail.setOrderNo(userHasPropertyCard.getOrderNo());
				
				propertyCardDeductionDetail.setPayBillAmt(pb.getAmount());
				propertyCardDeductionDetail.setPtbtAmt(thisUsePtbtAmt);
				propertyCardDeductionDetail.setRealPayAmt(deducntionAmt - thisUsePtbtAmt);
				
				userHasPropertyCard.setBalanceAmt(userHasPropertyCard.getBalanceAmt() - deducntionAmt);/*卡里还剩下多少可用余额*/
				userHasPropertyCard.setSys0UpdTime(null);
				
				propertyCardDeductionDetailList.add(propertyCardDeductionDetail);
				userHasPropertyCardUpdList.add(userHasPropertyCard);
				break;
			}else{//补贴不够，余额也不够, 找下一个代扣卡接着扣~~
				PropertyCardDeductionDetail propertyCardDeductionDetail = new PropertyCardDeductionDetail(); 
				propertyCardDeductionDetail.settPayBillFId(pb.getId());
				propertyCardDeductionDetail.settUserHasPropertyCardFId(userHasPropertyCard.getId());
				propertyCardDeductionDetail.settEbuyOrderFId(userHasPropertyCard.gettEbuyOrderFId());
				propertyCardDeductionDetail.setOrderNo(userHasPropertyCard.getOrderNo());
				
				propertyCardDeductionDetail.setPayBillAmt(pb.getAmount());
				propertyCardDeductionDetail.setPtbtAmt(thisUsePtbtAmt);
				propertyCardDeductionDetail.setRealPayAmt(userHasPropertyCard.getBalanceAmt()-thisUsePtbtAmt);
				
				deducntionAmt = deducntionAmt - userHasPropertyCard.getBalanceAmt(); //剩下未扣的钱deducntionAmt 找下一个代扣卡
				userHasPropertyCard.setBalanceAmt(0L);
				userHasPropertyCard.setSys0UpdTime(null);
				
				propertyCardDeductionDetailList.add(propertyCardDeductionDetail);
				userHasPropertyCardUpdList.add(userHasPropertyCard);
			}
		}
		
		//update and insert 数据 
		userHasPropertyCardBaseDao.updateUserHasPropertyCardBatch(userHasPropertyCardUpdList);
		List<BigInteger> idList = uuidManager.getNextUuidBigInteger(SEQConstants.t_property_card_deduction_detail, propertyCardDeductionDetailList.size());
		for(int i = 0; i < idList.size(); i++){
			propertyCardDeductionDetailList.get(i).setId(idList.get(i));
		}
		propertyCardDeductionDetailBaseDao.insertPropertyCardDeductionDetailBatch(propertyCardDeductionDetailList);
	}

	private void sendSuccessSMS(PropertyAccountInfo propertyAccountInfo, PayBill pb, BigInteger userId, PropertyCardTransactionFlowing propertyCardTransactionFlowing) {
		RealRoomShowNameAndPayEnd roomShowNameAndPayEnd = propertyCardDao.select_realRoom_showName_byRealRoomId(pb.gettRealRoomFId());
		SmsMq smsMq = new SmsMq();
		smsMq.setSrcId(propertyCardTransactionFlowing.getId());
		smsMq.setSrcType(SMSMQConstant.SMSMQ_Status_SrcType_PropertyCard);
		smsMq.setSendStatus(SMSMQConstant.SMSMQ_Status_NotSend);
		smsMq.setMobile(propertyAccountInfo.getNotifyPhone());
		//"尊敬的【%s】住户，本期【%s】已扣款成功，扣款金额：【%.2f】元，卡内余额：%.2f元，感谢您的支持。";
		smsMq.setContent(String.format(Success_SMS_Template, roomShowNameAndPayEnd.getRealRoomShowName(), pb.getBillTypeName(), propertyCardTransactionFlowing.getTransAmt()/100.0, propertyAccountInfo.getBalanceAmt()/100.0));
		smsMq.setSys0AddUser(userId);
		notificationDao.insertSmsMq(smsMq);
	}

	public TransLog getTransLogByOrderId(BigInteger orderId) {
		return propertyCardDao.getTransLogByOrderId(orderId);
	}

	public List<UserHasCardWithGroupBuildingNames> qryUserHasPropertyCardList(Map<String, Object> paramMap) {
		return propertyCardDao.qryUserHasPropertyCardList(paramMap);
	}
	
	public Integer qryUserHasPropertyCardCount(Map<String, Object> paramMap) {
		return propertyCardDao.qryUserHasPropertyCardCount(paramMap);
	}

	class TriggerThread extends Thread {
		BigInteger userId;
		List<UserSetRealroomDeductionConfig> updateConfigs;
		List<UserSetRealroomDeductionConfig> insertConfigs;

		public TriggerThread(BigInteger userId, List<UserSetRealroomDeductionConfig> updateConfigs, List<UserSetRealroomDeductionConfig> insertConfigs) {
			super();
			this.userId = userId;
			this.updateConfigs = updateConfigs;
			this.insertConfigs = insertConfigs;
		}

		public void run() {
			triggerAfterSaveConfig(userId, updateConfigs, insertConfigs);
		}
	}

	class DeductionAfterPayThread extends Thread {
		private BigInteger userId;
		private BigInteger realroomId;
		private PropertyAccountInfo pci;

		public DeductionAfterPayThread(PropertyAccountInfo pci, BigInteger realroomId, BigInteger userId) {
			super();
			this.pci = pci;
			this.userId = userId;
			this.realroomId = realroomId;
		}

		public void run() {
			triggerDeducntion4SingleRoom(pci, realroomId, userId);
		}
	}

	/**
	 * 查询用户累计购买金额记录
	 * @param userId
	 * @return
	 */
	public UserHasPropertyCard qrySumAmtByUserId(BigInteger userId) {
		return propertyCardDao.qrySumAmtByUserId(userId);
	}

	/**
	 * 查询配置划扣小区门牌
	 * @param userId
	 * @return
	 */
	public List<RealRoomConfig> getPropfeeCanPayRoomByUserId(BigInteger userId) {
		return propertyCardDao.getPropfeeCanPayRoomByUserId(userId);
	}

	/**
	 * 批量修改用户划扣配置
	 * @param userId
	 * @param configs
	 */
	public void editPropCardOpenStatus(BigInteger userId, String[] configs) {
		LinkedList<UserSetRealroomDeductionConfig> updateList = new LinkedList<UserSetRealroomDeductionConfig>();
		LinkedList<UserSetRealroomDeductionConfig> insertList = new LinkedList<UserSetRealroomDeductionConfig>();
		
		//将原来用户的数据全部恢复为未开启
		propertyCardDao.updatePropCardStatus(userId);
		
		for (int i = 0; i < configs.length; i++) {
			int configId = Integer.parseInt(configs[i].split("_")[0]);
			BigInteger realRoomId = BigInteger.valueOf(Long.parseLong(configs[i].split("_")[1]));
			UserSetRealroomDeductionConfig userSetRealroomDeductionConfig = new UserSetRealroomDeductionConfig();
			userSetRealroomDeductionConfig.settRealRoomFId(realRoomId);
			userSetRealroomDeductionConfig.setOpenState(1);
			userSetRealroomDeductionConfig.settUserFId(userId);
			
			if(configId > 0) {//更新
				userSetRealroomDeductionConfig.setId(BigInteger.valueOf(Long.parseLong(configId+"")));
				userSetRealroomDeductionConfig.setSys0UpdTime(DateUtils.getCurrentDate());
				updateList.add(userSetRealroomDeductionConfig);
			} else {//新增
				userSetRealroomDeductionConfig.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_user_set_realroom_deduction_config));
				userSetRealroomDeductionConfig.setSys0AddTime(DateUtils.getCurrentDate());
				insertList.add(userSetRealroomDeductionConfig);
			}
			
		}
		
		if(!updateList.isEmpty()) {
			userSetRealroomDeductionConfigBaseDao.updateUserSetRealroomDeductionConfigBatch(updateList);
		}
		if(!insertList.isEmpty()) {
			userSetRealroomDeductionConfigBaseDao.insertUserSetRealroomDeductionConfigBatch(insertList);
		}
	}
	
	/**
	 * 获取用户优惠等级
	 * 1>>A,2>>B,3>>C
	 * @param userId
	 * @return
	 */
	public int getDiscountLevel(BigInteger userId) {
		//查询用户的门牌小区信息
		List<Map<String, Object>> list = propertyCardDao.getGroupBuildingPayBillAvgAmt(userId);
		if(list!=null && list.size()>0) {
			Map<String, Object> map = list.get(0);
			if(map == null) {
				return 3;
			}
			long amt = map.get("amtAvg") == null ? 0l : Long.parseLong(map.get("amtAvg").toString());
			
			//获取优惠等级区间
			String levels = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.DISCOUNTRANGE);
			long levelA = Long.valueOf(levels.split(",")[0]);
			long levelB = Long.valueOf(levels.split(",")[1]);
			
			if(!StringUtils.isEmpty(map.get("verifyStatus")) && map.get("verifyStatus").toString().equals("4")) {//存在验证门牌信息
				if(0l < amt && amt <= levelA) {
					return 1;
				} else if(levelA < amt && amt <= levelB) {
					return 2;
				} else {
					return 3;
				}
			} else if(list.size() == 1){//只有一个没有验证的门牌
				if(amt <= levelA) {
					return 1;
				} else if(levelA < amt && amt <= levelB) {
					return 2;
				} else {
					return 3;
				}
			} else {//存在多个没有验证的门牌
				return 3;
			}
		} else {
			return 3;
		}
	}
	
	
	/**
	 * 生成周期 账单
	 * @param ebuyOrder 订单
	 * @param month 缴费月份
	 * @param realRoomId 真实门牌id
	 * @param cycleType 周期缴费类型（1固定周期，2选择周期）
	 * @param groupBuilding 小区
	 * @param billMonthStart 账单开始月份
	 * @param realroomSoftwareFeeFId 第三方物业账单id
     * @return
     */
	private PayBill createPayBill(PayBill payBill, BigInteger gbId, BigInteger realRoomId) {
		 //物业费类型
		Map<String, Object> billTypeMap = new HashMap<String, Object>();
		billTypeMap.put("gbId",gbId);
		billTypeMap.put("isPropFee",1);//物业费
		billTypeMap.put("paytimeType",2);//周期缴费
		PayBillType payBillType = payBillTypeBaseDao.selectPayBillTypeByCondition(billTypeMap,false).get(0);

		//真实门牌信息
		RealRoom realRoom = realRoomBaseDao.selectRealRoomBySeqId(realRoomId);

//		PayBill payBill = new PayBill();
		payBill.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_bill));
		payBill.setBillTypeName(payBillType.getName());
		payBill.setBillTypeId(payBillType.getId());
		payBill.setBillTimeCfgId(BigInteger.valueOf(-1));//数据库设计不能为空，选择周期没有该id 所以默认-1为id
		payBill.setHbAmount(0L);
		payBill.setIsPay(2);
		payBill.setIsPropFee(1);
		payBill.setPaytimeType(2);
		//payBill.setBillMonthStart(billMonthStart);
		payBill.setPayDayStart(payBill.getBillMonthStart());
		String billMonthEnd = DateUtils.convertDateToStr(DateUtils.increateMonth(DateUtils.convertStrToDate(payBill.getBillMonthStart()), 0),"yyyy-MM-dd");
		payBill.setBillMonthEnd(billMonthEnd);
		payBill.setPayDayEnd(billMonthEnd);
		payBill.setBillMonthSize(1L);
		payBill.setPayTime(DateUtils.getCurrentDate());
		payBill.setPropertyProprietorId(realRoom.gettPropertyProprietorFId().toString());
		payBill.setSuccPayAmount(payBill.getAmount());
		payBill.settRealRoomFId(realRoomId);
		payBill.setPaymentWay(PayBillDict.PaymentWay.Property_Card_Deduction);
		payBill.setPreferType(0);//1随机立减,0没有随机立减
		
		payBill.setCycleType(1);//1固定周期，2选择周期
		payBill.setBankCollectionStatus(0);
		//payBill.settRealroomSoftwareFeeFId(realroomSoftwareFeeFId);
		//新增账单
		Integer resCount = payBillBaseDao.insertPayBill(payBill);
		if (resCount == null || resCount <= 0) {
			throw new BusinessRuntimeException("账单数据新增失败！");
		}

		return payBill;
	}

	/**
	 * 生成 t_property_fee_detail
	 * @param payBill
	 */
	private void createPropertyFeeDetail(PayBill payBill, List<PropertyFeeDetail> propertyFeeDetailList) {
		for (int i=0; i < propertyFeeDetailList.size() ; i++){
			PropertyFeeDetail propertyFeeDetail = propertyFeeDetailList.get(i);
			propertyFeeDetail.settPayBillFId(payBill.getId());
			propertyFeeDetail.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_property_fee_detail));
			propertyFeeDetail.setAllowancePrice(0l);
			if(propertyFeeDetail.getName().equals("管理费")) {//1
				propertyFeeDetail.setType(1);
			} else if(propertyFeeDetail.getName().equals("主体金")) {//2
				propertyFeeDetail.setType(2);
			} else if(propertyFeeDetail.getName().equals("垃圾处理费")) {//3
				propertyFeeDetail.setType(3);
			} else if(propertyFeeDetail.getName().equals("水费")) {//4
				propertyFeeDetail.setType(4);
			} else if(propertyFeeDetail.getName().equals("污水处理费")) {//5
				propertyFeeDetail.setType(5);
			} else {//9
				propertyFeeDetail.setType(9);
			}
			propertyFeeDetail.setSys0AddTime(DateUtils.getCurrentDateStr());
			propertyFeeDetail.setSys0DelState(0);
			propertyFeeDetailBaseDao.insertPropertyFeeDetail(propertyFeeDetail);
		}
	}
}
