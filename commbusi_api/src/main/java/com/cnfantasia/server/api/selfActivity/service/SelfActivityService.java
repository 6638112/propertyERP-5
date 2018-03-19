package com.cnfantasia.server.api.selfActivity.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.selfActivity.dao.SelfActivityDao;
import com.cnfantasia.server.api.selfActivity.dict.SelfActivityDict;
import com.cnfantasia.server.api.selfActivity.entity.SeftActivityWithDetail;
import com.cnfantasia.server.api.selfActivity.entity.SelfActivity4List;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.selfActivity.dao.ISelfActivityBaseDao;
import com.cnfantasia.server.domainbase.selfActivity.entity.SelfActivity;
import com.cnfantasia.server.domainbase.selfActivityDetail.dao.ISelfActivityDetailBaseDao;
import com.cnfantasia.server.domainbase.selfActivityDetail.entity.SelfActivityDetail;

public class SelfActivityService {
	
	@Resource
	private SelfActivityDao selfActivityDao;
	@Resource
	private ISelfActivityBaseDao selfActivityBaseDao;
	@Resource
	private IUuidManager uuidManager;

	@Resource
	private IFileServerService fileServerService;
	
	@Resource
	private ISysParamManager sysParamManager;
	@Resource
	private ISelfActivityDetailBaseDao selfActivityDetailBaseDao;

	public int qrySelfActivityListForCount(Map<String, Object> paramMap) {
		return selfActivityDao.qrySelfActivityListForCount(paramMap);
	}

	public List<SelfActivity4List> qrySelfActivityListForPage(Map<String, Object> paramMap) {
		return selfActivityDao.qrySelfActivityListForPage(paramMap);
	}

	public Integer addSelfActivity(String name, List<String> pics, List<String> jumpTypes, List<String> jumpParams) {
		int count = 0;
		//添加selfActivity记录
		BigInteger activityId = uuidManager.getNextUuidBigInteger(SEQConstants.t_self_activity);
		SelfActivity activity = new SelfActivity();
		activity.setId(activityId);
		activity.setName(name);
		activity.setStatus(SelfActivityDict.SelfActivityStatus_UnPublish);
		String server = sysParamManager.getSysParaValue(SysParamKey.Last_Api_BaseUrl);
		String url = server.replace("http:", "https:") + "selfActivity/view.html?id=" + activityId;
		activity.setUrl(url);
		count += selfActivityBaseDao.insertSelfActivity(activity);
		//生成 detail 记录
		List<SelfActivityDetail> detailList = new ArrayList<>(pics.size());
		List<BigInteger> detailIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_self_activity_detail, pics.size());
		for (int i = 0; i < pics.size(); i++) {
			SelfActivityDetail detail = new SelfActivityDetail();
			detail.setId(detailIds.get(i));
			detail.setJumpType(Integer.valueOf(jumpTypes.get(i)));
			detail.setJumpParam(jumpParams.get(i));
			detail.setPicUrl(pics.get(i));
			detail.settSelfActivityFId(activityId);
			detailList.add(detail);
		}
		count += selfActivityDetailBaseDao.insertSelfActivityDetailBatch(detailList);
		return count;
	}

	public SeftActivityWithDetail qrySelfActivityWithDetaiById(BigInteger id) {
		return selfActivityDao.qrySelfActivityWithDetaiById(id);
	}

	/**
	 * 更新自定义活动
	 * @param sa 活动主题
	 * @param sadUpdList 更新的活动详情
	 * @param sadAddNewList 增加的详情
	 * @param willDeleteIdList 哪些是删除的活动的IdList
	 * @return
	 */
	public int upddateActivity(SelfActivity sa, List<SelfActivityDetail> sadUpdList, List<SelfActivityDetail> sadAddNewList, List<BigInteger> willDeleteIdList) {
		int effectCount = 0;
		effectCount += selfActivityBaseDao.updateSelfActivity(sa);
		
		CnfantasiaCommbusiUtil.newStandardList(sadAddNewList,SEQConstants.t_self_activity_detail);
		effectCount += selfActivityDetailBaseDao.insertSelfActivityDetailBatch(sadAddNewList);
		
		effectCount += selfActivityDetailBaseDao.updateSelfActivityDetailBatch(sadUpdList);
		
		effectCount += selfActivityDetailBaseDao.deleteSelfActivityDetailLogicBatch(willDeleteIdList);
		return  effectCount;
	}
}
