package com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.Double;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(小区商家关系表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class GroupBuildingHasTCommunitySupply implements Serializable{
*/


public class GroupBuildingHasTCommunitySupply extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 小区Id */	private BigInteger tGroupBuildingFId;	/** 商家Id */	private BigInteger tCommunitySupplyFId;	/** 排序,降序,大的排前面 */	private Integer order;	/** 小区与商家的距离,米 */	private Double distance;	/**  */	private BigInteger tCommunitySupplyCompanyFId;
	public GroupBuildingHasTCommunitySupply(){
	}
	public GroupBuildingHasTCommunitySupply(GroupBuildingHasTCommunitySupply arg){
		this.id = arg.getId();		this.tGroupBuildingFId = arg.gettGroupBuildingFId();		this.tCommunitySupplyFId = arg.gettCommunitySupplyFId();		this.order = arg.getOrder();		this.distance = arg.getDistance();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.tCommunitySupplyCompanyFId = arg.gettCommunitySupplyCompanyFId();
	}
	/**	 * 	 * @param id 	 * @param tGroupBuildingFId 小区Id	 * @param tCommunitySupplyFId 商家Id	 * @param order 排序,降序,大的排前面	 * @param distance 小区与商家的距离,米	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param tCommunitySupplyCompanyFId 	 */
	public GroupBuildingHasTCommunitySupply(BigInteger id,BigInteger tGroupBuildingFId,BigInteger tCommunitySupplyFId,Integer order,Double distance,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger tCommunitySupplyCompanyFId){
		this.id = id;		this.tGroupBuildingFId = tGroupBuildingFId;		this.tCommunitySupplyFId = tCommunitySupplyFId;		this.order = order;		this.distance = distance;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.tCommunitySupplyCompanyFId = tCommunitySupplyCompanyFId;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");		sbf.append("tCommunitySupplyFId=").append(tCommunitySupplyFId).append(";");		sbf.append("order=").append(order).append(";");		sbf.append("distance=").append(distance).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("tCommunitySupplyCompanyFId=").append(tCommunitySupplyCompanyFId).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettGroupBuildingFId() {		return tGroupBuildingFId;	}	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {		this.tGroupBuildingFId = tGroupBuildingFId;	}	public BigInteger gettCommunitySupplyFId() {		return tCommunitySupplyFId;	}	public void settCommunitySupplyFId(BigInteger tCommunitySupplyFId) {		this.tCommunitySupplyFId = tCommunitySupplyFId;	}	public Integer getOrder() {		return order;	}	public void setOrder(Integer order) {		this.order = order;	}	public Double getDistance() {		return distance;	}	public void setDistance(Double distance) {		this.distance = distance;	}	public BigInteger gettCommunitySupplyCompanyFId() {		return tCommunitySupplyCompanyFId;	}	public void settCommunitySupplyCompanyFId(BigInteger tCommunitySupplyCompanyFId) {		this.tCommunitySupplyCompanyFId = tCommunitySupplyCompanyFId;	}
	
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
		GroupBuildingHasTCommunitySupply other = (GroupBuildingHasTCommunitySupply) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
