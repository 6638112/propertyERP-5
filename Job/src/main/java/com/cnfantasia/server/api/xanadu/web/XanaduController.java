/**   
* Filename:    XanaduController.java   
* @version:    1.0  
* Create at:   2014年11月27日 上午2:16:24   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月27日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.xanadu.web;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.xanadu.service.IXanaduService;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    XanaduController.java
 * @version:    1.0.0
 * Create at:   2014年11月27日 上午2:16:24
 * Description: 世外桃源状态
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月27日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/xanadu")
public class XanaduController extends BaseController{
	
	private IXanaduService xanaduService;
	public void setXanaduService(IXanaduService xanaduService) {
		this.xanaduService = xanaduService;
	}

	@RequestMapping("/qryCurrStatus.json")
	@ResponseBody
	public JsonResponse qryCurrStatus(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		Boolean status = xanaduService.getCurrStatus(userId);
		//3.结果处理
		jsonResponse.putData("status", status);
		return jsonResponse;
	}
	
	@RequestMapping("/changeCurrStatus.json")
	@ResponseBody
	public JsonResponse changeCurrStatus(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String statusStr = request.getParameter("status");
		if(StringUtils.isEmpty(statusStr)){
			throw new BusinessRuntimeException("xanadu.changeXanaduStatus.paramStatus.empty");
		}
		Boolean statusReq = Boolean.valueOf(request.getParameter("status"));
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		Boolean status = xanaduService.changeXanaduStatus(userId,statusReq);
		//3.结果处理
		jsonResponse.putData("status", status);
		return jsonResponse;
	}
	
}
