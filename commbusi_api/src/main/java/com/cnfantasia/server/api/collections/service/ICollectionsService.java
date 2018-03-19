/**   
* Filename:    ICollectionsService.java   
* @version:    1.0  
* Create at:   2014年7月23日 上午8:10:31   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.collections.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.user.entity.CollectionsEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;

/**
 * Filename:    ICollectionsService.java
 * @version:    1.0.0
 * Create at:   2014年7月23日 上午8:10:31
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月23日       shiyl             1.0             1.0 Version
 */
public interface ICollectionsService {
	/**
	 * 点收藏或者取消收藏，返回已收藏总数
	 * @param userId
	 * @param goalType
	 * @param goalId
	 * @param isCollections
	 */
	public int doCollections(BigInteger userId,Integer goalType,BigInteger goalId,boolean isCollections);
	
	/**
	 * 查询是否收藏过
	 * @param userId
	 * @param goalType
	 * @param goalId
	 * @return
	 */
	public boolean qryIsCollections(BigInteger userId,Integer goalType,BigInteger goalId);
	
	/**
	 * 查询收藏的总数
	 * @param goalType
	 * @param goalId
	 * @return
	 */
	public int qryCollectionsCount(Integer goalType,BigInteger goalId);
	
	/**
	 * 根据用户Id,收藏类别 分页查询收藏列表
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	public List<CollectionsEntity> getCollectionsCommunitySupplyList(BigInteger userId,PageModel pageModel);
}
