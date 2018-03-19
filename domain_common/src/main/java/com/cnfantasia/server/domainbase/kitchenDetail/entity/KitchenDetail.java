package com.cnfantasia.server.domainbase.kitchenDetail.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(厨房菜谱详情) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class KitchenDetail implements Serializable{
*/


public class KitchenDetail extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 参数类别 */	private Integer type;	/** 种类 */
	private Integer category;
	/** 参数名称 */	private String key;	/** 参数取值 */	private String value;	/**  */	private BigInteger tKitchenCookbookFId;
	public KitchenDetail(){
	}
	public KitchenDetail(KitchenDetail arg){
		this.id = arg.getId();		this.type = arg.getType();		this.category = arg.getCategory();
		this.key = arg.getKey();		this.value = arg.getValue();		this.tKitchenCookbookFId = arg.gettKitchenCookbookFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param type 参数类别	 * @param key 参数名称	 * @param value 参数取值	 * @param tKitchenCookbookFId 	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public KitchenDetail(BigInteger id,Integer type,Integer category,String key,String value,BigInteger tKitchenCookbookFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.type = type;		this.category = category;
		this.key = key;		this.value = value;		this.tKitchenCookbookFId = tKitchenCookbookFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("category=").append(category).append(";");
		sbf.append("key=").append(key).append(";");		sbf.append("value=").append(value).append(";");		sbf.append("tKitchenCookbookFId=").append(tKitchenCookbookFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getType() {		return type;	}	public void setType(Integer type) {		this.type = type;	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}	public String getKey() {		return key;	}	public void setKey(String key) {		this.key = key;	}	public String getValue() {		return value;	}	public void setValue(String value) {		this.value = value;	}	public BigInteger gettKitchenCookbookFId() {		return tKitchenCookbookFId;	}	public void settKitchenCookbookFId(BigInteger tKitchenCookbookFId) {		this.tKitchenCookbookFId = tKitchenCookbookFId;	}
	
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
		KitchenDetail other = (KitchenDetail) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
