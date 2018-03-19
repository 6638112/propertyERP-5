/**   
* Filename:    EatTodayDao.java   
* @version:    1.0  
* Create at:   2014年5月24日 上午5:02:36   
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
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;

/**
 * Filename:    EatTodayDao.java
 * @version:    1.0.0
 * Create at:   2014年5月24日 上午5:02:36
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月24日       shiyl             1.0             1.0 Version
 */
public class EatTodayDao extends AbstractBaseDao implements IEatTodayDao{

	@Override
	public List<EatMenuRecommendEntity> selectTodayRecommend() {
		return sqlSession.selectList("eatToday.select_TodayRecommend");
	}

}
