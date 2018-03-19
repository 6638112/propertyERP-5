package com.cnfantasia.server.domainbase.carPrefer.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;
import java.lang.Long;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(车禁随机立减) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CarPrefer implements Serializable{
*/


public class CarPrefer extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/** 解放号 */
	private BigInteger tUserFId;
	/** 车牌id */
	private String tCarNumListFId;
	/** 小区id */
	private BigInteger tGroupBuildingFId;
	/** 缴费类型 */
	private Integer payType;
	/** 月卡缴费月数 */
	private Long payMonth;
	/** 总优惠金额（单位 */
	private Long amount;
	/** 优惠金额（单位 */
	private Long couponAmount;
	/** 运营策略时间 */
	private String operationDate;

	public CarPrefer(){
	}
	public CarPrefer(CarPrefer arg){
		this.id = arg.getId();
		this.tUserFId = arg.gettUserFId();
		this.tCarNumListFId = arg.gettCarNumListFId();
		this.tGroupBuildingFId = arg.gettGroupBuildingFId();
		this.payType = arg.getPayType();
		this.payMonth = arg.getPayMonth();
		this.amount = arg.getAmount();
		this.couponAmount = arg.getCouponAmount();
		this.operationDate = arg.getOperationDate();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();

	}
	/**
	 * 
	 * @param id 
	 * @param tUserFId 解放号
	 * @param tCarNumListFId 车牌id
	 * @param tGroupBuildingFId 小区id
	 * @param payType 缴费类型
	 * @param payMonth 月卡缴费月数
	 * @param amount 总优惠金额（单位
	 * @param couponAmount 优惠金额（单位
	 * @param operationDate 运营策略时间
	 * @param sys0AddTime 
	 * @param sys0UpdTime 
	 * @param sys0DelTime 
	 * @param sys0AddUser 
	 * @param sys0UpdUser 
	 * @param sys0DelUser 
	 * @param sys0DelState 记录状态
	 */

	public CarPrefer(BigInteger id,BigInteger tUserFId,String tCarNumListFId,BigInteger tGroupBuildingFId,Integer payType,Long payMonth,Long amount,Long couponAmount,String operationDate,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.tUserFId = tUserFId;
		this.tCarNumListFId = tCarNumListFId;
		this.tGroupBuildingFId = tGroupBuildingFId;
		this.payType = payType;
		this.payMonth = payMonth;
		this.amount = amount;
		this.couponAmount = couponAmount;
		this.operationDate = operationDate;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("tUserFId=").append(tUserFId).append(";");
		sbf.append("tCarNumListFId=").append(tCarNumListFId).append(";");
		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");
		sbf.append("payType=").append(payType).append(";");
		sbf.append("payMonth=").append(payMonth).append(";");
		sbf.append("amount=").append(amount).append(";");
		sbf.append("couponAmount=").append(couponAmount).append(";");
		sbf.append("operationDate=").append(operationDate).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger gettUserFId() {
		return tUserFId;
	}
	public void settUserFId(BigInteger tUserFId) {
		this.tUserFId = tUserFId;
	}
	public String gettCarNumListFId() {
		return tCarNumListFId;
	}
	public void settCarNumListFId(String tCarNumListFId) {
		this.tCarNumListFId = tCarNumListFId;
	}
	public BigInteger gettGroupBuildingFId() {
		return tGroupBuildingFId;
	}
	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {
		this.tGroupBuildingFId = tGroupBuildingFId;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Long getPayMonth() {
		return payMonth;
	}
	public void setPayMonth(Long payMonth) {
		this.payMonth = payMonth;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(Long couponAmount) {
		this.couponAmount = couponAmount;
	}
	public String getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}

	
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
		CarPrefer other = (CarPrefer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
