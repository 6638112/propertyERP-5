/**   
* Filename:    IFamilyMsgDao.java   
* @version:    1.0  
* Create at:   2015年3月12日 上午9:31:52   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.familyMsg.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.familyMsg.entity.FamilyMsgEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;

/**
 * Filename:    IFamilyMsgDao.java
 * @version:    1.0.0
 * Create at:   2015年3月12日 上午9:31:52
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月12日       shiyl             1.0             1.0 Version
 */
public interface IFamilyMsgDao {

	/**
	 * 查询留言列表信息
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	public List<FamilyMsgEntity> selectMsgList(BigInteger userId, PageModel pageModel);

	/**
	 * 查询单个留言的详情
	 * @param userId
	 * @param msgId
	 * @return
	 */
	public FamilyMsgEntity selectMsgDetail(BigInteger userId, BigInteger msgId);
	
	/**
	 * 将关联的数据同步到留言的拓展消息
	 * @param familyMsgId
	 * @return
	 */
	public Integer synFamilyMsgExtData(BigInteger familyMsgId);

	/**
	 * 将关联的用户数据同步到艾特的用户表中
	 * @param familyMsgId
	 */
	public Integer synFamilyMsgHasTUserData(BigInteger familyMsgId);
}
