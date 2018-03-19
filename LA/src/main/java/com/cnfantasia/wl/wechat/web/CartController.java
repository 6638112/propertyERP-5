package com.cnfantasia.wl.wechat.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.ebuy.entity.ProductIdQtyEntity;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.livingFeePayRecord.entity.LivingFeePayRecord;
import com.cnfantasia.server.domainbase.livingFeePayRecord.service.LivingFeePayRecordBaseService;
import com.cnfantasia.server.domainbase.livingFeeSp.entity.LivingFeeSp;
import com.cnfantasia.server.domainbase.livingFeeSp.service.LivingFeeSpBaseService;
import com.cnfantasia.server.ms.ebuy.dto.FightgroupProductEntity;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.utils.MapConverter;
import com.cnfantasia.wl.wechat.model.Product4PrepareOrder;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 购物车
 *
 * @author wenfq 2015-01-19
 *
 */
@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {

	@Resource
	private IHttpClient simpleHttpClient;

	@Resource
	LivingFeePayRecordBaseService livingFeePayRecordBaseService;
	@Resource
	private LivingFeeSpBaseService livingFeeSpBaseService;

	/**
	 * 购物车查看
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryBuyCar.do")
	public ModelAndView qryBuyCar(HttpServletRequest request) {
		//if (LoginHelper.getSessionKey(request) == null)//未登录时，要先登录
		//LoginHelper.login(request, simpleHttpClient);
		//LoginHelper.login4WeChat(request, simpleHttpClient);

		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/qryBuyCar.json", null, LoginHelper.prepareReqHeader(request));
		JsonResponse coupons = simpleHttpClient.submitSimple("/coupon/getCouponCanSend.json", null, LoginHelper.prepareReqHeader(request));
		request.setAttribute("jsonResponse", jsonResponse);
		request.setAttribute("coupons", coupons);
		ModelAndView view = new ModelAndView();
		view.setViewName("/ebuy/shoppingCart");
		return view;
	}

	/**
	 * 加入购物车
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/add2BuyCar.do")
	@ResponseBody
	public JsonResponse add2BuyCar(HttpServletRequest request) {
		//		if (LoginHelper.getSessionKey(request) == null)//未登录时，要先登录
		//			LoginHelper.login(request, simpleHttpClient);

		// 创建参数队列    
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", request.getParameter("ptId"));
		params.put("productSpecId", request.getParameter("specId"));//syl-add-商品规格Id
		if (request.getParameter("productQty") == null) {
			params.put("productQty", 1);
		} else {
			params.put("productQty", request.getParameter("productQty"));
		}

		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/add2BuyCar.json", params, LoginHelper.prepareReqHeader(request));

		return jsonResponse;
	}

	/**
	 * 移除购物车(多个)
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/remove2BuyCarBatch.do")
	public String remove2BuyCarBatch(HttpServletRequest request) {
		//		if (LoginHelper.getSessionKey(request) == null)//未登录时，要先登录
		//			LoginHelper.login(request, simpleHttpClient);

		// 创建参数队列    
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productIdList", JSON.toJSON(request.getParameterValues("productCheckBox")));

		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/remove2BuyCarBatch.json", params, LoginHelper.prepareReqHeader(request));

		int productCount = JSON.parseObject(jsonResponse.getDataValue().toString()).getInteger("productCount");

		if (productCount > 0) {
			return "redirect:/cart/edit.do";
		} else {//购物车没有商品了就去商城首页
			return "redirect:/product/index.do";
		}
	}

	/**
	 * 移除购物车(全部)
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/remove2BuyCarAll.do")
	@ResponseBody
	public JsonResponse remove2BuyCarAll(HttpServletRequest request) {
		//		if (LoginHelper.getSessionKey(request) == null)//未登录时，要先登录
		//			LoginHelper.login(request, simpleHttpClient);

		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/remove2BuyCarAll.json", null, LoginHelper.prepareReqHeader(request));

		return jsonResponse;
	}

	/**
	 * 查询购物车商品总数
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryBuyCarProductCount.do")
	@ResponseBody
	public JsonResponse qryBuyCarProductCount(HttpServletRequest request) {
		//		if (LoginHelper.getSessionKey(request) == null)//未登录时，要先登录
		//			LoginHelper.login(request, simpleHttpClient);

		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/qryBuyCarProductCount.json", null, LoginHelper.prepareReqHeader(request));

		return jsonResponse;
	}

	/**
	 * 确认订单（准备付款, 付款前才生成订单）
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkProdctInfo.do")
	public ModelAndView checkProdctInfo(HttpServletRequest request, HttpServletResponse response) {

		// 创建参数队列    
		List<Product4PrepareOrder> productList = new ArrayList<Product4PrepareOrder>();
		String[] prdts = request.getParameterValues("ptId");
		if (prdts == null) {//可能是从修改地址后返回过来的
			prdts = (String[]) request.getSession().getAttribute("prdts");
		} else {
			request.getSession().setAttribute("prdts", prdts);
		}

		for (int i = 0; i < prdts.length; i++) {
			String[] idQtyPair = prdts[i].split(":");
			Product4PrepareOrder p = new Product4PrepareOrder();
			p.setProductId(idQtyPair[0]);//商品ID
			p.setProductQty(Integer.parseInt(idQtyPair[1]));//商品数量
			p.setProductSpecId(idQtyPair.length == 3 ? (idQtyPair[2]) : null);//商品规格
			productList.add(p);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productList", JSON.toJSON(productList));
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/checkProdctInfo.json", params, LoginHelper.prepareReqHeader(request));
		request.setAttribute("jsonResponse", jsonResponse);

		JsonResponse addresResponse = simpleHttpClient.submitSimple("/ebuy/qryDeliveryAddressDetailDefault.json", null, LoginHelper.prepareReqHeader(request));
		request.setAttribute("addresResponse", addresResponse);

		JsonResponse invoiceResponse = simpleHttpClient.submitSimple("/ebuy/qryInvoice.json", null, LoginHelper.prepareReqHeader(request));
		request.setAttribute("invoiceResponse", invoiceResponse);

		ModelAndView view = new ModelAndView();
		view.setViewName("/ebuy/checkPayList");
		return view;
	}

	/**
	 * 查询停车费详情
	 * @return
	 */
	@RequestMapping("/queryCarFeeDetail.do")
	public ModelAndView queryCarFeeDetail(String carId, String groupBuilding, String carNum, String validDate, String cardname, HttpServletRequest request){
		Map<String, Object> carFeeMap = new HashMap<String, Object>();
		carFeeMap.put("carId", carId);
		JsonResponse carFeeResponse = simpleHttpClient.submitSimple("/access/queryCarInfoByCardId.json", carFeeMap, LoginHelper.prepareReqHeader(request));

		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> map = (Map<String, Object>)carFeeResponse.getDataValue();
		Object needBillIsOpen = map.get("needBillIsOpen");
		modelAndView.addObject("groupBuilding", groupBuilding);
		modelAndView.addObject("carNum", carNum);
		modelAndView.addObject("validDate", validDate);
		modelAndView.addObject("carId", carId);
		modelAndView.addObject("fee", map.get("fee"));
		modelAndView.addObject("cardname", cardname);
		modelAndView.addObject("needBillIsOpen", (needBillIsOpen != null) && needBillIsOpen.toString().equals("1"));
		modelAndView.addObject("carFeeTypeList", map.get("carFeeTypeList"));
		modelAndView.addObject("isCanPay", !DataUtil.isEmpty(map.get("carFeeTypeList")) ? 1 : 0);
		modelAndView.setViewName("/payCarFee/parkingFee");

		return modelAndView;
	}

	/**
	 * 获取临时车缴费费用
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/getParkingFee.html")
	public ModelAndView getParkingFee(String carId, String plate, String fee, String inDate, String gbName, 
			String parkingTime, String enterDate, String couponAmount, int carTmpIsOpen, String prepaymentamount) throws UnsupportedEncodingException{
		Map<String, Object> feeInfo = new HashMap<String, Object>();
		feeInfo.put("carId", carId);
		feeInfo.put("plate", URLDecoder.decode(plate, "UTF-8"));
		feeInfo.put("fee", URLDecoder.decode(fee, "UTF-8"));
		feeInfo.put("inDate", URLDecoder.decode(inDate, "UTF-8"));
		feeInfo.put("gbName", URLDecoder.decode(gbName, "UTF-8"));
		feeInfo.put("parkingTime", URLDecoder.decode(parkingTime, "UTF-8"));
		feeInfo.put("enterDate", URLDecoder.decode(enterDate, "UTF-8"));
		feeInfo.put("prepaymentamount", URLDecoder.decode(prepaymentamount, "UTF-8"));
		if(StringUtils.isNotBlank(couponAmount)){
			feeInfo.put("couponAmount", URLDecoder.decode(couponAmount, "UTF-8"));
			feeInfo.put("carTmpIsOpen", carTmpIsOpen);
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/temporaryParkingFee/payParkingFee");
		modelAndView.addObject("feeInfo", feeInfo);
		return modelAndView;
	}

	/**
	 * 准备支付工单(维修单用)
	 * @param request
	 * @return
	 */
	@RequestMapping("/prepareForPay.do")
	public ModelAndView prepareForPay(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse = simpleHttpClient.submitSimple("/dredge/prepareForPay.json", CommonController.getParameterMap(request), LoginHelper.prepareReqHeader(request));
		request.setAttribute("dataValue", jsonResponse.getDataValue());
		//3.结果处理
		return new ModelAndView("/dredge/myAppointmentPay");
	}

	/**
	 * 购物车编辑
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit.do")
	public ModelAndView edit(HttpServletRequest request) {
		//		if (LoginHelper.getSessionKey(request) == null)//未登录时，要先登录
		//			LoginHelper.login(request, simpleHttpClient);

		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/qryBuyCar.json", null, LoginHelper.prepareReqHeader(request));
		request.setAttribute("jsonResponse", jsonResponse);

		ModelAndView view = new ModelAndView();
		view.setViewName("/ebuy/shoppingCart_edit");
		return view;
	}

	/**
	 * 查询订单详情
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryOrderDetail.do")
	public ModelAndView qryOrderDetail(HttpServletRequest request) {

		// 创建参数队列    
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", request.getParameter("orderId"));

		//查询订单
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/qryOrderDetail.json", params, LoginHelper.prepareReqHeader(request));
		request.setAttribute("jsonResponse", jsonResponse);
		return new ModelAndView("/ebuy/checkPayListFromMyOrder");
	}
	
	/**
	 * 限时抢购
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/limitBuyDetail.do", method = RequestMethod.GET)
	public ModelAndView limitBuyDetail(HttpServletRequest request) {
		
		JsonResponse jsonResponse = simpleHttpClient.doGet("/limitBuy/limitBuyDetail.json", getParameterMap(request), LoginHelper.prepareReqHeader(request));
		request.setAttribute("jsonResponse", jsonResponse);
		
		JsonResponse addresResponse = simpleHttpClient.submitSimple("/ebuy/qryDeliveryAddressDetailDefault.json", null, LoginHelper.prepareReqHeader(request));
		request.setAttribute("addresResponse", addresResponse);
		
		request.getSession().setAttribute("fromWhereParam", request.getQueryString());
		
		return new ModelAndView("/ebuy/checkPayLimitBuy");
	}

	/**
	 * 立即抢购
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/buyRightNow.do")
	public ModelAndView buyRightNow(HttpServletRequest request) {

		// 创建参数队列    
		Map<String, Object> params = new HashMap<String, Object>();
		String ptId = request.getParameter("ptId");
		params.put("productId", ptId);
		params.put("productSpecId", request.getParameter("specId"));//syl-add-商品规格Id

		long productQty = ParamUtils.getLong(request, "productQty", 1L);
		params.put("productQty", productQty);

		if (ptId != null) {
			request.getSession().setAttribute("prdts", new String[] {ptId +":1"});//抢购是买一个商品
		}

		if(request.getQueryString().contains("jfqsource=buyRightNow")){
			request.getSession().setAttribute("fromWhere", "buyRightNow");
			request.getSession().setAttribute("fromWhereParam", request.getQueryString());
		}

		JsonResponse jsonResponse;
//		if(request.getSession().getAttribute("buyRightNow") == null) {
//			jsonResponse = simpleHttpClient.submitSimple("/ebuy/add2BuyCar.json", params, LoginHelper.prepareReqHeader(request));
//			request.getSession().setAttribute("buyRightNow", true);
//		}

		jsonResponse = simpleHttpClient.submitSimple("/ebuy/remove2BuyCarAll.json", params, LoginHelper.prepareReqHeader(request));
		jsonResponse = simpleHttpClient.submitSimple("/ebuy/add2BuyCar.json", params, LoginHelper.prepareReqHeader(request));
		ProductIdQtyEntity entity = new ProductIdQtyEntity();
		entity.setProductId(new BigInteger(ptId));
		entity.setProductQty(productQty);
		List<ProductIdQtyEntity> productList = new ArrayList<ProductIdQtyEntity>(1);
		productList.add(entity);
		params.clear();
		params.put("productList", JSON.toJSONString(productList));
		//检查订单
		jsonResponse = simpleHttpClient.submitSimple("/ebuy/checkProdctInfo.json", params, LoginHelper.prepareReqHeader(request));
		request.setAttribute("jsonResponse", jsonResponse);

		JsonResponse addresResponse = simpleHttpClient.submitSimple("/ebuy/qryDeliveryAddressDetailDefault.json", null, LoginHelper.prepareReqHeader(request));
		request.setAttribute("addresResponse", addresResponse);

		JsonResponse invoiceResponse = simpleHttpClient.submitSimple("/ebuy/qryInvoice.json", null, LoginHelper.prepareReqHeader(request));
		request.setAttribute("invoiceResponse", invoiceResponse);

		if("limitbuy".equals(ParamUtils.getString(request,"jfqsource"))){//如要是限时促销进来的话，购物车还要清空
			simpleHttpClient.submitSimple("/ebuy/remove2BuyCarAll.json", params, LoginHelper.prepareReqHeader(request));
		}
		
		request.getSession().setAttribute("fromWhereParam", request.getQueryString());

		return new ModelAndView("/ebuy/checkPayList");
	}

	//拼购确认付款页面
	@RequestMapping("/getLaFightOrderMsg.do")
	public String getLaFightOrderMsg(HttpServletRequest request,ModelMap model){
		Map<String, Object> paramap = new HashMap<String, Object>();
		BigInteger productId= ParamUtils.getBigInteger(request, "productId", null);
		Integer productNum = ParamUtils.getInteger(request, "productNum", 1);
		productNum = productNum < 1 ? 1 : productNum;
		paramap.put("productId", productId);
		paramap.put("productNum", productNum);
		JsonResponse ProductDetail = simpleHttpClient.submitSimple("/groupPurchase/getLaFightOrderMsg.json", paramap, LoginHelper.prepareReqHeader(request));
		String userName = ((JSONObject)ProductDetail.getDataValue()).getString("userName");
		String userPhone = ((JSONObject)ProductDetail.getDataValue()).getString("userPhone");
		String fightProduct = ((JSONObject)ProductDetail.getDataValue()).getString("fightProduct");
		String picserverUrl = ((JSONObject)ProductDetail.getDataValue()).getString("picserverUrl");
		boolean containsCoupon = ((JSONObject) ProductDetail.getDataValue()).getBoolean("ext_isContainCoupon");
		String couponInfo = ((JSONObject) ProductDetail.getDataValue()).getString("ext_couponCombiInfo");
		List<Map<String, Object>> coupons = JSON.parseObject(couponInfo, List.class);
		FightgroupProductEntity fightProductJson = JSON.parseObject(fightProduct, FightgroupProductEntity.class);
		model.addAttribute("userName", userName);
		model.addAttribute("userPhone", userPhone);
		model.addAttribute("fightProduct", fightProductJson);
		model.addAttribute("picserverUrl", picserverUrl);
		model.addAttribute("containsCoupon", containsCoupon);
		model.addAttribute("coupons", coupons);
		model.addAttribute("productNum", productNum);
		return "/ebuyFight/groupBuyingcheckPayList";
	}

	/**
	 * 进入缴费页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/livingPayType.do", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView livingPayType(HttpServletRequest request) {
		int feeType = ParamUtils.getInt(request, "feeType", 0);
		if(feeType <= 0){
			throw new ValidateRuntimeException("feeType不能为空");
		}

		BigInteger userId = ParamUtils.getBigInteger(request, "userId", null);
		if(userId == null) {
			String userIdString = LoginHelper.getRegist3rdResponseFromSession(request).getUserId();
			if(StringUtils.isNotEmpty(userIdString)){
				userId = new BigInteger(userIdString);
			}
		}
		LivingFeePayRecord livingFeePayRecordQry = new LivingFeePayRecord();
		livingFeePayRecordQry.settUserFId(userId);
		livingFeePayRecordQry.settLivingFeeItemFId(BigInteger.valueOf(feeType));
		List<LivingFeePayRecord> livingFeePayRecordList = livingFeePayRecordBaseService.getLivingFeePayRecordByCondition(MapConverter.toMap(livingFeePayRecordQry));
		if(livingFeePayRecordList != null && !livingFeePayRecordList.isEmpty())
			request.setAttribute("historyRecord", livingFeePayRecordList.get(0));

		//宽带业务
		if(BigInteger.valueOf(6).equals(BigInteger.valueOf(feeType))) {
			List<LivingFeeSp> livingFeeSpList = livingFeeSpBaseService.getLivingFeeSpByCondition(null);
			request.setAttribute("livingFeeSpList", livingFeeSpList);
		}

		return new ModelAndView("/feePay/feeType");
	}
}
