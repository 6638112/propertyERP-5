package com.cnfantasia.server.domainbase.kitchenCookbookTypeHasTKitchenCookbook.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(菜谱类别与菜谱关系表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class KitchenCookbookTypeHasTKitchenCookbook implements Serializable{
*/


public class KitchenCookbookTypeHasTKitchenCookbook extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 菜谱类别Id */	private BigInteger tKitchenCookbookTypeFId;	/** 菜谱Id */	private BigInteger tKitchenCookbookFId;
	public KitchenCookbookTypeHasTKitchenCookbook(){
	}
	public KitchenCookbookTypeHasTKitchenCookbook(KitchenCookbookTypeHasTKitchenCookbook arg){
		this.id = arg.getId();		this.tKitchenCookbookTypeFId = arg.gettKitchenCookbookTypeFId();		this.tKitchenCookbookFId = arg.gettKitchenCookbookFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tKitchenCookbookTypeFId 菜谱类别Id	 * @param tKitchenCookbookFId 菜谱Id	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public KitchenCookbookTypeHasTKitchenCookbook(BigInteger id,BigInteger tKitchenCookbookTypeFId,BigInteger tKitchenCookbookFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tKitchenCookbookTypeFId = tKitchenCookbookTypeFId;		this.tKitchenCookbookFId = tKitchenCookbookFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tKitchenCookbookTypeFId=").append(tKitchenCookbookTypeFId).append(";");		sbf.append("tKitchenCookbookFId=").append(tKitchenCookbookFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettKitchenCookbookTypeFId() {		return tKitchenCookbookTypeFId;	}	public void settKitchenCookbookTypeFId(BigInteger tKitchenCookbookTypeFId) {		this.tKitchenCookbookTypeFId = tKitchenCookbookTypeFId;	}	public BigInteger gettKitchenCookbookFId() {		return tKitchenCookbookFId;	}	public void settKitchenCookbookFId(BigInteger tKitchenCookbookFId) {		this.tKitchenCookbookFId = tKitchenCookbookFId;	}
	
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
		KitchenCookbookTypeHasTKitchenCookbook other = (KitchenCookbookTypeHasTKitchenCookbook) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
