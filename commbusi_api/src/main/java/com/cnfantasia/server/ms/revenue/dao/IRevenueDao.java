package com.cnfantasia.server.ms.revenue.dao;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.entity.BeginEndDate;
import com.cnfantasia.server.ms.revenue.entity.DredgeBillForRevenue;
import com.cnfantasia.server.ms.revenue.entity.DredgeRevenue;
import com.cnfantasia.server.ms.revenue.entity.EbuyDelivDiscount;
import com.cnfantasia.server.ms.revenue.entity.EbuyOrderRevenue;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;
import com.cnfantasia.server.ms.revenue.entity.MinMaxDate;
import com.cnfantasia.server.ms.revenue.entity.PayBillForRevenue;
import com.cnfantasia.server.ms.revenue.entity.PayBillForRevenueOtherFee;
import com.cnfantasia.server.ms.revenue.entity.PayBillForRevenueSubsidy;
import com.cnfantasia.server.ms.revenue.entity.PropCompanyWithRevenue;
import com.cnfantasia.server.ms.revenue.entity.RevenueAmountPrevEntity;
import com.cnfantasia.server.ms.revenue.entity.RevenueApply4Export;
import com.cnfantasia.server.ms.revenue.entity.RevenueConfigByTime;
import com.cnfantasia.server.ms.revenue.entity.RevenueConfigDetail;
import com.cnfantasia.server.ms.revenue.entity.RevenueConfigForList;
import com.cnfantasia.server.ms.revenue.entity.RevenueMoneyShow;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;
import com.cnfantasia.server.ms.revenue.entity.RevenueSettlement;
import com.cnfantasia.server.ms.revenue.entity.RoomValidateForRevenue;
import com.cnfantasia.server.ms.revenue.entity.UserWithPropCompany;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IRevenueDao {
	public List<Map<String, Object>> select_revenueList(Map<String, Object> paramMap);

	public int select_revenueList_forCount(Map<String, Object> paramMap);
	
	/**
	 * 查询物业公司列表,返回收益配置信息
	 * @param companyName
	 * @param pageModel
	 * @return
	 */
	public List<PropCompanyWithRevenue> selectPropCompanyWithRevenueList(BigInteger companyId,String companyName, PageModel pageModel);
	
	/**
	 * 查询收益配置列表
	 * @param companyId
	 * @param companyName
	 * @param projectType
	 * @param pageModel
	 * @return
	 */
	public List<RevenueConfigForList> selectRevenueConfigList(BigInteger companyId, String companyName, Integer projectType, PageModel pageModel);
	
	/**
	 * 根据用户Id获取对应管理的物业公司信息
	 * @param omsUserId
	 * @return
	 */
	public UserWithPropCompany selectUserWithPropCompanyByUserId(BigInteger omsUserId);
	
	/**
	 * 根据名称查询物业公司列表
	 * @param companyName
	 * @return
	 */
	public List<PropertyCompany> selectPropCompanyList(String companyName);
	
	/**
	 * 根据Id查询详情
	 * @param dataId
	 * @return
	 */
	public RevenueConfigDetail selectRevenueConfigDetail(BigInteger dataId);
	
	/**
	 * 根据物业公司Id,收益项目类型，查询最早生效时间和最晚截止时间
	 * @param companyId
	 * @param projectType
	 * @return
	 */
	public MinMaxDate selectMinMaxDate(BigInteger companyId,Integer projectType);
	
	/**
	 * 查询物业公司收益总额信息
	 * @param revenueRole
	 * @param searchName
	 * @param pageModel
	 * @return
	 */
	public List<RevenueAmountPrevEntity> selectRevenueAmountPrevList(RevenueRole revenueRole, String searchName, PageModel pageModel);
	
	/**
	 * 根据物业公司Id查询对应时间段的门牌认证数
	 * @param revenueRole
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Integer selectRoomValidCountByPropertyCompanyId(RevenueRole revenueRole, String startTime, String endTime);
	
	/**
	 * 查询物业公司对应某时间段的用户实际缴费金额总额
	 * 条件：已缴费
	 * @param revenueRole
	 * @param startTime 已保证时间段内有生效的收益规则配置
	 * @param endTime
	 * @return 单位为分
	 */
	public Double selectPayBillRealPayMoneyTotal(RevenueRole revenueRole, String startTime, String endTime);
	
	/**
	 * 查询物业公司对应某时间段的补贴金额总额
	 * 条件：已缴费
	 * @param revenueRole
	 * @param startTime 已保证时间段内有生效的收益规则配置
	 * @param endTime
	 * @return 单位为分
	 */
	public Double selectPayBillSubsidyMoneyTotal(RevenueRole revenueRole,
			String startTime, String endTime);
	
	/**
	 * 查询物业公司对应某时间段的其它费用金额总额
	 * 条件：已缴费
	 * @param revenueRole
	 * @param startTime 已保证时间段内有生效的收益规则配置
	 * @param endTime
	 * @return 单位为分
	 */
	public Double selectPayBillOtherFeeTotal(RevenueRole revenueRole,
			String startTime, String endTime);
	
	/**
	 * 根据物业公司ID计算某个时间段的配置信息
	 * @param projectType
	 * @param companyId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<RevenueConfigByTime> selectRevenueConfigByTimeListByCompanyId(Integer projectType, BigInteger companyId, String startTime, String endTime);
	
	/**
	 * 根据代理商ID,查询下属的物业公司Id列表
	 * @param agentId
	 * @return
	 */
	public List<BigInteger> selectCompanyIdListByAgentId(BigInteger agentId);
	
	/**
	 * 查询物业公司对应类别已生效规则列表
	 * @param companyId
	 * @return
	 */
	public List<RevenueConfigByTime> selectActiveRevenueConfigList(RevenueRole revenueRole,Integer projectType);
	
	/**
	 * 查询各个时间段内属于该物业公司的且缴费成功的账单,且未生成过sig对应类别的物业账单 用户实际缴费
	 * @param companyId
	 * @param projectType
	 * @param startTime
	 * @param endTime
	 * @return [startTime,endTime)
	 */
	public List<PayBillForRevenue> selectToSigPayBillList(BigInteger companyId,Integer projectType, String startTime, String endTime);
	
	/**
	 * 使用物业账单月份作为查询时间段
	 * @param companyId
	 * @param projectType
	 * @param configStartTime
	 * @param configEndTime
	 * @return
	 */
	public List<PayBillForRevenue> selectToSigPayBillList4BillMonth(
			BigInteger companyId, Integer projectType, String configStartTime,
			String configEndTime);
	
	/**
	 * 查询各个时间段内维修单, 且未生成过sig对应类别的维修账单
	 * @param companyId
	 * @param projectType
	 * @param startTime
	 * @param endTime
	 * @return [startTime,endTime)
	 */
	public List<DredgeBillForRevenue> selectToSigDredgeBillList(BigInteger companyId,Integer projectType, String startTime, String endTime);
	
	/**
	 * 查询各个时间段内属于该物业公司的且缴费成功的账单,且未生成过sig对应类别的物业账单  平台补贴额
	 * @param companyId
	 * @param projectType
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<PayBillForRevenueSubsidy> selectToSigPayBillSubsidyList(
			BigInteger companyId, Integer projectType, String startTime,
			String endTime);
	/**
	 * 查询各个时间段内属于该物业公司的且缴费成功的账单,且未生成过sig对应类别的物业账单  其他代收费用
	 * @param companyId
	 * @param projectType
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<PayBillForRevenueOtherFee> selectToSigPayBillOtherFeeList(
			BigInteger companyId, Integer projectType, String startTime,
			String endTime);
	
//	/**
//	 * 根据起止时间 获取对应时间段的 对应角色对应收益项目 的单个收益额列表
//	 * @param projectType
//	 * @param revenueRole
//	 * @return
//	 */
//	public List<RevenueSignalAmount> selectRevenueSignalAmountList(Integer projectType, RevenueRole revenueRole);
	
	/**
	 * 查询所有包含开启配置项的物业公司Id列表
	 * 针对物业费
	 * @return
	 */
	public List<BigInteger> selectRevConfigCompanyIdList(Integer projectType,UserRole role);
	
	/**
	 * 查询所有包含开启配置项的代理商Id列表
	 * @return
	 */
	public List<BigInteger> selectRevConfigAgentIdList(Integer projectType,UserRole role);
	
	/**
	 * 根据omsUserId查询对应的拥有的角色
	 * @param omsUserId
	 * @return
	 */
	public List<Integer> selectOmsUserRoleListByUserId(BigInteger omsUserId);
	
	/**
	 * 查询各个时间段内属于该物业公司的且验证通过的门牌,且未生成过sig对应类别的
	 * @param companyId
	 * @param projectType
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<RoomValidateForRevenue> selectToSigRoomValidateList(RevenueRole revenueRole, Integer projectType, String startTime, String endTime);

	public List<RevenueSettlement> select_revenueSettleList(RevenueRole revenueRole,Map<String, Object> paramMap);
	/**
	 * 财务人员查看收益中心列表
	 * @param paramMap
	 * @return
	 */
	public List<RevenueApply> selectRevenueApplyListFinance(Map<String, Object> paramMap,PageModel pageModel);
	/**
	 * 查询满足条件的结算金额总计
	 * @param omsUserId
	 * @param userRole
	 * @param paramMap
	 * @return
	 */
	public Double selectTotalAmountAllFinance(Map<String, Object> paramMap);
	public int select_revenueSettleList_forCount(Map<String, Object> paramMap);
	
	/**
	 * 创建角色信息
	 * @param omsUserId
	 * @param expectedRole
	 * @return
	 */
	public RevenueRole createRevenueRole(BigInteger omsUserId, UserRole expectedRole);
	
	/**
	 * 刷新roleName
	 */
	public Integer updateRevenueSignalAmountRoleName();
	public List<EbuyOrderRevenue> selectEbuyRevenueList(Map<String, Object> paramMap);
	
	public List<EbuyRevenueSignalAmount> selectEbuyRevenueSignalAmountList(Map<String, Object> paramMap);
	
	public int selectEbuyRevenueSignalAmountCount(Map<String, Object> paramMap);
	
	public EbuyRevenueTotal selectEbuyRevenueTotal(Map<String, Object> paramMap);
	
	/**
	 * 获取最近申请提款截止的时间
	 * @param miniRoleId
	 * @param miniRoleType
	 * @param goalType
	 * @return
	 */
	public String selectLastApplyGoalEndTime(BigInteger miniRoleId, Integer miniRoleType, Integer goalType);
	
	/**
	 * 标记sig提款状态为提款中
	 * @param applyAddId
	 * @param miniRoleId
	 * @param miniRoleType
	 * @param goalType
	 * @param goalStartTime
	 * @param goalEndTime
	 */
	public Integer updateRevenueSignalAmountAsDoing(BigInteger applyAddId, BigInteger miniRoleId, Integer miniRoleType, Integer goalType, String goalStartTime,
			String goalEndTime,Integer optType);
	
	/**
	 * 从明细中查询对应提款单总额
	 * @param applyAddId
	 * @return
	 */
	public RevenueMoneyShow selectRevenueApplyTotalFromDetail(BigInteger applyAddId);
	/**
	 * 从明细中查询对应提款单总额
	 * @param applyAddId
	 * @return
	 */
	public Double selectRevenueApplyTotalByTime(BigInteger miniRoleId, Integer miniRoleType, Integer goalType, String goalStartTime, String goalEndTime);
	
	/**
	 * 标记提款单为已结算
	 * @param applyId
	 * @return
	 */
	public Integer updateRevenueApplyAsFinished(BigInteger applyId,BigInteger operUserId);
	/**
	 * 标记提款单为已结算,批量
	 * @param applyIdList
	 * @return
	 */
	public Integer updateRevenueApplyAsFinishedBatch(Set<BigInteger> applyIdList,BigInteger operUserId);
	
	/**
	 * 购销电商供应商提款中转为已结算
	 * @param applyIds
	 * @param operUserId
	 * @return
	 */
	public Integer updateRevenueApplyWithMerchant(List<BigInteger> applyIds,BigInteger operUserId);
	
	/**
	 * 更新apply的备份信息
	 * @param applyId
	 */
	public Integer updateRevenueApplyBakInfo(BigInteger applyId,Integer miniRoleType);
	
	public Integer updateForEbuyRevenue(Map<String, Object> paramMap);

	
	public List<EbuyDelivDiscount> getEbuyDelivDiscountList(Map<String, Object> paramMap);
	
	public Integer updateForEbuyRevenueProccessed(BigInteger deliverOrderId);

	
	public Integer updateDeliveryRevenueStatus(Map<String, Object> paramMap);
	
	/**
	 * 查询系统可提补贴款的物业公司Id列表，且当前日期是小区缴费周期的前一天或当天
	 * @return
	 */
	public List<BigInteger> selectSysApplyToDoCompanyList();

	/**
	 * 计算维修单金额 
	 * @param revenueRole
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Double selectDredgePayMoneyTotal(RevenueRole revenueRole,
			String startTime, String endTime);

	/**
	 * 查询师傅上次提现的时间 
	 * @param userId 师傅的userID
	 * @return
	 */
	public String selectLastEndTime_byMasterUserId(BigInteger userId);
	
	/**
	 * 获取物业公司的历史未标记结算的实收账单起止月份
	 * @param companyId
	 * @return
	 */
	public BeginEndDate selectPropertyRealPayHistoryMonth(BigInteger companyId);
	
//	/**
//	 * 根据物业公司查询对应的小区跨越月份
//	 * @param companyId
//	 * @return
//	 */
//	public Integer selectGbMonthChangeByCompanyId(BigInteger companyId);
	
	/**
	 * 按结算日找出所有符合条件的物业公司或代理公司的ID
	 * @param paramMap
	 * @return
	 */
	public List<BigInteger> selectSysApplyToDoList(Map<String, Object> paramMap);
	
	public List<RevenueApply> selectRevenueApplyListFinance(Map<String, Object> paramMap);
	
	public List<EbuyRevenueSignalAmount> selectEbuyRevenueSignalAmountExportList(Map<String, Object> paramMap);

	/**
	 * 查询推荐人上次提现的时间 
	 * @param userId 推荐人的userID
	 * @return
	 */
	public String selectLastEndTime_byRecommendUserId(BigInteger userId);
	
	public int selectRevenueSignalAmountCount(Map<String, Object> paramMap);
	
	public boolean isPropertyManagerRevenuet(Map<String, Object> paramMap);

	public List<BigInteger> selectPayBill_idList_byRevenueApply(BigInteger raId);

	/**
	 * 查询管理处的收益
	 * @param omsUserId
	 * @param searchName
	 * @param pageModel
	 * @return
	 */
	public List<RevenueAmountPrevEntity> selectRevenueAmountManagementPrevList(BigInteger omsUserId, String searchName,
			PageModel pageModel);

	/**
	 * 管理处结算情况查询
	 * @param paramMap
	 * @return
	 */
	public List<RevenueSettlement> select_revenueSettleManagementList(Map<String, Object> paramMap);
	
	public Integer updateRevenueSignalAmountAsDoing(BigInteger applyAddId, BigInteger miniRoleId, Integer miniRoleType, Integer goalType, String goalStartTime,
			String goalEndTime,Integer optType, BigInteger gbId);
	
	/**
	 * 查询师傅补贴金额（收益中心）
	 * @param applyId
	 * @return
	 */
	public List<DredgeRevenue> selectDredgeAmountPtbt(BigInteger applyId);

	List<RevenueApply4Export> selectRevenueApplyListFinance4Export(Map<String, Object> paramMap);

	/**
	 * 付款到物业公司的账单，将其收益明细直接标为“已结算”
	 * @return 更新记录数
	 */
    Integer updateRevnueSignalAmountToSettled();
}
