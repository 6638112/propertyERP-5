package com.cnfantasia.server.domainbase.operationSaHasEbSupply.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:() 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class OperationSaHasEbSupply implements Serializable{
*/


public class OperationSaHasEbSupply extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 服务范围Id */	private BigInteger saId;	/** 电商商家Id */	private BigInteger ebSupplyId;	/** 运营数据类型 */	private Integer type;	/** 关联关系(去除优先) */	private Integer relation;
	public OperationSaHasEbSupply(){
	}
	public OperationSaHasEbSupply(OperationSaHasEbSupply arg){
		this.id = arg.getId();		this.saId = arg.getSaId();		this.ebSupplyId = arg.getEbSupplyId();		this.type = arg.getType();		this.relation = arg.getRelation();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param saId 服务范围Id	 * @param ebSupplyId 电商商家Id	 * @param type 运营数据类型	 * @param relation 关联关系(去除优先)	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public OperationSaHasEbSupply(BigInteger id,BigInteger saId,BigInteger ebSupplyId,Integer type,Integer relation,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.saId = saId;		this.ebSupplyId = ebSupplyId;		this.type = type;		this.relation = relation;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("saId=").append(saId).append(";");		sbf.append("ebSupplyId=").append(ebSupplyId).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("relation=").append(relation).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getSaId() {		return saId;	}	public void setSaId(BigInteger saId) {		this.saId = saId;	}	public BigInteger getEbSupplyId() {		return ebSupplyId;	}	public void setEbSupplyId(BigInteger ebSupplyId) {		this.ebSupplyId = ebSupplyId;	}	public Integer getType() {		return type;	}	public void setType(Integer type) {		this.type = type;	}	public Integer getRelation() {		return relation;	}	public void setRelation(Integer relation) {		this.relation = relation;	}
	
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
		OperationSaHasEbSupply other = (OperationSaHasEbSupply) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
