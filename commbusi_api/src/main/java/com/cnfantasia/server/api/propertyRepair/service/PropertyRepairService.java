package com.cnfantasia.server.api.propertyRepair.service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.dredgeBill.service.IDredgeBillBaseService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.comments.entity.GoalIdCommentsEntity;
import com.cnfantasia.server.api.comments.service.ICommentsService;
import com.cnfantasia.server.api.commonBusiness.constant.CommonModuleDict;
import com.cnfantasia.server.api.commonBusiness.service.ICommMobileService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.propertyRepair.constant.PropertyRepairDict;
import com.cnfantasia.server.api.propertyRepair.dao.IPropertyRepairDao;
import com.cnfantasia.server.api.propertyRepair.entity.PropertyRepair4List;
import com.cnfantasia.server.api.propertyRepair.entity.PropertyRepairEntity;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.commonBusiness.constant.SmallPicUploadConfig;
import com.cnfantasia.server.business.commonBusiness.entity.WidthHeight;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.exception.ExceptionParseUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.ImageZipUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.RandomUtils;
import com.cnfantasia.server.domainbase.groupBuildingHasTPropertyService.entity.GroupBuildingHasTPropertyService;
import com.cnfantasia.server.domainbase.groupBuildingHasTPropertyService.service.IGroupBuildingHasTPropertyServiceBaseService;
import com.cnfantasia.server.domainbase.propertyRepair.dao.PropertyRepairBaseDao;
import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;
import com.cnfantasia.server.domainbase.propertyRepair.service.PropertyRepairBaseService;
import com.cnfantasia.server.domainbase.propertyRepairType.dao.IPropertyRepairTypeBaseDao;
import com.cnfantasia.server.domainbase.propertyRepairType.entity.PropertyRepairType;
import com.cnfantasia.server.domainbase.propertyRepairType.service.IPropertyRepairTypeBaseService;
import com.propertySoftwareDock.base.dao.IPropertySoftwareDockDao;
import com.propertySoftwareDock.base.entity.MailSendThread;
import com.propertySoftwareDock.base.entity.RealRoomSoftwareInfo;
import com.propertySoftwareDock.base.entity.RepairPushEntity;
import com.propertySoftwareDock.base.service.IPropertySoftwareDockService;

public class PropertyRepairService extends PropertyRepairBaseService implements IPropertyRepairService {
	private Log logger = LogFactory.getLog(getClass());

	@Resource
	IPropertyRepairDao propertyRepairDao;

	@Resource
	IUuidManager uuidManager;

	@Resource
	IDualDao dualDao;

	@Resource
	private ISysParamManager sysParamManager;

	@Resource
	private IFileServerService fileServerService;

	@Resource
	private PropertyRepairBaseDao propertyRepairBaseDao;

	@Resource
	private ICommentsService commentsService;

	@Resource
	ICommMobileService commMobileService;

	@Resource
	IPropertyRepairTypeBaseDao propertyRepairTypeBaseDao;

	@Resource
	IGroupBuildingHasTPropertyServiceBaseService groupBuildingHasTPropertyServiceBaseService;

	@Resource
	private ICommonRoomService commonRoomService;

	@Resource
	private IPropertySoftwareDockDao propertySoftwareDockDao;

	@Resource
	private IPropertyRepairTypeBaseService propertyRepairTypeBaseService;

	@Resource
	private IDredgeBillBaseService dredgeBillBaseService;

	@Override
	public void addRepair(PropertyRepair propertyRepair, List<RequestFileEntity> picList) {

		//新增报修单
		{
			//保存图片信息
			String picUrl = uploadRepairPic(propertyRepair.gettUserFId(), picList);
			if(StringUtils.isNotEmpty(picUrl))
				propertyRepair.setPicUrl(picUrl); //多个图片保存在同一字段中，用分号分隔

			{//报修单加上单号：yyyyMMdd+userId+3位随机整数
				String number = DateUtil.formatMinuteTogether.get().format(new java.util.Date()) + "" + propertyRepair.gettUserFId();
				int i = (int) (Math.random() * 900) + 100;
				number = number + i;
				propertyRepair.setNumber(number);
			}

			int res = propertyRepairBaseDao.insertPropertyRepair(propertyRepair);
			if (res <= 0) {
				throw new BusinessRuntimeException("PropertyRepairService.addRepair.insertPropertyRepair.failed");
			}

			//推送到物业软件
			new PushRepairThread(propertyRepair, picList).start();

			try{
				sendMsgToServiceAfterAddRepair(propertyRepair.gettPropertyRepairTypeFId(), propertyRepair, propertyRepair.gettGroupBuildingFId());
			}catch (Exception e){
				logger.info(e.getMessage(), e);
			}
		}
	}
	
	private void sendMsgToServiceAfterAddRepair(BigInteger repairTypeId, PropertyRepair propertyRepair, BigInteger gbId) {
		//String msg = "【小区名+门牌号】提交报修单，报修类型：【报修类型】，业主联系电话：【联系电话】【解放区】";
		String propertyRepairTypeName = propertyRepairTypeBaseDao.selectPropertyRepairTypeBySeqId(repairTypeId).getName();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tGroupBuildingFId", gbId);
		List<GroupBuildingHasTPropertyService> groupBuildingHasTPropertyServiceList = groupBuildingHasTPropertyServiceBaseService
				.getGroupBuildingHasTPropertyServiceByCondition(paramMap);
		if (groupBuildingHasTPropertyServiceList.size() == 1) {
//			String msg = SmsPropertyUtil.getProperty("send_to_service_after_add_repair",
//					propertyRepair.getAddress(), propertyRepairTypeName, propertyRepair.getTel());
//			commMobileService.sendMsg(groupBuildingHasTPropertyServiceList.get(0).getServerMobile(), msg);
			ShortMsgUtil.sendMessage(groupBuildingHasTPropertyServiceList.get(0).getServerMobile(), "order_dredge1",
					propertyRepair.getAddress(), propertyRepairTypeName, propertyRepair.getTel());
		}
	}

	/**
	 * 保存报修图片信息
	 */
	private String uploadRepairPic(BigInteger userId, List<RequestFileEntity> picList) {
		StringBuilder picUrl = new StringBuilder();

		if (picList != null && picList.size() > 0) {
			String repairPicBase = sysParamManager.getSysParaValue(SysParamKey.Repair_Pic_BasePath);
			for (int i = 0; i < picList.size(); i++) {//上传图片
				RequestFileEntity requestFile = picList.get(i);
				//生成文件名 userId+time+index+3位随机数
				String resFileName = new StringBuffer().append(userId).append(DateUtil.getCurrSysTimeStr()).append("_").append(i + 1).append("_")
						.append(RandomUtils.createRandom(true, 3)).append(".").append(requestFile.getFileType()).toString();
				picUrl.append(resFileName).append(";");
				String serverPath = repairPicBase + resFileName;
				try {
					fileServerService.uploadFile(serverPath, requestFile.getFileContent());
					generateMiniImageThread(serverPath);
				} catch (IOException e) {
					logger.info("uploadRepairPic upload Repair file cause exception:" + e.getMessage(), e);
					throw new BusinessRuntimeException("RepairService.uploadRepairPic.uploadFile.error", new Object[] { requestFile.getFileName() });
				}
			}
		}

		return picUrl.toString();
	}
	
	private void generateMiniImageThread(final String serverPath) {
		new Thread() {
			@Override
			public void run() {
				generateMiniImage(new File(fileServerService.getFileServierUploadBasePath() + serverPath));
			}
		}.start();
	}
	
	private void generateMiniImage(File bigImageFile) {
		Map<String, WidthHeight> guigeList = SmallPicUploadConfig.BusinessModelType.Dredge.getGuigeList();

		String fileName = bigImageFile.getName();
		int lastPointIndex = fileName.lastIndexOf(".");
		String fileType = fileName.substring(lastPointIndex); //文件后缀名，带.号

		//String goalDirectoryFilePath = descDirecBasePath + fileName.substring(0, lastPointIndex);
		//   ../dredgePic/5038472015-08-12_10_00_16_1_755.jpg
		String goalDirectoryFilePath = bigImageFile.getAbsolutePath().substring(0, bigImageFile.getAbsolutePath().lastIndexOf("."));

		// 创建小图的目标目录
		File goalFileDir = new File(goalDirectoryFilePath);
		if (!goalFileDir.exists()) {
			goalFileDir.mkdirs();
		}

		for (String goalFileName : guigeList.keySet()) {
			String smallIcon = goalDirectoryFilePath + "/" + goalFileName + fileType;
			try {
				WidthHeight tmpWidthHeight = guigeList.get(goalFileName);
				ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(), tmpWidthHeight.getWidth(), tmpWidthHeight.getHeight(), 1f, smallIcon);
				//	String resSmallIcon = ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(),tmpWidth.getWidth(),null, quality, smallIcon);
				//	String resSmallIcon = ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(), tmpWidth.getWidth(),tmpWidth.getHeight(), quality, smallIcon);
			} catch (Exception e) {
				logger.info(smallIcon + "create small image failure. ");
			}
		}
	}

	@Override
	public List<PropertyRepairType> getPropertyRepairTypeList_ByGbId(Map<String, Object> paramMap) {
		return propertyRepairDao.getPropertyRepairTypeList_ByGbId(paramMap);
	}

	@Override
	public Map<String, Object> qryIsRepairEnable(Map<String, Object> paramMap) {
		return propertyRepairDao.qryIsRepairEnable(paramMap);
	}

	@Override
	public List<PropertyRepair4List> getRepairList(BigInteger userId, int type,PageModel pageModel) {
		return propertyRepairDao.getRepairList(userId, commonRoomService.getGroupBuildingIdByUserId(userId), type, pageModel);
	}

	@Override
	public List<Map<String, Object>> propertyRepair2MapList(BigInteger userId, List<PropertyRepair4List> propertyRepairList) {
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		for (PropertyRepair4List propertyRepair : propertyRepairList) {
			Map<String, Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("repairTypeName", propertyRepair.getRepairTypeName());
			tmpMap.put("processDesc", propertyRepair.getProcessDesc());
			tmpMap.put("id", propertyRepair.getId());
			tmpMap.put("repairState", propertyRepair.getRepaireState());
			tmpMap.put("submitTime", propertyRepair.getSys0AddTime());
			tmpMap.put("repairContent", propertyRepair.getRepairContent());
			resList.add(tmpMap);
		}
		return resList;
	}

	@Override
	public PropertyRepairEntity qryRepairDetail(String repairId) {
		BigInteger prId = new BigInteger(repairId);
		PropertyRepairEntity propertyRepairEntity = propertyRepairDao.getRepairDetail(prId);
		if (propertyRepairEntity.gettPropertyRepairerFId() != null) {//找维修工的评论信息
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tPropertyRepairerFId", propertyRepairEntity.gettPropertyRepairerFId());
			List<PropertyRepair> prList = propertyRepairDao.selectPropertyRepairByCondition(paramMap, false);
			List<BigInteger> prIdList = new ArrayList<BigInteger>();
			for (int i = 0; i < prList.size(); i++) {
				prIdList.add(prList.get(i).getId());
			}

			/*CommentsEntity commentsEntity = commentsService.getFirstComentDetail(propertyRepairEntity.gettPropertyRepairerFId(),
					CommonModuleDict.CommonModule_TargetType.T_PROPERTY_REPAIR_COMMENT);

			List<CommentsEntity> cList = commentsService.getCommentsList(CommonModuleDict.CommonModule_TargetType.T_PROPERTY_REPAIR_COMMENT,
					propertyRepairEntity.gettPropertyRepairerFId());
			 */

			//评论的对象是报修单，不再是维修工，通过报修单去找维修工
			List<GoalIdCommentsEntity> goalIdCommentsEntity = commentsService.getCommentsListMulti(
					CommonModuleDict.CommonModule_TargetType.T_PROPERTY_REPAIR_COMMENT, prIdList, null);
			List<CommentsEntity> cList = new ArrayList<CommentsEntity>();
			double totalStarLevel = 0;
			CommentsEntity firstCommentsEntity = null;

			for (int i = 0; i < goalIdCommentsEntity.size(); i++) {
				for (int j = 0; j < goalIdCommentsEntity.get(i).getCommentsEntityList().size(); j++) {
					cList.add(goalIdCommentsEntity.get(i).getCommentsEntityList().get(j));
					totalStarLevel += goalIdCommentsEntity.get(i).getCommentsEntityList().get(j).getLevel();

					if (goalIdCommentsEntity.get(i).getCommentsEntityList().get(j).getTargetId().toString().equals(repairId)) {
						firstCommentsEntity = goalIdCommentsEntity.get(i).getCommentsEntityList().get(j);
					}
				}
			}

			if (cList.size() > 0) {
				propertyRepairEntity.setAvgStarLevel(totalStarLevel / cList.size());
			}

			if (firstCommentsEntity != null && goalIdCommentsEntity.size() > 0 && goalIdCommentsEntity.get(0).getCommentsEntityList().size() > 0) {
				firstCommentsEntity.setAvgTotalStarLevel(totalStarLevel / cList.size());
				propertyRepairEntity.setFirstComment(firstCommentsEntity);
			}

			propertyRepairEntity.setCommentCount(cList.size());
		}

		return propertyRepairEntity;
	}

	@Override
	public Object propertyRepair2Map(PropertyRepairEntity propertyRepairEntity) {
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("id", propertyRepairEntity.getId());
		if (!StringUtils.isEmpty(propertyRepairEntity.getPicUrl())) {
			String[] pics = propertyRepairEntity.getPicUrl().split(";");
			String relativePath = sysParamManager.getSysParaValue(SysParamKey.Repair_Pic_BasePath);
			for (int i = 0; i < pics.length; i++) {
				pics[i] = fileServerService.getAccessUrl(relativePath + pics[i], propertyRepairEntity);
			}
			tmpMap.put("picList", pics);
		}
		tmpMap.put("repairState", propertyRepairEntity.getRepaireState());
		tmpMap.put("repairContent", propertyRepairEntity.getRepairContent());
		tmpMap.put("submitTime", propertyRepairEntity.getSys0AddTime());
		tmpMap.put("processDesc", propertyRepairEntity.getFinishDesc());
		tmpMap.put("repairTypeName", propertyRepairEntity.getRepairTypeName());
		if (propertyRepairEntity.getPropertyRepairer() != null) {
			Map<String, Object> repairerMap = new HashMap<String, Object>();
			repairerMap.put("id", propertyRepairEntity.getPropertyRepairer().getId());
			repairerMap.put("userPicUrl", propertyRepairEntity.getPropertyRepairer().getHeadimgurl());
			repairerMap.put("repairerName", propertyRepairEntity.getPropertyRepairer().getName());
			repairerMap.put("tel", propertyRepairEntity.getPropertyRepairer().getTel());
			repairerMap.put("starLevel", propertyRepairEntity.getAvgStarLevel());
			tmpMap.put("ext_repairer", repairerMap);
		}

		tmpMap.put("ext_commentTotalCount", propertyRepairEntity.getCommentCount());
		if (propertyRepairEntity.getFirstComment() != null) {
			CommentsEntity fisrtComment = propertyRepairEntity.getFirstComment();
			Map<String, Object> firstCommentMap = new HashMap<String, Object>();
			firstCommentMap.put("id", fisrtComment.getId());
			firstCommentMap.put("userPicUrl", fisrtComment.getUser().getImgprofile());
			firstCommentMap.put("time", fisrtComment.getSys0AddTime());
			firstCommentMap.put("userId", fisrtComment.getUser().getId());
			firstCommentMap.put("userName", fisrtComment.getUser().getNickName());
			firstCommentMap.put("starLevel", fisrtComment.getLevel());
			firstCommentMap.put("txtContent", fisrtComment.getContent());
			firstCommentMap.put("ext_user_BelongGbName", fisrtComment.getUserGroupBuilding().getName());

			tmpMap.put("ext_firstComments", firstCommentMap);
		}

		return tmpMap;
	}

	@Override
	public void pushRepairToSoftware(RepairPushEntity entity) {
		BigInteger repairId = entity.getDredgeBill().getId();
		String key = RedisCachePrefix.property_repair_push_software + "dredge_" + repairId;

		IPropertySoftwareDockService dockService = (IPropertySoftwareDockService) CnfantasiaCommbusiUtil
				.getBeanManager(entity.getSoftwareInfo().getServiceClassName());
		String billNo = null;
		String exception = "";
		try {
			billNo = dockService.pushPropertyRepair(entity);
		} catch (Exception e) {
			e.printStackTrace();
			exception = ExceptionParseUtil.parseExceptionMessage(e);
		} finally {
			if (billNo != null) {
				//保存billNo，并设置为师傅已接单
				DredgeBill dredgeBill = entity.getDredgeBill();
				dredgeBill.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Accepted);
				dredgeBill.settWorkerFId(entity.getSoftwareInfo().getDefaultRepairerId());
				dredgeBill.setSoftwareBillId(billNo);
				dredgeBillBaseService.updateDredgeBill(dredgeBill);

				//清除这个redis内容
				RedisCacheHandler.del(key);

				//将该单移至获取详情redis中
				entity.setFailCount(0);
				entity.setPicList(null);
				entity.setDredgeBill(dredgeBill);
				String getDetailKey = RedisCachePrefix.property_repair_need_software_detail + "dredge_" + repairId;
				RedisCacheHandler.set(getDetailKey, JSON.toJSONString(entity), getRepairDetailExpireSecond(entity));

			} else {
				int failCount = entity.getFailCount() + 1;
				entity.setFailCount(failCount);
				if (failCount < 3) {//添加失败计数
					RedisCacheHandler.set(key, JSON.toJSONString(entity), getRepairDetailExpireSecond(entity));
				} else {
					RedisCacheHandler.del(key);
					String notifyMail = sysParamManager.getSysParaValue(SysParamKey.PropertySoftwareNotifyFailMail);
					if (!DataUtil.isEmpty(notifyMail)) {
						String content = "推送物业管理软件维修单失败：<br>" +
								"pushEntity : " + JSON.toJSONString(entity) + "<br>" +
								"exception : " + exception + "<br>";
						new com.propertySoftwareDock.base.entity.MailSendThread("推送物业管理软件维修单失败",content, notifyMail).start();
					}
				}
			}
		}
	}
	/*public void pushRepairToSoftware(RepairPushEntity entity) {
		BigInteger repairId = entity.getRepair().getId();
		String key = RedisCachePrefix.property_repair_push_software + repairId;

		IPropertySoftwareDockService dockService = (IPropertySoftwareDockService) CnfantasiaCommbusiUtil
				.getBeanManager(entity.getSoftwareInfo().getServiceClassName());
		String billNo = null;
		String exception = "";
		try {
			billNo = dockService.pushPropertyRepair(entity);
		} catch (Exception e) {
			e.printStackTrace();
			exception = ExceptionParseUtil.parseExceptionMessage(e);
		} finally {
			if (billNo != null) {
				if (entity.getRepair() != null) {
					//保存billNo，并设置为已分配师傅
					String expectTime = entity.getRepair().getExpectDate() + " " + entity.getRepair().getExpectTimeBegin();
					expectTime = expectTime.length() > 16 ? expectTime : expectTime + ":00";
					PropertyRepair repair = entity.getRepair();
					repair.setSoftwareBillId(billNo);
					repair.setRepaireState(PropertyRepairDict.RepairStatus.SET_REPAIRER);
					repair.settPropertyRepairerFId(entity.getSoftwareInfo().getDefaultRepairerId());
					repair.setEstimateDoorTimeBegin(expectTime);
					repair.setAsignDesc("物业已分配师傅处理，具体上门时间请联系师傅。");
					repair.setAsignTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					propertyRepairBaseDao.updatePropertyRepair(repair);

					//将该单移至获取详情redis中
					entity.setFailCount(0);
					entity.setPicList(null);
					entity.setRepair(repair);
					String getDetailKey = RedisCachePrefix.property_repair_need_software_detail + repairId;
					RedisCacheHandler.set(getDetailKey, JSON.toJSONString(entity), getRepairDetailExpireSecond(entity));
				} else if (entity.getDredgeBill() != null) {
					DredgeBill dredgeBill = entity.getDredgeBill();
					dredgeBill.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Accepted);
					dredgeBill.settWorkerFId(entity.getSoftwareInfo().getDefaultRepairerId());
					dredgeBill.setSoftwareBillId(billNo);
					dredgeBillBaseService.updateDredgeBill(dredgeBill);

					//将该单移至获取详情redis中
					entity.setFailCount(0);
					entity.setPicList(null);
					entity.setDredgeBill(entity.getDredgeBill());
					String getDetailKey = RedisCachePrefix.property_repair_need_software_detail + "dredge_" + repairId;
					RedisCacheHandler.set(getDetailKey, JSON.toJSONString(entity), getRepairDetailExpireSecond(entity));
				}


				//清除这个redis内容
				RedisCacheHandler.del(key);

			} else {
				int failCount = entity.getFailCount() + 1;
				entity.setFailCount(failCount);
				if (failCount < 3) {//添加失败计数
					RedisCacheHandler.set(key, JSON.toJSONString(entity), getRepairDetailExpireSecond(entity));
				} else {
					RedisCacheHandler.del(key);
					String notifyMail = sysParamManager.getSysParaValue(SysParamKey.PropertySoftwareNotifyFailMail);
					if (!DataUtil.isEmpty(notifyMail)) {
						String content = "推送物业管理软件维修单失败：<br>" +
								"pushEntity : " + JSON.toJSONString(entity) + "<br>" +
								"exception : " + exception + "<br>";
						new MailSendThread("推送物业管理软件维修单失败",content, notifyMail).start();
					}
				}
			}
		}
	}*/

	/**
	 * redis过期时间，设置为预约时间后三天
	 * @param entity
	 * @return
     */
	public int getRepairDetailExpireSecond(RepairPushEntity entity) {
		if (entity == null || (entity.getRepair() == null && entity.getDredgeBill() == null)) {
			return 1;
		}
		String expectTime = "";
		if (entity.getRepair() != null) {
			expectTime = entity.getRepair().getExpectDate() + " " + entity.getRepair().getExpectTimeBegin();
		} else if (entity.getDredgeBill() != null){
			entity.getDredgeBill().getExpectdate();
		}
		expectTime = expectTime.replaceAll("-", "/");
		Date now = new Date();
		Date expect = new Date(expectTime);
		Long second = (expect.getTime() - now.getTime()) / 1000;
		return second.intValue() + 60 * 60 * 24 * 3;
	}

	private class PushRepairThread extends Thread {
		private PropertyRepair propertyRepair;
		private List<RequestFileEntity> picList;
		PushRepairThread(PropertyRepair repair,
						 List<RequestFileEntity> picList) {
			this.propertyRepair = repair;
			this.picList = picList;
		}

		@Override
		public void run() {
			BigInteger realRoomId = commonRoomService.selectRealRoomByUserId(propertyRepair.gettUserFId()).getId();
			RealRoomSoftwareInfo realRoomSoftwareInfo = propertySoftwareDockDao.getRealRoomSoftwareInfo(realRoomId);
			if (realRoomSoftwareInfo != null && realRoomSoftwareInfo.getSoftwareHouseId() != null
					&& realRoomSoftwareInfo.getDefaultRepairerId() != null) {
				RepairPushEntity entity = new RepairPushEntity();
				entity.setFailCount(0);
				entity.setRepair(propertyRepair);
				entity.setSoftwareInfo(realRoomSoftwareInfo);
				entity.setPicList(picList);
				BigInteger repairTypeId = propertyRepair.gettPropertyRepairTypeFId();
				if (repairTypeId != null) {
					entity.setRepairTypeName(propertyRepairTypeBaseService.getPropertyRepairTypeBySeqId(repairTypeId).getName());
				}
				try {
					pushRepairToSoftware(entity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
