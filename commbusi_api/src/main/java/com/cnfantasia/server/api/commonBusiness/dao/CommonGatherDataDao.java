/**   
* Filename:    CommonGatherDataDao.java   
* @version:    1.0  
* Create at:   2015年6月29日 上午2:16:45   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年6月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cnfantasia.server.api.commonBusiness.entity.GoalIdTypeForGatherData;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.commonGatherData.dao.ICommonGatherDataBaseDao;
import com.cnfantasia.server.domainbase.commonGatherData.entity.CommonGatherData;

/**
 * Filename:    CommonGatherDataDao.java
 * @version:    1.0.0
 * Create at:   2015年6月29日 上午2:16:45
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年6月29日       shiyl             1.0             1.0 Version
 */
public class CommonGatherDataDao extends AbstractBaseDao implements ICommonGatherDataDao{
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	private ICommonGatherDataBaseDao commonGatherDataBaseDao;
	public void setCommonGatherDataBaseDao(ICommonGatherDataBaseDao commonGatherDataBaseDao) {
		this.commonGatherDataBaseDao = commonGatherDataBaseDao;
	}
	
	@Override
	public CommonGatherData selectGatherDataSignal(Integer goalType, BigInteger goalId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("goalType", goalType);
		tmpMap.put("goalId", goalId);
		return sqlSession.selectOne("commonGatherData.select_GatherData_Signal", tmpMap);
	}

	@Override
	public List<CommonGatherData> selectGatherDataMulti(Integer goalType, List<BigInteger> goalIdList) {
		if(goalIdList==null||goalIdList.size()<=0){
			return null;
		}
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("goalType", goalType);
		tmpMap.put("goalIdList", goalIdList);
		return sqlSession.selectList("commonGatherData.select_GatherData_Multi_oneType", tmpMap);
	}

	@Override
	public List<CommonGatherData> selectGatherDataMulti(Set<GoalIdTypeForGatherData> goalIdTypeList) {
			List<Map<String,Object>> paramMapList = new ArrayList<Map<String,Object>>();
			for(GoalIdTypeForGatherData tmpData:goalIdTypeList){
				Map<String,Object> tmpMap = new HashMap<String, Object>();
				tmpMap.put("goalType", tmpData.getTargetType());
				tmpMap.put("goalId", tmpData.getTargetId());
				paramMapList.add(tmpMap);
			}
			List<CommonGatherData> existList = sqlSession.selectList("commonGatherData.select_GatherData_Multi_someType", paramMapList);
			return existList;
	}
	
//	@Override
//	public Integer updateCommonGatherDataSupportAppendCount(Integer goalType, BigInteger goalId, Integer count) {
//		Integer supportCount = count;
//		Integer commentCount = null;
//		Integer collectCount = null;
//		return updateCommonGatherDataAllAppendCount(goalType, goalId, supportCount, commentCount, collectCount);
//	}
//
//	@Override
//	public Integer updateCommonGatherDataCommentAppendCount(Integer goalType, BigInteger goalId, Integer count) {
//		Integer supportCount = null;
//		Integer commentCount = count;
//		Integer collectCount = null;
//		return updateCommonGatherDataAllAppendCount(goalType, goalId, supportCount, commentCount, collectCount);
//	}
//
//	@Override
//	public Integer updateCommonGatherDataCollectAppendCount(Integer goalType, BigInteger goalId, Integer count) {
//		Integer supportCount = null;
//		Integer commentCount = null;
//		Integer collectCount = count;
//		return updateCommonGatherDataAllAppendCount(goalType, goalId, supportCount, commentCount, collectCount);
//	}
	
//	@Override
//	public Integer updateCommonGatherDataAllAppendCount(Integer goalType, BigInteger goalId, Integer supportCount,
//			Integer commentCount, Integer collectCount) {
//		//查询是否存在，存在则更新,不存在则新增
//		CommonGatherData signalData = selectGatherDataSignal(goalType, goalId);
//		if(signalData==null){
//			CommonGatherData commonGatherDataAdd = new CommonGatherData();
//			BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_common_gather_data);
//			String lastUpdateTime = dualDao.getNowTime();
//			commonGatherDataAdd.setId(addId);
//			commonGatherDataAdd.setLastUpdateTime(lastUpdateTime);
//			commonGatherDataAdd.setTargetId(goalId);
//			commonGatherDataAdd.setTargetType(goalType);
//			commonGatherDataAdd.setTotalCollectCount((long)collectCount);
//			commonGatherDataAdd.setTotalCommentCount((long)commentCount);
//			commonGatherDataAdd.setTotalSupportCount((long)supportCount);
//			return commonGatherDataBaseDao.insertCommonGatherData(commonGatherDataAdd);
//		}else{
//			CommonGatherData commonGatherDataUpd = new CommonGatherData();
//			String lastUpdateTime = dualDao.getNowTime();
//			commonGatherDataUpd.setId(signalData.getId());
//			commonGatherDataUpd.setLastUpdateTime(lastUpdateTime);
//			commonGatherDataUpd.setTotalCollectCount((long)collectCount+signalData.getTotalCollectCount());
//			commonGatherDataUpd.setTotalCommentCount((long)commentCount+signalData.getTotalCommentCount());
//			commonGatherDataUpd.setTotalSupportCount((long)supportCount+signalData.getTotalSupportCount());
//			return commonGatherDataBaseDao.updateCommonGatherData(commonGatherDataUpd);
//		}
//	}
	
	@Override
	public Integer updateCommonGatherDataAllAppendCountBatch(List<CommonGatherData> commonGatherDataList) {
		if(commonGatherDataList==null || commonGatherDataList.size()<=0){
			return 0;
		}
		Map<GoalIdTypeForGatherData,CommonGatherData> srcMap = new HashMap<GoalIdTypeForGatherData, CommonGatherData>();
		for(CommonGatherData tmpGatherData:commonGatherDataList){
			GoalIdTypeForGatherData goalIdType = new GoalIdTypeForGatherData(tmpGatherData.getTargetType(), tmpGatherData.getTargetId());
			srcMap.put(goalIdType, tmpGatherData);
		}
		List<CommonGatherData> existCommonGatherDataList = selectGatherDataMulti(srcMap.keySet());
		//1.判断哪些需要新增，哪些需要更新
		List<CommonGatherData> toAddList = new ArrayList<CommonGatherData>();
		List<CommonGatherData> toUpdList = new ArrayList<CommonGatherData>();
			for(CommonGatherData srcCommonGatherData:commonGatherDataList){
				boolean isExist = false;
				CommonGatherData existTmpGatherData = null;
				if(existCommonGatherDataList!=null&&existCommonGatherDataList.size()>0){
					for(CommonGatherData tmpExistGatherData:existCommonGatherDataList){
						if(srcCommonGatherData.getTargetId().compareTo(tmpExistGatherData.getTargetId())==0
								&&srcCommonGatherData.getTargetType().compareTo(tmpExistGatherData.getTargetType())==0){
							isExist = true;
							existTmpGatherData = tmpExistGatherData;
							break;
						}
					}
				}
				CommonGatherData tmpAA = new CommonGatherData();
				tmpAA.setTargetId(srcCommonGatherData.getTargetId());
				tmpAA.setTargetType(srcCommonGatherData.getTargetType());
				
				if(isExist){//存在则更新
					tmpAA.setId(existTmpGatherData.getId());//设定Id
					Long totalCollectCount = srcCommonGatherData.getTotalCollectCount()/*+existTmpGatherData.getTotalCollectCount()*/;//syl-upd
					Long totalCommentCount = srcCommonGatherData.getTotalCommentCount()/*+existTmpGatherData.getTotalCommentCount()*/;//syl-upd
					Long totalSupportCount = srcCommonGatherData.getTotalSupportCount()/*+existTmpGatherData.getTotalSupportCount()*/;//syl-upd
//					totalCollectCount = totalCollectCount<=0?0:totalCollectCount;//syl-upd
//					totalCommentCount = totalCommentCount<=0?0:totalCommentCount;//syl-upd
//					totalSupportCount = totalSupportCount<=0?0:totalSupportCount;//syl-upd
					tmpAA.setTotalCollectCount(totalCollectCount);//累计数量
					tmpAA.setTotalCommentCount(totalCommentCount);
					tmpAA.setTotalSupportCount(totalSupportCount);
					toUpdList.add(tmpAA);
				}else{//不存在则新增
					tmpAA.setTotalCollectCount(srcCommonGatherData.getTotalCollectCount()<=0?0:srcCommonGatherData.getTotalCollectCount());//设定数量
					tmpAA.setTotalCommentCount(srcCommonGatherData.getTotalCommentCount()<=0?0:srcCommonGatherData.getTotalCommentCount());
					tmpAA.setTotalSupportCount(srcCommonGatherData.getTotalSupportCount()<=0?0:srcCommonGatherData.getTotalSupportCount());
					toAddList.add(tmpAA);
				}
				
			}
	
		//2.执行新增和更新
		int addCount = 0;
		int updCount = 0;
		String lastUpdateTime = dualDao.getNowTime();
		if(toAddList!=null&&toAddList.size()>0){
			List<BigInteger> addIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_common_gather_data,toAddList.size());
			for(int i=0;i<toAddList.size();i++){
				CommonGatherData indexI = toAddList.get(i);
				indexI.setId(addIdList.get(i));//设定Id
				indexI.setLastUpdateTime(lastUpdateTime);//设定更新时间
			}
			addCount = commonGatherDataBaseDao.insertCommonGatherDataBatch(toAddList);
		}
		if(toUpdList!=null&&toUpdList.size()>0){
			/*for(int i=0;i<toUpdList.size();i++){
				CommonGatherData indexI = toUpdList.get(i);
				indexI.setLastUpdateTime(lastUpdateTime);
			}*/  //syl-upd
//			updCount= commonGatherDataBaseDao.updateCommonGatherDataBatch(toUpdList);//syl-upd
			updCount= sqlSession.update("commonGatherData.update_CommonGatherData_Batch_Append", toUpdList);//syl-upd
		}
		return addCount+updCount;
	}
	
}
