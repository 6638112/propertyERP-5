package com.cnfantasia.server.domainbase.refundRecord.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(退款记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class RefundRecord implements Serializable{
*/


public class RefundRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 退款时间 */	private String refundTime;	/** 有效时间 */	private String endTime;	/** 用户与门牌 */	private BigInteger tUserHasTRoomFId;	/** 用户id */	private BigInteger tUserFId;	/** 退款订单id */	private BigInteger tRefundOrderFId;	/** 使用状态 */	private Integer status;	/** 兑换的金额 */	private Long savedMoney;	/** 使用的时间 */	private String usedTime;
	public RefundRecord(){
	}
	public RefundRecord(RefundRecord arg){
		this.id = arg.getId();		this.refundTime = arg.getRefundTime();		this.endTime = arg.getEndTime();		this.tUserHasTRoomFId = arg.gettUserHasTRoomFId();		this.tUserFId = arg.gettUserFId();		this.tRefundOrderFId = arg.gettRefundOrderFId();		this.status = arg.getStatus();		this.savedMoney = arg.getSavedMoney();		this.usedTime = arg.getUsedTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param refundTime 退款时间	 * @param endTime 有效时间	 * @param tUserHasTRoomFId 用户与门牌	 * @param tUserFId 用户id	 * @param tRefundOrderFId 退款订单id	 * @param status 使用状态	 * @param savedMoney 兑换的金额	 * @param usedTime 使用的时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public RefundRecord(BigInteger id,String refundTime,String endTime,BigInteger tUserHasTRoomFId,BigInteger tUserFId,BigInteger tRefundOrderFId,Integer status,Long savedMoney,String usedTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.refundTime = refundTime;		this.endTime = endTime;		this.tUserHasTRoomFId = tUserHasTRoomFId;		this.tUserFId = tUserFId;		this.tRefundOrderFId = tRefundOrderFId;		this.status = status;		this.savedMoney = savedMoney;		this.usedTime = usedTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("refundTime=").append(refundTime).append(";");		sbf.append("endTime=").append(endTime).append(";");		sbf.append("tUserHasTRoomFId=").append(tUserHasTRoomFId).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("tRefundOrderFId=").append(tRefundOrderFId).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("savedMoney=").append(savedMoney).append(";");		sbf.append("usedTime=").append(usedTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getRefundTime() {		return refundTime;	}	public void setRefundTime(String refundTime) {		this.refundTime = refundTime;	}	public String getEndTime() {		return endTime;	}	public void setEndTime(String endTime) {		this.endTime = endTime;	}	public BigInteger gettUserHasTRoomFId() {		return tUserHasTRoomFId;	}	public void settUserHasTRoomFId(BigInteger tUserHasTRoomFId) {		this.tUserHasTRoomFId = tUserHasTRoomFId;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public BigInteger gettRefundOrderFId() {		return tRefundOrderFId;	}	public void settRefundOrderFId(BigInteger tRefundOrderFId) {		this.tRefundOrderFId = tRefundOrderFId;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public Long getSavedMoney() {		return savedMoney;	}	public void setSavedMoney(Long savedMoney) {		this.savedMoney = savedMoney;	}	public String getUsedTime() {		return usedTime;	}	public void setUsedTime(String usedTime) {		this.usedTime = usedTime;	}
	
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
		RefundRecord other = (RefundRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
