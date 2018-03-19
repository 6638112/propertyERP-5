package com.cnfantasia.server.domainbase.ebuyFightOrder.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(拼购详情订单) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyFightOrder implements Serializable{
*/


public class EbuyFightOrder extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	public EbuyFightOrder(){
	}
	public EbuyFightOrder(EbuyFightOrder arg){
		this.id = arg.getId();
	}
	/**
	public EbuyFightOrder(BigInteger id,BigInteger tEbuyOrderFId,String delivAddressArea,BigInteger tEbuyProductFightGroupsFId,String buyTime,Integer status,Integer pointType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
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
		EbuyFightOrder other = (EbuyFightOrder) obj;
		if (id == null) {
		return true;
	}
	
}