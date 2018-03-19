package com.cnfantasia.server.domainbase.propertyFeeDetail.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;import java.lang.Double;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业费费用清单详情) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyFeeDetail implements Serializable{
*/


public class PropertyFeeDetail extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tPayBillFId;	/** 账期id (极致，选择周期为空 ) */	private BigInteger tCycleId;	/** 费用类型 */	private Integer type;	/** 费用名称 */	private String name;	/** 单价(元) */	private Double signalPrice;	/** 单价串(抄表 */	private String signalPriceStr;	/** 计价单位名称 */	private String priceUnitName;	/** 计价单位取值 */	private Long priceUnitValue;	/** 临时费用账单合计 */	private Double totalAmount;	/** 欠费结转（分） */	private Double unpaid;	/** 费用合计(分) */	private Double totalPrice;	/** 购买物业宝理财产品抵扣金额 */	private Long allowancePrice;	/** 收费模式类型：1：抄表收费，2：固定收费，3：临时收费, 4 */	private Integer feeType;	/** 账单月份跨度 */	private Long billMonthSize;	/** 费用项对应的原始数据的id */	private BigInteger itemHasRoomId;	/** 抄表收费项对应的表名称 */	private String mrName;	/** 抄表倍率 */	private Double multiplierTimes;
	public PropertyFeeDetail(){
	}
	public PropertyFeeDetail(PropertyFeeDetail arg){
		this.id = arg.getId();		this.tPayBillFId = arg.gettPayBillFId();		this.tCycleId = arg.gettCycleId();		this.type = arg.getType();		this.name = arg.getName();		this.signalPrice = arg.getSignalPrice();		this.signalPriceStr = arg.getSignalPriceStr();		this.priceUnitName = arg.getPriceUnitName();		this.priceUnitValue = arg.getPriceUnitValue();		this.totalAmount = arg.getTotalAmount();		this.unpaid = arg.getUnpaid();		this.totalPrice = arg.getTotalPrice();		this.allowancePrice = arg.getAllowancePrice();		this.feeType = arg.getFeeType();		this.billMonthSize = arg.getBillMonthSize();		this.itemHasRoomId = arg.getItemHasRoomId();		this.mrName = arg.getMrName();		this.multiplierTimes = arg.getMultiplierTimes();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tPayBillFId 	 * @param tCycleId 账期id (极致，选择周期为空 )	 * @param type 费用类型	 * @param name 费用名称	 * @param signalPrice 单价(元)	 * @param signalPriceStr 单价串(抄表	 * @param priceUnitName 计价单位名称	 * @param priceUnitValue 计价单位取值	 * @param totalAmount 临时费用账单合计	 * @param unpaid 欠费结转（分）	 * @param totalPrice 费用合计(分)	 * @param allowancePrice 购买物业宝理财产品抵扣金额	 * @param feeType 收费模式类型：1：抄表收费，2：固定收费，3：临时收费, 4	 * @param billMonthSize 账单月份跨度	 * @param itemHasRoomId 费用项对应的原始数据的id	 * @param mrName 抄表收费项对应的表名称	 * @param multiplierTimes 抄表倍率	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PropertyFeeDetail(BigInteger id,BigInteger tPayBillFId,BigInteger tCycleId,Integer type,String name,Double signalPrice,String signalPriceStr,String priceUnitName,Long priceUnitValue,Double totalAmount,Double unpaid,Double totalPrice,Long allowancePrice,Integer feeType,Long billMonthSize,BigInteger itemHasRoomId,String mrName,Double multiplierTimes,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tPayBillFId = tPayBillFId;		this.tCycleId = tCycleId;		this.type = type;		this.name = name;		this.signalPrice = signalPrice;		this.signalPriceStr = signalPriceStr;		this.priceUnitName = priceUnitName;		this.priceUnitValue = priceUnitValue;		this.totalAmount = totalAmount;		this.unpaid = unpaid;		this.totalPrice = totalPrice;		this.allowancePrice = allowancePrice;		this.feeType = feeType;		this.billMonthSize = billMonthSize;		this.itemHasRoomId = itemHasRoomId;		this.mrName = mrName;		this.multiplierTimes = multiplierTimes;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tPayBillFId=").append(tPayBillFId).append(";");		sbf.append("tCycleId=").append(tCycleId).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("signalPrice=").append(signalPrice).append(";");		sbf.append("signalPriceStr=").append(signalPriceStr).append(";");		sbf.append("priceUnitName=").append(priceUnitName).append(";");		sbf.append("priceUnitValue=").append(priceUnitValue).append(";");		sbf.append("totalAmount=").append(totalAmount).append(";");		sbf.append("unpaid=").append(unpaid).append(";");		sbf.append("totalPrice=").append(totalPrice).append(";");		sbf.append("allowancePrice=").append(allowancePrice).append(";");		sbf.append("feeType=").append(feeType).append(";");		sbf.append("billMonthSize=").append(billMonthSize).append(";");		sbf.append("itemHasRoomId=").append(itemHasRoomId).append(";");		sbf.append("mrName=").append(mrName).append(";");		sbf.append("multiplierTimes=").append(multiplierTimes).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettPayBillFId() {		return tPayBillFId;	}	public void settPayBillFId(BigInteger tPayBillFId) {		this.tPayBillFId = tPayBillFId;	}	public BigInteger gettCycleId() {		return tCycleId;	}	public void settCycleId(BigInteger tCycleId) {		this.tCycleId = tCycleId;	}	public Integer getType() {		return type;	}	public void setType(Integer type) {		this.type = type;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public Double getSignalPrice() {		return signalPrice;	}	public void setSignalPrice(Double signalPrice) {		this.signalPrice = signalPrice;	}	public String getSignalPriceStr() {		return signalPriceStr;	}	public void setSignalPriceStr(String signalPriceStr) {		this.signalPriceStr = signalPriceStr;	}	public String getPriceUnitName() {		return priceUnitName;	}	public void setPriceUnitName(String priceUnitName) {		this.priceUnitName = priceUnitName;	}	public Long getPriceUnitValue() {		return priceUnitValue;	}	public void setPriceUnitValue(Long priceUnitValue) {		this.priceUnitValue = priceUnitValue;	}	public Double getTotalAmount() {		return totalAmount;	}	public void setTotalAmount(Double totalAmount) {		this.totalAmount = totalAmount;	}	public Double getUnpaid() {		return unpaid;	}	public void setUnpaid(Double unpaid) {		this.unpaid = unpaid;	}	public Double getTotalPrice() {		return totalPrice;	}	public void setTotalPrice(Double totalPrice) {		this.totalPrice = totalPrice;	}	public Long getAllowancePrice() {		return allowancePrice;	}	public void setAllowancePrice(Long allowancePrice) {		this.allowancePrice = allowancePrice;	}	public Integer getFeeType() {		return feeType;	}	public void setFeeType(Integer feeType) {		this.feeType = feeType;	}	public Long getBillMonthSize() {		return billMonthSize;	}	public void setBillMonthSize(Long billMonthSize) {		this.billMonthSize = billMonthSize;	}	public BigInteger getItemHasRoomId() {		return itemHasRoomId;	}	public void setItemHasRoomId(BigInteger itemHasRoomId) {		this.itemHasRoomId = itemHasRoomId;	}	public String getMrName() {		return mrName;	}	public void setMrName(String mrName) {		this.mrName = mrName;	}	public Double getMultiplierTimes() {		return multiplierTimes;	}	public void setMultiplierTimes(Double multiplierTimes) {		this.multiplierTimes = multiplierTimes;	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertyFeeDetail other = (PropertyFeeDetail) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
