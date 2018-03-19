package com.cnfantasia.server.api.ebuyFightSupplyMerchant.service;

import com.cnfantasia.server.api.ebuyFightSupplyMerchant.dao.IEbuyFightSupplyMerchantDao;
import com.cnfantasia.server.api.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchantDto;
import com.cnfantasia.server.api.ebuyorder.entity.DeliveryMethod;
import com.cnfantasia.server.api.ebuyorder.service.EbuyMerchantService;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyFightMerchantHasGroupBuilding.entity.EbuyFightMerchantHasGroupBuilding;
import com.cnfantasia.server.domainbase.ebuyFightMerchantHasGroupBuilding.service.IEbuyFightMerchantHasGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchant;
import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.service.IEbuyFightSupplyMerchantBaseService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kangduo on 2016/5/25.
 */
public class ebuyFightSupplyMerchantService implements IEbuyFightSupplyMerchantService {

    private IEbuyFightSupplyMerchantDao ebuyFightSupplyMerchantDao;

    private IUuidManager uuidManager;

    private IEbuyFightSupplyMerchantBaseService ebuyFightSupplyMerchantBaseService;

    private IEbuyFightMerchantHasGroupBuildingBaseService ebuyFightMerchantHasGroupBuildingBaseService;

    private EbuyMerchantService ebuyMerchantService;

    public void setEbuyMerchantService(EbuyMerchantService ebuyMerchantService) {
        this.ebuyMerchantService = ebuyMerchantService;
    }
    public void setEbuyFightSupplyMerchantDao(IEbuyFightSupplyMerchantDao ebuyFightSupplyMerchantDao) {
        this.ebuyFightSupplyMerchantDao = ebuyFightSupplyMerchantDao;
    }
    public void setUuidManager(IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }
    public void setEbuyFightSupplyMerchantBaseService(IEbuyFightSupplyMerchantBaseService ebuyFightSupplyMerchantBaseService) {
        this.ebuyFightSupplyMerchantBaseService = ebuyFightSupplyMerchantBaseService;
    }
    public void setEbuyFightMerchantHasGroupBuildingBaseService(IEbuyFightMerchantHasGroupBuildingBaseService ebuyFightMerchantHasGroupBuildingBaseService) {
        this.ebuyFightMerchantHasGroupBuildingBaseService = ebuyFightMerchantHasGroupBuildingBaseService;
    }

    @Override
    public List<EbuyFightSupplyMerchantDto> getFightMerchantList(EbuyFightSupplyMerchantDto dto, PageModel pageModel) {
        return ebuyFightSupplyMerchantDao.getFightMerchantList(dto, pageModel);
    }

    @Override
    public List<EbuyFightSupplyMerchantDto> getFightMerchantList(EbuyFightSupplyMerchantDto dto) {
        return getFightMerchantList(dto, null);
    }

    @Override
    public BigInteger getFightMerchantCount(EbuyFightSupplyMerchantDto dto) {
        return ebuyFightSupplyMerchantDao.getFightMerchantCount(dto);
    }

    @Override
    @Transactional
    public void addFightMerchant(EbuyFightSupplyMerchantDto dto) {
        EbuyFightSupplyMerchant ebuyFightSupplyMerchant = dto.getEbuyFightSupplyMerchant();
        List<BigInteger> buildingIds = new ArrayList<BigInteger>(dto.getBuildingIds());

        //自提点邮费方式
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("name", "免邮费");
        param.put("fee", 0);
        List<DeliveryMethod> methods = ebuyMerchantService.selectLastestDeliveryMethodCondition(param);
        Long deliveryMethod = methods.get(0).getId();
        //自提点处理
        BigInteger merchantId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_fight_supply_merchant);
        ebuyFightSupplyMerchant.setId(merchantId);
        ebuyFightSupplyMerchant.settEbuyDeliveryMethodFId(BigInteger.valueOf(deliveryMethod));
        ebuyFightSupplyMerchantBaseService.insertEbuyFightSupplyMerchant(ebuyFightSupplyMerchant);

        //自提点小区
        List<BigInteger> bIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_supply_merchant_has_group_building, buildingIds.size());
        EbuyFightMerchantHasGroupBuilding ebuyFightMerchantHasGroupBuilding = null;
        List<EbuyFightMerchantHasGroupBuilding> relations = new ArrayList<EbuyFightMerchantHasGroupBuilding>();
        for (int i = 0; i < buildingIds.size(); i++) {
            //todo 距离 和 时间 没加上
            ebuyFightMerchantHasGroupBuilding = new EbuyFightMerchantHasGroupBuilding();
            ebuyFightMerchantHasGroupBuilding.setId(bIds.get(i));
            ebuyFightMerchantHasGroupBuilding.settEbuyFightSupplyMerchantFId(merchantId);
            ebuyFightMerchantHasGroupBuilding.settGroupBuildingId(buildingIds.get(i));
            relations.add(ebuyFightMerchantHasGroupBuilding);
        }
        ebuyFightMerchantHasGroupBuildingBaseService.insertEbuyFightMerchantHasGroupBuildingBatch(relations);
    }

    @Override
    @Transactional
    public void updFightMerchant(EbuyFightSupplyMerchantDto dto) {
        EbuyFightSupplyMerchant ebuyFightSupplyMerchant = dto.getEbuyFightSupplyMerchant();
        ebuyFightSupplyMerchantBaseService.updateEbuyFightSupplyMerchant(ebuyFightSupplyMerchant);

        //删除之前的小区
        EbuyFightMerchantHasGroupBuilding hasGroupBuilding = new EbuyFightMerchantHasGroupBuilding();
        hasGroupBuilding.settEbuyFightSupplyMerchantFId(dto.getEbuyFightSupplyMerchant().getId());
        List<EbuyFightMerchantHasGroupBuilding> buildings = ebuyFightMerchantHasGroupBuildingBaseService.getEbuyFightMerchantHasGroupBuildingByCondition(MapConverter.toMap(hasGroupBuilding));
        List<BigInteger> ids = new ArrayList<BigInteger>();
        for (EbuyFightMerchantHasGroupBuilding building : buildings) {
            ids.add(building.getId());
        }
        if (ids.size() > 0) {
            ebuyFightMerchantHasGroupBuildingBaseService.deleteEbuyFightMerchantHasGroupBuildingLogicBatch(ids);
        }

        //自提点小区
        List<BigInteger> buildingIds = new ArrayList<BigInteger>(dto.getBuildingIds());
        List<BigInteger> bIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_supply_merchant_has_group_building, buildingIds.size());
        EbuyFightMerchantHasGroupBuilding ebuyFightMerchantHasGroupBuilding = null;
        List<EbuyFightMerchantHasGroupBuilding> relations = new ArrayList<EbuyFightMerchantHasGroupBuilding>();
        for (int i = 0; i < buildingIds.size(); i++) {
            ebuyFightMerchantHasGroupBuilding = new EbuyFightMerchantHasGroupBuilding();
            ebuyFightMerchantHasGroupBuilding.setId(bIds.get(i));
            ebuyFightMerchantHasGroupBuilding.settEbuyFightSupplyMerchantFId(dto.getEbuyFightSupplyMerchant().getId());
            ebuyFightMerchantHasGroupBuilding.settGroupBuildingId(buildingIds.get(i));
            relations.add(ebuyFightMerchantHasGroupBuilding);
        }
        ebuyFightMerchantHasGroupBuildingBaseService.insertEbuyFightMerchantHasGroupBuildingBatch(relations);
    }

    public List<EbuyFightMerchantHasGroupBuilding> getEbuyFightMerchantHasGbByGbidAndProductId(BigInteger gbId, BigInteger productId) {
        return ebuyFightSupplyMerchantDao.getEbuyFightMerchantHasGbByGbidAndProductId(gbId, productId);
    }
}
