package com.cnfantasia.server.api.ebuyProductFightGroups.service;

import com.cnfantasia.server.api.ebuyProductFightGroups.entity.EbuyProductFightGroupsDto;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.ebuyProductFightGroups.entity.EbuyProductFightGroups;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by kangduo on 2016.05.27
 */
public interface IEbuyProductFightGroupsService {

    public List<EbuyProductFightGroupsDto> getEbuyProductFightGroupsByCondition(EbuyProductFightGroupsDto dto, PageModel pageModel);

    public List<EbuyProductFightGroupsDto> getEbuyProductFightGroupsByCondition(EbuyProductFightGroupsDto dto);

    public BigInteger getEbuyProductFightGroupsCount(EbuyProductFightGroupsDto dto);

    public void updateEbuyProductFightGroups(EbuyProductFightGroups ebuyProductFightGroups, String productDesc, String image);
    
    public String addEbuyProductFightGroups(EbuyProductFightGroups ebuyProductFightGroups, String productDesc, String image, List<String> ziTidianIds);
}
