package com.cnfantasia.jfq.share.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.jfq.share.entity.PrizeSurpriseGiftEntity;
import com.cnfantasia.jfq.share.entity.PrizeSurpriseGiftEntityDetail;
import com.cnfantasia.jfq.share.entity.ShareActiveEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;

public interface IShareDao {

	ShareActiveEntity getShareDetail(String huaid, String time);
	
	/**
	 * 查询意外惊喜列表
	 * @param userId
	 * @param userType
	 * @param pageModel
	 * @return
	 */
	public List<PrizeSurpriseGiftEntity> selectPrizeSurpriseGiftList(BigInteger userId, Integer userType, PageModel pageModel,Boolean isFetched,Boolean checkIsLightApp);

	/**
	 * @param userId
	 * @param userType
	 * @param detailId
	 * @param isFetched
	 * @param checkIsLightApp
	 * @return
	 */
	public PrizeSurpriseGiftEntityDetail selectPrizeSurpriseGiftDetail(BigInteger userId, Integer userType, BigInteger detailId, Boolean isFetched, boolean checkIsLightApp);

	/**
	 * @param detailId
	 * @return
	 */
	public PrizeSurpriseGiftEntityDetail selectPrizeSurpriseGiftDetailById(BigInteger detailId);

}
