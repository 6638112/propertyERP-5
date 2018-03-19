package com.cnfantasia.wl.wechat.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.pub.constant.CookieConstant;
import com.cnfantasia.pub.entity.Regist3rdResponse;
import com.cnfantasia.pub.util.WeChatConfig;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.business.pub.utils.CookieUtil;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.wl.wechat.WeChatConstant;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;
import com.cnfantasia.wl.wechat.util.AccessTokenGetter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 登录
 * 
 * @author wenfq 2015-01-06
 * 
 */
public class LoginHelper {
	private final static Log logger = LogFactory.getLog(LoginHelper.class);
	
	@Resource
	private IHttpClient simpleHttpClient;

	/**
	 * 常规登录
	 * 
	 * @deprecated
	 * @param request
	 * @return
	 */
	public static JsonResponse login(HttpServletRequest request, IHttpClient simpleHttpClient) {
		List<Header> appendHeaderList = prepareReqHeader(request);

		// 创建参数队列    
		Map<String, Object> params = new HashMap<String, Object>();
		String number = ParamUtils.getString(request, "myNumber", null);
		String password = ParamUtils.getString(request, "myPassword", null);
		if(StringUtils.isEmpty(number) && StringUtils.isEmpty(password)) {
			params.put("number", "50003");
			params.put("password", "123456");
		} else {
			params.put("number", number);
			params.put("password", password);
		}
		params.put("loginType", 3); // 2 = 手机+短信验证码

		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/login/doLogin.json", params, appendHeaderList);
		request.getSession().setAttribute(WeChatConstant.SessionKey,
				JSONObject.parseObject(jsonResponse.getDataValue().toString()).getString(WeChatConstant.SessionKey));
		
		String responseStr = jsonResponse.getDataValue().toString();
		Regist3rdResponse regist3rdResponse = JSON.parseObject(responseStr, Regist3rdResponse.class);
		request.getSession().setAttribute("regist3rdResponse", regist3rdResponse);
		return jsonResponse;
	}

	/**
	 * 用微信用户登录API
	 * 
	 * @deprecated
	 * @param request
	 * @return
	 */
	public static JsonResponse login4WeChat(HttpServletRequest request, IHttpClient simpleHttpClient) {
		List<Header> appendHeaderList = prepareReqHeader(request);

		// 创建参数队列    
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("regType", 6);
		params.put("openId", "o33fQt28_THzvbBIHgG6N_w5uaTI");
		params.put("accessToken", AccessTokenGetter.getAccess_token());
		params.put("realName", "");
		params.put("sex", "1");
		params.put("imgProfile", "http://wx.qlogo.cn/mmopen/PiajxSqBRaEKI8tIDAGgTraibVyU2f1tbZHTT7ZIaoClpg73ibHlLOcIbx4c8iaM2G4sHRRTaLc4wbuv0icm5QvhEtQ/0");
		params.put("mail", "mail");
		params.put("nickName", "DoDo.C");

		params.put("tencentUnionID", "tencentUnionID");

		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/login/regist3rd.json", params, appendHeaderList);
		request.getSession().setAttribute(WeChatConstant.SessionKey,
				JSONObject.parseObject(jsonResponse.getDataValue().toString()).getString(WeChatConstant.SessionKey));
		return jsonResponse;
	}

	/**
	 * 用微信登录API
	 * 
	 * @param request
	 * @param user
	 *            微信用户信息
	 */
	public static String loginAPI(IHttpClient simpleHttpClient, HttpServletRequest request, SnsUserinfo user) {
		Regist3rdResponse regist3rdResponse = LoginHelper.getRegist3rdResponseFromSession(request);
		if (regist3rdResponse != null && regist3rdResponse.getUserId()!=null) {
			return JSON.toJSONString(regist3rdResponse);
		}
		
		//2登录API
		List<Header> appendHeaderList = LoginHelper.prepareReqHeader(request);

		Map<String, Object> params = new HashMap<String, Object>();// 创建参数队列    

		if(user.getOpenid() == null){
			logger.info("user's openid is null");
			return null;
		}
		
		params.put("regType", 6);
		params.put("openId", user.getOpenid());
		//params.put("accessToken", request.getSession().getAttribute("access_token").toString());
		//因为 com.cnfantasia.server.api.commonBusiness.service.CommonLoginService.WlightAppValidateUrl使用的是全局AccessToken，不是autho2.0返回的授权AccessToken
		//见http://www.cnblogs.com/txw1958/p/weixin76-user-info.html
		params.put("accessToken", AccessTokenGetter.getAccess_token());
		params.put("realName", user.getNickname());
		params.put("sex", transSexFromWhatToJFQ(user.getSex()));
		String userAvatar = user.getHeadimgurl();
		if (userAvatar != null && userAvatar.contains("http")) {
			params.put("imgProfile", user.getHeadimgurl());
		}
		params.put("nickName", user.getNickname());
		params.put("unionId", user.getUnionid());

		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/login/regist3rd.json", params, appendHeaderList);
		request.getSession().setAttribute(WeChatConstant.SessionKey,
				JSONObject.parseObject(jsonResponse.getDataValue().toString()).getString(WeChatConstant.SessionKey));
		logger.info("登录返回的消息" + jsonResponse.getDataValue());
		
		String responseStr = jsonResponse.getDataValue().toString();
		regist3rdResponse = JSON.parseObject(responseStr, Regist3rdResponse.class);
		request.getSession().setAttribute("regist3rdResponse", regist3rdResponse);
		
		return responseStr;
	}
	
	public static Regist3rdResponse getRegist3rdResponseFromSession(HttpServletRequest request) {
		Regist3rdResponse regist3rdResponse = (Regist3rdResponse) request.getSession().getAttribute("regist3rdResponse");
		return regist3rdResponse;
	}
	
	/**
	 * 判断当前用户是否已经登录
	 * @param request
	 * @return
	 */
	public static boolean isLoginUser(HttpServletRequest request){
		Regist3rdResponse regist3rdResponse = getRegist3rdResponseFromSession(request);
		return regist3rdResponse != null && regist3rdResponse.getUserId() != null && request.getSession().getAttribute(WeChatConstant.SessionKey) != null;
	}

	/**
	 * 转换微信Sex为解放区Sex
	 * <p>
	 * 微信：值为1时是男性，值为2时是女性，值为0时是未知 <br>
	 * 解放区：0男，1女
	 * 
	 * @author wenfq 2015-07-16
	 * @return
	 */
	private static String transSexFromWhatToJFQ(String weChatSex) {
		if ("1".equals(weChatSex))
			return "0";
		else if ("2".equals(weChatSex))
			return "1";
		else
			return null;
	}

	/**
	 * 获微信用户信息：先用Session中获取，若没，则去Cookie中获取，则再没有，则请求微信授权获取
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static SnsUserinfo getSnsUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ClientProtocolException {
		logger.info("loginHelperTemp get;request.getSession().getId():" + request.getSession().getId());
		if(request.getCookies() != null && request.getCookies().length > 0) {
			logger.info("loginHelperTemp get;request.getCookies:" + request.getCookies()[0].getDomain() + ";;" +  request.getCookies()[0].getPath());
		}
		
		
		if (request.getSession().getAttribute(CookieConstant.SnsUser) != null//如果非首次打开Session中有值，不需要去微信端取值
				&& StringUtils.isNotEmpty(((SnsUserinfo) request.getSession().getAttribute(CookieConstant.SnsUser)).getNickname())) {
			logger.info("loginHelperTemp get; nickName:" + ((SnsUserinfo) request.getSession().getAttribute(CookieConstant.SnsUser)).getNickname());
			return (SnsUserinfo) request.getSession().getAttribute(CookieConstant.SnsUser);
		}

		if (CookieUtil.getCookieByName(request, CookieConstant.SnsUser) != null) {//如果Cookie中有值，也不用去微信端取值
			String snsUserJsonString = java.net.URLDecoder.decode(CookieUtil.getCookieByName(request, CookieConstant.SnsUser).getValue(), "utf-8");
			return JSON.parseObject(snsUserJsonString, SnsUserinfo.class);
		}

		//1获取微信用户信息
		String code = request.getParameter("code");
		/*String myNumber = ParamUtils.getString(request, "myNumber", null);
		if(StringUtils.isEmpty(code) && myNumber == null){
			String currentUrl = request.getRequestURL() + "?" + request.getQueryString();
			logger.error("code is null.currentUrl===>"+currentUrl);
			String wechatRedirectUrl = WeChatConstant.OAuth2_URL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", URLEncoder.encode(currentUrl, "utf-8"));
			response.sendRedirect(wechatRedirectUrl); //重定向到微信授权页
			return null;
		}*/
		
		String requestURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

		requestURL = String.format(requestURL, WeChatConfig.APPID, WeChatConfig.APPSECRET, code);
		logger.debug(requestURL);
		HttpGet httpGet = new HttpGet(requestURL);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse hcResponse = httpClient.execute(httpGet);
		String entityContent = EntityUtils.toString(hcResponse.getEntity());
		logger.debug("getSnsUserInfo1:" + entityContent);

		JSONObject jsonObj = JSON.parseObject(entityContent);

		String access_token = jsonObj.getString("access_token");
		request.getSession().setAttribute("access_token", access_token);
		String openid = jsonObj.getString("openid");

		requestURL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
		requestURL = String.format(requestURL, access_token, openid);
		logger.info(requestURL);
		httpGet = new HttpGet(requestURL);
		httpClient = HttpClients.createDefault();
		hcResponse = httpClient.execute(httpGet);
		entityContent = EntityUtils.toString(hcResponse.getEntity(), HTTP.UTF_8);

		logger.info("微信用户信息" + entityContent);
		SnsUserinfo user = JSON.parseObject(entityContent, SnsUserinfo.class);
		
		logger.info("loginHelperTemp set;request.getSession().getId():" + request.getSession().getId());
		if(request.getCookies() != null && request.getCookies().length > 0) {
			logger.info("loginHelperTemp set;request.getCookies:" + request.getCookies()[0].getDomain() + ";;" +  request.getCookies()[0].getPath());
		}
		logger.info("loginHelperTemp set" + user.getNickname());
		
		request.getSession().setAttribute(CookieConstant.SnsUser, user);
		if (!JSON.parseObject(entityContent).containsKey("errcode")) {
			CookieUtil.addCookie(response, CookieConstant.SnsUser, URLEncoder.encode(entityContent, "utf-8"), CookieConstant.cookieExpireTime_one_hour);
		}
		return user;
	}

	/**
	 * 准备请求头
	 * 
	 * @param request
	 * @return
	 */
	public static List<Header> prepareReqHeader(HttpServletRequest request) {
		List<Header> appendHeaderList = new ArrayList<Header>();
		appendHeaderList.add(new BasicHeader("channelId", "1"));
		appendHeaderList.add(new BasicHeader("subChannelId", HeaderConstant.SubChannelId.Jfq_Light_App+""));
		appendHeaderList.add(new BasicHeader("deviceId", "fromJFQLightApp"));
		appendHeaderList.add(new BasicHeader("version", "1.0.0"));
		if (request.getSession().getAttribute(WeChatConstant.SessionKey) != null) {
			//移动协议里规定：请求头每次都传入sessionKey,退出后应清除sessionKey，未登录时sessionKey传入空值
			appendHeaderList.add(new BasicHeader(WeChatConstant.SessionKey, request.getSession().getAttribute(WeChatConstant.SessionKey).toString()));
		}
		if(request.getSession().getAttribute("version") != null) {
			appendHeaderList.add(new BasicHeader("version", request.getSession().getAttribute("version").toString()));
		}
		if(request.getSession().getAttribute("subChannelId") != null) {
			appendHeaderList.add(new BasicHeader("subChannelId", request.getSession().getAttribute("subChannelId").toString()));
		}
		return appendHeaderList;
	}

	/**
	 * 获取SessionKey
	 * 
	 * @param request
	 * @return
	 */
	public static String getSessionKey(HttpServletRequest request) {
		if (request.getSession().getAttribute(WeChatConstant.SessionKey) != null) {
			return request.getSession().getAttribute(WeChatConstant.SessionKey).toString();
		} else {
			return null;
		}
	}

	/**
	 * 处理Session共享，特别是 app调起轻应用，提交订单什么操作的，确保订单记录在app登录的用户下
	 * @param request
	 * @param response
	 * @param logger
	 * @param simpleHttpClient
	 * @throws IOException
	 */
	public static void shareSessionKey(HttpServletRequest request, HttpServletResponse response, IHttpClient simpleHttpClient) throws IOException {
		String subChannelId = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
		String version = HeaderManager.getVersion();
		String sessionKey = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY);
		logger.debug("listWeiXiuHomeType.do-sessionKey:" + sessionKey + ", version:" + version + ",subChannelId:" + subChannelId + ", sessionId:" +  request.getSession().getId());
		if(subChannelId == null || version == null) {
			subChannelId = (String) request.getSession().getAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
			version = (String) request.getSession().getAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION);
		} else {
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION, version);
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL, subChannelId);
		}

		if (!"1".equals(subChannelId) && !"2".equals(subChannelId)) {
			logger.debug("listWeiXiuHomeType.do-weixin");
			SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
			LoginHelper.loginAPI(simpleHttpClient, request, user);
		}

		if(sessionKey != null) {
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION, version);
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY, sessionKey);
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL, subChannelId);
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_GBID, HeaderManager.getGbId());
		} else {
			request.getSession().removeAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY);
		}
	}
}
