package com.cnfantasia.server.api.access.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.access.constant.AccessConstant;
import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.access.constant.AccessDict.Code;
import com.cnfantasia.server.api.access.constant.XMFCarDict;
import com.cnfantasia.server.api.access.dao.IAccessDao;
import com.cnfantasia.server.api.access.entity.CarBill;
import com.cnfantasia.server.api.access.entity.CarDetailEntity;
import com.cnfantasia.server.api.access.entity.CarFeeType;
import com.cnfantasia.server.api.access.entity.CarNumPayLogDetail;
import com.cnfantasia.server.api.access.entity.CarNumPrefixEntity;
import com.cnfantasia.server.api.access.entity.CarPreferDto;
import com.cnfantasia.server.api.access.entity.CarPreferParam;
import com.cnfantasia.server.api.access.entity.GroupBuildingParkingInfo;
import com.cnfantasia.server.api.access.entity.JfqPlotEntity;
import com.cnfantasia.server.api.access.entity.MonthCarInfo;
import com.cnfantasia.server.api.access.entity.ParkingRecordEntity;
import com.cnfantasia.server.api.access.entity.PayCarKeyOrderParam;
import com.cnfantasia.server.api.access.entity.PlotInfo;
import com.cnfantasia.server.api.access.entity.TmpCarPayDetail;
import com.cnfantasia.server.api.access.entity.UserCarpushMsg;
import com.cnfantasia.server.api.access.entity.UserDoorKeyMsg;
import com.cnfantasia.server.api.access.session.IAccessPublishHandler;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.common.constant.Lock;
import com.cnfantasia.server.api.common.service.ICommonLockService;
import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRedenvelopeService;
import com.cnfantasia.server.api.couponArea.contant.UserCouponStatus;
import com.cnfantasia.server.api.ebuy.util.OrderNoGenerator;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.msgpush.service.IMsgpushService;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.payment.serviceUntran.ICommPayService;
import com.cnfantasia.server.api.property.dto.PreOrderDto;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.building.dao.IBuildingBaseDao;
import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.carGroupBuilding.entity.CarGroupBuilding;
import com.cnfantasia.server.domainbase.carHpfCardsn.dao.ICarHpfCardsnBaseDao;
import com.cnfantasia.server.domainbase.carHpfCardsn.entity.CarHpfCardsn;
import com.cnfantasia.server.domainbase.carHuaanMsg.dao.ICarHuaanMsgBaseDao;
import com.cnfantasia.server.domainbase.carHuaanMsg.entity.CarHuaanMsg;
import com.cnfantasia.server.domainbase.carNumList.dao.ICarNumListBaseDao;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumPayLog.dao.ICarNumPayLogBaseDao;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.PayCarFeeLog;
import com.cnfantasia.server.domainbase.carNumPayLog.service.ICarNumPayLogBaseService;
import com.cnfantasia.server.domainbase.carXmfMsg.dao.ICarXmfMsgBaseDao;
import com.cnfantasia.server.domainbase.carXmfMsg.entity.CarXmfMsg;
import com.cnfantasia.server.domainbase.cloudKeyPaylog.dao.ICloudKeyPaylogBaseDao;
import com.cnfantasia.server.domainbase.cloudKeyPaylog.entity.CloudKeyPaylog;
import com.cnfantasia.server.domainbase.cloudKeyUserAudit.dao.ICloudKeyUserAuditBaseDao;
import com.cnfantasia.server.domainbase.cloudKeyUserAudit.entity.CloudKeyUserAudit;
import com.cnfantasia.server.domainbase.coupon.dao.CouponBaseDao;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.ebuyOrder.dao.IEbuyOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrderExt.dao.IEbuyOrderExtBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrderExt.entity.EbuyOrderExt;
import com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.dao.IEbuyOrderHasCouponBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.entity.EbuyOrderHasCoupon;
import com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.openDoorLog.entity.OpenDoorLog;
import com.cnfantasia.server.domainbase.openDoorLog.service.IOpenDoorLogBaseService;
import com.cnfantasia.server.domainbase.parkCache.entity.ParkCache;
import com.cnfantasia.server.domainbase.parkingRecord.dao.IParkingRecordBaseDao;
import com.cnfantasia.server.domainbase.parkingRecord.entity.ParkingRecord;
import com.cnfantasia.server.domainbase.payKeyList.dao.IPayKeyListBaseDao;
import com.cnfantasia.server.domainbase.realRoom.dao.IRealRoomBaseDao;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.roomHasCarNum.entity.RoomHasCarNum;
import com.cnfantasia.server.domainbase.user.dao.UserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.userCoupon.dao.IUserCouponBaseDao;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;

@Service
public class AccessService implements IAccessService {
	@Resource
	private IAccessDao accessDao;
	@Resource
	private IDualDao dualDao;
	@Resource
	private IUuidManager uuidManager;
	@Resource
	private UserBaseDao userBaseDao;
	@Resource
	private IEbuyOrderBaseDao ebuyOrderBaseDao;
	@Resource
	private ICarNumPayLogBaseDao carNumPayLogBaseDao;
	@Resource
	private ICarNumListBaseDao carNumListBaseDao;
	@Resource
	private IBuildingBaseDao buildingBaseDao;
	@Resource
	private IRealRoomBaseDao realRoomBaseDao;
	@Resource
	private ICloudKeyPaylogBaseDao cloudKeyPaylogBaseDao;
	@Resource
	private ICarNumPayLogBaseService carNumPayLogBaseService;
	@Resource
	private IPayKeyListBaseDao payKeyListBaseDao;
	@Resource
	private ICloudKeyUserAuditBaseDao cloudKeyUserAuditBaseDao;
	@Resource
	private ICommonLockService commonLockService;
	@Resource
	private IOpenDoorLogBaseService openDoorLogBaseService;
	@Resource
	private IMsgpushService msgpushService;
	@Resource
	private IGroupBuildingBaseDao groupBuildingBaseDao;
	@Resource
	private ICommonRedenvelopeService commonRedenvelopeService;
	@Resource
	private ICommPayService commPayService;
	@Resource
	private IAccessPublishHandler accessPublishHandler;
	@Resource
	private IParkingRecordBaseDao parkingRecordBaseDao;
	@Resource
	private ICarXmfMsgBaseDao carXmfMsgBaseDao;
	@Resource
	private IEbuyOrderExtBaseDao ebuyOrderExtBaseDao;
	@Resource
	private IUserCouponBaseDao userCouponBaseDao;
	@Resource
	private CouponBaseDao couponBaseDao;
	@Resource
	private IEbuyOrderHasCouponBaseDao ebuyOrderHasCouponBaseDao;
	@Resource
	private ThirdCarFactory thirdCarFactory;
	@Resource
	private ICarHuaanMsgBaseDao carHuaanMsgBaseDao;
	@Resource
	private ICarHpfCardsnBaseDao carHpfCardsnBaseDao;
	@Resource
	private HpfCarService hpfCarService;

	@Override
	public CarGroupBuilding getCarGroupBuilding(final String trade_code) {
		return accessDao.getCarGroupBuilding(trade_code);
	}

	@Override
	public CarNumList getCardetail(Map<String, Object> paramMap) {
		return accessDao.getCardetail(paramMap);
	}

	@Override
	public void updatecarNumendTime(final Map<String, Object> paramMap) {
		accessDao.updatecarNumendTime(paramMap);
	}

	@Override
	public List<ParkingRecord> qryParkingRecord(final BigInteger carId, final PageModel pageModel) {
		return accessDao.qryParkingRecord(carId, pageModel);
	}

	/**
	 * 查询停车记录（version>=V325）
	 */
	@Override
	public List<ParkingRecordEntity> qryParkingRecord2(final BigInteger carId, final PageModel pageModel){
		return accessDao.qryParkingRecord2(carId, pageModel);
	}

	@Override
	public List<CarDetailEntity> getCarInfos(final BigInteger userId) {
		return accessDao.qryCarNumListByRealRoom(userId);
	}

	@Override
	public CarDetailEntity qryCarInfo(final BigInteger userId, final String carNum) {
		return accessDao.qryCarInfo(userId, carNum);
	}

	@Override
	public List<CarNumPayLogDetail> qryPaymentRecord(final BigInteger userId, final BigInteger carId, final PageModel pageModel) {
		return accessDao.qryPaymentRecord(userId, carId, pageModel);
	}

	@Override
	public boolean qryBinded(final BigInteger userId, final BigInteger realroomId) {
		return accessDao.qryBinded(userId, realroomId);
	}

	@Override
	public GroupBuildingParkingInfo qryParkingInfo(final BigInteger buildingId) {
		return accessDao.qryParkingInfo(buildingId);
	}

	@Override
	public boolean qryRoomAndCar(final String plateNum) {
		return accessDao.qryRoomAndCar(plateNum);
	}

	@Override
	@Transactional
	public PreOrderDto payCarKeyOrder(PayCarKeyOrderParam param) {
		// 1、查询随机立减的金额
		CarNumList carNumList = carNumListBaseDao.selectCarNumListBySeqId(param.getCarId());
		CarPreferParam carPreferParam = new CarPreferParam();
		carPreferParam.setCarId(carNumList.getId());
		carPreferParam.setUserId(UserContext.getOperIdMustExistBigInteger());

		long realAmount = 0;// 实际支付金额（单位：分）
		long ptAmountBt   = 0;// 平台优惠金额（单位：分）
		long wyAmountBt   = 0;// 物业优惠金额（单位：分）
		long amount     = 0;// 总金额（单位：分）
		long hbAmt = ((param.getHbAmt()==null) || (param.getHbAmt().doubleValue()<=0)) ? 0 : param.getHbAmt().longValue();// 红包金额（单位：分）
		Long receivableFee = null;
		long couponMoney = 0;// 券的金额（单位：分）

		CarPreferDto carPreferDto = null;
		String xmfCarId = StringUtils.EMPTY;
		Code code = BaseCarService.getCodeByGbId((param.getPayNum() > 0) ? carNumList.gettGroupBuildingFId() : param.getGbId());
		if (param.getPayNum()>0) {// 月卡缴费
			forbiddenOuterCarPay(carNumList.getFrom());
			BigInteger gbId = carNumList.gettGroupBuildingFId();
			MonthCarInfo monthCarInfo = thirdCarFactory.getMonthCarPayInfo(gbId, carNumList.gettCarNum(), (int)param.getPayNum());
			
			amount = monthCarInfo.getRealAmt();
			receivableFee = monthCarInfo.getAmt();
			xmfCarId = monthCarInfo.getCarId();
			if(AccessDict.Code.xiaomifeng.equals(code)) {
				String expire = DateUtil.formatDay.get().format(monthCarInfo.getExpire());
				dealXMFMonthCarAvailableMonth(expire, param.getPayNum());
			}

			boolean isLA = param.getSubChannelId() != null && param.getSubChannelId().intValue() == 7;// 是否为轻应用
			if(!isLA){// 轻应用的月卡缴费没有“随机立减”优惠
				GroupBuilding gb = groupBuildingBaseDao.selectGroupBuildingBySeqId(gbId);
				if(gb!=null && gb.getCarMonthIsOpen()!=null && gb.getCarMonthIsOpen().toString().equals("1")){
					carPreferParam.setGbId(gbId);
					carPreferParam.setPayMonth((int)(param.getPayNum()));
					carPreferParam.setAmount(BigInteger.valueOf(amount));
					carPreferParam.setPayType(1);

					carPreferDto = accessDao.getPayCarPrefer(carPreferParam);
					ptAmountBt = carPreferDto.getAmount().longValue();
				}
			}
			dealPerMonthFeeWithFree(amount);
		} else {// 临停车缴费
			GroupBuilding gb = groupBuildingBaseDao.selectGroupBuildingBySeqId(param.getGbId());
			if(gb!=null && gb.getCarTmpIsOpen()!=null && gb.getCarTmpIsOpen() != AccessDict.TmpCarOpenStatus.CLOSE){
				int tmpCarOpenStatus = gb.getCarTmpIsOpen();

				carPreferParam.setGbId(param.getGbId());
				carPreferParam.setAmount(BigInteger.valueOf(PriceUtil.multiply100(param.getPayFee())));
				carPreferParam.setPayType(0);
				carPreferParam.setCarTmpOpenStatus(tmpCarOpenStatus);

				carPreferDto = accessDao.getPayCarPrefer(carPreferParam);
				if(tmpCarOpenStatus == AccessDict.TmpCarOpenStatus.JFQ_OPEN){
					ptAmountBt = carPreferDto.getAmount().longValue();
				} else if(tmpCarOpenStatus == AccessDict.TmpCarOpenStatus.WY_OPEN){
					wyAmountBt = carPreferDto.getAmount().longValue();
				}
			}
			amount = PriceUtil.multiply100(param.getPayFee());
			receivableFee = Double.valueOf(param.getReceivableFee()*100).longValue();

			// 处理券
			couponMoney = getCouponMoney(param.getUcId())*100;
		}
		ptAmountBt = ptAmountBt + hbAmt;
		realAmount = amount - ptAmountBt - wyAmountBt;
		if (couponMoney>0) {
			if (realAmount<couponMoney) {
				couponMoney = realAmount;
			}
			ptAmountBt += couponMoney;
			realAmount -= couponMoney;
		}

		// 2、订单记录表
		BigInteger orderId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order);
		EbuyOrder ebuyOrder = new EbuyOrder();
		ebuyOrder.setType(EbuyDict.EbuyOrder_Type.CarKey_Bill);//订单类型：车禁缴费
		ebuyOrder.setId(orderId);
		if (param.getUserId()!=null) {
			ebuyOrder.setBuyerId(param.getUserId());
			User user = userBaseDao.selectUserBySeqId(param.getUserId());
			ebuyOrder.setCurrRoomId(user!=null?user.getDefaultRoomId():null);
			ebuyOrder.setCreater(param.getUserId());
		}
		String nowTime = dualDao.getNowTime();
		ebuyOrder.setBuyTime(nowTime);
		ebuyOrder.setOrderNo(OrderNoGenerator.getOrderNo(orderId));
		ebuyOrder.setPayMethod(null);
		ebuyOrder.setPayTime(null);
		ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.DaiFuKuan);
		ebuyOrder.setPayStatus(EbuyDict.EbuyOrder_PayStatus.Not_Pay);//支付状态为待支付
		ebuyOrder.setDelivStatus(EbuyDict.EbuyOrder_DelivStatus.Not_Deliv);//发货状态为未发货
		ebuyOrder.setTotalDeliveryFee(0L);//总配送费
		ebuyOrder.setAmount(realAmount); //应付金额 （单位：分）
		ebuyOrder.setTotalCouponAmount(ptAmountBt); //优惠金额（单位：分）
		ebuyOrder.setSubChannel(param.getSubChannelId() == null ? null : param.getSubChannelId() + "");
		// 新增订单
		int res = ebuyOrderBaseDao.insertEbuyOrder(ebuyOrder);
		if (res <= 0) {
			BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.BUSINESS_FAILED);
			businessRuntimeException.setErrorMsg("新增订单出错！");
			throw businessRuntimeException;
		}

		// 3、红包
		if (hbAmt>0) {
			commonRedenvelopeService.updatePayCouponByJFB(ebuyOrder, hbAmt);
		}

		// 物业补贴
		if(wyAmountBt>0){
			EbuyOrderExt ebuyOrderExt = new EbuyOrderExt();
			ebuyOrderExt.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_ext));
			ebuyOrderExt.settEbuyOrderFId(orderId);
			ebuyOrderExt.setWyCouponAmount(wyAmountBt);
			ebuyOrderExt.setSys0AddTime(nowTime);
			ebuyOrderExt.setSys0DelState(0);
			ebuyOrderExtBaseDao.insertEbuyOrderExt(ebuyOrderExt);
		}

		// 4、生成次缴卡或月缴卡缴费记录
		CarNumPayLog carNumpayLog = new CarNumPayLog();
		BigInteger cnplId= uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_pay_log);
		carNumpayLog.setId(cnplId);
		carNumpayLog.setSys0AddTime(nowTime);
		carNumpayLog.settCarNumId(param.getCarId());
		carNumpayLog.setStatus(0);//解放区缴费
		if (param.getPayNum()==0) {// 只能临停车有值，月卡不能有值；因为YHSNotifyRunnable.java中根据此值判断是“临停车缴费”还是“月卡缴费”
			carNumpayLog.settGroupBuildingFId(param.getGbId());
			if(AccessDict.Code.huapengfei.equals(code)) {
				String cardsn = getHpfCardsn(carNumList.gettCarNum());
				carNumpayLog.setCardsn(cardsn);
			}
		}
		carNumpayLog.setFee(amount);// 实缴金额+优惠金额
		carNumpayLog.setCouponAmount(ptAmountBt);// 随机立减优惠金额 + 红包金额（单位：分）+优惠券
		carNumpayLog.setReceivableFee(receivableFee);// 应收费用（第三方车禁需要，单位：分）
		carNumpayLog.setPayNum(param.getPayNum());
		carNumpayLog.settEbuyOrderId(ebuyOrder.getId());
		carNumpayLog.settCarPreferFId((carPreferDto != null) ? carPreferDto.getId() : null);
		carNumpayLog.setNeedBill(param.getNeedBill());
		int rest =carNumPayLogBaseDao.insertCarNumPayLog(carNumpayLog);
		if (rest <= 0) {
			BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.BUSINESS_FAILED);
			businessRuntimeException.setErrorMsg("新增缴费记录出错！");
			throw businessRuntimeException;
		} else if (AccessDict.Code.xiaomifeng.equals(code)) {// 小蜜蜂车禁
			CarXmfMsg carXmfMsg = new CarXmfMsg();
			carXmfMsg.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_xmf_msg));
			carXmfMsg.settCarNumPayLogFId(cnplId);
			carXmfMsg.settXmfOrderId(param.getOrderid());
			if(param.getPayNum()==0){
				carXmfMsg.setDiscountAmount(param.getDiscountamount());
				carXmfMsg.setCarType(XMFCarDict.CarType.TMP_CAR);
			} else {
				carXmfMsg.setDiscountAmount(0L);
				carXmfMsg.setCarType(XMFCarDict.CarType.MONTH_CAR);
			}
			carXmfMsg.setFee(amount);
			carXmfMsg.setXmfCarId(xmfCarId);
			carXmfMsg.setPayStatus(0);
			carXmfMsg.setPushStatus(0);
			int times = 0;
			carXmfMsg.setPushTimes(times);
			carXmfMsg.setSendTime(CarUtil.getSendTime(times));
			carXmfMsg.setSys0DelState(0);
			carXmfMsg.setSys0AddTime(nowTime);

			carXmfMsgBaseDao.insertCarXmfMsg(carXmfMsg);
		} else if (AccessDict.Code.huaan.equals(code)) {
			CarHuaanMsg carHuaanMsg = new CarHuaanMsg();
			carHuaanMsg.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_huaan_msg));
			carHuaanMsg.settCarNumPayLogFId(cnplId);
			carHuaanMsg.settCarNum(carNumList.gettCarNum());
			if(param.getPayNum()==0){
				carHuaanMsg.setCarType(XMFCarDict.CarType.TMP_CAR);
			} else {
				carHuaanMsg.setCarType(XMFCarDict.CarType.MONTH_CAR);
			}
			carHuaanMsg.setFee(amount);
			int times = 0;
			carHuaanMsg.setPushTimes(times);
			carHuaanMsg.setPayStatus(0);
			carHuaanMsg.setSendTime(CarUtil.getSendTime(times));
			carHuaanMsg.setPushStatus(0);
			carHuaanMsg.setSys0DelState(0);
			carHuaanMsg.setSys0AddTime(nowTime);

			carHuaanMsgBaseDao.insertCarHuaanMsg(carHuaanMsg);
		}
		if (null!=param.getUcId() && couponMoney>0) {// 券
			EbuyOrderHasCoupon ebuyOrderHasCoupon = new EbuyOrderHasCoupon();
			ebuyOrderHasCoupon.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_has_coupon));
			ebuyOrderHasCoupon.settEbuyOrderFId(orderId);
			ebuyOrderHasCoupon.settUserCouponFId(param.getUcId());
			ebuyOrderHasCoupon.setAmount(couponMoney);
			ebuyOrderHasCoupon.setSys0AddTime(nowTime);
			ebuyOrderHasCoupon.setSys0DelState(0);
			ebuyOrderHasCouponBaseDao.insertEbuyOrderHasCoupon(ebuyOrderHasCoupon);
		}
		if (!param.isFromTotalOrder() && realAmount <= 0) {// 免单处理（一键缴费的免单已在总订单处处理）
			commPayService.paySuccessOperate(ebuyOrder.getId(), EbuyDict.EbuyPay_PayMethod.PureSupriseGiftList);// 纯优惠支付
		}

		PreOrderDto preOrder = new PreOrderDto();
		preOrder.setOrderId(ebuyOrder.getId());
		preOrder.setTotalAmt(BigDecimal.valueOf(amount));
		preOrder.setAmtBt(BigDecimal.valueOf(ptAmountBt+wyAmountBt));
		preOrder.setRealAmt(BigDecimal.valueOf(realAmount));
		preOrder.setFree(realAmount <= 0);

		return preOrder;
	}

	/**
	 * 处理月卡费用为0的月卡
	 * @param amount
	 */
	private void dealPerMonthFeeWithFree(long amount) {
		if(amount<=0) {
			BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.BUSINESS_FAILED);
			businessRuntimeException.setErrorMsg("月卡费用为0，不允许线上缴费！");
			throw businessRuntimeException;
		}
	}

	/**
	 * 外来车不允许在APP上缴费
	 * @param amount
	 */
	public void forbiddenOuterCarPay(Object from) {
		if(from!=null && from.toString().equals("1")) {
			BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.BUSINESS_FAILED);
			businessRuntimeException.setErrorMsg("非小区车辆，不允许缴费！");
			throw businessRuntimeException;
		}
	}
	
	/**
	 * 处理月卡可缴月份问题 
	 * @param expire yyyy-MM-dd
	 * @param month
	 */
	private void dealXMFMonthCarAvailableMonth(String expire, long month) {
		int maxMonth = XMFCarService.getPayMaxMonths(expire);
		if (month > maxMonth) {
			BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.BUSINESS_FAILED);
			businessRuntimeException.setErrorMsg("缴费月份数不能大于可缴月份数！");
			throw businessRuntimeException;
		}
	}
	
	/**
	 * 获取优惠券金额（单位：元）
	 * @param ucId
	 * @return
	 */
	private long getCouponMoney(BigInteger ucId){
		long couponMoney = 0L;
		if (null!=ucId) {
			UserCoupon userCoupon = userCouponBaseDao.selectUserCouponBySeqId(ucId);
			if (UserCouponStatus.VALID.equals(userCoupon.getStatus())) {
				// 全使用有效期校验
				if (StringUtils.isNotBlank(userCoupon.getUseStartDate())) {
					Date userStartDate = DateUtils.convertStrToDate(userCoupon.getUseStartDate());
					if (DateUtils.getDiffDays(new Date(), userStartDate)<0) {
						BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.BUSINESS_FAILED);
						businessRuntimeException.setErrorMsg("券不在使用期内，支付失败！");
						throw businessRuntimeException;
					}
				}
				Date userEndDate = DateUtils.convertStrToDate(userCoupon.getUseEndDate());
				if (DateUtils.getDiffDays(new Date(), userEndDate)>0) {
					BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.BUSINESS_FAILED);
					businessRuntimeException.setErrorMsg("券已过期，支付失败！");
					throw businessRuntimeException;
				} else {
					Coupon coupon = couponBaseDao.selectCouponBySeqId(userCoupon.gettCouponFId());
					couponMoney = coupon.getDiscountMoney();
				}
			} else {
				BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.BUSINESS_FAILED);
				if (UserCouponStatus.USED.equals(userCoupon.getStatus())) {
					businessRuntimeException.setErrorMsg("该券已被使用，支付失败！");
				} else {
					businessRuntimeException.setErrorMsg("该券已失效，支付失败！");
				}
				throw businessRuntimeException;
			}
		}

		return couponMoney;
	}

	/**
	 * 根据userId和小区Id查询缴费记录
	 * @param userId
	 * @param groupBuildingId
	 * @return
	 */
	@Override
	public List<PayCarFeeLog> qryPaymentCarFeeRecords(BigInteger userId, BigInteger groupBuildingId){
		return accessDao.qryPaymentCarFeeRecords(userId, groupBuildingId);
	}

	public void updateCarKeyBill(BigInteger orderId){
		CarNumPayLog carNumpayLog = accessDao.getCarPayLogDetail(orderId);
		CarNumList car = carNumListBaseDao.selectCarNumListBySeqId(carNumpayLog.gettCarNumId());
		if(carNumpayLog.getPayNum()>0){
			BigInteger gbId = car.gettGroupBuildingFId();
			MonthCarInfo monthCarInfo = thirdCarFactory.getMonthCarInfo(gbId, car.gettCarNum());
			String startExpiredate = DateUtil.formatSecond.get().format(new Date(monthCarInfo.getExpire()));
			String endExpireDate = dealPayExpire(monthCarInfo.getExpire(), carNumpayLog.getPayNum().intValue());			
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("carId", carNumpayLog.gettCarNumId());
			paramMap.put("expireDate", endExpireDate);
			accessDao.updateCarexpireDate(paramMap);
			{
				// 修改缴费区间
				carNumpayLog.setPayStartDate(startExpiredate);
				carNumpayLog.setPayEndDate(endExpireDate);
			}
		}else{
			if(car.getFee()==null || car.getFee()==0){
				car.setFee(carNumpayLog.getFee());
			}else{
				car.setFee(carNumpayLog.getFee()+car.getFee());
			}
			car.setSys0UpdTime(dualDao.getNowTime());
			carNumListBaseDao.updateCarNumList(car);
		}
		carNumpayLog.setPayTime(dualDao.getNowTime());
		carNumpayLog.setPushStatus(0);
		int times = 0;
		carNumpayLog.setPushTimes(times);
		carNumpayLog.setSendTime(CarUtil.getSendTime(times));
		
		carNumPayLogBaseDao.updateCarNumPayLog(carNumpayLog);
	}
	
	/**
	 * <p>有效期处理</p>
	 * 当上次缴费有效期为30，且续费月数有31号，则有效期为31号<br>
	 * 当有效开始日期为2月的最后一天，则截止也应为当月最后一天
	 */
	public String dealPayExpire(long expire, int payMonth) {
		Date startExpire = new Date(expire);
		
		Date endExpire = DateUtils.addMonths(startExpire, payMonth);
		// 当月最后一天
		int startExpireLastDay = DateUtils.getLastDayOfMonth(startExpire).getDate();
		if(startExpire.getDate()==startExpireLastDay){
			endExpire = DateUtils.getLastDayOfMonth(endExpire);
		}

		return DateUtil.formatSecond.get().format(endExpire);
	}
	
	@Override
	public RealRoom getValidateUserId(Map<String, Object> paramMap) {
		return accessDao.getValidateUserId(paramMap);
	}

	@Override
	public CarNumPayLog getCarPayLogDetail(BigInteger orderId) {
		return accessDao.getCarPayLogDetail(orderId);
	}

	@Override
	public String getGbNameByOrderId(BigInteger orderId) {
		return accessDao.getGbNameByOrderId(orderId);
	}

	/**
	 * 根据orderId获取停车缴费支付备注。格式：{物业公司名称}车牌，缴费期间[停车费]
	 *
	 * @param orderId
	 * @return
	 */
	@Override
	public String getPayNoteWithCarByOrderId(BigInteger orderId){
		return accessDao.getPayNoteWithCarByOrderId(orderId);
	}
	
	@Override
	public void updateCarexpireDate(Map<String, Object> paramMap) {
		accessDao.updateCarexpireDate(paramMap);;
	}
	
	@Override
	public BigInteger getRealRoomIdByGbId(BigInteger gbId, String buildingName, String roomNum,String unit_num) {
		BigInteger buildingId = null;
		Building buildingQry = new Building();
		buildingQry.settGroupBuildingFId(gbId);
		buildingQry.setName(buildingName);
		List<Building> existBuildingList = buildingBaseDao.selectBuildingByCondition(MapConverter.toMap(buildingQry), false);
		if(existBuildingList!=null&&existBuildingList.size()>0){
			buildingId = existBuildingList.get(0).getId();
		}
		BigInteger realRoomAddId = null;
		RealRoom realRoomQry = new RealRoom();
		realRoomQry.settBuildingFId(buildingId);
		realRoomQry.setUnitName(unit_num);
		realRoomQry.setNumTail(roomNum);
		List<RealRoom> existRealRoomList = realRoomBaseDao.selectRealRoomByCondition(MapConverter.toMap(realRoomQry), false);
		if(existRealRoomList!=null&&existRealRoomList.size()>0){
			realRoomAddId = existRealRoomList.get(0).getId();
		}
		return realRoomAddId;
	}

	@Override
	@Transactional
	public BigInteger payDoorKeyOrder(BigInteger payId,Long payFee, BigInteger userId, Integer subChannelId) {
		BigInteger resOrderId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order);
		String nowTime = dualDao.getNowTime();
		EbuyOrder ebuyOrder = new EbuyOrder();
		ebuyOrder.setType(EbuyDict.EbuyOrder_Type.DoorKey_Bill);//订单类型：门禁缴费
		ebuyOrder.setId(resOrderId);
		ebuyOrder.setBuyerId(userId);
		ebuyOrder.setCurrRoomId(userBaseDao.selectUserBySeqId(userId).getDefaultRoomId());
		ebuyOrder.setBuyTime(nowTime);
		ebuyOrder.setCreater(userId);
		ebuyOrder.setOrderNo(OrderNoGenerator.getOrderNo(resOrderId));
		ebuyOrder.setPayMethod(null);
		ebuyOrder.setPayTime(null);
		ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.DaiFuKuan);
		ebuyOrder.setPayStatus(EbuyDict.EbuyOrder_PayStatus.Not_Pay);//支付状态为待支付
		ebuyOrder.setDelivStatus(EbuyDict.EbuyOrder_DelivStatus.Not_Deliv);//发货状态为未发货
		ebuyOrder.setTotalDeliveryFee(0L);//总配送费
		ebuyOrder.setAmount(payFee); //应付金额
		ebuyOrder.setTotalCouponAmount(0L); //优惠金额
		ebuyOrder.setSubChannel(subChannelId == null ? null : subChannelId + "");
		// 新增订单
		int res = ebuyOrderBaseDao.insertEbuyOrder(ebuyOrder);
		if (res <= 0) {
			throw new BusinessRuntimeException("payDoorKeyOrder.insertEbuyOrder.error");
		}
		//生成门禁缴费记录
		CloudKeyPaylog cloudKeyPaylog = new CloudKeyPaylog();
		BigInteger payLogId = uuidManager.getNextUuidBigInteger(SEQConstants.t_cloud_key_paylog);
		cloudKeyPaylog.setId(payLogId);
		cloudKeyPaylog.setSys0AddTime(nowTime);
		cloudKeyPaylog.setStatus(0);
		cloudKeyPaylog.settEbuyOrderId(resOrderId);
		cloudKeyPaylog.settPayKeyListId(payId);
		cloudKeyPaylog.setCreateTime(nowTime);
		cloudKeyPaylog.setHuaId(userId);
		cloudKeyPaylog.setPayStatus(1);
		int keyRes = cloudKeyPaylogBaseDao.insertCloudKeyPaylog(cloudKeyPaylog);
		if (keyRes <= 0) {
			throw new BusinessRuntimeException("payDoorKeyOrder.insertCloudKeyPaylog.error");
		}
		return resOrderId;
	}

	public void updateDoorKeyBill(BigInteger orderId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tEbuyOrderId", orderId);
		paramMap.put("sys0DelState", 0);
		List<CloudKeyPaylog> resList = cloudKeyPaylogBaseDao.selectCloudKeyPaylogByCondition(paramMap, false);
		EbuyOrder ebuyOrder = ebuyOrderBaseDao.selectEbuyOrderBySeqId(orderId);
		if(resList!=null && resList.size()>0){
			CloudKeyPaylog cloudKeyPaylog = resList.get(0);
			cloudKeyPaylog.setPayStatus(2);
			cloudKeyPaylog.setSys0UpdTime(CnfantasiaCommbusiUtil.getCurrentTime());
			cloudKeyPaylogBaseDao.updateCloudKeyPaylog(cloudKeyPaylog);
			long monNum = payKeyListBaseDao.selectPayKeyListBySeqId(cloudKeyPaylog.gettPayKeyListId()).getPayTime();
			paramMap.clear();
			paramMap.put("huaId", ebuyOrder.getBuyerId());
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, Integer.parseInt(String.valueOf(monNum)));
			SimpleDateFormat format = new SimpleDateFormat(AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
			String exprideTime = format.format(cal.getTime());
			List<CloudKeyUserAudit> userList = cloudKeyUserAuditBaseDao.selectCloudKeyUserAuditByCondition(paramMap,false);
			for(CloudKeyUserAudit auditUser:userList){
				auditUser.setSys0DelTime(exprideTime);//到期时间
			}
			cloudKeyUserAuditBaseDao.updateCloudKeyUserAuditBatch(userList);
		}
	}

	@Override
	public List<UserDoorKeyMsg> qryUserDoorKeyDetail(Map<String, Object> paramMap) {
		return accessDao.qryUserDoorKeyDetail(paramMap);
	}

	@Override
	public List<CarNumPayLogDetail> qryjiefangquPayLog() {
		return accessDao.qryjiefangquPayLog(null);
	}

	@Override
	public List<CarNumPayLogDetail> updateCarNumPayLog() {
		commonLockService.getLock(Lock.CAR_BILLPUSH);
		List<CarNumPayLogDetail> resList = accessDao.qryjiefangquPayLog(null);
		for(CarNumPayLog payLog:resList){
			payLog.setPushStatus(AccessConstant.push_status_at);
			carNumPayLogBaseDao.updateCarNumPayLog(payLog);
		}
		return resList;
	}

	@Override
	public void saveOpenDoorLog(List<OpenDoorLog> logList) {
		openDoorLogBaseService.insertOpenDoorLogBatch(logList);
	}

	@Override
	public List<UserCarpushMsg> qrycarexpirePushMsg() {
		return accessDao.qrycarexpirePushMsg();
	}

	@Override
	public void pushTouserCarBill(List<UserCarpushMsg> carmsgList){

		String nowTime = CnfantasiaCommbusiUtil.getCurrentTime();
		for(UserCarpushMsg usercarMsg:carmsgList){
			//都为注册用户
			CommUserDataEntity cudes = new CommUserDataEntity(usercarMsg.getUserId(),1,usercarMsg.getDefaultRoomId());
			Message msg = new Message();
			msg.setCreater(usercarMsg.getUserId());
			msg.setContent(usercarMsg.getMsgDesc());
			msg.setTitle(usercarMsg.getCarMsgtitle());
			msg.setTime(nowTime);
			msg.setType(NoticeDict.Message_Type.Car_bill_expire_msg);
			msg.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message));
			if(usercarMsg.getUserCar().getCarStatus()==1){
				usercarMsg.getUserCar().setCardname("月卡车");
				usercarMsg.getUserCar().setMonthcard(true);
			}else{
				usercarMsg.getUserCar().setCardname("次卡车");
				usercarMsg.getUserCar().setMonthcard(false);
			}
			List<BigInteger> idList = uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter,2);
			List<MessageParameter> mps = new ArrayList<MessageParameter>();
			MessageParameter tmpMessageParameter = new MessageParameter();
			tmpMessageParameter.setId(idList.get(0));
			tmpMessageParameter.setKey("pushId");
			tmpMessageParameter.settMessageFId(msg.getId());
			tmpMessageParameter.setValue(MsgpushDict.PushId.CarBillMsg);
			//车辆参数
			MessageParameter tmpMessageParameter1 = new MessageParameter();
			tmpMessageParameter1.setId(idList.get(1));
			tmpMessageParameter1.setKey("extraPayload");
			tmpMessageParameter1.setValue(usercarMsgTojson(usercarMsg));
			tmpMessageParameter1.settMessageFId(msg.getId());
			mps.add(tmpMessageParameter);
			mps.add(tmpMessageParameter1);
			// 超时时间是第二天0点
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_YEAR, 1);
			msgpushService.addMessage2Pool(cudes, msg, DateUtil.formatDay.get().format(c.getTime()), mps);
		}

	}

	@Override
	public RoomHasCarNum qryCarBindedUser(Map<String, Object> paramMap) {
		return accessDao.qryCarBindedUser(paramMap);
	}

	public String usercarMsgTojson(UserCarpushMsg usercarMsg){
		//转map为json
		Map<String, Object> jsonStr = new HashMap<String, Object>();
		jsonStr.put("cardname", usercarMsg.getUserCar().getCardname());
		jsonStr.put("fee",usercarMsg.getUserCar().getFee());
		jsonStr.put("monthcard", usercarMsg.getUserCar().getMonthcard());
		jsonStr.put("platenumber", usercarMsg.getUserCar().getPlatenumber());
		jsonStr.put("groupbuildingName", usercarMsg.getUserCar().getGroupbuildingName());
		jsonStr.put("id", usercarMsg.getUserCar().getId());
		final Date now = new Date();
		Date expiredate;
		try {
			expiredate = DateUtil.formatSecond.get().parse(usercarMsg.getExprideTime());
			jsonStr.put("expiredate", expiredate);
			jsonStr.put("validdays", (expiredate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(jsonStr,true);
	}

	@Override
	public List<ParkCache> getCache(String parkCode, List<BigInteger> remains) {
		return accessDao.getCache(parkCode, remains);
	}

	@Override
	public List<CarNumPayLogDetail> getPendingCarNumPayLog(BigInteger gbid) {
		commonLockService.getLock(Lock.CAR_BILLPUSH);
		List<CarNumPayLogDetail> resList = accessDao.qryjiefangquPayLog(gbid);
		return resList;
	}

	/**
	 * 根据carId查询car信息(carNum、fee)
	 * @param carId
	 * @return
	 */
	@Override
	public Map<String, Object> queryCarInfoByCardId(BigInteger carId){
		return accessDao.queryCarInfoByCardId(carId);
	}

	/**
	 * 根据carNum查询car信息(carId、carNum、fee)
	 * @param carId
	 * @return
	 */
	@Override
	public Map<String, Object> queryCarInfoByCardNum(String carNum){
		return accessDao.queryCarInfoByCardNum(carNum);
	}

	/**
	 * 查询临时车费用
	 *
	 * @param plate
	 * @param gbId
	 * @param isLimitEndTime
	 */
	public TmpCarPayDetail qryParkingFeeDetail(BigInteger orderId){
		return accessDao.qryParkingFeeDetail(orderId);
	}

	/**
	 * 临时车查询小区gbId、carId
	 * @param paramMap ==>carNum
	 * @return
	 */
	@Override
	public  Map<String, Object> qryParkingInfo(Map<String, Object> paramMap){
		return accessDao.qryParkingInfo(paramMap);
	}

	/**
	 * 查询车禁缴费随机立减优惠金额
	 *
	 * @param carPreferParam
	 * @return
	 */
	@Override
	public CarPreferDto getPayCarPrefer(CarPreferParam carPreferParam){
		return accessDao.getPayCarPrefer(carPreferParam);
	}

	/**
	 * 查询物业缴费处车辆缴费卡
	 *
	 *
	 * @param gbId
	 * @param userId
	 * @return
	 */
	@Override
	public List<CarBill> queryCarBill(BigInteger gbId, BigInteger userId){
		return accessDao.queryCarBill(gbId, userId);
	}

	@Override
	public CarNumList queryCarNumListByCondition(Map<String, Object> paramMap){
		return accessDao.queryCarNumListByCondition(paramMap);
	}

	/**
	 * <p>处理进场时间<p>
	 * 华鹏飞车禁同步过来的时间有问题，要处理
	 *
	 * @param inTime
	 * @return
	 */
	private static final String dealInTime(String inTime){
		if(null!=inTime){
			String second = "00";
			return inTime.substring(0, inTime.length()-second.length()).concat(second);
		}

		return inTime;
	}

	/**
	 * 车辆进场记录
	 * @param carNum
	 * @param gbId
	 * @param inDate
	 * @param carType 车辆类型
	 */
	@Override
	@Transactional
	public boolean vehIn(String carNum, BigInteger gbId, String inDate, Integer carType, String cardsn){
		inDate = dealInTime(inDate);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("carNum", carNum);
		paramMap.put("gbId", gbId);
		CarNumList carnum = getCardetail(paramMap);

		if (null == carnum) {
			// 没有车辆信息，新增一临时车
			carnum = new CarNumList();
			carnum.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_list));
			carnum.settCarNum(carNum);
			carnum.settGroupBuildingFId(gbId);
			carnum.setFee(0L);
			carnum.setExpireDate(DateUtils.getCurrentDate());
			carnum.setStatus(carType);
			carnum.setLockStatus(0);
			carnum.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
			carNumListBaseDao.insertCarNumList(carnum);
		}

		dealHpfCardsn(cardsn, gbId, carNum);
		
		paramMap.clear();
		paramMap.put("tCarNumId", carnum.getId());
		paramMap.put("tGroupBuildingId", gbId);
		paramMap.put("parkingStartTime", inDate);
		int count = parkingRecordBaseDao.selectParkingRecordCount(paramMap, true);
		if(count==0){
			ParkingRecord parkingRecord = new ParkingRecord();
			parkingRecord.setParkingStartTime(inDate);
			parkingRecord.setParkingEndTime(null);
			parkingRecord.setParkingFee(0L);
			parkingRecord.settCarNumId(carnum.getId());
			parkingRecord.settGroupBuildingId(gbId);
			parkingRecord.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_parking_record));
			return parkingRecordBaseDao.insertParkingRecord(parkingRecord) > 0;
		} else {
			return true;
		}
	}
	
	private void dealHpfCardsn(String cardsn, BigInteger gbId, String carnum) {
		if(null!=cardsn) {
			Code code = BaseCarService.getCodeByGbId(gbId);
			if(AccessDict.Code.huapengfei.equals(code)) {
				Map<String, Object> paramMap = new HashMap<String, Object>(1);
				paramMap.put("tCarnum", carnum);
				List<CarHpfCardsn> carHpfCardsnList = carHpfCardsnBaseDao.selectCarHpfCardsnByCondition(paramMap, false);
				if(null==carHpfCardsnList || carHpfCardsnList.size()==0) {
					CarHpfCardsn carHpfCardsn = new CarHpfCardsn();
					carHpfCardsn.settCarnum(carnum);
					carHpfCardsn.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_hpf_cardsn));
					carHpfCardsn.setCardsn(cardsn);
					carHpfCardsn.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
					carHpfCardsn.setSys0DelState(0);
					carHpfCardsnBaseDao.insertCarHpfCardsn(carHpfCardsn);
				} else {
					CarHpfCardsn carHpfCardsn = carHpfCardsnList.get(0);
					carHpfCardsn.setCardsn(cardsn);
					carHpfCardsn.setSys0UpdTime(CnfantasiaCommbusiUtil.getCurrentTime());
					carHpfCardsnBaseDao.updateCarHpfCardsn(carHpfCardsn);
				}
			}
		}
	}
	
	@Override
	public String getHpfCardsn(String carnum) {
		String cardsn;
		
		Map<String, Object> paramMap = new HashMap<String, Object>(1);
		paramMap.put("tCarnum", carnum);
		List<CarHpfCardsn> carHpfCardsnList = carHpfCardsnBaseDao.selectCarHpfCardsnByCondition(paramMap, false);
		if(null==carHpfCardsnList || carHpfCardsnList.size()==0) {
			cardsn = DateTime.now().toString("yyMMddHHmmss");
			
			CarHpfCardsn carHpfCardsn = new CarHpfCardsn();
			carHpfCardsn.settCarnum(carnum);
			carHpfCardsn.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_hpf_cardsn));
			carHpfCardsn.setCardsn(cardsn);
			carHpfCardsn.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
			carHpfCardsn.setSys0DelState(0);
			carHpfCardsnBaseDao.insertCarHpfCardsn(carHpfCardsn);
		} else {
			cardsn = carHpfCardsnList.get(0).getCardsn();
		}
		
		return cardsn;
	}

	/**
	 * 车辆出场记录
	 *
	 * @param carNum
	 * @param gbId
	 * @param inDate
	 * @param outDate
	 * @param fee 费用（单位：分）
	 * @param carType 车辆类型
	 * @return
	 */
	@Override
	@Transactional
	public boolean vehOut(String carNum, BigInteger gbId, String inDateStr, String outDateStr, long fee, Integer carType){
		inDateStr = dealInTime(inDateStr);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("carNum", carNum);
		paramMap.put("gbId", gbId);
		CarNumList carnum = getCardetail(paramMap);
		if (null == carnum) {
			// 没有车辆信息，新增一临时车
			carnum = new CarNumList();
			carnum.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_list));
			carnum.settCarNum(carNum);
			carnum.settGroupBuildingFId(gbId);
			carnum.setFee(0L);
			carnum.setExpireDate(DateUtils.getCurrentDate());
			carnum.setStatus(carType);
			carnum.setLockStatus(0);
			carnum.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
			carNumListBaseDao.insertCarNumList(carnum);
		}
		if(fee>0) {
			CarNumPayLog carNumPayLog = new CarNumPayLog();
			paramMap.clear();
			paramMap.put("tCarNumId", carnum.getId());
			paramMap.put("fee", fee);
			paramMap.put("payNum", 0);
			if(AccessDict.Code.huaan.equals(BaseCarService.getCodeByGbId(gbId))) {// 华安的payTime有误差
				Date outDate = DateUtils.convertStrToDate(outDateStr, AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
				DateTime outDateTime = new DateTime(outDate.getTime());
				String payTimeSTART = outDateTime.minusMinutes(5).toString(AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
				paramMap.put("payTime_START", payTimeSTART);
				paramMap.put("payTime_END", outDateStr);
			} else {
				paramMap.put("payTime", outDateStr);
			}
			int paylogCount = carNumPayLogBaseService.getCarNumPayLogCount(paramMap);
			if(paylogCount==0){// 没有记录则新增
				BigInteger cnplId = uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_pay_log);
				carNumPayLog.setId(cnplId);
				carNumPayLog.settCarNumId(carnum.getId());
				carNumPayLog.setFee(fee);
				carNumPayLog.setPayNum(0L);
				carNumPayLog.setPayTime(outDateStr);
				carNumPayLog.setCashStatus(1);
				carNumPayLog.setNeedBill(0);
				carNumPayLog.setPushStatus(1);
				carNumPayLog.setStatus(1);
				carNumPayLog.setCouponAmount(0L);
				carNumPayLog.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
				
				carNumPayLogBaseService.insertCarNumPayLog(carNumPayLog);
			}
		}

		// 查询入场信息，若没有，则新建
		paramMap.clear();
		paramMap.put("parkingStartTime", inDateStr);
		paramMap.put("tCarNumId", carnum.getId());
		paramMap.put("tGroupBuildingId", gbId);
		List<ParkingRecord> prs = parkingRecordBaseDao.selectParkingRecordByCondition(paramMap,false);

		boolean result = false;
		if (null != prs && 0 < prs.size()) {
			ParkingRecord parkingRecord = prs.get(0);
			parkingRecord.setParkingStartTime(inDateStr);
			parkingRecord.setParkingEndTime(outDateStr);
			parkingRecord.setParkingFee(fee);
			result = 0 < parkingRecordBaseDao.updateParkingRecord(parkingRecord);
		} else {
			ParkingRecord parkingRecord = new ParkingRecord();
			parkingRecord.setParkingStartTime(inDateStr);
			parkingRecord.setParkingEndTime(outDateStr);
			parkingRecord.setParkingFee(fee);
			parkingRecord.settCarNumId(carnum.getId());
			parkingRecord.settGroupBuildingId(gbId);
			parkingRecord.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_parking_record));
			result = 0 < parkingRecordBaseDao.insertParkingRecord(parkingRecord);
		}

		return result;
	}

	/**
	 * 线下缴费同步
	 * @param jsonObject
	 * @param isMonthCar 是否为月卡缴费
	 * @return
	 */
	@Override
	public boolean synXmfCarPayLog(JSONObject jsonObject, boolean isMonthCar){
		BigDecimal actualFee = jsonObject.getBigDecimal("actualfee");
		if(null!=actualFee){
			long fee = actualFee.multiply(BigDecimal.valueOf(100L)).longValue();
			if(fee>0){
				String carNum = jsonObject.getString("VLP");
				String parkId = jsonObject.getString("parkid");
				BigInteger gbId = hpfCarService.getGbIdByParkId(parkId);

				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("carNum", carNum);
				paramMap.put("gbId", gbId);
				CarNumList carnum = getCardetail(paramMap);
				if (null == carnum) {
					// 没有车辆信息，新增一临时车
					carnum = new CarNumList();
					carnum.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_list));
					carnum.settCarNum(carNum);
					carnum.settGroupBuildingFId(gbId);
					carnum.setFee(0L);
					carnum.setExpireDate(DateUtils.getCurrentDate());

					String carType = jsonObject.getString("Ctype");
					Integer jfqCarType = HpfCarService.getJFQCarType(carType);
					carnum.setStatus(jfqCarType);
					carnum.setLockStatus(0);
					carnum.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
					carNumListBaseDao.insertCarNumList(carnum);
				}

				CarNumPayLog carNumPayLog = new CarNumPayLog();
				carNumPayLog.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_pay_log));
				carNumPayLog.settCarNumId(carnum.getId());
				carNumPayLog.setStatus(1);
				carNumPayLog.setPushStatus(1);

				if(isMonthCar){
					long perMonthFee = jsonObject.getLongValue("perMonthFee");
					carNumPayLog.setPayNum(fee/perMonthFee);
					carNumPayLog.setPayStartDate(jsonObject.getString("starttime"));
					String expireDate = jsonObject.getString("endtime");
					carNumPayLog.setPayEndDate(expireDate);

					// 月卡要更新有效期
					carnum.setFee(perMonthFee);
					carnum.setExpireDate(expireDate);
					carNumListBaseDao.updateCarNumList(carnum);
				} else {
					carNumPayLog.setPayNum(0L);
					carNumPayLog.settGroupBuildingFId(gbId);
				}
				carNumPayLog.setFee(fee);
				carNumPayLog.setCouponAmount(0L);

				BigDecimal receivableFee = jsonObject.getBigDecimal("receivablefee");
				carNumPayLog.setReceivableFee(receivableFee.multiply(BigDecimal.valueOf(100L)).longValue());
				String now = DateTime.now().toString(AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
				String outtime = jsonObject.getString("outtime");
				if(StringUtils.isNotBlank(outtime)){
					carNumPayLog.setPayTime(outtime.toString());
				} else {
					carNumPayLog.setPayTime(now);
				}
				carNumPayLog.setCashStatus(1);
				carNumPayLog.setNeedBill(0);
				carNumPayLog.setSys0AddTime(now);

				return carNumPayLogBaseDao.insertCarNumPayLog(carNumPayLog)>0;
			}
		}

		return true;
	}

	/**
	 * 处理车牌前缀
	 */
	@Override
	public String getCarNumPrefixByGbId(BigInteger gbId){
		if (null==gbId) {
			return AccessDict.DEFAULT_CAR_NUM_PREFIX;
		}
		String key = AccessDict.CachePrefix.CARNUM_KEY_PREFIX + gbId;
		String cacheData = RedisCacheHandler.get(key);
		if (StringUtils.isBlank(cacheData)) {
			String carNum = accessDao.selectCarNumPrefixByGbId(gbId);
			return dealCarNumPrefixByGbId(gbId, carNum);
		} else {
			return cacheData;
		}
	}

	private String dealCarNumPrefixByGbId(BigInteger gbId, String carNum){
		String carNumPrefix = AccessDict.DEFAULT_CAR_NUM_PREFIX;
		if(StringUtils.isNotBlank(carNum) && carNum.length()>=2){
			carNumPrefix = carNum.substring(0, 2);
		}
		int cacheTime = CnfantasiaCommbusiUtil.getSysParaValueInt(SysParamKey.CAR_PREFIX_CACHE_TIME, 3600);//默认时间1小时（3600秒）
		String key = AccessDict.CachePrefix.CARNUM_KEY_PREFIX + gbId;
		RedisCacheHandler.set(key, carNumPrefix, cacheTime);

		return carNumPrefix;
	}

	/**
	 * 刷新车牌前缀缓存
	 */
	@Override
	public void refreshCarNumPrefix(){
		List<CarNumPrefixEntity> carNumPrefixList = accessDao.selectCarNumPrefixList();
		if(null!=carNumPrefixList && carNumPrefixList.size()>0){
			for(CarNumPrefixEntity carNumPrefix : carNumPrefixList){
				if(null==carNumPrefix.getGbId()){continue;}
				dealCarNumPrefixByGbId(carNumPrefix.getGbId(), carNumPrefix.getCarNum());
			}
		}
	}

	/**
	 * 获取除小蜜蜂之外的费用列表
	 * @param fee
	 * @return
	 */
	@Override
	public List<CarFeeType> getCarFeeTypeListNotXMF(BigDecimal fee){
		List<CarFeeType> carFeeTypeList = new ArrayList<CarFeeType>();
		int[] months = {1,3,6,12};
		for(int i=0; i<months.length; i++){
			CarFeeType carFeeType = new CarFeeType();
			carFeeType.setFee(fee.multiply(BigDecimal.valueOf(months[i])));
			carFeeType.setNum(months[i]);
			carFeeType.setUnit("月");
			carFeeType.setTypeName(months[i]+"个月");

			carFeeTypeList.add(carFeeType);
		}

		return carFeeTypeList;
	}

	@Override
	public BigInteger getGbIdByCarNum(String carNum) {
		return accessDao.getGbIdByCarNum(carNum);
	}
	
	/**
     * 查询解放区停车场
     * @param name
     * @return
     */
	@Override
    public List<JfqPlotEntity> getJFQPlots(String name){
		return accessDao.selectJFQPlots(name);
    }
	
	/**
	 * 查询停车场信息（轻应用导航）
	 * @param city
	 * @param plotName
	 * @return
	 */
	public List<PlotInfo> getPlots(String city, String plotName){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("city", city);
		paramMap.put("plotName", plotName);
		
		return accessDao.selectPlots(paramMap);
	}
}
