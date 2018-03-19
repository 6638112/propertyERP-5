/**   
* Filename:    ICommonKitchenService.java   
* @version:    1.0  
* Create at:   2014年9月24日 上午1:52:51   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月24日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.kitchen.entity.KitchenCookbookCollectEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenCookbookStepEntity;
import com.cnfantasia.server.domainbase.kitchenCookbook.entity.KitchenCookbook;
import com.cnfantasia.server.domainbase.kitchenCookbookStep.entity.KitchenCookbookStep;
import com.cnfantasia.server.domainbase.kitchenCookbookStepTips.entity.KitchenCookbookStepTips;
import com.cnfantasia.server.domainbase.kitchenCookbookTopType.entity.KitchenCookbookTopType;
import com.cnfantasia.server.domainbase.kitchenCookbookType.entity.KitchenCookbookType;
import com.cnfantasia.server.domainbase.kitchenDetail.entity.KitchenDetail;

/**
 * Filename:    ICommonKitchenService.java
 * @version:    1.0.0
 * Create at:   2014年9月24日 上午1:52:51
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月24日       shiyl             1.0             1.0 Version
 */
public interface ICommonKitchenService {
	public Map<String, Object> cookbookInfo2Map(KitchenCookbook kitchenCookbook, Integer totalLikeEatCount,
			List<KitchenDetail> kitchenParamList, Integer isLikeEat, List<KitchenCookbookStepEntity> kitchenCookbookStepList,Integer collectFlag);
	
	public Map<String, Object> cookbookInfo2Map(KitchenCookbook kitchenCookbook, Integer totalLikeEatCount,
			List<KitchenDetail> kitchenParamList, Integer isLikeEat, List<KitchenCookbookStepEntity> kitchenCookbookStepList
			,Integer collectFlag
			,Boolean defaultNullFlag);
	
	public Map<String, Object> cookbookStepInfo2Map(KitchenCookbookStep kitchenCookbookStep,List<KitchenCookbookStepTips> kitchenCookbookStepTipsList);
	
	public Map<String, Object> cookbookTypeInfo2Map(KitchenCookbookType kitchenCookbookType,KitchenCookbookTopType kitchenCookbookTopType,Integer collectFlag);

	/**
	 * 菜谱步骤的tips转Map
	 * @param tmpTips
	 * @return
	 */
	public Map<String, Object> kitchenCookbookStepTips2Map(KitchenCookbookStepTips tmpTips);

	/**
	 * 菜谱收藏信息转Map
	 * @param collectEntity
	 * @return
	 */
	public Map<String, Object> cookbookCollect2Map(KitchenCookbookCollectEntity collectEntity,Boolean defaultNullFlag);
}
