package com.cnfantasia.server.domainbase.communitySupplyTmp.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(店铺申请审核表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommunitySupplyTmp implements Serializable{
*/


public class CommunitySupplyTmp extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	public CommunitySupplyTmp(){
	}
	public CommunitySupplyTmp(CommunitySupplyTmp arg){
		this.id = arg.getId();
	}
	/**
	public CommunitySupplyTmp(BigInteger id,BigInteger createUserId,BigInteger createRoomId,BigInteger tCommunitySupplyTypeFId,BigInteger tCommunitySupplyFId,String supplyName,BigInteger addressBlockId,BigInteger groupBuildingId,String addressDetail,String contectPhone,Integer auditType,Integer auditStatus,String auditTime,String auditDesc,String companyPhone,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,String companyName,Integer addType,String delImgIds,String delContectIds){
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
	}
	
	public BigInteger getId() {
	
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
		CommunitySupplyTmp other = (CommunitySupplyTmp) obj;
		if (id == null) {
		return true;
	}
	
}