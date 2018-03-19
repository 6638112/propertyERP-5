/**   
* Filename:    ICommonPrizeService.java   
* @version:    1.0  
* Create at:   2014年6月27日 上午12:33:02   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月27日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.commonBusiness.entity.StartEndDate;
import com.cnfantasia.server.api.prize.entity.PrizeCountWithUserEntity;
import com.cnfantasia.server.api.prize.entity.PrizeRecordEntity;
import com.cnfantasia.server.api.prize.entity.PrizeRecordEntityWithBusinessMonthYear;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;
import com.cnfantasia.server.domainbase.prizeRecordTmp.entity.PrizeRecordTmp;
import com.cnfantasia.server.domainbase.room.entity.Room;

/**
 * Filename:    ICommonPrizeService.java
 * @version:    1.0.0
 * Create at:   2014年6月27日 上午12:33:02
 * Description:抽奖相关公告模块
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月27日       shiyl             1.0             1.0 Version
 */
public interface ICommonPrizeService {
	
	/**
	 * 按对应月份是否有已经使用过的折扣,返回使用过的记录数
	 * @param userId
	 * @param month
	 * @return
	 */
//	public int getIsUsedDiscountCount(BigInteger userId,String month);
	public int getIsUsedDiscountCount(BigInteger userId,IBusinessMonthYear monthYearWithGB);
	
//	/**
//	 * 查询最低折扣对应的基本信息
//	 * 若有多个最低折扣，则返回时间最早的那个
//	 * @param userId
//	 * @param month:yyyyMM,起止MM取值范围为[01,12],各位数前面需补0
//	 * @return
//	 */
//	public PrizeRecordSimpleEntity getLeastDiscountFirstByMonth(BigInteger userId,String month);
	
	/**
	 * 根据日期动态判断使用某个月份查询对应的折扣信息
	 * @param userId
	 * @param time
	 * @return
	 */
//	public PrizeRecordEntity getLeastDiscountFirstByDayTimeAndUsed(BigInteger userId,String time);
//	public PrizeRecordEntity getLeastDiscountFirstByDayTimeAndUsed(BigInteger userId,BusinessMonthYearWithGB monthYearWithGB);
	
//	public PrizeRecordEntity getLeastDiscountFirstByMonthYearAndUsed(BigInteger userId,BusinessMonthYearWithGB monthYearWithGB);
	
	/**
	 * 获取当天最低折扣
	 * @param deviceId
	 * @param deviceType
	 * @param month
	 * @return
	 */
//	public PrizeRecordTmp getLeastDiscountNotLoginNowDay(String deviceId,Long deviceType);
	public PrizeRecordTmp getLeastDiscountNotLoginNowDayTmpUser(BigInteger tmpUserId);
	
	/**
	 * 根据抽奖记录Id查询对应所属门牌信息
	 * 注：该抽奖记录Id为已绑定门牌的用户的正式抽奖记录
	 * @param prizeRecordId
	 * @return
	 */
	public Room getRoomByPrizeRecordId(BigInteger prizeRecordId);
	/**
	 * 标记对应折扣为已使用
	 * @param prizeRecordId
	 * @return
	 */
	public Integer markDiscountAsUsed(BigInteger prizeRecordId,Integer usedType,Long savedMoney);
	
	/**
	 * 查询用户对应时间所有折扣列表
	 */
//	public List<PrizeRecordEntity> getDiscountListByMonth(BigInteger userId, String yearMonth);
	public List<PrizeRecordEntity> getDiscountListByMonth(BigInteger userId, IBusinessMonthYear monthYearWithGB);
	
	/**
	 * 查询用户对应时间折扣列表 返回抽奖人信息
	 * @param userId
	 * @param yearMonth
	 * @param pageModel
	 * @return
	 */
//	public List<PrizeRecordEntity> getDiscountListByMonthWithUser(BigInteger userId, String yearMonth, PageModel pageModel);
	public List<PrizeRecordEntity> getDiscountListByMonthWithUser(BigInteger userId, IBusinessMonthYear monthYearWithGB, PageModel pageModel);
	
	/**
	 * 标记折扣为已退回
	 * @param toBackIds
	 * @return
	 */
	public Integer markPrizeRecordAsIsBack(List<BigInteger> toBackIds);

//	/**
//	 * 查询用户对应时间的物业日期
//	 * @param userId
//	 * @param time
//	 * @return
//	 */
//	public Date getPropertyMonthByTime(BigInteger userId, String time);
	
	/**
	 * 查询用户所有的抽奖次数
	 * @param userId
	 * @return
	 */
	public int getPrizeDoneCountAllTime(BigInteger userId);
	
	/**
	 * 查询已签约小区可分配的奖项数量
	 * @return
	 */
	public Integer getPrizeCountSigned();
	/**
	 * 查询非签约小区可分配的奖项数量
	 * @return
	 */
	public Integer getPrizeCountUnSigned();
	/**
	 * 查询总的可分配的奖项数量
	 * @return
	 */
	public Integer getPrizeCountTotal();
	/**
	 * 根据用户Id查询对应可分配的奖项数量
	 * @return
	 */
	public Integer getPrizeCountAuto(BigInteger userId);

	/**
	 * 根据指定月份获取物业周期日期
	 * @param userId
	 * @param month
	 * @return
	 */
	public StartEndDate getPreMonthStartEndByMonth(BigInteger userId, String month);
	
//	/**
//	 * 根据指定时间获取物业周期日期
//	 * @param userId
//	 * @param time
//	 * @return
//	 */
//	public StartEndDate getPreMonthStartEndByTime(BigInteger userId, String time);
	
	/**
	 * 根据用户Id查询对应门牌的所有抽奖记录对应的物业起止月份信息
	 * @param userId
	 * @return
	 */
	public StartEndDate getFirstLastPropMonth(BigInteger userId);
	/**
	 * 根据用户Id,起止时间 查询所有的折扣列表信息
	 * @param userId
	 * @param startYearMonth
	 * @param endYearMonth
	 * @return
	 */
	public List<PrizeRecordEntity> getPrizeRecordEntityMonthAllWithNull(BigInteger userId,String startYearMonthDay,String endYearMonthDay);
	
	/**
	 * 查询用户所属家庭下对应起止时间最多抽奖次数的用户信息
	 * @param userId
	 * @param startYearMonthDay
	 * @param endYearMonthDay
	 * @return
	 */
	public PrizeCountWithUserEntity getFamilyMaxPrizeCountInfoByUserId(BigInteger userId,String startYearMonthDay,String endYearMonthDay);
	
	/**
	 * 查询单个用户对应时间抽奖次数信息
	 * @param userId
	 * @param startYearMonthDay
	 * @param endYearMonthDay
	 * @return
	 */
	public PrizeCountWithUserEntity getSignalPrizeCountInfoByUserId(BigInteger userId,String startYearMonthDay,String endYearMonthDay);
	
	/**
	 * 查询用户所有的抽奖次数
	 * @param userId
	 * @param userType
	 * @return
	 */
	public Integer getAllPrizeDoneCount(BigInteger userId,Integer userType);
	
	/**
	 * 根据抽奖记录Id查询对应的用户信息
	 * @param prizeRecordId
	 * @return
	 */
	public UserSimpleEntity getUserByPrizeRecordId(BigInteger prizeRecordId);
	
	/**
	 * 查询最低折扣对应的基本信息
	 * 如果有已经使用的折扣，则返回使用的折扣
	 * 若有多个最低折扣，则返回时间最早的那个
	 * @param userId
	 * @param month
	 * @return
	 */
	public PrizeRecordEntity getLeastDiscountFirstByMonthAndUsed(BigInteger userId,IBusinessMonthYear monthYearWithGB);
	public PrizeRecordEntityWithBusinessMonthYear getLeastDiscountFirstByMonthAndUsedNowTime(BigInteger userId);
	
	/**
	 * 通过用户Id刷新对应门牌的最低折扣
	 * @param userId
	 */
	public void freshMiniDiscountByUserId(BigInteger userId,Double discount,BigInteger prizeRecordId);
	
	/**
	 * 根据用户Id查询所属小区前面2个月缴费周期的开始时间
	 * @param userId
	 * @return
	 */
	public String getPre2MonthStartDay(BigInteger userId);
}
