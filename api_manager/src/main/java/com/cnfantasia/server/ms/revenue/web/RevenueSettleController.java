package com.cnfantasia.server.ms.revenue.web;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.revenueApplyPush.bean.ResultInfo;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ExcelUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.domainbase.propertyCompany.service.IPropertyCompanyBaseService;
import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;
import com.cnfantasia.server.domainbase.propertyManagement.service.IPropertyManagementBaseService;
import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.MapConverter;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;
import com.cnfantasia.server.ms.revenue.entity.RevenueSearchParam;
import com.cnfantasia.server.ms.revenue.entity.RevenueSettlement;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;

/**
 * 收益结算 
 * 
 * @author wenfq 2014-12-22
 */
@Controller
@RequestMapping("/revenueSettle")
public class RevenueSettleController extends BaseController{
	
	@Resource
	IRevenueService revenueService;
	
	@Resource
	IPropertyCompanyBaseService propertyCompanyBaseService;
	@Resource
	IPropertyManagementBaseService propertyManagementBaseService;
	
	private ModelAndView  listBase(HttpServletRequest request,String formUrl,Integer role){
		BigInteger omsUserId = UserContext.getOperIdBigIntegerMustExist();
		request.setAttribute("formUrl", formUrl);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("date01", ParamUtils.getStringTrim(request, "date01"));
		paramMap.put("date02", ParamUtils.getStringTrim(request, "date02"));
		paramMap.put("tkNo", ParamUtils.getStringTrim(request, "tkNo"));
		paramMap.put("tkStatus", ParamUtils.getInt(request, "tkStatus", 0));
		
		PageUtils.addPageInfoToParam(request, paramMap);
		
		if(RevenueDict.MiniRoleType.PropertyCompany == role.intValue()){
			PropertyCompany pcQry = new PropertyCompany();
			pcQry.setAdminId(omsUserId);
			List<PropertyCompany> pcList = propertyCompanyBaseService.getPropertyCompanyByCondition(MapConverter.convertBean(pcQry));
			if(!pcList.isEmpty()){
				PropertyManagement pmQry = new PropertyManagement();
				pmQry.settPropertyCompanyFId(pcList.get(0).getId());
				List<PropertyManagement> pmList = propertyManagementBaseService.getPropertyManagementByCondition(MapConverter.toMap(pmQry));
				List<BigInteger> pmIdList = new ArrayList<BigInteger>();
				for(int i = 0; i < pmList.size(); i++){
					pmIdList.add(pmList.get(i).getId());
				}
				paramMap.put("pmIdList", pmIdList);
			}
		}
		
		List<RevenueSettlement> resList = revenueService.select_revenueSettleList(omsUserId,UserRole.createUserRole(role),paramMap);
		/**
		 * 修复bug：
		 *     此处select_revenueSettleList必须在select_revenueSettleList_forCount的前面，
		 * 因为paramMap里面有些参数在“select_revenueSettleList”里面初始化
		 * */ 
		int resultSize = revenueService.select_revenueSettleList_forCount(paramMap);
		
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("resList", resList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/revenue/revenueSettleList");
		return modelAndView;
	}
	
	/**
	 * 列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/listPropCompany.html")
	public ModelAndView listPropCompany(HttpServletRequest request) {
		return listBase(request, "/revenueSettle/listPropCompany.html", RevenueDict.MiniRoleType.PropertyCompany);
	}
	
	/**
	 * 列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/listPropManagement.html")
	public ModelAndView listPropManagement(HttpServletRequest request) {
		return listBase(request, "/revenueSettle/listPropManagement.html", RevenueDict.MiniRoleType.PCManagement);
	}
	
	/**
	 * 列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/listPropertyAgent.html")
	public ModelAndView listPropertyAgent(HttpServletRequest request) {
		return listBase(request, "/revenueSettle/listPropertyAgent.html", RevenueDict.MiniRoleType.PropertyAgent);
	}
	
	/**
	 * 列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/listEbuy.html")
	public ModelAndView listEbuy(HttpServletRequest request) {
		return listBase(request, "/revenueSettle/listEbuy.html", RevenueDict.MiniRoleType.DownstairStore);
	}
	
	/**
	 * 财务人员查看结算中心列表
	 * 
	 * @param searchParam
	 * @param request
	 * @return
	 */
	@RequestMapping("/listFinance.html")
	public ModelAndView listFinance(RevenueSearchParam searchParam, HttpServletRequest request) {
		Map<String, Object> paramMap = MapConverter.toMap(searchParam);
		
		PageModel pageModel = ControllerUtils.getPageModel(request, 1,10);
		BigInteger omsUserId = UserContext.getOperIdBigIntegerMustExist();
		UserRole userRole = UserRole.Financer;
		//校验
		RevenueRole revenueRole =  revenueService.createRevenueRoleExcep(omsUserId, userRole);
		if(revenueRole.getRole().compareTo(UserRole.Financer)!=0){//非财务人员无法获取数据
			throw new BusinessRuntimeException("revenueApply.getList.notFinancer");
		}
		
		List<RevenueApply> resList = revenueService.getRevenueApplyListFinance(paramMap,pageModel);
		Double totalAmountAll = revenueService.getTotalAmountAll(paramMap);//结算金额总计
		
		request.setAttribute("resList", resList);
		request.setAttribute("totalAmountAll", totalAmountAll);
		request.setAttribute("page", pageModel.toMap());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/revenue/revenueSettleListFinance");
		return modelAndView;
	}
	
	/**
	 * 导出当前查询结果
	 * 
	 * @param searchParam
	 * @param response
	 */
	@RequestMapping("/exportExcel.html")
	public void exportExcel(RevenueSearchParam searchParam, HttpServletResponse response){
		Map<String, Object> paramMap = MapConverter.toMap(searchParam);
		
		SimpleDateFormat format = new SimpleDateFormat("_yyyyMMddHHmmss");
		String exportFileName = "提款申请记录" + format.format(new Date());
		HSSFWorkbook workbook = revenueService.getRevenueApplyHSSFWorkbook(paramMap);

		ExcelUtil.commonExport(exportFileName, workbook, response);
	}
	
	/**
	 * 生成单据
	 * 
	 * @param applyId
	 * @param goalType
	 * @param miniRoleType
	 * @return
	 */
	@RequestMapping("/pushBill2EAS.html")
	@ResponseBody
	public JsonResponse pushBill2EAS(String applyId, String goalType, String miniRoleType){
		ResultInfo resultInfo = revenueService.pushBill2EAS(applyId, goalType, miniRoleType);
		
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setDataValue(resultInfo);
		return jsonResponse;
	}
	
	/**
	 * 导出功能
	 * @param request
	 * @return
	 *//*
	@RequestMapping("/exportExcel.html")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response){
		OutputStream fOut = null;
		InputStream fin = null;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("date01", ParamUtils.getStringTrim(request, "date01"));
			paramMap.put("date02", ParamUtils.getStringTrim(request, "date02"));
			paramMap.put("tkNo", ParamUtils.getStringTrim(request, "tkNo"));
			paramMap.put("tkStatus", ParamUtils.getInt(request, "tkStatus", 0));
			
			paramMap.put("roleName", ParamUtils.getStringTrim(request, "roleName"));
			paramMap.put("goalType", ParamUtils.getInteger(request, "goalType",null));
			paramMap.put("roleType", ParamUtils.getInteger(request, "roleType",null));
			
//			PageModel pageModel = ControllerUtils.getPageModel(request, 1,10);
			BigInteger omsUserId = UserContext.getOperIdBigIntegerMustExist();
			UserRole userRole = UserRole.Financer;
			//校验
			RevenueRole revenueRole =  revenueService.createRevenueRoleExcep(omsUserId, userRole);
			if(revenueRole.getRole().compareTo(UserRole.Financer)!=0){//非财务人员无法获取数据
				throw new BusinessRuntimeException("revenueApply.getList.notFinancer");
			}
			
			List<RevenueApply> resList = revenueService.getRevenueApplyListFinance(paramMap);
			
			// 生成提示信息
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String fileName = format.format(new Date());
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
			fOut = response.getOutputStream();
			HSSFWorkbook workbook;
			workbook = createReport(resList);
			
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
	
	@SuppressWarnings("deprecation")
	public HSSFWorkbook createReport(List<RevenueApply> resList) {
		String[] titles = {"序号", "银行", "地区（省）", "地区（市/区）", "支行名", "开户名", "卡号", "金额", "电话号码", "备注"};
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
		if(resList != null) {
			NumberFormat nf = new DecimalFormat("#0.00##");
			for (int i = 0, j = 0; j < resList.size(); j++) {
				row = sheet.createRow((j+1));
				// 设置excel每列宽度
				sheet.setColumnWidth(j, width);
				RevenueApply bean = resList.get(j);
				i = 0;
//				{"序号","银行","地区（省）","地区（市/区）","支行名","开户名","卡号","金额","电话号码", "备注"}
				fillCellData(row, i++, String.valueOf(j+1),style);
				fillCellData(row, i++, bean.getbBankName(),style);
				fillCellData(row, i++, bean.getBankProvince(),style);
				fillCellData(row, i++, bean.getBankCity(),style);
				fillCellData(row, i++, bean.getBankBranch(),style);
				fillCellData(row, i++, bean.getAccountName(),style);
				fillCellData(row, i++, bean.getbBankNo(),style);
				fillCellData(row, i++, nf.format(bean.getTotalAmount()),style);
				fillCellData(row, i++, bean.getbPhone(),style);
			}
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
	*/
}
