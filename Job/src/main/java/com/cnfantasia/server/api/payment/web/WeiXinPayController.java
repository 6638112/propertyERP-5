/**   
 * Filename:    WeiXinPayController.java   
 * @version:    1.0  
 * Create at:   2014年6月12日 下午12:21:00   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年6月12日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.payment.web;

import java.math.BigInteger;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.payment.constant.WeiXinPayConstantUtil.WeiXinPay_GoalAccType;
import com.cnfantasia.server.api.payment.entity.WeiXinNotifyEntity;
import com.cnfantasia.server.api.payment.serviceUntran.IWeiXinPayService;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.json.JsonResponse;
import com.tenpay.ClientRequestHandler;
import com.tenpay.PackageRequestHandler;
import com.tenpay.PrepayIdRequestHandler;

/**
 * Filename: WeiXinPayController.java
 * 
 * @version: 1.0.0 Create at: 2014年6月12日 下午12:21:00 Description:微信支付处理
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年6月12日 shiyl 1.0 1.0 Version
 */
@Controller
@RequestMapping("/weixinPay")
public class WeiXinPayController extends AbstractWeiXinPaymentController {
	private static final String payNotifyUrl = "weixinPay/payNotify.json?";

	private IWeiXinPayService weiXinPayService;

	public void setWeiXinPayService(IWeiXinPayService weiXinPayService) {
		this.weiXinPayService = weiXinPayService;
	}

	private ISysParamManager sysParamManager;

	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}

	/**
	 * 支付请求
	 * 
	 * @param request
	 */
	@RequestMapping("/prePayRequest.json")
	@ResponseBody
//	@Override
	public JsonResponse prePayRequest(HttpServletRequest request, HttpServletResponse response) {
		return super.prePayRequest(WeiXinPay_GoalAccType.CaiZhiYun,request, response);
	}

	@RequestMapping("/payNotify.json")
	@ResponseBody
//	@Override
	public JsonResponse payNotify(HttpServletRequest request, HttpServletResponse response) {
		return super.payNotify(WeiXinPay_GoalAccType.CaiZhiYun,request, response);
	}

	@Override
	public Map<String, Object> doWeixinPrePay(WeiXinPay_GoalAccType goalAccType,BigInteger userId, BigInteger orderId, String notify_url,
			String remoteAddr, PackageRequestHandler packageReqHandler, PrepayIdRequestHandler prepayReqHandler,
			ClientRequestHandler clientHandler) {
		return weiXinPayService.weixinPrePay(goalAccType,userId, orderId, notify_url, remoteAddr, packageReqHandler, prepayReqHandler,
				clientHandler);
	}

	@Override
	public String getNotifyUrl() {
		String baseUrl = sysParamManager.getSysParaValue(SysParamKey.PAYNOTIFY_BASEURL);
		return baseUrl + payNotifyUrl;
	}

	@Override
	public void payNotify(WeiXinNotifyEntity resultEntity) {
		weiXinPayService.payNotify(resultEntity);
	}

	@Override
	public void addNotifyRecord(WeiXinNotifyEntity currWeiXinNotifyEntity, String payErrInfo, BigInteger orderId) {
		weiXinPayService.addNotifyRecord(currWeiXinNotifyEntity, payErrInfo, orderId);
	}
	
}
