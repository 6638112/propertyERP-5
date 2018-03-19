/**   
* Filename:    IPrizeRecordTmpDataDao.java   
* @version:    1.0  
* Create at:   2015年6月8日 上午9:43:03   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年6月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.prizeRecordTmpData.entity.PrizeRecordTmpData;

/**
 * Filename:    IPrizeRecordTmpDataDao.java
 * @version:    1.0.0
 * Create at:   2015年6月8日 上午9:43:03
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年6月8日       shiyl             1.0             1.0 Version
 */
public interface IPrizeRecordTmpDataDao {
	
	/**
	 * 查询用户临时表待处理的次数
	 * @param userId
	 * @param userType
	 * @param roomId
	 * @return
	 */
	public Integer selectPrizeRecordTmpData2DealCount(BigInteger userId,Integer userType,BigInteger roomId);
	
	/**
	 * 标记抽奖记录状态为失败
	 * @param userId
	 * @param userType
	 * @param toDealRecordId
	 */
	public Integer updatePrizeRecordTmpDataAsFailed(BigInteger userId,Integer userType,Integer prizeNum,BigInteger roomId,String prizeTime);
	
	/**
	 * 标记抽奖记录状态为成功
	 * @param userId
	 * @param userType
	 * @param prizeNum 对应抽奖次数
	 */
	public Integer updatePrizeRecordTmpDataAsSuccess(BigInteger userId,Integer userType,Integer prizeNum,BigInteger roomId,String prizeTime);
	
	/**
	 * 标记抽奖记录状态为处理结束
	 * @param userId
	 * @param userType
	 * @param prizeNum 对应抽奖次数
	 */
	public Integer updatePrizeRecordTmpDataAsFinished(BigInteger recordTmpDataId);

	/**
	 * 查询[未标记为失败的]临时抽奖记录列表
	 * @param userId
	 * @param userType
	 * @param isTimeOut null:全部，true 已超时的 false 未超时的
	 * @return
	 */
	public List<PrizeRecordTmpData> selectTmpPrizeDataListNotFailedByUserInfo(BigInteger userId, Integer userType,Boolean isTimeOut);

	/**
	 * 获取超时的，未标记为失败的
	 * @return
	 */
	public List<PrizeRecordTmpData> selectTmpPrizeDataListNotFailedAndTimeOut();
	
	/**
	 * 查询单个详情
	 * @param recordTmpDataId
	 * @return
	 */
	public PrizeRecordTmpData selectPrizeRecordTmpDataDetail(BigInteger recordTmpDataId);
}
