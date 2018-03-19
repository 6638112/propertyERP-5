/**   
* Filename:    ICommDataUpgradeService.java   
* @version:    1.0  
* Create at:   2015年4月22日 上午3:09:51   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;


/**
 * Filename:    ICommDataUpgradeService.java
 * @version:    1.0.0
 * Create at:   2015年4月22日 上午3:09:51
 * Description:数据升级服务类别
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月22日       shiyl             1.0             1.0 Version
 */
public interface ICommDataUpgradeService {
	
	/**
	 * 查询微信的登录账号信息
	 * @param accType
	 * @param accountNo
	 * @param unionId
	 * @return
	 */
	LoginNo qryExistLoginNoForWeiXin(Long accType, String accountNo, String unionId, Long subChannelId, String deviceId, String version);
	
	/**
	 * 查询当前账号对应的unionId是否有值，若无则更新对应的unionId,对于有unionId但是没有账号记录的情况，新增一条账号绑定记录
	 * 如果unionId为空，则更新对应记录的unionId
	 * @param accType
	 * @param accountNo
	 * @param unionId
	 * @return
	 */
	void updateWeiXinUnionIdIfNotExist(Long accType, String accountNo, String unionId);
	
	/**
	 * 微信用户 数据合并的处理
	 * 通过微信联合Id
	 * @param weixinUnionId 微信联合Id
	 */
	void mergeDataWeiXin(BigInteger currUserId, String weixinUnionId);
	
	/**
	 * 将用户就的门牌下的意外惊喜转移到新门牌
	 * @param userId
	 * @param oldRoomId
	 * @param newRoomId
	 */
	void convertSurpriseGiftDataByRoomId(BigInteger oldUserId, BigInteger oldRoomId, BigInteger newUserId, BigInteger newRoomId);
	
	/**
	 * 将用户old门牌下的抽奖折扣转移到新门牌下
	 * @param userId
	 * @param oldRoomId
	 * @param newRoomId
	 */
	void transferPrizeRecordFromOld2New(BigInteger oldUserId, BigInteger oldRoomId, BigInteger newUserId, BigInteger newRoomId);
	
	/**
	 * 将用户old门牌下的神马粮票转移到新门牌下
	 * @param userId
	 * @param oldRoomId
	 * @param newRoomId
	 */
	void convertShenMaHbOld2New(BigInteger oldUserId, BigInteger oldRoomId, BigInteger newUserId, BigInteger newRoomId);
	

///**
// * 将原始登录用户的数据 全部更新到 目标用户下
// * @param srcUserId
// * @param goalUserId
// */
//public void mergeLogInUserData(BigInteger srcUserId, BigInteger goalUserId);


/**
 * 执行绑定操作
 * @param mainUserId 当前登录的用户Id
 * @param applyAccountNo 待绑定的账号
 * @param applyAccType 待绑定的账号类型
 * @param applyUnionId 待绑定的账号UnionId
 * @param isApplyAccCanAdd 待绑定的账号不存在时是否可以新增
 */
void executeBindAction(BigInteger mainUserId, String applyAccountNo, Long applyAccType, String applyUnionId,
					   boolean isApplyAccCanAdd);
/**绑定手机*/
void executeBindActionForPhone(BigInteger mainUserId, String newPhone);

//	public void doOldWeixinLoginNoUpgrade(String openId,Long accType,Long subChannelId,String version, String deviceId
//			,String unionId);

void convertDataFromOld2New(BigInteger oldUserId, BigInteger oldRoomId, BigInteger newUserId, BigInteger newRoomId);
}
