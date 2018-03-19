package com.cnfantasia.server.api.userHasPropertyCard.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.userHasPropertyCard.entity.UserHasPropertyCardEntity;
import com.cnfantasia.server.domainbase.userHasPropertyCard.dao.IUserHasPropertyCardBaseDao;
import com.cnfantasia.server.domainbase.userHasPropertyCard.entity.UserHasPropertyCard;

public interface IUserHasPropertyCardDao extends IUserHasPropertyCardBaseDao{

	/**
	 * 更新用户购买记录表（加锁校验）
	 * @param userHasPropertyCard
	 * @return
	 */
	public boolean updateUserHasPropertyCardWithLcok(UserHasPropertyCardEntity userHasPropertyCard);
	
	/**
	 * 根据userId查询(用户购买物业代扣卡)信息
	 * @param userId
	 * @return
	 */
	public List<UserHasPropertyCard> selectUserHasPropertyCardByUserId(BigInteger userId);
}
