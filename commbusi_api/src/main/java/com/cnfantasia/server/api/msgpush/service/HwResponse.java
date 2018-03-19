package com.cnfantasia.server.api.msgpush.service;

public class HwResponse {

    // resultcode:
    // 101 Invalid api_key. 不存在的api key，一般指不存在的 appId
    // 20005 Illegal cache Mode. 不合法的存储模式
    // 20006 Illegal msg Type. 不合法的消息类型
    // 20203 No permission to send message to these tmIDs. 无权发送消息给此token
    // 20204 Invalid expire time. 无效的超时时间
    // 20205 Not enough quota. 没有足够的限额
    // 80200004 Illegal token. 不合法的token
    // 80200007 Illegal priority. 不合法的优先级
    // 80200015 Illegal message. 不合法的消息
    // 80200018 Invalid user type. 不存在的用户类型
    // 1 error occured. 内部错误
    // 0 success

    private int resultcode;
    private String message;
    private String error;
    private String requestID;

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public int getResultcode() {
        return resultcode;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setResultcode(final int resultcode) {
        this.resultcode = resultcode;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }
}
