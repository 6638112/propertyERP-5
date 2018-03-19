/**   
* Filename:    ICommonGatherDataService.java   
* @version:    1.0  
* Create at:   2015年6月29日 上午2:15:28   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年6月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import com.cnfantasia.server.api.commonBusiness.entity.GoalIdTypeForGatherData;
import com.cnfantasia.server.domainbase.commonGatherData.entity.CommonGatherData;

/**
 * Filename:    ICommonGatherDataService.java
 * @version:    1.0.0
 * Create at:   2015年6月29日 上午2:15:28
 * Description:通用模块信息归集,包含评论点赞和收藏
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年6月29日       shiyl             1.0             1.0 Version
 */
public interface ICommonGatherDataService {
	
	/**
	 * 获取单个对象的归集信息 
	 * @param goalType
	 * @param goalId
	 * @return
	 */
	public CommonGatherData getGatherDataSignal(Integer goalType, BigInteger goalId);
	
	/**
	 * 获取多个对象的归集信息 
	 * @param goalType
	 * @param goalId
	 * @return
	 */
	public List<CommonGatherData> getGatherDataMulti(Integer goalType, List<BigInteger> goalIdList);
	public List<CommonGatherData> getGatherDataMulti(Set<GoalIdTypeForGatherData> goalIdTypeList);
	
	/**
	 * 刷新总点赞数量,默认增加1
	 * @param goalType
	 * @param goalId
	 */
	public void appendTotalSupportCount(Integer goalType, BigInteger goalId,Integer count);
	/**
	 * 刷新总评论数量,默认增加1
	 * @param goalType
	 * @param goalId
	 */
	public void appendTotalCommentCount(Integer goalType, BigInteger goalId,Integer count);
	
	/**
	 * 刷新总收藏数量,默认增加1
	 * @param goalType
	 * @param goalId
	 */
	public void appendTotalCollectCount(Integer goalType, BigInteger goalId,Integer count);
	
	/**
	 * 更新统计数量，附加方式
	 * @param goalType
	 * @param goalId
	 * @param count
	 */
	public void appendCommonGatherDataAllAppendCount(Integer goalType, BigInteger goalId
			,Integer supportCount,Integer commentCount,Integer collectCount);
	
//	/**
//	 * 批量更新统计数量，附加方式
//	 * @param goalType
//	 * @param goalId
//	 * @param count
//	 */
//	public void appendCommonGatherDataAllAppendCountBatch(List<CommonGatherData> commonGatherDataList);
	
	/**
	 * 更新统计信息，参数数据为待作合并处理
	 * @param gatherDataList
	 */
	public void appendCommonGatherData2Handle(List<CommonGatherData> gatherDataList);
}
