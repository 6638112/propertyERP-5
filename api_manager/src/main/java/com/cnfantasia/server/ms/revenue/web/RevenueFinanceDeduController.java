package com.cnfantasia.server.ms.revenue.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.cnfantasia.server.domainbase.channelPartner.service.ChannelPartnerBaseService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtils;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;
import com.cnfantasia.server.ms.revenue.entity.FinanceDeduRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;
import com.cnfantasia.server.ms.revenue.entity.RevenueRoleAll;
import com.cnfantasia.server.ms.revenue.entity.RevenueRoleMultiId;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;
import com.cnfantasia.server.ms.revenue.service.RevenueFinanceDeduService;

/**
 * 物业宝抵扣收益
 * @author yewj
 *
 */
@Controller
@RequestMapping("/revenueFinanceDedu")
public class RevenueFinanceDeduController extends BaseController{
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Resource
	private RevenueFinanceDeduService revenueFinanceDeduService;
	
	@Resource
	private IRevenueService revenueService;
	
	@Resource
	private ChannelPartnerBaseService channelPartnerService;
	
	/**
	 * 明细列表信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/financeDeduList.html")
	public ModelAndView list(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String wyName = ParamUtils.getString(request, "wyName", null);
		String gbName = ParamUtils.getString(request, "gbName", null);
		Date monthStart = ParamUtils.getDate(request, "monthStart", null, "yyyy-MM");
		Date monthEnd = ParamUtils.getDate(request, "monthEnd", null, "yyyy-MM");
		Integer tkStatus = ParamUtils.getInteger(request, "tkStatus", null);
		Long roleId = ParamUtils.getLong(request, "roleId", null);
		Long applyId = ParamUtils.getLong(request, "applyId", null);
		Calendar cal = Calendar.getInstance();
		if(monthStart != null) {
			cal.setTime(monthStart);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			monthStart = cal.getTime();
		}
		if(monthEnd != null) {
			cal.setTime(monthEnd);
			cal.add(Calendar.MONTH, 1);
			monthEnd = cal.getTime();
		}
		  
		paramMap.put("wyName", wyName);
		paramMap.put("gbName", gbName);
		paramMap.put("monthStart", monthStart);
		paramMap.put("monthEnd", monthEnd);
		paramMap.put("tkStatus", tkStatus);
		paramMap.put("roleId", roleId);
		paramMap.put("applyId", applyId);
		
		Set<UserRole> userRoleSet = revenueService.getOmsUserRoleListByUserId(UserContext.getOperIdBigIntegerMustExist());
		if(!userRoleSet.contains(UserRole.Financer) && !userRoleSet.contains(UserRole.SysAdmin)) {
			UserRole userRole = null;
			if(userRoleSet.contains(UserRole.PropertyCompany)) {
				userRole = UserRole.PropertyCompany;
				request.setAttribute("roleType", 1);
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
		}
		
		//导出的时候使用的查询条件map
		request.getSession().setAttribute("revenueDeduParamMap_"+UserContext.getOperIdBigIntegerMustExist(), ((HashMap<String, Object>) paramMap).clone());
		
		PageUtils.addPageInfoToParam(request, paramMap);
		
		List<FinanceDeduRevenueSignalAmount> financeDeduRevenueSignalAmountList = revenueFinanceDeduService.selectRevenueSaList(paramMap);
		int resultSize = revenueFinanceDeduService.selectRevenueSaCount(paramMap);
		EbuyRevenueTotal ebuyRevenueTotal = revenueFinanceDeduService.selectRevenueTotal(paramMap);
		
		request.setAttribute("financeDeduRevenueSignalAmountList", financeDeduRevenueSignalAmountList);
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("ebuyRevenueTotal", ebuyRevenueTotal);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/revenueDredge/financeDeduList");
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
			HashMap<String, Object> paramMap = (HashMap<String, Object>) request.getSession().getAttribute("revenueDeduParamMap_"+UserContext.getOperIdBigIntegerMustExist());
			Integer roleType = 0;
			Set<UserRole> userRoleSet = revenueService.getOmsUserRoleListByUserId(UserContext.getOperIdBigIntegerMustExist());
			if(!userRoleSet.contains(UserRole.Financer) && !userRoleSet.contains(UserRole.SysAdmin)) {
				UserRole userRole = null;
				if(userRoleSet.contains(UserRole.PropertyCompany)) {
					userRole = UserRole.PropertyCompany;
					roleType = 1;
					request.setAttribute("roleType", 1);
				}else if(userRoleSet.contains(UserRole.PCManagement)){
					userRole = UserRole.PCManagement;
					roleType = 1;
					request.setAttribute("roleType", 1);
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
			
			List<FinanceDeduRevenueSignalAmount> financeDeduRevenueSignalAmountList = revenueFinanceDeduService.selectRevenueSaList(paramMap);
			
			// 生成提示信息
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String fileName = format.format(new Date());
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
			fOut = response.getOutputStream();
			HSSFWorkbook workbook;
			if(roleType ==0) {//管理员，财务
				workbook = revenueFinanceDeduService.createReport(financeDeduRevenueSignalAmountList);
			} else {
				workbook = createReport(financeDeduRevenueSignalAmountList, roleType);
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
	
	public HSSFWorkbook createReport(List<FinanceDeduRevenueSignalAmount> financeDeduRevenueSignalAmountList, Integer roleType) {
		String[] titles = {"物业公司","小区","楼栋","单元","房间号","订单号","抵扣金额", "抵扣月份", "结算状态"};
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("物业宝抵扣明细");
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
		if(financeDeduRevenueSignalAmountList != null) {
			NumberFormat nf = new DecimalFormat("#0.00##");
			for (int i = 0, j = 0; j < financeDeduRevenueSignalAmountList.size(); j++) {
				row = sheet.createRow((j+1));
				// 设置excel每列宽度
				sheet.setColumnWidth(j, width);
				FinanceDeduRevenueSignalAmount bean = financeDeduRevenueSignalAmountList.get(j);
				i = 0;
//				{"物业公司","小区","楼栋","单元","房间号","订单号","抵扣金额", "抵扣月份", "结算状态"};
				fillCellData(row, i++, bean.getRoleName(), style);
				fillCellData(row, i++, bean.getGbName(), style);
				fillCellData(row, i++, bean.getBuilding(), style);
				fillCellData(row, i++, bean.getUnitName(), style);
				fillCellData(row, i++, bean.getRoom(), style);
				fillCellData(row, i++, bean.getOrderNo(), style);
				fillCellData(row, i++, nf.format(bean.getAmount()), style);
				fillCellData(row, i++, DateUtils.formatYearAndMonth(bean.getMonth()), style);
				fillCellData(row, i++, bean.getTkStatusStr(), style);
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
	
	
	
}
