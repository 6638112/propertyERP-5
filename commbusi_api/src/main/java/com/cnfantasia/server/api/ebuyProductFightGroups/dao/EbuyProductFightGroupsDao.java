package com.cnfantasia.server.api.ebuyProductFightGroups.dao;

import com.cnfantasia.server.api.coupon.dao.ICouponDao;
import com.cnfantasia.server.api.ebuyProductFightGroups.entity.EbuyProductFightGroupsDto;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kangduo on 2016.05.27
 */
public class EbuyProductFightGroupsDao extends AbstractBaseDao implements IEbuyProductFightGroupsDao {

    @Override
    public List<EbuyProductFightGroupsDto> getEbuyProductFightGroupsByCondition(EbuyProductFightGroupsDto dto, PageModel pageModel) {
        Map<String, Object> param = MapConverter.toMap(dto);
        if (pageModel != null) {
            param.put("begin", pageModel.getBegin());
            param.put("length", pageModel.getLength());
        }
        return sqlSession.selectList("eubyProductFightGroups.getEbuyProductFightGroups", param);
    }

    @Override
    public BigInteger getEbuyProductFightGroupsCount(EbuyProductFightGroupsDto dto) {
        return sqlSession.selectOne("eubyProductFightGroups.getEbuyProductFightGroupsCount", MapConverter.toMap(dto));
    }

    @Override
    public Integer updateProductFightGroupsPriceByProductId(BigInteger productId, Long price) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("productId", productId);
        param.put("price", price);
        return sqlSession.delete("eubyProductFightGroups.updateProductFightGroupsPriceByProductId", param);
    }
}
