package com.cnfantasia.server.commbusi.microblogQueue.dao;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.cache.constant.CacheConstant;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.cache.microblogPushType.MicroblogPushTypeDataReload;
import com.cnfantasia.server.commbusi.microblogQueue.entity.GBLeastDiscount;
import com.cnfantasia.server.commbusi.microblogQueue.entity.GBWithPropMonthYear;
import com.cnfantasia.server.commbusi.microblogQueue.entity.GbIdAndPayDistance;
import com.cnfantasia.server.commbusi.microblogQueue.entity.PropertyGoodNewsRowDataEntity;
import com.cnfantasia.server.commbusi.microblogQueue.entity.SimpleAddressInfo;
import com.cnfantasia.server.commbusi.microblogQueue.entity.TypeCodeDymPriority;
import com.cnfantasia.server.commbusi.microblogQueue.entity.TypeCodeSendCount;
import com.cnfantasia.server.domainbase.microblogPushType.entity.MicroblogPushType;
import com.cnfantasia.server.domainbase.microblogQueue.dao.MicroblogQueueBaseDao;
import com.cnfantasia.server.domainbase.microblogQueue.entity.MicroblogQueue;

public class MicroblogQueueDao extends MicroblogQueueBaseDao implements IMicroblogQueueDao {
	
	@Override
	public GBWithPropMonthYear selectGbPropertyCurrMonthInfoByGbId(BigInteger gbId) {
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbId", gbId);
		return sqlSession.selectOne("microblogQueue.select_GbProperty_CurrMonthInfo_ByGbId", tmpMap);
	}
	
	@Override
	public List<GBWithPropMonthYear> selectCanPayGbYesterdayEndList(Long mqLevelCode) {
		Integer dayDistance = -1;//-1昨天,-6有数据
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("dayDistance", dayDistance);
		tmpMap.put("mqLevelCode", mqLevelCode);
		return sqlSession.selectList("microblogQueue.select_CanPay_GbList_ByDayDistance",tmpMap);
	}
	
	@Override
	public BigInteger selectNotExistMQGbIdForUpdate(BigInteger gbId,Long mqLevelCode) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbId", gbId);
		tmpMap.put("mqLevelCode", mqLevelCode);
		return sqlSession.selectOne("microblogQueue.select_NotExist_MQGbId_ForUpdate",tmpMap);
	}
	
	@Override
	public List<GbIdAndPayDistance> selectPayBillGroupBuildingListForMQ(Long mqLevelCode) {
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("mqLevelCode", mqLevelCode);
		tmpMap.put("dayStart", 1);//缴费前1到3天提醒
		tmpMap.put("dayEnd", 3);
		return sqlSession.selectList("microblogQueue.select_payBillGroupBuildingList_forMQ", tmpMap);
	}
	
	@Override
	public List<PropertyGoodNewsRowDataEntity> selectGoodNewsDataList(BigInteger gbId, String propYear, String propMonth,
			String propPayDateBegin, String propPayDateEnd) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("f_id_t_group_building", gbId);
		tmpMap.put("p_year", propYear);
		tmpMap.put("p_month", propMonth);
		tmpMap.put("p_date_begin", propPayDateBegin);
		tmpMap.put("p_date_end", propPayDateEnd);
		return sqlSession.selectList("microblogQueue.select_GoodNews_DataList", tmpMap);
	}

	@Override
	public Long selectPayUserCount(BigInteger gbId,String propPayDateBegin, String propPayDateEnd) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("f_id_t_group_building", gbId);
		tmpMap.put("p_date_begin", propPayDateBegin);
		tmpMap.put("p_date_end", propPayDateEnd);
		return sqlSession.selectOne("microblogQueue.select_Pay_UserCount", tmpMap);
	}
	@Override
	public List<TypeCodeSendCount> selectTypeCodeSendCountByGbId(BigInteger gbId){
		List<TypeCodeSendCount> codeCountList = sqlSession.selectList("microblogQueue.select_TypeCode_SendCount_ByGbId",gbId);
		return codeCountList;
	}
	@Override
	public Set<MicroblogPushType> selectMicroblogPushTypeAllList(BigInteger gbId){
		Set<MicroblogPushType> srcTypeList = null;
		{//初始type数据
			MicroblogPushTypeDataReload.reloadIfNotExist();
			List<String> existJsonData = RedisCacheHandler.hvals(CacheConstant.ModelCode.hset_microblogPushType_info);
			if(existJsonData!=null&&existJsonData.size()>0){
				srcTypeList = new HashSet<MicroblogPushType>();
				for(String tmpJson:existJsonData){
					MicroblogPushType tmpMicroblogPushType = JSON.parseObject(tmpJson, MicroblogPushType.class);
					srcTypeList.add(tmpMicroblogPushType);
				}
			}
		}
		return srcTypeList;
	}
	@Override
	public MicroblogQueue selectTopMicroblogQueueToday(BigInteger gbId,List<BigInteger> saIdList,List<TypeCodeDymPriority> priorityList){
		if(saIdList==null||saIdList.size()<=0){saIdList = new ArrayList<BigInteger>();saIdList.add(new BigInteger("0"));}
		if(priorityList==null||priorityList.size()<=0){return null;}
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbId", gbId);
		tmpMap.put("saIdList", saIdList);
		tmpMap.put("priorityList", priorityList);
		return sqlSession.selectOne("microblogQueue.select_Top_MicroblogQueue_Today", tmpMap);
	}
	@Override
	public MicroblogQueue selectMicroblogQueueForUpdate(BigInteger queueId,BigInteger gbId){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("queueId", queueId);
		tmpMap.put("gbId", gbId);
		MicroblogQueue mqDetailForUpdate = sqlSession.selectOne("microblogQueue.select_MicroblogQueue_ForUpdate",tmpMap);
		return mqDetailForUpdate;
	}
	@Override
	public boolean markMicroblogQueueAsPushAble(BigInteger queueId){
		Integer resCount = sqlSession.update("microblogQueue.update_MicroblogQueue_asPushAble_ById", queueId);
		return (resCount==null||resCount<=0)?false:true;
	}
	@Override
	public Integer selectExsitGbHasQueueCount(BigInteger queueId,BigInteger gbId){
		Integer existCount = null;
		{
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("gbId", gbId);
			tmpMap.put("queueId", queueId);
			existCount = sqlSession.selectOne("microblogQueue.select_exsit_gbHasQueue_Count",tmpMap);
		}
		return existCount;
	}

	@Override
	public SimpleAddressInfo selectSimpleAddressInfoByUserHasRoomId(
			BigInteger userHasRoomFId) {
		return sqlSession.selectOne("microblogQueue.select_SimpleAddressInfo_ByUserHasRoomId",userHasRoomFId);
	}
	@Override
	public SimpleAddressInfo selectSimpleAddressInfoByRoomId(BigInteger roomId){
		return sqlSession.selectOne("microblogQueue.select_SimpleAddressInfo_ByRoomId",roomId);
	}
	@Override
	public SimpleAddressInfo selectSimpleAddressInfoByUserDefaultRoomId(BigInteger userId){
		return sqlSession.selectOne("microblogQueue.select_SimpleAddressInfo_ByUserDefaultRoomId",userId);
	}
	
//	@Override
//	public List<GroupBuildingEntity> selectSignGroupBuildingListForMQ(
//			Map<String, Object> param) {
//		return sqlSession.selectList("microblogQueue.select_signGroupBuildingList_forMQ", param);
//	}


	@Override
	public GBLeastDiscount selectLeastDiscountByGbId(BigInteger gbId,
			String propPayDateBegin, String propPayDateEnd) {
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbId", gbId);
		tmpMap.put("propPayDateBegin", propPayDateBegin);
		tmpMap.put("propPayDateEnd", propPayDateEnd);
		return sqlSession.selectOne("microblogQueue.select_LeastDiscount_ByGbId", tmpMap);
	}

	@Override
	public List<BigInteger> selectCanPushForGbToday() {
		return sqlSession.selectList("microblogQueue.select_CanPushForGb_Today");
	}

	@Override
	public BigInteger selectGbIdForUpdate(BigInteger gbId) {
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbId", gbId);
		return sqlSession.selectOne("microblogQueue.select_GbId_ForUpdate", tmpMap);
	}

	
//	/**
//	 * 将属于服务范围的所有可推送的队列消息立即存入到微博表
//	 * @param gbId
//	 */
//	public void freshAllPuashAbleMq2MicroContent(BigInteger gbId){
//		//查询归属于当前小区的可推送的MQ列表
//		List<MicroblogQueue> toAddMQList = null;
//		{
//			List<BigInteger> saIdList = fetchSaIdList(gbId);
//			if(saIdList!=null&&saIdList.size()>0){
//				Map<String,Object> tmpMap = new HashMap<String, Object>();
//				tmpMap.put("gbId", gbId);
//				tmpMap.put("saIdList", saIdList);
//				toAddMQList = sqlSession.selectList("microblogQueue.select_PuashAble_MicroblogQueue_List", tmpMap);
//			}
//		}
//		if(toAddMQList!=null&&toAddMQList.size()>0){
//			transferMQ2MicroblogContent(toAddMQList, gbId);
//		}
//	}

	
//	/**
//	 * 批量将MQ消息存入到微博表
//	 * @param mqList
//	 * @param gbId
//	 */
//	private void transferMQ2MicroblogContent(List<MicroblogQueue> mqList,BigInteger gbId){
//		if(mqList!=null&&mqList.size()>0){
//			for(MicroblogQueue tmpMQ:mqList){
//				transferMQ2MicroblogContent(tmpMQ, gbId);
//			}
//		}
//	}
	
}
