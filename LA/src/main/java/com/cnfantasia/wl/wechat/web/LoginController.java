package com.cnfantasia.wl.wechat.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.pub.entity.Regist3rdResponse;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.annotation.CheckLogin;
import com.cnfantasia.web.filter.BindPhoneFilter;
import com.cnfantasia.wl.wechat.WeChatConstant;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;

/**
 * 轻应用登录相关
 * @author wenfq 2016-04-19
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private IHttpClient simpleHttpClient;
	
	/**
	 * 微信登录API
	 * @param request
	 * @param response
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @author wenfq 2016-04-19
	 */
	@RequestMapping("/regist3rd.do")
	public String personalCenter(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
		String number = ParamUtils.getString(request, "myNumber", null);
		String password = ParamUtils.getString(request, "myPassword", null);
		if(StringUtils.isEmpty(number) || StringUtils.isEmpty(password)) {
			SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
			LoginHelper.loginAPI(simpleHttpClient, request, user);
		} else {
			LoginHelper.login(request, simpleHttpClient);
		}
		
//		LoginHelper.login(request, simpleHttpClient);

		if(!LoginHelper.isLoginUser(request)){//尝试自动登录了一次，仍是登录失败，跳到404
			return "/error/404";
		}
		
		String url = (String) request.getSession().getAttribute(BindPhoneFilter.LAST_REQUEST_SERVLET_PATH);
		request.getSession().setAttribute(BindPhoneFilter.LAST_REQUEST_SERVLET_PATH, null);
		return "redirect:" + url;
	}
	
	
	/**
	 * 绑定手机号
	 * @param request
	 * @param response
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @author wenfq 2016-04-19
	 */
	@RequestMapping("/bindPhone.json")
	@ResponseBody
	public JsonResponse bindPhone(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/user/bindPhone.json", getParameterMap(request), LoginHelper.prepareReqHeader(request));
		logger.info("after bindPhone response is:" + jsonResponse.getDataValue().toString()) ; 
		//因为绑定了手机，API做了自动登录，即jsonResponse已经包含了登录信息，所以要刷新regist3rdResponse
		Regist3rdResponse regist3rdResponse = JSON.parseObject(jsonResponse.getDataValue().toString(), Regist3rdResponse.class);
		logger.info("after bindPhone response is:" + jsonResponse.getDataValue().toString()) ; 
		request.getSession().setAttribute(WeChatConstant.SessionKey, regist3rdResponse.getSessionKey());
		request.getSession().setAttribute("regist3rdResponse", regist3rdResponse);
		return jsonResponse;
	}
}
