/**   
* Filename:    ISurpriseGiftService.java   
* @version:    1.0  
* Create at:   2015年3月25日 上午8:07:45   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.surpriseGift.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import com.cnfantasia.server.api.prize.entityDB.SupriseGiftForDB;
import com.cnfantasia.server.api.surpriseGift.entity.CouponCombiEntity;
import com.cnfantasia.server.api.surpriseGift.entity.PrizeSurpriseGiftEntity;
import com.cnfantasia.server.api.surpriseGift.entity.PrizeSurpriseGiftEntityDetail;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.surpriseGiftConfigPic.entity.SurpriseGiftConfigPic;

/**
 * Filename:    ISurpriseGiftService.java
 * @version:    1.0.0
 * Create at:   2015年3月25日 上午8:07:45
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月25日       shiyl             1.0             1.0 Version
 */
public interface ISurpriseGiftService {

	/**
	 * 查询首页图标信息
	 * @param userId
	 * @param userType
	 * @param srcType
	 * @return
	 */
	public SurpriseGiftConfigPic qryTopLog(BigInteger userId, Integer userType, Long srcType,Boolean checkIsLightApp);
	public SurpriseGiftConfigPic qryTopLog(BigInteger userId, Integer userType,Boolean checkIsLightApp);
	
	/**
	 * 查询意外惊喜列表
	 * @param userId
	 * @param userType
	 * @param pageModel
	 * @return
	 */
	public List<PrizeSurpriseGiftEntity> qryPrizeSurpriseGiftList(BigInteger userId, Integer userType,PageModel pageModel,boolean checkIsLightApp);
	
//	/**
//	 * 增加意外惊喜记录
//	 * @param name
//	 * @param dataType
//	 * @param dataId
//	 * @param userId
//	 * @param userType
//	 * @param expiryTime
//	 * @param hbAmount
//	 * @param fromType
//	 * @param fromId
//	 */
//	public PrizeSurpriseGift addPrizeSurpriseGift(String name,Integer dataType,BigInteger dataId,BigInteger userId, Integer userType,String expiryTime
//			,Long hbAmount,Integer fromType,BigInteger fromId);
	
	/**
	 * 保持意外惊喜信息
	 * @param supriseGiftForDB
	 * @param userId
	 * @param userType
	 * @param fromId
	 * @param fromType
	 * @return
	 */
	public PrizeSurpriseGiftEntity addPrizeSurpriseGift(SupriseGiftForDB supriseGiftForDB, BigInteger userId, Integer userType,
			BigInteger fromId, Integer fromType,boolean isAutoFetch,Boolean checkIsLightApp);
	
	/**
	 * 标记意外惊喜为已领取
	 * @param userId
	 * @param userType
	 * @param prizeSurpriseGiftId
	 */
	public void markPrizeSurpriseGiftAsFetched(BigInteger userId, Integer userType,BigInteger prizeSurpriseGiftId,Boolean checkIsLightApp);
	
	/**
	 * 查询意外惊喜详情
	 * @param userId
	 * @param userType
	 * @param prizeSurpriseGiftId
	 * @return
	 */
	public PrizeSurpriseGiftEntityDetail getPrizeSurpriseGiftDetail(BigInteger userId, Integer userType,BigInteger prizeSurpriseGiftId,Boolean checkIsLightApp);

	/**
	 * 直接抽取意外惊喜
	 * @param userId
	 * @param userType
	 * @param fromType
	 * @return
	 */
	public PrizeSurpriseGiftEntity getSurpriseGiftDirect(BigInteger userId, Integer userType, Integer fromType,BigInteger fromId,Boolean checkIsLightApp,String cityName);
	
	/**
	 * 查询优惠券组合信息
	 * @param userId
	 * @param userType
	 * @return
	 */
	public CouponCombiEntity getCouponCombiData(BigInteger userId, Integer userType,Long totalAmount,Integer jfqSubType);
	
	/**
	 * 查询所有可用的优惠券信息
	 * @param userId
	 * @param userType
	 * @return
	 */
	public List<PrizeSurpriseGiftEntity> getCouponAvailableList(BigInteger userId, Integer userType,Boolean checkIsLightApp);
	
	/**
	 * 查询所有可用的优惠券总数
	 * @param userId
	 * @param userType
	 * @return
	 */
	public Integer getCouponAvailableCount(BigInteger userId, Integer userType,Boolean checkIsLightApp);
	
	/**
	 * 标记对应优惠券为已使用
	 * @param userId
	 * @param userType
	 * @param couponIdList
	 */
	public void markCouponListAsUsed(BigInteger userId, Integer userType,Set<BigInteger> couponIdList,Boolean checkIsLightApp);
	
	/**
	 * 批量退回优惠券信息
	 * @param surpriseGiftIdList
	 */
	public void backSurpriseGiftBatch(List<BigInteger> surpriseGiftIdList);
	
}
