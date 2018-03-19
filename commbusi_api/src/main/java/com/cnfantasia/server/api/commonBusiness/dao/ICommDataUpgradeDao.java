/**   
* Filename:    ICommDataUpgradeDao.java   
* @version:    1.0  
* Create at:   2015年4月22日 上午6:23:05   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;
import com.cnfantasia.server.domainbase.loginNoOldUpgradeDate.entity.LoginNoOldUpgradeDate;

/**
 * Filename:    ICommDataUpgradeDao.java
 * @version:    1.0.0
 * Create at:   2015年4月22日 上午6:23:05
 * Description:数据升级Dao层
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月22日       shiyl             1.0             1.0 Version
 */
public interface ICommDataUpgradeDao {
	
	/**
	 * 查询微信的登录账号信息
	 * @param accType
	 * @param accountNo
	 * @param unionId
	 * @return
	 */
	LoginNo selectExistLoginNoForWeiXin(Long accType, String accountNo, String unionId);
	
//	/**
//	 * 如果unionId为空，则更新对应记录的unionId
//	 * @param accType
//	 * @param accountNo
//	 * @param unionId
//	 * @return
//	 */
//	public Integer updateWeiXinUnionIdIfNotExist(Long accType, String accountNo, String unionId);
	
	/**
	 * 根据微信联合Id查询默认的登录主账号
	 * @param unionId
	 * @return
	 */
	LoginNo selectDefaultLoginNoByWeiXinUnionId(String unionId);
	
	/**
	 * 根据微信联合Id查询所有的登录账号
	 * @param unionId
	 * @return
	 */
	List<LoginNo> selectLoginNoListByWeiXinUnionId(String unionId);

//	/**
//	 * 转移意外惊喜数据
//	 * @param srcUserId
//	 * @param goalUserId
//	 */
//	public Integer updateSurpriseGiftFromSrc2Goal(BigInteger srcUserId, BigInteger goalUserId);

	/**
	 * 将用户老门牌下的意外惊喜转移到新门牌
	 * @param userId
	 * @param oldRoomId
	 * @param newRoomId
	 * @return
	 */
	Integer updateOldSurpriseGift2NewRoomId(BigInteger oldUserId, BigInteger oldRoomId, BigInteger newUserId, BigInteger newRoomId);
	
	/**
	 * 将用户old门牌下的抽奖折扣转移到新门牌下
	 * 
	 * @param userId
	 * @param oldRoomId
	 * @param newRoomId
	 */
	int updatePrizeRecFromOne2Another(BigInteger oldUserId, BigInteger oldRoomId, BigInteger newUserId, BigInteger newRoomId);
	
	/**
	 * 将用户old门牌下的神马粮票转移到新门牌下
	 * @param userId
	 * @param oldRoomId
	 * @param newRoomId
	 * @return
	 */
	Integer updateShenMaHbFromOne2Another(BigInteger oldUserId, BigInteger oldRoomId, BigInteger newUserId, BigInteger newRoomId);
	
	/**
	 * 根据设备信息获取对应的账号
	 * @param subChannelId
	 * @param deviceId
	 * @param accType
	 */
	List<LoginNoOldUpgradeDate> selectLoginNoOldUpgradeDateByDevice(Long subChannelId, String deviceId, Long accType);
	
	/**
	 * 查询老用户的微信登录数据
	 * @param oldWeixinDataId
	 * @return
	 */
	List<LoginNo> selectOldWeiXinByDeviceInfo(BigInteger oldWeixinDataId);

	/**
	 * 检查并判断是否更新门牌管理员的信息
	 * @param srcAdminUserId
	 * @param goalAdminUserId
	 * @param roomId
	 */
	Integer updateAndCheckRealRoomValidateInfo(BigInteger srcAdminUserId, BigInteger goalAdminUserId, BigInteger roomId);

	/**
	 * 根据账号及类别查询单个详情
	 * @param accountNo
	 * @param accType
	 * @return
	 */
	LoginNo selectLoginNoByAccountNoAndType(String accountNo, Long accType);

	
}
