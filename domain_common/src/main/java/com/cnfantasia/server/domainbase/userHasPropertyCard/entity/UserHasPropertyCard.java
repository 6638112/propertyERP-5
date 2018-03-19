package com.cnfantasia.server.domainbase.userHasPropertyCard.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(用户购买物业代扣卡) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class UserHasPropertyCard implements Serializable{
*/


public class UserHasPropertyCard extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	public UserHasPropertyCard(){
	}
	public UserHasPropertyCard(UserHasPropertyCard arg){
		this.id = arg.getId();
	}
	/**
	public UserHasPropertyCard(BigInteger id,Long cardAmount,Long discountAmt,Long realPayAmt,Long balanceAmt,String orderNo,BigInteger tEbuyOrderFId,String buyTime,BigInteger tUserFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,BigInteger tPropertyCardFId){
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
		UserHasPropertyCard other = (UserHasPropertyCard) obj;
		if (id == null) {
		return true;
	}
	
}