package com.cnfantasia.server.commbusi.UserAndDevicePayCount.dao;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

/**
 * Created by yangh on 2016/9/30.
 */
public class UserAndDevicePayCountDao extends AbstractBaseDao implements IUserAndDevicePayCountDao {
    @Override
    public int updateUserPayCount() {
        return sqlSession.update("payCountInit.userPayCount");
    }

    @Override
    public int updateDevicePayCount() {
        return sqlSession.update("payCountInit.devicePayCount");
    }
}
