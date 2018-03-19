package com.cnfantasia.server.ms.communitySupplyCompany.service;

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
import com.cnfantasia.server.domainbase.communitySupply.dao.ICommunitySupplyBaseDao;
import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;
import com.cnfantasia.server.domainbase.communitySupplyCompany.service.CommunitySupplyCompanyBaseService;
import com.cnfantasia.server.domainbase.communitySupplyContect.dao.ICommunitySupplyContectBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;
import com.cnfantasia.server.domainbase.communitySupplyPic.entity.CommunitySupplyPic;
import com.cnfantasia.server.domainbase.communitySupplyPic.service.ICommunitySupplyPicBaseService;
import com.cnfantasia.server.domainbase.communitySupplyTmp.dao.ICommunitySupplyTmpBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyTmp.entity.CommunitySupplyTmp;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupplyTmp.dao.IGroupBuildingHasTCommunitySupplyTmpBaseDao;
import com.cnfantasia.server.ms.communitySupply.constant.CommunitySupplyConstant;
import com.cnfantasia.server.ms.communitySupplyCompany.dao.ICommunitySupplyCompanyDao;
import com.cnfantasia.server.ms.communitySupplyCompany.entity.CommunitySupplyCompanyEntity;
import com.cnfantasia.server.ms.communitySupplyCompany.entity.CommunitySupplyEditEntity;
import com.cnfantasia.server.ms.communitySupplyTmp.constant.CommunitySupplyTmpConstant;
import com.cnfantasia.server.ms.communitySupplyTmp.dao.ICommunitySupplyTmpDao;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;

public class CommunitySupplyCompanyService extends CommunitySupplyCompanyBaseService implements ICommunitySupplyCompanyService {


	private Log logger = LogFactory.getLog(getClass());
	@Resource
	ICommunitySupplyTmpBaseDao communitySupplyTmpBaseDao;
	
	@Resource
	ICommunitySupplyBaseDao communitySupplyBaseDao;
	
	@Resource
	ICommunitySupplyContectBaseDao communitySupplyContectBaseDao;
	
	@Resource
	ICommunitySupplyPicBaseService communitySupplyPicBaseService;
	
	@Resource
	IGroupBuildingHasTCommunitySupplyTmpBaseDao groupBuildingHasTCommunitySupplyTmpBaseDao;
	
	@Resource
	ICommunitySupplyCompanyDao communitySupplyCompanyDao;
	
	@Resource
	ICommunitySupplyTmpDao communitySupplyTmpDao;
	@Override
	public List<CommunitySupplyCompanyEntity> getCommunitySupplyTmpList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return communitySupplyCompanyDao.getCommunitySupplyCompanyList_byUserId_forPage(curPageIndex, pageSize, paramMap);
	}

	@Override
	public int getCommunitySupplyCompany_byUserId_forCount(Map<String, Object> paramMap) {
		return communitySupplyCompanyDao.getCommunitySupplyCompany_byUserId_forCount(paramMap);
	}

	@Override
	public List<CommunitySupplyEditEntity> getCommunitySupplyEditEntityList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return communitySupplyCompanyDao.getCommunitySupplyEditEntityList_byUserId_forPage(curPageIndex, pageSize, paramMap);
	}

	@Override
	public int getCommunitySupplyEditEntityList_byUserId_forCount(Map<String, Object> paramMap) {
		return communitySupplyCompanyDao.getCommunitySupplyEditEntityList_byUserId_forCount(paramMap);
	}

	@Override
	public CommunitySupplyEditEntity getCommunitySupplyEditEntity_byId(BigInteger id) {
		return communitySupplyCompanyDao.getCommunitySupplyEditEntity_byId(id);
	}

	@Override
	public int saveCommunitySupplyEditTmp(String supplyTmpId, String supplyTmpType, String supplyId, String supplyTmpName, 
			String[] contectPhones, String addressBlockId, String addressDetail, List<String> contactImgUrls, String delImgIds, String delContectIds) {
		int i = 0;
		try{
			//组装临时商家
			CommunitySupplyTmp communitySupplyTmp = new CommunitySupplyTmp();
			if(StringUtils.isNotEmpty(supplyTmpId)){//修改
				communitySupplyTmp = this.communitySupplyTmpBaseDao.selectCommunitySupplyTmpBySeqId(new BigInteger(supplyTmpId));
			}
			communitySupplyTmp.setCreateUserId(CnfantasiaCommUtil.getCurrentUserId());//创建人ID
			communitySupplyTmp.setSupplyName(supplyTmpName);
			communitySupplyTmp.settCommunitySupplyTypeFId(new BigInteger(supplyTmpType));
			//communitySupplyTmp.setContectPhone(contectPhone);
			communitySupplyTmp.setAddressBlockId(new BigInteger(addressBlockId));
			communitySupplyTmp.setAddressDetail(addressDetail);
			communitySupplyTmp.setAuditStatus(CommunitySupplyConstant.CommunitySupplyTmp_Audit_Status.WAIT_AUDIT);//待审核
			communitySupplyTmp.setAddType(CommunitySupplyTmpConstant.CommunitySupplyTmp_Add_Type.Add_Type_Edit_CS);//商家编辑
			communitySupplyTmp.settCommunitySupplyFId(new BigInteger(supplyId));
			communitySupplyTmp.setDelImgIds(delImgIds);//删除的图片IDs
			communitySupplyTmp.setDelContectIds(delContectIds);//删除的电话IDs
			if(StringUtils.isNotEmpty(supplyTmpId)){//修改
				CnfantasiaCommUtil.updateStandard(communitySupplyTmp);
				i = communitySupplyTmpBaseDao.updateCommunitySupplyTmp(communitySupplyTmp);
			}else{
				CnfantasiaCommUtil.newStandard(communitySupplyTmp, SEQConstants.t_community_supply_tmp);
				i = communitySupplyTmpBaseDao.insertCommunitySupplyTmp(communitySupplyTmp);
			}
			if(i>0){
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
			}
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int auditCSEditPass(String tmpId, String supplyId) {
		int i=0;
		//更新店铺的类型、名称、地址、详细地址、商铺电话和图片
		try{
			CommunitySupplyTmp supplyTmp = this.communitySupplyTmpBaseDao.selectCommunitySupplyTmpBySeqId(CnfantasiaCommUtil.convert2BigInteger(tmpId));
			CommunitySupply supply = this.communitySupplyBaseDao.selectCommunitySupplyBySeqId(CnfantasiaCommUtil.convert2BigInteger(supplyId));
			supply.settCommunitySupplyTypeFId(supplyTmp.gettCommunitySupplyTypeFId());
			supply.setName(supplyTmp.getSupplyName());
			supply.setAddressDetail(supplyTmp.getAddressDetail());
			CnfantasiaCommUtil.updateStandard(supply);
			i = this.communitySupplyBaseDao.updateCommunitySupply(supply);
			//新增联系方式
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tCommunitySupplyTmpFId", tmpId);
			List<CommunitySupplyContect> supplyAddContects = new ArrayList<CommunitySupplyContect>();
			List<CommunitySupplyContect> supplyTmpContects = this.communitySupplyContectBaseDao.selectCommunitySupplyContectByCondition(paramMap, true);
			if(null!=supplyTmpContects && supplyTmpContects.size()>0){
				for (CommunitySupplyContect communitySupplyContect : supplyTmpContects) {
					communitySupplyContect.settCommunitySupplyFId(CnfantasiaCommUtil.convert2BigInteger(supplyId));//新增的电话绑定店铺
					CnfantasiaCommUtil.updateStandard(communitySupplyContect);
					supplyAddContects.add(communitySupplyContect);
				}
			}
			//删除的联系方式
			paramMap.clear();
			paramMap.put("tCommunitySupplyFId", supplyId);
			List<CommunitySupplyContect> supplyDelContects = this.communitySupplyContectBaseDao.selectCommunitySupplyContectByCondition(paramMap, true);
			if(null!=supplyDelContects && supplyDelContects.size()>0){
				for (CommunitySupplyContect delContect : supplyDelContects) {
					CnfantasiaCommUtil.deleteStandard(delContect);
					supplyAddContects.add(delContect);
				}
			}
			if(!supplyAddContects.isEmpty()){
				i = communitySupplyContectBaseDao.updateCommunitySupplyContectBatch(supplyAddContects);
			}
			
			//新增图片
			List<CommunitySupplyPic> communitySupplyPicList = new ArrayList<CommunitySupplyPic>();
			paramMap.clear();
			paramMap.put("tCommunitySupplyTmpFId", tmpId);
			List<CommunitySupplyPic> picList = communitySupplyPicBaseService.getCommunitySupplyPicByCondition(paramMap);
			for (CommunitySupplyPic communitySupplyPic : picList) {
				communitySupplyPic.settCommunitySupplyFId(CnfantasiaCommUtil.convert2BigInteger(supplyId));
				communitySupplyPic.settCommunitySupplyTmpFId(null);//临时数据不在保存这些图片
				CnfantasiaCommUtil.updateStandard(communitySupplyPic);
				communitySupplyPicList.add(communitySupplyPic);
			}
			//删除图片
			if(StringUtils.isNotEmpty(supplyTmp.getDelImgIds())){
				CommunitySupplyPic pic;
				String [] delImgIdArray = supplyTmp.getDelImgIds().split(",");
				for (String id : delImgIdArray) {
					pic = communitySupplyPicBaseService.getCommunitySupplyPicBySeqId(CnfantasiaCommUtil.convert2BigInteger(id));
					pic.settCommunitySupplyFId(null);
					pic.setPicType(CommunitySupplyTmpConstant.CommunitySupplyPic_Type.Pic_Type_Del);
					CnfantasiaCommUtil.deleteStandard(pic);
					communitySupplyPicList.add(pic);
				}
			}
			if(!communitySupplyPicList.isEmpty())
				communitySupplyTmpDao.updatePicBatchForCSEdit(communitySupplyPicList);
			//审核通过
			supplyTmp.setAuditStatus(CommunitySupplyConstant.CommunitySupplyTmp_Audit_Status.AUDIT_PASS);
			//清空已经删除的图片和电话字段
			supplyTmp.setDelContectIds(null);
			supplyTmp.setDelImgIds(null);
			supplyTmp.setContectPhone(null);
			CnfantasiaCommUtil.updateStandard(supplyTmp);
			i = communitySupplyTmpDao.updateCSTmpForEdit(supplyTmp);
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
		}
		return i;
	}

}
