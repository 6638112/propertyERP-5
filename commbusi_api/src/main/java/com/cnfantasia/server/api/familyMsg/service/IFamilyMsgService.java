/**   
* Filename:    IFamilyMsgService.java   
* @version:    1.0  
* Create at:   2015年3月12日 上午9:31:08   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.familyMsg.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import com.cnfantasia.server.api.familyMsg.entity.FamilyMsgEntity;
import com.cnfantasia.server.api.familyMsg.entity.MsgExtDataAdd;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;

/**
 * Filename:    IFamilyMsgService.java
 * @version:    1.0.0
 * Create at:   2015年3月12日 上午9:31:08
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月12日       shiyl             1.0             1.0 Version
 */
public interface IFamilyMsgService {

	/**
	 * 发表留言信息
	 * @param userId
	 * @param content
	 * @param extDataType TODO
	 * @param userIdList
	 * @param msgExtDataAddList
	 */
	public void addMsg(BigInteger userId, String content, Integer extDataType, Set<BigInteger> userIdList,List<MsgExtDataAdd> msgExtDataAddList, List<RequestFileEntity> picList);

	/**
	 * 查询留言列表信息
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	public List<FamilyMsgEntity> qryMsgList(BigInteger userId, PageModel pageModel);

	/**
	 * 查询单个留言的详情
	 * @param userId
	 * @param msgId
	 * @return
	 */
	public FamilyMsgEntity qryMsgDetail(BigInteger userId, BigInteger msgId);
	
}
