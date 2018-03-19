package com.cnfantasia.server.api.ebuyorder.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.dao.CommonEbuyDao;
import com.cnfantasia.server.api.ebuyorder.dao.EbuyOrderPusherDao;
import com.cnfantasia.server.api.ebuyorder.util.HjxPushUtil;
import com.cnfantasia.server.api.payment.constant.EbuyConstant;
import com.cnfantasia.server.business.pub.entity.Order4HJX;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.dao.IEbuyDeliveryPushRecorderBaseDao;
import com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.entity.EbuyDeliveryPushRecorder;

public class EbuyOrderPusherService {
	private Log logger = LogFactory.getLog(getClass());

	@Resource
	private ISysParamManager sysParamManager;

	@Resource
	private IEbuyDeliveryPushRecorderBaseDao ebuyDeliveryPushRecorderBaseDao;

	@Resource
	private IUuidManager uuidManager;

	@Resource
	private EbuyOrderPusherDao ebuyOrderPusherDao;

	@Resource
	private CommonEbuyDao commonEbuyDao;
	
	public EbuyOrderPusherService() {
		logger.info("EbuyOrderPusherService init!");
	}

	
//	private String pushOrder2HJX2(String orderInfoJson) {
//		StringBuilder paramString = new StringBuilder();
//		paramString.append(SysParamKey.HJX_Source_tag + "=" + sysParamManager.getSysParaValue(SysParamKey.HJX_Source_tag) + "|");
//		paramString.append(SysParamKey.HJX_Version + "=" + sysParamManager.getSysParaValue(SysParamKey.HJX_Version) + "|");
//		paramString.append("order=" + orderInfoJson + "|");
//		paramString.append(sysParamManager.getSysParaValue(SysParamKey.HJX_Salt));
//		logger.info("push params is: " + paramString.toString());
//		String signature = Md5Util.MD5(paramString.toString()); //签名
//		
//		HttpUtil httpUtil = new HttpUtil();
//		httpUtil.addParameter(SysParamKey.HJX_Source_tag, sysParamManager.getSysParaValue(SysParamKey.HJX_Source_tag));
//		httpUtil.addParameter(SysParamKey.HJX_Version, sysParamManager.getSysParaValue(SysParamKey.HJX_Version));
//		httpUtil.addParameter("order", orderInfoJson);
//		httpUtil.addParameter(signature, signature);
//		httpUtil.addParameter(SysParamKey.HJX_Source_tag, sysParamManager.getSysParaValue(SysParamKey.HJX_Source_tag));
//		httpUtil.addParameter(SysParamKey.HJX_Source_tag, sysParamManager.getSysParaValue(SysParamKey.HJX_Source_tag));
//		httpUtil.addParameter(SysParamKey.HJX_Source_tag, sysParamManager.getSysParaValue(SysParamKey.HJX_Source_tag));
//		String responseString;
//		try {
//			responseString = httpUtil.post(sysParamManager.getSysParaValue(SysParamKey.OrderPushURL), "UTF-8", 10000);
//		} catch (Exception e) {
//			logger.info(e.getMessage(), e);
//			responseString = "{\"code\":\"-1\",\"description\":\"推送过程有异常，订单推送失败\"}";
//		}
//		
//		return responseString;
//	}

	

	/**
	 * 失败的订单再次推送
	 * 
	 * @param edoId
	 * @return 推送结果
	 */
	public String pushAaginForDeliveryOrder(long edoId, long orderId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tEbuyDeliveryOrderFId", edoId);
		List<EbuyDeliveryPushRecorder> edprList = ebuyDeliveryPushRecorderBaseDao.selectEbuyDeliveryPushRecorderByCondition(paramMap, false);

		BigInteger edprId = null;
		String pushContent = null;
		if (edprList.size() == 0) {
			edprId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_delivery_push_recorder);
			Order4HJX orderInfo = commonEbuyDao.preparOrder4SplMerchat(new BigInteger(orderId + ""), EbuyConstant.EbuySupplyMerchant_Id.Spl_Merchant_HJX_Id);
			pushContent = JSON.toJSONString(orderInfo);
		} else {
			//edprList本身就是order f_id desc的，所以edprList.get(0)就是最后一条推送失败的记录
			EbuyDeliveryPushRecorder edpr = edprList.get(0);
			edprId = edpr.getId();
			pushContent = edpr.getPushContent();
		}

		String responseString = pushOrder2HJX(pushContent);

		EbuyDeliveryPushRecorder ebuyDeliveryPushRecorder = new EbuyDeliveryPushRecorder();
		ebuyDeliveryPushRecorder.setId(edprId);
		ebuyDeliveryPushRecorder.setIspushSuccess(JSON.parseObject(responseString).getIntValue("code") == 0 ? 1 : 0);
		ebuyDeliveryPushRecorder.setPushResult(responseString);
		if (edprList.size() == 0) {//需要新增
			ebuyDeliveryPushRecorder.settEbuyDeliveryOrderFId(new BigInteger(edoId + ""));
			ebuyDeliveryPushRecorder.settEbuySupplyMerchantFId(EbuyConstant.EbuySupplyMerchant_Id.Spl_Merchant_HJX_Id);
			ebuyDeliveryPushRecorder.setPushContent(pushContent);
			ebuyDeliveryPushRecorderBaseDao.insertEbuyDeliveryPushRecorder(ebuyDeliveryPushRecorder);
		} else {//需要更新
			ebuyDeliveryPushRecorderBaseDao.updateEbuyDeliveryPushRecorder(ebuyDeliveryPushRecorder);
		}
		if (JSON.parseObject(responseString).getIntValue("code") == 0)
			return "推送成功";
		else
			return JSON.parseObject(responseString).getString("description");
	}

	
	@Transactional(propagation=Propagation.REQUIRES_NEW,timeout=60)
	public BigInteger pushOrder2HJXSignal(Order4HJX orderInfo){
		logger.error("ThreadId:" + Thread.currentThread().getId() + "---"+ JSON.toJSONString(orderInfo));
		//加锁,锁住配送单
		BigInteger existEdoId = ebuyOrderPusherDao.selectEbuyDeliveryOrderForUpdate(new BigInteger(orderInfo.getEdoId()));
		if(existEdoId==null){return null;}
		
		//预先存入一条推送记录
		EbuyDeliveryPushRecorder ebuyDeliveryPushRecorder = new EbuyDeliveryPushRecorder();
		ebuyDeliveryPushRecorder.setPushContent(JSON.toJSONString(orderInfo));
		ebuyDeliveryPushRecorder.settEbuySupplyMerchantFId(EbuyConstant.EbuySupplyMerchant_Id.Spl_Merchant_HJX_Id);
		ebuyDeliveryPushRecorder.settEbuyDeliveryOrderFId(new BigInteger(orderInfo.getEdoId()));
		{//先设定默认值
			String resStr = "TODO";
			Integer ispushSuccess = 0;//是否推送成功=={"0":"失败","1":"成功 "}
			ebuyDeliveryPushRecorder.setIspushSuccess(ispushSuccess);
			ebuyDeliveryPushRecorder.setPushResult(resStr);
		}
		
		BigInteger pushRecorderAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_delivery_push_recorder);
		ebuyDeliveryPushRecorder.setId(pushRecorderAddId);
		ebuyDeliveryPushRecorderBaseDao.insertEbuyDeliveryPushRecorder(ebuyDeliveryPushRecorder);
		return pushRecorderAddId;
	}
	
	/**
	 * @param pushContent
	 * @return
	 */
	private String pushOrder2HJX(String pushContent) {
		String orderInfoJson = pushContent;
		String HJX_Source_tag = sysParamManager.getSysParaValue(SysParamKey.HJX_Source_tag);
		String HJX_Version = sysParamManager.getSysParaValue(SysParamKey.HJX_Version);
		String HJX_Salt = sysParamManager.getSysParaValue(SysParamKey.HJX_Salt);
		String OrderPushURL = sysParamManager.getSysParaValue(SysParamKey.OrderPushURL);
		return HjxPushUtil.pushOrder2HJX(orderInfoJson, HJX_Source_tag, HJX_Version, HJX_Salt, OrderPushURL);
	}
}
