package com.cnfantasia.server.ms.login.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.httpcllient.SimpleHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.ms.pub.BaseController;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	
	private IHttpClient simpleHttpClient;
	public void setSimpleHttpClient(SimpleHttpClient simpleHttpClient) {
		this.simpleHttpClient = simpleHttpClient;
	}

	@RequestMapping("/list.html")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/login/list");
		return mav;
	}
	
	@RequestMapping("/testHttpClient.json")
	@ResponseBody
	public JsonResponse testHttpClient(HttpServletRequest request){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("type", 3);
		params.put("page", 1);
		params.put("pageNum", 10);
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/user/qryMsgList.json", params);
		return jsonResponse;
	}
}
