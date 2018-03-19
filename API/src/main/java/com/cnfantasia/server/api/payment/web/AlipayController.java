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

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.payment.entity.AliPrePayParamEntity;
import com.cnfantasia.server.api.payment.serviceUntran.AliDifferenceMerchantPayService;
import com.cnfantasia.server.api.payment.serviceUntran.IAlipayService;
import com.cnfantasia.server.api.property.service.IPropertyService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.commbusi.plotproperty.service.IPlotpropertyCfgService;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

	@Resource
	private AliDifferenceMerchantPayService aliDifferenceMerchantPayService;
    @Resource
    private IPlotpropertyCfgService plotpropertyCfgService;

	private String getNotifyUrl() {
		String tmpUrl = "alipay/payNotify.json";
		String baseUrl = sysParamManager.getSysParaValue(SysParamKey.PAYNOTIFY_BASEURL);
		baseUrl = baseUrl.replace("API510", "API");
		return baseUrl + tmpUrl;
	}

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
		/*if(StringUtils.isEmpty(request.getParameter("orderId"))){
			throw new BusinessRuntimeException("Payment.orderId.isEmpty");
		}
		BigInteger orderId = new BigInteger(request.getParameter("orderId"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//-- 切换门牌有问题 弃用 BigInteger gbId = UserContext.getCurrLoginNo().getUserEntity().getDefaultRoomEntity().getRealRoomEntity().getBuildingEntity().gettGroupBuildingFId();
        BigInteger gbId = plotpropertyCfgService.getGroupBuildingIdByUserId(userId);
        boolean isExpressGateway = false;
		// 2.交互
		String notifyUrl = getNotifyUrl();

		AliPrePayParamEntity aliPrePayParamEntity = new AliPrePayParamEntity();
		aliPrePayParamEntity.setUserId(userId);
		aliPrePayParamEntity.setOrderId(orderId);
		aliPrePayParamEntity.setNotifyUrl(notifyUrl);
		aliPrePayParamEntity.setGbId(gbId);
		aliPrePayParamEntity.setExpressGateway(isExpressGateway);

		String resStr = aliDifferenceMerchantPayService.prePay(aliPrePayParamEntity);*/
		// 3.结果处理
		//jsonResponse.putData("linkString", resStr);
		//return jsonResponse;
		throw new BusinessRuntimeException("非常抱歉，支付业务已关闭!");
	}
	
	/**
	 * 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/payNotify.json")
	public void payNotify(HttpServletRequest request, HttpServletResponse response) {
		logger.info("AlipayController payNotify start..");
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
				params.put(name, valueStr);
				logger.info(name+"==="+valueStr);
			}
		}
		// 2.交互
		String app_id = params.get("app_id");
		Boolean notifyRes = false;
		//新版支付宝
		if(!DataUtil.isEmpty(app_id)) {
            notifyRes = aliDifferenceMerchantPayService.payNotify(params);
		} else {
            notifyRes = alipayService.payNotify(params);
		}
		logger.info("alipayService.payNotify(params):" + notifyRes);
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
			out = response.getOutputStream();
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
	}
	
	/**
	 * 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/payNotifyTest.json")
	public void payNotifyTest(HttpServletRequest request, HttpServletResponse response) {
		logger.info("AlipayController payNotify start..");
		String url = request.getRequestURL().toString();
		if(!url.contains("api.jiefangqu.com") &&
				(url.startsWith("http://121.41.36.141:8080/API/") || url.startsWith("http://127.0.0.1") || url.startsWith("http://localhost")
						|| url.startsWith("http://192.168.1.45") || url.startsWith("http://api.linlile")
						)) {

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
//					try {
//						valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//					} catch (UnsupportedEncodingException e) {
//						logger.info(valueStr+" .getBytes cause exception,", e);
//					}
					params.put(name, valueStr);
					logger.info(name+"==="+valueStr);
				}
			}
			// 2.交互
			Boolean notifyRes = alipayService.payNotifyTest(params);
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
				out = response.getOutputStream();
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
		}
	}
}
