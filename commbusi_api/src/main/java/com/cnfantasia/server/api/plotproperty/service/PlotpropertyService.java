/**
 * Filename:    PlotpropertyService.java
 * @version:    1.0
 * Create at:   2014年8月13日 上午8:24:51
 * Description:
 *
 * Modification History:
 * Date        Author      Version     Description
 * -----------------------------------------------------------------
 * 2014年8月13日    shiyl      1.0         1.0 Version
 */
package com.cnfantasia.server.api.plotproperty.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cnfantasia.server.api.plotproperty.entity.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.service.ICommonEbuyService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonPrizeService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRedenvelopeService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.util.CommonRoomUtil;
import com.cnfantasia.server.api.ebuy.util.OrderNoGenerator;
import com.cnfantasia.server.api.paybillUserPrefer.entity.PaybillUserPrefer;
import com.cnfantasia.server.api.paybillUserPrefer.service.IPaybillUserPreferService;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.payment.serviceUntran.ICommPayService;
import com.cnfantasia.server.api.payment.serviceUntran.IPayConfigService;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyConstant;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyDict;
import com.cnfantasia.server.api.plotproperty.dao.IPlotpropertyDao;
import com.cnfantasia.server.api.plotproperty.util.PayBillShowUtil;
import com.cnfantasia.server.api.plotproperty.util.PropertyFeeCalculator;
import com.cnfantasia.server.api.prize.constant.PrizeDict;
import com.cnfantasia.server.api.prize.entity.PrizeRecordEntityWithBusinessMonthYear;
import com.cnfantasia.server.api.prize.entity.PrizeRecordSimpleEntity;
import com.cnfantasia.server.api.property.constant.PropertyConstant;
import com.cnfantasia.server.api.property.dto.PayBillDetailDto;
import com.cnfantasia.server.api.property.dto.PreOrderDto;
import com.cnfantasia.server.api.property.dto.PropertyBillTailDto;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.redenvelope.util.IDiscount2hbRule;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.api.room.dao.IGroupBuildingDao;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.RealRoomEntity;
import com.cnfantasia.server.api.room.entity.RoomBaseInfo;
import com.cnfantasia.server.api.room.entity.RoomEntity;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.roomVerifyPayment.dao.IRoomVerifyPaymentDao;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.commbusi.alterPeriod.dao.IAlterPeriodDao;
import com.cnfantasia.server.commbusi.plotproperty.entity.AppendBillInfo;
import com.cnfantasia.server.commbusi.plotproperty.entity.BusinessMonthYearFactory;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;
import com.cnfantasia.server.commbusi.plotproperty.entity.ISectionDateSignal;
import com.cnfantasia.server.commbusi.plotproperty.entity.MonthOrTime;
import com.cnfantasia.server.commbusi.plotproperty.entity.PayBillInfo;
import com.cnfantasia.server.commbusi.plotproperty.entity.PropFeeDiscount;
import com.cnfantasia.server.commbusi.plotproperty.entity.SectionDateFactory;
import com.cnfantasia.server.commbusi.plotproperty.service.IPlotpropertyCfgService;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.alterPeriodDataDetail.dao.IAlterPeriodDataDetailBaseDao;
import com.cnfantasia.server.domainbase.alterPeriodDataDetail.entity.AlterPeriodDataDetail;
import com.cnfantasia.server.domainbase.alterPeriodDataDetail.service.IAlterPeriodDataDetailBaseService;
import com.cnfantasia.server.domainbase.alterPeriodPrefer.dao.IAlterPeriodPreferBaseDao;
import com.cnfantasia.server.domainbase.alterPeriodPrefer.entity.AlterPeriodPrefer;
import com.cnfantasia.server.domainbase.devicePayCount.entity.DevicePayCount;
import com.cnfantasia.server.domainbase.devicePayCount.service.IDevicePayCountBaseService;
import com.cnfantasia.server.domainbase.ebuyOrder.dao.IEbuyOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.dao.IEbuyOrderHasTPayBillBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.entity.EbuyOrderHasTPayBill;
import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.entity.FixedFeeItemHasRoom;
import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.service.IFixedFeeItemHasRoomBaseService;
import com.cnfantasia.server.domainbase.fixedFeeItemRoomData.service.IFixedFeeItemRoomDataBaseService;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.payBill.dao.IPayBillBaseDao;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payBillSplit.entity.PayBillSplit;
import com.cnfantasia.server.domainbase.payBillType.dao.IPayBillTypeBaseDao;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;
import com.cnfantasia.server.domainbase.propertyFeeDetail.dao.IPropertyFeeDetailBaseDao;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;
import com.cnfantasia.server.domainbase.propertyManagement.service.IPropertyManagementBaseService;
import com.cnfantasia.server.domainbase.propertyService.entity.PropertyService;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.room.dao.IRoomBaseDao;
import com.cnfantasia.server.domainbase.room.entity.Room;
import com.cnfantasia.server.domainbase.roomVerifyPayment.entity.RoomVerifyPayment;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.userHasTRoom.dao.IUserHasTRoomBaseDao;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;
import com.cnfantasia.server.domainbase.userPayCount.entity.UserPayCount;
import com.cnfantasia.server.domainbase.userPayCount.service.IUserPayCountBaseService;
import com.cnfantasia.server.ms.payBill.dao.IPayBillDao;
import com.propertySoftwareDock.base.dao.IPropertySoftwareDockDao;
import com.propertySoftwareDock.base.entity.RealRoomSoftwareInfo;
import com.propertySoftwareDock.base.service.IPropertySoftwareDockService;

/**
 * Filename:    PlotpropertyService.java
 * @version:    1.0.0
 * Create at:   2014年8月13日 上午8:24:51
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月13日       shiyl             1.0             1.0 Version
 */
public class PlotpropertyService implements IPlotpropertyService{

	private Log logger = LogFactory.getLog(getClass());

	private IPlotpropertyDao plotpropertyDao;
	public void setPlotpropertyDao(IPlotpropertyDao plotpropertyDao) {
		this.plotpropertyDao = plotpropertyDao;
	}

	private IPayBillBaseDao payBillBaseDao;

	private IPaybillUserPreferService paybillUserPreferService;

	@Resource
	private IUserHasTRoomBaseDao userHasTRoomBaseDao;

	@Resource
	private IAlterPeriodDao alterPeriodDao;

	@Resource
	private IPayBillTypeBaseDao payBillTypeBaseDao;
	@Resource
	private IPropertyFeeDetailBaseDao propertyFeeDetailBaseDao;
	@Resource
	private IPayConfigService payConfigService;
	@Resource
	private IAlterPeriodPreferBaseDao alterPeriodPreferBaseDao;
	@Resource
	private IPropertySoftwareDockDao propertySoftwareDockDao;

    @Resource
    private IFixedFeeItemHasRoomBaseService fixedFeeItemHasRoomBaseService;
    @Resource
    private IFixedFeeItemRoomDataBaseService fixedFeeItemRoomDataBaseService;

	private IGroupBuildingDao groupBuildingDao;
	public void setGroupBuildingDao(IGroupBuildingDao groupBuildingDao) {
		this.groupBuildingDao = groupBuildingDao;
	}

	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}

	private ICommonPrizeService commonPrizeService;
	public void setCommonPrizeService(ICommonPrizeService commonPrizeService) {
		this.commonPrizeService = commonPrizeService;
	}

	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	private IEbuyOrderBaseDao ebuyOrderBaseDao;
	public void setEbuyOrderBaseDao(IEbuyOrderBaseDao ebuyOrderBaseDao) {
		this.ebuyOrderBaseDao = ebuyOrderBaseDao;
	}

	private IEbuyOrderHasTPayBillBaseDao ebuyOrderHasTPayBillBaseDao;
	public void setEbuyOrderHasTPayBillBaseDao(IEbuyOrderHasTPayBillBaseDao ebuyOrderHasTPayBillBaseDao) {
		this.ebuyOrderHasTPayBillBaseDao = ebuyOrderHasTPayBillBaseDao;
	}

	private IRoomBaseDao roomBaseDao;
	public void setRoomBaseDao(IRoomBaseDao roomBaseDao) {
		this.roomBaseDao = roomBaseDao;
	}

	private IUserBaseDao userBaseDao;
	public void setUserBaseDao(IUserBaseDao userBaseDao) {
		this.userBaseDao = userBaseDao;
	}

	private IDiscount2hbRule discount2hbRule;
	public void setDiscount2hbRule(IDiscount2hbRule discount2hbRule) {
		this.discount2hbRule = discount2hbRule;
	}

	private ICommPayService commPayService;
	public void setCommPayService(ICommPayService commPayService) {
		this.commPayService = commPayService;
	}

	private IPlotpropertyCfgService plotpropertyCfgService;
	public void setPlotpropertyCfgService(
			IPlotpropertyCfgService plotpropertyCfgService) {
		this.plotpropertyCfgService = plotpropertyCfgService;
	}

	private ICommonEbuyService commonEbuyService;
	public void setCommonEbuyService(ICommonEbuyService commonEbuyService) {
		this.commonEbuyService = commonEbuyService;
	}

	private ICommonRedenvelopeService commonRedenvelopeService;
	public void setCommonRedenvelopeService(ICommonRedenvelopeService commonRedenvelopeService) {
		this.commonRedenvelopeService = commonRedenvelopeService;
	}

	private IRoomVerifyPaymentDao roomVerifyPaymentDao;
	public void setRoomVerifyPaymentDao(IRoomVerifyPaymentDao roomVerifyPaymentDao) {
		this.roomVerifyPaymentDao = roomVerifyPaymentDao;
	}

	@Resource
	private IDevicePayCountBaseService devicePayCountBaseService;
	@Resource
	private IUserPayCountBaseService userPayCountBaseService;
	@Resource
	private IAlterPeriodDataDetailBaseService alterPeriodDataDetailBaseService;
	@Resource
	private IPayBillDao payBillDao;
	@Resource
	private IAlterPeriodDataDetailBaseDao alterPeriodDataDetailBaseDao;
	@Resource
	private IPropertyManagementBaseService propertyManagementBaseService;

	@Override
	public PlotpropertyOrderEntity getPlotpropertyOrderDetail(BigInteger userId, BigInteger orderId) {
		return plotpropertyDao.selectPlotpropertyOrderDetail(userId, orderId);
	}
	
	@Override
	public List<String> getPlotpropertyTotalOrderDetail(BigInteger orderId) {
		return plotpropertyDao.selectPlotpropertyTotalOrderDetail(orderId);
	}
	
	@Override
	public String getOrderAddress(BigInteger roomId) {
		return plotpropertyDao.selectOrderAddress(roomId);
	}
	
	@Override
	public PlotpropertyOrderEntity getPlotpropertyOrderDetail(Map<String, Object> paramMap) {
		return plotpropertyDao.selectPlotpropertyOrderDetail(paramMap);
	}
	@Override
	public PlotpropertyOrderEntity getPlotpropertyOrderDetailByOrderIdAndBillId(BigInteger orderId, BigInteger payBillId) {
		return plotpropertyDao.getPlotpropertyOrderDetailByOrderIdAndBillId(orderId,payBillId);
	}

	@Override
	public PlotpropertyCombEntity getLastPayBill(BigInteger userId, BigInteger billTypeId) {
		IBusinessMonthYear lastPayBillMonth = getLastPayBillMonthYear(userId,billTypeId);
		return getPayBillByMonth(userId, lastPayBillMonth,true,billTypeId);
	}

	@Override
	public IBusinessMonthYear getLastPayBillMonthYear(BigInteger userId, BigInteger billTypeId){
		return plotpropertyCfgService.getLastPayBillMonthYear(userId,billTypeId);
	}


	@Override
	public PayBillInfo getPayBillSimpleInfoByMonth(BigInteger userId, IBusinessMonthYear monthYearWithGB,BigInteger billTypeId){
		return plotpropertyCfgService.getPayBillSimpleInfoByMonth(userId, monthYearWithGB,billTypeId);
	}

	@Override
	public PlotpropertyCombEntity getPayBillDetailById(BigInteger userId, BigInteger billId,boolean needValidate) {
		getPayBillBeforeCheck(userId, needValidate);
		//查询默认门牌
		RoomEntity roomEntity = commonRoomService.getDefaultRoomInfoExceptionWhenEmptyWithSupport(userId);//包含点支持信息
		//查询物业账单
		GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(userId);
		PayBillInfo payBillEntity= plotpropertyCfgService.getPayBillSimpleInfoById(billId);

		IBusinessMonthYear resBMY = null;
		if(PlotpropertyDict.PayBillType_PaytimeType.MONTH.compareTo(payBillEntity.getPaytimeType())==0){//如果为月缴
			resBMY = BusinessMonthYearFactory.newInstanceByBillMonth(PayBillShowUtil.getBillShowMonth(payBillEntity), groupBuilding);//根据账单月份
		}else{//如果为周期缴费
			try {
				Date billStart = DateUtil.formatSecond.get().parse(payBillEntity.getBillMonthStart());
				Date billEnd = DateUtil.formatSecond.get().parse(payBillEntity.getBillMonthEnd());
				Date payStart = DateUtil.formatSecond.get().parse(payBillEntity.getPayDayStart());
				Date payEnd = DateUtil.formatSecond.get().parse(payBillEntity.getPayDayEnd());
				resBMY = BusinessMonthYearFactory.newInstance(billStart, billEnd, payStart, payEnd, groupBuilding);
			} catch (Exception e) {logger.error("PlotpropertyService.getPayBillDetailById failed;billId:"+billId,e);}
		}

		BigInteger billTypeId = payBillEntity.getBillTypeId();
		boolean useDefaultIfNull = false;
		PayBillType payBillType = plotpropertyCfgService.getPropBillTypeInfoByTypeIdAndPropIfNull(billTypeId, groupBuilding.getId(),useDefaultIfNull);
		if(payBillType==null){
			throw new BusinessRuntimeException("PlotpropertyService.getPayBillDetailById.payBillType.null",new Object[]{groupBuilding.getId()});
		}
		return getPayBillAfter(userId, payBillType, payBillEntity, groupBuilding, roomEntity, resBMY);
	}

	private void getPayBillBeforeCheck(BigInteger userId,boolean needValidate){
		//syl-add-2015-3-11 17:08:30 执行未成功缴费的订单对应的折扣退回处理
		deletePlotpropertyOrderAndBackDiscountAll();
		//查询单个账单，若只有支付失败的，且当前账单状态为未支付且f_payment_way=1 则退回折扣为可使用 
		if(needValidate){//syl-add 2015-5-14 21:23:43 needValidate
			checkPayBillRoomValidate(userId);
		}
	}
	private PlotpropertyCombEntity getPayBillAfter(BigInteger userId,PayBillType payBillType,PayBillInfo payBillEntity,GroupBuilding groupBuilding,RoomEntity roomEntity,IBusinessMonthYear monthYearWithGB){
		boolean isMonth = payBillType.getPaytimeType().compareTo(PlotpropertyDict.PayBillType_PaytimeType.MONTH) == 0;//是否为月缴账单
		PrizeRecordSimpleEntity prizeRecordEntity = null;
		List<PropFeeDiscount> splitPrizeRecordList = null;
		PlotpropertyOrderEntity plotpropertyOrderEntity = null;
		if(payBillEntity!=null){
			if(isMonth){//月缴则查询对应折扣月份的最低折扣信息
				String tmpYearMonthStr = DateUtil.strFormate(payBillEntity.getMonth(),DateUtil.formatSecond.get(), DateUtil.formatMonth.get());
				IBusinessMonthYear monthYearWithGBTmp = BusinessMonthYearFactory.newInstance(tmpYearMonthStr, groupBuilding, MonthOrTime.month);
				prizeRecordEntity = commonPrizeService.getLeastDiscountFirstByMonthAndUsed(userId,monthYearWithGBTmp);
			}else{//周期缴,则查询对应账单拆分表的折扣列表
				splitPrizeRecordList = plotpropertyCfgService.getLeastPrizeRecordListByBillId(payBillEntity.getId(),userId);
			}
			//支付订单信息
			plotpropertyOrderEntity = getPlotpropertyOrderByPayBillId(userId, payBillEntity.getId());
		}else{//没有物业账单，也查询该月的最低折扣
			if(isMonth){//syl-add-2015-12-15 16:24:39此时月缴才查询折扣
				if(StringUtils.isEmpty(monthYearWithGB)){//使用当月的
					PrizeRecordEntityWithBusinessMonthYear prizeRecordEntityExtend = commonPrizeService.getLeastDiscountFirstByMonthAndUsedNowTime(userId);
					prizeRecordEntity = prizeRecordEntityExtend.getPrizeRecordEntity();
				}else{
					prizeRecordEntity = commonPrizeService.getLeastDiscountFirstByMonthAndUsed(userId,monthYearWithGB);
				}
			}
		}
		if(isMonth){
			return new PlotpropertyCombEntity(roomEntity, payBillEntity, prizeRecordEntity, plotpropertyOrderEntity,payBillType,monthYearWithGB);
		}else{
			return new PlotpropertyCombEntity(roomEntity, payBillEntity, splitPrizeRecordList, plotpropertyOrderEntity,payBillType,monthYearWithGB);
		}
	}

	@Override
	public PlotpropertyCombEntity getPayBillByMonth(BigInteger userId, IBusinessMonthYear monthYearWithGB,boolean needValidate,BigInteger billTypeId) {
		getPayBillBeforeCheck(userId, needValidate);
		//查询默认门牌
		RoomEntity roomEntity = commonRoomService.getDefaultRoomInfoExceptionWhenEmptyWithSupport(userId);//包含点支持信息
		//查询物业账单
		GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(userId);
		boolean useDefaultIfNull = false;
		PayBillType payBillType = plotpropertyCfgService.getPropBillTypeInfoByTypeIdAndPropIfNull(billTypeId, groupBuilding.getId(),useDefaultIfNull);
		if(payBillType==null){
			throw new BusinessRuntimeException("PlotpropertyService.getPayBillByMonth.payBillType.null",new Object[]{groupBuilding.getId()});
		}
		PayBillInfo payBillEntity = getPayBillSimpleInfoByMonth(userId, monthYearWithGB,payBillType.getId());

		return getPayBillAfter(userId, payBillType, payBillEntity, groupBuilding, roomEntity, monthYearWithGB);
	}

	@Override
	public GroupBuildingEntity getGroupBuildingEntityById(BigInteger groupBuildingId,BigInteger userId) {
		return groupBuildingDao.selectGroupBuildingEntityById(groupBuildingId,userId);
	}

	@Override
	public RoomEntityWithValidate confirmRealRoomInfo(BigInteger userId, BigInteger realRoomId, String proprietorName,String payUserName) {
		RoomEntity currRoomEntity = commonRoomService.getDefaultRoomInfo(userId);
		if(!StringUtils.isEmpty(payUserName)){//刷新门牌信息
			Room roomUpd = new Room();
			roomUpd.setId(currRoomEntity.getId());
			roomUpd.setPayUserName(payUserName);
			roomBaseDao.updateRoom(roomUpd);
		}
		//校验信息是否合理
		RealRoomEntity realRoomEntity = commonRoomService.getRealRoomById(realRoomId);
		boolean validateRes = false;
		if(realRoomEntity!=null){
			//校验小区
			if(realRoomEntity.getBuildingEntity().gettGroupBuildingFId()
					.compareTo(currRoomEntity.getRealRoomEntity().getBuildingEntity().gettGroupBuildingFId())==0){
			}else{
				throw new BusinessRuntimeException("Plotproperty.confirmRealRoomInfo.groupBuilding.validate.failed");
			}
			{//校验是否已经有确认过的门牌
				Integer checkCount = getCheckRoomCountByRealRoomId(userId, realRoomId);
				if(checkCount!=null&&checkCount>0){//已存在确认过的门牌
					throw new BusinessRuntimeException("Plotproperty.confirmRealRoomInfo.checkRoomCount.isExist");
				}
			}

			validateRes = true;
		}
		if(!validateRes){
			throw new BusinessRuntimeException("Plotproperty.confirmRealRoomInfo.validate.failed");
		}
		//更改为新门牌，同时设置f_realroom_check_status=2
		Integer resCount = plotpropertyDao.updateAndConfirmRealRoomInfo(userId,realRoomId);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("Plotproperty.confirmRealRoomInfo.failed");
		} else {
			// 更新t_room_verify_payment注册状态
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tRealRoomId", realRoomId);
			List<RoomVerifyPayment> roomVerifyPayments = roomVerifyPaymentDao.selectRoomVerifyPaymentByCondition(paramMap, true);
			if(roomVerifyPayments!=null && roomVerifyPayments.size()>0){
				// update
				for(RoomVerifyPayment roomVerifyPayment:roomVerifyPayments){
					roomVerifyPayment.setRegisterState(1);
				}
				roomVerifyPaymentDao.updateRoomVerifyPaymentBatch(roomVerifyPayments);
			} else {
				// insert
				RealRoom realRoom = currRoomEntity.getRealRoomEntity();
				List<RoomVerifyPayment> roomVerifyPaymentList = roomVerifyPaymentDao.selectRoomVerifyPaymentsByBuildingId(realRoom.gettBuildingFId());
				{
					List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_room_verify_payment, roomVerifyPaymentList.size());
					for(int i=0; i<roomVerifyPaymentList.size(); i++){
						RoomVerifyPayment roomVerifyPayment = roomVerifyPaymentList.get(i);
						roomVerifyPayment.setId(ids.get(i));
						roomVerifyPayment.settRealRoomId(realRoomId);
						roomVerifyPayment.setRoomNum(realRoom.getNum());
						roomVerifyPayment.setUnit(realRoom.getUnitName());
						roomVerifyPayment.setRegisterState(1);
					}
					roomVerifyPaymentDao.insertRoomVerifyPaymentBatch(roomVerifyPaymentList);
				}
			}
		}

		//返回用户默认门牌信息
		return commonRoomService.getDefaultRoomInfo(userId);
	}

	@Override
	public PlotpropertyOrderEntity getPlotpropertyOrderByPayBillId(BigInteger userId,BigInteger payBillId) {
		PlotpropertyOrderEntity resOrderEntity = null;
		//用户当前门牌下提交的未支付订单列表
		List<PlotpropertyOrderEntity> currUserNotPayOrderWithRoomList = new ArrayList<PlotpropertyOrderEntity>();
		List<PlotpropertyOrderEntity> waitCallBack = new ArrayList<PlotpropertyOrderEntity>();//客户端支付成功，等待回调的订单列表
		//查询用户默认门牌信息
//		Room userRoom = commonRoomService.getDefaultRoomByUserId(userId);
		//查询账单是否已有对应的订单列表 
		List<PlotpropertyOrderEntity> tmoOrderList = plotpropertyDao.selectPlotpropertyOrderAllListByPayBillId(userId, payBillId);
		if(tmoOrderList!=null&&tmoOrderList.size()>0){
			for(PlotpropertyOrderEntity tmpPlotpropertyOrderEntity:tmoOrderList){

				if(EbuyDict.EbuyOrder_PayStatus.Pay_Success.compareTo(tmpPlotpropertyOrderEntity.getPayStatus())==0){
					resOrderEntity = tmpPlotpropertyOrderEntity;//成功直接返回
					break;
				}else if(tmpPlotpropertyOrderEntity.getClientPayStatus()!=null
						&&EbuyDict.EbuyOrder_ClientPayStatus.Client_Pay_Success.compareTo(tmpPlotpropertyOrderEntity.getClientPayStatus())==0){
					waitCallBack.add(tmpPlotpropertyOrderEntity);
				}else if(tmpPlotpropertyOrderEntity.getEbuyOrderHasTPayBill()!=null){
					/*if(userRoom.getId().compareTo(tmpPlotpropertyOrderEntity.getEbuyOrderHasTPayBill().getPrizeUserRoomId())==0){
//						resOrderEntity = tmpPlotpropertyOrderEntity;break;
						currUserNotPayOrderWithRoomList.add(tmpPlotpropertyOrderEntity);
					}else{
						//跳过记录
					}*/
					if(tmpPlotpropertyOrderEntity.getBuyerId().equals(userId)) {
						currUserNotPayOrderWithRoomList.add(tmpPlotpropertyOrderEntity);
					}
				}else{
//					resOrderEntity = tmpPlotpropertyOrderEntity;break;
				}
			}
		}

//		if(tmoOrderList!=null&&tmoOrderList.size()==1){
//			resOrderEntity = tmoOrderList.get(0);
//		}else if(tmoOrderList!=null&&tmoOrderList.size()>1){
//			throw new BusinessRuntimeException("Plotproperty.payBillId.hasMultiOrder.error",new Object[]{payBillId});
//		}

		if(resOrderEntity==null){
			if(waitCallBack!=null&&waitCallBack.size()>0){//先获取等待回调的
				resOrderEntity = waitCallBack.get(0);
				if(waitCallBack.size()>1){
					logger.info("Plotproperty.getPlotpropertyOrderByPayBillId.waitCallBack.size.morthan1,size is:"+waitCallBack.size());
				}
			}else if(currUserNotPayOrderWithRoomList!=null&&currUserNotPayOrderWithRoomList.size()>0){//再返回用户当前门牌下提交的抽奖记录的
				if(currUserNotPayOrderWithRoomList.size()==1){
					resOrderEntity = currUserNotPayOrderWithRoomList.get(0);
				}else{
					throw new BusinessRuntimeException("Plotproperty.getPlotpropertyOrderByPayBillId.multiOrder.failed");
				}
			}
		}

		return resOrderEntity;
	}

	/**
	 * 用户准备处理物业账单信息时
	 * 需要先进行的校验
	 * 校验 用户对应的门牌是否已经验证过
	 * @param userId
	 */
	private void checkPayBillRoomValidate(BigInteger userId){
		//校验 用户对应的门牌是否已经验证过
		RoomEntityWithValidate defaultRoomEntity = commonRoomService.getDefaultRoomInfoExceptionWhenEmpty(userId);
		if(commonRoomService.checkRealRoomFirstValidateAndUpd(defaultRoomEntity.gettRealRoomFId(), false)){
			defaultRoomEntity = commonRoomService.getDefaultRoomInfoExceptionWhenEmpty(userId);
		}
		RealRoomEntity currRealRoomEntity = defaultRoomEntity.getRealRoomEntity();
		if(	!defaultRoomEntity.checkIsValidedSuccess() ){//用户门牌未验证
			if(currRealRoomEntity.checkIsValidated()){//真实门牌有验证 则需要申请验证
//				throw new BusinessRuntimeException("Plotproperty.confirmPayBill.room.applyValidate");
			}else{//都未验证 则可以缴费
				Integer tmpVerifyStatus = defaultRoomEntity.fetchRoomValidteVerifyStatus();
				if(tmpVerifyStatus.compareTo(RoomDict.RoomValidte_VerifyStatus.SUCCESS)==0){

				}else if(tmpVerifyStatus.compareTo(RoomDict.RoomValidte_VerifyStatus.FAILED)==0){//验证失败
//					throw new BusinessRuntimeException("Plotproperty.confirmPayBill.verifyStatus.FAILED");
				}else if(tmpVerifyStatus.compareTo(RoomDict.RoomValidte_VerifyStatus.DOING)==0){//验证中
//					throw new BusinessRuntimeException("Plotproperty.confirmPayBill.verifyStatus.DOING");
				}else if(tmpVerifyStatus.compareTo(RoomDict.RoomValidte_VerifyStatus.UNDO)==0){//未验证
//					throw new BusinessRuntimeException("Plotproperty.confirmPayBill.verifyStatus.UNDO");
				}else{
//					throw new BusinessRuntimeException("Plotproperty.confirmPayBill.verifyStatus.Error");
				}
			}
		}else {
			if(!currRealRoomEntity.checkIsValidated()){
				commonRoomService.checkRealRoomFirstValidateAndUpd(currRealRoomEntity.getId(), true);
			}
		}

	}

	@Override
	public ConfirmPayBillRusultEntity confirmPayBill(BigInteger userId, BigInteger payBillId, Boolean useDiscount,Long subChannelId,Long hbMoney) {
		{//校验 用户对应的门牌是否已经验证过
			checkPayBillRoomValidate(userId);
		}
		BigInteger resOrderId = null;
		Boolean isAutoPaySucc = false;
		//查询当前账单基本信息
		PayBillEntity currPayBillEntity = plotpropertyDao.selectPayBillDetailById(userId, payBillId);
		PropertyFeeDetail manageFeeDetail = null;
		if(currPayBillEntity==null){
			throw new BusinessRuntimeException("Plotproperty.confirmPayBill.PayBill.qryEmpty");
		}
		boolean isMonth = currPayBillEntity.getPaytimeType().compareTo(PlotpropertyDict.PayBillType_PaytimeType.MONTH) == 0;//是否为月缴账单
//		boolean isProp = currPayBillEntity.getIsPropFee().compareTo(PlotpropertyDict.PayBillType_IsPropFee.YES)==0?true:false;//是否为物业费
		{
			for(PropertyFeeDetail tmpPropertyFeeDetail:currPayBillEntity.getPropertyFeeDetailList()){
				if(PlotpropertyDict.PropertyFeeDetail_Type.ManagerFee.compareTo(tmpPropertyFeeDetail.getType())==0){
					manageFeeDetail = tmpPropertyFeeDetail;
					break;
				}
			}
			if(manageFeeDetail==null){
				//syl-del-去掉校验逻辑
//				throw new BusinessRuntimeException("Plotproperty.confirmPayBill.manageFeeDetail.empty");
			}
		}
		//若账单状态为已支付，则直接提示已支付
		if(currPayBillEntity.getIsPay()!=null&&PlotpropertyDict.PayBill_IsPay.TRUE.compareTo(currPayBillEntity.getIsPay())==0){
			throw new BusinessRuntimeException("Plotproperty.confirmPayBill.PayBill.isPay");
		}else{//若未未支付
			//查询账单是否已有对应的订单列表 
			PlotpropertyOrderEntity tmpOrderEntity = getPlotpropertyOrderByPayBillId(userId, payBillId);
			if(tmpOrderEntity!=null){
				if(EbuyDict.EbuyOrder_PayStatus.Pay_Success.compareTo(tmpOrderEntity.getPayStatus())==0){//成功
					throw new BusinessRuntimeException("Plotproperty.confirmPayBill.orderStatus.inconsistent");//遍历 是否有成功的 则提示数据不一致
				}else {//未支付成功
					//判断是否使用了折扣
					EbuyOrderHasTPayBill tmpEbuyOrderHasTPayBill = tmpOrderEntity.getEbuyOrderHasTPayBill();
					//若订单已经生成 且使用了折扣 则返回当前订单Id
					if(tmpEbuyOrderHasTPayBill!=null&&tmpEbuyOrderHasTPayBill.getPrizeRecordId()!=null){
						resOrderId = tmpOrderEntity.getId();//返回账单Id
					}else{
						if(!useDiscount){//且不使用折扣
							resOrderId = tmpOrderEntity.getId();//返回账单Id
						}
					}
				}
			}
		}
		if(resOrderId==null){
			PrizeRecordSimpleEntity currUsedPrizeRrecord = null;//当前要使用的折扣信息
			List<PropFeeDiscount> splitPrizeRecordList = null;
			if(useDiscount){//useDiscount = true 时，查询当前是否有可用折扣，若有的话则继续，否则提示折扣已使用

				if(isMonth){
					String yearMonth = DateUtil.strFormate(currPayBillEntity.getMonth(),DateUtil.formatSecond.get(), DateUtil.formatMonth.get());
					IBusinessMonthYear monthYearWithGB = BusinessMonthYearFactory.newInstance(yearMonth, commonRoomService.getGroupBuildingByUserId(userId), MonthOrTime.month);
					PrizeRecordSimpleEntity prizeRecordSimpleEntity = commonPrizeService.getLeastDiscountFirstByMonthAndUsed(userId, monthYearWithGB);
					if(prizeRecordSimpleEntity!=null&&prizeRecordSimpleEntity.hasUsed()!=null&&!prizeRecordSimpleEntity.hasUsed()){//有最低折扣且未被使用
						currUsedPrizeRrecord = prizeRecordSimpleEntity;
					}else if(prizeRecordSimpleEntity!=null&&prizeRecordSimpleEntity.hasUsed()!=null&&prizeRecordSimpleEntity.hasUsed()){//有最低折扣且已被使用
						throw new BusinessRuntimeException("Plotproperty.confirmPayBill.miniDiscount.isUsed");
					}else{
						throw new BusinessRuntimeException("Plotproperty.confirmPayBill.miniDiscount.notExist");
					}
				}else{//周期缴,则查询对应账单拆分表的折扣列表
					splitPrizeRecordList = plotpropertyCfgService.getLeastPrizeRecordListByBillId(currPayBillEntity.getId(),userId);
				}

			}
			//生成订单相关信息，设定订单状态
			List<PayBillSplit> toUpdPayBillSplitList = new ArrayList<PayBillSplit>();
			// 组装订单信息
			String nowTime = dualDao.getNowTime();
			resOrderId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order);
			Double gainMoney = 0.0;//节省的金额
			Long totalDeliveryFee = 0L;
			if(currPayBillEntity.getIsPropFee().compareTo(PlotpropertyDict.PayBillType_IsPropFee.YES)==0){//仅物业费才可以优惠-syl-add-2015-12-16 16:50:09
				if(isMonth){
					if(currUsedPrizeRrecord!=null){//计算优惠的金额
						gainMoney = PropertyFeeCalculator.calculate(manageFeeDetail.getTotalPrice(), currUsedPrizeRrecord.getDiscount());
					} else {//月缴才会使用物业宝
//						gainMoney = manageFeeDetail.getAllowancePrice(); //使用物业宝抵扣
						gainMoney = currPayBillEntity.getDeduPrice()==null ? 0.0 : currPayBillEntity.getDeduPrice().doubleValue();
					}
				}else{
					if(splitPrizeRecordList!=null&&splitPrizeRecordList.size()>0){//计算优惠金额
						for(PropFeeDiscount tmpSplit:splitPrizeRecordList){
							PrizeRecord leastRecord = tmpSplit.getLeastRecord();
							Double gainMoneyTmp = 0.0;//节省的金额
							Long manageAmount = tmpSplit.getManageAmount()==null?0:tmpSplit.getManageAmount();
							if(leastRecord!=null){//有最低折扣
								gainMoneyTmp = PropertyFeeCalculator.calculate(manageAmount.doubleValue(), leastRecord.getDiscount());
								gainMoney+=gainMoneyTmp;
								tmpSplit.setDiscount(leastRecord.getDiscount());
								tmpSplit.setPrizeRecordId(leastRecord.getId());
							}
							
							tmpSplit.setSuccPayAmount(BigDecimal.valueOf(manageAmount).subtract(BigDecimal.valueOf(gainMoneyTmp)).longValue());
							toUpdPayBillSplitList.add(tmpSplit);
						}
					}
				}

			}

			if(gainMoney == null) {
				gainMoney = 0.0;
			}
			Double amount = currPayBillEntity.getAmount()-gainMoney+totalDeliveryFee;//实际待支付金额=原始金额-折扣优惠的金额+运费
			if(amount < 0) {
				amount = 0.0;
			}
			{
				EbuyOrder ebuyOrder = new EbuyOrder();
				ebuyOrder.setType(EbuyDict.EbuyOrder_Type.Property_Bill);//订单类型：物业账单
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
				{//拷贝配送地址
//					ebuyOrder.setDelivPeopleName(ebuyDeliveryAddressEntity.getPeopleName());
//					ebuyOrder.setDelivPhone(ebuyDeliveryAddressEntity.getPhone());
//					ebuyOrder.setDelivTargetType(ebuyDeliveryAddressEntity.getTargetType());
//					ebuyOrder.setDelivAddressArea(simpleDelivAddress.getAddressArea());
//					ebuyOrder.setDelivAddressDetail(simpleDelivAddress.getAddressDetail());
//					ebuyOrder.setDelivTargetId(simpleDelivAddress.getTargetId());
				}
				ebuyOrder.setTotalDeliveryFee(totalDeliveryFee);//总配送费
				ebuyOrder.setAmount(amount.longValue());

				ebuyOrder.setTotalCouponAmount(gainMoney.longValue());//add-优惠金额
				//syl-add2015-5-13 18:21:34 渠道
				ebuyOrder.setSubChannel(subChannelId==null?null:subChannelId+"");
				//syl-add-2015-12-16 17:27:02增加基础费用账单类型Id
				ebuyOrder.setBillTypeId(currPayBillEntity.getBillTypeId());
				ebuyOrder.setIsPropFee(currPayBillEntity.getIsPropFee());
				// 新增订单
				int res = ebuyOrderBaseDao.insertEbuyOrder(ebuyOrder);
				if(res<=0){
					throw new BusinessRuntimeException("Plotproperty.confirmPayBill.insertEbuyOrder.error");
				}
			}
			{//创建订单关系
				Double discount = null;
				BigInteger prizeRecordId = null;
				BigInteger prizeUserRoomId = null;
				if(currUsedPrizeRrecord!=null){
					discount = currUsedPrizeRrecord.getDiscount();
					prizeRecordId = currUsedPrizeRrecord.getId();
					Room prizeRoom = null;
					if(prizeRecordId!=null){
						prizeRoom = commonPrizeService.getRoomByPrizeRecordId(prizeRecordId);
					}
					if(prizeRoom!=null){
						prizeUserRoomId = prizeRoom.getId();
					}
					if(prizeUserRoomId==null){
						throw new BusinessRuntimeException("Plotproperty.confirmPayBill.prizeUserRoomId.notExist",new Object[]{prizeRecordId});
					}
				}
				BigInteger ebuyOrderHasTPayBillAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_has_t_pay_bill);
				EbuyOrderHasTPayBill ebuyOrderHasTPayBillAdd = new EbuyOrderHasTPayBill();
				ebuyOrderHasTPayBillAdd.setDiscount(discount);
				ebuyOrderHasTPayBillAdd.setId(ebuyOrderHasTPayBillAddId);
				ebuyOrderHasTPayBillAdd.setPrizeRecordId(prizeRecordId);
				ebuyOrderHasTPayBillAdd.settEbuyOrderFId(resOrderId);
				ebuyOrderHasTPayBillAdd.settPayBillFId(currPayBillEntity.getId());
				ebuyOrderHasTPayBillAdd.setPrizeUserRoomId(prizeUserRoomId);
				Integer resCount = ebuyOrderHasTPayBillBaseDao.insertEbuyOrderHasTPayBill(ebuyOrderHasTPayBillAdd);
				if(resCount==null||resCount<=0){
					throw new BusinessRuntimeException("Plotproperty.confirmPayBill.insertOrderHasPayBill.error");
				}
			}
			//标记折扣为已使用，应该是支付成功的时候标记折扣为已使用
//			commonPrizeService.markDiscountAsUsed(currUsedPrizeRrecord.getId());
			if(isMonth){
				//微信预支付成功，则锁定折扣信息
				if(currUsedPrizeRrecord!=null){
					//标记折扣为已使用
					Integer resCount = commonPrizeService.markDiscountAsUsed(currUsedPrizeRrecord.getId(),PrizeDict.PrizeRecord_UsedType.Plotproperty,gainMoney.longValue());
					if(resCount==null||resCount<=0){
						throw new BusinessRuntimeException("PropertyPayService.paySuccessOpt.markDiscount.failed");
					}
				}
			}else{
				if(splitPrizeRecordList!=null&&splitPrizeRecordList.size()>0){
					Integer resCount = plotpropertyCfgService.markDiscountAsUsed(toUpdPayBillSplitList,currPayBillEntity.getId(),PrizeDict.PrizeRecord_UsedType.Plotproperty);
					if(resCount==null||resCount<=0){
						throw new BusinessRuntimeException("PropertyPayService.paySuccessOpt.markDiscount.failed");
					}
				}
			}


			{//账单待支付金额为0则直接设定订单状态为成功 syl-add-2015-8-12 11:11:39
				if (amount!=null&&amount==0.0) {//剩余应付金额直接设置订单状态为支付成功
					BigInteger ebuyOrderAddId = resOrderId;
					commPayService.paySuccessOperate(ebuyOrderAddId, EbuyDict.EbuyPay_PayMethod.PureDiscount);
					isAutoPaySucc = true;
				}
			}
			//非物业费可以使用粮票支付syl-add-2015-12-16 17:49:51
			if(currPayBillEntity.getIsPropFee().compareTo(PlotpropertyDict.PayBillType_IsPropFee.NO_notMR)==0){
				BigInteger ebuyOrderAddId = resOrderId;
				if (hbMoney != null && hbMoney.compareTo(0L) > 0) {// 粮票使用且大于0
					logger.debug("bae pay bill start updateOrderByHb ...");
					Long orderLeftAmount=commonEbuyService.updateOrderByHb(userId, ebuyOrderAddId, hbMoney);
					logger.debug("bae pay bill updateOrderByHb success...");
					if (orderLeftAmount != null&& orderLeftAmount.compareTo(0L) == 0) {// 剩余应付金额直接设置订单状态为支付成功
						// 直接设定订单状态为成功
						logger.info("bae pay bill start to use hbMoney total.");
						commPayService.paySuccessOperate(ebuyOrderAddId, EbuyDict.EbuyPay_PayMethod.PureRedEnvelope);
//						commonEbuyService.paySuccessOperateComm(ebuyOrderAddId,EbuyDict.EbuyPay_PayMethod.PureRedEnvelope);
						logger.info("bae pay bill use hbMoney only success.");
						isAutoPaySucc = true;
					}
				}
			}

		}
		return new ConfirmPayBillRusultEntity(resOrderId, isAutoPaySucc);//返回订单Id
	}

	@Override
	public List<PayBillInfo> getIsPayBillList(BigInteger userId, PageModel pageModel,BigInteger billTypeId) {
		return plotpropertyCfgService.getIsPayBillList(userId,pageModel,billTypeId);
	}

	@Override
	public List<PropertyBillInfo> getIsPayBillList(BigInteger userId, PageModel pageModel) {
		List<PropertyBillInfo> list = plotpropertyCfgService.getIsPayBillList(userId,pageModel);
		List<PropertyBillInfo> list_02 = new ArrayList<PropertyBillInfo>();
		for (PropertyBillInfo propertyBillInfo : list) {
			List<EbuyOrder> orderList = propertyBillInfo.getOrderList();
			if(orderList != null && orderList.size() > 0) {
				EbuyOrder ebuyOrder = orderList.get(0);
				if(ebuyOrder.getPayStatus()!=null && ebuyOrder.getPayStatus()==1 && ebuyOrder.getClientPayStatus()!=null && ebuyOrder.getClientPayStatus() == 2) {
					propertyBillInfo.setOrderId(ebuyOrder.getId());
					if(ebuyOrder.getClientPayStatus()!=null) {
						propertyBillInfo.setClientPayStatus(ebuyOrder.getClientPayStatus());
					}
					if(ebuyOrder.getPayStatus()!=null) {
						propertyBillInfo.setPayStatus(ebuyOrder.getPayStatus());
					}
					if(ebuyOrder.getPayTime()!=null) {
						propertyBillInfo.setPayTime(ebuyOrder.getPayTime());
					}
					if(ebuyOrder.getTotalCouponAmount()!=null) {
						propertyBillInfo.setPreferentialAmt(Double.valueOf(PriceUtil.div100s(ebuyOrder.getTotalCouponAmount())));
					}
					list_02.add(propertyBillInfo);
				}
				if(ebuyOrder.getPayStatus()!=null && ebuyOrder.getPayStatus()!=1) {
					propertyBillInfo.setOrderId(ebuyOrder.getId());
					if(ebuyOrder.getClientPayStatus()!=null) {
						propertyBillInfo.setClientPayStatus(ebuyOrder.getClientPayStatus());
					}
					if(ebuyOrder.getPayStatus()!=null) {
						propertyBillInfo.setPayStatus(ebuyOrder.getPayStatus());
					}
					if(ebuyOrder.getPayTime()!=null) {
						propertyBillInfo.setPayTime(ebuyOrder.getPayTime());
					}
					if(ebuyOrder.getTotalCouponAmount()!=null) {
						propertyBillInfo.setPreferentialAmt(Double.valueOf(PriceUtil.div100s(ebuyOrder.getTotalCouponAmount())));
					}
					list_02.add(propertyBillInfo);
				}
			}
		}
		if(list_02!=null && list_02.size()>0) {
			return list_02;
		} else {
			return list;
		}
	}

	@Override
	public List<PayBillInfo> getNotPayBillList(BigInteger userId, PageModel pageModel,BigInteger billTypeId) {
		return plotpropertyCfgService.getNotPayBillList(userId,pageModel,billTypeId);
	}
	@Override
	public List<PayBillInfo> getNotPayBillList(BigInteger userId, BigInteger billTypeId) {
		List<PayBillInfo> resList = new ArrayList<PayBillInfo>();

//		临时注释掉
//		List<String> yearList = plotpropertyDao.selectExistPayBillYearList(userId,PlotpropertyDict.PayBill_IsPay.FALSE);
//		if(yearList!=null&&yearList.size()>0){
//			resList = plotpropertyDao.selectNotPayBillList(userId, yearList);
//		}

//		返回当月的未缴费的 后续增加返回 已过期的信息
		PlotpropertyCombEntity tmpPlotpropertyCombEntity = getLastPayBill(userId,billTypeId);
		if(tmpPlotpropertyCombEntity!=null){
			PayBillInfo payBill=tmpPlotpropertyCombEntity.getPayBillEntity();
			if(payBill!=null){
				Boolean isPay = null;
				isPay = payBill.getIsPay() != null && PlotpropertyDict.PayBill_IsPay.TRUE.compareTo(payBill.getIsPay()) == 0;
				if(!isPay){
					resList.add(payBill);//未支付则返回未支付的账单信息
				}
			}
		}
		return resList;
	}

	@Override
	public Integer updatePayBillInfoSuccessByApp(BigInteger payBillId,Long succPayAmount,Double discount,BigInteger prizeRecordId,Long hbAmount,BigInteger userId, Integer cycleType) {
		PaybillUserPrefer paybillUserPrefer = paybillUserPreferService.getUserBillPrefer(payBillId, userId);
		BigInteger preferType = null;
		if(paybillUserPrefer !=null || (cycleType != null && cycleType.equals(2))) {//增加选择周期缴费的优惠类型
			preferType = BigInteger.valueOf(1);
		}
		return plotpropertyDao.updatePayBillInfoSuccessByApp(payBillId, succPayAmount, discount, prizeRecordId,hbAmount,preferType,cycleType);
	}

	@Override
	public void deletePlotpropertyOrderAndBackDiscount(BigInteger orderId) {
		Integer resCount = plotpropertyDao.delLogicPlotpropertyOrderAndBackDiscount(orderId);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("Plotproperty.deletePlotpropertyOrder.failed");
		}
	}
	@Override
	public void deletePlotpropertyOrderAndBackDiscountAll() {
		Integer resCount = plotpropertyDao.delLogicPlotpropertyOrderAndBackDiscountAll();
		logger.info("PlotpropertyService.deletePlotpropertyOrderAndBackDiscountAll.resCount is :"+resCount);
	}

	@Override
	public Integer getCheckRoomCountByRealRoomId(BigInteger userId, BigInteger realRoomId) {
		return plotpropertyDao.selectCheckRoomCountByRealRoomId(userId, realRoomId);
	}

	@Override
	public List<PropertyService> getPlotpropertyServiceList(BigInteger userId) {
		BigInteger groupBuildingId = commonRoomService.getGroupBuildingIdByUserId(userId);
		List<PropertyService> propertyServiceList =  plotpropertyDao.selectPlotpropertyServiceList(groupBuildingId);
		return propertyServiceList;
	}

	@Override
	public Map<String, Object> plotpropertyCombEntity2Map02(PayBill payBill,PrizeRecordSimpleEntity prizeRecordSimpleEntity,List<PropertyFeeDetail> propertyFeeDetailList,PayBillType payBillType,IBusinessMonthYear businessMonthYear){
		Map<String,Object> resMap = new HashMap<String, Object>();
		Double miniDiscount = null;//最低折扣
		if(payBill!=null){
			if(payBill.getIsPay()!=null&&PlotpropertyDict.PayBill_IsPay.TRUE.compareTo(payBill.getIsPay())==0){//已经缴费过的
				miniDiscount = payBill.getDiscount();
			}else{
				if(prizeRecordSimpleEntity!=null&&prizeRecordSimpleEntity.hasUsed()!=null&&!prizeRecordSimpleEntity.hasUsed()){//有最低折扣且未被使用
					miniDiscount = prizeRecordSimpleEntity.getDiscount();
				}
			}
		}else{
			if(prizeRecordSimpleEntity!=null&&prizeRecordSimpleEntity.hasUsed()!=null&&!prizeRecordSimpleEntity.hasUsed()){//有最低折扣且未被使用
				miniDiscount = prizeRecordSimpleEntity.getDiscount();
			}
		}
		Long gainMoney = null;//优惠的金额
		Long manageFee = null;//应缴管理费[折扣前]
		Long manageFeeDiscount = null;//应缴管理费[折扣后]
		Long otherFee = 0L;//其他费用合计
		Map<String,Object> ext_manageFeeDetail = null;//应缴管理费详细
		List<Map<String,Object>> ext_otherFeeDetail = null;//其他费用详细
		if(propertyFeeDetailList!=null){
			ext_otherFeeDetail = new ArrayList<Map<String,Object>>();
			for(PropertyFeeDetail tmpPropertyFeeDetail:propertyFeeDetailList){
				Map<String,Object> tmpFeeMap = propertyFeeDetail2Map(tmpPropertyFeeDetail);
				if(PlotpropertyDict.PropertyFeeDetail_Type.ManagerFee.compareTo(tmpPropertyFeeDetail.getType())==0){
					manageFee = tmpPropertyFeeDetail.getTotalPrice()==null ? 0 : tmpPropertyFeeDetail.getTotalPrice().longValue();
					manageFeeDiscount = manageFee;
					if(miniDiscount!=null){
						//计算优惠的金额
						gainMoney = PropertyFeeCalculator.calculate(tmpPropertyFeeDetail.getTotalPrice(), miniDiscount).longValue();
						manageFeeDiscount = (tmpPropertyFeeDetail.getTotalPrice()==null?0:tmpPropertyFeeDetail.getTotalPrice().longValue())-gainMoney;
					}
					ext_manageFeeDetail=tmpFeeMap;
				}else{
					otherFee += (tmpPropertyFeeDetail.getTotalPrice()==null?0:tmpPropertyFeeDetail.getTotalPrice().longValue());
					ext_otherFeeDetail.add(tmpFeeMap);
				}
			}
		}
		Long amountDiscount = null;//总的折扣后金额
		if(payBill!=null){
			if(payBill.getIsPay()!=null&&PlotpropertyDict.PayBill_IsPay.TRUE.compareTo(payBill.getIsPay())==0){//已经缴费过的
				amountDiscount = (payBill.getSuccPayAmount() == null ? 0 : payBill.getSuccPayAmount()) + (payBill.getDeduPrice() == null ? 0 : payBill.getDeduPrice());
			}else{
				amountDiscount = payBill.getAmount();
				if(miniDiscount!=null){
					amountDiscount = payBill.getAmount()-gainMoney;
				}
			}
		}
		resMap.put("miniDiscount", miniDiscount);
		resMap.put("amountDiscount", PriceUtil.div100(amountDiscount == null ? 0 : amountDiscount));
		AppendBillInfo appendBillInfo = AppendBillInfo.newInstance(payBill,payBillType,businessMonthYear);
		resMap.put("billTypeName", appendBillInfo.getBillTypeName());
		if(payBill!=null){
			resMap.put("id", payBill.getId());
			resMap.put("amount", PriceUtil.div100(payBill.getAmount()));

			//syl-add-start 整合周期账单
			resMap.put("isPropFee", appendBillInfo.getIsPropFee());
			resMap.put("isMonthFee",appendBillInfo.getIsMonthFee());
			resMap.put("billPeriodDesc",appendBillInfo.getBillPeriodDesc());
			//syl-add-end
//			if(appendBillInfo.getIsMonthFee()){
//				resMap.put("month", DateUtil.strFormate(PayBillShowUtil.getBillShowMonth(payBill),DateUtil.formatSecond.get(), DateUtil.formatOnlyMonth.get()));
//				resMap.put("year", DateUtil.strFormate(PayBillShowUtil.getBillShowMonth(payBill),DateUtil.formatSecond.get(), DateUtil.formatOnlyYear.get()));
//			}

			appendPayInfo(payBill, null, resMap);
			//账单费用详情
			resMap.put("manageFee", PriceUtil.div100(manageFee));
			resMap.put("manageFeeDiscount", PriceUtil.div100(manageFeeDiscount));
			resMap.put("otherFee", PriceUtil.div100(otherFee));
			resMap.put("ext_manageFeeDetail", ext_manageFeeDetail);
			resMap.put("ext_otherFeeDetail", ext_otherFeeDetail);

		}
		return resMap;
	}

	/**
	 * 附加支付信息
	 * @param payBill
	 * @param ebuyOrder
	 * @param resMap
	 */
	private void appendPayInfo(PayBill payBill,EbuyOrder ebuyOrder,Map<String,Object> resMap){
		Boolean isPay = null;
		Integer payStatus = null;
		if(payBill!=null){
			if(payBill.getIsPay()!=null&&PlotpropertyDict.PayBill_IsPay.TRUE.compareTo(payBill.getIsPay())==0){
				isPay = true;
				payStatus = PlotpropertyConstant.PlotpropertyMap_PayStatus.successPay;
			}else{
				isPay = false;
				if(ebuyOrder!=null&&ebuyOrder.getClientPayStatus()!=null&&ebuyOrder.getClientPayStatus().compareTo(EbuyDict.EbuyOrder_ClientPayStatus.Client_Pay_Success)==0){
					payStatus = PlotpropertyConstant.PlotpropertyMap_PayStatus.waitPay;
				}else{
					payStatus = PlotpropertyConstant.PlotpropertyMap_PayStatus.notPay;
				}
			}
		}
		resMap.put("isPay", isPay);
		resMap.put("payStatus", payStatus);
	}
	/**
	 *
	 * @param payBill
	 * @param roomBaseInfo
	 * @param signStatus 签约状态
	 * @return
	 */
	@Override
	public Map<String,Object> plotpropertyCombEntity2Map02(BigInteger userId,PlotpropertyCombEntity plotpropertyCombEntity){
		String nowTime = dualDao.getNowTime();
		PlotpropertyCombShowEntity showEntity = PayBillShowUtil.parsePlotpropertyComb(userId, plotpropertyCombEntity, nowTime);
		Map<String,Object> resMap = new HashMap<String, Object>();
		BigInteger groupBuildingId = showEntity.getGroupBuildingId();//小区Id
		String groupBuildingDetail = showEntity.getGroupBuildingDetail();//门牌的市、区、小区信息
		String roomDetail = showEntity.getRoomDetail();//门牌的 楼栋、单元、门牌号的详细信息
		String propertyManageTel = showEntity.getPropertyManageTel();//物业管理处电话
		Boolean isRealRoomCheck = showEntity.getIsRealRoomCheck();//用户是否已经确认门牌
		String proprietorName = showEntity.getProprietorName();//业主姓名
//		Integer payLimiteStart = showEntity.getPayLimiteStart();//缴费周期Start
//		Integer payLimiteEnd = showEntity.getPayLimiteEnd();//缴费周期End
		Boolean isInPayCycle = showEntity.getIsInPayCycle();//是否在缴费周期内
		Boolean hasBillInfo = showEntity.getHasBillInfo();//是否有账单信息
		BigInteger orderId = showEntity.getOrderId();//物业订单Id
		Boolean isOrderDiscountCurrRoom = showEntity.getIsOrderDiscountCurrRoom();//订单使用的折扣是否为当前用户的默认门牌
		Double miniDiscount = showEntity.getMiniDiscount();//最低折扣
		Integer isBuyFinance = showEntity.getIsBuyFinance(); //是否购买了物业宝，用物业宝抵扣物业费
//		Long gainMoney = showEntity.getGainMoney();//优惠的金额
		Long manageFee = showEntity.getManageFee();//应缴管理费[折扣前]
		Long manageFeeDiscount = showEntity.getManageFeeDiscount();//应缴管理费[折扣后]
		Long otherFee = showEntity.getOtherFee();//其他费用合计
		PropertyFeeDetail ext_manageFeeDetail = showEntity.getExt_manageFeeDetail();//应缴管理费详细
		List<PropertyFeeDetail> ext_otherFeeDetail = showEntity.getExt_otherFeeDetail();//其他费用详细

		Long amountDiscount = showEntity.getAmountDiscount();//总的折扣后金额
		IBusinessMonthYear currPropStartEndTimeDescEntity = plotpropertyCombEntity.getMonthYearWithGB();
		Boolean isPay = showEntity.getIsPay();
		Integer payStatus = showEntity.getPayStatus();

		//小区相关信息
		boolean signStatus = plotpropertyCombEntity.fetchSignStatus();
		boolean isSupport = plotpropertyCombEntity.fetchIsSupport();
		int totalSupportCount = plotpropertyCombEntity.fetchTotalSupportCount();

		if(groupBuildingId!=null){//有小区信息
			resMap.put("groupBuildingId", groupBuildingId);
			resMap.put("groupBuildingDetail",groupBuildingDetail);
			resMap.put("roomDetail",roomDetail);
			resMap.put("signStatus",signStatus);//签约状态
			resMap.put("canPayProp",plotpropertyCombEntity.fetchCanPayProp());//syl-add-2015-8-4 15:29:37 是否可缴纳物业费
			resMap.put("isSupport",isSupport);//是否已经支持
			resMap.put("totalSupportCount",totalSupportCount);//总支持数
			resMap.put("miniDiscount", miniDiscount);
			resMap.put("isBuyFinance", isBuyFinance);
//			resMap.put("miniDiscount_convert_money", miniDiscount==null?null:PriceUtil.div100(discount2hbRule.getMoneyByDiscount(userId,miniDiscount)));
			resMap.put("miniDiscount_convert_money", showEntity.fetchMiniDiscountConvertMoney(discount2hbRule, userId));

			{//物业周期信息
				resMap.put("payLimiteStart",currPropStartEndTimeDescEntity==null?null:currPropStartEndTimeDescEntity.getPayTimeStartDesc());
				resMap.put("payLimiteEnd",currPropStartEndTimeDescEntity==null?null:currPropStartEndTimeDescEntity.getPayTimeEndDesc());
			}
			if(!signStatus){//未签约

			}else{
				resMap.put("isRealRoomCheck",isRealRoomCheck);
				if(isRealRoomCheck==null||!isRealRoomCheck){//未确认门牌

				}else{
					if(!StringUtils.isEmpty(proprietorName)){
						proprietorName = proprietorName.substring(0, 1)+"**";
					}
					resMap.put("proprietorName",proprietorName);
					resMap.put("isInPayCycle",isInPayCycle);
					resMap.put("propertyManageTel",propertyManageTel);
					resMap.put("hasBillInfo", hasBillInfo);

					PayBill payBill = plotpropertyCombEntity.getPayBillEntity();
					AppendBillInfo appendBillInfo = AppendBillInfo.newInstance(payBill,plotpropertyCombEntity.getPayBillType(),plotpropertyCombEntity.getMonthYearWithGB());
					resMap.put("billTypeName", appendBillInfo.getBillTypeName());
					if(hasBillInfo==null||!hasBillInfo){//无账单信息

					}else{
						resMap.put("amountDiscount", PriceUtil.div100(amountDiscount));
						if(payBill!=null){
							resMap.put("id", payBill.getId());
							resMap.put("amount", PriceUtil.div100(payBill.getAmount()));
							//syl-add-start 整合周期账单
							resMap.put("isPropFee", appendBillInfo.getIsPropFee());
							resMap.put("isMonthFee",appendBillInfo.getIsMonthFee());
							resMap.put("billPeriodDesc",appendBillInfo.getBillPeriodDesc());
							//syl-add-end
//							if(appendBillInfo.getIsMonthFee()){
//								resMap.put("month", DateUtil.strFormate(PayBillShowUtil.getBillShowMonth(payBill),DateUtil.formatSecond.get(), DateUtil.formatOnlyMonth.get()));
//								resMap.put("year", DateUtil.strFormate(PayBillShowUtil.getBillShowMonth(payBill),DateUtil.formatSecond.get(), DateUtil.formatOnlyYear.get()));
//							}
							if(!appendBillInfo.getIsMonthFee()){//增加折扣信息
								List<PropFeeDiscount> propFeeDiscountList = plotpropertyCombEntity.getSplitPrizeRecordList();
								List<Map<String,Object>> manageFeeList = new ArrayList<Map<String,Object>>();
								if(propFeeDiscountList!=null&&propFeeDiscountList.size()>0){
									boolean haveDiscount = false;
									for(PropFeeDiscount tmpPropFeeDiscount:propFeeDiscountList){
										Date date = null;
										try {date = DateUtil.formatSecond.get().parse(tmpPropFeeDiscount.getBillSubMonth());} catch (Exception e) {throw new BusinessRuntimeException(e);}
										ISectionDateSignal bmy = SectionDateFactory.newInstanceForBillMonth(date);
										Map<String,Object> tmpMap = new HashMap<String, Object>();
										tmpMap.put("billPeriodDesc", bmy.getPeriodDesc());
										tmpMap.put("manageFee", PriceUtil.div100(tmpPropFeeDiscount.getManageAmount()));
										tmpMap.put("manageFeeDiscount",PriceUtil.div100(tmpPropFeeDiscount.getSuccPayAmount()));
										tmpMap.put("miniDiscount", tmpPropFeeDiscount.getDiscount());
//										PrizeRecord leastRecord = tmpPropFeeDiscount.getLeastRecord();if(leastRecord!=null){haveDiscount = true;}
										if(tmpPropFeeDiscount.getPrizeRecordId()!=null){haveDiscount = true;}
//										
//										if(leastRecord.getDiscount()!=null){
//											tmpMap.put("manageFeeDiscount",PropertyFeeCalculator.calculate(tmpPropFeeDiscount.getManageAmount(), leastRecord.getDiscount()) );
//											tmpMap.put("miniDiscount", leastRecord.getDiscount());
//										}else{//无折扣
//											tmpMap.put("manageFeeDiscount",PriceUtil.div100(tmpPropFeeDiscount.getManageAmount()));
//											tmpMap.put("miniDiscount", null);
//										}
										manageFeeList.add(tmpMap);
									}
									if(haveDiscount){resMap.put("miniDiscount", 10);}//用于客户端判断是否有折扣
								}
//								{
//									Map<String,Object> tmpMap = new HashMap<String, Object>();
//									tmpMap.put("billPeriodDesc", "某年某月1");
//									tmpMap.put("manageFee", 99.99);
//									tmpMap.put("manageFeeDiscount  ", 123);
//									tmpMap.put("miniDiscount", 1.2);
//									manageFeeList.add(tmpMap);
//								}
								resMap.put("manageFeeList", manageFeeList);
							}
							resMap.put("isPay", isPay);
							resMap.put("payStatus", payStatus);

							//账单费用详情
							resMap.put("manageFee", PriceUtil.div100(manageFee));
							resMap.put("manageFeeDiscount", PriceUtil.div100(manageFeeDiscount));
							resMap.put("otherFee", PriceUtil.div100(otherFee));
							resMap.put("ext_manageFeeDetail", ext_manageFeeDetail==null?null:propertyFeeDetail2Map(ext_manageFeeDetail));
							List<Map<String,Object>> ext_otherFeeDetailListMap = new ArrayList<Map<String,Object>>();
							if(ext_otherFeeDetail!=null&&ext_otherFeeDetail.size()>0){
								for(PropertyFeeDetail tmpAA:ext_otherFeeDetail){
									Map<String,Object> otherFeeDetailTmpMap = propertyFeeDetail2Map(tmpAA);
									ext_otherFeeDetailListMap.add(otherFeeDetailTmpMap);
								}
							}
							resMap.put("ext_otherFeeDetail", ext_otherFeeDetailListMap);
							//支付订单相关信息
//							resMap.put("orderId", orderId);
//							resMap.put("orderIfUseDiscount", orderIfUseDiscount);
//							resMap.put("isDiscountCurrRoom", isDiscountCurrRoom);
							//折扣是否可编辑
							if(orderId!=null&&/*orderIfUseDiscount&&*/isOrderDiscountCurrRoom){
								resMap.put("isDiscountEditAble", false);
//								resMap.put("orderId", orderId);//syl-upd 2014-12-23 16:59:52 
								//syl-upd 2014-12-23 16:59:52 使用户每次走预支付流程 方便校验门牌是否有已经被校验
								resMap.put("orderId", null);
							}else{
								resMap.put("isDiscountEditAble", true);
								resMap.put("orderId", null);
							}
						}
					}
				}
			}
		}
		return resMap;
	}

	private Map<String,Object> propertyFeeDetail2Map(PropertyFeeDetail propertyFeeDetail){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("id",propertyFeeDetail.getId());
		tmpMap.put("type",propertyFeeDetail.getType());
		tmpMap.put("name",propertyFeeDetail.getName());
		tmpMap.put("signalPrice",propertyFeeDetail.getSignalPrice());
		tmpMap.put("priceUnitName",propertyFeeDetail.getPriceUnitName());
		tmpMap.put("priceUnitValue",PriceUtil.div100(propertyFeeDetail.getPriceUnitValue()));
		Double totalPrice = propertyFeeDetail.getTotalPrice()==null?0.0:propertyFeeDetail.getTotalPrice();
		tmpMap.put("totalPrice",PriceUtil.div100(BigDecimal.valueOf(totalPrice)));
		return tmpMap;
	}

	public Propfee getPropertyFeeAndCount(Map<String, Object> paramMap) {
		return plotpropertyDao.getPropertyFeeAndCount(paramMap);
	}

	/**
	 * 查询账单信息
	 *
	 * @param realRoomId
	 * @param isPay
	 * @return
	 */
	@Override
	public String getBillInfo(BigInteger realRoomId, String isPay){
		return plotpropertyDao.getBillInfo(realRoomId, isPay);
	}

	/**
	 * 是否用物业宝免缴本月账单
	 *
	 * @param realRoomId
	 * @return
	 */
	@Override
	public Integer getWYBPayStatus(BigInteger realRoomId){
		return plotpropertyDao.getWYBPayStatus(realRoomId);
	}

	@Override
	public List<PayBillType> getPayBillTypeList(BigInteger userId) {
		BigInteger gbId = commonRoomService.getGroupBuildingIdByUserId(userId);
		return plotpropertyCfgService.getPayBillTypeListAll(gbId);
	}

	@Override
	public IBusinessMonthYear getBusinessMonthYearByBillMonth(BigInteger userId,Date billMonth,BigInteger billTypeId){
		BigInteger gbId = commonRoomService.getGroupBuildingIdByUserId(userId);
		return plotpropertyCfgService.getBusinessMonthYearByBillMonth(gbId,billMonth,billTypeId);
	}

	@Override
	public IBusinessMonthYear getBusinessMonthYearByPayTime(BigInteger userId,
															Date date, BigInteger billTypeId) {
		return plotpropertyCfgService.getBusinessMonthYearByPayTime(userId, date, billTypeId);
	}

	@Override
	public BigInteger getPropPayBillTypeByGbId(BigInteger gbId,boolean useDefaultIfNull) {
		return plotpropertyCfgService.getPropPayBillTypeInfoByCondition(null, gbId, useDefaultIfNull).getId();
	}

	@Override
	public BigInteger getPropPayBillTypeByUserId(BigInteger userId,boolean useDefaultIfNull) {
		return plotpropertyCfgService.getPropPayBillTypeInfoByCondition(userId, null, useDefaultIfNull).getId();
	}


	@Override
	public PayBillType getPropBillTypeInfoByTypeIdAndPropIfNull(BigInteger typeId,BigInteger userId,boolean useDefaultIfNull){
		BigInteger gbId = commonRoomService.getGroupBuildingIdByUserId(userId);
		return plotpropertyCfgService.getPropBillTypeInfoByTypeIdAndPropIfNull(typeId,gbId,useDefaultIfNull);
	}

	@Override
	public List<PropertyBillEntity> getBillList(BigInteger userId) {
		List<PropertyBillEntity> propertyBillList = new ArrayList<PropertyBillEntity>();
		//获取小区信息
		GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(userId);
		//当前是周期缴费还是月缴
		PayBillType payBillTypeForWY = plotpropertyCfgService.getPayBillTypeForImport(groupBuilding.getId(), PlotpropertyConstant.DEFAULT_PAYBILL_FEE_NAME, true);
		Integer paytimeType = payBillTypeForWY==null?null:payBillTypeForWY.getPaytimeType();

		if(paytimeType == 1) {//月度缴
			List<PropertyBillEntity> propertyBillEntityList = plotpropertyDao.getInCycleBillByMonth(userId);
			/*PropertyBillEntity propertyBillEntity;
			if(propertyBillEntityList == null || propertyBillEntityList.isEmpty()) {//账单未出
				int payStart = groupBuilding.getPayPeriodStart();
				int payEnd = groupBuilding.getPayPeriodEnd();				
				//获取上次缴费月份
				String month = plotpropertyDao.selectLastPayBillMonth(userId);
				propertyBillEntity = new PropertyBillEntity();
				propertyBillEntity.setBillTitle(month);
				propertyBillEntity.setBillType("1");
				propertyBillEntity.setBillTypeName("物业费账单");
				propertyBillEntity.setBillTypeImg("");
				propertyBillEntity.setIsPay(0);
			} else {
				propertyBillEntity = propertyBillEntityList.get(0);
				//获取账单缴费月份
				String descStr = getDateDesc(propertyBillEntity.getForBillPayMonth(), groupBuilding);
				String payDescStr = descStr+" 可在线缴费";
				
				propertyBillEntity.setPayDateDesc(payDescStr);
			}*/
			if(propertyBillEntityList != null && propertyBillEntityList.size() > 0) {
				PropertyBillEntity propertyBillEntity = propertyBillEntityList.get(0);
				//获取账单缴费月份
				String descStr = getDateDesc(propertyBillEntity.getForBillPayMonth(), groupBuilding);
				String payDescStr = descStr+" 可在线缴费";

				propertyBillEntity.setPayDateDesc(payDescStr);
				propertyBillList.add(propertyBillEntity);
			}
		}

		//周期缴
		//获取该小区可进行缴费类型
		List<PayBillType> payBillTypeList = plotpropertyCfgService.getPayBillTypeList(groupBuilding.getId(), false);
		for (PayBillType payBillType : payBillTypeList) {
			PropertyBillEntity propertyBillEntity;
			propertyBillEntity = plotpropertyDao.getInCycleBillByDate(userId, payBillType.getId());
			/*if(propertyBillEntity == null) {//未出账信息
				//获取当前类型的可缴费时间
				propertyBillEntity = new PropertyBillEntity();
				String payTime = plotpropertyCfgService.getPayBillPayTime(payBillType.getId());
				propertyBillEntity.setBillTypeName(payBillType.getName());
				propertyBillEntity.setBillTypeImg(payBillType.getIcon());
				//propertyBillEntity.setPayDateDesc(payTime+" 可在线缴费");
			}*/
			if(propertyBillEntity != null) {
				propertyBillList.add(propertyBillEntity);
			}
		}

		return propertyBillList;
	}

	@Override
	public Map<String, Object> convertPropertyBillList(PropertyBillEntity propertyBillEntity) {
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("isSucBill", propertyBillEntity.getIsSucBill());
		resMap.put("payBillId", propertyBillEntity.getPayBillId());
		resMap.put("billTitle", propertyBillEntity.getBillTitle());
		
		//抄表也算是固定周期的一种
		String billType = propertyBillEntity.getBillType().equals(PlotpropertyDict.PayBillType_IsPropFee.NO_MR.toString()) ? "2": propertyBillEntity.getBillType();
		resMap.put("billType", billType);
		resMap.put("billTypeName", propertyBillEntity.getBillTypeName());

		String imgUrlDir = ApplicationContextBothUtil.getAbsolutePath(propertyBillEntity.getBillTypeImg(), SysParamKey.COMMUNITY_SUPPLY_TYPE_ICO_BASEPATH, propertyBillEntity.getLastUpdTime());
		resMap.put("billTypeImg", imgUrlDir);
		resMap.put("billPreferImg", "");//默认为空，如客户端需要显示随机立减图标，则进行图标路径填充

		Long billRelAmt = 0l;
		long billAmt = propertyBillEntity.getBillAmt();
		long deduPrice = propertyBillEntity.getDeduPrice();
		long preferentialAmt = propertyBillEntity.getPreferentialAmt();
		if(propertyBillEntity.getBillAmt() > 0) {
			if(propertyBillEntity.getFinanceStatus() == 1) {
				billRelAmt = billAmt - deduPrice - preferentialAmt;
			} else {
				billRelAmt = billAmt - preferentialAmt;
			}
		}
		if(billRelAmt < 0) {
			billRelAmt = 0l;
		}
		resMap.put("billAmt", PriceUtil.div100s(billAmt));
		resMap.put("billRelAmt", PriceUtil.div100s(billRelAmt));
		resMap.put("isPreferential", propertyBillEntity.getIsPreferential());
		resMap.put("preferentialType", propertyBillEntity.getPreferentialType());
		resMap.put("preferentialAmt", PriceUtil.div100s(preferentialAmt));

		if(propertyBillEntity.getPayDateDesc()!=null && !propertyBillEntity.getPayDateDesc().equals("")) {
			resMap.put("payDateDesc", propertyBillEntity.getPayDateDesc());
		} else {
			//选择周期,极致物业的没有对应的列表费用详情
			if(propertyBillEntity.getIsSucBill() == 1 && propertyBillEntity.getPayBillId() != null) {
				if((propertyBillEntity.getPayStart() != null || propertyBillEntity.getPayStart() != "") && (propertyBillEntity.getPayEnd() != null || propertyBillEntity.getPayEnd() != "")) {
					//String payDateDesc = "缴费截止至 "+propertyBillEntity.getPayStart() + "-" + propertyBillEntity.getPayEnd() +" 可在线缴费";
					String payDateDesc = "缴费截止至 "+propertyBillEntity.getPayEnd();
					resMap.put("payDateDesc", payDateDesc);
				}
			}
		}

		resMap.put("isPay", propertyBillEntity.getIsPay());
		resMap.put("isGetPreferential", propertyBillEntity.getIsGetPreferential());
		resMap.put("isFinanceStatus", propertyBillEntity.getFinanceStatus());
		resMap.put("propertyPeriodType", propertyBillEntity.getPropertyPeriodType());
		resMap.put("periodPayServiceStatus", propertyBillEntity.getPeriodPayServiceStatus());
		resMap.put("dataFromType", propertyBillEntity.getDataFromType());

		return resMap;
	}

	@Override
	public Map<String, Object> getPreferential(BigInteger userId, BigInteger billId) {
		//计算优惠金额
		Long preferAmt = 0l;
		preferAmt = plotpropertyDao.getPreferentialRandom(userId, billId);

		//更新优惠金额
		//1.获取账单优惠时间
		PayBill payBill = payBillBaseDao.selectPayBillBySeqId(billId);
		String startTime = "";
		String endTime = "";
		String payDescStr = "";
		startTime = payBill.getPayDayStart();
		endTime = payBill.getPayDayEnd();

		String startStr = "1月1日";
		String endStr = "12月31日";
		if(!startTime.equals("") && !endTime.equals("")) {
			Date startTimeDate = DateUtils.convertStrToDate(startTime, "yyyy-MM-dd HH:mm:ss");
			Date endTimeDate = DateUtils.convertStrToDate(endTime, "yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat simple = new SimpleDateFormat("MM月dd日");
			startStr = simple.format(startTimeDate);
			endStr = simple.format(endTimeDate);
			payDescStr = startStr+"-"+endStr;
		}
		//2.插入数据
		//2.1更新优惠表数据
		if(payBill.getAmount() < preferAmt ) {//如果随机金额大于账单金额，则显示账单金额
			preferAmt = payBill.getAmount();
		}
		PaybillUserPrefer payBillUserPrefer = paybillUserPreferService.getUserBillPrefer(billId, userId);
		GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(userId);
		if(groupBuilding.getIsPrefer() !=null && groupBuilding.getIsPrefer().equals(1)) {//小区配置了优惠
			if(payBillUserPrefer == null) {//插入
				payBillUserPrefer = new PaybillUserPrefer();
				BigInteger preferId = uuidManager.getNextUuidBigInteger(SEQConstants.t_paybill_user_prefer);
				payBillUserPrefer.setId(preferId);
				payBillUserPrefer.setIsGetPrefer(1);
				payBillUserPrefer.setPaybillId(billId);
				payBillUserPrefer.setPrefer(preferAmt);
				payBillUserPrefer.setPreferName(groupBuilding.getPreferName());
				payBillUserPrefer.setUserId(userId);
				payBillUserPrefer.setSys0AddTime(DateUtils.dateToString(new Date()));
				paybillUserPreferService.insertPaybillUserPrefer(payBillUserPrefer);
			} else {//更新
				payBillUserPrefer.setPrefer(preferAmt);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateStr = format.format(new Date());
				payBillUserPrefer.setSys0AddTime(dateStr);
				paybillUserPreferService.updatePaybillUserPrefer(payBillUserPrefer);
			}
			//3.返回数据
			Map<String,Object> resMap = new HashMap<String, Object>();
			resMap.put("preferentialAmt", PriceUtil.div100s(preferAmt));
			resMap.put("preferentialDate", payDescStr);

			return resMap;
		} else {
			//3.返回数据
			Map<String,Object> resMap = new HashMap<String, Object>();
			resMap.put("preferentialAmt", 0.00);
			resMap.put("preferentialDate", "");
			resMap.put("isPreferential", 0);//1 小区开通。0小区未开通

			return resMap;
		}

	}

	/**
	 * 获取缴费时间描述
	 * @param month YYYY-mm-dd
	 * @param groupBuilding
	 * @return xx月xx日-xx月xx日
	 */
	public String getDateDesc(String month,GroupBuilding groupBuilding) {
		if(month == null || month == "" || groupBuilding == null || (groupBuilding !=null && groupBuilding.getPayPeriodStart() == null || groupBuilding.getPayPeriodEnd() == null)) {
			return "xx月xx日-xx月xx日";
		}
		IBusinessMonthYear businessMonthYear = BusinessMonthYearFactory.newInstanceByBillMonth(month, groupBuilding);
		String startStr = businessMonthYear.getPayTimeStartDesc();
		String startStr2 = startStr.replaceAll("/", "-");
		startStr2= startStr2.substring(startStr2.indexOf("-")+1, startStr2.length());
		String endStr = businessMonthYear.getPayTimeEndDesc();
		String endStr2 = endStr.replaceAll("/", "-");
		endStr2 = endStr2.substring(endStr2.indexOf("-")+1, endStr2.length());
		String payDescStr = startStr2.replace("-", "月")+"日-"+endStr2.replace("-", "月")+"日";

		return payDescStr;
	}

	@Override
	public PlotpropertyOrderEntity getPlotpropertyNotPayOrderDetail(Map<String, Object> paramMap) {
		return plotpropertyDao.selectPlotpropertyNotPayOrderDetail(paramMap);
	}

	@Override
	@Transactional(propagation= Propagation.NESTED)
	public PreOrderDto confirmBill(BigInteger userId, BigInteger payBillId, Long subChannelId, String deviceId, Long hbAmountL, Integer month, Integer dataFromType, boolean isFromTotalOrder) {
		PreOrderDto preOrder = new PreOrderDto();
		if(payBillId != null && PropertyConstant.DataFromType.Jfq.equals(dataFromType.toString())) {//非选择周期缴费
			preOrder = confirmPayBill02(userId,payBillId, subChannelId, HeaderManager.getDeviceId(), hbAmountL, isFromTotalOrder);
		} else if(PropertyConstant.DataFromType.Third.equals(dataFromType.toString())) {//第三方物业  极致
			preOrder = confirmPayBillFor3rd(userId, subChannelId, HeaderManager.getDeviceId(), hbAmountL, isFromTotalOrder);
		}/* else if(payBillId == null && month != null && dataFromType.equals(0)) {//选择周期缴费
			preOrder = confirmAlterPayBill(userId, subChannelId,HeaderManager.getDeviceId(),month, hbAmountL, isFromTotalOrder);
		}*/
		return preOrder;
	}

	/**
	 * 周期缴费，月度缴费账单确认
	 * @param userId
	 * @param payBillId
	 * @param useDiscount
	 * @param subChannelId
	 * @param deviceId
	 * @param JFBAmount
     * @return
     */
	private PreOrderDto confirmPayBill02(BigInteger userId, BigInteger payBillId, Long subChannelId,String deviceId,long JFBAmount, boolean isFromTotalOrder) {
		{//校验 用户对应的门牌是否已经验证过
			checkPayBillRoomValidate(userId);
		}
		BigInteger resOrderId = null;
		Boolean isAutoPaySucc = false;
		//查询当前账单基本信息
		PayBillEntity currPayBillEntity = plotpropertyDao.selectPayBillDetailById(userId, payBillId);
		com.cnfantasia.server.api.property.service.PropertyService.dealBcBill(currPayBillEntity);
		
		if(currPayBillEntity!=null && currPayBillEntity.getBankCollectionStatus()!=null && currPayBillEntity.getBankCollectionStatus()==1){
			BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException(CommConstants.ResponseStatus.VALIDATE_ERR);
			businessRuntimeException.setErrorMsg("待缴账单中存在【托收中】的账单，请退出【物业缴费】，重新进入！");
			throw businessRuntimeException;
		}
		
		PropertyFeeDetail manageFeeDetail = null;
		if(currPayBillEntity==null){
			throw new BusinessRuntimeException("Plotproperty.confirmPayBill.PayBill.qryEmpty");
		}
//		boolean isMonth = currPayBillEntity.getPaytimeType().compareTo(PlotpropertyDict.PayBillType_PaytimeType.MONTH) == 0;//是否为月缴账单
//		boolean isProp = currPayBillEntity.getIsPropFee().compareTo(PlotpropertyDict.PayBillType_IsPropFee.YES)==0?true:false;//是否为物业费
		{
			for(PropertyFeeDetail tmpPropertyFeeDetail:currPayBillEntity.getPropertyFeeDetailList()){
				if(PlotpropertyDict.PropertyFeeDetail_Type.ManagerFee.compareTo(tmpPropertyFeeDetail.getType())==0){
					manageFeeDetail = tmpPropertyFeeDetail;
					break;
				}
			}
			if(manageFeeDetail==null){
				//syl-del-去掉校验逻辑
//				throw new BusinessRuntimeException("Plotproperty.confirmPayBill.manageFeeDetail.empty");
			}
		}
		EbuyOrder ebuyOrderUpdate = new EbuyOrder();//更新订单信息使用  订单存在的情况下
		PlotpropertyOrderEntity tmpOrderEntity = getPlotpropertyOrderByPayBillId(userId, payBillId);
		//若账单状态为已支付，则直接提示已支付
		if(currPayBillEntity.getIsPay()!=null&&PlotpropertyDict.PayBill_IsPay.TRUE.compareTo(currPayBillEntity.getIsPay())==0){
			throw new BusinessRuntimeException("Plotproperty.confirmPayBill.PayBill.isPay");
		}else{//若未未支付
			//查询账单是否已有对应的订单列表
			if(tmpOrderEntity!=null){
				if(EbuyDict.EbuyOrder_PayStatus.Pay_Success.compareTo(tmpOrderEntity.getPayStatus())==0){//成功
					throw new BusinessRuntimeException("Plotproperty.confirmPayBill.orderStatus.inconsistent");//遍历 是否有成功的 则提示数据不一致
				}else {//未支付成功
					//清除订单账单关系和订单
					// --修复Bug，因为回调慢的原因，导致用户点了多次确认付款（就会生成多个订单），改为非首次进来直接返回账单  added by wenfq 2017-01-10
//					ebuyOrderHasTPayBillBaseDao.deleteEbuyOrderHasTPayBillLogic(tmpOrderEntity.getEbuyOrderHasTPayBill().getId());
//					ebuyOrderBaseDao.deleteEbuyOrderLogic(tmpOrderEntity.getId());
					resOrderId = tmpOrderEntity.getId();// 返回账单Id
					ebuyOrderUpdate = tmpOrderEntity;
				}
			}
		}

		//获取账单--用户优惠金额信息
		PaybillUserPrefer paybillUserPrefer = paybillUserPreferService.getUserBillPrefer(payBillId, userId);
		Long gainMoney = paybillUserPrefer == null ? 0L : paybillUserPrefer.getPrefer();//节省的金额
		//为解决微信订单相同 金额不同的问题，先判断解放币是否相同，不同则生成新订单，同时删除原来已经有的订单
		if(ebuyOrderUpdate !=null && tmpOrderEntity != null &&(ebuyOrderUpdate.getTotalCouponAmount() - gainMoney) != JFBAmount) {
			resOrderId = null;
			ebuyOrderHasTPayBillBaseDao.deleteEbuyOrderHasTPayBillLogic(tmpOrderEntity.getEbuyOrderHasTPayBill().getId());
			ebuyOrderBaseDao.deleteEbuyOrderLogic(tmpOrderEntity.getId());
		}

		Long amount = 0L;//订单实付金额

		BigDecimal totalAmt = BigDecimal.valueOf(currPayBillEntity.getAmount());
		BigDecimal realAmt = null;
		BigDecimal amtBt = null;
		
		if(resOrderId==null){
			// 组装订单信息
			String nowTime = dualDao.getNowTime();
			resOrderId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order);
			Long totalDeliveryFee = 0L;
//			if(currPayBillEntity.getIsPropFee().compareTo(PlotpropertyDict.PayBillType_IsPropFee.YES)==0){//仅物业费才可以优惠-syl-add-2015-12-16 16:50:09
//				if(isMonth){
//					if(paybillUserPrefer!=null){//计算优惠的金额
//						gainMoney = paybillUserPrefer.getPrefer();
//					}
//				}
//			}

			if(gainMoney == null) {
				gainMoney = 0L;
			}

			long needMoney;
			if(currPayBillEntity.getFinanceStatus()!=null && currPayBillEntity.getFinanceStatus() == 1) {
				long preferMoney = currPayBillEntity.getAmount() - (currPayBillEntity.getDeduPrice() == null ? 0 : currPayBillEntity.getDeduPrice());
				if(preferMoney == 0) {
					gainMoney = 0L;
				} else {
					needMoney = preferMoney;
					amount = needMoney - gainMoney;
					if(amount <= 0) {
						amount = 0L;
						gainMoney = needMoney;
					}
				}
			} else {
				amount = currPayBillEntity.getAmount()-gainMoney+totalDeliveryFee;//实际待支付金额=原始金额-优惠的金额+运费
			}

			{
				EbuyOrder ebuyOrder = new EbuyOrder();
				ebuyOrder.setType(EbuyDict.EbuyOrder_Type.Property_Bill);//订单类型：物业账单
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
				ebuyOrder.setTotalDeliveryFee(totalDeliveryFee);//总配送费
				ebuyOrder.setAmount(amount);
				ebuyOrder.setTotalCouponAmount(gainMoney);//add-优惠金额
				//syl-add2015-5-13 18:21:34 渠道
				ebuyOrder.setSubChannel(subChannelId==null?null:subChannelId+"");
				//syl-add-2015-12-16 17:27:02增加基础费用账单类型Id
				ebuyOrder.setBillTypeId(currPayBillEntity.getBillTypeId());
				ebuyOrder.setIsPropFee(currPayBillEntity.getIsPropFee());
				//syl-add-2016-9-28 11:07:02增加设备id（限制设备缴费限制）
				ebuyOrder.setDeviceId(deviceId);

				//往redis放入订单号，如果有连续请求的则返回已经存在的订单id；过期时间60秒  key=用户id+账单id
				if(RedisCacheHandler.get(userId+ "_" +currPayBillEntity.getId().toString()) != null) {
					String[] strings = RedisCacheHandler.get(userId + "_" + currPayBillEntity.getId().toString()).split("_");
					String amt = strings[0];
					String orId = strings[1];
					if(amount.toString().equals(amt)) {//账单相同，金额相同  用户相同 则直接返回 订单id
						new ConfirmPayBillRusultEntity(BigInteger.valueOf(Long.valueOf(orId)), isAutoPaySucc);//返回订单Id
					}
				}
				RedisCacheHandler.set(userId + "_" + currPayBillEntity.getId().toString(), amount.toString() + "_" + resOrderId, 60);

				//更新订单的粮票支付信息
				if(JFBAmount > 0) {
					commonRedenvelopeService.updateOrderEntityByJFB(ebuyOrder, JFBAmount, gainMoney);
					amount = ebuyOrder.getAmount();//重新更新amout
				}

				// 新增订单
				int res = ebuyOrderBaseDao.insertEbuyOrder(ebuyOrder);
				if(res<=0){
					throw new BusinessRuntimeException("Plotproperty.confirmPayBill.insertEbuyOrder.error");
				}
				
				{
					realAmt = BigDecimal.valueOf(ebuyOrder.getAmount());
					amtBt = BigDecimal.valueOf(ebuyOrder.getTotalCouponAmount());
				}

				//更新优惠表信息
				if(JFBAmount > 0) {//必须先保存订单 才能金额优惠保存  外键约束
					commonRedenvelopeService.updatePayCouponByJFB(ebuyOrder, JFBAmount);
				}
			}
			{//创建订单关系
				Double discount = null;
				BigInteger prizeRecordId = null;
				BigInteger prizeUserRoomId = null;
				BigInteger ebuyOrderHasTPayBillAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_has_t_pay_bill);
				EbuyOrderHasTPayBill ebuyOrderHasTPayBillAdd = new EbuyOrderHasTPayBill();
				ebuyOrderHasTPayBillAdd.setDiscount(discount);
				ebuyOrderHasTPayBillAdd.setId(ebuyOrderHasTPayBillAddId);
				ebuyOrderHasTPayBillAdd.setPrizeRecordId(prizeRecordId);
				ebuyOrderHasTPayBillAdd.settEbuyOrderFId(resOrderId);
				ebuyOrderHasTPayBillAdd.settPayBillFId(currPayBillEntity.getId());
				ebuyOrderHasTPayBillAdd.setPrizeUserRoomId(prizeUserRoomId);
				Integer resCount = ebuyOrderHasTPayBillBaseDao.insertEbuyOrderHasTPayBill(ebuyOrderHasTPayBillAdd);
				if(resCount==null||resCount<=0){
					throw new BusinessRuntimeException("Plotproperty.confirmPayBill.insertOrderHasPayBill.error");
				}
			}
			/*重新修改订单粮票金额后可能会出现免单的情况*/
			//物业账单有免单的情况
			{//账单待支付金额为0则直接设定订单状态为成功 syl-add-2015-8-12 11:11:39
				if (!isFromTotalOrder && amount.compareTo(0L) == 0) {//剩余应付金额直接设置订单状态为支付成功
					commPayService.paySuccessOperate(resOrderId, EbuyDict.EbuyPay_PayMethod.PureDiscount);//纯优惠支付
					isAutoPaySucc = true;
				}
			}
		} else {
			realAmt = BigDecimal.valueOf(tmpOrderEntity.getAmount());
			amtBt = BigDecimal.valueOf(tmpOrderEntity.getTotalCouponAmount()==null ? 0L: tmpOrderEntity.getTotalCouponAmount());
		}

		PreOrderDto preOrder = new PreOrderDto();
		preOrder.setOrderId(resOrderId);
		preOrder.setFree(isAutoPaySucc);
		preOrder.setTotalAmt(totalAmt);
		preOrder.setRealAmt(realAmt);
		preOrder.setAmtBt(amtBt);
		return preOrder;

	}

	public void setPayBillBaseDao(IPayBillBaseDao payBillBaseDao) {
		this.payBillBaseDao = payBillBaseDao;
	}

	public void setPaybillUserPreferService(IPaybillUserPreferService paybillUserPreferService) {
		this.paybillUserPreferService = paybillUserPreferService;
	}

	@Override
	public PlotpropertyOrderEntity getPlotpropertyNoOrderDetail(Map<String, Object> paramMap) {
		return plotpropertyDao.getPlotpropertyNoOrderDetail(paramMap);
	}

	@Override
	public List<PropertyBillEntity> getBillList02(BigInteger userId, BigInteger realRoomId, GroupBuilding groupBuilding) {
		List<PropertyBillEntity> list = new ArrayList<PropertyBillEntity>();

		//判断物业费账单类型：第三方（极致），固定周期/月缴，选择周期
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userId", userId);
		if(propertySoftwareDockDao.getRealRoomSoftwareInfo(realRoomId) != null) {//不是第三方小区不进行数据查询
			PayBillEntity payBillEntity = get3rdPayBillEntity(realRoomId);
			//物业费类型  不管周期还是月缴  只要存在物业缴费类型就可以了
			if(payBillEntity != null) {
				Map<String, Object> billTypeMap = new HashMap<String, Object>();
				billTypeMap.put("gbId",groupBuilding.getId());
				billTypeMap.put("isPropFee",2);//物业费
				List<PayBillType> payBillTypes = payBillTypeBaseDao.selectPayBillTypeByCondition(billTypeMap, false);
				PropertyBillEntity propertyBillEntity = new PropertyBillEntity();
				if(payBillTypes != null && payBillTypes.size() > 0) {
					PayBillType payBillType = payBillTypes.get(0);
					propertyBillEntity.setBillTypeName(payBillType.getName());
					propertyBillEntity.setBillTypeImg(payBillType.getIcon());
				} else {
					propertyBillEntity.setBillTypeName("物业费");
					propertyBillEntity.setBillTypeImg("bill_1_1.jpg");
				}
				
				propertyBillEntity.setBillAmt(payBillEntity.getAmount());
				propertyBillEntity.setBillType("1");
				propertyBillEntity.setBillTitle("");// 极致不显示
				propertyBillEntity.setIsSucBill(1);
				propertyBillEntity.setIsPay(2);
				propertyBillEntity.setPropertyPeriodType(1);
				propertyBillEntity.setIsPreferential(groupBuilding.getIsPrefer() == null ? 0 : groupBuilding.getIsPrefer());
				propertyBillEntity.setDataFromType(payBillEntity.getDataFromType());
				String billTitle = payBillEntity.getBillMonthStart();
				if(org.apache.commons.lang.StringUtils.isNotBlank(billTitle)){
					Date dateStart = DateUtils.convertStrToDate(billTitle);
					String dateStartStr = DateUtils.convertDateToStr(dateStart, "yy.MM");
					billTitle = dateStartStr+"-"+dateStartStr + "(1个月)";
					propertyBillEntity.setBillTitle(billTitle);
				}
				list.add(propertyBillEntity);
			}
		} else {
			/*if(groupBuilding.getPropertyPeriodType() != null && groupBuilding.getPropertyPeriodType().equals(2)) {//选择周期缴费
				//查询列表时 需要去除固定周期缴费内容
				paraMap.put("propertyPeriodType", 2);//选择周期
				list = plotpropertyDao.getInCycleBillByUserId(paraMap);
				paraMap.put("realRoomId", realRoom.getId());
				List<AlterPayBillEntity> alterList = plotpropertyDao.getAlterPayBillEntity(paraMap);
                Map<String, AlterMonth2Bills> map = setMonthBills(alterList);
                //增加一个选择周期缴费信息
                PropertyBillEntity propertyBillEntity = new PropertyBillEntity();
                propertyBillEntity.setBillAmt(0l);
                propertyBillEntity.setBillType("1");
                propertyBillEntity.setIsSucBill(1);
                propertyBillEntity.setBillTypeName(payBillType.getName());
                propertyBillEntity.setIsPay(2);
                propertyBillEntity.setPropertyPeriodType(2);
                propertyBillEntity.setBillTypeImg(payBillType.getIcon());
                propertyBillEntity.setIsPreferential(groupBuilding.getIsPrefer() == null ? 0 : groupBuilding.getIsPrefer());

                String dateStartStr = alterPeriodDataDetail.getBillMonthStart();
                Date dateStart = DateUtils.convertStrToDate(dateStartStr);
                Date dateEnd = DateUtils.addMonths(dateStart, Integer.valueOf(groupBuilding)-1);
                String billTitle = DateUtils.convertDateToStr(dateStart, "yy.MM")+"-"+DateUtils.convertDateToStr(dateEnd, "yy.MM") + "("+ groupBuilding +"个月)";
                propertyBillEntity.setBillTitle(billTitle);

				//物业费类型
				Map<String, Object> billTypeMap = new HashMap<String, Object>();
				billTypeMap.put("gbId",groupBuilding.getId());
				billTypeMap.put("isPropFee",1);//物业费--选择周期缴费和原来一样 都当做物业费类型
				billTypeMap.put("paytimeType",2);//周期缴费
				List<PayBillType> payBillTypes = payBillTypeBaseDao.selectPayBillTypeByCondition(billTypeMap, false);
				if(!DataUtil.isEmpty(payBillTypes) && groupBuilding !=null && !"".equals(groupBuilding)) {//后台配置了选择周期 账单名称后才能进行查询
					PayBillType payBillType = payBillTypes.get(0);
					//判断欠费是否过多
					BigInteger realRoomId = realRoom.getId();
					paraMap.clear();
					paraMap.put("tRealRoomId",realRoomId);
					List<AlterPeriodDataDetail> alterPeriodDataDetailList = alterPeriodDataDetailBaseService.getAlterPeriodDataDetailByCondition(paraMap);
					if(alterPeriodDataDetailList != null && alterPeriodDataDetailList.size() > 0) {
						AlterPeriodDataDetail alterPeriodDataDetail = alterPeriodDataDetailList.get(0);
						//增加一个选择周期缴费信息
						PropertyBillEntity propertyBillEntity = new PropertyBillEntity();
						propertyBillEntity.setBillAmt(0l);
						propertyBillEntity.setBillType("1");
						propertyBillEntity.setIsSucBill(1);
						propertyBillEntity.setBillTypeName(payBillType.getName());
						propertyBillEntity.setIsPay(2);
						propertyBillEntity.setPropertyPeriodType(2);
						propertyBillEntity.setBillTypeImg(payBillType.getIcon());
						propertyBillEntity.setIsPreferential(groupBuilding.getIsPrefer() == null ? 0 : groupBuilding.getIsPrefer());
						
						String dateStartStr = alterPeriodDataDetail.getBillMonthStart();
						Date dateStart = DateUtils.convertStrToDate(dateStartStr);
						Date dateEnd = DateUtils.addMonths(dateStart, Integer.valueOf(groupBuilding)-1);
						String billTitle = DateUtils.convertDateToStr(dateStart, "yy.MM")+"-"+DateUtils.convertDateToStr(dateEnd, "yy.MM") + "("+ groupBuilding +"个月)";
						propertyBillEntity.setBillTitle(billTitle);
						
						PropertyAlterBillInfo propertyAlterBillInfo = alterPeriodDao.getAlterPeriodDetail(realRoomId);
						BigDecimal amt = BigDecimal.ZERO;
						for (int i = 0; i< propertyAlterBillInfo.getAlterFeeItems().size(); i++) {
							amt = amt.add(propertyAlterBillInfo.getAlterFeeItems().get(i).getTotalPrice());
						}
						
						//系统时间 - 滞纳金起始时间 的月份数
						int months = 0;
						String latefeeStart = alterPeriodDataDetail.getLatefeeStart();
						if(alterPeriodDataDetail.getLatefeeStart() != null && !"0000-00-00 00:00:00".equals(latefeeStart)) {
							months = DateUtils.getDiffMonths(DateUtils.convertStrToDate(latefeeStart), new Date());
							//(2016-10-26 - 2016-08-20 = 2 )其实业务逻辑应该是3
							if((DateUtils.addMonths(DateUtils.convertStrToDate(latefeeStart), months).getTime() - new Date().getTime()) < 0) {
								months = months + 1;
							}
						}
						//有滞纳金才对月份进行限制
						String[] periodMonthTmps = groupBuilding.getPeriodMonths().split(",");
						int index = 0;
						if(propertyAlterBillInfo.getLatefeeAmount() != null && propertyAlterBillInfo.getLatefeeAmount().doubleValue() > 0) {
							int month = 0;
							if(propertyAlterBillInfo.getLatefeeStart() != null) {
								month = DateUtils.getDiffMonths(DateUtils.convertStrToDate(propertyAlterBillInfo.getLatefeeStart()), new Date());
								if((DateUtils.addMonths(DateUtils.convertStrToDate(propertyAlterBillInfo.getLatefeeStart()), month).getTime() - new Date().getTime()) < 0) {
									month = month + 1;
								}
							}
							for (int j = 0; j< periodMonthTmps.length; j++) {
								if(Integer.valueOf(periodMonthTmps[j]) >= month) {
									index = j;
									break;
								}
							}
						}
						BigDecimal billAmt = amt.multiply(BigDecimal.valueOf(100*Integer.valueOf(periodMonthTmps[index])));
						propertyBillEntity.setBillAmt(billAmt.longValue());
						
						boolean isLateFeeAmtExist = (alterPeriodDataDetail.getLatefeeAmount() != null && alterPeriodDataDetail.getLatefeeAmount() > 0);
						if(isLateFeeAmtExist){
							Long lateFeeAmt = alterPeriodDataDetail.getLatefeeAmount() == null ? 0L : alterPeriodDataDetail.getLatefeeAmount();
							propertyBillEntity.setLateFeeAmt(lateFeeAmt);
						}
						
						String[] monthsArr = groupBuilding.getPeriodMonths().split(",");
						if(monthsArr.length > 0) {
							//如果不存在小区中的月份数，则表示欠费过多
							if(isLateFeeAmtExist) {//滞纳金大于零才进行
								if(months == 0) {//没有滞纳可以缴费
									propertyBillEntity.setPeriodPayServiceStatus(1);
								} else {
									for (int i = 0; i < monthsArr.length; i++) {
										//月数存在 且 月数 + 滞纳金时间 >= 系统时间
										if(Integer.valueOf(monthsArr[i]) >= months) {//存在月份
											propertyBillEntity.setPeriodPayServiceStatus(1);
											break;
										} else {
											propertyBillEntity.setPeriodPayServiceStatus(2);//欠费过多
										}
									}
								}
							} else {
								propertyBillEntity.setPeriodPayServiceStatus(1);
							}
							list.add(propertyBillEntity);
						}
					}
				}
			} else {//非选择性周期缴费
				paraMap.put("propertyPeriodType", null);
				list = plotpropertyDao.getInCycleBillByUserId(paraMap);
			}*/
            paraMap.put("propertyPeriodType", null);
            list = plotpropertyDao.getInCycleBillByUserId(paraMap);
		}
		return list;
	}

	@Override
	public Boolean isHasPayBillRecordByUserIdAndRealRoomId(BigInteger userId, BigInteger realRoomId) {
		return plotpropertyDao.isHasPayBillRecordByUserIdAndRealRoomId(userId, realRoomId) > 0;
	}

	@Override
	public Boolean isConfirmPass(BigInteger userId, BigInteger roomId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tUserFId", userId);
		paramMap.put("tRoomFId", roomId);
		List<UserHasTRoom> list = userHasTRoomBaseDao.selectUserHasTRoomByCondition(paramMap, false);
		if(list != null && list.size() > 0) {
			UserHasTRoom userHasTRoom = list.get(0);
			return userHasTRoom.getIsConfirmProprietor() != null && userHasTRoom.getIsConfirmProprietor() == 1;
		} else {
			return false;
		}
	}

	public void setUserHasTRoomBaseDao(IUserHasTRoomBaseDao userHasTRoomBaseDao) {
		this.userHasTRoomBaseDao = userHasTRoomBaseDao;
	}

	@Override
	public Double getTotalAmtNow(BigInteger userId) {
		Long totalAmt = plotpropertyDao.getTotalAmtNow(userId);
		Long unPaidAmt = plotpropertyDao.getTotalUnPaidAmtNow(userId);
		BigInteger realRoomId = commonRoomService.getDefaultRoomIdByUserId(userId);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userId", userId);
		paraMap.put("realRoomId", realRoomId);

		Long alterAmt = 0L;
		List<AlterCyclesEntity> alterCyclesEntities = getAlterPayBillEntity(paraMap);
		for (AlterCyclesEntity alterCyclesEntity : alterCyclesEntities) {
			List<AlterPayBillEntity> alterPayBillEntities = new ArrayList<AlterPayBillEntity>();
			if(DataUtil.isEmpty(alterCyclesEntity.getAlterCyclePayBillEntities())) continue;
			for (AlterCyclePayBillEntity alterCyclePayBillEntity : alterCyclesEntity.getAlterCyclePayBillEntities()) {
				for (AlterPayBillEntity alterPayBillEntity : alterCyclePayBillEntity.getAlterPayBillEntities()) {
					alterPayBillEntities.add(alterPayBillEntity);
				}
			}
			List<AlterMonth2Bills> list = setMonthBills(alterPayBillEntities);
			if(!DataUtil.isEmpty(list) && list.get(0) != null) {
				alterAmt += Long.parseLong(list.get(0).getAmt())*100;
			}
		}


		if(totalAmt == null) {
			return 0.00;
		} else {
			if(alterAmt != 0) {
				totalAmt = totalAmt + alterAmt;
			}
			if(unPaidAmt != null) {
				totalAmt += unPaidAmt;
			}
			return BigDecimalUtil.div100(totalAmt).doubleValue();
		}
	}

	/**
	 * 查询选择周期的可缴费月数--对应的账单信息
	 * @param alterList
	 * @return
	 */
	private List<AlterMonth2Bills> setMonthBills(List<AlterPayBillEntity> alterList) {
		Long size = 0L;
		Long unpaidSize = 0L;
		List<Long> sizeList = new ArrayList<Long>();
		List<Integer> subSizeList = new ArrayList<Integer>();
		int index = 0;
		Map<BigInteger, Long> preferMap = new HashMap<BigInteger, Long>();

		List<PayBill> payBillList = new ArrayList<PayBill>();
		List<PayBill> unpayBillList = new ArrayList<PayBill>();
		List<PayBill> nounpayBillList = new ArrayList<PayBill>();
		for (AlterPayBillEntity alterPayBillEntity : alterList) {
			size += alterPayBillEntity.getBillMonthSize();
			if(!DataUtil.isEmpty(alterPayBillEntity.getUnpaidList())) {
				for (PayBill payBill : alterPayBillEntity.getUnpaidList()) {
					unpayBillList.add(payBill);
					unpaidSize += payBill.getBillMonthSize();
					index ++;
				}
			}
			nounpayBillList.add(alterPayBillEntity);
			index ++;
			sizeList.add(size + unpaidSize);
			subSizeList.add(index);
			preferMap.put(alterPayBillEntity.getId(), alterPayBillEntity.getPreferAmt());
		}
		payBillList.addAll(unpayBillList);
		payBillList.addAll(nounpayBillList);

		List<AlterMonth2Bills> list = new ArrayList<AlterMonth2Bills>();
		for (int i = 0; i < subSizeList.size(); i++) {
			List<PayBill> payBillList1 = payBillList.subList(0, subSizeList.get(i));
			AlterMonth2Bills alterMonth2Bills = new AlterMonth2Bills();
			Long amt = 0L;
			String ids = "";
			Long preferAmt = 0L;
			for (PayBill payBill : payBillList1) {
				ids += payBill.getId() +",";
				amt += payBill.getAmount();
				preferAmt += preferMap.get(payBill.getId()) == null ? 0L : preferMap.get(payBill.getId());
			}
			alterMonth2Bills.setIds(ids);
			alterMonth2Bills.setAmt(PriceUtil.div100sHalfUp(amt));
			alterMonth2Bills.setPreferAmt(PriceUtil.div100sHalfUp(preferAmt));
			alterMonth2Bills.setMonth(sizeList.get(i).toString());
			list.add(alterMonth2Bills);
		}

		return list;
	}

	@Override
	public Boolean checkProprietorName(BigInteger realRoomId, String name) {
		//查询当前门牌下登记的业主姓名
		String proprietorName = plotpropertyDao.getRealRoomProprietorName(realRoomId);
		if(proprietorName == null || "".equals(proprietorName)) {//如果门牌下业主姓名为空，则返回验证成功
			return true;
		} else {
			if(name != null && !name.equals("")) {
				return proprietorName.contains(name);
			} else {
				return false;
			}
		}

	}

	@Override
	public int updateConfirmStatus(BigInteger userId, BigInteger roomId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tUserFId", userId);
		paramMap.put("tRoomFId", roomId);
		List<UserHasTRoom> list = userHasTRoomBaseDao.selectUserHasTRoomByCondition(paramMap, false);
		if(list != null && list.size() > 0) {
			UserHasTRoom userHasTRoom = list.get(0);
			userHasTRoom.setIsConfirmProprietor(1);
			return userHasTRoomBaseDao.updateUserHasTRoom(userHasTRoom);
		}
		return 0;
	}

	/**
	 * 判断缴费次数是否超过限制
	 * @param deviceId
	 * @param userId
	 * @return
	 */
	@Override
	public boolean isCanPayBill(String deviceId, BigInteger userId) {
		String maxPayTimes = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.MAXPAYTIMES);
		int maxUserTimes= Integer.valueOf(maxPayTimes.split(",")[0]);
		int maxDeviceTimes = Integer.valueOf(maxPayTimes.split(",")[1]);

		Map<String,Object> deviceMap = new HashMap<String, Object>();
		deviceMap.put("deviceId",deviceId);
		List<DevicePayCount> deviceList = devicePayCountBaseService.getDevicePayCountByCondition(deviceMap);
		int deviceNum = 0;
		if (deviceList != null && deviceList.size() > 0) {
			deviceNum = deviceList.get(0).getNum();
		}

		Map<String,Object> userMap = new HashMap<String, Object>();
		userMap.put("tUserId",userId);
		List<UserPayCount> userList = userPayCountBaseService.getUserPayCountByCondition(userMap);
		int userNum = 0;
		if (userList != null && userList.size() > 0) {
			userNum = userList.get(0).getNum();
		}

		return deviceNum < maxDeviceTimes && userNum < maxUserTimes;
	}

	@Override
	public PropertyAlterBillInfo getAlterPeriodDetail(BigInteger realRoomId) {
		return alterPeriodDao.getAlterPeriodDetail(realRoomId);
	}

	@Override
	public Map<String, Object> convertAlterPeriodDetail(PropertyAlterBillInfo propertyAlterBillInfo) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("realRoomId",propertyAlterBillInfo.getRealRoomId());
		resMap.put("billMonthStart",propertyAlterBillInfo.getBillMonthStart());
		resMap.put("propertyPeriodType",propertyAlterBillInfo.getPropertyPeriodType());
		resMap.put("isPrefer",propertyAlterBillInfo.getIsPrefer());
		resMap.put("alterFeeItems",propertyAlterBillInfo.getAlterFeeItems());
		resMap.put("address",propertyAlterBillInfo.getAddress());
		resMap.put("proprietorName",StringUtils.replaceNameToStar(propertyAlterBillInfo.getProprietorName()));
		resMap.put("JFBPercent",payConfigService.getPayConfigHbPercent(EbuyDict.EbuyOrder_Type.Property_Bill));//物业缴费类型为2
		resMap.put("dataFromType",0);//数据来源类型 0系统  1第三方

		if(propertyAlterBillInfo.getLatefeeAmount() != null) {
			resMap.put("latefeeAmount",BigDecimalUtil.div100(propertyAlterBillInfo.getLatefeeAmount()));
		} else {
			resMap.put("latefeeAmount",0);
		}

		if(!StringUtils.isEmpty(propertyAlterBillInfo.getPeriodMonths())) {
			List<Map<String, Object>> payPeriodList = new ArrayList<Map<String, Object>>();
			String[] months = propertyAlterBillInfo.getPeriodMonths().split(",");
			double amt = 0.00;
			for (int i = 0; i< propertyAlterBillInfo.getAlterFeeItems().size(); i++) {
				amt += propertyAlterBillInfo.getAlterFeeItems().get(i).getTotalPrice().doubleValue();
			}
			//有滞纳金才对月份进行限制
			if(propertyAlterBillInfo.getLatefeeAmount() != null && propertyAlterBillInfo.getLatefeeAmount().doubleValue() > 0) {
				int month = 0;
				if(propertyAlterBillInfo.getLatefeeStart() != null) {
					month = DateUtils.getDiffMonths(DateUtils.convertStrToDate(propertyAlterBillInfo.getLatefeeStart()), new Date());
					//(2016-10-26 - 2016-08-20 = 2 )其实业务逻辑应该是3
					if((DateUtils.addMonths(DateUtils.convertStrToDate(propertyAlterBillInfo.getLatefeeStart()), month).getTime() - new Date().getTime()) < 0) {
						month = month + 1;
					}
				}
				for (int j = 0; j< months.length; j++) {
					if(Integer.valueOf(months[j]) >= month) {
						Map<String, Object> mapMonths = new HashMap<String, Object>();
						mapMonths.put("month",months[j]);
						mapMonths.put("totalPrice", amt*Integer.valueOf(months[j]));
						payPeriodList.add(mapMonths);
					}
				}
			} else {
				for (int j = 0; j< months.length; j++) {
					Map<String, Object> mapMonths = new HashMap<String, Object>();
					mapMonths.put("month",months[j]);
					mapMonths.put("totalPrice", amt*Integer.valueOf(months[j]));
					payPeriodList.add(mapMonths);
				}
			}

			resMap.put("payPeriodList",payPeriodList);
		}
		return resMap;
	}

	@Override
	public Map<String, Object> getAlterPeriodPreferential(BigInteger userId, BigInteger realRoomId, Integer month) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(checkAlterPeriodMonth(userId,month)) {
			Long totalAmtByOneMonth = alterPeriodDao.getBasicSumAmt(realRoomId);
			if(totalAmtByOneMonth==null){totalAmtByOneMonth = 0L;}
			Long totalAmt =  month*totalAmtByOneMonth;
			Long preferAmt = alterPeriodDao.getPreferentialRandom(userId, realRoomId,totalAmt,month);
			if(preferAmt != null) {
				resMap.put("preferentialAmt", BigDecimalUtil.div100(preferAmt));
			} else {
				resMap.put("preferentialAmt", 0.00);
			}
		} else {
			resMap.put("preferentialAmt", 0.00);
		}

		return resMap;
	}

	/**
	 * 选择周期缴费确认账单
	 * @param userId
	 * @param subChannelId
	 * @param deviceId
	 * @param month
	 * @param JFBAmount
     * @return
     */
	private PreOrderDto confirmAlterPayBill(BigInteger userId, Long subChannelId, String deviceId, Integer month, long JFBAmount, boolean isFromTotalOrder) {
		{//校验 选择周期缴费月份是否存在
			if(!checkAlterPeriodMonth(userId,month)) {
				throw new BusinessRuntimeException("缴费月份有误,请联系客服！");
			}
		}

		{//校验 用户对应的门牌是否已经验证过
			checkPayBillRoomValidate(userId);
		}

		BigInteger resOrderId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order);
		Boolean isAutoPaySucc = false;
		String nowTime = dualDao.getNowTime();
		RealRoom realRoom = commonRoomService.selectRealRoomByUserId(userId);
		BigInteger realRoomId = realRoom.getId();
		BigInteger roomId = commonRoomService.getDefaultRoomIdByUserId(userId);

		//选择周期基础数据
		Map<String, Object> periodDetailMap = new HashMap<String, Object>();
		periodDetailMap.put("tRealRoomId",realRoomId);
		AlterPeriodDataDetail alterPeriodDataDetail  = alterPeriodDataDetailBaseService.getAlterPeriodDataDetailByCondition(periodDetailMap).get(0);
		Long lateFeeAmt = alterPeriodDataDetail.getLatefeeAmount() == null ? 0l : alterPeriodDataDetail.getLatefeeAmount();

		//小区信息
		GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(userId);

		Long amount = alterPeriodDao.getBasicSumAmt(realRoomId);//每月缴费基数总和
		Long gainMoney = 0l;
		if(groupBuilding.getIsPrefer() != null && groupBuilding.getIsPrefer().equals(1)) {//有随机立减
			gainMoney = alterPeriodDao.getPreferentialRandom(userId, realRoomId,amount*month,month);//随机立减金额
		}
		//用户实际缴费 = 每月缴费基数总和 * 缴费月数 + 滞纳金 - 随机立减金额
		Long needPayAmount = (amount*month + lateFeeAmt - gainMoney) < 0 ? 0 : (amount*month + lateFeeAmt - gainMoney);//随机立减最高为账单金额
		//查询当前账单基本信息
		EbuyOrder ebuyOrder = new EbuyOrder();
		{
			ebuyOrder.setId(resOrderId);
			ebuyOrder.setType(EbuyDict.EbuyOrder_Type.Property_Bill);//订单类型：物业账单
			ebuyOrder.setBuyerId(userId);
			ebuyOrder.setCurrRoomId(userBaseDao.selectUserBySeqId(userId).getDefaultRoomId());
			ebuyOrder.setBuyTime(nowTime);
			ebuyOrder.setCreater(userId);
			ebuyOrder.setOrderNo(OrderNoGenerator.getOrderNo(ebuyOrder.getId()));
			ebuyOrder.setPayMethod(null);
			ebuyOrder.setPayTime(null);
			ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.DaiFuKuan);
			ebuyOrder.setPayStatus(EbuyDict.EbuyOrder_PayStatus.Not_Pay);//支付状态为待支付
			ebuyOrder.setDelivStatus(EbuyDict.EbuyOrder_DelivStatus.Not_Deliv);//发货状态为未发货
			ebuyOrder.setTotalDeliveryFee(0l);//总配送费l
			ebuyOrder.setAmount(needPayAmount);
			ebuyOrder.setTotalCouponAmount(gainMoney);//add-优惠金额
			//syl-add2015-5-13 18:21:34 渠道
			ebuyOrder.setSubChannel(subChannelId==null?null:subChannelId+"");
			ebuyOrder.setIsPropFee(1);
			//syl-add-2016-9-28 11:07:02增加设备id（限制设备缴费限制）
			ebuyOrder.setDeviceId(deviceId);
			ebuyOrder.setCurrRoomId(roomId);

			//更新订单的粮票支付信息
			if(JFBAmount > 0) {
				commonRedenvelopeService.updateOrderEntityByJFB(ebuyOrder, JFBAmount, gainMoney);
				needPayAmount = ebuyOrder.getAmount();
			}

			// 新增订单
			int res = ebuyOrderBaseDao.insertEbuyOrder(ebuyOrder);
			if(res<=0){
				throw new BusinessRuntimeException("Plotproperty.confirmPayBill.insertEbuyOrder.error");
			}

			//更订单优惠信息
			if(JFBAmount > 0) {//外键约束问题
				commonRedenvelopeService.updatePayCouponByJFB(ebuyOrder, JFBAmount);
			}
		}

		//增加订单账单关系
		PayBill payBill = createPayBill(ebuyOrder, month, realRoomId, 2, groupBuilding, alterPeriodDataDetail.getBillMonthStart(), null);
		//增加账单和收费项关系
		createPropertyFeeDetail(payBill, lateFeeAmt);
		{//创建订单关系
			Double discount = null;
			BigInteger prizeRecordId = null;
			BigInteger prizeUserRoomId = ebuyOrder.getCurrRoomId();
			BigInteger ebuyOrderHasTPayBillAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_has_t_pay_bill);
			EbuyOrderHasTPayBill ebuyOrderHasTPayBillAdd = new EbuyOrderHasTPayBill();
			ebuyOrderHasTPayBillAdd.setDiscount(discount);
			ebuyOrderHasTPayBillAdd.setId(ebuyOrderHasTPayBillAddId);
			ebuyOrderHasTPayBillAdd.setPrizeRecordId(prizeRecordId);
			ebuyOrderHasTPayBillAdd.settEbuyOrderFId(ebuyOrder.getId());
			ebuyOrderHasTPayBillAdd.setPrizeUserRoomId(prizeUserRoomId);
			ebuyOrderHasTPayBillAdd.settPayBillFId(payBill.getId());
			Integer resCount = ebuyOrderHasTPayBillBaseDao.insertEbuyOrderHasTPayBill(ebuyOrderHasTPayBillAdd);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("Plotproperty.confirmPayBill.insertOrderHasPayBill.error");
			}
		}

		//更新随机立减的paybillId
		updatePeriodPayBillPrefer(payBill, userId);

		//物业账单有免单的情况
		{//账单待支付金额为0则直接设定订单状态为成功 syl-add-2015-8-12 11:11:39
			if (!isFromTotalOrder && needPayAmount.compareTo(0L) == 0) {//剩余应付金额直接设置订单状态为支付成功
				commPayService.paySuccessOperate(resOrderId, EbuyDict.EbuyPay_PayMethod.PureDiscount);//纯优惠支付
				isAutoPaySucc = true;
			}
		}
		
		PreOrderDto preOrder = new PreOrderDto();
		preOrder.setOrderId(resOrderId);
		preOrder.setFree(isAutoPaySucc);
		preOrder.setRealAmt(BigDecimal.valueOf(ebuyOrder.getAmount()));
		preOrder.setTotalAmt(BigDecimal.valueOf(payBill.getAmount()));
		preOrder.setAmtBt(BigDecimal.valueOf(ebuyOrder.getTotalCouponAmount()==null ? 0L : ebuyOrder.getTotalCouponAmount()));
		
		return preOrder;
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
	private PayBill createPayBill(EbuyOrder ebuyOrder, Integer month, BigInteger realRoomId, Integer cycleType, GroupBuilding groupBuilding, String billMonthStart, BigInteger realroomSoftwareFeeFId) {
		//物业费类型
		Map<String, Object> billTypeMap = new HashMap<String, Object>();
		billTypeMap.put("gbId",groupBuilding.getId());
//		billTypeMap.put("isPropFee",1);//物业费
//		billTypeMap.put("paytimeType",2);//周期缴费
		PayBillType payBillType = null;
		List<PayBillType> paybillTypeList = payBillTypeBaseDao.selectPayBillTypeByCondition(billTypeMap,false);
		if(paybillTypeList != null && paybillTypeList.size() > 0) {
			payBillType = payBillTypeBaseDao.selectPayBillTypeByCondition(billTypeMap,false).get(0);
		}

		//真实门牌信息
		RealRoom realRoom = commonRoomService.selectRealRoomByUserId(ebuyOrder.getBuyerId());

		PayBill payBill = new PayBill();
		payBill.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_bill));
		payBill.setAmount(ebuyOrder.getAmount() + ebuyOrder.getTotalCouponAmount());
		if(payBillType == null) {
			payBill.setBillTypeName("物业费");
			payBill.setBillTypeId(BigInteger.valueOf(1L));
		} else {
			payBill.setBillTypeName(payBillType.getName());
			payBill.setBillTypeId(payBillType.getId());
		}
		
		payBill.setBillTimeCfgId(BigInteger.valueOf(-1));//数据库设计不能为空，选择周期没有该id 所以默认-1为id
		payBill.setHbAmount(ebuyOrder.getTotalCouponAmount());
		payBill.setSys0AddTime(DateUtil.getCurrSysTimeStr());
		payBill.setIsPay(2);
		payBill.setIsPropFee(1);
		payBill.setPaytimeType(2);
		payBill.setBillMonthStart(billMonthStart);
		String billMonthEnd = DateUtils.convertDateToStr(DateUtils.increateMonth(DateUtils.convertStrToDate(billMonthStart),month - 1),"yyyy-MM-dd");
		payBill.setBillMonthEnd(billMonthEnd);
		payBill.setPayDayStart(billMonthStart);
		payBill.setPayDayEnd(billMonthEnd);
		payBill.setBillMonthSize(Long.valueOf(month));
		payBill.setPayTime(ebuyOrder.getPayTime());
		payBill.setPropertyProprietorId(realRoom.gettPropertyProprietorFId().toString());
		payBill.setSuccPayAmount(ebuyOrder.getAmount());
		payBill.settRealRoomFId(realRoomId);
		payBill.setPaymentWay(1);//1 用户在线支付
		if(groupBuilding.getIsPrefer() != null && groupBuilding.getIsPrefer().equals(1)) {//有优惠
			payBill.setPreferType(1);//1随机立减
		}
		payBill.setSys0DelState(1);
		payBill.setCycleType(cycleType);//1固定周期，2选择周期
		payBill.settRealroomSoftwareFeeFId(realroomSoftwareFeeFId);
		//新增账单
		Integer resCount = payBillDao.insertPayBillAllCum(payBill);
		if (resCount == null || resCount <= 0) {
			throw new BusinessRuntimeException("账单数据新增失败！");
		}

		return payBill;
	}

	/**
	 * 生成 t_property_fee_detail
	 * @param payBill
	 */
	private void createPropertyFeeDetail(PayBill payBill, Long lateFeeAmt) {
		List<Map<String,Object>> itemFeeList = alterPeriodDao.getAlterPeriodItemsFee(payBill.gettRealRoomFId());
		if (itemFeeList == null || itemFeeList.size() <= 0) {
			throw new BusinessRuntimeException("创建订单失败，收费项为空！");
		}
		for (int i=0; i < itemFeeList.size() ; i++){
			PropertyFeeDetail propertyFeeDetail = new PropertyFeeDetail();
			propertyFeeDetail.settPayBillFId(payBill.getId());
			Map<String,Object> map = itemFeeList.get(i);
			String name = map.get("name").toString();
			Long amount = Long.parseLong(map.get("amount").toString());
			propertyFeeDetail.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_property_fee_detail));
			propertyFeeDetail.setName(name);
			propertyFeeDetail.setTotalPrice(amount.doubleValue());
			propertyFeeDetail.setAllowancePrice(0l);
			if(name.equals("管理费")) {//1
				propertyFeeDetail.setType(1);
			} else if(name.equals("主体金")) {//2
				propertyFeeDetail.setType(2);
			} else if(name.equals("垃圾处理费")) {//3
				propertyFeeDetail.setType(3);
			} else if(name.equals("水费")) {//4
				propertyFeeDetail.setType(4);
			} else if(name.equals("污水处理费")) {//5
				propertyFeeDetail.setType(5);
			} else {//9
				propertyFeeDetail.setType(9);
			}
			propertyFeeDetail.setSys0AddTime(DateUtils.getCurrentDateStr());
			propertyFeeDetail.setSys0DelState(0);
			//v501为账单打印使用
			propertyFeeDetail.setFeeType(4);
			propertyFeeDetailBaseDao.insertPropertyFeeDetail(propertyFeeDetail);
		}
		//选择周期需要增加滞纳金信息
		if(lateFeeAmt != null && lateFeeAmt > 0) {
			PropertyFeeDetail propertyFeeDetail = new PropertyFeeDetail();
			propertyFeeDetail.settPayBillFId(payBill.getId());
			propertyFeeDetail.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_property_fee_detail));
			propertyFeeDetail.setName("滞纳金");
			propertyFeeDetail.setTotalPrice(lateFeeAmt.doubleValue());
			propertyFeeDetail.setAllowancePrice(0l);
			propertyFeeDetail.setType(9);
			propertyFeeDetail.setSys0AddTime(DateUtils.getCurrentDateStr());
			propertyFeeDetail.setSys0DelState(0);
			//v501为账单打印使用
			propertyFeeDetail.setFeeType(4);
			propertyFeeDetailBaseDao.insertPropertyFeeDetail(propertyFeeDetail);
		}
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

	/**
	 * 回调完成 更新选择周期基础数据信息
	 * @param orderId
	 */
	@Override
	public int updateAlterPeriodDataDetailAfterPaySuccess(BigInteger orderId) {
		int i = 0;
		//查询需要更新的数据
		List<Map<String, Object>> list = plotpropertyDao.getNeedAlterUpdate(orderId);
		//更新数据
		if(!DataUtil.isEmpty(list)) {
			logger.info("==need to update size==="+list.size());
			List<FixedFeeItemHasRoom> fixedFeeItemHasRooms = new ArrayList<FixedFeeItemHasRoom>();
			for (Map<String, Object> stringObjectMap : list) {
				FixedFeeItemHasRoom fixedFeeItemHasRoom = new FixedFeeItemHasRoom();
				Object itemHasRoomId1 = stringObjectMap.get("itemHasRoomId");
				Object billMonthEnd = stringObjectMap.get("billMonthEnd");
				logger.info("==start==itemHasRoomId==="+itemHasRoomId1+"====billMonthEnd==="+billMonthEnd);
				if(DataUtil.isEmpty(itemHasRoomId1)) continue;
				BigInteger itemHasRoomId = BigInteger.valueOf(Long.parseLong(itemHasRoomId1.toString()));
				String billMonth = DateUtils.convertDateToStr(DateUtils.convertStrToDate(billMonthEnd.toString()), "yyyy-MM-dd");
				fixedFeeItemHasRoom.setId(itemHasRoomId);
				fixedFeeItemHasRoom.setBillMonthStart(billMonth);
				fixedFeeItemHasRoom.setSys0UpdTime(DateUtils.getCurrentDate());
				fixedFeeItemHasRooms.add(fixedFeeItemHasRoom);
				logger.info("==end==itemHasRoomId==="+itemHasRoomId1+"====billMonthEnd==="+billMonthEnd);
			}
			i = fixedFeeItemHasRoomBaseService.updateFixedFeeItemHasRoomBatch(fixedFeeItemHasRooms);
			logger.info("==updated success size==="+i);
		}
		return i;
	}

	/**
	 * 校验选择周期 月份是否存在
	 * @param userId
	 * @param month
	 * @return
	 */
	@Override
	public boolean checkAlterPeriodMonth(BigInteger userId ,Integer month) {
		boolean isCanGetPrefer = false;
		if(month != null) {
			GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(userId);
			String monthStr = groupBuilding.getPeriodMonths();
			if(!StringUtils.isEmpty(monthStr)) {
				String[] monthArr = monthStr.split(",");
				for (int i = 0; i < monthArr.length; i++) {
					if(month == Integer.valueOf(monthArr[i])) {
						isCanGetPrefer = true;
						break;
					}
				}
			}
		}
		return isCanGetPrefer;
	}

	/**
	 * 第三方物业  极致确认账单
	 * @param userId
	 * @param subChannelId
	 * @param deviceId
	 * @param hbAmountL
     * @return
     */
	private PreOrderDto confirmPayBillFor3rd(BigInteger userId, Long subChannelId, String deviceId, Long JFBAmount, boolean isFromTotalOrder) {
		{//校验 用户对应的门牌是否已经验证过
			checkPayBillRoomValidate(userId);
		}

		BigInteger resOrderId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order);
		Boolean isAutoPaySucc = false;
		String nowTime = dualDao.getNowTime();
		RealRoom realRoom = commonRoomService.selectRealRoomByUserId(userId);
		BigInteger realRoomId = realRoom.getId();

		if(propertySoftwareDockDao.getRealRoomSoftwareInfo(realRoom.getId()) == null) {
			throw new BusinessRuntimeException("该小区不是第三方合作小区！");
		}

		BigInteger roomId = commonRoomService.getDefaultRoomIdByUserId(userId);
		//小区信息
		GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(userId);

		//缴费基础数据
		PayBillEntity payBillEntity = get3rdPayBillEntity(realRoomId);
		if(payBillEntity == null) {//调用极致接口失败
			throw new BusinessRuntimeException("Plotproperty.confirmPayBill.get3rdPayBill.error");
		}

		Long amount = payBillEntity.getAmount();
		Long gainMoney = 0L;
		if(groupBuilding.getIsPrefer() != null && groupBuilding.getIsPrefer().equals(1)) {//有随机立减
			gainMoney = alterPeriodDao.getPreferentialRandom(userId, realRoomId,amount,1);//随机立减金额
		}
		//用户实际缴费 = 每月缴费基数总和 * 缴费月数 + 滞纳金 - 随机立减金额
		Long needPayAmount = amount - gainMoney;//随机立减最高为账单金额
		//查询当前账单基本信息
		EbuyOrder ebuyOrder = new EbuyOrder();
		{
			ebuyOrder.setId(resOrderId);
			ebuyOrder.setType(EbuyDict.EbuyOrder_Type.Property_Bill);//订单类型：物业账单
			ebuyOrder.setBuyerId(userId);
			ebuyOrder.setCurrRoomId(userBaseDao.selectUserBySeqId(userId).getDefaultRoomId());
			ebuyOrder.setBuyTime(nowTime);
			ebuyOrder.setCreater(userId);
			ebuyOrder.setOrderNo(OrderNoGenerator.getOrderNo(ebuyOrder.getId()));
			ebuyOrder.setPayMethod(null);
			ebuyOrder.setPayTime(null);
			ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.DaiFuKuan);
			ebuyOrder.setPayStatus(EbuyDict.EbuyOrder_PayStatus.Not_Pay);//支付状态为待支付
			ebuyOrder.setDelivStatus(EbuyDict.EbuyOrder_DelivStatus.Not_Deliv);//发货状态为未发货
			ebuyOrder.setTotalDeliveryFee(0L);//总配送费l
			ebuyOrder.setAmount(needPayAmount);
			ebuyOrder.setTotalCouponAmount(gainMoney);//add-优惠金额
			//syl-add2015-5-13 18:21:34 渠道
			ebuyOrder.setSubChannel(subChannelId==null?null:subChannelId+"");
			ebuyOrder.setIsPropFee(1);
			//syl-add-2016-9-28 11:07:02增加设备id（限制设备缴费限制）
			ebuyOrder.setDeviceId(deviceId);
			ebuyOrder.setCurrRoomId(roomId);

			//更新订单的粮票支付信息
			if(JFBAmount > 0) {
				commonRedenvelopeService.updateOrderEntityByJFB(ebuyOrder, JFBAmount, gainMoney);
				needPayAmount = ebuyOrder.getAmount();
			}

			// 新增订单
			int res = ebuyOrderBaseDao.insertEbuyOrder(ebuyOrder);
			if(res<=0){
				throw new BusinessRuntimeException("Plotproperty.confirmPayBill.insertEbuyOrder.error");
			}

			//更订单优惠信息
			if(JFBAmount > 0) {//外键约束问题
				commonRedenvelopeService.updatePayCouponByJFB(ebuyOrder, JFBAmount);
			}
		}

		//增加订单账单关系
		PayBill payBill = createPayBill(ebuyOrder, 1, realRoomId, 1, groupBuilding, payBillEntity.getBillMonthStart(),payBillEntity.gettRealroomSoftwareFeeFId());
		//增加账单和收费项关系
		createPropertyFeeDetail(payBill, payBillEntity.getPropertyFeeDetailList());
		{//创建订单关系
			BigInteger prizeUserRoomId = ebuyOrder.getCurrRoomId();
			BigInteger ebuyOrderHasTPayBillAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_has_t_pay_bill);
			EbuyOrderHasTPayBill ebuyOrderHasTPayBillAdd = new EbuyOrderHasTPayBill();
			ebuyOrderHasTPayBillAdd.setDiscount(null);
			ebuyOrderHasTPayBillAdd.setId(ebuyOrderHasTPayBillAddId);
			ebuyOrderHasTPayBillAdd.setPrizeRecordId(null);
			ebuyOrderHasTPayBillAdd.settEbuyOrderFId(ebuyOrder.getId());
			ebuyOrderHasTPayBillAdd.setPrizeUserRoomId(prizeUserRoomId);
			ebuyOrderHasTPayBillAdd.settPayBillFId(payBill.getId());//先默认为-1，支付成功后再更新为账单id
			Integer resCount = ebuyOrderHasTPayBillBaseDao.insertEbuyOrderHasTPayBill(ebuyOrderHasTPayBillAdd);
			if(resCount<=0){
				throw new BusinessRuntimeException("Plotproperty.confirmPayBill.insertOrderHasPayBill.error");
			}
		}

		//更新随机立减的paybillId
		updatePeriodPayBillPrefer(payBill, userId);

		//物业账单有免单的情况
		{//账单待支付金额为0则直接设定订单状态为成功 syl-add-2015-8-12 11:11:39
			if (!isFromTotalOrder && needPayAmount.compareTo(0L) == 0) {//剩余应付金额直接设置订单状态为支付成功
				commPayService.paySuccessOperate(resOrderId, EbuyDict.EbuyPay_PayMethod.PureDiscount);//纯优惠支付
				isAutoPaySucc = true;
			}
		}
		
		PreOrderDto preOrder = new PreOrderDto();
		preOrder.setOrderId(resOrderId);
		preOrder.setFree(isAutoPaySucc);
		preOrder.setRealAmt(BigDecimal.valueOf(ebuyOrder.getAmount()));
		preOrder.setTotalAmt(BigDecimal.valueOf(payBill.getAmount()));
		preOrder.setAmtBt(BigDecimal.valueOf(ebuyOrder.getTotalCouponAmount()==null ? 0L : ebuyOrder.getTotalCouponAmount()));
		
		return preOrder;
	}

	/**
	 * 更新随机立减（周期缴费--选择周期/第三方对接-极致）
	 * @param payBill
	 * @param userId
     */
	private void updatePeriodPayBillPrefer(PayBill payBill, BigInteger userId) {
		if(payBill.getPreferType() != null && payBill.getPreferType().equals(1)) {//有随机立减
			//更新随机立减信息
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tRealRoomId", payBill.gettRealRoomFId());
			paramMap.put("tUserFId", userId);
			paramMap.put("payMonth", payBill.getBillMonthSize().intValue());
			AlterPeriodPrefer alterPeriodPrefer = alterPeriodPreferBaseDao.selectAlterPeriodPreferByCondition(paramMap, false).get(0);
			alterPeriodPrefer.settPayBillId(payBill.getId());
			Integer resCount = alterPeriodPreferBaseDao.updateAlterPeriodPrefer(alterPeriodPrefer);
			if (resCount == null || resCount <= 0) {
				throw new BusinessRuntimeException("选择周期缴费随机立减账单更新失败！");
			}
		}
	}

	@Override
	public Long getLateFeeByRealRoomId(BigInteger realRoomId) {
		return alterPeriodDao.getLateFeeByRealRoomId(realRoomId);
	}

	@Override
	public PropertyDetail getPlotpropertyNotPayOrderDetailFrom3rd(BigInteger userId) {
		RealRoom realRoom = commonRoomService.selectRealRoomByUserId(userId);
		if(propertySoftwareDockDao.getRealRoomSoftwareInfo(realRoom.getId()) == null) {
			throw new BusinessRuntimeException("该小区不是第三方合作小区！");
		}
		GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(userId);
		PropertyDetail propertyDetail = new PropertyDetail();
		PayBillEntity payBillEntity = get3rdPayBillEntity(realRoom.getId());

		if(payBillEntity == null) {
			throw new BusinessRuntimeException("获取3rd账单数据失败！");
		}

		if(payBillEntity != null) {//调取数据成功
			List<PropertyFeeDetail> propertyFeeDetailList = payBillEntity.getPropertyFeeDetailList();
			long prefereAmt = 0;
			long totalAmt = payBillEntity.getAmount();
			if(groupBuilding.getIsPrefer() != null && groupBuilding.getIsPrefer().equals(1)) {//有随机立减
				prefereAmt = alterPeriodDao.getPreferentialRandom(userId, realRoom.getId(),totalAmt,1);//随机立减金额
			}
			String couponDesc = "";//已随机立减￥50.00
			if(prefereAmt>0){
				couponDesc = "已"+groupBuilding.getPreferName()+"￥"+ PriceUtil.div100s(prefereAmt);
			}
			propertyDetail.setCouponDesc(couponDesc);
			{
				propertyDetail.setTopName(payBillEntity.getBillTypeName());
				List<Map<String,Object>> feeDetail = new ArrayList<Map<String,Object>>();
				if (propertyFeeDetailList != null && propertyFeeDetailList.size() > 0) {
					for (PropertyFeeDetail tmpSrcDetail : propertyFeeDetailList) {
						Map<String, Object> tmpMap = new HashMap<String, Object>();
						tmpMap.put("name", tmpSrcDetail.getName());
						tmpMap.put("totalPrice", "￥" + PriceUtil.div100s(tmpSrcDetail.getTotalPrice()==null?0:tmpSrcDetail.getTotalPrice().longValue())
								+ ((tmpSrcDetail.getAllowancePrice() == null || tmpSrcDetail.getAllowancePrice() == 0) ? "" : "（物业宝已抵扣）"));
						feeDetail.add(tmpMap);
					}
				}
				propertyDetail.setFeeDetail(feeDetail);
			}

			if(payBillEntity.getIsPay()==1) {
				propertyDetail.setPayStatus(2);
			} else {
				propertyDetail.setPayStatus(1);
			}

			propertyDetail.setTotalAmt(PriceUtil.div100(payBillEntity.getAmount()));
			//账单中的实际支付金额只有在支付成后才会有值payBillEntity.getSuccPayAmount()=总金额-优惠金额（随机立减）
			BigDecimal sucPay = PriceUtil.div100(payBillEntity.getAmount()).subtract(PriceUtil.div100(prefereAmt));
			if(sucPay.doubleValue()<=0) {
				sucPay = BigDecimal.valueOf(0);
			}
			if(PriceUtil.div100(payBillEntity.getAmount()).compareTo(BigDecimal.valueOf(0)) == 0) {
				prefereAmt = 0L;
			} else {
				if((payBillEntity.getAmount() - prefereAmt) <= 0) {
					prefereAmt = payBillEntity.getAmount();
				}
			}
			propertyDetail.setSucPay(sucPay);
			propertyDetail.setPreferentialAmt(PriceUtil.div100(prefereAmt));//随机立减 订单优惠金额 = 随机立减 + 粮票金额
			propertyDetail.setJFBAmount(new BigDecimal(0));//默认没有解放币
			propertyDetail.setProprietorName(StringUtils.replaceNameToStar(payBillEntity.getRealRoomEntity().getPropertyProprietor().getName()));
			RoomBaseInfo roomBaseInfo = CommonRoomUtil.getRoomBaseInfo(payBillEntity.getRealRoomEntity());
			propertyDetail.setAddrBuilding(roomBaseInfo.getGroupBuilding());
			propertyDetail.setAddrRoom(CommonRoomUtil.getAddressDetail02(payBillEntity.getRealRoomEntity()));
			//管理处电话
			PropertyManagement propertyManagement = propertyManagementBaseService.getPropertyManagementBySeqId(groupBuilding.gettPropertyManagementFId());
			if(propertyManagement != null) {
				propertyDetail.setManagerTel(propertyManagement.getTel());
			}
			if(payBillEntity.getFinanceStatus() !=null && payBillEntity.getFinanceStatus() == 1) {
				propertyDetail.setIsBuyFinance(true);
			} else {
				propertyDetail.setIsBuyFinance(false);
			}
			propertyDetail.setJFBPercent(payConfigService.getPayConfigHbPercent(EbuyDict.EbuyOrder_Type.Property_Bill));
			propertyDetail.setDataFromType(1);//数据来源
		}

		return propertyDetail;
	}

	/**
	 * 获取第三方  物业信息实体
	 * @param gbId
	 * @param realRoomId
     * @return
     */
	@Override
	public PayBillEntity get3rdPayBillEntity(BigInteger realRoomId) {
		 RealRoomSoftwareInfo realRoomSoftwareInfo = propertySoftwareDockDao.getRealRoomSoftwareInfo(realRoomId);
		if(realRoomSoftwareInfo != null ) {
			if(!StringUtils.isEmpty(realRoomSoftwareInfo.getServiceClassName())) {
				IPropertySoftwareDockService propertySoftwareDockService = (IPropertySoftwareDockService)CnfantasiaCommbusiUtil.getBeanManager(realRoomSoftwareInfo.getServiceClassName());
				return propertySoftwareDockService.getPropertyFeeItems(realRoomId);
			}
		}
		return null;
	}
	
	@Override
	public PropertyDetail convertPropertyDetail2(PlotpropertyOrderEntity plotpropertyOrder) {
		PropertyDetail propertyDetail = new PropertyDetail();
		PayBillEntity payBillEntity = plotpropertyOrder.getPayBillEntity();

		List<PropertyFeeDetail> propertyFeeDetailList = payBillEntity.getPropertyFeeDetailList();
		GroupBuildingEntity groupBuilding = payBillEntity.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity();
		//IBusinessMonthYear businessMonthYear = BusinessMonthYearFactory.createByPayBill(payBillEntity, groupBuilding);
		long deDuPrice = payBillEntity.getDeduPrice()==null?0l:payBillEntity.getDeduPrice();
		long prefereAmt = plotpropertyOrder.getPreferentialAmt();
		{
			String couponDesc = "";//已随机立减￥50.00
			//Long couponAmount = payBillEntity.getAmount() - payBillEntity.getSuccPayAmount() - (payBillEntity.getDeduPrice() == null ? 0 : payBillEntity.getDeduPrice());
			if(prefereAmt>0){
				if(payBillEntity.getFinanceStatus() !=null && payBillEntity.getFinanceStatus() == 1) {
					if(PriceUtil.div100(payBillEntity.getAmount() - deDuPrice)==BigDecimal.valueOf(0)) {
						couponDesc = "已"+groupBuilding.getPreferName()+"￥"+ PriceUtil.div100s(0l);
					} else {
						long shengyuMoney = payBillEntity.getAmount() - deDuPrice;
						if((shengyuMoney - prefereAmt) >=0) {
							couponDesc = "已"+groupBuilding.getPreferName()+"￥"+ PriceUtil.div100s(prefereAmt - plotpropertyOrder.getJFBAmount());
						} else {
							couponDesc = "已"+groupBuilding.getPreferName()+"￥"+ PriceUtil.div100(payBillEntity.getAmount() - deDuPrice);
						}
					}
				} else {
					couponDesc = "已"+groupBuilding.getPreferName()+"￥"+ PriceUtil.div100s(prefereAmt - plotpropertyOrder.getJFBAmount());
				}
			}
			propertyDetail.setCouponDesc(couponDesc);

			//String topName = payBillEntity.getBillTypeName()+" "+businessMonthYear.getBillMonth().getPeriodDesc();
			propertyDetail.setTopName(payBillEntity.getBillTypeName());

			List<Map<String,Object>> feeDetail = new ArrayList<Map<String,Object>>();
			if(propertyFeeDetailList!=null&&propertyFeeDetailList.size()>0){
				for(PropertyFeeDetail tmpSrcDetail:propertyFeeDetailList){
					Map<String,Object> tmpMap = new HashMap<String, Object>();
					tmpMap.put("name", tmpSrcDetail.getName());
					tmpMap.put("totalPrice", "￥" +PriceUtil.div100(BigDecimal.valueOf(tmpSrcDetail.getTotalPrice()==null?0.0:tmpSrcDetail.getTotalPrice()))+ ((tmpSrcDetail.getAllowancePrice() == null || tmpSrcDetail.getAllowancePrice() == 0) ? "" : "（物业宝已抵扣）"));
					feeDetail.add(tmpMap);
				}
			}
			if(payBillEntity.getLastUnpaid() != null && payBillEntity.getLastUnpaid() > 0) {
				Map<String,Object> tmpMap = new HashMap<String, Object>();
				tmpMap.put("name", "往月欠费");
				tmpMap.put("totalPrice", "￥" + PriceUtil.div100s(payBillEntity.getLastUnpaid()));
				feeDetail.add(tmpMap);
			}
			propertyDetail.setFeeDetail(feeDetail);
		}
		propertyDetail.setPayBillId(payBillEntity.getId()+"");

		if(payBillEntity.getIsPay()==1) {
			propertyDetail.setPayStatus(2);
		} else {
			propertyDetail.setPayStatus(1);
		}

		propertyDetail.setTotalAmt(PriceUtil.div100(payBillEntity.getAmount()));
		BigDecimal sucPay = PriceUtil.div100(payBillEntity.getAmount() - deDuPrice).subtract(PriceUtil.div100(prefereAmt));//账单中的实际支付金额只有在支付完成后才会有值payBillEntity.getSuccPayAmount()=总金额-优惠金额（随机立减）
		if(sucPay.doubleValue()<=0) {
			sucPay = BigDecimal.valueOf(0);
			if(payBillEntity.getFinanceStatus() !=null && payBillEntity.getFinanceStatus() == 1) {
				if(PriceUtil.div100(payBillEntity.getAmount() - deDuPrice)==BigDecimal.valueOf(0)) {
					prefereAmt = 0l;
				} else {
					if((payBillEntity.getAmount() - deDuPrice - prefereAmt) <= 0) {
						prefereAmt = payBillEntity.getAmount() - deDuPrice;
					}
				}
			}
		}
		propertyDetail.setSucPay(sucPay);
		propertyDetail.setPreferentialAmt(PriceUtil.div100(prefereAmt));//随机立减 订单优惠金额 = 随机立减 + 粮票金额
		propertyDetail.setJFBAmount(PriceUtil.div100(plotpropertyOrder.getJFBAmount()));
		propertyDetail.setProprietorName(replaceNameToStar(payBillEntity.getRealRoomEntity().getPropertyProprietor().getName()));
		//propertyDetail.setPayWay(plotpropertyOrder.getPayMethod()); //payWay {"1":"微信支付","2":"支付宝","3":"银联支付"}
		//propertyDetail.setPayLimiteStart(businessMonthYear.getPayTimeStartDesc());
		//propertyDetail.setPayLimiteEnd(businessMonthYear.getPayTimeEndDesc());
		//propertyDetail.setPayTm(DateUtil.strFormate(plotpropertyOrder.getPayTime(), DateUtil.formatSecond.get(), DateUtil.formatMinutes.get()));
		propertyDetail.setAddrBuilding(CommonRoomUtil.getAddressArea02(payBillEntity.getRealRoomEntity()));
		propertyDetail.setAddrRoom(CommonRoomUtil.getAddressDetail02(payBillEntity.getRealRoomEntity()));
		propertyDetail.setManagerTel(plotpropertyOrder.getManagerTel());
		if(payBillEntity.getFinanceStatus() !=null && payBillEntity.getFinanceStatus() == 1) {
			propertyDetail.setIsBuyFinance(true);
		} else {
			propertyDetail.setIsBuyFinance(false);
		}

		if(plotpropertyOrder.getEbuyOrderId() != null) {//判断是否存在订单
			propertyDetail.setOrderId(plotpropertyOrder.getEbuyOrderId().toString());
		}
		propertyDetail.setJFBPercent(payConfigService.getPayConfigHbPercent(EbuyDict.EbuyOrder_Type.Property_Bill));

		return propertyDetail;
	}
	
	/**
	 *  隐藏部分业主姓名
	 * @param userName
	 * @return
	 */
	@Override
	public String replaceNameToStar(String userName) {
		StringBuilder name = new StringBuilder();
		if(!StringUtils.isEmpty(userName)) {
			if(userName.length() > 2 ) {
				userName = String.valueOf(userName.charAt(0)) + "*" + String.valueOf(userName.charAt(userName.length() - 1));
			} else {
				userName = String.valueOf(userName.charAt(0))+"*";
			}
		} else {
			userName = "";
		}
		return userName;
	}

	/**
	 * 字符串时间比较 str >= str1 == true;
	 * @param str
	 * @param str1
	 * @return
	 */
	private static int comparaStrDate(String str, String str1) {
		if(DataUtil.isEmpty(str) && !DataUtil.isEmpty(str1)) {
			return -1;
		}
		if(!DataUtil.isEmpty(str) && DataUtil.isEmpty(str1)) {
			return 1;
		}
		if(DataUtil.isEmpty(str) && DataUtil.isEmpty(str1)) {
			return 1;
		}
		long date = DateUtils.convertStrToDate(str, "yyyy-MM").getTime();
		long date1 = DateUtils.convertStrToDate(str1, "yyyy-MM").getTime();
		if(date >  date1) {
			return 1;
		} else if(date == date1) {
			return 0;
		}
		return -1;
	}

    /**
     * 选择周期账期对应账单查询（当期）
     * @param paraMap
     * @return
     */
    @Override
    public List<AlterCyclesEntity> getAlterPayBillEntity(Map<String, Object> paraMap) {
        return plotpropertyDao.getAlterPayBillEntity(paraMap);
    }

	@Override
	public List<PayBillDetailDto> getPayBillDetailByIds(List<BigInteger> idsList) {
		return plotpropertyDao.getPayBillDetailByIds(idsList);
	}

	@Override
	public PropertyBillTailDto getRoomAddressAndPPName(BigInteger realRoomId) {
		return plotpropertyDao.getRoomAddressAndPPName(realRoomId);
	}
}