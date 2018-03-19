/**   
* Filename:    SurpriseGiftDao.java   
* @version:    1.0  
* Create at:   2015年3月25日 上午8:09:58   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.surpriseGift.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.surpriseGift.entity.PrizeSurpriseGiftEntity;
import com.cnfantasia.server.api.surpriseGift.entity.PrizeSurpriseGiftEntityDetail;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.domainbase.surpriseGiftConfigPic.entity.SurpriseGiftConfigPic;

/**
 * Filename:    SurpriseGiftDao.java
 * @version:    1.0.0
 * Create at:   2015年3月25日 上午8:09:58
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月25日       shiyl             1.0             1.0 Version
 */
public class SurpriseGiftDao extends AbstractBaseDao implements ISurpriseGiftDao{
	
	/**是否已领取*/
	private void appendExtData(Map<String,Object> tmpMap,Boolean isFetched){
		if(isFetched!=null){
			//<!-- '领取状态=={"1":"未领取","2":"已领取"}' -->
			tmpMap.put("fetchStatus", isFetched?2:1);
		}
	}
	
	private void appendSubChannelData(Map<String,Object> tmpMap,Boolean checkIsLightApp){
		if(checkIsLightApp==null){//默认为App
			checkIsLightApp = false;
		}
		tmpMap.put("checkIsLightApp", checkIsLightApp);
	}
	
	@Override
	public SurpriseGiftConfigPic selectSurpriseGiftConfigPicByType(Long type) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("type", type);
		return sqlSession.selectOne("surpriseGift.select_SurpriseGiftConfigPic_ByType", tmpMap);
	}

	@Override
	public List<PrizeSurpriseGiftEntity> selectPrizeSurpriseGiftList(BigInteger userId, Integer userType, PageModel pageModel,Boolean isFetched,Boolean checkIsLightApp) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		appendExtData(tmpMap, isFetched);
		appendSubChannelData(tmpMap, checkIsLightApp);
		String pageSqlKey = "surpriseGift.select_PrizeSurpriseGift_List_page";
		String countSqlKey = "surpriseGift.select_PrizeSurpriseGift_List_count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public Integer updatePrizeSurpriseGiftAsFetched(BigInteger userId, Integer userType, BigInteger prizeSurpriseGiftId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("userId", userId);
//		tmpMap.put("userType", userType);
		tmpMap.put("prizeSurpriseGiftId", prizeSurpriseGiftId);
		return sqlSession.update("surpriseGift.update_PrizeSurpriseGift_AsFetched", tmpMap);
	}

	@Override
	public PrizeSurpriseGiftEntityDetail selectPrizeSurpriseGiftDetail(BigInteger userId, Integer userType,
			BigInteger prizeSurpriseGiftId,Boolean isFetched,Boolean checkIsLightApp){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("userId", userId);
//		tmpMap.put("userType", userType);
		appendExtData(tmpMap, isFetched);
		appendSubChannelData(tmpMap, checkIsLightApp);
		tmpMap.put("prizeSurpriseGiftId", prizeSurpriseGiftId);
		return sqlSession.selectOne("surpriseGift.select_PrizeSurpriseGift_Detail", tmpMap);
	}

	@Override
	public PrizeSurpriseGiftEntity selectLastPrizeSurpriseGift(BigInteger userId, Integer userType,Boolean isFetched,Boolean checkIsLightApp) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		appendExtData(tmpMap, isFetched);
		appendSubChannelData(tmpMap, checkIsLightApp);
		return sqlSession.selectOne("surpriseGift.select_Last_PrizeSurpriseGift", tmpMap);
	}

	@Override
	public List<PrizeSurpriseGiftEntity> selectCouponAvailableList(BigInteger userId, Integer userType,Boolean isFetched,Boolean checkIsLightApp) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		appendExtData(tmpMap, isFetched);
		appendSubChannelData(tmpMap, checkIsLightApp);
		return sqlSession.selectList("surpriseGift.select_Coupon_Available_List", tmpMap);
	}
	
	@Override
	public Integer getCouponAvailableCount(BigInteger userId, Integer userType, boolean isFetched,Boolean checkIsLightApp) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		appendExtData(tmpMap, isFetched);
		appendSubChannelData(tmpMap, checkIsLightApp);
		return sqlSession.selectOne("surpriseGift.select_Coupon_Available_Count", tmpMap);
	}
	
	@Override
	public Integer updateCouponListAsUsed(BigInteger userId, Integer userType, Set<BigInteger> couponIdList,Boolean checkIsLightApp) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		tmpMap.put("couponIdList", couponIdList);
		appendSubChannelData(tmpMap, checkIsLightApp);
		return sqlSession.update("surpriseGift.update_CouponList_As_Used", tmpMap);
	}

	@Override
	public Integer updateSurpriseGiftBatch2NotUse(List<BigInteger> surpriseGiftIdList) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("surpriseGiftIdList", surpriseGiftIdList);
		return sqlSession.update("surpriseGift.update_SurpriseGift_Batch_2NotUse", tmpMap);
	}

}
