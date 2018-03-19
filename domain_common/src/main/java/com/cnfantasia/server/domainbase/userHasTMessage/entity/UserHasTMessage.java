package com.cnfantasia.server.domainbase.userHasTMessage.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(用户消息关系) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class UserHasTMessage implements Serializable{
*/


public class UserHasTMessage extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	public UserHasTMessage(){
	}
	public UserHasTMessage(UserHasTMessage arg){
		this.id = arg.getId();
	}
	/**
	public UserHasTMessage(BigInteger id,BigInteger tMessageFId,BigInteger tUserFId,Integer userType,Integer sendStatus,String expiryTime,Integer status,Long tryFailedCount,String lastErrorMsg,String lastSuccMsg,String sendData,BigInteger userRoomId,BigInteger tUserPushInfoFId,Integer clientMarkReceived,String thrdMessageId,String channel,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
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
		UserHasTMessage other = (UserHasTMessage) obj;
		if (id == null) {
		return true;
	}
	
}