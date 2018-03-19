package com.cnfantasia.server.ms.revenue.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cnfantasia.server.api.revenueApplyPush.bean.ResultInfo;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;
import com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.entity.RevenueSignalAmountEbuy;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.entity.BeginEndDate;
import com.cnfantasia.server.ms.revenue.entity.EbuyOrderRevenue;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;
import com.cnfantasia.server.ms.revenue.entity.LeftRightTime;
import com.cnfantasia.server.ms.revenue.entity.MinMaxDate;
import com.cnfantasia.server.ms.revenue.entity.PropCompanyWithRevenue;
import com.cnfantasia.server.ms.revenue.entity.RevenueAmountPrevEntity;
import com.cnfantasia.server.ms.revenue.entity.RevenueApplyParam;
import com.cnfantasia.server.ms.revenue.entity.RevenueBatchParam;
import com.cnfantasia.server.ms.revenue.entity.RevenueConfigDetail;
import com.cnfantasia.server.ms.revenue.entity.RevenueConfigForList;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;
import com.cnfantasia.server.ms.revenue.entity.RevenueSettlement;
import com.cnfantasia.server.ms.revenue.entity.UserWithPropCompany;

public interface IRevenueService {
	public List<Map<String, Object>> select_revenueList(Map<String, Object> paramMap);

	public int select_revenueList_forCount(Map<String, Object> paramMap);
	
	/**
	 * 查询物业公司列表
	 * @param companyName
	 * @param pageModel
	 * @return
	 */
	public List<PropCompanyWithRevenue> getPropCompanyWithRevenueList(BigInteger companyId,String companyName,PageModel pageModel);
	
//	/**
//	 * 查询收益配置列表
//	 * @param pageModel
//	 * @return
//	 */
//	public List<RevenueConfig> getRevenueConfigList(PageModel pageModel);
	
	/**
	 * 查询收益配置列表
	 * @param companyId
	 * @param projectType
	 * @param pageModel
	 * @return
	 */
	public List<RevenueConfigForList> getRevenueConfigList(BigInteger companyId, String companyName, Integer projectType, PageModel pageModel);
	
	/**
	 * 根据用户Id获取对应管理的物业公司信息
	 * @param omsUserId
	 * @return
	 */
	public UserWithPropCompany getUserWithPropCompanyByUserId(BigInteger omsUserId);
	
	/**
	 * 根据名称查询物业公司列表
	 * @param companyName
	 * @return
	 */
	public List<PropertyCompany> getPropCompanyList(String companyName);
	
	/**
	 * 新增收益配置详情
	 * @param companyId
	 * @param projectType
	 * @param ruleType
	 * @param totalValue
	 * @param companyValue
	 * @param agentValue
	 * @param platformValue
	 * @param repairValue 
	 * @param startDate
	 * @param endDate
	 */
	public void addRevenueConfig(BigInteger companyId, Integer projectType, Integer ruleType, Double totalValue, Double companyValue, Double agentValue, Double recommenderValue,
			Double platformValue, Double repairValue, String startDate, String endDate,Integer activeStatus);
	
	/**
	 * 修改首页配置
	 * @param rcId
	 * @param ruleType
	 * @param totalValue
	 * @param companyValue
	 * @param agentValue
	 * @param platformValue
	 * @param repairValue 
	 * @param startDate
	 * @param endDate
	 * @param activeStatus
	 */
	public void updRevenueConfig(BigInteger rcId,Integer ruleType, Double totalValue, Double companyValue, Double agentValue, Double recommenderValue,
			Double platformValue, Double repairValue, String startDate, String endDate,Integer activeStatus,LeftRightTime areaInfo,RevenueConfigDetail detail);
	
	/**
	 * 删除配置
	 * @param dataId
	 */
	public void delRevenueConfig(BigInteger dataId,LeftRightTime areaInfo);
	
	/**
	 * 根据Id查询详情
	 * @param dataId
	 * @return
	 */
	public RevenueConfigDetail getRevenueConfigDetail(BigInteger dataId);
	
	/**
	 * 查找最早最晚时间
	 * @param companyId
	 * @param projectType
	 * @return
	 */
	public MinMaxDate getMinMaxDate(BigInteger companyId, Integer projectType);
	
	/**
	 * 获取配置项可编辑的时间范围
	 * @param detailCompanyId
	 * @param detailProjectType
	 * @param detail
	 * @return
	 */
	public LeftRightTime getLeftRightTime(BigInteger detailCompanyId, Integer detailProjectType, RevenueConfigDetail detail);
	
	/**
	 * 查询 各种角色的 收益金额信息
	 * @param searchName
	 * @param pageModel
	 * @param expectedRole 角色，如财务，管理员，物业公司，物业管理处，代理
	 * @return
	 */
	public List<RevenueAmountPrevEntity> getRevenueAmountPrevList(BigInteger omsUserId,UserRole expectedRole, String searchName, PageModel pageModel);
	
	/**
	 * 根据omsUserId查询对应的拥有的角色
	 * @param omsUserId
	 * @return
	 */
	public Set<UserRole> getOmsUserRoleListByUserId(BigInteger omsUserId);

	/**
	 * 查询收益结算列表
	 */
	public List<RevenueSettlement> select_revenueSettleList(BigInteger omsUserId,UserRole expectedRole,Map<String, Object> paramMap);
	public List<RevenueApply> getRevenueApplyListFinance(Map<String, Object> paramMap,PageModel pageModel);

	/**
	 * 查询收益结算列表总记录数
	 * @param paramMap
	 * @return
	 */
	public int select_revenueSettleList_forCount(Map<String, Object> paramMap);

	public List<EbuyOrderRevenue> updateAndGetEbuyRevenueList(Map<String, Object> paramMap);
	
	public List<EbuyRevenueSignalAmount> selectEbuyRevenueSignalAmountList(Map<String, Object> paramMap);
	
	public int selectEbuyRevenueSignalAmountCount(Map<String, Object> paramMap);
	
	public int selectRevenueSignalAmountCount(Map<String, Object> paramMap);
	
	public boolean isPropertyManagerRevenuet(Map<String, Object> paramMap);
	
	public EbuyRevenueTotal selectEbuyRevenueTotal(Map<String, Object> paramMap);

	
	public void saveBatch(List<RevenueSignalAmount> revenueSignalAmountList, List<RevenueSignalAmountEbuy> revenueSignalAmountEbuyList);


	/**
	 * 用户发起提款
	 * @param omsUserId
	 * @param expectedRole
	 * @param projectType
	 * @return
	 */
	public BigInteger applyRevenue(BigInteger omsUserId,UserRole expectedRole,RevenueApplyParam applyParam);
	
	/**
	 * 系统发起提款
	 * @param applyParam
	 * @param optType
	 * @param applyUserId
	 */
	public BigInteger applyRevenueSystem(RevenueApplyParam applyParam, Integer optType,
			BigInteger applyUserId);
	
	/**
	 * 获取提款所需的参数
	 * @param omsUserId
	 * @param expectedRole
	 * @param projectType
	 * @return
	 */
	public RevenueApplyParam getRevenueApplyParam(BigInteger miniRoleId,Integer miniRoleType, Integer projectType);
	
	/**
	 * 确认结算
	 * @param applyId
	 */
	public void confirmRevenueApply(BigInteger applyId,BigInteger operUserId);
	public void confirmRevenueApplyBatch(Set<BigInteger> applyIdList, List<BigInteger> apply16Ids, BigInteger operUserId);
	
	public void saveRevenueEbuy(List<RevenueSignalAmount> revenueSignalAmountList, List<RevenueSignalAmountEbuy> revenueSignalAmountEbuyList);
	
	/**
	 * 查询满足条件的结算金额总计
	 * @param omsUserId
	 * @param expectedRole
	 * @param paramMap
	 * @return
	 */
	public Double getTotalAmountAll(Map<String, Object> paramMap);
	
	/**
	 * 创建角色
	 * @param omsUserId
	 * @param expectedRole
	 * @return
	 */
	public RevenueRole createRevenueRoleExcep(BigInteger omsUserId,
			UserRole expectedRole);
	
	public Integer updateDeliveryRevenueStatus(Map<String, Object> paramMap);
	
	
	/**
	 * 获取物业公司的历史未标记结算的实收账单起止月份
	 * @param companyId
	 * @return
	 */
	public BeginEndDate getPropertyRealPayHistoryMonth(BigInteger companyId);
	
	/**
	 * 标记指定月份的物业实收费用为已结算
	 * @param companyId
	 * @param beginEndDate
	 * @param isAll
	 */
	public void markPropertyRealPayAsFinished(BigInteger companyId,
			BeginEndDate beginEndDate, boolean isAll);
	
	public List<RevenueApply> getRevenueApplyListFinance(Map<String, Object> paramMap);
	
	public List<EbuyRevenueSignalAmount> selectEbuyRevenueSignalAmountExportList(Map<String, Object> paramMap);
	
	/***
	 * 超市收益导出
	 */
	public HSSFWorkbook createReport(List<EbuyRevenueSignalAmount> ebuyRevenueSignalAmountList);
	
	public List<BigInteger> selectPayBill_idList_byRevenueApply(BigInteger raId);

	HSSFWorkbook getRevenueHSSFWorkbook(RevenueBatchParam revenueBatchParam);

	/***
	 * 物业管理处收益查询
	 * @param omsUserId
	 * @param searchName
	 * @param pageModel
	 * @return
	 */
	public List<RevenueAmountPrevEntity> getRevenueAmountManagementPrevList(BigInteger omsUserId, String searchName,
			PageModel pageModel);
	
	/**
	 * 获取当前查询的所有数据的HSSFWorkbook
	 * 
	 * @param paramMap
	 * @return HSSFWorkbook
	 */
	public HSSFWorkbook getRevenueApplyHSSFWorkbook(Map<String, Object> paramMap);
	
	/**
	 * 生成单据
	 * 
	 * @param applyId
	 * @param goalType
	 * @param miniRoleType
	 * @return
	 */
	public ResultInfo pushBill2EAS(String applyId, String goalType, String miniRoleType);
}
