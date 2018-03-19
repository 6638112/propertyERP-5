/**   
* Filename:    ICollectionsDao.java   
* @version:    1.0  
* Create at:   2014年8月27日 上午3:47:12   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月27日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.collections.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.user.entity.CollectionsEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;

/**
 * Filename:    ICollectionsDao.java
 * @version:    1.0.0
 * Create at:   2014年8月27日 上午3:47:12
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月27日       shiyl             1.0             1.0 Version
 */
public interface ICollectionsDao {
//	/**
//	 * 根据用户Id,收藏类别 分页查询收藏列表
//	 * @param userId
//	 * @param type 收藏对象类型
//	 * @param pageModel
//	 * @return
//	 */
//	public List<CollectionsEntity> selectCollectionsList(BigInteger userId,Integer type,PageModel pageModel);
	
	/**
	 * 根据用户Id,收藏类别 分页查询收藏列表--社区商家
	 * @param userId
	 * @param type 收藏对象类型
	 * @param pageModel
	 * @return
	 */
	public List<CollectionsEntity> selectCollectionsCommunitySupplyList(BigInteger userId,PageModel pageModel);
	
	
}
