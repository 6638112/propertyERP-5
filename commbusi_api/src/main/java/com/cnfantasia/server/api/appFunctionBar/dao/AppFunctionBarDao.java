package com.cnfantasia.server.api.appFunctionBar.dao;

import com.cnfantasia.server.api.appFunctionBar.entity.AppFunctionBarGroup;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: AppFunctionBarDao
 * @Date: 2017-02-23 13:58
 * @Auther: kangduo
 * @Description: ()
 */
public class AppFunctionBarDao extends AbstractBaseDao implements IAppFunctionBarDao {
    @Override
    public List<AppFunctionBarGroup> getFuncBarGroupListByPositionType(Map<String, Object> param) {
        return sqlSession.selectList("appFunctionBar.getFuncBarGroupListByPositionType", param);
    }
}
