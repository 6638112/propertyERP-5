package com.cnfantasia.server.ms.ebuyProductSettle.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnfantasia.server.api.pub.constant.DictConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.ExcelUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.common.utils.UUIDGenerater;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.IEbuySupplyMerchantBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.entity.EbuySupplyMerchantBankAccount;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.service.IEbuySupplyMerchantBankAccountBaseService;
import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;
import com.cnfantasia.server.domainbase.revenueApply.service.IRevenueApplyBaseService;
import com.cnfantasia.server.ms.ebuyProductSettle.constants.EbuySettleApplyParam;
import com.cnfantasia.server.ms.ebuyProductSettle.constants.PageType;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.EbuyProductSettleApplyExportDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.EbuyProductSettleApplyListDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.EbuyProductSettleDetailAdminDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.RevenueApplyDto;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.SettleApplyDialogInfoDto;
import com.cnfantasia.server.ms.ebuyProductSettle.service.IEbuyProductSettleService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.session.UserContext;

@Controller
@RequestMapping("/ebuyProductSettle")
public class EbuyProductSettleController extends BaseController {

	@Resource
	private IEbuyProductSettleService ebuyProductSettleService;
	@Resource
	private IRevenueApplyBaseService revenueApplyBaseService;
	@Resource
	private IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService;

	@Resource
	private IEbuySupplyMerchantBankAccountBaseService ebuySupplyMerchantBankAccountBaseService;

	/**
	 * 结算记录列表
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/revenueListIndex.html")
	public ModelAndView revenueListIndex(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		// 参数处理
		String applyTimeStart = ParamUtils.getString(request, "applyTimeStart", null);
		String applyTimeEnd = ParamUtils.getString(request, "applyTimeEnd", null);
		String settleStatus = ParamUtils.getString(request, "settleStatus", null);
		String supplyMerchantId = ParamUtils.getString(request, "supplyMerchantId", null);
		String revenueType = ParamUtils.getString(request, "revenueType", null);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<EbuySupplyMerchant> supplyMerchants = null;
		boolean isAdmin = UserContext.getCurrUser().getIsadmin() == 1;
		if(isAdmin){
			paramMap.clear();
			paramMap.put("storeAuditStatus", 1);
			paramMap.put("revenueType", 1);
			supplyMerchants = ebuySupplyMerchantBaseService.getEbuySupplyMerchantByConditionDim(paramMap);
			paramMap.clear();
			paramMap.put("revenueType", 2);
			supplyMerchants.addAll(ebuySupplyMerchantBaseService.getEbuySupplyMerchantByCondition(paramMap));
			modelAndView.addObject("supplyMerchants", supplyMerchants);
		}
		modelAndView.addObject("isAdmin", isAdmin);

		paramMap.clear();
		paramMap.put("applyTimeStart", applyTimeStart);
		paramMap.put("applyTimeEnd", applyTimeEnd);
		paramMap.put("settleStatus", settleStatus);
		if(!isAdmin){paramMap.put("applyManId", UserContext.getOperIdBigInteger());}
		paramMap.put("isAdmin", isAdmin?"yes":"no");
		paramMap.put("supplyMerchantId", supplyMerchantId);
		paramMap.put("revenueType", revenueType);

		int total = ebuyProductSettleService.selectRevenueForCount(paramMap);
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		// 分页信息
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		List<RevenueApplyDto> revenueApplys = ebuyProductSettleService.selectRevenueForList(paramMap);


		modelAndView.addObject("total", total);
		modelAndView.addObject("revenueApplys", revenueApplys);
		modelAndView.setViewName("/ebuyProductSettle/ebuyProductSettleList");

		return modelAndView;
	}

	/**
	 * 结算记录列表-查看详情
	 *
	 * @param revenueApplyId
	 * @param request
	 * @return
	 */
	@RequestMapping("/revenueListDetail.html")
	public ModelAndView revenueListDetail(BigInteger revenueApplyId, String revApplyFinanceId, String visibleType, HttpServletRequest request) {
		// 结算信息、审核结果
		RevenueApply revenueApply = revenueApplyBaseService.getRevenueApplyBySeqId(revenueApplyId);

		// 结算清单
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplyMerchantId", revenueApply.gettEbuySupplyMerchantFk());
		paramMap.put("revenueApplyId", revenueApplyId);
		// 修复收益中心合并bug：f_visible_type=1为合并后的
		paramMap.put("revApplyFinanceId", revApplyFinanceId);
		paramMap.put("visibleType", visibleType);
		
		paramMap.put("pageType", PageType.DETAIL);

		int applyTotal = ebuyProductSettleService.selectRevenueApplyForCount(paramMap);
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		// 分页信息
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		List<EbuyProductSettleApplyListDto> applyList = ebuyProductSettleService.selectRevenueApplyForList(paramMap, revenueApply.getRevenueType());
		EbuySupplyMerchant currMerchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(revenueApply.gettEbuySupplyMerchantFk());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("applyTotal", applyTotal);
		modelAndView.addObject("applyList", applyList);
		modelAndView.addObject("revenueApply", revenueApply);
		modelAndView.addObject("currMerchant", currMerchant);
		boolean isAdmin = UserContext.getCurrUser().getIsadmin() == 1;
		modelAndView.addObject("isAdmin", isAdmin);
		modelAndView.addObject("revApplyFinanceId", revApplyFinanceId);
		modelAndView.addObject("visibleType", visibleType);
		
		modelAndView.setViewName("/ebuyProductSettle/ebuyProductSettleDetail");
		return modelAndView;
	}

	/**
	 * 结算申请页面
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/revenueApplyListIndex.html")
	public ModelAndView revenueApplyListIndex(EbuySettleApplyParam param, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		List<EbuySupplyMerchant> merchants = ebuyProductSettleService.selectMerchantsWithPurchase(UserContext.getCurrUser().getUserAccount());

		boolean hasRight = true;
		if (DataUtil.isEmpty(param.getSupplyMerchantId())) {
			hasRight = unmerchantNoPermission(merchants, request, modelAndView);
		}

		if (hasRight) {
			EbuySupplyMerchant nowMerchant = null;
			BigInteger merchantId = DataUtil.isEmpty(param.getSupplyMerchantId()) ? merchants.get(0).getId() : param.getSupplyMerchantId();
			for (EbuySupplyMerchant merchant : merchants) {
				if (merchant.getId().compareTo(merchantId) == 0) {
					nowMerchant = merchant;
					break;
				}
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("merchantId", nowMerchant.getId());
			SettleApplyDialogInfoDto settleApplyDialogInfoDto = ebuyProductSettleService.selectRevenueApplyInfoForDialog(paramMap);

			paramMap.clear();
			paramMap.put("supplyMerchantId", nowMerchant.getId());
			paramMap.put("pageType", PageType.APPLY_LIST);

			// 2、可结算金额
			BigDecimal totalAmount = ebuyProductSettleService.selectRevenueApplyForTotalAmount(paramMap, nowMerchant.getRevenueType());

			paramMap.put("orderNo", param.getOrderNo());
			paramMap.put("payTimeStart", param.getPayTimeStart());
			paramMap.put("payTimeEnd", param.getPayTimeEnd());
			// 3、结算列表
			int applyTotal = ebuyProductSettleService.selectRevenueApplyForCount(paramMap);
			// 页数的参数名
			String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
			// 每页显示的条数
			int pageSize = 10;
			// 当前页
			int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
			// 分页信息
			paramMap.put("_begin", pageSize * curPageIndex);
			paramMap.put("_length", pageSize);
			List<EbuyProductSettleApplyListDto> applyList =  ebuyProductSettleService.selectRevenueApplyForList(paramMap, nowMerchant.getRevenueType());

			EbuySupplyMerchant currMerchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(nowMerchant.getId());
			modelAndView.addObject("totalAmount", totalAmount);
			modelAndView.addObject("applyTotal", applyTotal);
			modelAndView.addObject("applyList", applyList);
			modelAndView.addObject("applyDialogInfo", settleApplyDialogInfoDto);
			modelAndView.addObject("merchants", merchants);
			modelAndView.addObject("currMerchant", currMerchant);
			modelAndView.addObject("merchantId", merchantId);
			modelAndView.setViewName("/ebuyProductSettle/ebuyProductSettleApply");
		}
		return modelAndView;
	}

	/**
	 * 非供应商无权限进入
	 *
	 * @param supplyMerchantIds
	 * @param request
	 * @param mav
	 * @return
	 */
	private boolean unmerchantNoPermission(List<EbuySupplyMerchant> merchants, HttpServletRequest request, ModelAndView mav){
		if (DataUtil.isEmpty(merchants)) {
			request.setAttribute(JSPConstants.OprtResult, "非供应商无权限进入！");
			request.setAttribute(JSPConstants.ToURL, "../security/welcome.html");
			mav.setViewName(JSPConstants.OprtSuccessPage);
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 结算申请页面列表导出
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/exportRevenueApplyList.html")
	public void exportRevenueApplyList(EbuySettleApplyParam param, HttpServletResponse response) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplyMerchantId", param.getSupplyMerchantId());
		paramMap.put("orderNo", param.getOrderNo());
		paramMap.put("payTimeStart", param.getPayTimeStart());
		paramMap.put("payTimeEnd", param.getPayTimeEnd());
		paramMap.put("pageType", param.getPageType());
		paramMap.put("revenueApplyId", param.getRevenueApplyId());
		// 修复收益中心合并bug：f_visible_type=1为合并后的
		paramMap.put("revApplyFinanceId", param.getRevApplyFinanceId());
		paramMap.put("visibleType", param.getVisibleType());
		
		BigInteger supplyMerchantId;
		if (!DataUtil.isEmpty(param.getRevenueApplyId())) {
			supplyMerchantId = revenueApplyBaseService.getRevenueApplyBySeqId(param.getRevenueApplyId()).gettEbuySupplyMerchantFk();
		} else {
			supplyMerchantId = param.getSupplyMerchantId();
		}
		EbuySupplyMerchant merchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(supplyMerchantId);
		if (merchant.getRevenueType().compareTo(1) == 0) {
			String exportFileName = "购销_"+ UUIDGenerater.getFileName();
			HSSFWorkbook workbook = ebuyProductSettleService.exportRevenueApplyList(paramMap);
			ExcelUtil.commonExport(exportFileName, workbook, response);
		} else if (merchant.getRevenueType().compareTo(2) == 0) {
			String exportFileName = "代销_"+ UUIDGenerater.getFileName();
			List<EbuyProductSettleApplyExportDto> data = ebuyProductSettleService.getEbuyProductSettleApplyList(paramMap);
			if (!DataUtil.isEmpty(data)) {

				//最后一行的统计
				EbuyProductSettleApplyExportDto lastLine = new EbuyProductSettleApplyExportDto();
				lastLine.setDeliveryOrderProductTotalAmount(BigDecimal.valueOf(0));
				lastLine.setDeliveryOrderTotalAmount(BigDecimal.valueOf(0));
				lastLine.setDeliveryOrderSettleAmount(BigDecimal.valueOf(0));
				lastLine.setDeliveryOrderTotalSettleAmount(BigDecimal.valueOf(0));
				lastLine.setDeliveryRefundFee(BigDecimal.valueOf(0));
				lastLine.setDeliveryRealFee(BigDecimal.valueOf(0));

				for (int i = 0; i < data.size(); i++) {
					EbuyProductSettleApplyExportDto dto = data.get(i);
					if (i == 0 || dto.getDeliveryOrderId().compareTo(data.get(i-1).getDeliveryOrderId()) != 0) {
						//商品金额小计
						dto.setDeliveryOrderProductTotalAmount(PriceUtil.div100(Long.valueOf(dto.getDeliveryOrderAmount() + dto.getDeliveryOrderCoupon())).subtract(dto.getDeliveryRealFee()));
						//订单总金额
						BigDecimal orderTotalAmount = PriceUtil.div100(Long.valueOf(dto.getDeliveryOrderAmount() + dto.getDeliveryOrderCoupon())).subtract(dto.getDeliveryRefundFee()).subtract(dto.getDeliveryRealFee());
						dto.setDeliveryOrderTotalAmount(orderTotalAmount);
						//订单结算金额
						BigDecimal orderSettleAmount = dto.getDeliveryOrderTotalAmount().subtract(dto.getDeliveryOrderTotalAmount().multiply(dto.getRevenueRate())).setScale(2, RoundingMode.HALF_UP);
						dto.setDeliveryOrderSettleAmount(orderSettleAmount);
						//订单结算总额
						dto.setDeliveryOrderTotalSettleAmount(dto.getDeliveryOrderSettleAmount().add(dto.getDeliveryRealFee()));

						//最后一行数据
						lastLine.setDeliveryOrderProductTotalAmount(lastLine.getDeliveryOrderProductTotalAmount().add(dto.getDeliveryOrderProductTotalAmount()));
						lastLine.setDeliveryOrderTotalAmount(lastLine.getDeliveryOrderTotalAmount().add(dto.getDeliveryOrderTotalAmount()));
						lastLine.setDeliveryOrderSettleAmount(lastLine.getDeliveryOrderSettleAmount().add(dto.getDeliveryOrderSettleAmount()));
						lastLine.setDeliveryOrderTotalSettleAmount(lastLine.getDeliveryOrderTotalSettleAmount().add(dto.getDeliveryOrderTotalSettleAmount()));
						lastLine.setDeliveryRefundFee(lastLine.getDeliveryRefundFee().add(dto.getDeliveryRefundFee()));
						lastLine.setDeliveryRealFee(lastLine.getDeliveryRealFee().add(dto.getDeliveryRealFee()));
					} else {
						dto.setDeliveryRefundFee(null);
						dto.setDeliveryRealFee(null);
						dto.setReceiver(null);
						dto.setReceiverMobile(null);
						dto.setDelivAddress(null);
					}
				}
				data.add(lastLine);
			}
			ExcelUtil<EbuyProductSettleApplyExportDto> excelUtil = new ExcelUtil<EbuyProductSettleApplyExportDto>();
			excelUtil.exportExcelWithoutTemplate(exportFileName, "sheet1", data, EbuyProductSettleApplyExportDto.class, response);
		}
	}

	/**
	 * 结算申请
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/applyRevenue.html")
	public ModelAndView applyRevenue(BigInteger supplyMerchantId, HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplyMerchantId", supplyMerchantId);
		paramMap.put("pageType", PageType.APPLY_LIST);

		int applyTotal = ebuyProductSettleService.selectRevenueApplyForCount(paramMap);
		if (applyTotal <= 0) {
			request.setAttribute(JSPConstants.OprtResult, "您目前已经没有未申请结算的订单，审核结算操作失败！");
			request.setAttribute(JSPConstants.ToURL, "../ebuyProductSettle/revenueApplyListIndex.html?merchantId="+supplyMerchantId);
		} else {
			// 查询银行卡信息是否为空
			if(isNullWithMerchantBankAccount(supplyMerchantId)){
				request.setAttribute(JSPConstants.OprtResult, "银行卡信息不全，请完善银行卡信息！申请失败！");
				request.setAttribute(JSPConstants.ToURL, "../ebuyProductSettle/revenueApplyListIndex.html?merchantId="+supplyMerchantId);
			} else {
				String mobile = ParamUtils.getString(request, "mobile", null);
				String note = ParamUtils.getString(request, "note", null);
				BigInteger applyId = ebuyProductSettleService.applyRevenue(UserContext.getOperIdBigIntegerMustExist(), supplyMerchantId, mobile, note, DictConstants.Channel_Sub.OOS);

				if (applyId != null) {
					request.setAttribute(JSPConstants.OprtResult, "操作成功！");
					request.setAttribute(JSPConstants.ToURL, "../ebuyProductSettle/revenueListIndex.html");
				} else {
					request.setAttribute(JSPConstants.OprtResult, "操作失败！");
					request.setAttribute(JSPConstants.ToURL, "../ebuyProductSettle/revenueApplyListIndex.html");
				}
			}
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}

	/**
	 * 判断供应商银行卡信息是否为空
	 *
	 * @param supplyMerchantId
	 * @return
	 */
	private boolean isNullWithMerchantBankAccount(BigInteger supplyMerchantId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tSupplyMerchantId", supplyMerchantId);
		List<EbuySupplyMerchantBankAccount> merchantBankAccounts = ebuySupplyMerchantBankAccountBaseService.getEbuySupplyMerchantBankAccountByCondition(paramMap);
		if (merchantBankAccounts == null || merchantBankAccounts.size() == 0) {
			return true;
		} else {
			EbuySupplyMerchantBankAccount merchantBankAccount = merchantBankAccounts.get(0);
			if (StringUtils.isEmpty(merchantBankAccount.getAccountBank()) ||
					StringUtils.isEmpty(merchantBankAccount.getBankName()) ||
					StringUtils.isEmpty(merchantBankAccount.getAccountName()) ||
					StringUtils.isEmpty(merchantBankAccount.getBankBranch())) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 结算审核详情
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/revenueHandlerDetail.html")
	public ModelAndView revenueHandlerDetail(HttpServletRequest request) {
		String revenueApplyId = request.getParameter("revenueApplyId");
		RevenueApply revenueApply = revenueApplyBaseService.getRevenueApplyBySeqId(new BigInteger(revenueApplyId));

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("revenueApply", revenueApply);
		modelAndView.addObject("revenueApplyId", revenueApplyId);
		modelAndView.setViewName("/ebuyProductSettle/ebuyProductSettleHandler");
		return modelAndView;
	}

	/**
	 * 结算申请处理
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/revenueApplyHandler.html")
	public ModelAndView revenueApplyHandler(HttpServletRequest request) {
		BigInteger revenueApplyId = ParamUtils.getBigInteger(request, "revenueApplyId", null);
		Integer auditStatus = ParamUtils.getInteger(request, "auditStatus", null);
		String handlerNote = ParamUtils.getString(request, "handlerNote", null);
		BigInteger userId = UserContext.getOperIdBigInteger();

		ModelAndView modelAndView = new ModelAndView();
		if(ebuyProductSettleService.revenueApplyHandler(revenueApplyId, auditStatus, handlerNote, userId)){
			request.setAttribute(JSPConstants.OprtResult, "操作成功！");
			request.setAttribute(JSPConstants.ToURL, "../ebuyProductSettle/revenueListIndex.html");
		} else {
			request.setAttribute(JSPConstants.OprtResult, "操作失败！");
			request.setAttribute(JSPConstants.ToURL, "../ebuyProductSettle/revenueHandlerDetail.html?revenueApplyId="+revenueApplyId.longValue());
		}
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}

	/**
	 * 结算管理导出
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping("/exportRevenueApplyListForAdmin.html")
	public void exportRevenueApplyListForAdmin(HttpServletRequest request, HttpServletResponse response) {
		BigInteger supplyMerchantId = ParamUtils.getBigInteger(request, "supplyMerchantId", null);
		String applyTimeStart = ParamUtils.getString(request, "applyTimeStart", null);
		String applyTimeEnd = ParamUtils.getString(request, "applyTimeEnd", null);
		String settleStatus = ParamUtils.getString(request, "settleStatus", null);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplyMerchantId", supplyMerchantId);
		paramMap.put("applyTimeStart", applyTimeStart);
		paramMap.put("applyTimeEnd", applyTimeEnd);
		paramMap.put("settleStatus", settleStatus);

		String exportFileName = "GLYJSGL_"+ UUIDGenerater.getFileName();
		HSSFWorkbook workbook = ebuyProductSettleService.selectRevenueAdminExportList(paramMap);
		ExcelUtil.commonExport(exportFileName, workbook, response);
	}

	/**
	 * 结算管理详情导出
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping("/exportRevenueDetailAdmin.html")
	public void exportRevenueDetailAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BigInteger revenueApplyId = ParamUtils.getBigInteger(request, "revenueApplyId", null);
		RevenueApply apply = revenueApplyBaseService.getRevenueApplyBySeqId(revenueApplyId);
		String revApplyFinanceId = ParamUtils.getString(request, "revApplyFinanceId", null);
		String visibleType = ParamUtils.getString(request, "visibleType", null);
		if (apply.getRevenueType().compareTo(1) == 0) {
			String exportFileName = "购销_" + UUIDGenerater.getFileName();
			HSSFWorkbook workbook = ebuyProductSettleService.selectRevenueDetailAdminExportList(revenueApplyId, revApplyFinanceId, visibleType);
			ExcelUtil.commonExport(exportFileName, workbook, response);
		} else {
			String exportFileName = "抽佣_" + UUIDGenerater.getFileName();
			ExcelUtil<EbuyProductSettleDetailAdminDto> excelUtil = new ExcelUtil<EbuyProductSettleDetailAdminDto>();
			List<EbuyProductSettleDetailAdminDto> data = ebuyProductSettleService.getEbuyProductSettleDetailExport(revenueApplyId, revApplyFinanceId, visibleType);
			excelUtil.exportExcelWithoutTemplate(exportFileName,"sheet", data, EbuyProductSettleDetailAdminDto.class, response);
		}
	}

}
