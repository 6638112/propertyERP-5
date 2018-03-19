package com.cnfantasia.server.api.appFunctionBar.service;

import com.cnfantasia.server.api.appFunctionBar.dao.IAppFunctionBarDao;
import com.cnfantasia.server.api.appFunctionBar.entity.AppFunctionBarGroup;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.operation.service.IAddressOperationService;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.room.constant.RoomConstants;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: AppFunctionBarService
 * @Date: 2017-02-23 13:58
 * @Auther: kangduo
 * @Description: ()
 */
public class AppFunctionBarService implements IAppFunctionBarService {
    private IAppFunctionBarDao appFunctionBarDao;
    public void setAppFunctionBarDao(IAppFunctionBarDao appFunctionBarDao) {
        this.appFunctionBarDao = appFunctionBarDao;
    }

    private IAddressOperationService addressOperationService;
    public void setAddressOperationService(IAddressOperationService addressOperationService) {
        this.addressOperationService = addressOperationService;
    }

    private ICommonRoomService commonRoomService;
    public void setCommonRoomService(ICommonRoomService commonRoomService) {
        this.commonRoomService = commonRoomService;
    }

    @Override
    public List<AppFunctionBarGroup> getFuncBarGroupListByPositionType(Integer positionType, Integer version) {
        BigInteger userId = UserContext.getOperIdBigInteger();
        BigInteger gbId = userId == null ?
                RoomConstants.DEFAULT_GROUP_BUILDING_ID : commonRoomService.getGroupBuildingIdByUserId(userId);
        List<BigInteger> codeIdList = addressOperationService.getCodeIdList(null, null, null, null, gbId);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("positionType", positionType);
        param.put("version", version);
        param.put("codeIdList", codeIdList);
        return appFunctionBarDao.getFuncBarGroupListByPositionType(param);
    }
}
