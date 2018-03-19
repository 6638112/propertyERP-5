package com.cnfantasia.server.domainbase.couponExchange.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(消费券兑换表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CouponExchange implements Serializable{
*/


public class CouponExchange extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** couponId */	private BigInteger couponId;	/** 兑换码 */	private String exchangeCode;	/** 状态，1 未兑换， 2 已兑换 */	private Integer status;	/** 领取的用户ID */	private BigInteger userId;
	public CouponExchange(){
	}
	public CouponExchange(CouponExchange arg){
		this.id = arg.getId();		this.couponId = arg.getCouponId();		this.exchangeCode = arg.getExchangeCode();		this.status = arg.getStatus();		this.userId = arg.getUserId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param couponId couponId	 * @param exchangeCode 兑换码	 * @param status 状态，1 未兑换， 2 已兑换	 * @param userId 领取的用户ID	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 删除状态	 */
	public CouponExchange(BigInteger id,BigInteger couponId,String exchangeCode,Integer status,BigInteger userId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.couponId = couponId;		this.exchangeCode = exchangeCode;		this.status = status;		this.userId = userId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("couponId=").append(couponId).append(";");		sbf.append("exchangeCode=").append(exchangeCode).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getCouponId() {		return couponId;	}	public void setCouponId(BigInteger couponId) {		this.couponId = couponId;	}	public String getExchangeCode() {		return exchangeCode;	}	public void setExchangeCode(String exchangeCode) {		this.exchangeCode = exchangeCode;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}
	
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
		CouponExchange other = (CouponExchange) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
