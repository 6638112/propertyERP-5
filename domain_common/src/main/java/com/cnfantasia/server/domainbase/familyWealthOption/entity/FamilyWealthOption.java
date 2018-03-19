package com.cnfantasia.server.domainbase.familyWealthOption.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(家庭财富选项) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class FamilyWealthOption implements Serializable{
*/


public class FamilyWealthOption extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 财富类别 */	private Integer type;	/** 选项名称 */	private String name;	/** 图标地址 */	private String iconUrl;	/** 描述 */	private String desc;	/** 顺序 */	private Integer order;
	public FamilyWealthOption(){
	}
	public FamilyWealthOption(FamilyWealthOption arg){
		this.id = arg.getId();		this.type = arg.getType();		this.name = arg.getName();		this.iconUrl = arg.getIconUrl();		this.desc = arg.getDesc();		this.order = arg.getOrder();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param type 财富类别	 * @param name 选项名称	 * @param iconUrl 图标地址	 * @param desc 描述	 * @param order 顺序	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public FamilyWealthOption(BigInteger id,Integer type,String name,String iconUrl,String desc,Integer order,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.type = type;		this.name = name;		this.iconUrl = iconUrl;		this.desc = desc;		this.order = order;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("iconUrl=").append(iconUrl).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("order=").append(order).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getType() {		return type;	}	public void setType(Integer type) {		this.type = type;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getIconUrl() {		return iconUrl;	}	public void setIconUrl(String iconUrl) {		this.iconUrl = iconUrl;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public Integer getOrder() {		return order;	}	public void setOrder(Integer order) {		this.order = order;	}
	
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
		FamilyWealthOption other = (FamilyWealthOption) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
