package com.cnfantasia.server.api.prize.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.achievement.constant.AchievementConstant;
import com.cnfantasia.server.api.achievement.service.IAchievementService;
import com.cnfantasia.server.api.commonBusiness.entity.RequestClientInfoEntity;
import com.cnfantasia.server.api.commonBusiness.entity.StartEndDate;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonDeviceService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonPrizeService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonUserService;
import com.cnfantasia.server.api.commonBusiness.util.EbuyChannelParseUtil;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.prize.entity.PrizeCheckParamEntity;
import com.cnfantasia.server.api.prize.entity.PrizeCountWithUserEntity;
import com.cnfantasia.server.api.prize.entity.PrizeFortunesEntity;
import com.cnfantasia.server.api.prize.entity.PrizeIgnoreUsedEntity;
import com.cnfantasia.server.api.prize.entity.PrizeRecordEntity;
import com.cnfantasia.server.api.prize.entity.PrizeResultDiscountEntity;
import com.cnfantasia.server.api.prize.entity.PrizeResultDiscountEntityExtend;
import com.cnfantasia.server.api.prize.entity.PrizeStateEntity;
import com.cnfantasia.server.api.prize.entity.SignalPrizeResult;
import com.cnfantasia.server.api.prize.entity.UserWithLeastDiscountEntity;
import com.cnfantasia.server.api.prize.service.IPrizeService;
import com.cnfantasia.server.api.prizeRule.service.IPrizeRuleManager;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.constant.DictConstants;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.surpriseGift.entity.PrizeSurpriseGiftEntity;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.api.version.util.VersionTransferUtil;
import com.cnfantasia.server.business.pub.CommControllerUtils;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.commbusi.plotproperty.entity.BusinessMonthYearFactory;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;
import com.cnfantasia.server.commbusi.plotproperty.entity.ISectionDateMulti;
import com.cnfantasia.server.commbusi.plotproperty.entity.MonthOrTime;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.achievement.entity.Achievement;
import com.cnfantasia.server.domainbase.prizeRecord.entity.PrizeRecord;
import com.cnfantasia.server.domainbase.prizeRecordTmpData.entity.PrizeRecordTmpData;
import com.cnfantasia.server.domainbase.userTmp.entity.UserTmp;
@Controller
@RequestMapping("/prize")
public class PrizeController extends BaseController{
//	private ConcurrentHashMap<BigInteger,Long> doPrizeWithConfirmMap = new ConcurrentHashMap<BigInteger, Long>();
	
	private IPrizeService prizeService;
	public void setPrizeService(IPrizeService prizeService) {
		this.prizeService = prizeService;
	}
	
	private ICommonPrizeService commonPrizeService;
	public void setCommonPrizeService(ICommonPrizeService commonPrizeService) {
		this.commonPrizeService = commonPrizeService;
	}
	
	private ICommonUserService commonUserService;
	public void setCommonUserService(ICommonUserService commonUserService) {
		this.commonUserService = commonUserService;
	}
	
	private IPrizeRuleManager prizeRuleManager;
	public void setPrizeRuleManager(IPrizeRuleManager prizeRuleManager) {
		this.prizeRuleManager = prizeRuleManager;
	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}
	
	private IAchievementService achievementService;
	public void setAchievementService(IAchievementService achievementService) {
		this.achievementService = achievementService;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private ICommonDeviceService commonDeviceService;
	public void setCommonDeviceService(ICommonDeviceService commonDeviceService) {
		this.commonDeviceService = commonDeviceService;
	}
	
//	private IPrizeRecordTmpDataService prizeRecordTmpDataService;
//	public void setPrizeRecordTmpDataService(IPrizeRecordTmpDataService prizeRecordTmpDataService) {
//		this.prizeRecordTmpDataService = prizeRecordTmpDataService;
//	}

	@RequestMapping("/freshTmpData.json")
	@ResponseBody
	public JsonResponse freshTmpData(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//2.交互
//		prizeRecordTmpDataService.autoFreshTmpPrizeData2Formal();
		//3.结果处理
		return jsonResponse;
	}
	
	@RequestMapping("/doPrize.json")
	@ResponseBody
	public JsonResponse doPrize(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		SignalPrizeResult signalPrizeResult = null;
		RequestClientInfoEntity requestClientInfoEntity = new RequestClientInfoEntity(request);
		//用户信息
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		Boolean isRegist = null;
		if(userId==null){
			String deviceId = HeaderManager.getDeviceId();//设备Id
			Long deviceType = HeaderManager.getSubChannelIdLong();
			isRegist = commonUserService.isRegistByDeviceInfo(deviceId, deviceType);//未登录用户需要查询设备是否已被注册
//			signalPrizeResult = prizeService.doPrizeNotLogin(deviceId,deviceType,requestClientInfoEntity,EbuyChannelParseUtil.parseIsLightApp(request));
			UserTmp userTmp =commonDeviceService.checkAndCreate(deviceId, deviceType);
			signalPrizeResult = prizeService.doPrizeNotLogin(userTmp,requestClientInfoEntity,EbuyChannelParseUtil.parseIsLightApp(request));
		}else{
			signalPrizeResult = prizeService.doPrizeIsLoginWithDefault(userId,requestClientInfoEntity,EbuyChannelParseUtil.parseIsLightApp(request));
			if(signalPrizeResult!=null&&signalPrizeResult.getIsLeastDiscount()!=null&&signalPrizeResult.getIsLeastDiscount()==true){//如果为最低折扣 则给家里人推送消息
				prizeService.notifyFamilyLeastDiscount(userId,signalPrizeResult.getDiscount());
			}
		}
		//3.结果处理
		appendResultDataForDoPrize(jsonResponse, isRegist, signalPrizeResult,request);
		doLeastDiscount(request, jsonResponse);//syl-upd 2014-9-11 18:20:45
		return jsonResponse;
	}
	private void appendResultDataForDoPrize(JsonResponse jsonResponse,Boolean isRegist,SignalPrizeResult signalPrizeResult,HttpServletRequest request){
		jsonResponse.putData("isRegist", isRegist);//是否已经被注册
		jsonResponse.putData("discount", signalPrizeResult.getDiscount());
		jsonResponse.putData("leftCount", signalPrizeResult.getLeftCount());
		jsonResponse.putData("leastDiscount", signalPrizeResult.getLeastDiscount());
		if(isLeastDiscountIgnoreUsed(request)){//新版本判断最低折扣的方式
			PrizeIgnoreUsedEntity ignoreEntity = signalPrizeResult.getIgnoreUsedEntity();
			//折扣是否已使用 isUsed (boolean 是 已使用，否 未使用)，
			//折扣使用描述 useDesc(String)
			//isLeastDiscount
			jsonResponse.putData("isUsed", ignoreEntity.isUsed());
			jsonResponse.putData("useDesc", ignoreEntity.getDesc());
			jsonResponse.putData("isLeastDiscount", ignoreEntity.isLeastDiscount());
		}else{
			jsonResponse.putData("isLeastDiscount", signalPrizeResult.getIsLeastDiscount());
		}
		
		jsonResponse.putData("savedHbMoney", signalPrizeResult.getSavedHbMoney()==null?null:PriceUtil.div100(signalPrizeResult.getSavedHbMoney()));
		jsonResponse.putData("prizeTimeLong", signalPrizeResult.getPrizeTime());//抽奖时间
		
		{//意外惊喜的处理
			Object surpriseGift = signalPrizeResult.getPrizeResultDiscountEntity().getBaseSurpriseGift();
			if(surpriseGift instanceof PrizeSurpriseGiftEntity){
				PrizeSurpriseGiftEntity tmpEntity = (PrizeSurpriseGiftEntity)surpriseGift;
				jsonResponse.putData("prizeSurpriseGift", commEntityConvertService.prizeSurpriseGift2Map(tmpEntity));//意外惊喜信息
			}else if(surpriseGift instanceof PrizeRecordTmpData){
				PrizeRecordTmpData tmpEntity = (PrizeRecordTmpData)surpriseGift;
				jsonResponse.putData("prizeSurpriseGift", commEntityConvertService.prizeSurpriseGift2Map(tmpEntity));//意外惊喜信息
			}
		}
		
		jsonResponse.putData("topLogoInfo", commEntityConvertService.surpriseGiftConfigPic2Map(signalPrizeResult.getSurpriseGiftConfigPic()));//首页图标信息
		
		PrizeResultDiscountEntity prizeResultDiscountEntity = signalPrizeResult.getPrizeResultDiscountEntity();
		if(prizeResultDiscountEntity!=null&&prizeResultDiscountEntity instanceof PrizeResultDiscountEntityExtend){
			PrizeResultDiscountEntityExtend discountEntityExtend = (PrizeResultDiscountEntityExtend)prizeResultDiscountEntity;
			jsonResponse.putData("currPrizeNum", discountEntityExtend.getPrizePreConditionValidateResult().fetchCurrPrizeCount());//当前抽奖对应第几次
			jsonResponse.putData("lastDiscount", discountEntityExtend.getLastDiscount());//上次抽奖折扣信息
			jsonResponse.putData("tmpPrizeRecordId", discountEntityExtend.getTmpPrizeRecordId());//临时抽奖记录Id
		}
		
	}
	
	@RequestMapping("/doPrizeWithConfirm.json")
	@ResponseBody
	public JsonResponse doPrizeWithConfirm(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		SignalPrizeResult signalPrizeResult = null;
		RequestClientInfoEntity requestClientInfoEntity = new RequestClientInfoEntity(request);
		//用户信息
		BigInteger userId = UserContext.getOperIdBigInteger();
//		if(userId!=null){
//			doPrizeWithConfirmMap.put(userId, new Date().getTime()/2000);
//		}
		PrizeCheckParamEntity prizeCheckParam = null;
		{//确认机制相关信息
			Integer confirmPrizeNum = null;//确认上次对应抽奖的编号,可为空
			{
				String confirmPrizeNumStr = request.getParameter("confirmPrizeNum");
				if(!StringUtils.isEmpty(confirmPrizeNumStr)){
					confirmPrizeNum = Integer.valueOf(confirmPrizeNumStr);
				}
			}
			
			
			BigInteger confirmRoomId = null;//对应抽奖编号的门牌Id,可为空
			{
				String confirmRoomIdStr = request.getParameter("confirmRoomId");
				if(!StringUtils.isEmpty(confirmRoomIdStr)){
					confirmRoomId = new BigInteger(confirmRoomIdStr);
				}
			}
			
			String confirmPrizeTime = null;//对应抽奖编号的抽奖时间,可为空
			{
				confirmPrizeTime = request.getParameter("confirmPrizeTime");
			}
			
			Integer confirmPrizeStatus = null;//对应抽奖编号的处理结果,可为空
			{
				String confirmPrizeStatusStr = request.getParameter("confirmPrizeStatus");
				if(!StringUtils.isEmpty(confirmPrizeStatusStr)){
					confirmPrizeStatus = Integer.valueOf(confirmPrizeStatusStr);
				}
			}
			
			BigInteger confirmPrizeRecordId = null;//对应抽奖编号的抽奖记录Id,可为空
			{
				String confirmPrizeRecordIdStr = request.getParameter("confirmPrizeRecordId");
				if(!StringUtils.isEmpty(confirmPrizeRecordIdStr)){
					confirmPrizeRecordId = new BigInteger(confirmPrizeRecordIdStr);
				}
			}
			
			Integer currPrizeNum = null;
			{
				String currPrizeNumStr = request.getParameter("currPrizeNum");
				if(!StringUtils.isEmpty(currPrizeNumStr)){
					currPrizeNum =Integer.valueOf(currPrizeNumStr);//当前请求抽奖对应第几次
				}
			}
			
			prizeCheckParam = new PrizeCheckParamEntity(confirmPrizeNum, confirmRoomId, confirmPrizeTime, confirmPrizeStatus, confirmPrizeRecordId, currPrizeNum);
		}
		//2.交互
		Boolean isRegist = null;
		if(userId==null){
			String deviceId = HeaderManager.getDeviceId();//设备Id
			Long deviceType = HeaderManager.getSubChannelIdLong();
			isRegist = commonUserService.isRegistByDeviceInfo(deviceId, deviceType);//未登录用户需要查询设备是否已被注册
			UserTmp userTmp =commonDeviceService.checkAndCreate(deviceId, deviceType);
			signalPrizeResult = prizeService.doPrizeNotLogin(userTmp,requestClientInfoEntity,EbuyChannelParseUtil.parseIsLightApp(request),prizeCheckParam);
//			signalPrizeResult = prizeService.doPrizeNotLogin(deviceId,deviceType,requestClientInfoEntity,EbuyChannelParseUtil.parseIsLightApp(request),prizeCheckParam);
		}else{
			signalPrizeResult = prizeService.doPrizeIsLoginWithDefault(userId,requestClientInfoEntity,EbuyChannelParseUtil.parseIsLightApp(request),prizeCheckParam);
			if(signalPrizeResult!=null&&signalPrizeResult.getIsLeastDiscount()!=null&&signalPrizeResult.getIsLeastDiscount()==true){//如果为最低折扣 则给家里人推送消息
				prizeService.notifyFamilyLeastDiscount(userId,signalPrizeResult.getDiscount());
			}
		}
		//3.结果处理
		appendResultDataForDoPrize(jsonResponse, isRegist, signalPrizeResult,request);
		return jsonResponse;
	}
	
	@RequestMapping("/qryPrizeList.json")
	@ResponseBody
	@Deprecated
	public JsonResponse qryPrizeList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		//用户信息
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//分页信息
		PageModel pageModel = ControllerUtils.getPageModel(request);
		//2.交互
		List<PrizeRecord> tmpList =prizeService.getPrizeRecordOrderByPrizeTime(userId,pageModel);
		//3.结果处理
		LinkedList<Map<String,Object>> resList = new LinkedList<Map<String,Object>>();
		double mimiDiscount = prizeRuleManager.getPrizeDiscountMax().doubleValue();
		Map<String,Object> mimiMap = null;
		for(PrizeRecord tmp:tmpList){
			Map<String,Object> rec01 = commEntityConvertService.prizeRecord2Map(tmp, null, null);
			resList.add(rec01);
			if(tmp.getDiscount()<=mimiDiscount){
				mimiDiscount = tmp.getDiscount();
				mimiMap = rec01;
			}
		}
		if(mimiMap!=null){
			resList.remove(mimiMap);
			resList.addFirst(mimiMap);
		}
//		Collections.sort(tmpList, new PrizeRecordComparator());
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	/**
	 * 查询家庭运势抽奖列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryPrizeListFortunesByMonthYear.json")
	@ResponseBody
	public JsonResponse qryPrizeListFortunesByMonthYear(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String yearMonth = CommControllerUtils.getYearMonth(request);
		if(StringUtils.isEmpty(yearMonth)){
			throw new BusinessRuntimeException("prizeController.qryPrizeListFortunes.month.empty");
		}
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		IBusinessMonthYear monthYearWithGB = BusinessMonthYearFactory.newInstance(yearMonth, commonRoomService.getGroupBuildingByUserId(userId), MonthOrTime.month);
		PrizeFortunesEntity prizeFortunesEntity = prizeService.getPrizeFortunesByMonthYear(userId,monthYearWithGB);
		List<PrizeRecordEntity> prizeRecordEntityList = prizeFortunesEntity.getPrizeList();//本月折扣列表
		PrizeRecordEntity leastPrizeRecord = prizeFortunesEntity.getLeastPrizeRecord();//最低折扣记录
		ISectionDateMulti propStartEndDate = prizeFortunesEntity.getPropStartEndDate();//物业周期起止时间
		PrizeCountWithUserEntity maxPrizeCountUserEntity = prizeFortunesEntity.getMaxPrizeCountUserEntity();//最大抽奖次数用户及次数信息
		PrizeCountWithUserEntity currPrizeCountUser = prizeFortunesEntity.getCurrPrizeCountUser();//当前用户本月抽奖次数
		StartEndDate firstLastPropMonth = prizeFortunesEntity.getFirstLastPropMonth();//门牌所有抽奖对应的物业起止月份
		//3.结果处理
		Map<String,Object> resMap = new HashMap<String, Object>();
		{//prizeList isMini 
			List<Map<String,Object>> prizeList = new ArrayList<Map<String,Object>>();
			for(PrizeRecordEntity tmpPrizeRecord:prizeRecordEntityList){
				Map<String,Object> tmpPrize = commEntityConvertService.prizeRecord2Map(tmpPrizeRecord, tmpPrizeRecord.getPrizeUser(), tmpPrizeRecord.hasUsed());
				boolean isLeastDiscount = false;
				if(leastPrizeRecord!=null&&leastPrizeRecord.getId()!=null&&tmpPrizeRecord.getId()!=null
						&&leastPrizeRecord.getId().compareTo(tmpPrizeRecord.getId())==0){
					isLeastDiscount = true;
				}
				tmpPrize.put("ext_isLeastDiscount", isLeastDiscount);//最低折扣
				prizeList.add(tmpPrize);
			}
			resMap.put("prizeList", prizeList);
		}
		{//当前年月对应的物业周期月份
			resMap.put("propStartDayLong", propStartEndDate==null?null:propStartEndDate.getStartDate().getTime());
			resMap.put("propEndDayLong", propStartEndDate==null?null:propStartEndDate.getEndDate().getTime());
//			resMap.put("propStartDayLong", propStartEndDate==null?null:propStartEndDate.getStartYearMonthDayLong());
//			resMap.put("propEndDayLong", propStartEndDate==null?null:propStartEndDate.getEndYearMonthDayLong());
		}
		{//第一次和最后一次抽奖时间
			resMap.put("firstPrizeTimeLong", firstLastPropMonth==null?null:firstLastPropMonth.getStartYearMonthDayLong());
			resMap.put("lastPrizeTimeLong", firstLastPropMonth==null?null:firstLastPropMonth.getEndYearMonthDayLong());
		}
		{//maxCountUserList 次数 多了多少次 称号
			List<Map<String,Object>> maxCountUserList = new ArrayList<Map<String,Object>>();
			Long currUserPrizeCount = (currPrizeCountUser==null||currPrizeCountUser.getPrizeCount()==null)?0:currPrizeCountUser.getPrizeCount();
			if(maxPrizeCountUserEntity!=null&&maxPrizeCountUserEntity.getPrizeCount()!=null){
				Map<String,Object> tmpUser = commEntityConvertService.baseUser2Map(maxPrizeCountUserEntity);
				tmpUser.put("ext_doneCount", maxPrizeCountUserEntity.getPrizeCount());
				tmpUser.put("ext_doneMoreThenCount", maxPrizeCountUserEntity.getPrizeCount()-currUserPrizeCount);
				{//称号信息
					Achievement tmpAchievement = achievementService.getAchievementById(AchievementConstant.Honour_maxPrizeCountBee_id);
					Map<String, Object> honourMap = commEntityConvertService.achievement2MapForPrizeHonour(tmpAchievement);
					if(honourMap!=null){
//					tmpUser.put("ext_honour_name", "勤劳蜜蜂");
//					tmpUser.put("ext_honour_icon_url", "http://b.jpg");
						tmpUser.putAll(honourMap);
					}
				}
				maxCountUserList.add(tmpUser);
			}
			resMap.put("maxCountUserList", maxCountUserList);
		} 
		{//leastDiscountUserList 最低折扣取值 称号
			List<Map<String,Object>> leastDiscountUserList = new ArrayList<Map<String,Object>>();
			UserSimpleEntity leastDiscountUser = leastPrizeRecord==null?null:leastPrizeRecord.getPrizeUser();//最低折扣用户
			if(leastPrizeRecord!=null&&leastDiscountUser!=null){
				Map<String,Object> tmpUser = commEntityConvertService.baseUser2Map(leastDiscountUser);
				tmpUser.put("ext_leastDiscountValue", leastPrizeRecord.getDiscount());
				{//称号信息
					Achievement tmpAchievement = achievementService.getAchievementById(AchievementConstant.Honour_leastDiscountPrizer_id);
					Map<String, Object> honourMap = commEntityConvertService.achievement2MapForPrizeHonour(tmpAchievement);
					if(honourMap!=null){
//						tmpUser.put("ext_honour_name", "幸运神手指");
//						tmpUser.put("ext_honour_icon_url", "http://a.jpg");
						tmpUser.putAll(honourMap);
					}
				}
				leastDiscountUserList.add(tmpUser);
			}
			resMap.put("leastDiscountUserList", leastDiscountUserList);
		}
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
//	{
//		JsonResponse jsonResponse = new JsonResponse();
//		Map<String,Object> resMap = new HashMap<String, Object>();
//		UserSimpleEntity user = new UserSimpleEntity();
//		user.setId(new BigInteger("1"));
//		user.setNickName("小明");
//		user.setHuaId("99832");
//		user.setImgprofile("400062014-06-26_14_11_39.jpg");
//		{//prizeList isMini 
//			List<Map<String,Object>> prizeList = new ArrayList<Map<String,Object>>();
//			for(int i=0;i<30;i++){
//				PrizeRecord tmpRecord =new PrizeRecord(new BigInteger(i+""), "2015-1-"+i+" 15:37:45", null, (int)(Math.random() * 100)/10.0, null, null, null, null, null, null, null, null, null, null, null, null, null);
//				Map<String,Object> tmpPrize = commEntityConvertService.prizeRecord2Map(tmpRecord, user, false);
//				if(i==20){
//					tmpPrize.put("ext_isLeastDiscount", true);//最低折扣
//				}else{
//					tmpPrize.put("ext_isLeastDiscount", false);//最低折扣
//				}
//				prizeList.add(tmpPrize);
//			}
//			resMap.put("prizeList", prizeList);
//		}
//		{//当前年月对应的物业周期月份
//			StartEndDate startEndDate = commonPrizeService.getPreMonthStartEndByMonth(userId, yearMonth);
//			resMap.put("propStartDayLong", startEndDate.getEndYearMonthDayLong());
//			resMap.put("propEndDayLong", startEndDate.getEndYearMonthDayLong());
//		}
//		{//第一次和最后一次抽奖时间
//			resMap.put("firstPrizeTimeLong", DateUtil.timeToLong("2014-07-01 17:10:48"));
//			resMap.put("lastPrizeTimeLong", DateUtil.timeToLong("2015-2-1 17:10:48"));
//		}
//		{//maxCountUserList 次数 多了多少次 称号
//			List<Map<String,Object>> maxCountUserList = new ArrayList<Map<String,Object>>();
//			{
//				Map<String,Object> tmpUser = commEntityConvertService.baseUser2Map(user);
//				tmpUser.put("ext_doneCount", 63);
//				tmpUser.put("ext_doneMoreThenCount", 15);
//				tmpUser.put("ext_honour_name", "勤劳蜜蜂");
//				tmpUser.put("ext_honour_icon_url", "http://b.jpg");
//				maxCountUserList.add(tmpUser);
//			}
//			{
//				Map<String,Object> tmpUser = commEntityConvertService.baseUser2Map(user);
//				tmpUser.put("ext_doneCount", 63);
//				tmpUser.put("ext_doneMoreThenCount", 15);
//				tmpUser.put("ext_honour_name", "勤劳蜜蜂");
//				tmpUser.put("ext_honour_icon_url", "http://b.jpg");
//				maxCountUserList.add(tmpUser);
//			}
//			resMap.put("maxCountUserList", maxCountUserList);
//		} 
//		{//leastDiscountUserList 最低折扣取值 称号
//			List<Map<String,Object>> leastDiscountUserList = new ArrayList<Map<String,Object>>();
//			{
//				Map<String,Object> tmpUser = commEntityConvertService.baseUser2Map(user);
//				tmpUser.put("ext_leastDiscountValue", 5.5);
//				tmpUser.put("ext_honour_name", "幸运神手指");
//				tmpUser.put("ext_honour_icon_url", "http://a.jpg");
//				leastDiscountUserList.add(tmpUser);
//			}
//			resMap.put("leastDiscountUserList", leastDiscountUserList);
//		}
//		jsonResponse.setDataValue(resMap);
//		return jsonResponse;
//	}
	
	/**
	 * 查询家庭成员对应月份最低抽奖记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryFamilyMemberLeastDiscountList.json")
	@ResponseBody
	public JsonResponse qryFamilyMemberLeastDiscountList(HttpServletRequest request){//TODO 
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		String yearMonth = CommControllerUtils.getYearMonth(request);
		//2.交互
		IBusinessMonthYear monthYearWithGB = BusinessMonthYearFactory.newInstance(yearMonth, commonRoomService.getGroupBuildingByUserId(userId), MonthOrTime.month);
		List<UserWithLeastDiscountEntity> dataList = prizeService.getUserWithLeastDiscountList(userId,monthYearWithGB);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(dataList!=null&&dataList.size()>0){
			for(UserWithLeastDiscountEntity tmpEntity:dataList){
				Map<String,Object> tmpMap = commEntityConvertService.baseUser2MapForPrizeRecord(tmpEntity);
				tmpMap.put("ext_leastDiscountValue", tmpEntity.getExt_leastDiscount());
				tmpMap.put("ext_doneCount", tmpEntity.getExt_doneCount());
				resList.add(tmpMap);
			}
		}
//		{
//			Map<String,Object> tmpMap = new HashMap<String, Object>();
//			tmpMap.put("userId", "0001");
//			tmpMap.put("userShowName", "小明");
//			tmpMap.put("imgProfile", "http://www.a.com/a.jpg");
//			tmpMap.put("ext_leastDiscountValue", "1.2");
//			tmpMap.put("ext_doneCount", "55");
//			resList.add(tmpMap);
//		}
//		{
//			Map<String,Object> tmpMap = new HashMap<String, Object>();
//			tmpMap.put("userId", "0002");
//			tmpMap.put("userShowName", "小花");
//			tmpMap.put("imgProfile", "http://www.a.com/b.jpg");
//			tmpMap.put("ext_leastDiscountValue", "6.2");
//			tmpMap.put("ext_doneCount", "75");
//			resList.add(tmpMap);
//		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	@RequestMapping("/qryPrizeListByMonthYear.json")
	@ResponseBody
	public JsonResponse qryPrizeListByMonthYear(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String yearMonth = CommControllerUtils.getYearMonth(request);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//分页信息
		PageModel pageModel = ControllerUtils.getPageModel(request);
		//2.交互
		IBusinessMonthYear monthYearWithGB = BusinessMonthYearFactory.newInstance(yearMonth, commonRoomService.getGroupBuildingByUserId(userId), MonthOrTime.month);
		List<PrizeRecordEntity> tmpList =prizeService.getPrizeRecordByMonth(userId, monthYearWithGB, pageModel);
		int doneCount = commonPrizeService.getIsUsedDiscountCount(userId, monthYearWithGB);//查询对应年月已经使用过的抽奖记录数
		//3.结果处理
		boolean isUsed = doneCount > 0;
		LinkedList<Map<String,Object>> resList = new LinkedList<Map<String,Object>>();
		if(tmpList!=null&&tmpList.size()>0){
			for(PrizeRecordEntity tmp:tmpList){
				Map<String,Object> rec01 = commEntityConvertService.prizeRecord2Map(tmp, tmp.getPrizeUser(), isUsed);
				resList.add(rec01);
			}
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	@RequestMapping("/qryLeftCountNotLogin.json")
	@ResponseBody
	public JsonResponse qryLeftCountNotLogin(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String deviceId = HeaderManager.getDeviceId();
		Long deviceType = HeaderManager.getSubChannelIdLong();
		//2.交互
		if(StringUtils.isEmpty(deviceId)||StringUtils.isEmpty(deviceType)){
			throw new ValidateRuntimeException("prize.getPrizeDoneCount.deviceIdOrdeviceType.isEmpty");
		}
		UserTmp userTmp =commonDeviceService.checkAndCreate(deviceId, deviceType);
		BigInteger userTmpId = userTmp.getId();
		PrizeStateEntity prizeStateEntity = prizeService.getCurrPrizeStateNotLogin(userTmpId);
		int defaultMaxCount = prizeRuleManager.getMaxPrizeCount();
		Double lastDiscount = prizeService.getLastDiscount(userTmpId, LoginDict.UserRegistOrTmp.TMP_USER);//syl-add-2015-6-11 15:42:28
		//3.结果处理
		Map<String,Object> resMap = prizeStateEntity2Map(prizeStateEntity, defaultMaxCount,lastDiscount);
		jsonResponse.setDataValue(resMap);
		doLeastDiscount(request, jsonResponse);//syl-upd 2014-9-11 18:20:45
		return jsonResponse;
	}
	
	@RequestMapping("/qryLeftCountIsLogin.json")
	@ResponseBody
	public JsonResponse qryLeftCountIsLogin(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		PrizeStateEntity prizeStateEntity = prizeService.getCurrPrizeStateIsLogin(userId);
		int defaultMaxCount = prizeRuleManager.getMaxPrizeCount();
		Double lastDiscount = prizeService.getLastDiscount(userId, LoginDict.UserRegistOrTmp.REGIST_USER);//syl-add-2015-6-11 15:42:28
		//3.结果处理
		Map<String,Object> resMap = prizeStateEntity2Map(prizeStateEntity, defaultMaxCount,lastDiscount);
		jsonResponse.setDataValue(resMap);
		doLeastDiscount(request, jsonResponse);//syl-upd 2014-9-11 18:20:45
		return jsonResponse;
	}
	
	private Map<String,Object> prizeStateEntity2Map(PrizeStateEntity prizeStateEntity,int defaultMaxCount,Double lastDiscount){
		Map<String,Object> resMap = new HashMap<String, Object>();
		{//剩余次数
			int doneCount = prizeStateEntity.getDonePrizeCount()==null?0:prizeStateEntity.getDonePrizeCount();
			int resCount = (defaultMaxCount-doneCount)<=0?0:(defaultMaxCount-doneCount);
			resMap.put("leftPrizeCount", resCount);
		}
		{//最低折扣
			resMap.put("leastDiscount", prizeStateEntity.getLeastDiscount());
		}
		{//是否已被使用
			resMap.put("isDiscountUsed", prizeStateEntity.getHasUsed());
		}
		{//最低折扣可兑换的粮票金额
			resMap.put("savedHbMoney", prizeStateEntity.getSavedHbMoney()==null?null:PriceUtil.div100(prizeStateEntity.getSavedHbMoney()));
		}
		{//上次抽奖折扣syl-add-2015-6-11 15:43:19
			resMap.put("lastDiscount", lastDiscount);
		}
		return resMap;
	}
	
//	public static void main(String[] args) {
//		PrizeRecord PrizeRecord1 = new PrizeRecord(null, "2012-01-02 11:21:11", null, null, null, null);
//		PrizeRecord PrizeRecord2 = new PrizeRecord(null, "2012-01-02 10:21:11", null, null, null, null);
//		PrizeRecord PrizeRecord3 = new PrizeRecord(null, "2012-01-02 12:21:11", null, null, null, null);
//		List<PrizeRecord> list = new ArrayList<PrizeRecord>();
//		list.add(PrizeRecord1);
//		list.add(PrizeRecord2);
//		list.add(PrizeRecord3);
//		for(PrizeRecord tmp:list){
//			System.out.println(tmp.getPrizeTime());
//		}
//		System.out.println("-------");
//		Collections.sort(list, new PrizeRecordComparator());
//		for(PrizeRecord tmp:list){
//			System.out.println(tmp.getPrizeTime());
//		}
//	}
	
	
//	public int getCurrUserCount(){
//		User user = new User();
//		user.setUserState(DictConstants.UserState.IN_USE);
//		int count = userService.getUserCount(MapConverter.toMap(user));
////		Point p0 = new Point(0, 0);
////		Point p1 = new Point(6, 50);
////		Point p2 = new Point(12, 200);
////		Point p3 = new Point(18, 500);
////		Point p4 = new Point(24, 800);
////		Point p5 = new Point(30, 1000);
////		 if(time<=6){
////			return (int)Point.getYByX(p0, p1, time);
////		}else if(time<=12){
////			return (int)Point.getYByX(p1, p2, time);
////		}else if(time<=18){
////			return (int)Point.getYByX(p2, p3, time);
////		}else if(time<=24){
////			return (int)Point.getYByX(p3, p4, time);
////		}else{
////			return (int)Point.getYByX(p4, p5, time);
////		}
//		 return count;
//	}
	
	@SuppressWarnings("rawtypes")
	private void doLeastDiscount(HttpServletRequest request,JsonResponse resJsonResponse){
		if(StringUtils.isEmpty(((Map)resJsonResponse.getDataValue()).get("leastDiscount"))){
			if(!StringUtils.isEmpty(request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION))){
				Long version = VersionTransferUtil.versionStr2Long(request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION));
				Long version130 = VersionTransferUtil.versionStr2Long("1.3.0");
				if(version.compareTo(version130)<0&&HeaderManager.getSubChannelId().equals(DictConstants.Channel_Sub.IOS)){
					resJsonResponse.putData("leastDiscount", 9.9);
				}
			}
		}
	}
	
	private boolean isLeastDiscountIgnoreUsed(HttpServletRequest request){
			if(!StringUtils.isEmpty(request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION))){
				Long version = VersionTransferUtil.versionStr2Long(request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION));
				Long version220 = VersionTransferUtil.versionStr2Long("2.2.0");
				if(version.compareTo(version220)>0){
					return true;
				}
			}
			return false;
	}
	
}
