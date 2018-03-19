/**   
 * Filename:    BaseController.java   
 * @version:    1.0  
 * Create at:   2014年5月9日 上午4:01:57   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年5月9日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.pub;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.pub.logger.SysLoggerProcessInterceptor;
import com.cnfantasia.server.business.pub.CommBaseController;
import com.cnfantasia.server.business.pub.exception.ExceptionParseUtil;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;

/**
 * Filename: BaseController.java
 * 
 * @version: 1.0.0 Create at: 2014年5月9日 上午4:01:57 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年5月9日 shiyl 1.0 1.0 Version
 */
public class BaseController extends CommBaseController<Object>{
	private Log logger = LogFactory.getLog(getClass());
	public static final String REQUEST_KEY_RESPONSE_DATA=CommBaseController.class.getName()+"_response";
	/**
	 * 异常控制，这便是异常细节可控，将来可用于支持国际化（异常信息国际化）
	 * */
	@ExceptionHandler(Exception.class)
	// @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseStatus(value = HttpStatus.OK)
	// 2014-5-15 8:32:41 update
	@ResponseBody
	public Object handleException(Exception ex, HttpServletRequest request) {
		logger.error("BaseController.handleException.info", ex);
		logger.error(ex.getMessage(), ex);
		// ex.printStackTrace();
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集参数
		String status = ExceptionParseUtil.parseStatus(ex,true);// status状态码
		String errCode = ExceptionParseUtil.parseErrorCode(ex,true);// errCode
		String errorMsg = ExceptionParseUtil.parseErrorMsg(ex,true);// errMessage

		{
			jsonResponse.setStatus(status);
			jsonResponse.setErrcode(errCode);
			jsonResponse.setMessage(errorMsg);
		} 
		/*{//调试信息
		 	String errStack = ExceptionParseUtil.parseExceptionMessage(ex);// errStack
			String uqCode = UserContext.getOperId()+"_"+new Date().getTime()+"_"+RandomUtils.createRandom(false, 5);
			StringBuffer sbf = new StringBuffer();
			sbf.append("<status>" + status + "</status>");
			sbf.append("<errCode>" + errCode + "</errCode>");
			sbf.append("<errorMsg>" + errorMsg + "</errorMsg>");
			sbf.append("<errStack>" + errStack + "</errStack>");
			jsonResponse.setDebugInfo(sbf.toString());
		}*/
		{//调试信息
			String uniqueCode = getUniqueCode(ex, request);
			jsonResponse.setTransNo(uniqueCode);
		}
		if (logger.isDebugEnabled()) {
			logger.error("jsonResponse is: " + JSON.toJSONString(jsonResponse));
		}
		// 3.结果处理
		String responseData = JSON.toJSONString(jsonResponse);
		request.setAttribute(REQUEST_KEY_RESPONSE_DATA, responseData);//存入request中
		return JSON.toJSONString(jsonResponse);
	}
	@Override
	public String getUniqueCode(Exception ex, HttpServletRequest request) {
		String uniqueCode = (String)request.getAttribute(SysLoggerProcessInterceptor.REQUEST_KEY_UQEXPKEY);
		return uniqueCode;
	}
	/**
	 * app根据传入页面跳转页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toPage.html")
	public ModelAndView toPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String page = ParamUtils.getString(request, "page", null);
		//拼购描述
		if(page.equals("groupBuyingDesc")) {
			modelAndView.setViewName("/ebuy/ebuyFight/groupBuyingDesc");
		}else{
			modelAndView.setViewName("/ebuy/" + "noItem");
		}
		return modelAndView;
	}
	
	/**
	 * 转request中参数转换为Map
	 * @param request
	 * @return
	 */
	public Map<String, Object> getParameterMap(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Enumeration<?> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String paramName = (String) enumeration.nextElement();

			String[] values = request.getParameterValues(paramName);
			if (values.length == 1) {
				map.put(paramName, values[0]);
			} else {
				map.put(paramName, values);
			}
		}
		return map;
	}
}
