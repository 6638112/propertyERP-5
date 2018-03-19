/**   
* Filename:    IEatTodayDao.java   
* @version:    1.0  
* Create at:   2014年5月24日 上午4:59:09   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月24日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.eatToday.dao;

import java.util.List;

import com.cnfantasia.server.api.eatToday.entity.EatMenuRecommendEntity;

/**
 * Filename:    IEatTodayDao.java
 * @version:    1.0.0
 * Create at:   2014年5月24日 上午4:59:09
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月24日       shiyl             1.0             1.0 Version
 */
public interface IEatTodayDao {
	/**
	 * 查询今天推荐的菜谱
	 * @return
	 */
	public List<EatMenuRecommendEntity> selectTodayRecommend();
}
