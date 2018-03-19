/**   
* Filename:    SupriseGiftForDB.java   
* @version:    1.0  
* Create at:   2015年4月8日 上午6:51:30   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entityDB;

import java.math.BigInteger;

import com.cnfantasia.server.api.commonBusiness.entityDB.DbCallBaseEntity;

/**
 * Filename:    SupriseGiftForDB.java
 * @version:    1.0.0
 * Create at:   2015年4月8日 上午6:51:30
 * Description:从数据库获取的意外惊喜数据项
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月8日       shiyl             1.0             1.0 Version
 */
public class SupriseGiftForDB extends DbCallBaseEntity{
	private static final long serialVersionUID = 1L;
	
	/**意外惊喜类型*/
	private Integer surpriseType;
	/**意外惊喜名称*/
	private String surpriseName;
	/**意外惊喜有效期*/
	private String expiryTime;
	/**粮票金额*/
	private Double hbAmount;
	/**兑换码*/
	private String exchangeCode;
	
	/**奖品Id*/
	private BigInteger giftId;
	
	public SupriseGiftForDB(){}
	public SupriseGiftForDB(String id,String status,Integer surpriseType,String surpriseName,String expiryTime
			,Double hbAmount){
		super(id, status);
		this.surpriseType = surpriseType;
		this.surpriseName = surpriseName;
		this.expiryTime = expiryTime;
		this.hbAmount = hbAmount;
	}
	
	public Integer getSurpriseType() {
		return surpriseType;
	}
	public void setSurpriseType(Integer surpriseType) {
		this.surpriseType = surpriseType;
	}
	public String getSurpriseName() {
		return surpriseName;
	}
	public void setSurpriseName(String surpriseName) {
		this.surpriseName = surpriseName;
	}
	public String getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(String expiryTime) {
		this.expiryTime = expiryTime;
	}
	public Double getHbAmount() {
		return hbAmount;
	}
	public void setHbAmount(Double hbAmount) {
		this.hbAmount = hbAmount;
	}
	public String getExchangeCode() {
		return exchangeCode;
	}
	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}
	public BigInteger getGiftId() {
		return giftId;
	}
	public void setGiftId(BigInteger giftId) {
		this.giftId = giftId;
	}
	public boolean isDataAvailable(){
		/**有类别且状态正常*/
		//不要使用id或者status判断是否有值
		return super.isDataAvailable() && getSurpriseType() != null;
	}
	
}
