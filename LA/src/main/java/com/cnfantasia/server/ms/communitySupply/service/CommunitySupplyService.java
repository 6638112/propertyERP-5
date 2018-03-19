package com.cnfantasia.server.ms.communitySupply.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.communitySupply.service.CommunitySupplyBaseService;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.domainbase.propertySuggestCommsupply.dao.IPropertySuggestCommsupplyBaseDao;
import com.cnfantasia.server.domainbase.propertySuggestCommsupply.entity.PropertySuggestCommsupply;
import com.cnfantasia.server.ms.communitySupply.dao.ICommunitySupplyDao;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyEntity;
import com.cnfantasia.server.ms.pub.session.UserContext;

public class CommunitySupplyService extends CommunitySupplyBaseService implements ICommunitySupplyService {
	private ICommunitySupplyDao communitySupplyDao;
	
	private IPropertySuggestCommsupplyBaseDao propertySuggestCommsupplyBaseDao;

	private IUuidManager uuidManager;
	private IDualDao dualDao;

	public void setCommunitySupplyDao(ICommunitySupplyDao communitySupplyDao) {
		this.communitySupplyDao = communitySupplyDao;
	}

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	public void setPropertySuggestCommsupplyBaseDao(IPropertySuggestCommsupplyBaseDao propertySuggestCommsupplyBaseDao) {
		this.propertySuggestCommsupplyBaseDao = propertySuggestCommsupplyBaseDao;
	}

	@Override
	public List<CommunitySupplyEntity> select_csList_byOmsUser(Map<String, Object> paramMap) {
		return communitySupplyDao.select_csList_byOmsUser(paramMap);
	}

	@Override
	public List<CommunitySupplyType> getCommunitySupplyTypeByCondition(Map<String, Object> paramMap) {
		return communitySupplyDao.getCommunitySupplyTypeByCondition(paramMap);
	}

	@Override
	public String suggestMark(String csId, String suggestMark, String pcId, String gbId) {
		String resultInfo = "操作失败";
		if ("1".equals(suggestMark)) {//标为推荐
			PropertySuggestCommsupply propertySuggestCommsupply = new PropertySuggestCommsupply();
			propertySuggestCommsupply.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_property_suggest_commsupply));
			propertySuggestCommsupply.settCommunitySupplyFId(new BigInteger(csId));
			propertySuggestCommsupply.settPropertyCompanyFId(new BigInteger(pcId));
			propertySuggestCommsupply.setGroupbulidId(new BigInteger(gbId));
			propertySuggestCommsupply.setSys0AddUser(UserContext.getCurrUser().getId());
			propertySuggestCommsupply.setTime(dualDao.getNowTime());

			int insertCount = propertySuggestCommsupplyBaseDao.insertPropertySuggestCommsupply(propertySuggestCommsupply);
			if (insertCount == 1)
				resultInfo = "标为推荐成功";
		} else {//取消推荐
			int insertCount = communitySupplyDao.deletePropertySuggestCommsupply_byCSId(csId, pcId, gbId);
			if (insertCount == 1)
				resultInfo = "取消推荐成功";
		}
		return resultInfo;
	}

	@Override
	public int getCommunitySupply_byUserId_forCount(Map<String, Object> paramMap) {
		return communitySupplyDao.getCommunitySupply_byUserId_forCount(paramMap);
	}

	@Override
	public List<CommunitySupplyEntity> getCommunitySupplyList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return communitySupplyDao.getCommunitySupplyList_byUserId_forPage(curPageIndex, pageSize, paramMap);
	}
}
