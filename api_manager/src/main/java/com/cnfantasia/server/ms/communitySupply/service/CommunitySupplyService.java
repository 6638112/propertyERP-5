package com.cnfantasia.server.ms.communitySupply.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.business.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.business.commonBusiness.entity.WidthHeight;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.utils.ImageZipUtil;
import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.PinyinUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.communitySupply.entity.CommunitySupply;
import com.cnfantasia.server.domainbase.communitySupply.service.CommunitySupplyBaseService;
import com.cnfantasia.server.domainbase.communitySupplyBelong.dao.CommunitySupplyBelongBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyBelong.entity.CommunitySupplyBelong;
import com.cnfantasia.server.domainbase.communitySupplyCompany.dao.CommunitySupplyCompanyBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyCompany.entity.CommunitySupplyCompany;
import com.cnfantasia.server.domainbase.communitySupplyCompanyPic.dao.CommunitySupplyCompanyPicBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyCompanyPic.entity.CommunitySupplyCompanyPic;
import com.cnfantasia.server.domainbase.communitySupplyContect.dao.CommunitySupplyContectBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;
import com.cnfantasia.server.domainbase.communitySupplyPic.dao.CommunitySupplyPicBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyPic.entity.CommunitySupplyPic;
import com.cnfantasia.server.domainbase.communitySupplyTmp.dao.ICommunitySupplyTmpBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyTmp.entity.CommunitySupplyTmp;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.dao.GroupBuildingHasTCommunitySupplyBaseDao;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.entity.GroupBuildingHasTCommunitySupply;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupplyTmp.dao.IGroupBuildingHasTCommunitySupplyTmpBaseDao;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupplyTmp.entity.GroupBuildingHasTCommunitySupplyTmp;
import com.cnfantasia.server.domainbase.omsUser.dao.OmsUserBaseDao;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.dao.OmsUserHasTOmsPermiRoleBaseDao;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.entity.OmsUserHasTOmsPermiRole;
import com.cnfantasia.server.domainbase.propertySuggestCommsupply.dao.IPropertySuggestCommsupplyBaseDao;
import com.cnfantasia.server.domainbase.propertySuggestCommsupply.entity.PropertySuggestCommsupply;
import com.cnfantasia.server.domainbase.user.dao.UserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.ms.communitySupply.constant.CommunitySupplyConstant;
import com.cnfantasia.server.ms.communitySupply.dao.ICommunitySupplyDao;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyBelongEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyBelongViewEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyDetailEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyTmpEntity;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyTmpViewEntity;
import com.cnfantasia.server.ms.communitySupply.entity.PropertySuggestCommsupplyEntity;
import com.cnfantasia.server.ms.communitySupplyCompany.entity.CommunitySupplyCompanyEntity;
import com.cnfantasia.server.ms.communitySupplyTmp.constant.CommunitySupplyTmpConstant;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtil;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class CommunitySupplyService extends CommunitySupplyBaseService implements ICommunitySupplyService {


	private Log logger = LogFactory.getLog(getClass());

	private ICommunitySupplyDao communitySupplyDao;
	
	private IPropertySuggestCommsupplyBaseDao propertySuggestCommsupplyBaseDao;

	private IUuidManager uuidManager;
	private IDualDao dualDao;

	private OmsUserBaseDao omsUserBaseDao;

	public void setOmsUserBaseDao(OmsUserBaseDao omsUserBaseDao) {
		this.omsUserBaseDao = omsUserBaseDao;
	}

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
	
	@Resource
	IGroupBuildingHasTCommunitySupplyTmpBaseDao groupBuildingHasTCommunitySupplyTmpBaseDao;
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
			propertySuggestCommsupply.setAuditStatus(1);//待审核

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

	@Override
	public List<CommunitySupplyTmpEntity> getCommunitySupplyTmpList_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return communitySupplyDao.getCommunitySupplyTmpList_forPage(curPageIndex, pageSize, paramMap);
	}

	@Override
	public int getCommunitySupplyTmpList_forCount(Map<String, Object> paramMap) {
		return communitySupplyDao.getCommunitySupplyTmpList_forCount(paramMap);
	}

	@Override
	public CommunitySupplyTmpViewEntity getTmpDetailById(String id) {
		return communitySupplyDao.getTmpDetailById(id);
	}

	/**
	 * 发短信,已不再使用，可以删掉
	 * 
	 * @param mobile
	 * @param msg
	 * @return
	 */
	@Deprecated
	private boolean sendSMS(String mobile, String msg) {
		try {
//			List<String> mobiles = new ArrayList<String>();
//			mobiles.add(mobile);
//			String isMessageSend = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.IS_MESSAGE_SEND);
//			FutureTask<String> task = new FutureTask<String>(new SendSmsRunnable(mobiles, msg, isMessageSend));
//			new Thread(task).start();
//			logger.info("发送短信的响应 = " + task.get());
			ShortMsgUtil.sendMessage(mobile, msg);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;
	}

	/**
	 * 新增审核通过，要更新多张表记录:
	 * <p>
	 * 1.更新CommunitySupplyTmp审核状态相关字段<br>
	 * 2.新增记录到CommunitySupply表，更新CommunitySupplyPIc字段<br>
	 * 3.新增记录CommunitySupplyCompany表，更新CommunitySupplyCompanyPic表中字段<br>
	 * 4.新增账OMSUser表，并分配角色 <br>
	 * 5.插入一条认领记录到t_community_supply_belong<br>
	 * 6.新增记录到t_group_building_has_t_community_supply中
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional
	public void auditCSTmpPass(String csTmpId, String omsUserId, String omsUserName, String roleId) {
		// 1.更新CommunitySupplyTmp审核状态相关字段
		CommunitySupplyTmp communitySupplyTmp = communitySupplyTmpBaseDao.selectCommunitySupplyTmpBySeqId(new BigInteger(csTmpId));
		communitySupplyTmp.setAuditTime(DateUtil.formatSecond.format(new Date()));
		communitySupplyTmp.setAuditType(CommunitySupplyConstant.CommunitySupplyTmp_Audit_Type.NEW_AUDIT);
		communitySupplyTmp.setAuditStatus(CommunitySupplyConstant.CommunitySupplyTmp_Audit_Status.AUDIT_PASS);
		communitySupplyTmp.setAuditDesc("审核通过");

		//2.新增记录到CommunitySupply表，更新CommunitySupplyPIc字段
		CommunitySupply communitySupply = updCommunitySupply(communitySupplyTmp);

		communitySupplyTmp.settCommunitySupplyFId(communitySupply.getId());
		communitySupplyTmpBaseDao.updateCommunitySupplyTmp(communitySupplyTmp);

		//3.新增记录CommunitySupplyCompany表，更新CommunitySupplyCompanyPic表中字段
		CommunitySupplyCompany communitySupplyCompany = new CommunitySupplyCompany();
		if (omsUserId == null) {//首次审核没有后台账号和商家，要分配一个，否则不用
			communitySupplyCompany.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_company));
			communitySupplyCompany.setName(communitySupplyTmp.getSupplyName());
			communitySupplyCompany.setPhone(communitySupplyTmp.getCompanyPhone());
			communitySupplyCompanyBaseDao.insertCommunitySupplyCompany(communitySupplyCompany);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tCommunitySupplyTmpFId", communitySupplyTmp.getId());
			List<CommunitySupplyCompanyPic> communitySupplyCompanyPicList = communitySupplyCompanyPicBaseDao.selectCommunitySupplyCompanyPicByCondition(
					paramMap, false);
			for (CommunitySupplyCompanyPic communitySupplyCompanyPic : communitySupplyCompanyPicList) {
				communitySupplyCompanyPic.settCommunitySupplyCompanyFId(communitySupplyCompany.getId());
			}

			if (communitySupplyCompanyPicList.size() > 0)
				communitySupplyCompanyPicBaseDao.updateCommunitySupplyCompanyPicBatch(communitySupplyCompanyPicList);

		//4.新增账OMSUser表，并分配角色 
			OmsUser omsUser = new OmsUser();
			omsUser.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_oms_user));
			omsUser.setUserAccount(omsUserName);
			omsUser.setPassword(Md5Util.MD5Twice(omsUserName));
			omsUser.setRealName(communitySupplyTmp.getSupplyName());
			omsUser.setUserState(0L);//启用
			omsUser.setIsadmin(0);//商家默认管理员为0
			omsUser.setIsSubUser(0);//商家默认子账户为0
			omsUser.setIsPmUser(0);
			omsUserBaseDao.insertOmsUser(omsUser);
			communitySupplyCompany.settOmsUserFId(omsUser.getId());
			communitySupplyCompanyBaseDao.updateCommunitySupplyCompany(communitySupplyCompany);

			OmsUserHasTOmsPermiRole omsUserHasTOmsPermiRole = new OmsUserHasTOmsPermiRole();
			omsUserHasTOmsPermiRole
					.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_oms_user_has_t_oms_permi_role));
			omsUserHasTOmsPermiRole.settOmsUserFId(omsUser.getId());
			omsUserHasTOmsPermiRole.settOmsPermiRoleFId(new BigInteger(roleId));
			omsUserHasTOmsPermiRoleBaseDao.insertOmsUserHasTOmsPermiRole(omsUserHasTOmsPermiRole);

			User user = userBaseDao.selectUserBySeqId(communitySupplyTmp.getCreateUserId());
			user.settOmsUserFId(omsUser.getId());
			userBaseDao.updateUser(user);
		}
		if(communitySupplyTmp.getAddType()==null || CommunitySupplyTmpConstant.CommunitySupplyTmp_Add_Type.Add_Type_CS==communitySupplyTmp.getAddType()){
			//5.插入一条认领记录到t_community_supply_belong
			CommunitySupplyBelong communitySupplyBelong = new CommunitySupplyBelong();
			communitySupplyBelong.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_belong));
			communitySupplyBelong.setClaimUserId(communitySupplyTmp.getCreateUserId());
			communitySupplyBelong.setAuditDesc("新增审核后自动产生");
			communitySupplyBelong.setAuditOmsUserId(UserContext.getCurrUser().getId());
			communitySupplyBelong.setAuditTime(DateUtil.formatSecond.format(new Date()));
			communitySupplyBelong.setAuditStatus(2);//审核通过
	
			if (omsUserId != null) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("tOmsUserFId", omsUserId);
				communitySupplyCompany = communitySupplyCompanyBaseDao.selectCommunitySupplyCompanyByCondition(paramMap, false).get(0);
			}
	
			communitySupplyBelong.settCommunitySupplyCompanyFId(communitySupplyCompany.getId());
			communitySupplyBelong.setCreatTime(DateUtil.formatSecond.format(new Date()));
			communitySupplyBelong.setGroupBuildingId(communitySupplyTmp.getGroupBuildingId());
			communitySupplyBelong.setCommunitySupplyId(communitySupply.getId());
			communitySupplyBelong.setSupplyCompanyPhone(communitySupplyCompany.getPhone());
			communitySupplyBelongBaseDao.insertCommunitySupplyBelong(communitySupplyBelong);
			//6.新增记录到t_group_building_has_t_community_supply中
			if (communitySupplyTmp.getGroupBuildingId() != null && communitySupplyTmp.getGroupBuildingId().compareTo(BigInteger.ZERO) > 0) {
				GroupBuildingHasTCommunitySupply groupBuildingHasTCommunitySupply = new GroupBuildingHasTCommunitySupply();
				groupBuildingHasTCommunitySupply.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_group_building_has_t_community_supply));
				groupBuildingHasTCommunitySupply.settGroupBuildingFId(communitySupplyTmp.getGroupBuildingId());
				groupBuildingHasTCommunitySupply.settCommunitySupplyCompanyFId(communitySupplyCompany.getId());
				groupBuildingHasTCommunitySupply.settCommunitySupplyFId(communitySupply.getId());
				groupBuildingHasTCommunitySupplyBaseDao.insertGroupBuildingHasTCommunitySupply(groupBuildingHasTCommunitySupply);
			}
		}else{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tCommunitySupplyTmpFId", csTmpId);
			List<GroupBuildingHasTCommunitySupplyTmp> gbList = this.groupBuildingHasTCommunitySupplyTmpBaseDao.selectGroupBuildingHasTCommunitySupplyTmpByCondition(paramMap, true);
			if(gbList!=null && gbList.size()>0){
				List gbTmps = new ArrayList();
				GroupBuildingHasTCommunitySupply groupBuildingHasTCommunitySupply;
				for (GroupBuildingHasTCommunitySupplyTmp tmp : gbList) {
					groupBuildingHasTCommunitySupply = new GroupBuildingHasTCommunitySupply();
					groupBuildingHasTCommunitySupply.settCommunitySupplyCompanyFId(communitySupplyCompany.getId());
					groupBuildingHasTCommunitySupply.settCommunitySupplyFId(communitySupply.getId());
					groupBuildingHasTCommunitySupply.settGroupBuildingFId(tmp.gettGroupBuildingFId());
					gbTmps.add(groupBuildingHasTCommunitySupply);
				}
				CnfantasiaCommUtil.newStandardList(gbTmps, SEQConstants.t_group_building_has_t_community_supply);
				groupBuildingHasTCommunitySupplyBaseDao.insertGroupBuildingHasTCommunitySupplyBatch(gbTmps);
			}
		}
		
		if (omsUserId == null) {//首次
//			sendSMS(communitySupplyTmp.getCompanyPhone(), MessageFormat.format(CommunitySupplyConstant.AddNew_AuditPassSMSInfo_First, omsUserName));
			ShortMsgUtil.sendMessage(communitySupplyTmp.getCompanyPhone(), "ebuy_account_add", omsUserName);
		} else {//非首次
			OmsUser omsUser = omsUserBaseDao.selectOmsUserBySeqId(new BigInteger(omsUserId));
//			sendSMS(communitySupplyTmp.getCompanyPhone(),
//					MessageFormat.format(CommunitySupplyConstant.AddNew_AuditPassSMSInfo_NotFirst, omsUser.getUserAccount()));
			ShortMsgUtil.sendMessage(communitySupplyTmp.getCompanyPhone(), "supply_audit_pass", omsUser.getUserAccount());
		}
	}

	private boolean copy(String fileFrom, String fileTo) {
		try {
			FileInputStream in = new java.io.FileInputStream(fileFrom);
			FileOutputStream out = new FileOutputStream(fileTo);
			byte[] bt = new byte[1024];
			int count;
			while ((count = in.read(bt)) > 0) {
				out.write(bt, 0, count);
			}
			in.close();
			out.close();
			return true;
		} catch (IOException ex) {
			return false;
		}
	}
	/**
	 * 生成小图
	 * 
	 * @param bigImageFileName
	 *            大图文件名
	 * @param csId
	 *            店铺ID
	 */
	private void generateMiniImage(String bigImageFileName, BigInteger csId) {
		Map<String, WidthHeight> guigeList = BusinessModelType.CommunitySupply.getGuigeList();
		String imgServerBasePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH);
		String descDirecBasePath = imgServerBasePath + PathConstants.CommunitySupplyBasicPicPath;

		File bigImageFile = new File(imgServerBasePath + PathConstants.CommunitySupplyPic + bigImageFileName);
		int lastPointIndex = bigImageFileName.lastIndexOf(".");
		String fileType = bigImageFileName.substring(lastPointIndex); //文件后缀名，带.号

		String goalDirectoryFilePath = descDirecBasePath + csId;

		copy(imgServerBasePath + PathConstants.CommunitySupplyPic + bigImageFileName, imgServerBasePath
				+ PathConstants.CommunitySupplyBasicPicPath + csId + fileType);

		// 创建小图的目标目录
		File goalFileDir = new File(goalDirectoryFilePath);
		if (!goalFileDir.exists()) {
			goalFileDir.mkdirs();
		}

		for (String goalFileName : guigeList.keySet()) {
			String smallIcon = goalDirectoryFilePath + "/" + goalFileName + fileType;
			try {
				WidthHeight tmpWidthHeight = guigeList.get(goalFileName);
				ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(), null, tmpWidthHeight.getHeight(), 1f, smallIcon);
				//	String resSmallIcon = ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(),tmpWidth.getWidth(),null, quality, smallIcon);
				//	String resSmallIcon = ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(), tmpWidth.getWidth(),tmpWidth.getHeight(), quality, smallIcon);
			} catch (Exception e) {
				logger.info(smallIcon + "create small image failure. ");
			}
		}
	}

	/**
	 * 新增记录到CommunitySupply表，更新CommunitySupplyPIc字段
	 * 
	 * @param communitySupplyTmp
	 * @return
	 */
	private CommunitySupply updCommunitySupply(CommunitySupplyTmp communitySupplyTmp) {
		CommunitySupply communitySupply = new CommunitySupply();
		communitySupply.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply));
		communitySupply.setName(communitySupplyTmp.getSupplyName());
		communitySupply.settCommunitySupplyTypeFId(communitySupplyTmp.gettCommunitySupplyTypeFId());
		try {
			communitySupply.setPinyinName(PinyinUtil.hanyuToPinyinSimple(communitySupplyTmp.getSupplyName()));
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			logger.info(e.getMessage(), e);
		}
		communitySupply.setAddressDetail(communitySupplyTmp.getAddressDetail());
		if(null!=communitySupplyTmp.getAddType()
				&& CommunitySupplyTmpConstant.CommunitySupplyTmp_Add_Type.Add_Type_PC==communitySupplyTmp.getAddType()){
			communitySupply.setSourceType(CommunitySupplyConstant.CommunitySupply_source_Type.ST_JFQ);//运维平台录入
		}else{
			communitySupply.setSourceType(CommunitySupplyConstant.CommunitySupply_source_Type.ST_APP);//APP用户新增
		}
		communitySupplyDao.insertCommunitySupply(communitySupply);

		{	//1更新商家图片信息
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tCommunitySupplyTmpFId", communitySupplyTmp.getId());
			List<CommunitySupplyPic> cspList = communitySupplyPicBaseDao.selectCommunitySupplyPicByCondition(paramMap, false);
			if (!cspList.isEmpty()) {//取店铺第一张图作为店铺的缩略图
				int lastItemIndex = cspList.size() - 1;
				generateMiniImage(cspList.get(lastItemIndex).getPicUrl(), communitySupply.getId());
				int lastPointIndex = cspList.get(lastItemIndex).getPicUrl().lastIndexOf(".");
				String fileType = cspList.get(lastItemIndex).getPicUrl().substring(lastPointIndex); //文件后缀名，带.号
				communitySupply.setPicUrl(communitySupply.getId() + fileType);
				communitySupplyDao.updateCommunitySupply(communitySupply);
			}

			for (CommunitySupplyPic communitySupplyPic : cspList) {
				communitySupplyPic.settCommunitySupplyFId(communitySupply.getId());
			}
			if (!cspList.isEmpty())
				communitySupplyPicBaseDao.updateCommunitySupplyPicBatch(cspList);

			//2更新商家联系方式
			List<CommunitySupplyContect> cscList = communitySupplyContectBaseDao.selectCommunitySupplyContectByCondition(paramMap, false);
			for (CommunitySupplyContect csc : cscList) {
				csc.settCommunitySupplyFId(communitySupply.getId());
			}
			if (!cscList.isEmpty()) {
				communitySupplyContectBaseDao.updateCommunitySupplyContectBatch(cscList);
			}
			
			if(!StringUtils.isEmpty(communitySupplyTmp.getContectPhone())){//如果商家表里有联系方式，这里再插入一条，跟之前版本兼容
				CommunitySupplyContect communitySupplyContect = new CommunitySupplyContect();
				communitySupplyContect.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_contect));
				communitySupplyContect.settCommunitySupplyFId(communitySupply.getId());
				communitySupplyContect.settCommunitySupplyTmpFId(communitySupplyTmp.getId());
				communitySupplyContect.setType(1);//1电话
				communitySupplyContect.setClickCount(0L);
				communitySupplyContect.setContectInfo(communitySupplyTmp.getContectPhone());
				communitySupplyContectBaseDao.insertCommunitySupplyContect(communitySupplyContect);
			}
		}
		

		return communitySupply;
	}

	@Override
	public int getCommunitySupplyBelongList_forCount(Map<String, Object> paramMap) {
		return communitySupplyDao.getCommunitySupplyBelongList_forCount(paramMap);
	}

	@Resource
	private ICommunitySupplyTmpBaseDao communitySupplyTmpBaseDao;
	@Resource
	GroupBuildingHasTCommunitySupplyBaseDao groupBuildingHasTCommunitySupplyBaseDao;
	@Resource
	CommunitySupplyBelongBaseDao communitySupplyBelongBaseDao;
	@Resource
	OmsUserHasTOmsPermiRoleBaseDao omsUserHasTOmsPermiRoleBaseDao;
	@Resource
	CommunitySupplyCompanyPicBaseDao communitySupplyCompanyPicBaseDao;
	@Resource
	CommunitySupplyCompanyBaseDao communitySupplyCompanyBaseDao;
	@Resource
	CommunitySupplyContectBaseDao communitySupplyContectBaseDao;
	@Resource
	CommunitySupplyPicBaseDao communitySupplyPicBaseDao;
	@Resource
	UserBaseDao userBaseDao;

	@Override
	public List<CommunitySupplyBelongEntity> getCommunitySupplyBelongList_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return communitySupplyDao.getCommunitySupplyBelongList_forPage(curPageIndex, pageSize, paramMap);
	}

	@Override
	public CommunitySupplyBelongViewEntity getBelongDetailById(String id) {
		return communitySupplyDao.getBelongDetailById(id);
	}

	/**
	 * 认领审核通过，要更新多张表记录:
	 * <p>
	 * 1.更新CommunitySupplyBelong审核状态相关字段<br>
	 * 3.新增记录CommunitySupplyCompany表，更新CommunitySupplyCompanyPic表中字段<br>
	 * 4.新增账OMSUser表，并分配角色 <br>
	 */
	@Override
	@Transactional
	public void auditCSBelongPass(String csBelongId, String omsUserId, String omsUserName, String roleId) {
		// 1.更新CommunitySupplyBelong审核状态相关字段
		CommunitySupplyBelong communitySupplyBelong = communitySupplyBelongBaseDao.selectCommunitySupplyBelongBySeqId(new BigInteger(csBelongId));
		communitySupplyBelong.setAuditTime(DateUtil.formatSecond.format(new Date()));
		communitySupplyBelong.setAuditStatus(CommunitySupplyConstant.CommunitySupplyBelong_Audit_Status.AUDIT_PASS);
		communitySupplyBelong.setAuditDesc("审核通过");
		communitySupplyBelongBaseDao.updateCommunitySupplyBelong(communitySupplyBelong);


		//3.新增记录CommunitySupplyCompany表，更新CommunitySupplyCompanyPic表中字段
		CommunitySupply communitySupply = communitySupplyDao.selectCommunitySupplyBySeqId(communitySupplyBelong.getCommunitySupplyId());
		CommunitySupplyCompany communitySupplyCompany = new CommunitySupplyCompany();
		if (omsUserId == null) {//首次审核没有后台账号和商家，要分配一个，否则不用
			communitySupplyCompany.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_company));
			communitySupplyCompany.setName(communitySupply.getName());
			communitySupplyCompany.setPhone(communitySupplyBelong.getSupplyCompanyPhone());
			communitySupplyCompanyBaseDao.insertCommunitySupplyCompany(communitySupplyCompany);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tCommunitySupplyBelongFId", communitySupplyBelong.getId());
			List<CommunitySupplyCompanyPic> communitySupplyCompanyPicList = communitySupplyCompanyPicBaseDao.selectCommunitySupplyCompanyPicByCondition(
					paramMap, false);
			for (CommunitySupplyCompanyPic communitySupplyCompanyPic : communitySupplyCompanyPicList) {
				communitySupplyCompanyPic.settCommunitySupplyCompanyFId(communitySupplyCompany.getId());
			}

			if (communitySupplyCompanyPicList.size() > 0)
				communitySupplyCompanyPicBaseDao.updateCommunitySupplyCompanyPicBatch(communitySupplyCompanyPicList);

			//4.新增账OMSUser表，并分配角色 
			OmsUser omsUser = new OmsUser();
			omsUser.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_oms_user));
			omsUser.setUserAccount(omsUserName);
			omsUser.setPassword(Md5Util.MD5Twice(omsUserName));
			omsUser.setRealName(communitySupply.getName());
			omsUser.setUserState(0L);//启用
			omsUser.setIsadmin(0);//商家默认管理员为0
			omsUser.setIsSubUser(0);//商家默认子账户为0
			omsUser.setIsPmUser(0);
			omsUserBaseDao.insertOmsUser(omsUser);
			communitySupplyCompany.settOmsUserFId(omsUser.getId());
			communitySupplyCompanyBaseDao.updateCommunitySupplyCompany(communitySupplyCompany);

			OmsUserHasTOmsPermiRole omsUserHasTOmsPermiRole = new OmsUserHasTOmsPermiRole();
			omsUserHasTOmsPermiRole
					.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_oms_user_has_t_oms_permi_role));
			omsUserHasTOmsPermiRole.settOmsUserFId(omsUser.getId());
			omsUserHasTOmsPermiRole.settOmsPermiRoleFId(new BigInteger(roleId));
			omsUserHasTOmsPermiRoleBaseDao.insertOmsUserHasTOmsPermiRole(omsUserHasTOmsPermiRole);

			User user = userBaseDao.selectUserBySeqId(communitySupplyBelong.getClaimUserId());
			user.settOmsUserFId(omsUser.getId());
			userBaseDao.updateUser(user);

			//5.更新Belong中商户ID
			communitySupplyBelong.settCommunitySupplyCompanyFId(communitySupplyCompany.getId());
			communitySupplyBelongBaseDao.updateCommunitySupplyBelong(communitySupplyBelong);
		} else {
			//5.更新Belong中商户ID
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tOmsUserFId", omsUserId);
			List<CommunitySupplyCompany> communitySupplyCompanyList = communitySupplyCompanyBaseDao.selectCommunitySupplyCompanyByCondition(paramMap, false);
			if (!communitySupplyCompanyList.isEmpty()) {
				communitySupplyBelong.settCommunitySupplyCompanyFId(communitySupplyCompanyList.get(0).getId());
				communitySupplyBelongBaseDao.updateCommunitySupplyBelong(communitySupplyBelong);
			}
		}

		if (omsUserId == null) {//首次
//			sendSMS(communitySupplyBelong.getSupplyCompanyPhone(), MessageFormat.format(CommunitySupplyConstant.Belong_AuditPassSMSInfo_First, omsUserName));
			ShortMsgUtil.sendMessage(communitySupplyBelong.getSupplyCompanyPhone(), "supply_account_add", omsUserName);
		} else {//非首次
			OmsUser omsUser = omsUserBaseDao.selectOmsUserBySeqId(new BigInteger(omsUserId));
//			sendSMS(communitySupplyBelong.getSupplyCompanyPhone(),
//					MessageFormat.format(CommunitySupplyConstant.Belong_AuditPassSMSInfo_NotFirst, omsUser.getUserAccount()));
			ShortMsgUtil.sendMessage(communitySupplyBelong.getSupplyCompanyPhone(), "claim_store_pass", omsUser.getUserAccount());
		}
	}

	@Override
	public int getPropertySuggestCommsupply_forCount(Map<String, Object> paramMap) {
		return communitySupplyDao.getPropertySuggestCommsupply_forCount(paramMap);
	}

	@Override
	public List<PropertySuggestCommsupplyEntity> getPropertySuggestCommsupply_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return communitySupplyDao.getPropertySuggestCommsupply_forPage(curPageIndex, pageSize, paramMap);
	}

	@Override
	public CommunitySupplyDetailEntity getCommunitySupplyDetailById(String id) {
		return communitySupplyDao.getCommunitySupplyDetailById(id);
	}

	@Override
	public PropertySuggestCommsupplyEntity getPropertySuggestCommsupplyDetail(String id) {
		return communitySupplyDao.getPropertySuggestCommsupplyDetail(id);
	}

	@Override
	public CommunitySupplyCompanyEntity getCommunitySupplyCompanyDetail(Map<String, Object> paramMap) {
		return communitySupplyDao.getCommunitySupplyCompanyDetail(paramMap);
	}

	@Override
	public int validateCSBelong_forCount(Map<String, Object> paramMap) {
		return communitySupplyDao.validateCSBelong_forCount(paramMap);
	}
}
