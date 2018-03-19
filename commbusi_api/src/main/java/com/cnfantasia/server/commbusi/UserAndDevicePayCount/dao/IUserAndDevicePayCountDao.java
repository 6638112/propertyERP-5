package com.cnfantasia.server.commbusi.UserAndDevicePayCount.dao;

/**
 * Created by yangh on 2016/9/30.
 */
public interface IUserAndDevicePayCountDao {
    /**
     * 用户缴费次数初始化
     */
    int updateUserPayCount();

    /**
     * 设备缴费次数初始化
     */
    int updateDevicePayCount();
}
