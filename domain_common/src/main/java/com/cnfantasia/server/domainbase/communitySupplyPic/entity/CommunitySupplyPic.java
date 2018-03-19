package com.cnfantasia.server.domainbase.communitySupplyPic.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(商家图片) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommunitySupplyPic implements Serializable{
*/


public class CommunitySupplyPic extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 图片地址 */	private String picUrl;	/**  */	private BigInteger tCommunitySupplyFId;	/**  */	private BigInteger tCommunitySupplyTmpFId;	/** 图片类型 */	private Integer picType;
	public CommunitySupplyPic(){
	}
	public CommunitySupplyPic(CommunitySupplyPic arg){
		this.id = arg.getId();		this.picUrl = arg.getPicUrl();		this.tCommunitySupplyFId = arg.gettCommunitySupplyFId();		this.tCommunitySupplyTmpFId = arg.gettCommunitySupplyTmpFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.picType = arg.getPicType();
	}
	/**	 * 	 * @param id 	 * @param picUrl 图片地址	 * @param tCommunitySupplyFId 	 * @param tCommunitySupplyTmpFId 	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param picType 图片类型	 */
	public CommunitySupplyPic(BigInteger id,String picUrl,BigInteger tCommunitySupplyFId,BigInteger tCommunitySupplyTmpFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,Integer picType){
		this.id = id;		this.picUrl = picUrl;		this.tCommunitySupplyFId = tCommunitySupplyFId;		this.tCommunitySupplyTmpFId = tCommunitySupplyTmpFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.picType = picType;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("picUrl=").append(picUrl).append(";");		sbf.append("tCommunitySupplyFId=").append(tCommunitySupplyFId).append(";");		sbf.append("tCommunitySupplyTmpFId=").append(tCommunitySupplyTmpFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("picType=").append(picType).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getPicUrl() {		return picUrl;	}	public void setPicUrl(String picUrl) {		this.picUrl = picUrl;	}	public BigInteger gettCommunitySupplyFId() {		return tCommunitySupplyFId;	}	public void settCommunitySupplyFId(BigInteger tCommunitySupplyFId) {		this.tCommunitySupplyFId = tCommunitySupplyFId;	}	public BigInteger gettCommunitySupplyTmpFId() {		return tCommunitySupplyTmpFId;	}	public void settCommunitySupplyTmpFId(BigInteger tCommunitySupplyTmpFId) {		this.tCommunitySupplyTmpFId = tCommunitySupplyTmpFId;	}	public Integer getPicType() {		return picType;	}	public void setPicType(Integer picType) {		this.picType = picType;	}
	
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
		CommunitySupplyPic other = (CommunitySupplyPic) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
