package com.cnfantasia.server.ms.communitySupply.web;

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

import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.ms.communitySupply.entity.CommunitySupplyEntity;
import com.cnfantasia.server.ms.communitySupply.service.ICommunitySupplyService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.session.UserContext;

@Controller
@RequestMapping("/communitySupply")
public class CommunitySupplyController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());
	
	private ICommunitySupplyService communitySupplyService;
	

	/**
	 * 商家类别
	 */
	private List<CommunitySupplyType> communitySupplyTypeList;

	public ICommunitySupplyService getCommunitySupplyService() {
		return communitySupplyService;
	}

	public void setCommunitySupplyService(ICommunitySupplyService communitySupplyService) {
		this.communitySupplyService = communitySupplyService;
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

		if (communitySupplyTypeList == null) {//延迟加载
			paramMap.clear();
			paramMap.put("sys0DelState", 0);
			communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeByCondition(paramMap);
		}
		request.setAttribute("cstList", communitySupplyTypeList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyList");
		return modelAndView;
	}

	/**
	 * 编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit.html")
	public ModelAndView edit(HttpServletRequest request) {
		String id = request.getParameter("id");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyEdit");
		return modelAndView;
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/save.html")
	public ModelAndView save(HttpServletRequest request) {
		request.setAttribute("result", "账号保存成功");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyOprtSuccess");
		return modelAndView;
	}

	/**
	 * 查看明细
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewDetail.html")
	public ModelAndView viewDetail(HttpServletRequest request) {
		String id = request.getParameter("id");
		request.setAttribute("result", "账号保存成功");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyDetail");
		return modelAndView;
	}

	/**
	 * 标为推荐/取消推荐
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/suggestMark.html")
	@ResponseBody
	public String suggestMark(HttpServletRequest request) {
		String suggestMark = request.getParameter("suggestMark"); //标记
		String csId = request.getParameter("csId"); //商家ID
		String pcId = request.getParameter("pcId");//物业公司ID
		String gbId = request.getParameter("gbId");//小区ID

		String resultInfo = communitySupplyService.suggestMark(csId, suggestMark, pcId, gbId);
		return resultInfo;
	}

	/**
	 * 查找
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/search.html")
	public ModelAndView search(HttpServletRequest request) {
		String supplyType = request.getParameter("supplyType");
		String csName = request.getParameter("csName");
		String gbName = request.getParameter("gbName");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		if (!"all".equals(supplyType)) {//查找全部时，不需要这个条件
			paramMap.put("supplyType", supplyType);
		}
		paramMap.put("csName", csName);
		paramMap.put("gbName", gbName);

		handleListOrSearch(request, paramMap);

		if (communitySupplyTypeList == null) {//延迟加载
			paramMap.clear();
			paramMap.put("sys0DelState", 0);
			communitySupplyTypeList = communitySupplyService.getCommunitySupplyTypeByCondition(paramMap);
		}
		request.setAttribute("cstList", communitySupplyTypeList);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/communitySupply/communitySupplyList");
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
		int resultSize = communitySupplyService.getCommunitySupply_byUserId_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		List<CommunitySupplyEntity> searchRestList = communitySupplyService.getCommunitySupplyList_byUserId_forPage(curPageIndex, pageSize, paramMap);

		request.setAttribute("resList", searchRestList);
	}
}
