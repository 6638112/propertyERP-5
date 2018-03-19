package com.cnfantasia.server.domainbase.homeMessageType.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(首页消息类型表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class HomeMessageType implements Serializable{
*/


public class HomeMessageType extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 名称 */	private String name;	/** code */	private String code;	/** 图标地址 */	private String iconUrl;	/** 优先级，越大级别越高 */	private Long order;	/** 是否与门牌有关，0不是，1是 */	private Integer isRelateRoom;	/** 开始版本 */	private Long startVersion;	/** 结束版本 */	private Long endVersion;
	public HomeMessageType(){
	}
	public HomeMessageType(HomeMessageType arg){
		this.id = arg.getId();		this.name = arg.getName();		this.code = arg.getCode();		this.iconUrl = arg.getIconUrl();		this.order = arg.getOrder();		this.isRelateRoom = arg.getIsRelateRoom();		this.startVersion = arg.getStartVersion();		this.endVersion = arg.getEndVersion();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 名称	 * @param code code	 * @param iconUrl 图标地址	 * @param order 优先级，越大级别越高	 * @param isRelateRoom 是否与门牌有关，0不是，1是	 * @param startVersion 开始版本	 * @param endVersion 结束版本	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public HomeMessageType(BigInteger id,String name,String code,String iconUrl,Long order,Integer isRelateRoom,Long startVersion,Long endVersion,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.code = code;		this.iconUrl = iconUrl;		this.order = order;		this.isRelateRoom = isRelateRoom;		this.startVersion = startVersion;		this.endVersion = endVersion;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("code=").append(code).append(";");		sbf.append("iconUrl=").append(iconUrl).append(";");		sbf.append("order=").append(order).append(";");		sbf.append("isRelateRoom=").append(isRelateRoom).append(";");		sbf.append("startVersion=").append(startVersion).append(";");		sbf.append("endVersion=").append(endVersion).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getCode() {		return code;	}	public void setCode(String code) {		this.code = code;	}	public String getIconUrl() {		return iconUrl;	}	public void setIconUrl(String iconUrl) {		this.iconUrl = iconUrl;	}	public Long getOrder() {		return order;	}	public void setOrder(Long order) {		this.order = order;	}	public Integer getIsRelateRoom() {		return isRelateRoom;	}	public void setIsRelateRoom(Integer isRelateRoom) {		this.isRelateRoom = isRelateRoom;	}	public Long getStartVersion() {		return startVersion;	}	public void setStartVersion(Long startVersion) {		this.startVersion = startVersion;	}	public Long getEndVersion() {		return endVersion;	}	public void setEndVersion(Long endVersion) {		this.endVersion = endVersion;	}
	
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
		HomeMessageType other = (HomeMessageType) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
