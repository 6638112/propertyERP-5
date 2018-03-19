package com.cnfantasia.server.ms.ebuy.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.common.exception.FocRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.IEbuySupplyMerchantBaseService;
import com.cnfantasia.server.ms.ebuy.entity.OrderDetailBean;
import com.cnfantasia.server.ms.ebuy.entity.ReportBean;
import com.cnfantasia.server.ms.ebuy.service.IEbuyorderService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.ControllerUtils;

/**
 * 
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月7日下午4:57:44
 */
//@RequestMapping("/ebuyorder")
@Deprecated
public class EbuyorderController extends BaseController {
	
	IEbuyorderService ebuyorderService;
	
	IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService;

	public void setEbuyorderService(IEbuyorderService ebuyorderService) {
		this.ebuyorderService = ebuyorderService;
	}
	
	public void setEbuySupplyMerchantBaseService(IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService) {
		this.ebuySupplyMerchantBaseService = ebuySupplyMerchantBaseService;
	}

	private Map<String, Object> retrieveParam(Map<String,String> request){
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(request.get("orderNo"))) {
			params.put("orderNo", request.get("orderNo"));
		}
		if (!StringUtils.isEmpty(request.get("huaId"))) {
			params.put("huaId", request.get("huaId"));
		}
		if (!StringUtils.isEmpty(request.get("realName"))) {
			params.put("realName", request.get("realName"));
		}
		if (!StringUtils.isEmpty(request.get("mobile"))) {
			params.put("mobile", request.get("mobile"));
		}
		if (!StringUtils.isEmpty(request.get("productName"))) {
			params.put("productName", request.get("productName"));
		}
		if (!StringUtils.isEmpty(request.get("typeName"))) {
			params.put("typeName", request.get("typeName"));
		}
		if (!StringUtils.isEmpty(request.get("supplyName"))) {
			params.put("supplyName", request.get("supplyName"));
		}
		if (!StringUtils.isEmpty(request.get("payMethod"))) {
			params.put("payMethod", request.get("payMethod"));
		}
		if (!StringUtils.isEmpty(request.get("orderStatus"))) {
			params.put("orderStatus", request.get("orderStatus"));
		}
		if (!StringUtils.isEmpty(request.get("payStatus"))) {
			params.put("payStatus", request.get("payStatus"));
		}
		if (!StringUtils.isEmpty(request.get("startTime"))) {
			params.put("startTime", request.get("startTime"));
		}
		if (!StringUtils.isEmpty(request.get("endTime"))) {
			params.put("endTime", request.get("endTime"));
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
	public JsonResponse orderlist(Map<String,String> request){
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集、整理参数;
		Map<String, Object> params = retrieveParam(request);
		PageModel pageModel = null;
		Integer page = null;
		Integer pageNum = null;
		{
			try {
				page = Integer.parseInt((String)request.get(PageModel.PageKey.PAGE));
				pageNum = Integer.parseInt((String)request.get(PageModel.PageKey.PAGE_NUM));
			} catch (Exception e) {
				throw new FocRuntimeException("request.page.parse.error");
			}
			pageModel = new PageModel((page-1)*pageNum, pageNum);
		}
		// 2.与数据库交互
		List<OrderDetailBean> beans = null;
		if(pageNum==-1){//-1 表示不分页
			beans = ebuyorderService.queryOrderDetailAll(params);
			return ControllerUtils.addPageInfo(jsonResponse, beans);
		}else{
			beans = ebuyorderService.queryOrderDetailByPage(pageModel, params);
			return ControllerUtils.addPageInfo(jsonResponse, beans, pageModel.isLast, pageModel.count);
		}
		// 3.返回
	}
	
	
	/**
	 * syl-add--2014-6-12 11:50:02
	 * 录入物流信息，并更改订单状态为已发货
	 * @param request
	 * @return
	 */
//	@RequestMapping("/setLogisticsInfo.json")
//	@ResponseBody
	public JsonResponse setLogisticsInfo(Map<String,String> request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger orderId = new BigInteger((String)request.get("id"));
		String logisticsName = (String)request.get("logisticsName");
		String logisticseCode = (String)request.get("logisticseCode");
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
	public JsonResponse orderItems(Map<String,String> request){
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
	public JsonResponse findSupplybyid(Map<String, String> params){
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集、整理参数;
		BigInteger merchantId = null;
		String merchantIdStr = (String)params.get("merchantId");
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
