/**   
* Filename:    PrizeResultForDB.java   
* @version:    1.0  
* Create at:   2015年3月30日 上午8:46:04   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entityDB;

import java.io.Serializable;

import com.cnfantasia.server.api.commonBusiness.entityDB.DbCallBaseEntity;

/**
 * Filename:    PrizeResultForDB.java
 * @version:    1.0.0
 * Create at:   2015年3月30日 上午8:46:04
 * Description:数据库抽奖返回折扣信息
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月30日       shiyl             1.0             1.0 Version
 */
public class PrizeResultForDB extends DbCallBaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**折扣*/
	private Double discount;
	/**奖池类型*/
	private Integer poolType;
	/**抽奖时间*/
	private String prizeTime;
	/**意外惊喜相关信息*/
	private SupriseGiftForDB supriseGiftForDB;
	
	public PrizeResultForDB(){}
	public PrizeResultForDB(String id,String status,Double discount,Integer poolType,String prizeTime,SupriseGiftForDB supriseGiftForDB){
		super(id,status);
		this.discount = discount;
		this.poolType = poolType;
		this.prizeTime = prizeTime;
		this.supriseGiftForDB = supriseGiftForDB;
	}
	
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Integer getPoolType() {
		return poolType;
	}
	public void setPoolType(Integer poolType) {
		this.poolType = poolType;
	}
	public String getPrizeTime() {
		return prizeTime;
	}
	public void setPrizeTime(String prizeTime) {
		this.prizeTime = prizeTime;
	}
	public SupriseGiftForDB getSupriseGiftForDB() {
		return supriseGiftForDB;
	}
	public void setSupriseGiftForDB(SupriseGiftForDB supriseGiftForDB) {
		this.supriseGiftForDB = supriseGiftForDB;
	}
	
	public boolean isDataAvailable(){
		/**有类别且状态正常*/
		if(super.isDataAvailable()&&getDiscount()!=null){//不要使用id或者status判断是否有值
			return true;
		}else{
			return false;
		}
	}
}
