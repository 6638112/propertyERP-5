package com.cnfantasia.server.api.ebuy.domain;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: EguExpressTraceJson
 * @Date: 2016-07-14 16:11
 * @Auther: kangduo
 * @Description:(依谷物流返回信息)
 */
public class JsonEguExpressTrace {
    private String status;
    private String state;
    private String errorMsg;
    private List<Map<String, String>> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<Map<String, String>> getData() {
        return data;
    }

    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }
}
