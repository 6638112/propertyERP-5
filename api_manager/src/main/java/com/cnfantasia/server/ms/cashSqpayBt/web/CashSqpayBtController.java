package com.cnfantasia.server.ms.cashSqpayBt.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.common.utils.ExcelUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.ms.cashSqpayBt.entity.CashSqpayBtDto;
import com.cnfantasia.server.ms.cashSqpayBt.entity.CashSqpayBtSearchParam;
import com.cnfantasia.server.ms.cashSqpayBt.service.ICashSqpayBtOOSService;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.MapConverter;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;

/**
 * 双乾支付优惠补贴
 * 
 * @author liyulin
 * @version 1.0 2016年9月9日 下午8:45:30
 */
@Controller
@RequestMapping("/cashSqpayBt")
public class CashSqpayBtController {

	@Resource
	private ICashSqpayBtOOSService cashSqpayBtOOSService;
	@Resource
	private IRevenueService revenueService;
	
	/**
	 * 查询双乾支付优惠补贴
	 * 
	 * @param cashSqpayBtSearchParam
	 * @param request
	 * @return
	 */
	@RequestMapping("/cashSqpayBtIndex.html")
	public ModelAndView cashSqpayBtIndex(CashSqpayBtSearchParam cashSqpayBtSearchParam, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if(isVisited(request)){
			Map<String, Object> paramMap = MapConverter.toMap(cashSqpayBtSearchParam);

			int total = cashSqpayBtOOSService.selectCashSqpayBtCount(paramMap);
			Long totalAmountBt = cashSqpayBtOOSService.selectCashSqpayBtForTotalAmountBt(paramMap);
			if(totalAmountBt==null){totalAmountBt = 0L;}
			
			// 页数的参数名 
			String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
			// 每页显示的条数 
			int pageSize = 10;
			// 当前页    
			int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
			// 分页信息
			paramMap.put("_begin", pageSize * curPageIndex);
			paramMap.put("_length", pageSize);
			List<CashSqpayBtDto> cashSqpayBtDtos = cashSqpayBtOOSService.selectCashSqpayBtList(paramMap);
			
			modelAndView.addObject("total", total);
			modelAndView.addObject("totalAmountBt", totalAmountBt.longValue());// 单位为分
			modelAndView.addObject("cashSqpayBtDtos", cashSqpayBtDtos);
			modelAndView.setViewName("/cashSqpayBt/list");
		} else {
			modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		}

		return modelAndView;
	}

	/**
	 * 双乾支付优惠补贴导出
	 * 
	 * @param cashFlowSummaryParam
	 * @param response
	 */
	@RequestMapping("/exportCashSqpayBts.html")
	public void exportCashSqpayBts(CashSqpayBtSearchParam cashSqpayBtSearchParam, HttpServletRequest request, HttpServletResponse response) {
		if(isVisited(request)){
			Map<String, Object> paramMap = MapConverter.toMap(cashSqpayBtSearchParam);

			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String exportFileName = "双乾支付优惠补贴_" + format.format(new Date());
			HSSFWorkbook workbook = cashSqpayBtOOSService.exportCashSqpayBts(paramMap);

			ExcelUtil.commonExport(exportFileName, workbook, response);
		}
	}
	
	/**
	 * 是否有权限访问
	 * 
	 * @return
	 */
	private boolean isVisited(HttpServletRequest request){
		Set<UserRole> userRoleSet = revenueService.getOmsUserRoleListByUserId(UserContext.getOperIdBigIntegerMustExist());
		if(!userRoleSet.contains(UserRole.Financer) && !userRoleSet.contains(UserRole.SysAdmin)) {
			request.setAttribute(JSPConstants.OprtResult, "非财务、管理员，无权限进入！");
			request.setAttribute(JSPConstants.ToURL, "../security/welcome.html");
			return false;
		}
		
		return true;
	}
}
