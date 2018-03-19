package com.cnfantasia.server.ms.activity.web;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domainbase.ebuyAdvertise.entity.EbuyAdvertise;
import com.cnfantasia.server.domainbase.ebuyAdvertise.service.IEbuyAdvertiseBaseService;
import com.cnfantasia.server.ms.activity.service.IActivityService;
import com.cnfantasia.server.ms.advertise.constants.AdvConstant;
import com.cnfantasia.server.ms.advertise.entity.AdvertiseDto;
import com.cnfantasia.server.ms.advertise.service.IAdvertiseForOmsService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

/**
 * 活动管理
 * 
 * @author liyulin
 * @version 1.0 2016年12月27日 下午2:40:16
 */
@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController {

	@Resource
	private IActivityService activityService;
	@Resource
	private IEbuyAdvertiseBaseService ebuyAdvertiseBaseService;
	@Resource
	private IAdvertiseForOmsService advertiseForOmsService;

	/**
	 * 列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/index.html")
	public ModelAndView index() {
		List<EbuyAdvertise> activities = activityService.selectActivities();
		int total = activities == null ? 0 : activities.size();

		ModelAndView mav = new ModelAndView();
		mav.addObject("activities", activities);
		mav.addObject("total", total);
		mav.setViewName("/activity/list");
		return mav;
	}

	/**
	 * 更新优先级
	 * 
	 * @param id
	 * @param order
	 * @return
	 */
	@RequestMapping("/updateOrder.html")
	@ResponseBody
	public JsonResponse updateOrder(BigInteger id, Long order){
		EbuyAdvertise ebuyAdvertise = new EbuyAdvertise();
		ebuyAdvertise.setId(id);
		ebuyAdvertise.setOrder(order);
		int affectedCount = ebuyAdvertiseBaseService.updateEbuyAdvertise(ebuyAdvertise);
		
		JsonResponse jsonResponse = new JsonResponse();
		if(affectedCount==0){
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			jsonResponse.setMessage("更新失败！");
		} else {
			jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
			jsonResponse.setMessage("更新成功！");
		}
		return jsonResponse;
	}
	
	/**
	 * 启用、停用
	 * 
	 * @param id
	 * @param state
	 * @return
	 */
	@RequestMapping("/updateState.html")
	@ResponseBody
	public JsonResponse updateState(BigInteger id, Integer state){
		EbuyAdvertise ebuyAdvertise = new EbuyAdvertise();
		ebuyAdvertise.setId(id);
		ebuyAdvertise.setSys0DelState(state);
		int affectedCount = ebuyAdvertiseBaseService.updateEbuyAdvertise(ebuyAdvertise);
		
		JsonResponse jsonResponse = new JsonResponse();
		if(affectedCount==0){
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			jsonResponse.setMessage("操作失败！");
		} else {
			jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
			jsonResponse.setMessage("操作成功！");
		}
		return jsonResponse;
	}
	
	@RequestMapping("/detailPage.html")
	public ModelAndView detailPage(BigInteger id, String pageType){
		EbuyAdvertise ebuyAdvertise = ebuyAdvertiseBaseService.getEbuyAdvertiseBySeqId(id);
		String[] pics = getPics(ebuyAdvertise.getPicName());
		
		List<Map<String, Object>> areas = advertiseForOmsService.getAdvAreaByAdvId(id, AdvConstant.EbSupplyType.Shou_Ye_Tan_Chuang);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("ebuyAdvertise", ebuyAdvertise);
		mav.addObject("appPic", (pics==null|| pics.length==0)?null:pics[0]);
		mav.addObject("previewPic", (pics!=null && pics.length==2)?pics[1]:null);
		String basePicPath = OmsSysParamManager.getImageServerUrl(PathConstants.Advertise_Pic_base) + PathConstants.Advertise_Pic_base;
		mav.addObject("basePicPath", basePicPath);
		mav.addObject("areas", areas);
		if (!DataUtil.isEmpty(areas)) {
            Map<String, Object> area = areas.get(0);
            if (area.get("gbId") != null && !"".equals(area.get("gbId")) && !"0".equals(area.get("gbId"))) {
                mav.addObject("areaType", 3);
            } else if (area.get("cityId") != null && !"".equals(area.get("cityId")) && !"0".equals(area.get("cityId"))) {
                mav.addObject("areaType", 2);
            } else {
                mav.addObject("areaType", 1);
            }
        }
		
		if("update".equals(pageType)){
			mav.setViewName("/activity/edit");
		} else {
			mav.setViewName("/activity/detail");
		}
		return mav;
	}
	
	private final String[] getPics(String picName){
		String[] pics = null;
		String appPic="", previewPic="";
		if(StringUtils.isNotBlank(picName)){
			pics = picName.split(";");// 顺序：app显示图片、预告图片
			
			if(pics.length>=1){
				appPic = pics[0];
			}
			if(pics.length==2){
				previewPic = pics[1];
			}
		}
		
		return new String[]{appPic, previewPic};
	}
	
	/**
	 * 更新活动
	 * 
	 * @param advertiseDto
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateActivity.html")
	public ModelAndView updateActivity(AdvertiseDto advertiseDto, HttpServletRequest request) {
		if(activityService.updateActivity(advertiseDto, request)){
			request.setAttribute(JSPConstants.OprtResult, "操作成功");
			request.setAttribute(JSPConstants.ToURL, "../activity/detailPage.html?pageType=detail&id="+advertiseDto.getEbuyAdvertise().getId());
		} else {
			request.setAttribute(JSPConstants.OprtResult, "操作失败");
			request.setAttribute(JSPConstants.ToURL, "../activity/detailPage.html?pageType=update&id="+advertiseDto.getEbuyAdvertise().getId());
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}
}
