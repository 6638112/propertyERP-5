package com.cnfantasia.server.domainbase.propertyService.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(物业服务信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyService implements Serializable{
*/


public class PropertyService extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 服务名称 */	private String name;	/** 图标地址 */	private String iconUrl;	/** 服务描述 */	private String desc;	/** 是否为所有小区默认添加 */	private Integer isDefaultAdd;	/** 是否为所有签约小区默认添加 */	private Integer isDefaultAddSign;	/** 是否为所有非签约小区默认添加 */	private Integer isDefaultAddUnsign;	/** 排序，升序 */	private Long order;
	public PropertyService(){
	}
	public PropertyService(PropertyService arg){
		this.id = arg.getId();		this.name = arg.getName();		this.iconUrl = arg.getIconUrl();		this.desc = arg.getDesc();		this.isDefaultAdd = arg.getIsDefaultAdd();		this.isDefaultAddSign = arg.getIsDefaultAddSign();		this.isDefaultAddUnsign = arg.getIsDefaultAddUnsign();		this.order = arg.getOrder();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 服务名称	 * @param iconUrl 图标地址	 * @param desc 服务描述	 * @param isDefaultAdd 是否为所有小区默认添加	 * @param isDefaultAddSign 是否为所有签约小区默认添加	 * @param isDefaultAddUnsign 是否为所有非签约小区默认添加	 * @param order 排序，升序	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public PropertyService(BigInteger id,String name,String iconUrl,String desc,Integer isDefaultAdd,Integer isDefaultAddSign,Integer isDefaultAddUnsign,Long order,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.iconUrl = iconUrl;		this.desc = desc;		this.isDefaultAdd = isDefaultAdd;		this.isDefaultAddSign = isDefaultAddSign;		this.isDefaultAddUnsign = isDefaultAddUnsign;		this.order = order;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("iconUrl=").append(iconUrl).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("isDefaultAdd=").append(isDefaultAdd).append(";");		sbf.append("isDefaultAddSign=").append(isDefaultAddSign).append(";");		sbf.append("isDefaultAddUnsign=").append(isDefaultAddUnsign).append(";");		sbf.append("order=").append(order).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getIconUrl() {		return iconUrl;	}	public void setIconUrl(String iconUrl) {		this.iconUrl = iconUrl;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public Integer getIsDefaultAdd() {		return isDefaultAdd;	}	public void setIsDefaultAdd(Integer isDefaultAdd) {		this.isDefaultAdd = isDefaultAdd;	}	public Integer getIsDefaultAddSign() {		return isDefaultAddSign;	}	public void setIsDefaultAddSign(Integer isDefaultAddSign) {		this.isDefaultAddSign = isDefaultAddSign;	}	public Integer getIsDefaultAddUnsign() {		return isDefaultAddUnsign;	}	public void setIsDefaultAddUnsign(Integer isDefaultAddUnsign) {		this.isDefaultAddUnsign = isDefaultAddUnsign;	}	public Long getOrder() {		return order;	}	public void setOrder(Long order) {		this.order = order;	}
	
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
		PropertyService other = (PropertyService) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
