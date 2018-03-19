package com.cnfantasia.server.domainbase.communityExchangeRelation.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(换物关系表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommunityExchangeRelation implements Serializable{
*/


public class CommunityExchangeRelation extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 发起的换物Id */	private BigInteger launchExchgId;	/** 参与的换物Id */	private BigInteger joinExchgId;	/** 关系创建者Id */	private BigInteger createrId;	/** 交换状态 */	private Integer status;	/** 关系建立时间 */	private String createTime;	/** 确认交换时间 */	private String confirmTime;
	public CommunityExchangeRelation(){
	}
	public CommunityExchangeRelation(CommunityExchangeRelation arg){
		this.id = arg.getId();		this.launchExchgId = arg.getLaunchExchgId();		this.joinExchgId = arg.getJoinExchgId();		this.createrId = arg.getCreaterId();		this.status = arg.getStatus();		this.createTime = arg.getCreateTime();		this.confirmTime = arg.getConfirmTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param launchExchgId 发起的换物Id	 * @param joinExchgId 参与的换物Id	 * @param createrId 关系创建者Id	 * @param status 交换状态	 * @param createTime 关系建立时间	 * @param confirmTime 确认交换时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CommunityExchangeRelation(BigInteger id,BigInteger launchExchgId,BigInteger joinExchgId,BigInteger createrId,Integer status,String createTime,String confirmTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.launchExchgId = launchExchgId;		this.joinExchgId = joinExchgId;		this.createrId = createrId;		this.status = status;		this.createTime = createTime;		this.confirmTime = confirmTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("launchExchgId=").append(launchExchgId).append(";");		sbf.append("joinExchgId=").append(joinExchgId).append(";");		sbf.append("createrId=").append(createrId).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("createTime=").append(createTime).append(";");		sbf.append("confirmTime=").append(confirmTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getLaunchExchgId() {		return launchExchgId;	}	public void setLaunchExchgId(BigInteger launchExchgId) {		this.launchExchgId = launchExchgId;	}	public BigInteger getJoinExchgId() {		return joinExchgId;	}	public void setJoinExchgId(BigInteger joinExchgId) {		this.joinExchgId = joinExchgId;	}	public BigInteger getCreaterId() {		return createrId;	}	public void setCreaterId(BigInteger createrId) {		this.createrId = createrId;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}	public String getConfirmTime() {		return confirmTime;	}	public void setConfirmTime(String confirmTime) {		this.confirmTime = confirmTime;	}
	
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
		CommunityExchangeRelation other = (CommunityExchangeRelation) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
