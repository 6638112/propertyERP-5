package com.cnfantasia.server.api.ebuyorder.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.api.ebuyorder.entity.OrderDetailBean;
import com.cnfantasia.server.api.ebuyorder.entity.ReportBean;
import com.cnfantasia.server.api.ebuyorder.service.IEbuyorderService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.IEbuySupplyMerchantBaseService;

/**
 * 
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月7日下午4:57:44
 */
//@RequestMapping("/ebuyorder")
public class EbuyorderController extends BaseController {
	
	IEbuyorderService ebuyorderService;
	
	IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService;

	public void setEbuyorderService(IEbuyorderService ebuyorderService) {
		this.ebuyorderService = ebuyorderService;
	}
	
	public void setEbuySupplyMerchantBaseService(IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService) {
		this.ebuySupplyMerchantBaseService = ebuySupplyMerchantBaseService;
	}

	private Map<String, Object> retrieveParam(HttpServletRequest request){
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(request.getParameter("orderNo"))) {
			params.put("orderNo", request.getParameter("orderNo"));
		}
		if (!StringUtils.isEmpty(request.getParameter("huaId"))) {
			params.put("huaId", request.getParameter("huaId"));
		}
		if (!StringUtils.isEmpty(request.getParameter("realName"))) {
			params.put("realName", request.getParameter("realName"));
		}
		if (!StringUtils.isEmpty(request.getParameter("mobile"))) {
			params.put("mobile", request.getParameter("mobile"));
		}
		if (!StringUtils.isEmpty(request.getParameter("productName"))) {
			params.put("productName", request.getParameter("productName"));
		}
		if (!StringUtils.isEmpty(request.getParameter("typeName"))) {
			params.put("typeName", request.getParameter("typeName"));
		}
		if (!StringUtils.isEmpty(request.getParameter("supplyName"))) {
			params.put("supplyName", request.getParameter("supplyName"));
		}
		if (!StringUtils.isEmpty(request.getParameter("payMethod"))) {
			params.put("payMethod", request.getParameter("payMethod"));
		}
		if (!StringUtils.isEmpty(request.getParameter("orderStatus"))) {
			params.put("orderStatus", request.getParameter("orderStatus"));
		}
		if (!StringUtils.isEmpty(request.getParameter("payStatus"))) {
			params.put("payStatus", request.getParameter("payStatus"));
		}
		if (!StringUtils.isEmpty(request.getParameter("startTime"))) {
			params.put("startTime", request.getParameter("startTime"));
		}
		if (!StringUtils.isEmpty(request.getParameter("endTime"))) {
			params.put("endTime", request.getParameter("endTime"));
		}
		return params;
	}
	
	
	/**
	 * 查询订单列表
	 * 
	 * @param request
	 * @return
	 */
//	@RequestMapping("/orderlist.json")
//	@ResponseBody
	public JsonResponse orderlist(HttpServletRequest request) throws Exception {
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集、整理参数;
		Map<String, Object> params = retrieveParam(request);
		PageModel pageModel = ControllerUtils.getPageModel(request);
		// 2.与数据库交互
		List<OrderDetailBean> beans = ebuyorderService.queryOrderDetailByPage(pageModel, params);
		// 3.返回
		return ControllerUtils.addPageInfo(jsonResponse, beans, pageModel.isLast, pageModel.count);
	}
	
	
	/**
	 * syl-add--2014-6-12 11:50:02
	 * 录入物流信息，并更改订单状态为已发货
	 * @param request
	 * @return
	 */
//	@RequestMapping("/setLogisticsInfo.json")
//	@ResponseBody
	public JsonResponse setLogisticsInfo(HttpServletRequest request) throws Exception {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger orderId = new BigInteger(request.getParameter("id"));
		String logisticsName = request.getParameter("logisticsName");
		String logisticseCode = request.getParameter("logisticseCode");
		//2.交互
		ebuyorderService.setLogisticsInfo(orderId, logisticsName, logisticseCode);
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 查询订单明细列表
	 * 
	 * @param request
	 * @return
	 */
//	@RequestMapping("/orderitems.json")
//	@ResponseBody
	public JsonResponse orderItems(HttpServletRequest request) throws Exception {
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集、整理参数;
		Map<String, Object> params = retrieveParam(request);
		List<ReportBean> reportBeans = ebuyorderService.selectOrderItemsForReport(params);
		// 3.返回
		return ControllerUtils.addPageInfo(jsonResponse, reportBeans, true, reportBeans.size());
	}
	
	/**
	 * 根据id查询供应商信息
	 * 
	 * @param request
	 * @return
	 */
//	@RequestMapping("/findSupplybyid.json")
//	@ResponseBody
	public JsonResponse findSupplybyid(HttpServletRequest request) throws Exception {
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集、整理参数;
		BigInteger merchantId = null;
		String merchantIdStr = request.getParameter("merchantId");
		if(!StringUtils.isEmpty(merchantIdStr)){
			merchantId = new BigInteger(merchantIdStr);
		}
		if(merchantId!=null){
			EbuySupplyMerchant supplyMerchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(merchantId);
			jsonResponse.setDataValue(supplyMerchant);
		}
		return jsonResponse;
	}
}
