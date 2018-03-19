package com.cnfantasia.wl.wechat.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.ms.pub.BaseController;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private IHttpClient simpleHttpClient;

	@RequestMapping("/qryPersonInfo.do")
	public ModelAndView qryPersonInfo(HttpServletRequest request) {
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/user/qryPersonInfo.json", null, LoginHelper.prepareReqHeader(request));

		request.setAttribute("jsonResponse", jsonResponse);

		//3.结果处理
		logger.debug(JSON.toJSONString(jsonResponse.getDataValue()));

		return new ModelAndView("/ebuy/userInfo");
	}
	
	@RequestMapping("/updPersonInfo.do")
	@ResponseBody
	public String updPersonInfo(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", request.getParameter("userId"));
		params.put("mobile", request.getParameter("mobile"));
		params.put("reqeustFrom", "wlLightApp");
		params.put("realName", request.getParameter("realName"));
		params.put("imgProfile", request.getParameter("imgProfile"));
		params.put("sex", request.getParameter("sex"));
		params.put("birthday", request.getParameter("birthday"));

		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/user/updPersonInfo.json", params, LoginHelper.prepareReqHeader(request));

		request.setAttribute("jsonResponse", jsonResponse);

		//3.结果处理
		logger.debug(JSON.toJSONString(jsonResponse.getDataValue()));
		return jsonResponse.getStatus().equals("0000") ? "更新成功" : "更新失败";
	}
}
