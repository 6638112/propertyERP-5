package com.cnfantasia.server.api.msAnnualConfession.web;

import java.math.BigInteger;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.msAnnualConfession.entity.MsAnnualConfessionDto;
import com.cnfantasia.server.api.msAnnualConfession.service.IMsAnnualConfessionService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.session.UserContext;

/**
 * 周年活动
 * 
 * @author liyulin
 * @version 1.0 2016年7月29日 下午8:50:01
 */
@Controller
@RequestMapping("/msAnnualConfession")
public class MsAnnualConfessionController extends BaseController{

    private final Logger logger = Logger.getLogger(getClass());

	@Resource
	private IMsAnnualConfessionService msAnnualConfessionService;
	
	/**
	 * 获取用户相关活动信息
	 * 
	 * @return
	 */
	@RequestMapping("/getUserInfo.json")
	public ModelAndView getUserInfo(){
		ModelAndView modelAndView = new ModelAndView();
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		MsAnnualConfessionDto msAnnualConfessionDto = msAnnualConfessionService.getAnnualConfessionByUserId(userId);
	
		logger.error("MsAnnualConfessionController.getUserInfo userId="+userId+"==>"+msAnnualConfessionDto);
		
		modelAndView.addObject("msAnnualConfession", msAnnualConfessionDto);
		modelAndView.setViewName("/confession/index");;
		
		return modelAndView;
	}
}
