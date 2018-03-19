package com.cnfantasia.server.api.msgpush.constant;

import java.util.Map;

/**
 * @ClassName: MsgToAddBasic
 * @Date: 2017-01-22 15:10
 * @Auther: kangduo
 * @Description: (需要发送的推送基本信息)
 */
public class MsgToAddBasic {
    private String title;
    private String content;
    private Integer isReleatRoom;
    private Long iOSMsgType;
    private String androidPushId;

    //除了pushId之外的msgPatameter
    private Map<String, Object> msgParameters;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsReleatRoom() {
        return isReleatRoom;
    }

    public void setIsReleatRoom(Integer isReleatRoom) {
        this.isReleatRoom = isReleatRoom;
    }

    public Long getiOSMsgType() {
        return iOSMsgType;
    }

    public void setiOSMsgType(Long iOSMsgType) {
        this.iOSMsgType = iOSMsgType;
    }

    public String getAndroidPushId() {
        return androidPushId;
    }

    public void setAndroidPushId(String androidPushId) {
        this.androidPushId = androidPushId;
    }

    public Map<String, Object> getMsgParameters() {
        return msgParameters;
    }

    public void setMsgParameters(Map<String, Object> msgParameters) {
        this.msgParameters = msgParameters;
    }
}
