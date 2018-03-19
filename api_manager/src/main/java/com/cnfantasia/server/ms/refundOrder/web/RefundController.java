package com.cnfantasia.server.ms.refundOrder.web;

import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.service.IEbuyDeliveryOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrder.service.IEbuyOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyRefundOrder.entity.EbuyRefundOrder;
import com.cnfantasia.server.domainbase.ebuyRefundOrder.service.IEbuyRefundOrderBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.payRedEnvelope.entity.PayRedEnvelope;
import com.cnfantasia.server.domainbase.payRedEnvelope.service.IPayRedEnvelopeBaseService;
import com.cnfantasia.server.domainbase.refundRecord.entity.RefundRecord;
import com.cnfantasia.server.domainbase.refundRecord.service.IRefundRecordBaseService;
import com.cnfantasia.server.ms.ebuy.entity.OrderDetailsBean;
import com.cnfantasia.server.ms.ebuy.entity.OrderProductBean;
import com.cnfantasia.server.ms.ebuy.service.IEbuyorderService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.refundOrder.entity.RefudOrderEntity;
import com.cnfantasia.server.ms.refundOrder.entity.RefudOrderProductEntity;
import com.cnfantasia.server.ms.refundOrder.service.IRefundOrderService;
import com.cnfantasia.server.ms.refundOrder.utils.KuaiDi;
import com.cnfantasia.server.ms.refundOrder.utils.KuaiDiMsg;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

/**退款管理
 * 
 * @author ligs
 *
 */
@Controller
@RequestMapping("/refundOrder")
public class RefundController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	@Resource
	private IRefundOrderService refundOrderService;
	
	@Resource
	private IEbuyorderService ebuyorderService;
	
	@Resource
	private IEbuyRefundOrderBaseService refundService;
	
	@Resource
	private IEbuyDeliveryOrderBaseService deliveryOrderService;
	
	@Resource
	private IEbuyOrderBaseService ebuyOrderService;
	
	@Resource
	private IPayRedEnvelopeBaseService payRedBaseService;
	
	@Resource
	private IUuidManager uuidManager;
	
	@Resource
	private IRefundRecordBaseService refundRecordService;
	
	@RequestMapping("/refundOrderList.html")
	public String refundList(HttpServletRequest request,ModelMap model){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		handleListOrSearch(request,paramMap);
		return "/ebuy/refundOrder";
	}
	
	@RequestMapping("/refundDetail.html")
	public String refundDetail(HttpServletRequest request,ModelMap model,String refundOrderId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String resUrl = null;
		if(StringUtils.isNotEmpty(refundOrderId)){
			RefudOrderEntity refundOrder = refundOrderService.getRefunddetail(CnfantasiaCommUtil.convert2BigInteger(refundOrderId));
			if(refundOrder.getRefundStatus()==2 || refundOrder.getRefundStatus()==3){
				resUrl = "/ebuy/refundDetail";
			}else{
				resUrl = "/ebuy/refundAudit";
			}
			List<RefudOrderProductEntity> productList = refundOrder.getProductList();
			RefudOrderProductEntity refundProduct = new RefudOrderProductEntity();
			long refundTotal = 0L;
			for(RefudOrderProductEntity refundpro:productList){
				refundTotal +=(refundpro.getProductQty()*refundpro.getProductPrice());
			}
			productList.add(refundProduct);
			model.addAttribute("refundTotal", refundTotal);
			model.addAttribute("refundOrder", refundOrder);
			model.addAttribute("refundproductList", productList);
			//退款图片
			if(StringUtils.isNotEmpty(refundOrder.getRefundPhotoes())){
				List<String> picList = Arrays.asList(refundOrder.getRefundPhotoes().split(";"));
				model.addAttribute("picList", picList);
			}
			//运单详情
			paramMap.put("delieveOrderId", refundOrder.gettEbuyDeliveryOrderFId());
			OrderDetailsBean orderDetail = ebuyorderService.selectOrderDetail(paramMap);
			//价格总计
			long total = 0L;
			for(OrderProductBean product : orderDetail.getProductList()) {
				total += product.getTotalPrice();
			}
			OrderProductBean lastProduct = new OrderProductBean();
			lastProduct.setForDisplay("总计");
			lastProduct.setTotalPrice(total);
			orderDetail.getProductList().add(lastProduct);
			model.addAttribute("orderDetail", orderDetail);
			model.addAttribute("refundOrder", refundOrder);
			model.addAttribute("total", total);

			List<EbuySupplyMerchant> supplyList = UserContext.getSupplyMerchantList();
			if(UserContext.getCurrUser().getIsadmin() != 1 && supplyList != null && supplyList.size() > 0) {
				request.setAttribute("isSupply", true);
			}

			//物流跟踪
			if(orderDetail.getDeliveryStatus() ==2 || orderDetail.getDeliveryStatus() ==3){
				String content = KuaiDi.searchkuaiDiInfo(KuaiDiMsg.KuaiDi100,orderDetail.getKuaidiCom(), orderDetail.getExpressNo());
				model.addAttribute("content", content);
			}
		}

		return resUrl;
	}
	
	@RequestMapping("/saveRefund.html")
	@ResponseBody
	public JsonResponse saveRefund(HttpServletRequest request,ModelMap model){
		JsonResponse message = new JsonResponse();
		String audit = ParamUtils.getString(request, "audit",null);
		String auditReason = ParamUtils.getString(request, "auditReason", null);
		BigInteger refundId = ParamUtils.getBigInteger(request, "refundId", null);
		String nowTime = CnfantasiaCommUtil.getCurrentTime();
		try {
			if(refundId!=null){
				EbuyRefundOrder  ebuyRefundOrder = refundService.getEbuyRefundOrderBySeqId(refundId);
				EbuyDeliveryOrder ebuyDeliveryOrder = deliveryOrderService.getEbuyDeliveryOrderBySeqId(ebuyRefundOrder.gettEbuyDeliveryOrderFId());
				if(audit.equals("pass")){
					if(ebuyRefundOrder.getPayStatus()!=null){
						ebuyRefundOrder.setRefundStatus(2);
						ebuyRefundOrder.setSys0UpdTime(CnfantasiaCommUtil.getCurrentTime());

						if(PriceUtil.multiply100(ebuyRefundOrder.getRefundRedEnvelope()) > 0) {
							//查询付款时的房间号
							EbuyOrder ebuyOrder = ebuyOrderService.getEbuyOrderBySeqId(ebuyDeliveryOrder.gettEbuyOrderFId());
							BigInteger roomId = ebuyOrder.getCurrRoomId();
							BigInteger userId = ebuyOrder.getBuyerId();
							//往退款粮票表添加数据
							BigInteger refundRecordId = uuidManager.getNextUuidBigInteger(SEQConstants.t_refund_record);
							RefundRecord refundRecord = new RefundRecord();
							refundRecord.setId(refundRecordId);
							refundRecord.setRefundTime(nowTime);
							refundRecord.setSys0AddTime(nowTime);
							refundRecord.setStatus(0);
							refundRecord.settRefundOrderFId(refundId);
							refundRecord.settUserHasTRoomFId(roomId);
							refundRecord.settUserFId(userId);
							refundRecord.setSavedMoney(PriceUtil.multiply100(ebuyRefundOrder.getRefundRedEnvelope()));
							refundRecord.setSys0DelState(0);
							refundRecordService.insertRefundRecord(refundRecord);
							//生成粮票
							PayRedEnvelope payred = new PayRedEnvelope();
							payred.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_red_envelope));
							payred.setAmountAvaible(PriceUtil.multiply100(ebuyRefundOrder.getRefundRedEnvelope()));
							payred.setAmountTotal(PriceUtil.multiply100(ebuyRefundOrder.getRefundRedEnvelope()));
							payred.setRoomId(roomId);
							payred.setStatus(1);
							payred.setConverterId(userId);
							payred.setUserId(userId);
							payred.setSys0AddTime(nowTime);
							payred.setFromType(4);
							payred.setFromTime(nowTime);
							payred.setFromId(refundRecordId);
							payRedBaseService.insertPayRedEnvelope(payred);
						}

						//运单修改退款成功，确认收货
						ebuyDeliveryOrder.setRefundStatus(2);
						ebuyDeliveryOrder.setReceiveTime(nowTime);
						ebuyDeliveryOrder.setStatus(3);
						message.setStatus("0000");
					}else{
						message.setStatus("0001");
						message.setMessage("退款账户为空，无法操作！！！");
					}
				}else if(audit.equals("nopass")){
					ebuyRefundOrder.setId(refundId);
					ebuyRefundOrder.setRefundReason(auditReason);
					ebuyRefundOrder.setRefundStatus(3);
	
					ebuyDeliveryOrder.setRefundStatus(3);
					message.setStatus("0000");
				}
				refundService.updateEbuyRefundOrder(ebuyRefundOrder);
				deliveryOrderService.updateEbuyDeliveryOrder(ebuyDeliveryOrder);
			}else{
				message.setStatus("0001");
				message.setMessage("无退款信息");
			}
		} catch (Exception e) {
			message.setStatus("0002");
			e.printStackTrace();
		}
		return message;
	}
	
	@RequestMapping("/search.html")
	public String search(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		BigInteger buyerId = ParamUtils.getBigInteger(request, "buyerId", null);
		String orderNo = ParamUtils.getString(request, "orderNo",null);
		Integer refundStatus = ParamUtils.getInteger(request, "refundStatus", null);
		String createTime_START = request.getParameter("createTime_START");
		String createTime_END = request.getParameter("createTime_END");
		String sys0UpdTime_START = request.getParameter("sys0UpdTime_START");
		String sys0UpdTime_END = request.getParameter("sys0UpdTime_END");
		Integer refundNo = ParamUtils.getInteger(request, "refundNo", null);
		paramMap.put("buyerId", buyerId);
		paramMap.put("orderNo", orderNo);
		paramMap.put("refundStatus", refundStatus);
		paramMap.put("createTime_START", createTime_START);
		paramMap.put("createTime_END", createTime_END);
		paramMap.put("sys0UpdTime_END", sys0UpdTime_END);
		paramMap.put("sys0UpdTime_START", sys0UpdTime_START);
		paramMap.put("refundNo", refundNo);
		handleListOrSearch(request, paramMap);
		return "/ebuy/refundOrder";
	}
	
	/**
	 * 导出退款明细
	 * @return
	 */
	@RequestMapping("/exportRefund.html")
	public void exportRefund(HttpServletRequest request,HttpServletResponse response){
		OutputStream fOut = null;
		InputStream fin = null;
		try {
			Map<String, Object> requestParams = getRequestParams(request);
			List<RefudOrderEntity> refundExportList = null;
			refundExportList = refundOrderService.selectRefundlist(requestParams);
			// 生成提示信息
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String fileName = format.format(new Date());
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
			fOut = response.getOutputStream();
			HSSFWorkbook workbook;
			workbook = createReportRefund(refundExportList);
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
	
	@SuppressWarnings("deprecation")
	public HSSFWorkbook createReportRefund(List<RefudOrderEntity> refundExportList) {
		String[] titles = {"申请时间","退款时间","订单编号","支付流水号","运单状态","供应商","解放号","退款粮票(元)","退款现金(元)","申请金额(元)", "退款账户", "退款方式", "退款状态"};
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("退款明细");
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
		short width = 6000;
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
		
		if(refundExportList != null) {
			int i;
			for (int j = 0; j < refundExportList.size(); j++) {
				row = sheet.createRow((j+1));
				// 设置excel每列宽度
				sheet.setColumnWidth(j, width);
				RefudOrderEntity order = refundExportList.get(j);
				i = 0;
				fillCellData(row, i++,order.getCreateTime(),style);
				fillCellData(row, i++,order.getSys0UpdTime(),style);
				fillCellData(row, i++,order.getOrderNo(),style);
				fillCellData(row, i++,order.getPayFlowNo(),style);
				if(order.getDeliveryOrder().getStatus()!=null){
					switch(order.getDeliveryOrder().getStatus()){
					case 0:fillCellData(row, i++,"未启动",style);
					break;
					case 1:fillCellData(row, i++,"待发货",style);
					break;
					case 2:fillCellData(row, i++,"待收货",style);
					break;
					case 3:fillCellData(row, i++,"确认收货",style);
					break;
					}
				}else{
					fillCellData(row, i++,"无",style);
				}
				fillCellData(row, i++,order.getEbuySupplyMerchant().getName(),style);
				fillCellData(row, i++,order.getBuyerId().toString(),style);
				fillCellData(row, i++,order.getRefundRedEnvelope().toString(),style);
				fillCellData(row, i++,order.getRefundMoney().toString(),style);
				fillCellData(row, i++,order.getApplyFee().toString(),style);
				if(order.getPayStatus()!=null){
					switch(order.getPayStatus()){
						case 1:fillCellData(row, i++,"微信",style);
						break;
						case 2:fillCellData(row, i++,"支付宝",style);
						break;
						case 3:fillCellData(row, i++,"银联",style);
						break;
						case 4:fillCellData(row, i++,"粮票",style);
						break;
						case 9:fillCellData(row, i++,"双乾支付",style);
						break;
						}
				}else{
					fillCellData(row, i++,"无",style);
				}
				if(order.getStatus()!=null){
					switch(order.getStatus()){
					case 1:fillCellData(row, i++,"部分退款",style);
					break;
					case 2:fillCellData(row, i++,"全额退款",style);
					break;
					}
				}else{
					fillCellData(row, i++,"无",style);
				}
				if(order.getRefundStatus()!=null){
					switch(order.getRefundStatus()){
					case 1:fillCellData(row, i++,"退款中",style);
					break;
					case 2:fillCellData(row, i++,"退款成功",style);
					break;
					case 3:fillCellData(row, i++,"退款失败",style);
					break;
					}
				}else{
					fillCellData(row, i++,"无",style);
				}
			}	
		}
		return wb;
	}
	
	@SuppressWarnings("deprecation")
	private void fillCellData(HSSFRow row,int index,String value,HSSFCellStyle style){
		HSSFCell cell = row.createCell(index);
		cell.setCellStyle(style);
		cell.setCellValue(value);
	}
	
	private Map<String, Object> getRequestParams(HttpServletRequest request){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderNo", ParamUtils.getString(request, "orderNo1"));
		params.put("buyerId", ParamUtils.getString(request, "buyerId1"));
		params.put("refundNo", ParamUtils.getString(request, "refundNo1"));
		params.put("refundStatus", ParamUtils.getString(request, "refundStatus1"));
//		params.put("createTime_START", ParamUtils.getDate(request, "createTime_START1", null, "yyyy-MM-dd HH:mm"));
//		params.put("createTime_END", ParamUtils.getDate(request, "createTime_END1", null, "yyyy-MM-dd HH:mm"));
//		params.put("sys0UpdTime_START", ParamUtils.getDate(request, "sys0UpdTime_START1", null, "yyyy-MM-dd HH:mm"));
//		params.put("sys0UpdTime_END", ParamUtils.getDate(request, "sys0UpdTime_END1", null, "yyyy-MM-dd HH:mm"));
		params.put("createTime_START", request.getParameter("createTime_START1"));
		params.put("createTime_END", request.getParameter("createTime_END1"));
		params.put("sys0UpdTime_START", request.getParameter("sys0UpdTime_START1"));
		params.put("sys0UpdTime_END", request.getParameter("sys0UpdTime_END1"));
		return params;
	}
	
	private void handleListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		paramMap.put("userRealName", UserContext.getCurrUser().getRealName());
		int resultSize = refundOrderService.selectlistnum(paramMap);
		request.setAttribute("resultSize", resultSize);
		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		PageUtils.addPageInfoToParam(request, paramMap);
		List<RefudOrderEntity> resList = refundOrderService.selectRefundlist(paramMap);
		request.setAttribute("resList", resList);
	}
}
