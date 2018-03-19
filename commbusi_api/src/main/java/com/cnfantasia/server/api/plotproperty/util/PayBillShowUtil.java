/**   
* Filename:    PayBillShowUtil.java   
* @version:    1.0  
* Create at:   2015年2月12日 上午2:58:30   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年2月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.plotproperty.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commonBusiness.util.CommonRoomUtil;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyConstant;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyDict;
import com.cnfantasia.server.api.plotproperty.entity.PlotpropertyCombEntity;
import com.cnfantasia.server.api.plotproperty.entity.PlotpropertyCombShowEntity;
import com.cnfantasia.server.api.plotproperty.entity.PlotpropertyOrderEntity;
import com.cnfantasia.server.api.prize.entity.PrizeRecordSimpleEntity;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.RoomEntity;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.commbusi.plotproperty.entity.AppendBillInfo;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;
import com.cnfantasia.server.commbusi.plotproperty.entity.PayBillInfo;
import com.cnfantasia.server.commbusi.plotproperty.entity.PropFeeDiscount;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.entity.EbuyOrderHasTPayBill;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;

/**
 * Filename:    PayBillShowUtil.java
 * @version:    1.0.0
 * Create at:   2015年2月12日 上午2:58:30
 * Description:物业账单数据显示工具类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年2月12日       shiyl             1.0             1.0 Version
 */
public class PayBillShowUtil {
	private static Log logger = LogFactory.getLog(PayBillShowUtil.class);
	
	/**
	 * 获取物业账单月份显示日期
	 * @param payBill
	 * @return
	 */
	public static String getBillShowMonth(PayBill payBill){
		if(payBill==null){return null;}
		if(payBill.getBillMonth()!=null){//先获取账单月份数据
			return payBill.getBillMonth();
		}else{//为空则默认使用折扣月份数据
			return payBill.getMonth();
		}
	}
	
	public static PlotpropertyCombShowEntity parsePlotpropertyComb(BigInteger userId,PlotpropertyCombEntity plotpropertyCombEntity,String nowTime){
		RoomEntity roomEntity = plotpropertyCombEntity.getRoomEntity();//门牌信息
		
		PayBillInfo payBillEntity = plotpropertyCombEntity.getPayBillEntity();//物业账单信息
		List<PropertyFeeDetail> propertyFeeDetailList = payBillEntity==null?null:payBillEntity.getPropertyFeeDetailList();//账单费用详情
		
		PrizeRecordSimpleEntity prizeRecordSimpleEntity = plotpropertyCombEntity.getPrizeRecordSimpleEntity();//对应折扣信息
		PlotpropertyOrderEntity plotpropertyOrderEntity = plotpropertyCombEntity.getPlotpropertyOrderEntity();//对应支付订单信息
		
		Boolean signStatus =plotpropertyCombEntity.fetchSignStatus();//签约状态
		Boolean isSupport = plotpropertyCombEntity.fetchIsSupport();//是否点支持
		Integer totalSupportCount = plotpropertyCombEntity.fetchTotalSupportCount();//总的支持人数
		EbuyOrderHasTPayBill ebuyOrderHasTPayBill = plotpropertyOrderEntity==null?null:plotpropertyOrderEntity.getEbuyOrderHasTPayBill();
		PayBillType payBillType = plotpropertyCombEntity.getPayBillType();
		IBusinessMonthYear monthYearWithGB = plotpropertyCombEntity.getMonthYearWithGB();
		List<PropFeeDiscount> splitPrizeRecordList = plotpropertyCombEntity.getSplitPrizeRecordList();
		return parsePlotpropertyComb(userId,payBillEntity,roomEntity,signStatus,isSupport,totalSupportCount,prizeRecordSimpleEntity,propertyFeeDetailList
				,plotpropertyOrderEntity,ebuyOrderHasTPayBill,nowTime,payBillType,monthYearWithGB,splitPrizeRecordList);
	}
	
	private static PlotpropertyCombShowEntity parsePlotpropertyComb(BigInteger userId,PayBill payBill,RoomEntity roomEntity,Boolean signStatus,Boolean isSupport,Integer totalSupportCount
			,PrizeRecordSimpleEntity prizeRecordSimpleEntity,List<PropertyFeeDetail> propertyFeeDetailList,EbuyOrder ebuyOrder,EbuyOrderHasTPayBill ebuyOrderHasTPayBill
			,String nowTime,PayBillType payBillType,IBusinessMonthYear monthYearWithGB,List<PropFeeDiscount> splitPrizeRecordList){
		BigInteger groupBuildingId = null;//小区Id
		String groupBuildingDetail = null;//门牌的市、区、小区信息
		String roomDetail = null;//门牌的 楼栋、单元、门牌号的详细信息
		String propertyManageTel = null;//物业管理处电话
		Boolean isRealRoomCheck = null;//用户是否已经确认门牌
		String proprietorName = null;//业主姓名
//		Integer payLimiteStart = null;//缴费周期Start
//		Integer payLimiteEnd = null;//缴费周期End
		Boolean isInPayCycle = null;//是否在缴费周期内
		
		if(roomEntity!=null){
			GroupBuildingEntity groupBuildingEntity = roomEntity.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity();
			groupBuildingId = groupBuildingEntity.getId();
			groupBuildingDetail = CommonRoomUtil.getAddressArea02(roomEntity);
			roomDetail= CommonRoomUtil.getAddressDetail02(roomEntity);
			propertyManageTel = groupBuildingEntity.getPropertyManagementEntity()==null?"无":groupBuildingEntity.getPropertyManagementEntity().getTel();
			if(roomEntity.getRealroomCheckStatus()!=null&&RoomDict.Room_RealRoomCheckStatus.IsConfirmed.compareTo(roomEntity.getRealroomCheckStatus())==0){
				isRealRoomCheck = true;
			}else{
				isRealRoomCheck = false;
			}
			if(roomEntity.getRealRoomEntity().getPropertyProprietor()!=null){//真实门牌的业主信息不为空
				proprietorName = roomEntity.getRealRoomEntity().getPropertyProprietor().getName();
			}
//			payLimiteStart = groupBuildingEntity.getPayPeriodStart();
//			payLimiteEnd = groupBuildingEntity.getPayPeriodEnd();
//			if(payLimiteStart!=null&&payLimiteEnd!=null){
////				isInPayCycle = PropertyPeriodCalculateUtil.checkIsInPeriod(nowTime, payLimiteStart, payLimiteEnd);//syl-del 2014-12-18 09:30:09
//				{//syl-upd-2014-12-18 09:30:00
//					isInPayCycle = false;
//					if(payBill!=null&&payBill.getMonth()!=null){
//						StartEndDate startEndDate = PropertyPeriodCalculateUtil.getPreMonthStartEndByTime(nowTime, payLimiteEnd);
//						Date payBillMonthDate = null;
//						try {
//							payBillMonthDate = DateUtil.formatMonth_Split.get().parse(payBill.getMonth());
//						} catch (ParseException e) {
//							logger.debug("PlotpropertyService.plotpropertyCombEntity2Map02.parseTime error,time is "+payBill.getMonth(),e);
//						}
//						isInPayCycle = startEndDate.checkIsInInterval(payBillMonthDate);
//					}
//				}
//			}
			{
				Date nowTimeDate = null;
				try {
					nowTimeDate = DateUtil.formatDay.get().parse(nowTime);
				} catch (ParseException e) {
					logger.debug("PlotpropertyService.plotpropertyCombEntity2Map02.parseTime error,time is "+nowTime,e);
				}
				if(nowTimeDate!=null&&monthYearWithGB!=null){
					isInPayCycle = monthYearWithGB.isInPayTime(nowTimeDate);
				}
			}
		}
		Boolean hasBillInfo = null;//是否有账单信息
		if(payBill!=null){
			hasBillInfo = true;
		}else{
			hasBillInfo = false;
		}
		//物业订单Id
		BigInteger orderId = ebuyOrder==null?null:ebuyOrder.getId();
//		Boolean orderIfUseDiscount = false;//对应订单是否使用了折扣
		Boolean isOrderDiscountCurrRoom = false;//订单使用的折扣是否为当前用户的默认门牌
		Integer isBuyFinance = 0;
		//最低折扣
		Double miniDiscount = null;
		if(payBill!=null){
			if(payBill.getIsPay()!=null&&PlotpropertyDict.PayBill_IsPay.TRUE.compareTo(payBill.getIsPay())==0){//已经缴费过的
				miniDiscount = payBill.getDiscount();
			}else{
				if(ebuyOrder!=null&&ebuyOrderHasTPayBill.getDiscount()!=null&&
						(ebuyOrderHasTPayBill.getPrizeUserRoomId()!=null&&roomEntity!=null
						&&ebuyOrderHasTPayBill.getPrizeUserRoomId()
						.compareTo(roomEntity.getId())==0)
						){
					isOrderDiscountCurrRoom = true;
					miniDiscount = ebuyOrderHasTPayBill.getDiscount();
				}else{
					if(prizeRecordSimpleEntity!=null&&prizeRecordSimpleEntity.hasUsed()!=null&&!prizeRecordSimpleEntity.hasUsed()){//有最低折扣且未被使用
						miniDiscount = prizeRecordSimpleEntity.getDiscount();
					}
				}

//				if(ebuyOrder!=null){//已经生成订单
//					if(ebuyOrderHasTPayBill.getDiscount()!=null){//且使用了折扣
//						miniDiscount = ebuyOrderHasTPayBill.getDiscount();
////						orderIfUseDiscount =  true;
//						if(ebuyOrderHasTPayBill.getPrizeUserRoomId()!=null&&roomEntity!=null
//								&&ebuyOrderHasTPayBill.getPrizeUserRoomId()
//								.compareTo(roomEntity.getId())==0){
//							isOrderDiscountCurrRoom = true;
//						}
//					}else{//有订单但是未使用折扣，则返回最低折扣
//						if(prizeRecordSimpleEntity!=null&&prizeRecordSimpleEntity.hasUsed()!=null&&!prizeRecordSimpleEntity.hasUsed()){//有最低折扣且未被使用
//							miniDiscount = prizeRecordSimpleEntity.getDiscount();
//						}
//					}
//				}else{
//					if(prizeRecordSimpleEntity!=null&&prizeRecordSimpleEntity.hasUsed()!=null&&!prizeRecordSimpleEntity.hasUsed()){//有最低折扣且未被使用
//						miniDiscount = prizeRecordSimpleEntity.getDiscount();
//					}
//				}
			}
		}else{//如果没有物业账单则返回最低折扣
			if(prizeRecordSimpleEntity!=null&&prizeRecordSimpleEntity.hasUsed()!=null&&!prizeRecordSimpleEntity.hasUsed()){//有最低折扣且未被使用
				miniDiscount = prizeRecordSimpleEntity.getDiscount();
			}
		}
		
		Boolean isPay = null;
		Integer payStatus = null;
//		ISectionDateMulti currPropStartEndTimeDescEntity = null;
		if(payBill!=null){
			//物业周期信息
//			currPropStartEndTimeDescEntity = PropertyPeriodCalculateUtil.getStartEndTimeDescEntity(payBill==null?null:payBill.getMonth(), DateUtil.formatDay.get(), payLimiteStart, payLimiteEnd);
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
		} else {
//			currPropStartEndTimeDescEntity = PropertyPeriodCalculateUtil.getStartEndTimeDescEntity(DateUtils.getTodayStr(), DateUtil.formatDay.get(), payLimiteStart, payLimiteEnd);
		}
		
		
		Double gainMoney = 0.0;//优惠的金额
		Double manageFee = null;//应缴管理费[折扣前]
		Double manageFeeDiscount = null;//应缴管理费[折扣后]
		Double otherFee = 0.0;//其他费用合计
		PropertyFeeDetail ext_manageFeeDetail = null;//应缴管理费详细
		List<PropertyFeeDetail> ext_otherFeeDetail = null;//其他费用详细
		if(propertyFeeDetailList!=null){
			ext_otherFeeDetail = new ArrayList<PropertyFeeDetail>();
			for(PropertyFeeDetail tmpPropertyFeeDetail:propertyFeeDetailList){
				PropertyFeeDetail tmpFeeMap = tmpPropertyFeeDetail;
				if(PlotpropertyDict.PropertyFeeDetail_Type.ManagerFee.compareTo(tmpPropertyFeeDetail.getType())==0){
					manageFee = tmpPropertyFeeDetail.getTotalPrice();
					manageFeeDiscount = manageFee;
					if(tmpPropertyFeeDetail.getAllowancePrice() != null && tmpPropertyFeeDetail.getAllowancePrice() > 0) {
						gainMoney = tmpPropertyFeeDetail.getAllowancePrice().doubleValue();
						manageFeeDiscount = tmpPropertyFeeDetail.getTotalPrice() - tmpPropertyFeeDetail.getAllowancePrice();
						
						isOrderDiscountCurrRoom = false;
						miniDiscount = null;
						isBuyFinance = 1;
					} else if(miniDiscount!=null){
						//计算优惠的金额
						gainMoney = PropertyFeeCalculator.calculate(tmpPropertyFeeDetail.getTotalPrice(), miniDiscount);
						manageFeeDiscount = tmpPropertyFeeDetail.getTotalPrice()-gainMoney;
					}else if(splitPrizeRecordList!=null&&splitPrizeRecordList.size()>0){//通过账单拆分计算优惠
						for(PropFeeDiscount tmpSplit:splitPrizeRecordList){
							if(!isPay){
								PrizeRecord leastRecord = tmpSplit.getLeastRecord();
								if(leastRecord!=null){//有最低折扣
									Double gainMoneyTmp = 0.0;//节省的金额
									Long manageAmount = tmpSplit.getManageAmount() == null ? 0 : tmpSplit.getManageAmount();
									gainMoneyTmp = PropertyFeeCalculator.calculate(manageAmount.doubleValue(), leastRecord.getDiscount());
									gainMoney+=gainMoneyTmp;
									tmpSplit.setDiscount(leastRecord.getDiscount());
									tmpSplit.setPrizeRecordId(leastRecord.getId());
									
									tmpSplit.setSuccPayAmount(BigDecimal.valueOf(manageAmount).subtract(BigDecimal.valueOf(gainMoneyTmp)).longValue());
								}else{
									tmpSplit.setDiscount(null);
									tmpSplit.setPrizeRecordId(null);
									tmpSplit.setSuccPayAmount(tmpSplit.getManageAmount());
//									gainMoney+=(tmpSplit.getManageAmount()-(tmpSplit.getSuccPayAmount()==null?tmpSplit.getManageAmount():tmpSplit.getSuccPayAmount()));
								}
							}else{
								gainMoney+=(tmpSplit.getManageAmount()-(tmpSplit.getSuccPayAmount()==null?tmpSplit.getManageAmount():tmpSplit.getSuccPayAmount()));
							}
							
						}
						manageFeeDiscount = tmpPropertyFeeDetail.getTotalPrice()-gainMoney;
						
					}
					ext_manageFeeDetail=tmpFeeMap;
				}else{
					otherFee = otherFee+(tmpPropertyFeeDetail.getTotalPrice()==null?0L:tmpPropertyFeeDetail.getTotalPrice());
					otherFee -= tmpPropertyFeeDetail.getAllowancePrice();
					gainMoney += tmpPropertyFeeDetail.getAllowancePrice(); //减到物业宝抵扣的其它费用
					ext_otherFeeDetail.add(tmpFeeMap);
				}
			}
		}
		
		Double amountDiscount = null;//总的折扣后金额
		if(payBill!=null){
			if(payBill.getIsPay()!=null&&PlotpropertyDict.PayBill_IsPay.TRUE.compareTo(payBill.getIsPay())==0){//已经缴费过的
				amountDiscount = payBill.getSuccPayAmount()==null ? 0.0 : payBill.getSuccPayAmount().doubleValue();
			}else{
				amountDiscount = payBill.getAmount()==null ? 0.0 : payBill.getAmount().doubleValue();
				if(miniDiscount!=null || gainMoney > 0){
					amountDiscount = payBill.getAmount()-gainMoney;
				}
			}
		}
		
		AppendBillInfo appendBillInfo = AppendBillInfo.newInstance(payBill,payBillType,monthYearWithGB);
		return new PlotpropertyCombShowEntity(groupBuildingId, groupBuildingDetail, roomDetail, propertyManageTel, 
				isRealRoomCheck, proprietorName/*, payLimiteStart, payLimiteEnd*/, isInPayCycle, hasBillInfo, orderId, 
				isOrderDiscountCurrRoom, miniDiscount, (gainMoney==null?null:gainMoney.longValue()), 
				(manageFee==null?null:manageFee.longValue()), (manageFeeDiscount==null?null:manageFeeDiscount.longValue()), 
				(otherFee==null?null:otherFee.longValue()), ext_manageFeeDetail, 
				ext_otherFeeDetail, (amountDiscount==null?null:amountDiscount.longValue()),/* currPropStartEndTimeDescEntity,*/ isPay, payStatus, isBuyFinance, appendBillInfo);
	}
	
}
