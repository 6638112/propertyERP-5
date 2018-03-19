package com.cnfantasia.server.api.userQuestion.service;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.homeMessage.constant.HomeMessageDict;
import com.cnfantasia.server.api.homeMessage.service.HomeMessageService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.msgpush.constant.MsgToAddBasic;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.msgpush.service.IPushAddService;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.userQuestion.constant.UserQuestionDict;
import com.cnfantasia.server.api.userQuestion.dao.UserQuestionDao;
import com.cnfantasia.server.api.userQuestion.entity.UserQuestion4Admin;
import com.cnfantasia.server.api.userQuestion.entity.UserQuestionDetail4Admin;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.common.utils.UUIDGenerater;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.omsCommSysPara.dao.IOmsCommSysParaBaseDao;
import com.cnfantasia.server.domainbase.propertyDistrict.entity.PropertyDistrict;
import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;
import com.cnfantasia.server.domainbase.userQuestion.dao.UserQuestionBaseDao;
import com.cnfantasia.server.domainbase.userQuestion.entity.UserQuestion;
import com.cnfantasia.server.domainbase.userQuestion.service.IUserQuestionBaseService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.propertySoftwareDock.base.entity.MailSendThread;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: UserQuestionService
 * @Date: 2017-02-23 14:32
 * @Auther: kangduo
 * @Description: ()
 */
public class UserQuestionService implements IUserQuestionService {
    private Log logger = LogFactory.getLog(getClass());

    private IUserQuestionBaseService userQuestionBaseService;
    public void setUserQuestionBaseService(IUserQuestionBaseService userQuestionBaseService) {
        this.userQuestionBaseService = userQuestionBaseService;
    }
    
    @Resource
    private UserQuestionDao userQuestionDao;

    @Resource
    private UserQuestionBaseDao userQuestionBaseDao;
    
    @Resource
    private IPushAddService pushAddService;

    private IUuidManager uuidManager;
    public void setUuidManager(IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }

    private ISysParamManager sysParamManager;
    public void setSysParamManager(ISysParamManager sysParamManager) {
        this.sysParamManager = sysParamManager;
    }

    private IFileServerService fileServerService;
    public void setFileServerService(IFileServerService fileServerService) {
        this.fileServerService = fileServerService;
    }

    @Resource
    private HomeMessageService homeMessageService;

    @Override
    public Integer addUserQuestion(UserQuestion uq, List<RequestFileEntity> picInfoList) {
        BigInteger id = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_question);
        uq.setId(id);
        String picNames = uploadDredgePic(picInfoList);
        uq.setPicUrl(picNames);
        int count = userQuestionBaseService.insertUserQuestion(uq);
        if (count > 0) {
            //发邮件通知片区负责人
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("sysParaCode", OmsSysParamKey.EmailSwitch);
            IOmsCommSysParaBaseDao omsCommSysParaBaseDao =(IOmsCommSysParaBaseDao) CnfantasiaCommbusiUtil.getBeanManager("omsCommSysParaBaseDao");
            String emailSwitch = omsCommSysParaBaseDao.selectOmsCommSysParaByCondition(paramMap, false).get(0).getSysParaValue();
            if (!"0".equals(emailSwitch)) {
                BigInteger roomId = uq.gettRoomFId();
                PropertyDistrict pd = userQuestionDao.getPropertyDistrictByRoomId(roomId);
                String notifyEmails = "";
                if (pd != null && !DataUtil.isEmpty(pd.getDirectorEmail())) {
                    notifyEmails = pd.getDirectorEmail();
                }
                //统一发的人
                String notifyMails = sysParamManager.getSysParaValue(SysParamKey.UserQeustion_NotifyMail);
                if (!DataUtil.isEmpty(notifyMails)) {
                    notifyEmails = notifyMails + "," + notifyEmails;
                }
                if (!"".equals(notifyEmails)) {
                    String detailAddr = userQuestionDao.getRoomDetailAddress(roomId);
                    String mailContent = "您当前所负责小区有业主发布寻求帮忙。" +
                            "<br>地址：" + detailAddr +
                            "<br>内容：" + uq.getContent() +
                            "<br>用户解放号：" + uq.getUserId();
                    new MailSendThread("有新的业主发布寻求帮忙", mailContent, notifyEmails).start();
                }
            }
        }
        return count;
    }

    /**
     * 保存图片信息
     */
    private String uploadDredgePic(List<RequestFileEntity> picList) {
        StringBuilder picUrl = new StringBuilder();

        if (picList != null && picList.size() > 0) {
            String picbaseDredge = sysParamManager.getSysParaValue(SysParamKey.DredgePicBasePath);
            String picbaseRepair = sysParamManager.getSysParaValue(SysParamKey.Repair_Pic_BasePath);
            for (RequestFileEntity requestFile : picList) {//上传图片
                String resFileName = UUIDGenerater.getFileName() + "." + requestFile.getFileType();
                picUrl.append(resFileName).append(";");
                String serverPathDredge = picbaseDredge + resFileName;
                String serverPathRepair = picbaseRepair + resFileName;
                try {
                    long startTime = System.currentTimeMillis();
                    //同时传到维修和公共维修目录，转单时可以直接用这个图
                    fileServerService.uploadFile(serverPathDredge, requestFile.getFileContent());
                    fileServerService.uploadFile(serverPathRepair, requestFile.getFileContent());
                    logger.info("uploadFile use time: " + (System.currentTimeMillis() - startTime));
                } catch (IOException e) {
                    logger.info("uploadUserQuestionPic upload cause exception:" + e.getMessage(), e);
                    throw new BusinessRuntimeException("UserQuestionService.uploadRepairPic.uploadFile.error", new Object[]{requestFile.getFileName()});
                }
            }
        }

        return picUrl.toString();
    }

	/**
	 * 运营后台管理员查看帮帮看列表
	 * @param paramMap
	 * @return
	 */
	public List<UserQuestion4Admin> getUserQuestionList(Map<String, Object> paramMap) {
		return userQuestionDao.getUserQuestionList(paramMap);
	}

	public int getUserQuestionCount(Map<String, Object> paramMap) {
		return userQuestionDao.getUserQuestionCount(paramMap);
	}

	public UserQuestionDetail4Admin getUserQuestionDetailDetail(BigInteger uqId) {
		return userQuestionDao.getUserQuestionDetailDetail(uqId);
	}

	/**
	 * 转单后的处理
	 * @param uqId 寻求帮忙ID
	 * @param dredgeBillId 上门服务单的Id
	 * @param status 即将变为的状态值
	 */
	public void afterConvertBill(String userId, BigInteger uqId, Integer dredgeBillId, int status) {
		UserQuestion userQuestion = new UserQuestion();
		userQuestion.setId(uqId);
		userQuestion.setServiceId(BigInteger.valueOf(dredgeBillId));
		userQuestion.setStatus(status);
        if(status == UserQuestionDict.QuestionStatus.TURN_DREDGE){
            userQuestion.setAnswerContent("正在为您安排到家师傅");
        } else if (status == UserQuestionDict.QuestionStatus.TURN_DREDGE_REPAIR) {
            userQuestion.setAnswerContent("正在为您安排物业师傅");
        }
		userQuestionBaseDao.updateUserQuestion(userQuestion);
		
		pushMsgToUser(uqId, status);
	}
	
	/**
	 * 回复用户
	 * @param replyType
	 * @param userId
	 * @param uqId
	 * @param replyContent
	 */
	public void replyUser(int replyType, String userId, BigInteger uqId, String replyContent) {
		UserQuestion userQuestion = new UserQuestion();
		userQuestion.setId(uqId);
		userQuestion.setStatus(replyType);
		userQuestion.setAnswerContent(replyContent);
		userQuestionBaseDao.updateUserQuestion(userQuestion);
		
		pushMsgToUser(uqId, replyType);
	}

	/**
	 * 发送消息给用户
	 * @param uqId 
	 * @param status
	 */
	private void pushMsgToUser(BigInteger uqId, int status) {
		UserQuestion uq = userQuestionBaseDao.selectUserQuestionBySeqId(uqId);
		CommUserDataEntity user = new CommUserDataEntity(uq.getUserId(), LoginDict.UserRegistOrTmp.REGIST_USER, uq.gettRoomFId());
		List<CommUserDataEntity> userList = new ArrayList<CommUserDataEntity>();
		userList.add(user);
		
        MsgToAddBasic basic = new MsgToAddBasic();
        
        basic.setiOSMsgType(NoticeDict.Message_Type.NoticeMessage); 
        basic.setAndroidPushId(MsgpushDict.PushId.NoticeMessage);
        basic.setIsReleatRoom(MsgpushDict.PushReleatRoom.TRUE);

        basic.setTitle("您的管家有新回复");
        if(status == UserQuestionDict.QuestionStatus.TURN_DREDGE){
        	basic.setContent("您的管家已处理，正在为您安排到家师傅");
        }else if(status == UserQuestionDict.QuestionStatus.TURN_DREDGE_REPAIR){
        	basic.setContent("您的管家已处理，正在为您安排物业师傅");
        }else if(status == UserQuestionDict.QuestionStatus.SYS_REPLY){
        	basic.setContent("回复内容：" + uq.getAnswerContent());
        }else if(status == UserQuestionDict.QuestionStatus.PROPERTY_REPLY){
        	basic.setContent("回复内容：" + uq.getAnswerContent());
        }
		pushAddService.addMessage2Pool(uqId, userList, basic);

        //添加寻求寻求帮助首页消息
        UserHasHomeMessage message = new UserHasHomeMessage();
        message.setMessageCode(HomeMessageDict.MessageCode.ASK_FOR_HELP_MESSAGE);
        message.setResouceId(uqId);
        message.setContent("您的管家已回复");
        if(status == UserQuestionDict.QuestionStatus.TURN_DREDGE){
            message.setExtInfo("正在为您安排到家师傅");
        } else if (status == UserQuestionDict.QuestionStatus.TURN_DREDGE_REPAIR) {
            message.setExtInfo("正在为您安排物业师傅");
        } else {
            message.setExtInfo(StringUtils.subStr(uq.getAnswerContent(), 10, "..."));
        }

        message.setUserId(uq.getUserId());
        message.settRoomFId(uq.gettRoomFId());
        homeMessageService.generateCommonMsg(message);
	}
}
