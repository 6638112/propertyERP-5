package com.cnfantasia.server.commbusi.microblogQueue.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import com.cnfantasia.server.commbusi.microblogQueue.entity.GBLeastDiscount;
import com.cnfantasia.server.commbusi.microblogQueue.entity.GBWithPropMonthYear;
import com.cnfantasia.server.commbusi.microblogQueue.entity.GbIdAndPayDistance;
import com.cnfantasia.server.commbusi.microblogQueue.entity.PropertyGoodNewsRowDataEntity;
import com.cnfantasia.server.commbusi.microblogQueue.entity.SimpleAddressInfo;
import com.cnfantasia.server.commbusi.microblogQueue.entity.TypeCodeDymPriority;
import com.cnfantasia.server.commbusi.microblogQueue.entity.TypeCodeSendCount;
import com.cnfantasia.server.domainbase.microblogPushType.entity.MicroblogPushType;
import com.cnfantasia.server.domainbase.microblogQueue.dao.IMicroblogQueueBaseDao;
import com.cnfantasia.server.domainbase.microblogQueue.entity.MicroblogQueue;

public interface IMicroblogQueueDao extends IMicroblogQueueBaseDao {
	
	/**
	 * 查询喜报数据
	 * @param gbId 小区Id
	 * @param propYear 账单年份
	 * @param propMonth 账单月份
	 * @param propPayDateBegin 缴费开始日期
	 * @param propPayDateEnd 缴费结束日期
	 * @return
	 */
	public List<PropertyGoodNewsRowDataEntity> selectGoodNewsDataList(BigInteger gbId,String propYear,String propMonth,String propPayDateBegin,String propPayDateEnd);
	
	
	/**
	 * 查询小区当前月份累计缴纳物业费用户数
	 * @param gbId
	 * @param propPayDateBegin 缴费开始日期
	 * @param propPayDateEnd 缴费结束日期
	 * @return
	 */
	public Long selectPayUserCount(BigInteger gbId,String propPayDateBegin, String propPayDateEnd);


	/**
	 * 查询缴费截止日期是昨天的小区列表,返回上个月的物业缴费信息
	 * @return
	 */
	public List<GBWithPropMonthYear> selectCanPayGbYesterdayEndList(Long mqLevelCode);
	
	/**
	 * 查询当天不存在指定条件的小区id
	 * @param gbId
	 * @param mqLevelCode
	 * @return
	 */
	public BigInteger selectNotExistMQGbIdForUpdate(BigInteger gbId,Long mqLevelCode);
	
	/**
	 * 各个类别及发送的次数
	 * @param gbId
	 * @return
	 */
	public List<TypeCodeSendCount> selectTypeCodeSendCountByGbId(BigInteger gbId);
	
	/**
	 * 查询所有街坊消息可推送类别
	 * @param gbId
	 * @return
	 */
	public Set<MicroblogPushType> selectMicroblogPushTypeAllList(BigInteger gbId);
	
	/**
	 * 查询当天截止到当前优先级最高的一条消息
	 * @param gbId
	 * @param saIdList
	 * @param priorityList
	 * @return
	 */
	public MicroblogQueue selectTopMicroblogQueueToday(BigInteger gbId,
			List<BigInteger> saIdList, List<TypeCodeDymPriority> priorityList);
	
	/**
	 * 标记队列数据为可发送 包含行锁
	 * @param queueId
	 * @param gbId
	 * @return
	 */
	public boolean markMicroblogQueueAsPushAble(BigInteger queueId);
	
	
	/**
	 * 查询小区+MqId的已存在的关系数
	 * @param queueId
	 * @param gbId
	 * @return
	 */
	public Integer selectExsitGbHasQueueCount(BigInteger queueId, BigInteger gbId);

	/**
	 * 锁住单行街坊队列数据
	 * @param queueId
	 * @param gbId
	 * @return
	 */
	public MicroblogQueue selectMicroblogQueueForUpdate(BigInteger queueId,
			BigInteger gbId);

	/**
	 * 根据用户门牌关系Id查询地址基本信息
	 * @param userHasRoomFId
	 * @return
	 */
	public SimpleAddressInfo selectSimpleAddressInfoByUserHasRoomId(BigInteger userHasRoomFId);
	/**
	 * 根据门牌Id查询地址基本信息
	 * @param userHasRoomFId
	 * @return
	 */
	public SimpleAddressInfo selectSimpleAddressInfoByRoomId(BigInteger roomId);
	/**
	 * 查询用户默认门牌地址基本信息
	 * @param userId
	 * @return
	 */
	public SimpleAddressInfo selectSimpleAddressInfoByUserDefaultRoomId(BigInteger userId);
	
//	/**
//	 * 获取全面签约合作但是未推送街坊消息的小区
//	 * */
//	public List<GroupBuildingEntity> selectSignGroupBuildingListForMQ(Map<String, Object> param);
	
	/**
	 * 获取物业缴费提前三天提醒但是未推送街坊消息的小区
	 * */
	public List<GbIdAndPayDistance> selectPayBillGroupBuildingListForMQ(Long mqLevelCode);

	/**
	 * 查询小区Id当前月份物业信息
	 * @param gbId
	 * @return
	 */
	public GBWithPropMonthYear selectGbPropertyCurrMonthInfoByGbId(BigInteger gbId);

	/**
	 * 查询小区对应缴费周期最低折扣信息
	 * @param gbId
	 * @param propPayDateBegin
	 * @param propPayDateEnd
	 * @return
	 */
	public GBLeastDiscount selectLeastDiscountByGbId(BigInteger gbId,
			String propPayDateBegin, String propPayDateEnd);

	/**
	 * 查询可推送的属于小区的范围的消息
	 * @return
	 */
	public List<BigInteger> selectCanPushForGbToday();
	
	/**
	 * 小区表Id的锁定
	 * @param gbId
	 * @return
	 */
	public BigInteger selectGbIdForUpdate(BigInteger gbId);
}
