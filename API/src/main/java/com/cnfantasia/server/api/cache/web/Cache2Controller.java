package com.cnfantasia.server.api.cache.web;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.access.service.IAccessService;
import com.cnfantasia.server.api.cache.dto.CacheMsg;
import com.cnfantasia.server.api.cache.dto.CheckResult;
//import com.cnfantasia.server.api.cache.dto.CheckResult;
import com.cnfantasia.server.api.cache.node.CacheConstants;
import com.cnfantasia.server.api.cache.node.ICachePublishHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;

/**
 * 新一代缓存管理
 * 
 * @author liyulin
 * @version 1.0 2017年1月9日 上午9:58:51
 */
@Controller
@RequestMapping("/cache2")
public class Cache2Controller extends BaseController {
	@Resource
	private ICachePublishHandler cachePublishHandler;
	@Resource
	private IAccessService accessService;
	/**上次刷新时间*/
	private static long refreshAllLastTime = 0L;
	private static long refreshSysParamLastTime = 0L;
	private static long refreshMybatisLastTime = 0L;
	/**1个小时内已刷新的次数*/
	private static int refreshedAllTimes = 0;
	private static int refreshedSysParamTimes = 0;
	private static int refreshedMybatisTimes = 0;


	@RequestMapping("loginPage.json")
	public ModelAndView loginPage() {
		return new ModelAndView("/cache/login");
	}
	
	/**
	 * 登陆校验
	 * 
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping("loginCheck.json")
	public ModelAndView loginCheck(String username, String password, HttpSession session){
		ModelAndView mav = new ModelAndView();
		if(CacheConstants.User.username.equals(username) && CacheConstants.User.password.equals(password)){
			session.setMaxInactiveInterval(60*30);// 半个小时
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			
			mav.setViewName("/cache/index");
		} else {
			mav.addObject("msg", "用户名或密码错误！");
			mav.setViewName("/cache/login");
		}
		return mav;
	}

	/**
	 * 刷新所有数据
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/refreshAll.json", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse refreshAll(HttpSession session) {
		accessService.refreshCarNumPrefix();
		
		return refreshCache(CacheConstants.MsgType.Refresh_All, session);
	}

	/**
	 * 只刷新t_comm_sys_para表数据
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/refreshSysParam.json", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse refreshSysParam(HttpSession session) {
		return refreshCache(CacheConstants.MsgType.Refresh_SysParam, session);
	}
	
	/**
	 * 刷新mybatis缓存
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/refreshMyBatisCache.json", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse refreshMyBatisCache(HttpSession session) {
		return refreshCache(CacheConstants.MsgType.Refresh_Mybatis_Cache, session);
	}
	
	/**
	 * 刷新车禁小区缓存
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/refreshAllHtCarGb.json", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse refreshAllHtCarGb(HttpSession session) {
		return refreshCache(CacheConstants.MsgType.Refresh_All_HtCarGb_Cache, session);
	}
	
	/**
	 * 刷新车牌前缀缓存
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/refreshCarNumPrefix.json", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse refreshCarNumPrefix(HttpSession session) {
		accessService.refreshCarNumPrefix();
		
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setMessage("操作成功！");
		jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
		return jsonResponse;
	}

	/**
	 * 刷新缓存
	 * 
	 * @param msgType
	 *            消息类型
	 * @return
	 */
	private final JsonResponse refreshCache(int msgType, HttpSession session) {
		JsonResponse jsonResponse = new JsonResponse();
		// 用户身份校验
		CheckResult checkUserResult = checkUser(session);
		if(!checkUserResult.isSuccess()){
			jsonResponse.setMessage(checkUserResult.getMsg());
			jsonResponse.setStatus("0005");// 与“/cache/index.jsp”页面对应
			return jsonResponse;
		}
		
		// 时间间隔校验
		String isMessageSend = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.IS_MESSAGE_SEND);
		// 只有生产上才发短信，根据这个判断是否为生成环境。只有生成环境才做时间限制
		if(StringUtils.isNotBlank(isMessageSend) && !"0".equals(isMessageSend)){
			CheckResult checkTimeResult = checkTime(msgType);
			if (!checkTimeResult.isSuccess()) {
				jsonResponse.setMessage(checkTimeResult.getMsg());
				jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
				return jsonResponse;
			}
		}

		CacheMsg msg = new CacheMsg(msgType);
		cachePublishHandler.sendMessage(msg);// 用redis处理多节点缓存刷新

		jsonResponse.setMessage("操作成功！");
		jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
		return jsonResponse;
	}

	/**
	 * 时间校验
	 * 
	 * @param msgType
	 * @return
	 */
	private final CheckResult checkTime(int msgType) {
		long now = new Date().getTime();
		boolean isOneHourLess = false;// 是否1小时内
		boolean isFiveTimesMore = false;// 是否超过5次
		if (msgType == CacheConstants.MsgType.Refresh_All) {
			isOneHourLess = ((now - refreshAllLastTime) <= CacheConstants.Refresh_Time_Limit);
			if(isOneHourLess){
				if(++refreshedAllTimes>5){
					isFiveTimesMore = true;
				}
			} else {
				refreshAllLastTime = now;
				refreshedAllTimes = 1;
			}
		} else if (msgType == CacheConstants.MsgType.Refresh_SysParam) {
			isOneHourLess = ((now - refreshSysParamLastTime) <= CacheConstants.Refresh_Time_Limit);
			if(isOneHourLess){
				if(++refreshedSysParamTimes>5){
					isFiveTimesMore = true;
				}
			} else {
				refreshSysParamLastTime = now;
				refreshedSysParamTimes = 1;
			}
		} else if(msgType == CacheConstants.MsgType.Refresh_Mybatis_Cache){
			isOneHourLess = ((now - refreshMybatisLastTime) <= CacheConstants.Refresh_Time_Limit);
			if(isOneHourLess){
				if(++refreshedMybatisTimes>5){
					isFiveTimesMore = true;
				}
			} else {
				refreshMybatisLastTime = now;
				refreshedMybatisTimes = 1;
			}
		}
		if (isOneHourLess && isFiveTimesMore) {
			return new CheckResult(false, "1个小时内只能刷新5次！已超限制！刷新失败！");
		} else {
			return new CheckResult(true);
		}
	}
	
	/**
	 * 用户身份校验
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	private final CheckResult checkUser(HttpSession session) {
		Object username = session.getAttribute("username");
		if (username==null || !CacheConstants.User.username.equals(username)) {
			return new CheckResult(false, "session已失效，请重新登陆！");
		}
		return new CheckResult(true);
	}

}
