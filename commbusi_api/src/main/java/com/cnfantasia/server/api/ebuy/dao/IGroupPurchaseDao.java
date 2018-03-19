package com.cnfantasia.server.api.ebuy.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuy.entity.FightgroupProductEntity;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;

public interface IGroupPurchaseDao {
	public List<FightgroupProductEntity> qryproList(Map<String, Object> paramMap);

	/**
	 *
	 * @param productId 拼购ID
	 * @param appType 1 APP 3 LA
     * @return
     */
	public FightgroupProductEntity qryproDeatil(BigInteger productId, Integer appType);
	public Integer updateFightCounts(Map<String, Object> paramMap);
	public EbuyOrder getUserFightNumMsg(BigInteger userId);
}
