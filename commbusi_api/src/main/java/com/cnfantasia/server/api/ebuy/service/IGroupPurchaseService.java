package com.cnfantasia.server.api.ebuy.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cnfantasia.server.api.ebuy.entity.FightgroupProductEntity;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;

public interface IGroupPurchaseService {
	public List<FightgroupProductEntity> selectproList(Map<String, Object> paramMap);
	public FightgroupProductEntity selectProDetail(BigInteger productId, Integer appType);
	//生成订单
	public Map<String, Object> saveEbuyOrderFightNew(FightgroupProductEntity fightProduct, Long hbAmount, Set<BigInteger> couponIdSet,
			Integer subChannelId,BigInteger userId,String userName,String phone, Integer buyNum);
	public EbuyOrder selectUserFightMsg(BigInteger userId);
}
