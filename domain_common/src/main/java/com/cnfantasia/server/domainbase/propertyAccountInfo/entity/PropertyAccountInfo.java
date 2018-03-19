package com.cnfantasia.server.domainbase.propertyAccountInfo.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(用户物业账户信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyAccountInfo implements Serializable{
*/


public class PropertyAccountInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 账户余额 */	private Long balanceAmt;	/** 购买用户id */	private BigInteger tUserFId;	/** 接受信息的手机号 */	private String notifyPhone;	/** 是否首次充值 */	private Integer isfirstcharge;
	public PropertyAccountInfo(){
	}
	public PropertyAccountInfo(PropertyAccountInfo arg){
		this.id = arg.getId();		this.balanceAmt = arg.getBalanceAmt();		this.tUserFId = arg.gettUserFId();		this.notifyPhone = arg.getNotifyPhone();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.isfirstcharge = arg.getIsfirstcharge();
	}
	/**	 * 	 * @param id 	 * @param balanceAmt 账户余额	 * @param tUserFId 购买用户id	 * @param notifyPhone 接受信息的手机号	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param isfirstcharge 是否首次充值	 */
	public PropertyAccountInfo(BigInteger id,Long balanceAmt,BigInteger tUserFId,String notifyPhone,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,Integer isfirstcharge){
		this.id = id;		this.balanceAmt = balanceAmt;		this.tUserFId = tUserFId;		this.notifyPhone = notifyPhone;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.isfirstcharge = isfirstcharge;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("balanceAmt=").append(balanceAmt).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("notifyPhone=").append(notifyPhone).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("isfirstcharge=").append(isfirstcharge).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Long getBalanceAmt() {		return balanceAmt;	}	public void setBalanceAmt(Long balanceAmt) {		this.balanceAmt = balanceAmt;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public String getNotifyPhone() {		return notifyPhone;	}	public void setNotifyPhone(String notifyPhone) {		this.notifyPhone = notifyPhone;	}	public Integer getIsfirstcharge() {		return isfirstcharge;	}	public void setIsfirstcharge(Integer isfirstcharge) {		this.isfirstcharge = isfirstcharge;	}
	
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
		PropertyAccountInfo other = (PropertyAccountInfo) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
