/**   
* Filename:    ExchangeSuccEntity.java   
* @version:    1.0  
* Create at:   2014年10月27日 上午7:31:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月27日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.exchange.entity;

/**
 * Filename:    ExchangeSuccEntity.java
 * @version:    1.0.0
 * Create at:   2014年10月27日 上午7:31:27
 * Description: 换物信息成功的实体类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月27日       shiyl             1.0             1.0 Version
 */
public class ExchangeSuccEntity {
	
	/**
	 * 构造方法
	 * @param launchExchgContent 发起的换物
	 * @param joinExchgContent 参与的换物
	 */
	public ExchangeSuccEntity(ExchangeBaseEntity launchExchgContent,ExchangeBaseEntity joinExchgContent){
		this.launchExchgContent = launchExchgContent;
		this.joinExchgContent = joinExchgContent;
	}
	
	/**发起的换物*/
	private ExchangeBaseEntity launchExchgContent;
	/**参与的换物*/
	private ExchangeBaseEntity joinExchgContent;
	public ExchangeBaseEntity getLaunchExchgContent() {
		return launchExchgContent;
	}
	public void setLaunchExchgContent(ExchangeBaseEntity launchExchgContent) {
		this.launchExchgContent = launchExchgContent;
	}
	public ExchangeBaseEntity getJoinExchgContent() {
		return joinExchgContent;
	}
	public void setJoinExchgContent(ExchangeBaseEntity joinExchgContent) {
		this.joinExchgContent = joinExchgContent;
	}
	
}
