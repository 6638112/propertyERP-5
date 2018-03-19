/**   
* Filename:    SupportDao.java   
* @version:    1.0  
* Create at:   2014年9月23日 上午3:50:42   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.support.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.kitchen.entity.KitchenEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;

/**
 * Filename:    SupportDao.java
 * @version:    1.0.0
 * Create at:   2014年9月23日 上午3:50:42
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月23日       shiyl             1.0             1.0 Version
 */
public class SupportDao extends AbstractBaseDao implements ISupportDao{

	@Override
	public List<KitchenEntity> selectKitchenCookbookSupportList(BigInteger userId,PageModel pageModel){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		String pageSqlKey = "support.select_Kitchen_Cookbook_Support_List_page";
		String countSqlKey = "support.select_Kitchen_Cookbook_Support_List_count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}
	
	
}
