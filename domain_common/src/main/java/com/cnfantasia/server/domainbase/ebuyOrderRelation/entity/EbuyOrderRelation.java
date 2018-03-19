package com.cnfantasia.server.domainbase.ebuyOrderRelation.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.Integer;
import java.lang.Long;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(总订单与子订单关系表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyOrderRelation implements Serializable{
*/


public class EbuyOrderRelation extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/** 总订单id */
	private BigInteger parentId;
	/** 子订单id */
	private BigInteger subId;
	/** t_ebuy_order字段f_type冗余 */
	private Integer subType;
	/** t_ebuy_order字段f_amount冗余 */
	private Long subAmount;

	public EbuyOrderRelation(){
	}
	public EbuyOrderRelation(EbuyOrderRelation arg){
		this.id = arg.getId();
		this.parentId = arg.getParentId();
		this.subId = arg.getSubId();
		this.subType = arg.getSubType();
		this.subAmount = arg.getSubAmount();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();

	}
	/**
	 * 
	 * @param id 
	 * @param parentId 总订单id
	 * @param subId 子订单id
	 * @param subType t_ebuy_order字段f_type冗余
	 * @param subAmount t_ebuy_order字段f_amount冗余
	 * @param sys0AddTime 
	 * @param sys0UpdTime 
	 * @param sys0DelTime 
	 * @param sys0AddUser 
	 * @param sys0UpdUser 
	 * @param sys0DelUser 
	 * @param sys0DelState 记录状态
	 */

	public EbuyOrderRelation(BigInteger id,BigInteger parentId,BigInteger subId,Integer subType,Long subAmount,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.parentId = parentId;
		this.subId = subId;
		this.subType = subType;
		this.subAmount = subAmount;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("parentId=").append(parentId).append(";");
		sbf.append("subId=").append(subId).append(";");
		sbf.append("subType=").append(subType).append(";");
		sbf.append("subAmount=").append(subAmount).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger getParentId() {
		return parentId;
	}
	public void setParentId(BigInteger parentId) {
		this.parentId = parentId;
	}
	public BigInteger getSubId() {
		return subId;
	}
	public void setSubId(BigInteger subId) {
		this.subId = subId;
	}
	public Integer getSubType() {
		return subType;
	}
	public void setSubType(Integer subType) {
		this.subType = subType;
	}
	public Long getSubAmount() {
		return subAmount;
	}
	public void setSubAmount(Long subAmount) {
		this.subAmount = subAmount;
	}

	
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
		EbuyOrderRelation other = (EbuyOrderRelation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
