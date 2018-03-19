package com.cnfantasia.server.api.carMsgTask.service;

import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.carMsgTask.dao.ICarMsgDao;
import com.cnfantasia.server.api.carMsgTask.entity.CarSendMsgEntity;
import com.cnfantasia.server.api.carMsgTask.entity.CarSendPushEntity;
import com.cnfantasia.server.api.homeMessage.constant.HomeMessageDict;
import com.cnfantasia.server.api.homeMessage.service.IHomeMessageService;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.message.service.IMessageBaseService;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.messageParameter.service.IMessageParameterBaseService;
import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;
import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;
import com.cnfantasia.server.domainbase.userHasTMessage.service.IUserHasTMessageBaseService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class CarMsgService implements ICarMsgService {
	
	private ICarMsgDao carMsgDao;
    private IUuidManager uuidManager;
    private IMessageBaseService messageBaseService;
    private IUserHasTMessageBaseService userHasTMessageBaseService;
    private IMessageParameterBaseService messageParameterBaseService;
	private IHomeMessageService homeMessageService;

	public void setCarMsgDao(ICarMsgDao carMsgDao) {
		this.carMsgDao = carMsgDao;
	}
	
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	public void setMessageBaseService(IMessageBaseService messageBaseService) {
		this.messageBaseService = messageBaseService;
	}

	public void setUserHasTMessageBaseService(
			IUserHasTMessageBaseService userHasTMessageBaseService) {
		this.userHasTMessageBaseService = userHasTMessageBaseService;
	}

	public void setMessageParameterBaseService(
			IMessageParameterBaseService messageParameterBaseService) {
		this.messageParameterBaseService = messageParameterBaseService;
	}

	public void setHomeMessageService(IHomeMessageService homeMessageService) {
		this.homeMessageService = homeMessageService;
	}

	/**
	 * 查询30天内到期的车辆绑定的所有用户，并发送短信
	 */
	@Override
	public void sendMsg() {
		List<CarSendMsgEntity> carMsgs = carMsgDao.queryCarMsgInfo();
		for (CarSendMsgEntity carMsg : carMsgs) {
			ShortMsgUtil.sendMessage(carMsg.getMobile(), "car.msg", carMsg.getCarNum(), carMsg.getGbName(), carMsg.getExpireDay());
		}
	}

	/**
	 * 查询30天内到期的车辆绑定的所有用户，并推送
	 */
	@Override
	@Transactional
	public void push() {
		List<CarSendPushEntity> carSendPushEntitys = carMsgDao.queryCarPushInfo();

		//填充月卡车的首页提醒
		Calendar calendar = Calendar.getInstance();
		int todayNum = calendar.get(Calendar.DAY_OF_MONTH);
		if (!DataUtil.isEmpty(carSendPushEntitys)) {
			//按userId排序
			Collections.sort(carSendPushEntitys);
			List<UserHasHomeMessage> messageList = new ArrayList<>(carSendPushEntitys.size());
			//计数器，计算同一个用户有几车要过期
			int count = 1;
			for (int i = 0; i < carSendPushEntitys.size() - 1; i++) {
				//和下一条不是同一个用户,则记录新消息
				if (carSendPushEntitys.get(i).getUserId().compareTo(carSendPushEntitys.get(i + 1).getUserId()) != 0) {
					UserHasHomeMessage message = new UserHasHomeMessage();
					message.setUserId(carSendPushEntitys.get(i).getUserId());
					message.setExpireTime(carSendPushEntitys.get(i).getExpireDate());
					message.setMessageCode(HomeMessageDict.MessageCode.CAR_TIMEOUT_ALERT);
					message.setContent("您有月卡即将到期");
					message.setExtInfo(count + "张即将到期");
					messageList.add(message);
					//重新开始计数
					count = 1;
				} else {
					count++;
				}
			}
			//最后一条，如果上一条和这个不是同一个人，count = 1是没错的，如果同一个人，count 也是对的
			UserHasHomeMessage message = new UserHasHomeMessage();
			message.setUserId(carSendPushEntitys.get(carSendPushEntitys.size() - 1).getUserId());
			message.setExpireTime(carSendPushEntitys.get(carSendPushEntitys.size() - 1).getExpireDate());
			message.setMessageCode(HomeMessageDict.MessageCode.CAR_TIMEOUT_ALERT);
			message.setContent("您有月卡即将到期");
			message.setExtInfo(count + "张即将到期");
			messageList.add(message);
			homeMessageService.generateCommonMsg(messageList);
		}

		//此部分在每月10号20号执行
        if (carSendPushEntitys != null && carSendPushEntitys.size() > 0 && (todayNum == 10 || todayNum == 20)) {
        	List<BigInteger> msgIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_message, carSendPushEntitys.size());
        	List<BigInteger> uhmIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_message, carSendPushEntitys.size());
        	List<BigInteger> mpIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter, carSendPushEntitys.size()*2);
        	
        	List<Message> messages = new ArrayList<Message>();
        	List<UserHasTMessage> userHasTMessages = new ArrayList<UserHasTMessage>();
        	List<MessageParameter> messageParameters = new ArrayList<MessageParameter>();
        	
        	int idx = 0;
        	for(CarSendPushEntity carSendPushEntity:carSendPushEntitys){
        		// t_message
        		Message msg = new Message();
        		msg.setId(msgIds.get(idx));
        		msg.setTitle("["+carSendPushEntity.getCarNum()+"]月卡即将到期");
        		String expireDate = carSendPushEntity.getExpireDate().substring(0, 10);// 只保留yyyy-MM-dd
        		msg.setContent("您的"+carSendPushEntity.getCarNum()+"将在"+expireDate+"到期,请尽快缴费");
        		msg.setTime(DateUtils.getCurrentDate());
        		msg.setIsRelateRoom(0);//消息与门牌无关
        		msg.setType(NoticeDict.Message_Type.Car_expire_msg);
        		
        		// t_user_has_t_message
        		UserHasTMessage uhm = new UserHasTMessage();
        		uhm.setId(uhmIds.get(idx));
        		uhm.setTryFailedCount(0L);
        		uhm.setSendStatus(NoticeDict.Message_SendStatus.UnDo);
        		uhm.setStatus(NoticeDict.Message_ReadStatus.NotRead);
        		uhm.settUserFId(carSendPushEntity.getUserId());
        		uhm.settMessageFId(msgIds.get(idx));
        		uhm.setUserType(1);

                // t_message_parameter
                MessageParameter mp1 = new MessageParameter();
                mp1.setId(mpIds.get(idx*2));
                mp1.settMessageFId(msgIds.get(idx));
                mp1.setKey("pushId");
                mp1.setValue(MsgpushDict.PushId.CarMsg);
                
                MessageParameter mp2 = new MessageParameter();
                mp2.setId(mpIds.get(idx*2+1));
                mp2.settMessageFId(msgIds.get(idx));
                mp2.setKey("extraPayload");
                mp2.setValue(carSendPushEntity.getCarNum());
                
                {
                	messages.add(msg);
                	userHasTMessages.add(uhm);
                	messageParameters.add(mp1);
                	messageParameters.add(mp2);
                }
        		idx++;
        	}
        	
        	messageBaseService.insertMessageBatch(messages);
        	userHasTMessageBaseService.insertUserHasTMessageBatch(userHasTMessages);
        	messageParameterBaseService.insertMessageParameterBatch(messageParameters);
        }
	}
}
