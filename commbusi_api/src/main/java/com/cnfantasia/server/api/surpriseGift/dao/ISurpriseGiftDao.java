/**   
* Filename:    ISurpriseGiftDao.java   
* @version:    1.0  
* Create at:   2015年3月25日 上午8:09:47   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.surpriseGift.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import com.cnfantasia.server.api.surpriseGift.entity.PrizeSurpriseGiftEntity;
import com.cnfantasia.server.api.surpriseGift.entity.PrizeSurpriseGiftEntityDetail;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.surpriseGiftConfigPic.entity.SurpriseGiftConfigPic;

/**
 * Filename:    ISurpriseGiftDao.java
 * @version:    1.0.0
 * Create at:   2015年3月25日 上午8:09:47
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月25日       shiyl             1.0             1.0 Version
 */
public interface ISurpriseGiftDao {
	/**
	 * 根据类别获取对应的图标配置
	 * @param type
	 * @return
	 */
	public SurpriseGiftConfigPic selectSurpriseGiftConfigPicByType(Long type);

	/**
	 * 查询意外惊喜列表
	 * @param userId
	 * @param userType
	 * @param pageModel
	 * @return
	 */
	public List<PrizeSurpriseGiftEntity> selectPrizeSurpriseGiftList(BigInteger userId, Integer userType, PageModel pageModel,Boolean isFetched,Boolean checkIsLightApp);
	/**
	 * 查询最新一个意外惊喜
	 * @param userId
	 * @param userType
	 * @return
	 */
	public PrizeSurpriseGiftEntity selectLastPrizeSurpriseGift(BigInteger userId, Integer userType,Boolean isFetched,Boolean checkIsLightApp);

	/**
	 * 更新意外惊喜为已领取
	 * @param userId
	 * @param userType
	 * @param prizeSurpriseGiftId
	 * @return
	 */
	public Integer updatePrizeSurpriseGiftAsFetched(BigInteger userId, Integer userType, BigInteger prizeSurpriseGiftId);

	/**
	 * 查询意外惊喜详情
	 * @param userId
	 * @param userType
	 * @param prizeSurpriseGiftId
	 * @return
	 */
	public PrizeSurpriseGiftEntityDetail selectPrizeSurpriseGiftDetail(BigInteger userId, Integer userType,
			BigInteger prizeSurpriseGiftId,Boolean isFetched,Boolean checkIsLightApp);

	/**
	 * 查询所有可用的优惠券列表
	 * @param userId
	 * @param userType
	 * @return
	 */
	public List<PrizeSurpriseGiftEntity> selectCouponAvailableList(BigInteger userId, Integer userType,Boolean isFetched,Boolean checkIsLightApp);
	
	/**
	 * @param userId
	 * @param userType
	 * @param isFetched
	 * @return
	 */
	public Integer getCouponAvailableCount(BigInteger userId, Integer userType, boolean isFetched,Boolean checkIsLightApp);
	
	/**
	 * 更新优惠券为已使用
	 * @param userId
	 * @param userType
	 * @param couponIdList
	 * @return
	 */
	public Integer updateCouponListAsUsed(BigInteger userId, Integer userType, Set<BigInteger> couponIdList,Boolean checkIsLightApp);

	/**
	 * 退回优惠券为可使用的状态
	 * @param surpriseGiftIdList
	 * @return
	 */
	public Integer updateSurpriseGiftBatch2NotUse(List<BigInteger> surpriseGiftIdList);

//	/**
//	 * 查询当天意外惊喜获取的次数
//	 * @param userId
//	 * @param userType
//	 * @param fromType
//	 * @param fromId
//	 * @return
//	 */
//	public Integer selectSurpriseGiftCountNowDay(BigInteger userId, Integer userType, Integer fromType,BigInteger fromId);
	
}
