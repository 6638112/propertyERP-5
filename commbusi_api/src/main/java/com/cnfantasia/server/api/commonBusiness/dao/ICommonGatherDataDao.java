/**   
* Filename:    ICommonGatherDataDao.java   
* @version:    1.0  
* Create at:   2015年6月29日 上午2:16:08   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年6月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import com.cnfantasia.server.api.commonBusiness.entity.GoalIdTypeForGatherData;
import com.cnfantasia.server.domainbase.commonGatherData.entity.CommonGatherData;

/**
 * Filename:    ICommonGatherDataDao.java
 * @version:    1.0.0
 * Create at:   2015年6月29日 上午2:16:08
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年6月29日       shiyl             1.0             1.0 Version
 */
public interface ICommonGatherDataDao {
	
	/**
	 * 查询单个信息详情
	 * @param goalType
	 * @param goalId
	 * @return
	 */
	public CommonGatherData selectGatherDataSignal(Integer goalType, BigInteger goalId);

	/**
	 * 查询多个信息详情
	 * @param goalType
	 * @param goalIdList
	 * @return
	 */
	public List<CommonGatherData> selectGatherDataMulti(Integer goalType, List<BigInteger> goalIdList);
	public List<CommonGatherData> selectGatherDataMulti(Set<GoalIdTypeForGatherData> goalIdTypeList);
	
//	/**
//	 * 更新点赞数量，附加方式
//	 * @param goalType
//	 * @param goalId
//	 * @param count
//	 */
//	public Integer updateCommonGatherDataSupportAppendCount(Integer goalType, BigInteger goalId, Integer count);
//
//	/**
//	 * 更新评论数量，附加方式
//	 * @param goalType
//	 * @param goalId
//	 * @param count
//	 */
//	public Integer updateCommonGatherDataCommentAppendCount(Integer goalType, BigInteger goalId, Integer count);
//
//	/**
//	 * 更新收藏数量，附加方式
//	 * @param goalType
//	 * @param goalId
//	 * @param count
//	 */
//	public Integer updateCommonGatherDataCollectAppendCount(Integer goalType, BigInteger goalId, Integer count);
	
//	/**
//	 * 更新统计数量，附加方式
//	 * @param goalType
//	 * @param goalId
//	 * @param count
//	 */
//	public Integer updateCommonGatherDataAllAppendCount(Integer goalType, BigInteger goalId
//			,Integer supportCount,Integer commentCount,Integer collectCount);
	
	/**
	 * 批量更新统计数量，附加方式
	 * @param goalType
	 * @param goalId
	 * @param count
	 */
	public Integer updateCommonGatherDataAllAppendCountBatch(List<CommonGatherData> commonGatherDataList);
	
}
