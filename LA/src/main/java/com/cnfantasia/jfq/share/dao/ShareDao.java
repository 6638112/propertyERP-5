package com.cnfantasia.jfq.share.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.jfq.share.entity.PrizeSurpriseGiftEntity;
import com.cnfantasia.jfq.share.entity.PrizeSurpriseGiftEntityDetail;
import com.cnfantasia.jfq.share.entity.ShareActiveEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

public class ShareDao extends AbstractBaseDao implements IShareDao {

	@Override
	public ShareActiveEntity getShareDetail(String huaid, String time) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("huaid", huaid);
		paramMap.put("time", time);

		return sqlSession.selectOne("share.select_shareDetail_byShareId", paramMap);
	}
	
	@Override
	public List<PrizeSurpriseGiftEntity> selectPrizeSurpriseGiftList(BigInteger userId, Integer userType, PageModel pageModel,Boolean isFetched,Boolean checkIsLightApp) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("userType", userType);
		appendExtData(tmpMap, isFetched);
		appendSubChannelData(tmpMap, checkIsLightApp);
		String pageSqlKey = "share.select_PrizeSurpriseGift_List_page";
		String countSqlKey = "share.select_PrizeSurpriseGift_List_count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}
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
	public PrizeSurpriseGiftEntityDetail selectPrizeSurpriseGiftDetail(BigInteger userId, Integer userType, BigInteger detailId, Boolean isFetched,
			boolean checkIsLightApp) {
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("userId", userId);
//		tmpMap.put("userType", userType);
//		tmpMap.put("detailId", detailId);
//		appendExtData(tmpMap, isFetched);
//		appendSubChannelData(tmpMap, checkIsLightApp);
//		return sqlSession.selectOne("share.select_PrizeSurpriseGift_Detail", tmpMap);
		return selectPrizeSurpriseGiftDetailById(detailId);
	}

	@Override
	public PrizeSurpriseGiftEntityDetail selectPrizeSurpriseGiftDetailById(BigInteger detailId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("detailId", detailId);
		return sqlSession.selectOne("share.select_PrizeSurpriseGiftDetail_ById", tmpMap);
	}
}
