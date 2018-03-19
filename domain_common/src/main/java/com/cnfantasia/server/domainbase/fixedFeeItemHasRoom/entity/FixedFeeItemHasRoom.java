package com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(固定收费项和房间关联信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class FixedFeeItemHasRoom implements Serializable{
*/


public class FixedFeeItemHasRoom extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 收费项id */	private BigInteger tFixedFeeItemId;	/** 冗余收费项 */	private String name;	/** 单价(分)指定金额不能填写 */	private Long signalPrice;	/** 计价单位取值 */	private Long priceUnitValue;	/** 费用合计(分) */	private Long totalPrice;	/** 真实房间id */	private BigInteger tFixedFeeDataId;	/** 物业费用起始时间（暂时只针对选择周期使用） */	private String billMonthStart;	/** 费用账单已经生成到的月份（截止月份） */	private String createBillMonth;
	public FixedFeeItemHasRoom(){
	}
	public FixedFeeItemHasRoom(FixedFeeItemHasRoom arg){
		this.id = arg.getId();		this.tFixedFeeItemId = arg.gettFixedFeeItemId();		this.name = arg.getName();		this.signalPrice = arg.getSignalPrice();		this.priceUnitValue = arg.getPriceUnitValue();		this.totalPrice = arg.getTotalPrice();		this.tFixedFeeDataId = arg.gettFixedFeeDataId();		this.billMonthStart = arg.getBillMonthStart();		this.createBillMonth = arg.getCreateBillMonth();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tFixedFeeItemId 收费项id	 * @param name 冗余收费项	 * @param signalPrice 单价(分)指定金额不能填写	 * @param priceUnitValue 计价单位取值	 * @param totalPrice 费用合计(分)	 * @param tFixedFeeDataId 真实房间id	 * @param billMonthStart 物业费用起始时间（暂时只针对选择周期使用）	 * @param createBillMonth 费用账单已经生成到的月份（截止月份）	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public FixedFeeItemHasRoom(BigInteger id,BigInteger tFixedFeeItemId,String name,Long signalPrice,Long priceUnitValue,Long totalPrice,BigInteger tFixedFeeDataId,String billMonthStart,String createBillMonth,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tFixedFeeItemId = tFixedFeeItemId;		this.name = name;		this.signalPrice = signalPrice;		this.priceUnitValue = priceUnitValue;		this.totalPrice = totalPrice;		this.tFixedFeeDataId = tFixedFeeDataId;		this.billMonthStart = billMonthStart;		this.createBillMonth = createBillMonth;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tFixedFeeItemId=").append(tFixedFeeItemId).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("signalPrice=").append(signalPrice).append(";");		sbf.append("priceUnitValue=").append(priceUnitValue).append(";");		sbf.append("totalPrice=").append(totalPrice).append(";");		sbf.append("tFixedFeeDataId=").append(tFixedFeeDataId).append(";");		sbf.append("billMonthStart=").append(billMonthStart).append(";");		sbf.append("createBillMonth=").append(createBillMonth).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettFixedFeeItemId() {		return tFixedFeeItemId;	}	public void settFixedFeeItemId(BigInteger tFixedFeeItemId) {		this.tFixedFeeItemId = tFixedFeeItemId;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public Long getSignalPrice() {		return signalPrice;	}	public void setSignalPrice(Long signalPrice) {		this.signalPrice = signalPrice;	}	public Long getPriceUnitValue() {		return priceUnitValue;	}	public void setPriceUnitValue(Long priceUnitValue) {		this.priceUnitValue = priceUnitValue;	}	public Long getTotalPrice() {		return totalPrice;	}	public void setTotalPrice(Long totalPrice) {		this.totalPrice = totalPrice;	}	public BigInteger gettFixedFeeDataId() {		return tFixedFeeDataId;	}	public void settFixedFeeDataId(BigInteger tFixedFeeDataId) {		this.tFixedFeeDataId = tFixedFeeDataId;	}	public String getBillMonthStart() {		return billMonthStart;	}	public void setBillMonthStart(String billMonthStart) {		this.billMonthStart = billMonthStart;	}	public String getCreateBillMonth() {		return createBillMonth;	}	public void setCreateBillMonth(String createBillMonth) {		this.createBillMonth = createBillMonth;	}
	
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
		FixedFeeItemHasRoom other = (FixedFeeItemHasRoom) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
