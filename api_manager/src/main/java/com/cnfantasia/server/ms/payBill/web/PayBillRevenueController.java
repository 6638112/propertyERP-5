package com.cnfantasia.server.ms.payBill.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.propertyManagementHasOmsUser.entity.PropertyManagementHasOmsUser;
import com.cnfantasia.server.domainbase.propertyManagementHasOmsUser.service.IPropertyManagementHasOmsUserBaseService;
import com.cnfantasia.server.ms.payBill.entity.PayBillEntity;
import com.cnfantasia.server.ms.payBill.entity.PayBillWithFeeDetailEntity;
import com.cnfantasia.server.ms.payBill.service.IPayBillService;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;

@Controller
@RequestMapping("/payBill")
public class PayBillRevenueController extends AbstractPayBillController{
	@Resource
	private IRevenueService revenueService;
	@Resource
	private IPayBillService payBillService;
	/**
	 * 物业账单列表-包含收益
	 * @param request
	 * @return
	 */
	@RequestMapping("/listRevenue.html")
	public ModelAndView listRevenue(HttpServletRequest request) {
		String formUrl = "/payBill/searchRevenue.html";
		return listBase(request, formUrl,true);
	}
	
	/**
	 * 查找
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchRevenue.html")
	public ModelAndView searchRevenue(HttpServletRequest request) {
		String formUrl = "/payBill/searchRevenue.html";
		return searchBase(request, formUrl,true);
	}
	
	@Override
	protected void insertParamOrAttribute(HttpServletRequest request, Map<String, Object> paramMap) {
		super.insertParamOrAttribute(request, paramMap);
		if(request.getAttribute("paymentWay")!=null){
			paramMap.put("paymentWay", request.getAttribute("paymentWay"));
		}
		if(request.getAttribute("isPay")!=null){
			paramMap.put("isPay", request.getAttribute("isPay"));
		}
	}

	private ModelAndView doSearchBase(HttpServletRequest request,String formUrl,boolean isRevenue,Integer roleType,Integer projectType){
		request.setAttribute("roleType", roleType);
		request.setAttribute("projectType", projectType);//默认查询物业费实收
		request.setAttribute("qryAll", true);
		return super.searchBase(request, formUrl, isRevenue);
	}
	
	/**以下为新增的url,需要配置权限*/
	
	/**
	 * 物业费实收明细列表， 管理员查询
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchRevenueAdmin.html")
	public ModelAndView searchRevenueAdmin(HttpServletRequest request) {
		request.setAttribute("isPay", 1);
		request.setAttribute("paymentWay", 1);
		String formUrl = "/payBill/searchRevenueAdmin.html";
		return doSearchBase(request, formUrl,true,RevenueDict.MiniRoleType.SysAdmin,RevenueDict.RevenueProject.PropertyRealPayAmout);
	}
	
	/**
	 * 物业费实收明细列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchRevenueFinance.html")
	public ModelAndView searchRevenueFinance(HttpServletRequest request) {
		request.setAttribute("isPay", 1);
		request.setAttribute("paymentWay", 1);
		String formUrl = "/payBill/searchRevenueFinance.html";
		return doSearchBase(request, formUrl,true,RevenueDict.MiniRoleType.Financer,RevenueDict.RevenueProject.PropertyRealPayAmout);//默认查询物业费实收
	}
	/**
	 * 物业费实收明细列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchRevenueCompany.html")
	public ModelAndView searchRevenueCompany(HttpServletRequest request) {
		request.setAttribute("isPay", 1);
		request.setAttribute("paymentWay", 1);
		String formUrl = "/payBill/searchRevenueCompany.html";
		return doSearchBase(request, formUrl,true,RevenueDict.MiniRoleType.PropertyCompany,RevenueDict.RevenueProject.PropertyRealPayAmout);//默认查询物业费实收
	}
	
	@Resource
	IPropertyManagementHasOmsUserBaseService propertyManagementHasOmsUserBaseService;
	/**
	 * 物业费实收明细列表, 管理处查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchRevenuePCManagement.html")
	public ModelAndView searchRevenuePCManagement(HttpServletRequest request) {
		request.setAttribute("isPay", 1);
		request.setAttribute("paymentWay", 1);
		
		setPropertyManagementId(request);
		
		String formUrl = "/payBill/searchRevenuePCManagement.html";
		return doSearchBase(request, formUrl, true, RevenueDict.MiniRoleType.PCManagement, RevenueDict.RevenueProject.PropertyRealPayAmout);
	}

	private void setPropertyManagementId(HttpServletRequest request) {
		PropertyManagementHasOmsUser propertyManagementHasOmsUserQry = new PropertyManagementHasOmsUser();
		propertyManagementHasOmsUserQry.settOmsUserFId(UserContext.getOperIdBigIntegerMustExist());
		List<PropertyManagementHasOmsUser> pmHasUserList = propertyManagementHasOmsUserBaseService.getPropertyManagementHasOmsUserByCondition(MapConverter.toMap(propertyManagementHasOmsUserQry));
		if(pmHasUserList.size() > 0){
			request.setAttribute("roleId", pmHasUserList.get(0).gettPropertyManagementFId());
		}
	}
	
	
	/**
	 * 对账单导出
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/exportPayBillRevenue.html")
	public void exportPayBillRevenue(HttpServletRequest request, HttpServletResponse response)/* throws IOException */{
		request.setAttribute("roleType", RevenueDict.MiniRoleType.SysAdmin);
		request.setAttribute("isPay", 1);
		request.setAttribute("paymentWay", 1);
		request.setAttribute("projectType", RevenueDict.RevenueProject.PropertyRealPayAmout);//默认查询物业费实收
		request.setAttribute("qryAll", true);
//		return super.exportPayBillBase(request, response);
		
		OutputStream fOut = null;
		InputStream fin = null;
		try {
			request.setAttribute("isRevenue", true);
			List<PayBillWithFeeDetailEntity> payBillImportedEntyList = payBillService.fetchExportListWy(request);
			HSSFWorkbook workbook = payBillService.createRport(payBillImportedEntyList);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String fileName = "物业代收费收益"+format.format(new Date());
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("content-disposition", "attachment;filename=" +new String(fileName.getBytes(), "iso8859-1") + ".xls");
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
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
	
//	public static String toUtf8String(String s) {
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < s.length(); i++) {
//			char c = s.charAt(i);
//			if (c >= 0 && c <= 255) {
//				sb.append(c);
//			} else {
//				byte[] b;
//				try {
//					b = Character.toString(c).getBytes("utf-8");
//				} catch (Exception ex) {
//					System.out.println(ex);
//					b = new byte[0];
//				}
//				for (int j = 0; j < b.length; j++) {
//					int k = b[j];
//					if (k < 0)
//						k += 256;
//					sb.append("%" + Integer.toHexString(k).toUpperCase());
//				}
//			}
//		}
//		return sb.toString();
//	}
	
	@Override
	public void parseRevenueParam(HttpServletRequest request, Map<String, Object> paramMap){
		Integer tkStatus_wy = ParamUtils.getInteger(request, "tkStatus_wy", null);
		BigInteger applyId = ParamUtils.getBigInteger(request, "applyId", null);
		BigInteger roleId = ParamUtils.getBigInteger(request, "roleId", null);
		if(roleId == null){
			roleId = (BigInteger) request.getAttribute("roleId");
		}
		Integer projectType = ParamUtils.getInteger(request, "projectType", null);
		paramMap.put("tkStatus_wy", tkStatus_wy);
		paramMap.put("applyId", applyId);
		paramMap.put("roleId", roleId);//角色Id的校验
		paramMap.put("projectType", projectType);
		
		Integer roleType = ParamUtils.getInteger(request, "roleType", 0);
		if(roleType == 13) {
			paramMap.put("roleType", roleType);
		}
		
		boolean qryAll = request.getAttribute("qryAll")==null?false:(Boolean)(request.getAttribute("qryAll"));
		paramMap.put("qryAll", qryAll);
		if(qryAll){
			//附加，for明细报表
			paramMap.put("roleType", request.getAttribute("roleType"));
			if(paramMap.get("projectType")==null){
				paramMap.put("projectType", request.getAttribute("projectType"));
			}
			if(roleId==null){
				BigInteger omsUserId = UserContext.getOperIdBigIntegerMustExist();
				paramMap.put("roleId",revenueService.getUserWithPropCompanyByUserId(omsUserId).getPropertyCompanyId() );
			}
		}
	}
	
	@Override
	public void rowCreateCell(HSSFRow row,int userPayColIndex,PayBillWithFeeDetailEntity pb){
		row.createCell(userPayColIndex + 8).setCellValue(getTkStatusWyStr(pb.getTkStatus_wy()));
		row.createCell(userPayColIndex + 9).setCellValue(pb.getPayFlowNo());//支付流水号 
	}
	
	private void trimMapString(Map<String, Object> tmpMap,String key){
		Object value = tmpMap.get(key);
		if(value!=null){
			tmpMap.put(key, ((String)value).trim());
		}
	}
	@Override
	public Map<String, Object> searchBaseInitMap(HttpServletRequest request){
//		Map<String, Object> tmpMap = super.searchBaseInitMap(request);
		String gbName = ParamUtils.getString(request,"gbName");
		String buyerId = ParamUtils.getString(request,"buyerId");
		String ppName = ParamUtils.getString(request,"ppName");
		String pbMonth = ParamUtils.getString(request,"pbMonth");
		
		String payTimeStart = ParamUtils.getString(request,"date01");
		String payTimeEnd = ParamUtils.getString(request,"date02");
		String pcName = ParamUtils.getString(request,"pcName");
		
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
		
//		Date now = new Date();
//		if(payTimeStart==null){
//			request.setAttribute("date01", DateUtils.convertDateToStr(DateUtils.decreateMonth(now, 3), "yyyy-MM-dd HH:mm"));
//			paramMap.put("payTimeStart",  DateUtils.convertDateToStr(DateUtils.decreateMonth(now, 3), "yyyy-MM-dd HH:mm"));
//		}else{
//			request.setAttribute("date01", payTimeStart);
//		}
//		if(payTimeEnd==null){
//			request.setAttribute("date02", DateUtils.convertDateToStr(now, "yyyy-MM-dd HH:mm"));
//			paramMap.put("payTimeEnd", DateUtils.convertDateToStr(now, "yyyy-MM-dd HH:mm"));
//		}else{
//			request.setAttribute("date02", payTimeEnd);
//		}
		
		insertParamOrAttribute(request, paramMap);
		
		trimMapString(paramMap, "gbName");
		trimMapString(paramMap, "contractNum");
		trimMapString(paramMap, "ppName");
		trimMapString(paramMap, "pcName");
		
		paramMap.put("billTypeName", ParamUtils.getString(request,"billTypeName"));//增加费用类型名称
		trimMapString(paramMap, "billTypeName");
		return paramMap;
	}
	
	/**
	 * 物业提款状态
	 */
	public String getTkStatusWyStr(Integer tkStatus){
		String payMethodString = "";
		if(tkStatus==null){return payMethodString;}
		switch (tkStatus) {
		case -1:
			payMethodString = "未处理";
			break;
		case 1:
			payMethodString = "未结算";
			break;
		case 2:
			payMethodString = "申请中";
			break;
		case 3:
			payMethodString = "已结算";
			break;
		default:
			break;
		}
		return payMethodString;
	}
	
	protected String getPayBillListPage(){
		return "/payBill/payBillListRevenue";
	}
	
//	private short setColumnWidth(HSSFSheet sheet,int columnIndex){
//		
//	}
	public HSSFWorkbook createReport(List<PayBillEntity> orderExportList) {
		String[] titles = {"物业公司","小区名称","缴费解放号","费用名称","账单月份","楼栋号","单元","房间号","账单金额(元)","使用折扣"
				,"实缴额","补贴额","业主姓名","账单状态","缴费时间","支付渠道","支付流水号","结算状态"};
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("物业代收费明细");
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
		short width = 4000;style.setFillBackgroundColor(height);
		// 创建Excel的sheet的一行
		HSSFRow row = sheet.createRow(0);
		// 设定行的高度
		row.setHeight(height);
		for (int i = 0; i < titles.length; i++) {
			// 设置excel每列宽度
			if(i==0 || i==4 || i==14|| i==16){
				sheet.setColumnWidth(i, width+width);
			}else{
				sheet.setColumnWidth(i, width);
			}
			HSSFCell cell = row.createCell(i);
			HSSFCellStyle styleHead = wb.createCellStyle();
			styleHead.cloneStyleFrom(style);
			styleHead.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
			styleHead.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(styleHead);
			cell.setCellValue(titles[i]);
		}
		if(orderExportList != null) {
			for (int i = 0, j = 0; j < orderExportList.size(); j++) {
				row = sheet.createRow((j+1));
				// 设置excel每列宽度
//				sheet.setColumnWidth(j, width);
				PayBillEntity bean = orderExportList.get(j);
				i = 0;
				fillCellData(row, i++, bean.getPcName(),style);
				fillCellData(row, i++, bean.getGroupBuildingName(),style);
				fillCellData(row, i++, (bean.getBuyerId()>0&&bean.getIsPay()!=null&&bean.getIsPay()==1)?(bean.getBuyerId()+""):"",style);
				fillCellData(row, i++, bean.getBillTypeName(),style);
				fillCellData(row, i++, StringUtils.isEmpty(bean.getMonth())?(bean.getBillMonthStart()+"至"+bean.getBillMonthEnd()):bean.getMonth(),style);
				fillCellData(row, i++, bean.getBuildingName(),style);
				fillCellData(row, i++, bean.getRealRoomUnitName()==null?"":bean.getRealRoomUnitName(),style);
				fillCellData(row, i++, bean.getRealRoomNum(),style);
				fillCellData(row, i++, PriceUtil.div100(bean.getAmount())+"",style);
				fillCellData(row, i++, bean.getDiscount()==null?"":bean.getDiscount()+"",style);
				fillCellData(row, i++, PriceUtil.div100(bean.getSuccPayAmount())+"",style);
				fillCellData(row, i++, PriceUtil.div100(bean.getAmount()-bean.getSuccPayAmount())+"",style);
				fillCellData(row, i++, bean.getPropertyProprietorName(),style);
				fillCellData(row, i++, (bean.getIsPay()!=null&&bean.getIsPay()==1)?"已缴":"未缴",style);
				fillCellData(row, i++, (bean.getIsPay()!=null&&bean.getIsPay()==1)?bean.getSys0UpdTime():"",style);
				fillCellData(row, i++, getPayMethodString(bean.getPayMethod()),style);
				fillCellData(row, i++, bean.getPayFlowNo(),style);
				fillCellData(row, i++, getTkStatusWyStr(bean.getTkStatus_wy()),style);
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
