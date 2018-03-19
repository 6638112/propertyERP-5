package com.cnfantasia.server.domainbase.dredgeBill.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(疏通工单) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class DredgeBill implements Serializable{
*/


public class DredgeBill extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	public DredgeBill(){
	}
	public DredgeBill(DredgeBill arg){
		this.id = arg.getId();
	}
	/**
	public DredgeBill(BigInteger id,String billNo,String address,String content,String expectdate,String tel,String linkName,Integer status,Integer billType,Long payAmount,Long ptbtAmount,String cancelReason,String picUrl,BigInteger roomid,BigInteger tUserFId,BigInteger tWorkerFId,BigInteger tEbuyOrderFId,Integer revenueStatus,Integer pushLevel,Integer submitChannel,String pushTime,String revenueTm,String acceptTime,String expectWorkTime,String finishedTm,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger tDredgeTypeFId,BigInteger tAddressBlockFId,String referrerMobile,Integer cashStatus,BigInteger tDredgeType2ndFId,Integer dredgeTypeNum,String softwareBillId){
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
		DredgeBill other = (DredgeBill) obj;
		if (id == null) {
		return true;
	}
	
}