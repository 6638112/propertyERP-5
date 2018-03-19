package com.cnfantasia.server.api.access.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.access.constant.HACarDict;
import com.cnfantasia.server.api.access.service.HACarService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.common.utils.HttpUtil;

/**
 * 华安车禁
 * 
 * @author liyulin
 * @version 1.0 2017年8月21日 上午9:59:41
 */
@Controller
@RequestMapping("/haCar")
public class HACarController extends BaseController{
	private final Logger logger = Logger.getLogger(getClass());
	
	@Resource
	private HACarService haCarService;
	
	/**
	 * 同步车辆出入场记录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/synParkingRecord.json")
	@ResponseBody
	public String synParkingRecord(HttpServletRequest request) {
		String allParams = HttpUtil.getAllParams("/haCar/synParkingRecord.json", request);
		logger.info(allParams);
		boolean isSuccess = haCarService.synParkingRecord(request);
		
		return getResult(isSuccess);
	}
	
	/**
	 * 同步线下月卡缴费记录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/sysRechargeLog.json")
	@ResponseBody
	public String sysRechargeLog(HttpServletRequest request) {
		String allParams = HttpUtil.getAllParams("/haCar/sysRechargeLog.json", request);
		logger.info(allParams);
		boolean isSuccess = haCarService.sysRechargeLog(request);
		
		return getResult(isSuccess);
	}
	
	private String getResult(boolean isSuccess) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(isSuccess) {
			resultMap.put("status", HACarDict.State.SUCCESS);
		} else {
			resultMap.put("status", HACarDict.State.FAIL);
			resultMap.put("msg", "同步失败");
		}
		
		return JSON.toJSONString(resultMap);
	}
}
