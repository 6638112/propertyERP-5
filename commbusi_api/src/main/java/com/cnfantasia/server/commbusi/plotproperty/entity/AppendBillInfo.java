package com.cnfantasia.server.commbusi.plotproperty.entity;

import java.util.Date;

import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyDict;
import com.cnfantasia.server.api.plotproperty.util.PayBillShowUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;

/**
 * 账单附加信息
* Filename:    AppendBillInfo.java
* @version:    1.0.0
* Create at:   2015年12月15日 下午7:36:26
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年12月15日       shiyl             1.0             1.0 Version
 */
public class AppendBillInfo {
	
	/**是否为物业费单*/
	private Boolean isPropFee;
	/**是否为月度缴费*/
	private Boolean isMonthFee;
	/**账单类型名称*/
	private String billTypeName;
	/**账单周期区间*/
	private String billPeriodDesc;
	
	private AppendBillInfo(Boolean isPropFee,Boolean isMonthFee,String billTypeName,String billPeriodDesc){
		this.isPropFee = isPropFee;
		this.isMonthFee = isMonthFee;
		this.billTypeName = billTypeName;
		this.billPeriodDesc = billPeriodDesc;
	}
	
	public static AppendBillInfo newInstance(PayBill payBill,PayBillType payBillType,IBusinessMonthYear monthYearWithGB){
		Boolean isPropFee = null;//是否为物业费单
		Boolean isMonthFee = null;//是否为月度缴费
		String billTypeName = null;//账单类型名称
		String billPeriodDesc = null;
		
		isPropFee = payBillType.getIsPropFee().compareTo(PlotpropertyDict.PayBillType_IsPropFee.YES) == 0;
		isMonthFee = payBillType.getPaytimeType().compareTo(PlotpropertyDict.PayBillType_PaytimeType.MONTH) == 0;
		billTypeName = payBillType.getName();
		if(monthYearWithGB!=null){
			billPeriodDesc = monthYearWithGB.getBillMonth().getPeriodDesc();//账单周期区间
		}else if(payBill!=null){
			DataType.ISectionDate billMonth = null;
			if(isMonthFee){
				String monthStr = PayBillShowUtil.getBillShowMonth(payBill);
				try {
					billMonth = SectionDateFactory.newInstanceForBillMonth(DateUtil.formatSecond.get().parse(monthStr));
				} catch (Exception e) {
					throw new BusinessRuntimeException("AppendBillInfo.parseBillMonth.signal.failed",new Object[]{monthStr});
				}
			}else{
				Date billStart = null;
				try {
					billStart = DateUtil.formatSecond.get().parse(payBill.getBillMonthStart());
				} catch (Exception e) {
					throw new BusinessRuntimeException(e);
				}
				Date billEnd = null;
				try {
					billEnd = DateUtil.formatSecond.get().parse(payBill.getBillMonthEnd());
				} catch (Exception e) {
					throw new BusinessRuntimeException(e);
				}
				billMonth = SectionDateFactory.newInstanceForBillMonth(billStart, billEnd);
			}
			billPeriodDesc = billMonth.getPeriodDesc();
		}
		
		AppendBillInfo appendBillInfo = new AppendBillInfo(isPropFee, isMonthFee, billTypeName, billPeriodDesc);
		return appendBillInfo;
	}
	
	public Boolean getIsPropFee() {
		return isPropFee;
	}

	public Boolean getIsMonthFee() {
		return isMonthFee;
	}

	public String getBillTypeName() {
		return billTypeName;
	}

	public String getBillPeriodDesc() {
		return billPeriodDesc;
	}
}
