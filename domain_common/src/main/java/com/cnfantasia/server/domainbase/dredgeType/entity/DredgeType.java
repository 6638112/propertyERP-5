package com.cnfantasia.server.domainbase.dredgeType.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(疏通类型) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class DredgeType implements Serializable{
*/


public class DredgeType extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 编码 */	private String code;	/** 疏通服务类别所属商家类型 */	private BigInteger tCommunitySupplyTypeFId;	/** 类型名称 */	private String name;	/** 图标 */	private String picUrl;	/** 描述 */	private String desc;	/** 价格说明 */	private String priceDesc;	/** 服务流程说明URL */	private String serviceProcessUrl;	/** 是否存在数量选择（1是，0否，默认为0） */	private Integer isHasNum;
	public DredgeType(){
	}
	public DredgeType(DredgeType arg){
		this.id = arg.getId();		this.code = arg.getCode();		this.tCommunitySupplyTypeFId = arg.gettCommunitySupplyTypeFId();		this.name = arg.getName();		this.picUrl = arg.getPicUrl();		this.desc = arg.getDesc();		this.priceDesc = arg.getPriceDesc();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();		this.serviceProcessUrl = arg.getServiceProcessUrl();		this.isHasNum = arg.getIsHasNum();
	}
	/**	 * 	 * @param id 	 * @param code 编码	 * @param tCommunitySupplyTypeFId 疏通服务类别所属商家类型	 * @param name 类型名称	 * @param picUrl 图标	 * @param desc 描述	 * @param priceDesc 价格说明	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 * @param serviceProcessUrl 服务流程说明URL	 * @param isHasNum 是否存在数量选择（1是，0否，默认为0）	 */
	public DredgeType(BigInteger id,String code,BigInteger tCommunitySupplyTypeFId,String name,String picUrl,String desc,String priceDesc,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,String serviceProcessUrl,Integer isHasNum){
		this.id = id;		this.code = code;		this.tCommunitySupplyTypeFId = tCommunitySupplyTypeFId;		this.name = name;		this.picUrl = picUrl;		this.desc = desc;		this.priceDesc = priceDesc;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;		this.serviceProcessUrl = serviceProcessUrl;		this.isHasNum = isHasNum;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("code=").append(code).append(";");		sbf.append("tCommunitySupplyTypeFId=").append(tCommunitySupplyTypeFId).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("picUrl=").append(picUrl).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("priceDesc=").append(priceDesc).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		sbf.append("serviceProcessUrl=").append(serviceProcessUrl).append(";");		sbf.append("isHasNum=").append(isHasNum).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getCode() {		return code;	}	public void setCode(String code) {		this.code = code;	}	public BigInteger gettCommunitySupplyTypeFId() {		return tCommunitySupplyTypeFId;	}	public void settCommunitySupplyTypeFId(BigInteger tCommunitySupplyTypeFId) {		this.tCommunitySupplyTypeFId = tCommunitySupplyTypeFId;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getPicUrl() {		return picUrl;	}	public void setPicUrl(String picUrl) {		this.picUrl = picUrl;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public String getPriceDesc() {		return priceDesc;	}	public void setPriceDesc(String priceDesc) {		this.priceDesc = priceDesc;	}	public String getServiceProcessUrl() {		return serviceProcessUrl;	}	public void setServiceProcessUrl(String serviceProcessUrl) {		this.serviceProcessUrl = serviceProcessUrl;	}	public Integer getIsHasNum() {		return isHasNum;	}	public void setIsHasNum(Integer isHasNum) {		this.isHasNum = isHasNum;	}
	
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
		DredgeType other = (DredgeType) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
