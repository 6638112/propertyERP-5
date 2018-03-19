/**   
* Filename:    IPrizeService.java   
* @version:    1.0  
* Create at:   2014年5月8日 上午2:58:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.commonBusiness.entity.RequestClientInfoEntity;
import com.cnfantasia.server.api.prize.entity.PrizeCheckParamEntity;
import com.cnfantasia.server.api.prize.entity.PrizeFortunesEntity;
import com.cnfantasia.server.api.prize.entity.PrizeRecordEntity;
import com.cnfantasia.server.api.prize.entity.PrizeStateEntity;
import com.cnfantasia.server.api.prize.entity.SignalPrizeResult;
import com.cnfantasia.server.api.prize.entity.UserWithLeastDiscountEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;
import com.cnfantasia.server.domainbase.userTmp.entity.UserTmp;

/**
 * Filename:    IPrizeService.java
 * @version:    1.0.0
 * Create at:   2014年5月8日 上午2:58:44
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月8日       shiyl             1.0             1.0 Version
 */
public interface IPrizeService {
	/**
	 * 未登录情况下的抽奖
	 * @return
	 */
	public SignalPrizeResult doPrizeNotLogin(UserTmp userTmp,/*String deviceId,Long deviceType,*/RequestClientInfoEntity requestClientInfoEntity,Boolean checkIsLightApp);
	public SignalPrizeResult doPrizeNotLogin(UserTmp userTmp,/*String deviceId,Long deviceType,*/RequestClientInfoEntity requestClientInfoEntity,Boolean checkIsLightApp,PrizeCheckParamEntity prizeCheckParam);
	/**
	 * 已登录情况下,并且设置了默认门牌的抽奖处理
	 * @param userId
	 * @return
	 */
	public SignalPrizeResult doPrizeIsLoginWithDefault(BigInteger userId,RequestClientInfoEntity requestClientInfoEntity,Boolean checkIsLightApp);
	public SignalPrizeResult doPrizeIsLoginWithDefault(BigInteger userId,RequestClientInfoEntity requestClientInfoEntity,Boolean checkIsLightApp,PrizeCheckParamEntity prizeCheckParam);
	
//	/**
//	 * 获取当月最低折扣
//	 * @param userHasTRoomId 用户门牌关系Id
//	 * @param monthFirstDay
//	 * @param monthLastDay
//	 * @return
//	 */
//	public Double getLeastDiscountIsLognWithDefault(BigInteger userHasTRoomId,String monthFirstDay,String monthLastDay);
	
//	/**
//	 * 获取当月最低折扣
//	 * @param device 设备Id
//	 * @param deviceType
//	 * @param monthFirstDay
//	 * @param monthLastDay
//	 * @return
//	 */
//	public Double getLeastDiscountNotLogin(String deviceId,Long deviceType,String monthFirstDay,String monthLastDay);
	
	/**
	 * 根据条件查询中奖记录(已绑定门牌的中奖记录)，按抽奖时间排序后，分页返回记录
	 * @param prizeRecordQuery
	 * @param pageModel
	 * @return
	 */
	public List<PrizeRecord> getPrizeRecordOrderByPrizeTime(BigInteger userId,PageModel pageModel);
	/**
	 * 按年月查询中奖记录
	 * @param userId
	 * @param month 如201406
	 * @param pageModel
	 * @return
	 */
	public List<PrizeRecordEntity> getPrizeRecordByMonth(BigInteger userId,IBusinessMonthYear monthYearWithGB,PageModel pageModel);
	
//	/**
//	 * 根据设备Id和渠道类别查询其是否已经注册过
//	 * @param deviceId
//	 * @param channelId
//	 * @return
//	 */
//	public boolean isRegistByDeviceInfo(String deviceId,Long channelId);
	
	
//	/**
//	 * 查询当天已抽奖次数(未登录用户)
//	 * @param deviceId
//	 * @param deviceType
//	 * @return
//	 */
//	public int getPrizeDoneCount(String deviceId, Long deviceType);
	
	/**
	 * 查询未登录用户当前的抽奖状态
	 * @param deviceId
	 * @param deviceType
	 * @return
	 */
	public PrizeStateEntity getCurrPrizeStateNotLogin(BigInteger userTmpId);
	/**
	 * 查询已登录用户当前的抽奖状态
	 * @param userId
	 * @return
	 */
	public PrizeStateEntity getCurrPrizeStateIsLogin(BigInteger userId);
	
	/**
	 * 通过年月查询对应折扣运势信息
	 * @param userId
	 * @param yearMonth
	 * @return
	 */
	public PrizeFortunesEntity getPrizeFortunesByMonthYear(BigInteger userId,IBusinessMonthYear monthYearWithGB);
	
	/**
	 * 通知用户家人有人抽取到最低折扣信息
	 * @param userId
	 * @param discount
	 */
	public void notifyFamilyLeastDiscount(BigInteger userId, Double discount);
	
	/**
	 * 查询家里人对应月份的抽奖次数及最低折扣信息
	 * @param userId
	 * @param monthYearWithGB
	 * @return
	 */
	public List<UserWithLeastDiscountEntity> getUserWithLeastDiscountList(BigInteger userId,IBusinessMonthYear monthYearWithGB);
	
	/**
	 * 查询上次抽奖折扣
	 * @param userId
	 * @param userType
	 * @return
	 */
	public Double getLastDiscount(BigInteger userId,Integer userType);
}
