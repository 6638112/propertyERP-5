package com.cnfantasia.server.domainbase.dredgeBillHasProductSpec.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Long;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(维修服务商品规格表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class DredgeBillHasProductSpec implements Serializable{
*/


public class DredgeBillHasProductSpec extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 维修单ID */	private BigInteger dredgeBillId;	/** 维修规格ID */	private BigInteger specificId;	/** 下单时销售价 */	private Long buyPrice;	/** 下单数量 */	private Long buyCount;
	public DredgeBillHasProductSpec(){
	}
	public DredgeBillHasProductSpec(DredgeBillHasProductSpec arg){
		this.id = arg.getId();		this.dredgeBillId = arg.getDredgeBillId();		this.specificId = arg.getSpecificId();		this.buyPrice = arg.getBuyPrice();		this.buyCount = arg.getBuyCount();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param dredgeBillId 维修单ID	 * @param specificId 维修规格ID	 * @param buyPrice 下单时销售价	 * @param buyCount 下单数量	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public DredgeBillHasProductSpec(BigInteger id,BigInteger dredgeBillId,BigInteger specificId,Long buyPrice,Long buyCount,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.dredgeBillId = dredgeBillId;		this.specificId = specificId;		this.buyPrice = buyPrice;		this.buyCount = buyCount;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("dredgeBillId=").append(dredgeBillId).append(";");		sbf.append("specificId=").append(specificId).append(";");		sbf.append("buyPrice=").append(buyPrice).append(";");		sbf.append("buyCount=").append(buyCount).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getDredgeBillId() {		return dredgeBillId;	}	public void setDredgeBillId(BigInteger dredgeBillId) {		this.dredgeBillId = dredgeBillId;	}	public BigInteger getSpecificId() {		return specificId;	}	public void setSpecificId(BigInteger specificId) {		this.specificId = specificId;	}	public Long getBuyPrice() {		return buyPrice;	}	public void setBuyPrice(Long buyPrice) {		this.buyPrice = buyPrice;	}	public Long getBuyCount() {		return buyCount;	}	public void setBuyCount(Long buyCount) {		this.buyCount = buyCount;	}
	
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
		DredgeBillHasProductSpec other = (DredgeBillHasProductSpec) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
