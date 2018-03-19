/**   
* Filename:    IAccountManageDao.java   
* @version:    1.0  
* Create at:   2015年4月29日 上午6:39:43   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.accountManage.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.accountManage.entity.LoginNoSimpleEntity;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;

/**
 * Filename:    IAccountManageDao.java
 * @version:    1.0.0
 * Create at:   2015年4月29日 上午6:39:43
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月29日       shiyl             1.0             1.0 Version
 */
public interface IAccountManageDao {

	/**
	 * 根据登录账号Id查询对应的详情,需要附加用户Id信息
	 * @param loginNoId
	 * @return
	 */
	public LoginNoSimpleEntity selectLoginNoSimpleEntityById(BigInteger loginNoId);

	/**
	 * 获取绑定的账号列表
	 * @param userId
	 * @return
	 */
	public List<LoginNo> selectBindAccList(BigInteger userId);
	
	/**
	 * 查询对应账号的详情
	 * @param toBindAccountNo
	 * @param toBindAccType
	 * @return
	 */
	public LoginNo selectLoginNoDetail(String toBindAccountNo, Long toBindAccType);
	
	/**
	 * 查询对应账号参与绑定的记录数
	 * @param toBindAccountNo
	 * @param toBindAccType
	 * @return
	 */
	public Integer selectBindRelationCount(String toBindAccountNo, Long toBindAccType);
	
	/**
	 * 查询用户对应的花号账户基本信息
	 * @param userId
	 * @return
	 */
	public LoginNo selectUserHuaLoginNoInfo(BigInteger userId);
	/**
	 * 查询用户对应的手机号账户基本信息
	 * @param userId
	 * @return
	 */
	public LoginNo selectUserPhoneAccInfo(BigInteger userId);
	
//	/**
//	 * 更新主账号及申请账号的绑定相关信息
//	 * @param mainUserId
//	 * @param applyLoginNoId
//	 * @return
//	 */
//	public Integer updateApplyAndMainAccountData(BigInteger mainUserId, BigInteger applyLoginNoId);
	
	/**
	 * 根据当前用户Id查询对应的主账号Id
	 * @param userId
	 * @return
	 */
	public BigInteger selectMainUserIdById(BigInteger userId);
	
	/**
	 * 将从账号的密码统一设置为主账号的密码
	 * @param userId 当前登录用户Id
	 * @return
	 */
	public Integer updateApplyPwdByMainUser(BigInteger userId);
	public Integer updateApplyPwdWithMainUser(BigInteger userId,String newPassword);
}
