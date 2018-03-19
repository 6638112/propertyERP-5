/**   
* Filename:    DualDao.java   
* @version:    1.0  
* Create at:   2014年5月8日 上午6:18:50   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.ms.pub.dao;

import java.util.Date;

import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.utils.DateUtil;

/**
 * Filename:    DualDao.java
 * @version:    1.0.0
 * Create at:   2014年5月8日 上午6:18:50
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月8日       shiyl             1.0             1.0 Version
 */
public class DualDao extends AbstractBaseDao implements IDualDao{

	@Override
	public String getNowTime() {
		return DateUtil.formatSecond.get().format(new Date());
//		return sqlSession.selectOne("pub.select_now_time");
	}

	@Override
	public String getNowDay() {
		return DateUtil.formatDay.get().format(new Date());
//		return sqlSession.selectOne("pub.select_now_date");
	}

	@Override
	public Date getNowDate() {
		return new Date();
	}
	
}
