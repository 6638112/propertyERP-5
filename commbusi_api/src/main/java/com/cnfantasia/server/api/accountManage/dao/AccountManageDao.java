/**   
* Filename:    AccountManageDao.java   
* @version:    1.0  
* Create at:   2015年4月29日 上午6:39:52   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.accountManage.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.accountManage.entity.LoginNoSimpleEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;

/**
 * Filename:    AccountManageDao.java
 * @version:    1.0.0
 * Create at:   2015年4月29日 上午6:39:52
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月29日       shiyl             1.0             1.0 Version
 */
public class AccountManageDao extends AbstractBaseDao implements IAccountManageDao{
	
	private void replaceUserAboutData(LoginNoSimpleEntity loginNoEntity) {
		if(loginNoEntity!=null){
			BigInteger currUserId = loginNoEntity.gettUserFId();
			//查询当前用户对应的主账号的userId
			BigInteger mainUserId = selectMainUserIdById(currUserId);
			UserSimpleEntity mainUserEntity = selectUserSimpleEntityById(mainUserId);
			loginNoEntity.setUserSimpleEntity(mainUserEntity);
			loginNoEntity.settUserFId(mainUserId);
		}
	}
	
	private UserSimpleEntity selectUserSimpleEntityById(BigInteger userId){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("accountManage.select_UserSimpleEntity_ById", tmpMap);
	}
	
	@Override
	public LoginNoSimpleEntity selectLoginNoSimpleEntityById(BigInteger loginNoId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("loginNoId", loginNoId);
		LoginNoSimpleEntity loginNoEntity =  sqlSession.selectOne("accountManage.select_LoginNoSimpleEntity_ById", tmpMap);
		replaceUserAboutData(loginNoEntity);
		return loginNoEntity;
	}

	@Override
	public List<LoginNo> selectBindAccList(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectList("accountManage.select_BindAcc_List", tmpMap);
	}
	
	@Override
	public LoginNo selectLoginNoDetail(String toBindAccountNo, Long toBindAccType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("toBindAccountNo", toBindAccountNo);
		tmpMap.put("toBindAccType", toBindAccType);
		return sqlSession.selectOne("accountManage.select_LoginNo_Detail", tmpMap);
	}
	
	@Override
	public Integer selectBindRelationCount(String toBindAccountNo, Long toBindAccType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("toBindAccountNo", toBindAccountNo);
		tmpMap.put("toBindAccType", toBindAccType);
		return sqlSession.selectOne("accountManage.select_BindRelation_Count", tmpMap);
	}
	
	@Override
	public LoginNo selectUserHuaLoginNoInfo(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("accountManage.select_UserHua_LoginNoInfo", tmpMap);
	}
	
	@Override
	public LoginNo selectUserPhoneAccInfo(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("accountManage.select_UserPhone_AccInfo", tmpMap);
	}
	
//	@Override
//	public Integer updateApplyAndMainAccountData(BigInteger mainUserId, BigInteger applyLoginNoId) {
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("mainUserId", mainUserId);
//		tmpMap.put("applyLoginNoId", applyLoginNoId);
//		return sqlSession.update("accountManage.update_ApplyAndMain_AccountData", tmpMap);
//	}
	
	@Override
	public BigInteger selectMainUserIdById(BigInteger userId) {
		BigInteger mainUserId =  fetchMainUserIdById(userId);
		{//递归获取主账号
			BigInteger nextMainUserId = fetchMainUserIdById(mainUserId);
			while(nextMainUserId.compareTo(mainUserId)!=0){
				mainUserId = nextMainUserId;
				nextMainUserId = fetchMainUserIdById(mainUserId);
			}
		}
		return mainUserId;
	}
	private BigInteger fetchMainUserIdById(BigInteger userId){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		BigInteger mainUserId =  sqlSession.selectOne("accountManage.select_MainUserId_ById", tmpMap);
		return mainUserId;
	}
	
	@Override
	public Integer updateApplyPwdByMainUser(BigInteger userId) {
		String mainPassword = null;
		BigInteger mainUserId = selectMainUserIdById(userId);
		{
			LoginNo mainLoginNo = selectUserPhoneAccInfo(mainUserId);//优先取手机号的密码
			if(mainLoginNo==null){//没有则取主账号的花号密码
				mainLoginNo = selectUserHuaLoginNoInfo(mainUserId);
			}
			mainPassword = mainLoginNo.getPassword();
		}
		return updateApplyPwdWithMainUser(userId, mainPassword);//将绑定相关的用户密码全部设置为相同的密码
	}

	@Override
	public Integer updateApplyPwdWithMainUser(BigInteger userId, String newPassword) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("mainPassword", newPassword);
		return sqlSession.update("accountManage.update_ApplyPwd_ByMainUser", tmpMap);
	}

}
