package com.cnfantasia.server.ms.groupBuildingBillCycle.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.service.IGroupBuildingBillCycleBaseService;
import com.cnfantasia.server.domainbase.mrFeeItem.entity.MrFeeItem;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.tmpFeeItem.entity.TmpFeeItem;
import com.cnfantasia.server.ms.groupBuildingBillCycle.dto.BillEditParam;
import com.cnfantasia.server.ms.groupBuildingBillCycle.entity.GroupBuildingBillCycleEntity;
import com.cnfantasia.server.ms.propertyPayConfig.entity.MrImportModelEntity;

public interface IGroupBuildingBillCycleService extends IGroupBuildingBillCycleBaseService {

	/**
	 * 查询小区下的账单数量
	 * @param paramMap
	 * @return
	 */
	int queryBuildingForCount(Map<String, Object> paramMap);

	/**
	 * 列表查询
	 * @param curPageIndex
	 * @param pageSize
	 * @param paramMap
	 * @param isPage
	 * @return
	 */
	List<GroupBuildingBillCycleEntity> queryBuildingForList(int curPageIndex, int pageSize, Map<String, Object> paramMap, boolean isPage);

	/**
	 * 查询 账期对象
	 * @param id
	 * @return
	 */
	GroupBuildingBillCycleEntity getGroupBuildingBillCycleById(BigInteger id);

	/**
	 * 保存信息
	 * @param billEditParam
	 * @return
	 */
	int saveOrUpdateBillCycel(BillEditParam billEditParam);

	/**
	 * 清除 小区下的未缴费账单
	 * @param paraMap
	 * @return
	 */
	int deleteAllBillById(Map<String, Object> paraMap);

	/**
	 * 判断是否存在重叠账期
	 * @param paraMap
	 * @return
	 */
	boolean isHashSameBillCycle(Map<String, Object> paraMap);

	/**
	 * 判断是否存在重叠窗口
	 * @param paraMap
	 * @return
	 */
	boolean isHashSameBillWindow(Map<String, Object> paraMap);

	/**
	 * 判断 账单名称是否重复
	 * @param paraMap
	 * @return
     */
	boolean isExistBillName(Map<String, Object> paraMap);

	PayBillType getPayBillType(String billName, BigInteger gbId);

	/**
	 * 同步固定周期数据
	 * @param cycleId
	 * @param gbId
	 * @return
     */
	JsonResponse synchroFixedData(BigInteger cycleId, BigInteger gbId);

	/**
	 * 账单生成
	 * @param cycleId
	 * @param gbId
     * @return
     */
	String createPayBill(BigInteger cycleId, BigInteger gbId);

	/**
	 * 导入抄水表数据
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	String importMrData(HttpServletRequest request) throws IOException;

	/**
	 * 导入临时数据
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	String importTmpBillData(HttpServletRequest request) throws IOException;

	/**
	 * 查询是否存在重复的账期配置
	 * @param paraMap
	 * @return
     */
	Boolean getIsHasSameCycleCfg(Map<String, Object> paraMap);
	/**
	 * 注：任何地方不能再次引用该方法
	 * 防止自动生成失败时间过了，所以使用手动生成
	 * @param cycleCfgId
	 * @param type
	 * @return
	 */
	void autoCreateCycleAndPayBill(BigInteger cycleCfgId, int type);

	/**
	 * 是否存在同名账期在执行自动生成
	 * @param billName
	 * @param gbId
     * @return
     */
	boolean isHasAutoCreateCycle(String billName, BigInteger gbId);

	/**
	 * 删除没有缴费账单的账期信息
	 * @param paraMap
	 * @return
     */
	String deleteGroupBuildingCycle(Map<String, Object> paraMap);

	/**
	 * 查询抄表导入需要的费用项列表
	 * @param mriQryMap
	 * @return
     */
	List<MrFeeItem> getMrFeeItemByCondition(Map<String, Object> mriQryMap);

	/**
	 * 查询临时导入需要的费用项列表
	 * @param mriQryMap
	 * @return
	 */
	List<TmpFeeItem> getTmpFeeItemByCondition(Map<String, Object> mriQryMap);
}
