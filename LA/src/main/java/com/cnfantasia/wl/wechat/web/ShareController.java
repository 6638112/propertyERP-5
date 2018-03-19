package com.cnfantasia.wl.wechat.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.wl.wechat.util.JsapiTicketGetter;
import com.cnfantasia.wl.wechat.util.Sign;

@Controller
@RequestMapping("/share")
public class ShareController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());
	
	
	/**
	 * 
	 * 获取微信分享相差参数， currentURL必须要传，用于表示当前的URL，带参数
	 * @param request
	 * @return
	 */
	@RequestMapping("/getShareParam.do")
	@ResponseBody
	public JsonResponse getShareParam(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JsonResponse jsonResponse = new JsonResponse();
		String currentURL = request.getParameter("currentURL");
		if(StringUtils.isEmpty(currentURL)){
			logger.info("ShareController.getShareParam.currentURL is null");
			throw new ValidateRuntimeException("ShareController.getShareParam.currentURL is null");
		}
		
		jsonResponse.putData("signInfo", Sign.sign(JsapiTicketGetter.getJsapiTicket(), currentURL));
		return jsonResponse;
	}
}
