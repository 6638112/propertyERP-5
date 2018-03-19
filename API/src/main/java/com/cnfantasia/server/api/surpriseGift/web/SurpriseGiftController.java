/**   
* Filename:    SurpriseGiftController.java   
* @version:    1.0  
* Create at:   2015年3月24日 上午7:57:29   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月24日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.surpriseGift.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commonBusiness.entity.RequestClientInfoEntity;
import com.cnfantasia.server.api.commonBusiness.entity.UserIdType;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonDeviceService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonUserService;
import com.cnfantasia.server.api.commonBusiness.util.EbuyChannelParseUtil;
import com.cnfantasia.server.api.commonBusiness.util.RequestParseUtil;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.prize.entity.PrizeResultDiscountEntity;
import com.cnfantasia.server.api.prize.service.IPrizeRecordTmpDataService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.surpriseGift.constant.SurpriseGiftConstant;
import com.cnfantasia.server.api.surpriseGift.entity.CouponCombiEntity;
import com.cnfantasia.server.api.surpriseGift.entity.PrizeSurpriseGiftEntity;
import com.cnfantasia.server.api.surpriseGift.entity.PrizeSurpriseGiftEntityDetail;
import com.cnfantasia.server.api.surpriseGift.service.ISurpriseGiftService;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.prizeSurpriseGift.entity.PrizeSurpriseGift;
import com.cnfantasia.server.domainbase.surpriseGiftConfigPic.entity.SurpriseGiftConfigPic;

/**
 * Filename:    SurpriseGiftController.java
 * @version:    1.0.0
 * Create at:   2015年3月24日 上午7:57:29
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月24日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/surpriseGift")
public class SurpriseGiftController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	
	private ISurpriseGiftService surpriseGiftService;
	public void setSurpriseGiftService(ISurpriseGiftService surpriseGiftService) {
		this.surpriseGiftService = surpriseGiftService;
	}
	
	private ICommonDeviceService commonDeviceService;
	public void setCommonDeviceService(ICommonDeviceService commonDeviceService) {
		this.commonDeviceService = commonDeviceService;
	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}
	
	private ICommonUserService commonUserService;
	public void setCommonUserService(ICommonUserService commonUserService) {
		this.commonUserService = commonUserService;
	}
	
	private IPrizeRecordTmpDataService prizeRecordTmpDataService;
	public void setPrizeRecordTmpDataService(IPrizeRecordTmpDataService prizeRecordTmpDataService) {
		this.prizeRecordTmpDataService = prizeRecordTmpDataService;
	}

	@RequestMapping("/qryTopLog.json")
	@ResponseBody
	public JsonResponse qryTopLog(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String srcTypeStr = request.getParameter("srcType");
		Long srcType = Long.valueOf(srcTypeStr);
		UserIdType userIdType = commonDeviceService.getUserIdType();
		BigInteger userId = userIdType.getUserId();
		Integer userType = userIdType.getUserType();
		//2.交互
		SurpriseGiftConfigPic tmpSurpriseGiftConfigPic = surpriseGiftService.qryTopLog(userId,userType,srcType,parseIsLightApp(request));
		//3.结果处理
		Map<String,Object> resMap = commEntityConvertService.surpriseGiftConfigPic2Map(tmpSurpriseGiftConfigPic);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * 其他途径获取意外惊喜
	 * @param request
	 * @return
	 */
	@RequestMapping("/doGetSurpriseGift.json")
	@ResponseBody
	public JsonResponse doGetSurpriseGift(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer fromType = Integer.valueOf(request.getParameter("fromType"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		Integer userType = LoginDict.UserRegistOrTmp.REGIST_USER;//目前只有注册用户可用获取意外惊喜
		
		String activeIdStr = request.getParameter("activeId");//来源Id
		BigInteger activeId = null;
		if(!StringUtils.isEmpty(activeIdStr)){
			activeId = new BigInteger(activeIdStr);
		}
		
		String cityName = request.getParameter("cityName");//城市名称
		//渠道信息
		RequestClientInfoEntity requestClientInfoEntity = new RequestClientInfoEntity(request);
		//2.交互
		PrizeSurpriseGiftEntity currPrizeSurpriseGift = null;
		if(RequestClientInfoEntity.checkSupriseGiftCanAdd(requestClientInfoEntity)){
			currPrizeSurpriseGift = surpriseGiftService.getSurpriseGiftDirect(userId,userType,fromType,activeId,parseIsLightApp(request),cityName);
		}
		//3.结果处理
//		Map<String,Object> tmpMap = commEntityConvertService.prizeSurpriseGift2Map(new PrizeSurpriseGift(new BigInteger("1001"), "解放区粮票", 2, null, 1680L, new BigInteger("50001"), LoginDict.UserRegistOrTmp.REGIST_USER, new BigInteger("666666"), "2015-4-7 10:42:51", 2, "2015-4-7 10:43:15", null, null, null, null, null, null, null, null, null, null));
		
//		currPrizeSurpriseGift = new PrizeSurpriseGiftEntity();
//		currPrizeSurpriseGift.setHbAmount(300L);
//		currPrizeSurpriseGift.setId(new BigInteger("5147267"));
//		currPrizeSurpriseGift.setDataType(1);
//		currPrizeSurpriseGift.setUseStatus(0);
		
		Map<String,Object> tmpMap = commEntityConvertService.prizeSurpriseGift2Map(currPrizeSurpriseGift);
		jsonResponse.setDataValue(tmpMap);
		return jsonResponse;
	}
	
	/**
	 * 标记意外惊喜奖品为已领取
	 * @param request
	 * @return
	 */
	@RequestMapping("/markSurpriseGiftAsFetched.json")
	@ResponseBody
	public JsonResponse markSurpriseGiftAsFetched(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger surpriseGiftId = ParamUtils.getBigInteger(request, "surpriseGiftId", null);
		BigInteger userId = UserContext.getOperIdBigInteger();
		Integer userType = LoginDict.UserRegistOrTmp.REGIST_USER;//目前只有注册用户可用获取意外惊喜
		//2.交互
		if(surpriseGiftId!=null&&userId!=null){
			surpriseGiftService.markPrizeSurpriseGiftAsFetched(userId, userType, surpriseGiftId,parseIsLightApp(request));
		}else{
			logger.info("surpriseGift.markSurpriseGiftAsFetched.json,surpriseGiftId is:"+surpriseGiftId+",userId is:"+userId);
			if(surpriseGiftId==null){
				throw new BusinessRuntimeException("surpriseGift.markSurpriseGiftAsFetched.surpriseGiftId.isEmpty");
			}else if(userId==null){
				throw new BusinessRuntimeException("surpriseGift.markSurpriseGiftAsFetched.userId.isEmpty");
			}
		}
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 查询优惠券列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryCouponList.json")
	@ResponseBody
	public JsonResponse qryCouponList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Long totalAmount = null;
		String totalAmountStr = request.getParameter("totalAmount");
		if(!StringUtils.isEmpty(totalAmountStr)){
			Double totalAmountDouble = Double.valueOf(totalAmountStr);
			totalAmount = PriceUtil.multiply100(totalAmountDouble);
		}
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		Integer userType = LoginDict.UserRegistOrTmp.REGIST_USER;//目前只有注册用户可用获取意外惊喜
		//2.交互
		CouponCombiEntity resCouponCombiEntity = surpriseGiftService.getCouponCombiData(userId, userType, totalAmount,parseJfq_SubType(request));
		//3.结果处理
		Map<String,Object> resMap = commEntityConvertService.couponCombiEntity2Map(resCouponCombiEntity);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * syl-add 2015-4-27 11:11:33 
	 * 增加判断解放区来源的细分 
	 */
	protected Integer parseJfq_SubType(HttpServletRequest request){
		return EbuyChannelParseUtil.parseJfq_SubType_ByHeader(request);
	}
	/**
	 * 判断是否为轻应用
	 * @param request
	 * @return true 为轻应用，false表示为app
	 */
	protected boolean parseIsLightApp(HttpServletRequest request){
		return EbuyChannelParseUtil.parseIsLightApp(request);
	}
	
	
	@RequestMapping("/qryGiftList.json")
	@ResponseBody
	public JsonResponse qryGiftList(HttpServletRequest request){
		return getQryGiftListResponse(request, null);
	}

	private JsonResponse getQryGiftListResponse(HttpServletRequest request, PageModel pageModel) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		if (pageModel == null) {
			pageModel = ControllerUtils.getPageModel(request);
		}

		Integer userType = LoginDict.UserRegistOrTmp.REGIST_USER;
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//BigInteger userId = new BigInteger("52585");

		//2.交互
//		List<PrizeSurpriseGift> prizeSurpriseGiftList = new ArrayList<PrizeSurpriseGift>();
//		prizeSurpriseGiftList.add(new PrizeSurpriseGift(new BigInteger("1001"), "解放区粮票", 2, null, 1680L, new BigInteger("50001"), LoginDict.UserRegistOrTmp.REGIST_USER, new BigInteger("666666"), "2015-4-7 10:42:51", 2, "2015-4-7 10:43:15", null, null, null, null, null, null, null, null, null, null));
//		prizeSurpriseGiftList.add(new PrizeSurpriseGift(new BigInteger("1002"), "解放区粮票", 2, null, 1680L, new BigInteger("50001"), LoginDict.UserRegistOrTmp.REGIST_USER, new BigInteger("666666"), "2015-4-7 10:42:51", 1, null, null, null, null, null, null, null, null, null, null, null));
		List<PrizeSurpriseGiftEntity> prizeSurpriseGiftList = surpriseGiftService.qryPrizeSurpriseGiftList(userId, userType, pageModel,parseIsLightApp(request));
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(prizeSurpriseGiftList!=null&&prizeSurpriseGiftList.size()>0){
			for(PrizeSurpriseGiftEntity tmpGift:prizeSurpriseGiftList){
				Map<String,Object> tmpMap = commEntityConvertService.prizeSurpriseGift2Map(tmpGift);
				resList.add(tmpMap);
			}
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	@RequestMapping("/qryGiftList.html")
//	@ResponseBody
	public ModelAndView qryGiftListHtml(HttpServletRequest request) {
		PageModel pageModel = new PageModel(0, 100);//先取100条
		request.setAttribute("giftList", JSON.toJSON(getQryGiftListResponse(request, pageModel).getDataValue()));
		return new ModelAndView("/prize/prize-list");
	}
	
	@RequestMapping("/prizeDetail.html")
	public ModelAndView prizeDetailHtml(HttpServletRequest request) {
		//1.搜集参数
		Integer userType = LoginDict.UserRegistOrTmp.REGIST_USER;
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		BigInteger detailId = ParamUtils.getBigInteger(request, "detailId", null);
		//2.交互
		PrizeSurpriseGiftEntityDetail detailEntity = surpriseGiftService.getPrizeSurpriseGiftDetail(userId, userType, detailId, parseIsLightApp(request));
		//3.结果处理
		Map<String,Object> gift = commEntityConvertService.prizeSurpriseGift2Map(detailEntity);
		gift.put("ext_valueCode", detailEntity.getTicketValue());
		gift.put("ext_useDesc", detailEntity.getUseDesc());
		gift.put("ext_linkUrl", detailEntity.getLinkUrl());
		
		request.setAttribute("gift", gift);
		return new ModelAndView("/prize/prizeDetail");
	}
	
	@RequestMapping("/prizeDetail.json")
	@ResponseBody
	public JsonResponse prizeDetailJson(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer userType = LoginDict.UserRegistOrTmp.REGIST_USER;
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		BigInteger detailId = ParamUtils.getBigInteger(request, "detailId", null);
		//2.交互
		PrizeSurpriseGiftEntityDetail detailEntity = surpriseGiftService.getPrizeSurpriseGiftDetail(userId, userType, detailId, parseIsLightApp(request));
		//3.结果处理
		Map<String,Object> gift = commEntityConvertService.prizeSurpriseGift2Map(detailEntity);
		gift.put("ext_valueCode", detailEntity.getTicketValue());
		gift.put("ext_useDesc", detailEntity.getUseDesc());
		gift.put("ext_linkUrl", detailEntity.getLinkUrl());
		jsonResponse.setDataValue(gift);
		return jsonResponse;
	}
	
	
	/**
	 * 查询消费券详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryGiftDetail.json")
	@ResponseBody
	public JsonResponse qryGiftDetail(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String giftIdStr = request.getParameter("giftId");
		BigInteger prizeSurpriseGiftId = new BigInteger(giftIdStr);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		Integer userType = LoginDict.UserRegistOrTmp.REGIST_USER;
		//2.交互
		PrizeSurpriseGiftEntity giftDetail = surpriseGiftService.getPrizeSurpriseGiftDetail(userId, userType, prizeSurpriseGiftId,parseIsLightApp(request));
		//3.结果处理
		Map<String,Object> resMap = new HashMap<String, Object>();
		if(giftDetail!=null){
			resMap = commEntityConvertService.prizeSurpriseGift2Map(giftDetail);
			String ext_userShowName = commonUserService.getUserShowNameById(giftDetail.getUserId());
			resMap.put("ext_userShowName", ext_userShowName);
		}
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	@RequestMapping("/toFlashPage.html")
	public ModelAndView toSupriseFlash(HttpServletRequest request){
		//1.搜集参数
		BigInteger supriseGiftId = null;
		{
			String supriseGiftIdStr = request.getParameter(SurpriseGiftConstant.Param_SupriseGiftId);
			if(!StringUtils.isEmpty(supriseGiftIdStr)){
				supriseGiftId = new BigInteger(supriseGiftIdStr);
			}
		}
		BigInteger userId = UserContext.getOperIdBigInteger();
		Integer userType = LoginDict.UserRegistOrTmp.REGIST_USER;
		//2.交互
		boolean isTmp = false;
		BigInteger toDealRecordId = supriseGiftId;
		NullValueAndHbAmount dealResult = dealNullValueAndHbAmount(toDealRecordId, userId, userType, request, isTmp);
		//3.结果处理
		return toAmazingPage(dealResult,request);
	}
	
	/**
	 * 临时意外惊喜页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toFlashPageTmp.html")
	public ModelAndView toFlashPageTmp(HttpServletRequest request){
		//1.搜集参数
		BigInteger toDealRecordId = null;
		{
			String supriseGiftIdStr = request.getParameter(SurpriseGiftConstant.Param_SupriseGiftId);
			if(!StringUtils.isEmpty(supriseGiftIdStr)){
				toDealRecordId = new BigInteger(supriseGiftIdStr);
			}
		}
		BigInteger userId = UserContext.getOperIdBigInteger();
		Integer userType = LoginDict.UserRegistOrTmp.REGIST_USER;
		//2.交互
		boolean isTmp = true;
		NullValueAndHbAmount dealResult = dealNullValueAndHbAmount(toDealRecordId, userId, userType, request, isTmp);
		//3.结果处理
		return toAmazingPage(dealResult, request);
	}
	
	private NullValueAndHbAmount dealNullValueAndHbAmount(BigInteger toDealRecordId,BigInteger userId,Integer userType,HttpServletRequest request,boolean isTmp){
		Long hbAmount = null;
		String nullValue = null;
		PrizeSurpriseGift prizeSurpriseGift = null;
		if(toDealRecordId!=null/*&&userId!=null&&userType!=null*/){
			if(!isTmp){
				BigInteger supriseGiftId = toDealRecordId;
//				surpriseGiftService.markPrizeSurpriseGiftAsFetched(userId, userType, supriseGiftId,parseIsLightApp(request));//标记为已领取,在点击领取的时候标记为领取
				prizeSurpriseGift = surpriseGiftService.getPrizeSurpriseGiftDetail(userId, userType, supriseGiftId,parseIsLightApp(request));
			}else{
				//标记意外惊喜为正式表，
				PrizeResultDiscountEntity prizeResultDiscountEntity = prizeRecordTmpDataService.transferTmpPrizeData2FormalForSuprise(toDealRecordId);
				if(prizeResultDiscountEntity!=null){
					BigInteger supriseGiftId = prizeResultDiscountEntity.getPrizeSurpriseGift().getId();
//					surpriseGiftService.markPrizeSurpriseGiftAsFetched(userId, userType, supriseGiftId,parseIsLightApp(request));//标记为已领取,在点击领取的时候标记为领取
					prizeSurpriseGift = surpriseGiftService.getPrizeSurpriseGiftDetail(userId, userType, supriseGiftId,parseIsLightApp(request));
				}
			}
			if(prizeSurpriseGift==null){
				nullValue = "0.0";//0.0表示supriseGiftId不为空,但是详情为空
			}else{
				hbAmount = prizeSurpriseGift.getHbAmount();
				if(hbAmount==null){
					nullValue = "0.00";//0.00表示prizeSurpriseGift不为空,但是粮票金额为空
				}
			}
		}else{
			nullValue = "0";//0表示supriseGiftId为空
		}
		if(userId==null&&nullValue!=null){
			nullValue = nullValue+"_";
		}
		return new NullValueAndHbAmount(hbAmount, nullValue,prizeSurpriseGift);
	}
	
	private ModelAndView toAmazingPage(NullValueAndHbAmount dealResult,HttpServletRequest request){
		Long hbAmount = dealResult.getHbAmount();
		String nullValue = dealResult.getNullValue();
		BigInteger supriseGiftId = dealResult.getSupriseGiftId();
		ModelAndView mav = new ModelAndView();
		request.setAttribute("hbAmount", hbAmount==null?nullValue:PriceUtil.div100(hbAmount));
		request.setAttribute("giftId", supriseGiftId);
		mav.setViewName("/surpriseGift/amazing");
		return mav;
	}
	
	@ExceptionHandler(Exception.class)
	// @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseStatus(value = HttpStatus.OK)
	@Override
	public Object handleException(Exception ex, HttpServletRequest request) {
		{
			String reqUrl = RequestParseUtil.parseUrlFromRequest(request);
			if(reqUrl!=null&&reqUrl.equals("/surpriseGift/toFlashPage.html")){
				request.setAttribute("hbAmount", 0);
				ModelAndView mav = new ModelAndView();
				mav.setViewName("/surpriseGift/amazing");
				return mav;
			}
		}
		return super.handleException(ex, request);
	}
	
}
