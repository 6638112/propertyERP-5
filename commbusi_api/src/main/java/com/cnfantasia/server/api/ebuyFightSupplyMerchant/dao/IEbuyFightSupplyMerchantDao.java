package com.cnfantasia.server.api.ebuyFightSupplyMerchant.dao;

import com.cnfantasia.server.api.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchantDto;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyFightMerchantHasGroupBuilding.entity.EbuyFightMerchantHasGroupBuilding;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by kangduo on 2016/5/25.
 */
public interface IEbuyFightSupplyMerchantDao {
    public List<EbuyFightSupplyMerchantDto> getFightMerchantList(EbuyFightSupplyMerchantDto dto, PageModel pageModel);

    public BigInteger getFightMerchantCount(EbuyFightSupplyMerchantDto dto);

    public List<EbuyFightMerchantHasGroupBuilding> getEbuyFightMerchantHasGbByGbidAndProductId(BigInteger gbId, BigInteger productId);
}
