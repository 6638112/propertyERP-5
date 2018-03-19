package com.cnfantasia.server.ms.revenue.web;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.plotproperty.constant.FinanceConfig;
import com.cnfantasia.server.api.plotproperty.entity.FinanceBuyEntity;
import com.cnfantasia.server.api.plotproperty.entity.FinanceDeductionDetail;
import com.cnfantasia.server.api.plotproperty.entity.FinanceProfitEntity;
import com.cnfantasia.server.api.plotproperty.entity.FinanceSum;
import com.cnfantasia.server.api.plotproperty.service.FinanceService;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;


/**
 * 物业宝收益中心报表
 * 
 * @author yewj 
 */
@Controller
@RequestMapping("/finance")
public class FinanceController extends BaseController {
	
	private FinanceService financeService;
	
	/**
	 * 物业抵扣收益表，只是物业或者是代理商看到的界面。 01
	 * @param request
	 * @return
	 */
	@RequestMapping("/profitList.html")
	public ModelAndView profitList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String city = ParamUtils.getString(request, "city", null);
		String residential = ParamUtils.getString(request, "residential", null);
		String building = ParamUtils.getString(request, "building", null);
		String roomNum = ParamUtils.getString(request, "roomNum", null);
		Date startTime = ParamUtils.getDate(request, "startTime", null, "yyyy-MM-dd HH:mm");
		Date endTime = ParamUtils.getDate(request, "endTime", null, "yyyy-MM-dd HH:mm");
		
		BigInteger omsUserId = UserContext.getOperIdBigIntegerMustExist();
		Map<String, Object> paramMap = new HashMap<String, Object>();
//		if(UserContext.getCurrUser().getIsadmin() != 1) {
//			paramMap.put("omsUserId", omsUserId);
//		}
		paramMap.put("omsUserId", omsUserId);
		paramMap.put("city", city);
		paramMap.put("residential", residential);
		paramMap.put("building", building);
		paramMap.put("roomNum", roomNum);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		PageUtils.addPageInfoToParam(request, paramMap);
		
		List<FinanceProfitEntity> profitList = financeService.getProfitListByWyOrAgent(paramMap);
		int resultSize = financeService.getProfitListByWyOrAgentCount(paramMap);
		FinanceSum financeSum = financeService.getProfitListByWyOrAgentSum(paramMap);
		request.setAttribute("profitList", profitList);
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("financeSum", financeSum);
		
		modelAndView.setViewName("/revenue/profitList");
		return modelAndView;
	}
	
	/**03
	 * 物业抵扣回款表，和物业抵扣收益表差不多，只不过这个界面是后台运营人员访问。包括物业和代理商的信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/profitAllList.html")
	public ModelAndView profitAllList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String agentName = ParamUtils.getString(request, "agentName", null);
		String wyName = ParamUtils.getString(request, "wyName", null);
		String city = ParamUtils.getString(request, "city", null);
		String residential = ParamUtils.getString(request, "residential", null);
		String building = ParamUtils.getString(request, "building", null);
		String roomNum = ParamUtils.getString(request, "roomNum", null);
		Date startTime = ParamUtils.getDate(request, "startTime", null, "yyyy-MM-dd HH:mm");
		Date endTime = ParamUtils.getDate(request, "endTime", null, "yyyy-MM-dd HH:mm");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("agentName", agentName);
		paramMap.put("wyName", wyName);
		paramMap.put("city", city);
		paramMap.put("residential", residential);
		paramMap.put("building", building);
		paramMap.put("roomNum", roomNum);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		PageUtils.addPageInfoToParam(request, paramMap);
		
		List<FinanceProfitEntity> profitList = financeService.getProfitAllList(paramMap);
		int resultSize = financeService.getProfitAllListCount(paramMap);
		FinanceSum financeSum = financeService.getProfitAllListSum(paramMap);
		request.setAttribute("profitList", profitList);
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("financeSum", financeSum);
		
		modelAndView.setViewName("/revenue/profitAllList");
		return modelAndView;
	}
	
	/**
	 * 02
	 * @param request
	 * @return
	 */
	@RequestMapping("/buyList.html")
	public ModelAndView buyList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String city = ParamUtils.getString(request, "city", null);
		String residential = ParamUtils.getString(request, "residential", null);
		String building = ParamUtils.getString(request, "building", null);
		String roomNum = ParamUtils.getString(request, "roomNum", null);
		String financeType = ParamUtils.getString(request, "financeType", null);
		Date deductionTime = ParamUtils.getDate(request, "deductionTime", null, "yyyy-MM");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("city", city);
		paramMap.put("residential", residential);
		paramMap.put("building", building);
		paramMap.put("roomNum", roomNum);
		paramMap.put("deductionTime", deductionTime);
		financeType = (financeType == null) ? FinanceConfig.FinanceType.FINANCE_PLOTPROPERTY : financeType;
		paramMap.put("financeType", financeType);
		PageUtils.addPageInfoToParam(request, paramMap);
		
		List<FinanceBuyEntity> buyList = financeService.getFinanceBuyList(paramMap);
		int resultSize = financeService.getFinanceBuyListCount(paramMap);
		FinanceSum financeSum = financeService.getFinanceBuyListSum(paramMap);
		request.setAttribute("buyList", buyList);
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("financeSum", financeSum);
		request.setAttribute("financeType", financeType);
		
		modelAndView.setViewName("/revenue/buyList");
		return modelAndView;
	}
	
	/**
	 * 04
	 * @param request
	 * @return
	 */
	@RequestMapping("/deductionList.html")
	public ModelAndView deductionList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String wyName = ParamUtils.getString(request, "wyName", null);
		String city = ParamUtils.getString(request, "city", null);
		String residential = ParamUtils.getString(request, "residential", null);
		String building = ParamUtils.getString(request, "building", null);
		String roomNum = ParamUtils.getString(request, "roomNum", null);
		Date startTime = ParamUtils.getDate(request, "startTime", null, "yyyy-MM-dd HH:mm");
		Date endTime = ParamUtils.getDate(request, "endTime", null, "yyyy-MM-dd HH:mm");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("wyName", wyName);
		paramMap.put("city", city);
		paramMap.put("residential", residential);
		paramMap.put("building", building);
		paramMap.put("roomNum", roomNum);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		PageUtils.addPageInfoToParam(request, paramMap);
		
		List<FinanceDeductionDetail> deductionList = financeService.getFinanceDeductionDetailList(paramMap);
		int resultSize = financeService.getFinanceDeductionDetailCount(paramMap);
		FinanceSum financeSum = financeService.getFinanceDeductionDetailSum(paramMap);
		request.setAttribute("deductionList", deductionList);
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("financeSum", financeSum);
		
		modelAndView.setViewName("/revenue/deductionList");
		return modelAndView;
	}


	public void setFinanceService(FinanceService financeService) {
		this.financeService = financeService;
	}

}
