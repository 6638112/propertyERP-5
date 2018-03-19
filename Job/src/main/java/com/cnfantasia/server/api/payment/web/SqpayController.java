/**   
* Filename:    SqpayController.java   
* @version:    1.0  
* Create at:   2015年10月12日 上午11:48:49   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年10月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.payment.web;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.payment.entity.OrderPayInfo;
import com.cnfantasia.server.api.payment.serviceUntran.ISqpayService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.exception.ExceptionParseUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.httpcllient.https.SqMD5Util;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyPrepaySqLog.entity.EbuyPrepaySqLog;
import com.cnfantasia.server.domainbase.ebuyPrepaySqLog.service.IEbuyPrepaySqLogBaseService;

/**
 * Filename:    SqpayController.java
 * @version:    1.0.0
 * Create at:   2015年10月12日 上午11:48:49
 * Description:双乾支付
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年10月12日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/sqpay")
public class SqpayController  extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	@Resource
	private ISqpayService sqpayService;
	@Resource
	private IEbuyPrepaySqLogBaseService ebuyPrepaySqLogBaseService;
	@Resource
	private IUuidManager uuidManager;
	
	@Resource
	private ISysParamManager sysParamManager;
	
	private String getPayNotifyBaseUrl(){
		String baseUrl = sysParamManager.getSysParaValue(SysParamKey.SQPAY_NOTIFY_BASEURL);
		return baseUrl;
	}
	private String getNotifyURL() {
		String tmpUrl = "sqpay/payNotify.html";
		String baseUrl = getPayNotifyBaseUrl();
		return baseUrl + tmpUrl;
//		return "http://www.jiefangqu.cn:80/Test95epay/notifyResult.jsp"; // 服务端后台支付状态通知.
	}
	private String getReturnURL() {
		String tmpUrl = "sqpay/payReturn.html";
		String baseUrl = getPayNotifyBaseUrl();
		return baseUrl + tmpUrl;
//		return "http://www.jiefangqu.cn:80/Test95epay/payResult.jsp"; // 客户支付返回商户前台网站URL.
	}
	
	/**
	 * 预支付请求
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/prePayRequest.html")
	public ModelAndView prePayRequestHtml(HttpServletRequest request, HttpServletResponse response){
		BigInteger orderId = null;
		try {
		// 1.搜集参数
			if(StringUtils.isEmpty(request.getParameter("orderId"))){
				throw new BusinessRuntimeException("Payment.orderId.isEmpty");
			}
			orderId = new BigInteger(request.getParameter("orderId"));
			BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//			BigInteger userId = new BigInteger("50270");
			logger.debug("sqpay.prePayRequestHtml,orderId is:"+orderId+",userId is:"+userId);
			// 2.交互
			OrderPayInfo orderPayInfo = sqpayService.getOrderPayInfoById(userId, orderId);
			String outTradeNo = orderPayInfo.getOutTradeNo();
			String subject = orderPayInfo.getProductInfo();
			String price = PriceUtil.div100(orderPayInfo.getTotalAmount()).toString();//单位转化为元
			String body = orderPayInfo.getProductDetail()!=null?orderPayInfo.getProductDetail():orderPayInfo.getProductInfo();
			// 3.结果处理
			String terminalKind = SqPayUtil.getTerminalKind();
			String MerNo				= SqPayUtil.getMerNo();
			String MD5key 			= SqPayUtil.getMD5key();
			String PaymentType	= SqPayUtil.getPaymentType();
			String PayType 			= SqPayUtil.getPayType();
			String NotifyURL 		= getNotifyURL();//服务端后台支付状态通知.  
			String ReturnURL		= getReturnURL();//客户支付返回商户前台网站URL.   
//				String BillNo				= String.valueOf(System.currentTimeMillis());
			String BillNo				= outTradeNo;
	    String Amount  			= price+"";
	    String MD5info; 
	    String MerRemark		= subject;
	    String  products    =  body;  
			SqMD5Util md5util = new SqMD5Util();
	    MD5info = md5util.signMap(new String[]{MerNo,BillNo,Amount,ReturnURL}, MD5key, "REQ");
	    
	    //设定Attribute
	    request.setAttribute("terminalKind", terminalKind);
	    request.setAttribute("MerNo", MerNo);
	    request.setAttribute("BillNo", BillNo);
	    request.setAttribute("Amount", Amount);
	    request.setAttribute("PaymentType", PaymentType);
	    request.setAttribute("PayType", PayType);
	    request.setAttribute("ReturnURL", ReturnURL);
	    request.setAttribute("NotifyURL", NotifyURL);
	    request.setAttribute("MD5info", MD5info);
	    request.setAttribute("MerRemark", MerRemark);
	    request.setAttribute("products", products);
	    if(logger.isDebugEnabled()){
	    	logger.debug("sqpay.initFormData.info"+",terminalKind:"+terminalKind+",MerNo:"+MerNo+",BillNo:"+BillNo+",Amount:"+Amount+",PaymentType:"+PaymentType+",PayType:"
	    			+PayType+",ReturnURL:"+ReturnURL+",NotifyURL:"+NotifyURL+",MD5info:"+MD5info+",MerRemark:"+MerRemark+",products:"+products);
	    }
		  //记录日志
			EbuyPrepaySqLog prepaySqLog = new EbuyPrepaySqLog();
			prepaySqLog.setAmout(Amount);
			prepaySqLog.setBillNo(BillNo);
			prepaySqLog.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_prepay_sq_log));
			prepaySqLog.setMd5Info(MD5info);
			prepaySqLog.setMerNo(MerNo);
			prepaySqLog.setMerRemark(MerRemark);
			prepaySqLog.setNotifyUrl(NotifyURL);
			prepaySqLog.setOrderId(orderId);
			prepaySqLog.setPaymentType(PaymentType);
			prepaySqLog.setPayType(PayType);
			prepaySqLog.setProducts(products);
			prepaySqLog.setReturnUrl(ReturnURL);
			prepaySqLog.setTerminalKind(terminalKind);
			ebuyPrepaySqLogBaseService.insertEbuyPrepaySqLog(prepaySqLog);
		} catch (Exception ex) {
			logger.debug("SqpayController prePayRequestHtml cause exception,orderId is:"+orderId,ex);
			boolean isSuccess = false;
			String resDesc = ExceptionParseUtil.parseErrorMsg(ex,true);
			request.setAttribute("status", isSuccess?"0":"1");//0成功,1失败
	   	request.setAttribute("resDesc", resDesc);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/sqpay/errorReturn");
			return mav;
		}
		
		//页面跳转
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/sqpay/gotoPay");
		return mav;
	}
	
	/**
	 * 支付回调
	 * @param request
	 */
	@RequestMapping("/payNotify.html")
	public void payNotify(HttpServletRequest request){
		//1.搜集参数
		String CharacterEncoding = "UTF-8";
    try {
			request.setCharacterEncoding(CharacterEncoding);
		} catch (UnsupportedEncodingException e) {
			logger.error("sqpay.payNotify.setCharacterEncoding cause error:", e);
		}
    StringBuffer paramAll = new StringBuffer();
    {
    	 @SuppressWarnings("unchecked")
			Enumeration<String> attNames = request.getParameterNames();
       while(attNames.hasMoreElements()){
       	String obj = attNames.nextElement();
       	paramAll.append(obj);
       	paramAll.append(":");
       	paramAll.append(request.getParameter(obj));
      	paramAll.append(";");
       }
    }
    String flowNo = request.getParameter("Orderno");
    String MerNo = request.getParameter("MerNo"); 
    String BillNo = request.getParameter("BillNo");	
    String Amount = request.getParameter("Amount");	
    String Succeed = request.getParameter("Succeed");
    String Result  = request.getParameter("Result"); 
    String MD5info = request.getParameter("MD5info");
    String MerRemark  = request.getParameter("MerRemark"); 
    String MD5key = SqPayUtil.getMD5key();
    //相关校验,来源
    logger.debug("sqpay.payNotify RemoteAddr:"+request.getRemoteAddr());
    paramAll.append("_RemoteAddr:"+request.getRemoteAddr()+";");
		//2.交互
    sqpayService.payNotify(MerNo,BillNo,Amount,Succeed,Result,MD5info,MerRemark,MD5key,paramAll.toString(),flowNo);
		//3.结果处理
	}
	
	/**
	 * 银行没有回调信息时，默认的错误页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/errorReturn.html")
	public ModelAndView errorReturn(HttpServletRequest request){
		boolean isSuccess = false;
		String resDesc = "交易处理中…";
		BigInteger orderId = ParamUtils.getBigInteger(request, "orderId", null);
		if(orderId!=null){
			boolean orderIsPay = sqpayService.qryOrderIsPay(orderId);
			if(orderIsPay){
				orderIsPay = true;
				resDesc = "支付成功";
			}
		}
		request.setAttribute("status", isSuccess?"0":"1");//0成功,1失败
   	request.setAttribute("resDesc", resDesc);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/sqpay/errorReturn");
		return mav;
	}
	
	/**
	 * 支付结果页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/payReturn.html")
	public ModelAndView payReturn(HttpServletRequest request){
		//1.搜集参数
		logger.debug("sqpay.payReturn RemoteAddr:"+request.getRemoteAddr());
		boolean isSuccess = false;
    String resDesc = "支付失败";
		try {
			String CharacterEncoding = "UTF-8";
			request.setCharacterEncoding(CharacterEncoding);
			String MerNo = request.getParameter("MerNo"); 
	    String MD5key = SqPayUtil.getMD5key();
	    String BillNo = request.getParameter("BillNo");	
	    String Amount = request.getParameter("Amount");	
	    String Succeed = request.getParameter("Succeed");
	    String Result  = request.getParameter("Result"); 
	    String MD5info = request.getParameter("MD5info");
//	    String MerRemark  = request.getParameter("MerRemark"); 
			//2.交互
	   	String md5str = new SqMD5Util().signMap(new String[]{MerNo,BillNo,Amount,String.valueOf(Succeed)}, MD5key, "RES");
	   	if(MD5info.equals(md5str)){
				if(Succeed.equals("88")){
					isSuccess = true;
					resDesc = "支付成功";
					sqpayService.markOrderIsClientPay(BillNo);
				}else {
					resDesc = Result;
				}
	   	}else{
	   		resDesc = "校验失败";
	   	}
		} catch (UnsupportedEncodingException e) {
			logger.error("sqpay.payReturn.setCharacterEncoding cause UnsupportedEncodingException:", e);
		}catch (Exception e) {
			logger.error("sqpay.payReturn cause Exception:", e);
		}
    
		//3.结果处理
   	request.setAttribute("status", isSuccess?"0":"1");//0成功,1失败
   	request.setAttribute("resDesc", resDesc);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/sqpay/payResult");
		return mav;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.OK)
	@Override
	public Object handleException(Exception ex, HttpServletRequest request) {
			boolean isSuccess = false;
			String resDesc = ExceptionParseUtil.parseErrorMsg(ex,true);
			request.setAttribute("status", isSuccess?"0":"1");//0成功,1失败
	   	request.setAttribute("resDesc", resDesc);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/sqpay/errorReturn");
			return mav;
	}
	
}
