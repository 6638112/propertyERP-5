package com.cnfantasia.server.domainbase.channelPartnerRecommend.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(渠道合伙人的推荐) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class ChannelPartnerRecommend implements Serializable{
*/


public class ChannelPartnerRecommend extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 物业公司名称 */	private String pcName;	/** 物业资质 */	private Integer propertyQualifications;	/** 小区住户数 */	private Long residentCount;	/** 公司地址 */	private String address;	/** 营业执照 */	private String iconBusinessLicense;	/** 公司联系人 */	private String linkman;	/** 渠道合伙人 */	private BigInteger tChannelPartnerFId;	/** 管理员是否关联 1关联，0未关联 */	private Integer isRelevance;
	public ChannelPartnerRecommend(){
	}
	public ChannelPartnerRecommend(ChannelPartnerRecommend arg){
		this.id = arg.getId();		this.pcName = arg.getPcName();		this.propertyQualifications = arg.getPropertyQualifications();		this.residentCount = arg.getResidentCount();		this.address = arg.getAddress();		this.iconBusinessLicense = arg.getIconBusinessLicense();		this.linkman = arg.getLinkman();		this.tChannelPartnerFId = arg.gettChannelPartnerFId();		this.isRelevance = arg.getIsRelevance();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param pcName 物业公司名称	 * @param propertyQualifications 物业资质	 * @param residentCount 小区住户数	 * @param address 公司地址	 * @param iconBusinessLicense 营业执照	 * @param linkman 公司联系人	 * @param tChannelPartnerFId 渠道合伙人	 * @param isRelevance 管理员是否关联 1关联，0未关联	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public ChannelPartnerRecommend(BigInteger id,String pcName,Integer propertyQualifications,Long residentCount,String address,String iconBusinessLicense,String linkman,BigInteger tChannelPartnerFId,Integer isRelevance,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.pcName = pcName;		this.propertyQualifications = propertyQualifications;		this.residentCount = residentCount;		this.address = address;		this.iconBusinessLicense = iconBusinessLicense;		this.linkman = linkman;		this.tChannelPartnerFId = tChannelPartnerFId;		this.isRelevance = isRelevance;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("pcName=").append(pcName).append(";");		sbf.append("propertyQualifications=").append(propertyQualifications).append(";");		sbf.append("residentCount=").append(residentCount).append(";");		sbf.append("address=").append(address).append(";");		sbf.append("iconBusinessLicense=").append(iconBusinessLicense).append(";");		sbf.append("linkman=").append(linkman).append(";");		sbf.append("tChannelPartnerFId=").append(tChannelPartnerFId).append(";");		sbf.append("isRelevance=").append(isRelevance).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getPcName() {		return pcName;	}	public void setPcName(String pcName) {		this.pcName = pcName;	}	public Integer getPropertyQualifications() {		return propertyQualifications;	}	public void setPropertyQualifications(Integer propertyQualifications) {		this.propertyQualifications = propertyQualifications;	}	public Long getResidentCount() {		return residentCount;	}	public void setResidentCount(Long residentCount) {		this.residentCount = residentCount;	}	public String getAddress() {		return address;	}	public void setAddress(String address) {		this.address = address;	}	public String getIconBusinessLicense() {		return iconBusinessLicense;	}	public void setIconBusinessLicense(String iconBusinessLicense) {		this.iconBusinessLicense = iconBusinessLicense;	}	public String getLinkman() {		return linkman;	}	public void setLinkman(String linkman) {		this.linkman = linkman;	}	public BigInteger gettChannelPartnerFId() {		return tChannelPartnerFId;	}	public void settChannelPartnerFId(BigInteger tChannelPartnerFId) {		this.tChannelPartnerFId = tChannelPartnerFId;	}	public Integer getIsRelevance() {		return isRelevance;	}	public void setIsRelevance(Integer isRelevance) {		this.isRelevance = isRelevance;	}
	
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
		ChannelPartnerRecommend other = (ChannelPartnerRecommend) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
