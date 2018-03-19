package com.cnfantasia.server.domainbase.operationHomeSupplyType.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;
import java.lang.Long;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(首页商家类别运营表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class OperationHomeSupplyType implements Serializable{
*/


public class OperationHomeSupplyType extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 类别Id */
	private BigInteger id;
	/** 类别名称 */
	private String name;
	/** 类别 */
	private Integer dataType;
	/** 数据级别 */
	private Integer dataLevel;
	/** 所属上级Id */
	private BigInteger parentId;
	/** 普通商家类别Id */
	private BigInteger supplyTypeId;
	/** 链接地址 */
	private String linkUrl;
	/** 编码 */
	private String code;
	/** 描述 */
	private String desc;
	/** 类别图标地址 */
	private String iconName;
	/** 是否火热开启 */
	private Integer isHot;
	/** 排序,升序 */
	private Long order;
	/** 版本控制 */
	private Long version;
	/** 99999 */
	private Long maxVersion;
	/** 最近更新时间 */
	private String lastUpdTime;
	/** 首页显示位置 */
	private Integer homePlace;
	/** 生效开始时间 */
	private String startTime;
	/** 生效截止时间 */
	private String endTime;

	public OperationHomeSupplyType(){
	}
	public OperationHomeSupplyType(OperationHomeSupplyType arg){
		this.id = arg.getId();
		this.name = arg.getName();
		this.dataType = arg.getDataType();
		this.dataLevel = arg.getDataLevel();
		this.parentId = arg.getParentId();
		this.supplyTypeId = arg.getSupplyTypeId();
		this.linkUrl = arg.getLinkUrl();
		this.code = arg.getCode();
		this.desc = arg.getDesc();
		this.iconName = arg.getIconName();
		this.isHot = arg.getIsHot();
		this.order = arg.getOrder();
		this.version = arg.getVersion();
		this.maxVersion = arg.getMaxVersion();
		this.lastUpdTime = arg.getLastUpdTime();
		this.homePlace = arg.getHomePlace();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();
		this.startTime = arg.getStartTime();
		this.endTime = arg.getEndTime();

	}
	/**
	 * 
	 * @param id 类别Id
	 * @param name 类别名称
	 * @param dataType 类别
	 * @param dataLevel 数据级别
	 * @param parentId 所属上级Id
	 * @param supplyTypeId 普通商家类别Id
	 * @param linkUrl 链接地址
	 * @param code 编码
	 * @param desc 描述
	 * @param iconName 类别图标地址
	 * @param isHot 是否火热开启
	 * @param order 排序,升序
	 * @param version 版本控制
	 * @param maxVersion 99999
	 * @param lastUpdTime 最近更新时间
	 * @param homePlace 首页显示位置
	 * @param sys0AddTime 新增时间
	 * @param sys0UpdTime 更新时间
	 * @param sys0DelTime 删除时间
	 * @param sys0AddUser 新增者
	 * @param sys0UpdUser 修改者
	 * @param sys0DelUser 删除者
	 * @param sys0DelState 记录状态
	 * @param startTime 生效开始时间
	 * @param endTime 生效截止时间
	 */

	public OperationHomeSupplyType(BigInteger id,String name,Integer dataType,Integer dataLevel,BigInteger parentId,BigInteger supplyTypeId,String linkUrl,String code,String desc,String iconName,Integer isHot,Long order,Long version,Long maxVersion,String lastUpdTime,Integer homePlace,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,String startTime,String endTime){
		this.id = id;
		this.name = name;
		this.dataType = dataType;
		this.dataLevel = dataLevel;
		this.parentId = parentId;
		this.supplyTypeId = supplyTypeId;
		this.linkUrl = linkUrl;
		this.code = code;
		this.desc = desc;
		this.iconName = iconName;
		this.isHot = isHot;
		this.order = order;
		this.version = version;
		this.maxVersion = maxVersion;
		this.lastUpdTime = lastUpdTime;
		this.homePlace = homePlace;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;
		this.startTime = startTime;
		this.endTime = endTime;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("name=").append(name).append(";");
		sbf.append("dataType=").append(dataType).append(";");
		sbf.append("dataLevel=").append(dataLevel).append(";");
		sbf.append("parentId=").append(parentId).append(";");
		sbf.append("supplyTypeId=").append(supplyTypeId).append(";");
		sbf.append("linkUrl=").append(linkUrl).append(";");
		sbf.append("code=").append(code).append(";");
		sbf.append("desc=").append(desc).append(";");
		sbf.append("iconName=").append(iconName).append(";");
		sbf.append("isHot=").append(isHot).append(";");
		sbf.append("order=").append(order).append(";");
		sbf.append("version=").append(version).append(";");
		sbf.append("maxVersion=").append(maxVersion).append(";");
		sbf.append("lastUpdTime=").append(lastUpdTime).append(";");
		sbf.append("homePlace=").append(homePlace).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		sbf.append("startTime=").append(startTime).append(";");
		sbf.append("endTime=").append(endTime).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	public Integer getDataLevel() {
		return dataLevel;
	}
	public void setDataLevel(Integer dataLevel) {
		this.dataLevel = dataLevel;
	}
	public BigInteger getParentId() {
		return parentId;
	}
	public void setParentId(BigInteger parentId) {
		this.parentId = parentId;
	}
	public BigInteger getSupplyTypeId() {
		return supplyTypeId;
	}
	public void setSupplyTypeId(BigInteger supplyTypeId) {
		this.supplyTypeId = supplyTypeId;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getIconName() {
		return iconName;
	}
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	public Integer getIsHot() {
		return isHot;
	}
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}
	public Long getOrder() {
		return order;
	}
	public void setOrder(Long order) {
		this.order = order;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
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
	public Integer getHomePlace() {
		return homePlace;
	}
	public void setHomePlace(Integer homePlace) {
		this.homePlace = homePlace;
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
		OperationHomeSupplyType other = (OperationHomeSupplyType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
