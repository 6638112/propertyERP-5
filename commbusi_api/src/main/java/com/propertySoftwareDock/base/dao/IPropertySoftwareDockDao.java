package com.propertySoftwareDock.base.dao;

import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.propertySoftwareDock.base.entity.BuildingRoomProprietorEntity;
import com.propertySoftwareDock.base.entity.RealRoomSoftwareInfo;
import com.propertySoftwareDock.base.entity.RealRoomSysInfo;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: IPropertySoftWareDockDao
 * @Date: 2016-11-21 10:06
 * @Auther: kangduo
 * @Description:(物业软件对接dao层)
 */
public interface IPropertySoftwareDockDao {
    public BigInteger getGbIdByRealRoomId(BigInteger realRoomId);

    public RealRoomSoftwareInfo getRealRoomSoftwareInfo(BigInteger realRoomId);

    public List<RealRoomSoftwareInfo> getUnNotifySoftwarePayBillIds();

    public List<RealRoomSysInfo> getMapperedRealRoomSysInfoListByGbId(BigInteger gbId);

    public List<RealRoomSysInfo> getAllRealRoomSysInfoListByGbId(BigInteger gbId);

    /**
     * 新增信息
     * @param gbId
     * @param buildingSet
     * @param brpSet
     */
    public void addBuildingRoomProprietor(BigInteger gbId, Set<String> buildingSet, Set<BuildingRoomProprietorEntity> brpSet);
}
