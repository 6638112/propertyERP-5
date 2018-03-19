package com.cnfantasia.server.api.userHasPropertyCard.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.userHasPropertyCard.entity.UserHasPropertyCardEntity;
import com.cnfantasia.server.domainbase.userHasPropertyCard.dao.UserHasPropertyCardBaseDao;
import com.cnfantasia.server.domainbase.userHasPropertyCard.entity.UserHasPropertyCard;

public class UserHasPropertyCardDao extends UserHasPropertyCardBaseDao implements IUserHasPropertyCardDao {
	
	/**
	 * 更新用户购买记录表（加锁校验）
	 * @param userHasPropertyCard
	 * @return
	 */
	@Override
	public boolean updateUserHasPropertyCardWithLcok(
			UserHasPropertyCardEntity userHasPropertyCard) {
		return sqlSession.update("userHasPropertyCard.update_userHasPropertyCard_withLock", userHasPropertyCard)>0;
	}

	/**
	 * 根据userId查询(用户购买物业代扣卡)信息
	 * @param userId
	 * @return
	 */
	@Override
	public List<UserHasPropertyCard> selectUserHasPropertyCardByUserId(BigInteger userId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		return sqlSession.selectList("userHasPropertyCard.select_userHasPropertyCard_with_userId", paramMap);
	}
}
