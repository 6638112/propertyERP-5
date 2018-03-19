package com.cnfantasia.server.domainbase.dredgeAddress.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(维修地址) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class DredgeAddress implements Serializable{
*/


public class DredgeAddress extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 所属用户 */	private BigInteger tUserFId;	/** 小区ID */	private BigInteger gbId;	/** 行政区ID */	private BigInteger blockId;	/** 维修地址(小区名+房间) */	private String address;
	public DredgeAddress(){
	}
	public DredgeAddress(DredgeAddress arg){
		this.id = arg.getId();		this.tUserFId = arg.gettUserFId();		this.gbId = arg.getGbId();		this.blockId = arg.getBlockId();		this.address = arg.getAddress();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tUserFId 所属用户	 * @param gbId 小区ID	 * @param blockId 行政区ID	 * @param address 维修地址(小区名+房间)	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public DredgeAddress(BigInteger id,BigInteger tUserFId,BigInteger gbId,BigInteger blockId,String address,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tUserFId = tUserFId;		this.gbId = gbId;		this.blockId = blockId;		this.address = address;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("gbId=").append(gbId).append(";");		sbf.append("blockId=").append(blockId).append(";");		sbf.append("address=").append(address).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public BigInteger getGbId() {		return gbId;	}	public void setGbId(BigInteger gbId) {		this.gbId = gbId;	}	public BigInteger getBlockId() {		return blockId;	}	public void setBlockId(BigInteger blockId) {		this.blockId = blockId;	}	public String getAddress() {		return address;	}	public void setAddress(String address) {		this.address = address;	}
	
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
		DredgeAddress other = (DredgeAddress) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
