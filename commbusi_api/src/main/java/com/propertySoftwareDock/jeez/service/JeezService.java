package com.propertySoftwareDock.jeez.service;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.dao.ICommonRoomDao;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.plotproperty.entity.PayBillEntity;
import com.cnfantasia.server.api.propertyRepair.constant.PropertyRepairDict;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.api.room.entity.RealRoomEntity;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.common.xml.JaxbXMLUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.building.service.IBuildingBaseService;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.dredgeBill.service.IDredgeBillBaseService;
import com.cnfantasia.server.domainbase.gbSoftwareConfig.entity.GbSoftwareConfig;
import com.cnfantasia.server.domainbase.gbSoftwareConfig.service.IGbSoftwareConfigBaseService;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payBill.service.IPayBillBaseService;
import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;
import com.cnfantasia.server.domainbase.propertyProprietor.service.IPropertyProprietorBaseService;
import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;
import com.cnfantasia.server.domainbase.propertyRepair.service.IPropertyRepairBaseService;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.realRoom.service.IRealRoomBaseService;
import com.cnfantasia.server.domainbase.realroomSoftwareFee.entity.RealroomSoftwareFee;
import com.cnfantasia.server.domainbase.realroomSoftwareFee.service.IRealroomSoftwareFeeBaseService;
import com.cnfantasia.server.domainbase.realroomSoftwareMapper.entity.RealroomSoftwareMapper;
import com.cnfantasia.server.domainbase.realroomSoftwareMapper.service.IRealroomSoftwareMapperBaseService;
import com.propertySoftwareDock.base.dao.IPropertySoftwareDockDao;
import com.propertySoftwareDock.base.entity.*;
import com.propertySoftwareDock.base.exception.PropertySoftwareDockException;
import com.propertySoftwareDock.base.service.IPropertySoftwareDockService;
import com.propertySoftwareDock.jeez.entity.*;
import com.propertySoftwareDock.jeez.util.JeezUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.transaction.annotation.Transactional;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.math.BigInteger;
import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @ClassName: JeezService
 * @Date: 2016-11-17 20:06
 * @Auther: kangduo
 * @Description:(Jeez对接Service层)
 */
public class JeezService implements IPropertySoftwareDockService {

    private Log logger = LogFactory.getLog(getClass());
    private static final String reqCount = "300";
    private static final Object lock = new Object();

    private IGbSoftwareConfigBaseService gbSoftwareConfigBaseService;
    public void setGbSoftwareConfigBaseService(IGbSoftwareConfigBaseService gbSoftwareConfigBaseService) {
        this.gbSoftwareConfigBaseService = gbSoftwareConfigBaseService;
    }

    private IPropertySoftwareDockDao propertySoftwareDockDao;
    public void setPropertySoftwareDockDao(IPropertySoftwareDockDao propertySoftwareDockDao) {
        this.propertySoftwareDockDao = propertySoftwareDockDao;
    }

    private IRealroomSoftwareFeeBaseService realroomSoftwareFeeBaseService;
    public void setRealroomSoftwareFeeBaseService(IRealroomSoftwareFeeBaseService realroomSoftwareFeeBaseService) {
        this.realroomSoftwareFeeBaseService = realroomSoftwareFeeBaseService;
    }

    private IPayBillBaseService payBillBaseService;
    public void setPayBillBaseService(IPayBillBaseService payBillBaseService) {
        this.payBillBaseService = payBillBaseService;
    }

    private IRealroomSoftwareMapperBaseService realroomSoftwareMapperBaseService;
    public void setRealroomSoftwareMapperBaseService(IRealroomSoftwareMapperBaseService realroomSoftwareMapperBaseService) {
        this.realroomSoftwareMapperBaseService = realroomSoftwareMapperBaseService;
    }

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

    private ICommonRoomDao commonRoomDao;
    public void setCommonRoomDao(ICommonRoomDao commonRoomDao) {
        this.commonRoomDao = commonRoomDao;
    }

    private ISysParamManager sysParamManager;
    public void setSysParamManager(ISysParamManager sysParamManager) {
        this.sysParamManager = sysParamManager;
    }

    private IPropertyRepairBaseService propertyRepairBaseService;
    public void setPropertyRepairBaseService(IPropertyRepairBaseService propertyRepairBaseService) {
        this.propertyRepairBaseService = propertyRepairBaseService;
    }

    private IDredgeBillBaseService dredgeBillBaseService;
    public void setDredgeBillBaseService(IDredgeBillBaseService dredgeBillBaseService) {
        this.dredgeBillBaseService = dredgeBillBaseService;
    }

    /**
     * 同步数据，正常流程返回null，不正常流程返回结果
     */
    @Override
    @Transactional
    public String initData(BigInteger gbId) throws PropertySoftwareDockException {
        StringBuilder sb = new StringBuilder();
        //取对接需要的信息
        GbSoftwareConfig gbConfig = new GbSoftwareConfig();
        gbConfig.setGbId(gbId);
        List<GbSoftwareConfig> gbSoftwareConfigList = gbSoftwareConfigBaseService
                .getGbSoftwareConfigByCondition(MapConverter.toMap(gbConfig));
        if (gbSoftwareConfigList.isEmpty()) {
            throw new PropertySoftwareDockException(PropertySoftwareDockException.No_Such_Gb);
        }
        gbConfig = gbSoftwareConfigList.get(0);
        //拿极致的房间及用户
        Map<JeezHouse, List<JeezUser>> userHouseMap = getHouseAndUserMap(gbConfig);
        //一个房间如果有多个用户，则抛异常
        for (Map.Entry<JeezHouse, List<JeezUser>> entry : userHouseMap.entrySet()) {
            List<JeezUser> jeezUserList = entry.getValue();
//            JeezHouse jeezHouse = entry.getKey();
            if (jeezUserList != null && jeezUserList.size() > 1) {
            	for(int i = jeezUserList.size() -1; i > 0; i--) {
            		jeezUserList.remove(i);
            	}
//                sb.append("房间").append(jeezHouse.getBuildingName()).append(jeezHouse.getUnitNumber())
//                        .append(jeezHouse.getSimpleHouseNumber()).append("有多个业主；");
            }
        }
        if (!sb.toString().isEmpty()) {
            return sb.append("请联系物业修改。").toString();
        }
        //拿系统已配置的房间及对应customer
        List<RealRoomSysInfo> realRoomSysInfoList = propertySoftwareDockDao.getMapperedRealRoomSysInfoListByGbId(gbId);
        List<RealRoomSysInfo> realRoomSysInfoListForNew = propertySoftwareDockDao.getAllRealRoomSysInfoListByGbId(gbId);

        boolean hasThis;
        //找出本地有物业软件没有的房间配置，删除之
        List<BigInteger> delMapperList = new ArrayList<BigInteger>();
        for (RealRoomSysInfo realRoomSysInfo : realRoomSysInfoList) {
            hasThis = false;
            for (Map.Entry<JeezHouse, List<JeezUser>> entry : userHouseMap.entrySet()) {
                JeezHouse house = entry.getKey();
                if (realRoomSysInfo.getSoftwareRoomId().equals(house.getId())) {
                    hasThis = true;
                    break;
                }
            }
            if (!hasThis) {
                delMapperList.add(realRoomSysInfo.getRealRoomMapperId());
            }
        }
        if (!delMapperList.isEmpty()) {
            realroomSoftwareFeeBaseService.deleteRealroomSoftwareFeeLogicBatch(delMapperList);
        }

        //处理极致数据, 找出中的极致房间, 并处理之
        Map<JeezHouse, JeezUser> newJeezHouseUserMap = compareSoftwareAndSysInfo(realRoomSysInfoList, userHouseMap);
        dealNewJeezRoom(newJeezHouseUserMap, gbId, realRoomSysInfoListForNew);
        return null;
    }

    private void dealNewJeezRoom(Map<JeezHouse, JeezUser> newJeezHouseUserMap, BigInteger gbId, List<RealRoomSysInfo> realRoomSysInfoList) {
        JeezUser jeezUser;
        JeezHouse jeezHouse;
        RealroomSoftwareMapper mapper;
        List<RealRoom> updRealRoomList = new ArrayList<RealRoom>();
        List<RealroomSoftwareMapper> insertMapperList = new ArrayList<RealroomSoftwareMapper>();
        Set<String> buildingNameSet = new HashSet<String>();
        Set<BuildingRoomProprietorEntity> brpSet = new HashSet<BuildingRoomProprietorEntity>();
        List<PropertyProprietor> addProprietorList = new ArrayList<PropertyProprietor>();
        Set<PropertyProprietor> updProprietorSet = new HashSet<PropertyProprietor>();
        List<BigInteger> addPPIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_property_proprietor, newJeezHouseUserMap.size());
        int ppIdx = 0;
        for (Map.Entry<JeezHouse, JeezUser> entry : newJeezHouseUserMap.entrySet()) {
            jeezHouse = entry.getKey();
            jeezUser = entry.getValue();
            String unitName = jeezHouse.getUnitNumber().replaceAll("单元", "");
            RealRoom sysRealRoom = new RealRoom();
            boolean sysHasThisRoom = false;
            RealRoomSysInfo currRealRoomSysInfo = null;
            for (RealRoomSysInfo realRoomSysInfo : realRoomSysInfoList) {
                String sysUnitName = realRoomSysInfo.getRealRoom().getUnitName() == null ? "" : realRoomSysInfo.getRealRoom().getUnitName();
                if (realRoomSysInfo.getBuilding().getName().equals(jeezHouse.getBuildingName())
                        && sysUnitName.equals(unitName)
                        && realRoomSysInfo.getRealRoom().getNumTail().equals(jeezHouse.getSimpleHouseNumber())) {

                    sysHasThisRoom = true;
                    currRealRoomSysInfo = realRoomSysInfo;
                    sysRealRoom.setId(realRoomSysInfo.getRealRoomId());
                    sysRealRoom.setCheckStatus(realRoomSysInfo.getRealRoom().getCheckStatus());

                    realRoomSysInfoList.remove(realRoomSysInfo);
                    break;
                }
            }
            //如果系统没有该房间，插入记录并添加映射
            if (!sysHasThisRoom) {
                buildingNameSet.add(jeezHouse.getBuildingName());
                BuildingRoomProprietorEntity brp = new BuildingRoomProprietorEntity();
                brp.setBuildingName(jeezHouse.getBuildingName());
                if (!DataUtil.isEmpty(unitName)) {
                    brp.setRoomUnit(unitName);
                }
                brp.setRoomNumber(jeezHouse.getSimpleHouseNumber());
                brp.setProprietorName(jeezUser.getName());
                brp.setProprietorPhone(jeezUser.getMobile());
                brp.setSoftwareCustomerId(jeezUser.getId());
                brp.setSoftwareRoomId(jeezHouse.getId());
                brpSet.add(brp);
            } else {
                boolean roomAddIn = false;
                if (!sysRealRoom.getCheckStatus().equals(RoomDict.CheckStatus.WuXuShenHe)) {
                    roomAddIn = true;
                    sysRealRoom.setCheckStatus(RoomDict.CheckStatus.WuXuShenHe);
                    updRealRoomList.add(sysRealRoom);
                }
                //添加映射
                mapper = new RealroomSoftwareMapper();
                mapper.setSoftwareCustomerId(jeezUser.getId());
                mapper.settRealRoomFId(sysRealRoom.getId());
                mapper.setSoftwareRoomId(jeezHouse.getId());
                insertMapperList.add(mapper);
                //已有的房间，检查数据一致不一致，不一致则修改
                //本地房间未关联业主
                String userName = jeezUser.getName();
                String userPhone = jeezUser.getMobile();
                if (currRealRoomSysInfo.getPropertyProprietor() == null) {
                    PropertyProprietor addProprietor = new PropertyProprietor();
                    addProprietor.setName(userName);
                    addProprietor.setPhone(userPhone);
                    addProprietor.setId(addPPIds.get(ppIdx++));
                    addProprietorList.add(addProprietor);

                    //已加进内存，改值会跟着变
                    sysRealRoom.settPropertyProprietorFId(addProprietor.getId());
                    if (!roomAddIn) {
                        updRealRoomList.add(sysRealRoom);
                    }
                } else if (!userName.equals(currRealRoomSysInfo.getPropertyProprietor().getName())
                        || !userPhone.equals(currRealRoomSysInfo.getPropertyProprietor().getPhone())) { //业主信息有变化
                    PropertyProprietor updProprietor = new PropertyProprietor();
                    updProprietor.setId(currRealRoomSysInfo.getPropertyProprietor().getId());
                    updProprietor.setName(userName);
                    updProprietor.setPhone(userPhone);
                    updProprietorSet.add(updProprietor);
                }
            }
        }
        if (addProprietorList.size() > 0) {
            propertyProprietorBaseService.insertPropertyProprietorBatch(addProprietorList);
        }
        if (updProprietorSet.size() > 0) {
            propertyProprietorBaseService.updatePropertyProprietorBatch(new ArrayList<PropertyProprietor>(updProprietorSet));
        }
        if (updRealRoomList.size() > 0) {
            realRoomBaseService.updateRealRoomBatch(updRealRoomList);
        }
        if (insertMapperList.size() > 0) {
            List<BigInteger> mapperIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_realroom_software_mapper, insertMapperList.size());
            int idx = 0;
            for (RealroomSoftwareMapper realroomSoftwareMapper : insertMapperList) {
                realroomSoftwareMapper.setId(mapperIds.get(idx++));
            }
            realroomSoftwareMapperBaseService.insertRealroomSoftwareMapperBatch(insertMapperList);
        }
        if (buildingNameSet.size() > 0 && brpSet.size() > 0) {
            List<BigInteger> realRoomIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_real_room, brpSet.size());
            int idx = 0;
            for (BuildingRoomProprietorEntity entity : brpSet) {
                entity.setRealRoomId(realRoomIds.get(idx++));
            }
            propertySoftwareDockDao.addBuildingRoomProprietor(gbId, buildingNameSet, brpSet);
            List<RealroomSoftwareMapper> mapperList = new ArrayList<RealroomSoftwareMapper>(brpSet.size());
            List<BigInteger> mapperIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_realroom_software_mapper, brpSet.size());
            idx = 0;
            for (BuildingRoomProprietorEntity entity : brpSet) {
                RealroomSoftwareMapper mapperAdd = new RealroomSoftwareMapper();
                mapperAdd.setId(mapperIds.get(idx++));
                mapperAdd.setSoftwareRoomId(entity.getSoftwareRoomId());
                mapperAdd.settRealRoomFId(entity.getRealRoomId());
                mapperAdd.setSoftwareCustomerId(entity.getSoftwareCustomerId());
                mapperList.add(mapperAdd);
            }
            realroomSoftwareMapperBaseService.insertRealroomSoftwareMapperBatch(mapperList);
        }
    }
    /**
     * 根据已有信息找出系统与极致的差别，处理已配置房间，找出未配置房间
     * @param realRoomSysInfoList
     * @param userHouseMap
     * @return 新增加的物业房间
     */
    private Map<JeezHouse, JeezUser> compareSoftwareAndSysInfo(List<RealRoomSysInfo> realRoomSysInfoList,
                                                               Map<JeezHouse, List<JeezUser>> userHouseMap) {
        boolean hasThis;
        String num, numTail, unitName, buildingName, userName, userPhone, userId;
        RealroomSoftwareMapper realroomSoftwareMapper;
        RealRoom updRealRoom;
        Building updBuilding;
        PropertyProprietor updProprietor;
        Map<JeezHouse, JeezUser> resMap = new HashMap<JeezHouse, JeezUser>();
        List<RealRoom> updRealRoomList = new ArrayList<RealRoom>();
        List<RealroomSoftwareMapper> updMapperRoomList = new ArrayList<RealroomSoftwareMapper>();
        Set<Building> updBuildingSet = new HashSet<Building>();
        Set<PropertyProprietor> updProprietorSet = new HashSet<PropertyProprietor>();
        List<PropertyProprietor> addProprietorList = new ArrayList<PropertyProprietor>();
        int idx = 0;
        List<BigInteger> addPPIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_property_proprietor, realRoomSysInfoList.size());
        for (Map.Entry<JeezHouse, List<JeezUser>> entry : userHouseMap.entrySet()) {
            JeezHouse house = entry.getKey();
            JeezUser user = entry.getValue().get(0);
            hasThis = false;//标记之前是否存在此配置，不存在则记录并后面处理
            //信息格式化
            numTail = house.getSimpleHouseNumber();
            unitName = house.getUnitNumber().replaceAll("单元", "");
            num = DataUtil.isEmpty(unitName) ? numTail : unitName + "-" + numTail;
            buildingName = house.getBuildingName();
            userName = user.getName();
            userPhone = user.getMobile();
            userId = user.getId();

            for (RealRoomSysInfo realRoomSysInfo : realRoomSysInfoList) {
                updRealRoom = null;
                if (house.getId().equals(realRoomSysInfo.getSoftwareRoomId())) {
                    hasThis = true;
                    if (!userId.equals(realRoomSysInfo.getSoftwareCustomerId())) {
                        realroomSoftwareMapper = new RealroomSoftwareMapper();
                        realroomSoftwareMapper.setId(realRoomSysInfo.getRealRoomMapperId());
                        realroomSoftwareMapper.setSoftwareCustomerId(realRoomSysInfo.getSoftwareCustomerId());
                        updMapperRoomList.add(realroomSoftwareMapper);
                    }
                    //房间信息有变化
                    if (!realRoomSysInfo.getRealRoom().getNum().equals(num)
                            || (realRoomSysInfo.getRealRoom().getUnitName() == null && !"".equals(unitName))
                            || (realRoomSysInfo.getRealRoom().getUnitName() != null && !realRoomSysInfo.getRealRoom().getUnitName().equals(unitName))) {
                        updRealRoom = new RealRoom();
                        updRealRoom.setId(realRoomSysInfo.getRealRoomId());
                        updRealRoom.setNum(num);
                        updRealRoom.setNumTail(numTail);
                        updRealRoom.setUnitName(unitName);
                    }
                    //楼栋信息有变化
                    if (!realRoomSysInfo.getBuilding().getName().equals(buildingName)) {
                        updBuilding = new Building();
                        updBuilding.setId(realRoomSysInfo.getBuilding().getId());
                        updBuilding.setName(buildingName);
                        updBuildingSet.add(updBuilding);
                    }
                    //本地房间未关联业主
                    if (realRoomSysInfo.getPropertyProprietor() == null) {
                        PropertyProprietor addProprietor = new PropertyProprietor();
                        addProprietor.setName(userName);
                        addProprietor.setPhone(userPhone);
                        addProprietor.setId(addPPIds.get(idx++));
                        addProprietorList.add(addProprietor);
                        if (updRealRoom == null) {
                            updRealRoom = new RealRoom();
                            updRealRoom.setId(realRoomSysInfo.getRealRoomId());
                        }
                        updRealRoom.settPropertyProprietorFId(addProprietor.getId());
                    } else if (!userName.equals(realRoomSysInfo.getPropertyProprietor().getName())
                            || !userPhone.equals(realRoomSysInfo.getPropertyProprietor().getPhone())) { //业主信息有变化
                        updProprietor = new PropertyProprietor();
                        updProprietor.setId(realRoomSysInfo.getPropertyProprietor().getId());
                        updProprietor.setName(userName);
                        updProprietor.setPhone(userPhone);
                        updProprietorSet.add(updProprietor);
                    }
                    if (updRealRoom != null) {
                        updRealRoomList.add(updRealRoom);
                    }
                    break;
                }
            }
            //没有当前房间且返回的数据房间号不为空
            if (!hasThis && !StringUtils.isEmpty(house.getSimpleHouseNumber())) {
                resMap.put(house, user);
            }
        }
        if (updProprietorSet.size() > 0) {
            propertyProprietorBaseService.updatePropertyProprietorBatch(new ArrayList<PropertyProprietor>(updProprietorSet));
        }
        if (addProprietorList.size() > 0) {
            propertyProprietorBaseService.insertPropertyProprietorBatch(addProprietorList);
        }
        if (updRealRoomList.size() > 0) {
            realRoomBaseService.updateRealRoomBatch(updRealRoomList);
        }
        if (updMapperRoomList.size() > 0) {
            realroomSoftwareMapperBaseService.updateRealroomSoftwareMapperBatch(updMapperRoomList);
        }
        if (updBuildingSet.size() > 0) {
            buildingBaseService.updateBuildingBatch(new ArrayList<Building>(updBuildingSet));
        }
        if (updRealRoomList.size() > 0) {
            realRoomBaseService.updateRealRoomBatch(updRealRoomList);
        }

        return resMap;
    }

    /**
     * 获取单个小区所有用户的房间信息
     * @param gbConfig 小区配置
     * @return 所有用户的房间信息
     * @throws JeezException 连极致服务器产生的异常
     */
    private Map<JeezHouse, List<JeezUser>> getHouseAndUserMap(GbSoftwareConfig gbConfig) throws PropertySoftwareDockException {
        logger.info("物业软件对接，同步数据开始===============================");
        //取极致数据
        String res;
        JeezResult result;
        String minId = "0";
        Map<JeezHouse, List<JeezUser>> houseAndUser = new HashMap<JeezHouse, List<JeezUser>>();
        List<JeezUser> totalJeezUsers = new ArrayList<JeezUser>();
        try {
            KeyPair keyPair = JeezUtil.getRSAKeyPair(gbConfig.getRsaPublicKey(), gbConfig.getRsaPrivateKey());
            res = JeezStub.getUsersInfo(gbConfig.getSoftwareGbId(), reqCount, minId,
                    gbConfig.getDatabaseCode(), keyPair, gbConfig.getIpAddress());
            result = JaxbXMLUtil.convertToJavaBean(res, JeezResult.class);
            List<JeezUser> users = result.getCustomers();
            if (!DataUtil.isEmpty(users)) {
                totalJeezUsers.addAll(users);
            }
            //分批次取，如果取的数量和预设一样，证明还有，继续取
            while (!DataUtil.isEmpty(users) && users.size() == Integer.parseInt(reqCount)) {
                res = JeezStub.getUsersInfo(gbConfig.getSoftwareGbId(), reqCount, minId,
                        gbConfig.getDatabaseCode(), keyPair, gbConfig.getIpAddress());
                result = JaxbXMLUtil.convertToJavaBean(res, JeezResult.class);
                users = result.getCustomers();
                totalJeezUsers.addAll(users);
            }
            //按用户取房间信息，并对应相应的房间与用户
            for (JeezUser user : totalJeezUsers) {
                res = JeezStub.getRoomInfo(user.getId(), gbConfig.getDatabaseCode(), keyPair, gbConfig.getIpAddress());
                result = JaxbXMLUtil.convertToJavaBean(res, JeezResult.class);
                List<JeezHouse> houses = result.getHouses();
                if (!DataUtil.isEmpty(houses)) {
                    for (JeezHouse house : houses) {
                        List<JeezUser> jeezUserList = houseAndUser.get(house) == null ? new ArrayList<JeezUser>() : houseAndUser.get(house);
                        jeezUserList.add(user);
                        houseAndUser.put(house, jeezUserList);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("物业软件对接，同步数据结束，有异常：" + e.getMessage() + "=============");
            throw new PropertySoftwareDockException(PropertySoftwareDockException.Get_Data_Error);
        }
        //如果房间为空，抛异常
        if (houseAndUser.isEmpty()) {
            logger.info("物业软件对接，同步数据结束，无异常，但未拿到房间===============");
            throw new PropertySoftwareDockException(PropertySoftwareDockException.Empty_Gb_House);
        }
        logger.info("物业软件对接，同步数据结束，无异常，顺利====================");
        return houseAndUser;
    }

    @Override
    public PayBillEntity getPropertyFeeItems(BigInteger realRoomId) {

        //查询当前是否已查询费用，有则使用当前的
        String today = getToday();
        logger.info("物业软件对接：查询房间费用开始，realRoomId=" + realRoomId + "today=" + today + "===============================");
        RealroomSoftwareFee softwareFee = new RealroomSoftwareFee();
        softwareFee.setDataReqDate(today);
        softwareFee.settRealRoomFId(realRoomId);
        List<RealroomSoftwareFee> feeList = realroomSoftwareFeeBaseService.
                getRealroomSoftwareFeeByCondition(MapConverter.toMap(softwareFee));
        if (!DataUtil.isEmpty(feeList) && feeList.get(0).getNotifyCount() != 99) {
            softwareFee = feeList.get(0);
            PayBill payBill = new PayBill();
            payBill.settRealroomSoftwareFeeFId(softwareFee.getId());
            payBill.setIsPay(1);
            //如果已付款，不再显示账单
            int payCount = payBillBaseService.getPayBillCount(MapConverter.toMap(payBill));
            logger.info("物业软件对接：查询房间费用结束，系统有缓存，" + (payCount > 0 ? "已付款" : "未付款") + "softwareFeeId:" + softwareFee.getId());
            if (payCount > 0) {
                return null;
            } else {
                String feeData = softwareFee.getFeeData();
                RealRoomEntity entity = commonRoomDao.selectRealRoomById(realRoomId);
                return JeezStub.getPayBillEntityBySoftwareFee(JSON.parseObject(feeData, JeezFeeEntity.class), entity, softwareFee.getId());
            }
        }

        //查对接基本信息
        RealRoomSoftwareInfo realRoomSoftwareInfo = propertySoftwareDockDao.getRealRoomSoftwareInfo(realRoomId);
        if (realRoomSoftwareInfo != null) {
            int nowDay = Integer.parseInt(today.split("-")[2]);
            //不可缴费日期返回null
            if (realRoomSoftwareInfo.getCannotConnectStartDate() != null
                    && realRoomSoftwareInfo.getCannotConnectEndDate() != null
                    && nowDay >= realRoomSoftwareInfo.getCannotConnectStartDate()
                    && nowDay <= realRoomSoftwareInfo.getCannotConnectEndDate()) {
                return null;
            }

            KeyPair keyPair = JeezUtil.getRSAKeyPair(realRoomSoftwareInfo.getRsaPublicKey(), realRoomSoftwareInfo.getRsaPrivateKey());
            String payBillXml;
            String key = RedisCachePrefix.is_Some_One_Query_Software_Property_Fee + realRoomId;
            try {
                synchronized (lock) {
                    //如果不为空，证明有人在查这个房间的费用，避免重复产生，直接返回null
                    boolean isQuerying = RedisCacheHandler.get(key) != null;
                    if (isQuerying) {
                        return null;
                    } else {
                        // 如果没人在查，下面会进行查，标记为已查，自动过期5秒
                        RedisCacheHandler.set(key, "Y", 5);
                    }
                }

                payBillXml = JeezStub.getPayBill(realRoomSoftwareInfo.getSoftwareHouseId(), realRoomSoftwareInfo.getSoftwareCustomerId(),
                        realRoomSoftwareInfo.getDbCode(), keyPair, realRoomSoftwareInfo.getServerAddress());
                Document doc = DocumentHelper.parseText(payBillXml);
                if (JeezStub.SuccessFlag.equals(JeezUtil.getElementValue(doc, "Flag"))) {
                    JeezFeeEntity feeEntity = JaxbXMLUtil.convertToJavaBean(payBillXml, JeezFeeEntity.class);
                    softwareFee.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_realroom_software_fee));
                    softwareFee.settRealRoomFId(realRoomId);
                    softwareFee.setDataReqDate(today);
                    softwareFee.setFeeData(feeEntity.toString());
                    softwareFee.setNotifyCount(0);
                    realroomSoftwareFeeBaseService.insertRealroomSoftwareFee(softwareFee);
                    logger.info("物业软件对接：查询房间费用结束，系统无缓存，webservice获取数据");
                    RealRoomEntity entity = commonRoomDao.selectRealRoomById(realRoomId);
                    //查询完数据，清除redis记录
                    RedisCacheHandler.clearCache(key);
                    return JeezStub.getPayBillEntityBySoftwareFee(feeEntity, entity, softwareFee.getId());
                }
                logger.info("物业软件对接：查询房间费用结束，系统无缓存，webservice未获取到数据" + payBillXml);
            } catch (Exception e) {
                logger.info("物业软件对接：查询房间费用结束，系统无缓存，webservice异常" + e.getMessage());
                e.printStackTrace();
                RedisCacheHandler.clearCache(key);
                return null;
            }
        }
        return null;
    }

    /**
     * 获取当前日期（避免刚好用户交完费变第二天还没来及销账又查，推迟4个小时）
     * @return 日期
     */
    private static String getToday() {
        Calendar c = Calendar.getInstance();
        if (c.get(Calendar.HOUR_OF_DAY) < 4) {
            c.add(Calendar.DAY_OF_MONTH, -1);
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    @Override
    @Transactional
    public void noticePaid(BigInteger payBillId) {
        PayBill payBill = payBillBaseService.getPayBillBySeqId(payBillId);
        boolean success;
        if (payBill == null || payBill.gettRealroomSoftwareFeeFId() == null) {
            return;
        }
        RealroomSoftwareFee softwareFee = realroomSoftwareFeeBaseService.
                getRealroomSoftwareFeeBySeqId(payBill.gettRealroomSoftwareFeeFId());
        RealRoomSoftwareInfo softwareInfo = propertySoftwareDockDao.getRealRoomSoftwareInfo(payBill.gettRealRoomFId());
        try {
            logger.info("物业软件对接：销账开始===============================");
            KeyPair keyPair = JeezUtil.getRSAKeyPair(softwareInfo.getRsaPublicKey(), softwareInfo.getRsaPrivateKey());
            success = JeezStub.payByBills(softwareInfo.getSoftwareHouseId(), softwareInfo.getSoftwareCustomerId(),
                    softwareInfo.getDbCode(), payBillId.toString(),
                    JSON.parseObject(softwareFee.getFeeData(), JeezFeeEntity.class), keyPair, softwareInfo.getServerAddress());
        } catch (Exception e) {
            logger.info("物业软件对接：销账对接异常" + e.getMessage());
            success = false;
            e.printStackTrace();
        }
        if (!success) {
            //失败后失败次数+1
            softwareFee.setNotifyCount(softwareFee.getNotifyCount() + 1);
            realroomSoftwareFeeBaseService.updateRealroomSoftwareFee(softwareFee);
            if (softwareFee.getNotifyCount() >= 3) {
                //失败三次发送通知邮件
                String notifyMail = sysParamManager.getSysParaValue(SysParamKey.PropertySoftwareNotifyFailMail);
                if (!DataUtil.isEmpty(notifyMail)) {
                    String content = "通知物业管理软件失败：<br>" +
                            "realRoomSoftwareInfo : " + JSON.toJSONString(softwareInfo) + "<br>" +
                            "realroomSoftwareFee  : " + JSON.toJSONString(softwareFee);
                    new MailSendThread("物业管理软件通知失败",content, notifyMail).start();
                }
            }
            logger.info("物业软件对接：销账失败");
        } else {
            logger.info("物业软件对接：销账成功");
            softwareFee.setNotifyCount(99);
            realroomSoftwareFeeBaseService.updateRealroomSoftwareFee(softwareFee);
        }
        logger.info("物业软件对接：销账结束=======================");
    }

    @Override
    public String pushPropertyRepair(RepairPushEntity pushEntity) throws Exception {
        RealRoomSoftwareInfo softwareInfo = pushEntity.getSoftwareInfo();
        DredgeBill dredgeBill = pushEntity.getDredgeBill();
        //数据不满足不推
        if (softwareInfo.getSoftwareHouseId() == null || softwareInfo.getDefaultRepairerId() == null) {
            return null;
        }
        String softwareBillNo;
        String expectTime = dredgeBill.getExpectdate();//需要格式：2016-12-21 12:00:00
        expectTime = expectTime.length() > 16 ? expectTime : expectTime + ":00";
        List<RequestFileEntity> picList = pushEntity.getPicList();
        String image1 = null, image2 = null, image3 = null;
        if (!DataUtil.isEmpty(picList)) {
            image1 = Base64.encode(picList.get(0).getFileContent());
            if (picList.size() > 1) {
                image2 = Base64.encode(picList.get(1).getFileContent());
            }
            if (picList.size() > 2) {
                image3 = Base64.encode(picList.get(2).getFileContent());
            }
        }

        KeyPair keyPair = JeezUtil.getRSAKeyPair(softwareInfo.getRsaPublicKey(), softwareInfo.getRsaPrivateKey());
        String repairContent = dredgeBill.getContent() == null ? "无" :dredgeBill.getContent();
        repairContent = "[" + pushEntity.getRepairTypeName() + "]" + repairContent;
        String xml = JeezStub.pushRepair(softwareInfo.getServerAddress(), softwareInfo.getSoftwareHouseId(),
                expectTime, repairContent, "本房间住户", dredgeBill.getTel(),
                softwareInfo.getDbCode(), softwareInfo.getSoftwareCustomerId(),image1, image2, image3, keyPair);
        JeezResult result = JaxbXMLUtil.convertToJavaBean(xml, JeezResult.class);
        //成功处理billNo,不成功抛异常待处理
        if (result.getFlag() == 1) {
            //提交服务申请成功，申请单号：FW000002
            softwareBillNo = result.getMessage().substring(result.getMessage().lastIndexOf("单号：") + 3);
        } else {
            throw new PropertySoftwareDockException(result.getMessage());
        }
        return softwareBillNo;
    }

    @Override
    public String synRepairDetail(RepairPushEntity entity) throws Exception {
        RealRoomSoftwareInfo softwareInfo = entity.getSoftwareInfo();
        PropertyRepair repair = entity.getRepair();
        //数据不满足不推
        if (softwareInfo.getSoftwareHouseId() == null || softwareInfo.getDefaultRepairerId() == null) {
            return null;
        }
        KeyPair keyPair = JeezUtil.getRSAKeyPair(softwareInfo.getRsaPublicKey(), softwareInfo.getRsaPrivateKey());
        String softwareBillId = repair == null ? entity.getDredgeBill().getSoftwareBillId() : repair.getSoftwareBillId();
        String xml = JeezStub.getRepairDetail(softwareInfo.getServerAddress(),
                softwareInfo.getSoftwareHouseId(), softwareBillId,
                softwareInfo.getDbCode(), softwareInfo.getSoftwareCustomerId(), keyPair);
        JeezResult result = JaxbXMLUtil.convertToJavaBean(xml, JeezResult.class);
        if (result.getFlag() == 1) {
            entity.setFailCount(0);
            JeezRepairDetail jeezRepairDetail = result.getRepairDetails().get(0);
            if ("已结束".equals(jeezRepairDetail.getStatus()) || "已完成".equals(jeezRepairDetail.getStatus())) {
                if (entity.getRepair() != null && !repair.getRepaireState().equals(PropertyRepairDict.RepairStatus.DONE)) {
                    repair.setRepaireState(PropertyRepairDict.RepairStatus.DONE);
                    repair.settPropertyRepairerFId(softwareInfo.getDefaultRepairerId());
                    repair.setFinishedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    repair.setFinishDesc("物业标注完成");
                    entity.setRepair(repair);

                    propertyRepairBaseService.updatePropertyRepair(repair);
                } else if (entity.getDredgeBill() != null && !entity.getDredgeBill().getStatus().equals(DredgeConstant.DredgeBill.DredgeBill_Status_FinishedHasComment)) {
                    DredgeBill dredgeBill = entity.getDredgeBill();
                    dredgeBill.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_FinishedHasComment);
                    dredgeBill.setPayAmount(0L);
                    dredgeBill.setPtbtAmount(0L);
                    entity.setDredgeBill(dredgeBill);

                    dredgeBillBaseService.updateDredgeBill(dredgeBill);
                }
            }
        } else {
            throw new PropertySoftwareDockException(result.getMessage());
        }
        return "success";
    }
}
