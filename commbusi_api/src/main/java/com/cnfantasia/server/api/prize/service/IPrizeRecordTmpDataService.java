/**   
* Filename:    IPrizeRecordTmpDataService.java   
* @version:    1.0  
* Create at:   2015年6月8日 上午3:45:04   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年6月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.service;

import java.math.BigInteger;

import com.cnfantasia.server.api.commonBusiness.entity.RequestClientInfoEntity;
import com.cnfantasia.server.api.prize.entity.PrizeCheckParamEntity;
import com.cnfantasia.server.api.prize.entity.PrizePreConditionValidateResult;
import com.cnfantasia.server.api.prize.entity.PrizeResultDiscountEntity;
import com.cnfantasia.server.api.prize.entity.PrizeResultDiscountEntityExtend;
import com.cnfantasia.server.api.prize.entityDB.PrizeResultForDB;
import com.cnfantasia.server.api.prize.entityDB.SupriseGiftForDB;
import com.cnfantasia.server.api.surpriseGift.entity.PrizeSurpriseGiftEntity;
import com.cnfantasia.server.domainbase.prizeRecordTmpData.entity.PrizeRecordTmpData;

/**
 * Filename:    IPrizeRecordTmpDataService.java
 * @version:    1.0.0
 * Create at:   2015年6月8日 上午3:45:04
 * Description:抽奖记录临时数据数据类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年6月8日       shiyl             1.0             1.0 Version
 */
public interface IPrizeRecordTmpDataService {
	
	/**
	 * 校验进行抽奖的前提条件
	 * @param userId
	 * @param userType
	 * @param prizeCheckParamEntity
	 * @return 返回当前已抽次数
	 */
	public PrizePreConditionValidateResult validatePrizePreCondition(BigInteger userId,Integer userType,PrizeCheckParamEntity prizeCheckParamEntity);
	public PrizePreConditionValidateResult validatePrizePreCondition(BigInteger userId,Integer userType);
	
	/**
	 * 通过通知信息处理临时抽奖记录
	 * @param userId
	 * @param userType
	 * @param prizeCheckParamEntity
	 */
	public void dealTmpPrizeDataByNotifyInfo(BigInteger userId,Integer userType,PrizeCheckParamEntity prizeCheckParamEntity);
	
	/**
	 * 存入临时待处理抽奖记录
	 * @param userId
	 * @param userType
	 * @param prizeRecordTmpData
	 * @return 返回对应记录Id
	 */
	public PrizeRecordTmpData addTmpPrizeData(BigInteger userId,Integer userType,PrizeRecordTmpData prizeRecordTmpData);
	
	/**
	 * 将临时表的指定数据转移到正式表
	 * @return 返回正式表对应的抽奖记录Id
	 */
	public PrizeResultDiscountEntity transferTmpPrizeData2FormalForSuprise(BigInteger toDealRecordId);
	public PrizeResultDiscountEntity transferTmpPrizeData2Formal(BigInteger toDealRecordId);

	/**
	 * 将抽奖结果的意外惊喜存入数据库(如果有意外惊喜)
	 * @param supriseGiftForDB
	 * @param goalUserType
	 * @param goalUserId
	 * @param prizeRecordAddId
	 * @return
	 */
	public PrizeSurpriseGiftEntity checkAndStorePrizeSurpriseGift(SupriseGiftForDB supriseGiftForDB, Integer goalUserType,
			BigInteger goalUserId, BigInteger prizeRecordAddId);

	/**
	 * 根据当前时间获取对应折扣结束时间
	 * @param nowTime
	 * @return
	 */
	public String getPrizeEndTime(String nowTime);

	/**
	 * 保存抽奖结果,直接存储到正式
	 * @param goalUserId
	 * @param goalUserType
	 * @param prizeResult
	 * @param requestClientInfoEntity
	 * @return
	 */
	public PrizeResultDiscountEntity storePrizeResult(BigInteger goalUserId, Integer goalUserType, PrizeResultForDB prizeResult,RequestClientInfoEntity requestClientInfoEntity);
	/**
	 * 保存抽奖结果,存储到临时表
	 */
	public PrizeResultDiscountEntityExtend storePrizeResult(BigInteger goalUserId, Integer goalUserType, PrizeResultForDB prizeResult,RequestClientInfoEntity requestClientInfoEntity,PrizeCheckParamEntity prizeCheckParamEntity);
	
	/**
	 * 将对应用户的临时数据全部转移到正式表
	 * 包含状态有：未处理的和成功的
	 * @param goalUserId
	 * @param goalUserType
	 */
	public void transferTmpPrizeData2Formal(BigInteger goalUserId, Integer goalUserType,Boolean isTimeOut);
	
	
}
