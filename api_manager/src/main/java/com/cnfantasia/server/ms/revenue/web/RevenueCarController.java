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

import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtils;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.entity.CarRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;
import com.cnfantasia.server.ms.revenue.entity.RevenueRoleAll;
import com.cnfantasia.server.ms.revenue.entity.RevenueRoleMultiId;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;
import com.cnfantasia.server.ms.revenue.service.RevenueCarService;

/**
 * 车禁收益
 * @author yewj
 *
 */
@Controller
@RequestMapping("/revenueCar")
public class RevenueCarController extends BaseController{
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Resource
	private RevenueCarService revenueCarService;
	
	@Resource
	private IRevenueService revenueService;
	
	/**
	 * 初始化参数
	 * 
	 * @param request
	 * @return
	 */
	private Map<String, Object> initParam(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String wyName = ParamUtils.getString(request, "wyName", null);
		Date goalRevTimeStart = ParamUtils.getDate(request, "goalRevTimeStart", null, "yyyy-MM-dd HH:mm");
		Date goalRevTimeEnd = ParamUtils.getDate(request, "goalRevTimeEnd", null, "yyyy-MM-dd HH:mm");
		Integer tkStatus = ParamUtils.getInteger(request, "tkStatus", null);
		Integer carType = ParamUtils.getInteger(request, "carType", null);
		Long roleId = ParamUtils.getLong(request, "roleId", null);
		Long applyId = ParamUtils.getLong(request, "applyId", null);
		Integer goalType = ParamUtils.getInteger(request, "goalType", null);
		String carNum = ParamUtils.getString(request, "carNum", null);
		if(goalType==null){
			goalType = 6;// 默认为：停车费实收
		}
		paramMap.put("wyName", wyName);
		paramMap.put("goalRevTimeStart", goalRevTimeStart);
		paramMap.put("goalRevTimeEnd", goalRevTimeEnd);
		paramMap.put("tkStatus", tkStatus);
		paramMap.put("roleId", roleId);
		paramMap.put("applyId", applyId);
		paramMap.put("carType", carType);
		paramMap.put("goalType", goalType);
		paramMap.put("carNum", carNum);
		
		Set<UserRole> userRoleSet = revenueService.getOmsUserRoleListByUserId(UserContext.getOperIdBigIntegerMustExist());
		if(!userRoleSet.contains(UserRole.Financer) && !userRoleSet.contains(UserRole.SysAdmin)) {
			UserRole userRole = null;
			if(userRoleSet.contains(UserRole.PropertyCompany)) {
				userRole = UserRole.PropertyCompany;
				//request.setAttribute("roleType", 1);
				paramMap.put("exportType", 1);
			} else if(userRoleSet.contains(UserRole.PCManagement)) {
				userRole = UserRole.PCManagement;
				//request.setAttribute("roleType", 1);
				paramMap.put("exportType", 1);
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
		} /*else {
			request.setAttribute("roleType", 0);
		}*/
		return paramMap;
	}
	
	/**
	 * 明细列表信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/carList.html")
	public ModelAndView list(HttpServletRequest request){
		Map<String, Object> paramMap = initParam(request);
		//导出的时候使用的查询条件map
		request.getSession().setAttribute("revenueCarParamMap_"+UserContext.getOperIdBigIntegerMustExist(), ((HashMap<String, Object>) paramMap).clone());
		
		PageUtils.addPageInfoToParam(request, paramMap);
		
		List<CarRevenueSignalAmount> carRevenueSignalAmountList = revenueCarService.selectCarRevenueSaList(paramMap);
		int resultSize = revenueCarService.selectCarRevenueSaCount(paramMap);
		EbuyRevenueTotal ebuyRevenueTotal = revenueCarService.selectCarRevenueTotal(paramMap);
		
		request.setAttribute("carRevenueSignalAmountList", carRevenueSignalAmountList);
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("ebuyRevenueTotal", ebuyRevenueTotal);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/revenueDredge/carList");
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
			Map<String, Object> paramMap = initParam(request);
			
			List<CarRevenueSignalAmount> carRevenueSignalAmountList = revenueCarService.selectCarRevenueSaList(paramMap);
			
			// 生成提示信息
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String fileName = format.format(new Date());
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
			fOut = response.getOutputStream();
			HSSFWorkbook workbook;
			if(paramMap.get("exportType")!=null && paramMap.get("exportType").toString().equals("1")) {
				workbook = createReport(carRevenueSignalAmountList);
			} else {
				workbook = revenueCarService.createReport(carRevenueSignalAmountList);
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
	
	public HSSFWorkbook createReport(List<CarRevenueSignalAmount> carRevenueSignalAmountList) {
		String[] titles = {"物业公司","小区名称","楼栋","单元","房间号","车牌号","缴费时间","车辆类型", "缴费时长", "账单金额", "实缴金额", "补贴金额", "支付方式", "支付流水", "结算状态"};
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("车禁收益明细");
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
		if(carRevenueSignalAmountList != null) {
			NumberFormat nf = new DecimalFormat("#0.00##");
			double totalAmount = 0.0, totalAmountUsrReal = 0.0, totalAmountPtbt = 0.0;
			for (int i = 0, j = 0; j < carRevenueSignalAmountList.size(); j++) {
				row = sheet.createRow((j+1));
				// 设置excel每列宽度
				sheet.setColumnWidth(j, width);
				CarRevenueSignalAmount bean = carRevenueSignalAmountList.get(j);
				i = 0;
//				{"物业公司","小区名称","楼栋","单元","房间号","车牌号","缴费时间","车辆类型", "缴费时长", "缴费金额", "支付方式", "支付流水", "结算状态"};
				fillCellData(row, i++, bean.getRoleName(), style);
				fillCellData(row, i++, bean.getGbName(), style);
				fillCellData(row, i++, bean.getBuilding(), style);
				fillCellData(row, i++, bean.getUnitName(), style);
				fillCellData(row, i++, bean.getRoom(), style);
				fillCellData(row, i++, bean.getCarNum(), style);
				fillCellData(row, i++, DateUtils.formatMinuteTime(bean.getPayTime()), style);
				if(bean.getPayNum() != null && bean.getPayNum() == 12) {
					fillCellData(row, i++, "年卡车",style);
				} else if(bean.getPayNum() != null && bean.getPayNum() == 0) {
					fillCellData(row, i++, "次缴车",style);
				} else {
					fillCellData(row, i++, "月卡车",style);
				}
				fillCellData(row, i++, (bean.getPayNum() == null || bean.getPayNum() == 0) ? "" : bean.getPayNum().toString() + "个月", style);
				fillCellData(row, i++, nf.format(bean.getAmount()), style);
				fillCellData(row, i++, nf.format(bean.getAmountUsrReal()), style);
				fillCellData(row, i++, nf.format(bean.getAmountPtbt()), style);
				fillCellData(row, i++, bean.getPayMethodStr(), style);
				fillCellData(row, i++, bean.getPayFlowNo(), style);
				fillCellData(row, i++, bean.getTkStatusStr(), style);
				
				totalAmount        += bean.getAmount();
				totalAmountUsrReal += bean.getAmountUsrReal();
				totalAmountPtbt    += bean.getAmountPtbt();
			}
			// 最后一行，统计
			row = sheet.createRow((carRevenueSignalAmountList.size()+1));
			fillCellData(row, 8, "总计：", style);
			fillCellData(row, 9, nf.format(totalAmount), style);
			fillCellData(row, 10, nf.format(totalAmountUsrReal), style);
			fillCellData(row, 11, nf.format(totalAmountPtbt), style);
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
