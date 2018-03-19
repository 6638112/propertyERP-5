package com.cnfantasia.server.ms.payBill.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.omsPermiRole.constant.OmsPermiRoleConstant;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyDict;
import com.cnfantasia.server.api.plotproperty.service.FinanceService;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.NumberUtils;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.entity.OmsUserHasTOmsPermiRole;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.ms.buildingRoom.service.IBuildingRoomService;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.groupBuilding.service.IGroupBuildingService;
import com.cnfantasia.server.ms.payBill.constant.PayBillDict;
import com.cnfantasia.server.ms.payBill.entity.PayBillEntity;
import com.cnfantasia.server.ms.payBill.entity.PayBillTotalData;
import com.cnfantasia.server.ms.payBill.entity.PayBillWithFeeDetailEntity;
import com.cnfantasia.server.ms.payBill.service.FeePrintService;
import com.cnfantasia.server.ms.payBill.service.IPayBillService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;

/**
 * 物业账单Controller抽象类
* Filename:    AbstractPayBillController.java
* @version:    1.0.0
* Create at:   2015年12月1日 上午11:01:00
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年12月1日       shiyl             1.0             1.0 Version
 */
@Controller
public class AbstractPayBillController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	@Resource
	protected FinanceService financeService;
	
	@Resource
	protected IPayBillService payBillService;
	
	@Resource
	protected FeePrintService feePrintService;
	
	
	@Resource
	protected IBuildingRoomService buildingRoomService;
	
	protected ModelAndView searchBase(HttpServletRequest request,String formUrl){
		boolean isRevenue = false;
		return searchBase(request, formUrl, isRevenue);
	}
	
	protected void insertParamOrAttribute(HttpServletRequest request,Map<String, Object> paramMap){
		String isPay =  ParamUtils.getString(request,"isPay");
		String paymentWay =  ParamUtils.getString(request,"paymentWay");
		if (!"-1".equals(isPay) && !"3".equals(isPay) && !"2".equals(isPay)) {
			paramMap.put("isPay", isPay);
		} else if("3".equals(isPay)) {
			paramMap.put("isPay", 2);
			paramMap.put("financeStatus", 1);
		} else if("2".equals(isPay)) {
			paramMap.put("isPay", 2);
			paramMap.put("financeStatus", 0);
		} else if("4".equals(isPay)){
			paramMap.put("isPay", 4);
		} else if("5".equals(isPay)){
			paramMap.put("isPay", 5);
		}
		if(UserContext.getCurrUser().getIsadmin() != null && UserContext.getCurrUser().getIsadmin() == 1 && (isPay == null || "".equals(isPay))
				&&  ParamUtils.getString(request,"gbId") == null) {
			paramMap.put("isPay", 4);
			request.setAttribute("isPay", 4);
		}
		if ((!"-1".equals(paymentWay))) {
			paramMap.put("paymentWay", paymentWay);
		}
	}
	protected Map<String, Object> searchBaseInitMap(HttpServletRequest request){
		String gbName = ParamUtils.getString(request,"gbName");
		String buyerId = ParamUtils.getString(request,"buyerId");
		String ppName = ParamUtils.getString(request,"ppName");
		String pbMonth = ParamUtils.getString(request,"pbMonth");
		
		String payTimeStart = ParamUtils.getString(request,"date01");
		String payTimeEnd = ParamUtils.getString(request,"date02");
		String pcName = ParamUtils.getString(request,"pcName");

		String gbId = ParamUtils.getString(request,"gbId");
		String cycleType = ParamUtils.getString(request,"cycleType");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pcName", pcName);
		if (!"all".equals(gbName)) {
			paramMap.put("gbName", gbName);
		}
		paramMap.put("buyerId", buyerId);
		paramMap.put("ppName", ppName);
		paramMap.put("pbMonth", pbMonth);
		paramMap.put("payTimeStart", payTimeStart);
		paramMap.put("payTimeEnd", payTimeEnd);
		paramMap.put("gbId", gbId);
		paramMap.put("cycleType", cycleType);

		Date now = new Date();
		if(payTimeStart==null){
			//request.setAttribute("date01", DateUtils.convertDateToStr(DateUtils.decreateMonth(now, 3), "yyyy-MM-dd HH:mm"));
			//paramMap.put("payTimeStart",  DateUtils.convertDateToStr(DateUtils.decreateMonth(now, 3), "yyyy-MM-dd HH:mm"));
		}else{
			request.setAttribute("date01", payTimeStart);
		}
		if(payTimeEnd==null){
			//request.setAttribute("date02", DateUtils.convertDateToStr(now, "yyyy-MM-dd HH:mm"));
			//paramMap.put("payTimeEnd", DateUtils.convertDateToStr(now, "yyyy-MM-dd HH:mm"));
		}else{
			request.setAttribute("date02", payTimeEnd);
		}
		
		insertParamOrAttribute(request, paramMap);
		return paramMap;
	}
	
	protected ModelAndView searchBase(HttpServletRequest request,String formUrl,boolean isRevenue){
		long startTime  = System.currentTimeMillis();
		request.setAttribute("isRevenue", isRevenue);
		request.setAttribute("formUrl", formUrl);
		Map<String, Object> paramMap = searchBaseInitMap(request);

		String searchFrom = ParamUtils.getString(request, "searchFrom");
		if("menu".equals(searchFrom)) {//菜单查询，默认账单起始时间：所带月为当前月
			String pbMonth = DateUtils.getCurrentDateStr("yyyy-MM");
			paramMap.put("pbMonth", pbMonth);
			request.setAttribute("pbMonth", pbMonth);
		}

		handleListOrSearch(request, paramMap,isRevenue);

		logger.info("payBill list fetch data use time: " + (System.currentTimeMillis()-startTime));

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("gbId",  ParamUtils.getString(request,"gbId"));
		modelAndView.setViewName(getPayBillListPage());
		return modelAndView;
	}
	
	protected void parseRevenueParam(HttpServletRequest request, Map<String, Object> paramMap){
		return;
	}
	
	protected ModelAndView listBase(HttpServletRequest request,String formUrl){
		boolean isRevenue = false;
		return listBase(request, formUrl, isRevenue);
	}
	protected String getPayBillListPage(){
		return "/payBill/payBillList";
	}
	
	protected ModelAndView listBase(HttpServletRequest request,String formUrl,boolean isRevenue){
		request.setAttribute("formUrl", formUrl);
		request.setAttribute("isRevenue", isRevenue);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		handleListOrSearch(request, paramMap,isRevenue);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getPayBillListPage());
		return modelAndView;
	}
	
	/**
	 * 统一处理List和Search请求
	 * 
	 * @param request
	 * @param paramMap
	 *            请求参数
	 */
	private void handleListOrSearch(HttpServletRequest request, Map<String, Object> paramMap,boolean isRevenue) {
		paramMap.put("omsUserId", UserContext.getOperId());
		paramMap.put("omsUserName", UserContext.getCurrUser().getRealName());
		paramMap.put("isadmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isBusiness", this.isBusiness());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		paramMap.put("isRevenue", isRevenue);//syl-add-是否返回提款状态信息
		
		paramMap.put("bName",  ParamUtils.getString(request,"bName"));
		paramMap.put("unitName",  ParamUtils.getString(request,"unitName"));
		paramMap.put("roomNum",  ParamUtils.getString(request,"roomNum"));
		paramMap.put("billTypeName",  ParamUtils.getString(request,"billTypeName"));
		if(isRevenue){
			parseRevenueParam(request, paramMap);
		}
		{ 
			paramMap.put("buyerId",  ParamUtils.getString(request,"buyerId"));//缴费解放号
			paramMap.put("paytimeType", PlotpropertyDict.PayBillType_PaytimeType.MONTH); //物业月度缴费
		}
		int resultSize = payBillService.getPayBill_byUserId_forCount_Revenue(paramMap);
		request.setAttribute("resultSize", resultSize);

		PageUtils.addPageInfoToParam(request, paramMap);
		
		//存储查询条件，导出数据时要用到，见exportPayBill()方法
		request.getSession().setAttribute("payBillListQueryParamMap", ((HashMap<String, Object>) paramMap).clone());
		List<PayBillEntity> searchRestList = payBillService.getPayBillList_byUserId_forRevenuePage(paramMap);
		if(isRevenue){
			PayBillTotalData totalData = payBillService.getTotalData(paramMap);
			request.setAttribute("totalData", totalData);
		} else {
			getGbToRequest(request);
		}
		request.setAttribute("resList", searchRestList);
		request.setAttribute("pageType",  ParamUtils.getString(request,"pageType"));
	}

	protected void getGbToRequest(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("adminId", CnfantasiaCommUtil.getCurrentUserId());
		paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		IGroupBuildingService groupBuildingService = (IGroupBuildingService)CnfantasiaCommUtil.getBeanManager("msGroupBuildingService");
		List<GroupBuildingSimpleEntity> list = groupBuildingService.selectGroupBuildingForDialogList(paramMap);
		request.setAttribute("gbList", list);
	}
	
	/**
	 * 判断当前用户是否是商务后台角色
	 * */
	protected int isBusiness(){
		int flag = 0;
		BigInteger businessRole = CnfantasiaCommUtil.getRoleIdByRoleCode(OmsPermiRoleConstant.Sys_Role_Business);
		if(null!=businessRole){
			List<OmsUserHasTOmsPermiRole> roles = CnfantasiaCommUtil.getCurrentUserRoles();
			for (OmsUserHasTOmsPermiRole role : roles) {
				if(role.gettOmsPermiRoleFId().equals(businessRole)){
					flag = 1;
					break;
				}
			}
		}
		return flag;
	}
	
	protected List<PayBillEntity> fetchExportPayBillList(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> paramMap = (HashMap<String, Object>) request.getSession().getAttribute("payBillListQueryParamMap");
		boolean isRevenue = ParamUtils.getBoolean(request, "isRevenue", false);//syl-add
		{
			paramMap.put("isRevenue", isRevenue);//syl-add
			request.setAttribute("isRevenue", isRevenue);
		}
		
		//int resultSize = payBillService.getPayBill_byUserId_forCount(paramMap);
		paramMap.remove(PageModel.BEGIN);
		paramMap.remove(PageModel.LENGTH);
		List<PayBillEntity> searchRestList = payBillService.getPayBillList_byUserId_forRevenuePage(paramMap);
		return searchRestList;
	}
	protected List<PayBillWithFeeDetailEntity> fetchExportList(HttpServletRequest request) {
		boolean isRevenue = ParamUtils.getBoolean(request, "isRevenue", false);//syl-add
		List<PayBillEntity> searchRestList = fetchExportPayBillList(request);
		List<BigInteger> payBillIdList = new ArrayList<BigInteger>();
		for (int i = 0; i < searchRestList.size(); i++) {
			payBillIdList.add(searchRestList.get(i).getId());
		}
		HashMap<String, Object> paramMap = (HashMap<String, Object>) request.getSession().getAttribute("payBillListQueryParamMap");
		List<PayBillWithFeeDetailEntity> payBillImportedEntyList = payBillService.getExportedPayBillFroReven(payBillIdList,isRevenue,paramMap);
		return payBillImportedEntyList;
	}
	
	
	public String getExportExcelModelPath(){
		return "/docs/jfq_payBill_bank_statement.xls";
	}
	public void rowCreateCell(HSSFRow row,int userPayColIndex,PayBillWithFeeDetailEntity pb){}
	public ModelAndView exportPayBillBase(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<PayBillWithFeeDetailEntity> payBillImportedEntyList = fetchExportList(request);
		String resultMsg = payBillImportedEntyList.size() == 0 ? "没有可导出的数据" : "导出成功";
		if (payBillImportedEntyList.size() > 0) {
			String filePath = request.getSession().getServletContext().getRealPath(getExportExcelModelPath());
			FileInputStream fin = new FileInputStream(new File(filePath));
			HSSFWorkbook wb = new HSSFWorkbook(fin);
			HSSFSheet sheet = wb.getSheetAt(0);
			int dataFromRowIndex = 4; //从第5行开始是数据行
			int detailNameRowIndex = 2;//详细费用名称行, 即第3行
			for (int i = 0; i < payBillImportedEntyList.size(); i++) {
				PayBillWithFeeDetailEntity pb = payBillImportedEntyList.get(i);
				HSSFRow row = sheet.getRow(i + dataFromRowIndex);
				if (row == null) {
					row = sheet.createRow(i + dataFromRowIndex);
				}
				row.createCell(0).setCellValue(pb.getMonth()); // 月份	
				row.createCell(1).setCellValue(pb.getGroupBuildingName());//小区	
				row.createCell(2).setCellValue(pb.getContractNum());//原合同号	
				row.createCell(3).setCellValue(pb.getBuildingName());//楼栋
				row.createCell(4).setCellValue(pb.getRealRoomUnitName());//单元	
				row.createCell(5).setCellValue(pb.getRealRoomNum());//房号	
				row.createCell(6).setCellValue(pb.getPropertyProprietorName());//业主姓名

				HSSFRow detailNameRow = sheet.getRow(detailNameRowIndex);//详细费用名称行
				for (int j = 0; j < pb.getPropertyFeeDetails().size(); j++) {
					PropertyFeeDetail detail = pb.getPropertyFeeDetails().get(j);
					if (j == 0) {//管理费
						detailNameRow.getCell(7).setCellValue(detail.getName());
						if (detail.getTotalPrice() != null)
							row.createCell(7).setCellValue(detail.getTotalPrice() / 100.0);//分项总价
						if (detail.getSignalPrice() != null)
							row.createCell(8).setCellValue(detail.getSignalPrice());//分项单价
						if (detail.getPriceUnitValue() != null)
							row.createCell(9).setCellValue(detail.getPriceUnitValue()/100.0);//分项用量
						row.createCell(10).setCellValue("");//折扣
						row.createCell(11).setCellValue("");//优惠金额
					} else {
						int offset = detail.getType() - 1;

						detailNameRow.getCell(9 + offset * 3).setCellValue(detail.getName());
						if (detail.getTotalPrice() != null)
							row.createCell(9 + offset * 3).setCellValue(detail.getTotalPrice() / 100.0);//分项总价
						if (detail.getSignalPrice() != null)
							row.createCell(10 + offset * 3).setCellValue(detail.getSignalPrice());//分项单价
						if (detail.getPriceUnitValue() != null)
							row.createCell(11 + offset * 3).setCellValue(detail.getPriceUnitValue()/100.0);//分项用量
					}
				}
				//是否有物业宝抵扣
				boolean isFinanceDedu = pb.getFinanceStatus() != null && pb.getFinanceStatus() == 1;
				//是否部分已缴
				boolean isPartPay = (isFinanceDedu && pb.getDeduPrice() != null && pb.getIsPay() != null && pb.getIsPay() == 2);
				
				//第37列是“用户缴费”列
				int userPayColIndex = 36;
				//解放区补贴
				if(isPartPay) {//物业宝抵扣,部分已缴
					row.createCell(userPayColIndex).setCellValue("");
					row.createCell(userPayColIndex + 1).setCellValue("");
				} else {
					if (pb.getSuccPayAmount() != null) {//用户实缴
						row.createCell(userPayColIndex).setCellValue(pb.getSuccPayAmount() / 100.0);
					}
					long subsidyAmt = pb.getAmount() - NumberUtils.getLongValue(pb.getSuccPayAmount()) - NumberUtils.getLongValue(pb.getDeduPrice());
					if(subsidyAmt>0){
						row.createCell(userPayColIndex + 1).setCellValue(subsidyAmt / 100.0);
					}
				}
				
				if(isFinanceDedu && pb.getDeduPrice()!=null){//物业宝抵扣
					row.createCell(userPayColIndex + 2).setCellValue(pb.getDeduPrice() / 100.0);
				}
				userPayColIndex++;
				
				if(isPartPay) {//物业宝抵扣,部分已缴
					row.createCell(userPayColIndex + 3).setCellValue("");
					row.createCell(userPayColIndex + 2).setCellValue(pb.getDeduPrice() / 100.0);
				} else if ((pb.getIsPay() != null && pb.getIsPay() == 1) || isFinanceDedu) {
					row.createCell(userPayColIndex + 3).setCellValue(pb.getPayTime());
					row.createCell(userPayColIndex + 2).setCellValue(pb.getAmount() / 100.0);
				}
				if(pb.getPayPeriodStart() != null) {
					row.createCell(userPayColIndex + 4).setCellValue(pb.getPayPeriodStart());
				}
				if(pb.getPayPeriodEnd() != null) {
					row.createCell(userPayColIndex + 5).setCellValue(pb.getPayPeriodEnd());
				}
				if(pb.getOrderNo() != null) {
					row.createCell(userPayColIndex + 6).setCellValue(pb.getOrderNo());
				}
				if(pb.getPayMethod() == 0) {
					if(pb.getPaymentWay() != null && pb.getPaymentWay() == PayBillDict.PaymentWay.Marked_Manual){
						row.createCell(userPayColIndex + 7).setCellValue("物业手工标记");
					}else if(pb.getPaymentWay() != null && pb.getPaymentWay() == PayBillDict.PaymentWay.Property_Card_Deduction){
						row.createCell(userPayColIndex + 7).setCellValue("代扣卡续费");
					}else if(pb.getPaymentWay() != null && pb.getPaymentWay() == PayBillDict.PaymentWay.BANK_COLLECTION){
						row.createCell(userPayColIndex + 7).setCellValue("银行托收");
					}else if(isFinanceDedu) {
						row.createCell(userPayColIndex + 7).setCellValue("物业宝");
					} 
				} else {
					row.createCell(userPayColIndex + 7).setCellValue(getPayMethodString(pb.getPayMethod()));
				}
				rowCreateCell(detailNameRow, userPayColIndex, pb);
			}
			// 生成提示信息
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String fileName = "月度账单对账报表" + format.format(new Date());
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
			wb.write(response.getOutputStream());
		}

		request.setAttribute(JSPConstants.OprtResult, resultMsg);
		request.setAttribute(JSPConstants.ToURL, "../payBill/search.html");
		return new ModelAndView(JSPConstants.OprtSuccessPage);
	}
	
	
	
	/**
	 * 返回支付方式 <br>
	 * 支付方式=={"1":"微信支付","2":"支付宝","3":"银联支付","4":"纯粮票支付","5":"纯积分支付","6":
	 * "微信轻应用支付"}
	 * 
	 * @param pb
	 * @return
	 */
	protected String getPayMethodString(int payMethod) {
		String payMethodString = "";
		switch (payMethod) {
		case 1:
			payMethodString = "微信支付";
			break;
		case 2:
			payMethodString = "支付宝";
			break;
		case 3:
			payMethodString = "银联支付";
			break;
		case 4:
			payMethodString = "纯粮票支付";
			break;
		case 5:
			payMethodString = "纯积分支付";
			break;
		case 6:
			payMethodString = "微信轻应用支付";
			break;
		case 7:
			payMethodString = "纯优惠券支付";
			break;
		case 8:
			payMethodString = "纯折扣支付";
			break;
		case 9:
			payMethodString = "双乾支付";
			break;
		default:
			break;
		}
		return payMethodString;
	}
	
}
