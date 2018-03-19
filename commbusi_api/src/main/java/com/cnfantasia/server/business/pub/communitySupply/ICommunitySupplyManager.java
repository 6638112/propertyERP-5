package com.cnfantasia.server.business.pub.communitySupply;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.dredge.entity.DredgeTypeForUser;

/**
 * 维修类型缓存
 */
public interface ICommunitySupplyManager {

    List<Map<String, Object>> getCommunitySupplyTypeList(List<BigInteger> addrCodeIdList);

    List<DredgeTypeForUser> getDredgeTypeListByParentId(BigInteger dredgeTypeId, String blockId, BigInteger managementFId);

    void refreshCache();
}
