package com.cnfantasia.server.domainbase.surpriseGiftUseDetail.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(意外惊喜的优惠券使用详情) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class SurpriseGiftUseDetail implements Serializable{
*/


public class SurpriseGiftUseDetail extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tPayCouponFId;	/** 对应优惠券使用的金额 */	private Long amount;	/** 意外惊喜记录Id */	private BigInteger surpriseGiftId;	/**  */	private BigInteger userId;	/** 使用时间 */	private String consumTime;
	public SurpriseGiftUseDetail(){
	}
	public SurpriseGiftUseDetail(SurpriseGiftUseDetail arg){
		this.id = arg.getId();		this.tPayCouponFId = arg.gettPayCouponFId();		this.amount = arg.getAmount();		this.surpriseGiftId = arg.getSurpriseGiftId();		this.userId = arg.getUserId();		this.consumTime = arg.getConsumTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tPayCouponFId 	 * @param amount 对应优惠券使用的金额	 * @param surpriseGiftId 意外惊喜记录Id	 * @param userId 	 * @param consumTime 使用时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public SurpriseGiftUseDetail(BigInteger id,BigInteger tPayCouponFId,Long amount,BigInteger surpriseGiftId,BigInteger userId,String consumTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tPayCouponFId = tPayCouponFId;		this.amount = amount;		this.surpriseGiftId = surpriseGiftId;		this.userId = userId;		this.consumTime = consumTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tPayCouponFId=").append(tPayCouponFId).append(";");		sbf.append("amount=").append(amount).append(";");		sbf.append("surpriseGiftId=").append(surpriseGiftId).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("consumTime=").append(consumTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettPayCouponFId() {		return tPayCouponFId;	}	public void settPayCouponFId(BigInteger tPayCouponFId) {		this.tPayCouponFId = tPayCouponFId;	}	public Long getAmount() {		return amount;	}	public void setAmount(Long amount) {		this.amount = amount;	}	public BigInteger getSurpriseGiftId() {		return surpriseGiftId;	}	public void setSurpriseGiftId(BigInteger surpriseGiftId) {		this.surpriseGiftId = surpriseGiftId;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public String getConsumTime() {		return consumTime;	}	public void setConsumTime(String consumTime) {		this.consumTime = consumTime;	}
	
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
		SurpriseGiftUseDetail other = (SurpriseGiftUseDetail) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
