/**   
* Filename:    BehaviorLogController.java   
* @version:    1.0  
* Create at:   2015年4月14日 上午7:33:15   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月14日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.logger.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.pub.BaseController;

/**
 * Filename:    BehaviorLogController.java
 * @version:    1.0.0
 * Create at:   2015年4月14日 上午7:33:15
 * Description:记录用户行为的日志处理器
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月14日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/behaviorLog")
public class BehaviorLogController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping("/thirdParty.json")
	public void thirdParty(HttpServletRequest request){
		//1.搜集参数
		String requestParams = JSON.toJSONString(request.getParameterMap());
		logger.debug("BehaviorLogController.thirdParty.param Data is:"+requestParams);
		//2.交互
		//3.结果处理
	}
	
	
}
