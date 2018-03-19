package com.cnfantasia.server.api.dredge.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.pub.BaseController;

/**
 */
@RequestMapping("/act")
@Controller
public class ActController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());
	
	/**
	 * 缴物业费
	 * @throws IOException 
	 */
	@RequestMapping(value="/index.html")
	@ResponseBody
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("/act_mothersday/index");
		return modelAndView;
	}

}
