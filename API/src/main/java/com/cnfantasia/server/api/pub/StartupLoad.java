package com.cnfantasia.server.api.pub;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.cnfantasia.server.api.access.service.HpfCarService;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;

/**
 * Servlet implementation class StartupLoad
 */
public class StartupLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		ISysParamManager sysParamManager = (ISysParamManager) CnfantasiaCommbusiUtil.getBeanManager("sysParamManager");
		sysParamManager.updateContextPath(config.getServletContext().getContextPath());
		
		HpfCarService hpfCarService = (HpfCarService)CnfantasiaCommbusiUtil.getBeanManager("hpfCarService");
		hpfCarService.init();
	}


}
