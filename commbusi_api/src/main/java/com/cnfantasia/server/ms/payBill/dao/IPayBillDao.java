package com.cnfantasia.server.ms.payBill.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UnPaidPayBillEntity;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.payBill.dao.IPayBillBaseDao;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payBillTimeCfg.entity.PayBillTimeCfg;
import com.cnfantasia.server.domainbase.propertyGbAd.entity.PropertyGbAd;
import com.cnfantasia.server.ms.payBill.entity.*;

public interface IPayBillDao extends IPayBillBaseDao {

	/**
	 * 整理待导入的数据
	 * 
	 * @param payBills
	 *            从Excel中解析过来的数据
	 * @return 返回给用户结果信息，哪些行可导，哪些行不能导入
	 */
	String verifyImportPayBill(List<PayBillWithFeeDetailEntity> payBills);

	/**
	 * 找出待导出的数据
	 * 
	 * @param payBillIdList
	 * @param paramMap 
	 * @return
	 */
	List<PayBillWithFeeDetailEntity> getExportedPayBill(List<BigInteger> payBillIdList,boolean isRevenue, HashMap<String, Object> paramMap);

	List<PayBillWithFeeDetailEntity> getExportedPayBillFroReven(List<BigInteger> payBillIdList,boolean isRevenue, HashMap<String, Object> paramMap);

	/**
	 * 找出待导出的物业数据
	 * 
	 * @param payBillIdList
	 * @return
	 */
	List<PayBillWithFeeDetailEntity> getExportedPayBillWy(List<BigInteger> payBillIdList,boolean isRevenue);

	/**
	 * 查看单个账单详情
	 *
	 * @param payBill
	 *            物业账单ID
	 */
	PayBillWithFeeDetailEntity getPayBillForDetail(PayBill payBill);

	/**
	 * 查看单个账单支付详情2
	 * 
	 * @param id
	 *            物业账单ID
	 */
	List<HashMap<String, Object>> select_payBill_with_payRecord(String id);
	
	List<BillMrDetail> selectBillDetailForMr(BigInteger payBillId);

	/**
	 * 手功标记物业账单为已缴
	 * 
	 * @param id
	 * @return
	 */
	int markByManual(String id, BigInteger omsUserId);

	/**
	 * 根据用户ID查找缴费单的总记录数
	 * 
	 * @param paramMap
	 * @return
	 */
	int getPayBill_byUserId_forCount(Map<String, Object> paramMap);
	int getPayBill_byUserId_forCount_Revenue(Map<String, Object> paramMap);

	/**
	 * 根据用户ID查找缴费单
	 * 
	 * @param curPageIndex
	 *            第几页
	 * @param pageSize
	 *            每页的大小
	 * @param paramMap
	 *            请求参数
	 * @return
	 */
	List<PayBillEntity> getPayBill_byUserId_forPage(Map<String, Object> paramMap);
	List<PayBillEntity> getPayBill_byUserId_forRevenuePage(Map<String, Object> paramMap);
	/**
	 * 查询总额
	 * @param paramMap
	 * @return
	 */
	PayBillTotalData selectTotalData(Map<String, Object> paramMap);
	
	com.cnfantasia.server.api.plotproperty.entity.PayBillEntity selectPayBillEntity(Map<String, Object> paramMap);
	
	String getPropertyCompanyName(Long groupBuildingId);
	
	List<GroupBuilding> getGroupBuildingByOmsId(Map<String, Object> paramMap);

	/**
	 * 根据小区Id获取对应的广告
	 * @param gbId
	 * @return
	 */
	PropertyGbAd selectConfigAdUrl(BigInteger gbId);

	String verifyImportPayBillPeriod(List<PayBillWithFeeDetailEntity> payBills);
	
	/**
	 * 找出时间重叠的账单配置，统计其总数
	 * @param payBillTimeCfg
	 * @return
	 */
	int selectCountCrossTimeConfig(PayBillTimeCfg payBillTimeCfg);

	List<PayBillPeriod4Export> getExportedPayBillPeriod(List<BigInteger> payBillIdList);
	

	/**
	 * 获取小区下的账单数量
	 * @param bigInteger
	 * @param paytimeType 
	 * @param bigInteger2 
	 * @return
	 */
	int getPayBillByGbIdForCount(BigInteger bigInteger, BigInteger bigInteger2);

	/**
	 * 更新账单账期信息
	 * @param updateMap
	 */
	int updatePayBillBillMonth(HashMap<String, Object> updateMap);

	/**
	 * 删除账单（未缴费）
	 * @param billId
	 * @return
	 */
	int delPayBill(BigInteger billId, BigInteger delUser);

	/**
	 * 查询 每个小区的平均账单金额
	 * @param paraMap
	 * @return
	 */
	List<Map<String, Object>> getGroupbuildingAvgAmount(HashMap<String, Object> paraMap);

	/**
	 * 新增一条账单记录
	 * @param payBill
	 * @return
     */
	int insertAlterPeriodPayBill(PayBill payBill);

	/**
	 * 更新账期
	 * @param gbId
	 * @param payBillTypeId
	 */
	int updateBillCycle(BigInteger gbId, BigInteger payBillTypeId, int operateStatus);

	/**
	 * 根据费用类型查询账期id
	 * @param payBillTypeId
	 * @return
	 */
	List<BigInteger> getGroupBuildingBillCycleByTypeId(BigInteger gbId, BigInteger payBillTypeId);

	/**
	 * 根据账期id更新账单信息
	 * @param billCycleIds
	 * @return
	 */
	int updatePayBillByCycleIds(List<BigInteger> billCycleIds);

	List<BigInteger> getCompayIdUserManageByOmsUser(Map<String, Object> paraMap);

	List<BigInteger> filterNotSendPayBillMsgUser(List<BigInteger> userIdList);
	
	List<MsgForPaybill> getMsgForPaybillEnd();

	List<MsgForPaybill> getMsgForPaybillStart();

	List<MsgForPaybill> getMsgForPaybillStartUnRegister();

	List<MsgForPaybill> getMsgForPaybillBeforeBank();

	List<MsgForPaybill> getMsgForPaybillAfterBank();
	
	List<MsgForPaybill> getMsgForPaybillAfterBankSuccess();

	/**
	 * 查小区对应的收费项名称
	 * @param gbId
	 * @param feeType
	 * @param billIds
	 * @return
     */
	List<ExportBillDetailHead> selectFeeDetailNameByGbId(BigInteger gbId, Integer feeType, List<BigInteger> billIds);

	/**
	 * 插入paybill所有数据包含删除状态
	 * @param payBill
	 * @return
     */
	int insertPayBillAllCum(PayBill payBill);

	/**
	 * 查询欠费金额
	 * @param id
	 * @return
     */
	Long getUnpaidAmtById(BigInteger id);

	/**
	 * 查询欠费明细列表
	 * @param payBillId
	 * @return
     */
	List<UnPaidPayBillEntity> getUnpaidListById(BigInteger payBillId);

	/**
	 * 查询账单的金额：
	 * 收费总额，用户支付，补贴总额，物业宝抵扣总额
	 * @param paramMap
	 * @return
	 */
	Map<String,BigDecimal> getPayBillsAmounts(Map<String, Object> paramMap);

	List<Map<String,Object>> getNeedFixedUpdate(BigInteger payBillId);

	/**
	 * 判断账单是否为托收中的：欠费账单只要母账单托收中也算托收中
	 * @return
	 */
	int isBankCollectionByPayBillId(BigInteger payBillId);
}
