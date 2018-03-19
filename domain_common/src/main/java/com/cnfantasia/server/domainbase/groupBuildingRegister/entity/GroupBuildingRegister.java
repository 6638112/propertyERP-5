package com.cnfantasia.server.domainbase.groupBuildingRegister.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(注册的小区信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class GroupBuildingRegister implements Serializable{
*/


public class GroupBuildingRegister extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 小区名称 */	private String name;	/** 小区地址 */	private String addressDesc;	/**  */	private BigInteger tAddressBlockFId;	/**  */	private BigInteger tPropertyCompanyFId;	/** 渠道合伙人推荐的物业公司 */	private BigInteger tChannelPartnerRecommendFId;	/** 审核状态 */	private Integer auditstatus;	/** 审核结果描述 */	private String auditDesc;
	public GroupBuildingRegister(){
	}
	public GroupBuildingRegister(GroupBuildingRegister arg){
		this.id = arg.getId();		this.name = arg.getName();		this.addressDesc = arg.getAddressDesc();		this.tAddressBlockFId = arg.gettAddressBlockFId();		this.tPropertyCompanyFId = arg.gettPropertyCompanyFId();		this.tChannelPartnerRecommendFId = arg.gettChannelPartnerRecommendFId();		this.auditstatus = arg.getAuditstatus();		this.auditDesc = arg.getAuditDesc();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 小区名称	 * @param addressDesc 小区地址	 * @param tAddressBlockFId 	 * @param tPropertyCompanyFId 	 * @param tChannelPartnerRecommendFId 渠道合伙人推荐的物业公司	 * @param auditstatus 审核状态	 * @param auditDesc 审核结果描述	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public GroupBuildingRegister(BigInteger id,String name,String addressDesc,BigInteger tAddressBlockFId,BigInteger tPropertyCompanyFId,BigInteger tChannelPartnerRecommendFId,Integer auditstatus,String auditDesc,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.addressDesc = addressDesc;		this.tAddressBlockFId = tAddressBlockFId;		this.tPropertyCompanyFId = tPropertyCompanyFId;		this.tChannelPartnerRecommendFId = tChannelPartnerRecommendFId;		this.auditstatus = auditstatus;		this.auditDesc = auditDesc;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("addressDesc=").append(addressDesc).append(";");		sbf.append("tAddressBlockFId=").append(tAddressBlockFId).append(";");		sbf.append("tPropertyCompanyFId=").append(tPropertyCompanyFId).append(";");		sbf.append("tChannelPartnerRecommendFId=").append(tChannelPartnerRecommendFId).append(";");		sbf.append("auditstatus=").append(auditstatus).append(";");		sbf.append("auditDesc=").append(auditDesc).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getAddressDesc() {		return addressDesc;	}	public void setAddressDesc(String addressDesc) {		this.addressDesc = addressDesc;	}	public BigInteger gettAddressBlockFId() {		return tAddressBlockFId;	}	public void settAddressBlockFId(BigInteger tAddressBlockFId) {		this.tAddressBlockFId = tAddressBlockFId;	}	public BigInteger gettPropertyCompanyFId() {		return tPropertyCompanyFId;	}	public void settPropertyCompanyFId(BigInteger tPropertyCompanyFId) {		this.tPropertyCompanyFId = tPropertyCompanyFId;	}	public BigInteger gettChannelPartnerRecommendFId() {		return tChannelPartnerRecommendFId;	}	public void settChannelPartnerRecommendFId(BigInteger tChannelPartnerRecommendFId) {		this.tChannelPartnerRecommendFId = tChannelPartnerRecommendFId;	}	public Integer getAuditstatus() {		return auditstatus;	}	public void setAuditstatus(Integer auditstatus) {		this.auditstatus = auditstatus;	}	public String getAuditDesc() {		return auditDesc;	}	public void setAuditDesc(String auditDesc) {		this.auditDesc = auditDesc;	}
	
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
		GroupBuildingRegister other = (GroupBuildingRegister) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
