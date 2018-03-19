package com.cnfantasia.server.ms.revenue.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PayUtils;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtils;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;
import com.cnfantasia.server.ms.revenue.entity.RevenueRoleAll;
import com.cnfantasia.server.ms.revenue.entity.RevenueRoleMultiId;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;
import com.cnfantasia.server.ms.revenue.service.RevenueDredgeService;

/**
 * 师傅收益
 * @author yewj
 *
 */
@Controller
@RequestMapping("/revenueDredge")
public class RevenueDredgeController extends BaseController{
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Resource
	private RevenueDredgeService revenueDredgeService;
	
	@Resource
	private IRevenueService revenueService;
	
	/**
	 * 明细列表信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/dredgeList.html")
	public ModelAndView list(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String wyOrAgentName = ParamUtils.getString(request, "wyOrAgentName", null);
		String gbName = ParamUtils.getString(request, "gbName", null);
		Date payTmStart = ParamUtils.getDate(request, "payTmStart", null, "yyyy-MM-dd HH:mm");
		Date payTmEnd = ParamUtils.getDate(request, "payTmEnd", null, "yyyy-MM-dd HH:mm");
		Long roleId = ParamUtils.getLong(request, "roleId", null);
		Long applyId = ParamUtils.getLong(request, "applyId", null);
		String recommenderName = ParamUtils.getString(request, "recommenderName", null);
//		Integer profitObject = ParamUtils.getInteger(request, "profitObject", 0);
//		Integer roleType = ParamUtils.getInteger(request, "roleType", null); //0管理员或者财务;1物业公司;2代理商;3师傅;4推荐人
		paramMap.put("wyOrAgentName", wyOrAgentName);
		paramMap.put("gbName", gbName);
		paramMap.put("payTmStart", payTmStart);
		paramMap.put("payTmEnd", payTmEnd);
		paramMap.put("roleId", roleId);
		paramMap.put("applyId", applyId);
		paramMap.put("recommenderName", recommenderName);
		
		processStatus(request, paramMap);
		
		Set<UserRole> userRoleSet = revenueService.getOmsUserRoleListByUserId(UserContext.getOperIdBigIntegerMustExist());
		if(!userRoleSet.contains(UserRole.Financer) && !userRoleSet.contains(UserRole.SysAdmin)) {
			UserRole userRole = null;
			if(userRoleSet.contains(UserRole.PropertyCompany)) {
				userRole = UserRole.PropertyCompany;
				request.setAttribute("roleType", 1);
			} else if(userRoleSet.contains(UserRole.PropertyAgent)) {
				userRole = UserRole.PropertyAgent;
				request.setAttribute("roleType", 2);
			}else if(userRoleSet.contains(UserRole.PCManagement)) {
				userRole = UserRole.PCManagement;
				request.setAttribute("roleType", 1);
				paramMap.put("roleType", UserRole.PCManagement.getCode());
			}
			
			RevenueRole role = revenueService.createRevenueRoleExcep(UserContext.getOperIdBigIntegerMustExist(), userRole);
			if(role instanceof RevenueRoleMultiId) {
				RevenueRoleMultiId multRole = (RevenueRoleMultiId) role;
				List<BigInteger> roleIdList = multRole.getRoleIdList();
				paramMap.put("roleIdList", roleIdList);
			} else if(role instanceof RevenueRoleAll) {
				//不需要处理，即搜索全部
			} else {
				if(roleId == null) {
					paramMap.put("roleId", role.getRoleId());
				}
			}
		} else {
			request.setAttribute("roleType", 0);
			paramMap.put("roleType", UserRole.Financer.getCode());
		}
		
		//导出的时候使用的查询条件map
		request.getSession().setAttribute("revenueDredgeParamMap_"+UserContext.getOperIdBigIntegerMustExist(), ((HashMap<String, Object>) paramMap).clone());
		
		PageUtils.addPageInfoToParam(request, paramMap);
		
		List<EbuyRevenueSignalAmount> ebuyRevenueSignalAmountList = revenueDredgeService.selectDredgeRevenueSaList(paramMap);
		int resultSize = revenueDredgeService.selectDredgeRevenueSaCount(paramMap);
		EbuyRevenueTotal ebuyRevenueTotal = revenueDredgeService.selectDredgeRevenueTotal(paramMap);
		
		request.setAttribute("ebuyRevenueSignalAmountList", ebuyRevenueSignalAmountList);
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("ebuyRevenueTotal", ebuyRevenueTotal);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/revenueDredge/dredgeList");
		return mav;
	}
	
	/**
	 * 明细列表信息 -- 管理处
	 * @param request
	 * @return
	 */
	@RequestMapping("/dredgeManagementList.html")
	public ModelAndView dredgeManagementList(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String wyOrAgentName = ParamUtils.getString(request, "wyOrAgentName", null);
		String gbName = ParamUtils.getString(request, "gbName", null);
		Date payTmStart = ParamUtils.getDate(request, "payTmStart", null, "yyyy-MM-dd HH:mm");
		Date payTmEnd = ParamUtils.getDate(request, "payTmEnd", null, "yyyy-MM-dd HH:mm");
//		Integer tkStatus = ParamUtils.getInteger(request, "tkStatus", null);
		Long roleId = ParamUtils.getLong(request, "roleId", null);
		Long applyId = ParamUtils.getLong(request, "applyId", null);
		String recommenderName = ParamUtils.getString(request, "recommenderName", null);
//		Integer profitObject = ParamUtils.getInteger(request, "profitObject", 0);
//		Integer roleType = ParamUtils.getInteger(request, "roleType", null); //0管理员或者财务;1物业公司;2代理商;3师傅;4推荐人
		paramMap.put("wyOrAgentName", wyOrAgentName);
		paramMap.put("gbName", gbName);
		paramMap.put("payTmStart", payTmStart);
		paramMap.put("payTmEnd", payTmEnd);
		paramMap.put("omsUserId", UserContext.getOperIdBigIntegerMustExist());
		paramMap.put("roleId", roleId);
		paramMap.put("applyId", applyId);
		paramMap.put("recommenderName", recommenderName);
		
		processStatus(request, paramMap);
		
		//导出的时候使用的查询条件map
		request.getSession().setAttribute("revenueDredgeParamMap_"+UserContext.getOperIdBigIntegerMustExist(), ((HashMap<String, Object>) paramMap).clone());
		
		PageUtils.addPageInfoToParam(request, paramMap);
		
		List<EbuyRevenueSignalAmount> ebuyRevenueSignalAmountList = revenueDredgeService.selectDredgeRevenueSaManagementList(paramMap);
		int resultSize = revenueDredgeService.selectDredgeRevenueSaManagementCount(paramMap);
		EbuyRevenueTotal ebuyRevenueTotal = revenueDredgeService.selectDredgeRevenueManagementTotal(paramMap);
		
		request.setAttribute("ebuyRevenueSignalAmountList", ebuyRevenueSignalAmountList);
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("ebuyRevenueTotal", ebuyRevenueTotal);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/revenueDredge/dredgeList");
		return mav;
	}
	
	/**
	 * 导出功能
	 * @param request
	 * @return
	 */
	@RequestMapping("/exportExcel.html")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response){
		OutputStream fOut = null;
		InputStream fin = null;
		try {
			Long roleId = ParamUtils.getLong(request, "roleId", null);
			HashMap<String, Object> paramMap = (HashMap<String, Object>) request.getSession().getAttribute("revenueDredgeParamMap_"+UserContext.getOperIdBigIntegerMustExist());
			Integer roleType = 0;
			
			Set<UserRole> userRoleSet = revenueService.getOmsUserRoleListByUserId(UserContext.getOperIdBigIntegerMustExist());
			if(!userRoleSet.contains(UserRole.Financer) && !userRoleSet.contains(UserRole.SysAdmin)) {
				UserRole userRole = null;
				if(userRoleSet.contains(UserRole.PropertyCompany)) {
					userRole = UserRole.PropertyCompany;
					roleType = 1;
					request.setAttribute("roleType", 1);
				} else if(userRoleSet.contains(UserRole.PCManagement)) {
					userRole = UserRole.PCManagement;
					roleType = 1;
					request.setAttribute("roleType", 1);
				}else if(userRoleSet.contains(UserRole.PropertyAgent)) {
					userRole = UserRole.PropertyAgent;
					roleType = 2;
					request.setAttribute("roleType", 2);
				}
				RevenueRole role = revenueService.createRevenueRoleExcep(UserContext.getOperIdBigIntegerMustExist(), userRole);
				if(role instanceof RevenueRoleMultiId) {
					RevenueRoleMultiId multRole = (RevenueRoleMultiId) role;
					List<BigInteger> roleIdList = multRole.getRoleIdList();
					paramMap.put("roleIdList", roleIdList);
				} else if(role instanceof RevenueRoleAll) {
					//不需要处理，即搜索全部
				} else {
					if(roleId == null) {
						paramMap.put("roleId", role.getRoleId());
					}
				}
			} else {
				request.setAttribute("roleType", 0);
			}
			
			List<EbuyRevenueSignalAmount> ebuyRevenueSignalAmountList = revenueDredgeService.selectDredgeRevenueSaList(paramMap);
			
			// 生成提示信息
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String fileName = "上门维修收益"+format.format(new Date());
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("content-disposition", "attachment;filename=" +new String(fileName.getBytes(), "iso8859-1") + ".xls");
			fOut = response.getOutputStream();
			HSSFWorkbook workbook;
			
			if(roleType == 0) {//管理员/财务
				workbook = revenueDredgeService.createReport(ebuyRevenueSignalAmountList);
			} else {
				workbook = createReport(ebuyRevenueSignalAmountList, roleType);
			}
			
			workbook.write(fOut);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			try {
				if (fOut != null) {
					fOut.flush();
					fOut.close();
				}
				if (fin != null) {
					fin.close();
				}
			} catch (IOException ie) {
				throw new RuntimeException(ie);
			}
		}
	}
	
	public HSSFWorkbook createReport(List<EbuyRevenueSignalAmount> ebuyRevenueSignalAmountList, Integer roleType) {
		String[] titles = {"物业公司","代理商","小区名称","预约解放号","预约服务", "推荐人手机号","订单金额", "优惠金额","支付金额","支付渠道","流水号", "订单完成时间", "师傅收入","平台收入","与平台收入比（物业）", "物业收入", "与平台收入比（代理）","代理收入","与平台收入比（推荐人）", "推荐人收入"};
		if(roleType == 1) {
			titles = new String[]{"物业公司","代理商","小区名称","预约解放号","预约服务","订单金额","优惠金额","支付金额","支付渠道","流水号", "订单完成时间", "收益金额（物业）", "结算状态"};
		} else if(roleType == 2) {
			titles = new String[]{"物业公司","代理商","小区名称","预约解放号","预约服务","订单金额","优惠金额","支付金额","支付渠道","流水号", "订单完成时间", "收益金额（代理）", "结算状态"};
		}
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("上门维修收益明细");
		// 创建字体样式
		HSSFFont font = wb.createFont();
		font.setFontName("微软雅黑");
		// 创建单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 设置字体
		style.setFont(font);
		short height = 300;
		short width = 4000;
		// 创建Excel的sheet的一行
		HSSFRow row = sheet.createRow(0);
		// 设定行的高度
		row.setHeight(height);
		for (int i = 0; i < titles.length; i++) {
			// 设置excel每列宽度
			sheet.setColumnWidth(i, width);
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(titles[i]);
		}
		if(ebuyRevenueSignalAmountList != null) {
			NumberFormat nf = new DecimalFormat("#0.00##");
			NumberFormat nf2 = new DecimalFormat("#0.00%");
			for (int i = 0, j = 0; j < ebuyRevenueSignalAmountList.size(); j++) {
				row = sheet.createRow((j+1));
				// 设置excel每列宽度
				sheet.setColumnWidth(j, width);
				EbuyRevenueSignalAmount bean = ebuyRevenueSignalAmountList.get(j);
				i = 0;
//				{"物业公司","代理商","小区名称","预约解放号","预约服务","粮票金额","支付金额","支付渠道","流水号", "订单完成时间", " 收益金额", "结算状态"}
				fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getPcName(),style);
				fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getAgentName(),style);
				fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getGroupBuildingName(),style);
				fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getHuaId(),style);
				fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getDredgeServiceName(),style);
				if(roleType != 1 && roleType != 2) {
					fillCellData(row, i++, bean.getRecommenderName(),style);
				}
				fillCellData(row, i++, nf.format(bean.getSrcMoney()),style);
				fillCellData(row, i++, nf.format(bean.getRevenueSignalAmountEbuy().getAmountDiscount()),style);
				fillCellData(row, i++, nf.format(bean.getRevenueSignalAmountEbuy().getAmountOrderReal()),style);
				fillCellData(row, i++, PayUtils.getPayMethodStr(bean.getRevenueSignalAmountEbuy().getPayMethod()),style);
				fillCellData(row, i++, bean.getPayFlowNo(),style);
				fillCellData(row, i++, DateUtils.formatMinuteTime(bean.getPayTm()),style);
//				fillCellData(row, i++, nf.format(bean.getAmount()),style);
//				fillCellData(row, i++, bean.getTkStatusStr(),style);
				
				if(roleType == 1) {
					fillCellData(row, i++, bean.getAmountWy() == null ? "" : nf.format(bean.getAmountWy()),style);
					fillCellData(row, i++, bean.getTkStatusWyStr(),style);
				} else if(roleType == 2) {
					fillCellData(row, i++, bean.getAmountAgent() == null ? "" : nf.format(bean.getAmountAgent()),style);
					fillCellData(row, i++, bean.getTkStatusAgentStr(),style);
				} else {
//					"师傅收入","平台收入","与平台收入比（物业）", "物业收入", "与平台收入比（代理）","代理收入","与平台收入比（推荐人）", "推荐人收入"
					Double platfromProfit = bean.getSrcMoney() - bean.getAmountRepair();
					fillCellData(row, i++, bean.getAmountRepair() == null ? "" : nf.format(bean.getAmountRepair()),style);
					fillCellData(row, i++, bean.getAmountPlatform() == null ? "" : nf.format(bean.getAmountPlatform()),style);
					fillCellData(row, i++, bean.getAmountWy() == null || platfromProfit == 0.0 ? "0%" : nf2.format(BigDecimalUtil.div(bean.getAmountWy(), platfromProfit, 4)),style);//"与平台收入比（物业）
					fillCellData(row, i++, bean.getAmountWy() == null ? "" : nf.format(bean.getAmountWy()),style); //"物业收入"
					fillCellData(row, i++, bean.getAmountAgent() == null || platfromProfit == 0.0 ? "0%" : nf2.format(BigDecimalUtil.div(bean.getAmountAgent(), platfromProfit, 4)),style); //"与平台收入比（代理）
					fillCellData(row, i++, bean.getAmountAgent() == null ? "" : nf.format(bean.getAmountAgent()),style); //"代理收入"
					fillCellData(row, i++, bean.getAmountRecommender() == null || platfromProfit == 0.0 ? "0%" : nf2.format(BigDecimalUtil.div(bean.getAmountRecommender(), platfromProfit, 4)),style); //"与平台收入比（推荐人）"
					fillCellData(row, i++, bean.getAmountRecommender() == null ? "" : nf.format(bean.getAmountRecommender()),style); //"推荐人收入"
				}
			}
		}

		// 设置单元格的样式格式
		return wb;
	}
	
	private void fillCellData(HSSFRow row,int index,String value,HSSFCellStyle style){
		HSSFCell cell = row.createCell(index);
		cell.setCellStyle(style);
		cell.setCellValue(value);
	}
	
	private void processStatus(HttpServletRequest request, Map<String, Object> paramMap) {
		Integer profitObject = ParamUtils.getInteger(request, "profitObject", -1);
		Integer tkStatus = ParamUtils.getInteger(request, "tkStatus", null);
		
		switch (profitObject.intValue()) {
		case 0:
//			paramMap.put("tkStatus", tkStatus);
			break;
		case 1:
			paramMap.put("tkStatusRepair", tkStatus);
			break;
		case 2:
			paramMap.put("tkStatusWy", tkStatus);
			break;
		case 3:
			paramMap.put("tkStatusAgent", tkStatus);
			break;
		case 4:
			paramMap.put("tkStatusPlatform", tkStatus);
			break;
		case 5:
			paramMap.put("tkStatusRecommender", tkStatus);
			break;
		default:
			paramMap.put("tkStatus", tkStatus);
			break;
		}
	}
	
//	/**
//	 * 配置概要预览列表信息
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/ebuyRevenue.html")
//	public ModelAndView ebuyRevenue(HttpServletRequest request){
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		String supplyName = ParamUtils.getString(request, "supplyName", null);
//		String groupBuildingName = ParamUtils.getString(request, "groupBuildingName", null);
//		Date payTmStart = ParamUtils.getDate(request, "payTmStart", null, "yyyy-MM-dd");
//		Date payTmEnd = ParamUtils.getDate(request, "payTmEnd", null, "yyyy-MM-dd");
//		Integer tkStatus = ParamUtils.getInteger(request, "tkStatus", null);
//		paramMap.put("supplyName", supplyName);
//		paramMap.put("groupBuildingName", groupBuildingName);
//		paramMap.put("payTmStart", payTmStart);
//		paramMap.put("payTmEnd", payTmEnd);
//		paramMap.put("tkStatus", tkStatus);
//		
//		List<EbuyRevenueSignalAmount> ebuyRevenueSignalAmountList = revenueService.selectEbuyRevenueSignalAmountList(paramMap);
//		int resultSize = revenueService.selectEbuyRevenueSignalAmountCount(paramMap);
//		EbuyRevenueTotal ebuyRevenueTotal = revenueService.selectEbuyRevenueTotal(paramMap);
//		
//		request.setAttribute("ebuyRevenueSignalAmountList", ebuyRevenueSignalAmountList);
//		request.setAttribute("resultSize", resultSize);
//		request.setAttribute("ebuyRevenueTotal", ebuyRevenueTotal);
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/revenueEbuy/ebuyList");
//		return mav;
//	}
	
	
}
