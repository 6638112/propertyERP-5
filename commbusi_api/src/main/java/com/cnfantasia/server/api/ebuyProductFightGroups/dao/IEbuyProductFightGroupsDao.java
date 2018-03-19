package com.cnfantasia.server.api.ebuyProductFightGroups.dao;

import com.cnfantasia.server.api.ebuyProductFightGroups.entity.EbuyProductFightGroupsDto;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by kangduo on 2016.05.27
 */
public interface IEbuyProductFightGroupsDao {

    public List<EbuyProductFightGroupsDto> getEbuyProductFightGroupsByCondition(EbuyProductFightGroupsDto dto, PageModel pageModel);

    public BigInteger getEbuyProductFightGroupsCount(EbuyProductFightGroupsDto dto);

    public Integer updateProductFightGroupsPriceByProductId(BigInteger productId, Long price);

}
