package com.cnfantasia.server.ms.commSysPara.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.ms.pub.BaseController;

@Controller
@RequestMapping("/commSysPara")
public class CommSysParaController extends BaseController{
	/**
     * 刷新参数
     * @param request
     * @return
     */
    @RequestMapping("/refreshSysPara.json")
    @ResponseBody
    public JsonResponse refreshSysPara(HttpServletRequest request) {
    	JsonResponse jsonResponse = new JsonResponse();
    	
    	ISysParamManager sysParamManager = (ISysParamManager) CnfantasiaCommbusiUtil.getBeanManager("sysParamManager");
    	sysParamManager.updSysParaValue();
    	jsonResponse.putData("result", "ok");
    	return jsonResponse;
    }	
}
