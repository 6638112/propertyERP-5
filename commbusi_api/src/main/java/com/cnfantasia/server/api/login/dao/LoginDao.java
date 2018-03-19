/**   
* Filename:    LoginDao.java   
* @version:    1.0  
* Create at:   2014年5月6日 上午9:12:02   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.login.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.api.accountManage.dao.IAccountManageDao;
import com.cnfantasia.server.api.login.entity.LoginNoEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.user.dao.IUserDao;
import com.cnfantasia.server.api.user.entity.UserEntity;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;
import com.cnfantasia.server.domainbase.prizeRecordTmp.entity.PrizeRecordTmp;

/**
 * Filename:    LoginDao.java
 * @version:    1.0.0
 * Create at:   2014年5月6日 上午9:12:02
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月6日       shiyl             1.0             1.0 Version
 */
@Repository
public class LoginDao extends AbstractBaseDao implements ILoginDao{
	
	private IAccountManageDao accountManageDao;
	public void setAccountManageDao(IAccountManageDao accountManageDao) {
		this.accountManageDao = accountManageDao;
	}
	
	private IUserDao userDao;
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	private void replaceUserAboutData(LoginNoEntity loginNoEntity) {
		if(loginNoEntity!=null){
			BigInteger currUserId = loginNoEntity.gettUserFId();
			//查询当前用户对应的主账号的userId
			BigInteger mainUserId = accountManageDao.selectMainUserIdById(currUserId);
			if(mainUserId.compareTo(currUserId)!=0){
				UserEntity mainUserEntity = userDao.selectUserById(mainUserId);//查询主账号详情
				//不相同则查询对应的user详细替换 userEntity及tUserId
				loginNoEntity.setUserEntity(mainUserEntity);
				loginNoEntity.settUserFId(mainUserId);
			}
		}
	}
	
	@Override
	public LoginNoEntity selectLoginNoEntitySupportBind(String number,String password,Long loginType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("no", number);
		tmpMap.put("password", password);
		tmpMap.put("type", loginType);
		LoginNoEntity res =  sqlSession.selectOne("login.select_loginNo_byNamePwd",tmpMap);
		replaceUserAboutData(res);//syl-add 用于支持账号绑定 2015-5-8 16:06:23
		return res;
	}
	
	@Override
	public LoginNoEntity selectLoginNoEntityByAccountSupportBind(String accountNo, Long type) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("no", accountNo);
		tmpMap.put("type", type);
		LoginNoEntity res = sqlSession.selectOne("login.select_loginNo_byNoAndType",tmpMap);
		replaceUserAboutData(res);//syl-add 用于支持账号绑定 2015-5-8 16:06:23
		return res;
	}
	
	@Override
	public boolean setNewPassword(BigInteger tUserFId,String password) {
//		LoginNo loginNo = new LoginNo();
//		loginNo.settUserFId(tUserFId);
//		loginNo.setPassword(password);
//		int res = sqlSession.update("login.setNewPwd_byNamePwdType",MapConverter.toMap(loginNo));
//		return res>0?true:false;
		int res = accountManageDao.updateApplyPwdWithMainUser(tUserFId,password);
		return res>0?true:false;
	}

	@Override
	public BigInteger selectUserIdByAccount(String account,Long type) {
//		LoginNoEntity qryEntity01 = new LoginNoEntity();
//		qryEntity01.setNo(account);
//		qryEntity01.setType(type);
//		return sqlSession.selectOne("login.select_UserId_ByAccount",MapConverter.toMap(qryEntity01));
		
		LoginNo loginNo = accountManageDao.selectLoginNoDetail(account, type);
		BigInteger res = loginNo==null?null:loginNo.gettUserFId();
		return res;
	}

	@Override
	public int selectCountByIdPwd(BigInteger id,String oldPassword) {
		LoginNoEntity qryEntity = new LoginNoEntity();
		qryEntity.settUserFId(id);
		qryEntity.setPassword(oldPassword);
		return sqlSession.selectOne("login.select_Count_ByIdPwd",MapConverter.toMap(qryEntity));
	}

//	@Override 
//	public int deletePrizeRecordTmpByUserTmpId(BigInteger userTmpId) {
//		return sqlSession.update("login.delete_PrizeRecordTmp_ByUserTmpId_Logic", userTmpId);
//	}
	@Override 
	public int deletePrizeRecordTmpByUserTmpId(String deviceId,Long deviceType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("deviceId", deviceId);
		tmpMap.put("deviceType", deviceType);
		return sqlSession.update("login.delete_PrizeRecordTmp_ByUserTmpId_Logic", tmpMap);
	}

	@Override
	public PrizeRecordTmp selectLastRecord(BigInteger userTmpId) {
		return sqlSession.selectOne("login.select_LastRecord",userTmpId);
	}

}
