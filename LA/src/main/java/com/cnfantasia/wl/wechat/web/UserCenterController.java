package com.cnfantasia.wl.wechat.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;

/**
 * 会员中心
 * 
 * @author wenfq 2015-01-19
 * 
 */
@Controller
@RequestMapping("/userCenter")
public class UserCenterController extends BaseController {

	@Resource
	private IHttpClient simpleHttpClient;

	/**
	 * 会员中心查看
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@RequestMapping("/userCenterView.do")
	public ModelAndView qryBuyCar(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
		SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
		request.setAttribute("nickName", user.getNickname());
		request.setAttribute("headimgurl", user.getHeadimgurl());
		LoginHelper.loginAPI(simpleHttpClient, request, user);
		//		LoginHelper.login(request, simpleHttpClient);

		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/qryBuyCarProductCount.json", null, LoginHelper.prepareReqHeader(request));
		request.setAttribute("buyCarProductCount", JSON.parseObject(jsonResponse.getDataValue().toString()).getInteger("productCount"));

		//jsonResponse = simpleHttpClient.submitSimple("/ebuy/qryOrderList.json", null, LoginHelper.prepareReqHeader(request));
		return new ModelAndView("/ebuy/userCenter");
	}

	/**
	 * 购物车编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit.do")
	public ModelAndView edit(HttpServletRequest request) {
		//		if (LoginHelper.getSessionKey(request) == null)//未登录时，要先登录
		//			LoginHelper.login(request, simpleHttpClient);

		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/qryBuyCar.json", null, LoginHelper.prepareReqHeader(request));
		request.setAttribute("jsonResponse", jsonResponse);

		ModelAndView view = new ModelAndView();
		view.setViewName("/ebuy/shoppingCart_edit");
		return view;
	}
}
