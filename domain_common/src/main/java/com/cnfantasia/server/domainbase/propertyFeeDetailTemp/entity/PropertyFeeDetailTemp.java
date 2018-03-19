package com.cnfantasia.server.domainbase.propertyFeeDetailTemp.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;import java.lang.Double;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业收费项费用明细临时表（生成账单使用）) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyFeeDetailTemp implements Serializable{
*/


public class PropertyFeeDetailTemp extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 费用项类型 */	private Integer type;	/** 费用名称 */	private String name;	/** 抄表一户多表表名称 */	private String mrName;	/** 费用合计(分) */	private Double price;	/** 账单合计 */	private Double totalAmount;	/** 单价(分) */	private Double signalPrice;	/** 单价串(抄表 */	private String signalPriceStr;	/** 计价单位取值(多少平方米/用量) */	private Long priceUnitValue;	/** 本期读数（抄表） */	private Double nowValue;	/** 上期读取（抄表） */	private Double priorValue;	/** 小区id */	private BigInteger tGbId;	/** 真实房间id */	private BigInteger tRealRoomId;	/** 账期id */	private BigInteger tBillCycleId;	/** 费用项id(常规类型对应t_fixed_fee_item_has_room的f_id) */	private BigInteger targetId;	/** 房间收费标准ID（抄表 */	private BigInteger tMrStandardRoomId;	/** 抄表倍率 */	private Double multiplierTimes;	/** 抄表往月欠费（单位 */	private Long lastUnpaid;	/** 物业费用起始时间（暂时只针对选择周期使用） */	private String billMonthStart;	/** 费用账单已经生成到的月份（截止月份） */	private String createBillMonth;
	public PropertyFeeDetailTemp(){
	}
	public PropertyFeeDetailTemp(PropertyFeeDetailTemp arg){
		this.id = arg.getId();		this.type = arg.getType();		this.name = arg.getName();		this.mrName = arg.getMrName();		this.price = arg.getPrice();		this.totalAmount = arg.getTotalAmount();		this.signalPrice = arg.getSignalPrice();		this.signalPriceStr = arg.getSignalPriceStr();		this.priceUnitValue = arg.getPriceUnitValue();		this.nowValue = arg.getNowValue();		this.priorValue = arg.getPriorValue();		this.tGbId = arg.gettGbId();		this.tRealRoomId = arg.gettRealRoomId();		this.tBillCycleId = arg.gettBillCycleId();		this.targetId = arg.getTargetId();		this.tMrStandardRoomId = arg.gettMrStandardRoomId();		this.multiplierTimes = arg.getMultiplierTimes();		this.lastUnpaid = arg.getLastUnpaid();		this.billMonthStart = arg.getBillMonthStart();		this.createBillMonth = arg.getCreateBillMonth();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param type 费用项类型	 * @param name 费用名称	 * @param mrName 抄表一户多表表名称	 * @param price 费用合计(分)	 * @param totalAmount 账单合计	 * @param signalPrice 单价(分)	 * @param signalPriceStr 单价串(抄表	 * @param priceUnitValue 计价单位取值(多少平方米/用量)	 * @param nowValue 本期读数（抄表）	 * @param priorValue 上期读取（抄表）	 * @param tGbId 小区id	 * @param tRealRoomId 真实房间id	 * @param tBillCycleId 账期id	 * @param targetId 费用项id(常规类型对应t_fixed_fee_item_has_room的f_id)	 * @param tMrStandardRoomId 房间收费标准ID（抄表	 * @param multiplierTimes 抄表倍率	 * @param lastUnpaid 抄表往月欠费（单位	 * @param billMonthStart 物业费用起始时间（暂时只针对选择周期使用）	 * @param createBillMonth 费用账单已经生成到的月份（截止月份）	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PropertyFeeDetailTemp(BigInteger id,Integer type,String name,String mrName,Double price,Double totalAmount,Double signalPrice,String signalPriceStr,Long priceUnitValue,Double nowValue,Double priorValue,BigInteger tGbId,BigInteger tRealRoomId,BigInteger tBillCycleId,BigInteger targetId,BigInteger tMrStandardRoomId,Double multiplierTimes,Long lastUnpaid,String billMonthStart,String createBillMonth,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.type = type;		this.name = name;		this.mrName = mrName;		this.price = price;		this.totalAmount = totalAmount;		this.signalPrice = signalPrice;		this.signalPriceStr = signalPriceStr;		this.priceUnitValue = priceUnitValue;		this.nowValue = nowValue;		this.priorValue = priorValue;		this.tGbId = tGbId;		this.tRealRoomId = tRealRoomId;		this.tBillCycleId = tBillCycleId;		this.targetId = targetId;		this.tMrStandardRoomId = tMrStandardRoomId;		this.multiplierTimes = multiplierTimes;		this.lastUnpaid = lastUnpaid;		this.billMonthStart = billMonthStart;		this.createBillMonth = createBillMonth;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("mrName=").append(mrName).append(";");		sbf.append("price=").append(price).append(";");		sbf.append("totalAmount=").append(totalAmount).append(";");		sbf.append("signalPrice=").append(signalPrice).append(";");		sbf.append("signalPriceStr=").append(signalPriceStr).append(";");		sbf.append("priceUnitValue=").append(priceUnitValue).append(";");		sbf.append("nowValue=").append(nowValue).append(";");		sbf.append("priorValue=").append(priorValue).append(";");		sbf.append("tGbId=").append(tGbId).append(";");		sbf.append("tRealRoomId=").append(tRealRoomId).append(";");		sbf.append("tBillCycleId=").append(tBillCycleId).append(";");		sbf.append("targetId=").append(targetId).append(";");		sbf.append("tMrStandardRoomId=").append(tMrStandardRoomId).append(";");		sbf.append("multiplierTimes=").append(multiplierTimes).append(";");		sbf.append("lastUnpaid=").append(lastUnpaid).append(";");		sbf.append("billMonthStart=").append(billMonthStart).append(";");		sbf.append("createBillMonth=").append(createBillMonth).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getType() {		return type;	}	public void setType(Integer type) {		this.type = type;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getMrName() {		return mrName;	}	public void setMrName(String mrName) {		this.mrName = mrName;	}	public Double getPrice() {		return price;	}	public void setPrice(Double price) {		this.price = price;	}	public Double getTotalAmount() {		return totalAmount;	}	public void setTotalAmount(Double totalAmount) {		this.totalAmount = totalAmount;	}	public Double getSignalPrice() {		return signalPrice;	}	public void setSignalPrice(Double signalPrice) {		this.signalPrice = signalPrice;	}	public String getSignalPriceStr() {		return signalPriceStr;	}	public void setSignalPriceStr(String signalPriceStr) {		this.signalPriceStr = signalPriceStr;	}	public Long getPriceUnitValue() {		return priceUnitValue;	}	public void setPriceUnitValue(Long priceUnitValue) {		this.priceUnitValue = priceUnitValue;	}	public Double getNowValue() {		return nowValue;	}	public void setNowValue(Double nowValue) {		this.nowValue = nowValue;	}	public Double getPriorValue() {		return priorValue;	}	public void setPriorValue(Double priorValue) {		this.priorValue = priorValue;	}	public BigInteger gettGbId() {		return tGbId;	}	public void settGbId(BigInteger tGbId) {		this.tGbId = tGbId;	}	public BigInteger gettRealRoomId() {		return tRealRoomId;	}	public void settRealRoomId(BigInteger tRealRoomId) {		this.tRealRoomId = tRealRoomId;	}	public BigInteger gettBillCycleId() {		return tBillCycleId;	}	public void settBillCycleId(BigInteger tBillCycleId) {		this.tBillCycleId = tBillCycleId;	}	public BigInteger getTargetId() {		return targetId;	}	public void setTargetId(BigInteger targetId) {		this.targetId = targetId;	}	public BigInteger gettMrStandardRoomId() {		return tMrStandardRoomId;	}	public void settMrStandardRoomId(BigInteger tMrStandardRoomId) {		this.tMrStandardRoomId = tMrStandardRoomId;	}	public Double getMultiplierTimes() {		return multiplierTimes;	}	public void setMultiplierTimes(Double multiplierTimes) {		this.multiplierTimes = multiplierTimes;	}	public Long getLastUnpaid() {		return lastUnpaid;	}	public void setLastUnpaid(Long lastUnpaid) {		this.lastUnpaid = lastUnpaid;	}	public String getBillMonthStart() {		return billMonthStart;	}	public void setBillMonthStart(String billMonthStart) {		this.billMonthStart = billMonthStart;	}	public String getCreateBillMonth() {		return createBillMonth;	}	public void setCreateBillMonth(String createBillMonth) {		this.createBillMonth = createBillMonth;	}
	
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
		PropertyFeeDetailTemp other = (PropertyFeeDetailTemp) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
