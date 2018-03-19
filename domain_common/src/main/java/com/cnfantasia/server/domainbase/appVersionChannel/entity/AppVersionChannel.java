package com.cnfantasia.server.domainbase.appVersionChannel.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(应用版本不同渠道信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class AppVersionChannel implements Serializable{
*/


public class AppVersionChannel extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 渠道编码 */	private String channelCode;	/**  */	private BigInteger tAppBaseInfoFId;	/** 版本号 */	private Long version;	/** 是否需要强制更新 */	private Integer forceUpdate;	/** 更新描述 */	private String textDesc;	/**  */	private String picDesc;	/** 版本下载地址 */	private String versionDwonUrl;	/** 添加时间 */	private String createTime;	/** 创建人Id */	private BigInteger createrId;	/** 灰度发布类别 */	private Integer gateType;
	public AppVersionChannel(){
	}
	/**	 * 	 * @param id 	 * @param channelCode 渠道编码	 * @param tAppBaseInfoFId 	 * @param version 版本号	 * @param forceUpdate 是否需要强制更新	 * @param textDesc 更新描述	 * @param picDesc 	 * @param versionDwonUrl 版本下载地址	 * @param createTime 添加时间	 * @param createrId 创建人Id	 * @param gateType 灰度发布类别	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public AppVersionChannel(BigInteger id,String channelCode,BigInteger tAppBaseInfoFId,Long version,Integer forceUpdate,String textDesc,String picDesc,String versionDwonUrl,String createTime,BigInteger createrId,Integer gateType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.channelCode = channelCode;		this.tAppBaseInfoFId = tAppBaseInfoFId;		this.version = version;		this.forceUpdate = forceUpdate;		this.textDesc = textDesc;		this.picDesc = picDesc;		this.versionDwonUrl = versionDwonUrl;		this.createTime = createTime;		this.createrId = createrId;		this.gateType = gateType;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("channelCode=").append(channelCode).append(";");		sbf.append("tAppBaseInfoFId=").append(tAppBaseInfoFId).append(";");		sbf.append("version=").append(version).append(";");		sbf.append("forceUpdate=").append(forceUpdate).append(";");		sbf.append("textDesc=").append(textDesc).append(";");		sbf.append("picDesc=").append(picDesc).append(";");		sbf.append("versionDwonUrl=").append(versionDwonUrl).append(";");		sbf.append("createTime=").append(createTime).append(";");		sbf.append("createrId=").append(createrId).append(";");		sbf.append("gateType=").append(gateType).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getChannelCode() {		return channelCode;	}	public void setChannelCode(String channelCode) {		this.channelCode = channelCode;	}	public BigInteger gettAppBaseInfoFId() {		return tAppBaseInfoFId;	}	public void settAppBaseInfoFId(BigInteger tAppBaseInfoFId) {		this.tAppBaseInfoFId = tAppBaseInfoFId;	}	public Long getVersion() {		return version;	}	public void setVersion(Long version) {		this.version = version;	}	public Integer getForceUpdate() {		return forceUpdate;	}	public void setForceUpdate(Integer forceUpdate) {		this.forceUpdate = forceUpdate;	}	public String getTextDesc() {		return textDesc;	}	public void setTextDesc(String textDesc) {		this.textDesc = textDesc;	}	public String getPicDesc() {		return picDesc;	}	public void setPicDesc(String picDesc) {		this.picDesc = picDesc;	}	public String getVersionDwonUrl() {		return versionDwonUrl;	}	public void setVersionDwonUrl(String versionDwonUrl) {		this.versionDwonUrl = versionDwonUrl;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}	public BigInteger getCreaterId() {		return createrId;	}	public void setCreaterId(BigInteger createrId) {		this.createrId = createrId;	}	public Integer getGateType() {		return gateType;	}	public void setGateType(Integer gateType) {		this.gateType = gateType;	}
	
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
		AppVersionChannel other = (AppVersionChannel) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
