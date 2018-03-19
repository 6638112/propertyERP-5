package com.cnfantasia.server.domainbase.msPrizeOption.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(奖项表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class MsPrizeOption implements Serializable{
*/


public class MsPrizeOption extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 名称 */	private String name;	/** 奖品图标 */	private String icon;	/** 开启状态 */	private Integer status;	/** 手气如何文字说明 */	private String luckDesc;	/** 使用说明 */	private String useDesc;	/** 使用消费券跳转URL */	private String linkUrl;	/** 奖品有效期开始时间,用户使用期限 */	private String valiStartTime;	/** 奖品有效期截止时间,用户使用期限 */	private String valiEndTime;	/** 备注信息 */	private String comment;	/** 最近更新时间 */	private String lastUpdTime;
	public MsPrizeOption(){
	}
	public MsPrizeOption(MsPrizeOption arg){
		this.id = arg.getId();		this.name = arg.getName();		this.icon = arg.getIcon();		this.status = arg.getStatus();		this.luckDesc = arg.getLuckDesc();		this.useDesc = arg.getUseDesc();		this.linkUrl = arg.getLinkUrl();		this.valiStartTime = arg.getValiStartTime();		this.valiEndTime = arg.getValiEndTime();		this.comment = arg.getComment();		this.lastUpdTime = arg.getLastUpdTime();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 名称	 * @param icon 奖品图标	 * @param status 开启状态	 * @param luckDesc 手气如何文字说明	 * @param useDesc 使用说明	 * @param linkUrl 使用消费券跳转URL	 * @param valiStartTime 奖品有效期开始时间,用户使用期限	 * @param valiEndTime 奖品有效期截止时间,用户使用期限	 * @param comment 备注信息	 * @param lastUpdTime 最近更新时间	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public MsPrizeOption(BigInteger id,String name,String icon,Integer status,String luckDesc,String useDesc,String linkUrl,String valiStartTime,String valiEndTime,String comment,String lastUpdTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.icon = icon;		this.status = status;		this.luckDesc = luckDesc;		this.useDesc = useDesc;		this.linkUrl = linkUrl;		this.valiStartTime = valiStartTime;		this.valiEndTime = valiEndTime;		this.comment = comment;		this.lastUpdTime = lastUpdTime;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("icon=").append(icon).append(";");		sbf.append("status=").append(status).append(";");		sbf.append("luckDesc=").append(luckDesc).append(";");		sbf.append("useDesc=").append(useDesc).append(";");		sbf.append("linkUrl=").append(linkUrl).append(";");		sbf.append("valiStartTime=").append(valiStartTime).append(";");		sbf.append("valiEndTime=").append(valiEndTime).append(";");		sbf.append("comment=").append(comment).append(";");		sbf.append("lastUpdTime=").append(lastUpdTime).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getIcon() {		return icon;	}	public void setIcon(String icon) {		this.icon = icon;	}	public Integer getStatus() {		return status;	}	public void setStatus(Integer status) {		this.status = status;	}	public String getLuckDesc() {		return luckDesc;	}	public void setLuckDesc(String luckDesc) {		this.luckDesc = luckDesc;	}	public String getUseDesc() {		return useDesc;	}	public void setUseDesc(String useDesc) {		this.useDesc = useDesc;	}	public String getLinkUrl() {		return linkUrl;	}	public void setLinkUrl(String linkUrl) {		this.linkUrl = linkUrl;	}	public String getValiStartTime() {		return valiStartTime;	}	public void setValiStartTime(String valiStartTime) {		this.valiStartTime = valiStartTime;	}	public String getValiEndTime() {		return valiEndTime;	}	public void setValiEndTime(String valiEndTime) {		this.valiEndTime = valiEndTime;	}	public String getComment() {		return comment;	}	public void setComment(String comment) {		this.comment = comment;	}	public String getLastUpdTime() {		return lastUpdTime;	}	public void setLastUpdTime(String lastUpdTime) {		this.lastUpdTime = lastUpdTime;	}
	
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
		MsPrizeOption other = (MsPrizeOption) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
