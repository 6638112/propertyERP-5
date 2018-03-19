/**   
* Filename:    TaobaoController.java   
* @version:    1.0  
* Create at:   2014年5月21日 上午3:58:19   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月21日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.callback.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Filename:    TaobaoController.java
 * @version:    1.0.0
 * Create at:   2014年5月21日 上午3:58:19
 * Description: 淘宝登陆回调
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月21日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/callback/opentb")
public class TaobaoController {
	//http://202.170.133.211:8080/callback/opentb/login.html
	
	private Log logger = LogFactory.getLog(getClass());
	@RequestMapping("/login.html")
	@ResponseBody
	public void login(HttpServletRequest request){
		logger.info("queryString:"+request.getQueryString());
		logger.info("getRequestURI:"+request.getRequestURI());
		{
			Enumeration<?> enumerParams = request.getParameterNames();
			while(enumerParams.hasMoreElements()){
				Object paramKey = enumerParams.nextElement();
				logger.info("Taobao test param:"+paramKey+"==>"+request.getParameter(paramKey.toString()));
			}
		}
		{
			Enumeration<?> enumerAttr = request.getAttributeNames();
			while(enumerAttr.hasMoreElements()){
				Object paramKey = enumerAttr.nextElement();
				logger.info("Taobao test attr:"+paramKey+"==>"+request.getAttribute(paramKey.toString()));
			}
		}
		
	}
}
