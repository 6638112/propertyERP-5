/**   
* Filename:    PlotpropertyCombShowEntity.java   
* @version:    1.0  
* Create at:   2015年5月5日 下午12:55:11   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年5月5日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.plotproperty.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.redenvelope.util.IDiscount2hbRule;
import com.cnfantasia.server.commbusi.plotproperty.entity.AppendBillInfo;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;

/**
 * Filename:    PlotpropertyCombShowEntity.java
 * @version:    1.0.0
 * Create at:   2015年5月5日 下午12:55:11
 * Description:物业账单前端展示字段搜集
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年5月5日       shiyl             1.0             1.0 Version
 */
public class PlotpropertyCombShowEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**小区Id*/
	private BigInteger groupBuildingId = null;
	/**门牌的市、区、小区信息*/
	private String groupBuildingDetail = null;
	/**门牌的 楼栋、单元、门牌号的详细信息*/
	private String roomDetail = null;
	/**物业管理处电话*/
	private String propertyManageTel = null;
	/**用户是否已经确认门牌*/
	private Boolean isRealRoomCheck = null;
	/**业主姓名*/
	private String proprietorName = null;
//	/**缴费周期Start*/
//	private Integer payLimiteStart = null;
//	/**缴费周期End*/
//	private Integer payLimiteEnd = null;
	/**是否在缴费周期内*/
	private Boolean isInPayCycle = null;
	/**是否有账单信息*/
	private Boolean hasBillInfo = null;
	/**物业订单Id*/
	private BigInteger orderId = null;
	/**订单使用的折扣是否为当前用户的默认门牌*/
	private Boolean isOrderDiscountCurrRoom = null;
	/**最低折扣*/
	private Double miniDiscount = null;
	/**优惠的金额*/
	private Long gainMoney = 0L;
	/**应缴管理费[折扣前]*/
	private Long manageFee = null;
	/**应缴管理费[折扣后]*/
	private Long manageFeeDiscount = null;
	/**其他费用合计*/
	private Long otherFee = null;
	/**应缴管理费详细*/
	private PropertyFeeDetail ext_manageFeeDetail = null;
	/**其他费用详细*/
	private List<PropertyFeeDetail> ext_otherFeeDetail = null;
	/**总的折扣后金额*/
	private Long amountDiscount = null;
	
//	/**物业周期信息*/
//	private ISectionDateMulti currPropStartEndTimeDescEntity = null;
	/**物业账单是否已支付*/
	private Boolean isPay = null;
	/**物业账单支付状态*/
	private Integer payStatus = null;
	
	private Integer isBuyFinance = 0;
	
	/**附加账单信息*/
	private AppendBillInfo appendBillInfo;
	
	
	public PlotpropertyCombShowEntity(BigInteger groupBuildingId,String groupBuildingDetail,String roomDetail,String propertyManageTel,Boolean isRealRoomCheck,String proprietorName
			/*,Integer payLimiteStart,Integer payLimiteEnd*/,Boolean isInPayCycle,Boolean hasBillInfo,BigInteger orderId,Boolean isOrderDiscountCurrRoom
			,Double miniDiscount,Long gainMoney,Long manageFee,Long manageFeeDiscount,Long otherFee,PropertyFeeDetail ext_manageFeeDetail,List<PropertyFeeDetail> ext_otherFeeDetail,Long amountDiscount
			/*,ISectionDateMulti currPropStartEndTimeDescEntity*/,Boolean isPay,Integer payStatus, Integer isBuyFinance,AppendBillInfo appendBillInfo){
		this.groupBuildingId = groupBuildingId;
		this.groupBuildingDetail = groupBuildingDetail;
		this.roomDetail = roomDetail;
		this.propertyManageTel = propertyManageTel;
		this.isRealRoomCheck = isRealRoomCheck;
		this.proprietorName = proprietorName;
//		this.payLimiteStart = payLimiteStart;
//		this.payLimiteEnd = payLimiteEnd;
		this.isInPayCycle = isInPayCycle;
		this.hasBillInfo = hasBillInfo;
		this.orderId = orderId;
		this.isOrderDiscountCurrRoom = isOrderDiscountCurrRoom;
		this.miniDiscount = miniDiscount;
		this.gainMoney = gainMoney;
		this.manageFee = manageFee;
		this.manageFeeDiscount = manageFeeDiscount;
		this.otherFee = otherFee;
		this.ext_manageFeeDetail = ext_manageFeeDetail;
		this.ext_otherFeeDetail = ext_otherFeeDetail;
		this.amountDiscount = amountDiscount;
		
//		this.currPropStartEndTimeDescEntity = currPropStartEndTimeDescEntity;
		this.isPay = isPay;
		this.payStatus = payStatus;
		this.isBuyFinance = isBuyFinance;
		this.appendBillInfo = appendBillInfo;
		
	}
	
	public BigInteger getGroupBuildingId() {
		return groupBuildingId;
	}
	public String getGroupBuildingDetail() {
		return groupBuildingDetail;
	}
	public String getRoomDetail() {
		return roomDetail;
	}
	public String getPropertyManageTel() {
		return propertyManageTel;
	}
	public Boolean getIsRealRoomCheck() {
		return isRealRoomCheck;
	}
	public String getProprietorName() {
		return proprietorName;
	}
//	public Integer getPayLimiteStart() {
//		return payLimiteStart;
//	}
//	public Integer getPayLimiteEnd() {
//		return payLimiteEnd;
//	}
	public Boolean getIsInPayCycle() {
		return isInPayCycle;
	}
	public Boolean getHasBillInfo() {
		return hasBillInfo;
	}
	public BigInteger getOrderId() {
		return orderId;
	}
	public Boolean getIsOrderDiscountCurrRoom() {
		return isOrderDiscountCurrRoom;
	}
	public Double getMiniDiscount() {
		return miniDiscount;
	}
	public Long getGainMoney() {
		return gainMoney;
	}
	public Long getManageFee() {
		return manageFee;
	}
	public Long getManageFeeDiscount() {
		return manageFeeDiscount;
	}
	public Long getOtherFee() {
		return otherFee;
	}
	public PropertyFeeDetail getExt_manageFeeDetail() {
		return ext_manageFeeDetail;
	}
	public List<PropertyFeeDetail> getExt_otherFeeDetail() {
		return ext_otherFeeDetail;
	}
	public Long getAmountDiscount() {
		return amountDiscount;
	}

//	public ISectionDateMulti getCurrPropStartEndTimeDescEntity() {
//		return currPropStartEndTimeDescEntity;
//	}

	public Boolean getIsPay() {
		return isPay;
	}

	public Integer getPayStatus() {
		return payStatus;
	}
	
	public BigDecimal fetchMiniDiscountConvertMoney(IDiscount2hbRule discount2hbRule,BigInteger userId){
		return miniDiscount==null?null:PriceUtil.div100(discount2hbRule.getMoneyByDiscount(userId,miniDiscount));
	}

	public Integer getIsBuyFinance() {
		return isBuyFinance;
	}

	public void setIsBuyFinance(Integer isBuyFinance) {
		this.isBuyFinance = isBuyFinance;
	}

	public AppendBillInfo getAppendBillInfo() {
		return appendBillInfo;
	}

	
}
