package com.cnfantasia.server.ms.advertise.dao;


import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.operation.entity.AddressIdEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.ms.advertise.entity.AdvQryParam;
import com.cnfantasia.server.ms.advertise.entity.EbuyAdvertiseDto;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;

public interface IAdvertiseForOmsDao{

    public List<Map<String, Object>> getShelfProductForAdv(String qryStr, BigInteger supplyMerchantId, Integer appType, PageModel pageModel);

    public List<EbuyAdvertiseDto> getThemeProductAdvList(AdvQryParam param, PageModel pageModel);

    public Long getThemeProductAdvListCount(AdvQryParam param);

    public List<Map<String, Object>> getThemeProductAdvProductList(BigInteger advId);

    public List<Map<String, Object>> getAdvAreaByAdvId(BigInteger advId, Integer advType);

    public Integer deletePromoteShelfProductsByAdvId(BigInteger advId);

    public Integer deleteSaEbSupplyByAdvId(BigInteger advId, Integer advType);

    public AddressIdEntity getAddressIds(BigInteger countryId, BigInteger provinceId, BigInteger cityId, BigInteger blockId, BigInteger gbId);

    public List<EbuyAdvertiseDto> getAlertAdvList(AdvQryParam param, PageModel pageModel);

    public Long getAlertAdvListCount(AdvQryParam param);
    
    public List<GroupBuildingSimpleEntity> getGbs();
}
