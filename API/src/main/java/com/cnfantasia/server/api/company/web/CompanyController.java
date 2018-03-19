/**   
* Filename:    CompanyController.java   
* @version:    1.0  
* Create at:   2014年6月14日 上午7:38:28   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月14日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.company.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commSysPara.entity.CompanyInfoConfig;
import com.cnfantasia.server.api.company.service.ICompanyService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.common.json.JsonResponse;

/**
 * Filename:    CompanyController.java
 * @version:    1.0.0
 * Create at:   2014年6月14日 上午7:38:28
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月14日       shiyl             1.0             1.0 Version
 */
@RequestMapping("/company")
@Controller
public class CompanyController extends BaseController{
	
	private ICompanyService companyService;
	public void setCompanyService(ICompanyService companyService) {
		this.companyService = companyService;
	}

	@ResponseBody
	@RequestMapping("/getPhoneNum.json")
	public JsonResponse getPhoneNum(){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		CompanyInfoConfig companyInfoConfig = companyService.getCompanyServiceInfo();
		//3.结果处理
		jsonResponse.putData("phone", companyInfoConfig.getTel());
		return jsonResponse;
	}
	
	@ResponseBody
	@RequestMapping("/getJfqQrcode.json")
	public JsonResponse getJfqQrcode(){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		String jfqQrcodeUrl = companyService.getJfqQrcodeUrl();
		//3.结果处理
		jsonResponse.putData("jfqQrcodeUrl",jfqQrcodeUrl);
		return jsonResponse;
	}
	
}
