package com.cnfantasia.server.ms.storeInvite.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/storeInvite")
public class StoreInviteController {

	@RequestMapping("/index.html")
	public ModelAndView parkingTip(){
		return new ModelAndView("/storeInvite/index");
	}
}
