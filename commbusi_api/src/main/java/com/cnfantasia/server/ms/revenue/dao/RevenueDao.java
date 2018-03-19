package com.cnfantasia.server.ms.revenue.dao;

import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyDict;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.business.pub.page.TotalCountGetter;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;
import com.cnfantasia.server.ms.revenue.constant.RevenueConstant;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
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
import com.cnfantasia.server.ms.revenue.entity.RevenueRoleAll;
import com.cnfantasia.server.ms.revenue.entity.RevenueRoleMultiId;
import com.cnfantasia.server.ms.revenue.entity.RevenueSettlement;
import com.cnfantasia.server.ms.revenue.entity.RoomValidateForRevenue;
import com.cnfantasia.server.ms.revenue.entity.UserWithPropCompany;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RevenueDao extends AbstractBaseDao implements IRevenueDao {

	@Override
	public List<Map<String, Object>> select_revenueList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenue.select_revenueList", paramMap);
	}

	@Override
	public int select_revenueList_forCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "revenue.select_revenueList", paramMap);
	}

	@Override
	public List<PropCompanyWithRevenue> selectPropCompanyWithRevenueList(BigInteger companyId,String companyName, PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("companyId", companyId);
		tmpMap.put("companyName", companyName);
		String pageSqlKey = "revenue.select_PropCompanyWithRevenue_List_page";
		String countSqlKey = "revenue.select_PropCompanyWithRevenue_List_count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}


	@Override
	public List<RevenueConfigForList> selectRevenueConfigList(BigInteger companyId, String companyName, Integer projectType, PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("companyId", companyId);
		tmpMap.put("companyName", companyName);
		tmpMap.put("projectType", projectType);
		String pageSqlKey = "revenue.select_RevenueConfig_List_page";
		String countSqlKey = "revenue.select_RevenueConfig_List_count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public UserWithPropCompany selectUserWithPropCompanyByUserId(BigInteger omsUserId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("omsUserId", omsUserId);
		return sqlSession.selectOne("revenue.select_UserWithPropCompany_ByUserId", tmpMap);
	}

	@Override
	public List<PropertyCompany> selectPropCompanyList(String companyName) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("companyName", companyName);
		return sqlSession.selectList("revenue.select_PropCompany_List", tmpMap);
	}

	@Override
	public RevenueConfigDetail selectRevenueConfigDetail(BigInteger dataId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("dataId", dataId);
		return sqlSession.selectOne("revenue.select_RevenueConfig_Detail", tmpMap);
	}

	@Override
	public MinMaxDate selectMinMaxDate(BigInteger companyId, Integer projectType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("companyId", companyId);
		tmpMap.put("projectType", projectType);
		return sqlSession.selectOne("revenue.select_MinMaxDate", tmpMap);
	}

	@Override
	public Integer selectRoomValidCountByPropertyCompanyId(RevenueRole revenueRole, String startTime, String endTime) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("miniRoleId", revenueRole.getRoleId());
		tmpMap.put("miniRoleType", revenueRole.getRole().getCode());
		tmpMap.put("startTime", startTime);
		tmpMap.put("endTime", endTime);
		return sqlSession.selectOne("revenue.select_RoomValidCount_ByPropertyCompanyId", tmpMap);
	}
	
	private Double selectRevenueMoneyTotalByGoalType(RevenueRole revenueRole, String startTime, String endTime,Integer goalType){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("miniRoleId", revenueRole.getRoleId());
		tmpMap.put("miniRoleType", revenueRole.getRole().getCode());
		tmpMap.put("startTime", startTime);
		tmpMap.put("endTime", endTime);
		tmpMap.put("goalType", goalType);
		return sqlSession.selectOne("revenue.select_Revenue_MoneyTotal_ByGoalType",tmpMap);
	}
	
	@Override
	public Double selectPayBillRealPayMoneyTotal(RevenueRole revenueRole, String startTime, String endTime) {
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("miniRoleId", revenueRole.getRoleId());
//		tmpMap.put("miniRoleType", revenueRole.getRole().getCode());
//		tmpMap.put("startTime", startTime);
//		tmpMap.put("endTime", endTime);
//		return sqlSession.selectOne("revenue.select_PayBill_RealPayMoney_Total",tmpMap);
		Integer goalType = RevenueDict.RevenueProject.PropertyRealPayAmout;
		return selectRevenueMoneyTotalByGoalType(revenueRole, startTime, endTime, goalType);
	}
	
	@Override
	public Double selectPayBillSubsidyMoneyTotal(RevenueRole revenueRole,
			String startTime, String endTime) {
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("miniRoleId", revenueRole.getRoleId());
//		tmpMap.put("miniRoleType", revenueRole.getRole().getCode());
//		tmpMap.put("startTime", startTime);
//		tmpMap.put("endTime", endTime);
//		return sqlSession.selectOne("revenue.select_PayBill_SubsidyMoney_Total",tmpMap);
		Integer goalType = RevenueDict.RevenueProject.PropertySubsidyAmout;
		return selectRevenueMoneyTotalByGoalType(revenueRole, startTime, endTime, goalType);
	}
	
	@Override
	public Double selectPayBillOtherFeeTotal(RevenueRole revenueRole,
			String startTime, String endTime){
		Integer goalType = RevenueDict.RevenueProject.PropertyOtherFee;
		return selectRevenueMoneyTotalByGoalType(revenueRole, startTime, endTime, goalType);
	}
	
	@Override
	public List<RevenueConfigByTime> selectRevenueConfigByTimeListByCompanyId(Integer projectType, BigInteger companyId, String startTime, String endTime) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("projectType", projectType);
		tmpMap.put("companyId", companyId);
		tmpMap.put("startTime", startTime);
		tmpMap.put("endTime", endTime);
		return sqlSession.selectList("revenue.select_RevenueConfigByTimeList_ByCompanyId", tmpMap);
	}

	@Override
	public List<BigInteger> selectCompanyIdListByAgentId(BigInteger agentId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("agentId", agentId);
		return sqlSession.selectList("revenue.select_CompanyIdList_ByAgentId", tmpMap);
	}
	
	private Map<String,Object> generateParamMap(BigInteger companyId,Integer projectType, String startTime, String endTime,Integer isPropFee){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("companyId", companyId);
		tmpMap.put("projectType", projectType);
		tmpMap.put("startTime", startTime);
		tmpMap.put("endTime", endTime);
		tmpMap.put("isPropFee", isPropFee);//是否为物业费
		return tmpMap;
	}
	private List<PayBillForRevenue> selectToSigPayBillList(BigInteger companyId,Integer projectType, String startTime, String endTime,Integer isPropFee) {
		Map<String,Object> tmpMap = generateParamMap(companyId, projectType, startTime, endTime, isPropFee);
		return sqlSession.selectList("revenue.select_ToSig_PayBill_List", tmpMap);
	}
	@Override
	public List<PayBillForRevenue> selectToSigPayBillList(BigInteger companyId,Integer projectType, String startTime, String endTime) {
		return selectToSigPayBillList(companyId, projectType, startTime, endTime, PlotpropertyDict.PayBillType_IsPropFee.YES);
	}
	@Override
	public List<PayBillForRevenue> selectToSigPayBillList4BillMonth(BigInteger companyId,Integer projectType, String startTime, String endTime) {
		return selectToSigPayBillList(companyId, projectType, startTime, endTime, PlotpropertyDict.PayBillType_IsPropFee.YES);
	}
	
	
	@Override
	public List<PayBillForRevenueSubsidy> selectToSigPayBillSubsidyList(
			BigInteger companyId, Integer projectType, String startTime,
			String endTime) {
		Map<String,Object> tmpMap = generateParamMap(companyId, projectType, startTime, endTime, PlotpropertyDict.PayBillType_IsPropFee.YES);
		return sqlSession.selectList("revenue.select_ToSig_PayBillSubsidy_List", tmpMap);
	}
	
	public List<PayBillForRevenueOtherFee> selectToSigPayBillOtherFeeList(
			BigInteger companyId, Integer projectType, String startTime,
			String endTime){
		Map<String,Object> tmpMap = generateParamMap(companyId, projectType, startTime, endTime, PlotpropertyDict.PayBillType_IsPropFee.NO_notMR);
		return sqlSession.selectList("revenue.select_ToSig_PayBillOtherFee_List", tmpMap);
	}
	
	
//	@Override
//	public List<RevenueSignalAmount> selectRevenueSignalAmountList(Integer projectType, RevenueRole revenueRole) {
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("projectType", projectType);
//		tmpMap.put("miniRoleType", revenueRole.getRole().getCode());
//		tmpMap.put("miniRoleId",  revenueRole.getRoleId());
//		return sqlSession.selectList("revenue.select_RevenueSignal_AmountList",tmpMap);
//	}

	@Override
	public List<BigInteger> selectRevConfigCompanyIdList(Integer projectType,UserRole role) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("projectType", projectType);
		tmpMap.put("miniRoleType", role.getCode());
		return sqlSession.selectList("revenue.select_RevConfig_CompanyIdList",tmpMap);
	}
	
	@Override
	public List<BigInteger> selectRevConfigAgentIdList(Integer projectType,UserRole role) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("projectType", projectType);
		tmpMap.put("miniRoleType", role.getCode());
		return sqlSession.selectList("revenue.select_RevConfig_AgentIdList",tmpMap);
	}
	
	@Override
	public List<Integer> selectOmsUserRoleListByUserId(BigInteger omsUserId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("omsUserId", omsUserId);
		EachRoleCount eachCount = sqlSession.selectOne("revenue.select_OmsUserRoleList_ByUserId",tmpMap);
		List<Integer> roleList = new ArrayList<Integer>();
		if(eachCount!=null){
			if(eachCount.getAdminCount()!=null&&eachCount.getAdminCount()>0){
				roleList.add(RevenueDict.MiniRoleType.SysAdmin);
			}
			if(eachCount.getCompanyCount()!=null&&eachCount.getCompanyCount()>0){
				roleList.add(RevenueDict.MiniRoleType.PropertyCompany);
			}
			if(eachCount.getPcManagermentCount()!=null&&eachCount.getPcManagermentCount()>0){
				roleList.add(RevenueDict.MiniRoleType.PCManagement);
			}
			if(eachCount.getAgentCount()!=null&&eachCount.getAgentCount()>0){
				roleList.add(RevenueDict.MiniRoleType.PropertyAgent);
			}
			if(eachCount.getFinanceCount()!=null&&eachCount.getFinanceCount()>0){
				roleList.add(RevenueDict.MiniRoleType.Financer);
			}
			if(eachCount.getDownStarCount()!=null&&eachCount.getDownStarCount()>0){
				roleList.add(RevenueDict.MiniRoleType.DownstairStore);
			}
			if(eachCount.getMasterCount()!=null&&eachCount.getMasterCount()>0){
				roleList.add(RevenueDict.MiniRoleType.RepairMaster);
			}
			if(eachCount.getServiceCount()!=null&&eachCount.getServiceCount()>0){
				roleList.add(RevenueDict.MiniRoleType.CustomerService);
			}
			if(eachCount.getDistrictCount()!=null&&eachCount.getDistrictCount()>0){
				roleList.add(RevenueDict.MiniRoleType.District);
			}
		}
		return roleList;
//		return sqlSession.selectList("revenue.select_OmsUserRoleList_ByUserId",tmpMap);
	}
	
	@Override
	public List<RevenueConfigByTime> selectActiveRevenueConfigList(RevenueRole revenueRole, Integer projectType) {
		UserRole role = revenueRole.getRole();
		BigInteger roleId = revenueRole.getRoleId();
		switch (role) {
			case PropertyCompany:
				return selectActiveRevenueConfigList(roleId, projectType);
			case PropertyAgent://查询代理商角色的验证门牌
				List<BigInteger> companyIdList = selectCompanyIdListByAgentId(roleId);
				return selectActiveRevenueConfigList(companyIdList, projectType);
			case RepairMaster:
				return selectActiveRevenueConfigList(roleId, projectType);
			default:
				throw new BusinessRuntimeException("RevenueDao.selectActiveRevenueConfigList.roleNotSupport",new Object[]{role.getCode()});
		}
	}
	
	@Override
	public List<RoomValidateForRevenue> selectToSigRoomValidateList(RevenueRole revenueRole, Integer projectType, String startTime, String endTime) {
		UserRole role = revenueRole.getRole();
		BigInteger roleId = revenueRole.getRoleId();
		switch (role) {
			case PropertyCompany:
				return selectToSigRoomValidateList(roleId, projectType, startTime, endTime);
			case PropertyAgent:
				List<BigInteger> companyIdList = selectCompanyIdListByAgentId(roleId);
				return selectToSigRoomValidateList(companyIdList, projectType, startTime, endTime);
			default:
				throw new BusinessRuntimeException("RevenueDao.selectToSigRoomValidateList.roleNotSupport",new Object[]{role.getCode()});
		}
	}
	
	private List<RevenueConfigByTime> selectActiveRevenueConfigList(BigInteger companyId, Integer projectType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("projectType", projectType);
		tmpMap.put("companyId", companyId);
		if(projectType!=null&&
				(projectType.compareTo(RevenueDict.RevenueProject.PropertyRealPayAmout)==0||projectType.compareTo(RevenueDict.RevenueProject.PropertySubsidyAmout)==0
				||projectType.compareTo(RevenueDict.RevenueProject.ServiceOrder)==0||projectType.compareTo(RevenueDict.RevenueProject.PropertyOtherFee)==0)
			){//syl-add-锁定物业费实收规则
			tmpMap.put("companyId", RevenueConstant.DEFAULT_PROPERTY_COMPANY_ID);
		}
		return sqlSession.selectList("revenue.select_Active_RevenueConfig_List", tmpMap);
	}
	private List<RevenueConfigByTime> selectActiveRevenueConfigList(List<BigInteger> companyIdList, Integer projectType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("projectType", projectType);
		tmpMap.put("companyIdList", companyIdList);
		if(projectType!=null&&
				(projectType.compareTo(RevenueDict.RevenueProject.PropertyRealPayAmout)==0||projectType.compareTo(RevenueDict.RevenueProject.PropertySubsidyAmout)==0
				||projectType.compareTo(RevenueDict.RevenueProject.ServiceOrder)==0)
			){//syl-add-锁定物业费实收规则
			List<BigInteger> tmpList = new ArrayList<BigInteger>();
			tmpList.add(RevenueConstant.DEFAULT_PROPERTY_COMPANY_ID);
			tmpMap.put("companyIdList", tmpList);
		}
		return sqlSession.selectList("revenue.select_Active_RevenueConfig_List_Multi", tmpMap);
	}
	
	private List<RoomValidateForRevenue> selectToSigRoomValidateList(BigInteger companyId, Integer projectType, String startTime, String endTime) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("projectType", projectType);
		tmpMap.put("companyId", companyId);
		tmpMap.put("startTime", startTime);
		tmpMap.put("endTime", endTime);
		return sqlSession.selectList("revenue.select_ToSig_RoomValidate_List", tmpMap);
	}
	
	private List<RoomValidateForRevenue> selectToSigRoomValidateList(List<BigInteger> companyIdList, Integer projectType, String startTime, String endTime) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("projectType", projectType);
		tmpMap.put("companyIdList", companyIdList);
		tmpMap.put("startTime", startTime);
		tmpMap.put("endTime", endTime);
		return sqlSession.selectList("revenue.select_ToSig_RoomValidate_List_Multi", tmpMap);
	}
	
	@Override
	public List<RevenueAmountPrevEntity> selectRevenueAmountPrevList(RevenueRole revenueRole, String searchName, PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		if(revenueRole==null){return null;}
		//queryType 1.拥有查询全部数据的权限;2.多个Id;3.单个Id
		if(revenueRole instanceof RevenueRoleAll){//拥有查询全部数据的权限
			tmpMap.put("queryType", 1);
		}else if(revenueRole instanceof RevenueRoleMultiId){//多个Id
			tmpMap.put("queryType", 2);
			RevenueRoleMultiId tmp = (RevenueRoleMultiId)revenueRole;
			List<BigInteger> roleIdList = tmp.getRoleIdList();
			if(roleIdList==null||roleIdList.size()<=0){return null;}
			tmpMap.put("miniRoleType", tmp.getRole().getCode());
			tmpMap.put("miniRoleIdList", roleIdList);
		}else{//单个Id
			BigInteger roleId = revenueRole.getRoleId();
			if(roleId==null){return null;}
			tmpMap.put("queryType", 3);
			tmpMap.put("miniRoleType", revenueRole.getRole().getCode());
			tmpMap.put("miniRoleId", roleId);
		}
		tmpMap.put("searchName", searchName);
		String pageSqlKey = "revenue.select_RevenueAmountPrev_List_page";
		String countSqlKey = "revenue.select_RevenueAmountPrev_List_count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}
	
	@Override
	public RevenueRole createRevenueRole(BigInteger omsUserId,UserRole expectedRole){
		List<Integer> roleTypeList = selectOmsUserRoleListByUserId(omsUserId);
		if(roleTypeList.contains(RevenueDict.MiniRoleType.District)) {
			return new RevenueRoleAll(expectedRole);
		}
		if(omsUserId==null||expectedRole==null){return null;}
		if(roleTypeList==null||!roleTypeList.contains(expectedRole.getCode())){
			return null;//未包含该角色
		}
		BigInteger roleId = null;
		switch (expectedRole) {
			case PropertyCompany:
				roleId = selectPropCompanyIdByOmsUserId(omsUserId);
				break;
			case PCManagement:
				roleId = selectPCManagementIdByOmsUserId(omsUserId);
				break;
			case PropertyAgent:
				roleId = selectPropAgentIdByOmsUserId(omsUserId);
				break;
			case SysAdmin:
			case Financer:
				return new RevenueRoleAll(expectedRole);
			case DownstairStore:
				List<BigInteger> roleIdList = selectDownstairStoreIdByOmsUserId(omsUserId);
				if(roleIdList==null || roleIdList.size()<=0){
					return null;
				}
				return new RevenueRoleMultiId(expectedRole, roleIdList);
			case RepairMaster:
				roleId = selectRepairMasterIdByUserId(omsUserId);
				return new RevenueRole(expectedRole, roleId);
			default:
				break;
		}
		if(roleId==null){return null;}
		RevenueRole revenueRole = new RevenueRole(expectedRole, roleId);
		return revenueRole;
	}
	
	private BigInteger selectRepairMasterIdByUserId(BigInteger omsUserId) {
		return omsUserId;
	}

	private BigInteger selectPropCompanyIdByOmsUserId(BigInteger omsUserId){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("omsUserId", omsUserId);
		return sqlSession.selectOne("revenue.select_PropCompanyId_ByOmsUserId",omsUserId);
	}
	
	private BigInteger selectPCManagementIdByOmsUserId(BigInteger omsUserId){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("omsUserId", omsUserId);
		return sqlSession.selectOne("revenue.select_PCManagementId_ByOmsUserId",omsUserId);
	}
	
	private BigInteger selectPropAgentIdByOmsUserId(BigInteger omsUserId){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("omsUserId", omsUserId);
		return sqlSession.selectOne("revenue.select_PropAgentId_ByOmsUserId",omsUserId);
	}
	private List<BigInteger> selectDownstairStoreIdByOmsUserId(BigInteger omsUserId){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("omsUserId", omsUserId);
		return sqlSession.selectList("revenue.selectDownstairStoreIdByOmsUserId",omsUserId);
	}
	@Override
	public List<RevenueSettlement> select_revenueSettleList(RevenueRole revenueRole,Map<String, Object> tmpMap) {
		if(tmpMap==null){
			tmpMap= new HashMap<String, Object>();
		}
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
		if(revenueRole==null){return null;}
		//queryType 1.拥有查询全部数据的权限;2.多个Id;3.单个Id
		if(revenueRole instanceof RevenueRoleAll){//拥有查询全部数据的权限
			tmpMap.put("queryType", 1);
		}else if(revenueRole instanceof RevenueRoleMultiId){//多个Id
			tmpMap.put("queryType", 2);
			RevenueRoleMultiId tmp = (RevenueRoleMultiId)revenueRole;
			List<BigInteger> roleIdList = tmp.getRoleIdList();
			if(roleIdList==null||roleIdList.size()<=0){return null;}
			tmpMap.put("miniRoleType", tmp.getRole().getCode());
			tmpMap.put("miniRoleIdList", roleIdList);
		}else{//单个Id
			BigInteger roleId = revenueRole.getRoleId();
			if(roleId==null){return null;}
			tmpMap.put("queryType", 3);
			tmpMap.put("miniRoleType", revenueRole.getRole().getCode());
			tmpMap.put("miniRoleId", roleId);
		}
		return sqlSession.selectList("revenue.select_Rev_applyList", tmpMap);
	}
	@Override
	public List<RevenueApply> selectRevenueApplyListFinance(Map<String, Object> paramMap,PageModel pageModel) {
		String pageSqlKey = "revenue.select_RevenueApplyList_Finance_page";
		String countSqlKey = "revenue.select_RevenueApplyList_Finance_count";
		return PageQueryUtil.selectPageList(sqlSession, paramMap, pageModel, pageSqlKey, countSqlKey);
	}
	
	@Override
	public List<RevenueApply4Export> selectRevenueApplyListFinance4Export(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenue.select_RevenueApplyList_Finance_for_export", paramMap);
	}

	@Override
	public Integer updateRevnueSignalAmountToSettled() {
		return sqlSession.update("revenue.updateRevnueSignalAmountToSettled");
	}

	public List<RevenueApply> selectRevenueApplyListFinance(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenue.select_RevenueApplyList_Finance_page", paramMap);
	}
	
	@Override
	public Double selectTotalAmountAllFinance(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenue.select_TotalAmount_All_Finance", paramMap);
	}
	
	@Override
	public int select_revenueSettleList_forCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "revenue.select_Rev_applyList", paramMap);
	}
	@Override
	public Integer updateRevenueSignalAmountRoleName() {
		Integer resCount = 0;
		resCount+=sqlSession.update("revenue.update_RevenueSignalAmount_RoleName_PropertyCompany");
		resCount+=sqlSession.update("revenue.update_RevenueSignalAmount_RoleName_PropertyAgent");
		return resCount;
	}

	@Override
	public String selectLastApplyGoalEndTime(BigInteger miniRoleId, Integer miniRoleType, Integer goalType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("miniRoleId", miniRoleId);
		tmpMap.put("miniRoleType", miniRoleType);
		tmpMap.put("goalType", goalType);
		return sqlSession.selectOne("revenue.select_Last_ApplyGoalEndTime", tmpMap);
	}

	@Override
	public Integer updateRevenueSignalAmountAsDoing(BigInteger applyAddId, BigInteger miniRoleId, Integer miniRoleType, Integer goalType, String goalStartTime,
			String goalEndTime,Integer optType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("applyAddId", applyAddId);
		tmpMap.put("miniRoleId", miniRoleId);
		tmpMap.put("miniRoleType", miniRoleType);
		tmpMap.put("goalType", goalType);
		tmpMap.put("goalStartTime", goalStartTime);
		tmpMap.put("goalEndTime", goalEndTime);
		tmpMap.put("optType", optType);
		return sqlSession.update("revenue.update_RevenueSignalAmount_AsDoing", tmpMap);
	}
	
	@Override
	public Integer updateRevenueSignalAmountAsDoing(BigInteger applyAddId, BigInteger miniRoleId, Integer miniRoleType, Integer goalType, String goalStartTime,
			String goalEndTime,Integer optType, BigInteger gbId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("applyAddId", applyAddId);
		tmpMap.put("miniRoleId", miniRoleId);
		tmpMap.put("miniRoleType", miniRoleType);
		tmpMap.put("goalType", goalType);
		tmpMap.put("goalStartTime", goalStartTime);
		tmpMap.put("goalEndTime", goalEndTime);
		tmpMap.put("optType", optType);
		tmpMap.put("gdId", gbId);
		return sqlSession.update("revenue.update_RevenueSignalAmount_AsDoing", tmpMap);
	}

	@Override
	public RevenueMoneyShow selectRevenueApplyTotalFromDetail(BigInteger applyAddId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("applyAddId", applyAddId);
		return sqlSession.selectOne("revenue.select_RevenueApplyTotal_FromDetail", tmpMap);
	}

	@Override
	public Double selectRevenueApplyTotalByTime(BigInteger miniRoleId, Integer miniRoleType, Integer goalType, String goalStartTime, String goalEndTime) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("miniRoleId", miniRoleId);
		tmpMap.put("miniRoleType", miniRoleType);
		tmpMap.put("goalType", goalType);
		tmpMap.put("goalStartTime", goalStartTime);
		tmpMap.put("goalEndTime", goalEndTime);
		return sqlSession.selectOne("revenue.select_RevenueApplyTotal_ByTime", tmpMap);
	}

	@Override
	public Integer updateRevenueApplyAsFinished(BigInteger applyId,BigInteger operUserId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("applyId", applyId);
		tmpMap.put("operUserId", operUserId);
		return sqlSession.update("revenue.update_RevenueApply_AsFinished", tmpMap);
	}
	@Override
	public Integer updateRevenueApplyAsFinishedBatch(Set<BigInteger> applyIdList,BigInteger operUserId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("applyIdList", applyIdList);
		tmpMap.put("operUserId", operUserId);
		return sqlSession.update("revenue.update_RevenueApply_AsFinished_Batch", tmpMap);
	}
	
	@Override
	public Integer updateRevenueApplyWithMerchant(List<BigInteger> applyIds,BigInteger operUserId){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("applyIdList", applyIds);
		tmpMap.put("operUserId", operUserId);
		return sqlSession.update("revenue.update_revenueApply_with_merchant", tmpMap);
	}
	
	//收益中心－楼下店铺相关
	public Integer updateForEbuyRevenue(Map<String, Object> paramMap) {
		return sqlSession.update("revenue.updateForEbuyRevenue", paramMap);
	}
	
	public Integer updateDeliveryRevenueStatus(Map<String, Object> paramMap) {
		return sqlSession.update("revenue.updateDeliveryRevenueStatus", paramMap);
	}
	
	public List<EbuyOrderRevenue> selectEbuyRevenueList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenue.selectEbuyRevenueList", paramMap);
	}
	
	public Integer updateForEbuyRevenueProccessed(BigInteger deliverOrderId) {
		return sqlSession.update("revenue.updateForEbuyRevenueProccessed", deliverOrderId);
	}
	
	public List<EbuyRevenueSignalAmount> selectEbuyRevenueSignalAmountList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenue.selectEbuyRevenueSignalAmountList", paramMap);
	}
	
	public List<EbuyRevenueSignalAmount> selectEbuyRevenueSignalAmountExportList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenue.selectEbuyRevenueSignalAmountExportList", paramMap);
	}
	
	public int selectRevenueSignalAmountCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenue.selectRevenueSignalAmountCount", paramMap);
	}
	
	public boolean isPropertyManagerRevenuet(Map<String, Object> paramMap) {
		Integer count =  sqlSession.selectOne("revenue.isPropertyManagerRevenuet", paramMap);
		return count > 0 ? true : false;
	}
	
	public EbuyRevenueTotal selectEbuyRevenueTotal(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenue.selectEbuyRevenueTotal", paramMap);
	}
	
	public List<EbuyDelivDiscount> getEbuyDelivDiscountList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenue.getEbuyDelivDiscountList", paramMap);
	}
	
	public int selectEbuyRevenueSignalAmountCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("revenue.selectEbuyRevenueSignalAmountCount", paramMap);
	}

	//楼下店
	
	@Override
	public Integer updateRevenueApplyBakInfo(BigInteger applyId,Integer miniRoleType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("applyId", applyId);
		Integer resCount = 0;
		if(miniRoleType!=null){
			if(RevenueDict.MiniRoleType.PropertyCompany.compareTo(miniRoleType)==0){
				resCount = sqlSession.update("revenue.update_RevenueApply_BakInfo_PropertyCompany", tmpMap);
			}else if(RevenueDict.MiniRoleType.PCManagement.compareTo(miniRoleType)==0){
				resCount = sqlSession.update("revenue.update_RevenueApply_BakInfo_PropertyManagement", tmpMap);
			}else if(RevenueDict.MiniRoleType.PropertyAgent.compareTo(miniRoleType)==0){
				resCount = sqlSession.update("revenue.update_RevenueApply_BakInfo_PropertyAgent", tmpMap);
			}else if(RevenueDict.MiniRoleType.DownstairStore.compareTo(miniRoleType)==0){
				resCount = sqlSession.update("revenue.update_RevenueApply_BakInfo_DownstairStore", tmpMap);
			}else if(RevenueDict.MiniRoleType.RepairMaster.compareTo(miniRoleType)==0){
				resCount = sqlSession.update("revenue.update_RevenueApply_BakInfo_RepairMaster", tmpMap);
			}else if(RevenueDict.MiniRoleType.Recommender.compareTo(miniRoleType)==0){
				resCount = sqlSession.update("revenue.update_RevenueApply_BakInfo_RepairRecommend", tmpMap);
			}
		}
		return resCount;
	}

	@Override
	public List<DredgeBillForRevenue> selectToSigDredgeBillList(
			BigInteger masterUserId, Integer projectType, String startTime,
			String endTime) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("masterUserId", masterUserId);
		tmpMap.put("projectType", projectType);
		tmpMap.put("startTime", startTime);
		tmpMap.put("endTime", endTime);
		return sqlSession.selectList("revenue.select_ToSig_DredgeBill_List", tmpMap);
	}


	@Override
	public List<BigInteger> selectSysApplyToDoCompanyList() {
		return sqlSession.selectList("revenue.select_SysApply_ToDo_Company_List");
	}
	
	@Override
	public List<BigInteger> selectSysApplyToDoList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenue.select_SysApply_ToDo_List", paramMap);
	}

	@Override
	public Double selectDredgePayMoneyTotal(RevenueRole revenueRole,
			String startTime, String endTime) {
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("miniRoleId", revenueRole.getRoleId());
//		tmpMap.put("miniRoleType", revenueRole.getRole().getCode());
//		tmpMap.put("startTime", startTime);
//		tmpMap.put("endTime", endTime);
//		return sqlSession.selectOne("revenue.select_DredgePayMoney_Total",tmpMap);
		Integer goalType = RevenueDict.RevenueProject.ServiceOrder;
		return selectRevenueMoneyTotalByGoalType(revenueRole, startTime, endTime, goalType);
	}

	@Override
	public String selectLastEndTime_byMasterUserId(BigInteger userId) {
		return sqlSession.selectOne("dredge.selectLastEndTime_byMasterUserId", userId);
	}
	
	@Override
	public BeginEndDate selectPropertyRealPayHistoryMonth(BigInteger companyId){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("companyId", companyId);
		/*{
			Date lastBillMonth =sqlSession.selectOne("revenue.select_Last_BillMonth_ByCompanyId", companyId);//根据物业公司Id获取上个已缴费的账单月份
			if(lastBillMonth==null){return null;}
			tmpMap.put("lastBillMonth", lastBillMonth);
		}*/
		return sqlSession.selectOne("revenue.select_PropertyRealPay_History_Month", tmpMap);
	}

	@Override
	public String selectLastEndTime_byRecommendUserId(BigInteger userId) {
		return sqlSession.selectOne("dredge.selectLastEndTime_byRecommendUserId", userId);
	}

	@Override
	public List<BigInteger> selectPayBill_idList_byRevenueApply(BigInteger raId) {
		return sqlSession.selectList("revenue.selectPayBill_idList", raId);
	}

	@Override
	public List<RevenueAmountPrevEntity> selectRevenueAmountManagementPrevList(BigInteger omsUserId, String searchName,
			PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("searchName", searchName);
		tmpMap.put("omsUserId", omsUserId);
		String pageSqlKey = "revenue.select_RevenueAmountManagementPrev_List_page";
		String countSqlKey = "revenue.select_RevenueAmountManagementPrev_List_count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}

	@Override
	public List<RevenueSettlement> select_revenueSettleManagementList(Map<String, Object> paramMap) {
		return sqlSession.selectList("revenue.select_Rev_apply_Manage_List", paramMap);
	}
	/**
	 * 查询师傅补贴金额（收益中心）
	 * @param applyId
	 * @return
	 */
	@Override
	public List<DredgeRevenue> selectDredgeAmountPtbt(BigInteger applyId){
		return sqlSession.selectList("revenue.select_dredge_amount_ptbt", applyId);
	}

}
