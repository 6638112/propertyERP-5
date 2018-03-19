/**   
* Filename:    TmpValicodeController.java   
* @version:    1.0  
* Create at:   2014年7月17日 上午7:11:07   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月17日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.tmpValicode.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.common.json.JsonResponse;

/**
 * Filename:    TmpValicodeController.java
 * @version:    1.0.0
 * Create at:   2014年7月17日 上午7:11:07
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年7月17日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/tmpValicode")
public class TmpValicodeController extends BaseController{
//	private Log logger = LogFactory.getLog(getClass());
//	private IEbuyOrderBaseDao ebuyOrderBaseDao;
//	public void setEbuyOrderBaseDao(IEbuyOrderBaseDao ebuyOrderBaseDao) {
//		this.ebuyOrderBaseDao = ebuyOrderBaseDao;
//	}
//	
//	private ISysParamManager sysParamManager;
//	public void setSysParamManager(ISysParamManager sysParamManager) {
//		this.sysParamManager = sysParamManager;
//	}
	
	
	@RequestMapping("/testPermi2.json")
	@ResponseBody
	public JsonResponse testPermi2(){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		//3.结果处理
		jsonResponse.putData("hi", "rose");
		return jsonResponse;
	}
	
//	@RequestMapping("/testMfsg4562.json")
//	@ResponseBody
//	public JsonResponse testMsg(){
//		BigInteger orderId = new BigInteger("40001");
//		JsonResponse jsonResponse = new JsonResponse();
//		//1.搜集参数
//		//2.交互
//		Boolean isNotifyAdmin = Boolean.valueOf(sysParamManager.getSysParaValue(SysParamKey.PAY_SUCC_NOTIFY_SWITCH));
//		String notifyPhone = sysParamManager.getSysParaValue(SysParamKey.PAY_SUCC_NOTIFY_PHONE);
//		if(isNotifyAdmin){//短信通知管理员
//			try {
//				EbuyOrder orderDetail = ebuyOrderBaseDao.selectEbuyOrderBySeqId(orderId);
//				String msg = "用户["+orderDetail.getBuyerId()+"]于"+orderDetail.getBuyTime()+"购买商品支付成功,总价"+PriceUtil.div100(orderDetail.getAmount())+"元，订单编号为"+orderDetail.getOrderNo()+",请知悉。";
////				msg = "123";
//				List<String> mobiles = new ArrayList<String>();
//				mobiles.add(notifyPhone);
//				FutureTask<String> task = new FutureTask<String>(new SendSmsRunnable(mobiles, msg));
//				new Thread(task).start();
//				logger.debug(msg);
//				logger.info("订单"+orderDetail.getOrderNo()+"支付成功，短信通知管理员的响应 = " + task.get());
//			} catch (Exception e) {
//				logger.error(e);
//			}
//		}
//		//3.结果处理
//		return jsonResponse;
//	}
	
}
