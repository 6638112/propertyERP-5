package com.cnfantasia.server.domainbase.userPositionInfo.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(用户定位信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class UserPositionInfo implements Serializable{
*/


public class UserPositionInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 用户Id */	private BigInteger userId;	/** 用户类型 */	private Integer userType;	/** 百度经度 */	private String baiduLng;	/** 百度纬度 */	private String baiduLat;	/** 最近的小区Id */	private BigInteger groupbuildingId;
	public UserPositionInfo(){
	}
	public UserPositionInfo(UserPositionInfo arg){
		this.id = arg.getId();		this.userId = arg.getUserId();		this.userType = arg.getUserType();		this.baiduLng = arg.getBaiduLng();		this.baiduLat = arg.getBaiduLat();		this.groupbuildingId = arg.getGroupbuildingId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param userId 用户Id	 * @param userType 用户类型	 * @param baiduLng 百度经度	 * @param baiduLat 百度纬度	 * @param groupbuildingId 最近的小区Id	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public UserPositionInfo(BigInteger id,BigInteger userId,Integer userType,String baiduLng,String baiduLat,BigInteger groupbuildingId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.userId = userId;		this.userType = userType;		this.baiduLng = baiduLng;		this.baiduLat = baiduLat;		this.groupbuildingId = groupbuildingId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("userType=").append(userType).append(";");		sbf.append("baiduLng=").append(baiduLng).append(";");		sbf.append("baiduLat=").append(baiduLat).append(";");		sbf.append("groupbuildingId=").append(groupbuildingId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public Integer getUserType() {		return userType;	}	public void setUserType(Integer userType) {		this.userType = userType;	}	public String getBaiduLng() {		return baiduLng;	}	public void setBaiduLng(String baiduLng) {		this.baiduLng = baiduLng;	}	public String getBaiduLat() {		return baiduLat;	}	public void setBaiduLat(String baiduLat) {		this.baiduLat = baiduLat;	}	public BigInteger getGroupbuildingId() {		return groupbuildingId;	}	public void setGroupbuildingId(BigInteger groupbuildingId) {		this.groupbuildingId = groupbuildingId;	}
	
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
		UserPositionInfo other = (UserPositionInfo) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
