package com.cnfantasia.server.ms.groupBuilding.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.PinyinUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.gbSoftwareConfig.entity.GbSoftwareConfig;
import com.cnfantasia.server.domainbase.gbSoftwareConfig.service.IGbSoftwareConfigBaseService;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.GroupBuildingBaseService;
import com.cnfantasia.server.domainbase.groupBuildingRegister.dao.GroupBuildingRegisterBaseDao;
import com.cnfantasia.server.domainbase.groupBuildingRegister.entity.GroupBuildingRegister;
import com.cnfantasia.server.ms.groupBuilding.constant.GroupBuildingConstant;
import com.cnfantasia.server.ms.groupBuilding.dao.IGroupBuildingDao;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleViewEntity;
import com.cnfantasia.server.ms.payBill.entity.PrintConfigList;
import com.cnfantasia.server.ms.propertyCompany.dao.IPropertyCompanyDao;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;

public class GroupBuildingService extends GroupBuildingBaseService implements IGroupBuildingService {
	@Resource
	private IPropertyCompanyDao propertyCompanyDao;
	@Resource
	private IGbSoftwareConfigBaseService gbSoftwareConfigBaseService;

	private IUuidManager uuidManager;

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IGroupBuildingDao groupBuildingDao;

	public void setGroupBuildingDao(IGroupBuildingDao groupBuildingDao) {
		this.groupBuildingDao = groupBuildingDao;
	}

	@Override
	public int insertGroupBuildingBatch(List<GroupBuilding> groupBuildingList) {
		List<BigInteger> idList = uuidManager.getNextUuidBigInteger(SEQConstants.t_group_building, groupBuildingList.size());
		for (int i = 0; i < idList.size(); i++) {
			groupBuildingList.get(i).setId(idList.get(i));
		}
		return super.insertGroupBuildingBatch(groupBuildingList);
	}

	@Override
	public List<GroupBuildingSimpleViewEntity> selectGroupBuildingByOmsUser(Map<String, Object> paramMap) {
		return groupBuildingDao.selectGroupBuildingByOmsUser(paramMap);
	}

	@Override
	public List<GroupBuildingSimpleViewEntity> selectGroupBuildingBySupply(Map<String, Object> paramMap) {
		return groupBuildingDao.selectGroupBuildingBySupply(paramMap);
	}

	@Override
	public List<GroupBuildingSimpleEntity> selectGroupBuildingForList(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return groupBuildingDao.selectGroupBuildingForList(curPageIndex, pageSize, paramMap);
	}

	@Override
	public int selectGroupBuildingForCount(Map<String, Object> paramMap) {
		return groupBuildingDao.selectGroupBuildingForCount(paramMap);
	}

	@Override
	public GroupBuildingSimpleEntity selectGroupBuildingById(BigInteger id) {
		return groupBuildingDao.selectGroupBuildingById(id);
	}

	@Override
	public int deleteGroupBuildingById(String id) {
		int i = 0;
		try{
			if(!StringUtils.isEmpty(id)){
				GroupBuilding groupBuilding = new GroupBuilding();
				groupBuilding.setId(CnfantasiaCommUtil.convert2BigInteger(id));
				groupBuilding.setSys0DelState(1);
				groupBuilding.setSys0DelTime(CnfantasiaCommUtil.getCurrentTime());
				groupBuilding.setSys0DelUser(CnfantasiaCommUtil.getCurrentUserId());
				i = groupBuildingDao.updateGroupBuilding(groupBuilding);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int saveOrUpdateGroupBuilding(String id, String gbName, String blockId, String addressDesc, String managementId,
			String companyId, String streetName, String streetTel, String neighborName, String neighborTel) {
		int i = 0;
		try{
			GroupBuilding groupBuilding = new GroupBuilding();
			if(!StringUtils.isEmpty(id)){
				//groupBuilding.setId(CnfantasiaCommUtil.convert2BigInteger(id));
				groupBuilding = groupBuildingDao.selectGroupBuildingBySeqId(CnfantasiaCommUtil.convert2BigInteger(id));
			}
			groupBuilding.setName(gbName);
			groupBuilding.settBlockFId(CnfantasiaCommUtil.convert2BigInteger(blockId));
			groupBuilding.setAddressDesc(addressDesc);
			groupBuilding.setStreetName(streetName);
			groupBuilding.setStreetTel(streetTel);
			groupBuilding.setNeighborName(neighborName);
			groupBuilding.setNeighborTel(neighborTel);
			groupBuilding.settPropertyManagementFId(CnfantasiaCommUtil.convert2BigInteger(managementId));
			groupBuilding.settPropertyCompanyFId(CnfantasiaCommUtil.convert2BigInteger(companyId));
			if(!StringUtils.isEmpty(id)){
				if(groupBuilding.getCheckStatus()==GroupBuildingConstant.Check_Status.CS2){
					groupBuilding.setCheckStatus(GroupBuildingConstant.Check_Status.CS0);
				}
				CnfantasiaCommUtil.updateStandard(groupBuilding);
				i = groupBuildingDao.updateGroupBuilding02(groupBuilding);
			}else{
				groupBuilding.setCheckStatus(GroupBuildingConstant.Check_Status.CS0);
				groupBuilding.setSignStatus(1);
				groupBuilding.setCooperationType(GroupBuildingConstant.Cooperation_Type.BASE);//首次添加都是基本合作
				groupBuilding.setIsPrefer(1);
				groupBuilding.setPreferName("随机立减");
				CnfantasiaCommUtil.newStandard(groupBuilding, SEQConstants.t_group_building);
				i = groupBuildingDao.insertGroupBuilding(groupBuilding);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int saveAuditGroupBuilding(String gbId, String auditResult, String auditDesc) {
		int i = 0;
		try{
			GroupBuilding groupBuilding = new GroupBuilding();
			groupBuilding.setId(CnfantasiaCommUtil.convert2BigInteger(gbId));
			if("0".equals(auditResult)){//审核通过

				int cptType = propertyCompanyDao.selectPropertyCompanyBySeqId(
						groupBuildingDao.selectGroupBuildingById(groupBuilding.getId()).gettPropertyCompanyFId()).getCooperationType();
				groupBuilding.setCooperationType(cptType);//合作类型
				groupBuilding.setCheckStatus(GroupBuildingConstant.Check_Status.CS1);
			}else{
				groupBuilding.setAuditDesc(auditDesc);
				groupBuilding.setCheckStatus(GroupBuildingConstant.Check_Status.CS2);
			}
			CnfantasiaCommUtil.updateStandard(groupBuilding);
			i =this.groupBuildingDao.updateGroupBuilding(groupBuilding);
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int saveAuditEditGroupBuilding(GroupBuilding groupBuilding, GbSoftwareConfig config) {
		int i = 0;
		try{
			if (config != null && config.getSoftwareType() != null) {
				if (config.getSoftwareType() == 1) {
					config.setServiceClassName("jeezService");
				}
				GbSoftwareConfig searchConfig = new GbSoftwareConfig();
				searchConfig.setGbId(groupBuilding.getId());
				List<GbSoftwareConfig> configList = gbSoftwareConfigBaseService
						.getGbSoftwareConfigByCondition(MapConverter.toMap(searchConfig));
				int softwareType = config.getSoftwareType();
				if (!DataUtil.isEmpty(configList)) {
					config.setId(configList.get(0).getId());
					config.setIsValid(softwareType == 0 ? 0 : 1);
					gbSoftwareConfigBaseService.updateGbSoftwareConfig(config);
				} else if (softwareType != 0) {
					config.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_gb_software_config));
					config.setIsValid(1);
					gbSoftwareConfigBaseService.insertGbSoftwareConfig(config);
				}
			}
			CnfantasiaCommUtil.updateStandard(groupBuilding);
			i = this.groupBuildingDao.updateGroupBuilding(groupBuilding);
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public List<GroupBuildingSimpleEntity> selectGroupBuildingForDialogList(Map<String, Object> paramMap) {
		return groupBuildingDao.selectGroupBuildingForDialogList(paramMap);
	}

	@Override
	public Long queryGroupbuildingIsExists(Map<String, Object> paramMap) {
		return groupBuildingDao.queryGroupbuildingIsExists(paramMap);
	}

	@Override
	public List<GroupBuildingSimpleEntity> selectGroupBuildingForCPList(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return groupBuildingDao.selectGroupBuildingForCPList(curPageIndex, pageSize, paramMap);
	}

	@Override
	public int selectGroupBuilding4CPForCount(Map<String, Object> paramMap) {
		return groupBuildingDao.selectGroupBuilding4CPForCount(paramMap);
	}

	@Resource
	private GroupBuildingRegisterBaseDao groupBuildingRegisterBaseDao;
	
	/**
	 * 保存渠道新增小区-审批结果
	 * @author wenfq 2015-06-30
	 */
	@Override
	@Transactional
	public int saveAuditGroupBuilding4CP(String id, String auditStatus, String auditDesc) {
		GroupBuildingRegister gbr = new GroupBuildingRegister();
		gbr.setId(new BigInteger(id));
		CnfantasiaCommUtil.updateStandard(gbr);
		gbr.setAuditstatus(Integer.parseInt(auditStatus));
		gbr.setAuditDesc(auditDesc);
		
		int udpCount = groupBuildingRegisterBaseDao.updateGroupBuildingRegister(gbr);
		if("2".equals(auditStatus))//审核不通过
			return udpCount;
		
		//在正式小区表里增加一条小区信息：
		//注意：若有同一行政区下有重名且已经审核（checkStatus=1 or 9）的小区信息则不用添加
		gbr = groupBuildingRegisterBaseDao.selectGroupBuildingRegisterBySeqId(new BigInteger(id));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tBlockFId", gbr.gettAddressBlockFId());
		paramMap.put("name", gbr.getName());
		paramMap.put("checkStatus", 1);
		int existGroupBuildingCount = groupBuildingDao.selectGroupBuildingCount(paramMap, false);
		paramMap.put("checkStatus", 9);
		existGroupBuildingCount += groupBuildingDao.selectGroupBuildingCount(paramMap, false);
		boolean isExistGroupBuilding = existGroupBuildingCount > 0;
		if(!isExistGroupBuilding){
			GroupBuilding gb = new GroupBuilding();
			CnfantasiaCommUtil.newStandard(gb, SEQConstants.t_group_building);
			gb.setName(gbr.getName());
			try {
				gb.setPinyinName(PinyinUtil.hanyuToPinyinSimple(gbr.getName()));
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
			}
			gb.setCreater(gbr.getSys0AddUser());
			gb.setAddressDesc(gbr.getAddressDesc());
			gb.setCheckStatus(new Integer(9));//无须再审核
			gb.settBlockFId(gbr.gettAddressBlockFId());
			gb.setSignStatus(new Integer(0));
			udpCount += groupBuildingDao.insertGroupBuilding(gb);
		}
		return udpCount;
	}

	@Override
	public GroupBuildingSimpleEntity selectGroupBuildingByGbrId(BigInteger id) {
		return groupBuildingDao.selectGroupBuildingByGbrId(id);
	}

	
	/**
	 * 查询短信通知的所有手机号码
	 * 
	 * @param msgType
	 * @param gbId
	 * @return
	 */
	@Override
	public List<String> queryMobiles(String msgType, String gbId){
		return groupBuildingDao.queryMobiles(msgType, gbId);
	}

	@Override
	public List<Map<String, Object>> getBuildingListByNameAndCityId(String name, BigInteger cityId, PageModel pageModel) {
		return groupBuildingDao.getBuildingListByNameAndCityId(name, cityId, pageModel);
	}

	@Override
	public List<Map<String, Object>> getBuildingListByNameAndBlockId(String name, BigInteger blockId, List<BigInteger> gbIdList) {
		return groupBuildingDao.getBuildingListByNameAndBlockId(name, blockId, gbIdList);
	}

	/**
	 * 根据小区名称及楼下店供应商服务范围查询小区
	 * 
	 * @param gbName
	 * @param merchantId
	 * @return
	 */
	public List<Map<String, Object>> searchByNameAndMerchantServiceArea(String gbName, BigInteger merchantId){
		return groupBuildingDao.searchByNameAndMerchantServiceArea(gbName, merchantId);
	}
	
	@Override
	public List<Map<String, Object>> getBuildingListBySeqIdList(List<BigInteger> ids) {
		return groupBuildingDao.getBuildingListBySeqIdList(ids);
	}

	@Override
	public List<Map<String, Object>> getBuildingListForSelected(Map<String, Object> paramMap) {
		return groupBuildingDao.getBuildingListForSelected(paramMap);
	}

	@Override
	public List<BigInteger> selectGroupBuildingRealRoomList(BigInteger gbId) {
		return groupBuildingDao.selectGroupBuildingRealRoomList(gbId);
	}

	@Override
	public List<Map<String, Object>> selectBillTypeListByGbId(BigInteger bgId, String paytimeType, String isMeterReading) {
		//增加选择周期的校验
		Integer propertyPeriodType = groupBuildingDao.selectGroupBuildingBySeqId(bgId).getPropertyPeriodType();
		return groupBuildingDao.selectBillTypeListByGbId(bgId, paytimeType,propertyPeriodType, isMeterReading);
	}

	@Override
	public int isHasEmptyProprietorByGbId(BigInteger gbId) {
		Integer totalCount = groupBuildingDao.selectProprietorByGbIdCount(gbId);
		Integer emptyCount = groupBuildingDao.isHasEmptyProprietorByGbId(gbId);
		if(totalCount != null && totalCount > 0) {
			if(emptyCount != null && emptyCount > 0) {
				if(emptyCount < totalCount) {
					return 1;//部分为空
				} else {
					return 2;//全部为空
				}
			} else {
				return 0;//全部都有业主信息
			}
		} else {
			return 3;//没有楼栋门牌信息
		}
	}

	@Override
	public void updateHistoryManagementRevenueData(GroupBuilding groupBuilding, String managementId) {
		if(BigInteger.valueOf(Long.parseLong(managementId)) != groupBuilding.gettPropertyManagementFId()) {
			groupBuildingDao.updateHistoryManagementRevenueData(groupBuilding.getId(), BigInteger.valueOf(Long.parseLong(managementId)));
		}
		
	}

	public List<GroupBuilding> getgbListByGbIds(List<BigInteger> gbIdList, PageModel pageModel) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("gbIdList", gbIdList);
		if (pageModel != null) {
			param.put("_begin", pageModel.getBegin());
			param.put("_length", pageModel.getLength());
		}
		return groupBuildingDao.getgbListByGbIds(param);
	}
	
	@Override
	public List<PrintConfigList> getGbListForPrint(Map<String, Object> paramMap){
		return groupBuildingDao.getGbListForPrint(paramMap);
	}
	
	@Override
	public Integer getGbCountForPrint(Map<String, Object> paramMap){
		return groupBuildingDao.getGbCountForPrint(paramMap);
	}

	@Override
	public int getBuildingListForSelectedCount(Map<String, Object> paramMap) {
		return groupBuildingDao.getBuildingListForSelectedCount(paramMap);
	}

	@Override
	public List<Map<BigInteger, String>> getGroupBuildingList(Map<String, Object> paraMap) {
		return groupBuildingDao.getGroupBuildingList(paraMap);
	}
}
