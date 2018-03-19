/**   
* Filename:    ExchangeEntity.java   
* @version:    1.0  
* Create at:   2014年10月17日 上午3:40:09   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月17日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.exchange.entity;

import java.util.List;

/**
 * Filename:    ExchangeEntity.java
 * @version:    1.0.0
 * Create at:   2014年10月17日 上午3:40:09
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月17日       shiyl             1.0             1.0 Version
 */
public class ExchangeEntity extends ExchangeBaseEntity{
	private static final long serialVersionUID = 1L;
	
	/**参与交换的换物列表*/
	private List<ExchangeRelationGoalEntity>  exchangeRelationGoalEntityList;
	public List<ExchangeRelationGoalEntity> getExchangeRelationGoalEntityList() {
		return exchangeRelationGoalEntityList;
	}
	public void setExchangeRelationGoalEntityList(List<ExchangeRelationGoalEntity> exchangeRelationGoalEntityList) {
		this.exchangeRelationGoalEntityList = exchangeRelationGoalEntityList;
	}
	
	
}
