package com.cnfantasia.server.domainbase.cloudKeyPaylog.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(云钥匙付款记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CloudKeyPaylog implements Serializable{
*/


public class CloudKeyPaylog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 付款编号 */	private BigInteger tEbuyOrderId;	/** 付费列表Id */	private BigInteger tPayKeyListId;	/** 购买人id */	private BigInteger huaId;	/** 购买时间 */	private String createTime;	/** 是否过期 */	private Integer status;	/** 付款状态 */	private Integer payStatus;
	public CloudKeyPaylog(){
	}
	public CloudKeyPaylog(CloudKeyPaylog arg){
		this.id = arg.getId();		this.tEbuyOrderId = arg.gettEbuyOrderId();		this.tPayKeyListId = arg.gettPayKeyListId();		this.huaId = arg.getHuaId();		this.createTime = arg.getCreateTime();		this.status = arg.getStatus();		this.payStatus = arg.getPayStatus();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tEbuyOrderId 付款编号	 * @param tPayKeyListId 付费列表Id	 * @param huaId 购买人id	 * @param createTime 购买时间	 * @param status 是否过期	 * @param payStatus 付款状态	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CloudKeyPaylog(BigInteger id,BigInteger tEbuyOrderId,BigInteger tPayKeyListId,BigInteger huaId,String createTime,Integer status,Integer payStatus,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tEbuyOrderId = tEbuyOrderId;		this.tPayKeyListId = tPayKeyListId;		this.huaId = huaId;		this.createTime = createTime;		this.status = status;		this.payStatus = payStatus;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tEbuyOrderId=").append(tEbuyOrderId).append(";");		sbf.append("tPayKeyListId=").append(tPayKeyListId).append(";");		sbf.append("huaId=").append(huaId).append(";");		sbf.append("createTime=").append(createTime).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("payStatus=").append(payStatus).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettEbuyOrderId() {		return tEbuyOrderId;	}	public void settEbuyOrderId(BigInteger tEbuyOrderId) {		this.tEbuyOrderId = tEbuyOrderId;	}	public BigInteger gettPayKeyListId() {		return tPayKeyListId;	}	public void settPayKeyListId(BigInteger tPayKeyListId) {		this.tPayKeyListId = tPayKeyListId;	}	public BigInteger getHuaId() {		return huaId;	}	public void setHuaId(BigInteger huaId) {		this.huaId = huaId;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public Integer getPayStatus() {		return payStatus;	}	public void setPayStatus(Integer payStatus) {		this.payStatus = payStatus;	}
	
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
		CloudKeyPaylog other = (CloudKeyPaylog) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
