package com.cnfantasia.server.api.ebuy.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.cnfantasia.server.api.ebuy.entity.EbuyFlowRecharge;
import com.cnfantasia.server.api.ebuy.service.IEbuyFlowRechargeService;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.HttpUtil;

public class FlowRechargePool {
	
	protected final static Logger LOG = Logger.getLogger(FlowRechargePool.class);
	
	private static IEbuyFlowRechargeService ebuyFlowRechargeService;
	
	private static ISysParamManager sysParamManager;
	
	private static String URL;// = sysParamManager.getSysParaValue("rechargeUrl");
	
	private static String APP_ID;// = sysParamManager.getSysParaValue("rechargeAppId");
	
	private static String SECERT_KEY;//  = sysParamManager.getSysParaValue("rechargeSecertKey");
	
	private static String APP_ID_HF;// = sysParamManager.getSysParaValue("rechargeAppId");
	
	private static String SECERT_KEY_HF;//  = sysParamManager.getSysParaValue("rechargeSecertKey");
	
	private static Map<String, Integer> orderIdMap = new HashMap<String, Integer>();
	
//	static{
//		URL = sysParamManager.getSysParaValue("rechargeUrl");
//		APP_ID = sysParamManager.getSysParaValue("rechargeAppId");
//		SECERT_KEY  = sysParamManager.getSysParaValue("rechargeSecertKey");
//		new Thread(new FlowRechargeWorker(ebuyFlowRechargeService)).start();
//	}
	
	public void init() {
		URL = sysParamManager.getSysParaValue("rechargeUrl");
		APP_ID = sysParamManager.getSysParaValue("rechargeAppId");
		SECERT_KEY  = sysParamManager.getSysParaValue("rechargeSecertKey");
		APP_ID_HF = sysParamManager.getSysParaValue("rechargeAppIdHf");
		SECERT_KEY_HF  = sysParamManager.getSysParaValue("rechargeSecertKeyHf");
		if(StringUtils.isEmpty(APP_ID_HF) || StringUtils.isEmpty(SECERT_KEY_HF)) {
			APP_ID_HF = "cshhf_api";
			SECERT_KEY_HF = "b18313e4ff3746dd99fca47b1bcc736d";
		}
//		List<String> orderList = ebuyFlowRechargeService.getOrderIdForRecharge();
//		for(String orderId : orderList) {
//			orderIdMap.put(orderId, 0);
//		}
		new Thread(new FlowRechargeWorker(ebuyFlowRechargeService)).start();
	}
	
	public static void addOrderIdForRecharge(String orderId) {
		LOG.debug("FlowRechargeWorker:addOrderIdForRecharge start," + orderId);
//		RedisCacheHandler.hset(CacheConstant.ModelCode.FLOW_RECHARGE, orderId, "0");
		orderIdMap.put(orderId, 0);
		LOG.debug("FlowRechargeWorker:addOrderIdForRecharge end," + orderId);
	}
	
	public void setEbuyFlowRechargeService(
			IEbuyFlowRechargeService ebuyFlowRechargeService) {
		FlowRechargePool.ebuyFlowRechargeService = ebuyFlowRechargeService;
	}

	public void setSysParamManager(ISysParamManager sysParamManager) {
		FlowRechargePool.sysParamManager = sysParamManager;
	}

	/**
	 * 每隔一段时间批量插入
	 * @author weijian_ye
	 *
	 */
	static class FlowRechargeWorker implements Runnable {
		protected final static Logger logger = Logger.getLogger(FlowRechargeWorker.class);
		
		private IEbuyFlowRechargeService ebuyFlowRechargeService;
		
		public FlowRechargeWorker(IEbuyFlowRechargeService ebuyFlowRechargeService) {
			this.ebuyFlowRechargeService = ebuyFlowRechargeService;
		}

		public void run() {
			//redis存放<key, Map<orderId, value>>;
			//key 为CacheConstant.ModelCode.FLOW_RECHARGE， value为调用第三方充值接口的次数，当调用10次仍失败，不再为此订单充值
//			sleep(60000);
			logger.debug("FlowRechargeWorker start run!");
			while (true) {
				try {
					logger.debug("FlowRechargeWorker:" + System.currentTimeMillis());
//					Set<String> orderIdKey = RedisCacheHandler.hkeys(CacheConstant.ModelCode.FLOW_RECHARGE);
					Set<String> orderIdKey = orderIdMap.keySet();
					logger.debug("FlowRechargeWorker:" + System.currentTimeMillis() + "," + orderIdKey.size());
					while(orderIdKey.size() == 0){	//调用size比较多，用LinkedBlockingQueue的size无需对队列加锁
						Thread.sleep(5000);	//5*60*1000
					}
					
					for(String orderId : orderIdKey) {
//						Integer value = Integer.valueOf(RedisCacheHandler.hget(CacheConstant.ModelCode.FLOW_RECHARGE, orderId));
						Integer value = orderIdMap.get(orderId);
						logger.debug("FlowRechargeWorker:" + orderId + ",value:" + value);
						
						if(value < 10) {
							Map<String, Object> paramMap = new HashMap<String, Object>();
							paramMap.put("orderId", orderId);
							EbuyFlowRecharge flowRecharge = ebuyFlowRechargeService.getFlowRecharge(paramMap);
							if(flowRecharge == null) {
								Thread.sleep(500*value);
								value ++;
//								RedisCacheHandler.hset(CacheConstant.ModelCode.FLOW_RECHARGE, orderId, value.toString());
								orderIdMap.put(orderId, value);
//								RedisCacheHandler.hdel(CacheConstant.ModelCode.FLOW_RECHARGE, orderId);
							} else {
								try {
									JSONObject retValue = rechargeFlow(flowRecharge);
									logger.info("FlowRechargeWorker:" + retValue);
									
									//调用充值成功
									if("00".equals(retValue.getJSONObject("MSGBODY").getJSONObject("RESP").getString("RCODE"))) {
//										RedisCacheHandler.hdel(CacheConstant.ModelCode.FLOW_RECHARGE, orderId);
										orderIdMap.remove(orderId);
										
										flowRecharge.setStatus(1);
										flowRecharge.setRetOrderId(retValue.getJSONObject("MSGBODY").getJSONObject("CONTENT").getString("ORDERID"));
										flowRecharge.setCallBackResult(retValue.toString());
										ebuyFlowRechargeService.updateFlowRecharge(flowRecharge);
									} else { //调用充值失败
//										value ++;
//										RedisCacheHandler.hset(CacheConstant.ModelCode.FLOW_RECHARGE, orderId, value.toString());
										flowRecharge.setStatus(2);
										flowRecharge.setOrderId(Long.valueOf(orderId));
										flowRecharge.setCallBackResult(retValue.toString());
										ebuyFlowRechargeService.updateFlowRecharge(flowRecharge);
//										RedisCacheHandler.hdel(CacheConstant.ModelCode.FLOW_RECHARGE, orderId);
										orderIdMap.remove(orderId);
									}
								} catch (Exception e) {
									//调用充值异常，主要是因为第三方充值系统故障。所以等待一分钟后继续调用充值
									logger.error("recharge failed:可能运营商系统问题，等待20秒后再次充值！");
									logger.error(e.getMessage(), e);
									value ++;
//									RedisCacheHandler.hset(CacheConstant.ModelCode.FLOW_RECHARGE, orderId, value.toString());
									orderIdMap.put(orderId, value);
									Thread.sleep(5000*value);
								}
							}
						} else {
							EbuyFlowRecharge flowRecharge = new EbuyFlowRecharge();
							flowRecharge.setOrderId(Long.valueOf(orderId));
							flowRecharge.setStatus(2);
							ebuyFlowRechargeService.updateFlowRecharge(flowRecharge);
//							RedisCacheHandler.hdel(CacheConstant.ModelCode.FLOW_RECHARGE, orderId);
							orderIdMap.remove(orderId);
						}
					}
				} catch (Exception ex) {
					logger.error("recharge failed!!!", ex);
					sleep(1000);
				}
			}

		}
		
		private JSONObject rechargeFlow(EbuyFlowRecharge flowRecharge) throws HttpException, IOException {
			String orderId = flowRecharge.getOrderId().toString();
			String dateStr = DateUtils.getCurrentDateStr("yyyyMMddHHmmssSS");
			String myAppId = APP_ID;
			String mySecretKey = SECERT_KEY;
			if(flowRecharge.getOrderType().equals("3")) { //话费
				myAppId = APP_ID_HF;
				mySecretKey = SECERT_KEY_HF;
			}
			
			String secertkey = dateStr + flowRecharge.getOrderId() + myAppId + mySecretKey;
			String sign = mySecretKey + flowRecharge.getPhone() + flowRecharge.getFlow() + flowRecharge.getOrderType();
			
			Map<String, Object> param = new HashMap<String, Object>();
			Map<String, Object> header = new HashMap<String, Object>();
			param.put("HEADER", header);
			header.put("VERSION", "V1.0");
			header.put("TIMESTAMP", dateStr);
			header.put("SEQNO", orderId);
			header.put("APPID", myAppId);
			header.put("SECERTKEY", Md5Util.MD5(secertkey));
			
			Map<String, Object> msgbody = new HashMap<String, Object>();
			Map<String, Object> content = new HashMap<String, Object>();
			param.put("MSGBODY", msgbody);
			msgbody.put("CONTENT", content);
			
			content.put("USER", flowRecharge.getPhone());
			content.put("PACKAGEID", flowRecharge.getFlow());
			content.put("ORDERTYPE", flowRecharge.getOrderType());
			content.put("EXTORDER", orderId);
			content.put("SIGN", Md5Util.MD5(sign));
			JSONObject reqParam = new JSONObject(param);
			JSONObject returnV = HttpUtil.post(URL, reqParam);
			
			logger.info("reqParam:" + reqParam.toString());
			logger.debug("returnStr:" + returnV.toString());
			
			return returnV;
		}
		
		private void sleep(long time) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
			}
		}

	}
}
