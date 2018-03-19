package com.cnfantasia.server.domainbase.propertyAccountInfo.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(用户物业账户信息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class PropertyAccountInfo implements Serializable{
*/


public class PropertyAccountInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	public PropertyAccountInfo(){
	}
	public PropertyAccountInfo(PropertyAccountInfo arg){
		this.id = arg.getId();
	}
	/**
	public PropertyAccountInfo(BigInteger id,Long balanceAmt,BigInteger tUserFId,String notifyPhone,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,Integer isfirstcharge){
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
		PropertyAccountInfo other = (PropertyAccountInfo) obj;
		if (id == null) {
		return true;
	}
	
}