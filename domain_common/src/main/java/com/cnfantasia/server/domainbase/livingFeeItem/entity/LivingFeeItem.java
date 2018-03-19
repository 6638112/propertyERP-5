package com.cnfantasia.server.domainbase.livingFeeItem.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.Long;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(生活缴费支持的项目类别) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class LivingFeeItem implements Serializable{
*/


public class LivingFeeItem extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 消息编号 */	private BigInteger id;	/** 排序字段 */	private Integer sort;	/** 消息类型 */	private Long type;	/** 对应小图标图片 */	private String picUrl;	/** 名称 */	private String name;
	public LivingFeeItem(){
	}
	public LivingFeeItem(LivingFeeItem arg){
		this.id = arg.getId();		this.sort = arg.getSort();		this.type = arg.getType();		this.picUrl = arg.getPicUrl();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.name = arg.getName();
	}
	/**	 * 	 * @param id 消息编号	 * @param sort 排序字段	 * @param type 消息类型	 * @param picUrl 对应小图标图片	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param name 名称	 */
	public LivingFeeItem(BigInteger id,Integer sort,Long type,String picUrl,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,String name){
		this.id = id;		this.sort = sort;		this.type = type;		this.picUrl = picUrl;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.name = name;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("sort=").append(sort).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("picUrl=").append(picUrl).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("name=").append(name).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getSort() {		return sort;	}	public void setSort(Integer sort) {		this.sort = sort;	}	public Long getType() {		return type;	}	public void setType(Long type) {		this.type = type;	}	public String getPicUrl() {		return picUrl;	}	public void setPicUrl(String picUrl) {		this.picUrl = picUrl;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}
	
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
		LivingFeeItem other = (LivingFeeItem) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
