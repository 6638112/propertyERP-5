/**   
* Filename:    IPrizeActivityService.java   
* @version:    1.0  
* Create at:   2015年9月9日 下午7:55:48   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.ActHasOptParam;
import com.cnfantasia.server.commbusi.prizeActivity.entity.ActOptionParam;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeActHasOptEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeActivityEntity;
import com.cnfantasia.server.domainbase.msPrizeActivity.entity.MsPrizeActivity;
import com.cnfantasia.server.domainbase.msPrizeOption.entity.MsPrizeOption;

/**
 * Filename:    IPrizeActivityService.java
 * @version:    1.0.0
 * Create at:   2015年9月9日 下午7:55:48
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月9日       shiyl             1.0             1.0 Version
 */
public interface IPrizeActivityService {
	
	/**
	 * 查询活动列表
	 * @param name
	 * @param activityStatus                                                      
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<MsPrizeActivity> getMsPrizeActivityList(String name,Integer activityStatus,String startTime,String endTime,PageModel pageModel);
	
	/**
	 * 查询活动详情
	 * @param actId
	 * @return
	 */
	public MsPrizeActivityEntity getMsPrizeActivityDetail(BigInteger actId);
	
	/**
	 * 删除活动
	 * @param actId
	 * @return
	 */
	public void deleteMsPrizeActivity(BigInteger actId);
	
	/**
	 * 添加活动
	 * @param name 活动名称
	 * @param startTime 活动开始时间
	 * @param endTime 活动截止时间
	 * @param shareText 分析文案
	 * @param shareIconImage 分析的图标
	 * @param actOptionParamList 包含的奖项列表
	 * @param activityStatus 活动状态
	 * @return
	 */
	public void addMsPrizeActivity(BigInteger userId,String name,String startTime,String endTime,String shareText
			,RequestFileEntity shareIconImage,List<ActOptionParam> actOptionParamList,Integer activityStatus);
	
	/**
	 * 查询活动包含的奖项列表信息
	 * @param actId
	 * @return
	 */
	public List<MsPrizeActHasOptEntity> getActHasOptList(BigInteger actId);
	
	/**
	 * 修改活动信息
	 * @param prizeActId 活动Id
	 * @param userId
	 * @param name
	 * @param startTime
	 * @param endTime
	 * @param shareText
	 * @param shareIconImage
	 * @param actOptionParamList
	 * @param activityStatus
	 * @param actHasOptParamList
	 */
	public void updateMsPrizeActivity(BigInteger prizeActId,BigInteger userId,String name,String startTime,String endTime,String shareText
			,RequestFileEntity shareIconImage,List<ActOptionParam> actOptionParamList,Integer activityStatus,List<ActHasOptParam> actHasOptParamList);
	
	/**
	 * 查询某个奖项下面可用的奖品数量
	 * @param optId
	 * @return
	 */
	public Integer getLeftPrizeOptionCount(BigInteger optId);
	
	/**
	 * 查询可用于参加活动的奖项列表
	 * @param actStartTime 活动开始时间
	 * @param actEndTime 活动结束时间
	 * @param ignoreActId 去除的活动Id
	 * @return
	 */
	public List<MsPrizeOption> getMsPrizeOptionAvalList(String actStartTime,String actEndTime,BigInteger ignoreActId);
	
	/**
	 * 查询当前在进行中的活动
	 * @return
	 */
	public MsPrizeActivity getMsPrizeActivityCurrDoing();
	
}
