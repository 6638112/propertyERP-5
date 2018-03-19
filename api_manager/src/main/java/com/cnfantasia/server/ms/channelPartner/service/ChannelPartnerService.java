package com.cnfantasia.server.ms.channelPartner.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domainbase.channelPartner.service.ChannelPartnerBaseService;
import com.cnfantasia.server.ms.channelPartner.dao.IChannelPartnerDao;
import com.cnfantasia.server.ms.channelPartner.entity.ChannelPartnerDetailEntity;
import com.cnfantasia.server.ms.channelPartner.entity.ChannelPartnerEntity;
import com.cnfantasia.server.ms.channelPartner.entity.GroupBuildingRegisterEntiy;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanySimple;


public class ChannelPartnerService extends ChannelPartnerBaseService implements IChannelPartnerService {
	@Resource
	private IChannelPartnerDao channelPartnerDao;
	@Resource
	private IUuidManager uuidManager;
	@Resource
	private IDualDao dualDao;

	@Override
	public int getChannelPartnerCount(Map paramMap) {
		return channelPartnerDao.selectChannelPartnerCount(paramMap);
	}

	@Override
	public List<ChannelPartnerEntity> getChannelPartnerList_forPage(Map paramMap) {
		return channelPartnerDao.getChannelPartnerList_forPage(paramMap);
	}

	@Override
	public ChannelPartnerDetailEntity select_cp_detail(Map<String, Object> paramMap) {
		return channelPartnerDao.select_cp_detail(paramMap);
	}

	@Override
	public List<GroupBuildingRegisterEntiy> select_gbr_by_cprId(String cprId) {
		return channelPartnerDao.select_gbr_by_cprId(cprId);
	}

	public int getGroupBuildingCount(Map<String, Object> paramMap) {
		return  channelPartnerDao.getGroupBuildingCount(paramMap);
	}

	public List<Map<Long, String>> getChannelPartnerList_ByCpnameAndType(String cpName, String channelType) {
		return channelPartnerDao.getChannelPartnerList_ByCpnameAndType(cpName, channelType);
	}

	/**
	 * 查询 代理合作的物业公司信息
	 * @param paramMap
	 * @return
	 */
	public List<PropertyCompanySimple> selectRelevanceCompanyList(Map<String, Object> paramMap) {
		return channelPartnerDao.selectRelevanceCompanyList(paramMap);
	}

}
