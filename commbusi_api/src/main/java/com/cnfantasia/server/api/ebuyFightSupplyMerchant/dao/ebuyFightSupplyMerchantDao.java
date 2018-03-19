package com.cnfantasia.server.api.ebuyFightSupplyMerchant.dao;

import com.cnfantasia.server.api.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchantDto;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.domainbase.ebuyFightMerchantHasGroupBuilding.entity.EbuyFightMerchantHasGroupBuilding;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kangduo on 2016/5/25.
 */
public class ebuyFightSupplyMerchantDao extends AbstractBaseDao implements IEbuyFightSupplyMerchantDao{


    @Override
    public List<EbuyFightSupplyMerchantDto> getFightMerchantList(EbuyFightSupplyMerchantDto dto, PageModel pageModel) {
        Map<String, Object> param = MapConverter.toMap(dto);
        if (param == null) {
            param = new HashMap<String, Object>();
        }
        if (pageModel != null) {
            param.putAll(pageModel.toMap());
        }
        return sqlSession.selectList("ebuyFightSupplyMerchant.getFightMerchantList", param);
    }

    @Override
    public BigInteger getFightMerchantCount(EbuyFightSupplyMerchantDto dto) {
        return sqlSession.selectOne("ebuyFightSupplyMerchant.getFightMerchantCount", MapConverter.toMap(dto));
    }

    public List<EbuyFightMerchantHasGroupBuilding> getEbuyFightMerchantHasGbByGbidAndProductId(BigInteger gbId, BigInteger productId) {
        Map<String, Object> param = new HashMap<String, Object>();
        if (gbId != null) {
            param.put("gbId", gbId);
        }
        if (productId != null) {
            param.put("productId", productId);
        }
        return sqlSession.selectList("ebuyFightSupplyMerchant.getEbuyFightMerchantHasGbByGbidAndProductId", param);
    }
}
