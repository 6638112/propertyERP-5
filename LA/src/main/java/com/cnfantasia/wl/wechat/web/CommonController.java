package com.cnfantasia.wl.wechat.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.HttpUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.wl.wechat.WeChatConstant;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;

/**
 * 通用转发器
 * @author wenfq 2015-12-26
 *
 */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {
	
	@Resource
	private IHttpClient simpleHttpClient;
	
	/**
	 * 通用转发器:detailUrl表示要跳转到哪个接口，isNeedLogin表示是否需要登录，默认0不需要，1需要
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@ResponseBody
	@RequestMapping("/toUrl.do")
	public JsonResponse toUrl(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
		JsonResponse jsonResponse = new JsonResponse();
		
		String detailUrl= request.getParameter("detailUrl");
		if(detailUrl == null){
			jsonResponse.setMessage("detailUrl can't be null");
			return jsonResponse;
		}
		
		int isNeedLogin = ParamUtils.getInt(request, "isNeedLogin",0);

		if(isNeedLogin == 1){//需要登录
			SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
			LoginHelper.loginAPI(simpleHttpClient, request, user);
			
			//LoginHelper.login(request, simpleHttpClient);
		}

		if(request.getMethod().equals(RequestMethod.POST.toString())) {
			jsonResponse = simpleHttpClient.submitSimple(detailUrl, getParameterMap(request), LoginHelper.prepareReqHeader(request));
		}else if(request.getMethod().equals(RequestMethod.GET.toString())){
			jsonResponse = simpleHttpClient.doGet(detailUrl, getParameterMap(request), LoginHelper.prepareReqHeader(request));
		}

		return jsonResponse;
	}
	
	
	private HttpUtil getHttpUtil(HttpServletRequest request) {
		HttpUtil httpUtil = new HttpUtil();
		httpUtil.addHeader("channelId", "1");
		httpUtil.addHeader("subChannelId", HeaderConstant.SubChannelId.Jfq_Light_App+"");
		httpUtil.addHeader("deviceId", "fromJFQLightApp");
		httpUtil.addHeader("version", "1.0.0");
		httpUtil.addHeader("gbId", (String)request.getSession().getAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_GBID));
		if (request.getSession().getAttribute(WeChatConstant.SessionKey) != null) {
			//移动协议里规定：请求头每次都传入sessionKey,退出后应清除sessionKey，未登录时sessionKey传入空值
			httpUtil.addHeader(WeChatConstant.SessionKey, request.getSession().getAttribute(WeChatConstant.SessionKey).toString());
		}
		
		return httpUtil;
	}

}
