/**   
* Filename:    IPrizeActivityDao.java   
* @version:    1.0  
* Create at:   2015年9月9日 下午7:56:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.commbusi.prizeActivity.entity.GiftUqMarkCode;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeActHasOptEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeActivityEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeGiftEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeGiftForPrize;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeOptionEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeOptionForList;
import com.cnfantasia.server.commbusi.prizeActivity.entity.TimeRange;
import com.cnfantasia.server.domainbase.msPrizeActivity.entity.MsPrizeActivity;
import com.cnfantasia.server.domainbase.msPrizeOption.entity.MsPrizeOption;

/**
 * Filename:    IPrizeActivityDao.java
 * @version:    1.0.0
 * Create at:   2015年9月9日 下午7:56:27
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月9日       shiyl             1.0             1.0 Version
 */
public interface IPrizeActivityDao {

	/**
	 * 按条件查询抽奖活动列表
	 * @param name
	 * @param activityStatus
	 * @param startTime
	 * @param endTime
	 * @param pageModel
	 * @return
	 */
	List<MsPrizeActivity> selectMsPrizeActivityList(String name,
			Integer activityStatus, String startTime, String endTime,
			PageModel pageModel);

	/**
	 * 查看活动详情
	 * @param actId
	 * @return
	 */
	public MsPrizeActivityEntity selectMsPrizeActivityDetail(BigInteger actId);
	
	/**
	 * 查询活动的展示状态
	 * @param actId
	 * @return
	 */
	public Integer selectMsPrizeActivityQryStatus(BigInteger actId);
	
	/**
	 * 查询活动包含的奖项列表
	 * @param actId
	 * @return
	 */
	public List<MsPrizeActHasOptEntity> selectActHasOptList(BigInteger actId);

	/**
	 * 查询奖项列表
	 * @param name
	 * @param qryStatus
	 * @param pageModel
	 * @return
	 */
	public List<MsPrizeOptionForList> selectMsPrizeOptionList(String name, Integer qryStatus,Integer useStatus,
			PageModel pageModel);
	/**
	 * 查询可用于参加活动的奖项列表
	 * @return
	 */
	public List<MsPrizeOption> selectMsPrizeOptionAvalList(String actStartTime,String actEndTime,BigInteger ignoreActId);

	/**
	 * 查询奖项详情
	 * @param priOptId
	 * @return
	 */
	public MsPrizeOptionEntity selectMsPrizeOptionDetail(BigInteger priOptId);
	
	/**
	 * 查询奖项被哪些活动使用
	 * @param priOptId
	 * @return
	 */
	public List<String> selectMsPrizeOptionIsUsedPriActList(BigInteger priOptId);
	
	/**
	 * 查询所有服务时间范围列表
	 * @return
	 */
	public List<TimeRange> selectPrizeActivityTimeRangeList();

	/**
	 * 查询某个奖项下面可用的奖品数量
	 * @param priOptId
	 * @return
	 */
	public Integer selectLeftPrizeOptionCount(BigInteger priOptId);

	/**
	 * 查询存在重复数据的code列表
	 * @param dataMapKey
	 * @return
	 */
	public Set<GiftUqMarkCode> selectRepeatCodeSet(Set<GiftUqMarkCode> toCheckCodeSet);

	/**
	 * 查询奖品列表
	 * @param priOptId
	 * @param codeName
	 * @param valueCode
	 * @param uqMark
	 * @param qryStatus
	 * @param pageModel
	 * @return
	 */
	public List<MsPrizeGiftEntity> selectMsPrizeGiftList(BigInteger priOptId,
			String codeName, String valueCode, String uqMark,
			Integer qryStatus, PageModel pageModel);

	/**
	 * 删除奖品及对应code信息
	 * @param giftId
	 * @return
	 */
	public Integer deleteMsPrizeGiftAndCodeLogic(BigInteger giftId);

	/**
	 * 删除奖项当前的城市配置信息
	 * @param optId
	 */
	public Integer deletePirzeOptionCity(BigInteger optId);

	/**
	 * 删除活动,同时删除关联奖项关系
	 * @param actId
	 * @return
	 */
	public Integer deleteMsPrizeActivityWithRelaLogic(BigInteger actId);

	/**
	 * 查询当前在进行中的活动
	 * @return
	 */
	public MsPrizeActivity selectMsPrizeActivityCurrDoing();

	/**
	 * 查询奖品详情
	 * @param giftId
	 * @return
	 */
	public MsPrizeGiftForPrize selectMsPrizeGiftDetail(BigInteger giftId);

}
