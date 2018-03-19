package com.cnfantasia.server.api.access.codec.ake.bean.request;

import com.cnfantasia.server.api.access.codec.ake.bean.base.ParkRequest;

public class LoginReq implements ParkRequest {
    private static final long serialVersionUID = -2257294272389541657L;
    private String parkAccount;
    private String parkPwd;

    public String getParkAccount() {
        return this.parkAccount;
    }

    public void setParkAccount(String parkAccount) {
        this.parkAccount = parkAccount;
    }

    public String getParkPwd() {
        return this.parkPwd;
    }

    public void setParkPwd(String parkPwd) {
        this.parkPwd = parkPwd;
    }

    public String toString() {
        return "LoginReq [parkAccount=" + this.parkAccount + ", parkPwd=" + this.parkPwd + "]";
    }
}

