package com.cnfantasia.server.ms.revenue.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
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

import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.revenueConfig.service.RevenueConfigBaseService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtils;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;
import com.cnfantasia.server.ms.revenue.entity.FinanceRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;
import com.cnfantasia.server.ms.revenue.entity.RevenueRoleAll;
import com.cnfantasia.server.ms.revenue.entity.RevenueRoleMultiId;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;
import com.cnfantasia.server.ms.revenue.service.RevenueFinanceService;

/**
 * 师傅收益
 * @author yewj
 *
 */
@Controller
@RequestMapping("/revenueFinance")
public class RevenueFinanceController extends BaseController{
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Resource
	private RevenueFinanceService revenueFinanceService;
	
	@Resource
	private IRevenueService revenueService;
	
	@Resource
	private RevenueConfigBaseService revenueConfigBaseService;
	
	/**
	 * 物业宝 明细列表信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/financeList.html")
	public ModelAndView financeList(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String wyOrAgentName = ParamUtils.getString(request, "wyOrAgentName", null);
		String gbName = ParamUtils.getString(request, "gbName", null);
		Date goalRevTimeStart = ParamUtils.getDate(request, "goalRevTimeStart", null, "yyyy-MM-dd HH:mm");
		Date goalRevTimeEnd = ParamUtils.getDate(request, "goalRevTimeEnd", null, "yyyy-MM-dd HH:mm");
		Integer tkStatus = ParamUtils.getInteger(request, "tkStatus", null);
		Long roleId = ParamUtils.getLong(request, "roleId", null);
		Long applyId = ParamUtils.getLong(request, "applyId", null);
		
		paramMap.put("wyOrAgentName", wyOrAgentName);
		paramMap.put("gbName", gbName);
		paramMap.put("goalRevTimeStart", goalRevTimeStart);
		paramMap.put("goalRevTimeEnd", goalRevTimeEnd);
		paramMap.put("tkStatus", tkStatus);
		paramMap.put("roleId", roleId);
		paramMap.put("applyId", applyId);
		paramMap.put("revenueProject", RevenueDict.RevenueProject.WuyebaoAmount);
		
		Set<UserRole> userRoleSet = revenueService.getOmsUserRoleListByUserId(UserContext.getOperIdBigIntegerMustExist());
		if(!userRoleSet.contains(UserRole.Financer) && !userRoleSet.contains(UserRole.SysAdmin)) {
			UserRole userRole = null;
			if(userRoleSet.contains(UserRole.PropertyCompany)) {
				userRole = UserRole.PropertyCompany;
				request.setAttribute("roleType", 1);
			} else if(userRoleSet.contains(UserRole.PropertyAgent)) {
				userRole = UserRole.PropertyAgent;
				request.setAttribute("roleType", 2);
			} else if(userRoleSet.contains(UserRole.PCManagement)) {
				userRole = UserRole.PCManagement;
				request.setAttribute("roleType", 1);
				paramMap.put("roleType", 13);
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
		
		//导出的时候使用的查询条件map
	    request.getSession().setAttribute("revenueFinanceParamMap_"+UserContext.getOperIdBigIntegerMustExist(), ((HashMap<String, Object>) paramMap).clone());
		
		PageUtils.addPageInfoToParam(request, paramMap);
		
		List<FinanceRevenueSignalAmount> financeRevenueSignalAmountList = revenueFinanceService.selectFinanceRevenueSaList(paramMap);
		int resultSize = revenueFinanceService.selectFinanceRevenueSaCount(paramMap);
		EbuyRevenueTotal ebuyRevenueTotal = revenueFinanceService.selectFinanceRevenueTotal(paramMap);
		
		request.setAttribute("financeRevenueSignalAmountList", financeRevenueSignalAmountList);
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("ebuyRevenueTotal", ebuyRevenueTotal);
		
		request.setAttribute("revenueProject", RevenueDict.RevenueProject.WuyebaoAmount);//收益项目：3物业宝，9停车宝
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/revenueDredge/financeList");
		return mav;
	}
	
	/**
	 * 停车宝 明细列表信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/carFinanceList.html")
	public ModelAndView carFinanceList(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String wyOrAgentName = ParamUtils.getString(request, "wyOrAgentName", null);
		String gbName = ParamUtils.getString(request, "gbName", null);
		Date goalRevTimeStart = ParamUtils.getDate(request, "goalRevTimeStart", null, "yyyy-MM-dd HH:mm");
		Date goalRevTimeEnd = ParamUtils.getDate(request, "goalRevTimeEnd", null, "yyyy-MM-dd HH:mm");
		Integer tkStatus = ParamUtils.getInteger(request, "tkStatus", null);
		Long roleId = ParamUtils.getLong(request, "roleId", null);
		Long applyId = ParamUtils.getLong(request, "applyId", null);
		
		paramMap.put("wyOrAgentName", wyOrAgentName);
		paramMap.put("gbName", gbName);
		paramMap.put("goalRevTimeStart", goalRevTimeStart);
		paramMap.put("goalRevTimeEnd", goalRevTimeEnd);
		paramMap.put("tkStatus", tkStatus);
		paramMap.put("roleId", roleId);
		paramMap.put("applyId", applyId);
		paramMap.put("revenueProject", RevenueDict.RevenueProject.CarFinanceBaoAmout);
		
		processStatus(request, paramMap);
		
		Set<UserRole> userRoleSet = revenueService.getOmsUserRoleListByUserId(UserContext.getOperIdBigIntegerMustExist());
		if(!userRoleSet.contains(UserRole.Financer) && !userRoleSet.contains(UserRole.SysAdmin)) {
			UserRole userRole = null;
			if(userRoleSet.contains(UserRole.PropertyCompany)) {
				userRole = UserRole.PropertyCompany;
				request.setAttribute("roleType", 1);
			}else if(userRoleSet.contains(UserRole.PCManagement)) {
				userRole = UserRole.PCManagement;
				request.setAttribute("roleType", 1);
				paramMap.put("roleType", 13);
			} else if(userRoleSet.contains(UserRole.PropertyAgent)) {
				userRole = UserRole.PropertyCompany;
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
		
		//导出的时候使用的查询条件map
		request.getSession().setAttribute("revenueFinanceParamMap_"+UserContext.getOperIdBigIntegerMustExist(), ((HashMap<String, Object>) paramMap).clone());
		
		PageUtils.addPageInfoToParam(request, paramMap);
		
		List<FinanceRevenueSignalAmount> financeRevenueSignalAmountList = revenueFinanceService.selectFinanceRevenueSaList(paramMap);
		int resultSize = revenueFinanceService.selectFinanceRevenueSaCount(paramMap);
		EbuyRevenueTotal ebuyRevenueTotal = revenueFinanceService.selectFinanceRevenueTotal(paramMap);
		
		request.setAttribute("financeRevenueSignalAmountList", financeRevenueSignalAmountList);
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("ebuyRevenueTotal", ebuyRevenueTotal);
		
		request.setAttribute("revenueProject", RevenueDict.RevenueProject.CarFinanceBaoAmout);//收益项目：3物业宝，9停车宝
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/revenueDredge/carFinanceList");
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
			HashMap<String, Object> paramMap = (HashMap<String, Object>) request.getSession().getAttribute("revenueFinanceParamMap_"+UserContext.getOperIdBigIntegerMustExist());
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
					paramMap.put("roleType", 13);
				} else if(userRoleSet.contains(UserRole.PropertyAgent)) {
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
			List<FinanceRevenueSignalAmount> financeRevenueSignalAmountList = revenueFinanceService.selectFinanceRevenueSaList(paramMap);
			
			// 生成提示信息
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String fileName = format.format(new Date());
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
			fOut = response.getOutputStream();
			HSSFWorkbook workbook;
			
			if(roleType == 1 || roleType==2) {//物业和代理
				workbook = createReport(financeRevenueSignalAmountList, roleType, paramMap);
			} else {//admin和财务
				workbook = revenueFinanceService.createReport(financeRevenueSignalAmountList);
			}
			
			workbook.write(fOut);
		} catch (Exception e) {
			e.printStackTrace();
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
	
	/**
	 * 物业或代理的导出，按列表原样导出即可
	 * @param financeRevenueSignalAmountList
	 * @param roleType
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public HSSFWorkbook createReport(List<FinanceRevenueSignalAmount> financeRevenueSignalAmountList, Integer roleType, HashMap<String, Object> paramMap) {
		int revenueProject = (Integer) paramMap.get("revenueProject"); 
		String[] titles = {"物业公司","代理商","城市","小区","楼栋","单元门牌号","订单号","投资金额", "回款时间", "收益金额（物业）", "结算状态", "收益金额（代理）", "结算状态"};
		if(roleType == 1) {
			if(revenueProject == RevenueDict.RevenueProject.WuyebaoAmount){
				titles = new String[]{"物业公司","代理商","城市","小区","楼栋","单元门牌号","订单号","投资金额", "回款时间", "收益金额（物业）", "结算状态"};
			}else if(revenueProject == RevenueDict.RevenueProject.CarFinanceBaoAmout){
				titles = new String[]{"物业公司","代理商","城市","小区","车牌号","订单号","投资金额", "回款时间", "收益金额（物业）", "结算状态"};
			}
		} else if(roleType == 2) {
			if(revenueProject == RevenueDict.RevenueProject.WuyebaoAmount){
				titles = new String[]{"物业公司","代理商","城市","小区","楼栋","单元门牌号","订单号","投资金额", "回款时间", "收益金额（代理）", "结算状态"};
			}else if(revenueProject == RevenueDict.RevenueProject.CarFinanceBaoAmout){
				titles = new String[]{"物业公司","代理商","城市","小区","车牌号","订单号","投资金额", "回款时间", "收益金额（代理）", "结算状态"};
			}
		}
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("物业宝收益明细");
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
		if(financeRevenueSignalAmountList != null) {
			NumberFormat nf = new DecimalFormat("#0.00##");
			int index = 1;//标记到那一行
			BigDecimal totalBuyMoney = BigDecimal.ZERO;//投资金额总计
			double totalAmount = 0.00;//收益总和
			for (int i = 0, j = 0; j < financeRevenueSignalAmountList.size(); j++) {
				row = sheet.createRow((j+1));
				// 设置excel每列宽度
				sheet.setColumnWidth(j, width);
				FinanceRevenueSignalAmount bean = financeRevenueSignalAmountList.get(j);
				i = 0;
//				{"物业公司","代理商","城市","小区","楼栋","单元门牌号","订单号","投资金额", "回款时间", "收益金额", "结算状态"}
				fillCellData(row, i++, bean.getFinanceBuyEntity().getPropertyCompanyName(),style);
				fillCellData(row, i++, bean.getFinanceBuyEntity().getChannelPartnerName(),style);
				fillCellData(row, i++, bean.getFinanceBuyEntity().getFinanceReqEntity().getCity(),style);
				fillCellData(row, i++, bean.getFinanceBuyEntity().getFinanceReqEntity().getResidential(),style);
				if(revenueProject == RevenueDict.RevenueProject.WuyebaoAmount.intValue()){
					fillCellData(row, i++, bean.getFinanceBuyEntity().getFinanceReqEntity().getBuilding(),style);
					fillCellData(row, i++, bean.getFinanceBuyEntity().getFinanceReqEntity().getRoomNum(),style);
				}else if(revenueProject == RevenueDict.RevenueProject.CarFinanceBaoAmout.intValue()){
					fillCellData(row, i++, bean.getFinanceBuyEntity().getCarNum(),style);
				}
				fillCellData(row, i++, bean.getFinanceBuyEntity().getOrderNo(),style);
				fillCellData(row, i++, nf.format(bean.getFinanceBuyEntity().getBuyMoney()),style);
				fillCellData(row, i++, DateUtils.formatMinuteTime(bean.getGoalRevTimeDate()),style);
				
				if(roleType == 1) {
					fillCellData(row, i++, nf.format(bean.getAmountWy()),style);
					fillCellData(row, i++, bean.getTkStatusWyStr(),style);
					totalAmount += bean.getAmountWy();
				} else if(roleType == 2) {
					fillCellData(row, i++, nf.format(bean.getAmountAgent()),style);
					fillCellData(row, i++, bean.getTkStatusAgentStr(),style);
					totalAmount += bean.getAmountAgent();
				}
				totalBuyMoney = totalBuyMoney.add(bean.getFinanceBuyEntity().getBuyMoney());
				index = j+1;
			}
			
			//增加数据合计信息
			row = sheet.createRow((index+1));
			
			int totalColIndex = 6; //合计起始列
			
			if(revenueProject == RevenueDict.RevenueProject.WuyebaoAmount.intValue()){
				totalColIndex = 6;
			}else if(revenueProject == RevenueDict.RevenueProject.CarFinanceBaoAmout.intValue()){
				totalColIndex = 5;
			}
			
			fillCellData(row, totalColIndex, "总计",style);
			fillCellData(row, totalColIndex+1, nf.format(totalBuyMoney==null?0.00:totalBuyMoney),style);
			fillCellData(row, totalColIndex+3, nf.format(totalAmount),style);
		}

		// 设置单元格的样式格式
		return wb;
	}
	
	@SuppressWarnings("deprecation")
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
			//paramMap.put("tkStatusRepair", tkStatus);
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
			//paramMap.put("tkStatusRecommender", tkStatus);
			break;
		default:
			paramMap.put("tkStatus", tkStatus);
			break;
		}
	}
}
