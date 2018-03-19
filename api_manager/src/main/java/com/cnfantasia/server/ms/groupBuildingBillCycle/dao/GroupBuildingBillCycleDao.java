package com.cnfantasia.server.ms.groupBuildingBillCycle.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.paybill.entity.PayBillCycle;
import com.cnfantasia.server.business.pub.page.TotalCountGetter;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.dao.GroupBuildingBillCycleBaseDao;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.entity.GroupBuildingBillCycle;
import com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.entity.GroupBuildingBillCycleConfig;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.ms.groupBuildingBillCycle.entity.GroupBuildingBillCycleEntity;

public class GroupBuildingBillCycleDao extends GroupBuildingBillCycleBaseDao implements IGroupBuildingBillCycleDao {

	@Override
	public int queryBuildingForCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "billcycle.select_billcycle_forList", paramMap);
	}

	@Override
	public List<GroupBuildingBillCycleEntity> queryBuildingForList(Map<String, Object> paramMap) {
		return sqlSession.selectList("billcycle.select_billcycle_forList", paramMap);
	}

	@Override
	public GroupBuildingBillCycleEntity getGroupBuildingBillCycleById(BigInteger id) {
		return sqlSession.selectOne("billcycle.select_billcycle_by_id", id);
	}

	@Override
	public int deleteAllBillById(Map<String, Object> paraMap) {
		return sqlSession.update("billcycle.delete_billcycle_by_id", paraMap);
	}

	@Override
	public Integer isHasBillCycleByCondition(Map<String, Object> paraMap) {
		return sqlSession.selectOne("billcycle.is_has_billCycle_by_condition", paraMap);
	}

	@Override
	public Integer isHashSameBillWindow(Map<String, Object> paraMap) {
		return sqlSession.selectOne("billcycle.is_has_billPeriod_by_condition", paraMap);
	}

	@Override
	public List<PayBillCycle> selectPayBillForBillCycle() {
		return sqlSession.selectList("billcycle.select_pay_bill_for_bill_cycle_month");
	}

	@Override
	public List<PayBillCycle> selectPayBillForBillCycle02() {
		return sqlSession.selectList("billcycle.select_pay_bill_for_bill_cycle_period");
	}

	@Override
	public int deleteFeeDetailTempByCycleAndGb(BigInteger cycleId, BigInteger gbId, int type) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("cycleId", cycleId);
		paraMap.put("gbId", gbId);
		paraMap.put("type", type);
		return sqlSession.delete("billcycle.deleteFeeDetailTempByCycleAndGb", paraMap);
	}

	@Override
	public List<RealRoom> getNeedCreateBillRealRoom(BigInteger cycleId, BigInteger gbId) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("cycleId", cycleId);
		paraMap.put("gbId", gbId);
		return sqlSession.selectList("billcycle.getNeedCreateBillRealRoom", paraMap);
	}

	@Override
	public List<GroupBuildingBillCycleConfig> getIsHasSameCycleCfg(Map<String, Object> paraMap) {
		return sqlSession.selectList("groupBuildingCycleCfg.getIsHasSameCycleCfg", paraMap);
	}
}
