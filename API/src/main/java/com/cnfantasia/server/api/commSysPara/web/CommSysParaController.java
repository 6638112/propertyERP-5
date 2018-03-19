package com.cnfantasia.server.api.commSysPara.web;

import com.cnfantasia.server.api.commSysPara.entity.CompanyInfoConfig;
import com.cnfantasia.server.api.commSysPara.parser.CompanyInfoParamParser;
import com.cnfantasia.server.api.commonBusiness.service.ICommonLoginService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.json.JsonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


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

    @Resource
    private CompanyInfoParamParser companyInfoParamParser;

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

	/**
	 * 获取客服电话
	 * @param type 1 400电话 2 普通电话
	 * @return
     */
	@RequestMapping(value = "/getServePhone.json")
	@ResponseBody
	public JsonResponse getServePhone(String type) {
		JsonResponse json = new JsonResponse();
		String servePhone = null;
        CompanyInfoConfig companyInfoConfig = companyInfoParamParser.parseParamValue();
        if (type == null || "1".equals(type)) {
			servePhone = companyInfoConfig.getServiceTel();
		} else {
			servePhone = companyInfoConfig.getTel();
		}
		json.putData("servePhone", servePhone);
		return json;
	}
}
