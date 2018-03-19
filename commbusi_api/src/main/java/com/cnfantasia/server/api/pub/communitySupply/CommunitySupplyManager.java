package com.cnfantasia.server.api.pub.communitySupply;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.dredge.entity.DredgeTypeEntity;
import com.cnfantasia.server.api.dredge.entity.DredgeTypeForUser;
import com.cnfantasia.server.api.dredge.service.DredgeService;
import com.cnfantasia.server.api.fileServer.constant.FileServerConstant;
import com.cnfantasia.server.api.fileServer.service.FileServerService;
import com.cnfantasia.server.api.pub.sysParam.SysParamManager;
import com.cnfantasia.server.business.pub.communitySupply.ICommunitySupplyManager;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domainbase.addressBlock.service.IAddressBlockBaseService;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.domainbase.dredgeTypePriceConfig.entity.DredgeTypePriceConfig;
import com.cnfantasia.server.domainbase.dredgeTypePriceConfig.service.IDredgeTypePriceConfigBaseService;
import com.gexin.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.*;

/**
 * 维修类型缓存
 */
public class CommunitySupplyManager implements ICommunitySupplyManager {

    //上门服务大类
    private static final String Dredge_Level1 = "communitySupplyType";
    //上门服务子类
    private static final String Dredge_Level2_Prefix = "dredgeType_";
    //缓存过期时间一小时
    private static final int Expire_Time_Seconds = 60 * 60;

    private Date cacheDate = null;

    private Map<String, List<? extends Object>> communitySupplyMap = Collections.synchronizedMap(new HashMap<String, List<? extends Object>>());

    private DredgeService dredgeService;
    public void setDredgeService(DredgeService dredgeService) {
        this.dredgeService = dredgeService;
    }

    private FileServerService fileServerService;
    public void setFileServerService(FileServerService fileServerService) {
        this.fileServerService = fileServerService;
    }

    private ISysParamManager sysParamManager;
    public void setSysParamManager(SysParamManager sysParamManager) {
        this.sysParamManager = sysParamManager;
    }

    private IAddressBlockBaseService addressBlockBaseService;
    public void setAddressBlockBaseService(IAddressBlockBaseService addressBlockBaseService) {
        this.addressBlockBaseService = addressBlockBaseService;
    }

    @Resource
    private IDredgeTypePriceConfigBaseService dredgeTypePriceConfigBaseService;

    /**
     * 获取上门服务大类
     * @return
     */
    @Override
    public List<Map<String, Object>> getCommunitySupplyTypeList(List<BigInteger> addrCodeIdList) {
        //检查缓存超时
        checkCacheDate();

        if (communitySupplyMap.get(Dredge_Level1 + "_" + JSON.toJSONString(addrCodeIdList)) == null) {
            List<CommunitySupplyType> types = dredgeService.getCommunitySupplyTypesInDredgeType(addrCodeIdList);
            List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
            String iconBasePath = sysParamManager.getSysParaValue(SysParamKey.COMMUNITY_SUPPLY_TYPE_ICO_BASEPATH);
            for (CommunitySupplyType type : types) {
                Map<String, Object> tmpMap = new HashMap<String, Object>();
                tmpMap.put("id", type.getId());
                tmpMap.put("name", type.getName());
                tmpMap.put("type", 1);
                tmpMap.put("picUrl",
                        StringUtils.isEmpty(type.getIconBigUrl()) ? FileServerConstant.DEFAULT_NULL_PIC_URL
                                : fileServerService.getAccessUrl(iconBasePath + type.getIconBigUrl(), type));
                resList.add(tmpMap);
            }
            communitySupplyMap.put(Dredge_Level1, resList);
        }
        return (List<Map<String, Object>>) communitySupplyMap.get(Dredge_Level1);
    }

    /**
     * 获取指定类型的服务小类，并判断指定block是否有此服务
     * @param parentTypeId 维修类型
     * @param blockId 区ID
     * @return
     */
    @Override
    @Deprecated//从507版本废弃不用
    public List<DredgeTypeForUser> getDredgeTypeListByParentId(BigInteger parentTypeId, String blockId, BigInteger managementFId) {
        //检查缓存超时
        checkCacheDate();
        String desStr = Dredge_Level2_Prefix + blockId + "_" + parentTypeId + "_" + managementFId;
        if (communitySupplyMap.get(desStr) == null) {
            BigInteger cityId = blockId == null ? null : addressBlockBaseService.getAddressBlockBySeqId(new BigInteger(blockId)).gettCityFId();
            //查询指定类型下的所有服务
            //先按照管理处查询，没有数据则进行城市查询
            List<DredgeTypeEntity> dredgeTypeList = new ArrayList<>();//dredgeService.getDredgeTypeByParentTypeId(parentTypeId, null, managementFId);
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("tPropertyManagementFId", managementFId);
            List<DredgeTypePriceConfig> dredgeTypePriceConfigByCondition = dredgeTypePriceConfigBaseService.getDredgeTypePriceConfigByCondition(paraMap);
            if(DataUtil.isEmpty(dredgeTypePriceConfigByCondition)) {//根据管理处查询为空则 范围放大至 城市  --- 兼容老版本数据
//                dredgeTypeList = dredgeService.getDredgeTypeByParentTypeId(parentTypeId, cityId, null);
            }

            //转成指定格式
            List<DredgeTypeForUser> resList = new ArrayList<DredgeTypeForUser>();
            if (!DataUtil.isEmpty(dredgeTypeList)) {
                for (DredgeTypeEntity dredgeType : dredgeTypeList) {
                    //查询是否开通此服务
                    int isOpenDredgeService = parentTypeId.compareTo(DredgeConstant.ParentCommunitySupplyType.WYBX) == 0 ?
                            1 : dredgeService.qryIsOpenDredgeService4Block(blockId, String.valueOf(dredgeType.getId()));
                    DredgeTypeForUser dredgeTypeForUser = dredgeService.getDredgeTypeForUser(dredgeType);
                    dredgeTypeForUser.setParentName(dredgeType.getParentName());
                    dredgeTypeForUser.setIsOpenService(isOpenDredgeService);
                    resList.add(dredgeTypeForUser);
                }
            }

            //加入缓存
            communitySupplyMap.put(desStr, resList);
        }
        return (List<DredgeTypeForUser>) communitySupplyMap.get(desStr);
    }

    @Override
    public void refreshCache() {
        cacheDate = new Date();
        communitySupplyMap = Collections.synchronizedMap(new HashMap<String, List<? extends Object>>());
    }

    private void checkCacheDate() {
        if (cacheDate == null) {
            cacheDate = new Date();
        }
        //缓存超时
        Date now = new Date();
        if (now.getTime() - cacheDate.getTime() > Expire_Time_Seconds * 1000) {
            refreshCache();
        }
    }
}
