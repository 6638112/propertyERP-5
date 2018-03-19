package com.cnfantasia.server.api.access.codec.ake.bean.base;

import java.io.Serializable;

public abstract interface ParkRequest extends Serializable {

    final int STATUS_SUCCESS = 1; // 业务成功
    final int STATUS_FAIL = STATUS_SUCCESS + 1; // 业务失败
    final int STATUS_ERROR = STATUS_FAIL + 1; // 系统异常
}
