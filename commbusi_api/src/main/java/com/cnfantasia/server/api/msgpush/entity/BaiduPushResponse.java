package com.cnfantasia.server.api.msgpush.entity;

/**
 * @ClassName: BaiduPushResponse
 * @Date: 2017-01-06 16:06
 * @Auther: kangduo
 * @Description: (百度推送响应数据)
 */
public class BaiduPushResponse {
    private Long requestId;
    private String errorCode;
    private String errorMsg;
    private String msgId;
    private Long sendTime;
    private String sendTimeFormat;
    private boolean success = true;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSendTimeFormat() {
        return sendTimeFormat;
    }

    public void setSendTimeFormat(String sendTimeFormat) {
        this.sendTimeFormat = sendTimeFormat;
    }
}
