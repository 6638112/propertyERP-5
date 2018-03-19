package com.cnfantasia.server.ms.advertise.service;


import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.ms.advertise.entity.AdvQryParam;
import com.cnfantasia.server.ms.advertise.entity.AdvertiseDto;
import com.cnfantasia.server.ms.advertise.entity.EbuyAdvertiseDto;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;

public interface IAdvertiseForOmsService {

    public List<Map<String, Object>> getShelfProductForAdv(String qryStr, BigInteger supplyMerchantId, Integer appType, PageModel pageModel);

    public List<EbuyAdvertiseDto> getThemeProductAdvList(AdvQryParam param, PageModel pageModel);

    public Long getThemeProductAdvListCount(AdvQryParam param);

    public List<EbuyAdvertiseDto> getAlertAdvList(AdvQryParam param, PageModel pageModel);

    public Long getAlertAdvListCount(AdvQryParam param);

    public List<Map<String, Object>> getThemeProductAdvProductList(BigInteger advId);

    public List<Map<String, Object>> getAdvAreaByAdvId(BigInteger advId, Integer advType);

    public void addThemeProductAdv(AdvertiseDto dto);

    public void updThemeProductAdv(AdvertiseDto dto);

    public void addAlertAdv(AdvertiseDto dto);

    public void updAlertAdv(AdvertiseDto dto);
    
    /**
     * <p>广告范围处理</p>
     * <p>
     * 首页弹窗、到家：ebSupplyType=4<br>
     * 街坊ebSupplyType=2<br>
     * 拦腰ebSupplyType=3
     * </p>
     * 
     * @param areaType
     * @param advId
     * @param cityIds
     * @param gbIds
     * @param ebSupplyType
     */
    public void dealServiceArea(int areaType, BigInteger advId, List<BigInteger> cityIds,List<BigInteger> blockIds, List<BigInteger> gbIds, Integer ebSupplyType);
    
    public List<GroupBuildingSimpleEntity> getGbs();
}
