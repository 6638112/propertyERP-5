package com.cnfantasia.server.ms.cloudkey.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.cloudKeyUserData.service.ICloudKeyUserDataService;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ExcelUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.buildingKeyList.entity.BuildingKeyList;
import com.cnfantasia.server.domainbase.buildingKeyList.service.IBuildingKeyListBaseService;
import com.cnfantasia.server.domainbase.cloudKeyUserAudit.entity.CloudKeyUserAudit;
import com.cnfantasia.server.domainbase.cloudKeyUserAudit.service.ICloudKeyUserAuditBaseService;
import com.cnfantasia.server.domainbase.cloudKeyUserData.entity.CloudKeyUserData;
import com.cnfantasia.server.domainbase.cloudKeyUserList.entity.CloudKeyUserList;
import com.cnfantasia.server.domainbase.cloudKeyUserList.service.ICloudKeyUserListBaseService;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.omsUser.service.IOmsUserBaseService;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.domainbase.propertyCompany.service.IPropertyCompanyBaseService;
import com.cnfantasia.server.domainbase.propertyManagementHasOmsUser.service.IPropertyManagementHasOmsUserBaseService;
import com.cnfantasia.server.ms.cloudkey.entity.CarPayLogList;
import com.cnfantasia.server.ms.cloudkey.entity.CloudKeyAudit;
import com.cnfantasia.server.ms.cloudkey.service.ICloudKeyService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;

/**
 * 云钥匙
 * @author ligs
 *
 */
@Controller
@RequestMapping("/cloudkey")
public class CloudkeyController extends BaseController{
	@Resource
	private ICloudKeyService cloudKeyService;
	
	@Resource
	private ICloudKeyUserAuditBaseService cloudKeyUserAuditBaseService;
	
	@Resource
	private IBuildingKeyListBaseService  buildingKeyListBaseService;
	
	@Resource
	private ICloudKeyUserListBaseService  cloudKeyUserListBaseService;
	
    @Resource
    private IUuidManager uuidManager;
    
    @Resource
    private IPropertyManagementHasOmsUserBaseService propertyManagementHasOmsUserBaseService;
	
    @Resource
    private IOmsUserBaseService  omsUserBaseService;
    
    @Resource(name="cloudKeyUserDataService")
	private ICloudKeyUserDataService cloudKeyUserDataService;
    
    @Resource
    private IPropertyCompanyBaseService propertyCompanyBaseService;
    
    @Resource
	private IRevenueService revenueService;
    
	@RequestMapping("/buildingKeyList.html")
	public String buildingKeyListaudit(HttpServletRequest request,ModelMap model){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		handleListOrSearch(request,paramMap);
		return "/cloudkey/buildingKeyList";
	}
	
	@RequestMapping("/search.html")
	public String search(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		BigInteger status = ParamUtils.getBigInteger(request, "status", null);
		String buildingname = ParamUtils.getString(request, "buildingname", null);
		String unitName = ParamUtils.getString(request, "unitName", null);
		BigInteger huaId= ParamUtils.getBigInteger(request, "huaId", null);
		String createTime_START = request.getParameter("startTime");
		String createTime_END = request.getParameter("endTime");
		paramMap.put("status", status.intValue() == -1 ? null: status);
		paramMap.put("buildingname", buildingname);
		paramMap.put("unitName", unitName);
		paramMap.put("huaId", huaId);
		paramMap.put("sys0AddTime_START", createTime_START);
		paramMap.put("sys0AddTime_END", createTime_END);
		handleListOrSearch(request,paramMap);
		return "/cloudkey/buildingKeyList";
	}
	
	@RequestMapping("/buildingKeyAudit.html")
	@ResponseBody
	public JsonResponse cloudkeyAudit(HttpServletRequest request){ 
		JsonResponse jsonResponse = new JsonResponse();
		String nowTime = CnfantasiaCommUtil.getCurrentTime();
		BigInteger cloudId = ParamUtils.getBigInteger(request, "cloudId", null);
		String auditResult = ParamUtils.getString(request, "auditResult", null);
		String auditReason = ParamUtils.getString(request, "auditReason",null);
		if(cloudId != null){
			CloudKeyUserAudit userAudit = cloudKeyUserAuditBaseService.getCloudKeyUserAuditBySeqId(cloudId);
			if(userAudit!=null){
				// 查询钥匙是否存在
				Map<String, Object> keyMap = new HashMap<String, Object>();
				keyMap.put("tBuildingFId", userAudit.gettBuildingFId());
				keyMap.put("tGroupBuildingFId", userAudit.gettGroupBuildingFId());
				int keyNum = buildingKeyListBaseService.getBuildingKeyListCount(keyMap);
				if(keyNum <= 0){
					jsonResponse.setStatus("0001");
					jsonResponse.setMessage("该楼栋暂时还没有配置钥匙，审核不能操作，请联系管理员！");
				} else {
					userAudit.setId(cloudId);
					Integer status = auditResult.equals("pass")?1:2;
					userAudit.setStatus(status);
					if(status==2){
						userAudit.setAuditReason(auditReason);
					}
					userAudit.setSys0UpdTime(nowTime);
					userAudit.setAuditTime(nowTime);
					String endTime = cloudKeyService.qryuserEndTime(userAudit.getHuaId());
					userAudit.setSys0DelTime(endTime);
					//查询该楼栋密钥
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("tBuildingFId", userAudit.gettBuildingFId());
					paramMap.put("sys0DelState", 0);
					List<BuildingKeyList> keyList = buildingKeyListBaseService.getBuildingKeyListByCondition(paramMap);
					if(keyList !=null && keyList.size()>0){
						//密钥表添加数据
						List<CloudKeyUserList> userkeyList = new ArrayList<CloudKeyUserList>();
						List<BigInteger> idList = uuidManager.getNextUuidBigInteger(SEQConstants.t_cloud_key_user_list, keyList.size());
						for(int i=0;i< keyList.size();i++){
							CloudKeyUserList userKey = new CloudKeyUserList();
							userKey.setId(idList.get(i));
							userKey.setHuaId(userAudit.getHuaId());
							userKey.setPayStatus(0);
							userKey.setSys0AddTime(nowTime);
							userKey.setStatus(0);
							userKey.settBuildingKeyFId(keyList.get(i).getId());
							userKey.settGroupBuildingFId(userAudit.gettGroupBuildingFId());
							userKey.settBuildingFId(userAudit.gettBuildingFId());
							userKey.setSys0DelState(0);
							userkeyList.add(userKey);
						}
						cloudKeyUserListBaseService.insertCloudKeyUserListBatch(userkeyList);
					}
					cloudKeyUserAuditBaseService.updateCloudKeyUserAudit(userAudit);
					jsonResponse.setStatus("0000");
				}
			}else{
				jsonResponse.setStatus("0001");
				jsonResponse.setMessage("没有该id的审核信息！！");
			}
		}else{
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("id号为空，请选择！！");
		}
		return jsonResponse;
	}
	
	/**
	 * 根据auditUserId查询门禁认证审核信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/buildingKeyDetail.html")
	public ModelAndView auditOrDetail(HttpServletRequest request){
		BigInteger auditId = ParamUtils.getBigInteger(request, "auditUserId", null);
		ModelAndView modelAndView = new ModelAndView();
		if(auditId!=null){
			CloudKeyAudit cloudKeyAudit = cloudKeyService.qryCloudKeyAuditDetail(auditId);
			List<CloudKeyUserData> cloudKeyUserDatas = cloudKeyUserDataService.queryCloudKeyUserDataByCondition(auditId);
			modelAndView.addObject("cloudKeyAudit", cloudKeyAudit);
			modelAndView.addObject("cloudKeyUserDatas", cloudKeyUserDatas);
			if(cloudKeyAudit.getStatus()==0){
				modelAndView.setViewName("/cloudkey/cloudKeyUserAudit");
			}else{
				modelAndView.setViewName("/cloudkey/cloudKeyuserDetail");
			}
		}
		return modelAndView;
	}
	
	private void handleListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		paramMap.put("gbIdList", UserContext.getGbIdList());
		int resultSize = cloudKeyService.qryCloudKeyAuditcount(paramMap);
		request.setAttribute("resultSize", resultSize);
		PageUtils.addPageInfoToParam(request, paramMap);
		List<CloudKeyAudit> resList = cloudKeyService.qryCloudKeyAuditList(paramMap);
		request.setAttribute("resList", resList);
	}
	
	@RequestMapping("/parkingRateList.html")
	public String carsPayLogList(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		BigInteger userid = CnfantasiaCommUtil.getCurrentUserId();
		OmsUser user = omsUserBaseService.getOmsUserBySeqId(userid);
		paramMap.put("adminId", userid);
		List<PropertyCompany> pcList = propertyCompanyBaseService.getPropertyCompanyByCondition(paramMap);
		paramMap.clear();
		paramMap.put("gbIdList", UserContext.getGbIdList());
		if((UserContext.getGbIdList()!=null && UserContext.getGbIdList().size() >0) || user.getIsadmin() ==1){
			if(user.getIsadmin() ==1){
				carhandleListOrSearch(request,paramMap);
				return "/cloudkey/parkingRateList";
			}else {//车禁合作物业
				if(pcList.size() > 0) {
					request.setAttribute("pcName", pcList.get(0).getName());
				}
				carhandleListOrSearch(request,paramMap);
				return "/cloudkey/parkingRateList";
			}
		}else{
			request.setAttribute(JSPConstants.OprtResult, "跳转失败，您当前角色没有车禁角色信息!");
			request.setAttribute(JSPConstants.ToURL, "../security/welcome.html");
			return JSPConstants.OprtSuccessPage;
		}
	}
	
	private void carhandleListOrSearch(HttpServletRequest request, Map<String, Object> paramMap){
		initCarBMap(paramMap, request);// 设置停车宝抵扣查询参数
		
		int resultSize = cloudKeyService.qryCarPayLogCount(paramMap);
		request.setAttribute("resultSize", resultSize);
		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		
		List<CarPayLogList> resList = cloudKeyService.qryCarPayLog(paramMap);
		Long totalMoney = cloudKeyService.qryCarTotalPay(paramMap);
		
		request.setAttribute("totalMoney", totalMoney);
		request.setAttribute("resList", resList);
	}
	
	/**
	 * 设置停车宝抵扣查询参数
	 * 
	 * @param paramMap
	 */
	private void initCarBMap(Map<String, Object> paramMap, HttpServletRequest request){
		paramMap.put("carNum", request.getParameter("carNum"));
		paramMap.put("gbName", request.getParameter("gbName"));
		paramMap.put("parking", request.getParameter("parking"));
	}
	
	@RequestMapping("/paysearch.html")
	public String carSearch(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String pcName = ParamUtils.getString(request, "pcName",null);
		Integer carType = ParamUtils.getInteger(request, "carType", null);
		Integer payType = ParamUtils.getInteger(request, "payType", null);
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		
		paramMap.clear();
		paramMap.put("gbIdList", UserContext.getGbIdList());
		paramMap.put("pcName", pcName);
		paramMap.put("carType", carType);
		paramMap.put("payType", payType);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		carhandleListOrSearch(request,paramMap);
		return "/cloudkey/parkingRateList";
	}
	
	@RequestMapping("/exportpayList.html")
	public void expCarsearch(HttpServletRequest request,HttpServletResponse response){
		String pcName = ParamUtils.getString(request, "pcName",null);
		Integer carType = ParamUtils.getInteger(request, "carType", null);
		Integer payType = ParamUtils.getInteger(request, "payType", null);
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbIdList", UserContext.getGbIdList());
		paramMap.put("pcName", pcName);
		paramMap.put("carType", carType);
		paramMap.put("payType", payType);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		
		initCarBMap(paramMap, request);// 设置停车宝抵扣查询参数
		
		List<CarPayLogList> carExportList = cloudKeyService.qryCarPayLog(paramMap);
		String exportFileName = "停车费一览表";
		HSSFWorkbook workbook = createReportcar(carExportList, exportFileName);
		String now = DateTime.now().toString("yyyy.MM.dd.HH.mm.ss");
		ExcelUtil.commonExport(exportFileName + now, workbook, response);
	}
	
	
	@SuppressWarnings("deprecation")
	public HSSFWorkbook createReportcar(List<CarPayLogList> carExportList, String exportFileName) {
		String[] titles = {"物业公司", "停车场", "小区", "楼栋", "单元", "房间号", "车牌号", "用户名", "手机号", "缴费时间", "车辆类型", "缴费时长", "缴费金额(元)", "实缴(元)", "平台补贴(元)", "物业补贴(元)", "支付渠道", "支付详情", "是否需要发票", "支付流水号"};
		int[] cellWidth = {8000,    5000,  6000,  3000, 2000,  2000,  3000,   3000,  4000,   6000,    2400,    2400,    3400,      2500,     2500,       2500,       4000,   2500,    3500,      6500};
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet(exportFileName);
		// 创建字体样式
		HSSFFont titleFont = wb.createFont();
		titleFont.setFontName("微软雅黑");
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		
		HSSFFont contentFont = wb.createFont();
		contentFont.setFontName("微软雅黑");
		
		// 创建单元格样式
		HSSFCellStyle titleStyle = wb.createCellStyle();
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		titleStyle.setFont(titleFont);
		titleStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); 
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		
		HSSFCellStyle contentStyle = wb.createCellStyle();
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		contentStyle.setFont(contentFont);
		// 创建Excel的sheet的一行
		HSSFRow row = sheet.createRow(0);
		// 设定行的高度
		row.setHeight((short)400);
		for (int i = 0; i < titles.length; i++) {
			// 设置excel每列宽度
			sheet.setColumnWidth(i, cellWidth[i]);
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(titleStyle);
			cell.setCellValue(titles[i]);
		}
		
		if(carExportList != null) {
			int i;
			for (int j = 0; j < carExportList.size(); j++) {
				i = 0;
				row = sheet.createRow((j+1));
				row.setHeight((short)300);
				// 设置excel每列宽度
				sheet.setColumnWidth(i, cellWidth[i]);
				CarPayLogList order = carExportList.get(j);
				fillCellData(row, i++,order.getPcName(),contentStyle);
				fillCellData(row, i++,order.getParking(),contentStyle);
				fillCellData(row, i++,order.getGbName(),contentStyle);
				
				fillCellData(row, i++,order.getBuildingname(),contentStyle);
				fillCellData(row, i++,order.getUnitName(),contentStyle);
				fillCellData(row, i++,order.getRoomNum(),contentStyle);
				
				fillCellData(row, i++,order.getCarNum(),contentStyle);
				fillCellData(row, i++,order.getUserName(),contentStyle);
				fillCellData(row, i++,order.getPhoneNum(),contentStyle);
				fillCellData(row, i++,order.getPayTime(),contentStyle);
				switch(order.getCarType()){
				case 1:fillCellData(row, i++,"次缴车",contentStyle);
				break;
				case 2:fillCellData(row, i++,"月卡车",contentStyle);
				break;
				case 3:fillCellData(row, i++,"年卡车",contentStyle);
				break;
				}
				fillCellData(row, i++,order.getPayNum().toString()+"个月",contentStyle);
				fillCellData(row, i++,PriceUtil.div100(order.getPayMoney()).toString(),contentStyle);
				fillCellData(row, i++,PriceUtil.div100(order.getPayMoney()-order.getCouponAmount()-order.getWyCouponAmount()).toString(),contentStyle);
				fillCellData(row, i++,PriceUtil.div100(order.getCouponAmount()).toString(),contentStyle);
				fillCellData(row, i++,PriceUtil.div100(order.getWyCouponAmount()).toString(),contentStyle);
				fillCellData(row, i++,getPayType(order.getPayType()),contentStyle);
				fillCellData(row, i++,order.getPayMethod(),contentStyle);
				if(order.getNeedBill()!=null && order.getNeedBill().toString().equals("1")){
					fillCellData(row, i++,"需要",contentStyle);
				} else {
					fillCellData(row, i++,"",contentStyle);
				}
				
				fillCellData(row, i++,order.getPayFlown(),contentStyle);
			}	
		}
		return wb;
	}
	
	private String getPayType(int payType){
		if(payType==0){
			return "线上支付";
		} else if(payType==1){
			return "现金支付";
		}else if(payType==2){
			return "停车宝抵扣";
		}
		return "";
	}
	
	@SuppressWarnings("deprecation")
	private void fillCellData(HSSFRow row,int index,String value,HSSFCellStyle style){
		HSSFCell cell = row.createCell(index);
		cell.setCellStyle(style);
		cell.setCellValue(value);
	}
	
	private Map<String, Object> getRequestParams(HttpServletRequest request){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("carType", ParamUtils.getString(request, "carType1"));
		params.put("payType", ParamUtils.getString(request, "payType1"));
//		params.put("pcName", ParamUtils.getString(request, "pcName1"));
		params.put("startTime", request.getParameter("startTime1"));
		params.put("endTime", request.getParameter("endTime1"));
		return params;
	}
}
