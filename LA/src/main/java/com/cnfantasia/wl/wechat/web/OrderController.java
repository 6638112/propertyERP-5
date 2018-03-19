package com.cnfantasia.wl.wechat.web;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.jfq.wechat.util.TemplateMsgSender;
import com.cnfantasia.pub.util.WeChatConfig;
import com.cnfantasia.server.api.commonBusiness.entity.UserIdType;
import com.cnfantasia.server.business.pub.session.SessionValueManager;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.userRecommendOrder.entity.UserRecommendOrder;
import com.cnfantasia.server.domainbase.userRecommendOrder.service.UserRecommendOrderBaseService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.annotation.CheckLogin;
import com.cnfantasia.server.ms.pub.constant.FocConstants;
import com.cnfantasia.wl.wechat.WeChatConstant;
import com.cnfantasia.wl.wechat.model.Product4PrepareOrder;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;
import com.wxap.util.MD5Util;
import com.wxap.util.Sha1Util;

/**
 * 订单
 * 
 * @author wenfq
 * 
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());

	@Resource
	private IHttpClient simpleHttpClient;

	@Resource
	UserRecommendOrderBaseService userRecommendOrderBaseService;

	@Resource
	IUuidManager uuidManager;

	/**
	 * 微信支付已经调用
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/isWeChatPayed.do")
	@ResponseBody
	public String isWeChatPayed(HttpServletRequest request) {
		return "微信支付已经调用";
	}

	/**
	 * 订单列表
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@RequestMapping("/qryOrderList.do")
	public ModelAndView qryOrderList(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
		SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
		LoginHelper.loginAPI(simpleHttpClient, request, user);
		
//		LoginHelper.login(request, simpleHttpClient);

		// 创建参数队列    
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", 1);
		params.put("pageNum", 100);
		params.put("orderStatus", "[1]"); //待付款
		JsonResponse waitPayResponse = simpleHttpClient.submitSimple("/ebuy/qryOrderList.json", params, LoginHelper.prepareReqHeader(request));
		if (waitPayResponse.getStatus().equals(CommConstants.ResponseStatus.LOGIN_TIME_OUT)
				|| waitPayResponse.getStatus().equals(CommConstants.ResponseStatus.BUSINESS_FAILED)) {
			LoginHelper.login4WeChat(request, simpleHttpClient);
		}
		request.setAttribute("waitPayResponse", waitPayResponse);

		/*params.clear();
		params.put("page", 1);
		params.put("pageNum", 20);
		params.put("orderStatus", "[4]"); //寄送中
		JsonResponse dispatchingResponse = simpleHttpClient.submitSimple("/ebuy/qryOrderList.json", params, LoginHelper.prepareReqHeader(request));
		request.setAttribute("dispatchingResponse", dispatchingResponse);*/

		params.clear();
		params.put("page", 1);
		params.put("pageNum", 20);
		params.put("orderStatus", "[3,4,5,6]"); //已付款
		JsonResponse finishedResponse = simpleHttpClient.submitSimple("/ebuy/qryOrderList.json", params, LoginHelper.prepareReqHeader(request));
		request.setAttribute("finishedResponse", finishedResponse);

		ModelAndView view = new ModelAndView();
		view.setViewName("/ebuy/myOrder");
		return view;
	}

	/**
	 * 查看订单详情
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@RequestMapping("/viewOrderDetail.do")
	@CheckLogin
	public ModelAndView viewOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
		// 创建参数队列    
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", request.getParameter("orderId"));
		params.put("fromLA", "Y");
		if (request.getSession().getAttribute(WeChatConstant.SessionKey) == null) {//可能用户没有登录进来查看详情，自动登录一次
			SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
			LoginHelper.loginAPI(simpleHttpClient, request, user);
		}
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/qryOrderDetail.json", params, LoginHelper.prepareReqHeader(request));
		logger.info("order detail is: " + jsonResponse.getDataValue().toString());
		request.setAttribute("orderDetail", jsonResponse.getDataValue());

		//从列表进入的不弹
		String entrance = request.getParameter("entrance");
		if (!"orderList".equals(entrance)) {
			List list = null;
			Integer couponSize = ((JSONObject) jsonResponse.getDataValue()).getInteger("shareCouponSize");
			request.setAttribute("hasShareCoupon", couponSize > 0);
			if(couponSize == 0 && jsonResponse.getDataValue() instanceof Map){
				JsonResponse json = simpleHttpClient.submitSimple("/coupon/getCouponSendResult.json", params, LoginHelper.prepareReqHeader(request));
				Map<String, Object> data = (Map<String, Object>)jsonResponse.getDataValue();
				if(data.get("list")!=null && !data.get("list").toString().equals("[]")){
					list = ((JSONObject) json.getDataValue()).getObject("list", ArrayList.class);
				}
			}
			request.setAttribute("list", list);

		} else {
			request.setAttribute("list", null);
			request.setAttribute("hasShareCoupon", false);
		}

		//需要推送模板消息到用户
		if ("1".equals(request.getParameter("isNeedSendMsg"))) {
			sendTemplateMsg(request, response, jsonResponse);
		}
		return new ModelAndView("/ebuy/payListDetails");
	}

	/**
	 * 发送《付款成功》模块消息
	 * 
	 * @param request
	 * @param response
	 * @param jsonResponse
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private void sendTemplateMsg(HttpServletRequest request, HttpServletResponse response, JsonResponse jsonResponse) throws ClientProtocolException,
			IOException {
		SnsUserinfo snsUserInfo = LoginHelper.getSnsUserInfo(request, response);
		String postData = "{\"touser\":\"OPENID\",\"template_id\":\"TEMPLATE_ID\",\"url\":\"URL\",\"topcolor\":\"#FF0000\",\"data\":DATA}";
		postData = postData.replace("OPENID", snsUserInfo.getOpenid());
		postData = postData.replace("TEMPLATE_ID", "8gN3sl1OSN3c9BdjmkxIYEKlZHEg6Aoc-UwFh7HaaHs");//支付成功的模板ID
		//postData = postData.replace("TEMPLATE_ID", "hDCYRbZmGazj46IR_wVyPLS1MahXcz2yw_FwlGQciyA");//支付成功的模板ID-test
		postData = postData.replace("URL", request.getRequestURL() + "?orderId=" + request.getParameter("orderId"));
		JSONObject orderInfo = JSON.parseObject(jsonResponse.getDataValue().toString());
		postData = postData.replace(
				"DATA",
				"{\"first\":{\"value\":\"恭喜你购买成功！我们已收到您的货款，开始为您打包商品，请耐心等待: )\",\"color\":\"#173177\"},\"orderMoneySum\":{\"value\":\""
						+ +orderInfo.getDouble("totalAmount") + "元\",\"color\":\"#173177\"},\"orderProductName\":{\"value\":\""
						+ orderInfo.getJSONArray("productInfo").getJSONObject(0).getJSONArray("productList").getJSONObject(0).getString("name")
						+ "\",\"color\":\"#173177\"},\"remark\":{\"value\":\"欢迎再次购买, 更多优惠与折扣请下载解放区APP！\",\"color\":\"#173177\"}}");

		try {
			TemplateMsgSender.sendMessage(postData);
		} catch (Exception e) {
			logger.info("sendTemplateMsg has exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 删除订单
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@RequestMapping("/delOrderById.do")
	@ResponseBody
	public JsonResponse delOrderById(HttpServletRequest request) throws ClientProtocolException, IOException {
		// 创建参数队列    
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", request.getParameter("orderId"));
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/delOrderById.json", params, LoginHelper.prepareReqHeader(request));
		logger.info("order delete response: " + jsonResponse.getDataValue().toString());
		return jsonResponse;
	}

	/**
	 * 	获取微信JS支付信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@RequestMapping("/getWXPayParams.do")
	@ResponseBody
	public String getWXPayParam(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
		String orderId = ParamUtils.getString(request, "orderId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", orderId);
		logger.info("loginHelperTemp; orderController:" + request.getSession().getId());
		SnsUserinfo snsUser = (SnsUserinfo) LoginHelper.getSnsUserInfo(request, response);
		params.put("openid", snsUser.getOpenid());

		//预支付
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/weiXinPayJieFangQuLightApp/prePayRequest.json", params,
				LoginHelper.prepareReqHeader(request));
		logger.info("prepay's jsonResponse is: " + jsonResponse.getDataValue().toString());

		//正式支付所需参数
		JSONObject jsonObject = JSON.parseObject(jsonResponse.getDataValue().toString());
		SortedMap<String, Object> signParams = new TreeMap<String, Object>();
		signParams.put("appId", WeChatConfig.APPID);
		signParams.put("nonceStr", Sha1Util.getNonceStr());
		signParams.put("timeStamp", Sha1Util.getTimeStamp());
		signParams.put("package", "prepay_id=" + jsonObject.get("prepay_id"));
		signParams.put("signType", "MD5");
		String sign = MD5Util.createMD5Sign("UTF-8", signParams);
		signParams.put("paySign", sign);

		signParams.put("orderId", orderId);//添加订单号（冗余信息）
		logger.info("weixin pay signParams is: " + signParams);
		return JSON.toJSONString(signParams);
	}

	/**
	 * 提交订单(多个商品）
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@RequestMapping("/submitOrderMulti.do")
	@ResponseBody
	public String submitOrderMulti(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
		String addressId = request.getParameter("addressId");
		String[] productIdQty = request.getParameterValues("productIdQty");
		String[] cpIds = request.getParameterValues("cpId");//优惠券
		List<Product4PrepareOrder> prdtIdQtyList = new ArrayList<Product4PrepareOrder>(productIdQty.length);

		for (int i = 0; i < productIdQty.length; i++) {
			Product4PrepareOrder p = new Product4PrepareOrder();
			String[] idQtyPair = productIdQty[i].split(":");
			p.setProductId(idQtyPair[0]);
			p.setProductQty(Integer.parseInt(idQtyPair[1]));
			p.setProductSpecId(idQtyPair.length == 3 ? idQtyPair[2] : null);
			prdtIdQtyList.add(p);
		}

		// 创建参数队列    
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deliveryAddressId", addressId);
		params.put("productList", JSON.toJSONString(prdtIdQtyList));
		params.put("hbAmount", ParamUtils.getDouble(request, "hbAmount"));
		params.put("companyName", request.getParameter("companyName"));
		params.put("productTypeName", request.getParameter("productTypeName"));
		params.put("merchantIdDeliveryTypeList", request.getParameter("merchantIdDeliveryTypeList"));
		params.put("gbId", request.getParameter("gbId"));
		if (cpIds != null && cpIds.length > 0) {
			params.put("couponIdList", JSON.toJSONString(cpIds));
		}

		//先提交订单
		String orderId = request.getParameter("orderId");
		if (orderId == null) {//第一次提交订单
			JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/submitOrderMulti.json", params, LoginHelper.prepareReqHeader(request));
			if(!jsonResponse.getStatus().equals(FocConstants.ResponseStatus.SUCCESS))
				return JSON.toJSONString(jsonResponse);
			
			//再微信统一下单
			orderId = JSON.parseObject(jsonResponse.getDataValue().toString()).getString("orderId");
		}

		if (request.getParameter("shouldPayAmt") != null) {// "用户需要支付现金额"
			double shouldPayAmt = Double.parseDouble(request.getParameter("shouldPayAmt"));
			if (shouldPayAmt < 0.000001) {// "用户不需要支付现金"
				params.clear();
				params.put("orderId", orderId);
				params.put("message", "user do't use cash to pay");
				return JSON.toJSONString(params);
			}
		}

		//记录推荐人与订单的关系 added by wenfq 2017-09-07
		BigInteger recommend_userId = (BigInteger) request.getSession().getAttribute(SessionValueManager.Recommend_User_Id);
		if(recommend_userId != null ) {
			UserRecommendOrder userRecommendOrder = new UserRecommendOrder();
			userRecommendOrder.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_user_recommend_order));
			userRecommendOrder.settEbuyOrderFId(new BigInteger(orderId));
			userRecommendOrder.settUserFId(recommend_userId);
			userRecommendOrderBaseService.insertUserRecommendOrder(userRecommendOrder);
		}

		params.clear();
		params.put("orderId", orderId);
		SnsUserinfo snsUser = (SnsUserinfo) LoginHelper.getSnsUserInfo(request, response);
		params.put("openid", snsUser.getOpenid());

		//预支付
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/weiXinPayJieFangQuLightApp/prePayRequest.json", params,
				LoginHelper.prepareReqHeader(request));
		logger.info("prepay's jsonResponse is: " + jsonResponse.getDataValue().toString());

		//正式支付所需参数
		JSONObject jsonObject = JSON.parseObject(jsonResponse.getDataValue().toString());
		SortedMap<String, Object> signParams = new TreeMap<String, Object>();
		signParams.put("appId", WeChatConfig.APPID);
		signParams.put("nonceStr", Sha1Util.getNonceStr());
		signParams.put("timeStamp", Sha1Util.getTimeStamp());
		signParams.put("package", "prepay_id=" + jsonObject.get("prepay_id"));
		signParams.put("signType", "MD5");
		String sign = MD5Util.createMD5Sign("UTF-8", signParams);
		signParams.put("paySign", sign);
		
		signParams.put("orderId", orderId);//添加订单号（冗余信息）
		logger.info("weixin pay signParams is: " + signParams);
		return JSON.toJSONString(signParams);
	}
	
	/**
	 * 轻应用拼购订单
	 */
	@RequestMapping("/submitFightOrderMulti.do")
	@ResponseBody
	public String submitFightOrderMulti(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
		// 创建参数队列    
		Map<String, Object> params = new HashMap<String, Object>();
		//先提交订单
		String orderId = request.getParameter("orderId");
		String payStatus = null;
		if (orderId == null) {//第一次提交订单
			JsonResponse jsonResponse = simpleHttpClient.submitSimple("/groupPurchase/getEbuyFightOrder.json", getParameterMap(request), LoginHelper.prepareReqHeader(request));
			if(!jsonResponse.getStatus().equals(FocConstants.ResponseStatus.SUCCESS))
				return JSON.toJSONString(jsonResponse);
			
			//再微信统一下单
			orderId = "" + JSON.parseObject(jsonResponse.getDataValue().toString(), Map.class).get("orderId");
			payStatus = "" + JSON.parseObject(jsonResponse.getDataValue().toString(), Map.class).get("payStatus");
		}

		if (payStatus != null && "2".equals(payStatus)) {
			params.clear();
			params.put("orderId", orderId);
			params.put("message", "user do't use cash to pay");
			return JSON.toJSONString(params);
		}
		if (request.getParameter("shouldPayAmt") != null) {// "用户需要支付现金额"
			double shouldPayAmt = Double.parseDouble(request.getParameter("shouldPayAmt"));
			if (shouldPayAmt < 0.000001) {// "用户不需要支付现金"
				params.clear();
				params.put("orderId", orderId);
				params.put("message", "user do't use cash to pay");
				return JSON.toJSONString(params);
			}
		}
	
		params.clear();
		params.put("orderId", orderId);
		SnsUserinfo snsUser = (SnsUserinfo) LoginHelper.getSnsUserInfo(request, response);
		params.put("openid", snsUser.getOpenid());

		//预支付
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/weiXinPayJieFangQuLightApp/prePayRequest.json", params,
				LoginHelper.prepareReqHeader(request));
		logger.info("prepay's jsonResponse is: " + jsonResponse.getDataValue().toString());

		//正式支付所需参数
		JSONObject jsonObject = JSON.parseObject(jsonResponse.getDataValue().toString());
		SortedMap<String, Object> signParams = new TreeMap<String, Object>();
		signParams.put("appId", WeChatConfig.APPID);
		signParams.put("nonceStr", Sha1Util.getNonceStr());
		signParams.put("timeStamp", Sha1Util.getTimeStamp());
		signParams.put("package", "prepay_id=" + jsonObject.get("prepay_id"));
		signParams.put("signType", "MD5");
		String sign = MD5Util.createMD5Sign("UTF-8", signParams);
		signParams.put("paySign", sign);
		
		signParams.put("orderId", orderId);//添加订单号（冗余信息）
		logger.info("weixin pay signParams is: " + signParams);
		return JSON.toJSONString(signParams);
	}

}
