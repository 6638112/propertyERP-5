package com.cnfantasia.server.ms.payCarFee.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.pub.entity.Regist3rdResponse;
import com.cnfantasia.pub.util.WeChatConfig;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.ms.payCarFee.service.IPayCarFeeService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;
import com.cnfantasia.wl.wechat.web.LoginHelper;
import com.wxap.util.MD5Util;
import com.wxap.util.Sha1Util;

/**
 * 停车缴费Controller
 *
 * @author Liyl
 * @version 1.0 2016年3月16日 13:26:28
 */
@Controller
@RequestMapping("/payCarFee")
public class MonthCarController extends BaseController{

	@Resource(name="payCarFeeService")
	private IPayCarFeeService payCarFeeService;
	
	@Resource
	private IHttpClient simpleHttpClient;
	@Resource
	private ISysParamManager sysParamManager;
	
	/**
	 * “停车缴费”首页
	 * @return
	 */
	@RequestMapping("/index.do")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return new ModelAndView("/payCarFee/index");
	}
	
	/**
	 * 跳到“新增车辆”界面
	 * @return
	 */
	@RequestMapping("/newCar.do")
	public ModelAndView newCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView modelAndView = new ModelAndView("/payCarFee/newCar");
		JsonResponse plotJsonResponse = simpleHttpClient.submitSimple("/access/getJFQPlots.json", null, LoginHelper.prepareReqHeader(request));
		Map<String, Object> resultMap = (Map<String, Object>)plotJsonResponse.getDataValue();
		modelAndView.addObject("plots", resultMap.get("list"));
		return modelAndView;
	}
	
	/**
	 * 处理（新增车辆页面）提交的车辆信息
	 * @param name 姓名
	 * @param carNo 车牌号
	 * @param session
	 * @return
	 */
	@RequestMapping("/bindCar.do")
	@ResponseBody
	public JsonResponse bindCar(String name, String carNo, String plotId, HttpServletRequest request){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("realname", name);
		params.put("platenum", carNo);
		params.put("plotId", plotId);
		
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/access/bindCar.json", params, LoginHelper.prepareReqHeader(request));
		return jsonResponse;
	}
	
	/**
	 * 查询该用户的所有缴费信息
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping("/monthCar.do")
	public ModelAndView queryCarFeeAllBills(HttpServletRequest request) throws ClientProtocolException, IOException{
		// 查询已缴账单列表
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/access/qryCarFeeInfo.json", null, LoginHelper.prepareReqHeader(request));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("carFees", jsonResponse.getDataValue());
		modelAndView.addObject("adUrl", sysParamManager.getSysParaValue(SysParamKey.LA_BASE_URL) + "dredge/productDetail.html?productId=1425&jfqsource=weixin");
		modelAndView.setViewName("/payCarFee/monthCarList");
		return modelAndView;
	}
	
	/**
	 * 查询往期账单
	 * @return
	 */
	@RequestMapping("/queryCarFeeHistory.do")
	public ModelAndView queryCarFeeHistory(HttpServletRequest request){
		Map<String, Object> carFeeMap = new HashMap<String, Object>();
		Object groupBuildingId = request.getParameter("groupBuildingId");
		
		carFeeMap.put("groupBuildingId", groupBuildingId); // 小区Id
		JsonResponse carFeeResponse = simpleHttpClient.submitSimple("/access/qryPaymentCarFeeRecords.json", carFeeMap, LoginHelper.prepareReqHeader(request));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("carFees", carFeeResponse.getDataValue());
		modelAndView.setViewName("/payCarFee/billingHistory");
		return modelAndView;
	}
	
	/**
	 * （确认）缴费
	 * @return
	 */
	@RequestMapping("/payCarFee.do")
	@ResponseBody
	public String payCarFee(String carId, String payNum, int needBill, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> carFeeMap = new HashMap<String, Object>();
		carFeeMap.put("carId", carId);
		JsonResponse carFeeResponse = simpleHttpClient.submitSimple("/access/queryCarInfoByCardId.json", carFeeMap, LoginHelper.prepareReqHeader(request));
		if(!CommConstants.ResponseStatus.SUCCESS.equals(carFeeResponse.getStatus())) {
			return JSON.toJSONString(carFeeResponse);
		}
		Map<String, Object> map = (Map<String, Object>)carFeeResponse.getDataValue();
		BigDecimal fee = new BigDecimal(map.get("fee").toString());
		// 1、提交订单
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("carId", carId);
		params.put("payNum", payNum);
		params.put("payFee", fee.multiply(new BigDecimal(payNum)));
		params.put("needBill", needBill);// 是否需要发票=={"0":"不需要","1";"需要"}
		JsonResponse payResultResponse = simpleHttpClient.submitSimple("/access/payCarKeyOrder.json", params, LoginHelper.prepareReqHeader(request));
		if(!CommConstants.ResponseStatus.SUCCESS.equals(payResultResponse.getStatus())) {
			return JSON.toJSONString(payResultResponse);
		}
		Object dataObject = payResultResponse.getDataValue();
		
		Object orderId = ((Map)dataObject).get("orderId");
		HashMap<String, Object> detailParams = new HashMap<String, Object>();
		detailParams.put("orderId", orderId);
		SnsUserinfo snsUser = null;
		try {
			snsUser = (SnsUserinfo) LoginHelper.getSnsUserInfo(request, response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		detailParams.put("openid", snsUser.getOpenid());
		
		// 2、预支付
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/weiXinPayJieFangQuLightApp/prePayRequest.json", detailParams, LoginHelper.prepareReqHeader(request));
		if(!CommConstants.ResponseStatus.SUCCESS.equals(jsonResponse.getStatus())) {
			return JSON.toJSONString(jsonResponse);
		}
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
		return JSON.toJSONString(signParams);
	}
	
	/**
	 * 查询缴费结果详情
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/queryPayCarFeeResult.do")
	public ModelAndView queryPayCarFeeResult(String orderId, String cardname, HttpServletRequest request){
		HashMap<String, Object> detailParams = new HashMap<String, Object>();
		detailParams.put("orderId", orderId);
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/access/getCarKeyOrderDetail.json", detailParams, LoginHelper.prepareReqHeader(request));
		
		Regist3rdResponse regist3rdResponse = LoginHelper.getRegist3rdResponseFromSession(request);
		String realname = regist3rdResponse.getRealName();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("payCarFeeResult", jsonResponse.getDataValue());
		modelAndView.addObject("cardname", cardname);// 月卡，年卡，次缴卡
		modelAndView.addObject("realname", realname);
		modelAndView.setViewName("/payCarFee/parkingFeeDoneDetails");
		return modelAndView;
	}
	
}
