package com.cnfantasia.server.ms.groupBuildingBillCycle.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.paybill.entity.PayBillCycle;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.dao.IGroupBuildingBillCycleBaseDao;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.entity.GroupBuildingBillCycle;
import com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.entity.GroupBuildingBillCycleConfig;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.ms.groupBuildingBillCycle.entity.GroupBuildingBillCycleEntity;
import com.cnfantasia.server.ms.propertyPayConfig.entity.MrImportModelEntity;

public interface IGroupBuildingBillCycleDao extends IGroupBuildingBillCycleBaseDao {

	/**
	 * 查询小区下的账单数量
	 * @param paramMap
	 * @return
	 */
	int queryBuildingForCount(Map<String, Object> paramMap);

	/**
	 * 列表查询
	 * @param paramMap
	 * @return
	 */
	List<GroupBuildingBillCycleEntity> queryBuildingForList(Map<String, Object> paramMap);

	/**
	 * 收费账期对象查询
	 * @param id
	 * @return
	 */
	GroupBuildingBillCycleEntity getGroupBuildingBillCycleById(BigInteger id);

	/**
	 * 清除 小区下的未缴费账单
	 * @param paraMap
	 * @return
	 */
	int deleteAllBillById(Map<String, Object> paraMap);

	/**
	 * 根据条件查询小区下是否存在账单
	 * @param paraMap
	 * @param b
	 * @return
	 */
	Integer isHasBillCycleByCondition(Map<String, Object> paraMap);

	/**
	 * 根据条件查询小区下同种费用类型是否存在缴费窗口重叠
	 * @param paraMap
	 * @return
	 */
	Integer isHashSameBillWindow(Map<String, Object> paraMap);

	List<PayBillCycle> selectPayBillForBillCycle();

	List<PayBillCycle> selectPayBillForBillCycle02();

	/**
	 * 清除 临时表收费项数据
	 * @param cycleId
	 * @param gbId
	 * @param type "1":"定期收费","2":"抄表收费","3":"临时收费"
     * @return
     */
	int deleteFeeDetailTempByCycleAndGb(BigInteger cycleId, BigInteger gbId, int type);

	/**
	 * 生成账单
	 * @param cycleId
	 * @param gbId
     * @return
     */
	List<RealRoom> getNeedCreateBillRealRoom(BigInteger cycleId, BigInteger gbId);

	List<GroupBuildingBillCycleConfig> getIsHasSameCycleCfg(Map<String, Object> paraMap);
}
