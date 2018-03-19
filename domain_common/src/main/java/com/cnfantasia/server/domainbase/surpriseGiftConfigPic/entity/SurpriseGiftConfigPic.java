package com.cnfantasia.server.domainbase.surpriseGiftConfigPic.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(意外惊喜配置图标) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class SurpriseGiftConfigPic implements Serializable{
*/


public class SurpriseGiftConfigPic extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 图标地址 */	private String picUrl;	/** 图标点击后的地址 */	private String backPicUrl;	/** 图标类别 */	private Long type;	/** 最近更新时间 */	private String lastModifyTime;
	public SurpriseGiftConfigPic(){
	}
	public SurpriseGiftConfigPic(SurpriseGiftConfigPic arg){
		this.id = arg.getId();		this.picUrl = arg.getPicUrl();		this.backPicUrl = arg.getBackPicUrl();		this.type = arg.getType();		this.lastModifyTime = arg.getLastModifyTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param picUrl 图标地址	 * @param backPicUrl 图标点击后的地址	 * @param type 图标类别	 * @param lastModifyTime 最近更新时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public SurpriseGiftConfigPic(BigInteger id,String picUrl,String backPicUrl,Long type,String lastModifyTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.picUrl = picUrl;		this.backPicUrl = backPicUrl;		this.type = type;		this.lastModifyTime = lastModifyTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("picUrl=").append(picUrl).append(";");		sbf.append("backPicUrl=").append(backPicUrl).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("lastModifyTime=").append(lastModifyTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getPicUrl() {		return picUrl;	}	public void setPicUrl(String picUrl) {		this.picUrl = picUrl;	}	public String getBackPicUrl() {		return backPicUrl;	}	public void setBackPicUrl(String backPicUrl) {		this.backPicUrl = backPicUrl;	}	public Long getType() {		return type;	}	public void setType(Long type) {		this.type = type;	}	public String getLastModifyTime() {		return lastModifyTime;	}	public void setLastModifyTime(String lastModifyTime) {		this.lastModifyTime = lastModifyTime;	}
	
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
		SurpriseGiftConfigPic other = (SurpriseGiftConfigPic) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
