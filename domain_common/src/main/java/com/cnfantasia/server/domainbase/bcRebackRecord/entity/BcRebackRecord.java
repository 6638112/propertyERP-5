package com.cnfantasia.server.domainbase.bcRebackRecord.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(回盘记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class BcRebackRecord implements Serializable{
*/


public class BcRebackRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 账单id */	private BigInteger paybillId;	/** 回盘信息 */	private String rebackContent;	/** 回盘时间 */	private String rebackTime;	/** 回盘结果={1成功; 2未找到账单;3未找到账单;4金额不匹配} */	private Integer status;	/**  */	private BigInteger tBcGroupBuildingCycleFId;
	public BcRebackRecord(){
	}
	public BcRebackRecord(BcRebackRecord arg){
		this.id = arg.getId();		this.paybillId = arg.getPaybillId();		this.rebackContent = arg.getRebackContent();		this.rebackTime = arg.getRebackTime();		this.status = arg.getStatus();		this.tBcGroupBuildingCycleFId = arg.gettBcGroupBuildingCycleFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param paybillId 账单id	 * @param rebackContent 回盘信息	 * @param rebackTime 回盘时间	 * @param status 回盘结果={1成功; 2未找到账单;3未找到账单;4金额不匹配}	 * @param tBcGroupBuildingCycleFId 	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public BcRebackRecord(BigInteger id,BigInteger paybillId,String rebackContent,String rebackTime,Integer status,BigInteger tBcGroupBuildingCycleFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.paybillId = paybillId;		this.rebackContent = rebackContent;		this.rebackTime = rebackTime;		this.status = status;		this.tBcGroupBuildingCycleFId = tBcGroupBuildingCycleFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("paybillId=").append(paybillId).append(";");		sbf.append("rebackContent=").append(rebackContent).append(";");		sbf.append("rebackTime=").append(rebackTime).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("tBcGroupBuildingCycleFId=").append(tBcGroupBuildingCycleFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getPaybillId() {		return paybillId;	}	public void setPaybillId(BigInteger paybillId) {		this.paybillId = paybillId;	}	public String getRebackContent() {		return rebackContent;	}	public void setRebackContent(String rebackContent) {		this.rebackContent = rebackContent;	}	public String getRebackTime() {		return rebackTime;	}	public void setRebackTime(String rebackTime) {		this.rebackTime = rebackTime;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public BigInteger gettBcGroupBuildingCycleFId() {		return tBcGroupBuildingCycleFId;	}	public void settBcGroupBuildingCycleFId(BigInteger tBcGroupBuildingCycleFId) {		this.tBcGroupBuildingCycleFId = tBcGroupBuildingCycleFId;	}
	
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
		BcRebackRecord other = (BcRebackRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
