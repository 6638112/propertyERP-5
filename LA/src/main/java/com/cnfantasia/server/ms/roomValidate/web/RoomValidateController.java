package com.cnfantasia.server.ms.roomValidate.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.ms.propertyProprietor.entity.PropertyProprietorEntity;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.roomValidate.service.IRoomValidateService;

@Controller
@RequestMapping("/roomValidate")
public class RoomValidateController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());
	
	private IRoomValidateService roomValidateService;

	public void setRoomValidateService(IRoomValidateService roomValidateService) {
		this.roomValidateService = roomValidateService;
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
		modelAndView.setViewName("/roomValidate/roomValidateList");
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
		int resultSize = roomValidateService.getRVList_byUserId_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		List<PropertyProprietorEntity> searchRestList = roomValidateService.getRVList_byUserId_forPage(paramMap);

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
		String rvStatus = request.getParameter("rvStatus");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String uId = request.getParameter("uId");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		if (!rvStatus.equals("0")) {
			paramMap.put("rvStatus", rvStatus);
		}
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		paramMap.put("uId", uId);

		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/roomValidate/roomValidateList");
		return modelAndView;
	}

	/**
	 * 审核
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/audit.html")
	public ModelAndView audit(HttpServletRequest request) {
		String rvId = request.getParameter("id");

		request.setAttribute("rvInfo", roomValidateService.select_rm_forAudit(rvId));

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/roomValidate/roomValidateEdit");
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

		request.setAttribute("rvInfo", roomValidateService.select_rm_forAudit(rvId));

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/roomValidate/roomValidateView");
		return modelAndView;
	}

	/**
	 * 保存审核结果
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveAuditResult.html")
	@Transactional
	public ModelAndView saveAuditResult(HttpServletRequest request) {
		String rvId = request.getParameter("rvId");
		String rrId = request.getParameter("rrId");
		String auditResult = request.getParameter("auditResult");//审核结果：pass notPass
		String auditNotPassResultDesc = request.getParameter("auditNotPassResultDesc"); //不通过原因说明
		String roomId = request.getParameter("rId");
		String userId = request.getParameter("userId");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());

		paramMap.put("rvId", rvId);
		paramMap.put("rrId", rrId);
		paramMap.put("auditNotPassResultDesc", auditNotPassResultDesc);
		paramMap.put("roomId", roomId);
		paramMap.put("userId", userId);

		int updateCount = 0;
		if ("pass".equals(auditResult)) {
			updateCount = roomValidateService.auditPassUpdate(paramMap);
		} else {
			updateCount = roomValidateService.auditNotPassUpdate(paramMap);
		}
		
		if (updateCount > 0) {
			request.setAttribute(JSPConstants.OprtResult, "审批结果保存成功");
		} else {
			request.setAttribute(JSPConstants.OprtResult, "审批结果保存失败");
		}

		request.setAttribute(JSPConstants.ToURL, "../roomValidate/list.html");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}

}
