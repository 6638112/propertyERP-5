/**   
* Filename:    ISupportService.java   
* @version:    1.0  
* Create at:   2014年7月23日 上午8:10:31   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.support.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.kitchen.entity.KitchenEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;

/**
 * Filename:    ISupportService.java
 * @version:    1.0.0
 * Create at:   2014年7月23日 上午8:10:31
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月23日       shiyl             1.0             1.0 Version
 */
public interface ISupportService {
	/**
	 * 点赞或者取消赞，返回已赞总数
	 * @param userId
	 * @param goalType
	 * @param goalId
	 * @param isSupport
	 */
	public int doSupport(BigInteger userId,Integer goalType,BigInteger goalId,boolean isSupport);
	
	/**
	 * 查询是否赞过
	 * @param userId
	 * @param goalType
	 * @param goalId
	 * @return
	 */
	public boolean qryIsSupport(BigInteger userId,Integer goalType,BigInteger goalId);
	
	/**
	 * 查询赞的总数
	 * @param goalType
	 * @param goalId
	 * @return
	 */
	public int qrySupportCount(Integer goalType,BigInteger goalId);
	
	/**
	 * 查询点赞的菜谱列表
	 * @param userId
	 * @return
	 */
	public List<KitchenEntity> getKitchenCookbookSupportList(BigInteger userId,PageModel pageModel);
}
