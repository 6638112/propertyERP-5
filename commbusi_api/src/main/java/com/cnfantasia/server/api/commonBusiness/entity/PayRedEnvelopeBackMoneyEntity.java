/**   
* Filename:    PayRedEnvelopeBackMoneyEntity.java   
* @version:    1.0  
* Create at:   2014年7月2日 上午8:10:37   
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

import com.alibaba.fastjson.JSON;

/**
 * Filename:    PayRedEnvelopeBackMoneyEntity.java
 * @version:    1.0.0
 * Create at:   2014年7月2日 上午8:10:37
 * Description:	退回粮票金额的实体类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月2日       shiyl             1.0             1.0 Version
 */
public class PayRedEnvelopeBackMoneyEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**粮票Id*/
	private BigInteger id;
	/**应退回的粮票金额*/
	private Long backMmoney;
	
	public PayRedEnvelopeBackMoneyEntity(){}
	public PayRedEnvelopeBackMoneyEntity(BigInteger id,Long backMmoney){
		this.id = id;
		this.backMmoney = backMmoney;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public Long getBackMmoney() {
		return backMmoney;
	}
	public void setBackMmoney(Long backMmoney) {
		this.backMmoney = backMmoney;
	}
	
}
