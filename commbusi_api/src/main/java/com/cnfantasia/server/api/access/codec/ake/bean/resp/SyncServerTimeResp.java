package com.cnfantasia.server.api.access.codec.ake.bean.resp;

import com.cnfantasia.server.api.access.codec.ake.bean.base.ResponseBaseData;
import java.util.Date;

public class SyncServerTimeResp implements ResponseBaseData {
    private static final long serialVersionUID = -6976566449329068780L;
    private Date time;

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String toString() {
        return "SyncServerTimeResp [time=" + this.time + "]";
    }
}

