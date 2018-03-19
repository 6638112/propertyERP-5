package com.cnfantasia.server.api.msgpush.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.entity.UserIdType;
import com.cnfantasia.server.api.msgpush.constant.BaiduPushKey;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.msgpush.entity.BaiduMessageFormat;
import com.cnfantasia.server.api.msgpush.entity.MessagepushEntity;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.notice.entity.MessageEntity;
import com.cnfantasia.server.api.pub.constant.DictConstants;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.common.utils.HtmlTagFilterUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.userPushInfo.entity.UserPushInfo;

public class DataConvertUtil {
	
	
	public static List<BaiduMessageFormat> messagePushBaseEntity2BaiduMessageFormat(List<MessagepushEntity> msgList) {
		if (msgList == null || msgList.size() <= 0) {
			return new ArrayList<BaiduMessageFormat>();
		}
		List<BaiduMessageFormat> toDealList = new Vector<BaiduMessageFormat>();
		if (msgList.size() > 0) {
			for (MessagepushEntity tmpMessagepush : msgList) {
				BaiduMessageFormat baiduMessageFormatTmp = DataConvertUtil.messagePushBaseEntity2BaiduMessageFormat(tmpMessagepush);
				if(baiduMessageFormatTmp!=null){
					toDealList.add(baiduMessageFormatTmp);
				}
			}
		}
		return toDealList;
	}
	
	/**
	 * 过滤掉已经发送成功的和没有用户信息的
	 * @param tmpMessagepush
	 * @return
	 */
	public static BaiduMessageFormat messagePushBaseEntity2BaiduMessageFormat(MessagepushEntity tmpMessagepush) {

		if (tmpMessagepush==null||tmpMessagepush.fetchSendStatus() == null
				|| NoticeDict.Message_SendStatus.SendSuccess.compareTo(tmpMessagepush.fetchSendStatus()) == 0) {
			return null;
		}
		MessageEntity message = tmpMessagepush.getMessage();
		UserPushInfo userPushInfo = tmpMessagepush.getUserPushInfo();
		if (StringUtils.isEmpty(userPushInfo)) {
			return null;
		}
		String msg_keys = userPushInfo.getId() + "_" + message.getId();
		String title = HtmlTagFilterUtil.removeHtmlTagInfo(message.getTitle());
		String description = HtmlTagFilterUtil.removeHtmlTagInfo(message.getContent());
		
		String baiduChannelId = !StringUtils.isEmpty(userPushInfo.getChannelBd())?userPushInfo.getChannelBd():userPushInfo.getBaiduChannelId();
		String baiduUserId = userPushInfo.getBaiduUserId();

		String push_type = MsgpushDict.BaiDu_push_type.Signal;// 单个人
		String device_type = null;
		String deploy_status = null;

		if (userPushInfo.gettChannelSubFId().compareTo(Long.valueOf(DictConstants.Channel_Sub.ANDROID)) == 0
				|| userPushInfo.gettChannelSubFId().compareTo(HeaderConstant.SubChannelId.Jfq_Master_App_Android) == 0) {
			device_type = MsgpushDict.BaiDu_device_type.Andriod;
			deploy_status = null;
		} else if (userPushInfo.gettChannelSubFId().compareTo(Long.valueOf(DictConstants.Channel_Sub.IOS)) == 0
				|| userPushInfo.gettChannelSubFId().compareTo(HeaderConstant.SubChannelId.Jfq_Master_App_ISO) == 0) {
			device_type = MsgpushDict.BaiDu_device_type.iOS;
			deploy_status = ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.IOS_DEPLOY_STATUS);
		}

		String message_type = MsgpushDict.BaiDu_message_type.Notice;// 消息类型：通知
		String messageContent = null;
		{
			Map<String, Object> msgDetail = new HashMap<String, Object>();
			// 加入Android和Ios的特殊内容
			if (userPushInfo.gettChannelSubFId().compareTo(Long.valueOf(DictConstants.Channel_Sub.ANDROID)) == 0
					|| userPushInfo.gettChannelSubFId().compareTo(HeaderConstant.SubChannelId.Jfq_Master_App_Android) == 0) {
				msgDetail.put(BaiduPushKey.Content_android_title, title);
				int titleLenth = title == null ? 0 : title.length();
				msgDetail.put(BaiduPushKey.Content_android_description, localSubStr(description, 100 - titleLenth));

		          message_type = MsgpushDict.BaiDu_message_type.Message;// 消息类型：通知

                List<MessageParameter> paramList = tmpMessagepush.getMessage().getMessageParameterList();
                if (null != paramList && 0 < paramList.size()) {
                	Map<String, Object> map = new HashMap<String, Object>();
                    for (MessageParameter mp : paramList) {
                    	map.put(mp.getKey(), mp.getValue());
                    }
					map.put("userHasMessageId", tmpMessagepush.getUserHasTMessage().getId());
                    msgDetail.put(BaiduPushKey.Content_android_custom_content, map);
                } else {
                }
                // String targetView = null;
//				if (!StringUtils.isEmpty(message.getAndroidTargetView())) {// 消息的视图保持原始
//					targetView = message.getAndroidTargetView();
//				} else {// 否则使用模板，需要填充参数
//					targetView = tmpMessagepush.getMessage().getMessageView().getAndroidView();
//					int lastIndex = targetView.lastIndexOf("end");
//					if (lastIndex != -1) {
//						String pre = targetView.substring(0, lastIndex);
//						String tail = "end";
//						StringBuffer sbfTotal = new StringBuffer();
//						sbfTotal.append(pre);
//						List<MessageParameter> paramList = tmpMessagepush.getMessage().getMessageParameterList();
//						{// 附加消息详情id
//							sbfTotal.append("S.");
//							sbfTotal.append("id");
//							sbfTotal.append("=");
//							sbfTotal.append(message.getId().toString());
//							sbfTotal.append(";");
//						}//写在前
//						if (paramList != null && paramList.size() > 0) {
//							for (MessageParameter messageParameter : paramList) {
//								sbfTotal.append("i.");
//								sbfTotal.append(messageParameter.getKey());
//								sbfTotal.append("=");
//								sbfTotal.append(messageParameter.getValue());
//								sbfTotal.append(";");
//							}
//						}
//						sbfTotal.append(tail);
//						targetView = sbfTotal.toString();
//					}
//				}
//				if (targetView != null) {
//					msgDetail.put(BaiduPushKey.Content_android_open_type, "2");// TODO openType = 2
//					msgDetail.put(BaiduPushKey.Content_android_pkg_content, targetView);
//					msgDetail.put(BaiduPushKey.Content_android_pkg_name, "com.jiefangqu.living");//syl-add-2014-11-25 10:39:45
//				}
			} else if (userPushInfo.gettChannelSubFId().compareTo(Long.valueOf(DictConstants.Channel_Sub.IOS)) == 0
					|| userPushInfo.gettChannelSubFId().compareTo(HeaderConstant.SubChannelId.Jfq_Master_App_ISO) == 0) {
				// String targetView = null;
				// if(!StringUtils.isEmpty(message.getIosTargetView())){
				// targetView = message.getAndroidTargetView();
				// }else{
				// targetView =
				// tmpMessagepush.getMessage().getMessageView().getIosView();
				// }
				{
					int titleLenth = title == null ? 0 : title.length();
					Map<String, Object> apsMap = new HashMap<String, Object>();
					// iOS如果描述过长推送不过去
					apsMap.put(BaiduPushKey.Content_IOS_aps_alert, localSubStr(description, 100 - titleLenth));
					/*apsMap.put(BaiduPushKey.Content_IOS_aps_sound, "");//3.0百度推送传空字符串会报错
					apsMap.put(BaiduPushKey.Content_IOS_aps_badge, null);*/
					msgDetail.put(BaiduPushKey.Content_IOS_aps, apsMap);
				}
				{// keyValues
					Map<String, Object> keyValues = null;
					List<MessageParameter> paramList = tmpMessagepush.getMessage().getMessageParameterList();
					if (paramList != null && paramList.size() > 0) {
						keyValues = new HashMap<String, Object>();
						for (MessageParameter messageParameter : paramList) {
							keyValues.put(messageParameter.getKey(), messageParameter.getValue());
						}
						keyValues.put("userHasMessageId", tmpMessagepush.getUserHasTMessage().getId());
					}
					msgDetail.put(BaiduPushKey.Content_IOS_keyValues, keyValues);
				}
				msgDetail.put(BaiduPushKey.Content_IOS_detailId, tmpMessagepush.getMessage().getId());
				msgDetail.put(BaiduPushKey.Content_IOS_msgType, tmpMessagepush.getMessage().getType());
				msgDetail.put(BaiduPushKey.Content_IOS_title, message.getTitle());
			}
			messageContent = JSON.toJSONString(msgDetail);
		}
		String tag = null;// tag设置为null
		UserIdType userIdType = new UserIdType(userPushInfo.getUserId(), userPushInfo.getUserType());
		BigInteger messageId = tmpMessagepush.getMessage().getId();
		Long messageType = tmpMessagepush.getMessage().getType();
		BigInteger userHasMessageId = tmpMessagepush.fetchUserMsgRelaId();
		BaiduMessageFormat baiduMessageFormatTmp = new BaiduMessageFormat(msg_keys, messageContent, baiduChannelId,
				baiduUserId, push_type, device_type, message_type, tag, userIdType, messageId, deploy_status, messageType,
				userHasMessageId);
		baiduMessageFormatTmp.setUserPushInfoId(tmpMessagepush.getUserPushInfo().getId());
		return baiduMessageFormatTmp;
	}

	private static String localSubStr(String str, int length) {
		return (str == null || str.length() <= length) ? str : str.substring(0, length) + "...";
	}
	
}
