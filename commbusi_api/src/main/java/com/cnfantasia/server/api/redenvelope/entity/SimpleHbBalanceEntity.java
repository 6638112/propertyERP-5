/**   
* Filename:    SimpleHbBalanceEntity.java   
* @version:    1.0  
* Create at:   2014年6月26日 上午3:03:45   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redenvelope.entity;

import java.math.BigDecimal;

import com.cnfantasia.server.common.utils.PriceUtil;

/**
 * Filename:    SimpleHbBalanceEntity.java
 * @version:    1.0.0
 * Create at:   2014年6月26日 上午3:03:45
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月26日       shiyl             1.0             1.0 Version
 */
public class SimpleHbBalanceEntity {
	/**
	 * 主题
	 */
	private String subject = "";
	/**

	 * 描述
	 */
	private String desc = "";

	/**
	 * 粮票分类，0全国通用，1限体验店用
	 */
	private int type = 0;

	/**余额*/
	private Long balance;
	/**已兑换金额*/
	private Long totalConvertMoney;
	/**已消费金额*/
	private Long totalConsumMoney;
	
	public SimpleHbBalanceEntity(Long balance,Long totalConvertMoney,Long totalConsumMoney){
		this.balance = balance;
		this.totalConvertMoney = totalConvertMoney;
		this.totalConsumMoney = totalConsumMoney;
	}

	public SimpleHbBalanceEntity() {

	}

	public BigDecimal getBalanceDiv100() {
		if(balance==null){return BigDecimal.valueOf(0L);}
		return PriceUtil.div100(balance);
	}
	public BigDecimal getTotalConvertMoneyDiv100() {
		if(totalConvertMoney==null){return BigDecimal.valueOf(0L);}
		return PriceUtil.div100(totalConvertMoney);
	}
	public BigDecimal getTotalConsumMoneyDiv100() {
		if(totalConsumMoney==null){return BigDecimal.valueOf(0L);}
		return PriceUtil.div100(totalConsumMoney);
	}
	
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public Long getTotalConvertMoney() {
		return totalConvertMoney;
	}
	public void setTotalConvertMoney(Long totalConvertMoney) {
		this.totalConvertMoney = totalConvertMoney;
	}
	public Long getTotalConsumMoney() {
		return totalConsumMoney;
	}
	public void setTotalConsumMoney(Long totalConsumMoney) {
		this.totalConsumMoney = totalConsumMoney;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSubject() {
		return subject;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
