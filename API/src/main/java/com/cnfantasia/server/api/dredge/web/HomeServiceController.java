package com.cnfantasia.server.api.dredge.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.ebuy.entity.EbuyAdvertise;
import com.cnfantasia.server.api.ebuy.service.IEbuyAdvertiseService;
import com.cnfantasia.server.api.operation.service.IAddressOperationService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.ParamUtils;

/**
 * 到家服务
 * @author yewj
 */
@RequestMapping("/homeService")
@Controller
public class HomeServiceController extends BaseController {
	
	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private ICommonRoomService commonRoomService;
	
	@Resource
	private IAddressOperationService addressOperationService;
	
	@Resource
	private IEbuyAdvertiseService ebuyAdvertiseService;
	
	/**
	 * 查询首页广告栏列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryAds.json")
	@ResponseBody
	public JsonResponse qryAds(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		String code = ParamUtils.getString(request, "code", "WX");
		Integer version = HeaderManager.getVersionNum();
		List<String> codeList = new ArrayList<>();
		codeList.add(code);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("codeList", codeList);
		paramMap.put("version", version);
		
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
		if(gbId == null) {//如果作为参数传过来gbId，则用，如果没传用请求头或默认
			gbId = commonRoomService.getGroupBuildingIdByUserIdForEbuy(UserContext.getOperIdBigInteger());
		}
		
		if(gbId == null) {
			gbId = RoomConstants.DEFAULT_GROUP_BUILDING_ID;
		}
		
		List<BigInteger> codeIdList = addressOperationService.getCodeIdList(null, null, null, null, gbId);
		paramMap.put("codeIdList", codeIdList);
		
		List<EbuyAdvertise> adList = ebuyAdvertiseService.getEbuyAdvertiseList(paramMap);
		if (DataUtil.isEmpty(adList)) {
			code = "WX_DEFAULT";
//			codeList.clear();
//			codeList.add(code);
			adList = ebuyAdvertiseService.getEbuyAdvertiseList("WX_DEFAULT", version);
		}
		List<EbuyAdvertise> adList2 = new ArrayList<EbuyAdvertise>();
		for(EbuyAdvertise ad : adList) {
			ad.setPicUrl(CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.AD_PIC_BASE_PATH, ad.getPicName(), ad.getUpdTime()));
			if(ad.getType() == 1 && version != null) {
//				if(ad.getLinkUrl().contains("?")) {
//					ad.setLinkUrl(ad.getLinkUrl() + "&version=" + version);
//				} else {
//					ad.setLinkUrl(ad.getLinkUrl() + "?version=" + version);
//				}
				ad.setLinkUrl(ad.getLinkUrl());
			}
			if(code.equals(ad.getCode())) {
				adList2.add(ad);
			}
		}
		
		jsonResponse.putData("list", adList2);
		return jsonResponse;
	}

	/**
	 * 根据传入的page参数跳转到相应的页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/index.html")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("/homeService/index");
		return modelAndView;
	}
	
	/**
	 * 根据传入的page参数跳转到相应的页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/serviceProcess.html")
	public ModelAndView serviceProcess(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("/homeService/serviceProcess");
		return modelAndView;
	}

}
