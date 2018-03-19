package com.cnfantasia.server.api.operateMsg.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.msgpush.constant.MsgToAddBasic;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.msgpush.service.IPushAddService;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.messageToBeSend.entity.MessageToBeSend;
import com.cnfantasia.server.domainbase.operationMsgPushConfig.entity.OperationMsgPushConfig;
import com.cnfantasia.server.domainbase.operationMsgPushConfig.service.OperationMsgPushConfigBaseService;

/**
 * 运营消息-push发送器
 * @author wenfq  2017年6月8日
 *
 */
public class PushMsgSender implements IOperateMsgSender {

	@Override
	public List<CommUserDataEntity> prepare(MessageToBeSend msg) {
		OperateMsgService operateMsgService = (OperateMsgService) CnfantasiaCommbusiUtil.getBeanManager("operateMsgService");
		List<CommUserDataEntity> userEndityList = operateMsgService.qryUserIdByMsgToBeSendId(msg.getId());
		return userEndityList;
	}

	@Override
	public int sendMessage(List userList, MessageToBeSend msg) {
		MsgToAddBasic basic = new MsgToAddBasic();
        
        basic.setiOSMsgType(NoticeDict.Message_Type.OperateMessage); 
        basic.setAndroidPushId(MsgpushDict.PushId.OperateMessge);
        basic.setIsReleatRoom(MsgpushDict.PushReleatRoom.TRUE);
        basic.setTitle(msg.getTitle());
        basic.setContent(msg.getContent());
        
        if(msg.getJumpTarget()!=null){
	        OperationMsgPushConfigBaseService operationMsgPushConfigBaseService =  (OperationMsgPushConfigBaseService) CnfantasiaCommbusiUtil.getBeanManager("operationMsgPushConfigBaseService");
	        OperationMsgPushConfig OperationMsgPushConfigQry = new OperationMsgPushConfig();
	        OperationMsgPushConfigQry.setNumber(msg.getJumpTarget());
	        List<OperationMsgPushConfig> operationMsgPushConfigList = operationMsgPushConfigBaseService.getOperationMsgPushConfigByCondition(MapConverter.convertBean(OperationMsgPushConfigQry));
	        if(operationMsgPushConfigList.size() == 1){
		        OperationMsgPushConfig pushConfig = operationMsgPushConfigList.get(0);
		        Map<String, Object> paramMap = new HashMap<String, Object>();
				if (StringUtils.isEmpty(msg.getEbuyProductDetail())) {
					paramMap.put("iosParams", pushConfig.getIosParam());
					paramMap.put("androidParams", pushConfig.getAndroidParam());
					paramMap.put("huaweiParams", pushConfig.getHuaweiParam());
				} else {//商品详情，参数{0}需要替换
					paramMap.put("iosParams", pushConfig.getIosParam().replace("#", msg.getEbuyProductDetail()));
					paramMap.put("androidParams", pushConfig.getAndroidParam().replace("#", msg.getEbuyProductDetail()));
					paramMap.put("huaweiParams", pushConfig.getHuaweiParam().replace("###", msg.getEbuyProductDetail()));
				}
				
		        basic.setMsgParameters(paramMap);
	        }
        }
        
        IPushAddService pushAddService = (IPushAddService) CnfantasiaCommbusiUtil.getBeanManager("pushAddService");
		int effect = pushAddService.addMessage2Pool(msg.getId(), userList, basic);
		
		return effect;
	}

}
