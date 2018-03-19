/**   
* Filename:    IEatTodayService.java   
* @version:    1.0  
* Create at:   2014年5月24日 上午4:59:29   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月24日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.eatToday.service;

import java.util.List;

import com.cnfantasia.server.api.eatToday.entity.EatMenuRecommendEntity;

/**
 * Filename:    IEatTodayService.java
 * @version:    1.0.0
 * Create at:   2014年5月24日 上午4:59:29
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月24日       shiyl             1.0             1.0 Version
 */
public interface IEatTodayService {
	/**
	 * 查询今天推荐的菜谱
	 * @return
	 */
	public List<EatMenuRecommendEntity> getTodayRecommend();
}
