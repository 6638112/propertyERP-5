package com.cnfantasia.server.api.access.codec.ake.bean.resp;

import com.cnfantasia.server.api.access.codec.ake.bean.base.ResponseBaseData;
import com.cnfantasia.server.api.access.codec.ake.bean.request.MonthCardDetail;
import java.util.List;

public class QueryMonthCardResp implements ResponseBaseData {
    private static final long serialVersionUID = 8018165613331244698L;
    private String parkCode;
    private List<MonthCardDetail> data;

    public String getParkCode() {
        return this.parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public List<MonthCardDetail> getData() {
        return this.data;
    }

    public void setData(List<MonthCardDetail> data) {
        this.data = data;
    }
}

