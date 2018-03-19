/**   
* Filename:    IAccountManageService.java   
* @version:    1.0  
* Create at:   2015年4月29日 上午6:39:19   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.accountManage.service;

import java.math.BigInteger;

import com.cnfantasia.server.api.accountManage.entity.LoginInfoWithBindEntity;

/**
 * Filename:    IAccountManageService.java
 * @version:    1.0.0
 * Create at:   2015年4月29日 上午6:39:19
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月29日       shiyl             1.0             1.0 Version
 */
public interface IAccountManageService {
	
//	/**
//	 * 根据登录账号Id查询对应的详情
//	 * @param loginNoId
//	 * @return
//	 */
//	public LoginNoSimpleEntity getLoginNoSimpleEntityById(BigInteger loginNoId);
//	
//	/**
//	 * 获取用户绑定的账号列表
//	 * @param userId
//	 * @return
//	 */
//	public List<LoginNo> getBindAccList(BigInteger userId);
	
	/**
	 * 根据登录账号Id查询对应的详情
	 * 获取用户绑定的账号列表
	 * @param userId
	 * @param currLoginNoId
	 * @return
	 */
	public LoginInfoWithBindEntity getLoginInfoWithBindEntity(BigInteger userId,BigInteger currLoginNoId);

	/**
	 * 绑定第三方数据
	 * @param userId
	 * @param regType
	 * @param openId
	 * @param accessToken
	 * @param unionId
	 * @param currLoginNoId
	 * @return
	 */
	public LoginInfoWithBindEntity submitAccountBind3rd(BigInteger userId, Integer regType, String openId,
			String accessToken, String unionId, BigInteger currLoginNoId);
	
//	/**
//	 * 账号绑定-绑定手机
//	 * @param userId 当前登录用户
//	 * @param newPhone 新手机
//	 */
//	public LoginInfoWithBindEntity  submitAccountBindPhone(BigInteger userId, BigInteger currLoginNoId, String newPhone);
	
}
