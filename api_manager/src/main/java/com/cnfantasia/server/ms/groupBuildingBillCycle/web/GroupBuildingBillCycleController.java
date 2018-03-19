package com.cnfantasia.server.ms.groupBuildingBillCycle.web;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.groupBuildingCycleCfg.constant.CycleCfgDict;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.GroupBuildingBillCycleConfigEntity;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.GroupBuildingHasFeeItemEntity;
import com.cnfantasia.server.api.groupBuildingCycleCfg.service.GroupBuildingCycleCfgService;
import com.cnfantasia.server.api.lateFee.calculateMethod.CalculateLateFeeByGbImp;
import com.cnfantasia.server.api.lateFee.util.CalculateLateFeeRunnable;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyConstant;
import com.cnfantasia.server.api.plotproperty.service.FinanceService;
import com.cnfantasia.server.api.plotproperty.util.FinanceDeductionRunnable;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.commbusi.plotproperty.service.IPlotpropertyCfgService;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.ExcelUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.fixedFeeItem.entity.FixedFeeItem;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.entity.GroupBuildingCalculateLatefeeRule;
import com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.service.GroupBuildingCalculateLatefeeRuleBaseService;
import com.cnfantasia.server.domainbase.groupBuildingExt.entity.GroupBuildingExt;
import com.cnfantasia.server.domainbase.groupBuildingExt.service.GroupBuildingExtBaseService;
import com.cnfantasia.server.domainbase.mrFeeItem.entity.MrFeeItem;
import com.cnfantasia.server.domainbase.mrFeeItem.service.MrFeeItemBaseService;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.tmpFeeItem.entity.TmpFeeItem;
import com.cnfantasia.server.domainbase.tmpFeeItem.service.ITmpFeeItemBaseService;
import com.cnfantasia.server.ms.buildingRoom.entity.RoomEntity;
import com.cnfantasia.server.ms.buildingRoom.service.IBuildingRoomService;
import com.cnfantasia.server.ms.groupBuilding.service.IGroupBuildingService;
import com.cnfantasia.server.ms.groupBuildingBillCycle.dto.BillCycleParam;
import com.cnfantasia.server.ms.groupBuildingBillCycle.dto.BillEditParam;
import com.cnfantasia.server.ms.groupBuildingBillCycle.entity.GroupBuildingBillCycleEntity;
import com.cnfantasia.server.ms.groupBuildingBillCycle.service.IGroupBuildingBillCycleService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.payBill.service.IPayBillService;
import com.cnfantasia.server.ms.propertyPayConfig.entity.MrExportModelEntity;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.MapConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.FutureTask;

/**
 * @className: CalculateLateFeeRunnable
 * @date: 2016-08-06 15:28
 * @author: yanghua
 * @description:(账期管理)
 */
@Controller
@RequestMapping("/groupBuildingBillCycle")
public class GroupBuildingBillCycleController extends BaseController {
	@Resource
	private IGroupBuildingBillCycleService groupBuildingBillCycleService;
	@Resource
	private IGroupBuildingService msGroupBuildingService;
	@Resource
	private IPayBillService payBillService;
	@Resource
	private IPlotpropertyCfgService plotpropertyCfgService;
	@Resource
	private IGroupBuildingService groupBuildingService;
	@Resource
	protected IBuildingRoomService buildingRoomService;
	@Resource
	private MrFeeItemBaseService mrFeeItemBaseService;
	@Resource
	protected FinanceService financeService;
	@Resource
	private GroupBuildingCycleCfgService groupBuildingCycleCfgService;
	@Resource
	private GroupBuildingExtBaseService groupBuildingExtBaseService;
	@Resource
	private GroupBuildingCalculateLatefeeRuleBaseService groupBuildingCalculateLatefeeRuleBaseService;

	private Log logger = LogFactory.getLog(this.getClass());

	/**
	 * 收费账期管理 列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/billCycleList.html")
	public ModelAndView list(BillCycleParam param, HttpServletRequest request) {
		String searchFrom = ParamUtils.getString(request, "searchFrom");
		//菜单查询，默认账单起始时间：所带月为当前月
		if("menu".equals(searchFrom)) {
			String currentDateStr = DateUtils.getCurrentDateStr("yyyy-MM");
			param.setBillCycleStart(currentDateStr);
			request.setAttribute("billCycleStart", currentDateStr);
		}
		Map<String, Object> paramMap = MapConverter.toMap(param);
		paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isSubUser", UserContext.getCurrUser().getIsSubUser());
		paramMap.put("adminId", UserContext.getCurrUser().getId());
		paramMap.put("userId", UserContext.getOperIdBigInteger());
		paramMap.put("feeType", ParamUtils.getInteger(request, "feeType", null));
		paramMap.put("gbId", ParamUtils.getBigInteger(request, "gbId", null));
		paramMap.put("gbIdList", UserContext.getGbIdList());

		handleListOrSearchBuilding(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		request.setAttribute("feeType", ParamUtils.getInteger(request, "feeType", null));
		request.setAttribute("gbId", ParamUtils.getBigInteger(request, "gbId", null));
		modelAndView.setViewName("/groupBuildingBillCycle/billCycleList");
		return modelAndView;
	}
	
	/**
	 * 临时收费列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/tmpBillList.html")
	public ModelAndView tmpBillList(BillCycleParam param, HttpServletRequest request) {

		Map<String, Object> paramMap = MapConverter.toMap(param);
		paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		paramMap.put("adminId", UserContext.getOperId());
		paramMap.put("userId", UserContext.getOperIdBigInteger());
		// 是否"临时收费子项"
		paramMap.put("isTmpBill", 1);

		handleListOrSearchBuilding(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/groupBuildingBillCycle/tmpBillList");
		return modelAndView;
	}
	
	/**
	 * 统一处理List和Search请求
	 * @param request
	 * @param paramMap
	 */
	private void handleListOrSearchBuilding(HttpServletRequest request, Map<String, Object> paramMap) {
		//总数量
		int resultSize = groupBuildingBillCycleService.queryBuildingForCount(paramMap);
		request.setAttribute("resultSize", resultSize);
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		//分页信息
		List<GroupBuildingBillCycleEntity> searchRestList = groupBuildingBillCycleService.queryBuildingForList(curPageIndex, pageSize, paramMap, true);
		
		request.setAttribute("resList", searchRestList);
		request.setAttribute("groupBuildingName", request.getParameter("groupBuildingName"));
		request.setAttribute("billName", request.getParameter("billName"));
	}
	
	@RequestMapping("/saveEdit.html")
	public ModelAndView saveEdit(BillEditParam billEditParam, HttpServletRequest request) throws IOException{
		if(billEditParam.getBillMonth()!=null) {
			//账单周期，月缴（默认每月一日）
			billEditParam.setBillMonth(billEditParam.getBillMonth()+"-01");
		}
		
		if(billEditParam.getId() != null) {
			// 账单开始时间
			billEditParam.setBillMonthStart(billEditParam.getBillMonthStartEdit());
			// 账单结束时间
			billEditParam.setBillMonthEnd(billEditParam.getBillMonthEndEdit());
		}

        //判断该费用名称是否存在自动生成账期（存在则不进行新增或者修改）
        //账单已缴的情况下 不提交费用名称，所以先判断id是否存在
        if(DataUtil.isEmpty(billEditParam.getId()) && groupBuildingBillCycleService.isHasAutoCreateCycle(billEditParam.getBillName().trim(), billEditParam.getGbId())) {
            request.setAttribute(JSPConstants.OprtResult, "该账期为自动生成，不可新增");
            request.setAttribute(JSPConstants.ToURL, "../groupBuildingBillCycle/billCycleList.html");
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName(JSPConstants.OprtSuccessPage);
            return modelAndView;
        }

		//判断是否存在同一个小区下，同种缴费类型的账期重叠 5014297
		boolean isCanAdd = false;
		if(billEditParam.getBillName() != null && !"".equals(billEditParam.getBillName())) {
			PayBillType payBillType = groupBuildingBillCycleService.getPayBillType(billEditParam.getBillName(), billEditParam.getGbId());
			//存在重名账单，才进行时间的校验
			if(payBillType != null) {
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("id", billEditParam.getId());
				paraMap.put("gbId", billEditParam.getGbId());
				paraMap.put("billTypeId", payBillType.getId());
				paraMap.put("billMonthStart", billEditParam.getBillMonthStart());
				paraMap.put("billMonthEnd", billEditParam.getBillMonthEnd());
				paraMap.put("billPayStart", billEditParam.getBillPayStart());
				paraMap.put("billPayEnd", billEditParam.getBillPayEnd());

				isCanAdd = groupBuildingBillCycleService.isHashSameBillCycle(paraMap);
			}
		}

		//账期不重复
		if(!isCanAdd) {
			if (billEditParam.getBillMonthStart() != null){
				billEditParam.setBillMonthStart(DateUtils.convertDateToStr(DateUtils.convertStrToDate(billEditParam.getBillMonthStart(), "yyyy-MM"), "yyyy-MM-dd"));
			}
			if (billEditParam.getBillMonthEnd() != null){
				billEditParam.setBillMonthEnd(DateUtils.convertDateToStr(DateUtils.convertStrToDate(billEditParam.getBillMonthEnd(), "yyyy-MM"), "yyyy-MM-dd"));
			}

			if(DateUtils.convertStrToDate(billEditParam.getBillMonthStart(), "yyyy-MM").getTime() > DateUtils.convertStrToDate(billEditParam.getBillMonthEnd(), "yyyy-MM").getTime()) {
				request.setAttribute(JSPConstants.OprtResult, "账单开始时间不能大于结束时间");
			} else if(DateUtils.convertStrToDate(billEditParam.getBillPayStart()).getTime() > DateUtils.convertStrToDate(billEditParam.getBillPayEnd()).getTime()) {
				request.setAttribute(JSPConstants.OprtResult, "缴费开始时间不能大于结束时间");
			} else {
				if(groupBuildingBillCycleService.saveOrUpdateBillCycel(billEditParam)>0){
					request.setAttribute(JSPConstants.OprtResult, "保存成功");
				}else{
					request.setAttribute(JSPConstants.OprtResult, "保存失败");
				}
			}
		
		} else {
			request.setAttribute(JSPConstants.OprtResult, "账单日期跟账单日期不能重叠，缴费日期跟缴费日期不能重叠");
		}
		request.setAttribute(JSPConstants.ToURL, "../groupBuildingBillCycle/billCycleList.html");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}

	/**
	 * 收费账期-初始化编辑或添加配置信息
	 * @author huangzj 2015-05-12
	 * @param request
	 * @return
	 */
	@RequestMapping("/initEdit.html")
	@ResponseBody
	public String initEdit(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		//管理处子账号
		if(UserContext.getCurrUser().getIsPmUser() == 1 && UserContext.getCurrUser().getParentUserId()!=null){
			paramMap.put("adminId", UserContext.getCurrUser().getParentUserId());
		}else if(UserContext.getCurrUser().getIsPmUser() == 1 && UserContext.getCurrUser().getParentUserId() == null){//管理处主账号
			paramMap.put("adminId", UserContext.getCurrUser().getId());
		}else{//物业公司主子账号，
			paramMap.put("adminId", UserContext.getOperId());
		}
		
		String id = request.getParameter("id");
		String gbId = request.getParameter("gbId");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		//修改
		if(!StringUtils.isBlank(id)) {
			//判断是否存在账单;3.33 版本修改，如果导入的账单都没有进行缴费，则可以进行账期修改（如果都没有缴费则不存在账单信息）
			//此处现在指是否存在已经缴费完成的账单,部分已缴费
			boolean isHasPayBill = payBillService.isHasPayBillByGbId(gbId, id);
			//查询收费账期信息
			GroupBuildingBillCycleEntity groupBuildingBillCycle = groupBuildingBillCycleService.getGroupBuildingBillCycleById(BigInteger.valueOf(Long.parseLong(id.trim())));
			Map<String, Object> map = MapConverter.toMap(groupBuildingBillCycle);
			
			resultMap.put("isHasPayBill", isHasPayBill);
			resultMap.put("gbc", map);
		} else {//新增
			//获取小区信息
			paramMap.put("gbIdList", UserContext.getGbIdList());
			List<Map<String, Object>> groupBuildingList = msGroupBuildingService.getBuildingListForSelected(paramMap);
			resultMap.put("gbList", groupBuildingList);
		}
		
		return JSON.toJSONString(resultMap);
	}

	@RequestMapping("/deleteAllBill.json")
	@ResponseBody
	public JsonResponse deleteAllBill(HttpServletRequest request) throws IOException{
		JsonResponse jsonResponse = new JsonResponse();
		BigInteger cycleId = ParamUtils.getBigInteger(request, "id", null);
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);

		Map<String, Object> paraMap = new HashMap<String, Object>();
		//小区ID
		paraMap.put("gbId", gbId);
		//账期id
		paraMap.put("billCycleId", cycleId);
		paraMap.put("userId", UserContext.getCurrUser().getId());

		if(groupBuildingBillCycleService.deleteAllBillById(paraMap)>0){
			jsonResponse.setMessage("清除成功");
		}else{
			jsonResponse.setMessage("已清除");
		}

		if(!DataUtil.isEmpty(gbId)) {
			CalculateLateFeeByGbImp calculateLateFeeByGbImp = (CalculateLateFeeByGbImp) CnfantasiaCommbusiUtil.getBeanManager("calculateLateFeeByGbImp");
			FutureTask<Boolean> booleanFutureTask = new FutureTask<>(new CalculateLateFeeRunnable(calculateLateFeeByGbImp, gbId, new BigInteger("1")));
			booleanFutureTask.run();
		}
		
		return jsonResponse;
	}

	@RequestMapping("/deleteGroupBuildingCycle.json")
	@ResponseBody
	public JsonResponse deleteGroupBuildingCycle(HttpServletRequest request) throws IOException{
		JsonResponse jsonResponse = new JsonResponse();
		BigInteger cycleId = ParamUtils.getBigInteger(request, "id", null);
		BigInteger billTypeId = ParamUtils.getBigInteger(request, "billTypeId", null);
		BigInteger billTimeCfgId = ParamUtils.getBigInteger(request, "billTimeCfgId", null);
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);

		Map<String, Object> paraMap = new HashMap<String, Object>();
		//小区ID
		paraMap.put("gbId", gbId);
		//账期id
		paraMap.put("billCycleId", cycleId);
		//账期id
		paraMap.put("billTypeId", billTypeId);
		//账期id
		paraMap.put("billTimeCfgId", billTimeCfgId);

		if(cycleId != null && gbId != null) {
			String res = groupBuildingBillCycleService.deleteGroupBuildingCycle(paraMap);
			jsonResponse.setMessage(res);
		} else {
			jsonResponse.setMessage("参数校验失败，请刷新重试！");
		}

		return jsonResponse;
	}
	
	@RequestMapping("/selectBillTypeList.html")
	@ResponseBody
	public String selectBillTypeList(HttpServletRequest request) {
		String gbId = request.getParameter("gbId");
		String paytimeType = request.getParameter("paytimeType");
		
		String isMeterReading = request.getParameter("isMeterReading"); 
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> billTypeList = new ArrayList<Map<String,Object>>();
		if("1".equals(paytimeType)) {//月度缴费
			//判断物业费是月度缴还是周期缴
			PayBillType payBillType = plotpropertyCfgService.getPayBillTypeForImport(BigInteger.valueOf(Long.parseLong(gbId)), PlotpropertyConstant.DEFAULT_PAYBILL_FEE_NAME, true);
			Integer paytimeType02 = payBillType==null?null:payBillType.getPaytimeType();
			Map<String, Object> map = new HashMap<String, Object>();
			//物业费月度缴
			if(paytimeType02 != null && paytimeType02.equals(1)) {
				map.put("id", 1);
				map.put("name", "物业费");
				billTypeList.add(map);
			} 
		} else {//周期缴费
			billTypeList = msGroupBuildingService.selectBillTypeListByGbId(BigInteger.valueOf(Long.parseLong(gbId)), paytimeType, isMeterReading);
		}
		resultMap.put("billTypeList", billTypeList);
		
		return JSON.toJSONString(resultMap);
	}

	@RequestMapping("/synchroFixedData.json")
	@ResponseBody
	public JsonResponse synchroFixedData(BigInteger cycleId, BigInteger gbId) {
		return groupBuildingBillCycleService.synchroFixedData(cycleId, gbId);
	}

	@RequestMapping("/createPayBill.json")
	@ResponseBody
	public JsonResponse createPayBill(BigInteger cycleId, BigInteger gbId) {
		JsonResponse jsonResponse = new JsonResponse();
		String lockKey = RedisCachePrefix.Pay_Bill + "create_" + cycleId + "_" + gbId;
		String msg = org.apache.commons.lang.StringUtils.EMPTY;
		if(RedisCacheHandler.get(lockKey) == null) {
			try {
				RedisCacheHandler.set(lockKey, "create"+cycleId);
				msg = groupBuildingBillCycleService.createPayBill(cycleId, gbId);
				FutureTask<Boolean> task = new FutureTask<Boolean>(new FinanceDeductionRunnable(financeService, gbId));
				new Thread(task).start();
			} catch (Exception e){
				logger.error("createPayBill.Exception", e);
				
				Map<String, Object> paraMap = new HashMap<String, Object>();
				//小区ID
				paraMap.put("gbId", gbId);
				//账期id
				paraMap.put("billCycleId", cycleId);
				groupBuildingBillCycleService.deleteAllBillById(paraMap);
				msg = "生成账单系统异常，请联系客服！";
			} finally {
				RedisCacheHandler.del(lockKey);
			}
		} else {
			msg = "数据正在生成中，请等待！";
		}
		jsonResponse.setMessage(msg);
		return jsonResponse;
	}
	
	/**
	 * 下载【抄表数据导入】模板
	 * 
	 * @param gbId
	 * @param response
	 */
	@RequestMapping("/exportMRTemplate.json")
	public void exportMRTemplate(BigInteger gbId, BigInteger cycleId, BigInteger gbbcCfgId, HttpServletResponse response){
		GroupBuilding gb = groupBuildingService.selectGroupBuildingById(gbId);
		
		HSSFWorkbook wb = new HSSFWorkbook();
		// 基本字体（不加粗）
		HSSFFont baseFont = wb.createFont();
		baseFont.setFontName("微软雅黑");
		// 加粗字体
		HSSFFont boldFont = wb.createFont();
		boldFont.setFontName("微软雅黑");
		boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 创建单元格样式
		HSSFCellStyle baseStyle = wb.createCellStyle();
		baseStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		baseStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		baseStyle.setFont(baseFont);
		// 绿色背景
		HSSFCellStyle greenStyle = wb.createCellStyle();
		greenStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		greenStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		greenStyle.setFont(boldFont);
		greenStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex()); 
		greenStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);		
		// 黄色背景
		HSSFCellStyle yellowStyle = wb.createCellStyle();
		yellowStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		yellowStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		yellowStyle.setFont(boldFont);
		yellowStyle.setFillForegroundColor(IndexedColors.GOLD.getIndex()); 
		yellowStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		//插入收费项配置数据
		Map<String, Object> mriQryMap = new HashMap<String, Object>();
		mriQryMap.put("gbId", gbId);
		mriQryMap.put("cycleId", cycleId);
		List<MrFeeItem> mriList = new ArrayList<>();
		if(!DataUtil.isEmpty(gbbcCfgId)) {
			mriList = groupBuildingBillCycleService.getMrFeeItemByCondition(mriQryMap);
		} else {
			mriQryMap.remove("cycleId");
			mriList = mrFeeItemBaseService.getMrFeeItemByCondition(mriQryMap);
			Collections.reverse(mriList);
		}

		for(int i = 0;  i<  mriList.size(); i++){
			MrFeeItem mfi = mriList.get(i);

			HSSFSheet sheet = wb.createSheet(mfi.getName());
			HSSFRow gbRow = sheet.createRow(0);
			gbRow.setHeight((short)300);
			// 小区名称 row
			HSSFCell gbCell = gbRow.createCell(0);
			gbRow.setRowStyle(baseStyle);
			gbCell.setCellValue(gb.getName());
			// 标题
			String[] blueCells = {"楼栋", "单元", "门牌"};
			HSSFRow row1 = sheet.createRow(1);
			for(int k=0; k<blueCells.length; k++){
				HSSFCell tmpCell = row1.createCell(k);
				tmpCell.setCellValue(blueCells[k]);
			}
			// 合并
			for(int col=0; col<3; col++){
				sheet.addMergedRegion(new CellRangeAddress(1, 2, col, col));
			}
			for(int j=0; j<3; j++){
				HSSFCell tmpCell = row1.getCell(j);
				tmpCell.setCellStyle(greenStyle);
			}
			HSSFRow row2 = sheet.createRow(2);

			List<MrExportModelEntity> mrExportModelEntities = buildingRoomService.getMrExportModelEntity(gbId, mfi.getId());
			for (int i1 = 0; i1 < mrExportModelEntities.size(); i1++) {
				MrExportModelEntity mrExportModelEntity = mrExportModelEntities.get(i1);
				//从第4行开始是数据行
				int dataFromRowIndex = 3;
				HSSFRow tmpRow = sheet.getRow(i1 + dataFromRowIndex);
				if (tmpRow == null) {
					tmpRow = sheet.createRow(i1 + dataFromRowIndex);
				}

				tmpRow.setRowStyle(baseStyle);
				//楼栋
				tmpRow.createCell(0).setCellValue(mrExportModelEntity.getBuildingName());
				//单元
				tmpRow.createCell(1).setCellValue(mrExportModelEntity.getUnitName());
				//房号
				tmpRow.createCell(2).setCellValue(mrExportModelEntity.getNumTail());
				//计费表名称
				tmpRow.createCell(3).setCellValue(mrExportModelEntity.getMrName());
				//计费倍率
				tmpRow.createCell(4).setCellValue(mrExportModelEntity.getMultiplierTimes());

				HSSFCell tmpCell = row1.createCell(3);
				tmpCell.setCellValue(mfi.getName());
				tmpCell.setCellStyle(yellowStyle);

				HSSFCell tmpCell1 = row2.createCell(3);
				tmpCell1.setCellValue("计费表名称");
				tmpCell1.setCellStyle(baseStyle);

				HSSFCell tmpCell2 = row2.createCell(4);
				tmpCell2.setCellValue("倍率");
				tmpCell2.setCellStyle(baseStyle);

				HSSFCell tmpCell3 = row2.createCell(5);
				tmpCell3.setCellValue("上期读数");
				tmpCell3.setCellStyle(baseStyle);

				HSSFCell tmpCell4 = row2.createCell(6);
				tmpCell4.setCellValue("本期读数");
				tmpCell4.setCellStyle(baseStyle);

				sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 6));
			}

			//小区名称单元格合并
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
			sheet.getRow(0).getCell(0).getCellStyle().setAlignment(HSSFCellStyle.ALIGN_CENTER);
		}

		ExcelUtil.commonExport("【"+gb.getName() + "】抄表数据导入模板", wb, response);
	}

	/**
	 * 导入抄表数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/importMrData.html")
	public ModelAndView importMrData(HttpServletRequest request) throws Exception {
		String result = groupBuildingBillCycleService.importMrData(request);

		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String urlSuffix = "?" + pageIndexName + "=" + ParamUtils.getInt(request, "currentPage", 1);

		request.setAttribute(JSPConstants.OprtResult, result);
		//保证导入后仍停留在之前的页面
		request.setAttribute(JSPConstants.ToURL, "../groupBuildingBillCycle/billCycleList.html" + urlSuffix);
		return new ModelAndView(JSPConstants.OprtSuccessPage);
	}
	
	@Resource
	private ITmpFeeItemBaseService tmpFeeItemBaseService;
	
	/**
	 * 下载【临时数据导入】模板
	 * 
	 * @param gbId
	 * @param response
	 */
	@RequestMapping("/exportTmpBillTemplate.json")
	public void exportTmpBillTemplate(BigInteger gbId, BigInteger cycleId, BigInteger gbbcCfgId, HttpServletResponse response){
		GroupBuilding gb = groupBuildingService.selectGroupBuildingById(gbId);
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("临时数据项导入表");
		// 基本字体（不加粗）
		HSSFFont baseFont = wb.createFont();
		baseFont.setFontName("微软雅黑");
		// 加粗字体
		HSSFFont boldFont = wb.createFont();
		boldFont.setFontName("微软雅黑");
		// head加粗
		boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 创建单元格样式
		HSSFCellStyle baseStyle = wb.createCellStyle();
		baseStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		baseStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		baseStyle.setFont(baseFont);
		// 绿色背景
		HSSFCellStyle greenStyle = wb.createCellStyle();
		greenStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		greenStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		greenStyle.setFont(boldFont);
		greenStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex()); 
		greenStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);		
		// 黄色背景
		HSSFCellStyle yellowStyle = wb.createCellStyle();
		yellowStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		yellowStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		yellowStyle.setFont(boldFont);
		yellowStyle.setFillForegroundColor(IndexedColors.GOLD.getIndex()); 
		yellowStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);	
		HSSFRow gbRow = sheet.createRow(0);
		gbRow.setHeight((short)300);
		// 小区名称 row
		HSSFCell gbCell = gbRow.createCell(0);
		gbRow.setRowStyle(baseStyle);
		gbCell.setCellValue(gb.getName());
		// 标题
		String[] blueCells = {"楼栋", "单元", "门牌"};
		HSSFRow row1 = sheet.createRow(1);
		for(int i=0; i<blueCells.length; i++){
			HSSFCell tmpCell = row1.createCell(i);
			tmpCell.setCellValue(blueCells[i]);
		}
		// 合并
		for(int col=0; col<3; col++){
			sheet.addMergedRegion(new CellRangeAddress(1, 2, col, col));
		}
		
		for(int i=0; i<3; i++){
			HSSFCell tmpCell = row1.getCell(i);
			tmpCell.setCellStyle(greenStyle);
		}
		
		List<String> titleList = new ArrayList<String>();
		List<List<String>> allSubTitleList = new ArrayList<List<String>>();
		
		//临时收费
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbId", gbId);
		paramMap.put("cycleId", cycleId);
		List<TmpFeeItem> tmpFeeItemList = new ArrayList<>();
		if(!DataUtil.isEmpty(gbbcCfgId)) {
			tmpFeeItemList = groupBuildingBillCycleService.getTmpFeeItemByCondition(paramMap);
		} else {
			paramMap.clear();
			paramMap.put("tGbId", gbId);
			tmpFeeItemList = tmpFeeItemBaseService.getTmpFeeItemByCondition(paramMap);
			Collections.reverse(tmpFeeItemList);//需求要求：配置项顺序与导入模板顺序一致
		}

		int lastColNum = 3;// 最后一列
		if(tmpFeeItemList==null || tmpFeeItemList.size()==0){// 无配置
			titleList.addAll(Arrays.asList("XXX费", "XXX", "XXX", "XXX", "XXX", "XXX", "XXX", "XXX", "XXX"));
			for(int i=0; i<titleList.size(); i++){
				allSubTitleList.add(Arrays.asList("费用合计", "单价", "用量"));
				lastColNum += 3;
			}
		} else {// 有配置
			for(int i=0; i<tmpFeeItemList.size(); i++){
				TmpFeeItem tmpFeeItem = tmpFeeItemList.get(i);
				titleList.add(tmpFeeItem.getName());
				if(1==tmpFeeItem.getCalculateType()){
					allSubTitleList.add(Arrays.asList("费用合计", "单价", "建筑面积"));
					lastColNum += 3;
				} else if(2==tmpFeeItem.getCalculateType()){
					allSubTitleList.add(Arrays.asList("费用合计"));
					lastColNum += 1;
				} else {
					allSubTitleList.add(Arrays.asList("费用合计", "单价", "用量"));
					lastColNum += 3;
				}
			}
		}
		
		HSSFCell lastCell = row1.createCell(lastColNum);
		lastCell.setCellValue("费用合计");
		sheet.addMergedRegion(new CellRangeAddress(1, 2, lastColNum, lastColNum));
		lastCell.setCellStyle(greenStyle);
		
		HSSFRow row2 = sheet.createRow(2);
		
		int colNumStart = 3;
		for(int i = 0;  i<  titleList.size(); i++){
			List<String> subTitleList = allSubTitleList.get(i);
			
			HSSFCell tmpCell = row1.createCell(colNumStart);
			tmpCell.setCellValue(titleList.get(i));
			tmpCell.setCellStyle(yellowStyle);
			
			for(int k=0; k<subTitleList.size(); k++){
				HSSFCell tmpCell1 = row2.createCell(colNumStart + k);
				tmpCell1.setCellValue(subTitleList.get(k));
				tmpCell1.setCellStyle(baseStyle);
			}
			sheet.addMergedRegion(new CellRangeAddress(1, 1, colNumStart, colNumStart + (subTitleList.size()-1)));
			colNumStart += subTitleList.size();
		}
		//小区名称单元格合并
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, lastColNum));
		sheet.getRow(0).getCell(0).getCellStyle().setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		// 费用合并
		paramMap.clear();
		//导出小区
		paramMap.put("groupbuildingId", gbId);
		List<RoomEntity> rooms = buildingRoomService.queryRoomForList(null, null, paramMap, false);

		//从第4行开始是数据行
		int dataFromRowIndex = 3;
		for (int i = 0; i < rooms.size(); i++) {
			RoomEntity room = rooms.get(i);
			HSSFRow tmpRow = sheet.getRow(i + dataFromRowIndex);
			if (tmpRow == null) {
				tmpRow = sheet.createRow(i + dataFromRowIndex);
			}
			
			int j = 0;
			tmpRow.setRowStyle(baseStyle);
			//楼栋
			tmpRow.createCell(j++).setCellValue(room.getBuildingName());
			//单元
			tmpRow.createCell(j++).setCellValue(room.getUnitName());
			//房号
			tmpRow.createCell(j++).setCellValue(room.getNumTail());
		}
		
		ExcelUtil.commonExport("【"+gb.getName() + "】临时数据导入模板", wb, response);
	}
	
	/**
	 * 导入临时费用数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("importTmpBillData.json")
	public ModelAndView importTmpBillData(HttpServletRequest request) throws Exception {
		groupBuildingBillCycleService.importTmpBillData(request);
        return new ModelAndView(JSPConstants.OprtSuccessPage);
	}

	/**
	 * 收费时间列表
	 */
	@RequestMapping("getCollectFeesTimeList.html")
	public ModelAndView getCollectFeesTimeList(HttpServletRequest request) {
        //可以进行配置的小区列表
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
        paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
        paramMap.put("gbName", ParamUtils.getString(request, "gbName"));

        if(UserContext.getCurrUser().getIsPmUser() == 1 && UserContext.getCurrUser().getParentUserId()!=null){//管理处子账号
            paramMap.put("adminId", UserContext.getCurrUser().getParentUserId());
        }else if(UserContext.getCurrUser().getIsPmUser() == 1 && UserContext.getCurrUser().getParentUserId() == null){//管理处主账号
            paramMap.put("adminId", UserContext.getCurrUser().getId());
        }else{//物业公司主子账号，
            paramMap.put("adminId", UserContext.getOperId());
        }

		//获取小区信息
		paramMap.put("gbIdList", UserContext.getGbIdList());
		paramMap.put("tGbId", ParamUtils.getBigInteger(request, "gbId", null));

        //总数量
        int resultSize = msGroupBuildingService.getBuildingListForSelectedCount(paramMap);
        // 页数的参数名
        String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
        // 每页显示的条数
        int pageSize = Integer.valueOf(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
        // 当前页
        int curPageIndex = com.cnfantasia.server.common.utils.StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        paramMap.put("_begin", pageSize * curPageIndex);
        paramMap.put("_length", pageSize);

        List<Map<String, Object>> groupBuildingList = msGroupBuildingService.getBuildingListForSelected(paramMap);

        ModelAndView modelAndView = new ModelAndView("/groupBuildingBillCycle/collectFeesTimeList");
        modelAndView.addObject("resultSize", resultSize);
        modelAndView.addObject("resList", groupBuildingList);
        modelAndView.addObject("param", paramMap);

        return modelAndView;
	}

    /**
     * 跳转收费时间管理
     */
    @RequestMapping("goToCollectFeesCfg.html")
    public ModelAndView goToCollectFeesCfg(HttpServletRequest request) {
        //查询对应小区下的账期管理时间配置
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tGbId", ParamUtils.getString(request, "gbId"));
        List<GroupBuildingBillCycleConfigEntity> groupBuildingBillCycleConfigList = groupBuildingCycleCfgService.getGroupBuildingBillCycleConfigByCondition(paramMap);
		//都查询出来，在前端进行选择周期的隐藏
		GroupBuildingHasFeeItemEntity groupBuildingHasFeeItemEntity = groupBuildingCycleCfgService.getBuildingHasFeeItemEntitiesByGbId(ParamUtils.getBigInteger(request, "gbId", null), 1);

		paramMap.clear();
		paramMap.put("id", ParamUtils.getString(request, "gbId"));
		List<GroupBuildingExt> groupBuildingExtByCondition = groupBuildingExtBaseService.getGroupBuildingExtByCondition(paramMap);
		Boolean isBankCollection = false;
		if(!DataUtil.isEmpty(groupBuildingExtByCondition)) {
			GroupBuildingExt groupBuildingExt = groupBuildingExtByCondition.get(0);
			//是否不开启银行托收={"0":"否","1":"是"}
			isBankCollection = groupBuildingExt.getOpenBankCollection() == 1;
		}
		ModelAndView modelAndView = new ModelAndView("/groupBuildingBillCycle/collectFeesCfg");
        modelAndView.addObject("resList", groupBuildingBillCycleConfigList);
        modelAndView.addObject("groupBuildingHasFeeItemEntity", groupBuildingHasFeeItemEntity);
        modelAndView.addObject("gbName", ParamUtils.getString(request, "gbName"));
        modelAndView.addObject("isBankCollection", isBankCollection);
        modelAndView.addObject("gbId",  ParamUtils.getString(request, "gbId"));
        if(groupBuildingBillCycleConfigList!=null && groupBuildingBillCycleConfigList.size()>0) {
			for (GroupBuildingBillCycleConfigEntity gbbcc : groupBuildingBillCycleConfigList) {
				if(CycleCfgDict.RechargeMode.PROPERTY_RECHARGE.compareTo(gbbcc.getChargingMode())==0) {
					gbbcc.setMinRecharge(BigDecimalUtil.div100(BigDecimal.valueOf(gbbcc.getMinRecharge()), 2).doubleValue());
					gbbcc.setMaxRecharge(BigDecimalUtil.div100(BigDecimal.valueOf(gbbcc.getMaxRecharge()), 2).doubleValue());
				}
			}
        }

        return modelAndView;
    }
    
	@RequestMapping("getGroupBuildingHasFeeItems.json")
	@ResponseBody
	public JsonResponse getGroupBuildingHasFeeItems(HttpServletRequest request) {
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
		//收费模式：1固定，2选择
		Integer cycleType = ParamUtils.getInteger(request, "cycleType", 1);
		GroupBuildingHasFeeItemEntity groupBuildingHasFeeItemEntity = groupBuildingCycleCfgService.getBuildingHasFeeItemEntitiesByGbId(gbId, cycleType);
		JsonResponse jsonResponse = new JsonResponse();
		String html = "";
		if (!DataUtil.isEmpty(groupBuildingHasFeeItemEntity.getFixedFeeItems())) {
			html = "常规收费项：</br>";
			for (FixedFeeItem fixedFeeItem : groupBuildingHasFeeItemEntity.getFixedFeeItems()) {
				html += "<label><input name='fixedFeeItemsIds' type='checkbox'  value='"+fixedFeeItem.getId()+"'>"+fixedFeeItem.getName()+"&nbsp;&nbsp;</label>";
			}
			html += "</br>";
		}

		if(!DataUtil.isEmpty(groupBuildingHasFeeItemEntity.getMrFeeItems())) {
			html += "抄表收费项：</br>";
			for (MrFeeItem mrFeeItem : groupBuildingHasFeeItemEntity.getMrFeeItems()) {
				html += "<label><input name='mrFeeItemsIds' type='checkbox'  value='"+mrFeeItem.getId()+"'>"+mrFeeItem.getName()+"&nbsp;&nbsp;</label>";
			}
			html += "</br>";
		}

		if (!DataUtil.isEmpty(groupBuildingHasFeeItemEntity.getTmpFeeItems())) {
			html += "临时收费项：</br>";
			for (TmpFeeItem tmpFeeItem : groupBuildingHasFeeItemEntity.getTmpFeeItems()) {
				html += "<label><input name='tmpFeeItemsIds' type='checkbox'  value='"+tmpFeeItem.getId()+"'>"+tmpFeeItem.getName()+"&nbsp;&nbsp;</label>";
			}
		}

		jsonResponse.setDataValue(html);

		return jsonResponse;
	}

    /**
     * 保存收费时间管理配置
     */
    @RequestMapping("saveCollectFeesCfg.json")
    @ResponseBody
    public JsonResponse saveCollectFeesCfg(GroupBuildingBillCycleConfigEntity groupBuildingBillCycleConfig, HttpServletRequest request) {
		boolean isSuccess = false;
    	JsonResponse jsonResponse = new JsonResponse();
    	if(CycleCfgDict.RechargeMode.PROPERTY_RECHARGE.compareTo(groupBuildingBillCycleConfig.getChargingMode())==0) {
    		//校验是否存在同小区、同费用名称的账期
            boolean isHasSameCycleCfg = checkBillNameRepeat(groupBuildingBillCycleConfig.getBillName(), groupBuildingBillCycleConfig.gettGbId(), groupBuildingBillCycleConfig.getId());
            if(isHasSameCycleCfg) {
                jsonResponse.setStatus("0001");
                jsonResponse.setMessage("存在相同的费用名称，请重新填写费用名称！");
                return jsonResponse;
            }
            
    		int startDay = groupBuildingBillCycleConfig.getStartDay();
    		int endDay = groupBuildingBillCycleConfig.getEndDay();
    		
    		if(groupBuildingBillCycleConfig.getRechargeMonthMode()==0 && startDay>endDay) {
    			jsonResponse.setStatus("0001");
    			jsonResponse.setMessage("缴费开始时间不能大于结束时间！");
    			return jsonResponse;
    		}
    		
    		groupBuildingBillCycleConfig.setMaxRecharge(groupBuildingBillCycleConfig.getMaxRecharge()*100);

    		groupBuildingBillCycleConfig.setMinRecharge(groupBuildingBillCycleConfig.getMinRecharge()*100);
			groupBuildingBillCycleConfig.setBillMonthStart(DateUtils.getCurrentDate());
			groupBuildingBillCycleConfig.setBillMonthEnd(DateUtils.getCurrentDate());
			groupBuildingBillCycleConfig.setBillPayStart(DateUtils.getCurrentDate());
			groupBuildingBillCycleConfig.setBillPayEnd(DateUtils.getCurrentDate());
			isSuccess = groupBuildingCycleCfgService.saveCollectFeesCfg(groupBuildingBillCycleConfig);
    	} else {
    		groupBuildingBillCycleConfig.setMaxRecharge(null);
    		groupBuildingBillCycleConfig.setMinRecharge(null);
    		groupBuildingBillCycleConfig.setRechargeMonthMode(null);
    		
    		//校验账期是否重复--和手动生成的账期进行校验
    		String billMonthStart = groupBuildingBillCycleConfig.getBillMonthStart();
    		String billMonthEnd = DataUtil.isEmpty(groupBuildingBillCycleConfig.getBillMonthEnd()) ? billMonthStart : groupBuildingBillCycleConfig.getBillMonthEnd();
    		String billPayStart = groupBuildingBillCycleConfig.getBillPayStart();
    		String billPayEnd = groupBuildingBillCycleConfig.getBillPayEnd();

            if (billMonthStart != null){
                billMonthStart = DateUtils.convertDateToStr(DateUtils.convertStrToDate(billMonthStart, "yyyy-MM"), "yyyy-MM-dd");
            }
            if (billMonthEnd != null){
                billMonthEnd = DateUtils.convertDateToStr(DateUtils.convertStrToDate(billMonthEnd, "yyyy-MM"), "yyyy-MM-dd");
            }
    		if (billPayStart != null) {
    			billPayStart = DateUtils.convertDateToStr(DateUtils.convertStrToDate(billPayStart), "yyyy-MM-dd");
    			groupBuildingBillCycleConfig.setBillPayStart(billPayStart);
    		}
    		if (billPayEnd != null) {
    			billPayEnd = DateUtils.convertDateToStr(DateUtils.convertStrToDate(billPayEnd), "yyyy-MM-dd");
    			groupBuildingBillCycleConfig.setBillPayEnd(billPayEnd);
    		}

    		if(DateUtils.convertStrToDate(billMonthStart, "yyyy-MM").getTime() > DateUtils.convertStrToDate(billMonthEnd, "yyyy-MM").getTime()) {
    			jsonResponse.setStatus("0001");
    			jsonResponse.setMessage("账单开始时间不能大于结束时间！");
    			return jsonResponse;
    		}
    		if(DateUtils.convertStrToDate(billPayStart).getTime() > DateUtils.convertStrToDate(billPayEnd).getTime()) {
    			jsonResponse.setStatus("0001");
    			jsonResponse.setMessage("缴费开始时间不能大于结束时间！");
    			return jsonResponse;
    		}

            //校验是否存在同小区、同费用名称的账期
            boolean isHasSameCycleCfg = checkBillNameRepeat(groupBuildingBillCycleConfig.getBillName(), groupBuildingBillCycleConfig.gettGbId(), groupBuildingBillCycleConfig.getId());
            if(isHasSameCycleCfg) {
                jsonResponse.setStatus("0001");
                jsonResponse.setMessage("存在相同的费用名称，请重新填写费用名称！");
                return jsonResponse;
            }

    		if(!DataUtil.isEmpty(groupBuildingBillCycleConfig.getId())) {
    			groupBuildingBillCycleConfig.setSys0UpdTime(DateUtils.getCurrentDate());
    			groupBuildingBillCycleConfig.setSys0UpdUser(UserContext.getOperIdBigInteger());
    		} else {
    			groupBuildingBillCycleConfig.setSys0AddTime(DateUtils.getCurrentDate());
    			groupBuildingBillCycleConfig.setSys0AddUser(UserContext.getOperIdBigInteger());
    		}

    		//判断费用项是否重复使用
    		String isHasSameFeeItem = groupBuildingCycleCfgService.isHasSameFeeItem(groupBuildingBillCycleConfig);
    		if(!DataUtil.isEmpty(isHasSameFeeItem)) {
    			jsonResponse.setStatus("0001");
    			jsonResponse.setMessage(isHasSameFeeItem+"费用项已经被其他账单使用，请重新选择！");
    			return jsonResponse;
    		}

			//处理费用名称空格
            groupBuildingBillCycleConfig.setBillName(groupBuildingBillCycleConfig.getBillName().replace(" ", ""));
            groupBuildingBillCycleConfig.setBillMonthEnd(billMonthEnd);
            groupBuildingBillCycleConfig.setBillMonthStart(billMonthStart);

    		//设置选择周期的缴费月份
    		if(groupBuildingBillCycleConfig.getChargingMode() == 2) {
    			String str = "";
    			for (int i1 = 0; i1 < groupBuildingBillCycleConfig.getMonths().length; i1++) {
    				str += groupBuildingBillCycleConfig.getMonths()[i1] + ",";
    			}
    			groupBuildingBillCycleConfig.setPeriodMonths(str);
    		}

    		//收费项类型：1抄表，2固定，3临时
    		String feeType = "";
    		if(!DataUtil.isEmpty(groupBuildingBillCycleConfig.getMrFeeItemsIds())) {
    			feeType += "1,";
    		}
    		if(!DataUtil.isEmpty(groupBuildingBillCycleConfig.getFixedFeeItemsIds())) {
    			feeType += "2,";
    		}
    		if(!DataUtil.isEmpty(groupBuildingBillCycleConfig.getTmpFeeItemsIds())) {
    			feeType += "3,";
    		}
    		groupBuildingBillCycleConfig.setFeeType(feeType);

    		Calendar dateStart = Calendar.getInstance();
    		dateStart.setTime(DateUtils.convertStrToDate(billPayStart, "yyyy-MM-dd"));
    		int start = dateStart.get(Calendar.DAY_OF_MONTH);

    		Calendar dateEnd = Calendar.getInstance();
    		dateEnd.setTime(DateUtils.convertStrToDate(billPayEnd, "yyyy-MM-dd"));
    		int end = dateEnd.get(Calendar.DAY_OF_MONTH);
			//结束时间限制在每月的28号
    		if(start > 28 || end > 28) {
    			jsonResponse.setStatus("0001");
    			jsonResponse.setMessage("缴费时间只能小于每月的28号");
    			return jsonResponse;
    		}

    		isSuccess = groupBuildingCycleCfgService.saveCollectFeesCfg(groupBuildingBillCycleConfig);
    	}

        //返回保存结果
		if(isSuccess) {
			jsonResponse.setStatus("0000");
			jsonResponse.setMessage("保存成功！");
		} else {
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("保存失败！");
		}
        return jsonResponse;
    }
    
    private boolean checkBillNameRepeat(String billName, BigInteger gbId, BigInteger id) {
    	//校验是否存在同小区、同费用名称的账期
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("feeName", billName);
        paraMap.put("gbId", gbId);
        paraMap.put("id", id);
        return groupBuildingBillCycleService.getIsHasSameCycleCfg(paraMap);
    }

	/***
	 * 删除收费账单配置
	 * @param id
	 * @return
     */
	@RequestMapping("delCollectFeesCfg.json")
	@ResponseBody
	public JsonResponse delCollectFeesCfg(BigInteger id, BigInteger gbId) {
		JsonResponse jsonResponse = new JsonResponse();
		BigInteger userId = UserContext.getCurrUser().getId();
		int i = groupBuildingCycleCfgService.delCollectFeesCfg(id, userId);
		if(i > 0) {
			jsonResponse.setStatus("0000");
			jsonResponse.setMessage("删除成功！");
		} else {
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("删除失败！");
		}

		if(!DataUtil.isEmpty(gbId)) {
			CalculateLateFeeByGbImp calculateLateFeeByGbImp = (CalculateLateFeeByGbImp) CnfantasiaCommbusiUtil.getBeanManager("calculateLateFeeByGbImp");
			FutureTask<Boolean> booleanFutureTask = new FutureTask<>(new CalculateLateFeeRunnable(calculateLateFeeByGbImp, gbId, userId));
			booleanFutureTask.run();
		}

		return jsonResponse;
	}

    /**
     * 收费类型维护列表
     */
    @RequestMapping("collectFeesTypeList.html")
    public ModelAndView collectFeesTypeList(HttpServletRequest request) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
        paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
        paramMap.put("gbName", ParamUtils.getString(request, "gbName"));
        if(UserContext.getCurrUser().getIsPmUser() == 1 && UserContext.getCurrUser().getParentUserId()!=null){//管理处子账号
            paramMap.put("adminId", UserContext.getCurrUser().getParentUserId());
        }else if(UserContext.getCurrUser().getIsPmUser() == 1 && UserContext.getCurrUser().getParentUserId() == null){//管理处主账号
            paramMap.put("adminId", UserContext.getCurrUser().getId());
        }else{//物业公司主子账号，
            paramMap.put("adminId", UserContext.getOperId());
        }

        // 页数的参数名
        String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
        // 每页显示的条数
        int pageSize = Integer.valueOf(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
        // 当前页
        int curPageIndex = com.cnfantasia.server.common.utils.StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        //获取小区信息
  		paramMap.put("gbIdList", UserContext.getGbIdList());
  		paramMap.put("tGbId", ParamUtils.getBigInteger(request, "gbId", null));
  		//总数量
        int resultSize = msGroupBuildingService.getBuildingListForSelectedCount(paramMap);
    
        paramMap.put("_begin", pageSize * curPageIndex);
        paramMap.put("_length", pageSize);
		List<Map<String, Object>> groupBuildingList = msGroupBuildingService.getBuildingListForSelected(paramMap);

        ModelAndView modelAndView = new ModelAndView("/groupBuildingBillCycle/collectFeesTypeList");
        modelAndView.addObject("resultSize", resultSize);
        modelAndView.addObject("resList", groupBuildingList);
        modelAndView.addObject("param", paramMap);

        return modelAndView;
    }

	/**
	 * 跳转到滞纳金配置页面
	 * @param request
	 * @return
     */
	@RequestMapping("/goTolateFeeCfg.html")
	public ModelAndView goTolateFeeCfg(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
		String gbName = ParamUtils.getString(request, "gbName");

		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("tGbId", gbId);
		List<GroupBuildingCalculateLatefeeRule> groupBuildingCalculateLatefeeRules = groupBuildingCalculateLatefeeRuleBaseService.getGroupBuildingCalculateLatefeeRuleByCondition(paraMap);
		if(!DataUtil.isEmpty(groupBuildingCalculateLatefeeRules)) {
			modelAndView.addObject("entity", groupBuildingCalculateLatefeeRules.get(0));
		}
		modelAndView.addObject("gbId", gbId);
		modelAndView.addObject("gbName", gbName);
		modelAndView.setViewName("/groupBuildingBillCycle/lateFeeCfg");

		return modelAndView;
	}

	/**
	 * 保存滞纳金配置
	 * @param groupBuildingCalculateLatefeeRule
	 * @param request
     * @return
     */
	@RequestMapping("/savelateFeeCfg.json")
	public ModelAndView savelateFeeCfg(GroupBuildingCalculateLatefeeRule groupBuildingCalculateLatefeeRule, HttpServletRequest request) {
		int i = groupBuildingCycleCfgService.savelateFeeCfg(groupBuildingCalculateLatefeeRule);
		return collectFeesTypeList(request);
	}

    /**
     * 注：任何地方不能再次引用该方法
     * 防止自动无法生成账期和账单，所有特别开放一个手动更具(t_group_building_bill_cycle_config)配置id的信息来进行生成
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/createCycleAndPayBill.json")
    @ResponseBody
    public JsonResponse createCycleAndPayBill(HttpServletRequest request) throws IOException{
    	 JsonResponse jsonResponse = new JsonResponse();
         BigInteger cycleCfgId = ParamUtils.getBigInteger(request, "id", null);
		//0生成账单账期，1只生成欠费信息
 		int type = ParamUtils.getInt(request, "type", 0);
 		/*String phone = ParamUtils.getString(request, "phone");
         if(DataUtil.isEmpty(phone)|| (!DataUtil.isEmpty(phone) && !"17688959966".equals(phone))) {
             jsonResponse.setMessage("没有操作权限!");
             return jsonResponse;
         }*/
         groupBuildingBillCycleService.autoCreateCycleAndPayBill(cycleCfgId, type);

         jsonResponse.setMessage("生成成功！");

         return jsonResponse;
    }

	public static void main(String[] args) {
		System.out.println("kangduo is so handsome, and liyulin is so doge~");
		System.out.println("qiangge is so rich, and he has four billion $s");
	}
}
