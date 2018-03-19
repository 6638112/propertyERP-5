package com.cnfantasia.server.domainbase.alterRoomHasFeeItem.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(房间收费项表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class AlterRoomHasFeeItem implements Serializable{
*/


public class AlterRoomHasFeeItem extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 真实房间id */	private BigInteger tRealRoomId;	/** 收费项id */	private BigInteger tAlterPeriodFeeItemId;	/** 收费项金额（分） */	private Long amount;
	public AlterRoomHasFeeItem(){
	}
	public AlterRoomHasFeeItem(AlterRoomHasFeeItem arg){
		this.id = arg.getId();		this.tRealRoomId = arg.gettRealRoomId();		this.tAlterPeriodFeeItemId = arg.gettAlterPeriodFeeItemId();		this.amount = arg.getAmount();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tRealRoomId 真实房间id	 * @param tAlterPeriodFeeItemId 收费项id	 * @param amount 收费项金额（分）	 * @param sys0AddTime 	 * @param sys0UpdTime 	 * @param sys0DelTime 	 * @param sys0AddUser 	 * @param sys0UpdUser 	 * @param sys0DelUser 	 * @param sys0DelState 记录状态	 */
	public AlterRoomHasFeeItem(BigInteger id,BigInteger tRealRoomId,BigInteger tAlterPeriodFeeItemId,Long amount,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tRealRoomId = tRealRoomId;		this.tAlterPeriodFeeItemId = tAlterPeriodFeeItemId;		this.amount = amount;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tRealRoomId=").append(tRealRoomId).append(";");		sbf.append("tAlterPeriodFeeItemId=").append(tAlterPeriodFeeItemId).append(";");		sbf.append("amount=").append(amount).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettRealRoomId() {		return tRealRoomId;	}	public void settRealRoomId(BigInteger tRealRoomId) {		this.tRealRoomId = tRealRoomId;	}	public BigInteger gettAlterPeriodFeeItemId() {		return tAlterPeriodFeeItemId;	}	public void settAlterPeriodFeeItemId(BigInteger tAlterPeriodFeeItemId) {		this.tAlterPeriodFeeItemId = tAlterPeriodFeeItemId;	}	public Long getAmount() {		return amount;	}	public void setAmount(Long amount) {		this.amount = amount;	}
	
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
		AlterRoomHasFeeItem other = (AlterRoomHasFeeItem) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
