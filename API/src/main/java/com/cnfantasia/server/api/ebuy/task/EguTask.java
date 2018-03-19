package com.cnfantasia.server.api.ebuy.task;

import java.io.IOException;
import java.math.BigInteger;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.ebuy.domain.JsonEgu;
import com.cnfantasia.server.api.ebuy.entity.PushEguOrderEntity;
import com.cnfantasia.server.api.ebuy.service.IEguService;
import com.cnfantasia.server.api.payment.constant.EbuyConstant;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.SignatureUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.service.IEbuyDeliveryOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.entity.EbuyDeliveryPushRecorder;
import com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.service.IEbuyDeliveryPushRecorderBaseService;

public class EguTask {
	private Log logger = LogFactory.getLog(getClass());
	@Resource
	private IEguService eguService;
	
	@Resource
	private IEbuyDeliveryPushRecorderBaseService ebuydeliveryPushRecorderService;
	
	@Resource
	private IUuidManager uuidManager;
	
	@Resource
	private IEbuyDeliveryOrderBaseService deliveryOrderService;
	
	String eguTradeCode = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.egu_order_code);
	
	//依谷网提供的key
	String key = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.egu_order_key);
//	private final static String key = "www.egu365.com";
	
	public Object eguOrderPush(){
		pushEguOrder();
		return null;
	}
	
	private void pushEguOrder(){
		String eguOrdercontent = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.egu_order_push_url);
		List<PushEguOrderEntity> reslist = eguService.updatePushEguOrder();
		if(reslist==null || reslist.size()==0){
			return;
		}
		execTask(reslist,eguOrdercontent);
	}
	
	private String pushOrder2egu(String OrderPushURL,PushEguOrderEntity pushOrder) {
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建参数队列 
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair("trade_code", eguTradeCode));
		formparams.add(new BasicNameValuePair("order_id", pushOrder.getEdo_id().toString()));
		formparams.add(new BasicNameValuePair("member_name", pushOrder.getMember_name()));
		formparams.add(new BasicNameValuePair("member_mobile", pushOrder.getMember_mobile()));
		formparams.add(new BasicNameValuePair("goods", JSONObject.toJSONString(pushOrder.getGoods())));
		formparams.add(new BasicNameValuePair("area", pushOrder.getArea()));
		formparams.add(new BasicNameValuePair("address", pushOrder.getAddress()));
		formparams.add(new BasicNameValuePair("remark", pushOrder.getRemark()));
		//生成signature
		String signature = getSignature(pushOrder);
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
			logger.info("response from egu is: " + responseString);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			//status:-2为自己指定
			responseString = "{\"status\":\"-2\",\"errorMsg\":\"推送过程有异常，订单推送失败\"}";
		} finally {
			try {
				client.close();
				if(hcResponse!=null){
					hcResponse.close();
				}
			} catch (IOException e) {
				logger.info(e.getMessage(), e);
				responseString = "{\"status\":\"-2\",\"errorMsg\":\"推送过程有异常，订单推送失败\"}";
			}
		}
		return responseString;
	}
	public synchronized void execTask(List<PushEguOrderEntity> reslist,String OrderPushURL) {
		logger.debug("EguTask start:" + DateUtils.getCurrentDate());
		List<EbuyDeliveryPushRecorder> pushlist = new ArrayList<EbuyDeliveryPushRecorder>();
		List<EbuyDeliveryOrder> orderList = new ArrayList<EbuyDeliveryOrder>();
		for(int i=0;i<reslist.size();i++) {
			if(reslist.get(i)==null){
				return;
			}
			//推送记录表添加数据
			EbuyDeliveryPushRecorder pushRecorder= new EbuyDeliveryPushRecorder();
			EbuyDeliveryOrder ebuyOrder = new EbuyDeliveryOrder();
			ebuyOrder.setId(reslist.get(i).getEdo_id());
			BigInteger pushId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_delivery_push_recorder);
			String responseString= pushOrder2egu(OrderPushURL,reslist.get(i));
			//解析返回数据
			try{
				JsonEgu responseJson = JSON.parseObject(responseString,JsonEgu.class);
				if(responseJson.getStatus().equals("0")){
					pushRecorder.setIspushSuccess(1);//推送成功
					pushRecorder.setPushEguorderNo(responseJson.getEguOrder());
					//status为-1为依谷网指定，直接读取errorMsg
					ebuyOrder.setPushStatus(EbuyDict.DeliveryOrderpush_Status.TuiSongChengGong);//配送单标记为已推送
				}else{
					pushRecorder.setIspushSuccess(0);//推送失败
					ebuyOrder.setPushStatus(EbuyDict.DeliveryOrderpush_Status.NotStart);
				}
			} catch (Exception e) {
				logger.info(e.getMessage(), e);
				pushRecorder.setIspushSuccess(0);//推送失败
				responseString = "{\"status\":\"-2\",\"errorMsg\":\"返回数据解析错误，订单推送失败\"}";
				ebuyOrder.setPushStatus(EbuyDict.DeliveryOrderpush_Status.NotStart);
			}
			pushRecorder.setId(pushId);
			pushRecorder.setPushContent(JSONObject.toJSONString(reslist.get(i)));
			pushRecorder.setPushResult(responseString);
			pushRecorder.settEbuySupplyMerchantFId(EbuyConstant.EbuySupplyMerchant_Id.Spl_Merchant_EGU_Id);
			pushRecorder.settEbuyDeliveryOrderFId(reslist.get(i).getEdo_id());
			pushRecorder.setSys0DelState(0);
			pushRecorder.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
			pushlist.add(pushRecorder);
			orderList.add(ebuyOrder);
		}
		ebuydeliveryPushRecorderService.insertEbuyDeliveryPushRecorderBatch(pushlist);
		deliveryOrderService.updateEbuyDeliveryOrderBatch(orderList);
		logger.debug("EguTask end:" + DateUtils.getCurrentDate() + ";size:" + pushlist.size());
	}
	
	private String getSignature(PushEguOrderEntity pushOrder){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("order_id", pushOrder.getEdo_id());
		paramMap.put("member_name", pushOrder.getMember_name());
		paramMap.put("member_mobile", pushOrder.getMember_mobile());
		paramMap.put("goods", JSONObject.toJSONString(pushOrder.getGoods()));
		paramMap.put("area", pushOrder.getArea());
		paramMap.put("address", pushOrder.getAddress());
		paramMap.put("remark", pushOrder.getRemark());
		paramMap.put("trade_code", eguTradeCode);
		String signature = SignatureUtil.getSign(paramMap,key);
		return signature;
	}
	
}
