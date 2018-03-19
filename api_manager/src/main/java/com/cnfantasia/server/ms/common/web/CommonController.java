package com.cnfantasia.server.ms.common.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.ms.pub.BaseController;

/**
 * 通用Controller
 * @author wenfq 2015-1-13
 *
 */
@Controller 
@RequestMapping("/common")
public class CommonController extends BaseController {
	@Resource
	private IHttpClient simpleHttpClient;
	
	/**
	 * 根据传入的page参数跳转到相应的页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/toPage.html")
	public ModelAndView toPage(HttpServletRequest request) {
		//如果不传page参数，直接进入404页面
		String page = ParamUtils.getString(request, "page", "/error/404");
		
		return new ModelAndView(page);
	}
	
	/**
	 * 通用转发器:detailUrl表示要跳转到哪个接口，isNeedLogin表示是否需要登录，默认0不需要，1需要
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@ResponseBody
	@RequestMapping("/toUrl.json")
	public JsonResponse toUrl(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
		JsonResponse jsonResponse = new JsonResponse();
		
		String detailUrl= request.getParameter("detailUrl");
		if(detailUrl == null){
			jsonResponse.setMessage("detailUrl can't be null");
			return jsonResponse;
		}
		jsonResponse = simpleHttpClient.submitSimple(detailUrl, getParameterMap(request));
		return jsonResponse;
	}
	
	/**
	 * 执行一次Task任务
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(value="/executeTask.json", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse executeTask(HttpServletRequest request, String taskName, String methodName) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		JsonResponse jsonResponse = new JsonResponse();
		if(request.getRequestURL().toString().contains("jiefangqu")){
			jsonResponse.setMessage("生产环境不允许请求该接口");
			jsonResponse.setStatus("0001");
			return jsonResponse;
		}
		
		Object task = CnfantasiaCommbusiUtil.getBeanManager(taskName);
		Method method = task.getClass().getDeclaredMethod(methodName, null);
		method.invoke(task, null);
		
		return jsonResponse;
	}
}
