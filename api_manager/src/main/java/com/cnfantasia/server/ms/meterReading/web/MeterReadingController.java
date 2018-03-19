package com.cnfantasia.server.ms.meterReading.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnfantasia.server.common.utils.*;
import com.cnfantasia.server.domainbase.mrCalculateRuleCfg.entity.MrCalculateRuleCfg;
import com.cnfantasia.server.domainbase.mrCalculateRuleCfg.service.IMrCalculateRuleCfgBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.meterReading.constant.FeeTypeDict;
import com.cnfantasia.server.api.meterReading.entity.MrFeeItemWithFormula;
import com.cnfantasia.server.api.meterReading.entity.RealRoomHasMrLastRecordEntity;
import com.cnfantasia.server.api.meterReading.service.MeterReadingService;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyDict;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.file.FileDownloadUtils;
import com.cnfantasia.server.business.pub.utils.NumberUtils;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.entity.GroupBuildingBillCycle;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.service.IGroupBuildingBillCycleBaseService;
import com.cnfantasia.server.domainbase.mrFeeItem.entity.MrFeeItem;
import com.cnfantasia.server.domainbase.mrFeeItem.service.MrFeeItemBaseService;
import com.cnfantasia.server.domainbase.mrFeeItemFormula.entity.MrFeeItemFormula;
import com.cnfantasia.server.domainbase.mrFeeItemFormula.service.MrFeeItemFormulaBaseService;
import com.cnfantasia.server.domainbase.mrPayBillRecord.entity.MrPayBillRecord;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.ms.buildingRoom.entity.RoomEntity;
import com.cnfantasia.server.ms.buildingRoom.service.IBuildingRoomService;
import com.cnfantasia.server.ms.groupBuildingBillCycle.entity.GroupBuildingBillCycleEntity;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.payBill.constant.PayBillDict;
import com.cnfantasia.server.ms.payBill.entity.PayBillWithFeeDetailEntity;
import com.cnfantasia.server.ms.payBill.entity.PayBillWithFeeDetailWithMrRecordEntity;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.MapConverter;

/**
 * 抄表控制器
 * @author wenfq  2016年12月22日
 *
 */
@Controller
@RequestMapping("/meterReading")
public class MeterReadingController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());
	@Resource
	private MeterReadingService meterReadingService;
	
	@Resource
	private MrFeeItemBaseService mrFeeItemBaseService;
	
	@Resource
	private MrFeeItemFormulaBaseService mrFeeItemFormulaBaseService;
	
	@Resource
	protected IBuildingRoomService buildingRoomService;
	@Resource
	private IGroupBuildingBillCycleBaseService groupBuildingBillCycleBaseService;
	@Resource
	private IMrCalculateRuleCfgBaseService mrCalculateRuleCfgBaseService;
	/**
	 * 小区列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/listGb.html")
	public ModelAndView listGb(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbName", request.getParameter("gbName"));
		paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		paramMap.put("adminId", UserContext.getOperId());
		
		//总数量
		int resultSize = meterReadingService.selectGroupBuildingForCount(paramMap);
		request.setAttribute("resultSize", resultSize);
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		//分页信息
		List<GroupBuilding> searchRestList = meterReadingService.selectGroupBuildingForList(curPageIndex, pageSize, paramMap);
		request.setAttribute("resList", searchRestList);

		return new ModelAndView("/meterReading/gb4MRList");
	}
	
	
	/**
	 * 导出抄表模板
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/exportMeterReadingTemplate.html")
	public ModelAndView exportMeterReadingTemplate(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String gbId = request.getParameter("groupbuildingId");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("groupbuildingId", gbId);//导出小区
		List<RoomEntity> rooms = buildingRoomService.queryRoomForList(null, null, paramMap, false);
		if (rooms.size() > 0) {
			String filePath = request.getSession().getServletContext().getRealPath("/docs/jfq_meterReading_import_template.xls");
			FileInputStream fin = new FileInputStream(new File(filePath));
			HSSFWorkbook wb = new HSSFWorkbook(fin);
			String gbName = request.getParameter("groupbuildingName");
			String erportMonth = request.getParameter("erportMonth");
			wb.setSheetName(0, gbName+"-"+erportMonth+"抄表数据");
			HSSFSheet sheet = wb.getSheetAt(0);
			
			sheet.getRow(0).getCell(0).setCellValue(gbName);
			
			int dataFromRowIndex = 3; //从第4行开始是数据行
			for (int i = 0; i < rooms.size(); i++) {
				RoomEntity room = rooms.get(i);
				HSSFRow row = sheet.getRow(i + dataFromRowIndex);
				if (row == null) {
					row = sheet.createRow(i + dataFromRowIndex);
				}
				
				int j = 0;
				row.createCell(j++).setCellValue(room.getBuildingName());//楼栋
				row.createCell(j++).setCellValue(room.getUnitName());//单元	
				row.createCell(j++).setCellValue(room.getNumTail());//房号	
			}
			
			{
				//插入收费项配置数据
				MrFeeItem mriQry = new MrFeeItem();
				mriQry.setGbId(new BigInteger(gbId));
				List<MrFeeItem> mriList = mrFeeItemBaseService.getMrFeeItemByCondition(MapConverter.convertBean(mriQry));
				Collections.reverse(mriList);
				for(int i = 0;  i<  mriList.size(); i++){
					MrFeeItem mfi = mriList.get(i);
					sheet.getRow(1).createCell(3 + i * 2).setCellValue(mfi.getName());
					this.addBorderForCell(sheet.getRow(1).getCell(3 + i * 2), wb);
					this.addBorderForCell(sheet.getRow(1).createCell(4 + i * 2), wb);
					//sheet.getRow(1).getCell(3 + i * 2).getCellStyle().setAlignment(HSSFCellStyle.VERTICAL_CENTER);
					sheet.getRow(1).getCell(3 + i * 2).getCellStyle().setAlignment(HSSFCellStyle.ALIGN_CENTER);
					
					sheet.getRow(2).createCell(3 + i * 2).setCellValue("上期读数");
					this.addBorderForCell(sheet.getRow(2).getCell(3 + i * 2), wb);
					sheet.getRow(2).getCell(3 + i * 2).getCellStyle().setAlignment(HSSFCellStyle.ALIGN_CENTER);
					
					sheet.getRow(2).createCell(4 + i * 2).setCellValue("本期读数");
					this.addBorderForCell(sheet.getRow(2).getCell(4 + i * 2), wb);
					sheet.getRow(2).getCell(4 + i * 2).getCellStyle().setAlignment(HSSFCellStyle.ALIGN_CENTER);
					
					sheet.addMergedRegion(new CellRangeAddress(1, 1, 3 + i * 2, 4 + i * 2));
				}
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, mriList.size()*2 + 2));//小区名称单元格合并
				sheet.getRow(0).getCell(0).getCellStyle().setAlignment(HSSFCellStyle.ALIGN_CENTER);
				
				addBorderForCell(sheet.getRow(0).getCell(0), wb);
			}
			
			String fileName = gbName+"-"+erportMonth+"抄表数据.xls";
			fileName = FileDownloadUtils.encodeFilename(fileName, request);
			response.setContentType("application/vnd.ms-excel");    
			response.setHeader("Content-disposition", "attachment;filename=" + fileName); 
			OutputStream ouputStream = response.getOutputStream();    
			wb.write(ouputStream);    
			ouputStream.flush();    
			ouputStream.close();    
		}else{
			ModelAndView modelAndView = new ModelAndView();
			request.setAttribute(JSPConstants.OprtResult, "下载模板失败，当前小区没有房号数据!");
			request.setAttribute(JSPConstants.ToURL, "../security/welcome.html");
			modelAndView.setViewName(JSPConstants.OprtSuccessPage);
			return modelAndView;
		}
		
		return null;
	}
	
	/**
	 * 收费项配置——编辑
	 * @param request
	 * @return
	 */
	@RequestMapping("/mrFeeItemEdit.html")
	public ModelAndView mrFeeItemEdit(HttpServletRequest request) {
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
		String gbName = ParamUtils.getString(request, "gbName", null);
		if(gbId == null){
			throw new BusinessRuntimeException("gbId can't be null");
		}
		MrFeeItem mriQry = new MrFeeItem();
		mriQry.setGbId(gbId);
		List<MrFeeItem> mriList = mrFeeItemBaseService.getMrFeeItemByCondition(MapConverter.convertBean(mriQry));
		Collections.reverse(mriList);
		request.setAttribute("mriList", mriList);
		request.setAttribute("gbId", gbId);
		request.setAttribute("gbName", gbName);

		return new ModelAndView("/meterReading/mrFeeItemEdit");
	}
	
	/**
	 * 收费项配置——保存修改或新增的收费项
	 * @param request
	 * @return
	 */
	@RequestMapping("/mrFeeItemSave.json")
	@ResponseBody
	public JsonResponse mrFeeItemSave(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		String feeItemJson =request.getParameter("feeItemJson");
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
		
		if(gbId == null){
			throw new BusinessRuntimeException("gbId can't be null");
		}
		
		if(feeItemJson == null){
			throw new BusinessRuntimeException("feeItemJson can't be null");
		}
		
		meterReadingService.saveMrFeeItem(feeItemJson, gbId);
		
		return jsonResponse;
	}
	
	/**
	 * 收费项配置——删除
	 * @param request
	 * @return
	 */
	@RequestMapping("/mrFeeItemDelete.json")
	@ResponseBody
	public JsonResponse mrFeeItemDelete(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		BigInteger feeItemId = ParamUtils.getBigInteger(request, "feeItemId", null);
		if(feeItemId == null){
			throw new BusinessRuntimeException("feeItemId can't be null");
		}

		//检查收费项是否被其他费用作为用量基础计算
		Map<String, Object> paraMap = new HashMap<String, Object>(1);
		paraMap.put("basisItemId", feeItemId);
		List<MrCalculateRuleCfg> mrCalculateRuleCfgByCondition = mrCalculateRuleCfgBaseService.getMrCalculateRuleCfgByCondition(paraMap);
		if(DataUtil.isEmpty(mrCalculateRuleCfgByCondition)) {
			mrFeeItemBaseService.deleteMrFeeItemLogic(feeItemId);
			//删除对应的收费规则，收费标准
			BigInteger userId = UserContext.getOperIdBigInteger();
			meterReadingService.deleteMrCalculateRulAndStandardByFeeItemId(feeItemId, userId);
		} else {
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("有其他收费项按照此项读数计算，请先修改相应收费项的收费规则后再操作！");
		}

		return jsonResponse;
	}
	
	/**
	 * 收费项配置计算规则——编辑
	 * @param request
	 * @return
	 */
	@RequestMapping("/mrFeeItemFormulaEdit.html")
	public ModelAndView mrFeeItemFormulaEdit(HttpServletRequest request) {
		BigInteger mfiId = ParamUtils.getBigInteger(request, "mfiId", null);
		if(mfiId == null){
			throw new BusinessRuntimeException("mfiId can't be null");
		}
		
		MrFeeItemFormula mrifQry = new MrFeeItemFormula();
		mrifQry.settMrFeeItemFId(mfiId);
		List<MrFeeItemFormula> mrifList = mrFeeItemFormulaBaseService.getMrFeeItemFormulaByCondition(MapConverter.convertBean(mrifQry));
		 Collections.reverse(mrifList);
		request.setAttribute("mrifList", mrifList);
		
		return new ModelAndView("/meterReading/mrFeeItemFormulaEdit");
	}
	
	/**
	 * 收费项配置计算规则——保存修改或新增
	 * @param request
	 * @return
	 */
	@RequestMapping("/mrFeeItemFormulaSave.json")
	@ResponseBody
	public JsonResponse mrFeeItemFormulaSave(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		String feeItemFormulaJson = request.getParameter("feeItemFormulaJson");
		BigInteger mfiId = ParamUtils.getBigInteger(request, "mfiId", null);
		if(mfiId == null){
			throw new BusinessRuntimeException("mfiId can't be null");
		}
		
		if(feeItemFormulaJson == null){
			throw new BusinessRuntimeException("feeItemFormulaJson can't be null");
		}
		
		meterReadingService.saveMrFeeItemFormula(feeItemFormulaJson, mfiId);
		return jsonResponse;
	}
	
	/**
	 * 缴费周期配置
	 * @param request
	 * @return
	 */
	@RequestMapping("/billCycleList.html")
	public ModelAndView billCycleList(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		paramMap.put("adminId", UserContext.getOperId());
		paramMap.put("userId", UserContext.getOperIdBigInteger());

		paramMap.put("paytimeType", PlotpropertyDict.PayBillType_PaytimeType.PEORID);//1月缴；2周期缴
		paramMap.put("groupBuildingName", request.getParameter("groupBuildingName"));//小区名称
		paramMap.put("gbId", request.getParameter("gbId"));//小区Id
		paramMap.put("billName", request.getParameter("billName"));//账单名称
		paramMap.put("pbtType", PlotpropertyDict.PayBillType_IsPropFee.NO_MR);//抄表类型  账单
		
		//总数量
		int resultSize = meterReadingService.queryBillCycleCount(paramMap);
		request.setAttribute("resultSize", resultSize);
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		//分页信息
		List<GroupBuildingBillCycleEntity> searchRestList = meterReadingService.queryBillCycleForList(curPageIndex, pageSize, paramMap, true);
		
		request.setAttribute("resList", searchRestList);
		request.setAttribute("groupBuildingName", request.getParameter("groupBuildingName"));
		request.setAttribute("gbId", request.getParameter("gbId"));
		request.setAttribute("billName", request.getParameter("billName"));
		
		return new ModelAndView("/groupBuildingBillCycle/billCycleList4mr");
	}

	private boolean isEmptyRow(HSSFSheet sheet, int i) {
		boolean isEmptyRow = false;
		if (sheet.getRow(i) == null) {//处理空行的情况，有可能用户没有删除空白行
			return true;
		}

		if(StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)))
				&& StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)))){
			// 	楼栋	  房号 都为空时，也认为是空行, 跳过不导入
			isEmptyRow = true;
		}
		
		return isEmptyRow;
	}

	/**
	 * 校验数据有效性<p>
	 * 周期导入模板校验如下：
	 * 
		1、模板上已存在的所有列（包括账单类型的备选列）不允许删除
		2、以下标题名称不允许修改：配置项、小区、楼栋、单元、房号、业主姓名
		3、以下列为必填：本期读数
		4、以下列为非必填：上期读数
	 * @param sheet
	 * @return 校验成功返回“通过校验”，否则返回具体的失败原因 
	 */
	private String verifyMeterReadingData(HSSFSheet sheet, BigInteger gbId, BigInteger billCycleId, boolean isBillCycle) {
		String verifyResult = "通过校验";
		
		String[] colNames = { "楼栋", "单元", "门牌"};
		for (int j = 0; j < colNames.length; j++) {
			if (!colNames[j].equals(HSSFCellUtil.getStringValue(sheet.getRow(1).getCell(j)))) {
				return "第3行，第" + (j + 1) + "列的名称不是" + colNames[j] + ", 请重新下载抄表模板";
			}
		}
		
		short lastCellNumIndex = sheet.getRow(2).getLastCellNum();
		int colNumStart = isBillCycle ? 4 : 3;
		for (int j = colNumStart; j < lastCellNumIndex; j+=2){
			if(!"上期读数".equals(HSSFCellUtil.getStringValue(sheet.getRow(2).getCell(j)))
					|| !"本期读数".equals(HSSFCellUtil.getStringValue(sheet.getRow(2).getCell(j+1))))
			return "模板不正确，请重新下载";
		}
		
		{
			//校验收费项配置数据
			MrFeeItem mriQry = new MrFeeItem();
			mriQry.setGbId(gbId);
			List<MrFeeItem> mriList = mrFeeItemBaseService.getMrFeeItemByCondition(MapConverter.convertBean(mriQry));
			if(mriList.isEmpty()){
				return "收费配置为空，请进入【抄表收费项】中进行配置";
			}
			Collections.reverse(mriList);
			int excelFeeItemCount = (lastCellNumIndex-3)/2;
			if(excelFeeItemCount != mriList.size()){
				return "Excel中缴费配置项不正确，请重新导出抄表模板";
			}
			for(int i = 0; i < mriList.size(); i++){
				MrFeeItem mfi = mriList.get(i);
				if(!mfi.getName().equals(HSSFCellUtil.getStringValue(sheet.getRow(1).getCell(colNumStart + (i*2))))){
					return "Excel中缴费配置项不正确，请重新导出抄表模板";
				}
			}
		}
		
		if(sheet.getLastRowNum() == 2)
			return "请添加要导入的数据";
		
		for(int i = colNumStart; i <= sheet.getLastRowNum(); i++){
			boolean isEmptyRow = isEmptyRow(sheet, i);
			
			if(isEmptyRow) continue;
			
			// 楼栋、房号  不能为空
			if(StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0))))
				return "第"+(i+1)+"行的楼栋不能为空";
			if(StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2))))
				return "第"+(i+1)+"行的门牌不能为空";
			
			for (int j = colNumStart+1; j < lastCellNumIndex; j += 2) {
				try {
					double startValue = HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(j - 1));
					double endValue = HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(j));
					
					if(sheet.getRow(i).getCell(j)==null)
						return "第" + (i + 1) + "行的本期读数不能为空";
					
					if (startValue > 0 && startValue > endValue) {
						return "第" + (i + 1) + "行的本期读数必须要大于上期读数";
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					return "第" + (i + 1) + "行的本期读数和上期读数只能录入数字";
				}
			}
		}
		
		List<MrFeeItemWithFormula> mrFeeItemWithFormulaList = meterReadingService.getMrFeeItemWithFormulaByGbId(gbId+"");
		if(mrFeeItemWithFormulaList.isEmpty()){
			return "请先完成收费配置";
		}else{
			if(mrFeeItemWithFormulaList.size() != (sheet.getRow(2).getLastCellNum() - colNumStart)/2)
				return  "收费配置个数与Excel中不一致";
		}
		
		return verifyResult;
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
		boolean isBillCycle = "1".equals(request.getParameter("isBillCycle"));// 是否从【小区缴费管理】-【收费账单配置】-【缴费周期】页面进入的
		String result = "导入成功";
		String toUrl = "../meterReading/billCycleList.html?gbId=" + request.getParameter("importGbId");
		int feeDetailColumnStart = 3;//从第3列（含）开始，上次读数
		if(isBillCycle){
			feeDetailColumnStart = 4;// 【小区缴费管理】-【收费账单配置】-【缴费周期】页面多了“往月欠费”一列
			toUrl = "../groupBuildingBillCycle/billCycleList.html";
		} 
		if (request instanceof MultipartHttpServletRequest) {
			// 转型为Spring的MultipartHttpRequest(重点的所在)
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			MultipartFile uploadExcelfile = multipartRequest.getFile("excelFile");
			HSSFWorkbook wb = new HSSFWorkbook(uploadExcelfile.getInputStream());
			HSSFSheet sheet = wb.getSheetAt(0);
			int startRow = 3; //从第3行（含）开始导入数据
			int feeDetailColumnEnd = wb.getSheetAt(0).getRow(2).getLastCellNum()-1;//最后一列，
			int feeDetailInterval = 2;//每2列是一份抄表明细
			String gbId = request.getParameter("importGbId");
			String gbName = request.getParameter("importGbName");
			if(org.apache.commons.lang.StringUtils.isBlank(request.getParameter("billCycleId"))){
				request.setAttribute(JSPConstants.OprtResult, "账单类型对应缴费时间暂未配置！请配置！");
				request.setAttribute(JSPConstants.ToURL, toUrl);
				return new ModelAndView(JSPConstants.OprtSuccessPage);
			}
			BigInteger gbbcId = new BigInteger(request.getParameter("billCycleId"));
			String verifyResult = verifyMeterReadingData(sheet, BigInteger.valueOf(Long.parseLong(gbId)), gbbcId, isBillCycle);
			if(!verifyResult.equals("通过校验")){
				request.setAttribute(JSPConstants.OprtResult,"校验失败："+ verifyResult);
				request.setAttribute(JSPConstants.ToURL, toUrl);
				return new ModelAndView(JSPConstants.OprtSuccessPage);
			}
			
			//获取账单月份
			GroupBuildingBillCycle groupBuildingBillCycle = groupBuildingBillCycleBaseService.getGroupBuildingBillCycleBySeqId(gbbcId);
			String billMonthStart = groupBuildingBillCycle.getBillMonthStart();
			String billMonthEnd = groupBuildingBillCycle.getBillMonthEnd();
			String payDayStart = groupBuildingBillCycle.getBillPayStart();
			String payDayEnd = groupBuildingBillCycle.getBillPayEnd();
			
			List<PayBillWithFeeDetailWithMrRecordEntity> payBills = new ArrayList<PayBillWithFeeDetailWithMrRecordEntity>();
			List<MrFeeItemWithFormula> mrFeeItemWithFormulaList = meterReadingService.getMrFeeItemWithFormulaByGbId(gbId);
			List<RealRoomHasMrLastRecordEntity> realRoomLastRecordList = meterReadingService.getRealRoomLastRecordByGbId(gbId);
			List<RealRoomHasMrLastRecordEntity> realRoomLastRecordAddNewList = new ArrayList<RealRoomHasMrLastRecordEntity>();
			
			for (int i = startRow; i < sheet.getLastRowNum() + 1; i++) {
				PayBillWithFeeDetailWithMrRecordEntity payBill = new PayBillWithFeeDetailWithMrRecordEntity();
				payBill.setIsPay(PayBillDict.isPayed_no);
				
				boolean isEmptyRow = isEmptyRow(sheet, i);
				
				if(isEmptyRow) continue;
				
				PayBillType payBillType = meterReadingService.getPayBillType(gbId);
				payBill.setBillTypeName(payBillType.getName());
				payBill.setBillTypeId(payBillType.getId());
				
				int columnIndex = 0;
				
				payBill.setPayDayStart(payDayStart);
				payBill.setPayDayEnd(payDayEnd);
				payBill.setBillMonthStart(billMonthStart);
				payBill.setBillMonthEnd(billMonthEnd);
				payBill.settBillCycleId(groupBuildingBillCycle.getId());
				payBill.setBillTimeCfgId(groupBuildingBillCycle.gettPayBillTimeCfgId());
				payBill.setPaytimeType(groupBuildingBillCycle.getPaytimeType());
				
				payBill.setBillMonthSize((long)DateUtils.getDiffMonths(DateUtils.convertStrToDate(billMonthStart), DateUtils.convertStrToDate(billMonthEnd))+1);
				
				payBill.setGroupBuildingName(gbName);//小区名称
				payBill.setGroupBuildingId(request.getParameter("importGbId"));//小区Id
				
				HSSFRow row = sheet.getRow(i);
				HSSFCell buildingCell = row.getCell(columnIndex++);
				payBill.setBuildingName(HSSFCellUtil.getStringValue(buildingCell));
				
				HSSFCell unitCell = row.getCell(columnIndex++);
				payBill.setRealRoomUnitName(HSSFCellUtil.getStringValue(unitCell));
				
				HSSFCell realRoomCell = row.getCell(columnIndex++);
				payBill.setRealRoomNum(HSSFCellUtil.getStringValue(realRoomCell));
				
				if(isBillCycle){
					HSSFCell lastUnpaidCell = row.getCell(columnIndex++);
					payBill.setLastUnpaid(NumberUtils.doubleM100ToLong(HSSFCellUtil.getNumbericValue(lastUnpaidCell)));
				}
				
				long payBillAmount = 0; //账单金额
				for (int j = feeDetailColumnStart; j <= feeDetailColumnEnd; j += feeDetailInterval) {//每2列是一份缴费明细
					PropertyFeeDetail pfd = new PropertyFeeDetail();
					MrPayBillRecord mpbr = new MrPayBillRecord();
					pfd.setName(HSSFCellUtil.getStringValue(sheet.getRow(1).getCell(j))); 
					pfd.setType(PayBillDict.PropertyFeeDetailDict.FeeType_Other);
					pfd.settCycleId(gbbcId);
					pfd.setFeeType(FeeTypeDict.Chao_Biao);
					
					pfd.setPriceUnitName("用量");//计量单位
					pfd.setAllowancePrice(0L);
					
					{	// 按计算规则来计算 
						MrFeeItemWithFormula mfiWithFormula = mrFeeItemWithFormulaList.get((j-feeDetailColumnStart)/2);
						double startValue = HSSFCellUtil.getNumbericValue(row.getCell(j));
						double endValue = HSSFCellUtil.getNumbericValue(row.getCell(j + 1));
						mpbr.setEndValue(endValue);
						mpbr.settMrFeeItemFId(mfiWithFormula.getId());
						
						if(mfiWithFormula.getMfifList().size() == 0){
							result = "第"+ (i+1) + "行的"+  pfd.getName()+ "计算规则未配置，请进入【抄表收费项】中配置";
							request.setAttribute(JSPConstants.OprtResult, result);
							request.setAttribute(JSPConstants.ToURL, toUrl);
							return new ModelAndView(JSPConstants.OprtSuccessPage);
						}else if(mfiWithFormula.getMfifList().size() == 1){//单一计价规则
							double d = 0;
							if (startValue > 0) {//说明导入数据，有上期读数，就不用数据库中的上期读数
								d = endValue - startValue;
								mpbr.setStartValue(startValue);
							}else{//从数据库中取上期读数
								RealRoomHasMrLastRecordEntity rrRecord = getRealRoomLastRecord(realRoomLastRecordList, payBill, mfiWithFormula);
								if (rrRecord != null && rrRecord.getLastRecord() >= 0){
									d = endValue - rrRecord.getLastRecord();
									mpbr.setStartValue(rrRecord.getLastRecord());
									if(d<0){
										result = "第"+ (i+1) + "行本次导入的读数必须大于上次导入上期读数，"+ pfd.getName() + "上次导入的读数是：" + rrRecord.getLastRecord();
										request.setAttribute(JSPConstants.OprtResult, result);
										request.setAttribute(JSPConstants.ToURL, toUrl);
										return new ModelAndView(JSPConstants.OprtSuccessPage);
									}
								}else{
									result = "第"+ (i+1) + "行房间首次导入，"+  pfd.getName()+ "必须录入上期读数";
									request.setAttribute(JSPConstants.OprtResult, result);
									request.setAttribute(JSPConstants.ToURL, toUrl);
									return new ModelAndView(JSPConstants.OprtSuccessPage);
								}
							}
							
							pfd.setPriceUnitValue(NumberUtils.doubleM100ToLong(d));
							double pfdTotalAmt = d*mfiWithFormula.getMfifList().get(0).getUnitValue();
							pfd.setTotalPrice(pfdTotalAmt*100);
							//pfd.setSignalPrice(mfiWithFormula.getMfifList().get(0).getUnitValue());//单价，如果是单一计费需要保存
						}else{//按阶梯规则来计算
							double totalAmount = 0;
							double d = 0;//用量
							if (startValue > 0) {//说明导入数据中有上期读数，有上期读数，就不用数据库中的上期读数
								d = endValue - startValue;
								mpbr.setStartValue(startValue);
							}else{//从数据库中取上期读数
								RealRoomHasMrLastRecordEntity rrRecord = getRealRoomLastRecord(realRoomLastRecordList, payBill, mfiWithFormula);
								if(rrRecord != null && rrRecord.getLastRecord() >=0){
									d = endValue - rrRecord.getLastRecord();
									mpbr.setStartValue(rrRecord.getLastRecord());
									if(d<0){
										result = "第"+ (i+1) + "行本次导入的读数必须大于上次导入上期读数，"+ pfd.getName() + "上次导入的读数是：" + rrRecord.getLastRecord();
										request.setAttribute(JSPConstants.OprtResult, result);
										request.setAttribute(JSPConstants.ToURL, toUrl);
										return new ModelAndView(JSPConstants.OprtSuccessPage);
									}
								}else{
									result = "第"+ (i+1) + "行房间首次导入，"+  pfd.getName()+ "必须录入上期读数";
									request.setAttribute(JSPConstants.OprtResult, result);
									request.setAttribute(JSPConstants.ToURL, toUrl);
									return new ModelAndView(JSPConstants.OprtSuccessPage);
								}
							}
							pfd.setPriceUnitValue(NumberUtils.doubleM100ToLong(d));
							for(int k = 0; k < mfiWithFormula.getMfifList().size(); k++){
								MrFeeItemFormula mrFeeItemFormula = mfiWithFormula.getMfifList().get(k);
								if(d>mrFeeItemFormula.getEndValue()){
									totalAmount += (mrFeeItemFormula.getEndValue()-mrFeeItemFormula.getStartValue()) * mrFeeItemFormula.getUnitValue();
								}else{
									totalAmount += (d - mrFeeItemFormula.getStartValue())* mrFeeItemFormula.getUnitValue();
									pfd.setTotalPrice(totalAmount*100);
									break;
								}
							}
						}
						
						addOrUpdateRealRoomLastRecord(realRoomLastRecordList,realRoomLastRecordAddNewList, payBill, mfiWithFormula, endValue);
					}
					
					payBillAmount += pfd.getTotalPrice();
					
					payBill.addPropertyFeeDetail(pfd);
					payBill.getMrPayBillRecordList().add(mpbr);
				}
				payBill.setAmount(payBillAmount);
				payBills.add(payBill);
			}
			
			result = meterReadingService.saveImportPayBillPeriod(payBills, realRoomLastRecordList, realRoomLastRecordAddNewList);
		}
		
		request.setAttribute(JSPConstants.OprtResult, result);
		request.setAttribute(JSPConstants.ToURL, toUrl);
		return new ModelAndView(JSPConstants.OprtSuccessPage);
	}


	private void addOrUpdateRealRoomLastRecord(List<RealRoomHasMrLastRecordEntity> realRoomLastRecordList, List<RealRoomHasMrLastRecordEntity> realRoomLastRecordAddNewList, PayBillWithFeeDetailEntity payBill,
			MrFeeItemWithFormula mfiWithFormula, double endValue) {
		RealRoomHasMrLastRecordEntity rrRecord = getRealRoomLastRecord(realRoomLastRecordList, payBill, mfiWithFormula);
		if(rrRecord!=null){ //更新上期读数
			rrRecord.setLastRecord(endValue);
			rrRecord.setLastUpdTime(DateUtils.getCurrentDate());
		}else{//插入上期读数
			RealRoomHasMrLastRecordEntity realRoomLastRecord = new RealRoomHasMrLastRecordEntity();
			realRoomLastRecord.setId(CnfantasiaCommbusiUtil.getUuid(SEQConstants.t_real_room_has_mr_last_record));
			realRoomLastRecord.setLastRecord(endValue);
			realRoomLastRecord.setLastUpdTime(DateUtils.getCurrentDate());
			realRoomLastRecord.settMrFeeItemFId(mfiWithFormula.getId());
			
			realRoomLastRecord.setGbName(payBill.getGroupBuildingName());
			realRoomLastRecord.setBuildingName(payBill.getBuildingName());
			RealRoom rr = new RealRoom();
			rr.setUnitName(payBill.getRealRoomUnitName());
			rr.setNumTail(payBill.getRealRoomNum());
			realRoomLastRecord.setRr(rr);
			
			realRoomLastRecordAddNewList.add(realRoomLastRecord);
		}
	}
	
	private RealRoomHasMrLastRecordEntity getRealRoomLastRecord(List<RealRoomHasMrLastRecordEntity> realRoomLastRecordList,
			PayBillWithFeeDetailEntity payBill, MrFeeItemWithFormula mfiWithFormula) {
		for (int i = 0; i < realRoomLastRecordList.size(); i++) {
			RealRoomHasMrLastRecordEntity rrRecord = realRoomLastRecordList.get(i);
			if(rrRecord.getId() == null)
				continue;
			
			String payBillFullName = payBill.getGroupBuildingName() +"_" + payBill.getBuildingName() 
				+"_" + payBill.getRealRoomUnitName()+"_" + payBill.getRealRoomNum();
			if(rrRecord.getFullName().equals(payBillFullName) && rrRecord.gettMrFeeItemFId().equals(mfiWithFormula.getId()))
				return rrRecord;
		}
		
		return null;
	}
	
	/**
	 *  Style the cell with borders all around.
	 * @param cell
	 */
	private void addBorderForCell(HSSFCell cell, HSSFWorkbook wb){
        HSSFCellStyle style = wb.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setRightBorderColor(HSSFColor.BLACK.index);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setTopBorderColor(HSSFColor.BLACK.index);
        cell.setCellStyle(style);
	}
}
