package com.cnfantasia.server.domainbase.ebuySupplyMerchantHasGroupBuilding.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(供应商与小区关联表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuySupplyMerchantHasGroupBuilding implements Serializable{
*/


public class EbuySupplyMerchantHasGroupBuilding extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tEbuySupplyMerchantId;	/**  */	private BigInteger tGroupBuildingId;	/**  */	private BigInteger tAddressCityId;	/** 距离 */	private Long distance;	/** 到达时间 */	private Long deliveTime;
	public EbuySupplyMerchantHasGroupBuilding(){
	}
	public EbuySupplyMerchantHasGroupBuilding(EbuySupplyMerchantHasGroupBuilding arg){
		this.id = arg.getId();		this.tEbuySupplyMerchantId = arg.gettEbuySupplyMerchantId();		this.tGroupBuildingId = arg.gettGroupBuildingId();		this.tAddressCityId = arg.gettAddressCityId();		this.distance = arg.getDistance();		this.deliveTime = arg.getDeliveTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tEbuySupplyMerchantId 	 * @param tGroupBuildingId 	 * @param tAddressCityId 	 * @param distance 距离	 * @param deliveTime 到达时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuySupplyMerchantHasGroupBuilding(BigInteger id,BigInteger tEbuySupplyMerchantId,BigInteger tGroupBuildingId,BigInteger tAddressCityId,Long distance,Long deliveTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tEbuySupplyMerchantId = tEbuySupplyMerchantId;		this.tGroupBuildingId = tGroupBuildingId;		this.tAddressCityId = tAddressCityId;		this.distance = distance;		this.deliveTime = deliveTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tEbuySupplyMerchantId=").append(tEbuySupplyMerchantId).append(";");		sbf.append("tGroupBuildingId=").append(tGroupBuildingId).append(";");		sbf.append("tAddressCityId=").append(tAddressCityId).append(";");		sbf.append("distance=").append(distance).append(";");		sbf.append("deliveTime=").append(deliveTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettEbuySupplyMerchantId() {		return tEbuySupplyMerchantId;	}	public void settEbuySupplyMerchantId(BigInteger tEbuySupplyMerchantId) {		this.tEbuySupplyMerchantId = tEbuySupplyMerchantId;	}	public BigInteger gettGroupBuildingId() {		return tGroupBuildingId;	}	public void settGroupBuildingId(BigInteger tGroupBuildingId) {		this.tGroupBuildingId = tGroupBuildingId;	}	public BigInteger gettAddressCityId() {		return tAddressCityId;	}	public void settAddressCityId(BigInteger tAddressCityId) {		this.tAddressCityId = tAddressCityId;	}	public Long getDistance() {		return distance;	}	public void setDistance(Long distance) {		this.distance = distance;	}	public Long getDeliveTime() {		return deliveTime;	}	public void setDeliveTime(Long deliveTime) {		this.deliveTime = deliveTime;	}
	
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
		EbuySupplyMerchantHasGroupBuilding other = (EbuySupplyMerchantHasGroupBuilding) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
