package com.cnfantasia.server.commbusi.UserAndDevicePayCount.service;

/**
 * Created by yangh on 2016/9/30.
 */
public interface IUserAndDevicePayCountService {

    /**
     * 用户缴费次数初始化
     */
    int updateUserPayCount();

    /**
     * 设备缴费次数初始化
     */
    int updateDevicePayCount();
}
