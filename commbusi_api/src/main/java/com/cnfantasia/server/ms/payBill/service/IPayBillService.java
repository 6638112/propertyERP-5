package com.cnfantasia.server.ms.payBill.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UnPaidPayBillEntity;
import com.cnfantasia.server.ms.payBill.entity.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.payBill.service.IPayBillBaseService;

public interface IPayBillService extends IPayBillBaseService {
	
	void saveImportPayBill(List<PayBillWithFeeDetailEntity> payBills);

	List<PayBillWithFeeDetailEntity> getExportedPayBill(List<BigInteger> payBillIdList,boolean isRevenue, HashMap<String, Object> paramMap);
	List<PayBillWithFeeDetailEntity> getExportedPayBillFroReven(List<BigInteger> payBillIdList,boolean isRevenue, HashMap<String, Object> paramMap);
	List<PayBillWithFeeDetailEntity> getExportedPayBillWy(List<BigInteger> payBillIdList,boolean isRevenue);

	/**
	 * 查看单个账单详情
	 *
	 * @param id
	 */
	PayBillWithFeeDetailEntity getPayBillForDetail(BigInteger id);
	
	List<BillMrDetail> selectBillDetailForMr(BigInteger payBillId);

	List select_payBill_with_payRecord(String id);

	int markByManual(String id, BigInteger omsUserId, MarkReq markReq);

	int getPayBill_byUserId_forCount(Map<String, Object> paramMap);
	int getPayBill_byUserId_forCount_Revenue(Map<String, Object> paramMap);

	List<PayBillEntity> getPayBillList_byUserId_forPage(Map<String, Object> paramMap);
	List<PayBillEntity> getPayBillList_byUserId_forRevenuePage(Map<String, Object> paramMap);
	PayBillTotalData getTotalData(Map<String, Object> paramMap);
	
	com.cnfantasia.server.api.plotproperty.entity.PayBillEntity selectPayBillEntity(Map<String, Object> paramMap);
	
	String getPropertyCompanyName(Long groupBuildingId);
	
	List<GroupBuilding> getGroupBuildingByOmsId(Map<String, Object> paramMap);

	/**
	 * 根据小区Id获取对应的广告
	 * @param gbId
	 * @return
	 */
	String getConfigAdUrl(BigInteger gbId);

	/**
	 * 保存周期账单
	 * @param payBills 待导入的Excel数据
	 * @return
	 */
	String saveImportPayBillPeriod(List<PayBillWithFeeDetailEntity> payBills);

	/**
	 * 得到待导出的周期账单
	 * @param payBillIdList
	 * @return
	 */
	List<PayBillPeriod4Export> getExportedPayBillPeriod(List<BigInteger> payBillIdList);
	
	/**
	 * 对导入的账单（月度）进行校验
	 * @param payBills
	 * @return 返回校验信息，哪些行是重复账单，或全部导入成功
	 */
	String verifyImportPayBill(List<PayBillWithFeeDetailEntity> payBills);
	
	/**
	 * 对账单导出excel
	 */
	HSSFWorkbook createRport(List<PayBillWithFeeDetailEntity> payBillImportedEntyList);
	
	/**
	 * 获取导出对账单的list
	 */
	List<PayBillWithFeeDetailEntity> fetchExportList(HttpServletRequest request);
	
	/**
	 * 获取导出对账单的list物业代收
	 */
	List<PayBillWithFeeDetailEntity> fetchExportListWy(HttpServletRequest request);
	
	/**
	 * 根据小区id,判断是否存在订单
	 * @param gbId
	 * @param id
	 */
	boolean isHasPayBillByGbId(String gbId, String id);

	/**
	 * 删除账单（未缴费）
	 * @param id
	 * @return
	 */
	int delPayBill(String id, BigInteger delUserId);

	/**
	 * 查询 小区平均账单金额查询
	 * @param paraMap 
	 * @return
	 */
	List<Map<String, Object>> getGroupbuildingAvgAmount(HashMap<String, Object> paraMap);

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

	List<BigInteger> getCompayIdUserManageByOmsUser(BigInteger omsUserId);

	List<MsgForPaybill> getMsgForPaybillEnd();
	
	List<MsgForPaybill> getMsgForPaybillStart();

	List<MsgForPaybill> getMsgForPaybillStartUnRegister();

	List<MsgForPaybill> getMsgForPaybillBeforeBank();

	List<MsgForPaybill> getMsgForPaybillAfterBank();
	
	List<MsgForPaybill> getMsgForPaybillAfterBankSuccess();

	/**
	 * 导出账单时查询  每个账单的费用明细
	 * @param gbId
	 * @param feeType 收费模式
	 * @param billIds
	 * @return
     */
	List<ExportBillDetailHead> selectFeeDetailNameByGbId(BigInteger gbId, Integer feeType, List<BigInteger> billIds);

	/**
	 * 账单金额修改
	 * @param fixedList 固定收费
	 * @param mrList 抄表
	 * @param tmpList 临时
	 * @param payBillId 账单id
     * @param userId
	 * @return
     */
	void savePayBillAmount(List<List> fixedList, List<List> mrList, List<List> tmpList, BigInteger payBillId, BigInteger userId) throws Exception;

	/**
	 * 根据账单id查询欠费详情列表.
	 * @param payBillId
	 * @return
     */
	List<Map<String, List>> getUnpaidListById(BigInteger payBillId);

	/**
	 * 查询欠费金额
	 * @param id
	 * @return
     */
	BigDecimal getUnpaidAmtById(BigInteger id);

	/**
	 * 欠费结清
	 * @param id
	 * @param omsUserId
     * @return
     */
	int unpaidSettle(String id, BigInteger omsUserId);

	/**
	 * 查询账单的金额：
	 * 收费总额，用户支付，补贴总额，物业宝抵扣总额
	 * @param paramMap
	 * @return
     */
	Map<String,BigDecimal> getPayBillsAmounts(Map<String, Object> paramMap);

	/**
	 * 判断账单是否为托收中的：欠费账单只要母账单托收中也算托收中
	 * @return
     */
	boolean isBankCollectionByPayBillId(BigInteger payBillId);
}
