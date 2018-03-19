package com.cnfantasia.wl.wechat.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;
import com.cnfantasia.wl.wechat.web.LoginHelper;

public class WeixinUtils {
	
	/**
	 * 设置用户是否已经关注公众号
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static int setIsSubscribe(HttpServletRequest request, HttpServletResponse response) throws IOException, ClientProtocolException {
		//用户是否订阅
		SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
		if (user.getOpenid() != null) {
			int subscribe = JSON.parseObject(UserInfoGetter.getUserInfo(user.getOpenid())).getInteger("subscribe");
			request.getSession().setAttribute("subscribe", subscribe);
			return subscribe;
		} else {
			request.getSession().setAttribute("subscribe", 0);
			return 0;
		}
	}

}
