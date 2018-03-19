package com.cnfantasia.jfq.share.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.jfq.share.entity.PrizeSurpriseGiftEntity;
import com.cnfantasia.jfq.share.entity.PrizeSurpriseGiftEntityDetail;
import com.cnfantasia.jfq.share.entity.ShareActiveEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.user.entity.User;

public interface IShareService {
	public User getUserInfo(String userId);

	public ShareActiveEntity getShareDetail(String huaid, String time);
	
	/**
	 * 查询意外惊喜列表
	 * @param userId
	 * @param userType
	 * @param pageModel
	 * @return
	 */
	public List<PrizeSurpriseGiftEntity> qryPrizeSurpriseGiftList(BigInteger userId, Integer userType,PageModel pageModel,boolean checkIsLightApp);

	/**
	 * 查询奖项详情
	 * @param detailId
	 * @return
	 */
	public PrizeSurpriseGiftEntityDetail qryPrizeSurpriseGiftDetail(BigInteger detailId,BigInteger userId, Integer userType,boolean checkIsLightApp);
	
	/**
	 * 根据Id查询详情
	 * @param detailId
	 * @return
	 */
	public PrizeSurpriseGiftEntityDetail qryPrizeSurpriseGiftDetail(BigInteger detailId);
	
}
