package com.cnfantasia.server.api.payment.web;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.payment.constant.PayConfigConstant;
import com.cnfantasia.server.api.payment.entity.SqPayBtResponse;
import com.cnfantasia.server.api.payment.serviceUntran.ISqpayBtService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.business.pub.exception.ExceptionParseUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * 双乾支付补贴优惠（Version>=335）
 * 
 * @author liyulin
 * @version 1.0 2016年9月20日 上午9:33:22
 */
@Controller
@RequestMapping("sqPayBt")
public class SqPayBtController extends BaseController{
	private final static Log logger = LogFactory.getLog(SqPayBtController.class);
	@Resource
	private ISqpayBtService sqpayBtService;
	@Resource
	private ISysParamManager sysParamManager;
	
	/**
	 * 预支付请求
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/prePayRequest.json")
	@ResponseBody
	public JsonResponse prePayRequestJson(HttpServletRequest request){
		JsonResponse jsonResponse = null;
		BigInteger orderId = null;
		try {
			if(StringUtils.isEmpty(request.getParameter("orderId"))){
				throw new BusinessRuntimeException("Payment.orderId.isEmpty");
			}
			orderId = new BigInteger(request.getParameter("orderId"));
			String merNo = sysParamManager.getSysParaValue(SysParamKey.SQ_BT_MER_NO);
//			SqPayBtPrePayDto sqPayBtPrePayDto = sqpayBtService.prePayRequestHandler(orderId, SqPayUtil.getMerNo(), request);
			
//			Map<String, Object> prePayMap = MapConverter.toMap(sqPayBtPrePayDto);
			jsonResponse = sqpayBtService.prePayRequestHandler(orderId, merNo, request);
		} catch (Exception ex) {
			logger.debug("SqPayBtController prePayRequestJson cause exception,orderId is:" + orderId, ex);
			
			String resDesc = ExceptionParseUtil.parseErrorMsg(ex,true);
			jsonResponse = new JsonResponse();
			jsonResponse.setMessage(resDesc);
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		}
		
		return jsonResponse;
	}
	
	/**
	 * 支付回调
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/payNotify.json")
	@ResponseBody
	public String payNotifyJson(HttpServletRequest request){
		//1.搜集参数
		try {
			request.setCharacterEncoding(PayConfigConstant.CHARTSET_UTF_8);
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
	        paramAll.append(";RemoteAddr:"+request.getRemoteAddr()+";");
	        logger.info("SqPayBtController.payNotifyJson paramAll:["+paramAll+"]");
	    }
	    
	    String Sign     = request.getParameter("Sign"); 
	    String dataInfo = request.getParameter("dataInfo");
	    Map<String, String> dataMap = JSON.parseObject(dataInfo, Map.class);
	    
	    String UserNo         = dataMap.get("UserNo");
	    String MerNo          = dataMap.get("MerNo"); 
	    String BillNo         = dataMap.get("BillNo");	
	    String Amount         = dataMap.get("Amount");	
	    String PayAmount      = dataMap.get("PayAmount");
	    String DiscountAmount = dataMap.get("DiscountAmount"); 
	    String OrderState     = dataMap.get("OrderState");
	    String StateExplain   = dataMap.get("StateExplain"); 
	    String Remark         = dataMap.get("Remark"); 
	    
		//2.交互
	    SqPayBtResponse sqPayBtRequest = new SqPayBtResponse();
	    sqPayBtRequest.setUserId(UserNo);
	    sqPayBtRequest.setMerNo(MerNo);
	    sqPayBtRequest.setBillNo(BillNo);
	    sqPayBtRequest.setAmount(Amount);
	    sqPayBtRequest.setPayAmount(PayAmount);
	    sqPayBtRequest.setDiscountAmount(DiscountAmount);
	    sqPayBtRequest.setOrderState(OrderState);
	    sqPayBtRequest.setStateExplain(StateExplain);
	    sqPayBtRequest.setRemark(Remark);
	    sqPayBtRequest.setSign(Sign);
	    //3.结果处理
	    sqpayBtService.payNotify(sqPayBtRequest);
	    
	    return "success";// 表示已经回调，通知双乾
	}
	
	/**
	 * 支付回调
	 * @param request
	 */
	@RequestMapping("/payNotifyTest.json")
	@ResponseBody
	public String payNotifyJsonTest(String orderNo,String amtBt,String orderState,HttpServletRequest request){
		String url = request.getRequestURL().toString();
		if(!url.contains("api.jiefangqu.com") 
				&& (url.startsWith("http://127.0.0.1") || url.startsWith("http://localhost") || url.startsWith("http://api.linlile"))) {
			//2.交互
		    SqPayBtResponse sqPayBtRequest = new SqPayBtResponse();
		    sqPayBtRequest.setBillNo(orderNo);
		    sqPayBtRequest.setDiscountAmount(amtBt);
		    sqPayBtRequest.setOrderState(orderState);
		    //3.结果处理
		    sqpayBtService.payNotifyTest(sqPayBtRequest);
		    return "success";
		}
	    
	    return "forbidden";
	}
	
}
