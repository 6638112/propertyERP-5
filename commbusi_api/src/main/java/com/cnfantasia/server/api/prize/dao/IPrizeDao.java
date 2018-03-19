/**   
* Filename:    IPrizeDao.java   
* @version:    1.0  
* Create at:   2014年5月8日 上午2:57:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.prize.entity.PrizeRecordQuery;
import com.cnfantasia.server.api.prize.entity.UserWithLeastDiscountEntity;
import com.cnfantasia.server.api.prize.entityDB.PrizeResultForDB;
import com.cnfantasia.server.api.prize.entityDB.SupriseGiftForDB;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;
import com.cnfantasia.server.domainbase.prizeRecordTmp.entity.PrizeRecordTmp;


/**
 * Filename:    IPrizeDao.javal
 * @version:    1.0.0
 * Create at:   2014年5月8日 上午2:57:44
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月8日       shiyl             1.0             1.0 Version
 */
public interface IPrizeDao {
	
//	/**
//	 * 获取当月最低折扣
//	 * @param userHasTRoomId 用户门牌关系Id
//	 * @param monthFirstDay
//	 * @param monthLastDay
//	 * @return
//	 */
//	public Double selectLeastDiscountIsLognWithDefault(BigInteger userHasTRoomId,String monthFirstDay,String monthLastDay);

	/**
	 * 根据条件查询中奖记录(已绑定门牌的中奖记录)，按抽奖时间排序后，分页返回记录
	 * @param prizeRecordQuery
	 * @param pageModel
	 * @return
	 */
	public List<PrizeRecord> selectPrizeRecordOrderByPrizeTime(PrizeRecordQuery prizeRecordQuery,PageModel pageModel);
	
//	/**
//	 * 按年月查询中奖记录
//	 * @param userId
//	 * @param month 如201406
//	 * @param pageModel
//	 * @return
//	 */
//	public List<PrizeRecordEntity> selectPrizeRecordByMonth(BigInteger userId,String month,PageModel pageModel);
	
	public int selectPrizeRecordCount(PrizeRecordQuery prizeRecordQuery);
	/**
	 * 查询当天已抽奖次数
	 * @param userId
	 * @return
	 */
	public Integer selectPrizeDoneCount(BigInteger userId,Integer userType);
	
	/**
	 * 查询上次抽奖折扣
	 * @param userId
	 * @param userType
	 * @return
	 */
	public Double selectLastDiscount(BigInteger userId,Integer userType);
	
	/**
	 * 查询当天已抽奖次数(未登录用户)
	 * @param deviceId
	 * @param deviceType
	 * @return
	 */
//	public int selectPrizeDoneCount(String deviceId, Long deviceType);
	
	/**
	 * 查询当月产生的中奖纪录 已签约的
	 * 不查询已经退回到奖池的
	 * @return
	 */
	public List<PrizeRecord> selectPrizeRecordCurrMonthSign();
	/**
	 * 查询当月产生的中奖纪录 非签约的
	 * 不查询已经退回到奖池的
	 * @return
	 */
	public List<PrizeRecord> selectPrizeRecordCurrMonthUnSign();
	
	/**
	 * 查询当月产生的临时中奖纪录
	 * @return
	 */
	public List<PrizeRecordTmp> selectPrizeRecordTmpCurrMonth();
	
	/**
	 * 从数据库进行抽奖
	 * @param userId
	 * @param userType
	 * @return
	 */
	public PrizeResultForDB doPrizeFromDB(BigInteger userId,Integer userType);
	
	/**
	 * 直接抽取意外惊喜奖品
	 * @param userId
	 * @param userType
	 * @param fromType
	 * @return
	 */
	public SupriseGiftForDB fetchSupriseGiftFromDB(BigInteger userId,Integer userType,Integer fromType,BigInteger fromId,String cityName);

	/**
	 * 查询家里人对应月份的抽奖次数及最低折扣信息
	 * @param userId
	 * @param yearMonth
	 * @return
	 */
	public List<UserWithLeastDiscountEntity> selectUserWithLeastDiscountList(BigInteger userId, String startYearMonthDay,
			String endYearMonthDay);

	/**
	 * 
	 */
	public Integer testTrans();
	
}
