/**   
* Filename:    CommonLoginDao.java   
* @version:    1.0  
* Create at:   2014年9月1日 上午8:53:42   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.commUserSession.dao.ICommUserSessionBaseDao;
import com.cnfantasia.server.domainbase.commUserSession.entity.CommUserSession;

/**
 * Filename:    CommonLoginDao.java
 * @version:    1.0.0
 * Create at:   2014年9月1日 上午8:53:42
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月1日       shiyl             1.0             1.0 Version
 */
public class CommonLoginDao extends AbstractBaseDao implements ICommonLoginDao{
	
	private ICommUserSessionBaseDao commUserSessionBaseDao;
	public void setCommUserSessionBaseDao(ICommUserSessionBaseDao commUserSessionBaseDao) {
		this.commUserSessionBaseDao = commUserSessionBaseDao;
	}

	@Override
	public Integer selectUserCountByAccInfo(String accountNo, String password, Long accountType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("accountNo", accountNo);
		tmpMap.put("password", password);
		tmpMap.put("accountType", accountType);
		return sqlSession.selectOne("commonLogin.select_UserCount_By_AccInfo", tmpMap);
	}

	@Override
	public Integer deleteUserSessionLogicByUserId(String accountNo,Long accountType, int subChannel) {
		//根据accountNo,accountType 查询其用户对应所有的记录
		List<CommUserSession> commUserSessionList = null;
		{
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("accountNo", accountNo);
			tmpMap.put("accountType", accountType);
			tmpMap.put("subChannelId", subChannel);
			commUserSessionList = sqlSession.selectList("commonLogin.select_UserSessionList_ofSameUser_ByAccnoType", tmpMap);
		}
		List<BigInteger> toDelIdList = null;
		if(commUserSessionList!=null&&commUserSessionList.size()>0){
			toDelIdList = new ArrayList<BigInteger>();
			if(accountType.compareTo(LoginDict.AccountType.WEI_XIN_LIGHT_APP)==0){//如果当前类别为轻应用则只删除当前的
				for(CommUserSession tmpUserSession:commUserSessionList){//取类型为轻应用的
					if(tmpUserSession.getAccType().compareTo(LoginDict.AccountType.WEI_XIN_LIGHT_APP)==0){
						toDelIdList.add(tmpUserSession.getId());
					}
				}
			}else{//如果当前类别不是轻应用，则删除除了轻应用其它类别的
				for(CommUserSession tmpUserSession:commUserSessionList){//取类型不是轻应用的
					if(tmpUserSession.getAccType().compareTo(LoginDict.AccountType.WEI_XIN_LIGHT_APP)!=0){
						toDelIdList.add(tmpUserSession.getId());
					}
				}
			}
		}
		Integer resCount = 0;
		if(toDelIdList!=null&&toDelIdList.size()>0){
			resCount = commUserSessionBaseDao.deleteCommUserSessionLogicBatch(toDelIdList);
		}
		return resCount;
		
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("accountNo", accountNo);
//		tmpMap.put("accountType", accountType);
//		return sqlSession.update("commonLogin.delete_UserSession_Logic_ByUserId",tmpMap);
	}

	@Override
	public Integer updateLoginSessionByUserId(BigInteger userId, String no, Long type) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("accountNo", no);
		tmpMap.put("accountType", type);
		return sqlSession.update("commonLogin.update_LoginSession_ByUserId", tmpMap);
		
	}

	@Override
	public int selectValidSessionKeyCountBy(BigInteger userId, Long subChannelId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("subChannelId", subChannelId);
		return sqlSession.selectOne("commonLogin.selectValidSessionKeyCountBy", tmpMap);
	}

	@Override
	public void deleteUserSessionLogicByUserId(String accNo, Long accType) {
		deleteUserSessionLogicByUserId(accNo, accType, 0);
	}
	
}
