package com.cnfantasia.server.ms.bankCollection.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.bankCollection.constant.BankCollectionConstants;
import com.cnfantasia.server.api.bankCollection.entity.BCHistoryDto;
import com.cnfantasia.server.api.bankCollection.entity.BcBankInfoDto;
import com.cnfantasia.server.api.bankCollection.entity.BcInfoByGbDto;
import com.cnfantasia.server.api.bankCollection.entity.BcInfoByPPDto;
import com.cnfantasia.server.api.bankCollection.entity.BcInfoEditReq;
import com.cnfantasia.server.api.bankCollection.entity.BcPrintDetail;
import com.cnfantasia.server.api.bankCollection.entity.BcPrintSum;
import com.cnfantasia.server.api.bankCollection.entity.PPBCInfo4List;
import com.cnfantasia.server.api.bankCollection.entity.RebackRecordDto;
import com.cnfantasia.server.api.bankCollection.entity.ResultMsg;
import com.cnfantasia.server.api.bankCollection.service.BankCollectionService;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.ExcelUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.ZipUtil;
import com.cnfantasia.server.domainbase.bankCollectionDate.entity.BankCollectionDate;
import com.cnfantasia.server.domainbase.bankCollectionDate.service.IBankCollectionDateBaseService;
import com.cnfantasia.server.domainbase.basedataBank.entity.BasedataBank;
import com.cnfantasia.server.domainbase.basedataBank.service.IBasedataBankBaseService;
import com.cnfantasia.server.domainbase.bcFinanceOrg.entity.BcFinanceOrg;
import com.cnfantasia.server.domainbase.bcFinanceOrg.service.IBcFinanceOrgBaseService;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycle.entity.BcGroupBuildingCycle;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycle.service.IBcGroupBuildingCycleBaseService;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.propertyCompanyBankCollectionInfo.entity.PropertyCompanyBankCollectionInfo;
import com.cnfantasia.server.domainbase.propertyCompanyBankCollectionInfo.service.IPropertyCompanyBankCollectionInfoBaseService;
import com.cnfantasia.server.domainbase.propertyProprietorBankCollectionInfo.entity.PropertyProprietorBankCollectionInfo;
import com.cnfantasia.server.domainbase.propertyProprietorBankCollectionInfo.service.IPropertyProprietorBankCollectionInfoBaseService;
import com.cnfantasia.server.ms.bankCollection.entity.BCHistoryReq;
import com.cnfantasia.server.ms.bankCollection.entity.BcPrintDetailDto;
import com.cnfantasia.server.ms.bankCollection.entity.RebackRecordReq;
import com.cnfantasia.server.ms.bankCollection.util.JspToHtmlUtil;
import com.cnfantasia.server.ms.buildingRoom.entity.RoomEntity;
import com.cnfantasia.server.ms.buildingRoom.service.IBuildingRoomService;
import com.cnfantasia.server.ms.groupBuilding.service.IGroupBuildingService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.omsUser.service.IOmsUserService;
import com.cnfantasia.server.ms.pd4ml.util.Html2PdfUtil;
import com.cnfantasia.server.ms.pd4ml.util.PrintConfig;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.MapConverter;
@Controller
@RequestMapping("/bankCollection")
public class BankCollectionController extends BaseController{

	private final Log logger = LogFactory.getLog(getClass());
	@Resource
	private BankCollectionService bankCollectionService;
	@Resource
	private IOmsUserService omsUserService;
	@Resource
	private IBankCollectionDateBaseService bankCollectionDateBaseService;
	@Resource
	private IBcFinanceOrgBaseService bcFinanceOrgBaseService;
	@Resource
	private IPropertyCompanyBankCollectionInfoBaseService propertyCompanyBankCollectionInfoBaseService;
	@Resource
	private IPropertyProprietorBankCollectionInfoBaseService propertyProprietorBankCollectionInfoBaseService;
	@Resource
	private IGroupBuildingService groupBuildingService;
	@Resource
	private IBuildingRoomService buildingRoomService;
	@Resource
	private IBasedataBankBaseService basedataBankBaseService;
	@Resource
	private IBcGroupBuildingCycleBaseService bcGroupBuildingCycleBaseService;
	@Resource
	private IGroupBuildingService msGroupBuildingService;

	/**
	 * 托收信息维护
	 * @param request
	 * @return
	 */
	@RequestMapping("/bankCollectionInfo.html")
	public ModelAndView bankCollectionInfo(HttpServletRequest request) {
		BigInteger pcId = UserContext.getUser().getPropertyCompanyId();
		if(pcId==null){
			request.setAttribute(JSPConstants.OprtResult, "非物业公司账号，不能操作！");
			request.setAttribute(JSPConstants.ToURL, "../security/welcome.html");
			return new ModelAndView(JSPConstants.OprtSuccessPage);
		}
		
		List<BcBankInfoDto> bcBankInfoList = bankCollectionService.selectBcBankInfoList(pcId);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tPropertyCompanyFId", pcId);
		List<BankCollectionDate> bankCollectionDateList = bankCollectionDateBaseService.getBankCollectionDateByCondition(paramMap);
		
		List<Integer> bankCollectionDates = new ArrayList<Integer>();
		if(bankCollectionDateList!=null && bankCollectionDateList.size()>0){
			for(BankCollectionDate bankCollectionDate : bankCollectionDateList){
				bankCollectionDates.add(bankCollectionDate.getBankCollectionDate());
			}
			
			Collections.sort(bankCollectionDates);  
		}

		ModelAndView modelAndView = new ModelAndView("/bankCollection/bankCollectionInfoList");
		modelAndView.addObject("bcBankInfoList", bcBankInfoList);
		modelAndView.addObject("bankCollectionDates", bankCollectionDates);
		modelAndView.addObject("bcDateSize", bankCollectionDates.size());
		return modelAndView;
	}

	/**
	 * 保存托收时间配置
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveBankCollectionDate.html")
	@ResponseBody
	public JsonResponse saveBankCollectionDate(String bcDate1, String bcDate2) {
		JsonResponse jsonResponse = new JsonResponse();

		BigInteger pcId = UserContext.getUser().getPropertyCompanyId();
		if(pcId==null){
			jsonResponse.setMessage("非物业公司账号，不能操作！");
		} else {
			bankCollectionService.saveBankCollectionDate(bcDate1, bcDate2, pcId);
			jsonResponse.setMessage("操作成功");
		}

		return jsonResponse;
	}
	
	/**
	 * 跳到托收信息维护
	 * @param pcbciId
	 * @return
	 */
	@RequestMapping("/goBankCollectionInfoEditPage.html")
	public ModelAndView goBankCollectionInfoEditPage(BigInteger pcbciId){
		ModelAndView modelAndView = new ModelAndView("/bankCollection/bankCollectionInfoEdit");
		
		BigInteger pcId = UserContext.getUser().getPropertyCompanyId();
		/**所有银行*/
		List<BcFinanceOrg> bcFinanceOrgList = bcFinanceOrgBaseService.getBcFinanceOrgByCondition(null);
		/**物业公司对应的所有管理处、小区信息*/
		List<BcInfoByGbDto> bcInfoByGbs = bankCollectionService.selectBcInfoByGB(pcId, pcbciId);
		/**物业公司对应的所有用户托收信息*/
		List<BcInfoByPPDto> bcInfoByPPs = bankCollectionService.selectBcInfoByPP(pcId, pcbciId);
		
		if(pcbciId!=null){
			/**物业公司与小区之间的托收银行信息*/
			PropertyCompanyBankCollectionInfo pcBankCollectionInfo = propertyCompanyBankCollectionInfoBaseService.getPropertyCompanyBankCollectionInfoBySeqId(pcbciId);
			modelAndView.addObject("pcBankCollectionInfo", pcBankCollectionInfo);
		}
		
		modelAndView.addObject("bcFinanceOrgList", bcFinanceOrgList);
		modelAndView.addObject("bcInfoByGbs", bcInfoByGbs);
		modelAndView.addObject("bcInfoByPPs", bcInfoByPPs);
		modelAndView.addObject("pcbciId", pcbciId);
		return modelAndView;
	}
	
	/**
	 * 保存托收信息
	 * @return
	 */
	@RequestMapping("/saveBankCollectionInfo.html")
	public ModelAndView saveBankCollectionInfo(HttpServletRequest request){
		String[] gdIds = request.getParameterValues("gbId");
		String[] ppbciIds = request.getParameterValues("ppbciId");
		Integer collectionRange = ParamUtils.getInteger(request, "collectionRange", null);
		String no = request.getParameter("no");
		String contractNo = request.getParameter("contractNo");
		String bankOrg = request.getParameter("bankOrg");
		String bankOrgName = request.getParameter("bankOrgName");
		String bankAccount = request.getParameter("bankAccount");
		String pcbciId = request.getParameter("pcbciId");
		String gbIds = request.getParameter("gbIds");// 按业主托盘需要
		
		BcInfoEditReq bcInfoEditReq = new BcInfoEditReq();
		bcInfoEditReq.setGdIds(gdIds);
		bcInfoEditReq.setPpbciIds(ppbciIds);
		bcInfoEditReq.setCollectionRange(collectionRange);
		bcInfoEditReq.setNo(no);
		bcInfoEditReq.setContractNo(contractNo);
		bcInfoEditReq.setBankOrg(bankOrg);
		bcInfoEditReq.setBankOrgName(bankOrgName);
		bcInfoEditReq.setBankAccount(bankAccount);
		bcInfoEditReq.setPcbciId(pcbciId);
		BigInteger pcId = UserContext.getUser().getPropertyCompanyId();
		bcInfoEditReq.setPcId(pcId);
		if(bcInfoEditReq.getCollectionRange()==BankCollectionConstants.CollectionRange.By_PP){
			@SuppressWarnings("unchecked")
			Set<String> gbIdSet = JSON.parseObject(gbIds, Set.class);
			bcInfoEditReq.setGbIdSet(gbIdSet);
		}
		
		ResultMsg resultMsg = new ResultMsg();
		try{
			resultMsg = bankCollectionService.saveBankCollectionInfo(bcInfoEditReq);
		} catch (Exception e){
			resultMsg.setSuccess(false);
			logger.debug("BankCollectionController.saveBankCollectionInfo=>"+JSON.toJSONString(bcInfoEditReq), e);
		}
		
		if(resultMsg.isSuccess()){
			request.setAttribute(JSPConstants.OprtResult, "保存成功!");
			request.setAttribute(JSPConstants.ToURL, "../bankCollection/bankCollectionInfo.html");
		} else {
			if(StringUtils.isBlank(resultMsg.getMsg())){
				request.setAttribute(JSPConstants.OprtResult, "保存失败!");
			} else {
				request.setAttribute(JSPConstants.OprtResult, resultMsg.getMsg());
			}
			
			String url = "../bankCollection/goBankCollectionInfoEditPage.html";
			if(StringUtils.isNotBlank(pcbciId)){
				url += "?pcbciId"+pcbciId;
			}
			request.setAttribute(JSPConstants.ToURL, url);
		}
		
		return new ModelAndView(JSPConstants.OprtSuccessPage);
	}

	/**
	 * 业主托收信息维护
	 * @param request
	 * @return
	 */
	@RequestMapping("/ppbankCollectionInfo.html")
	public ModelAndView ppbankCollectionInfo(HttpServletRequest request) {
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbName", ParamUtils.getString(request, "gbName"));//小区
		paramMap.put("buildingName", ParamUtils.getString(request, "buildingName"));//楼栋
		paramMap.put("unitName", ParamUtils.getString(request, "unitName"));//单元
		paramMap.put("rrNum", ParamUtils.getString(request, "rrNum"));//房号
		paramMap.put("roomNo", ParamUtils.getString(request, "roomNo"));//房间编码
		
		paramMap.put("bankOwnerName", ParamUtils.getString(request, "bankOwnerName"));//开卡人姓名
		
		paramMap.put("ppPhone", ParamUtils.getString(request, "ppPhone"));//手机号
		paramMap.put("bankAccount", ParamUtils.getString(request, "bankAccount"));//银行卡号
		paramMap.put("bankName", ParamUtils.getString(request, "bankName"));//开卡银行
		paramMap.put("gbId", gbId);//小区id

		//总数量
		int resultSize = bankCollectionService.getPropertyProprietorBankCollectionInfoByConditionCount(paramMap);
		
		PageUtils.addPageInfoToParam(request, paramMap);

		List<PropertyProprietorBankCollectionInfo> ppBankInfoList = bankCollectionService.getPropertyProprietorBankCollectionInfoByCondition(paramMap);

		ModelAndView modelAndView = new ModelAndView("/bankCollection/ppBankCollectionInfoList");
		modelAndView.addObject("resultSize", resultSize);
		modelAndView.addObject("resList", ppBankInfoList);
		modelAndView.addObject("param", paramMap);

		return modelAndView;
	}


	/**
	 * 跳到业主托收信息修改
	 * @param request
	 * @return
	 */
	@RequestMapping("/goPPBankCollectionInfoEdit.html")
	public ModelAndView goPPBankCollectionInfoEdit(HttpServletRequest request) {
		BigInteger id = ParamUtils.getBigInteger(request, "id", null);
		String showType = ParamUtils.getString(request, "showType");
		PPBCInfo4List ppbcInfo4List = bankCollectionService.getPropertyProprietorBankCollectionInfoBySeqId(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("entity", ppbcInfo4List);
		if("show".equals(showType)) {//查看
			modelAndView.setViewName("/bankCollection/ppBankCollectionInfoView");
		} else {//编辑
			modelAndView.setViewName("/bankCollection/ppBankCollectionInfoEdit");
		}

		return modelAndView;
	}

	/**
	 * 业主托收信息编辑
	 * @param request
	 * @return
     */
	@RequestMapping("/ppBankCollectionInfoUpdate.html")
	public ModelAndView ppBankCollectionInfoUpdate(HttpServletRequest request) {
		String roomNo = ParamUtils.getString(request, "roomNo");
		String bankAccount = ParamUtils.getString(request, "bankAccount");
		String bankName = ParamUtils.getString(request, "bankName");
		String bankOwnerName = ParamUtils.getString(request, "bankOwnerName");
		BigInteger id = ParamUtils.getBigInteger(request, "id", null);

		PropertyProprietorBankCollectionInfo propertyProprietorBankCollectionInfo = propertyProprietorBankCollectionInfoBaseService.getPropertyProprietorBankCollectionInfoBySeqId(id);
		propertyProprietorBankCollectionInfo.setRoomNo(roomNo);
		propertyProprietorBankCollectionInfo.setBankAccount(bankAccount);
		propertyProprietorBankCollectionInfo.setBankName(bankName);
		propertyProprietorBankCollectionInfo.setBankOwnerName(bankOwnerName);
		Map<String, Object> paramMap02 = new HashMap<String, Object>();
		paramMap02.put("name", bankName);
		BigInteger basedataBankId = null;
		List<BasedataBank> basedataBankList = basedataBankBaseService.getBasedataBankByCondition(paramMap02);
		if(!DataUtil.isEmpty(basedataBankList)) {
			basedataBankId = basedataBankList.get(0).getId();
		}
		propertyProprietorBankCollectionInfo.settBasedataBankFId(basedataBankId);
		propertyProprietorBankCollectionInfo.setId(id);
		propertyProprietorBankCollectionInfo.setSys0UpdTime(DateUtils.getCurrentDate());

		bankCollectionService.updatePropertyProprietorBankCollectionInfo(propertyProprietorBankCollectionInfo);

		return new ModelAndView("redirect:/bankCollection/ppbankCollectionInfo.html");
	}

	/**
	 * 下载【业主托收数据导入】模板
	 *
	 * @param gbId
	 * @param response
	 */
	@RequestMapping("/exportPpInfoTemplate.json")
	public void exportTmpBillTemplate(BigInteger gbId, HttpServletResponse response, HttpServletRequest request){

		GroupBuilding gb = groupBuildingService.selectGroupBuildingById(gbId);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("groupbuildingId", gbId);
		List<RoomEntity> rooms = buildingRoomService.queryRoomForList(null, null, paramMap, false);

		String filePath = request.getSession().getServletContext().getRealPath("/docs/jfq_ppinfo_import_template.xls");
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(fin);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HSSFSheet sheet = wb.getSheetAt(0);
		//excel表头
		HSSFRow row0 = sheet.getRow(0);
		row0.getCell(0).setCellValue(gb.getId().toString());
		row0.getCell(1).setCellValue(gb.getName());
		int dataFromRowIndex = 2; //从第3行开始是数据行
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
			//row.createCell(j++).setCellValue(room.getProprietor() == null ? "" : room.getProprietor().getName());//业主姓名
			row.createCell(j++).setCellValue(room.getProprietor() == null ? "" : room.getProprietor().getPhone());//手机
			row.createCell(j++).setCellValue("");//房间编码
			HSSFCellStyle cellStyle2 = wb.createCellStyle();
			HSSFDataFormat format = wb.createDataFormat();
			cellStyle2.setDataFormat(format.getFormat("@"));
			HSSFCell cellBankNo = row.createCell(j++);
			cellBankNo.setCellStyle(cellStyle2);
			cellBankNo.setCellValue("");//银行卡
			row.createCell(j++).setCellValue("");//开卡人姓名
			row.createCell(j++).setCellValue("");//开卡银行
		}
		
		if (rooms.size() == 0) {
			HSSFRow row = sheet.getRow(2);
			if(row == null) row = sheet.createRow(2);
			row.createCell(1).setCellValue(gb.getName()+"下没有房间信息，请先导入房间信息！");
		}
		ExcelUtil.commonExport("【"+gb.getName() + "】业主托收数据导入模板", wb, response);
	}

	/**
	 * 导入业主托收信息
	 * @param request
	 * @return
	 * @throws Exception
     */
	@RequestMapping("/importPpInfoData.json")
	public ModelAndView importPpInfoData(HttpServletRequest request) throws Exception {
		String result = bankCollectionService.importPpInfoData(request);

		request.setAttribute(JSPConstants.OprtResult, result);
		request.setAttribute(JSPConstants.ToURL, "../bankCollection/ppbankCollectionInfo.html");

		return new ModelAndView(JSPConstants.OprtSuccessPage);
	}
	
	/**
	 * 托收数据查询
	 * @param bcHistoryReq
	 * @return
	 */
	@RequestMapping("/bcHistory.html")
	public ModelAndView bcHistory(BCHistoryReq bcHistoryReq, HttpServletRequest request){
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
		BigInteger pcId = UserContext.getUser().getPropertyCompanyId();
		if(pcId==null){
			request.setAttribute(JSPConstants.OprtResult, "非物业公司账号，不能操作！");
			request.setAttribute(JSPConstants.ToURL, "../security/welcome.html");
			return new ModelAndView(JSPConstants.OprtSuccessPage);
		}
		
		if (StringUtils.isNotBlank(bcHistoryReq.getBillMonthStart())) {
			String billMonthStart = DateUtils.convertDateToStr(DateUtils.convertStrToDate(bcHistoryReq.getBillMonthStart(), "yyyy-MM"), "yyyy-MM-dd");
			bcHistoryReq.setBillMonthStart(billMonthStart);
		}
		if (StringUtils.isNotBlank(bcHistoryReq.getBillMonthEnd())) {
			String billMonthEnd = DateUtils.convertDateToStr(DateUtils.convertStrToDate(bcHistoryReq.getBillMonthEnd(), "yyyy-MM"), "yyyy-MM-dd");
			bcHistoryReq.setBillMonthEnd(billMonthEnd);
		}

		String searchFrom = ParamUtils.getString(request, "searchFrom");
		if("menu".equals(searchFrom)) {//菜单查询，默认账单起始时间：所带月为当前月
			String currentDateStr = DateUtils.getCurrentDateStr("yyyy-MM");
			bcHistoryReq.setBillMonthStart(currentDateStr);
			request.setAttribute("billMonthStart", currentDateStr);
		}
		
		Map<String, Object> paramMap = MapConverter.toMap(bcHistoryReq);
		paramMap.put("pcId",pcId);
		paramMap.put("gbId",gbId);
		int total = bankCollectionService.selectBCHistoryForCount(paramMap);
		
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		// 分页信息
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		List<BCHistoryDto> bcHistorys = bankCollectionService.selectBCHistoryForList(paramMap);
		
		if(bcHistorys!=null && bcHistorys.size()>0){
			for(BCHistoryDto bcHistory:bcHistorys){
				/**处理重复的小区名称*/
//				String gbNamesStr = bcHistory.getGbNames();
//				if(StringUtils.isNotBlank(gbNamesStr)){
//					String[] gbNames = gbNamesStr.split(",");
//					List<String> gbNameList = new ArrayList<String>();
//					for(String gbName : gbNames){
//						String tmpGbName = gbName.trim().replaceAll(",", "");
//						if(!gbNameList.contains(tmpGbName)){
//							gbNameList.add(tmpGbName);
//						}
//					}
//					if(gbNameList.size()>0){
//						String allGbName = gbNameList.toString();
//						bcHistory.setGbNames(allGbName.substring(1, allGbName.length()-1));
//					}
//				}
				/**处理类名*/
				String fileFormat = getFileFormat(bcHistory.getClassName());
				bcHistory.setFileFormat(fileFormat);
			}
		}
		
		/**所有银行*/
		List<BcFinanceOrg> bcFinanceOrgList = bcFinanceOrgBaseService.getBcFinanceOrgByCondition(null);
		
		ModelAndView modelAndView = new ModelAndView("/bankCollection/bcHistory");
		modelAndView.addObject("bcHistorys", bcHistorys);
		modelAndView.addObject("total", total);
		modelAndView.addObject("bcFinanceOrgList", bcFinanceOrgList);
		return modelAndView;
	}
	
	/**
	 * 根据接口类名获取文件格式
	 * @param className
	 * @return
	 */
	private static final String getFileFormat(String className){
		if(StringUtils.isBlank(className)){
			return null;
		} else if(className.endsWith("JinRongLianBankCollectionTransfer")){
			return ".zip";
		} else if(className.endsWith("NongShangHangBCTransfer")){
			return ".RET";
		} else if(className.endsWith("ICBCBankCollectionTransfer")){
			return ".txt";
		} else if(className.endsWith("PingAnBankCollectionTransfer")){
			return ".xls";
		}
		return null;
	}
	
	/**
	 * 下载出盘文件
	 * @throws IOException 
	 */
	@RequestMapping("/downOutBC.html")
	public ModelAndView downOutBC(BigInteger bgbcId, String allInfo, HttpServletRequest request, HttpServletResponse response){
		BcGroupBuildingCycle bgbc = bcGroupBuildingCycleBaseService.getBcGroupBuildingCycleBySeqId(bgbcId);
		String sumFileUrl = bgbc.getSumFileUrl();
		String detailFileUrl = bgbc.getDetailFileUrl();
		
		String basePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + File.separator;
		String[] fileNames = new String[]{basePath + sumFileUrl, 
										  basePath + detailFileUrl};
		//String[] fileNames = new String[]{"E:/迅雷下载/test/1.txt", "E:/迅雷下载/test/2.txt", "E:/迅雷下载/test/3.txt"};
		if(StringUtils.isBlank(detailFileUrl) || !new File(basePath + detailFileUrl).exists() 
				|| (StringUtils.isNotBlank(sumFileUrl) && !new File(basePath + sumFileUrl).exists())){
			// detailFileUrl必须存在，sumFileUrl不一定存在
			request.setAttribute(JSPConstants.ToURL, "../bankCollection/bcHistory.html");
			request.setAttribute(JSPConstants.OprtResult, "文件已不存在，下载失败！");
			return new ModelAndView(JSPConstants.OprtSuccessPage);
		}
		
		String zipFilePath = System.getProperty("java.io.tmpdir")+ File.separator +"bc" + File.separator + UUID.randomUUID().toString()+".zip";

        File zipFile = new File(zipFilePath);
		ZipUtil.zipFiles(fileNames, zipFile);
		
		// 设置response的Header
		response.setContentType("application/octet-stream;charset=UTF-8");
		ServletOutputStream outputStream = null;
		FileInputStream fis = null;
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(allInfo + ".zip", "UTF-8"));
			response.setContentLength((int) zipFile.length());  
			// 浏览器缓存20分钟
			response.setHeader("Cache-Control", "max-age=1200");

			outputStream = response.getOutputStream();
			fis = FileUtils.openInputStream(zipFile);  
			int BUFFERED_SIZE_5M = 1024 * 1024 * 5;
	        byte[] buffer = new byte[BUFFERED_SIZE_5M];
	        
	        while(fis.read(buffer, 0, BUFFERED_SIZE_5M) != -1){  
	        	outputStream.write(buffer, 0, BUFFERED_SIZE_5M);  
	        }  
	        outputStream.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(outputStream!=null){
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis!=null){
				try {
						fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
			if(zipFile!=null && zipFile.exists()){
				zipFile.delete();
			}
	       
		}
		return null;
	}
	
	/**
	 * 导入回盘文件
	 * @param request
	 * @return
	 */
	@RequestMapping("/importInBC.html")
	public ModelAndView importInBC(HttpServletRequest request){
		BigInteger bcgroupBuildingCycleId = ParamUtils.getBigInteger(request, "bgbcId", null);
		String fileFormat = request.getParameter("fileFormat");
		String msg = "";
		request.setAttribute(JSPConstants.ToURL, "../bankCollection/bcHistory.html");
		
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile uploadfile = multipartRequest.getFile("bankFile");
			if(uploadfile != null) {
				String zipName = uploadfile.getOriginalFilename();
				if(zipName!=null){
					//zipName = zipName.substring(zipName.lastIndexOf("."));
					if(fileFormat!=null && !zipName.endsWith(fileFormat)){
						request.setAttribute(JSPConstants.OprtResult, "上传文件格式不正常，请上传"+fileFormat+"格式文件！");
						return new ModelAndView(JSPConstants.OprtSuccessPage);
					}
				}
				
				ResultMsg resultMsg = null;
				try {
					resultMsg = bankCollectionService.importDetailFile(uploadfile, bcgroupBuildingCycleId);
					msg = resultMsg.getMsg();
				} catch (Exception e) {
					msg = "回盘失败！";
					e.printStackTrace();
				}
			} else {
				msg = "没有文件，请重新上传！";
			}
		} else {
			msg = "文件格式有误，请检查！";
		}
		request.setAttribute(JSPConstants.OprtResult, msg);

		return new ModelAndView(JSPConstants.OprtSuccessPage);
	}
	
	/**
	 * 查看回盘结果
	 * @return
	 */
	@RequestMapping("/viewBc.html")
	public ModelAndView viewBc(RebackRecordReq rebackRecordReq, HttpServletRequest request){
		Map<String, Object> paramMap = MapConverter.toMap(rebackRecordReq);
		
		List<BigInteger> bgbcIds = new ArrayList<BigInteger>();
		String[] bgbcIdsStr = rebackRecordReq.getBgbcIds().split(",");
		for(String bgbcIdStr:bgbcIdsStr){
			bgbcIds.add(new BigInteger(bgbcIdStr));
		}
		paramMap.put("bgbcIds", bgbcIds);
		
		int total = bankCollectionService.selectRebackRecordForCount(paramMap);
		
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		// 分页信息
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		List<RebackRecordDto> rebackRecords = bankCollectionService.selectRebackRecordForList(paramMap);
		
		ModelAndView modelAndView = new ModelAndView("/bankCollection/rebackRecord");
		modelAndView.addObject("rebackRecords", rebackRecords);
		modelAndView.addObject("total", total);
		modelAndView.addObject("bgbcIds", rebackRecordReq.getBgbcIds());
		return modelAndView;
	}
	
	/**
	 * 确认回盘完成
	 * @return
	 */
	@RequestMapping("/confirmBc.html")
	@ResponseBody
	public JsonResponse confirmBc(BigInteger bgbcId, BigInteger pcbciId){
		JsonResponse jsonResponse = new JsonResponse();
		//重复操作校验
		String key = RedisCachePrefix.Bank_Collection + "_confirm_" + bgbcId;
		if(RedisCacheHandler.get(key) == null){
			RedisCacheHandler.set(key, bgbcId.toString());
			
			BigInteger userId = UserContext.getOperIdBigIntegerMustExist();
			try{
				ResultMsg resultMsg = bankCollectionService.confirmBc(userId, bgbcId, pcbciId);
				if(resultMsg.isSuccess()){
					jsonResponse.setMessage("操作成功！");
					jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);				
				} else {
					RedisCacheHandler.del(key);
					jsonResponse.setMessage(resultMsg.getMsg());
					jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
				}
			} catch(Exception e){
				RedisCacheHandler.del(key);
				jsonResponse.setMessage("操作失败！");
				jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			}
		} else {
			jsonResponse.setMessage("请勿重复操作！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		}
		return jsonResponse;
	}

	/**
	 * 查询开启托收小区
	 * @param request
	 * @return
	 */
	@RequestMapping("/initEdit.json")
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
		}else{//物业公司主子账号
			paramMap.put("adminId", UserContext.getOperId());
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//获取小区信息
		paramMap.put("gbIdList", UserContext.getGbIdList());
		paramMap.put("openBankCollection", 1);
		List<Map<String, Object>> groupBuildingList = msGroupBuildingService.getBuildingListForSelected(paramMap);
		resultMap.put("gbList", groupBuildingList);

		return JSON.toJSONString(resultMap);
	}
	
	/**
	 * 打印出盘明细
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/printOfferDetail.html")
	public void printOfferDetail(BigInteger bgbcId, BigInteger pcbciId, HttpServletRequest request, HttpServletResponse response) throws Exception{
		PropertyCompanyBankCollectionInfo pcBankCollectionInfo = propertyCompanyBankCollectionInfoBaseService.getPropertyCompanyBankCollectionInfoBySeqId(pcbciId);
		
		// 查询
		List<BcPrintDetail> bcPrintDetailList =  bankCollectionService.getBcPrintDetailByBgbcId(bgbcId);
		
		List<BcPrintSum> bcPrintWithBSumList = new ArrayList<BcPrintSum>(); //bankCollectionService.getBcPrintSumWithBByBgbcId(bgbcId);
		List<BcPrintSum> bcPrintWithGbSumList = new ArrayList<BcPrintSum>(); //bankCollectionService.getBcPrintSumWithGbByBgbcId(bgbcId);
		
		getPrintSumGroup(bcPrintDetailList, bcPrintWithBSumList, bcPrintWithGbSumList);
		
		List<BcPrintDetailDto> data = dealDataForPrint(bcPrintDetailList, bcPrintWithBSumList, bcPrintWithGbSumList);
		
		/**小区名称*/
		BcGroupBuildingCycle bcGroupBuildingCycle = bcGroupBuildingCycleBaseService.getBcGroupBuildingCycleBySeqId(bgbcId);
		String gbNames = bcGroupBuildingCycle.getGbNames();
		if(StringUtils.isNotBlank(gbNames) && gbNames.endsWith(",")){
			gbNames = gbNames.substring(0, gbNames.length()-1);
			gbNames = gbNames.replaceAll(",", "、");
		}
		/**划款方案名称=【托收机构名称】【账单名称】*/
		String title = pcBankCollectionInfo.getBankName().concat(bcGroupBuildingCycle.getPayBillTypeName());
		
		request.setAttribute("now", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
		request.setAttribute("gbNames", gbNames);
		request.setAttribute("title", title);
		request.setAttribute("data", data);
		
		dealPrintForPdf(request, response);
	}

	/**
	 * 根据bcPrintDetailList按小区、按楼栋分组合并数据
	 * @author wenfq 2017-07-20
	 * @param bcPrintDetailList 所有账账单明细数据，已按gbId，bId排序
	 * @param bcPrintWithBSumList 按楼栋合并
	 * @param bcPrintWithGbSumList 按小区合并
	 */
	private void getPrintSumGroup(List<BcPrintDetail> bcPrintDetailList, List<BcPrintSum> bcPrintWithBSumList, List<BcPrintSum> bcPrintWithGbSumList) {
		for (BcPrintDetail bcPrintDetail : bcPrintDetailList) {
			BcPrintSum bcPrintWithBSum = null;
			BcPrintSum bcPrintWithGBSum = null;
			
			if(bcPrintWithBSumList.isEmpty()
					|| !bcPrintWithBSumList.get(bcPrintWithBSumList.size()-1).getbId().equals(bcPrintDetail.getbId()) ){
				bcPrintWithBSum = new BcPrintSum();
				bcPrintWithBSumList.add(bcPrintWithBSum);
			}else{
				bcPrintWithBSum = bcPrintWithBSumList.get(bcPrintWithBSumList.size()-1);
				bcPrintWithBSum.setCount(bcPrintWithBSum.getCount()+1);
			}
			
			if(bcPrintWithGbSumList.isEmpty()
					|| (!bcPrintWithGbSumList.get(bcPrintWithGbSumList.size()-1).getGbId().equals(bcPrintDetail.getGbId()))){
				bcPrintWithGBSum = new BcPrintSum();
				bcPrintWithGbSumList.add(bcPrintWithGBSum);
			}else{
				bcPrintWithGBSum = bcPrintWithGbSumList.get(bcPrintWithGbSumList.size()-1);
				bcPrintWithGBSum.setCount(bcPrintWithGBSum.getCount()+1);
			}
			
			bcPrintWithBSum.setbId(bcPrintDetail.getbId());
			bcPrintWithBSum.setGbId(bcPrintDetail.getGbId());
			bcPrintWithBSum.setName(bcPrintDetail.getbName());
			bcPrintWithBSum.setTotalOutAmount(bcPrintWithBSum.getTotalOutAmount().add(bcPrintDetail.getOutAmount()));
			bcPrintWithBSum.setTotalDealAmount(bcPrintWithBSum.getTotalDealAmount().add(bcPrintDetail.getDealAmount()));
			bcPrintWithBSum.setTotalDealLateAmount(bcPrintWithBSum.getTotalDealLateAmount().add(bcPrintDetail.getDealLateAmount()));
			
			bcPrintWithGBSum.setbId(bcPrintDetail.getbId());
			bcPrintWithGBSum.setGbId(bcPrintDetail.getGbId());
			bcPrintWithGBSum.setName(bcPrintDetail.getGbName());
			bcPrintWithGBSum.setTotalOutAmount(bcPrintWithGBSum.getTotalOutAmount().add(bcPrintDetail.getOutAmount()));
			bcPrintWithGBSum.setTotalDealAmount(bcPrintWithGBSum.getTotalDealAmount().add(bcPrintDetail.getDealAmount()));
			bcPrintWithGBSum.setTotalDealLateAmount(bcPrintWithGBSum.getTotalDealLateAmount().add(bcPrintDetail.getDealLateAmount()));
		}
	}
	
	/**
	 * 输出pdf
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dealPrintForPdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jspOutput = JspToHtmlUtil.getJspOutput("/WEB-INF/jsp/bankCollection/printOfferDetail.jsp", request, response);
		String tmpBaseDirUrl = Html2PdfUtil.getTmpBaseDirUrl();
		// 1、生成pdf
		logger.debug("1、组装html，并生成pdf:"+tmpBaseDirUrl);
		String fileUrl = tmpBaseDirUrl +File.separator+"0.pdf";
		File pdf = new File(fileUrl);
		Html2PdfUtil.writePdf(jspOutput, pdf, "A4");
		
		// 2、合并，并在网页显示PDF
		logger.debug("2、合并，并在网页显示PDF:"+tmpBaseDirUrl);
		Html2PdfUtil.showMergePDF(tmpBaseDirUrl, Arrays.asList(pdf), PrintConfig.BC_PDF_NAME, response);
	}
	
	/**每一页显示的行数*/
	private static final int pageRowCount = 39;
	private List<BcPrintDetailDto> dealDataForPrint(List<BcPrintDetail> bcPrintDetailList, List<BcPrintSum> bcPrintWithBSum, List<BcPrintSum> bcPrintWithGbSum){
		List<BcPrintDetailDto> data = new ArrayList<BcPrintDetailDto>();
		int gbi=0;
		int bi=0;
		int currentGbDetailSize = 0;
		int pageNum = 1;
		if(bcPrintDetailList!=null && bcPrintDetailList.size()>0){
			if(bcPrintDetailList.size()==1){
				BcPrintDetailDto bcPrintDetailDto = new BcPrintDetailDto(bcPrintDetailList.get(0), true, false, pageNum);
				BcPrintSum bcPrintBSum = bcPrintWithBSum.get(bi);
				BcPrintSum bcPrintGSum = bcPrintWithGbSum.get(gbi);
				currentGbDetailSize++;
				dealLastItemForPrint(data, bcPrintDetailDto, bcPrintBSum, bcPrintGSum, currentGbDetailSize, pageNum);
			} else {
				for(int i=0; i<bcPrintDetailList.size(); i++){
					BcPrintDetail bcPrintDetail = bcPrintDetailList.get(i);
					BcPrintSum bcPrintBSum = bcPrintWithBSum.get(bi);
					currentGbDetailSize++;
					if(i<bcPrintDetailList.size()-1){
						boolean isPageStart = (currentGbDetailSize%pageRowCount==1);
						boolean isPageEnd = (currentGbDetailSize%pageRowCount==0);
						BcPrintDetailDto bcPrintDetailDto = new BcPrintDetailDto(bcPrintDetail, isPageStart, isPageEnd, pageNum);
						data.add(bcPrintDetailDto);
						if(isPageEnd){
							pageNum++;
						}
						
						if(bcPrintDetailList.get(i+1).getbId().compareTo(bcPrintBSum.getbId())!=0){
							BcPrintDetail bcPrintDetailWithB = new BcPrintDetail();
							bcPrintDetailWithB.setRoomNo(bcPrintBSum.getName());
							bcPrintDetailWithB.setPpName("小计：");
							bcPrintDetailWithB.setbId(bcPrintBSum.getbId());
							bcPrintDetailWithB.setGbId(bcPrintBSum.getGbId());
							bcPrintDetailWithB.setBankAccount(String.valueOf(bcPrintBSum.getCount()));
							bcPrintDetailWithB.setOutAmount(bcPrintBSum.getTotalOutAmount());
							bcPrintDetailWithB.setDealAmount(bcPrintBSum.getTotalDealAmount());
							bcPrintDetailWithB.setDealLateAmount(bcPrintBSum.getTotalDealLateAmount());
							
							currentGbDetailSize++;
							boolean isPageStartB = (currentGbDetailSize%pageRowCount==1);
							boolean isPageEndB = (currentGbDetailSize%pageRowCount==0);
							BcPrintDetailDto bcPrintDetailBDto = new BcPrintDetailDto(bcPrintDetailWithB, isPageStartB, isPageEndB, pageNum);
							data.add(bcPrintDetailBDto);
							if(isPageEndB){
								pageNum++;
							}
							
							BcPrintSum bcPrintGSum = bcPrintWithGbSum.get(gbi);
							if(bcPrintDetailList.get(i+1).getGbId().compareTo(bcPrintGSum.getGbId())!=0){
								BcPrintDetail bcPrintDetailWithGb = new BcPrintDetail();
								bcPrintDetailWithGb.setRoomNo(bcPrintGSum.getName());
								bcPrintDetailWithGb.setPpName("合计：");
								bcPrintDetailWithGb.setbId(bcPrintGSum.getbId());
								bcPrintDetailWithGb.setGbId(bcPrintGSum.getGbId());
								bcPrintDetailWithGb.setBankAccount(String.valueOf(bcPrintGSum.getCount()));
								bcPrintDetailWithGb.setOutAmount(bcPrintGSum.getTotalOutAmount());
								bcPrintDetailWithGb.setDealAmount(bcPrintGSum.getTotalDealAmount());
								bcPrintDetailWithGb.setDealLateAmount(bcPrintGSum.getTotalDealLateAmount());
								
								currentGbDetailSize++;
								boolean isPageStartGb = (currentGbDetailSize%pageRowCount==1);
								BcPrintDetailDto bcPrintDetailWithGbDto = new BcPrintDetailDto(bcPrintDetailWithGb, isPageStartGb, true, pageNum);
								data.add(bcPrintDetailWithGbDto);
								
								gbi++;
								currentGbDetailSize = 0;
								pageNum = 1;
							}
							bi++;
						}
					} else {// 最后一个
						BcPrintSum bcPrintGSum = bcPrintWithGbSum.get(gbi);
						if(bcPrintDetailList.get(i-1).getGbId().compareTo(bcPrintGSum.getGbId())!=0){
							currentGbDetailSize = 1;
							pageNum = 1;
						}
						boolean isPageStart = (currentGbDetailSize%pageRowCount==1);
						boolean isPageEnd = (currentGbDetailSize%pageRowCount==0);
						BcPrintDetailDto bcPrintDetailDto = new BcPrintDetailDto(bcPrintDetail, isPageStart, isPageEnd, pageNum);
						dealLastItemForPrint(data, bcPrintDetailDto, bcPrintBSum, bcPrintGSum, currentGbDetailSize, pageNum);
					}
				}
			}
			
			dealTotalPageNum(data);
		}
		
		return data;
	}
	
	/**
	 * 处理最后一行
	 * @param data
	 * @param bcPrintDetailDto
	 * @param bcPrintBSum
	 * @param bcPrintGSum
	 * @param currentGbDetailSize
	 * @param pageNum
	 */
	private void dealLastItemForPrint(List<BcPrintDetailDto> data, BcPrintDetailDto bcPrintDetailDto, BcPrintSum bcPrintBSum, 
			BcPrintSum bcPrintGSum, int currentGbDetailSize, int pageNum){
		if(bcPrintDetailDto.isPageEnd()){
			pageNum++;
		}
		data.add(bcPrintDetailDto);
		
		BcPrintDetail bcPrintDetailWithBTmp = new BcPrintDetail();
		bcPrintDetailWithBTmp.setRoomNo(bcPrintBSum.getName());
		bcPrintDetailWithBTmp.setPpName("小计：");
		bcPrintDetailWithBTmp.setbId(bcPrintBSum.getbId());
		bcPrintDetailWithBTmp.setGbId(bcPrintBSum.getGbId());
		bcPrintDetailWithBTmp.setBankAccount(String.valueOf(bcPrintBSum.getCount()));
		bcPrintDetailWithBTmp.setOutAmount(bcPrintBSum.getTotalOutAmount());
		bcPrintDetailWithBTmp.setDealAmount(bcPrintBSum.getTotalDealAmount());
		bcPrintDetailWithBTmp.setDealLateAmount(bcPrintBSum.getTotalDealLateAmount());
		
		currentGbDetailSize++;
		boolean isPageStartB = (currentGbDetailSize%pageRowCount==1);
		boolean isPageEndB = (currentGbDetailSize%pageRowCount==0);
		
		BcPrintDetailDto bcPrintDetailWithBDto = new BcPrintDetailDto(bcPrintDetailWithBTmp, isPageStartB, isPageEndB, pageNum);
		data.add(bcPrintDetailWithBDto);
		
		if(isPageEndB){
			pageNum++;
		}
		
		BcPrintDetail bcPrintDetailWithGbTmp = new BcPrintDetail();
		bcPrintDetailWithGbTmp.setRoomNo(bcPrintGSum.getName());
		bcPrintDetailWithGbTmp.setPpName("合计：");
		bcPrintDetailWithGbTmp.setbId(bcPrintGSum.getbId());
		bcPrintDetailWithGbTmp.setGbId(bcPrintGSum.getGbId());
		bcPrintDetailWithGbTmp.setBankAccount(String.valueOf(bcPrintGSum.getCount()));
		bcPrintDetailWithGbTmp.setOutAmount(bcPrintGSum.getTotalOutAmount());
		bcPrintDetailWithGbTmp.setDealAmount(bcPrintGSum.getTotalDealAmount());
		bcPrintDetailWithGbTmp.setDealLateAmount(bcPrintGSum.getTotalDealLateAmount());
		
		currentGbDetailSize++;
		boolean isPageStartGb = (currentGbDetailSize%pageRowCount==1);
		BcPrintDetailDto bcPrintDetailWithGbDto = new BcPrintDetailDto(bcPrintDetailWithGbTmp, isPageStartGb, true, pageNum);
		data.add(bcPrintDetailWithGbDto);
	}
	
	/**
	 * 处理总页码
	 * @param data
	 */
	private void dealTotalPageNum(List<BcPrintDetailDto> data){
		int totalPageNum = 0;
		int startIndex = 0;
		for(int i=0, size=data.size(); i<size; i++){
			BcPrintDetailDto item = data.get(i);
			if(i!=size-1){
				if(item.isPageEnd()){
					totalPageNum++;
					if(item.getBcPrintDetail().getGbId().compareTo(data.get(i+1).getBcPrintDetail().getGbId())!=0){
						setPotalPageNumByRange(data, startIndex, i, totalPageNum);
						totalPageNum = 0;
						startIndex = i+1;
					}
				}
			} else {
				if(item.isPageEnd()){
					totalPageNum++;
					setPotalPageNumByRange(data, startIndex, i, totalPageNum);
				}
			}
		}
	}
	
	/**
	 * 设置总页码
	 * @param data
	 * @param start
	 * @param end
	 * @param totalPageNum
	 */
	public void setPotalPageNumByRange(List<BcPrintDetailDto> data, int start, int end, int totalPageNum){
		for(int i=start; i<=end; i++){
			if(data.get(i).isPageEnd()){
				data.get(i).setTotalPageNum(totalPageNum);
			}
		}
	}
	
	/**
	 * 造打印测试数据
	 * @param bcPrintDetailList
	 * @param bcPrintWithBSum
	 * @param bcPrintWithGbSum
	 */
	private void createPrintTestData(List<BcPrintDetail> bcPrintDetailList, List<BcPrintSum> bcPrintWithBSum, List<BcPrintSum> bcPrintWithGbSum){
		for(int gbi=0; gbi<3; gbi++){
			BcPrintSum bcPrintGSum = new BcPrintSum();
			BigInteger gbId = BigInteger.valueOf(gbi+1);
			bcPrintGSum.setGbId(gbId);
			bcPrintGSum.setCount(33);
			bcPrintGSum.setName("小区"+gbi);
			bcPrintGSum.setTotalDealAmount(BigDecimal.valueOf(100.01));
			bcPrintGSum.setTotalDealLateAmount(BigDecimal.valueOf(200.1));
			bcPrintGSum.setTotalOutAmount(BigDecimal.valueOf(300.34));

			bcPrintGSum.setCount(11);
			for(int bi=0; bi<3; bi++){
				BcPrintSum bcPrintBSum = new BcPrintSum();
				BigInteger bId = BigInteger.valueOf(bi+1);
				bcPrintBSum.setbId(bId);
				bcPrintBSum.setGbId(gbId);
				bcPrintBSum.setCount(11);
				bcPrintBSum.setName("楼栋"+bi);
				bcPrintBSum.setTotalDealAmount(BigDecimal.valueOf(100.01));
				bcPrintBSum.setTotalDealLateAmount(BigDecimal.valueOf(200.1));
				bcPrintBSum.setTotalOutAmount(BigDecimal.valueOf(300.34));
				
				for(int i=0; i<40; i++){
					BcPrintDetail bcPrintDetail = new BcPrintDetail();
					bcPrintDetail.setRoomNo("xxxx"+i);
					bcPrintDetail.setPpName("业主"+i);
					bcPrintDetail.setBankAccount("NOxxxx"+i);
					bcPrintDetail.setbId(bId);
					bcPrintDetail.setGbId(gbId);
					bcPrintDetail.setOutAmount(BigDecimal.valueOf(i));
					bcPrintDetail.setDealAmount(BigDecimal.valueOf(i));
					bcPrintDetail.setDealLateAmount(BigDecimal.valueOf(i));
					bcPrintDetailList.add(bcPrintDetail);
				}
				bcPrintWithBSum.add(bcPrintBSum);
			}
			bcPrintWithGbSum.add(bcPrintGSum);
		}
	}
}

