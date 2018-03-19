package com.cnfantasia.server.ms.limitBuyActivity.web;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.limitBuy.contant.LimitBuyDict;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.limitBuyActivity.entity.LimitBuyActivity;
import com.cnfantasia.server.ms.limitBuyActivity.entity.LimitBuyActivityDetailDto;
import com.cnfantasia.server.ms.limitBuyActivity.entity.LimitBuyActivityListDto;
import com.cnfantasia.server.ms.limitBuyActivity.entity.LimitBuyActivityUpdateParam;
import com.cnfantasia.server.ms.limitBuyActivity.entity.QryParam;
import com.cnfantasia.server.ms.limitBuyActivity.service.ILimitBuyActivityService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.MapConverter;

/**
 * 限时购
 * 
 * @author liyulin
 * @version 1.0 2016年12月28日 下午6:28:24
 */
@Controller
@RequestMapping("/limitBuyActivity")
public class LimitBuyActivityController extends BaseController{
	
	@Resource
	private ILimitBuyActivityService limitBuyActivityService;
	@Resource
	private IUuidManager uuidManager;

	/**
	 * 限时购列表
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/index.html")
	public ModelAndView index(QryParam param, HttpServletRequest request){
		Map<String, Object> paramMap = MapConverter.toMap(param);
		int total = limitBuyActivityService.selectLimitBuyActivityForCount(paramMap);
		
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		// 分页信息
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		List<LimitBuyActivityListDto> labs = limitBuyActivityService.selectLimitBuyActivityForPage(paramMap);
		for(LimitBuyActivityListDto lab:labs){
			lab.setOriginalPrice(lab.getOriginalPrice().divide(BigDecimal.valueOf(100)));
			lab.setRobPrice(lab.getRobPrice().divide(BigDecimal.valueOf(100)));
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("labs", labs);
		mav.addObject("total", total);
		mav.setViewName("/limitBuyActivity/list");
		return mav;
	}
	
	/**
	 * 进入添加限时购页面
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping("/addPage.html")
	public ModelAndView addPage(BigInteger productId){
		LimitBuyActivityDetailDto lba = limitBuyActivityService.selectLimitBuyActivityForBaseDetail(productId);
		lba.setOriginalPrice(lba.getOriginalPrice().divide(BigDecimal.valueOf(100)));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("lba", lba);
		mav.setViewName("/limitBuyActivity/add");
		return mav;
	}
	
	@RequestMapping("/addLimitBuyActivity.html")
	public ModelAndView addLimitBuyActivity(LimitBuyActivityUpdateParam param, HttpServletRequest request){
	    ModelAndView mav = new ModelAndView();
		mav.setViewName(JSPConstants.OprtSuccessPage);   

		long limitNumber = ParamUtils.getInt(request, "limitNumber", -1);//促销每人限够数量
		long leftCount = ParamUtils.getInt(request, "leftCount", 0);//促销库存

		if (leftCount <= 0) {
			request.setAttribute(JSPConstants.OprtResult, "操作失败");
			request.setAttribute(JSPConstants.ToURL, "../limitBuyActivity/addPage.html?productId=" + param.getId());
			return mav;
		}
	        
		BigInteger id = uuidManager.getNextUuidBigInteger(SEQConstants.t_limit_buy_activity);
		LimitBuyActivity limitBuyActivity = new LimitBuyActivity();
		limitBuyActivity.setId(id);
		limitBuyActivity.setTitle(param.getTitle());
		limitBuyActivity.settEbuyProductFId(param.getId());
		limitBuyActivity.setPositionType(LimitBuyDict.PositionType.IN_PUB_STORE);
		limitBuyActivity.setActivityPrice(param.getRobPrice().multiply(new BigDecimal("100")).longValue());
		limitBuyActivity.setPriceDiscount(param.getOriginalPrice().multiply(new BigDecimal("100")).longValue());
		limitBuyActivity.setActivityStartTime(param.getStartTime());
		limitBuyActivity.setActivityEndTime(param.getEndTime());
		limitBuyActivity.setSys0AddTime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
		limitBuyActivity.setSys0AddUser(UserContext.getOperIdBigIntegerMustExist());
		
		limitBuyActivity.setLeftCount(leftCount);
		limitBuyActivity.setMaxCanBuy(limitNumber);
		
		int affectedCount = limitBuyActivityService.insertLimitBuyActivity(limitBuyActivity);
		if(affectedCount==0){
			request.setAttribute(JSPConstants.OprtResult, "操作失败");
			request.setAttribute(JSPConstants.ToURL, "../limitBuyActivity/addPage.html?productId="+param.getId());
		} else {
			request.setAttribute(JSPConstants.OprtResult, "操作成功");
			request.setAttribute(JSPConstants.ToURL, "../limitBuyActivity/index.html");
		}
		
		
		return mav;
	}
	
	/**
	 * 查看详情
	 * 
	 * @param lbaId
	 * @param pageType
	 * @return
	 */
	@RequestMapping("/detailPage.html")
	public ModelAndView detailPage(BigInteger lbaId, String pageType){
		LimitBuyActivityDetailDto lba = limitBuyActivityService.selectLimitBuyActivityForDetail(lbaId);
		
		lba.setOriginalPrice(lba.getOriginalPrice().divide(BigDecimal.valueOf(100)));
		lba.setRobPrice(lba.getRobPrice().divide(BigDecimal.valueOf(100)));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("lba", lba);
		if("update".equals(pageType)){
			mav.setViewName("/limitBuyActivity/edit");
		} else {
			mav.setViewName("/limitBuyActivity/detail");
		}
		return mav;
	}
	
	/**
	 * 更新
	 * @param limitBuyActivity
	 * @return
	 */
	@RequestMapping("/updateLimitBuyActivity.html")
	public ModelAndView updateLimitBuyActivity(LimitBuyActivityUpdateParam param, HttpServletRequest request){
		LimitBuyActivity limitBuyActivity = new LimitBuyActivity();
		limitBuyActivity.setId(param.getId());
		limitBuyActivity.setTitle(param.getTitle());
		limitBuyActivity.setActivityPrice(param.getRobPrice().multiply(new BigDecimal("100")).longValue());
		limitBuyActivity.setActivityStartTime(param.getStartTime());
		limitBuyActivity.setActivityEndTime(param.getEndTime());
		limitBuyActivity.setSys0UpdTime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
		limitBuyActivity.setSys0UpdUser(UserContext.getOperIdBigIntegerMustExist());
		
		int affectedCount = limitBuyActivityService.updateLimitBuyActivity(limitBuyActivity);
		if(affectedCount==0){
			request.setAttribute(JSPConstants.OprtResult, "操作失败");
			request.setAttribute(JSPConstants.ToURL, "../limitBuyActivity/detailPage.html?pageType=update&lbaId="+param.getId());
		} else {
			request.setAttribute(JSPConstants.OprtResult, "操作成功");
			request.setAttribute(JSPConstants.ToURL, "../limitBuyActivity/detailPage.html?pageType=detail&lbaId="+param.getId());
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(JSPConstants.OprtSuccessPage);
		return mav;
	}
	
	/**
	 * 结束
	 * @param limitBuyActivity
	 * @return
	 */
	@RequestMapping("/finishLimitBuyActivity.html")
	@ResponseBody
	public JsonResponse finishLimitBuyActivity(BigInteger lbaId){
		String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
		LimitBuyActivity limitBuyActivity = new LimitBuyActivity();
		limitBuyActivity.setId(lbaId);
		limitBuyActivity.setActivityEndTime(now);
		limitBuyActivity.setSys0UpdTime(now);
		limitBuyActivity.setSys0UpdUser(UserContext.getOperIdBigIntegerMustExist());
		
		JsonResponse jsonResponse = new JsonResponse();
		int affectedCount = limitBuyActivityService.updateLimitBuyActivity(limitBuyActivity);
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
	 * 从首页移除或放入首页
	 * @param limitBuyId
	 * @param positionType
     * @return
     */
	@RequestMapping(value = "/resetPosition.html", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse resetPosition(BigInteger limitBuyId, Integer positionType) {
		LimitBuyActivity activity = new LimitBuyActivity();
		activity.setId(limitBuyId);
		activity.setPositionType(positionType);
		limitBuyActivityService.updateLimitBuyActivity(activity);
		return new JsonResponse();
	}
}
