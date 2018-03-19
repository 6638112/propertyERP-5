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
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueProd;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;
import com.cnfantasia.server.ms.revenue.entity.RevenueRoleAll;
import com.cnfantasia.server.ms.revenue.entity.RevenueRoleMultiId;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;

/**
 * 店铺收益
 * @author yewj
 *
 */
@Controller
@RequestMapping("/revenueEbuy")
public class RevenueEbuyController extends BaseController{
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Resource
	private IRevenueService revenueService;
	
	@Resource
	private RevenueConfigCommonController revenueConfigCommonController;
	
	/**
	 * 配置概要预览列表信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/ebuyList.html")
	public ModelAndView list(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		String supplyName = ParamUtils.getString(request, "supplyName", null);
		String wyOrAgentName = ParamUtils.getString(request, "wyOrAgentName", null);
//		Long smId = ParamUtils.getLong(request, "smId", null);
		String groupBuildingName = ParamUtils.getString(request, "gbName", null);
		Date payTmStart = ParamUtils.getDate(request, "payTmStart", null, "yyyy-MM-dd HH:mm");
		Date payTmEnd = ParamUtils.getDate(request, "payTmEnd", null, "yyyy-MM-dd HH:mm");
//		Integer tkStatus = ParamUtils.getInteger(request, "tkStatus", null);
		Long roleId = ParamUtils.getLong(request, "roleId", null);
		Long applyId = ParamUtils.getLong(request, "applyId", null);
		paramMap.put("supplyName", supplyName);
//		paramMap.put("smId", roleId);
		paramMap.put("groupBuildingName", groupBuildingName);
		paramMap.put("payTmStart", payTmStart);
		paramMap.put("payTmEnd", payTmEnd);
//		paramMap.put("tkStatus", tkStatus);
		paramMap.put("roleId", roleId);
		paramMap.put("applyId", applyId);
		paramMap.put("wyOrAgentName", wyOrAgentName);
		processStatus(request, paramMap);
		
		Integer roleType = 3; //供应商
		
		Set<UserRole> userRoleSet = revenueService.getOmsUserRoleListByUserId(UserContext.getOperIdBigIntegerMustExist());
		if(!userRoleSet.contains(UserRole.Financer) && !userRoleSet.contains(UserRole.SysAdmin)) {
			UserRole userRole = null;
			if(userRoleSet.contains(UserRole.PropertyCompany)) {
				userRole = UserRole.PropertyCompany;
				request.setAttribute("roleType", 2);
				roleType = 2; //物业公司　
			} else if(userRoleSet.contains(UserRole.PCManagement)) {
				userRole = UserRole.PCManagement;
				request.setAttribute("roleType", 2);
				roleType = userRole.getCode(); //管理处
			} else if(userRoleSet.contains(UserRole.PropertyAgent)) {
				userRole = UserRole.PropertyAgent;
				request.setAttribute("roleType", 3);
				roleType = 3; //代理商
			} else if(userRoleSet.contains(UserRole.DownstairStore)) {
				userRole = UserRole.DownstairStore;
				request.setAttribute("roleType", 5);
				roleType = 5; //供应商
			}
			RevenueRole role = revenueService.createRevenueRoleExcep(UserContext.getOperIdBigIntegerMustExist(), userRole);
			if(role instanceof RevenueRoleMultiId) {
				RevenueRoleMultiId multRole = (RevenueRoleMultiId) role;
				List<BigInteger> smIdList = multRole.getRoleIdList();
				paramMap.put("smIdList", smIdList);
			} else if(role instanceof RevenueRoleAll) {
				//不需要处理，即搜索全部
			} else {
				if(roleId == null) {
					paramMap.put("roleId", role.getRoleId());
				}
			}
		} else if(userRoleSet.contains(UserRole.SysAdmin)) {
			request.setAttribute("roleType", 1);
			roleType = 1; // 管理员
		} else if(userRoleSet.contains(UserRole.Financer)) {
			request.setAttribute("roleType", 1);
			roleType = 1; // 财务
		}
		
		paramMap.put("roleType", roleType);
		//导出的时候使用的查询条件map
		request.getSession().setAttribute("revenueEbuyParamMap_"+UserContext.getOperIdBigIntegerMustExist(), ((HashMap<String, Object>) paramMap).clone());
		PageUtils.addPageInfoToParam(request, paramMap);
		
		List<EbuyRevenueSignalAmount> ebuyRevenueSignalAmountList = revenueService.selectEbuyRevenueSignalAmountList(paramMap);
		int resultSize = revenueService.selectEbuyRevenueSignalAmountCount(paramMap);
		EbuyRevenueTotal ebuyRevenueTotal = revenueService.selectEbuyRevenueTotal(paramMap);
		
		request.setAttribute("ebuyRevenueSignalAmountList", ebuyRevenueSignalAmountList);
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("ebuyRevenueTotal", ebuyRevenueTotal);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/revenueEbuy/ebuyList");
		return mav;
	}
	
	private void processStatus(HttpServletRequest request, Map<String, Object> paramMap) {
		Integer profitObject = ParamUtils.getInteger(request, "profitObject", -1);
		Integer tkStatus = ParamUtils.getInteger(request, "tkStatus", null);
		
		switch (profitObject.intValue()) {
		case 0:
//			paramMap.put("tkStatus", tkStatus);
			break;
		case 1:
			paramMap.put("tkStatusSupply", tkStatus);
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
		default:
			paramMap.put("tkStatus", tkStatus);
			break;
		}
	}
	
	/**
	 * 导出功能
	 * @param request
	 * @return
	 */
	@RequestMapping("/ebuySearch.html")
	public void ebuySearch(HttpServletRequest request, HttpServletResponse response){
		OutputStream fOut = null;
		InputStream fin = null;
		try {
			Long roleId = ParamUtils.getLong(request, "roleId", null);
			HashMap<String, Object> paramMap = (HashMap<String, Object>) request.getSession().getAttribute("revenueEbuyParamMap_"+UserContext.getOperIdBigIntegerMustExist());
			processStatus(request, paramMap);
			
			Integer roleType = 5; //供应商
			
			Set<UserRole> userRoleSet = revenueService.getOmsUserRoleListByUserId(UserContext.getOperIdBigIntegerMustExist());
			if(!userRoleSet.contains(UserRole.Financer) && !userRoleSet.contains(UserRole.SysAdmin)) {
				UserRole userRole = null;
				if(userRoleSet.contains(UserRole.PropertyCompany)) {
					userRole = UserRole.PropertyCompany;
					request.setAttribute("roleType", 2);
					roleType = 2; //物业公司　
				}else if(userRoleSet.contains(UserRole.PCManagement)) {
					userRole = UserRole.PCManagement;
					request.setAttribute("roleType", 2);
					roleType = userRole.getCode(); //管理处
				} else if(userRoleSet.contains(UserRole.PropertyAgent)) {
					userRole = UserRole.PropertyAgent;
					request.setAttribute("roleType", 3);
					roleType = 3; //代理商
				} else if(userRoleSet.contains(UserRole.DownstairStore)) {
					userRole = UserRole.DownstairStore;
					request.setAttribute("roleType", 5);
					roleType = 5; //代理商
				}
				RevenueRole role = revenueService.createRevenueRoleExcep(UserContext.getOperIdBigIntegerMustExist(), userRole);
				if(role instanceof RevenueRoleMultiId) {
					RevenueRoleMultiId multRole = (RevenueRoleMultiId) role;
					List<BigInteger> smIdList = multRole.getRoleIdList();
					paramMap.put("smIdList", smIdList);
				} else if(role instanceof RevenueRoleAll) {
					//不需要处理，即搜索全部
				} else {
					if(roleId == null) {
						paramMap.put("roleId", role.getRoleId());
					}
				}
			} else if(userRoleSet.contains(UserRole.SysAdmin)) {
				request.setAttribute("roleType", 1);
				roleType = 1; // 管理员
			} else if(userRoleSet.contains(UserRole.Financer)) {
				request.setAttribute("roleType", 1);
				roleType = 1; // 财务
			}
			paramMap.put("roleType", roleType);
			
			List<EbuyRevenueSignalAmount> ebuyRevenueSignalAmountList = revenueService.selectEbuyRevenueSignalAmountExportList(paramMap);
			
			// 生成提示信息
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String fileName = format.format(new Date());
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
			fOut = response.getOutputStream();
			HSSFWorkbook workbook;
			if(roleType == 1) {//管理员和财务
				workbook = revenueService.createReport(ebuyRevenueSignalAmountList);
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
//		String[] titles = {"店铺名称","解放号","小区名称","平台补贴金额","优惠金额","运单实付","运单总额","订单实付", "支付渠道", "流水号", "支付时间", "退款金额", "结算金额", "结算状态"};
		//roleType = 3
		String[] titles = { "订单号","解放号","支付时间","商品名称","购买数量","结算价","结算价小计", "运费", "运单总额", "平台使用费", "退款金额","结算金额", "支付方式", "收货人姓名", "收货人联系方式", "收货地址"};
		if(roleType == 1) { //
			titles = new String[] {"订单号","解放号","支付时间","店铺名称","商品名称","购买数量","结算价","结算价小计", "运费", "运单总额", "运单实付", "优惠金额", "平台收入", "代收金额", "退款金额", "结算金额", "收益金额", "支付方式", "支付流水号","收货人姓名", "收货人联系方式", "收货地址"};
		} else if(roleType == 5) {
			titles = new String[] {"订单号","解放号","支付时间","店铺名称","商品名称","购买数量","结算价","结算价小计", "运费", "运单总额", "平台收入", "退款金额", "结算金额", "支付方式", "收货人姓名", "收货人联系方式", "收货地址"};
		} else if(roleType == 2 || roleType == 3) {
			titles = new String[] {"物业公司","代理商","店铺名称","小区名称","订单号","运单总额", "支付时间", "退款金额", "平台收入", "收益金额", "结算状态"};
		}
		
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("店铺收益明细");
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
			int count = 0;
			for (int i = 0, j = 0;  j < ebuyRevenueSignalAmountList.size(); j++) {
				EbuyRevenueSignalAmount bean = ebuyRevenueSignalAmountList.get(j);
				int prodIndex = 0;
				for(EbuyRevenueProd prod : bean.getEbuyRevenueProdList()) {
					if(roleType == 1) {
						row = sheet.createRow((count + 1));
						sheet.setColumnWidth(count, width); // 设置excel每列宽度
						count ++;
						
						i = 0;
						fillCellData(row, i++, bean.getOrderNo(),style);
						fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getHuaId(),style);
						//支付时间
						if(bean.getRevenueSignalAmountEbuy().getPayMethod() == null) {
							fillCellData(row, i++, "",style);
						} else {
							fillCellData(row, i++, DateUtils.formatTime(bean.getPayTm()),style);
						}
						fillCellData(row, i++, bean.getSupplyName(),style); //店铺名称
						fillCellData(row, i++, prod.getProductName(),style); //商品名称
						fillCellData(row, i++, String.valueOf(prod.getQty()),style); 
						//结算价
						fillCellData(row, i++, nf.format(prod.getSettlementPrice()),style);
						
						if(prodIndex == 0) {
							//结算价小计
							fillCellData(row, i++, nf.format(bean.getDeliveTotalFee()),style);
							
							//运费
							fillCellData(row, i++, nf.format(prod.getDeliveFee()),style);
							
							//运单总额
//							fillCellData(row, i++, nf.format(BigDecimalUtil.add(bean.getDeliveTotalFee(), prod.getDeliveFee())),style);
							fillCellData(row, i++, nf.format(bean.getRevenueSignalAmountEbuy().getAmountTotal()), style);
							fillCellData(row, i++, String.valueOf(bean.getRevenueSignalAmountEbuy().getAmountReal()),style); //运单实付
							fillCellData(row, i++, nf.format(bean.getRevenueSignalAmountEbuy().getAmountDiscount()),style); //优惠金额
							
							//平台使用费
							if(bean.getRevenueSignalAmountEbuy().getAmoutDeduct() != null && bean.getRevenueSignalAmountEbuy().getAmoutDeduct() > 0) {
								fillCellData(row, i++, nf.format(bean.getRevenueSignalAmountEbuy().getAmoutDeduct()),style);
							} else {
								bean.getRevenueSignalAmountEbuy().setAmoutDeduct(0.0);
								fillCellData(row, i++, "",style);
							}
							//代收金额
							fillCellData(row, i++, String.valueOf(BigDecimalUtil.sub(bean.getRevenueSignalAmountEbuy().getAmountReal(), bean.getRevenueSignalAmountEbuy().getAmoutDeduct())),style); //代收金额
							//退款金额
							fillCellData(row, i++, String.valueOf(bean.getRevenueSignalAmountEbuy().getAmountRefund()),style);
							
							fillCellData(row, i++, nf.format(bean.getSrcMoney() - bean.getRevenueSignalAmountEbuy().getAmoutDeduct()),style);//结算金额
							fillCellData(row, i++, nf.format(bean.getAmountPlatform() == null ? 0 : bean.getAmountPlatform()),style);//收益金额
							
							fillCellData(row, i++, PayUtils.getPayMethodStr(bean.getRevenueSignalAmountEbuy().getPayMethod()),style);
							
							fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getFlowNo(),style); //流水号
							
							fillCellData(row, i++, bean.getDelivePeople(),style); //收款人姓名
							fillCellData(row, i++, bean.getDelivePhone(),style); //收款人联系方式
							fillCellData(row, i++, bean.getDeliveAddress(),style); //收款人地址
							
							prodIndex ++;
						}
					} else if (roleType == 5) {
						row = sheet.createRow((count + 1));
						sheet.setColumnWidth(count, width); // 设置excel每列宽度
						count ++;
						
						i = 0;
						fillCellData(row, i++, bean.getOrderNo(),style);
						fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getHuaId(),style);
						if(bean.getRevenueSignalAmountEbuy().getPayMethod() == null) {
							fillCellData(row, i++, "",style);
						} else {
							fillCellData(row, i++, DateUtils.formatTime(bean.getPayTm()),style);
						}
						fillCellData(row, i++, bean.getRoleName(),style);
						fillCellData(row, i++, prod.getProductName(),style); //商品名称
						fillCellData(row, i++, String.valueOf(prod.getQty()),style); 
						//结算价
						fillCellData(row, i++, nf.format(prod.getSettlementPrice()),style);
						
						if(prodIndex == 0) {
							//结算价小计
							fillCellData(row, i++, nf.format(bean.getDeliveTotalFee()),style);
							
							//运费
							fillCellData(row, i++, nf.format(prod.getDeliveFee()),style);
							
							//运单总额
//							fillCellData(row, i++, nf.format(BigDecimalUtil.add(bean.getDeliveTotalFee(), prod.getDeliveFee())),style);
							fillCellData(row, i++, nf.format(bean.getRevenueSignalAmountEbuy().getAmountTotal()), style);
							
							//平台使用费
							if(bean.getRevenueSignalAmountEbuy().getAmoutDeduct() != null && bean.getRevenueSignalAmountEbuy().getAmoutDeduct() > 0) {
								fillCellData(row, i++, nf.format(bean.getRevenueSignalAmountEbuy().getAmoutDeduct()),style);
							} else {
								bean.getRevenueSignalAmountEbuy().setAmoutDeduct(0.0);
								fillCellData(row, i++, "",style);
							}
							//退款金额
							fillCellData(row, i++, String.valueOf(bean.getRevenueSignalAmountEbuy().getAmountRefund()),style);
							
							fillCellData(row, i++, nf.format(bean.getAmount()),style);
							
							fillCellData(row, i++, PayUtils.getPayMethodStr(bean.getRevenueSignalAmountEbuy().getPayMethod()),style);
							
							fillCellData(row, i++, bean.getDelivePeople(),style); //收款人姓名
							fillCellData(row, i++, bean.getDelivePhone(),style); //收款人联系方式
							fillCellData(row, i++, bean.getDeliveAddress(),style); //收款人地址
							
							prodIndex ++;
						}
					} else if (roleType == 2 || roleType == 3) {
						row = sheet.createRow((count + 1));
						sheet.setColumnWidth(count, width); // 设置excel每列宽度
						count ++;
						
						i = 0;
						fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getPcName(),style);
						fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getAgentName(),style);
						fillCellData(row, i++, bean.getSupplyName(),style);
						fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getGroupBuildingName(),style);
						fillCellData(row, i++, bean.getOrderNo(),style);
						fillCellData(row, i++, nf.format(bean.getRevenueSignalAmountEbuy().getAmountTotal()),style);
						fillCellData(row, i++, DateUtils.formatTime(bean.getPayTm()),style);
						fillCellData(row, i++, nf.format(bean.getRevenueSignalAmountEbuy().getAmountRefund()),style);
						fillCellData(row, i++, nf.format(bean.getRevenueSignalAmountEbuy().getAmoutDeduct()),style);
						if(roleType == 2) {
							fillCellData(row, i++, nf.format(bean.getAmountWy()),style);
							fillCellData(row, i++, bean.getTkStatusWyStr(),style);
						} else {
							fillCellData(row, i++, nf.format(bean.getAmountAgent()),style);
							fillCellData(row, i++, bean.getTkStatusAgentStr(),style);
						}
					}
					
				}
			}
		}

		// 设置单元格的样式格式
		return wb;
	}
	
//	/**
//	 * 导出功能
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/ebuySearch.html")
//	public void ebuySearch(HttpServletRequest request, HttpServletResponse response){
//		OutputStream fOut = null;
//		InputStream fin = null;
//		try {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			
//			String supplyName = ParamUtils.getString(request, "supplyName", null);
//			String groupBuildingName = ParamUtils.getString(request, "groupBuildingName", null);
//			Date payTmStart = ParamUtils.getDate(request, "payTmStart", null, "yyyy-MM-dd HH:mm");
//			Date payTmEnd = ParamUtils.getDate(request, "payTmEnd", null, "yyyy-MM-dd HH:mm");
//			Integer tkStatus = ParamUtils.getInteger(request, "tkStatus", null);
//			Long roleId = ParamUtils.getLong(request, "roleId", null);
//			Long applyId = ParamUtils.getLong(request, "applyId", null);
//			paramMap.put("supplyName", supplyName);
//			paramMap.put("groupBuildingName", groupBuildingName);
//			paramMap.put("payTmStart", payTmStart);
//			paramMap.put("payTmEnd", payTmEnd);
//			paramMap.put("tkStatus", tkStatus);
//			paramMap.put("roleId", roleId);
//			paramMap.put("applyId", applyId);
//			
//			if(roleId == null && UserContext.getCurrUser().getIsadmin() != 1 && UserContext.getSupplyMerchantIdList().size() > 0) {
//				List<BigInteger> smIdList = UserContext.getSupplyMerchantIdList();
//				paramMap.put("smIdList", smIdList);
//			}
//			
//			List<EbuyRevenueSignalAmount> ebuyRevenueSignalAmountList = revenueService.selectEbuyRevenueSignalAmountList(paramMap);
//			
//			// 生成提示信息
//			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//			String fileName = format.format(new Date());
//			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
//			response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
//			fOut = response.getOutputStream();
//			HSSFWorkbook workbook;
//			workbook = createReport(ebuyRevenueSignalAmountList);
//			
//			workbook.write(fOut);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			throw new RuntimeException(e);
//		} finally {
//			try {
//				if (fOut != null) {
//					fOut.flush();
//					fOut.close();
//				}
//				if (fin != null) {
//					fin.close();
//				}
//			} catch (IOException ie) {
//				throw new RuntimeException(ie);
//			}
//		}
//	}
//	
//	@SuppressWarnings("deprecation")
//	public HSSFWorkbook createReport(List<EbuyRevenueSignalAmount> ebuyRevenueSignalAmountList) {
//		String[] titles = {"店铺名称","解放号","小区名称","平台补贴金额","优惠金额","运单实付","运单总额","订单实付", "支付渠道", "流水号", "支付时间", "退款金额", "结算金额", "结算状态"};
//		// 创建Excel的工作书册 Workbook,对应到一个excel文档
//		HSSFWorkbook wb = new HSSFWorkbook();
//		// 创建Excel的工作sheet,对应到一个excel文档的tab
//		HSSFSheet sheet = wb.createSheet("店铺收益明细");
//		// 创建字体样式
//		HSSFFont font = wb.createFont();
//		font.setFontName("微软雅黑");
//		// 创建单元格样式
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//		// 设置字体
//		style.setFont(font);
//		short height = 300;
//		short width = 4000;
//		// 创建Excel的sheet的一行
//		HSSFRow row = sheet.createRow(0);
//		// 设定行的高度
//		row.setHeight(height);
//		for (int i = 0; i < titles.length; i++) {
//			// 设置excel每列宽度
//			sheet.setColumnWidth(i, width);
//			HSSFCell cell = row.createCell(i);
//			cell.setCellStyle(style);
//			cell.setCellValue(titles[i]);
//		}
//		if(ebuyRevenueSignalAmountList != null) {
//			for (int i = 0, j = 0; j < ebuyRevenueSignalAmountList.size(); j++) {
//				row = sheet.createRow((j+1));
//				// 设置excel每列宽度
//				sheet.setColumnWidth(j, width);
//				EbuyRevenueSignalAmount bean = ebuyRevenueSignalAmountList.get(j);
//				i = 0;
////				{"订单号","解放号","订单状态","订单生成时间","支付时间","支付方式","支付状态","供应商名称", "商品名称", "商品类别", "商品单价(元)", "购买数量", "购买单位","商品金额(元)","物流费用(元)",
////					"订单总额(元)","使用优惠", "实际支付", "物流方式", "物流单号", "收货人姓名", "收货人联系方式","收货地址"};
//				fillCellData(row, i++, bean.getRoleName(),style);
//				fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getHuaId(),style);
//				fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getGroupBuildingName(),style);
//				fillCellData(row, i++, "0",style);
//				fillCellData(row, i++, String.valueOf(bean.getRevenueSignalAmountEbuy().getAmountDiscount()),style);
//				fillCellData(row, i++, String.valueOf(bean.getRevenueSignalAmountEbuy().getAmountReal()),style);
//				fillCellData(row, i++, String.valueOf(bean.getRevenueSignalAmountEbuy().getAmountTotal()),style);
//				fillCellData(row, i++, String.valueOf(bean.getRevenueSignalAmountEbuy().getAmountOrderReal()),style);
//				fillCellData(row, i++, PayUtils.getPayMethodStr(bean.getRevenueSignalAmountEbuy().getPayMethod()), style);
//				fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getFlowNo(), style);
//				fillCellData(row, i++, DateUtils.formatMinuteTime(bean.getPayTm()),style);
//				fillCellData(row, i++, String.valueOf(bean.getRevenueSignalAmountEbuy().getAmountRefund()),style);
//				fillCellData(row, i++, String.valueOf(bean.getAmount()),style);
//				fillCellData(row, i++, bean.getTkStatusStr(),style);
//			}
//		}
//
//		// 设置单元格的样式格式
//		return wb;
//	}
	
	@SuppressWarnings("deprecation")
	private void fillCellData(HSSFRow row,int index,String value,HSSFCellStyle style){
		HSSFCell cell = row.createCell(index);
		cell.setCellStyle(style);
		cell.setCellValue(value);
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
