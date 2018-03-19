/**   
* Filename:    CommSysLoggerController.java   
* @version:    1.0  
* Create at:   2014年8月30日 上午7:17:03   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commSysLogger.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commonBusiness.service.ICommonLoginService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.business.commSysLogger.service.ISysLoggerManager;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.commLogger.entity.CommLogger;

/**
 * Filename:    CommSysLoggerController.java
 * @version:    1.0.0
 * Create at:   2014年8月30日 上午7:17:03
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月30日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/commSysLogger")
public class CommSysLoggerController extends BaseController{
	
	private ICommonLoginService commonLoginService;
	public void setCommonLoginService(ICommonLoginService commonLoginService) {
		this.commonLoginService = commonLoginService;
	}
	
	private ISysLoggerManager<CommLogger> sysLoggerManager;
	public void setSysLoggerManager(ISysLoggerManager<CommLogger> sysLoggerManager) {
		this.sysLoggerManager = sysLoggerManager;
	}
	
	/**
	 * 将日志信息及时存入到数据库
	 * @param request
	 * @return
	 */
	@RequestMapping("/freshLoggerCatche.json")
	@ResponseBody
	public JsonResponse freshLoggerCatche(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer lgSize = null;
		//2.交互
		boolean res = commonLoginService.verifyAccount(request);
		if(res){
			sysLoggerManager.synch2DatabaseCurrThread();
			lgSize =  sysLoggerManager.getCatcheLogSize();
			jsonResponse.putData("lgSize",lgSize);
			jsonResponse.putData("result", "ok");
		}
		//3.结果处理
		return jsonResponse;
	}
	
}
