/**   
* Filename:    UserIdType.java   
* @version:    1.0  
* Create at:   2014年7月6日 下午3:08:31   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.entity;

import java.io.Serializable;
import java.math.BigInteger;

import com.alibaba.fastjson.JSON;

/**
 * Filename:    UserIdType.java
 * @version:    1.0.0
 * Create at:   2014年7月6日 下午3:08:31
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月6日       shiyl             1.0             1.0 Version
 */
public class UserIdType implements Serializable{
	private static final long serialVersionUID = 1L;
	private BigInteger userId;
	private Integer userType;
	public UserIdType(){}
	public UserIdType(BigInteger userId,Integer userType){
		this.userId = userId;
		this.userType = userType;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
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
		UserIdType other = (UserIdType) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userType == null) {
			if (other.userType != null)
				return false;
		} else if (!userType.equals(other.userType))
			return false;
		return true;
	}
	
}
