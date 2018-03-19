package com.propertySoftwareDock.base.dao;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.building.service.IBuildingBaseService;
import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;
import com.cnfantasia.server.domainbase.propertyProprietor.service.IPropertyProprietorBaseService;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.realRoom.service.IRealRoomBaseService;
import com.propertySoftwareDock.base.entity.BuildingRoomProprietorEntity;
import com.propertySoftwareDock.base.entity.RealRoomSoftwareInfo;
import com.propertySoftwareDock.base.entity.RealRoomSysInfo;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: PropertySoftWareDockDao
 * @Date: 2016-11-21 10:08
 * @Auther: kangduo
 * @Description:(物业软件对接dao层)
 */
public class PropertySoftwareDockDao extends AbstractBaseDao implements IPropertySoftwareDockDao {

    private IBuildingBaseService buildingBaseService;
    public void setBuildingBaseService(IBuildingBaseService buildingBaseService) {
        this.buildingBaseService = buildingBaseService;
    }

    private IRealRoomBaseService realRoomBaseService;
    public void setRealRoomBaseService(IRealRoomBaseService realRoomBaseService) {
        this.realRoomBaseService = realRoomBaseService;
    }

    private IPropertyProprietorBaseService propertyProprietorBaseService;
    public void setPropertyProprietorBaseService(IPropertyProprietorBaseService propertyProprietorBaseService) {
        this.propertyProprietorBaseService = propertyProprietorBaseService;
    }

    private IUuidManager uuidManager;
    public void setUuidManager(IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }

    @Override
    public BigInteger getGbIdByRealRoomId(BigInteger realRoomId) {
        return sqlSession.selectOne("propertySoftwareDock.getGbIdByRealRoomId", realRoomId);
    }

    @Override
    public RealRoomSoftwareInfo getRealRoomSoftwareInfo(BigInteger realRoomId) {
        return sqlSession.selectOne("propertySoftwareDock.getRealRoomSoftwareInfo", realRoomId);
    }

    @Override
    public List<RealRoomSoftwareInfo> getUnNotifySoftwarePayBillIds() {
        return sqlSession.selectList("propertySoftwareDock.getUnNotifySoftwarePayBillIds");
    }

    @Override
    public List<RealRoomSysInfo> getMapperedRealRoomSysInfoListByGbId(BigInteger gbId) {
        return sqlSession.selectList("propertySoftwareDock.getMapperedRealRoomSysInfoListByGbId", gbId);
    }

    @Override
    public List<RealRoomSysInfo> getAllRealRoomSysInfoListByGbId(BigInteger gbId) {
        return sqlSession.selectList("propertySoftwareDock.getAllRealRoomSysInfoListByGbId", gbId);
    }

    @Override
    /**
     * 添加新房间（brpSet的realRoomId要先设置好值）
     */
    public void addBuildingRoomProprietor(BigInteger groupBuildingId,
                                           Set<String> buildingSet,
                                           Set<BuildingRoomProprietorEntity> brpSet) {

        //先导入楼栋，已存的楼栋需要更新CheckStatu==9，没有就插入
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tGroupBuildingFId", groupBuildingId);

        List<Building> buildingList = buildingBaseService.getBuildingByCondition(paramMap);
        List<Building> willBeUpdatedBuildingList = new ArrayList<Building>(); //待更新状态的楼栋

        for (Building building : buildingList) {
            if (buildingSet.remove(building.getName())) {//剔除已存在的楼栋
                if (!building.getCheckStatus().equals(RoomDict.CheckStatus.ShenHeTongGuo)
                        && !building.getCheckStatus().equals(RoomDict.CheckStatus.WuXuShenHe)) {
                    Building b = new Building();
                    b.setId(building.getId());
                    b.setCheckStatus(RoomDict.CheckStatus.WuXuShenHe);
                    willBeUpdatedBuildingList.add(b);
                }
            }
            updateBuildingIdForBrpSet(brpSet, building);
        }

        if (willBeUpdatedBuildingList.size() > 0) {//已存在但用户看不见的楼栋需要更新CheckStatu==9
            buildingBaseService.updateBuildingBatch(willBeUpdatedBuildingList);
        }
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        if (!buildingSet.isEmpty()) {
            List<BigInteger> idList = uuidManager.getNextUuidBigInteger(SEQConstants.t_building, buildingSet.size());
            List<Building> willInsertBuildingList = new ArrayList<Building>();
            int i = 0;
            for(String buildName: buildingSet){
                Building b = new Building();
                b.setId(idList.get(i++));
                b.setName(buildName);
                b.setNameCharOrder(StringUtils.getSubCharString(buildName));
                b.setNameDigitOrder(StringUtils.getSubDigitString(buildName));
                b.settGroupBuildingFId(groupBuildingId);
                b.setSys0AddTime(now);
                b.setCheckStatus(RoomDict.CheckStatus.WuXuShenHe);
                willInsertBuildingList.add(b);
                updateBuildingIdForBrpSet(brpSet, b);
            }
            buildingBaseService.insertBuildingBatch(willInsertBuildingList);
        }


        //插入新的房间及业主
        if (!brpSet.isEmpty()) {
            List<PropertyProprietor> propertyProprietorWillInsertList = new ArrayList<PropertyProprietor>();
            List<BigInteger> ppIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_property_proprietor, brpSet.size());

            List<RealRoom> realRoomWillInsertList = new ArrayList<RealRoom>(brpSet.size());
            int i = 0;
            for (BuildingRoomProprietorEntity brp : brpSet) {
                //业主
                PropertyProprietor pp = new PropertyProprietor();
                pp.setId(ppIdList.get(i));
                pp.setName(brp.getProprietorName());
                pp.setPhone(brp.getProprietorPhone());
                pp.setIdentifyNo(brp.getProprietorIdNumber());
                propertyProprietorWillInsertList.add(pp);
                //房间
                RealRoom rr = new RealRoom();
                rr.setId(brp.getRealRoomId());
                rr.settBuildingFId(brp.getBuildingId());
                rr.setNum(StringUtils.isEmpty(brp.getRoomUnit()) ? brp.getRoomNumber() : brp.getRoomUnit() + "-" + brp.getRoomNumber());
                rr.setNumTail(brp.getRoomNumber());
                rr.setNumTailCharOrder(StringUtils.getSubCharString(rr.getNumTail()));
                rr.setNumTailDigitOrder(StringUtils.getSubDigitString(rr.getNumTail()));
                rr.setUnitName(brp.getRoomUnit());
                rr.setRoomSize(brp.getRoomSize());
                rr.setPropertyFeePerM2(brp.getRoomManagerPrice());
                rr.setCheckStatus(RoomDict.CheckStatus.WuXuShenHe);
                rr.settPropertyProprietorFId(pp.getId());
                brp.setRealRoomId(rr.getId());
                realRoomWillInsertList.add(rr);
                i++;
            }
            propertyProprietorBaseService.insertPropertyProprietorBatch(propertyProprietorWillInsertList);
            realRoomBaseService.insertRealRoomBatch(realRoomWillInsertList);
        }
    }

    /**
     * 更新BuildingRoomProprietorEntity中的BuildingId
     *
     * @param brpSet
     * @param building
     */
    private void updateBuildingIdForBrpSet(Set<BuildingRoomProprietorEntity> brpSet, Building building) {
        for (BuildingRoomProprietorEntity brp : brpSet) {
            if (building.getName().equals(brp.getBuildingName())) {
                brp.setBuildingId(building.getId());
            }
        }
    }
}
