/**   
* Filename:    CommonGatherDataService.java   
* @version:    1.0  
* Create at:   2015年6月29日 上午2:16:21   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年6月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.cnfantasia.server.api.commonBusiness.dao.ICommonGatherDataDao;
import com.cnfantasia.server.api.commonBusiness.entity.GoalIdTypeForGatherData;
import com.cnfantasia.server.api.commonBusiness.queue.GatherDataPool;
import com.cnfantasia.server.domainbase.commonGatherData.entity.CommonGatherData;

/**
 * Filename:    CommonGatherDataService.java
 * @version:    1.0.0
 * Create at:   2015年6月29日 上午2:16:21
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年6月29日       shiyl             1.0             1.0 Version
 */
public class CommonGatherDataService implements ICommonGatherDataService{
	
	private ICommonGatherDataDao commonGatherDataDao;
	public void setCommonGatherDataDao(ICommonGatherDataDao commonGatherDataDao) {
		this.commonGatherDataDao = commonGatherDataDao;
	}
	
	@Override
	public CommonGatherData getGatherDataSignal(Integer goalType, BigInteger goalId) {
		return commonGatherDataDao.selectGatherDataSignal(goalType,goalId);
	}
	
	@Override
	public List<CommonGatherData> getGatherDataMulti(Integer goalType, List<BigInteger> goalIdList) {
		return commonGatherDataDao.selectGatherDataMulti(goalType,goalIdList);
	}

	@Override
	public List<CommonGatherData> getGatherDataMulti(Set<GoalIdTypeForGatherData> goalIdTypeList) {
		return commonGatherDataDao.selectGatherDataMulti(goalIdTypeList);
	}
	
	@Override
	public void appendTotalSupportCount(Integer goalType, BigInteger goalId, Integer count) {
		Integer supportCount = count;
		Integer commentCount = null;
		Integer collectCount = null;
		appendCommonGatherDataAllAppendCount(goalType, goalId, supportCount, commentCount, collectCount);
	}

	@Override
	public void appendTotalCommentCount(Integer goalType, BigInteger goalId, Integer count) {
		Integer supportCount = null;
		Integer commentCount = count;
		Integer collectCount = null;
		appendCommonGatherDataAllAppendCount(goalType, goalId, supportCount, commentCount, collectCount);
	}

	@Override
	public void appendTotalCollectCount(Integer goalType, BigInteger goalId, Integer count) {
		Integer supportCount = null;
		Integer commentCount = null;
		Integer collectCount = count;
		appendCommonGatherDataAllAppendCount(goalType, goalId, supportCount, commentCount, collectCount);
	}

	@Override
	public void appendCommonGatherDataAllAppendCount(Integer goalType, BigInteger goalId, Integer supportCount,
			Integer commentCount, Integer collectCount) {
		supportCount = supportCount==null?0:supportCount;
		commentCount = commentCount==null?0:commentCount;
		collectCount = collectCount==null?0:collectCount;
		//用户点赞，取消赞 业务处理流程
		//1.点赞详情表增加或者逻辑删除一条记录
		//2.缓存队列附加 一条操作记录的数据
		CommonGatherData tmpCommonGatherData = new CommonGatherData();
		tmpCommonGatherData.setTargetId(goalId);
		tmpCommonGatherData.setTargetType(goalType);
		tmpCommonGatherData.setTotalCollectCount((long)collectCount);
		tmpCommonGatherData.setTotalCommentCount((long)commentCount);
		tmpCommonGatherData.setTotalSupportCount((long)supportCount);
		GatherDataPool.addGatherData(tmpCommonGatherData);
//		commonGatherDataDao.updateCommonGatherDataAllAppendCount(goalType, goalId, supportCount, commentCount, collectCount);
	}

//	@Override
//	public void appendCommonGatherDataAllAppendCountBatch(List<CommonGatherData> commonGatherDataList) {
//		commonGatherDataDao.updateCommonGatherDataAllAppendCountBatch(commonGatherDataList);
//	}

	@Override
	public void appendCommonGatherData2Handle(List<CommonGatherData> gatherDataList) {
		if(gatherDataList==null||gatherDataList.size()<=0){
			return;
		}
		Map<GoalIdTypeForGatherData,List<CommonGatherData>> srcMapList = new HashMap<GoalIdTypeForGatherData, List<CommonGatherData>>();
		//1.统计IdType相同的
		for(CommonGatherData tmpCommonGatherData:gatherDataList){
			GoalIdTypeForGatherData goalIdType = new GoalIdTypeForGatherData(tmpCommonGatherData.getTargetType(), tmpCommonGatherData.getTargetId());
			List<CommonGatherData> tmpList = srcMapList.get(goalIdType);
			if(tmpList == null){
				tmpList = new ArrayList<CommonGatherData>();
				tmpList.add(tmpCommonGatherData);
				srcMapList.put(goalIdType, tmpList);
			}else{
				tmpList.add(tmpCommonGatherData);
			}
		}
		//2.数据合并到一起
		List<CommonGatherData> lastGatherDataList = new ArrayList<CommonGatherData>();
		for(Entry<GoalIdTypeForGatherData, List<CommonGatherData>> tmpEntry:srcMapList.entrySet()){
			GoalIdTypeForGatherData key = tmpEntry.getKey();
			List<CommonGatherData> value = tmpEntry.getValue();
			CommonGatherData lastTmp = new CommonGatherData();
			lastTmp.setTargetId(key.getTargetId());
			lastTmp.setTargetType(key.getTargetType());
			Long totalCollectCount = 0L;
			Long totalCommentCount = 0L;
			Long totalSupportCount = 0L;
			for(CommonGatherData tmpAA:value){
				totalCollectCount+=tmpAA.getTotalCollectCount();
				totalCommentCount+=tmpAA.getTotalCommentCount();
				totalSupportCount+=tmpAA.getTotalSupportCount();
			}
			lastTmp.setTotalCollectCount(totalCollectCount);
			lastTmp.setTotalCommentCount(totalCommentCount);
			lastTmp.setTotalSupportCount(totalSupportCount);
			lastGatherDataList.add(lastTmp);
		}
		//3.执行批量提交
		commonGatherDataDao.updateCommonGatherDataAllAppendCountBatch(lastGatherDataList);
	}

}
