package com.cnfantasia.server.api.access.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.access.service.HpfCarService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.common.utils.HttpUtil;
import com.cnfantasia.server.domainbase.htCarGb.entity.HtCarGb;

/**
 * 华庭车禁
 * 
 * @author liyulin
 * @version 1.0 2017年6月15日 下午6:41:38
 */
@Controller
@RequestMapping("/hTCar")
public class HpfCarController extends BaseController {
	private final Logger logger = Logger.getLogger(getClass());
	
	@Resource
	private HpfCarService hpfCarService;
	/**
	 * 入口
	 * 
	 * @return
	 */
	@RequestMapping("/index.json")
	@ResponseBody
	public String index(String a, String Msg, HttpServletRequest request) {
		String allParams = HttpUtil.getAllParams("/hTCar/index.json", request);
		logger.info(allParams);
		
		String resultMsg = hpfCarService.dealResponse(a, Msg);
		logger.info("resultMsg==>"+resultMsg);
		return resultMsg;
	}
	
	/**
	 * 获取websocket所有客户端信息
	 * @return
	 */
	@RequestMapping("/getWebSocketAllClientInfo.json")
	@ResponseBody
	public String getWebSocketAllClientInfo(){
		List<HtCarGb> allHtCarGbs = hpfCarService.getHtCarGbList();
		Map<String, String> htCarGbInfoMap = new HashMap<String, String>();
		for(HtCarGb htCarGb:allHtCarGbs){
			htCarGbInfoMap.put(htCarGb.gettGroupBuildingFId().toString(), htCarGb.gettParkid());
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("webSocketAllClientInfo", hpfCarService.getWebSocketInfoList());
		resultMap.put("allHtCarGbs", htCarGbInfoMap);
		
		String result = JSON.toJSONString(resultMap);
		return result;
	}

}
