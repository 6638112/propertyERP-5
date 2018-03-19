/**   
 * Filename:    ICommonPrizeDao.java   
 * @version:    1.0  
 * Create at:   2014年6月27日 上午12:33:52   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年6月27日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.commonBusiness.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.prize.entity.FirstLastPrizeTimeEntity;
import com.cnfantasia.server.api.prize.entity.PrizeCountWithUserEntity;
import com.cnfantasia.server.api.prize.entity.PrizeRecordEntity;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.prizeRecordTmp.entity.PrizeRecordTmp;
import com.cnfantasia.server.domainbase.room.entity.Room;

/**
 * Filename: ICommonPrizeDao.java
 * 
 * @version: 1.0.0 Create at: 2014年6月27日 上午12:33:52 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年6月27日 shiyl 1.0 1.0 Version
 */
public interface ICommonPrizeDao {
	/**
	 * 按对应月份是否有已经使用过的折扣,返回使用过的记录数
	 * 
	 * @param userId
	 * @param month
	 *          :yyyyMM,起止MM取值范围为[01,12],各位数前面需补0
	 * @return
	 */
//	public int selectIsUsedDiscountCount(BigInteger userId, String month);

	public int selectIsUsedDiscountCount(BigInteger userId, String startDate, String endDate);

	//
	// /**
	// * 查询对应月份可用的折扣数
	// * @param userId
	// * @param month :yyyyMM,起止MM取值范围为[01,12],各位数前面需补0
	// * @return
	// */
	// public int selectDiscountTotalCount(BigInteger userId,String month);

	/**
	 * 查询最低折扣对应的基本信息 若有多个最低折扣，则返回时间最早的那个
	 * 
	 * @param userId
	 * @param month
	 *          :yyyyMM,起止MM取值范围为[01,12],各位数前面需补0
	 * @return
	 */
//	public PrizeRecordEntity selectLeastDiscountFirstByMonth(BigInteger userId, String month);

	public PrizeRecordEntity selectLeastDiscountFirstByMonth(BigInteger userId, String startDate, String endDate);

	/**
	 * 查询用户对应时间所有折扣列表
	 * 
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<PrizeRecordEntity> selectDiscountListByMonth(BigInteger userId, String startDate, String endDate);

	/**
	 * 查询用户对应时间折扣列表 返回抽奖人信息
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @param pageModel
	 * @param leastRecordId 最低折扣记录的Id
	 * @return
	 */
	public List<PrizeRecordEntity> selectDiscountListByMonthWithUser(BigInteger userId,String startDate, String endDate, PageModel pageModel,BigInteger leastRecordId);

	/**
	 * 查询已经使用的折扣信息，若无则返回空
	 * 
	 * @param userId
	 * @param month
	 * @return
	 */
//	public PrizeRecordEntity selectIsUsedPrizeRecordByMonth(BigInteger userId, String month);

	public PrizeRecordEntity selectIsUsedPrizeRecordByMonth(BigInteger userId, String startDate, String endDate);

	// /**
	// * 获取当月最低折扣
	// * @param deviceId
	// * @param deviceType
	// * @param month
	// * @return
	// */
	// public Double selectLeastDiscountNotLoginByMonth(String deviceId,Long
	// deviceType,String month);
	/**
	 * 获取当天最低折扣
	 * 
	 * @param deviceId
	 * @param deviceType
	 * @param month
	 * @return
	 */
//	public PrizeRecordTmp selectLeastDiscountNotLoginNowDay(String deviceId, Long deviceType);
	public PrizeRecordTmp selectLeastDiscountNotLoginNowDayByTmpUser(BigInteger tmpUserId);

	/**
	 * 根据抽奖记录Id查询对应所属门牌信息 注：该抽奖记录Id为已绑定门牌的用户的正式抽奖记录
	 * 
	 * @param prizeRecordId
	 * @return
	 */
	public Room selectRoomByPrizeRecordId(BigInteger prizeRecordId);

	/**
	 * 标记对应折扣为已使用
	 * 
	 * @param prizeRecordId
	 * @return
	 */
	public Integer updateDiscountAsUsed(BigInteger prizeRecordId, Integer usedType,Long savedMoney);

	/**
	 * 标记折扣已退回
	 * 
	 * @param toBackIds
	 * @return
	 */
	public Integer markPrizeRecordAsIsBack(List<BigInteger> toBackIds);
	
	/**
	 * 查询用户所有的抽奖次数
	 * @param userId
	 * @return
	 */
	public int selectPrizeDoneCountAllTime(BigInteger userId);
	
	/**
	 * 查询已签约的小区下的门牌总数
	 * @return
	 */
	public int selectSignedRealRoomTotalCount();
	/**
	 * 查询未签约小区下的用户总数
	 * @return
	 */
	public int selectUnSignedUserTotalCount();
	
	/**
	 * 根据用户Id,起止时间 查询所有的折扣列表信息
	 * @param userId
	 * @param startYearMonth
	 * @param endYearMonth
	 * @return
	 */
	public List<PrizeRecordEntity> selectPrizeRecordEntityMonthAllWithNull(BigInteger userId,String startYearMonthDay,String endYearMonthDay);
	
	/**
	 * 查询用户所属家庭下对应起止时间最多抽奖次数的用户信息
	 * @param userId
	 * @param startYearMonthDay
	 * @param endYearMonthDay
	 * @return
	 */
	public PrizeCountWithUserEntity selectFamilyMaxPrizeCountInfoByUserId(BigInteger userId,String startYearMonthDay,String endYearMonthDay);
	/**
	 * 查询单个用户对应时间抽奖次数信息
	 * @param userId
	 * @param startYearMonthDay
	 * @param endYearMonthDay
	 * @return
	 */
	public PrizeCountWithUserEntity selectSignalPrizeCountInfoByUserId(BigInteger userId,String startYearMonthDay,String endYearMonthDay);
	
	/**
	 * 根据用户Id查询家庭对应所有的起止抽奖时间
	 * @param userId
	 * @return
	 */
	public FirstLastPrizeTimeEntity selectFamilyFirstLastPrizeTimeByUserId(BigInteger userId);

	/**
	 * 查询注册用户所有抽奖次数
	 * @param userId
	 * @return
	 */
	public Integer selectAllPrizeDoneCountRegistUser(BigInteger userId);

	/**
	 * 查询临时用户所有抽奖次数
	 * @param userId
	 * @return
	 */
	public Integer selectAllPrizeDoneCountTmpUser(BigInteger userId);

	/**
	 * 根据抽奖记录Id查询对应的用户信息
	 * @param prizeRecordId
	 * @return
	 */
	public UserSimpleEntity selectUserByPrizeRecordId(BigInteger prizeRecordId);

	/**
	 * 直接关联room_realRoom 查询获取最低折扣信息
	 * @param userId
	 * @param startYearMonthDay
	 * @param endYearMonthDay
	 * @return
	 */
	public PrizeRecordEntity selectMiniPrizeRecordEntityFromRoomNowTime(BigInteger userId, String startYearMonthDay,
			String endYearMonthDay);

	/**
	 * 通过用户Id刷新对应门牌的最低折扣
	 * @param userId
	 */
	public Integer updateMiniDiscountByUserId(BigInteger userId,Double discount,BigInteger prizeRecordId);
	
	/**
	 * 根据小区Id查询前面2个月缴费周期的开始时间
	 * @param groupBuildId
	 * @return
	 */
	public String selectPre2MonthStartDay(BigInteger groupBuildId);
}
