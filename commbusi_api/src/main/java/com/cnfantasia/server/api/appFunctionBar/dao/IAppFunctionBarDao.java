package com.cnfantasia.server.api.appFunctionBar.dao;

import com.cnfantasia.server.api.appFunctionBar.entity.AppFunctionBarGroup;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IAppFunctionBarDao
 * @Date: 2017-02-23 13:57
 * @Auther: kangduo
 * @Description: ()
 */
public interface IAppFunctionBarDao {
    public List<AppFunctionBarGroup> getFuncBarGroupListByPositionType(Map<String, Object> param);
}
