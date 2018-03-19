package com.cnfantasia.server.domainbase.communityForumType.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(社区论坛的帖子类别) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommunityForumType implements Serializable{
*/


public class CommunityForumType extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 类别名称 */	private String name;	/** 类别图标地址 */	private String icon;	/** 类别描述 */	private String desc;	/** 模块类别 */	private Integer modelType;	/** 8大模块对应url地址 */	private String urlFor8;	/** 最终目标url路径 */	private String urlFor8Goal;	/** 排序 */	private Integer order;
	public CommunityForumType(){
	}
	public CommunityForumType(CommunityForumType arg){
		this.id = arg.getId();		this.name = arg.getName();		this.icon = arg.getIcon();		this.desc = arg.getDesc();		this.modelType = arg.getModelType();		this.urlFor8 = arg.getUrlFor8();		this.urlFor8Goal = arg.getUrlFor8Goal();		this.order = arg.getOrder();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 类别名称	 * @param icon 类别图标地址	 * @param desc 类别描述	 * @param modelType 模块类别	 * @param urlFor8 8大模块对应url地址	 * @param urlFor8Goal 最终目标url路径	 * @param order 排序	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public CommunityForumType(BigInteger id,String name,String icon,String desc,Integer modelType,String urlFor8,String urlFor8Goal,Integer order,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.icon = icon;		this.desc = desc;		this.modelType = modelType;		this.urlFor8 = urlFor8;		this.urlFor8Goal = urlFor8Goal;		this.order = order;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("icon=").append(icon).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("modelType=").append(modelType).append(";");		sbf.append("urlFor8=").append(urlFor8).append(";");		sbf.append("urlFor8Goal=").append(urlFor8Goal).append(";");		sbf.append("order=").append(order).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getIcon() {		return icon;	}	public void setIcon(String icon) {		this.icon = icon;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public Integer getModelType() {		return modelType;	}	public void setModelType(Integer modelType) {		this.modelType = modelType;	}	public String getUrlFor8() {		return urlFor8;	}	public void setUrlFor8(String urlFor8) {		this.urlFor8 = urlFor8;	}	public String getUrlFor8Goal() {		return urlFor8Goal;	}	public void setUrlFor8Goal(String urlFor8Goal) {		this.urlFor8Goal = urlFor8Goal;	}	public Integer getOrder() {		return order;	}	public void setOrder(Integer order) {		this.order = order;	}
	
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
		CommunityForumType other = (CommunityForumType) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
