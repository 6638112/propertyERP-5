package com.cnfantasia.server.api.userQuestion.dao;

import com.cnfantasia.server.domainbase.propertyDistrict.entity.PropertyDistrict;

import java.math.BigInteger;

/**
 * @ClassName: IUserQuestionDao
 * @Date: 2017-02-23 14:31
 * @Auther: kangduo
 * @Description: ()
 */
public interface IUserQuestionDao {
    /**
     * 查询指定房间片区信息
     * @param roomId
     * @return
     */
    public PropertyDistrict getPropertyDistrictByRoomId(BigInteger roomId);

    /**
     * 获取门牌详情 <城市><区县><小区><楼栋-单元-房号> .
     * @param roomId 门牌ID
     * @return
     */
    String getRoomDetailAddress(BigInteger roomId);
}
