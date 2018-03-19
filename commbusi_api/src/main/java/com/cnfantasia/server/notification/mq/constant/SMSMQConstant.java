package com.cnfantasia.server.notification.mq.constant;

/**
 * 手机短信消息队列
 * @author wenfq
 *
 */
public class SMSMQConstant {
	/**
	 * 未发送
	 */
	public final static int SMSMQ_Status_NotSend = 0;
	/**
	 * 发送成功
	 */
	public final static int SMSMQ_Status_SendSuccess = 1;
	/**
	 * 发送失败
	 */
	public final static int SMSMQ_Status_SendFail = 2;
	/**
	 * 用户拒绝
	 */
	public final static int SMSMQ_Status_SendUserRefuse = 3;
	
	
	/**
	 * 上门维修单
	 */
	public final static int SMSMQ_Status_SrcType = 0;

	public static int SMSMQ_Status_SrcType_Dredge = 0;
	
	/**
	 * 物业代扣卡
	 */
	public static int SMSMQ_Status_SrcType_PropertyCard = 1 ;

}