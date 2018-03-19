package com.cnfantasia.server.ms.communitySupplyTmp.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.communitySupplyContect.dao.ICommunitySupplyContectBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;
import com.cnfantasia.server.domainbase.communitySupplyPic.entity.CommunitySupplyPic;
import com.cnfantasia.server.domainbase.communitySupplyPic.service.ICommunitySupplyPicBaseService;
import com.cnfantasia.server.domainbase.communitySupplyTmp.dao.ICommunitySupplyTmpBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyTmp.entity.CommunitySupplyTmp;
import com.cnfantasia.server.domainbase.communitySupplyTmp.service.CommunitySupplyTmpBaseService;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupplyTmp.dao.IGroupBuildingHasTCommunitySupplyTmpBaseDao;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupplyTmp.entity.GroupBuildingHasTCommunitySupplyTmp;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.dao.IOmsUserHasTOmsPermiRoleBaseDao;
import com.cnfantasia.server.ms.communitySupply.constant.CommunitySupplyConstant;
import com.cnfantasia.server.ms.communitySupplyTmp.constant.CommunitySupplyTmpConstant;
import com.cnfantasia.server.ms.communitySupplyTmp.dao.ICommunitySupplyTmpDao;
import com.cnfantasia.server.ms.communitySupplyTmp.entity.CommunitySupplyTmpEntity;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;

public class CommunitySupplyTmpService extends CommunitySupplyTmpBaseService implements ICommunitySupplyTmpService {


	private Log logger = LogFactory.getLog(getClass());
	@Resource
	ICommunitySupplyTmpBaseDao communitySupplyTmpBaseDao;
	
	@Resource
	ICommunitySupplyPicBaseService communitySupplyPicBaseService;
	
	@Resource
	IGroupBuildingHasTCommunitySupplyTmpBaseDao groupBuildingHasTCommunitySupplyTmpBaseDao;
	
	@Resource
	ICommunitySupplyTmpDao communitySupplyTmpDao;
	
	@Resource
	ICommunitySupplyContectBaseDao communitySupplyContectBaseDao;
	
	@Override
	public List<CommunitySupplyTmpEntity> getCommunitySupplyTmpList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		
		return communitySupplyTmpDao.getCommunitySupplyTmpList_byUserId_forPage(curPageIndex, pageSize, paramMap);
	}

	@Override
	public int getCommunitySupplyTmp_byUserId_forCount(Map<String, Object> paramMap) {
		return communitySupplyTmpDao.getCommunitySupplyTmp_byUserId_forCount(paramMap);
	}

	@Override
	public CommunitySupplyTmpEntity getCommunitySupplyTmp_byId(BigInteger id) {
		return communitySupplyTmpDao.getCommunitySupplyTmp_byId(id);
	}

	@Resource
	private IOmsUserHasTOmsPermiRoleBaseDao omsUserHasTOmsPermiRoleBaseDao;
	
	/**
	 * 检查用户是否具有某个角色
	 */
	private boolean checkUserHasRole(BigInteger omsUserId, String roleId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tOmsUserFId", omsUserId);
		paramMap.put("tOmsPermiRoleFId", roleId);
		
		return omsUserHasTOmsPermiRoleBaseDao.selectOmsUserHasTOmsPermiRoleCount(paramMap, false) > 0;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int saveCommunitySupplyTmp(String supplyTmpId, String supplyTmpType, String supplyTmpName, String companyName, String companyPhone,
			String[] contectPhones, String addressBlockId, String addressDetail, String[] serviceGroupBuildingIds, 
			String miniPicId, String bigPicId, List<String> contactImgUrls, String delImgIds, String delContectIds) {
		int i = 0;
		try{
			//组装临时商家
			CommunitySupplyTmp communitySupplyTmp = new CommunitySupplyTmp();
			if(StringUtils.isNotEmpty(supplyTmpId)){//修改
				communitySupplyTmp = this.communitySupplyTmpBaseDao.selectCommunitySupplyTmpBySeqId(new BigInteger(supplyTmpId));
			}
			communitySupplyTmp.setCreateUserId(UserContext.getCurrUser().getId());//创建人ID
			communitySupplyTmp.setSupplyName(supplyTmpName);
			communitySupplyTmp.settCommunitySupplyTypeFId(new BigInteger(supplyTmpType));
			communitySupplyTmp.setCompanyName(companyName);
			//communitySupplyTmp.setContectPhone(contectPhone);
			communitySupplyTmp.setCompanyPhone(companyPhone);
			communitySupplyTmp.setAddressBlockId(new BigInteger(addressBlockId));
			communitySupplyTmp.setAddressDetail(addressDetail);
			communitySupplyTmp.setAuditStatus(CommunitySupplyConstant.CommunitySupplyTmp_Audit_Status.WAIT_AUDIT);//待审核
			
			if(StringUtils.isNotEmpty(supplyTmpId)){//修改
				CnfantasiaCommUtil.updateStandard(communitySupplyTmp);
				i = communitySupplyTmpBaseDao.updateCommunitySupplyTmp(communitySupplyTmp);
				//删除以前的店铺联系方式
				this.communitySupplyTmpDao.delCommunitySupplyTmpContect_byId(new BigInteger(supplyTmpId), UserContext.getCurrUser().getId());
				//删除以前的服务小区信息
				this.communitySupplyTmpDao.delGroupBuildingHasTCommunitySupplyTmp_byId(new BigInteger(supplyTmpId), UserContext.getCurrUser().getId());
			}else{//新增
				CnfantasiaCommUtil.newStandard(communitySupplyTmp, SEQConstants.t_community_supply_tmp);
				boolean isCompany = checkUserHasRole(UserContext.getCurrUser().getId(), OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.Channel_Partner_Company_Role_ID));
				boolean isPerson = checkUserHasRole(UserContext.getCurrUser().getId(), OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.Channel_Partner_Person_Role_ID));
				if(isCompany||isPerson){
					communitySupplyTmp.setAddType(CommunitySupplyTmpConstant.CommunitySupplyTmp_Add_Type.Add_Type_CP);//代理渠道录入
				}else{
					communitySupplyTmp.setAddType(CommunitySupplyTmpConstant.CommunitySupplyTmp_Add_Type.Add_Type_PC);//物业录入
				}
				i = communitySupplyTmpBaseDao.insertCommunitySupplyTmp(communitySupplyTmp);
			}
			if(i>0){
				//图片
				if(null!=contactImgUrls && contactImgUrls.size()>0){
					List<CommunitySupplyPic> pics = new ArrayList<CommunitySupplyPic>();
					CommunitySupplyPic communitySupplyPic;
					for (String contactImgUrl : contactImgUrls) {
						communitySupplyPic = new CommunitySupplyPic();
						communitySupplyPic.settCommunitySupplyTmpFId(communitySupplyTmp.getId());
						communitySupplyPic.setPicUrl(contactImgUrl);
						communitySupplyPic.setPicType(CommunitySupplyTmpConstant.CommunitySupplyPic_Type.Pic_Type_Add);
						CnfantasiaCommUtil.newStandard(communitySupplyPic, SEQConstants.t_community_supply_pic);
						pics.add(communitySupplyPic);
					}
					communitySupplyPicBaseService.insertCommunitySupplyPicBatch(pics);
				}
				//联系方式
				if(null!=contectPhones && contectPhones.length>0){
					List<CommunitySupplyContect> contects = new ArrayList<CommunitySupplyContect>();
					CommunitySupplyContect contect;
					for (String contectPhone : contectPhones) {
						contect = new CommunitySupplyContect();
						contect.setContectInfo(contectPhone);//新增电话
						contect.setType(1);//类型电话
						contect.setEsqName(supplyTmpName);//商家店铺名
						contect.settCommunitySupplyTmpFId(communitySupplyTmp.getId());//先绑定修改的tmp对象ID，审核后再绑定商家店铺ID
						CnfantasiaCommUtil.newStandard(contect, SEQConstants.t_community_supply_contect);
						contects.add(contect);
					}
					communitySupplyContectBaseDao.insertCommunitySupplyContectBatch(contects);
				}
				//服务小区
				if(serviceGroupBuildingIds!=null && serviceGroupBuildingIds.length>0){
					List gbTmps = new ArrayList();
					GroupBuildingHasTCommunitySupplyTmp  gbTmp;
					for (String gbId : serviceGroupBuildingIds) {
						gbTmp = new GroupBuildingHasTCommunitySupplyTmp();
						gbTmp.settCommunitySupplyTmpFId(communitySupplyTmp.getId());
						gbTmp.settGroupBuildingFId(new BigInteger(gbId));
						gbTmps.add(gbTmp);
					}
					CnfantasiaCommUtil.newStandardList(gbTmps, SEQConstants.t_group_building_has_t_community_supply_tmp);
					groupBuildingHasTCommunitySupplyTmpBaseDao.insertGroupBuildingHasTCommunitySupplyTmpBatch(gbTmps);
				}
				if(StringUtils.isNotEmpty(delImgIds)){
					List<CommunitySupplyPic> pics = new ArrayList<CommunitySupplyPic>();
					CommunitySupplyPic pic;
					String [] delImgIdArray = delImgIds.split(",");
					for (String id : delImgIdArray) {
						pic = communitySupplyPicBaseService.getCommunitySupplyPicBySeqId(CnfantasiaCommUtil.convert2BigInteger(id));
						pic.settCommunitySupplyFId(null);
						pic.setPicType(CommunitySupplyTmpConstant.CommunitySupplyPic_Type.Pic_Type_Del);
						CnfantasiaCommUtil.deleteStandard(pic);
						pics.add(pic);
					}
					communitySupplyPicBaseService.updateCommunitySupplyPicBatch(pics);
				}
			}
		}catch(Exception e){
			logger.error(e);
		}
		return 0;
	}

	@Override
	public int delCommunitySupplyTmp(BigInteger supplyTmpId) {
		int i = 0;
		try{
			CommunitySupplyTmp tmp = this.communitySupplyTmpBaseDao.selectCommunitySupplyTmpBySeqId(supplyTmpId);
			CnfantasiaCommUtil.deleteStandard(tmp);
			i = communitySupplyTmpBaseDao.updateCommunitySupplyTmp(tmp);
			//删除图片
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tCommunitySupplyTmpFId", supplyTmpId);
			List<CommunitySupplyPic> picURLs = communitySupplyPicBaseService.getCommunitySupplyPicByCondition(paramMap);
			if(picURLs!=null && picURLs.size()>0){
				for (CommunitySupplyPic pic : picURLs) {
					CnfantasiaCommUtil.deleteStandard(pic);
				}
				i = communitySupplyPicBaseService.updateCommunitySupplyPicBatch(picURLs);
			}
			//删除关联的服务小区
			paramMap.clear();
			paramMap.put("tCommunitySupplyTmpFId", supplyTmpId);
			List<GroupBuildingHasTCommunitySupplyTmp> gbList = this.groupBuildingHasTCommunitySupplyTmpBaseDao.selectGroupBuildingHasTCommunitySupplyTmpByCondition(paramMap, true);
			if(gbList!=null && gbList.size()>0){
				for (GroupBuildingHasTCommunitySupplyTmp communitySupplyTmp : gbList) {
					CnfantasiaCommUtil.deleteStandard(communitySupplyTmp);
				}
				i = groupBuildingHasTCommunitySupplyTmpBaseDao.updateGroupBuildingHasTCommunitySupplyTmpBatch(gbList);
			}
		}catch(Exception e){
			logger.error(e);
		}
		return i;
	}

	@Override
	public List<String> getGBHasCSIsExists(Map<String, Object> paramMap, boolean isTmp) {
		return communitySupplyTmpDao.getGBHasCSIsExists(paramMap,isTmp);
	}
	
	
}
