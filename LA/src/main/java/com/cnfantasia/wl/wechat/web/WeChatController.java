package com.cnfantasia.wl.wechat.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.wl.wechat.util.AccessTokenGetter;
 
@Controller
@RequestMapping("/wechat")
public class WeChatController extends BaseController {

	/**
	 * 开发者模式token校验
	 * 
	 * @param wxAccount
	 *            开发者url后缀
	 * @param response
	 * @param tokenModel
	 * @throws ParseException
	 * @throws IOException
	 * @ResponseBody
	 * @RequestMapping(value = "/check/{wxToken}", method = RequestMethod.GET,
	 *                       produces = "text/plain") public String
	 *                       validate(@PathVariable("wxToken") String wxToken,
	 *                       CheckModel tokenModel) throws ParseException,
	 *                       IOException { return
	 *                       tokenService.validate(wxToken,tokenModel); }
	 */

	@RequestMapping("/test.do")
	@ResponseBody
	public ModelAndView testMethod(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/index");
		//return "this is test url";
		return view;
	}

	/**
	 * 公众号开发模式配置服务器时调用，验证方法
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/validate.do")
	@ResponseBody
	public String validate(HttpServletRequest request) {
//		if (WechatURLFilter.checkSignature(request)) {
//			return request.getParameter("echostr");
//		}
		return request.getParameter("echostr"); //成功就用这个
		//return "validate failure";
	}

	/**
	 * 对外提供获取AccessToken方法
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAccessToken.html")
	@ResponseBody
	public String getAccessToken(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("AccessToken", AccessTokenGetter.getAccess_token());
		return JSON.toJSONString(resMap);
	}

	/**
	 * 对外主动刷新AccessToken方法
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/refreshAccessToken.html")
	@ResponseBody
	public String refreshAccessToken(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("AccessToken", AccessTokenGetter.refreshAccessToken());
		return JSON.toJSONString(resMap);
	}

	/**
	 * 对外提供获取微信用户信息方法
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@RequestMapping("/getSNSUser.html")
	@ResponseBody
	public String getSNSUser(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
		return JSON.toJSONString(LoginHelper.getSnsUserInfo(request, response));
	}
}
