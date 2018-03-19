package com.cnfantasia.server.domainbase.communityAds.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Long;
import java.lang.Integer;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(街坊广告信息表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommunityAds implements Serializable{
*/


public class CommunityAds extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 主键Id */
	private BigInteger id;
	/** 图标地址 */
	private String icon;
	/** 详细链接url */
	private String detailUrl;
	/** 排序判断 */
	private Long order;
	/** 有效开始时间 */
	private String startTime;
	/** 有效截止时间 */
	private String endTime;
	/** 广告名称 */
	private String name;
	/** 开启状态 */
	private Integer status;
	/** 最小版本 */
	private Long minVersion;
	/** 最大版本 */
	private Long maxVersion;
	/** 最近更新时间 */
	private String lastUpdTime;

	public CommunityAds(){
	}
	public CommunityAds(CommunityAds arg){
		this.id = arg.getId();
		this.icon = arg.getIcon();
		this.detailUrl = arg.getDetailUrl();
		this.order = arg.getOrder();
		this.startTime = arg.getStartTime();
		this.endTime = arg.getEndTime();
		this.name = arg.getName();
		this.status = arg.getStatus();
		this.minVersion = arg.getMinVersion();
		this.maxVersion = arg.getMaxVersion();
		this.lastUpdTime = arg.getLastUpdTime();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();

	}
	/**
	 * 
	 * @param id 主键Id
	 * @param icon 图标地址
	 * @param detailUrl 详细链接url
	 * @param order 排序判断
	 * @param startTime 有效开始时间
	 * @param endTime 有效截止时间
	 * @param name 广告名称
	 * @param status 开启状态
	 * @param minVersion 最小版本
	 * @param maxVersion 最大版本
	 * @param lastUpdTime 最近更新时间
	 * @param sys0AddTime 新增时间
	 * @param sys0UpdTime 更新时间
	 * @param sys0DelTime 删除时间
	 * @param sys0AddUser 新增者
	 * @param sys0UpdUser 修改者
	 * @param sys0DelUser 删除者
	 * @param sys0DelState 记录状态
	 */

	public CommunityAds(BigInteger id,String icon,String detailUrl,Long order,String startTime,String endTime,String name,Integer status,Long minVersion,Long maxVersion,String lastUpdTime,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.icon = icon;
		this.detailUrl = detailUrl;
		this.order = order;
		this.startTime = startTime;
		this.endTime = endTime;
		this.name = name;
		this.status = status;
		this.minVersion = minVersion;
		this.maxVersion = maxVersion;
		this.lastUpdTime = lastUpdTime;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("icon=").append(icon).append(";");
		sbf.append("detailUrl=").append(detailUrl).append(";");
		sbf.append("order=").append(order).append(";");
		sbf.append("startTime=").append(startTime).append(";");
		sbf.append("endTime=").append(endTime).append(";");
		sbf.append("name=").append(name).append(";");
		sbf.append("status=").append(status).append(";");
		sbf.append("minVersion=").append(minVersion).append(";");
		sbf.append("maxVersion=").append(maxVersion).append(";");
		sbf.append("lastUpdTime=").append(lastUpdTime).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDetailUrl() {
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	public Long getOrder() {
		return order;
	}
	public void setOrder(Long order) {
		this.order = order;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getMinVersion() {
		return minVersion;
	}
	public void setMinVersion(Long minVersion) {
		this.minVersion = minVersion;
	}
	public Long getMaxVersion() {
		return maxVersion;
	}
	public void setMaxVersion(Long maxVersion) {
		this.maxVersion = maxVersion;
	}
	public String getLastUpdTime() {
		return lastUpdTime;
	}
	public void setLastUpdTime(String lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}

	
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
		CommunityAds other = (CommunityAds) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
