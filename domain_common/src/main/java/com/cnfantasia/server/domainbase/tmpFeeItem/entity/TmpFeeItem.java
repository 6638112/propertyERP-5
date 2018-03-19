package com.cnfantasia.server.domainbase.tmpFeeItem.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(临时收费项) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class TmpFeeItem implements Serializable{
*/


public class TmpFeeItem extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 收费项名称 */	private String name;	/** 计费方式（1：建筑面积*单价,2：指定金额,3 */	private Integer calculateType;	/** 小区id */	private BigInteger tGbId;	/** 是否计算滞纳金（1计算，2不计算 */	private Integer isCalculateLatefee;
	public TmpFeeItem(){
	}
	public TmpFeeItem(TmpFeeItem arg){
		this.id = arg.getId();		this.name = arg.getName();		this.calculateType = arg.getCalculateType();		this.tGbId = arg.gettGbId();		this.isCalculateLatefee = arg.getIsCalculateLatefee();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param name 收费项名称	 * @param calculateType 计费方式（1：建筑面积*单价,2：指定金额,3	 * @param tGbId 小区id	 * @param isCalculateLatefee 是否计算滞纳金（1计算，2不计算	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public TmpFeeItem(BigInteger id,String name,Integer calculateType,BigInteger tGbId,Integer isCalculateLatefee,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.name = name;		this.calculateType = calculateType;		this.tGbId = tGbId;		this.isCalculateLatefee = isCalculateLatefee;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("name=").append(name).append(";");		sbf.append("calculateType=").append(calculateType).append(";");		sbf.append("tGbId=").append(tGbId).append(";");		sbf.append("isCalculateLatefee=").append(isCalculateLatefee).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public Integer getCalculateType() {		return calculateType;	}	public void setCalculateType(Integer calculateType) {		this.calculateType = calculateType;	}	public BigInteger gettGbId() {		return tGbId;	}	public void settGbId(BigInteger tGbId) {		this.tGbId = tGbId;	}	public Integer getIsCalculateLatefee() {		return isCalculateLatefee;	}	public void setIsCalculateLatefee(Integer isCalculateLatefee) {		this.isCalculateLatefee = isCalculateLatefee;	}
	
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
		TmpFeeItem other = (TmpFeeItem) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
