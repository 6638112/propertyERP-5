/**   
* Filename:    EbuyOrderPusherTask.java   
* @version:    1.0  
* Create at:   2015年8月4日 上午11:57:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月4日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuyorder.serviceUn;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.ebuyorder.dao.EbuyOrderPusherDao;
import com.cnfantasia.server.api.ebuyorder.service.EbuyOrderPusherService;
import com.cnfantasia.server.api.ebuyorder.util.HjxPushUtil;
import com.cnfantasia.server.business.pub.entity.Order4HJX;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.dao.IEbuyDeliveryPushRecorderBaseDao;
import com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.entity.EbuyDeliveryPushRecorder;

/**
 * Filename:    EbuyOrderPusherTask.java
 * @version:    1.0.0
 * Create at:   2015年8月4日 上午11:57:27
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月4日       shiyl             1.0             1.0 Version
 */
public class EbuyOrderPusherTask {
	private Log logger = LogFactory.getLog(getClass());
	@Resource
	private EbuyOrderPusherService ebuyOrderPusherService;

	@Resource
	private EbuyOrderPusherDao ebuyOrderPusherDao;
	@Resource
	private IEbuyDeliveryPushRecorderBaseDao ebuyDeliveryPushRecorderBaseDao;
	@Resource
	private ISysParamManager sysParamManager;
	
	/**
	 * 批量推送接口，放到Spring的定时任务里调用，只推送过去24小时内的
	 */
	public void pushOrder4HJXSplMerchat() {
		List<Order4HJX> order4HJXList = ebuyOrderPusherDao.preparOrder4HJXSplMerchat();

		if (order4HJXList.size() == 0)
			return;
		
		StringBuilder strB = new StringBuilder();
		for (Order4HJX orderInfo : order4HJXList) {
			strB.append(orderInfo.getOrderId());
		}
		logger.error(strB.toString());
		

		for (Order4HJX orderInfo : order4HJXList) {
			if (orderInfo == null)
				return;
			/*{//推送给海吉星之前，先查下是否推送成功，若成功则不再推送，避免出现重复推送
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("pushContent", "source_order_tag\":\"" + orderInfo.getSource_order_tag());
				paramMap.put("tEbuyDeliveryOrderFId", orderInfo.getEdoId());
				paramMap.put("ispushSuccess", 1);
				if (ebuyDeliveryPushRecorderBaseDao.selectEbuyDeliveryPushRecorderCount(paramMap, true) > 0)//已成功推送
					return;
			}*/
			
			BigInteger pushRecorderAddId = ebuyOrderPusherService.pushOrder2HJXSignal(orderInfo);
			if(pushRecorderAddId!=null){
				//先设定默认值
				String resStr = "TODO";
				Integer ispushSuccess = 0;//是否推送成功=={"0":"失败","1":"成功 "}
				try {
					String responseString = pushOrder2HJX(JSON.toJSONString(orderInfo));
					logger.info("EbuyOrderPusherService.pushOrder2HJXSignal response string: " + responseString);
					resStr = responseString;
					ispushSuccess = JSON.parseObject(responseString).getIntValue("code") == 0 ? 1 : 0;
				} catch (Exception e) {
					//resStr = e.getMessage();
					ispushSuccess = 0;
					logger.error("EbuyOrderPusherService.pushOrder2HJXSignal failed.", e);
				}
				//更新推送结果
				EbuyDeliveryPushRecorder ebuyDeliveryPushRecorderUpd = new EbuyDeliveryPushRecorder();
				ebuyDeliveryPushRecorderUpd.setId(pushRecorderAddId);
				ebuyDeliveryPushRecorderUpd.setIspushSuccess(ispushSuccess);
				ebuyDeliveryPushRecorderUpd.setPushResult(resStr);
				ebuyDeliveryPushRecorderBaseDao.updateEbuyDeliveryPushRecorder(ebuyDeliveryPushRecorderUpd);
			}
		
		}
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
