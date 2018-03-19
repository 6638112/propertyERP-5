/**   
* Filename:    AccountManageService.java   
* @version:    1.0  
* Create at:   2015年4月29日 上午6:39:24   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.accountManage.service;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.accountManage.dao.IAccountManageDao;
import com.cnfantasia.server.api.accountManage.entity.LoginInfoWithBindEntity;
import com.cnfantasia.server.api.accountManage.entity.LoginNoSimpleEntity;
import com.cnfantasia.server.api.commonBusiness.entity.LoginAccNoAndType;
import com.cnfantasia.server.api.commonBusiness.service.ICommDataUpgradeService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonLoginService;
import com.cnfantasia.server.common.exception.TimeOutRuntimeException;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;

/**
 * Filename:    AccountManageService.java
 * @version:    1.0.0
 * Create at:   2015年4月29日 上午6:39:24
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月29日       shiyl             1.0             1.0 Version
 */
public class AccountManageService implements IAccountManageService{
	private Log logger = LogFactory.getLog(getClass());
	
	private IAccountManageDao accountManageDao;
	public void setAccountManageDao(IAccountManageDao accountManageDao) {
		this.accountManageDao = accountManageDao;
	}
	
	private ICommonLoginService commonLoginService;
	public void setCommonLoginService(ICommonLoginService commonLoginService) {
		this.commonLoginService = commonLoginService;
	}
	
	private ICommDataUpgradeService commDataUpgradeService;
	public void setCommDataUpgradeService(ICommDataUpgradeService commDataUpgradeService) {
		this.commDataUpgradeService = commDataUpgradeService;
	}
	
	private LoginNoSimpleEntity getLoginNoSimpleEntity(BigInteger userId, BigInteger currLoginNoId){
		//由于绑定原因，currLoginNoId对应的userId与现在的userId不相同
		LoginNoSimpleEntity loginNoSimpleEntity = accountManageDao.selectLoginNoSimpleEntityById(currLoginNoId);
		if(loginNoSimpleEntity==null){
			throw new TimeOutRuntimeException("accountManage.getLoginNoSimpleEntity.null");//超时处理
		}
		return loginNoSimpleEntity;
	}
	
	@Override
	public LoginInfoWithBindEntity getLoginInfoWithBindEntity(BigInteger userId, BigInteger currLoginNoId) {
		logger.info("accountManage.getLoginInfoWithBindEntity param,userId is:"+userId+",currLoginNoId is:"+currLoginNoId);
		LoginNoSimpleEntity loginNoSimpleEntity = getLoginNoSimpleEntity(userId, currLoginNoId);
		List<LoginNo> loginNoList = accountManageDao.selectBindAccList(userId);
		LoginInfoWithBindEntity resEntity = new LoginInfoWithBindEntity(loginNoSimpleEntity, loginNoList);
		return resEntity;
	}

	@Override
	public LoginInfoWithBindEntity submitAccountBind3rd(BigInteger userId, Integer regType, String openId,
			String accessToken, String unionId, BigInteger currLoginNoId) {
		logger.info("accountManage.submitAccountBind3rd info,userId is:"+userId+",regType is:"+regType+",openId is:"+openId
				+",accessToken is:"+accessToken+",unionId is:"+unionId+",currLoginNoId is:"+currLoginNoId);
		//获取并检查当前登录的主账号
		getLoginNoSimpleEntity(userId, currLoginNoId);
		
		//校验待绑定信息的合法性
		LoginAccNoAndType loginAccNoAndType = commonLoginService.validateAccessToken(regType, openId, accessToken);
		String toBindAccountNo = loginAccNoAndType.getAccountNo();//待绑定的账号
		Long toBindAccType = loginAccNoAndType.getAccType();//待绑定的账号类别
		//执行绑定操作
		boolean isApplyAccNeedAdd = true;
		commDataUpgradeService.executeBindAction(userId, toBindAccountNo, toBindAccType,unionId,isApplyAccNeedAdd);
		return getLoginInfoWithBindEntity(userId, currLoginNoId);
	}

//	@Override
//	public LoginInfoWithBindEntity submitAccountBindPhone(BigInteger userId, BigInteger currLoginNoId, String newPhone) {
//		//获取并检查当前登录的主账号
//		getLoginNoSimpleEntity(userId, currLoginNoId);
//		
//		//校验待绑定信息的合法性
//		//查询当前用户已经绑定的手机号
//		LoginNo oldLoginNo = commonLoginService.getUserPhoneAccInfo(userId);
//		boolean currUserHaveBindPhone;//当前用户是否有绑定手机
//		if(oldLoginNo==null){
//			currUserHaveBindPhone = false;
//		}else{
//			currUserHaveBindPhone = true;
//		}
//		if(currUserHaveBindPhone){//当前用户是否已绑定手机-是
//			//判断目标账号与当前账号是否相同
//			if(oldLoginNo.getNo().equals(newPhone)){
//				throw new ValidateRuntimeException("accountManage.submitAccountBindPhone.check.phoneSame.error");
//			}else{
//				//校验验证码是否正确
//				Boolean valicodeResCache = (Boolean)SessionManager.getSession().getAttribute(LoginConstant.Change_Phone_Bind_Result);
//				if(valicodeResCache==null||valicodeResCache!=true){
//					throw new ValidateRuntimeException("bindPhone.check.oldValicode.error");
//				}else{
//					SessionManager.getSession().setAttribute(LoginConstant.Change_Phone_Bind_Result, null);//清空
//				}
//			}
//		}else{//当前用户是否已绑定手机-否
//		}
//		
//		//判断目标账号是否已经注册
//		String toBindAccountNo = newPhone;
//		commDataUpgradeService.executeBindActionForPhone(userId, toBindAccountNo,currUserHaveBindPhone);
//		return getLoginInfoWithBindEntity(userId, currLoginNoId);
//	}
	
}
