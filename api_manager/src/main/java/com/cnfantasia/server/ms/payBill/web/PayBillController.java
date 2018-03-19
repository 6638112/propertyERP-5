package com.cnfantasia.server.ms.payBill.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.commonBusiness.util.CommonRoomUtil;
import com.cnfantasia.server.api.homeMessage.service.IHomeMessageService;
import com.cnfantasia.server.api.lateFee.calculateMethod.CalculateLateFeeByGbImp;
import com.cnfantasia.server.api.lateFee.calculateMethod.CalculateLateFeeByRoomImp;
import com.cnfantasia.server.api.lateFee.util.CalculateLateFeeRunnable;
import com.cnfantasia.server.api.meterReading.constant.FeeTypeDict;
import com.cnfantasia.server.api.plotproperty.util.FinanceDeductionRunnable;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.room.entity.RealRoomEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.file.FileDownloadUtils;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.NumberUtils;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.commbusi.plotproperty.service.IPlotpropertyCfgService;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.*;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.entity.GroupBuildingBillCycle;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.service.IGroupBuildingBillCycleBaseService;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payBillType.dao.IPayBillTypeBaseDao;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.payBillType.service.PayBillTypeBaseService;
import com.cnfantasia.server.domainbase.printConfig.service.IPrintConfigBaseService;
import com.cnfantasia.server.domainbase.printInitTemplate.entity.PrintInitTemplate;
import com.cnfantasia.server.domainbase.printInitTemplate.service.IPrintInitTemplateBaseService;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.ms.buildingRoom.entity.RoomEntity;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.groupBuilding.service.IGroupBuildingService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.payBill.constant.PayBillDict;
import com.cnfantasia.server.ms.payBill.constant.PrintEditKeyBase;
import com.cnfantasia.server.ms.payBill.constant.PrintEditKeyEnum;
import com.cnfantasia.server.ms.payBill.dao.PayBillPrintDao;
import com.cnfantasia.server.ms.payBill.entity.*;
import com.cnfantasia.server.ms.payBill.service.PayBillPrintService;
import com.cnfantasia.server.ms.pd4ml.util.Html2PdfUtil;
import com.cnfantasia.server.ms.pd4ml.util.PrintConfig;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.FutureTask;

@Controller
@RequestMapping("/payBill")
public class PayBillController extends AbstractPayBillController{
	private Log logger = LogFactory.getLog(getClass());

	@Resource
	private IPlotpropertyCfgService plotpropertyCfgService;
	@Resource
	private IGroupBuildingBillCycleBaseService groupBuildingBillCycleBaseService;
	@Resource
	private IPayBillTypeBaseDao payBillTypeBaseDao;
	@Resource
	private IGroupBuildingService groupBuildingService;
	@Resource
	private PayBillPrintService payBillPrintService;
	@Resource
	private IHomeMessageService homeMessageService;
	@Resource
	private IPrintInitTemplateBaseService printInitTemplateBaseService;
	@Resource
	private PayBillPrintDao payBillPrintDao;
	@Resource
	private IPrintConfigBaseService printConfigBaseService;
	@Resource
	private IUuidManager uuidManager;
	
	/**
	 * 周期账单列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/listPeriod.html")
	public ModelAndView listPeriodPayBill(HttpServletRequest request) {
		long l = System.currentTimeMillis();
		//getGbToRequest(request);
		request.setAttribute("gbList", UserContext.getGbIdList());

		Map<String, Object> paramMap = searchBaseInitMap(request);
		paramMap.put("omsUserId", UserContext.getOperId());
		paramMap.put("omsUserName", UserContext.getCurrUser().getRealName());
		paramMap.put("isadmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isBusiness", this.isBusiness());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		
		paramMap.put("buyerId", request.getParameter("buyerId"));
		
		paramMap.put("bName", request.getParameter("bName"));
		paramMap.put("unitName", request.getParameter("unitName"));
		paramMap.put("roomNum", request.getParameter("roomNum"));
		paramMap.put("payDayStart", request.getParameter("payDayStart"));
		paramMap.put("payDayEnd", request.getParameter("payDayEnd"));
		
		paramMap.put("cycleType", request.getParameter("cycleType"));
		//账期ID
		paramMap.put("cycleId", request.getParameter("cycleId"));
		//抄表条件
		paramMap.put("pbtIsPropfee", request.getParameter("pbtIsPropfee"));
		//收费模式 1：抄表收费，2：固定收费，3：临时收费
		paramMap.put("feeType", request.getParameter("feeType"));
		//非收益中心
		paramMap.put("isRevenue", false);
		
		if (request.getParameter("billMonthStart") != null) {
			paramMap.put("billMonthStart",
					DateUtils.convertDateToStr(DateUtils.convertStrToDate(request.getParameter("billMonthStart"), "yyyy-MM"), "yyyy-MM-dd"));
		} else if(request.getParameter("billMonthEnd") == null && request.getParameter("gbId") == null) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -2);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			Date dateStart = ParamUtils.getDate(request, "dateStart", cal.getTime(), "yyyy-MM");
			paramMap.put("billMonthStart", DateUtils.convertDateToStr(dateStart, "yyyy-MM-dd"));
			request.setAttribute("billMonthStart", DateUtils.convertDateToStr(dateStart, "yyyy-MM"));
		}

		if (request.getParameter("billMonthEnd") != null) {
			paramMap.put("billMonthEnd", DateUtils.convertDateToStr(DateUtils.convertStrToDate(request.getParameter("billMonthEnd"), "yyyy-MM"), "yyyy-MM-dd"));
		}

		paramMap.put("billTypeName", request.getParameter("billTypeName"));
		Integer isReport = ParamUtils.getInteger(request, "isReport", 0);
		Integer isUnpaid = ParamUtils.getInteger(request, "isUnpaid", 0);
		//收费一览表查询
		if(!DataUtil.isEmpty(isReport) && isReport == 1) {
			String month = (String) paramMap.get("billMonthStart");
			paramMap.put("isUnpaid", isUnpaid);
			paramMap.put("billMonthStart", month);
			paramMap.put("isReport", isReport);
		}

		String searchFrom = ParamUtils.getString(request, "searchFrom");
		//菜单查询，默认账单起始时间：所带月为当前月
		if("menu".equals(searchFrom)) {
			String currentDateStr = DateUtils.getCurrentDateStr("yyyy-MM");
			paramMap.put("billMonthStart", currentDateStr);
			request.setAttribute("billMonthStart", currentDateStr);
		}

		long l12 = System.currentTimeMillis();
		logger.info("[pay bill list get time1:]" + (l12 - l));
		int resultSize = payBillService.getPayBill_byUserId_forCount(paramMap);
		long l13 = System.currentTimeMillis();
		logger.info("[pay bill list get time2:]" + (l13 - l12));
		request.setAttribute("resultSize", resultSize);

		//存储查询条件，导出数据时要用到，见exportPayBill()方法
		request.getSession().setAttribute("payBillListQueryParamMap", ((HashMap<String, Object>) paramMap).clone());
		
		PageUtils.addPageInfoToParam(request, paramMap);
		long l14 = System.currentTimeMillis();
		List<PayBillEntity> searchRestList = payBillService.getPayBillList_byUserId_forPage(paramMap);
		long l15 = System.currentTimeMillis();
		logger.info("[pay bill list get time3:]" + (l15 - l14));
		request.setAttribute("resList", searchRestList);
		request.setAttribute("gbId", request.getParameter("gbId"));
		request.setAttribute("pageType", request.getParameter("pageType"));
		request.setAttribute("cycleType", request.getParameter("cycleType"));
		request.setAttribute("feeType", request.getParameter("feeType"));
		long l1 = System.currentTimeMillis();
		logger.info("[pay bill list get time4:]" + (l1 - l15));

		return new ModelAndView("/payBill/payBillPeriodList"); 
	}
	
	@Resource
	PayBillTypeBaseService payBillTypeBaseService;

	@RequestMapping("/qryPayBillTypeListByGbId.html")
	@ResponseBody
	public String getPayBillTypeByGbId(HttpServletRequest request){
		long gbId = ParamUtils.getLong(request, "gbId", -1L);
		return JSON.toJSONString(plotpropertyCfgService.getPayBillTypeListAll(new BigInteger(gbId+""),false));
	}

	/**
	 * 编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit.html")
	public ModelAndView edit(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/payBill/payBillEdit");
		return modelAndView;
	}

	/**
	 * 查看明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewDetail.html")
	public ModelAndView viewDetail(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		BigInteger payBillId = ParamUtils.getBigInteger(request, "id", null);
		PayBillWithFeeDetailEntity payBillImportedEntity = payBillService.getPayBillForDetail(payBillId);
		modelAndView.addObject("payBill", payBillImportedEntity);
		List<PropertyFeeDetail> pfds = payBillImportedEntity.getPropertyFeeDetails();

		//欠费合计
		BigDecimal totalUnpaid= BigDecimal.ZERO;
		//存在欠费
		if(payBillImportedEntity.getCollectArrears() == 2 && payBillImportedEntity.isUnpaidFee()) {
			totalUnpaid = payBillService.getUnpaidAmtById(payBillImportedEntity.getId());
		}
		//本月应缴合计
		BigDecimal totalSuccAmount = BigDecimalUtil.div100(payBillImportedEntity.getAmount(), 2, BigDecimal.ROUND_HALF_UP);
		//费用总计
		BigDecimal totalAmounts = totalSuccAmount.add(totalUnpaid);


		if(pfds!=null && pfds.size()>0){
			// 固定收费
			List<PropertyFeeDetail> fixedPfds = new ArrayList<PropertyFeeDetail>();
			// 临时收费
			List<PropertyFeeDetail> tmpPfds = new ArrayList<PropertyFeeDetail>();
			// 选择周期收费项
			List<PropertyFeeDetail> alterFeeDetails = new ArrayList<PropertyFeeDetail>();
			// 第三方
			List<PropertyFeeDetail> thrdFeeDetails = new ArrayList<PropertyFeeDetail>();
			//滞纳金
			List<PropertyFeeDetail> lateFeeDetails = new ArrayList<PropertyFeeDetail>();
			Double fixedTotal = 0.0;
			Double mrTotal = 0.0;
			Double tmpdTotal = 0.0;
			Double alterFeeDetailTotal = 0.0;
			//V519以前使用的  不知道干啥的 没有复用yh
			Double lateFee = 0.0;
			BigDecimal lateFeeTotal = BigDecimal.ZERO;
			Double thrdTotal = 0.0;
			if(null!=pfds && pfds.size()>0){
				 //显示顺序要与【收费项设置】的顺序一致
				for(int index=pfds.size()-1; index>=0; index--){
					PropertyFeeDetail pfd = pfds.get(index);
					if(FeeTypeDict.Gu_Ding.equals(pfd.getFeeType())){
						fixedPfds.add(pfd);
						fixedTotal += pfd.getTotalPrice();
					} else if(FeeTypeDict.Lin_Shi.equals(pfd.getFeeType())){
						tmpPfds.add(pfd);
						tmpdTotal += pfd.getTotalPrice();
					} else if(FeeTypeDict.Xuan_Ze_Zhou_Qi.equals(pfd.getFeeType())) {//选择周期借用临时收费数组存
						alterFeeDetails.add(pfd);
						alterFeeDetailTotal += pfd.getTotalPrice();
						if("滞纳金".equals(pfd.getName())) {
							lateFee = pfd.getTotalPrice();
						}
					} else if(pfd.getFeeType() == null || "".equals(pfd.getFeeType())) {
						thrdTotal += pfd.getTotalPrice();
						thrdFeeDetails.add(pfd);
					} else if(FeeTypeDict.LATEFEE.equals(pfd.getFeeType())) {//滞纳金
						lateFeeDetails.add(pfd);
                        BigDecimal bigDecimal = BigDecimal.valueOf(pfd.getTotalPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);
                        lateFeeTotal = lateFeeTotal.add(bigDecimal);
					}
				}
			}

			//抄表
			List<BillMrDetail> billMrDetails = payBillService.selectBillDetailForMr(payBillId);
			//显示顺序要与【收费项设置】的顺序一致
			Collections.reverse(billMrDetails);
			modelAndView.addObject("billMrDetails", billMrDetails);
			BigDecimal mrTotalBid = new BigDecimal(0);
			for(BillMrDetail billMrDetail:billMrDetails){
				mrTotalBid = mrTotalBid.add(billMrDetail.getTotalPrice());
			}
			if(mrTotalBid != null) {
				mrTotal += mrTotalBid.doubleValue();
			}
			modelAndView.addObject("mrTotal", mrTotal);

			//固定
			modelAndView.addObject("fixedPfds", fixedPfds);
			modelAndView.addObject("fixedTotal", fixedTotal);

			//临时
			modelAndView.addObject("tmpPfds", tmpPfds);
			Double totalAmount = null;
			if(tmpPfds!=null && tmpPfds.size()>0){
				totalAmount = tmpPfds.get(0).getTotalAmount();
			}
			if(totalAmount!=null && totalAmount>0){
				modelAndView.addObject("tmpdTotal", totalAmount);
			} else {
				modelAndView.addObject("tmpdTotal", tmpdTotal);
			}

			//选择周期
			modelAndView.addObject("alterFeeDetails", alterFeeDetails);
			//总费用 = （alterFeeDetailTotal - 滞纳金）*缴费时长 + 滞纳金
			modelAndView.addObject("alterFeeDetailTotal", (alterFeeDetailTotal - lateFee )* payBillImportedEntity.getBillMonthSize() + lateFee);

			//第三方缴费（极致）
			modelAndView.addObject("thrdFeeDetails", thrdFeeDetails);
			modelAndView.addObject("thrdTotal", thrdTotal);

			//滞纳金
			lateFeeTotal = BigDecimalUtil.div100(lateFeeTotal).setScale(2, BigDecimal.ROUND_HALF_UP);
			modelAndView.addObject("lateFeeDetails", lateFeeDetails);
			modelAndView.addObject("lateFeeTotal", lateFeeTotal);
		}

		if(payBillImportedEntity!=null){
			String billMonthStart = payBillImportedEntity.getBillMonthStart();
			String billMonthend = payBillImportedEntity.getBillMonthEnd();

			if(StringUtils.isNotBlank(billMonthStart) && StringUtils.isNotBlank(billMonthend)){
				DateTimeFormatter format = DateTimeFormat.forPattern("yyyy年MM月");
				DateTime startTime = DateTime.parse(billMonthStart, format);
				DateTime endTime = DateTime.parse(billMonthend, format);
				Months months = Months.monthsBetween(startTime, endTime);
				modelAndView.addObject("months", months.getMonths()+1);
			}
		}

		modelAndView.addObject("totalAmounts", totalAmounts);
		modelAndView.addObject("totalSuccAmount", totalSuccAmount);
		modelAndView.addObject("totalUnpaid", totalUnpaid);
		modelAndView.addObject("payBillWithPayRecored", payBillService.select_payBill_with_payRecord(payBillId.toString()));
		modelAndView.setViewName("/payBill/payBillDetail");

		return modelAndView;
	}
	
	/**
	 * 打印
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws InvalidParameterException 
	 */
	@RequestMapping("/print.html")
	public ModelAndView print(HttpServletRequest request, HttpServletResponse response) throws InvalidParameterException, IOException {
		BigInteger id = ParamUtils.getBigInteger(request, "id", null);
		Long feePrintId = ParamUtils.getLong(request, "feePrintId", null);
		//账单详情页打印
		if(id != null) {
			String feeType = request.getParameter("feeType");
			BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
			PrintTemplateEntity printTemplateEntity = payBillPrintService.getPrintTemplateByGbId(gbId);
			/**
			 * 配有打印模板的按模板打印；没有的按正规打印
			 */
			String printTemplate = null;
			if(printTemplateEntity!=null){
				printTemplate = payBillPrintService.getInitPrintTemplate(gbId.toString(), id, feeType);
			} else {
				printTemplate = payBillPrintService.getCommPrintTemplate(gbId, id, feeType);
			}
			// 生成html，并输出
			String html = Html2PdfUtil.getHtml(printTemplate);
			String tmpBaseDirUrl = Html2PdfUtil.getTmpBaseDirUrl();
			// 1、生成pdf
			logger.debug("1、组装html，并生成pdf:"+tmpBaseDirUrl);
			String fileUrl = tmpBaseDirUrl +File.separator+"0.pdf";
			File pdf = new File(fileUrl);
			Html2PdfUtil.writePdf(html, pdf, request.getParameter("pageSize"));
			
			// 2、合并，并在网页显示PDF
			logger.debug("2、合并，并在网页显示PDF:"+tmpBaseDirUrl);
			Html2PdfUtil.showMergePDF(tmpBaseDirUrl, Arrays.asList(pdf), PrintConfig.PDF_NAME, response);
			return null;
		} else if(feePrintId != null) { // “账单打印管理”-“账单查询”页面打印
			String viewName = payBillPrintService.printForQry(feePrintId, request);
			return new ModelAndView(viewName);
		} else {// “账单打印管理”-“账单查询”页面打印“空白页”
			String viewName = payBillPrintService.printBlank(request);
			return new ModelAndView(viewName);
		}
	}
	
	/**
	 * 录入账单页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addAcountPage.html")
	public ModelAndView addAcountPage(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		paramMap.put("adminId", UserContext.getOperId());
		
		List<GroupBuilding> groupBuildingList = payBillService.getGroupBuildingByOmsId(paramMap);
		
		
		request.setAttribute("groupBuildingList", groupBuildingList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/payBill/accountAdd");
		return modelAndView;
	}
	
	@RequestMapping("/getSelectBuildingList.html")
	@ResponseBody
	public String getSelectBuildingList(Long id) throws Exception {
		try {
			List<SelectDo> buildingList = feePrintService.getSelectBuildingList(id);
			
			return JSON.toJSONString(buildingList);
		} catch (Exception e) {
			logger.error(e);
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("msg", "后台异常！");
			msg.put("status", "error");
			return JSON.toJSONString(msg);
		}
	}
	
	@RequestMapping("/getSelectUnitNameList.html")
	@ResponseBody
	public String getSelectUnitNameList(Long id) throws Exception {
		try {
			List<SelectDo> buildingList = feePrintService.getSelectUnitNameList(id);
			
			return JSON.toJSONString(buildingList);
		} catch (Exception e) {
			logger.error(e);
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("msg", "后台异常！");
			msg.put("status", "error");
			return JSON.toJSONString(msg);
		}
	}
	
	@RequestMapping("/getAddr.html")
	@ResponseBody
	public Object getAddr(HttpServletRequest request) throws Exception {
		JsonResponse message = new JsonResponse();
		BigInteger realRoomId = ParamUtils.getBigInteger(request, "realRoomId", null);
		try {
			if(realRoomId == null) {
				message.setStatus("error");
				message.setMessage("请选择正确的门牌号！");
				return message;
			}
			
			RealRoomEntity realRoom = feePrintService.selectRealRoomById(realRoomId);
			message.putData("addr", CommonRoomUtil.getAddressDetail(realRoom));
			message.setStatus("info");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message.setMessage(e.getMessage());
			message.setStatus("error");
		}
		return message;
	}
	
	@RequestMapping("/getSelectRoomNumList.html")
	@ResponseBody
	public String getSelectRoomNumList(Long buildingId, String unitName) throws Exception {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("buildingId", buildingId);
			paramMap.put("unitName", unitName);
			List<SelectDo> buildingList = feePrintService.getSelectRoomNumList(paramMap);
			
			return JSON.toJSONString(buildingList);
		} catch (Exception e) {
			logger.error(e);
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("msg", "后台异常！");
			msg.put("status", "error");
			return JSON.toJSONString(msg);
		}
	}
	
	/**
	 * 录入账单页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addAcount.html")
	@ResponseBody
	public String addAcount(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		try {
			Long realRoomId = ParamUtils.getLong(request, "realRoomId", null);
			String addr = ParamUtils.getString(request, "addr", null);
			String accountName = ParamUtils.getString(request, "accountName", null);
			String accountType = ParamUtils.getString(request, "accountType", null);
			String accountMonth = ParamUtils.getString(request, "accountMonth", null);
			String account = ParamUtils.getString(request, "account", null);
			String payTime = ParamUtils.getString(request, "payTime", null);
			String[] detailFeeName = request.getParameterValues("detailFeeName");
			String[] detailFeeAmount = request.getParameterValues("detailFeeAmount");
			
			if(realRoomId != null) {
				FeePrint feePrint = new FeePrint();
				feePrint.setRealRoomId(realRoomId);
				feePrint.setAddr(addr);
				feePrint.setAccountName(accountName);
				feePrint.setAccountType(accountType);
				feePrint.setAccountMonth(accountMonth);
				feePrint.setAccount(account);
				feePrint.setPayTm(DateUtils.convertStrToDate(payTime, "yyyy-MM-dd HH:mm"));
				
				List<FeeDetail> feeDetailList = new ArrayList<FeeDetail>();
				//第1个是无效数据，故从下标从1开始
				for(int i = 1; i < detailFeeAmount.length; i++){
					FeeDetail feeDetail = new FeeDetail();
					feeDetail.setName(detailFeeName[i]);
					feeDetail.setAmount(new BigDecimal(detailFeeAmount[i]));
					feeDetailList.add(feeDetail);
				}
				feePrint.setFeeDetail(JSON.toJSONString(feeDetailList));
				
				feePrintService.insertFeePrint(feePrint);
			} else {
				jsonResponse.setErrcode(CommConstants.ResponseStatus.VALIDATE_ERR);
				jsonResponse.setMessage("门牌号不能为空！");
			}
		} catch(Exception e) {
			jsonResponse.setErrcode(CommConstants.ResponseStatus.SYSTEM_ERR);
			jsonResponse.setMessage("系统异常，请联系管理员！");
		}
		
		return JSON.toJSONString(jsonResponse);
	}
	
	/**
	 *　查看账单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/acountList.html")
	public ModelAndView acountList(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String groupBuild = ParamUtils.getString(request, "groupBuild", null);
		String building = ParamUtils.getString(request, "building", null);
		String unitName = ParamUtils.getString(request, "unitName", null);
		String roomNum = ParamUtils.getString(request, "roomNum", null);
		String accountName = ParamUtils.getString(request, "accountName", null);
		String accountMonth = ParamUtils.getString(request, "accountMonth", null);
		paramMap.put("groupBuild", groupBuild);
		paramMap.put("building", building);
		paramMap.put("unitName", unitName);
		paramMap.put("roomNum", roomNum);
		paramMap.put("accountName", accountName);
		paramMap.put("accountMonth", accountMonth);
		
		paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		paramMap.put("adminId", UserContext.getOperId());
		//只查当前登录用户所管理的小区
		List<GroupBuilding> groupBuildingList = payBillService.getGroupBuildingByOmsId(paramMap);
		paramMap.put("groupBuildingList", groupBuildingList);
		PageUtils.addPageInfoToParam(request, paramMap);
		
		List<FeePrint> feePrintList = feePrintService.getFeePrintList(paramMap);
		int resultSize = feePrintService.getFeePrintListCount(paramMap);
		
		request.setAttribute("feePrintList", feePrintList);
		request.setAttribute("resultSize", resultSize);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/payBill/payAcountList");
		return modelAndView;
	}

	/**
	 * 导入账单(月度物业费)
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/importPayBill02.html")
	public ModelAndView importPayBill02(HttpServletRequest request) throws Exception {
		String result = "导入成功";
		if (request instanceof MultipartHttpServletRequest) {
			// 转型为Spring的MultipartHttpRequest(重点的所在)
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			MultipartFile uploadExcelfile = multipartRequest.getFile("excelFile");
			HSSFWorkbook wb = new HSSFWorkbook(uploadExcelfile.getInputStream());
			HSSFSheet sheet = wb.getSheetAt(0);
			//从第5行（含）开始导入数据
			int startRow = 4;
			//从第7列（含）开始，
			int feeDetailColumnStart = 5;
			//从第33列（含）结束，
			int feeDetailColumnEnd = 31;
			//每3列是一份缴费明细
			int feeDetailInterval = 3;
			
			//获取账单月份
			String billCycleId = request.getParameter("billCycleId");
			GroupBuildingBillCycle groupBuildingBillCycle = groupBuildingBillCycleBaseService.getGroupBuildingBillCycleBySeqId(BigInteger.valueOf(Long.parseLong(billCycleId)));
			String billMonth = groupBuildingBillCycle.getBillMonth();
			List<PayBillWithFeeDetailEntity> payBills = new ArrayList<PayBillWithFeeDetailEntity>();
			String gbId = HSSFCellUtil.getStringValue(sheet.getRow(0).getCell(0));
			if(gbId == null) {
				request.setAttribute(JSPConstants.OprtResult, "账期模板不正确，请重新下载模板，A1缺少小区id！");
			} else if(!BigInteger.valueOf(Long.parseLong(gbId)).equals(groupBuildingBillCycle.gettGroupBuildingId())) {
				request.setAttribute(JSPConstants.OprtResult, "A1小区id信息不匹配，请重新下载模板！");
			} else if(groupBuildingBillCycle == null) {
				request.setAttribute(JSPConstants.OprtResult, "账期不存在，请重新下载模板！");
			} else if(!groupBuildingBillCycle.getPaytimeType().equals(1)){
				request.setAttribute(JSPConstants.OprtResult, "账期模板不正确，请重新下载模板！");
			} else {
				if(billMonth!=null && !"".equals(billMonth)) {
					for (int i = startRow; i < sheet.getLastRowNum() + 1; i++) {
						PayBillWithFeeDetailEntity payBill = new PayBillWithFeeDetailEntity();
						payBill.setIsPay(PayBillDict.isPayed_no);
						//处理空行的情况，有可能用户没有删除空白行
						if (sheet.getRow(i) == null || sheet.getRow(i).getCell(0) == null) {
							continue;
						}
						
						payBill.setMonth(billMonth);
						//小区名称
						payBill.setGroupBuildingName("" + sheet.getRow(i).getCell(0).getStringCellValue().trim());
						payBill.settBillCycleId(groupBuildingBillCycle.getId());
						//字符串
						if (sheet.getRow(i).getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING) {
							payBill.setBuildingName(sheet.getRow(i).getCell(1).getStringCellValue());
						} else {//数字
							payBill.setBuildingName("" + (int) sheet.getRow(i).getCell(1).getNumericCellValue());
						}

						//单元可能为空
						if (sheet.getRow(i).getCell(2) == null) {
							payBill.setRealRoomUnitName("");
						} else {
							if (sheet.getRow(i).getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING) {
								payBill.setRealRoomUnitName(sheet.getRow(i).getCell(2).getStringCellValue());
							} else {
								if ((int) sheet.getRow(i).getCell(2).getNumericCellValue() > 0) {
									payBill.setRealRoomUnitName("" + (int) sheet.getRow(i).getCell(2).getNumericCellValue());
								} else {
									payBill.setRealRoomUnitName("");
								}
							}
						}
						
						if (sheet.getRow(i).getCell(3).getCellType() == HSSFCell.CELL_TYPE_STRING) {
							payBill.setRealRoomNum(sheet.getRow(i).getCell(3).getStringCellValue());
						} else {
							payBill.setRealRoomNum("" + (int) sheet.getRow(i).getCell(3).getNumericCellValue());
						}

						//每3列是一份缴费明细
						for (int j = feeDetailColumnStart; j <= feeDetailColumnEnd; j += feeDetailInterval) {
							PropertyFeeDetail pfd = new PropertyFeeDetail();
							int type = (j - feeDetailColumnStart) / feeDetailInterval + 1;
							pfd.setType(type);
							/*
							 * 之前预留的type=6,7,8都已经用上，type=9是其它，所以不需要特殊处理了。 if (type == 6)
							 * {//最后一个用“其它费用”代替 枚举代号是9 pfd.setType(PayBillDict
							 * .PropertyFeeDetailDict.FeeType_Other); }
							 */
							// 取第3行中的费用类型名称
							pfd.setName(sheet.getRow(2).getCell(j).getStringCellValue());
							
							if (sheet.getRow(i).getCell(j) == null || sheet.getRow(i).getCell(j).getNumericCellValue() <= 0) {
								//总价 为空或0时，跳过不需要保存
								continue;
							} else {
								//总价 金额要4舍5入到分
								pfd.setTotalPrice(DataUtil.isEmpty(sheet.getRow(i).getCell(j)) ? 0 : sheet.getRow(i).getCell(j).getNumericCellValue()*100);
							}
							
							if (sheet.getRow(i).getCell(j + 1) == null || sheet.getRow(i).getCell(j + 1).getNumericCellValue() <= 0) {
								//单价
								pfd.setSignalPrice(null);
							} else {
								//单价
								pfd.setSignalPrice((sheet.getRow(i).getCell(j + 1).getNumericCellValue()));
							}
							
							if (sheet.getRow(i).getCell(j + 2) == null || sheet.getRow(i).getCell(j + 2).getNumericCellValue() <= 0) {
								//用量
								pfd.setPriceUnitValue(null);
							} else {
								//用量
								pfd.setPriceUnitValue(DataUtil.isEmpty(sheet.getRow(i).getCell(j + 2)) ? 0 : NumberUtils.doubleM100ToLong(sheet.getRow(i).getCell(j + 2).getNumericCellValue()));
								//计量单位
								pfd.setPriceUnitName(sheet.getRow(3).getCell(j + 2).getStringCellValue());
							}
							pfd.setAllowancePrice(0L);
							
							payBill.addPropertyFeeDetail(pfd);
						}
						if(null!=sheet.getRow(i) && null!=sheet.getRow(i).getCell(feeDetailColumnEnd + 1)){
							payBill.setAmount(DataUtil.isEmpty(sheet.getRow(i).getCell(feeDetailColumnEnd + 1)) ? 0 : NumberUtils.doubleM100ToLong(sheet.getRow(i).getCell(feeDetailColumnEnd + 1).getNumericCellValue()));
						}
						payBills.add(payBill);
					}
					result = payBillService.verifyImportPayBill(payBills);
					request.setAttribute(JSPConstants.OprtResult, result);
					if(!payBills.isEmpty()){
						payBillService.saveImportPayBill(payBills);
					}
				} else {
					request.setAttribute(JSPConstants.OprtResult, "账期账单月份不存在，请重新下载模板！");
				}
			}
			
			FutureTask<Boolean> task = new FutureTask<Boolean>(new FinanceDeductionRunnable(financeService, null));
			new Thread(task).start();
		}
		
		request.setAttribute(JSPConstants.ToURL, "../groupBuildingBillCycle/billCycleList.html?paytimeType=1");
		return new ModelAndView(JSPConstants.OprtSuccessPage);
	}
	
	/**
	 * 导入账单(周期账单)
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/importPayBillPeriod02.html")
	public ModelAndView importPayBillPeriod02(HttpServletRequest request) throws Exception {
		String result = "导入成功";
		if (request instanceof MultipartHttpServletRequest) {
			// 转型为Spring的MultipartHttpRequest(重点的所在)
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			MultipartFile uploadExcelfile = multipartRequest.getFile("excelFile");
			HSSFWorkbook wb = new HSSFWorkbook(uploadExcelfile.getInputStream());
			HSSFSheet sheet = wb.getSheetAt(0);
			//从第6行（含）开始导入数据
			int startRow = 5;
			//从第10列（含）开始，
			int feeDetailColumnStart = 5;
			//从第36列（含）结束，
			int feeDetailColumnEnd = 31;
			//每3列是一份缴费明细
			int feeDetailInterval = 3;
			String gbId = request.getParameter("importGbId");
			String billCycleId = request.getParameter("billCycleId");
			String verifyResult = verifyPayBillPeriod02(sheet, BigInteger.valueOf(Long.parseLong(gbId)), BigInteger.valueOf(Long.parseLong(billCycleId)));
			if("通过校验".equals(verifyResult)){
				request.setAttribute(JSPConstants.OprtResult,"校验失败："+ verifyResult);
				request.setAttribute(JSPConstants.ToURL, "../groupBuildingBillCycle/billCycleList.html?paytimeType=2");
				return new ModelAndView(JSPConstants.OprtSuccessPage);
			}
			
			//获取账单月份
			GroupBuildingBillCycle groupBuildingBillCycle = groupBuildingBillCycleBaseService.getGroupBuildingBillCycleBySeqId(BigInteger.valueOf(Long.parseLong(billCycleId)));
			String billMonthStart = groupBuildingBillCycle.getBillMonthStart();
			String billMonthEnd = groupBuildingBillCycle.getBillMonthEnd();
			String payDayStart = groupBuildingBillCycle.getBillPayStart();
			String payDayEnd = groupBuildingBillCycle.getBillPayEnd();
			
			List<PayBillWithFeeDetailEntity> payBills = new ArrayList<PayBillWithFeeDetailEntity>();
			for (int i = startRow; i < sheet.getLastRowNum() + 1; i++) {
				PayBillWithFeeDetailEntity payBill = new PayBillWithFeeDetailEntity();
				payBill.setIsPay(PayBillDict.isPayed_no);
				
				boolean isEmptyRow = isEmptyRow(sheet, i);
				
				if(isEmptyRow) {
					continue;
				}
				
				payBill.setBillTypeName(sheet.getRow(0).getCell(0).getRichStringCellValue().getString());
				
				int columnIndex = 0;
				
				payBill.setPayDayStart(payDayStart);
				payBill.setPayDayEnd(payDayEnd);
				payBill.setBillMonthStart(billMonthStart);
				payBill.setBillMonthEnd(billMonthEnd);
				payBill.settBillCycleId(groupBuildingBillCycle.getId());
				payBill.setBillTimeCfgId(groupBuildingBillCycle.gettPayBillTimeCfgId());
				payBill.setPaytimeType(groupBuildingBillCycle.getPaytimeType());
				
				payBill.setBillMonthSize((long)DateUtils.getDiffMonths(DateUtils.convertStrToDate(billMonthStart), DateUtils.convertStrToDate(billMonthEnd))+1);
				//小区名称
				payBill.setGroupBuildingName("" + sheet.getRow(i).getCell(columnIndex++).getStringCellValue().trim());
				//小区Id
				payBill.setGroupBuildingId(request.getParameter("importGbId"));
				
				HSSFCell buildingCell = sheet.getRow(i).getCell(columnIndex++);
				//字符串
				if (buildingCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					payBill.setBuildingName(buildingCell.getStringCellValue());
				} else {//数字
					payBill.setBuildingName("" + (int) buildingCell.getNumericCellValue());
				}
				
				HSSFCell unitCell = sheet.getRow(i).getCell(columnIndex++);
				//单元可能为空
				if (unitCell == null) {
					payBill.setRealRoomUnitName("");
				} else {
					if (unitCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						payBill.setRealRoomUnitName(unitCell.getStringCellValue());
					} else {
						if ((int) unitCell.getNumericCellValue() > 0) {
							payBill.setRealRoomUnitName("" + (int) unitCell.getNumericCellValue());
						} else {
							payBill.setRealRoomUnitName("");
						}
					}
				}
				
				HSSFCell realRoomCell = sheet.getRow(i).getCell(columnIndex++);
				if (realRoomCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					payBill.setRealRoomNum(realRoomCell.getStringCellValue());
				} else {
					payBill.setRealRoomNum("" + (int) realRoomCell.getNumericCellValue());
				}
				//每3列是一份缴费明细
				for (int j = feeDetailColumnStart; j <= feeDetailColumnEnd; j += feeDetailInterval) {
					PropertyFeeDetail pfd = new PropertyFeeDetail();
					// int type = (j - feeDetailColumnStart) / feeDetailInterval + 1;
					// 取第3行中的费用类型名称
					pfd.setName(HSSFCellUtil.getStringValue(sheet.getRow(3).getCell(j)));
					
					pfd.setType(PayBillDict.PropertyFeeDetailDict.FeeType_Other);
					
					if (sheet.getRow(i).getCell(j) == null || sheet.getRow(i).getCell(j).getNumericCellValue() <= 0) {
						//总价 为空或0时，跳过不需要保存
						continue;
					} else {
						//总价金额要4舍5入到分
						pfd.setTotalPrice(DataUtil.isEmpty(sheet.getRow(i).getCell(j)) ? 0 : sheet.getRow(i).getCell(j).getNumericCellValue()*100);
					}
					
					if (sheet.getRow(i).getCell(j + 1) == null || sheet.getRow(i).getCell(j + 1).getNumericCellValue() <= 0) {
						//单价
						pfd.setSignalPrice(null);
					} else {
						//单价
						pfd.setSignalPrice(sheet.getRow(i).getCell(j + 1).getNumericCellValue());
					}
					
					if (sheet.getRow(i).getCell(j + 2) == null || sheet.getRow(i).getCell(j + 2).getNumericCellValue() <= 0) {
						//用量
						pfd.setPriceUnitValue(null);
					} else {
						//用量
						pfd.setPriceUnitValue(DataUtil.isEmpty(sheet.getRow(i).getCell(j + 2)) ? 0 : NumberUtils.doubleM100ToLong(sheet.getRow(i).getCell(j + 2).getNumericCellValue()));
						//计量单位
						pfd.setPriceUnitName(sheet.getRow(4).getCell(j + 2).getStringCellValue());
					}
					pfd.setAllowancePrice(0L);
					
					payBill.addPropertyFeeDetail(pfd);
				}
				if(null!=sheet.getRow(i) && null!=sheet.getRow(i).getCell(feeDetailColumnEnd + 1)){
					payBill.setAmount(DataUtil.isEmpty(sheet.getRow(i).getCell(feeDetailColumnEnd + 1)) ? 0 : NumberUtils.doubleM100ToLong(sheet.getRow(i).getCell(feeDetailColumnEnd + 1).getNumericCellValue()));
				}
				payBills.add(payBill);
			}
			
			result = payBillService.saveImportPayBillPeriod(payBills);
		}
		
		request.setAttribute(JSPConstants.OprtResult, result);
		request.setAttribute(JSPConstants.ToURL, "../groupBuildingBillCycle/billCycleList.html?paytimeType=2");
		return new ModelAndView(JSPConstants.OprtSuccessPage);
	}

	private boolean isEmptyRow(HSSFSheet sheet, int i) {
		boolean isEmptyRow = false;
		//处理空行的情况，有可能用户没有删除空白行
		if (sheet.getRow(i) == null) {
			return true;
		}

		if(StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(4)))
				&& StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(5)))
				&& StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(6)))
				&& StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(7)))){
			// 小区	楼栋	单元	房号 都为空时，也认为是空行, 跳过不导入
			isEmptyRow = true;
		}
		
		return isEmptyRow;
	}

	/**
	 * 校验周期账单的有效性<p>
	 * 周期导入模板校验如下：
	 * 
		1、模板上已存在的所有列（包括账单类型的备选列）不允许删除
		2、以下标题名称不允许修改：缴费开始时间、缴费截止时间、账单开始月份、账单截止月份、小区、楼栋、单元、房号、业主姓名、缴费合计
		3、以下列为必填：缴费开始时间、缴费截止时间、账单开始月份、账单截止月份、小区、楼栋、房号、缴费合计
		4、以下列为非必填：业主姓名、单元、单价、建筑面积
		5、缴费截止时间>=缴费开始时间>=当前时间
		6、账单截止月份>=账单开始月份
		7、缴费开始时间、缴费截止时间校验为年月日格式
		8、账单开始月份、账单截止月份校验为年月格式
		9、模板中必须有一项账单类型（如“天然气费”）（若无账单类型，提示请至少录入一个账单类型进行导入）
		10、已录入账单类型（如“天然气费”）列下必须有一个费用合计值，但不校验是否所有行都有费用合计值
		12、小区id不允许为空
		13、小区id和小区名称校验是否匹配（若不匹配，请以小区名称为准，提示其录入准确的小区id，或者重新导出模板）
		14、校验小区名称下是否存在第1行的账单名称（若不存在，导入不成功并给出友好提示）
		15、小区名称、楼栋、单元、房号、校验是否匹配（若不匹配，请给出明确提示哪里不匹配）

	 * @param sheet
	 * @return 校验成功返回“通过校验”，否则返回具体的失败原因 
	 */
	private String verifyPayBillPeriod02(HSSFSheet sheet, BigInteger gbId, BigInteger billCycleId) {
		
		/*
		 * 要有数据
		 * 限定一个小区，
		 * 缴费开始和截止日期必填 ，且必为日期
		 * 账单开始和截止日期必填， 且必为日期
		 * 费用栏目名称必须要改
		 * 缴费合计不能为空
		 * 每一行的明细金额之和要等于总金额
		 */
		
		String verifyResult = "通过校验";
		
		String[] colNames = { "小区", "楼栋", "单元", "房号", "业主姓名" };
		for (int j = 0; j < colNames.length; j++) {
			if (!colNames[j].equals(HSSFCellUtil.getStringValue(sheet.getRow(2).getCell(j)))) {
				return "第3行，第" + (j + 1) + "列的名称不是" + colNames[j] + ", 请检查是否是下载的周期账单模板";
			}
		}
		if(!"缴费合计".equals(HSSFCellUtil.getStringValue(sheet.getRow(2).getCell(32)))){
			return "第3行，第36列的名称不是 “缴费合计”，请检查是否是下载的周期账单模板";
		}
		
		if(sheet.getLastRowNum() == 4){
			return "请添加要导入的数据";
		}

		int dataStartRowIndex = 5;
		Set<String> gbNameSet = new HashSet<String>();
		for(int i = dataStartRowIndex; i <= sheet.getLastRowNum(); i++){
			boolean isEmptyRow = isEmptyRow(sheet, i);
			
			if(isEmptyRow) {
				continue;
			}
			
			gbNameSet.add(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)));	
			if(gbNameSet.size()>1) {
				return "每次只能导入一个小区的数据";
			}

			// 小区、楼栋、房号、缴费合计 不能为空
			if(StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)))) {
				return "第"+(i+1)+"行的小区不能为空";
			}
			if(StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)))) {
				return "第"+(i+1)+"行的楼栋不能为空";
			}
			if(StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(3)))) {
				return "第"+(i+1)+"行的房号不能为空";
			}
			if(StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(32)))) {
				return "第"+(i+1)+"行的缴费合计不能为空";
			}

		}
		//从第10列（含）开始，
		int feeDetailColumnStart = 5;
		//从第36列（含）结束，
		int feeDetailColumnEnd = 31;
		//每3列是一份缴费明细
		int feeDetailInterval = 3;
		
		//9、模板中必须有一项账单类型（如“天然气费”）（若无账单类型，提示请至少录入一个账单类型进行导入）
		if(StringUtils.isEmpty( HSSFCellUtil.getStringValue(sheet.getRow(0).getCell(0)))) {
			return "第一行的账单名称为空，请补充账单名称";
		}
		GroupBuilding groupBuilding = groupBuildingService.getGroupBuildingBySeqId(gbId);
		if(groupBuilding==null){
			return "单元格A2的小区id在系统中不存在";
		}else{
			String gbName = HSSFCellUtil.getStringValue(sheet.getRow(5).getCell(0));
			if(!groupBuilding.getName().equals(gbName)){
				return "单元格A2的小区id和小区名称校验不匹配，请重新下载模板";
			}
		}
		
		GroupBuildingBillCycle groupBuildingBillCycle = groupBuildingBillCycleBaseService.getGroupBuildingBillCycleBySeqId(billCycleId);
		if(groupBuildingBillCycle==null){
			return "单元格E2的周期id在系统中不存在";
		}
		PayBillType payBillType = payBillTypeBaseDao.selectPayBillTypeBySeqId(groupBuildingBillCycle.gettPayBillTypeId());
		String payBillTypeName = HSSFCellUtil.getStringValue(sheet.getRow(0).getCell(0));
		if(payBillTypeName == null) {
			return "单元格A1的费用名称不能为空";
		} else if(!payBillTypeName.equals(payBillType.getName())) {
			return "费用类型不匹配，请检查模板！";
		}
		
		for(int i = dataStartRowIndex; i <= sheet.getLastRowNum(); i++){
			boolean isEmptyRow = isEmptyRow(sheet, i);
			
			if(isEmptyRow) {
				continue;
			}
			
			//double rowSumAmount = 0;
			for(int j=feeDetailColumnStart; j<feeDetailColumnEnd; j=j+feeDetailInterval){
				HSSFCell cell = sheet.getRow(i).getCell(j);
				if(cell == null) {
					continue;
				}

				if(cell != null && cell.getCellType()!=HSSFCell.CELL_TYPE_BLANK && HSSFCellUtil.getStringValue(sheet.getRow(3).getCell(j)).contains("XXX")) {
					return "请修改第4行第" + (j+1) + "列费用栏目名称";
				}
			}
			
			if(sheet.getRow(i).getCell(32)==null) {
				return "第" +(i+1)+ "缴费合计不能为空";
			}

//			王冲说去掉这个校验,因为有时候，用户不要金额的小数部分 Added by wenfq 2016-10-28
//			double rowTotalAmount = HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(32));
//			if(Math.abs(rowTotalAmount-rowSumAmount)>0.0001){
//				return "第" +(i+1)+ "行明细金额之和不等于总金额" ;
//			}
		}
		
		return verifyResult;
	}

	
	/**
	 * 月度对账单导出
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/exportPayBill.html")
	public void exportPayBill(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 super.exportPayBillBase(request, response);
	}
	
	protected List<PayBillPeriod4Export> fetchExportPeriodList(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> paramMap = (HashMap<String, Object>) request.getSession().getAttribute("payBillListQueryParamMap");
		
		paramMap.remove(PageModel.BEGIN);
		paramMap.remove(PageModel.LENGTH);
		List<PayBillEntity> searchRestList = payBillService.getPayBillList_byUserId_forPage(paramMap);
		List<BigInteger> payBillIdList = new ArrayList<BigInteger>();
		for (int i = 0; i < searchRestList.size(); i++) {
			payBillIdList.add(searchRestList.get(i).getId());
		}

		return payBillService.getExportedPayBillPeriod(payBillIdList);
	}
	
	/**
	 * 周期对账单导出  v501使用（不分周期和月度）
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/exportPayBillPeriod.html")
	public void exportPayBillPeriod(HttpServletRequest request, HttpServletResponse response) {
		Integer feeType = ParamUtils.getInteger(request, "feeType", null);
		List<PayBillPeriod4Export> payBillPeriodImportedEntyList = fetchExportPeriodList(request);
		List<BigInteger> billIds = getBillIds(payBillPeriodImportedEntyList);
		String excelName = "jfq_payBill_bank_period_statement.xls";
		//使用母账单 模板
		if(feeType != null) {
			excelName = "jfq_payBill_bank_period_statement_child.xls";
		}
		String filePath = request.getSession().getServletContext().getRealPath("/docs/" + excelName);
		FileInputStream fin = null;
		HSSFWorkbook wb = null;
		OutputStream fOut = null;
		try {
			fin = new FileInputStream(new File(filePath));
			wb = new HSSFWorkbook(fin);
			//边框
			HSSFCellStyle centerstyle = wb.createCellStyle();
			//明细模板样式
			//获取明细item
			List<ExportBillDetailHead> feeDetailNameList = new ArrayList<ExportBillDetailHead>();
			HSSFFont font = wb.getFontAt((short) 0);
			font.setFontName("微软雅黑");
			font.setFontHeightInPoints((short) 12);
			HSSFCellStyle centerstyle0 = wb.createCellStyle();
			// 居中
			centerstyle0.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			centerstyle0.setFont(font);
			// 设背景颜色
			centerstyle0.setFillForegroundColor(IndexedColors.LIME.getIndex());
			centerstyle0.setFillPattern(CellStyle.SOLID_FOREGROUND);
			//重置第二行样式
			HSSFFont font0 = wb.getFontAt((short) 0);
			font.setFontName("微软雅黑");
			font.setFontHeightInPoints((short) 11);
			HSSFCellStyle centerstyle1 = wb.createCellStyle();
			centerstyle1.setFont(font0);
			centerstyle1.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
			centerstyle1.setFillPattern(CellStyle.SOLID_FOREGROUND);

			//sheet列表
			Map<String, HSSFSheet> sheetMap = new HashMap<String, HSSFSheet>();
			//从第5行开始是数据行
			int dataFromRowIndex = 4;
			//sheet行号矫正
			int ortherSheetStartRowTag = 0;
			for (int i = 0; i < payBillPeriodImportedEntyList.size(); i++) {
				PayBillPeriod4Export pb = payBillPeriodImportedEntyList.get(i);
				String gbName = pb.getGbName();
				HSSFSheet sheet = sheetMap.get(gbName);
				//不存在该小区  则新增sheet(默认第一个为模板，等生成完毕后再进行删除sheet0,如果以有数据的sheet作为模板后续sheet单元格可能会出错)
				if(sheet == null) {
					//生成空模板
					sheet = wb.createSheet();
					//复制第一个sheet的第一行到第4行的内容
					HSSFSheeCopy.copySheet(wb, 0, wb.getSheetIndex(sheet), 0, 3);
					//新增sheet后 ortherSheetStartRowTag=0 新的sheet行数从3开始
					ortherSheetStartRowTag=0;
					wb.setSheetName(wb.getSheetIndex(sheet), gbName);
					feeDetailNameList = payBillService.selectFeeDetailNameByGbId(pb.getGbId(), feeType, billIds);
				}
				HSSFRow row = sheet.getRow(ortherSheetStartRowTag + dataFromRowIndex);
				if (row == null) {
					row = sheet.createRow(ortherSheetStartRowTag + dataFromRowIndex);
				}
				//明细开始列 默认为子账单 5
				int detailStartIndex = 5;
				//创建房号基本信息单元格
				createCommonBaseCell(row, pb, centerstyle);
				//导出母账单
				if(feeType == null) {
					//创建账单缴费信息单元格
					createPayMsgBaseCell(row, pb, centerstyle);
					detailStartIndex = 14;
				}
				//记录收费明细的开始index
				Map<String, Integer> feeDetailIndex = new HashMap<String, Integer>();
				//创建明细模板
				createFeeDetailModel(sheet, pb, detailStartIndex, feeDetailIndex, wb, feeDetailNameList, centerstyle0, centerstyle1);
				//创建缴费明细单元格
				createFeeDetailCell(sheet, row, pb, feeDetailIndex, centerstyle, feeType);

				sheetMap.put(gbName, sheet);
				ortherSheetStartRowTag++;
			}
			//删除excel的模板sheet
			wb.removeSheetAt(0);
			// 生成提示信息
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String fileName = "周期账单对账报表" + format.format(new Date());
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
			fOut = response.getOutputStream();
			wb.write(fOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fOut != null) {
				try {
					fOut.flush();
					fOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static List<BigInteger> getBillIds(List<PayBillPeriod4Export> payBillPeriodImportedEntyList) {
		List<BigInteger> list = new ArrayList<BigInteger>();
		for (PayBillPeriod4Export payBillPeriod4Export : payBillPeriodImportedEntyList) {
			list.add(payBillPeriod4Export.getId());
		}
		return list;
	}

	/**
	 * 创建明细模板
	 * @param sheet
	 * @param pb
	 * @param detailStartIndex
     */
	private void createFeeDetailModel(HSSFSheet sheet, PayBillPeriod4Export pb, int detailStartIndex, Map<String, Integer> feeDetailIndex, HSSFWorkbook wb,
									  List<ExportBillDetailHead> feeDetailNameList,HSSFCellStyle centerstyle0, HSSFCellStyle centerstyle1) {
		HSSFRow row0 = sheet.getRow(0);
		HSSFRow row1 = sheet.getRow(1);
		HSSFRow row2 = sheet.getRow(2);
		HSSFRow row3 = sheet.getRow(3);
		row0.createCell(detailStartIndex).setCellValue("费用清单");
		row0.getCell(detailStartIndex).setCellStyle(centerstyle0);

		int initDetailStartIndex = detailStartIndex;
		for(int i = 0; i < feeDetailNameList.size(); i++) {
			//物业费@2   name@feeType
			String[] arryFeeDetailName = feeDetailNameList.get(i).getFeeItemName().split("@");
			String feeDetailName = arryFeeDetailName[0];
			String feeType = arryFeeDetailName[1];
			row1.createCell(detailStartIndex).setCellValue(feeDetailName);
			row1.getCell(detailStartIndex).setCellStyle(centerstyle1);
			row2.createCell(detailStartIndex).setCellValue("");
			row2.getCell(detailStartIndex).setCellStyle(centerstyle1);

			//抄表收费
			if(FeeTypeDict.Chao_Biao.equals(Integer.valueOf(feeType))) {
				//费用项名称开始列
				int startMergeFeeName = detailStartIndex;
				List<ExportBillDetailMrNameForHead> exportBillDetailMrNameForHeads = feeDetailNameList.get(i).getExportBillDetailMrNameForHeads();
				for (int i1 = 0; i1 < exportBillDetailMrNameForHeads.size(); i1++) {
					ExportBillDetailMrNameForHead exportBillDetailMrNameForHead = feeDetailNameList.get(i).getExportBillDetailMrNameForHeads().get(i1);
					//填充数据的时候还需要区分是哪种类型的，所以使用name@feeType
					feeDetailIndex.put(feeDetailNameList.get(i).getFeeItemName() + "@"+exportBillDetailMrNameForHead.getMrName(), detailStartIndex);
					row2.createCell(detailStartIndex).setCellValue(exportBillDetailMrNameForHead.getMrName());
					row3.createCell(detailStartIndex).setCellValue("费用合计");
					row3.createCell(detailStartIndex + 1).setCellValue("单价");
					row3.createCell(detailStartIndex + 2).setCellValue("建筑面积/用量");
					row3.createCell(detailStartIndex + 3).setCellValue("上期读数");
					row3.createCell(detailStartIndex + 4).setCellValue("本期读数");
					//--CellRangeAddress(起始行号，终止行号， 起始列号，终止列号）起始位0
					sheet.addMergedRegion(new CellRangeAddress(2, 2, detailStartIndex, detailStartIndex + 4));
					detailStartIndex += 5;
				}
				if(!DataUtil.isEmpty(exportBillDetailMrNameForHeads)) {
					sheet.addMergedRegion(new CellRangeAddress(1, 1, startMergeFeeName, startMergeFeeName + 5*exportBillDetailMrNameForHeads.size() - 1));
				}
			} else {
				//填充数据的时候还需要区分是哪种类型的，所以使用name@feeType
				feeDetailIndex.put(feeDetailNameList.get(i).getFeeItemName(), detailStartIndex);
				row3.createCell(detailStartIndex).setCellValue("费用合计");
				//滞纳金不显示  单价和用量
				if(!FeeTypeDict.LATEFEE.equals(Integer.valueOf(feeType))) {
					row3.createCell(detailStartIndex + 1).setCellValue("单价");
					row3.createCell(detailStartIndex + 2).setCellValue("建筑面积/用量");
					//CellRangeAddress(起始行号，终止行号， 起始列号，终止列号）
					sheet.addMergedRegion(new CellRangeAddress(1, 1, detailStartIndex, detailStartIndex + 2));
					sheet.addMergedRegion(new CellRangeAddress(1, 2, detailStartIndex, detailStartIndex + 2));
					detailStartIndex += 3;
				} else {
					detailStartIndex += 1;
				}
			}
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, initDetailStartIndex, detailStartIndex - 1));
		detailStartIndex ++;
	}

	/***
	 *生成费用明细
	 * @param pbm
	 * @param row
	 * @param checkDelete
	 */
	private void createFeeDetailCell(HSSFSheet sheet, HSSFRow row, PayBillPeriod4Export pb, Map<String, Integer> feeDetailIndex, HSSFCellStyle centerstyle, Integer feeType) {
		DecimalFormat df1 = new DecimalFormat("0.00");
		List<PropertyFeeDetail4Export> feeDetails = pb.getFeeDetailList();
		for (PropertyFeeDetail4Export feeDetail4Export : feeDetails) {
			String feeDetailName = feeDetail4Export.getName();
			Integer feeType1 = feeDetail4Export.getFeeType();
			String startIndexKey = feeType1.equals(1) ? feeDetailName + "@" + feeType1 + "@" + feeDetail4Export.getMrName() : feeDetailName + "@" + feeType1;
			Integer startIndex = feeDetailIndex.get(startIndexKey);
			//费用项和模板项需要匹配： 模板是按照收费模式查询的，但是导出数据是共用的方法没有进行收费模式分类
			if(startIndex != null) {
				//费用合计
				Double totalPrice = DataUtil.isEmpty(feeDetail4Export.getTotalPrice()) ? 0 : feeDetail4Export.getTotalPrice();
				BigDecimal totalPriceBigDecimal = BigDecimal.valueOf(totalPrice);
				if(totalPrice != null && totalPrice > 0) {
					row.createCell(startIndex).setCellValue(df1.format(totalPriceBigDecimal.divide(BigDecimal.valueOf(100),3, RoundingMode.HALF_UP).doubleValue()));
				} else {
					row.createCell(startIndex).setCellValue("");
				}
				row.getCell(startIndex).setCellStyle(centerstyle);

                //抄表有用量需要特殊处理，同时需要区分滞纳金：滞纳金只有一个合计金额项
				if(feeDetail4Export.getFeeType().equals(FeeTypeDict.Chao_Biao)) {
                    //单价
                    row.createCell(startIndex + 1).setCellValue(DataUtil.isEmpty(feeDetail4Export.getSignalPrice()) ? "" : amountDiv100ForExcel(feeDetail4Export.getSignalPrice()));
                    row.getCell(startIndex + 1).setCellStyle(centerstyle);
                    //用量
					row.createCell(startIndex + 2).setCellValue(DataUtil.isEmpty(feeDetail4Export.getDosageValue()) ? "" : feeDetail4Export.getDosageValue().doubleValue()+"");
					row.getCell(startIndex + 2).setCellStyle(centerstyle);
					//上期读数
					row.createCell(startIndex + 3).setCellValue(DataUtil.isEmpty(feeDetail4Export.getStartValue()) ? "0" : DataUtil.delDotZero(feeDetail4Export.getStartValue()));
					row.getCell(startIndex + 3).setCellStyle(centerstyle);
					//本期读数
					row.createCell(startIndex + 4).setCellValue(DataUtil.isEmpty(feeDetail4Export.getEndValue()) ? "0" : DataUtil.delDotZero(feeDetail4Export.getEndValue()));
					row.getCell(startIndex + 4).setCellStyle(centerstyle);
				} else if(!FeeTypeDict.LATEFEE.equals(feeDetail4Export.getFeeType())) {//滞纳金没有单价用量信息
                    //单价
                    row.createCell(startIndex + 1).setCellValue(DataUtil.isEmpty(feeDetail4Export.getSignalPrice()) ? "" : amountDiv100ForExcel(feeDetail4Export.getSignalPrice()));
                    row.getCell(startIndex + 1).setCellStyle(centerstyle);
                    //建筑面积
                    row.createCell(startIndex + 2).setCellValue(DataUtil.isEmpty(feeDetail4Export.getPriceUnitValue()) ? "" : PriceUtil.div100s(feeDetail4Export.getPriceUnitValue()));
                    row.getCell(startIndex + 2).setCellStyle(centerstyle);
				}
			}
		}
	}

	/**
	 * 生成用户房间基本信息
	 * @param row
	 * @param pb
     */
	private void createCommonBaseCell(HSSFRow row, PayBillPeriod4Export pb, HSSFCellStyle centerstyle) {
		String billMonth = "";
		if(StringUtils.isNotBlank(pb.getBillMonthStart()) || StringUtils.isNotBlank(pb.getBillMonthEnd())) {
			billMonth = pb.getBillMonthStart()+"--"+pb.getBillMonthEnd();
		}
		row.createCell(0).setCellValue(billMonth);
		row.getCell(0).setCellStyle(centerstyle);
		row.createCell(1).setCellValue(pb.getGbName());
		row.getCell(1).setCellStyle(centerstyle);
		row.createCell(2).setCellValue(pb.getbName());
		row.getCell(2).setCellStyle(centerstyle);
		row.createCell(3).setCellValue(pb.getUnitName());
		row.getCell(3).setCellStyle(centerstyle);
		row.createCell(4).setCellValue(pb.getRrNum());
		row.getCell(4).setCellStyle(centerstyle);
	}

	private static String amountDiv100ForExcel(Double obj) {
		BigDecimal bigDecimal = new BigDecimal(obj);
		BigDecimal chushu = new BigDecimal("100");
		BigDecimal divide = bigDecimal.divide(chushu);
		BigDecimal bigDecimal1 = divide.setScale(4, BigDecimal.ROUND_HALF_UP);
		return bigDecimal1.toString();
	}

	/**
	 * 用户缴费基本信息
	 * @param row
	 * @param pb
     */
	private void createPayMsgBaseCell(HSSFRow row, PayBillPeriod4Export pb, HSSFCellStyle centerstyle) {
		DecimalFormat df1 = new DecimalFormat("0.00");
		row.createCell(5).setCellValue(df1.format(pb.getSuccPayAmount().doubleValue()));
		row.getCell(5).setCellStyle(centerstyle);
		row.createCell(6).setCellValue(df1.format(pb.getPbSubSidyAmount().doubleValue()));
		row.getCell(6).setCellStyle(centerstyle);
		//物业宝抵扣
		row.createCell(7).setCellValue(df1.format(pb.getDeduPrice() == null ? 0 : pb.getDeduPrice().doubleValue()));
		row.getCell(7).setCellStyle(centerstyle);
		row.createCell(8).setCellValue(df1.format(pb.getPbAmount()));
		row.getCell(8).setCellStyle(centerstyle);
		String time = "";
		if(pb.getIsPay() == 1) {
			time = pb.getPbPayTime();
		}
		row.createCell(9).setCellValue(time);
		row.getCell(9).setCellStyle(centerstyle);
		if(pb !=null && pb.getCycleType()!=null && pb.getCycleType()==2){
			row.createCell(10).setCellValue("");
			row.createCell(11).setCellValue("");
		} else {
			row.createCell(10).setCellValue(pb.getPayDayStart());
			row.createCell(11).setCellValue(pb.getPayDayEnd());
		}
		row.getCell(10).setCellStyle(centerstyle);
		row.getCell(11).setCellStyle(centerstyle);
		row.createCell(12).setCellValue(pb.getOrderNo());
		row.getCell(12).setCellStyle(centerstyle);
		if(pb.getPaymentWay() == PayBillDict.PaymentWay.Property_Card_Deduction) {
			row.createCell(13).setCellValue("代扣卡续费");
		} else if(pb.getPaymentWay() == PayBillDict.PaymentWay.FINANCE_DEDUCTION) {
			row.createCell(13).setCellValue("物业宝抵扣");
		} else if(pb.getPaymentWay() == PayBillDict.PaymentWay.BANK_COLLECTION) {
			row.createCell(13).setCellValue("银行托收");
		} else if(pb.getPaymentWay() == PayBillDict.PaymentWay.Marked_Manual) {
			row.createCell(13).setCellValue("现金缴费");
		} else {
			row.createCell(13).setCellValue(getPayMethodString(pb.getPayMethod()));
		}
		row.getCell(13).setCellStyle(centerstyle);
	}

	/**
	 * 导出缴费模板  huangzj2015-06-03
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/exportPayBillTemplate.html")
	public ModelAndView exportPayBillTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		//导出小区
		paramMap.put("groupbuildingId", request.getParameter("groupbuildingId"));
		String gbName = request.getParameter("groupbuildingName");
		//缴费月份
		String erportMonth = request.getParameter("erportMonth");
		List<RoomEntity> rooms = buildingRoomService.queryRoomForList(null, null, paramMap, false);
		if (rooms.size() > 0) {
			Calendar a = Calendar.getInstance();
			a.set(Calendar.MONTH, Integer.parseInt(erportMonth)-1);
			String filePath = request.getSession().getServletContext().getRealPath("/docs/jfq_payBill_import_template.xls");
			FileInputStream fin = new FileInputStream(new File(filePath));
			HSSFWorkbook wb = new HSSFWorkbook(fin);
			String exportTime = a.get(Calendar.YEAR) + "年" + erportMonth + "月";
			wb.setSheetName(0, gbName+"-"+exportTime+"缴费单");
			HSSFSheet sheet = wb.getSheetAt(0);
			//从第5行开始是数据行
			int dataFromRowIndex = 4;
			for (int i = 0; i < rooms.size(); i++) {
				RoomEntity room = rooms.get(i);
				HSSFRow row = sheet.getRow(i + dataFromRowIndex);
				if (row == null) {
					row = sheet.createRow(i + dataFromRowIndex);
				}
				// 月份
				row.createCell(0).setCellValue(a);
				//小区
				row.createCell(1).setCellValue(room.getGroupbuildingName());
				//楼栋
				row.createCell(2).setCellValue(room.getBuildingName());
				//单元
				row.createCell(3).setCellValue(room.getUnitName());
				//房号
				row.createCell(4).setCellValue(room.getNumTail());
				if(null!=room.getProprietor()){
					//业主姓名
					row.createCell(5).setCellValue(room.getProprietor().getName());
				}
			}
			String fileName = gbName+"-"+exportTime+"缴费单.xls";
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
	 * 导出缴费模板 
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/exportPayBillTemplate02.html")
	public ModelAndView exportPayBillTemplate02(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		//导出小区
		paramMap.put("groupbuildingId", request.getParameter("groupbuildingId"));
		String gbName = request.getParameter("groupbuildingName");
		//缴费月份
		String erportMonth = request.getParameter("erportMonth");
		List<RoomEntity> rooms = buildingRoomService.queryRoomForList(null, null, paramMap, false);
		if (rooms.size() > 0) {
			String filePath = request.getSession().getServletContext().getRealPath("/docs/jfq_payBill_import_template.xls");
			FileInputStream fin = new FileInputStream(new File(filePath));
			HSSFWorkbook wb = new HSSFWorkbook(fin);
			String exportTime =erportMonth;
			wb.setSheetName(0, gbName+"-"+exportTime+"缴费单");
			HSSFSheet sheet = wb.getSheetAt(0);
			//从第5行开始是数据行
			int dataFromRowIndex = 4;
			for (int i = 0; i < rooms.size(); i++) {
				RoomEntity room = rooms.get(i);
				HSSFRow row = sheet.getRow(i + dataFromRowIndex);
				if (row == null) {
					row = sheet.createRow(i + dataFromRowIndex);
				}
				//小区
				row.createCell(0).setCellValue(room.getGroupbuildingName());
				//楼栋
				row.createCell(1).setCellValue(room.getBuildingName());
				//单元
				row.createCell(2).setCellValue(room.getUnitName());
				//房号
				row.createCell(3).setCellValue(room.getNumTail());
				if(null!=room.getProprietor()){
					//业主姓名
					row.createCell(4).setCellValue(room.getProprietor().getName());
				}
			}
			sheet.getRow(0).getCell(0).setCellValue(request.getParameter("groupbuildingId"));
			String fileName = gbName+"-"+exportTime+"缴费单.xls";
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
	 * 导出周期缴费模板  
	* @author wenfq 2015-12-17
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/exportPayBillPeriodTemplate.html")
	public ModelAndView exportPayBillPeriodTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		String gbId = request.getParameter("groupbuildingId");
		//导出小区
		paramMap.put("groupbuildingId", gbId);
		String gbName = request.getParameter("groupbuildingName");
		String payBillTypeName = request.getParameter("payBillTypeName");
		List<RoomEntity> rooms = buildingRoomService.queryRoomForList(null, null, paramMap, false);
		if (rooms.size() > 0) {
			Calendar a = Calendar.getInstance();
			String filePath = request.getSession().getServletContext().getRealPath("/docs/jfq_payBill_period_import_template.xls");
			FileInputStream fin = new FileInputStream(new File(filePath));
			HSSFWorkbook wb = new HSSFWorkbook(fin);
			String exportTime = a.get(Calendar.YEAR) + "年" + (a.get(Calendar.MONTH)+1) + "月";
			wb.setSheetName(0, gbName+"-"+exportTime+"缴费单");
			HSSFSheet sheet = wb.getSheetAt(0);
			sheet.getRow(0).getCell(0).setCellValue(payBillTypeName);
			if("物业费".equals(payBillTypeName)){
				sheet.getRow(3).getCell(9).setCellValue("管理费");
			}
			
			sheet.getRow(1).getCell(4).setCellValue(gbId);
			//从第6行开始是数据行
			int dataFromRowIndex = 5;
			for (int i = 0; i < rooms.size(); i++) {
				RoomEntity room = rooms.get(i);
				HSSFRow row = sheet.getRow(i + dataFromRowIndex);
				if (row == null) {
					row = sheet.createRow(i + dataFromRowIndex);
				}
				//小区 列
				int j = 4;
				//小区
				row.createCell(j++).setCellValue(room.getGroupbuildingName());
				//楼栋
				row.createCell(j++).setCellValue(room.getBuildingName());
				//单元
				row.createCell(j++).setCellValue(room.getUnitName());
				//房号
				row.createCell(j++).setCellValue(room.getNumTail());
				if(null!=room.getProprietor()){
					//业主姓名
					row.createCell(j++).setCellValue(room.getProprietor().getName());
				}
				row.createCell(36).setCellFormula("SUM(J6,M6,P6,S6,V6,Y6,AB6,AE6,AH6)".replace("6", ""+(dataFromRowIndex+i+1)));
			}
			
			if (rooms.size() > 0) {
		        sheet.getRow(dataFromRowIndex).getCell(0).getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_TOP);
		        sheet.getRow(dataFromRowIndex).getCell(1).getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_TOP);
		        sheet.getRow(dataFromRowIndex).getCell(2).getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_TOP);
		        sheet.getRow(dataFromRowIndex).getCell(3).getCellStyle().setVerticalAlignment(CellStyle.VERTICAL_TOP);

				sheet.addMergedRegion(new CellRangeAddress(5, 5 + rooms.size()-1, (short) 0, (short) 0));
				sheet.addMergedRegion(new CellRangeAddress(5, 5 + rooms.size()-1, (short) 1, (short) 1));
				sheet.addMergedRegion(new CellRangeAddress(5, 5 + rooms.size()-1, (short) 2, (short) 2));
				sheet.addMergedRegion(new CellRangeAddress(5, 5 + rooms.size()-1, (short) 3, (short) 3));
			}
			
			String fileName = gbName+"-"+exportTime+"缴费单.xls";
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
	 * 导出周期缴费模板  
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/exportPayBillPeriodTemplate02.html")
	public ModelAndView exportPayBillPeriodTemplate02(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		String gbId = request.getParameter("groupbuildingId");
		//导出小区
		paramMap.put("groupbuildingId", gbId);
		String gbName = request.getParameter("groupbuildingName");
		String payBillTypeName = request.getParameter("payBillTypeName");
		String erportMonth = request.getParameter("erportMonth");
		List<RoomEntity> rooms = buildingRoomService.queryRoomForList(null, null, paramMap, false);
		if (rooms.size() > 0) {
			String filePath = request.getSession().getServletContext().getRealPath("/docs/jfq_payBill_period_import_template.xls");
			FileInputStream fin = new FileInputStream(new File(filePath));
			HSSFWorkbook wb = new HSSFWorkbook(fin);
			wb.setSheetName(0, gbName+"-"+erportMonth+"缴费单");
			HSSFSheet sheet = wb.getSheetAt(0);
			sheet.getRow(0).getCell(0).setCellValue(payBillTypeName);
			if("物业费".equals(payBillTypeName)){
				sheet.getRow(3).getCell(5).setCellValue("管理费");
			}
			
			sheet.getRow(1).getCell(0).setCellValue(gbId);
			//从第6行开始是数据行
			int dataFromRowIndex = 5;
			for (int i = 0; i < rooms.size(); i++) {
				RoomEntity room = rooms.get(i);
				HSSFRow row = sheet.getRow(i + dataFromRowIndex);
				if (row == null) {
					row = sheet.createRow(i + dataFromRowIndex);
				}
				//小区 列
				int j = 0;
				//小区
				row.createCell(j++).setCellValue(room.getGroupbuildingName());
				//楼栋
				row.createCell(j++).setCellValue(room.getBuildingName());
				//单元
				row.createCell(j++).setCellValue(room.getUnitName());
				//房号
				row.createCell(j++).setCellValue(room.getNumTail());
				if(null!=room.getProprietor()){
					//业主姓名
					row.createCell(j++).setCellValue(room.getProprietor().getName());
				}
				row.createCell(32).setCellFormula("SUM(F6,I6,L6,O6,R6,U6,X6,AA6,AD6)".replace("6", ""+(dataFromRowIndex+i+1)));
			}
			
			String fileName = gbName+"-"+erportMonth+"缴费单.xls";
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
	 * 设置小区的批注，用gbId填充
	 * @param gbId
	 * @param sheet
	 */
	private void setGroupBuildingIdComment(String gbId, HSSFSheet sheet) {
		Workbook wb =  sheet.getWorkbook();

	    CreationHelper factory = wb.getCreationHelper();

	    Row row   = sheet.getRow(1);
	    Cell cell = sheet.getRow(1).getCell(4);
	    
	    Drawing drawing = sheet.createDrawingPatriarch();

	    // When the comment box is visible, have it show in a 1x3 space
	    ClientAnchor anchor = factory.createClientAnchor();
	    anchor.setCol1(cell.getColumnIndex());
	    anchor.setCol2(cell.getColumnIndex());
	    anchor.setRow1(row.getRowNum());
	    anchor.setRow2(row.getRowNum()+3);

	    // Create the comment and set the text+author
	    Comment comment = drawing.createCellComment(anchor);
	    RichTextString str = factory.createRichTextString("Hello, World!");
	    comment.setString(str);
	    comment.setAuthor("Apache POI");

	    // Assign the comment to the cell
	    cell.setCellComment(comment);
	}
	
	
	/**
	 * 账单（月度）查找, 列表界面也共用这个，只不过查询条件为空而已
	 * @param request
	 * @return
	 */
	@RequestMapping("/search.html")
	public ModelAndView search(HttpServletRequest request) {
		String formUrl = "/payBill/search.html";
		return searchBase(request, formUrl);
	}

	/**
	 * 手工标记
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/markByManual.html")
	@ResponseBody
	public JsonResponse markByManual(MarkReq markReq, HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		
		String id = request.getParameter("id");

		boolean isBankCollection = payBillService.isBankCollectionByPayBillId(new BigInteger(id));
		if(isBankCollection){
			jsonResponse.setMessage("银行托收期间，暂时不能更改账单，请确认回盘完成后再操作。");
			jsonResponse.setStatus( "0001");
			return jsonResponse;
		}
		
		BigInteger omsUserId = UserContext.getCurrUser().getId();
		int updateCount = payBillService.markByManual(id, omsUserId, markReq);

		jsonResponse.setMessage(updateCount == 1 ? "手工标记成功" : "标记失败");
		jsonResponse.setStatus(updateCount == 1 ? "0000" : "0001");

		return jsonResponse;
	}
	
	/**
	 * 删除账单（未缴费）
	 * @param request
	 * @return
	 */
	@RequestMapping("/delPayBill.html")
	@ResponseBody
	public JsonResponse delPayBill(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		
		String id = request.getParameter("id");
		BigInteger delUserId = UserContext.getOperIdBigInteger();

		boolean isBankCollection = payBillService.isBankCollectionByPayBillId(new BigInteger(id));
		if(isBankCollection){
			jsonResponse.setMessage("银行托收期间，暂时不能更改账单，请确认回盘完成后再操作。");
			jsonResponse.setStatus( "0001");
			return jsonResponse;
		}
		
		int updateCount = payBillService.delPayBill(id, delUserId);
		
		jsonResponse.setMessage(updateCount == 1 ? "删除成功" : "删除失败");
		jsonResponse.setStatus(updateCount == 1 ? "0000" : "0001");

		//更新当前房间滞纳金
		PayBill payBillBySeqId = payBillService.getPayBillBySeqId(new BigInteger(id));
		CalculateLateFeeByRoomImp calculateLateFeeByRoomImp = (CalculateLateFeeByRoomImp) CnfantasiaCommbusiUtil.getBeanManager("calculateLateFeeByRoomImp");
		FutureTask<Boolean> booleanFutureTask = new FutureTask<>(new CalculateLateFeeRunnable(calculateLateFeeByRoomImp, payBillBySeqId.gettRealRoomFId(), delUserId));
		booleanFutureTask.run();
		
		return jsonResponse;
	}

	
	/**
	 * 根据管理处ID获取小区信息
	 * */
	@RequestMapping("/getGroupbuildingByMgtId.html")
	@ResponseBody
	public String getGroupbuildingByMgtId(HttpServletRequest request) {
		//管理处ID
		String mgtId = request.getParameter("mgtId");
		IGroupBuildingService groupBuildingService = (IGroupBuildingService)CnfantasiaCommUtil.getBeanManager("msGroupBuildingService");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mgtId", mgtId);
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		List<GroupBuildingSimpleEntity> gbList = groupBuildingService.selectGroupBuildingForDialogList(paramMap);
		return JSON.toJSONString(gbList);
	}

	/**
	 * 打印配置小区列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/printConfig.html", method = RequestMethod.GET)
	public ModelAndView jumpToPrintConfig(HttpServletRequest request) {
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		String gbName = ParamUtils.getString(request, "gbName", null);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", gbName);
		param.put("gbIdList", UserContext.getGbIdList());
		param.put("isConfig", request.getParameter("isConfig"));
		
		int total = groupBuildingService.getGbCountForPrint(param);
		
		param.put("_begin", pageSize * curPageIndex);
		param.put("_length", pageSize);
		List<PrintConfigList> printConfigList = groupBuildingService.getGbListForPrint(param);
		if(printConfigList!=null){
			for(PrintConfigList printConfig:printConfigList){
				boolean isConfig = StringUtils.isNotBlank(printConfig.getTemplateContent());
				printConfig.setConfig(isConfig);
			}
		}
		
		ModelAndView mav = new ModelAndView("payBill/printConfig");
		mav.addObject("printConfigList", printConfigList);
		mav.addObject("resultSize", total);
		return mav;
	}

	/**
	 * 保存模板编辑数据请求
	 * 
	 * @param gbId
	 * @return
	 */
	@RequestMapping(value = "/editPrintConfig.html", method = RequestMethod.GET)
	public ModelAndView jumpToEditPrintConfig(BigInteger gbId) {
		GroupBuilding gb = groupBuildingService.getGroupBuildingBySeqId(gbId);
		
		List<PrintInitTemplate> printInitTemplateList = printInitTemplateBaseService.getPrintInitTemplateByCondition(null);
		Map<String, PrintTemplateEntity> printTemplateMap = payBillPrintDao.selectPrintTemplateByGbId(gbId);
		
		Map<String, PrintTemplateEntity> printTemplateMapResult = new HashMap<String, PrintTemplateEntity>();
		for(PrintInitTemplate printInitTemplateTmp : printInitTemplateList){
			String code = printInitTemplateTmp.getCode();
			PrintTemplateEntity printTemplateEntityTmp = (printTemplateMap==null ? null : printTemplateMap.get(code));
			// 为空，则取默认模板
			if(printTemplateEntityTmp==null){
				PrintTemplateEntity printTemplateEntity = new PrintTemplateEntity();
				printTemplateEntity.setCode(code);
				printTemplateEntity.setServiceState(1);
				printTemplateEntity.setTemplateContent(printInitTemplateTmp.getTemplateContent());
				
				printTemplateMapResult.put(code, printTemplateEntity);
			} else {
				printTemplateMapResult.put(code, printTemplateEntityTmp);
			}
		}
		
		// 处理code，将code换成“用户可理解的文字”
		for(Map.Entry<String, PrintTemplateEntity> entry:printTemplateMapResult.entrySet()){
			PrintTemplateEntity printTemplateEntity = entry.getValue();
			String templateContent = printTemplateEntity.getTemplateContent();
			templateContent = code2LettleForPrint(templateContent, printTemplateEntity.getCode());
			
			printTemplateEntity.setTemplateContent(templateContent);
		}

		ModelAndView mav = new ModelAndView("/payBill/editPrintConfig");
		mav.addObject("printTemplateMapResult", printTemplateMapResult);
		mav.addObject("letterTextMap1", PrintEditKeyEnum.INSTANCE.getPrintEditKey1().getLetterTextMap());
		mav.addObject("letterTextMap2", PrintEditKeyEnum.INSTANCE.getPrintEditKey2().getLetterTextMap());
		mav.addObject("gbId", gbId);
		mav.addObject("gbName", gb.getName());
		mav.addObject("isAdmin", (UserContext.getGbIdList()==null));
		return mav;
	}

	/**
	 * 处理code，将code换成“用户可理解的文字”
	 * @param printConfigTemplate
	 * @return
	 */
	private String code2LettleForPrint(String printConfigTemplate, String code) {
		PrintEditKeyBase printEditKey = PrintEditKeyEnum.INSTANCE.getPrintEditKey(code);
		Map<String, String> codeLetterMap = printEditKey.getChromeMap();
		if (codeLetterMap != null) {
			for (Map.Entry<String, String> codeLetter : codeLetterMap.entrySet()) {
				printConfigTemplate = printConfigTemplate.replaceAll(codeLetter.getKey(), codeLetter.getValue());
			}
		}
		return printConfigTemplate;
	}
	
	/**
	 * 打印模板编辑保存
	 * 
	 * @param gbId
	 * @param printDesc
	 * @param templateCode
	 * @return
	 */
	@RequestMapping(value = "/editPrintConfig.html", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse editPrintConfig(BigInteger gbId, String printDesc, String templateCode) {
		PrintEditKeyBase printEditKey = PrintEditKeyEnum.INSTANCE.getPrintEditKey(templateCode);
        // 处理code，将“用户可理解的文字”换成code
		// chrome浏览器处理
 		Map<String, String> chromeMap = printEditKey.getChromeMap();
 		for(Map.Entry<String, String> codeLetter:chromeMap.entrySet()){
 			printDesc = printDesc.replaceAll(codeLetter.getValue(), codeLetter.getKey());
 		}
		// firefox浏览器处理
 		Map<String, String> firefoxMap = printEditKey.getFirefoxMap();
 		for(Map.Entry<String, String> codeLetter:firefoxMap.entrySet()){
 			printDesc = printDesc.replaceAll(codeLetter.getValue(), codeLetter.getKey());
 		}
		// IE浏览器处理
 		Map<String, String> ieMap = printEditKey.getIEMap();
 		for(Map.Entry<String, String> codeLetter:ieMap.entrySet()){
 			printDesc = printDesc.replaceAll(codeLetter.getValue(), codeLetter.getKey());
 		}
     	
 		/**将其它模板设置为f_service_state=1。不存在，则insert；存在，则update*/
 		// 1、将其它模板设置为f_service_state=1
 		payBillPrintDao.updatePrintTemplateStopService(gbId);
 		
 		// 2、不存在，则insert；存在，则update
 		Map<String, Object> paramMap = new HashMap<String, Object>();
 		paramMap.put("code", templateCode);
 		List<PrintInitTemplate> printInitTemplateList = printInitTemplateBaseService.getPrintInitTemplateByCondition(paramMap);
 		BigInteger pitId = printInitTemplateList.get(0).getId();
 		
 		paramMap.clear();
 		paramMap.put("tGroupBuildingFId", gbId);
 		paramMap.put("tPrintInitTemplateFId", pitId);
 		List<com.cnfantasia.server.domainbase.printConfig.entity.PrintConfig> printConfigList = printConfigBaseService.getPrintConfigByCondition(paramMap);
 		
 		String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
 		BigInteger userId = UserContext.getOperIdBigIntegerMustExist();
		// insert
 		if(printConfigList==null || printConfigList.size()==0){
 			com.cnfantasia.server.domainbase.printConfig.entity.PrintConfig printConfig = new com.cnfantasia.server.domainbase.printConfig.entity.PrintConfig();
 			printConfig.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_print_config));
 			printConfig.setServiceState(0);
 			printConfig.setTemplateContent(printDesc);
 			printConfig.settGroupBuildingFId(gbId);
 			printConfig.settPrintInitTemplateFId(pitId);
 			printConfig.setSys0AddTime(now);
 			printConfig.setSys0AddUser(userId);
 			printConfig.setSys0DelState(0);
 			
 			printConfigBaseService.insertPrintConfig(printConfig);
 		} else {// update
 			com.cnfantasia.server.domainbase.printConfig.entity.PrintConfig printConfig = printConfigList.get(0);
 			printConfig.setServiceState(0);
 			printConfig.setTemplateContent(printDesc);
 			printConfig.setSys0UpdTime(now);
 			printConfig.setSys0UpdUser(userId);
 			
 			printConfigBaseService.updatePrintConfig(printConfig);
 		}
 		
        return new JsonResponse();
	}

	/**
	 * 跳转到账单金额修改页面
	 */
	@RequestMapping("goToEditPayBillAmount.html")
	public ModelAndView goToEditPayBillAmount(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/payBill/payBillAmountDetail");
		BigInteger payBillId = ParamUtils.getBigInteger(request, "id", null);
		String gbName = ParamUtils.getString(request, "gbName");
		String billName = ParamUtils.getString(request, "billName");

		boolean isBankCollection = payBillService.isBankCollectionByPayBillId(payBillId);
		if(isBankCollection){
			request.setAttribute(JSPConstants.OprtResult, "银行托收期间，暂时不能更改账单，请确认回盘完成后再操作。");
			request.setAttribute(JSPConstants.ToURL, "../payBill/listPeriod.html");
			modelAndView.setViewName(JSPConstants.OprtSuccessPage);
			return modelAndView;
		}

		PayBillWithFeeDetailEntity payBillImportedEntity = payBillService.getPayBillForDetail(payBillId);
		modelAndView.addObject("payBill", payBillImportedEntity);
		List<PropertyFeeDetail> pfds = payBillImportedEntity.getPropertyFeeDetails();

		if(pfds!=null && pfds.size()>0){
			// 固定收费
			List<PropertyFeeDetail> fixedPfds = new ArrayList<PropertyFeeDetail>();
			// 临时收费
			List<PropertyFeeDetail> tmpPfds = new ArrayList<PropertyFeeDetail>();
			Double fixedTotal = 0.0;
			Double mrTotal = 0.0;
			Double tmpdTotal = 0.0;
			for(PropertyFeeDetail pfd:pfds){
				if(FeeTypeDict.Gu_Ding.equals(pfd.getFeeType())){
					fixedPfds.add(pfd);
					fixedTotal += pfd.getTotalPrice();
				} else if(FeeTypeDict.Lin_Shi.equals(pfd.getFeeType())){
					tmpPfds.add(pfd);
					tmpdTotal += pfd.getTotalPrice();
				}
			}

			//根据不同的收费模式来选择需要返回的明细数据
			List<BillMrDetail> billMrDetails = payBillService.selectBillDetailForMr(payBillId);
			modelAndView.addObject("billMrDetails", billMrDetails);
			BigDecimal mrTotalBid = new BigDecimal(0);
			for(BillMrDetail billMrDetail:billMrDetails){
				mrTotalBid = mrTotalBid.add(billMrDetail.getTotalPrice());
			}
			if(mrTotalBid != null) {
				mrTotal += mrTotalBid.doubleValue();
			}
			// “抄表-往月欠费”
			if(payBillImportedEntity.getLastUnpaid()!=null){
				mrTotal += PriceUtil.div100(payBillImportedEntity.getLastUnpaid()).doubleValue();
			}

			modelAndView.addObject("mrTotal", mrTotal);
			modelAndView.addObject("fixedPfds", fixedPfds);
			modelAndView.addObject("fixedTotal", fixedTotal);
			modelAndView.addObject("tmpPfds", tmpPfds);
			Double totalAmount = null;
			if(tmpPfds!=null && tmpPfds.size()>0){
				totalAmount = tmpPfds.get(0).getTotalAmount();
			}
			if(totalAmount!=null && totalAmount>0){
				modelAndView.addObject("tmpdTotal", totalAmount);
			} else {
				modelAndView.addObject("tmpdTotal", tmpdTotal);
			}
		}

		if(payBillImportedEntity!=null){
			String billMonthStart = payBillImportedEntity.getBillMonthStart();
			String billMonthend = payBillImportedEntity.getBillMonthEnd();

			if(StringUtils.isNotBlank(billMonthStart) && StringUtils.isNotBlank(billMonthend)){
				DateTimeFormatter format = DateTimeFormat.forPattern("yyyy年MM月");
				DateTime startTime = DateTime.parse(billMonthStart, format);
				DateTime endTime = DateTime.parse(billMonthend, format);
				Months months = Months.monthsBetween(startTime, endTime);
				modelAndView.addObject("months", months.getMonths()+1);
			}
		}

		modelAndView.addObject("gbName", gbName);
		modelAndView.addObject("billName", billName);
		return modelAndView;
	}

	/**
	 * 保存账单金额信息
	 */
	@RequestMapping("savePayBillAmount.html")
	@ResponseBody
	public JsonResponse savePayBillAmount(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		String fixed = ParamUtils.getString(request, "fixed");
		String mr = ParamUtils.getString(request, "mr");
		String tmp = ParamUtils.getString(request, "tmp");
		BigInteger payBillId = ParamUtils.getBigInteger(request, "payBillId", null);
		BigInteger userId = UserContext.getOperIdBigInteger();

		List<List> fixedList = JSONObject.parseArray(fixed, List.class);
		List<List> mrList = JSONObject.parseArray(mr, List.class);
		List<List> tmpList = JSONObject.parseArray(tmp, List.class);

		try {
			payBillService.savePayBillAmount(fixedList, mrList, tmpList, payBillId, userId);
			jsonResponse.setMessage("保存成功！");

			//更新当前房间滞纳金
			PayBill payBillBySeqId = payBillService.getPayBillBySeqId(payBillId);
			CalculateLateFeeByRoomImp calculateLateFeeByRoomImp = (CalculateLateFeeByRoomImp) CnfantasiaCommbusiUtil.getBeanManager("calculateLateFeeByRoomImp");
			FutureTask<Boolean> booleanFutureTask = new FutureTask<>(new CalculateLateFeeRunnable(calculateLateFeeByRoomImp, payBillBySeqId.gettRealRoomFId(), userId));
			booleanFutureTask.run();
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setMessage("保存失败！");
		}
		return jsonResponse;
	}

	/**
	 * 收费一览表
	 * @param request
	 * @return
	 */
	@RequestMapping("/listPeriodPayBillView.html")
	public ModelAndView listPeriodPayBillView(HttpServletRequest request) {

		getGbToRequest(request);

		Map<String, Object> paramMap = searchBaseInitMap(request);

		paramMap.put("omsUserId", UserContext.getOperId());
		paramMap.put("omsUserName", UserContext.getCurrUser().getRealName());
		paramMap.put("isadmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isBusiness", this.isBusiness());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());

		paramMap.put("buyerId", request.getParameter("buyerId"));

		paramMap.put("bName", request.getParameter("bName"));
		paramMap.put("unitName", request.getParameter("unitName"));
		paramMap.put("roomNum", request.getParameter("roomNum"));
		paramMap.put("payDayStart", request.getParameter("payDayStart"));
		paramMap.put("payDayEnd", request.getParameter("payDayEnd"));

		paramMap.put("cycleType", request.getParameter("cycleType"));
		//账期ID
		paramMap.put("cycleId", request.getParameter("cycleId"));
		//抄表条件
		paramMap.put("pbtIsPropfee", request.getParameter("pbtIsPropfee"));
		//收费模式 1：抄表收费，2：固定收费，3：临时收费
		paramMap.put("feeType", request.getParameter("feeType"));
		//非收益中心
		paramMap.put("isRevenue", false);
		//全部查询==4
		if(DataUtil.isEmpty(paramMap.get("isPay"))) {
			paramMap.put("isPay", 4);
		}

		if (request.getParameter("billMonthStart") != null) {
			paramMap.put("billMonthStart",
					DateUtils.convertDateToStr(DateUtils.convertStrToDate(request.getParameter("billMonthStart"), "yyyy-MM"), "yyyy-MM-dd"));
		} else if(request.getParameter("billMonthEnd") == null && request.getParameter("gbId") == null) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -2);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			Date dateStart = ParamUtils.getDate(request, "dateStart", cal.getTime(), "yyyy-MM");
			paramMap.put("billMonthStart", DateUtils.convertDateToStr(dateStart, "yyyy-MM-dd"));
			request.setAttribute("billMonthStart", DateUtils.convertDateToStr(dateStart, "yyyy-MM"));
		}

		if (request.getParameter("billMonthEnd") != null) {
			paramMap.put("billMonthEnd", DateUtils.convertDateToStr(DateUtils.convertStrToDate(request.getParameter("billMonthEnd"), "yyyy-MM"), "yyyy-MM-dd"));
		}

		paramMap.put("billTypeName", request.getParameter("billTypeName"));

		String searchFrom = ParamUtils.getString(request, "searchFrom");
		//菜单查询，默认账单起始时间：所带月为当前月
		if("menu".equals(searchFrom)) {
			String currentDateStr = DateUtils.getCurrentDateStr("yyyy-MM");
			paramMap.put("billMonthStart", currentDateStr);
			request.setAttribute("billMonthStart", currentDateStr);
		}

		//存储查询条件，导出数据时要用到，见exportPayBill()方法
		request.getSession().setAttribute("payBillListQueryParamMap", ((HashMap<String, Object>) paramMap).clone());

		int resultSize = payBillService.getPayBill_byUserId_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		PageUtils.addPageInfoToParam(request, paramMap);

		List<PayBillEntity> searchRestList = payBillService.getPayBillList_byUserId_forPage(paramMap);

		//总金额
		BigDecimal totalAmount = BigDecimal.ZERO;
		//用户支付
		BigDecimal userPay = BigDecimal.ZERO;
		//物业宝抵扣
		BigDecimal deduAmount = BigDecimal.ZERO;
		//补贴金额
		BigDecimal perAmount = BigDecimal.ZERO;
		Map<String, BigDecimal> amounts = payBillService.getPayBillsAmounts(paramMap);
		if(!DataUtil.isEmpty(amounts)) {
			totalAmount = !DataUtil.isEmpty(amounts.get("totalAmount")) ? amounts.get("totalAmount").divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
			userPay = !DataUtil.isEmpty(amounts.get("userPay")) ? amounts.get("userPay").divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
			deduAmount = !DataUtil.isEmpty(amounts.get("deduAmount")) ? amounts.get("deduAmount").divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
			perAmount = !DataUtil.isEmpty(amounts.get("perAmount")) ? amounts.get("perAmount").divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
		}

		request.setAttribute("totalAmount", totalAmount);
		request.setAttribute("userPay", userPay);
		request.setAttribute("deduAmount", deduAmount);
		request.setAttribute("perAmount", perAmount);
		request.setAttribute("resList", searchRestList);

		return new ModelAndView("/payBill/payBillPeriodListView");
	}

	/**
	 * 欠费明细列表.
	 * @param request
	 * @return
     */
	@RequestMapping("getUnpaidList.html")
	public ModelAndView getUnpaidList(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		BigInteger payBillId = ParamUtils.getBigInteger(request, "payBillId", null);
		List<Map<String, List>> unPaidPayBillEntityList = payBillService.getUnpaidListById(payBillId);
		//表头合并的长度
		int colspan = 2;
		if(!DataUtil.isEmpty(unPaidPayBillEntityList) && !DataUtil.isEmpty(unPaidPayBillEntityList.get(0))) {
			colspan += unPaidPayBillEntityList.get(0).size();
		}
		modelAndView.addObject("colspan", colspan);
		modelAndView.addObject("unPaidPayBillEntityList", unPaidPayBillEntityList);
		modelAndView.setViewName("/payBill/unpaidList");
		return modelAndView;
	}

	/**
	 * 欠费结清.
	 * @param request
	 * @return
     */
	@RequestMapping("unpaidSettle.json")
	@ResponseBody
	public JsonResponse unpaidSettle(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();

		String id = request.getParameter("id");

		boolean isBankCollection = payBillService.isBankCollectionByPayBillId(new BigInteger(id));
		if(isBankCollection){
			jsonResponse.setMessage("银行托收期间，暂时不能更改账单，请确认回盘完成后再操作。");
			jsonResponse.setStatus( "0001");
			return jsonResponse;
		}

		BigInteger omsUserId = UserContext.getCurrUser().getId();
		int updateCount = payBillService.unpaidSettle(id, omsUserId);

		jsonResponse.setMessage(updateCount == 1 ? "成功" : "失败");
		jsonResponse.setStatus(updateCount == 1 ? "0000" : "0001");
		return jsonResponse;
	}

	private static BigDecimal amountDiv100(Long price) {
		if(DataUtil.isEmpty(price)) {
			return BigDecimal.ZERO;
		}
		BigDecimal tmpDeci = new BigDecimal(price);
		BigDecimal resBig = tmpDeci.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
		return resBig;
	}

	/**
	 * 计算滞纳金，防止滞纳金计算出问题，然后进行一次计算
	 * @param request
	 * @return
     */
	@RequestMapping("calculateLateFee.json")
	@ResponseBody
	public JsonResponse calculateLateFee(HttpServletRequest request) {
		BigInteger realRoomId = ParamUtils.getBigInteger(request, "realRoomId", null);
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
		if(!DataUtil.isEmpty(realRoomId)) {
			CalculateLateFeeByRoomImp calculateLateFeeByRoomImp = (CalculateLateFeeByRoomImp) CnfantasiaCommbusiUtil.getBeanManager("calculateLateFeeByRoomImp");
			FutureTask<Boolean> booleanFutureTask = new FutureTask<>(new CalculateLateFeeRunnable(calculateLateFeeByRoomImp, realRoomId, new BigInteger("1")));
			booleanFutureTask.run();
		}

		if(!DataUtil.isEmpty(gbId)) {
			CalculateLateFeeByGbImp calculateLateFeeByGbImp = (CalculateLateFeeByGbImp) CnfantasiaCommbusiUtil.getBeanManager("calculateLateFeeByGbImp");
			FutureTask<Boolean> booleanFutureTask = new FutureTask<>(new CalculateLateFeeRunnable(calculateLateFeeByGbImp, gbId, new BigInteger("1")));
			booleanFutureTask.run();
		}

		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setMessage("成功");
		jsonResponse.setStatus("0000");

		return jsonResponse;
	}
}
