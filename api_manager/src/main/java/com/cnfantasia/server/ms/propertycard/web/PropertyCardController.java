package com.cnfantasia.server.ms.propertycard.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.propertycard.entity.RealRoomConfig;
import com.cnfantasia.server.api.propertycard.entity.UserHasCardWithGroupBuildingNames;
import com.cnfantasia.server.api.propertycard.service.PropertyCardService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.userHasPropertyCard.entity.UserHasPropertyCard;
import com.cnfantasia.server.domainbase.userHasPropertyCard.service.UserHasPropertyCardBaseService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

/**
 * 物业代扣卡
 * @author wenfq 2016-04-27
 */
@RequestMapping("/propertyCard")
@Controller
public class PropertyCardController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());

	@Resource
	private PropertyCardService propertyCardService;
	
	@Resource
	private UserHasPropertyCardBaseService userHasPropertyCardBaseService;
	
	/**
	 * 单个小区划扣
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/triggerDeducntion4GroupBuilding.json")
	public JsonResponse triggerDeducntion4GroupBuilding(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);

		propertyCardService.triggerDeducntion4GroupBuilding(gbId);

		// 3.结果处理
		return jsonResponse;
	}
	
	
	/**
	 * 列出物业代扣卡购买记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/listCardBuyRecord.html")
	public ModelAndView listCardBuyBuyRecord(HttpServletRequest request) {
		handleListOrSearch(request);
		return new ModelAndView("/propertyCard/listUserBuyCardRecord");
	}

	private void handleListOrSearch(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("huaId", ParamUtils.getString(request, "huaId"));
		paramMap.put("gbName", ParamUtils.getString(request, "gbName"));
		paramMap.put("buyTimeStart", ParamUtils.getString(request, "date01"));
		paramMap.put("buyTimeEnd", ParamUtils.getString(request, "date02"));
		paramMap.put("pcToEnvelopeMoney", ParamUtils.getString(request, "pcToEnvelopeMoney", null));
		String search = ParamUtils.getString(request, "search");
		
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		//分页信息
		if(search.isEmpty()) {
			paramMap.put("_begin", curPageIndex*pageSize);
			paramMap.put("_length", pageSize);
		}
		
		List<UserHasCardWithGroupBuildingNames> buyList = propertyCardService.qryUserHasPropertyCardList(paramMap);
		request.setAttribute("resList", buyList);	
		if(!search.isEmpty()) {
			request.setAttribute("resultSize", buyList.size());
		} else {
			request.setAttribute("resultSize", propertyCardService.qryUserHasPropertyCardCount(paramMap));
		}
		
	}
	
	/**
	 * user 代扣卡购买记录详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/cardBuyRecordDetail")
	public ModelAndView cardBuyRecordDetail(HttpServletRequest request){
		BigInteger userId = ParamUtils.getBigInteger(request, "userId", null);
		
		//用户累计金额
		
		UserHasPropertyCard userHasPropertyCard = propertyCardService.qrySumAmtByUserId(userId);
		userHasPropertyCard.settUserFId(userId);
		
		//划扣小区
		List<RealRoomConfig> rooms = propertyCardService.getPropfeeCanPayRoomByUserId(userId);
		
		//购买详细记录
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tUserFId", userId);
		String pageIndexName = new org.displaytag.util.ParamEncoder("row")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);// 页数的参数名
        int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
        int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ?
                0 : (Integer.parseInt(request.getParameter(pageIndexName))-1);
        PageModel pageModel = new PageModel((curPageIndex)*pageSize, pageSize);
		
		List<UserHasPropertyCard> resList = userHasPropertyCardBaseService.getUserHasPropertyCardByCondition(paramMap, pageModel);
		
		int resultSize = userHasPropertyCardBaseService.getUserHasPropertyCardCount(paramMap);
		
		request.setAttribute("resList", resList);
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("userHasPropertyCard", userHasPropertyCard);
		request.setAttribute("rooms", rooms);
		
		return new ModelAndView("/propertyCard/userBuyCardRecordDetail");
	}
	
	/**
	 * 开启/关闭用户小区代扣卡自动划扣配置
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editPropCardOpenStatus.json")
	public String editPropCardOpenStatus(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		BigInteger userId = ParamUtils.getBigInteger(request, "userId", null);
		String[] configs = request.getParameterValues("configs");
		
		try {
			propertyCardService.editPropCardOpenStatus(userId, configs);
		} catch (Exception e) {
			logger.info("=========营运平台保存自动划扣信息失败=====");
		}
		
		return JSON.toJSONString(jsonResponse);
	}
}
