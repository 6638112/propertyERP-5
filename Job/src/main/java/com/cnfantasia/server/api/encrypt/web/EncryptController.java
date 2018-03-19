/**   
* Filename:    EncryptController.java   
* @version:    1.0  
* Create at:   2016年2月19日 下午2:58:50   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2016年2月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.encrypt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.commbusi.encrypt.service.IEncryptService;
import com.cnfantasia.server.common.json.JsonResponse;
import com.taobao.api.request.HotelsSearchRequest;

/**
 * Filename:    EncryptController.java
 * @version:    1.0.0
 * Create at:   2016年2月19日 下午2:58:50
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2016年2月19日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/encrypt")
public class EncryptController extends BaseController{
	
	@Autowired
	private IEncryptService encryptService;
	
	@RequestMapping("/freshCache.json")
	@ResponseBody
	public JsonResponse freshCache(HotelsSearchRequest request){
		encryptService.freshCache();
		
		JsonResponse res = new JsonResponse();
		return res;
	}
	
	@RequestMapping("/encTestAllColumn.json")
	@ResponseBody
	public JsonResponse encTest1(HotelsSearchRequest request){
		JsonResponse res = new JsonResponse();
		return res;
	}
	
	@RequestMapping("/encTestCfgColumn.json")
	@ResponseBody
	public JsonResponse encTestCfgColumn(HotelsSearchRequest request){
		JsonResponse res = new JsonResponse();
		return res;
	}
	
}
