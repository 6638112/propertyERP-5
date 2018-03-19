package com.cnfantasia.server.api.access.task;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.access.constant.AccessConstant;
import com.cnfantasia.server.api.access.entity.CarNumPayLogDetail;
import com.cnfantasia.server.api.access.service.IAccessService;
import com.cnfantasia.server.api.common.constant.Lock;
import com.cnfantasia.server.api.common.service.ICommonLockService;
import com.cnfantasia.server.business.pub.utils.SignatureUtil;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.carGroupBuilding.entity.CarGroupBuilding;
import com.cnfantasia.server.domainbase.carGroupBuilding.service.ICarGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumList.service.ICarNumListBaseService;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;
import com.cnfantasia.server.domainbase.carNumPayLog.service.ICarNumPayLogBaseService;

public class YHSPushPayLogTask {
	private Log logger = LogFactory.getLog(getClass());
	
	private final static String key = "www.jiefangqu.com";
	
	@Resource
	private ICarGroupBuildingBaseService carGroupBuildingBaseService;
	
	@Resource
	private ICarNumPayLogBaseService carNumPayLogBaseService;
	
	@Resource
	private ICarNumListBaseService carNumListBaseService;
	
	@Resource
	private IAccessService accessService;
	
	@Resource
	private ICommonLockService commonLockService;
	
	public Object pushPayLog() throws Exception{
		pushPayMsg();
		return null;
	}
	
	private void pushPayMsg(){
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		List<CarGroupBuilding> bCarMsgList = carGroupBuildingBaseService.getCarGroupBuildingByCondition(null);
		List<CarNumPayLogDetail> pushList = accessService.updateCarNumPayLog();
		if(pushList==null || pushList.size()==0){
			return;
		}
		execTask(pushList);
	}
	
	private void execTask(List<CarNumPayLogDetail> pushList){
		for(CarNumPayLogDetail pushOrder:pushList){
			if(pushOrder==null){
				return;
			}
			pushreslut(pushOrder.getPushUrl(),pushOrder.getTradeCode(),pushOrder);
		}
		
	}
	
	private String getSignature(CarNumPayLog pushOrder,String trade_code,CarNumList carmsg,String expireDate){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("trade_code", trade_code);
		paramMap.put("pay_time", pushOrder.getPayTime());
		if(carmsg.getStatus()==0){
			paramMap.put("pay_fee", PriceUtil.div100(pushOrder.getFee()));
		}else{
			paramMap.put("pay_num", pushOrder.getPayNum());
		}
		paramMap.put("car_num",	carmsg.gettCarNum());
		paramMap.put("car_lock", carmsg.getLockStatus());
		paramMap.put("pay_valid_date", carmsg.getExpireDate());
		String signature = SignatureUtil.getSign(paramMap,key);
		return signature;
	}
	
	private String pushOrder2YHS(String OrderPushURL,String trade_code,CarNumPayLog pushOrder){
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建参数队列 
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair("trade_code", trade_code));
		formparams.add(new BasicNameValuePair("pay_time", pushOrder.getPayTime()));
		CarNumList carmsg = carNumListBaseService.getCarNumListBySeqId(pushOrder.gettCarNumId());
		if(carmsg.getStatus()==0){
			formparams.add(new BasicNameValuePair("pay_fee", PriceUtil.div100(pushOrder.getFee()).toString()));	
		}else{
			formparams.add(new BasicNameValuePair("pay_num", pushOrder.getPayNum().toString()));
		}
		formparams.add(new BasicNameValuePair("car_lock", carmsg.getLockStatus().toString()));
		formparams.add(new BasicNameValuePair("car_num", carmsg.gettCarNum()));//测试
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String expireDate="";
		try {
			expireDate = formatter.format(formatter.parse(carmsg.getExpireDate()));
			formparams.add(new BasicNameValuePair("pay_valid_date", expireDate));
		} catch (ParseException e1) {
			logger.info("YHSPushPayLogTask ExpireDate ParseException");
			logger.info(e1.getMessage(), e1);
		}
		//生成signature
		String signature = getSignature(pushOrder,trade_code,carmsg,expireDate);
		formparams.add(new BasicNameValuePair("signature", signature));
		HttpPost httpPost = new HttpPost(OrderPushURL);
		HttpEntity entity;
		String responseString = null;
		CloseableHttpResponse hcResponse = null;
		try {
			entity = new UrlEncodedFormEntity(formparams, HTTP.UTF_8);
			httpPost.setEntity(entity);
			hcResponse = client.execute(httpPost);
			HttpEntity responseEntity = hcResponse.getEntity();
			responseString = EntityUtils.toString(responseEntity, HTTP.UTF_8);
			logger.info("response from YHS is: " + responseString);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			responseString = "{\"status\":\"0002\",\"message\":\"推送过程有异常，记录推送失败\"}";
		} finally {
			try {
				client.close();
				if(hcResponse!=null){
					hcResponse.close();
				}
			} catch (IOException e) {
				logger.info(e.getMessage(), e);
				responseString = "{\"status\":\"0002\",\"message\":\"推送过程有异常，记录推送失败\"}";
			}
		}
		return responseString;
	}
	
	private void pushreslut(String pushUrl,String trade_code,CarNumPayLogDetail pushOrder){
		String responseString = pushOrder2YHS(pushUrl,trade_code,pushOrder);
		try {
			int endIndex = responseString.lastIndexOf("\"");
			int startIndex = 0;
			for (int i = endIndex - 1; i >= 0; i--) {

				if (("\"").equals(String.valueOf(responseString.charAt(i)))) {
					startIndex = i;
					break;
				}
			}
			String pushreslut = responseString.substring(startIndex + 1, endIndex);
			if (pushreslut.equals("1")) {
				pushOrder.setPushStatus(AccessConstant.push_status_suc);
				carNumPayLogBaseService.updateCarNumPayLog(pushOrder);
			}else{
				pushOrder.setPushStatus(AccessConstant.push_status_not);
				carNumPayLogBaseService.updateCarNumPayLog(pushOrder);
			} 
		} catch (Exception e) {
			pushOrder.setPushStatus(AccessConstant.push_status_not);
			carNumPayLogBaseService.updateCarNumPayLog(pushOrder);
		}
	}
}
