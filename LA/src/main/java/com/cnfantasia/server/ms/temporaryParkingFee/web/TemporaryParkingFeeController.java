package com.cnfantasia.server.ms.temporaryParkingFee.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.pub.util.WeChatConfig;
import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.wl.wechat.WeChatConstant;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;
import com.cnfantasia.wl.wechat.web.LoginHelper;
import com.wxap.util.MD5Util;
import com.wxap.util.Sha1Util;

/**
 * 临时车缴费
 * 
 * @author liyulin
 * @version 1.0 2016年4月26日 下午4:14:52
 */
@Controller
@RequestMapping("/temporaryParkingFee")
public class TemporaryParkingFeeController  extends BaseController{

	private final Log logger = LogFactory.getLog(getClass());
	
	private final String GB_SESSION_KEY = "gbId";
	
	//private final String FROM_SESSION_KEY = "from";
	
	private final String CAR_USER_SESSION_KEY = "carUserKey";
	
	@Resource
	private IHttpClient simpleHttpClient;
	@Resource
	private ISysParamManager sysParamManager;
	
	/**
	 * 跳转类型
	 */
	public final static class JumpType{
		public final static String PAY_PAGE    = "1";// 付款页面， payParkingFee.jsp
		public final static String DETAIL_PAGE = "2";// 详情页面，parkingFeeDetail.jsp
		public final static String SELF_PAGE   = "3";// 没有相关信息，不跳转
	}
			
	/**
	 * 临时车缴费首页
	 * 
	 * @param gbId
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping("/goFeePage.html")
	public String goFeePage(String gbId, /*String from,*/ HttpServletRequest request, HttpServletResponse response) throws IOException{
		String ua = request.getHeader("user-agent").toLowerCase();  
		logger.info("TemporaryParkingFeeController-->goFeePage:"+ua);
		
		//if (ua.indexOf("micromessenger") > 0) {// 通过微信浏览器进入
			if ((!LoginHelper.isLoginUser(request) && request.getParameter("code") == null)) {//判断此用户是否是未关注或登录的用户 
				request.getSession().setAttribute("isLogin", true);
				String currentUrl = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.LA_BASE_URL)+ "temporaryParkingFee/goFeePage.html" + "?" + request.getQueryString();
				String wechatRedirectUrl = WeChatConstant.OAuth2_URL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI",currentUrl);
				return "redirect:" + wechatRedirectUrl; //重定向到微信授权页
			}
			if(StringUtils.isBlank(gbId)){
				//from = AccessDict.Code.XMF;
				//request.getSession().setAttribute(FROM_SESSION_KEY, from);
				request.getSession().removeAttribute(GB_SESSION_KEY);
			} else {
				request.getSession().setAttribute(GB_SESSION_KEY, gbId);
				//request.getSession().removeAttribute(FROM_SESSION_KEY);
			}
			
			SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
			request.getSession().setAttribute(CAR_USER_SESSION_KEY, user);
			
			//记录用户openId的请求临停车小区ID，放入redis，用于关注完后推送消息
			int subscribe = 1;
//			if (StringUtils.isBlank(gbId)) {
//				//是否已经关注公众号信息放入request
//				subscribe = WeixinUtils.setIsSubscribe(request, response);
//				if (user.getOpenid() != null) {
//					RedisCacheHandler.set(RedisCachePrefix.TEMP_CAR_VIEW_OPENID + user.getOpenid(), from, 5 * 60);
//				}
//			}
			
			return "forward:/temporaryParkingFee/getParkingFeeIndex.html?subscribe=" + subscribe + "&couponId="+request.getParameter("couponId");
//		} else {// 非微信浏览器进入
//			return "redirect:/temporaryParkingFee/parkingTip.html";
//		}
	}
	
	@RequestMapping("/getParkingFeeIndex.html")
	public ModelAndView getParkingFeeIndex(String subscribe, HttpSession session, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("/temporaryParkingFee/getParkingFeeIndex");
		
		Object carNumPrefix = AccessDict.DEFAULT_CAR_NUM_PREFIX;
		Object gbId = session.getAttribute(GB_SESSION_KEY);
		if(null!=gbId){
			HashMap<String, Object> detailParams = new HashMap<String, Object>();
			detailParams.put("gbId", gbId);
			JsonResponse jsonResponse = simpleHttpClient.submitSimple("/access/getCarNumPrefixByGbId.json", detailParams);
			if(jsonResponse.getStatus().equals(CommConstants.ResponseStatus.SUCCESS)){
				carNumPrefix = jsonResponse.getDataValue();
			}
		}
		modelAndView.addObject("carNumPrefix", carNumPrefix);
		BigInteger couponId = ParamUtils.getBigInteger(request, "couponId", null);
		boolean isAlter = !DataUtil.isEmpty(couponId);
		if(isAlter) {
			modelAndView.addObject("title", sysParamManager.getSysParaValue(SysParamKey.LA_TMP_CAR_PUSH_Title_Scan));
			modelAndView.addObject("desc", sysParamManager.getSysParaValue(SysParamKey.LA_TMP_CAR_PUSH_DESC_Scan));
			modelAndView.addObject("img", sysParamManager.getSysParaValue(SysParamKey.LA_BASE_URL) + "images/car_fee_coupon_send.png");
		}
		modelAndView.addObject("isAlter", isAlter);
		modelAndView.addObject("adUrl", sysParamManager.getSysParaValue(SysParamKey.LA_BASE_URL) + "dredge/productDetail.html?productId=1425&jfqsource=weixin");

		return modelAndView;
	}
	
	@RequestMapping("/parkingTip.html")
	public ModelAndView parkingTip(){
		return new ModelAndView("/temporaryParkingFee/parkingTip");
	}
	
	/**
	 * 临停车费用查询
	 * 
	 * @param carNum
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/carNumIsExisted.html")
	@ResponseBody
	public JsonResponse carNumIsExisted(String carNum, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		if(!LoginHelper.isLoginUser(request)){
			Object o = request.getSession().getAttribute(CAR_USER_SESSION_KEY);
			LoginHelper.loginAPI(simpleHttpClient, request, (SnsUserinfo)o);
		}
		
		//根据车牌号获取小区id
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("carNum", carNum);
		JsonResponse getGbIdByCarNumResponse = simpleHttpClient.submitSimple("/access/getGbIdByCarNum.json", map, LoginHelper.prepareReqHeader(request));
		Object gbId = getGbIdByCarNumResponse.getDataValue();

		request.getSession().setAttribute(GB_SESSION_KEY, gbId);

		//Object from = request.getSession().getAttribute(FROM_SESSION_KEY);
		JsonResponse result = new JsonResponse();
		if(null==gbId || (null!=gbId && "{}".equals(gbId.toString()))){
			result.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			result.setMessage("暂未查询到该车辆信息！");
		}else {
			HashMap<String, Object> detailParams = new HashMap<String, Object>();
			detailParams.put("plate", carNum);
			detailParams.put("gbId", gbId);
			//detailParams.put("from", from);
			detailParams.put("isOnlyQryFee", false);
			try{
				JsonResponse jsonResponse = simpleHttpClient.submitSimple("/access/qryFee.json", detailParams, LoginHelper.prepareReqHeader(request));
				// 没有“临时车缴费信息”，则提示
				if(jsonResponse.getStatus().equals(CommConstants.ResponseStatus.SUCCESS)){
					if(jsonResponse.getDataValue() instanceof Map){
						Map<String, Object> parkingFeeInfo = (Map<String, Object>)jsonResponse.getDataValue();
						if(parkingFeeInfo.get("inDate")==null || parkingFeeInfo.get("carId")==null){
							result.setMessage(JumpType.SELF_PAGE);
						} else {
							if(null==gbId){// from 小蜜蜂
								gbId = parkingFeeInfo.get("gbId");
								request.getSession().setAttribute(GB_SESSION_KEY, gbId);
							}
							// 还未缴费，跳入“缴费”页面
							StringBuilder parkingFee = new StringBuilder();
							parkingFee.append("carId=").append(parkingFeeInfo.get("carId"))
									  .append("&plate=").append(URLEncoder.encode(carNum, "UTF-8"))
									  .append("&fee=").append(URLEncoder.encode(parkingFeeInfo.get("fee").toString(), "UTF-8"))
									  .append("&inDate=").append(URLEncoder.encode(parkingFeeInfo.get("inDate").toString(), "UTF-8"))
									  .append("&gbName=").append(URLEncoder.encode(parkingFeeInfo.get("gbName").toString(), "UTF-8"))
									  .append("&parkingTime=").append(URLEncoder.encode(parkingFeeInfo.get("parkingTime").toString(), "UTF-8"))
									  .append("&enterDate=").append(URLEncoder.encode(parkingFeeInfo.get("enterDate").toString(), "UTF-8"));
							Object prepaymentamount = parkingFeeInfo.get("prepaymentamount");
							Double prepaymentAmount = 0.0;
							if(null!=prepaymentamount){
								prepaymentAmount = Double.parseDouble(prepaymentamount.toString());
							}
							parkingFee.append("&prepaymentamount=").append(URLEncoder.encode(prepaymentAmount.toString(), "UTF-8"));
							int carTmpIsOpen = Integer.parseInt(parkingFeeInfo.get("carTmpIsOpen").toString());
							if(carTmpIsOpen!=AccessDict.TmpCarOpenStatus.CLOSE){
								parkingFee.append("&couponAmount=").append(URLEncoder.encode(parkingFeeInfo.get("couponAmount").toString(), "UTF-8"));
							}
							parkingFee.append("&carTmpIsOpen=").append(carTmpIsOpen);
							result.setDataValue(parkingFee.toString());
							result.setMessage(JumpType.PAY_PAGE);
						}
					} else {
						result.setMessage(JumpType.SELF_PAGE);
					}
					result.setStatus(CommConstants.ResponseStatus.SUCCESS);
				} else {
					result.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
					String msg = StringUtils.isNotBlank(jsonResponse.getMessage()) ? jsonResponse.getMessage():"查询出错！";
					result.setMessage(msg);
				}
			} catch(Exception e){
				result.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
				result.setMessage("系统查询超时！");
			} 
		}
		
		return result;
	}
	
	/**
	 * 确认缴费
	 * 
	 * @param carId
	 * @param carNum
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping("/payParkingFee.html")
	@ResponseBody
	public String payParkingFee(String carId, String carNum, String carPreferId, String ucId, HttpServletRequest request, HttpServletResponse response) throws IOException{
		Object gbId = request.getSession().getAttribute(GB_SESSION_KEY);
		if(gbId==null){
			JsonResponse result = new JsonResponse();
			result.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			result.setMessage("会话已失效，请重新扫码进入！");
			return JSON.toJSONString(result);
		}
		
		if(!LoginHelper.isLoginUser(request)){
			Object o = request.getSession().getAttribute(CAR_USER_SESSION_KEY);
			LoginHelper.loginAPI(simpleHttpClient, request, (SnsUserinfo)o);
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("plate", carNum);
		params.put("gbId", gbId);
		params.put("isOnlyQryFee", true);
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/access/qryFee.json", params, LoginHelper.prepareReqHeader(request));
		Map<String, Object> feeMap = (Map<String, Object>)jsonResponse.getDataValue();
		BigDecimal fee = new BigDecimal(String.valueOf(feeMap.get("fee")));
		// 1、提交订单
		// 如果费用为0，则不需用缴费
		if(fee.doubleValue()<=0){
			JsonResponse result = new JsonResponse();
			result.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			result.setMessage("暂未产生费用，无需缴费！");
			return JSON.toJSONString(result);
		}
		
		params.clear();
		params.put("carId", carId);
		params.put("payNum", 0);
		params.put("payFee", fee);
		/*------------------华鹏飞车禁start------------------*/
		params.put("receivableFee", feeMap.get("receivableFee"));
		/*------------------华鹏飞车禁end------------------*/
		/*------------------小蜜蜂车禁start------------------*/
		params.put("orderid", feeMap.get("orderid"));
		if(null!=feeMap.get("discountamount") && StringUtils.isNotBlank(feeMap.get("discountamount").toString())){
			params.put("discountamount", BigDecimal.valueOf(Double.parseDouble(feeMap.get("discountamount").toString())).multiply(BigDecimal.valueOf(100L)).longValue());
		} else {
			params.put("discountamount", 0);
		}
		/*------------------小蜜蜂车禁end------------------*/
		params.put("gbId", gbId);
		params.put("ucId", ucId);
		JsonResponse payResultResponse = simpleHttpClient.submitSimple("/access/payCarKeyOrder.json", params, LoginHelper.prepareReqHeader(request));
		if("0000".equals(payResultResponse.getStatus())){
			Object dataObject = payResultResponse.getDataValue();
			Object orderId = ((Map)dataObject).get("orderId");
			Object free = ((Map)dataObject).get("free");
			if(Boolean.parseBoolean(free.toString())){
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("free", free);
				paramMap.put("orderId", orderId);
				return JSON.toJSONString(paramMap);
			} else {
				params.clear();
				params.put("orderId", orderId);
				SnsUserinfo snsUser = null;
				try {
					snsUser = LoginHelper.getSnsUserInfo(request, response);
				} catch (ClientProtocolException e) {
					logger.error("TemporaryParkingFeeController.payParkingFee", e);
				} catch (IOException e) {
					logger.error("TemporaryParkingFeeController.payParkingFee", e);
				}
				params.put("openid", snsUser.getOpenid());

				// 2、预支付
				JsonResponse payResponse = simpleHttpClient.submitSimple("/weiXinPayJieFangQuLightApp/prePayRequest.json", params, LoginHelper.prepareReqHeader(request));

				//正式支付所需参数
				JSONObject jsonObject = JSON.parseObject(payResponse.getDataValue().toString());
				SortedMap<String, Object> signParams = new TreeMap<String, Object>();
				signParams.put("appId", WeChatConfig.APPID);
				signParams.put("nonceStr", Sha1Util.getNonceStr());
				signParams.put("timeStamp", Sha1Util.getTimeStamp());
				signParams.put("package", "prepay_id=" + jsonObject.get("prepay_id"));
				signParams.put("signType", "MD5");
				String sign = MD5Util.createMD5Sign("UTF-8", signParams);
				signParams.put("paySign", sign);
				signParams.put("orderId", orderId);//添加订单号（冗余信息）
				signParams.put("free", false);

				String signResult = JSON.toJSONString(signParams);
				logger.info("TemporaryParkingFeeController-->payParkingFee==>"+signResult);
				return signResult;
			}
		} else {
			return JSON.toJSONString(payResultResponse);
		}
	}
	
	/**
	 * 查询临时车费用详情
	 * 
	 * @param send 是否发送缴费通知==>{"yes"：发送}
	 * @param carNum
	 * @param request
	 * @return
	 */
	@RequestMapping("queryParkingFee.html")
	public ModelAndView queryParkingFee(String carNum, String orderId, final HttpServletRequest request){
		HashMap<String, Object> detailParams = new HashMap<String, Object>();
		detailParams.put("orderId", orderId);
		detailParams.put("carNum", carNum);
		
		JsonResponse parkingFeeJsonResponse = simpleHttpClient.submitSimple("/access/qryParkingFeeDetail.json", detailParams, LoginHelper.prepareReqHeader(request));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("feeDetail", parkingFeeJsonResponse);
		modelAndView.addObject("adUrl", sysParamManager.getSysParaValue(SysParamKey.LA_BASE_URL) + "dredge/productDetail.html?productId=1425&jfqsource=weixin");
		modelAndView.setViewName("/temporaryParkingFee/parkingFeeDetail");
		return modelAndView;
	}
	
	/**
	 * 查询费用（刷新）
	 * 
	 * @param carNum
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping("/qryFee.html")
	@ResponseBody
	public JsonResponse qryFee(String carNum, HttpServletRequest request, HttpServletResponse response) throws IOException{
		Object gbId = request.getSession().getAttribute(GB_SESSION_KEY);
		JsonResponse jsonResponse = null;
		if(gbId==null){
			jsonResponse = new JsonResponse();
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			jsonResponse.setMessage("会话已失效，请重新扫码进入！");
		} else {
			HashMap<String, Object> detailParams = new HashMap<String, Object>();
			detailParams.put("plate", carNum);
			detailParams.put("gbId", gbId);
			detailParams.put("isOnlyQryFee", false);
			jsonResponse = simpleHttpClient.submitSimple("/access/qryFee.json", detailParams);
			if(jsonResponse.getStatus().equals(CommConstants.ResponseStatus.SUCCESS)){
				Map<String, Object> feeMap = (Map<String, Object>)jsonResponse.getDataValue();
				int carTmpIsOpen = Integer.parseInt(feeMap.get("carTmpIsOpen").toString());
				if(carTmpIsOpen!=AccessDict.TmpCarOpenStatus.CLOSE){
					jsonResponse.putData("couponAmount", feeMap.get("couponAmount"));
				}
				jsonResponse.putData("carTmpIsOpen", carTmpIsOpen);
			}
		}
		return jsonResponse;
	}
	
	/**
	 * 查询临停车券
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping("/qryCarCouponWithUserId.json")
	@ResponseBody
	public JsonResponse qryCarCouponByUserId(HttpServletRequest request) throws IOException{
		return simpleHttpClient.submitSimple("/access/qryCarCouponByUserId.json", null, LoginHelper.prepareReqHeader(request));
	}


	/**
	 * 查询停车场
	 * @return
	 */
	@RequestMapping("/parkingLots.html")
	public ModelAndView parkingLots(){
		return new ModelAndView("/temporaryParkingFee/parkingLots");
	}
	
	/**
	 * 查询停车场信息
	 * @param city
	 * @param plotName
	 * @return
	 */
	@RequestMapping("/getPlots.json")
	@ResponseBody
	public JsonResponse getPlots(String city, String plotName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("city", city);
		paramMap.put("plotName", plotName);
		
		return simpleHttpClient.submitSimple("/access/getPlots.json", paramMap, null);
	}
}
