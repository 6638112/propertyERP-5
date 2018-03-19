package com.cnfantasia.server.ms.propertyRepair.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.cnfantasia.server.ms.propertyRepair.entity.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.commonBusiness.service.ICommMobileService;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.dredge.entity.ProcessRecord;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.homeMessage.constant.HomeMessageDict;
import com.cnfantasia.server.api.homeMessage.service.IHomeMessageService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.redpoint.constant.RedpointConstant;
import com.cnfantasia.server.api.redpoint.constant.RedpointDict;
import com.cnfantasia.server.api.redpoint.dao.IRedpointDao;
import com.cnfantasia.server.api.redpoint.entity.BasicSourceEntity;
import com.cnfantasia.server.api.redpoint.entity.RedpointContentEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.utils.CommonMultiFileUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.FocRuntimeException;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.entity.DredgeBillHasProcessRecording;
import com.cnfantasia.server.domainbase.dredgeWorker.dao.DredgeWorkerBaseDao;
import com.cnfantasia.server.domainbase.dredgeWorker.entity.DredgeWorker;
import com.cnfantasia.server.domainbase.dredgeWorkerHasDredgeType.dao.DredgeWorkerHasDredgeTypeBaseDao;
import com.cnfantasia.server.domainbase.dredgeWorkerServiceAddressBlock.dao.DredgeWorkerServiceAddressBlockBaseDao;
import com.cnfantasia.server.domainbase.dredgeWorkerServiceAddressBlock.entity.DredgeWorkerServiceAddressBlock;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.groupBuildingHasTPropertyService.entity.GroupBuildingHasTPropertyService;
import com.cnfantasia.server.domainbase.groupBuildingHasTPropertyService.service.IGroupBuildingHasTPropertyServiceBaseService;
import com.cnfantasia.server.domainbase.loginNo.dao.LoginNoBaseDao;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.message.service.IMessageBaseService;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.messageParameter.service.IMessageParameterBaseService;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;
import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;
import com.cnfantasia.server.domainbase.propertyRepair.service.PropertyRepairBaseService;
import com.cnfantasia.server.domainbase.propertyRepairType.dao.IPropertyRepairTypeBaseDao;
import com.cnfantasia.server.domainbase.propertyRepairType.entity.PropertyRepairType;
import com.cnfantasia.server.domainbase.propertyRepairer.entity.PropertyRepairer;
import com.cnfantasia.server.domainbase.propertyRepairer.service.IPropertyRepairerBaseService;
import com.cnfantasia.server.domainbase.redpointContent.dao.IRedpointContentBaseDao;
import com.cnfantasia.server.domainbase.redpointContent.entity.RedpointContent;
import com.cnfantasia.server.domainbase.redpointContentSource.dao.IRedpointContentSourceBaseDao;
import com.cnfantasia.server.domainbase.redpointContentSource.entity.RedpointContentSource;
import com.cnfantasia.server.domainbase.user.dao.UserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;
import com.cnfantasia.server.domainbase.userHasTMessage.dao.IUserHasTMessageBaseDao;
import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.propertyRepair.constant.PropertyRepairConstant;
import com.cnfantasia.server.ms.propertyRepair.dao.IPropertyRepairDao;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;

public class PropertyRepairService extends PropertyRepairBaseService implements IPropertyRepairService {
	private Log logger = LogFactory.getLog(getClass());

	@Resource
	IPropertyRepairDao propertyRepairDao;

	@Resource
	IPropertyRepairTypeBaseDao propertyRepairTypeBaseDao;

	@Resource
	IUuidManager uuidManager;

	@Resource
	IDualDao dualDao;

	@Resource
	IGroupBuildingHasTPropertyServiceBaseService groupBuildingHasTPropertyServiceBaseService;

	@Resource
	IPropertyRepairerBaseService propertyRepairerBaseService;

	@Resource
	IGroupBuildingBaseService groupBuildingBaseService;

	@Resource
	ICommMobileService commMobileService;
	
	@Resource
	IRedpointContentBaseDao redpointContentBaseDao;
	@Resource
	IRedpointContentSourceBaseDao redpointContentSourceBaseDao;
	@Resource
	IRedpointDao redpointDao;

	@Resource
	private IHomeMessageService homeMessageService;
	
	@Resource
	IFileServerService fileServerService;
	
	@Override
	public List<PropertyRepairConfig> listRepairConfig(Map<String, Object> paramMap) {
		return propertyRepairDao.listRepairConfig(paramMap);
	}
	
	@Override
	public int listRepairConfig_forCount(Map<String, Object> paramMap) {
		return propertyRepairDao.listRepairConfig_forCount(paramMap);
	}

	@Override
	public int openRepairService(String gbId, String serviceMobile) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tGroupBuildingFId", gbId);
		paramMap.put("tPropertyServiceFId", PropertyRepairConstant.PROPERTY_SERVICE_REPAIR);
		List<GroupBuildingHasTPropertyService> groupBuildingHasTPropertyServiceList = groupBuildingHasTPropertyServiceBaseService
				.getGroupBuildingHasTPropertyServiceByCondition(paramMap);
		int udpCount = 0;
		if (groupBuildingHasTPropertyServiceList.size() == 1) {
			GroupBuildingHasTPropertyService groupBuildingHasTPropertyService = groupBuildingHasTPropertyServiceList.get(0);
			groupBuildingHasTPropertyService.setServerMobile(serviceMobile);
			groupBuildingHasTPropertyService.setStatus(1);//开启
			groupBuildingHasTPropertyService.setSys0UpdTime(null);
			udpCount = groupBuildingHasTPropertyServiceBaseService.updateGroupBuildingHasTPropertyService(groupBuildingHasTPropertyService);
		} else {
			GroupBuildingHasTPropertyService groupBuildingHasTPropertyService = new GroupBuildingHasTPropertyService();
			groupBuildingHasTPropertyService.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_group_building_has_t_property_service));
			groupBuildingHasTPropertyService.settGroupBuildingFId(new BigInteger(gbId));
			groupBuildingHasTPropertyService.settPropertyServiceFId(new BigInteger("" + PropertyRepairConstant.PROPERTY_SERVICE_REPAIR));
			groupBuildingHasTPropertyService.setServerMobile(serviceMobile);
			groupBuildingHasTPropertyService.setStatus(1);//开启
			groupBuildingHasTPropertyService.setSys0AddUser(UserContext.getCurrUser().getId());
			udpCount = groupBuildingHasTPropertyServiceBaseService.insertGroupBuildingHasTPropertyService(groupBuildingHasTPropertyService);
		}

		return udpCount;
	}

	@Override
	public List<PropertyRepairType> getPropertyRepairTypeListByGbId(String gbId) {
		return propertyRepairDao.getPropertyRepairTypeListByGbId(gbId);
	}

	@Override
	@Transactional
	public void saveRepairer(String gbId, String propertyManagementFId, BigInteger repairTypeId, String repairerId, String repairerName, String repairerTel,
			MultipartFile headimgurl,String idNumber, MultipartFile idimgurl1, MultipartFile idimgurl2) {
		PropertyRepairer propertyRepairer = new PropertyRepairer();
		propertyRepairer.setId(repairerId == null ? uuidManager.getNextUuidBigInteger(SEQConstants.t_property_repairer) : new BigInteger(repairerId));
		propertyRepairer.setName(repairerName);
		propertyRepairer.setTel(repairerTel);
		propertyRepairer.settPropertyRepairTypeFId(repairTypeId);
		propertyRepairer.setSys0AddUser(UserContext.getCurrUser().getId());
		propertyRepairer.setIdNo(idNumber);

		try {
			String headimgFileName = CommonMultiFileUtil.uploadFile(headimgurl, OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH),
					PathConstants.RepairPicBasePath, "propertyRepairer", propertyRepairer.getId());
			propertyRepairer.setHeadimgurl(headimgFileName);
			
			String idImgFileName1 = CommonMultiFileUtil.uploadFile(idimgurl1, OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH),
					PathConstants.RepairPicBasePath, "propertyRepairerID1", propertyRepairer.getId());
			propertyRepairer.setIdNumberPic1(idImgFileName1);
			
			String idImgFileName2 = CommonMultiFileUtil.uploadFile(idimgurl2, OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH),
					PathConstants.RepairPicBasePath, "propertyRepairerID2", propertyRepairer.getId());
			propertyRepairer.setIdNumberPic2(idImgFileName2);
			
		} catch (IOException e) {
			logger.info("propertyRepairer.headImg.upload.failure", e);
			e.printStackTrace();
		}

		if (gbId != null) {
			BigInteger tPropertyManagementFId = groupBuildingBaseService.getGroupBuildingBySeqId(new BigInteger(gbId)).gettPropertyManagementFId();
			propertyRepairer.settPropertyManagementFId(tPropertyManagementFId);
		}
		if (propertyManagementFId != null) {
			propertyRepairer.settPropertyManagementFId(new BigInteger(propertyManagementFId));
		}

		if (repairerId == null) {
			propertyRepairerBaseService.insertPropertyRepairer(propertyRepairer);
		} else {
			propertyRepairerBaseService.updatePropertyRepairer(propertyRepairer);
		}
		
		createLoginNoAndDredgeWorker(propertyRepairer);
	}
	
	@Resource 
	DredgeWorkerBaseDao dredgeWorkerBaseDao;
	@Resource
	DredgeWorkerServiceAddressBlockBaseDao dredgeWorkerServiceAddressBlockBaseDao;
	@Resource
	DredgeWorkerHasDredgeTypeBaseDao dredgeWorkerHasDredgeTypeBaseDao;
	@Resource
	private IHttpClient simpleHttpClient;
	@Resource
	LoginNoBaseDao loginNoBaseDao;
	
	/**
	 * 创建登录账号以及上门维修师傅信息
	 * 
	 * @param propertyRepairer
	 *            工人信息
	 */
	private void createLoginNoAndDredgeWorker(PropertyRepairer propertyRepairer){
		//1 创建后台登录账号
		propertyRepairer = propertyRepairerBaseService.getPropertyRepairerBySeqId(propertyRepairer.getId());
		String tel = propertyRepairer.getTel();
		
		{//先判断该手机号在系统中是否已经注册，若有注册，直接发个短信提示即可
			if(propertyRepairer.gettUserFId() != null) {
				return;
			}
			
			LoginNo loginNoQry = new LoginNo();
			loginNoQry.setNo(tel);
			loginNoQry.setType(LoginDict.AccountType.MOBILE);
			loginNoQry.setSys0DelState(0);
			List<LoginNo> loginNoList = loginNoBaseDao.selectLoginNoByCondition(MapConverter.convertBean(loginNoQry), false);
			if(loginNoList.size()==1){
				propertyRepairer.settUserFId(loginNoList.get(0).gettUserFId());//绑定师傅账号
				propertyRepairerBaseService.updatePropertyRepairer(propertyRepairer);
				
				DredgeWorker dredgeWorker = new DredgeWorker();
				dredgeWorker.settUserFId(loginNoList.get(0).gettUserFId());
				List<DredgeWorker> dredgeWorkerList = dredgeWorkerBaseDao.selectDredgeWorkerByCondition(MapConverter.convertBean(dredgeWorker), false);
				if (dredgeWorkerList.size() == 1) {
					dredgeWorker.setId(dredgeWorkerList.get(0).getId());
					dredgeWorker.setAuditDesc("物业修改工人信息，师傅自动审核通过");
					dredgeWorker.setAuditStatus(DredgeConstant.DredgeWorker.DredgeWorker_Status_Accepted);
					dredgeWorker.setCreateType(DredgeConstant.DredgeWorker.DredgeWorker_CreateType_PropertyCreate);
					dredgeWorkerBaseDao.updateDredgeWorker(dredgeWorker);
				} else if(dredgeWorkerList.size() == 0) {
					createDredageWorkerInfo(propertyRepairer, loginNoList.get(0).gettUserFId());
				}else{
					logger.info("the user has more than one DredgeWorker.");
				}

//				String smsContent = "尊敬的师傅您好，物业已为您开通物业维修，可通过以下链接下载解放区师傅端http://app.jiefangqu.com/master/index.html，即可在解放区接单了，登录账号为" + tel + "，如需其他帮助，请联系400-960-2228。";
//				commMobileService.sendMsg(tel, smsContent); 
				ShortMsgUtil.sendMessage(tel, "create_drdg_worker", tel);
				return;
			}
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("regType", LoginDict.AccountType.MOBILE);
		params.put("mobile", tel);
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/login/regist4ms.json", params, prepareReqHeader());
		if(jsonResponse == null){
			logger.error("/login/regist4ms.json: no response");
			throw new FocRuntimeException();
		}else if(!"0000".equals(jsonResponse.getStatus())){
			logger.error("/login/regist4ms.json: status is not 0000");
			throw new FocRuntimeException();
		}
		
		String oldPwd = ((JSONObject)jsonResponse.getDataValue()).getString("oldPwd");
		BigInteger tUserFId = ((JSONObject)jsonResponse.getDataValue()).getBigInteger("tUserFId");
		
		propertyRepairer.settUserFId(tUserFId);
		propertyRepairerBaseService.updatePropertyRepairer(propertyRepairer);
		
		createDredageWorkerInfo(propertyRepairer, tUserFId);
		
		// 发送短信通知 

//		String smsContent = "尊敬的师傅您好，物业已为您开通解放区师傅端，可通过以下链接下载解放区师傅端http://app.jiefangqu.com/master/index.html，即可在解放区接单了，登录账号为%s，密码%s如需其他帮助，请联系400-960-2228。";
//		commMobileService.sendMsg(tel, String.format(smsContent, tel, oldPwd));
		ShortMsgUtil.sendMessage(tel, "create_drdg_account", tel, oldPwd);
	}
	
	/**
	 * 准备请求头
	 * 
	 * @return
	 */
	private List<Header> prepareReqHeader() {
		List<Header> appendHeaderList = new ArrayList<Header>();
		appendHeaderList.add(new BasicHeader("channelId", "1"));
		appendHeaderList.add(new BasicHeader("subChannelId", HeaderConstant.SubChannelId.Jfq_Master_App+""));
		appendHeaderList.add(new BasicHeader("deviceId", "fromJFQMS"));
		appendHeaderList.add(new BasicHeader("version", "1.0.0"));
		return appendHeaderList;
	}
	

	/**
	 * 创建师傅信息
	 * 
	 * @param propertyRepairer
	 *            物业师傅
	 * @param loginNo
	 *            后台登录账号
	 */
	private void createDredageWorkerInfo(PropertyRepairer propertyRepairer, BigInteger tUserId) {
		DredgeWorker dw = new DredgeWorker();
		dw.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_worker));
		dw.settUserFId(tUserId);
		dw.setIdNo(propertyRepairer.getIdNo());
//		dw.setIdNumberPic1(propertyRepairer.getIdNumberPic1());
//		dw.setIdNumberPic2(propertyRepairer.getIdNumberPic2());
		dw.setRealName(propertyRepairer.getName());
		dw.setAuditStatus(DredgeConstant.DredgeWorker.DredgeWorker_Status_Accepted);
		dw.setCreateType(DredgeConstant.DredgeWorker.DredgeWorker_CreateType_PropertyCreate);
		dw.setWorkerLevel(DredgeConstant.DredgeWorkerLevel.Low_Level);
		dredgeWorkerBaseDao.insertDredgeWorker(dw);
		
		// 设置师傅的接单配置 
		
		GroupBuilding gbQry = new GroupBuilding();
		gbQry.settPropertyManagementFId(propertyRepairer.gettPropertyManagementFId());
		gbQry.setSys0DelState(0);
		List<GroupBuilding> gbList = groupBuildingBaseService.getGroupBuildingByCondition(MapConverter.toMap(gbQry));
		
		if(gbList.size() > 0 ){
			Set<BigInteger> abIdSet = new HashSet<BigInteger>();
			List<BigInteger> idList = uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_worker_service_address_block, gbList.size());
			for(int i = 0; i < gbList.size(); i++){
				if(abIdSet.add(gbList.get(i).gettBlockFId())){//如果该行政区没有被设置
					DredgeWorkerServiceAddressBlock dredgeWorkerServiceAddressBlock = new DredgeWorkerServiceAddressBlock();
					dredgeWorkerServiceAddressBlock.setId(idList.get(i));
					dredgeWorkerServiceAddressBlock.settUserFId(tUserId);
					dredgeWorkerServiceAddressBlock.settAddressBlockFId(gbList.get(i).gettBlockFId());
					dredgeWorkerServiceAddressBlockBaseDao.insertDredgeWorkerServiceAddressBlock(dredgeWorkerServiceAddressBlock);
				}
			}
		}
	}

	@Override
	public List<PropertyRepairTypeEntity> listRepairerTypeByOmsUser(Map<String, Object> paramMap) {
		return propertyRepairDao.listRepairerTypeByOmsUser(paramMap);
	}

	@Override
	public List<PropertyManagement> listPropertyManagementByOmsUser(OmsUser currUser) {
		return propertyRepairDao.listPropertyManagementByOmsUser(currUser);
	}

	@Override
	public int listRepairerTypeByOmsUser_forCount(Map<String, Object> paramMap) {
		return propertyRepairDao.listRepairerTypeByOmsUser_forCount(paramMap);
	}

	@Override
	public int listRepairer_forCount(Map<String, Object> paramMap) {
		return propertyRepairDao.listRepairer_forCount(paramMap);
	}

	@Override
	public List<PropertyRepairerEntity> listRepairer(Map<String, Object> paramMap) {
		return propertyRepairDao.listRepairer(paramMap);
	}

	@Override
	public int listRepair_forCount(Map<String, Object> paramMap) {
		return propertyRepairDao.listRepair_forCount(paramMap);
	}

	@Override
	public List<PropertyRepairEntity> listRepair(Map<String, Object> paramMap) {
		return propertyRepairDao.listRepair(paramMap);
	}

	@Override
	public PropertyRepairEntity select_PropertyRepair_byId(String id) {
		return propertyRepairDao.select_PropertyRepair_byId(id);
	}
	
	@Resource
	UserBaseDao userBaseDao;
	
	@Resource
	IMessageBaseService messageBaseService;
	
	@Resource
	IMessageParameterBaseService messageParameterBaseService;
	
	@Resource
	IUserHasTMessageBaseDao userHasTMessageBaseDao;

	@Override
	public void assignPropertyRepair(PropertyRepair propertyRepair) {
		int updCount = super.updatePropertyRepair(propertyRepair);
		if (updCount <= 0) {
			throw new BusinessRuntimeException("PropertyRepairService.assignPropertyRepair.failed");
		}

		try {
			sendSMSToRepairerAfterAssigned(propertyRepair);
			sendSMSToUser(propertyRepair, "dredge_on_way");

			propertyRepair = propertyRepairDao.selectPropertyRepairBySeqId(propertyRepair.getId());
			PropertyRepairer propertyRepairer = propertyRepairerBaseService.getPropertyRepairerBySeqId(propertyRepair.gettPropertyRepairerFId());
			if(propertyRepairer.gettUserFId() != null){
				PropertyRepairType propertyRepairType = propertyRepairTypeBaseDao.selectPropertyRepairTypeBySeqId(propertyRepair.gettPropertyRepairTypeFId());
				String title = "管理处已为您安排师傅进行维修咯";
				String content = "管理处已为您安排师傅进行维修, 订单类型：" + propertyRepairType.getName() + "，订单地址：" + propertyRepair.getAddress();
				String pushId = MsgpushDict.PushId.PropertyRepairBill_Assigned;
				BigInteger userId = propertyRepair.gettUserFId();
				addPushMessage(title, content, pushId, userId, NoticeDict.Message_Type.Property_Repair_APP);
			}
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}

		//产生首页消息
		UserHasHomeMessage message = new UserHasHomeMessage();
		message.setMessageCode(HomeMessageDict.MessageCode.PROPERTY_REPAIR_ALERT);
		message.setResouceId(propertyRepair.getId());
		homeMessageService.generateCommonMsg(message);
	}

	/**
	 * 添加推送消息 
	 * @param propertyRepair 物业报修单
	 */
	public void addPushMessage(String title, String content, String pushId, BigInteger desUserId, Long msgType) {

		Message msg = new Message();
		msg.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message));
		msg.setTitle(title);
		msg.setContent(content);
		msg.setTime(DateUtils.getCurrentDate());
		msg.setType(msgType);
		
		User user = userBaseDao.selectUserBySeqId(desUserId);
		CommUserDataEntity commUserData = new CommUserDataEntity(desUserId, LoginDict.UserRegistOrTmp.REGIST_USER, user.getDefaultRoomId());
		
		List<MessageParameter> mps = new ArrayList<MessageParameter>();
		MessageParameter tmpMessageParameter = new MessageParameter();
		tmpMessageParameter.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));
		tmpMessageParameter.settMessageFId(msg.getId());
		tmpMessageParameter.setKey("pushId");
		tmpMessageParameter.setValue(pushId);
		
		mps.add(tmpMessageParameter);
		// 超时时间是第二天0点
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, 1);

		messageBaseService.insertMessage(msg);
		
		if (mps.size() > 0) {
			messageParameterBaseService.insertMessageParameterBatch(mps);
		}
		
		BigInteger userId = commUserData.getUserId();
		Integer userType = commUserData.getUserType();
		BigInteger defaultRoomId = commUserData.getDefaultRoomId();
		BigInteger toAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_message);
		UserHasTMessage tmpUserHasTMessage = new UserHasTMessage();
		tmpUserHasTMessage.setId(toAddId);
		tmpUserHasTMessage.setTryFailedCount(0L);
		tmpUserHasTMessage.setSendStatus(NoticeDict.Message_SendStatus.UnDo);
		tmpUserHasTMessage.setStatus(NoticeDict.Message_ReadStatus.NotRead);
		tmpUserHasTMessage.settMessageFId(msg.getId());
		tmpUserHasTMessage.settUserFId(userId);
		tmpUserHasTMessage.setUserType(userType);
		tmpUserHasTMessage.setExpiryTime(DateUtil.formatDay.get().format(c.getTime()));
		tmpUserHasTMessage.setUserRoomId(defaultRoomId);
		userHasTMessageBaseDao.insertUserHasTMessage(tmpUserHasTMessage);
	}
	
	/**
	 * 分配成功后发送短信通知维修工
	 */
	private void sendSMSToRepairerAfterAssigned(PropertyRepair propertyRepair) {
		//		String msg = "【小区名+门牌号】提交报修单，报修类型：【报修类型】，业主联系电话：【联系电话】，请在【预计开始上门时间】至【预计结束上门时间】内上门。";
//		String msg = "{0}提交报修单，报修类型：{1}，业主联系电话：{2}";
		propertyRepair = propertyRepairDao.selectPropertyRepairBySeqId(propertyRepair.getId());
		PropertyRepairType propertyRepairType =propertyRepairTypeBaseDao.selectPropertyRepairTypeBySeqId(propertyRepair.gettPropertyRepairTypeFId());
		PropertyRepairer propertyRepairer = propertyRepairerBaseService.getPropertyRepairerBySeqId(propertyRepair.gettPropertyRepairerFId());
//		String msgContent = MessageFormat.format(msg, propertyRepair.getAddress(), propertyRepairType.getName(), propertyRepair.getTel());
		
		List<Object> args = new ArrayList<Object>();
		args.add(propertyRepair.getAddress());
		args.add(propertyRepairType.getName());
		args.add(propertyRepair.getTel());
		if(propertyRepair.getEstimateDoorTimeBegin() != null && propertyRepair.getEstimateDoorTimeEnd() != null) {
			args.add(propertyRepair.getEstimateDoorTimeBegin());
			args.add(propertyRepair.getEstimateDoorTimeEnd());
			//{1}提交报修单，报修类型：{2}，业主联系电话：{3}，请在{4}至{5}内上门。
			ShortMsgUtil.sendMessage(propertyRepairer.getTel(), "order_dredge2", args);
		} else {
			//{1}提交报修单，报修类型：{2}，业主联系电话：{3}
			ShortMsgUtil.sendMessage(propertyRepairer.getTel(), "order_dredge1", args);
		}

//		String msgTime = "，请在{0}至{1}内上门。";
//		if (propertyRepair.getEstimateDoorTimeBegin() != null && propertyRepair.getEstimateDoorTimeEnd() != null) {
//			msgContent += MessageFormat.format(msgTime, propertyRepair.getEstimateDoorTimeBegin(), propertyRepair.getEstimateDoorTimeEnd());
//		}
//		commMobileService.sendMsg(propertyRepairer.getTel(), msgContent);
	}

	/**
	 * 发短信通知业主
	 * 
	 * @param propertyRepair
	 */
	private void sendSMSToUser(PropertyRepair propertyRepair, String templateKey) {
		propertyRepair = propertyRepairDao.selectPropertyRepairBySeqId(propertyRepair.getId());//这样才能取到业主电话
//		commMobileService.sendMsg(propertyRepair.getTel(), msg);
		ShortMsgUtil.sendMessage(propertyRepair.getTel(), templateKey, "");
	}

	@Override
	public void closePropertyRepair(PropertyRepair propertyRepair) {
		int updCount = super.updatePropertyRepair(propertyRepair);
		if (updCount <= 0) {
			throw new BusinessRuntimeException("PropertyRepairService.assignPropertyRepair.failed");
		}
		sendSMSToUser(propertyRepair, "dredge_close");
		//没分配师傅时关闭，发推送消息
		propertyRepair = propertyRepairDao.selectPropertyRepairBySeqId(propertyRepair.getId());
		if (propertyRepair.gettPropertyRepairerFId() == null) {
			String title = "物业订单关闭";
			String content = "非常抱歉，您的物业订单因不在物业服务范围内，已被物业管理处关闭，点击查看详情。";
			String pushId = MsgpushDict.PushId.PropertyRepairBill_Closed;
			BigInteger userId = propertyRepair.gettUserFId();
			this.addPushMessage(title, content, pushId, userId, NoticeDict.Message_Type.Property_Repair_Closed);
		}
		
		try {//添加红点
			List<BasicSourceEntity> sourceList = new ArrayList<BasicSourceEntity>();
			sourceList.add(new BasicSourceEntity(RedpointDict.Redpoint_Content_SourceType.Dredage, propertyRepair.getId(), "UPDATE"));
			if(propertyRepair.gettPropertyRepairerFId() == null){//被关闭订单, 有文字红点
				addRedpointContent(propertyRepair.gettUserFId(), LoginDict.UserRegistOrTmp.REGIST_USER, propertyRepair.gettRoomFId(),
						RedpointConstant.RedpointContent_ModelCode.PROPERTYREPAIRBILL_HASCLOSEED, sourceList);
			}else{//有师傅的话，算是正常关闭（完成）
			}
			addRedpointContent(propertyRepair.gettUserFId(), LoginDict.UserRegistOrTmp.REGIST_USER, propertyRepair.gettRoomFId(),
					RedpointConstant.RedpointContent_ModelCode.DREDGEBILL_HASFINISHED, sourceList);
		}catch (Exception e){
			logger.info(e.getMessage(), e);
		}

		//产生首页消息
		UserHasHomeMessage message = new UserHasHomeMessage();
		message.setMessageCode(HomeMessageDict.MessageCode.PROPERTY_REPAIR_ALERT);
		message.setResouceId(propertyRepair.getId());
		homeMessageService.generateCommonMsg(message);
	}
	
	private void addRedpointContent(BigInteger userId, Integer userType, BigInteger currRoomId,
			String modelCode, List<BasicSourceEntity> sourceList) {
		String nowTime = dualDao.getNowTime();
		RedpointContentEntity existContent = redpointDao.selectRedpointContentByModelCode(userId,userType,currRoomId,modelCode);
		BigInteger contentId = null;
		if(existContent==null){//新增
			BigInteger toAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_redpoint_content);
			RedpointContent redpointContent = new RedpointContent();
			redpointContent.setId(toAddId);
			redpointContent.setClickStatus(RedpointDict.RedpointContent_clickStatus.NOT_CLICK);//未点
			redpointContent.setLastClickTime(null);//点击时间为空
			redpointContent.setLastModifyTime(nowTime);
			redpointContent.setModelCode(modelCode);
			redpointContent.setRoomId(currRoomId);
			redpointContent.setUserId(userId);
			redpointContent.setUserType(userType);
			Integer resCount = redpointContentBaseDao.insertRedpointContent(redpointContent);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("RedpointService.addRedpointContent.insertRedpointContent.failed");
			}
			contentId = toAddId;
		}else{//更新
			contentId = existContent.getId();
			RedpointContent toUpdateContent = new RedpointContent();
			toUpdateContent.setId(contentId);
			toUpdateContent.setClickStatus(RedpointDict.RedpointContent_clickStatus.NOT_CLICK);//未点
			toUpdateContent.setLastModifyTime(nowTime);
			Integer resCount = redpointContentBaseDao.updateRedpointContent(toUpdateContent);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("RedpointService.addRedpointContent.updateRedpointContent.failed");
			}
		}
		if(sourceList!=null&&sourceList.size()>0){
			List<BigInteger> toAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_redpoint_content_source, sourceList.size());
			List<RedpointContentSource> toAddSourceList = new ArrayList<RedpointContentSource>();
			for(int i=0;i<sourceList.size();i++){
				BasicSourceEntity tmpSource = sourceList.get(i);
				BigInteger toAddId = toAddIdList.get(i);
				BigInteger sourceId = tmpSource.getSourceId();
				Integer sourceType = tmpSource.getSourceType();
				String operationType = tmpSource.getOperationType();
				RedpointContentSource toAddSource = new RedpointContentSource();
				toAddSource.setId(toAddId);
				toAddSource.setLastModifyTime(nowTime);
				toAddSource.setSourceId(sourceId);
				toAddSource.setSourceType(sourceType);
				toAddSource.settRedpointContentFId(contentId);
				toAddSource.setOperationType(operationType);
				toAddSourceList.add(toAddSource);
			}
			Integer resCount = redpointContentSourceBaseDao.insertRedpointContentSourceBatch(toAddSourceList);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("RedpointService.addRedpointContent.insertRedpointContentSourceBatch.failed");
			}
		}
		redpointDao.freshRedpointContentClickStatus(userId, userType,currRoomId, modelCode);//刷新同步红点状态信息
	}

	@Override
	public double getStarLevelForRepairer(BigInteger id) {
		return propertyRepairDao.getStarLevelForRepairer(id);
	}

	@Override
	public List<PropertyRepairTypeEntity> getPropertyRepairTypeEntityListByPMId(BigInteger pmId) {
		return propertyRepairDao.getPropertyRepairTypeEntityListByPMId(pmId);
	}
	
	/**
	 * 转换流程记录，供app端使用
	 * @param prList
	 * @return
	 */
	@Override
	public List<ProcessRecord> getProcessRecordList(List<DredgeBillHasProcessRecording> prList){
		List<ProcessRecord> prMapList = new ArrayList<ProcessRecord>();
		for(int i = 0; i < prList.size(); i++){
			ProcessRecord pr = new ProcessRecord();
			DredgeBillHasProcessRecording dredgeBillHasProcessRecording = prList.get(i);
			pr.setId(dredgeBillHasProcessRecording.getId());
			pr.setPrDesc(dredgeBillHasProcessRecording.getProcessDesc());
			pr.setPrAddTimeStr(dredgeBillHasProcessRecording.getRecordingTime());
			pr.setPrAddTime(DateUtils.convertStrToDate(dredgeBillHasProcessRecording.getRecordingTime(), "yyyy-MM-dd HH:mm:ss").getTime());
			if (!StringUtils.isEmpty(dredgeBillHasProcessRecording.getProcessPics())) {
				String[] pics = dredgeBillHasProcessRecording.getProcessPics().split(";");
				List<String> picList = new ArrayList<String>();
				String relativePath = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.DredgePicBasePath);//图片在维修那边
				for (int j = 0; j < pics.length; j++) {
					picList.add(fileServerService.getAccessUrl(relativePath + pics[j], dredgeBillHasProcessRecording));
				}
				pr.setPicList(picList);
			}
			prMapList.add(pr);
		}
		return prMapList;
	}

	@Override
	public int qryIsOpenRepaireServiceByRoomId(BigInteger roomId) {
		return propertyRepairDao.qryIsOpenRepaireServiceByRoomId(roomId);
	}

	@Override
	public List<PropertyRepairTypeEntity> select_prTypeList_by_roomId(BigInteger roomId) {
		return propertyRepairDao.select_prTypeList_by_roomId(roomId);
	}

	@Override
	public List<DredgeTypeEntity> getDredgeTypeByCondition(Map<String, Object> map) {
		return propertyRepairDao.getDredgeTypeByCondition(map);
	}
	
}
