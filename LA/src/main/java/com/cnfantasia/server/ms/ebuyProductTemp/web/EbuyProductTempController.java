package com.cnfantasia.server.ms.ebuyProductTemp.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domainbase.ebuyProductParametersTemp.dao.EbuyProductParametersTempBaseDao;
import com.cnfantasia.server.domainbase.ebuyProductTemp.entity.EbuyProductTemp;
import com.cnfantasia.server.ms.ebuyProductTemp.dao.EbuyProductTempDao;
import com.cnfantasia.server.ms.ebuyProductTemp.importter.ProductImpotter4HJX;
import com.cnfantasia.server.ms.ebuyProductTemp.importter.ProductImpotter4ZL;
import com.cnfantasia.server.ms.ebuyProductTemp.service.IEbuyProductTempService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.session.UserContext;

@Controller
@RequestMapping("/ebuyProductTemp")
public class EbuyProductTempController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());
	private IEbuyProductTempService ebuyProductTempService;
	protected IUuidManager uuidManager;
	protected EbuyProductTempDao ebuyProductTempDao;
	protected EbuyProductParametersTempBaseDao ebuyProductParametersTempBaseDao;

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	public void setEbuyProductTempDao(EbuyProductTempDao ebuyProductTempBaseDao) {
		this.ebuyProductTempDao = ebuyProductTempBaseDao;
	}

	public void setEbuyProductParametersTempBaseDao(EbuyProductParametersTempBaseDao ebuyProductParametersTempBaseDao) {
		this.ebuyProductParametersTempBaseDao = ebuyProductParametersTempBaseDao;
	}

	public void setEbuyProductTempService(IEbuyProductTempService ebuyProductTempService) {
		this.ebuyProductTempService = ebuyProductTempService;
	}

	/**
	 * 列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/list.html")
	public ModelAndView list(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/ebuyProductTemp/ebuyProductTempList");
		return modelAndView;
	}

	/**
	 * 统一处理List和Search请求
	 * 
	 * @param request
	 * @param paramMap
	 *            请求参数
	 */
	private void handleListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		int resultSize = ebuyProductTempService.getProductList_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		List<EbuyProductTemp> searchRestList = ebuyProductTempService.getProductList_forPage(paramMap);

		request.setAttribute("resList", searchRestList);
	}

	/**
	 * 查找
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/search.html")
	public ModelAndView search(HttpServletRequest request) {
		String prdtName = request.getParameter("prdtName");
		String prdtStatus= request.getParameter("prdtStatus");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		if (!prdtStatus.equals("-1")) {
			paramMap.put("status", prdtStatus);
		}
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		paramMap.put("name", prdtName);

		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/ebuyProductTemp/ebuyProductTempList");
		return modelAndView;
	}

	/**
	 * 查看
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/view.html")
	public ModelAndView view(HttpServletRequest request) {
		String rvId = request.getParameter("id");

		//request.setAttribute("rvInfo", ebuyProductTempService.select_rm_forAudit(rvId));

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/ebuyProductTemp/ebuyProductTempView");
		return modelAndView;
	}

	/**
	 * 同步，同步到正式商品表t_ebuy_product中
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/sync.html")
	@ResponseBody
	public String syncToProduct(HttpServletRequest request) {
		String ptId = request.getParameter("id");
		String resultInfo = "同步失败";

		int updateCount = ebuyProductTempService.sync(ptId);
		if (updateCount > 0) {
			resultInfo = "同步成功";
		}
		return resultInfo;
	}

	/**
	 * 同步电商入口界面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("importter.html")
	public ModelAndView impotter(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/ebuyProductTemp/ebuyProductTempImportter");
		return modelAndView;
	}

	/**
	 * 从外部抓取数据，目前支持“海吉星”
	 * 
	 * @author wenfq 2014-12-31
	 * @return
	 */
	@RequestMapping("importDataHandller.html")
	@ResponseBody
	public String importDataFrom(HttpServletRequest request) {
		String supplyMerchant = request.getParameter("supplyMerchant");
		String serverRealPath = request.getSession().getServletContext().getRealPath("/");
		if ("hjx".equals(supplyMerchant)) {
			new Thread(new ProductImpotter4HJX(uuidManager, ebuyProductTempDao, ebuyProductParametersTempBaseDao, serverRealPath)).start();
		} else if ("zl".equals(supplyMerchant)) {
			new Thread(new ProductImpotter4ZL(uuidManager, ebuyProductTempDao, ebuyProductParametersTempBaseDao, serverRealPath)).start();
		}

		return "数据抓取中，该过程比较耗时，可能需要较长时间，您可先处理其它事务。";
	}

}
