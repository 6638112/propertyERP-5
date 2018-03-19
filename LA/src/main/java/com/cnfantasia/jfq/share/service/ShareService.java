package com.cnfantasia.jfq.share.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cnfantasia.jfq.share.dao.IShareDao;
import com.cnfantasia.jfq.share.entity.PrizeSurpriseGiftEntity;
import com.cnfantasia.jfq.share.entity.PrizeSurpriseGiftEntityDetail;
import com.cnfantasia.jfq.share.entity.ShareActiveEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commSysPara.service.CommSysParaBaseService;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.user.service.UserBaseService;

public class ShareService implements IShareService {
	@Resource
	CommSysParaBaseService commSysParaBaseService;
	@Resource
	UserBaseService userBaseService;

	@Resource
	IShareDao ShareDao;

	@Override
	public User getUserInfo(String userId) {
		User user = userBaseService.getUserBySeqId(new BigInteger(userId));

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sysParaCode", "FileServerConfig");
		String fileServerConfig = commSysParaBaseService.getCommSysParaByConditionDim(paramMap).get(0).getSysParaValue();
		String accessBaseURL = fileServerConfig.split(";")[0];
		paramMap.put("sysParaCode", "UserImageProfileConfig");
		String userImageProfileConfig = commSysParaBaseService.getCommSysParaByConditionDim(paramMap).get(0).getSysParaValue();
		String userImageProfileURL = userImageProfileConfig.split(";")[0];
		user.setImgprofile(accessBaseURL + userImageProfileURL + user.getImgprofile());
		return user;
	}

	@Override
	public ShareActiveEntity getShareDetail(String huaid, String time) {
		return ShareDao.getShareDetail(huaid, time);
	}
	
	@Override
	public List<PrizeSurpriseGiftEntity> qryPrizeSurpriseGiftList(BigInteger userId, Integer userType, PageModel pageModel,boolean checkIsLightApp) {
		return ShareDao.selectPrizeSurpriseGiftList(userId,userType,pageModel,true,checkIsLightApp);
	}

	@Override
	public PrizeSurpriseGiftEntityDetail qryPrizeSurpriseGiftDetail(BigInteger detailId,BigInteger userId, Integer userType,boolean checkIsLightApp) {
		return ShareDao.selectPrizeSurpriseGiftDetail(userId,userType,detailId,true,checkIsLightApp);
	}

	@Override
	public PrizeSurpriseGiftEntityDetail qryPrizeSurpriseGiftDetail(BigInteger detailId) {
		return ShareDao.selectPrizeSurpriseGiftDetailById(detailId);
	}
}
