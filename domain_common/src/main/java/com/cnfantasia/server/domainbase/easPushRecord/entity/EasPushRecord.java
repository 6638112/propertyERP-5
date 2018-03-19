package com.cnfantasia.server.domainbase.easPushRecord.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(EAS接口推送记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EasPushRecord implements Serializable{
*/


public class EasPushRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 推送时间 */	private String pushTime;	/** 推送返回结果 */	private String pushResponse;	/** 是否推送成功 */	private Integer isSuccess;	/** 提款单 */	private BigInteger tRevenueApplyFId;
	public EasPushRecord(){
	}
	public EasPushRecord(EasPushRecord arg){
		this.id = arg.getId();		this.pushTime = arg.getPushTime();		this.pushResponse = arg.getPushResponse();		this.isSuccess = arg.getIsSuccess();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.tRevenueApplyFId = arg.gettRevenueApplyFId();
	}
	/**	 * 	 * @param id 	 * @param pushTime 推送时间	 * @param pushResponse 推送返回结果	 * @param isSuccess 是否推送成功	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param tRevenueApplyFId 提款单	 */
	public EasPushRecord(BigInteger id,String pushTime,String pushResponse,Integer isSuccess,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger tRevenueApplyFId){
		this.id = id;		this.pushTime = pushTime;		this.pushResponse = pushResponse;		this.isSuccess = isSuccess;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.tRevenueApplyFId = tRevenueApplyFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("pushTime=").append(pushTime).append(";");		sbf.append("pushResponse=").append(pushResponse).append(";");		sbf.append("isSuccess=").append(isSuccess).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("tRevenueApplyFId=").append(tRevenueApplyFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getPushTime() {		return pushTime;	}	public void setPushTime(String pushTime) {		this.pushTime = pushTime;	}	public String getPushResponse() {		return pushResponse;	}	public void setPushResponse(String pushResponse) {		this.pushResponse = pushResponse;	}	public Integer getIsSuccess() {		return isSuccess;	}	public void setIsSuccess(Integer isSuccess) {		this.isSuccess = isSuccess;	}	public BigInteger gettRevenueApplyFId() {		return tRevenueApplyFId;	}	public void settRevenueApplyFId(BigInteger tRevenueApplyFId) {		this.tRevenueApplyFId = tRevenueApplyFId;	}
	
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
		EasPushRecord other = (EasPushRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
