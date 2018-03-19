/**   
* Filename:    ICommonLoginDao.java   
* @version:    1.0  
* Create at:   2014年9月1日 上午8:53:20   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.dao;

import java.math.BigInteger;

/**
 * Filename:    ICommonLoginDao.java
 * @version:    1.0.0
 * Create at:   2014年9月1日 上午8:53:20
 * Description: 公共登录处理Dao
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月1日       shiyl             1.0             1.0 Version
 */
public interface ICommonLoginDao {
	
	/**
	 * 按条件查询用户的记录数
	 * @param accountNo
	 * @param password
	 * @param accountType
	 * @return
	 */
	public Integer selectUserCountByAccInfo(String accountNo, String password,Long accountType);
	
	/**
	 * 根据用户id删除所有的登录session记录信息
	 * @param subChannel 
	 * @param userId
	 * @return
	 */
	public Integer deleteUserSessionLogicByUserId(String accountNo,Long accountType, int subChannel);
	
	public Integer updateLoginSessionByUserId(BigInteger userId,String no,Long type);
	
	/**
	 * 根据userId和subChannelId找到相同/相似渠道的有效SessionKey，
	 * 
	 * @param userId
	 * @param subChannelId 
	 * @return 如果>0, 则说明此用户在相同/相似渠道已经登录，再次登录需要踢出
	 */
	public int selectValidSessionKeyCountBy(BigInteger userId, Long subChannelId);

	public void deleteUserSessionLogicByUserId(String accNo, Long accType);
}
