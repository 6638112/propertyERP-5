package com.cnfantasia.server.api.commSysPara.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commonBusiness.service.ICommonLoginService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.json.JsonResponse;


@Controller
@RequestMapping("/commSysPara")
public class CommSysParaController extends BaseController{
	/*private ISysParamService commSysParaService;
	public void setCommSysParaService(ISysParamService commSysParaService) {
		this.commSysParaService = commSysParaService;
	}*/
	
	private ICommonLoginService commonLoginService;
	public void setCommonLoginService(ICommonLoginService commonLoginService) {
		this.commonLoginService = commonLoginService;
	}

	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	/**
	 * 刷新系统参数
	 */
	@RequestMapping("/refreshSysPara.json")
	@ResponseBody
	public JsonResponse refreshSysPara(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		boolean res = commonLoginService.verifyAccount(request);
		if(res){
			sysParamManager.updSysParaValue();
			jsonResponse.putData("result", "ok");
		}
		//3.结果处理
		return jsonResponse;
	}
}
