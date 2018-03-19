package com.cnfantasia.server.domainbase.cloudKeyUserList.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(业主门禁密钥表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CloudKeyUserList implements Serializable{
*/


public class CloudKeyUserList extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 购买人解放号 */	private BigInteger huaId;	/** 楼栋Id */	private BigInteger tBuildingFId;	/** 小区Id */	private BigInteger tGroupBuildingFId;	/** 门禁楼栋列表Id */	private BigInteger tBuildingKeyFId;	/** 是否付费 */	private Integer payStatus;	/** 是否过期 */	private Integer status;
	public CloudKeyUserList(){
	}
	public CloudKeyUserList(CloudKeyUserList arg){
		this.id = arg.getId();		this.huaId = arg.getHuaId();		this.tBuildingFId = arg.gettBuildingFId();		this.tGroupBuildingFId = arg.gettGroupBuildingFId();		this.tBuildingKeyFId = arg.gettBuildingKeyFId();		this.payStatus = arg.getPayStatus();		this.status = arg.getStatus();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param huaId 购买人解放号	 * @param tBuildingFId 楼栋Id	 * @param tGroupBuildingFId 小区Id	 * @param tBuildingKeyFId 门禁楼栋列表Id	 * @param payStatus 是否付费	 * @param status 是否过期	 * @param sys0AddTime 申请时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CloudKeyUserList(BigInteger id,BigInteger huaId,BigInteger tBuildingFId,BigInteger tGroupBuildingFId,BigInteger tBuildingKeyFId,Integer payStatus,Integer status,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.huaId = huaId;		this.tBuildingFId = tBuildingFId;		this.tGroupBuildingFId = tGroupBuildingFId;		this.tBuildingKeyFId = tBuildingKeyFId;		this.payStatus = payStatus;		this.status = status;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("huaId=").append(huaId).append(";");		sbf.append("tBuildingFId=").append(tBuildingFId).append(";");		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");		sbf.append("tBuildingKeyFId=").append(tBuildingKeyFId).append(";");		sbf.append("payStatus=").append(payStatus).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getHuaId() {		return huaId;	}	public void setHuaId(BigInteger huaId) {		this.huaId = huaId;	}	public BigInteger gettBuildingFId() {		return tBuildingFId;	}	public void settBuildingFId(BigInteger tBuildingFId) {		this.tBuildingFId = tBuildingFId;	}	public BigInteger gettGroupBuildingFId() {		return tGroupBuildingFId;	}	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {		this.tGroupBuildingFId = tGroupBuildingFId;	}	public BigInteger gettBuildingKeyFId() {		return tBuildingKeyFId;	}	public void settBuildingKeyFId(BigInteger tBuildingKeyFId) {		this.tBuildingKeyFId = tBuildingKeyFId;	}	public Integer getPayStatus() {		return payStatus;	}	public void setPayStatus(Integer payStatus) {		this.payStatus = payStatus;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}
	
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
		CloudKeyUserList other = (CloudKeyUserList) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
