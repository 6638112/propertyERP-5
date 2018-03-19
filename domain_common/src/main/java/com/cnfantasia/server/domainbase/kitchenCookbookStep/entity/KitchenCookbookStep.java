package com.cnfantasia.server.domainbase.kitchenCookbookStep.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(亨饪步骤信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class KitchenCookbookStep implements Serializable{
*/


public class KitchenCookbookStep extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 步骤文本说明 */	private String desc;	/** 步骤图片信息 */	private String picUrl;	/** 所属菜谱Id */	private BigInteger tKitchenCookbookFId;	/** 步骤编号 */	private Integer orderNum;
	public KitchenCookbookStep(){
	}
	public KitchenCookbookStep(KitchenCookbookStep arg){
		this.id = arg.getId();		this.desc = arg.getDesc();		this.picUrl = arg.getPicUrl();		this.tKitchenCookbookFId = arg.gettKitchenCookbookFId();		this.orderNum = arg.getOrderNum();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param desc 步骤文本说明	 * @param picUrl 步骤图片信息	 * @param tKitchenCookbookFId 所属菜谱Id	 * @param orderNum 步骤编号	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public KitchenCookbookStep(BigInteger id,String desc,String picUrl,BigInteger tKitchenCookbookFId,Integer orderNum,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.desc = desc;		this.picUrl = picUrl;		this.tKitchenCookbookFId = tKitchenCookbookFId;		this.orderNum = orderNum;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("desc=").append(desc).append(";");		sbf.append("picUrl=").append(picUrl).append(";");		sbf.append("tKitchenCookbookFId=").append(tKitchenCookbookFId).append(";");		sbf.append("orderNum=").append(orderNum).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getDesc() {		return desc;	}	public void setDesc(String desc) {		this.desc = desc;	}	public String getPicUrl() {		return picUrl;	}	public void setPicUrl(String picUrl) {		this.picUrl = picUrl;	}	public BigInteger gettKitchenCookbookFId() {		return tKitchenCookbookFId;	}	public void settKitchenCookbookFId(BigInteger tKitchenCookbookFId) {		this.tKitchenCookbookFId = tKitchenCookbookFId;	}	public Integer getOrderNum() {		return orderNum;	}	public void setOrderNum(Integer orderNum) {		this.orderNum = orderNum;	}
	
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
		KitchenCookbookStep other = (KitchenCookbookStep) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
