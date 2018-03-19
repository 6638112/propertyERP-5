package com.cnfantasia.server.api.ebuy.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.cnfantasia.server.api.ebuy.entity.FightgroupProductEntity;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;

public class GroupPurchaseDao implements IGroupPurchaseDao{
	private SqlSessionTemplate sqlSession;
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public List<FightgroupProductEntity> qryproList(Map<String, Object> paramMap) {
		return sqlSession.selectList("ebuyFight.selelct_fight_product_list", paramMap);
	}
	@Override
	public FightgroupProductEntity qryproDeatil(BigInteger productId, Integer appType) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("productId", productId);
		param.put("appType", appType);
		return sqlSession.selectOne("ebuyFight.selelct_fight_product_detail", param);
	}
	@Override
	public Integer updateFightCounts(Map<String, Object> paramMap) {
		return sqlSession.update("ebuyFight.update_ebuy_fight_left_count", paramMap);
	}
	@Override
	public EbuyOrder getUserFightNumMsg(BigInteger userId) {
		return sqlSession.selectOne("ebuyFight.select_user_fight_product_msg", userId);
	}
	

	
}
