package com.cnfantasia.server.domainbase.communitySupplyBelong.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(商家认领) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class CommunitySupplyBelong implements Serializable{
*/


public class CommunitySupplyBelong extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	public CommunitySupplyBelong(){
	}
	public CommunitySupplyBelong(CommunitySupplyBelong arg){
		this.id = arg.getId();
	}
	/**
	public CommunitySupplyBelong(BigInteger id,BigInteger claimUserId,BigInteger tCommunitySupplyCompanyFId,BigInteger claimRoomId,String creatTime,Integer auditStatus,String auditDesc,String auditTime,BigInteger auditOmsUserId,BigInteger groupBuildingId,BigInteger communitySupplyId,BigInteger tGroupBuildingHasTCommunitySupplyFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,Integer sys0DelState,BigInteger sys0DelUser,String supplyCompanyPhone){
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
		CommunitySupplyBelong other = (CommunitySupplyBelong) obj;
		if (id == null) {
		return true;
	}
	
}