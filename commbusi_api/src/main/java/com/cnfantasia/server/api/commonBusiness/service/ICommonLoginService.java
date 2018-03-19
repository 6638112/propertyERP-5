/**   
* Filename:    ICommonLoginService.java   
* @version:    1.0  
* Create at:   2014年6月3日 上午2:18:54   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月3日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.api.commonBusiness.entity.LoginAccNoAndType;
import com.cnfantasia.server.api.login.entity.LoginNoEntity;
import com.cnfantasia.server.api.pub.session.SimpleSessionUser;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;

/**
 * Filename:    ICommonLoginService.java
 * @version:    1.0.0
 * Create at:   2014年6月3日 上午2:18:54
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月3日       shiyl             1.0             1.0 Version
 */
public interface ICommonLoginService {
	public JsonResponse afterLoginCheck(HttpServletRequest request,LoginNoEntity obj);
	/**
	 * 获取sessionKey,并存入数据库
	 * @param accountNo
	 * @param accType
	 * @return
	 */
	public String getSessionKeyAnd2DB(HttpServletRequest request,BigInteger userId,String accountNo,Long accType);
	
	public SimpleSessionUser validateSessionKey(String sessionKey, int subChannel);
	
	/**
	 * 自动登录
	 * @param request
	 * @param account
	 * @param password
	 * @param accountType 可为空
	 */
	public void autoLogin(HttpServletRequest request, String accountNo, String password,Long accountType);
	/**
	 * 自动登录
	 * @param request
	 * @param accountNo 账号
	 * @param accType 账号类型
	 */
	public void autoLoginWithNoAndType(HttpServletRequest request,String accountNo,Long accType);
	
	/**
	 * 检查SessionKey，并判断根据情况自动登录
	 * @param request
	 */
	public void checkSessionKeyAndAutoLogin(HttpServletRequest request);
	/**
	 * 删除sessionKey
	 * @param sessionKey
	 */
	public void deleteSessionUserFromDB(String sessionKey);
	
	
	/**
	 * 校验账号是否正确
	 * @param request
	 * @return
	 */
	public boolean verifyAccount(HttpServletRequest request);
	
	/**
	 * 查询用户的绑定的手机账号的信息
	 * 若未绑定账号则返回空
	 * @param userId
	 * @return
	 */
	public LoginNo getUserPhoneAccInfo(BigInteger userId);
	
	/**
	 * 根据用户Id更新登录的session信息
	 * @param userId
	 * @param no
	 * @param type
	 * @return
	 */
	public Integer updateLoginSessionByUserId(BigInteger userId,String no,Long type);
	
	/**
	 * 验证第三方登录信息
	 * @param regType
	 * @param openId
	 * @param accessToken
	 * @param unionId
	 * @return
	 */
	public LoginAccNoAndType validateAccessToken(Integer regType, String openId, String accessToken);
	
	/**
	 * 根据微信信息获取对应的UnionId
	 * @param openId
	 * @param accessToken
	 * @return
	 */
	public String getUnionIdByWeiXinInfo(Integer regType,String openId, String accessToken);
	
	/**
	 * 新增登录账号
	 * @param addId
	 * @param account
	 * @param accountType
	 * @param userId
	 * @param unionId
	 * @param encodePwd
	 */
	public LoginNo insertLoginNo(BigInteger _addId, String _account, Long _accountType, BigInteger _userId, String _unionId,
			String _encodePwd,Integer createType);
	
	/**
	 * 设定指定用户的登录session失效
	 * @param userId
	 * @param accNo
	 * @param accType
	 */
	public void expiredLoginSessionByUserId(BigInteger userId,String accNo,Long accType);
}
