package com.cnfantasia.server.ms.user.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.user.entity.UserEntity;
import com.cnfantasia.server.ms.user.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	
	private IUserService userService;
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/qryPersonInfo.json")
	@ResponseBody
	public JsonResponse qryPersonInfo(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		BigInteger userId = UserContext.getOperIdBigInteger();
		UserEntity userEntity = userService.getUserById(userId);
		//3.结果处理
		logger.debug(JSON.toJSONString(userEntity));
		return jsonResponse;
	}
	
	@RequestMapping("/updPersonInfo.json")
	@ResponseBody
	public JsonResponse updPersonInfo(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		//3.结果处理
		return jsonResponse;
	}
	
	@RequestMapping("/list.html")
	public ModelAndView index(HttpServletRequest requset) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/omsUser/omsUserList");

		Map paramMap = new HashMap();
		List<OmsUser> resList = userService.getOmsUserByCondition(paramMap);
		requset.setAttribute("resList", resList);
		return modelAndView;
	}

	//测试权限
	@RequestMapping("/deleteUser.html")
	@ResponseBody
	public JsonResponse deleteUser(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
		//3.结果处理
		return jsonResponse;
	}
}
