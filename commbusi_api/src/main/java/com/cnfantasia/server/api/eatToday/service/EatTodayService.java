/**   
* Filename:    EatTodayService.java   
* @version:    1.0  
* Create at:   2014年5月24日 上午5:02:09   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月24日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.eatToday.service;

import java.util.List;

import com.cnfantasia.server.api.eatToday.dao.IEatTodayDao;
import com.cnfantasia.server.api.eatToday.entity.EatMenuRecommendEntity;

/**
 * Filename:    EatTodayService.java
 * @version:    1.0.0
 * Create at:   2014年5月24日 上午5:02:09
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月24日       shiyl             1.0             1.0 Version
 */
public class EatTodayService implements IEatTodayService{
	private IEatTodayDao eatTodayDao;
	public void setEatTodayDao(IEatTodayDao eatTodayDao) {
		this.eatTodayDao = eatTodayDao;
	}

	@Override
	public List<EatMenuRecommendEntity> getTodayRecommend() {
		return eatTodayDao.selectTodayRecommend();
	}

}
