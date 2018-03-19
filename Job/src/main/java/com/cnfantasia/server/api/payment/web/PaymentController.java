/**   
* Filename:    PaymentController.java   
* @version:    1.0  
* Create at:   2015年10月16日 上午11:44:10   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年10月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.payment.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.json.JsonResponse;

/**
 * Filename:    PaymentController.java
 * @version:    1.0.0
 * Create at:   2015年10月16日 上午11:44:10
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年10月16日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/payment")
public class PaymentController extends BaseController{
	@Resource
	private ISysParamManager sysParamManager;
	
	/**
	 * 查询支持的支付方式列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/payList.json")
	@ResponseBody
	public JsonResponse payList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		{
			Map<String,Object> payType = new HashMap<String, Object>();
			payType.put("type", "1");
			payType.put("name", "微信支付");
			resList.add(payType);
		}
		{
			Map<String,Object> payType = new HashMap<String, Object>();
			payType.put("type", "2");
			payType.put("name", "支付宝支付");
			resList.add(payType);
		}
		{
			String sqPayName = sysParamManager.getSysParaValue(SysParamKey.SQ_PAY_NAME);
			Map<String,Object> payType = new HashMap<String, Object>();
			payType.put("type", "3");
			payType.put("name", sqPayName);
			resList.add(payType);
		}
		//3.结果处理
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	
}
