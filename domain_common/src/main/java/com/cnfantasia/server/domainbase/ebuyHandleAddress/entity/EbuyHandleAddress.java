package com.cnfantasia.server.domainbase.ebuyHandleAddress.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(手动输入的收货地址) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyHandleAddress implements Serializable{
*/


public class EbuyHandleAddress extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 详细地址 */	private String addressDetail;	/** 所属小区Id(为空时从addressArea中获取地区信息) */	private BigInteger tGroupBuildingFId;	/** 小区名称，防止小区不存在时查的不对 */	private String gbName;	/** 区id */	private BigInteger blockId;	/** 地区信息 */	private String addressArea;
	public EbuyHandleAddress(){
	}
	public EbuyHandleAddress(EbuyHandleAddress arg){
		this.id = arg.getId();		this.addressDetail = arg.getAddressDetail();		this.tGroupBuildingFId = arg.gettGroupBuildingFId();		this.gbName = arg.getGbName();		this.blockId = arg.getBlockId();		this.addressArea = arg.getAddressArea();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param addressDetail 详细地址	 * @param tGroupBuildingFId 所属小区Id(为空时从addressArea中获取地区信息)	 * @param gbName 小区名称，防止小区不存在时查的不对	 * @param blockId 区id	 * @param addressArea 地区信息	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuyHandleAddress(BigInteger id,String addressDetail,BigInteger tGroupBuildingFId,String gbName,BigInteger blockId,String addressArea,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.addressDetail = addressDetail;		this.tGroupBuildingFId = tGroupBuildingFId;		this.gbName = gbName;		this.blockId = blockId;		this.addressArea = addressArea;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("addressDetail=").append(addressDetail).append(";");		sbf.append("tGroupBuildingFId=").append(tGroupBuildingFId).append(";");		sbf.append("gbName=").append(gbName).append(";");		sbf.append("blockId=").append(blockId).append(";");		sbf.append("addressArea=").append(addressArea).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getAddressDetail() {		return addressDetail;	}	public void setAddressDetail(String addressDetail) {		this.addressDetail = addressDetail;	}	public BigInteger gettGroupBuildingFId() {		return tGroupBuildingFId;	}	public void settGroupBuildingFId(BigInteger tGroupBuildingFId) {		this.tGroupBuildingFId = tGroupBuildingFId;	}	public String getGbName() {		return gbName;	}	public void setGbName(String gbName) {		this.gbName = gbName;	}	public BigInteger getBlockId() {		return blockId;	}	public void setBlockId(BigInteger blockId) {		this.blockId = blockId;	}	public String getAddressArea() {		return addressArea;	}	public void setAddressArea(String addressArea) {		this.addressArea = addressArea;	}
	
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
		EbuyHandleAddress other = (EbuyHandleAddress) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
