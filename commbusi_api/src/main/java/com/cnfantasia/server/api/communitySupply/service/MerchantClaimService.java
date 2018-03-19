/**   
* Filename:    MerchantClaimService.java   
* @version:    1.0  
* Create at:   2015年1月28日 上午2:06:15   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.callable.UploadSmallPicRunnable;
import com.cnfantasia.server.api.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.service.ISmallPicUploadService;
import com.cnfantasia.server.api.communitySupply.constant.CommunitySupplyDict;
import com.cnfantasia.server.api.communitySupply.constant.CommunitySupplyTmpDict;
import com.cnfantasia.server.api.communitySupply.dao.IMerchantClaimDao;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.RandomUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.communitySupplyBelong.dao.ICommunitySupplyBelongBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyBelong.entity.CommunitySupplyBelong;
import com.cnfantasia.server.domainbase.communitySupplyCompanyPic.dao.ICommunitySupplyCompanyPicBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyCompanyPic.entity.CommunitySupplyCompanyPic;
import com.cnfantasia.server.domainbase.communitySupplyPic.dao.ICommunitySupplyPicBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyPic.entity.CommunitySupplyPic;
import com.cnfantasia.server.domainbase.communitySupplyTmp.dao.ICommunitySupplyTmpBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyTmp.entity.CommunitySupplyTmp;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.dao.IGroupBuildingHasTCommunitySupplyBaseDao;
import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupply.entity.GroupBuildingHasTCommunitySupply;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;

/**
 * Filename:    MerchantClaimService.java
 * @version:    1.0.0
 * Create at:   2015年1月28日 上午2:06:15
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月28日       shiyl             1.0             1.0 Version
 */
public class MerchantClaimService implements IMerchantClaimService{
	private Log logger = LogFactory.getLog(getClass());
	
	private IMerchantClaimDao merchantClaimDao;
	public void setMerchantClaimDao(IMerchantClaimDao merchantClaimDao) {
		this.merchantClaimDao = merchantClaimDao;
	}
	
	private ICommunitySupplyTmpBaseDao communitySupplyTmpBaseDao;
	public void setCommunitySupplyTmpBaseDao(ICommunitySupplyTmpBaseDao communitySupplyTmpBaseDao) {
		this.communitySupplyTmpBaseDao = communitySupplyTmpBaseDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	private ISmallPicUploadService smallPicUploadService;
	public void setSmallPicUploadService(ISmallPicUploadService smallPicUploadService) {
		this.smallPicUploadService = smallPicUploadService;
	}
	
	private ICommunitySupplyPicBaseDao communitySupplyPicBaseDao;
	public void setCommunitySupplyPicBaseDao(ICommunitySupplyPicBaseDao communitySupplyPicBaseDao) {
		this.communitySupplyPicBaseDao = communitySupplyPicBaseDao;
	}
	
	private ICommunitySupplyCompanyPicBaseDao communitySupplyCompanyPicBaseDao;
	public void setCommunitySupplyCompanyPicBaseDao(ICommunitySupplyCompanyPicBaseDao communitySupplyCompanyPicBaseDao) {
		this.communitySupplyCompanyPicBaseDao = communitySupplyCompanyPicBaseDao;
	}
	
	private ICommunitySupplyBelongBaseDao communitySupplyBelongBaseDao;
	public void setCommunitySupplyBelongBaseDao(ICommunitySupplyBelongBaseDao communitySupplyBelongBaseDao) {
		this.communitySupplyBelongBaseDao = communitySupplyBelongBaseDao;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private IUserBaseDao userBaseDao;
	public void setUserBaseDao(IUserBaseDao userBaseDao) {
		this.userBaseDao = userBaseDao;
	}

	private IGroupBuildingHasTCommunitySupplyBaseDao groupBuildingHasTCommunitySupplyBaseDao;
	public void setGroupBuildingHasTCommunitySupplyBaseDao(
			IGroupBuildingHasTCommunitySupplyBaseDao groupBuildingHasTCommunitySupplyBaseDao) {
		this.groupBuildingHasTCommunitySupplyBaseDao = groupBuildingHasTCommunitySupplyBaseDao;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}

	@Override
	public List<CommunitySupplyType> getCommunitySupplyTypeCanAddList() {
		return merchantClaimDao.selectCommunitySupplyTypeCanAddList();
	}

	@Override
	public void addMerchant(BigInteger userId, BigInteger supplyTypeId, String supplyName, BigInteger addressBlockId,
			String addressDetail, String supplyContectInfo, List<RequestFileEntity> descriptPicList, String merchantPhone,
			List<RequestFileEntity> merchantLicensePicList) {
		BigInteger groupBuildingId = commonRoomService.getGroupBuildingIdByUserId(userId);
		BigInteger defaultRoomId = commonRoomService.getDefaultRoomIdByUserId(userId);
		BigInteger communitySupplyTmpAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_tmp);
		{//新增临时商家表记录(包含店铺信息和商户基本信息)
			CommunitySupplyTmp communitySupplyTmpAdd = new CommunitySupplyTmp();
			communitySupplyTmpAdd.setAddressDetail(addressDetail);
			communitySupplyTmpAdd.setCompanyPhone(merchantPhone);//商户电话
			communitySupplyTmpAdd.setContectPhone(supplyContectInfo);//店铺电话
			communitySupplyTmpAdd.setCreateUserId(userId);
			communitySupplyTmpAdd.setCreateRoomId(defaultRoomId);//保持用户当前默认门牌Id
			communitySupplyTmpAdd.setGroupBuildingId(groupBuildingId);//暂使用 用户当前门牌所属的小区Id
			communitySupplyTmpAdd.setAddressBlockId(addressBlockId);
			communitySupplyTmpAdd.setId(communitySupplyTmpAddId);
			communitySupplyTmpAdd.setSupplyName(supplyName);
			communitySupplyTmpAdd.settCommunitySupplyTypeFId(supplyTypeId);
			communitySupplyTmpAdd.setAuditStatus(CommunitySupplyTmpDict.CommunitySupply_AuditStatus.TODO);
			communitySupplyTmpAdd.setAuditType(CommunitySupplyTmpDict.CommunitySupply_AuditType.ADD);
			Integer resCount = communitySupplyTmpBaseDao.insertCommunitySupplyTmp(communitySupplyTmpAdd);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("merchantClaim.addMerchant.insertCommunitySupplyTmp.failed");
			}
		}
		//保存店铺描述图片信息
		if(descriptPicList!=null&&descriptPicList.size()>0){
			String supplyPicBase = sysParamManager.getSysParaValue(SysParamKey.COMMUNITY_SUPPLY_PIC_LIST_BASEPATH);
			List<String> picUrlList = new ArrayList<String>();
			for(int i=0;i<descriptPicList.size();i++){//上传图片
				RequestFileEntity requestFile = descriptPicList.get(i);
				//生成文件名 userId+time+index+两位随机数
				String resFileName = new StringBuffer().append(userId).append(DateUtil.getCurrSysTimeStr())
						.append("_").append(i+1).append("_").append(RandomUtils.createRandom(true, 3))
						.append(".").append(requestFile.getFileType()).toString();
				picUrlList.add(resFileName);
				String serverPath = supplyPicBase+resFileName;
				try {
					fileServerService.uploadFile(serverPath, requestFile.getFileContent());
				} catch (IOException e) {
					logger.info("upload descriptPic file cause exception:"+e.getMessage(), e);
					throw new BusinessRuntimeException("MerchantClaimService.addMerchant.uploadDescriptPic.error",new Object[]{requestFile.getFileName()});
				}
				FutureTask<Boolean> task = new FutureTask<Boolean>(new UploadSmallPicRunnable(smallPicUploadService,BusinessModelType.CommunitySupply,fileServerService.getFileServierUploadBasePath(),supplyPicBase, resFileName, requestFile.getFileContent()));
				new Thread(task).start();
			}
			List<CommunitySupplyPic> communitySupplyPicAddList = new ArrayList<CommunitySupplyPic>();
			List<BigInteger> picAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_pic,picUrlList.size());
			for(int i=0;i<picUrlList.size();i++){
				String picUrl = picUrlList.get(i);
				BigInteger picAddId = picAddIdList.get(i);
				CommunitySupplyPic communitySupplyPic = new CommunitySupplyPic();
				communitySupplyPic.setId(picAddId);
				communitySupplyPic.setPicUrl(picUrl);
				communitySupplyPic.settCommunitySupplyFId(null);//设置为空
				communitySupplyPic.settCommunitySupplyTmpFId(communitySupplyTmpAddId);
				communitySupplyPicAddList.add(communitySupplyPic);
			}
			int res = communitySupplyPicBaseDao.insertCommunitySupplyPicBatch(communitySupplyPicAddList);
			if(res!=communitySupplyPicAddList.size()){
				throw new BusinessRuntimeException("MerchantClaimService.addMerchant.insertCommunitySupplyPicBatch.failed");
			}
		}
		//保存商户图片信息
		uploadCommunityCompanyListPic(userId, merchantLicensePicList, null, null, communitySupplyTmpAddId);
	}

	@Override
	public void doClaimMerchant(BigInteger userId, BigInteger groupBuildSupplyRelaId, String merchantPhone,
			List<RequestFileEntity> merchantLicensePicList) {
		GroupBuildingHasTCommunitySupply groupBuildingHasTCommunitySupply = groupBuildingHasTCommunitySupplyBaseDao.selectGroupBuildingHasTCommunitySupplyBySeqId(groupBuildSupplyRelaId);
		if(groupBuildingHasTCommunitySupply==null){
			throw new BusinessRuntimeException("MerchantClaimService.doClaimMerchant.relation.null");
		}
		//新增认领表信息
		String creatTime = dualDao.getNowTime();
		User baseUser = userBaseDao.selectUserBySeqId(userId);
		BigInteger claimRoomId = baseUser==null?null:baseUser.getDefaultRoomId();
		BigInteger groupBuildingId = groupBuildingHasTCommunitySupply.gettGroupBuildingFId();
		BigInteger tGroupBuildingHasTCommunitySupplyFId = groupBuildSupplyRelaId;
		BigInteger communitySupplyId = groupBuildingHasTCommunitySupply.gettCommunitySupplyFId();
		BigInteger communitySupplyBelongAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_belong);
		Integer auditStatus = CommunitySupplyDict.CommunitySupply_Belong.UNDO;//认领状态默认 "1":"待审批
		{
			CommunitySupplyBelong communitySupplyBelongAdd = new CommunitySupplyBelong();
			communitySupplyBelongAdd.setAuditDesc(null);
			communitySupplyBelongAdd.setAuditOmsUserId(null);
			communitySupplyBelongAdd.setAuditStatus(auditStatus);
			communitySupplyBelongAdd.setAuditTime(null);
			communitySupplyBelongAdd.setClaimRoomId(claimRoomId);
			communitySupplyBelongAdd.setClaimUserId(userId);
			communitySupplyBelongAdd.setCommunitySupplyId(communitySupplyId);
			communitySupplyBelongAdd.setCreatTime(creatTime);
			communitySupplyBelongAdd.setGroupBuildingId(groupBuildingId);
			communitySupplyBelongAdd.setId(communitySupplyBelongAddId);
			communitySupplyBelongAdd.setSupplyCompanyPhone(merchantPhone);
			communitySupplyBelongAdd.settGroupBuildingHasTCommunitySupplyFId(tGroupBuildingHasTCommunitySupplyFId);
			Integer resCount = communitySupplyBelongBaseDao.insertCommunitySupplyBelong(communitySupplyBelongAdd);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("MerchantClaimService.doClaimMerchant.insertCommunitySupplyBelong.failed");
			}
		}
		//新增商户认领表图片信息
		uploadCommunityCompanyListPic(userId, merchantLicensePicList, communitySupplyBelongAddId, null, null);
	}
	
	/**
	 * 上传商户图片信息
	 * @param userId 
	 * @param merchantLicensePicList
	 * @param supplyBelongFId 认领过程中对应认领记录Id
	 * @param supplyCompanyFId 审核通过后所属商户Id
	 * @param supplyTmpFId 新增的临时商户对应记录Id
	 */
	private void uploadCommunityCompanyListPic(BigInteger userId,List<RequestFileEntity> merchantLicensePicList,BigInteger supplyBelongFId,BigInteger supplyCompanyFId,BigInteger supplyTmpFId){
	//保存商户图片信息
			if(merchantLicensePicList!=null&&merchantLicensePicList.size()>0){
				String supplyCompanyListPicBase = sysParamManager.getSysParaValue(SysParamKey.COMMUNITY_COMPANY_LISTPIC_BASEPATH);
				List<String> picUrlList = new ArrayList<String>();
				for(int i=0;i<merchantLicensePicList.size();i++){//上传图片
					RequestFileEntity requestFile = merchantLicensePicList.get(i);
					//生成文件名 userId+time+index+两位随机数
					String resFileName = new StringBuffer().append(userId).append(DateUtil.getCurrSysTimeStr())
							.append("_").append(i+1).append("_").append(RandomUtils.createRandom(true, 3))
							.append(".").append(requestFile.getFileType()).toString();
					picUrlList.add(resFileName);
					String serverPath = supplyCompanyListPicBase+resFileName;
					try {
						fileServerService.uploadFile(serverPath, requestFile.getFileContent());
					} catch (IOException e) {
						logger.info("upload supplyCompanyListPic file cause exception:"+e.getMessage(), e);
						throw new BusinessRuntimeException("MerchantClaimService.uploadCommunityCompanyListPic.uploadMerchantLicensePic.error",new Object[]{requestFile.getFileName()});
					}
					FutureTask<Boolean> task = new FutureTask<Boolean>(new UploadSmallPicRunnable(smallPicUploadService,BusinessModelType.CommunityCompany,fileServerService.getFileServierUploadBasePath(),supplyCompanyListPicBase, resFileName, requestFile.getFileContent()));
					new Thread(task).start();
				}
				List<CommunitySupplyCompanyPic> communitySupplyCompanyPicAddList = new ArrayList<CommunitySupplyCompanyPic>();
				List<BigInteger> picAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_supply_company_pic,picUrlList.size());
				for(int i=0;i<picUrlList.size();i++){
					String picUrl = picUrlList.get(i);
					BigInteger picAddId = picAddIdList.get(i);
					CommunitySupplyCompanyPic communitySupplyCompanyPic = new CommunitySupplyCompanyPic();
					communitySupplyCompanyPic.setId(picAddId);
					communitySupplyCompanyPic.setPicUrl(picUrl);
					communitySupplyCompanyPic.settCommunitySupplyBelongFId(supplyBelongFId);
					communitySupplyCompanyPic.settCommunitySupplyCompanyFId(supplyCompanyFId);
					communitySupplyCompanyPic.settCommunitySupplyTmpFId(supplyTmpFId);
					communitySupplyCompanyPicAddList.add(communitySupplyCompanyPic);
				}
				int res = communitySupplyCompanyPicBaseDao.insertCommunitySupplyCompanyPicBatch(communitySupplyCompanyPicAddList);
				if(res!=communitySupplyCompanyPicAddList.size()){
					throw new BusinessRuntimeException("MerchantClaimService.uploadCommunityCompanyListPic.insertCommunitySupplyCompanyPicBatch.failed");
				}
			}
	}
	
}
