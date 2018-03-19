package com.cnfantasia.server.domainbase.dredgeBillOtherInfo.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(维修单的与第三方供应商的信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class DredgeBillOtherInfo implements Serializable{
*/


public class DredgeBillOtherInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 第三方服务商的订单编码 */	private String serviceSplOrderNum;	/**  */	private BigInteger tDredgeBillFId;	/**  */	private BigInteger tDredge3rdSplFId;
	public DredgeBillOtherInfo(){
	}
	public DredgeBillOtherInfo(DredgeBillOtherInfo arg){
		this.id = arg.getId();		this.serviceSplOrderNum = arg.getServiceSplOrderNum();		this.tDredgeBillFId = arg.gettDredgeBillFId();		this.tDredge3rdSplFId = arg.gettDredge3rdSplFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param serviceSplOrderNum 第三方服务商的订单编码	 * @param tDredgeBillFId 	 * @param tDredge3rdSplFId 	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public DredgeBillOtherInfo(BigInteger id,String serviceSplOrderNum,BigInteger tDredgeBillFId,BigInteger tDredge3rdSplFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.serviceSplOrderNum = serviceSplOrderNum;		this.tDredgeBillFId = tDredgeBillFId;		this.tDredge3rdSplFId = tDredge3rdSplFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("serviceSplOrderNum=").append(serviceSplOrderNum).append(";");		sbf.append("tDredgeBillFId=").append(tDredgeBillFId).append(";");		sbf.append("tDredge3rdSplFId=").append(tDredge3rdSplFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getServiceSplOrderNum() {		return serviceSplOrderNum;	}	public void setServiceSplOrderNum(String serviceSplOrderNum) {		this.serviceSplOrderNum = serviceSplOrderNum;	}	public BigInteger gettDredgeBillFId() {		return tDredgeBillFId;	}	public void settDredgeBillFId(BigInteger tDredgeBillFId) {		this.tDredgeBillFId = tDredgeBillFId;	}	public BigInteger gettDredge3rdSplFId() {		return tDredge3rdSplFId;	}	public void settDredge3rdSplFId(BigInteger tDredge3rdSplFId) {		this.tDredge3rdSplFId = tDredge3rdSplFId;	}
	
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
		DredgeBillOtherInfo other = (DredgeBillOtherInfo) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
