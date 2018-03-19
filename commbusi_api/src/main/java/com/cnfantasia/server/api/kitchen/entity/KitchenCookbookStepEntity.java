/**   
* Filename:    KitchenCookbookStepEntity.java   
* @version:    1.0  
* Create at:   2014年11月14日 上午8:42:38   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月14日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.kitchen.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.kitchenCookbookStep.entity.KitchenCookbookStep;
import com.cnfantasia.server.domainbase.kitchenCookbookStepTips.entity.KitchenCookbookStepTips;

/**
 * Filename:    KitchenCookbookStepEntity.java
 * @version:    1.0.0
 * Create at:   2014年11月14日 上午8:42:38
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月14日       shiyl             1.0             1.0 Version
 */
public class KitchenCookbookStepEntity extends KitchenCookbookStep{

	private static final long serialVersionUID = 1L;
	
	/**步骤的tips*/
	private List<KitchenCookbookStepTips> kitchenCookbookStepTipsList;
	public List<KitchenCookbookStepTips> getKitchenCookbookStepTipsList() {
		return kitchenCookbookStepTipsList;
	}
	
	
}
