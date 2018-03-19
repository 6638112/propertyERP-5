package com.cnfantasia.server.api.operateMsg.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.commonBusiness.entity.OperateConfigRange;
import com.cnfantasia.server.api.operateMsg.dao.OperateMsgDao;
import com.cnfantasia.server.api.operateMsg.entity.MessageAddNewParamter;
import com.cnfantasia.server.api.operation.constant.OperationDict.OperationMsgDict;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.messageToBeSend.dao.IMessageToBeSendBaseDao;
import com.cnfantasia.server.domainbase.messageToBeSend.entity.MessageToBeSend;

public class OperateMsgService {
	private Log logger = LogFactory.getLog(getClass());
	@Resource
	OperateMsgDao operateMsgDao;
	
	@Resource
	IUuidManager uuidManager;

	@Resource
	IMessageToBeSendBaseDao messageToBeSendBaseDao;
	
	public List<MessageToBeSend> qryMessageToBeSendListForPage(Map<String, Object> paramMap) {
		return operateMsgDao.qryMessageToBeSendListForPage(paramMap);
	}

	public int qryMessageToBeSendListForCount(Map<String, Object> paramMap) {
		return operateMsgDao.qryMessageToBeSendListForCount(paramMap);
	}

	public BigInteger saveMessageToBeSend(MessageAddNewParamter m, MultipartFile blackFile, MultipartFile whiteFile) {
		CnfantasiaCommbusiUtil.newStandard(m, SEQConstants.t_message_to_be_send);
		m.setSendStatus(OperationMsgDict.SendStatus_NotSend);
		m.setSendCount(0L);
		
		try {
			if (blackFile != null) {
				InputStreamReader read = new InputStreamReader(blackFile.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(read);
	            String lineTxt = null;
	           	StringBuilder blackPhones = new StringBuilder();
	            while ((lineTxt = bufferedReader.readLine()) != null){
	            	if(StringUtils.isNotBlank(lineTxt.trim()))
	            		blackPhones.append(lineTxt.trim()).append(";");
	            }
	            m.setBlackList(blackPhones.toString());
	            bufferedReader.close();
	            read.close();
			}

			if (whiteFile != null) {
				InputStreamReader read = new InputStreamReader(whiteFile.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(read);
	           	StringBuilder whitePhones = new StringBuilder();
	           	String lineTxt = null;
	            while ((lineTxt = bufferedReader.readLine()) != null){
	            	if(StringUtils.isNotBlank(lineTxt.trim()))
	            		whitePhones.append(lineTxt.trim()).append(";");
	            }
	            m.setWhiteList(whitePhones.toString());
	            bufferedReader.close();
	            read.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
		messageToBeSendBaseDao.insertMessageToBeSend(m);
		
		return m.getId();
	}

	public List<OperateConfigRange> qrySendRangeByMsgId(BigInteger id) {
		return operateMsgDao.qrySendRangeByMsgId(id);
	}

	public List<String> qryUserMobileByMsgToBeSendId(BigInteger id) {
		return operateMsgDao.qryUserMobileByMsgToBeSendId(id);
	}

	public List<CommUserDataEntity> qryUserIdByMsgToBeSendId(BigInteger id) {
		return operateMsgDao.qryUserIdByMsgToBeSendId(id);
	}
	
	/**
	 * 查询所有待发送的运营消息
	 * @return
	 */
	public List<MessageToBeSend> qryMessageToBeSendList4Task(){
		return operateMsgDao.qryMessageToBeSendList4Task();
	}
	
	public int sendMsg(BigInteger id){
		MessageToBeSend msg =messageToBeSendBaseDao.selectMessageToBeSendBySeqId(id);
		return sendMsg(msg);
	}
	
	/**
	 * 发送或推送运营消息 
	 * @param msg
	 */
	public int sendMsg(MessageToBeSend msg){
		if(msg.getSendStatus() == OperationMsgDict.SendStatus_Success)
			return 0;
		
		IOperateMsgSender operateMsgSender = null;
		if(msg.getMsgType() == OperationMsgDict.MsgType_ShortMsg){
			operateMsgSender = new ShortMsgSender();
		}else if(msg.getMsgType() == OperationMsgDict.MsgType_PushMsg){
			operateMsgSender = new PushMsgSender();
		}
		
		int effect = operateMsgSender.sendMessage(operateMsgSender.prepare(msg), msg);
		msg.setSendCount(Long.valueOf(effect));
		msg.setSendStatus(OperationMsgDict.SendStatus_Success);
		msg.setSendTime(DateUtils.getCurrentDate());
		messageToBeSendBaseDao.updateMessageToBeSend(msg);
		
		return effect;
	}
}
