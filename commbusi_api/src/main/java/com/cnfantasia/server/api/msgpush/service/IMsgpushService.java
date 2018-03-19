/**   
* Filename:    IMsgpushService.java   
* @version:    1.0  
* Create at:   2014年9月22日 上午3:01:34   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.msgpush.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.msgpush.entity.BaiduMessageFormat;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;

/**
 * Filename:    IMsgpushService.java
 * @version:    1.0.0
 * Create at:   2014年9月22日 上午3:01:34
 * Description:消息推送服务类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月22日       shiyl             1.0             1.0 Version
 */
public interface IMsgpushService {
	
	String pushMessage(String msg_keys, String messageContent, String push_type,
					   String device_type, String message_type, String tag, String deploy_status);
	
	/**
	 * 推送消息
	 * @return
	 */
	String pushMessage(String msg_keys, String messageContent, String baiduChannelId, String baiduUserId, String push_type,
					   String device_type, String message_type, String tag, String deploy_status);
	
	
	/**
	 * 推送消息
	 * @return
	 */
	String pushMessage(String msg_keys, String messageContent, String baiduChannelId, String baiduUserId, String push_type, String device_type, String message_type,
					   String tag, String deploy_status, Long messageType);

	/**
	 * 更新消息推送的客户端userId，channel信息
	 * @param baiduUserId
	 * @param baiduChannelId
	 * @return
	 */
	void refreshPushClientInfo(Long subChannelId, BigInteger userId, Integer userType, String baiduUserId, String baiduChannelId, String appVersion);

    void refreshPushClientInfo(Long subChannelId, BigInteger userId, Integer userType, String appVersion, String channelid, int channeltype, String userAgent);
	
	/**
	 * 查询待发送的消息列表
	 */
	List<BaiduMessageFormat> getSendList();
//	public List<BaiduMessageFormat> getSendListOfGroup();
	/**
	 * 批量发送单个消息
	 * @param msgList
	 */
	int sendSignalMsgBatch(List<BaiduMessageFormat> msgList);
	
//	/**
	//	 * 定时任务自动发送
//	 */
//	public void autoSendTask();
	
	/**
	 * 更新消息发送状态
	 * @param userId
	 * @param userType
	 * @param messageId
	 * @param userHasMessageId
	 * @param sendStatus
	 * @return
	 */
	Integer changeMessageStatus(BaiduMessageFormat baiduMessageFormat, Integer sendStatus, String resCode, Boolean isFailed);
	
	/**
	 * 每个月1号自动记录消息信息
	 */
	void autoSaveUnusedPrizeDiscountList();
	
	/**
	 * 立即推送一条消息
	 * @param messageId
	 * @return
	 */
	Integer pushMessageImmediately(BigInteger userHasMsgId);
	
	/**
	 * 立即推送多条消息
	 * @param messageId
	 * @return
	 */
	Integer pushMessageImmediately(List<BigInteger> userHasMsgIdList);
	
	/**
	 * 将消息放入到待发送的消息列表中，等待发送
	 * @return
	 */
	Integer addMessage2Pool(CommUserDataEntity commUserData, BigInteger messageId, String expiryTime);
	Integer addMessage2Pool(CommUserDataEntity commUserData, Message toAddMessage, String expiryTime, List<MessageParameter> paramList);
	Integer addMessage2Pool(List<CommUserDataEntity> commUserDataList, Message toAddMessage, String expiryTime, List<MessageParameter> paramList);
	
	/**
	 * 将多条消息放入到待发送的消息列表中，等待发送
	 * @return
	 */
	Integer addMessage2Pool(CommUserDataEntity commUserData, List<BigInteger> messageIdList, String expiryTime);
	Integer addMessage2Pool(List<CommUserDataEntity> commUserDataList, BigInteger messageId, String expiryTime);
	
}
