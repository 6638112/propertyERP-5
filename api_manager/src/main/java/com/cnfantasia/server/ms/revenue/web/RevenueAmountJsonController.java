package com.cnfantasia.server.ms.revenue.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.entity.BeginEndDate;
import com.cnfantasia.server.ms.revenue.entity.RevenueApplyParam;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;

/**
 * 收益金额管理
 * @author shiyl
 *
 */
@Controller
@RequestMapping("/revenueAmountJson")
public class RevenueAmountJsonController extends BaseController{
	@Resource
	private IRevenueService revenueService;
	@Resource
	private RevenueConfigCommonController revenueConfigCommonController;
	
	/**
	 * 申请提款
	 * @param request
	 * @return
	 */
	@RequestMapping("/applyRevenuePropertyCompany.json")
	@ResponseBody
	public String applyRevenueAgent(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger omsUserId = UserContext.getOperIdBigIntegerMustExist();
		BigInteger miniRoleId = ParamUtils.getBigInteger(request, "miniRoleId", null);
		Integer miniRoleType = ParamUtils.getInteger(request, "miniRoleType", null);
		Integer goalType = ParamUtils.getInteger(request, "goalType", null);
		String goalStartTime = ParamUtils.getStringTrim(request, "goalStartTime");
		String goalEndTime = ParamUtils.getStringTrim(request, "goalEndTime");
		Double totalAmount = ParamUtils.getDouble(request, "totalAmount", null);
		RevenueApplyParam applyParam = new RevenueApplyParam(miniRoleId, miniRoleType, goalType, goalStartTime, goalEndTime, totalAmount);
		
//		UserRole userRole = UserRole.PropertyCompany;
		UserRole userRole = UserRole.createUserRole(miniRoleType);
		//2.交互
		revenueService.applyRevenue(omsUserId, userRole, applyParam);
		//3.结果处理
		return JSON.toJSONString(jsonResponse);
	}
	
	/**
	 * 申请提款
	 * @param request
	 * @return
	 */
	@RequestMapping("/applyRevenueEbuy.json")
	@ResponseBody
	public String applyRevenueEbuy(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger omsUserId = UserContext.getOperIdBigIntegerMustExist();
		BigInteger miniRoleId = ParamUtils.getBigInteger(request, "miniRoleId", null);
		Integer miniRoleType = ParamUtils.getInteger(request, "miniRoleType", null);
		Integer goalType = ParamUtils.getInteger(request, "goalType", null);
		String goalStartTime = ParamUtils.getStringTrim(request, "goalStartTime");
		String goalEndTime = ParamUtils.getStringTrim(request, "goalEndTime");
		Double totalAmount = ParamUtils.getDouble(request, "totalAmount", null);
		RevenueApplyParam applyParam = new RevenueApplyParam(miniRoleId, miniRoleType, goalType, goalStartTime, goalEndTime, totalAmount);
		
		UserRole userRole = UserRole.DownstairStore;
		//2.交互
		revenueService.applyRevenue(omsUserId, userRole, applyParam);
		//3.结果处理
		return JSON.toJSONString(jsonResponse);
	}
	
	/**
	 * 确认结算
	 * @param request
	 * @return
	 */
	@RequestMapping("/confirmRevApply.json")
	@ResponseBody
	public String confirmRevApply(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger omsUserId = UserContext.getOperIdBigIntegerMustExist();
		Set<BigInteger> idSet = new HashSet<BigInteger>();
		// 购销电商供应商t_revenue_apply的f_id
		List<BigInteger> apply16Ids = new ArrayList<BigInteger>();
		{
			String applyIdList = ParamUtils.getStringTrim(request, "applyId");
			String goalTypeList = ParamUtils.getStringTrim(request, "goalType");
			if(!StringUtils.isEmpty(applyIdList)){
				String[] idArray = applyIdList.split("\\|");
				String[] goalTypeArray = goalTypeList.split("\\|");
				for(int i=0, size = idArray.length; i<size; i++){
					String tmpId = idArray[i];
					if(!StringUtils.isEmpty(tmpId) && !"16".equals(goalTypeArray[i])){
						idSet.add(new BigInteger(tmpId));
					} else {
						apply16Ids.add(new BigInteger(tmpId));
					}
				}
			}
		}
//		BigInteger applyId = ParamUtils.getBigInteger(request, "applyId", null);
		//2.交互
		revenueService.confirmRevenueApplyBatch(idSet,apply16Ids,omsUserId);
		//3.结果处理
		return JSON.toJSONString(jsonResponse);
	}
	
	/**
	 * 标记历史数据为已结算
	 * @param request
	 * @return
	 */
	@RequestMapping("/markHistory.json")
	@ResponseBody
	public String markHistory(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger companyId = ParamUtils.getBigInteger(request, "companyId", null);
		String startMonth = ParamUtils.getStringTrim(request, "startMonth");
		String endMonth = ParamUtils.getStringTrim(request, "endMonth");
		boolean isAll = ParamUtils.getBoolean(request, "isAll", false);
		BeginEndDate beginEndDate = new BeginEndDate(startMonth, endMonth, DateUtil.formatMonth_Split.get());
		//2.交互
		revenueService.markPropertyRealPayAsFinished(companyId,beginEndDate,isAll);
		//3.结果处理
		return JSON.toJSONString(jsonResponse);
	}
	
}
