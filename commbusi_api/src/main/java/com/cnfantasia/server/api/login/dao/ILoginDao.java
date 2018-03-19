/**   
* Filename:    ILoginDao.java   
* @version:    1.0  
* Create at:   2014年5月6日 上午9:11:26   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.login.dao;

import java.math.BigInteger;

import com.cnfantasia.server.api.login.entity.LoginNoEntity;
import com.cnfantasia.server.domainbase.prizeRecordTmp.entity.PrizeRecordTmp;

/**
 * Filename:    ILoginDao.java
 * @version:    1.0.0
 * Create at:   2014年5月6日 上午9:11:26
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月6日       shiyl             1.0             1.0 Version
 */
public interface ILoginDao {
	/**
	 * 通过账号，密码，类型，查询登录信息
	 * 支持账号的绑定处理
	 * @param number
	 * @param password
	 * @param loginType
	 * @return
	 */
	LoginNoEntity selectLoginNoEntitySupportBind(String number,String password,Long loginType);
	
	/**
	 * 通过账号 类型，查询登录信息
	 * 支持账号的绑定处理
	 * @param accountNo
	 * @param type
	 * @return
	 */
	public LoginNoEntity selectLoginNoEntityByAccountSupportBind(String accountNo,Long type);
	
	/**
	 * 通过 用户Id，密码 设置新密码
	 * @param tUserFId
	 * @param password
	 * @return
	 */
	boolean setNewPassword(BigInteger tUserFId,String password);
	
	/**通过账号，账号类别获取对应的用户Id
	 * 
	 * @param account
	 * @param type
	 * @return
	 */
	public BigInteger selectUserIdByAccount(String account,Long type);
	
	/**
	 * 通过用户Id和密码查询登录信息表中匹配的记录数
	 * @param id
	 * @param oldPassword
	 * @return
	 */
	public int selectCountByIdPwd(BigInteger id,String oldPassword);
	
	/**
	 * 根据临时用户Id删除临时中奖记录
	 * @param userTmpId
	 * @return
	 */
//	public int deletePrizeRecordTmpByUserTmpId(BigInteger userTmpId);
	public int deletePrizeRecordTmpByUserTmpId(String deviceId,Long deviceType);
	
	/**
	 * 查询临时用最新一条的抽奖记录
	 */
	public PrizeRecordTmp selectLastRecord(BigInteger userTmpId);

}
