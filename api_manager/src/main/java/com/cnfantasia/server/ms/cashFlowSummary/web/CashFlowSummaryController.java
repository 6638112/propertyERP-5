package com.cnfantasia.server.ms.cashFlowSummary.web;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cnfantasia.server.domainbase.channelPartner.entity.ChannelPartner;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.ms.cashFlowSummary.entity.CashFlowSummaryDto;
import com.cnfantasia.server.ms.cashFlowSummary.entity.CashFlowSummaryParam;
import com.cnfantasia.server.ms.cashFlowSummary.service.ICashFlowSummaryService;
import com.cnfantasia.server.ms.channelPartner.service.IChannelPartnerService;
import com.cnfantasia.server.ms.propertyCompany.service.IPropertyCompanyService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.MapConverter;

/**
 * 现金流量汇总
 * 
 * @author liyulin
 * @version 1.0 2016年7月15日 下午2:24:49
 */
@Controller
@RequestMapping("/cashFlowSummary")
public class CashFlowSummaryController extends BaseController{

	@Resource
	private ICashFlowSummaryService msCashFlowSummaryService;
	@Resource
	private IPropertyCompanyService propertyCompanyService;
	@Resource
	private IChannelPartnerService channelPartnerService;

	/**
	 * 现金流量汇总表查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/cashFlowSummaryIndex.html")
	public ModelAndView cashFlowSummaryIndex(CashFlowSummaryParam cashFlowSummaryParam, HttpServletRequest request) {
		Map<String, Object> paramMap = MapConverter.toMap(cashFlowSummaryParam);
		filterByUserType(paramMap);

		int total = msCashFlowSummaryService.selectCashFlowSummaryForCount(paramMap);
		BigDecimal totalAmount = msCashFlowSummaryService.selectCashFlowSummaryForTotalAmount(paramMap);
		if(totalAmount==null){
			totalAmount = BigDecimal.ZERO; 
		}
		
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		// 分页信息
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		List<CashFlowSummaryDto> cashFlowSummarys = msCashFlowSummaryService.selectCashFlowSummaryForList(paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("total", total);
		modelAndView.addObject("totalAmount", totalAmount.divide(new BigDecimal("100")));// 除以100后，单位为元
		modelAndView.addObject("cashFlowSummarys", cashFlowSummarys);
		modelAndView.setViewName("/cashFlowSummary/list");

		return modelAndView;
	}

	/**
	 * 现金流量汇总表导出
	 * 
	 * @param cashFlowSummaryParam
	 * @param response
	 */
	@RequestMapping("/exportCashFlowSummary.html")
	public void exportCashFlowSummary(CashFlowSummaryParam cashFlowSummaryParam, HttpServletResponse response) {
		Map<String, Object> paramMap = MapConverter.toMap(cashFlowSummaryParam);
		filterByUserType(paramMap);

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String exportFileName = "现金流_" + format.format(new Date());
		HSSFWorkbook workbook = msCashFlowSummaryService.exportCashFlowSummary(paramMap);

		ExcelUtil.commonExport(exportFileName, workbook, response);
	}

	/**
	 * 根据用户类型，过滤数据
	 * 
	 * @param map
	 */
	private void filterByUserType(Map<String, Object> map) {
		BigInteger userId = UserContext.getOperIdBigIntegerMustExist();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("adminId", userId);
		List<PropertyCompany> propertyCompanys = propertyCompanyService.getPropertyCompanyByCondition(paramMap);
		if (propertyCompanys != null && propertyCompanys.size() > 0) {// 物业
			List<BigInteger> pcIds = new ArrayList<BigInteger>();
			for (PropertyCompany pc : propertyCompanys) {
				pcIds.add(pc.getId());
			}
			map.put("pcIds", pcIds);
		} else {
			paramMap.clear();
			paramMap.put("tOmsUserFId", userId);
			List<ChannelPartner> channelPartners = channelPartnerService.getChannelPartnerByCondition(paramMap);
			if (channelPartners != null && channelPartners.size() > 0) {// 代理
				List<BigInteger> cpIds = new ArrayList<BigInteger>();
				for (ChannelPartner cp : channelPartners) {
					cpIds.add(cp.getId());
				}
				map.put("cpIds", cpIds);
			} else {// 管理员、财务：不过滤，查询所有

			}
		}
	}
}
