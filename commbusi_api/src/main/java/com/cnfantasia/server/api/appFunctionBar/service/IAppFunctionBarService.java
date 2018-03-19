package com.cnfantasia.server.api.appFunctionBar.service;

import com.cnfantasia.server.api.appFunctionBar.entity.AppFunctionBarGroup;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: IAppFunctionBarService
 * @Date: 2017-02-23 13:58
 * @Auther: kangduo
 * @Description: ()
 */
public interface IAppFunctionBarService {
    public List<AppFunctionBarGroup> getFuncBarGroupListByPositionType(Integer positionType, Integer version);
}
