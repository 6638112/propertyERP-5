package com.cnfantasia.server.api.revenueApplyPush.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.revenueApplyPush.service.RevenueApplyPushService;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;

/**
 * 提款单推送接口 
 * @author wenfq 20160705
 */
@RequestMapping("/revenueApplyPush")
@Controller
public class RevenueApplyPushController extends BaseController {
	
	@Resource
	RevenueApplyPushService revenueApplyPushService;
	
	/**
	 * 供EAS请求，通知报销单或付款单已付款
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/notifySuccessPay.json")
	public JsonResponse notifySuccessPay(HttpServletRequest request) {
//		String salt = ParamUtils.getString(request, "salt"); //盐值
		String easCode = ParamUtils.getString(request, "easCode");//eas单据单号
		String srcCode = ParamUtils.getString(request, "srcCode");//解放区这边的提款单单号
		String payAmount = ParamUtils.getString(request, "payAmount");//实付金额
		
//		if(StringUtils.isEmpty(salt)){
//			throw new ValidateRuntimeException("salt can't be null.");
//		}
		
		if(StringUtils.isEmpty(easCode)){
			throw new ValidateRuntimeException("easCode can't be null.");
		}
		if(StringUtils.isEmpty(srcCode)){
			throw new ValidateRuntimeException("srcCode can't be null.");
		}
		
		if(StringUtils.isEmpty(payAmount)){
			throw new ValidateRuntimeException("payAmount can't be null.");
		}
		
		int updCount = revenueApplyPushService.updateRevenueApplyAfterEASPayed(easCode, srcCode, payAmount);
		
		JsonResponse jsonResponse = new JsonResponse();
		if(updCount > 0){
			jsonResponse.setMessage("成功更新" + updCount + "条数据");
		}else{
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("未更新到数据，请检查参数配置");
		}
		return jsonResponse;
	}
}
