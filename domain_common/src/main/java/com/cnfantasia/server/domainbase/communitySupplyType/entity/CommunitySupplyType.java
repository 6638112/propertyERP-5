package com.cnfantasia.server.domainbase.communitySupplyType.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(社区商家类别) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommunitySupplyType implements Serializable{
*/


public class CommunitySupplyType extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 类别名称 */	private String name;	/** 类别拼音名称 */	private String pinyinName;	/** 所属上级Id */	private BigInteger parentId;	/** 重要程度 */	private Integer importanceLevel;	/** 大图标地址 */	private String iconBigUrl;	/** 小图标地址 */	private String iconSmallUrl;	/** 排序编号，数字小排在前 */	private Long order;	/** 搜索的关键字 */	private String searchKey;
	public CommunitySupplyType(){
	}
	public CommunitySupplyType(CommunitySupplyType arg){
		this.id = arg.getId();		this.name = arg.getName();		this.pinyinName = arg.getPinyinName();		this.parentId = arg.getParentId();		this.importanceLevel = arg.getImportanceLevel();		this.iconBigUrl = arg.getIconBigUrl();		this.iconSmallUrl = arg.getIconSmallUrl();		this.order = arg.getOrder();		this.searchKey = arg.getSearchKey();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 类别名称	 * @param pinyinName 类别拼音名称	 * @param parentId 所属上级Id	 * @param importanceLevel 重要程度	 * @param iconBigUrl 大图标地址	 * @param iconSmallUrl 小图标地址	 * @param order 排序编号，数字小排在前	 * @param searchKey 搜索的关键字	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CommunitySupplyType(BigInteger id,String name,String pinyinName,BigInteger parentId,Integer importanceLevel,String iconBigUrl,String iconSmallUrl,Long order,String searchKey,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.pinyinName = pinyinName;		this.parentId = parentId;		this.importanceLevel = importanceLevel;		this.iconBigUrl = iconBigUrl;		this.iconSmallUrl = iconSmallUrl;		this.order = order;		this.searchKey = searchKey;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("pinyinName=").append(pinyinName).append(";");		sbf.append("parentId=").append(parentId).append(";");		sbf.append("importanceLevel=").append(importanceLevel).append(";");		sbf.append("iconBigUrl=").append(iconBigUrl).append(";");		sbf.append("iconSmallUrl=").append(iconSmallUrl).append(";");		sbf.append("order=").append(order).append(";");		sbf.append("searchKey=").append(searchKey).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getPinyinName() {		return pinyinName;	}	public void setPinyinName(String pinyinName) {		this.pinyinName = pinyinName;	}	public BigInteger getParentId() {		return parentId;	}	public void setParentId(BigInteger parentId) {		this.parentId = parentId;	}	public Integer getImportanceLevel() {		return importanceLevel;	}	public void setImportanceLevel(Integer importanceLevel) {		this.importanceLevel = importanceLevel;	}	public String getIconBigUrl() {		return iconBigUrl;	}	public void setIconBigUrl(String iconBigUrl) {		this.iconBigUrl = iconBigUrl;	}	public String getIconSmallUrl() {		return iconSmallUrl;	}	public void setIconSmallUrl(String iconSmallUrl) {		this.iconSmallUrl = iconSmallUrl;	}	public Long getOrder() {		return order;	}	public void setOrder(Long order) {		this.order = order;	}	public String getSearchKey() {		return searchKey;	}	public void setSearchKey(String searchKey) {		this.searchKey = searchKey;	}
	
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
		CommunitySupplyType other = (CommunitySupplyType) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
