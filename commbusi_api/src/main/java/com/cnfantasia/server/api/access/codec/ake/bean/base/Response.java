package com.cnfantasia.server.api.access.codec.ake.bean.base;

import java.io.Serializable;

import com.cnfantasia.server.api.access.codec.ake.bean.constant.ResponseType;

public class Response implements Serializable {
    private static final long serialVersionUID = 6967904240178289314L;
    private Integer status;
    private String messageId;
    private Integer resultCode;
    private String message;
    private ResponseType responseType;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(final String messageId) {
        this.messageId = messageId;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(final Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(final ResponseType responseType) {
        this.responseType = responseType;
    }

    @Override
    public String toString() {
        return "Response [status=" + status + ", messageId=" + messageId + ", resultCode=" + resultCode + ", message=" + message + ", responseType="
                + responseType + "]";
    }
}