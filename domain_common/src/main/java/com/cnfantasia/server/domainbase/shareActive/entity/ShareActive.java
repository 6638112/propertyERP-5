package com.cnfantasia.server.domainbase.shareActive.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(分享活动) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class ShareActive implements Serializable{
*/


public class ShareActive extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 分享者的ID */	private BigInteger tUserFId;	/** 分享时间 */	private String shareTime;	/** 分享的金额 */	private Long shareCashAmt;	/** 分享类型 */	private String type;
	public ShareActive(){
	}
	public ShareActive(ShareActive arg){
		this.id = arg.getId();		this.tUserFId = arg.gettUserFId();		this.shareTime = arg.getShareTime();		this.shareCashAmt = arg.getShareCashAmt();		this.type = arg.getType();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tUserFId 分享者的ID	 * @param shareTime 分享时间	 * @param shareCashAmt 分享的金额	 * @param type 分享类型	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public ShareActive(BigInteger id,BigInteger tUserFId,String shareTime,Long shareCashAmt,String type,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tUserFId = tUserFId;		this.shareTime = shareTime;		this.shareCashAmt = shareCashAmt;		this.type = type;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("shareTime=").append(shareTime).append(";");		sbf.append("shareCashAmt=").append(shareCashAmt).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public String getShareTime() {		return shareTime;	}	public void setShareTime(String shareTime) {		this.shareTime = shareTime;	}	public Long getShareCashAmt() {		return shareCashAmt;	}	public void setShareCashAmt(Long shareCashAmt) {		this.shareCashAmt = shareCashAmt;	}	public String getType() {		return type;	}	public void setType(String type) {		this.type = type;	}
	
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
		ShareActive other = (ShareActive) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
