package com.cnfantasia.server.ms.revenue.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.domainbase.channelPartner.service.ChannelPartnerBaseService;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.domainbase.propertyCompany.service.IPropertyCompanyBaseService;
import com.cnfantasia.server.ms.channelPartner.constant.ChannelPartnerConstant;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;

/**
 * 收益中心报表
 * 
 * @author wenfq 2014-12-22
 */
@Controller
@RequestMapping("/revenue")
public class RevenueController extends BaseController{

	IRevenueService revenueService;

	IGroupBuildingBaseService groupBuildingBaseService;

	IPropertyCompanyBaseService propertyCompanyBaseService;

	public void setGroupBuildingBaseService(IGroupBuildingBaseService groupBuildingBaseService) {
		this.groupBuildingBaseService = groupBuildingBaseService;
	}

	public void setPropertyCompanyBaseService(IPropertyCompanyBaseService propertyCompanyBaseService) {
		this.propertyCompanyBaseService = propertyCompanyBaseService;
	}

	public void setRevenueService(IRevenueService revenueService) {
		this.revenueService = revenueService;
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
		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/revenue/revenueList");
		return modelAndView;
	}

	/**
	 * 查找
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/search.html")
	public ModelAndView search(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pcName", request.getParameter("pcName"));
		paramMap.put("gbName", request.getParameter("gbName"));
		paramMap.put("startTime", request.getParameter("startTime"));
		paramMap.put("endTime", request.getParameter("endTime"));
		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/revenue/revenueList");
		modelAndView.addObject("param", paramMap);
		return modelAndView;
	}

	@Resource
	ChannelPartnerBaseService channelPartnerBaseService;

	/**
	 * 统一处理List和Search请求
	 * 
	 * @param request
	 * @param paramMap
	 *            请求参数
	 */
	private void handleListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		paramMap.put("omsUserId", UserContext.getOperId());
		paramMap.put("isadmin", UserContext.getCurrUser().getIsadmin());

		int isChannelPartner = 0; //是否为公司代理用户
		{//判断该用户是否公司代理用户
			Map<String, Object> paramMap1 = new HashMap<String, Object>();
			paramMap1.put("tOmsUserFId", UserContext.getCurrUser().getId());
			paramMap1.put("partnertype", ChannelPartnerConstant.PartnerType.Company);

			if (channelPartnerBaseService.getChannelPartnerCount(paramMap1) > 0) {
				isChannelPartner = 1;
				paramMap.put("isChannelPartner", isChannelPartner);
				paramMap.put("isadmin", null);
			}
		}

		int resultSize = revenueService.select_revenueList_forCount(paramMap);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		//存储查询条件，导出数据时要用到，见exportPayBill()方法
		request.getSession().setAttribute("payBillListQueryParamMap", ((HashMap<String, Object>) paramMap).clone());

		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		List<Map<String, Object>> searchRestList = revenueService.select_revenueList(paramMap);
		int searchRestListSize = searchRestList.size();
		appendOtherData(searchRestList);

		request.setAttribute("resultSize", resultSize + (searchRestList.size() - searchRestListSize));//(searchRestList.size() - searchRestListSize)追加的一些假数据
		request.setAttribute("resList", searchRestList);
	}

	/**
	 * 临时追加的，供商务使用，后续要删除掉 TODO
	 * 
	 * @param searchRestList
	 */
	private void appendOtherData(List<Map<String, Object>> searchRestList) {
		BigInteger omsUserId = UserContext.getOperIdBigInteger();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("adminId", omsUserId);
		List<PropertyCompany> propertyCompanyList = propertyCompanyBaseService.getPropertyCompanyByCondition(paramMap);
		if(propertyCompanyList.size() ==0)
			return;
		
		paramMap.clear();
		paramMap.put("tPropertyCompanyFId", propertyCompanyList.get(0).getId());
		List<GroupBuilding> groupBuildingList = groupBuildingBaseService.getGroupBuildingByCondition(paramMap);

		for (int i = 0; i < groupBuildingList.size(); i++) {
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("pcName", propertyCompanyList.get(0).getName());
			tempMap.put("modelName", "");
			tempMap.put("revItem", "认证门牌数");
			tempMap.put("gbName", groupBuildingList.get(i).getName());
			tempMap.put("f_unit", "20");
			searchRestList.add(tempMap);

			tempMap = new HashMap<String, Object>();
			tempMap.put("pcName", propertyCompanyList.get(0).getName());
			tempMap.put("modelName", "");
			tempMap.put("revItem", "小区用户P2P理财投资额");
			tempMap.put("gbName", groupBuildingList.get(i).getName());
			tempMap.put("f_unit", "2%");
			searchRestList.add(tempMap);


			tempMap = new HashMap<String, Object>();
			tempMap.put("pcName", propertyCompanyList.get(0).getName());
			tempMap.put("modelName", "");
			tempMap.put("revItem", "商品交易总额-物业供货");
			tempMap.put("gbName", groupBuildingList.get(i).getName());
			tempMap.put("f_unit", "100%");
			searchRestList.add(tempMap);

			tempMap = new HashMap<String, Object>();
			tempMap.put("pcName", propertyCompanyList.get(0).getName());
			tempMap.put("modelName", "");
			tempMap.put("revItem", "商品交易总额-解放区供货");
			tempMap.put("gbName", groupBuildingList.get(i).getName());
			tempMap.put("f_unit", "1%");
			searchRestList.add(tempMap);

			tempMap = new HashMap<String, Object>();
			tempMap.put("pcName", propertyCompanyList.get(0).getName());
			tempMap.put("modelName", "");
			tempMap.put("revItem", "商家竞价排名费用");
			tempMap.put("gbName", groupBuildingList.get(i).getName());
			tempMap.put("f_unit", "100%");
			searchRestList.add(tempMap);

			tempMap = new HashMap<String, Object>();
			tempMap.put("pcName", propertyCompanyList.get(0).getName());
			tempMap.put("modelName", "");
			tempMap.put("revItem", "广告排名有效点击数");
			tempMap.put("gbName", groupBuildingList.get(i).getName());
			tempMap.put("f_unit", "1");
			searchRestList.add(tempMap);
		}
	}
}
