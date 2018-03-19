/**   
* Filename:    ISupportDao.java   
* @version:    1.0  
* Create at:   2014年9月23日 上午3:50:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.support.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.kitchen.entity.KitchenEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;

/**
 * Filename:    ISupportDao.java
 * @version:    1.0.0
 * Create at:   2014年9月23日 上午3:50:27
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月23日       shiyl             1.0             1.0 Version
 */
public interface ISupportDao {
	
	/**
	 * 查询点赞的菜谱列表
	 * @return
	 */
	public List<KitchenEntity> selectKitchenCookbookSupportList(BigInteger userId,PageModel pageModel);
	
}
