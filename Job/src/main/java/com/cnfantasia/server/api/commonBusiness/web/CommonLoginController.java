/**   
* Filename:    CommonLoginController.java   
* @version:    1.0  
* Create at:   2014年9月1日 上午10:18:28   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnfantasia.server.api.pub.BaseController;

/**
 * Filename:    CommonLoginController.java
 * @version:    1.0.0
 * Create at:   2014年9月1日 上午10:18:28
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月1日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/commonLogin")
public class CommonLoginController extends BaseController{
//	private ICommonLoginService commonLoginService;
//	public void setCommonLoginService(ICommonLoginService commonLoginService) {
//		this.commonLoginService = commonLoginService;
//	}


//	@RequestMapping("/simpleLogin.json")
//	public JsonResponse simpleLogin(HttpServletRequest request){
//		JsonResponse jsonResponse = new JsonResponse();
//		//1.搜集参数
//		//2.交互
//		boolean res = commonLoginService.verifyAccount(request);
//		if(res){
//			jsonResponse.putData("result", "ok");
//		}
//		//3.结果处理
//		return jsonResponse;
//	}
	
}
