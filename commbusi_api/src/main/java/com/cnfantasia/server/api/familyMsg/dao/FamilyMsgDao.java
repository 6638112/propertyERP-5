/**   
* Filename:    FamilyMsgDao.java   
* @version:    1.0  
* Create at:   2015年3月12日 上午9:32:01   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.familyMsg.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.familyMsg.entity.FamilyMsgEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;

/**
 * Filename:    FamilyMsgDao.java
 * @version:    1.0.0
 * Create at:   2015年3月12日 上午9:32:01
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月12日       shiyl             1.0             1.0 Version
 */

public class FamilyMsgDao extends AbstractBaseDao implements IFamilyMsgDao{

	@Override
	public List<FamilyMsgEntity> selectMsgList(BigInteger userId, PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		String pageSqlKey = "familyMsg.select_Msg_List_Page"; 
		String countSqlKey = "familyMsg.select_Msg_List_Count"; 
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public FamilyMsgEntity selectMsgDetail(BigInteger userId, BigInteger msgId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("msgId", msgId);
		return sqlSession.selectOne("familyMsg.select_Msg_Detail", tmpMap);
	}

	@Override
	public Integer synFamilyMsgExtData(BigInteger familyMsgId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("familyMsgId", familyMsgId);
		return sqlSession.update("familyMsg.syn_FamilyMsgExtData_ByFamilyMsgId", tmpMap);
	}

	@Override
	public Integer synFamilyMsgHasTUserData(BigInteger familyMsgId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("familyMsgId", familyMsgId);
		return sqlSession.update("familyMsg.syn_FamilyMsgHasTUserData_ByFamilyMsgId", tmpMap);
		
	}
	
	
}
