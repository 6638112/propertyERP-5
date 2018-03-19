package com.cnfantasia.server.commbusi.UserAndDevicePayCount.service;

import com.cnfantasia.server.commbusi.UserAndDevicePayCount.dao.IUserAndDevicePayCountDao;
import com.cnfantasia.server.domainbase.devicePayCount.service.IDevicePayCountBaseService;

import javax.annotation.Resource;

/**
 * Created by yangh on 2016/9/30.
 */
public class UserAndDevicePayCountService implements IUserAndDevicePayCountService {

    @Resource
    private IUserAndDevicePayCountDao userAndDevicePayCountDao;

    @Override
    public int updateUserPayCount() {
        return userAndDevicePayCountDao.updateUserPayCount();
    }

    @Override
    public int updateDevicePayCount() {
        return userAndDevicePayCountDao.updateDevicePayCount();
    }
}
