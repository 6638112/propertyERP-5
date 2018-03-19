/**   
 * Filename:    AlipayController.java   
 * @version:    1.0  
 * Create at:   2014年12月4日 上午8:02:19   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年12月4日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.payment.web;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.payment.serviceUntran.IAlipayService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename: AlipayController.java
 * 
 * @version: 1.0.0 Create at: 2014年12月4日 上午8:02:19 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年12月4日 shiyl 1.0 1.0 Version
 */
@Controller
@RequestMapping("/alipay")
public class AlipayController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());
	
	private IAlipayService alipayService;
	public void setAlipayService(IAlipayService alipayService) {
		this.alipayService = alipayService;
	}

	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}

	private String getNotifyUrl() {
		String tmpUrl = "alipay/payNotify.json";
		String baseUrl = sysParamManager.getSysParaValue(SysParamKey.PAYNOTIFY_BASEURL);
		return baseUrl + tmpUrl;
	}

	// private String getCallBackUrl(){
	// String tmpUrl = "alipay/callBack.json";
	// String baseUrl =
	// sysParamManager.getSysParaValue(SysParamKey.PAYNOTIFY_BASEURL);
	// return baseUrl+tmpUrl;
	// }
	// private String getMerchantUrl(){
	// String tmpUrl = "alipay/merchant.json";
	// String baseUrl =
	// sysParamManager.getSysParaValue(SysParamKey.PAYNOTIFY_BASEURL);
	// return baseUrl+tmpUrl;
	// }
	
	/**
	 * 预支付请求
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/prePayRequest.json")
	@ResponseBody
	public JsonResponse prePayRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集参数
		if(StringUtils.isEmpty(request.getParameter("orderId"))){
			throw new BusinessRuntimeException("Payment.orderId.isEmpty");
		}
		BigInteger orderId = new BigInteger(request.getParameter("orderId"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		boolean isExpressGateway = false;
		// 2.交互
		String notifyUrl = getNotifyUrl();
		String resStr = alipayService.prePay(userId, orderId, notifyUrl, isExpressGateway);
		// 3.结果处理
		jsonResponse.putData("linkString", resStr);
		return jsonResponse;
	}
	
//	@RequestMapping("/testVoid.json")
//	public void testVoid(HttpServletRequest request, HttpServletResponse response) {
//		String resStr = "success";
//		ServletOutputStream out = null;
//		response.setContentType("text/html;charset=UTF-8");
//		try {
//			out = (ServletOutputStream)response.getOutputStream();
//			out.println(resStr);
//		} catch (IOException e) {
//			logger.error("Alipay payNotify out.println cause exception,resStr is :"+resStr, e);
//		}finally{
//			try {
//				if (out != null) {
//					out.flush();
//					out.close();
//				}
//			} catch (IOException ie) {
//				logger.error("Alipay payNotify out.println finally close cause exception,resStr is :"+resStr, ie);
//			}
//		}
//	}
//	
//	@RequestMapping("/testStr.json")
//	@ResponseBody
//	public String testStr(HttpServletRequest request, HttpServletResponse response) {
//		return "success";
//	}
	
	
	/**
	 * 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/payNotify.json")
//	@ResponseBody
	public void payNotify(HttpServletRequest request, HttpServletResponse response) {
		logger.info("AlipayController payNotify start..");
		// 1.搜集参数
		
//		{
//			Map<String,String> allLogParams = new HashMap<String, String>();
//			Enumeration<String> currParams = request.getParameterNames();
//			while (currParams.hasMoreElements()) {
//				String paramName = currParams.nextElement();
//				logger.info(paramName+" --is-- "+request.getParameter(paramName));
//				allLogParams.put(paramName, request.getParameter(paramName));
//			}
//		}
		
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		{
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//				try {
//					valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//				} catch (UnsupportedEncodingException e) {
//					logger.info(valueStr+" .getBytes cause exception,", e);
//				}
				params.put(name, valueStr);
				logger.info(name+"==="+valueStr);
			}
		}
		
		
		// 2.交互
		Boolean notifyRes = alipayService.payNotify(params);
//		Boolean notifyRes = alipayService.payNotify(out_trade_no, trade_no, trade_status, verifyNotifyRes, params);
		// 3.结果处理
		String resStr = null;
		if(notifyRes){
			resStr = "success";
		}else{
			resStr = "fail";
		}
		ServletOutputStream out = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			out = (ServletOutputStream)response.getOutputStream();
			out.println(resStr);
		} catch (IOException e) {
			logger.error("Alipay payNotify out.println cause exception,resStr is :"+resStr, e);
		}finally{
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException ie) {
				logger.error("Alipay payNotify out.println finally close cause exception,resStr is :"+resStr, ie);
			}
		}
//		return resStr;
	}

	// /**
	// * 页面跳转同步通知页面路径
	// * 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
	// * @param request
	// * @param response
	// * @return
	// */
	// @RequestMapping("/callBack.json")
	// @ResponseBody
	// public JsonResponse callBack(HttpServletRequest request,
	// HttpServletResponse response) {
	// logger.info("AlipayController callBack start..");
	// JsonResponse jsonResponse = new JsonResponse();
	// //1.搜集参数
	// Enumeration<String> currParams = request.getParameterNames();//TODO
	// while(currParams.hasMoreElements()){
	// logger.info(currParams.nextElement());
	// }
	// //2.交互
	// //3.结果处理
	// return jsonResponse;
	// }
	//
	// /**
	// * 操作中断返回地址
	// * 用户付款中途退出返回商户的地址。需http://格式的完整路径，不允许加?id=123这类自定义参数
	// * @param request
	// * @param response
	// * @return
	// */
	// @RequestMapping("/merchant.json")
	// @ResponseBody
	// public JsonResponse merchant(HttpServletRequest request,
	// HttpServletResponse response) {
	// logger.info("AlipayController merchant start..");
	// JsonResponse jsonResponse = new JsonResponse();
	// //1.搜集参数
	// Enumeration<String> currParams = request.getParameterNames();//TODO
	// while(currParams.hasMoreElements()){
	// logger.info(currParams.nextElement());
	// }
	// //2.交互
	// //3.结果处理
	// return jsonResponse;
	// }

}
