/**   
* Filename:    PayCouponDetailCommonEntity.java   
* @version:    1.0  
* Create at:   2014年7月2日 上午3:31:15   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月2日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.entity;

import java.io.Serializable;
import java.math.BigInteger;


/**
 * Filename:    PayCouponDetailCommonEntity.java
 * @version:    1.0.0
 * Create at:   2014年7月2日 上午3:31:15
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月2日       shiyl             1.0             1.0 Version
 */
public class PayCouponDetailCommonEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/** 对应优惠使用的金额 */
	private Long anount;
	/** 对应的优惠信息Id */
	private BigInteger tPayCouponFId;
	/** 使用者Id */
	private BigInteger userId;
	/** 使用时间 */
	private String consumTime;
	/** 其它详情 */
	private Object otherInfo;//粮票优惠详情，对应为使用的粮票Id-BigInteger
	
	public PayCouponDetailCommonEntity(){
	}
	/**
	 * 
	 * @param id 
	 * @param anount 对应粮票使用的金额
	 * @param tPayCouponFId 对应的优惠信息Id
	 * @param userId 使用者Id
	 * @param signalDetail 其它详情
	 */

	public PayCouponDetailCommonEntity(BigInteger id,Long anount,BigInteger tPayCouponFId,
			BigInteger userId,String consumTime,Object otherInfo){
		this.id = id;
		this.anount = anount;
		this.tPayCouponFId = tPayCouponFId;
		this.userId = userId;
		this.consumTime = consumTime;
		this.otherInfo = otherInfo;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("anount=").append(anount).append(";");
		sbf.append("tPayCouponFId=").append(tPayCouponFId).append(";");
		sbf.append("userId=").append(userId).append(";");
		sbf.append("consumTime=").append(consumTime).append(";");
		sbf.append("otherInfo=").append(otherInfo).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public Long getAnount() {
		return anount;
	}
	public void setAnount(Long anount) {
		this.anount = anount;
	}
	public BigInteger gettPayCouponFId() {
		return tPayCouponFId;
	}
	public void settPayCouponFId(BigInteger tPayCouponFId) {
		this.tPayCouponFId = tPayCouponFId;
	}
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public String getConsumTime() {
		return consumTime;
	}
	public void setConsumTime(String consumTime) {
		this.consumTime = consumTime;
	}
	
	public Object getOtherInfo() {
		return otherInfo;
	}
	
	public void setOtherInfo(Object otherInfo) {
		this.otherInfo = otherInfo;
	}
	
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
		PayCouponDetailCommonEntity other = (PayCouponDetailCommonEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
