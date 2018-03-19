/**   
* Filename:    FamilyMsgService.java   
* @version:    1.0  
* Create at:   2015年3月12日 上午9:31:20   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.familyMsg.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.FutureTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.comments.entity.GoalIdCommentsEntity;
import com.cnfantasia.server.api.comments.service.ICommentsService;
import com.cnfantasia.server.api.commonBusiness.callable.UploadSmallPicRunnable;
import com.cnfantasia.server.api.commonBusiness.constant.CommonModuleDict;
import com.cnfantasia.server.api.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.commonBusiness.entity.UserRoomApplyEntity;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonUserService;
import com.cnfantasia.server.api.commonBusiness.service.ISmallPicUploadService;
import com.cnfantasia.server.api.familyMsg.constant.FamilyMsgDict;
import com.cnfantasia.server.api.familyMsg.dao.IFamilyMsgDao;
import com.cnfantasia.server.api.familyMsg.entity.FamilyMsgEntity;
import com.cnfantasia.server.api.familyMsg.entity.MsgExtDataAdd;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.msgpush.service.IMsgpushService;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.redpoint.callable.RedpointAddRunnableForFamily;
import com.cnfantasia.server.api.redpoint.constant.RedpointConstant;
import com.cnfantasia.server.api.redpoint.constant.RedpointDict;
import com.cnfantasia.server.api.redpoint.entity.BasicSourceEntity;
import com.cnfantasia.server.api.redpoint.service.IRedpointService;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.RandomUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.familyMsg.dao.IFamilyMsgBaseDao;
import com.cnfantasia.server.domainbase.familyMsg.entity.FamilyMsg;
import com.cnfantasia.server.domainbase.familyMsgExtData.dao.IFamilyMsgExtDataBaseDao;
import com.cnfantasia.server.domainbase.familyMsgExtData.entity.FamilyMsgExtData;
import com.cnfantasia.server.domainbase.familyMsgHasTUser.dao.IFamilyMsgHasTUserBaseDao;
import com.cnfantasia.server.domainbase.familyMsgHasTUser.entity.FamilyMsgHasTUser;
import com.cnfantasia.server.domainbase.familyMsgPic.dao.IFamilyMsgPicBaseDao;
import com.cnfantasia.server.domainbase.familyMsgPic.entity.FamilyMsgPic;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.room.entity.Room;

/**
 * Filename:    FamilyMsgService.java
 * @version:    1.0.0
 * Create at:   2015年3月12日 上午9:31:20
 * Description:家庭留言板服务类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月12日       shiyl             1.0             1.0 Version
 */
public class FamilyMsgService implements IFamilyMsgService{
	private Log logger = LogFactory.getLog(getClass());

    private IMsgpushService msgpushService;

    public void setMsgpushService(IMsgpushService msgpushService) {
        this.msgpushService = msgpushService;
    }
    
	private IFamilyMsgDao familyMsgDao;
	public void setFamilyMsgDao(IFamilyMsgDao familyMsgDao) {
		this.familyMsgDao = familyMsgDao;
	}
	
	private ICommonUserService commonUserService;
	public void setCommonUserService(ICommonUserService commonUserService) {
		this.commonUserService = commonUserService;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IFamilyMsgExtDataBaseDao familyMsgExtDataBaseDao;
	public void setFamilyMsgExtDataBaseDao(IFamilyMsgExtDataBaseDao familyMsgExtDataBaseDao) {
		this.familyMsgExtDataBaseDao = familyMsgExtDataBaseDao;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private IFamilyMsgBaseDao familyMsgBaseDao;
	public void setFamilyMsgBaseDao(IFamilyMsgBaseDao familyMsgBaseDao) {
		this.familyMsgBaseDao = familyMsgBaseDao;
	}
	
	private IFamilyMsgHasTUserBaseDao familyMsgHasTUserBaseDao;
	public void setFamilyMsgHasTUserBaseDao(IFamilyMsgHasTUserBaseDao familyMsgHasTUserBaseDao) {
		this.familyMsgHasTUserBaseDao = familyMsgHasTUserBaseDao;
	}
	
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	private IFamilyMsgPicBaseDao familyMsgPicBaseDao;
	public void setFamilyMsgPicBaseDao(IFamilyMsgPicBaseDao familyMsgPicBaseDao) {
		this.familyMsgPicBaseDao = familyMsgPicBaseDao;
	}
	
	private ISmallPicUploadService smallPicUploadService;
	public void setSmallPicUploadService(ISmallPicUploadService smallPicUploadService) {
		this.smallPicUploadService = smallPicUploadService;
	}
	
	private ICommentsService commentsService;
	public void setCommentsService(ICommentsService commentsService) {
		this.commentsService = commentsService;
	}
	
	private IRedpointService redpointService;
	public void setRedpointService(IRedpointService redpointService) {
		this.redpointService = redpointService;
	}

	@Override
	public void addMsg(BigInteger userId, String content, Integer extDataType,
			Set<BigInteger> userIdList,List<MsgExtDataAdd> msgExtDataAddList, List<RequestFileEntity> picList) {
		List<UserSimpleEntity> toNotifyUserList = null;
		List<UserSimpleEntity> familyAllUserList = commonUserService.getFamilyMembersWithoutSelf(userId, false);
		if(userIdList==null){//userIdList为空则获取全部,否则校验是否为当前家庭的成员信息
//			toNotifyUserList = familyAllUserList;
		}else{
			if(familyAllUserList!=null&&familyAllUserList.size() > 0){
				toNotifyUserList = new ArrayList<UserSimpleEntity>();
				for(UserSimpleEntity tmpFmlUser:familyAllUserList){
					if(userIdList.contains(tmpFmlUser.getId())){
						toNotifyUserList.add(tmpFmlUser);
					}
				}
			}
		}
		//进行新增操作
		{
			//新增留言
			BigInteger familyMsgAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_family_msg);
			{
				String createTime = dualDao.getNowTime();
				Room defaultRoom = commonRoomService.getDefaultRoomByUserId(userId);
				BigInteger realRoomId = defaultRoom.gettRealRoomFId();
				BigInteger roomId = defaultRoom.getId();
				FamilyMsg familyMsgAdd = new FamilyMsg();
				familyMsgAdd.setContent(content);
				familyMsgAdd.setCreateTime(createTime);
				familyMsgAdd.setId(familyMsgAddId);
				familyMsgAdd.setRealRoomId(realRoomId);
				familyMsgAdd.setRoomId(roomId);
				familyMsgAdd.setUserId(userId);
				familyMsgBaseDao.insertFamilyMsg(familyMsgAdd);
			}
			//详细图片信息
			if(picList!=null&&picList.size()>0){//保存图片信息
				String fmyMsgListPicBasePath = sysParamManager.getSysParaValue(SysParamKey.FAMILY_MSG_LIST_PIC_BASEPATH);
				List<String> picUrlList = new ArrayList<String>();
				for(int i=0;i<picList.size();i++){//上传图片
					RequestFileEntity requestFile = picList.get(i);
					//生成文件名 userId+time+index+两位随机数
					String resFileName = new StringBuffer().append(userId).append(DateUtil.getCurrSysTimeStr())
							.append("_").append(i+1).append("_").append(RandomUtils.createRandom(true, 3))
							.append(".").append(requestFile.getFileType()).toString();
					picUrlList.add(resFileName);
					String serverPath = fmyMsgListPicBasePath+resFileName;
					try {
						fileServerService.uploadFile(serverPath, requestFile.getFileContent());
					} catch (IOException e) {
						logger.info("upload familyMsgListPic file cause exception:"+e.getMessage(), e);
						throw new BusinessRuntimeException("FamilyMsgService.addMsg.uploadFile.error",new Object[]{requestFile.getFileName()});
					}
					//增加生成小图的任务处理 
					FutureTask<Boolean> task = new FutureTask<Boolean>(new UploadSmallPicRunnable(smallPicUploadService,BusinessModelType.FamilyMsg,fileServerService.getFileServierUploadBasePath(),fmyMsgListPicBasePath, resFileName, requestFile.getFileContent()));
					new Thread(task).start();
				}
				List<FamilyMsgPic> familyMsgPicAddList = new ArrayList<FamilyMsgPic>();
				List<BigInteger> picAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_family_msg_pic,picUrlList.size());
				for(int i=0;i<picUrlList.size();i++){
					String picUrl = picUrlList.get(i);
					BigInteger picAddId = picAddIdList.get(i);
					FamilyMsgPic familyMsgPic = new FamilyMsgPic();
					familyMsgPic.setDesc(null);
					familyMsgPic.setId(picAddId);
					familyMsgPic.setName(null);
					familyMsgPic.settFamilyMsgFId(familyMsgAddId);
					familyMsgPic.setUploader(userId);
					familyMsgPic.setUrl(picUrl);
					familyMsgPicAddList.add(familyMsgPic);
				}
				int res = familyMsgPicBaseDao.insertFamilyMsgPicBatch(familyMsgPicAddList);
				if(res!=familyMsgPicAddList.size()){
					throw new BusinessRuntimeException("FamilyMsgService.addMsg.insertFamilyMsgPicBatch.failed");
				}
			}
			//新增@用户列表
			if(toNotifyUserList!=null&&toNotifyUserList.size()>0){
				List<FamilyMsgHasTUser> familyMsgHasTUserAddList = new ArrayList<FamilyMsgHasTUser>();
				List<BigInteger> addIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_family_msg_has_t_user, toNotifyUserList.size());
				for(int i=0;i<toNotifyUserList.size();i++){
					BigInteger id = addIdList.get(i);
					FamilyMsgHasTUser toAdd = new FamilyMsgHasTUser();
					toAdd.setId(id);
					toAdd.settFamilyMsgFId(familyMsgAddId);
					toAdd.settUserFId(toNotifyUserList.get(i).getId());
					familyMsgHasTUserAddList.add(toAdd);
				}
				familyMsgHasTUserBaseDao.insertFamilyMsgHasTUserBatch(familyMsgHasTUserAddList);
				//同步新增用户信息
				familyMsgDao.synFamilyMsgHasTUserData(familyMsgAddId);
			}
			//新增拓展信息 msgExtDataAddList信息作快照处理
			if(msgExtDataAddList!=null&&msgExtDataAddList.size()>0){
				List<FamilyMsgExtData> familyMsgExtDataAddList = new ArrayList<FamilyMsgExtData>();
				List<BigInteger> addIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_family_msg_ext_data, msgExtDataAddList.size());
				for(int i=0;i<msgExtDataAddList.size();i++){
					MsgExtDataAdd tmpAA = msgExtDataAddList.get(i);
					BigInteger id = addIdList.get(i);
					BigInteger dataId = tmpAA.getDataId();
					Integer dataType = tmpAA.getDateType();
					FamilyMsgExtData toAdd = new FamilyMsgExtData();
					toAdd.setDataId(dataId);
					toAdd.setDataType(dataType);
					toAdd.setId(id);
					toAdd.settFamilyMsgFId(familyMsgAddId);
					familyMsgExtDataAddList.add(toAdd);
				}
				familyMsgExtDataBaseDao.insertFamilyMsgExtDataBatch(familyMsgExtDataAddList);
				//同步拓展信息
				familyMsgDao.synFamilyMsgExtData(familyMsgAddId);
			}
			{//添加红点提醒
				List<BasicSourceEntity> sourceList = new ArrayList<BasicSourceEntity>();
				sourceList.add(new BasicSourceEntity(RedpointDict.Redpoint_Content_SourceType.FamilyMessage, familyMsgAddId, "INSERT"));
				FutureTask<Boolean> task = new FutureTask<Boolean>(new RedpointAddRunnableForFamily(redpointService, userId, RedpointConstant.RedpointContent_ModelCode.FML_MSG, sourceList));
				new Thread(task).start();
			}
		}
		//推送家人
		pushToFamily(userId, dualDao.getNowTime(), extDataType);
	}

    private List<CommUserDataEntity> mapCommUserDataEntity(List<UserSimpleEntity> uraes) {
        List<CommUserDataEntity> cudes = new ArrayList<CommUserDataEntity>();
        for (UserSimpleEntity urae : uraes) {
            if (null != urae) {
                cudes.add(new CommUserDataEntity(urae.getId(), LoginDict.UserRegistOrTmp.REGIST_USER, urae.getDefaultRoomId()));
            } else {
            }
        }
        return cudes;
    }

    private static Map<Integer, Long> s_mapDateType2MsgType = new HashMap<Integer, Long>() {
        /**
         * 
         */
        private static final long serialVersionUID = 962597215954126944L;

        {
            put(FamilyMsgDict.FamilyMsgExtData_type.TodayCookBookNew, NoticeDict.Message_Type.You_have_a_new_msg);
        }
    };

    /**
     * @author wangzhe
     * @date 2015年9月14日
     * @description 推送给家人
     *
     * @param userId 推送者id
     * @param timeDay 时间
     * @param datatype 消息类型
     */
    private void pushToFamily(BigInteger userId,String timeDay, int datatype){
        Long msgType = s_mapDateType2MsgType.get(datatype);
        if (null != msgType) {
            UserSimpleEntity user = commonUserService.getUserInfoById(userId);
            List<UserSimpleEntity> uses = commonUserService.getFamilyMembersWithoutSelf(userId, true);

            //uses.add(user);// 测试使用，添加自己作为推送对象

            if (0 < uses.size()) {
                List<CommUserDataEntity> cudes = mapCommUserDataEntity(uses);

                Message msg = new Message();
                msg.setCreater(userId);
                msg.setContent("您的家人  " + (null != user.getNickName()?user.getNickName():user.getId()) +  "  发来新的留言");
                msg.setTime(timeDay);
                msg.setType(msgType);
                msg.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message));

                
                List<BigInteger> paramIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter,2);
                List<MessageParameter> mps = new ArrayList<MessageParameter>();
                
                MessageParameter paramPushId = new MessageParameter();
                paramPushId.setId(paramIds.get(0));
                paramPushId.setKey("pushId");
                paramPushId.settMessageFId(msg.getId());
                paramPushId.setValue(MsgpushDict.PushId.NewMessage);
                mps.add(paramPushId);
                
                MessageParameter paramPushType = new MessageParameter();
                paramPushType.setId(paramIds.get(1));
                paramPushType.setKey("pushType");
                paramPushType.settMessageFId(msg.getId());
                paramPushType.setValue(Integer.toString(datatype));
                mps.add(paramPushType);

                // 超时时间是第二天0点
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_YEAR, 1);

                msgpushService.addMessage2Pool(cudes, msg, DateUtil.formatDay.get().format(c.getTime()), mps);
            } else {
            }
        } else {
        }
    }

	@Override
	public List<FamilyMsgEntity> qryMsgList(BigInteger userId, PageModel pageModel) {
		List<FamilyMsgEntity> msgList = familyMsgDao.selectMsgList(userId, pageModel);
		appendCommentsInfo(msgList);//附加评论信息
		return msgList;
	}
	
	@Override
	public FamilyMsgEntity qryMsgDetail(BigInteger userId, BigInteger msgId) {
		FamilyMsgEntity resFamilyMsgEntity =  familyMsgDao.selectMsgDetail(userId, msgId);
		{//附加评论信息
			List<FamilyMsgEntity> msgList = new ArrayList<FamilyMsgEntity>();
			if(resFamilyMsgEntity!=null){
				msgList.add(resFamilyMsgEntity);
			}
			appendCommentsInfo(msgList);
		}
		
		return resFamilyMsgEntity;
	}
	
	//附加评论信息
	private void appendCommentsInfo(List<FamilyMsgEntity> msgList){
		List<BigInteger> goalIdList = null;
		if(msgList!=null&&msgList.size()>0){
			goalIdList = new ArrayList<BigInteger>();
			for(FamilyMsgEntity tmpFamilyMsgEntity:msgList){
				goalIdList.add(tmpFamilyMsgEntity.getId());
			}
		}
		if(goalIdList!=null&&goalIdList.size()>0){
			List<GoalIdCommentsEntity> goalIdCommentsEntityList = commentsService.getCommentsListMulti(CommonModuleDict.CommonModule_TargetType.T_FAMILY_MESSAGE, goalIdList, FamilyMsgDict.DEFAULT_COMMENTS_LENGTH);
			for(FamilyMsgEntity tmpFamilyMsgEntity:msgList){
				for(GoalIdCommentsEntity tmpGoalIdCommentsEntity:goalIdCommentsEntityList){
					if(tmpFamilyMsgEntity.getId().compareTo(tmpGoalIdCommentsEntity.getGoalId())==0){
						tmpFamilyMsgEntity.setPreCommentsEntityList(tmpGoalIdCommentsEntity.getCommentsEntityList());
						break;
					}
				}
			}
		}
	}
	
}
