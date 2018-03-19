package com.cnfantasia.server.ms.ebuy.web;

import com.cnfantasia.server.api.ebuyorder.service.EbuyOrderPusherService;
import com.cnfantasia.server.api.homeMessage.constant.HomeMessageDict;
import com.cnfantasia.server.api.homeMessage.entity.UserUnReceiveDelivOrderEntity;
import com.cnfantasia.server.api.homeMessage.service.HomeMessageService;
import com.cnfantasia.server.api.homeMessage.service.IHomeMessageService;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.deliveryOrderExpressTrace.entity.DeliveryOrderExpressTrace;
import com.cnfantasia.server.domainbase.deliveryOrderExpressTrace.service.IDeliveryOrderExpressTraceBaseService;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.service.IEbuyDeliveryOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderComment.entity.EbuyDeliveryOrderComment;
import com.cnfantasia.server.domainbase.ebuyExpressCompany.entity.EbuyExpressCompany;
import com.cnfantasia.server.domainbase.ebuyExpressCompany.service.IEbuyExpressCompanyBaseService;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrder.service.IEbuyOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyPayRecord.entity.EbuyPayRecord;
import com.cnfantasia.server.domainbase.ebuyPayRecord.service.IEbuyPayRecordBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.IEbuySupplyMerchantBaseService;
import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;
import com.cnfantasia.server.ms.ebuy.entity.OrderBean;
import com.cnfantasia.server.ms.ebuy.entity.OrderDetailsBean;
import com.cnfantasia.server.ms.ebuy.entity.OrderExportBean;
import com.cnfantasia.server.ms.ebuy.entity.OrderProductBean;
import com.cnfantasia.server.ms.ebuy.service.IEbuyorderService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtils;
import com.cnfantasia.server.ms.refundOrder.utils.KuaiDi;
import com.cnfantasia.server.ms.refundOrder.utils.KuaiDiMsg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 类说明：订单管理
 * 
 * @author hunter
 * @since 2014年6月5日下午12:02:05
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

	private Log logger = LogFactory.getLog(OrderController.class);

//	@Resource
//	private EbuyorderController ebuyorderController;
	
	@Resource
	IEbuyorderService ebuyorderService;

	@Resource
	IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService;
	
	@Resource
	IEbuyExpressCompanyBaseService ebuyExpressCompanyBaseService;
	
	@Resource
	IEbuyDeliveryOrderBaseService ebuyDeliveryOrderBaseService;
	
	@Resource
	EbuyOrderPusherService ebuyOrderPusherService;
	
	@Resource
	IEbuyPayRecordBaseService ebuyPayRecordBaseService;

	@Resource
	private IDeliveryOrderExpressTraceBaseService deliveryOrderExpressTraceBaseService;

	@Resource
	private IHomeMessageService homeMessageService;

	private List<EbuyExpressCompany> expressList;
	
	private List<EbuySupplyMerchant> supplyMerchantList;
	
	
	/**
	 * 订单管理首页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/index.html")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		if(expressList == null) {
			expressList = ebuyExpressCompanyBaseService.getEbuyExpressCompanyByCondition(new HashMap<String, Object>());
		}
		request.setAttribute("expressList", expressList);
		Map<String, Object> requestParams = getRequestParams(request);
		
		List<EbuySupplyMerchant> supplyList = UserContext.getSupplyMerchantList();
		if(UserContext.getCurrUser().getIsadmin() == 1) {
			if(request.getSession().getAttribute("supplyMerchantList") == null) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.clear();
				paramMap.put("storeAuditStatus", 1);
				supplyMerchantList = ebuySupplyMerchantBaseService.getEbuySupplyMerchantByCondition(paramMap);
				request.getSession().setAttribute("supplyMerchantList", supplyMerchantList);
			}
			request.setAttribute("supplyMerchantList", supplyMerchantList);
		} else if(supplyList != null && supplyList.size() > 0) {
			requestParams.put("supplyList", supplyList);
			request.setAttribute("isSupply", true);
//			request.setAttribute("supplyMerchantList", supplyList);
			request.getSession().setAttribute("supplyMerchantList", supplyList);
		}
		
		int resultSize = 0;
		List<OrderBean> resList = new ArrayList<OrderBean>();
//		if((supplyList != null && supplyList.size() > 0) || UserContext.getCurrUser().getIsadmin() == 1) {
			resultSize = ebuyorderService.selectOrderCount(requestParams);
			PageUtils.addPageInfoToParam(request, requestParams);
			resList = ebuyorderService.selectOrderListByPage(requestParams);
//		}
		
		// 只有审核通过的店铺，页面“供应商名 称”搜索框才显示
		BigInteger merchantId = UserContext.getCurrUser().getMerchantId();
		if(merchantId!=null){
			EbuySupplyMerchant ebuySupplyMerchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(merchantId);
			if(ebuySupplyMerchant.getStoreAuditStatus()!=null && ebuySupplyMerchant.getStoreAuditStatus()!=2){
				request.setAttribute("sm", "invisible");
			}
		}
				
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("resList", resList);
		modelAndView.setViewName("/ebuy/indexSupply");
		return modelAndView;
	}
	
	/**
	 * 订单详情页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewOrderDetail.html")
	public ModelAndView viewOrderDetail(HttpServletRequest request,ModelMap model) {
		ModelAndView modelAndView = new ModelAndView("/ebuy/orderDetail");

		Map<String, Object> requestParams = getRequestParams(request);
		OrderDetailsBean orderDetail = ebuyorderService.selectOrderDetail(requestParams);
		
		//增加最后一行总计的显示
		long total = 0L;
		for(OrderProductBean product : orderDetail.getProductList()) {
			total += product.getTotalPrice();
		}
		OrderProductBean lastProduct = new OrderProductBean();
		lastProduct.setForDisplay("总计");
		lastProduct.setTotalPrice(total);
		orderDetail.getProductList().add(lastProduct);
		
		request.setAttribute("orderDetail", orderDetail);
		
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tEbuyOrderFId", orderDetail.getId());
		paramMap.put("payStatus", 2);
		List<EbuyPayRecord> ebuyPayRecordList = ebuyPayRecordBaseService.getEbuyPayRecordByCondition(paramMap);
		if(ebuyPayRecordList.size() > 0) {
			request.setAttribute("ebuyPayRecord", ebuyPayRecordList.get(0));
		}
		
		List<EbuySupplyMerchant> supplyList = UserContext.getSupplyMerchantList();
		if(UserContext.getCurrUser().getIsadmin() != 1 && supplyList != null && supplyList.size() > 0) {
			request.setAttribute("isSupply", true);
		}

		//物流跟踪
		if(orderDetail.getDeliveryStatus() ==2 || orderDetail.getDeliveryStatus() ==3){
			if ("依谷自营快递".equals(orderDetail.getExpressName())) {
				model.addAttribute("isSelfExpress", true);
				DeliveryOrderExpressTrace trace = new DeliveryOrderExpressTrace();
				trace.setDeliveryOrderId(new BigInteger(request.getParameter("delieveOrderId")));
				List<DeliveryOrderExpressTrace> traces = deliveryOrderExpressTraceBaseService.
						getDeliveryOrderExpressTraceByCondition(MapConverter.toMap(trace));
				model.addAttribute("content", traces);
			} else {
				String content = KuaiDi.searchkuaiDiInfo(KuaiDiMsg.KuaiDi100,orderDetail.getKuaidiCom(), orderDetail.getExpressNo());
				model.addAttribute("content", content);
				model.addAttribute("isSelfExpress", false);
			}
		}
		
		// order comment
		String orderId = request.getParameter("delieveOrderId");
		model.addAttribute("orderId", orderId);
		HashMap<String,Object> commentMap = new HashMap<String,Object>();
		commentMap.put("tEbuyOrderFId", orderId);
		model.addAttribute("orderComments", ebuyorderService.selectEbuyDeliveryOrderCommentByCondition(commentMap, false));
		return modelAndView;
	}
	
	@RequestMapping("/saveExpress.html")
	@ResponseBody
	public Object saveExpress(Long orderId, Long delieveOrderId, Long expressId, String expressNo) throws Exception {
		JsonResponse json = new JsonResponse();
		try {
			EbuyDeliveryOrder ebuyDeliveryOrder = new EbuyDeliveryOrder();
			ebuyDeliveryOrder.setId(BigInteger.valueOf(delieveOrderId));
			ebuyDeliveryOrder.settEbuyOrderFId(BigInteger.valueOf(orderId));
			ebuyDeliveryOrder.settEbuyExpressCompanyFId(BigInteger.valueOf(expressId));
			ebuyDeliveryOrder.setExpressNo(expressNo);
			ebuyDeliveryOrder.setStatus(EbuyDict.EbuyDeliveryOrder_Status.DaiShouHuo);
			ebuyDeliveryOrder.setDeliveryTime(DateUtils.getCurrentDate());
			ebuyorderService.saveExpressInfo(ebuyDeliveryOrder);
			
			json.setMessage("录入物流单号成功");
			json.setStatus("info");

			//更新运单主的消息
			UserHasHomeMessage message = new UserHasHomeMessage();
			message.setMessageCode(HomeMessageDict.MessageCode.EBUY_STORE_ALERT);
			message.setResouceId(BigInteger.valueOf(delieveOrderId));
			homeMessageService.generateCommonMsg(message);


		} catch (RuntimeException re) {
			json.setMessage(re.getMessage());
			json.setStatus("error");
		} catch (Exception e) {
			logger.error(e);
			json.setMessage("录入物流单号失败");
			json.setStatus("error");
		}
		return json;
	}
	
	@RequestMapping("/saveFee.html")
	@ResponseBody
	public Object saveFee(Long orderId, Long delieveOrderId, String deliFee) throws Exception {
		JsonResponse message = new JsonResponse();
		try {
			BigDecimal deliFeeB = new BigDecimal(deliFee);
			Long deliFeeL = deliFeeB.multiply(BigDecimal.valueOf(100L)).longValue();
			
			EbuyDeliveryOrder ebuyDeliveryOrder = new EbuyDeliveryOrder();
			ebuyDeliveryOrder.setId(BigInteger.valueOf(delieveOrderId));
			ebuyDeliveryOrder.settEbuyOrderFId(BigInteger.valueOf(orderId));
			ebuyDeliveryOrder.setDeliverySettlementFee(deliFeeL);
			CnfantasiaCommUtil.updateStandard(ebuyDeliveryOrder);
			ebuyDeliveryOrderBaseService.updateEbuyDeliveryOrder(ebuyDeliveryOrder);
			
			message.setMessage("录入运费成功");
			message.setStatus("info");
		} catch (RuntimeException re) {
			message.setMessage(re.getMessage());
			message.setStatus("error");
		} catch (Exception e) {
			logger.error(e);
			message.setMessage("录入运费失败");
			message.setStatus("error");
		}
		return message;
	}
	
	@RequestMapping("/pushAgain.html")
	@ResponseBody
	public String pushAgain(long delieveOrderId, long orderId) throws Exception {
		// "推送成功" or "具体错误信息"
		return ebuyOrderPusherService.pushAaginForDeliveryOrder(delieveOrderId, orderId);
	}

	/**
	 * 导出订单明细
	 * 
	 * @param form
	 * @param request
	 * @param response
	 */
	@RequestMapping("/orderExport")
	public void orderExport(HttpServletRequest request, HttpServletResponse response) {
		OutputStream fOut = null;
		InputStream fin = null;
		try {
			Map<String, Object> requestParams = getRequestParams(request);
			
			List<EbuySupplyMerchant> supplyList = UserContext.getSupplyMerchantList();
			if(UserContext.getCurrUser().getIsadmin() != 1 && supplyList != null && supplyList.size() > 0) {
				requestParams.put("supplyList", supplyList);
			}
			
			List<OrderExportBean> orderExportList = null;
//			if((supplyList != null && supplyList.size() > 0) || UserContext.getCurrUser().getIsadmin() == 1) {
				orderExportList = ebuyorderService.getOrderForExport(requestParams);
//			}
			
			// 生成提示信息
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String fileName = format.format(new Date());
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
			fOut = response.getOutputStream();
			HSSFWorkbook workbook;
			if(supplyList!= null && supplyList.size() > 0) {
				workbook = createReportSupply(orderExportList);
			} else {
				workbook = createReport(orderExportList);
			}
			
			workbook.write(fOut);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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
	
	public HSSFWorkbook createReport(List<OrderExportBean> orderExportList) {
		String[] titles = {"订单号","解放号","运单状态","订单生成时间","支付时间","支付方式","支付流水号","支付状态","结算状态", "有否退款", "退款状态", "退款方式", "退款金额","小区名称",
						"供应商名称","供应商属性","商品名称", "商品类别", "商品销售单价(元)", "购买数量", "尺寸", "颜色", "购买单位","商品销售总额(元)", "解放区运费","运单售价","订单售价",
						"使用优惠", "实际支付", "物流方式", "物流单号","商品采购单价","商品采购总额","供应商运费","运单采购价","订单采购价", "收货人姓名", "收货人联系方式","收货地址","身份证号"};
		
//		String[] titles = {"订单号","解放号","订单状态","订单生成时间","支付时间","支付方式","支付状态","供应商名称", "商品名称", "商品类别", "商品单价(元)", "购买数量", "尺寸", "颜色", "购买单位","商品金额(元)", "活动", "物流费用(元)",
//				"订单总额(元)","使用优惠", "实际支付", "物流方式", "物流单号", "收货人姓名", "收货人联系方式","收货地址"};
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("订单明细");
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
		if(orderExportList != null) {
			for (int i = 0, j = 0; j < orderExportList.size(); j++) {
				row = sheet.createRow((j+1));
				// 设置excel每列宽度
				sheet.setColumnWidth(j, width);
				OrderExportBean bean = orderExportList.get(j);
				i = 0;
//				{"订单号","解放号","订单状态","订单生成时间","支付时间","支付方式","支付流水号","支付状态","结算状态", "有否退款", "退款状态", "退款方式", "退款金额",
//				"供应商名称","供应商属性","商品名称", "商品类别", "商品销售单价(元)", "购买数量", "尺寸", "颜色", "购买单位","商品销售总额(元)", "解放区运费","运单售价","订单售价",
//				"使用优惠", "实际支付", "物流方式", "物流单号","商品采购单价","商品采购总额","供应商运费","运单采购价","订单采购价", "收货人姓名", "收货人联系方式","收货地址"}
				fillCellData(row, i++, bean.getOrderNo(),style);
				fillCellData(row, i++, bean.getHuaId(),style);
//				fillCellData(row, i++, bean.getStatusStr(),style);
				if(bean.getStatus() == 1) {
//					fillCellData(row, i++, "待支付",style);
					fillCellData(row, i++, "",style);
				} else if(bean.getRefundStatus() == 2) {
					fillCellData(row, i++, "已退款",style);
				} else if(bean.getRefundStatus() == 1) {
					fillCellData(row, i++, "退款中",style);
				} else if(bean.getStatus() == 3 && bean.getDeliveryStatus() <= 1 && bean.getRefundStatus() != 1 && bean.getRefundStatus() != 2) { // && refundStatus != 1 && refundStatus != 2
					fillCellData(row, i++, "待发货",style);
				} else if(bean.getDeliveryStatus() == 2) {
					fillCellData(row, i++, "待收货",style);
//				} else if(bean.getStatus() < 6 || bean.getDeliveryStatus() == 3) {
//					fillCellData(row, i++, "待评价",style);
				} else if(bean.getDeliveryStatus() == 3) {
					fillCellData(row, i++, "交易完成",style);
				}
				fillCellData(row, i++, DateUtils.formatTime(bean.getBuyTime()),style);
				fillCellData(row, i++, DateUtils.formatTime(bean.getPayTime()),style);
				fillCellData(row, i++, bean.getPayMethodStr(),style);
				fillCellData(row, i++, bean.getFlowNo(),style); //支付流水号
				fillCellData(row, i++, bean.getPayStatusStr(),style);
				fillCellData(row, i++, bean.getTkStatusStr(),style);
				if(bean.getRefundStatus() != null && bean.getRefundStatus() == 2) { //'退款状态={"0":"未启动","1":"退款中","2":"退款成功","3":"退款失败"}',
					fillCellData(row, i++, "有退款",style);
					fillCellData(row, i++, bean.getRefundOrderStatusStr(),style);//退款状态　{"1":"部分退款","2":"全额退款"}'
					fillCellData(row, i++, bean.getRefundPayStatusStr(),style); // "退款方式" 
					fillCellData(row, i++, bean.getRefundFee() == null ? "" : String.valueOf(bean.getRefundFee()),style); //退款金额
				} else {
//					fillCellData(row, i++, "",style);
					i += 4;
				}
				
				fillCellData(row, i++, bean.getGroupBuildingName(),style);
				fillCellData(row, i++, bean.getSupplyName(),style);
				fillCellData(row, i++, bean.getSupplyTypeStr(),style);
				fillCellData(row, i++, (bean.getProductName2() == null ? bean.getProductName() : bean.getProductName2()) ,style);
				fillCellData(row, i++, bean.getProductType(),style);
				fillCellData(row, i++, String.valueOf((bean.getPrice() == null ? 0 : bean.getPrice())/100.0D),style);//商品销售单价
				fillCellData(row, i++, bean.getQty().toString(),style);
				fillCellData(row, i++, bean.getSize(),style);
				fillCellData(row, i++, bean.getColour(),style);
				fillCellData(row, i++, bean.getPriceUnit(),style);
				fillCellData(row, i++, String.valueOf((bean.getPrice() * bean.getQty())/100.0D),style);//商品销售总额(元)
//				fillCellData(row, i++, String.valueOf(bean.getAmount()/100.0D),style);//商品销售总额(元)
				fillCellData(row, i++, String.valueOf((bean.getDeliveryFee() == null ? 0 : bean.getDeliveryFee().longValue())/100.0D),style);
				fillCellData(row, i++, String.valueOf((bean.getAmount() + bean.getDeliveryFee()) /100.0D),style);
				fillCellData(row, i++, String.valueOf((bean.getOrderAmount() + bean.getCouponAmount())/100.0D),style);//订单售价
				fillCellData(row, i++, String.valueOf((bean.getDeliveryOrderCoupon() == null ? 0 : bean.getDeliveryOrderCoupon())/100.0D),style); //粮票使用 //使用优惠
				fillCellData(row, i++, String.valueOf((bean.getDeliveryOrderAmount() == null ? 0 : bean.getDeliveryOrderAmount())/100.0D),style);//实际支付
				fillCellData(row, i++, bean.getExpressName(),style); //"物流方式"
				fillCellData(row, i++, bean.getExpressNo(),style);//物流单号
//				fillCellData(row, i++, bean.getOpName(),style); //活动名称
				fillCellData(row, i++, String.valueOf(bean.getPurchasePrice()/100.0D),style);//商品采购单价
				fillCellData(row, i++, String.valueOf(bean.getPurchasePrice()*bean.getQty()/100.0D),style);//商品采购总额
				fillCellData(row, i++, String.valueOf(bean.getDeliverySettleFee()/100.0D),style);//deliverySettleFee  供应商运费
				fillCellData(row, i++, String.valueOf((bean.getAmountPurchage() + bean.getDeliverySettleFee())/100.0D),style);//运单采购价
				fillCellData(row, i++, String.valueOf(bean.getAmoutPurchageOrder()/100.0D),style);//订单采购价 //FIXME
				
//				fillCellData(row, i++, String.valueOf((bean.getOrderAmount() + (bean.getCouponAmount() == null ? 0 : bean.getCouponAmount()))/100.0D),style);
//				fillCellData(row, i++, String.valueOf(bean.getOrderAmount()/100.0D),style);
				
				
				fillCellData(row, i++, bean.getDelivPeopleName(),style); //"收货人姓名"
				fillCellData(row, i++, bean.getDelivPhone(),style); //收货人联系方式
				fillCellData(row, i++, bean.getDelivAddressArea() + bean.getDelivAddressDetail(),style);//收货地址
				fillCellData(row, i++, bean.getIdentifyNo(), style); //身份证号
			}
		}

		// 设置单元格的样式格式
		return wb;
	}
	
	public HSSFWorkbook createReportSupply(List<OrderExportBean> orderExportList) {
		String[] titles = {"订单号","解放号","订单状态","订单生成时间","支付时间",
				"供应商名称","商品名称", "购买数量",  "物流方式", "物流单号","商品采购单价","商品采购总额","供应商运费","运单采购价", "收货人姓名", "收货人联系方式","收货地址"};
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("订单明细");
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
		
		if(orderExportList != null) {
			for (int i = 0, j = 0; j < orderExportList.size(); j++) {
				row = sheet.createRow((j+1));
				// 设置excel每列宽度
				sheet.setColumnWidth(j, width);
				OrderExportBean bean = orderExportList.get(j);
				i = 0;
//				{"订单号","解放号","订单状态","订单生成时间","支付时间","支付方式","支付流水号","支付状态","结算状态", "有否退款", "退款状态", "退款方式", "退款金额",
//				"供应商名称","供应商属性","商品名称", "商品类别", "商品销售单价(元)", "购买数量", "尺寸", "颜色", "购买单位","商品销售总额(元)", "解放区运费","运单售价","订单售价",
//				"使用优惠", "实际支付", "物流方式", "物流单号","商品采购单价","商品采购总额","供应商运费","运单采购价","订单采购价", "收货人姓名", "收货人联系方式","收货地址"}
				fillCellData(row, i++, bean.getOrderNo(),style);
				fillCellData(row, i++, bean.getHuaId(),style);
//				fillCellData(row, i++, bean.getStatusStr(),style);
				if(bean.getStatus() == 1) {
//					fillCellData(row, i++, "待支付",style);
					fillCellData(row, i++, "",style);
				} else if(bean.getRefundStatus() == 2) {
					fillCellData(row, i++, "已退款",style);
				} else if(bean.getRefundStatus() == 1) {
					fillCellData(row, i++, "退款中",style);
				} else if(bean.getStatus() == 3 && bean.getDeliveryStatus() <= 1 && bean.getRefundStatus() != 1 && bean.getRefundStatus() != 2) { // && refundStatus != 1 && refundStatus != 2
					fillCellData(row, i++, "待发货",style);
				} else if(bean.getDeliveryStatus() == 2) {
					fillCellData(row, i++, "待收货",style);
//				} else if(bean.getStatus() < 6 || bean.getDeliveryStatus() == 3) {
//					fillCellData(row, i++, "待评价",style);
				} else if(bean.getDeliveryStatus() == 3) {
					fillCellData(row, i++, "交易完成",style);
				}
				fillCellData(row, i++, DateUtils.formatTime(bean.getBuyTime()),style);
				fillCellData(row, i++, DateUtils.formatTime(bean.getPayTime()),style);
//				fillCellData(row, i++, bean.getPayMethodStr(),style);
//				fillCellData(row, i++, bean.getFlowNo(),style); //支付流水号
//				fillCellData(row, i++, bean.getPayStatusStr(),style);
//				fillCellData(row, i++, bean.getTkStatusStr(),style);
//				if(bean.getRefundStatus() != null && bean.getRefundStatus() == 2) { //'退款状态={"0":"未启动","1":"退款中","2":"退款成功","3":"退款失败"}',
//					fillCellData(row, i++, "有退款",style);
//					fillCellData(row, i++, bean.getRefundOrderStatusStr(),style);//退款状态　{"1":"部分退款","2":"全额退款"}'
//					fillCellData(row, i++, bean.getRefundPayStatusStr(),style); // "退款方式" 
//					fillCellData(row, i++, bean.getRefundFee() == null ? "" : String.valueOf(bean.getRefundFee()),style); //退款金额
//				} else {
////					fillCellData(row, i++, "",style);
//					i += 4;
//				}
				
				fillCellData(row, i++, bean.getSupplyName(),style);
//				fillCellData(row, i++, bean.getSupplyTypeStr(),style);
				fillCellData(row, i++, (bean.getProductName2() == null ? bean.getProductName() : bean.getProductName2()) ,style);
//				fillCellData(row, i++, bean.getProductType(),style);
//				fillCellData(row, i++, String.valueOf(bean.getPrice()/100.0D),style);//商品销售单价
				fillCellData(row, i++, bean.getQty().toString(),style);
//				fillCellData(row, i++, bean.getSize(),style);
//				fillCellData(row, i++, bean.getColour(),style);
//				fillCellData(row, i++, bean.getPriceUnit(),style);
//				fillCellData(row, i++, String.valueOf((bean.getPrice() * bean.getQty())/100.0D),style);//商品销售总额(元)
////				fillCellData(row, i++, String.valueOf(bean.getAmount()/100.0D),style);//商品销售总额(元)
//				fillCellData(row, i++, String.valueOf((bean.getDeliveryFee() == null ? 0 : bean.getDeliveryFee().longValue())/100.0D),style);
//				fillCellData(row, i++, String.valueOf((bean.getAmount() + bean.getDeliveryFee()) /100.0D),style);
//				fillCellData(row, i++, String.valueOf((bean.getOrderAmount() + bean.getCouponAmount())/100.0D),style);//订单售价
//				fillCellData(row, i++, String.valueOf((bean.getCouponAmount() == null ? 0 : bean.getCouponAmount())/100.0D),style); //粮票使用 //使用优惠
//				fillCellData(row, i++, String.valueOf(bean.getOrderAmount()/100.0D),style);//实际支付
				fillCellData(row, i++, bean.getExpressName(),style); //"物流方式"
				fillCellData(row, i++, bean.getExpressNo(),style);//物流单号
//				fillCellData(row, i++, bean.getOpName(),style); //活动名称
				fillCellData(row, i++, String.valueOf(bean.getPurchasePrice()/100.0D),style);//商品采购单价
				fillCellData(row, i++, String.valueOf(bean.getPurchasePrice()*bean.getQty()/100.0D),style);//商品采购总额
				fillCellData(row, i++, String.valueOf(bean.getDeliverySettleFee()/100.0D),style);//deliverySettleFee  供应商运费
				fillCellData(row, i++, String.valueOf((bean.getAmountPurchage() + bean.getDeliverySettleFee())/100.0D),style);//运单采购价
//				fillCellData(row, i++, String.valueOf(bean.getAmoutPurchageOrder()/100.0D),style);//订单采购价 //FIXME
				
//				fillCellData(row, i++, String.valueOf((bean.getOrderAmount() + (bean.getCouponAmount() == null ? 0 : bean.getCouponAmount()))/100.0D),style);
//				fillCellData(row, i++, String.valueOf(bean.getOrderAmount()/100.0D),style);
				
				
				fillCellData(row, i++, bean.getDelivPeopleName(),style); //"收货人姓名"
				fillCellData(row, i++, bean.getDelivPhone(),style); //收货人联系方式
				String address = (bean.getDelivAddressArea() == null ? "" : bean.getDelivAddressArea()) +
						(bean.getDelivAddressDetail() == null ? "" : bean.getDelivAddressDetail());
				fillCellData(row, i++, address, style);//收货地址
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
	
	private Map<String, Object> getRequestParams(HttpServletRequest request){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderNo", ParamUtils.getString(request, "orderNo"));
		params.put("huaId", ParamUtils.getString(request, "huaId"));
		params.put("realName", ParamUtils.getString(request, "realName"));
		
		String payTimeStart = request.getParameter("payTimeStart");
		if(payTimeStart!=null && !payTimeStart.trim().equals("")){
			params.put("payTimeStart", payTimeStart.trim());
		}
		String payTimeEnd = request.getParameter("payTimeEnd");
		if(payTimeEnd!=null && !payTimeEnd.trim().equals("")){
			params.put("payTimeEnd", payTimeEnd.trim());
		}
		
		params.put("mobile", ParamUtils.getString(request, "mobile"));
		params.put("expressIds", ParamUtils.getInteger(request, "expressIds", null));
		params.put("productName", ParamUtils.getString(request, "productName"));
		params.put("typeName", ParamUtils.getString(request, "typeName"));
		params.put("supplyName", ParamUtils.getString(request, "supplyName"));
		params.put("payMethod", ParamUtils.getString(request, "payMethod"));
		params.put("orderStatus", ParamUtils.getString(request, "orderStatus"));
		params.put("payStatus", ParamUtils.getString(request, "payStatus"));
		params.put("startTime", ParamUtils.getDate(request, "startTime", null, "yyyy-MM-dd HH:mm"));//(request, "startTime")); //params.put("startTime", ParamUtils.getDate(request, "startTime", null, 'yyyy-MM-dd HH'));
		params.put("endTime", ParamUtils.getDate(request, "endTime", null, "yyyy-MM-dd HH:mm"));
		//params.put("endTime", ParamUtils.getString(request, "endTime"));
		params.put("tSupplyMerchantFId", ParamUtils.getLong(request, "tSupplyMerchantFId", null));
		params.put("delieveOrderId", ParamUtils.getLong(request, "delieveOrderId", null));
		params.put("refundStatus", ParamUtils.getInteger(request, "refundStatus", null));
		params.put("expressNo", ParamUtils.getString(request, "expressNo", null));
		params.put("tkStatus", ParamUtils.getInteger(request, "tkStatus", null));
		params.put("supplyList", UserContext.getSupplyMerchantList());
		
		return params;
	}
	
	/**
	 * 添加单条运单备注
	 * @param orderId
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveOrderComment.html")
	@ResponseBody
	public Object saveOrderComment(Long orderId, String comment){
		JsonResponse message = new JsonResponse();
		try {
			EbuyDeliveryOrderComment ebuyDeliveryOrderComment = new EbuyDeliveryOrderComment();
			
			IUuidManager uuidManager = (IUuidManager) CnfantasiaCommUtil.getBeanManager("uuidManager");
			BigInteger id=uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_delivery_order_comment);
			ebuyDeliveryOrderComment.setId(id);
			ebuyDeliveryOrderComment.settEbuyOrderFId(BigInteger.valueOf(orderId));
			ebuyDeliveryOrderComment.setComment(comment);
			String addTime = DateUtils.getCurrentDate();
			
			ebuyDeliveryOrderComment.setAddTime(addTime);
			int excuteResult = ebuyorderService.saveEbuyDeliveryOrderComment(ebuyDeliveryOrderComment);
			if(excuteResult>0){
				message.setMessage("订单备注保存成功");
				message.setStatus(CommConstants.ResponseStatus.SUCCESS);
				message.setDataValue(addTime);
			} else {
				message.setMessage("订单备注保存失败");
				message.setStatus(CommConstants.ResponseStatus.SYSTEM_ERR);
			}
		} catch (RuntimeException re) {
			logger.error(re);
			message.setMessage(re.getMessage());
			message.setStatus(CommConstants.ResponseStatus.SYSTEM_ERR);
		} catch (Exception e) {
			logger.error(e);
			message.setMessage("订单备注保存失败");
			message.setStatus(CommConstants.ResponseStatus.SYSTEM_ERR);
		}
		return message;
	}
	
}
