/**   
* Filename:    RedenvelopeController.java   
* @version:    1.0  
* Create at:   2014年6月23日 上午1:05:55   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redenvelope.web;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.util.UserShowNameUtil;
import com.cnfantasia.server.api.payment.serviceUntran.IPayConfigService;
import com.cnfantasia.server.api.payment.util.PayConfigUtil;
import com.cnfantasia.server.api.prize.constant.PrizeDict;
import com.cnfantasia.server.api.prize.entity.PrizeRecordEntity;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.redenvelope.constant.RedenvelopeDict;
import com.cnfantasia.server.api.redenvelope.entity.HbConvertConsumEntity;
import com.cnfantasia.server.api.redenvelope.entity.HbConvertRuleDescEntity;
import com.cnfantasia.server.api.redenvelope.entity.PayRedEnvelopeDetailEntity;
import com.cnfantasia.server.api.redenvelope.entity.PayRedEnvelopeEntity;
import com.cnfantasia.server.api.redenvelope.entity.SimpleHbBalanceEntity;
import com.cnfantasia.server.api.redenvelope.entity.SimpleHbDiscountEntity;
import com.cnfantasia.server.api.redenvelope.service.IRedenvelopeService;
import com.cnfantasia.server.api.redenvelope.util.IDiscount2hbRule;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.room.util.RoomStatusCheckUtil;
import com.cnfantasia.server.api.room.util.SpecialUserChekUtil;
import com.cnfantasia.server.business.pub.CommControllerUtils;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.commbusi.plotproperty.entity.BusinessMonthYearFactory;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYearSignal;
import com.cnfantasia.server.commbusi.plotproperty.entity.MonthOrTime;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * Filename:    RedenvelopeController.java
 * @version:    1.0.0
 * Create at:   2014年6月23日 上午1:05:55
 * Description:粮票业务处理
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月23日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/redenvelope")
public class RedenvelopeController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	
	private IRedenvelopeService redenvelopeService;
	public void setRedenvelopeService(IRedenvelopeService redenvelopeService) {
		this.redenvelopeService = redenvelopeService;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private IDiscount2hbRule discount2hbRule;
	public void setDiscount2hbRule(IDiscount2hbRule discount2hbRule) {
		this.discount2hbRule = discount2hbRule;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
//	private ICommonPrizeService commonPrizeService;
//	public void setCommonPrizeService(ICommonPrizeService commonPrizeService) {
//		this.commonPrizeService = commonPrizeService;
//	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}
	
	private IPayConfigService payConfigService;
	public void setPayConfigService(IPayConfigService payConfigService) {
		this.payConfigService = payConfigService;
	}

	@RequestMapping("/discount2hbPreQry.json")
	@ResponseBody
	public JsonResponse discount2hbPreQry(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String yearMonth = CommControllerUtils.getYearMonth(request);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(userId);
		IBusinessMonthYearSignal monthYearWithGB = null;
		if(StringUtils.isEmpty(yearMonth)){//为空则取当前时间对应的物业月份
			String nowTime = dualDao.getNowTime();
			monthYearWithGB = BusinessMonthYearFactory.newInstance(nowTime, groupBuilding, MonthOrTime.time);
		}else{
			monthYearWithGB = BusinessMonthYearFactory.newInstance(yearMonth, groupBuilding, MonthOrTime.month);
		}
		SimpleHbDiscountEntity simpleHbEntity = redenvelopeService.getLastDiscountByMonth(userId, monthYearWithGB);
		//3.结果处理
		PrizeRecordEntity prizeRecord = simpleHbEntity.getPrizeRecord();
		jsonResponse.putData("isConvertAble", simpleHbEntity.getIsConvertAble());
		Integer reasonFlag = simpleHbEntity.getUnConvertAbleReasonFlag();
		jsonResponse.putData("unConvertAbleReasonFlag",reasonFlag);
		//syl-add-2015-1-27 18:23:57 如果折扣已使用 返回使用的类型
		if(reasonFlag!=null&&(reasonFlag.compareTo(RedenvelopeDict.Redenvelope_UnConvert_Reason.IS_USED)==0||reasonFlag.compareTo(RedenvelopeDict.Redenvelope_UnConvert_Reason.MUST_BE_ADMIN)==0)){
			jsonResponse.putData("discountUsedType",simpleHbEntity.getDiscountUsedType());
			//使用折扣缴纳了物业费
			if(simpleHbEntity.getDiscountUsedType()!=null&&simpleHbEntity.getDiscountUsedType().compareTo(PrizeDict.PrizeRecord_UsedType.Plotproperty)==0){
				jsonResponse.putData("discountSavedMonthForBill",simpleHbEntity.getDisCountSavedMoney_bill()==null?null:PriceUtil.div100(simpleHbEntity.getDisCountSavedMoney_bill()));
			}
		}
		//如果门牌需要校验，则查询当前门牌状态信息
		if(reasonFlag!=null&&reasonFlag.compareTo(RedenvelopeDict.Redenvelope_UnConvert_Reason.ROOM_NEED_VALIDATE)==0){
			//查询当前门牌状态
			RoomEntityWithValidate roomEntityWithValidate = commonRoomService.getDefaultRoomInfo(userId);
			if(commonRoomService.checkRealRoomFirstValidateAndUpd(roomEntityWithValidate.gettRealRoomFId(), false)){
				roomEntityWithValidate = commonRoomService.getDefaultRoomInfoExceptionWhenEmpty(userId);
			}
			Integer roomVerifyStatus = RoomDict.RoomValidte_VerifyStatus.UNDO;
			boolean canApplyValidate = false;
			if(roomEntityWithValidate.getRoomValidate()!=null){
				roomVerifyStatus = roomEntityWithValidate.getRoomValidate().getVerifyStatus();
//				if(roomEntityWithValidate.getRoomValidate().getVerifyStatus()==null
//						||roomEntityWithValidate.getRoomValidate().getVerifyStatus().compareTo(RoomDict.VerifyStatus.UNDO)==0){//门牌未验证
//					
//				}
			}
			if(roomVerifyStatus.compareTo(RoomDict.RoomValidte_VerifyStatus.UNDO)==0){
				RealRoom currRealRoom = roomEntityWithValidate==null?null:roomEntityWithValidate.getRealRoomEntity();
//				if(currRealRoom!=null&&currRealRoom.getValidateStatus()!=null&&currRealRoom.getValidateStatus().compareTo(RoomDict.RealRoom_ValidateStatus.IS_VALIDATED)==0){
				if(RoomStatusCheckUtil.checkIsRealRoomValidated(currRealRoom)){
					canApplyValidate = true;
				}
			}
			jsonResponse.putData("roomVerifyStatus",roomVerifyStatus);
			jsonResponse.putData("canApplyValidate",canApplyValidate);//返回是否可以直接申请验证
		}
		
//		if(reasonFlag!=null){
//			jsonResponse.putData("unConvertAbleReason",MessageSourceUtil.getMessage(RedenvelopeDict.Redenvelope_UnConvert_Reason.getMsgKey(reasonFlag)));
//		}else{
//			jsonResponse.putData("unConvertAbleReason","");
//		}
		
		jsonResponse.putData("hasConvert", simpleHbEntity.getHasConvert());
//		jsonResponse.putData("leftDay2Convert", simpleHbEntity.getLeftDay2Convert());
		if(prizeRecord!=null){
			jsonResponse.putData("money", simpleHbEntity.getMoneyYuan());
			jsonResponse.putData("discount", simpleHbEntity.getPrizeRecord().getDiscount());
			jsonResponse.putData("dateTimeLong", DateUtil.timeToLong(simpleHbEntity.getPrizeRecord().getPrizeTime()));
		}else{
			jsonResponse.putData("money", null);
			jsonResponse.putData("discount", null);
			jsonResponse.putData("dateTimeLong", null);
		}
		{
//		jsonResponse.putData("prizeUserInfo", UserShowNameUtil.getUserShowName(prizeRecord.getPrizeUser()));
//		jsonResponse.putData("prizeUserId", prizeRecord.getPrizeUser().getId());
//			jsonResponse.putData("prizeUserInfo", null);
//			jsonResponse.putData("prizeUserId", null);
			Map<String,Object> tmpUserMap = commEntityConvertService.baseUser2MapForPrizeRecordDiscount2hbPreQry(prizeRecord==null?null:prizeRecord.getPrizeUser());
			jsonResponse.putDataAll(tmpUserMap);
		}
		
		try {
			jsonResponse.putData("yearMonthLong", monthYearWithGB.getDiscountYearMonthLong());
		} catch (Exception e) {
			logger.debug("RedenvelopeController discount2hbPreQry yearMonthLong cause exception,yearMonth is:"+monthYearWithGB==null?null:monthYearWithGB.getBillMonthStr(DateUtil.formatMonth.get()),e);
		}
		return jsonResponse;
	}
	
	@RequestMapping("/discount2hb.json")
	@ResponseBody
	public JsonResponse discount2hb(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String yearMonth = CommControllerUtils.getYearMonth(request);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		if(StringUtils.isEmpty(yearMonth)){
			throw new BusinessRuntimeException("RedenvelopeService.doDiscount2hb.yearMonth.isNull");
		}
		//2.交互
		GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(userId);
		IBusinessMonthYear monthYearWithGB = BusinessMonthYearFactory.newInstance(yearMonth, groupBuilding, MonthOrTime.month);
		SimpleHbDiscountEntity simpleHbEntity =redenvelopeService.doDiscount2hb(userId, monthYearWithGB);
		//3.结果处理
		jsonResponse.putData("money", simpleHbEntity.getMoneyYuan());
		jsonResponse.putData("discount", simpleHbEntity.getPrizeRecord().getDiscount());
		return jsonResponse;
	}
	
	@RequestMapping("/qryBalance.json")
	@ResponseBody
	public JsonResponse qryBalance(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		userId = new BigInteger("49992");
		//2.交互
		SimpleHbBalanceEntity simpleHbBalanceEntity = redenvelopeService.getAllBalanceCollectInfo(userId, 0);
		//3.结果处理
		jsonResponse.putData("balance", simpleHbBalanceEntity.getBalanceDiv100());
		jsonResponse.putData("totalConvertMoney", simpleHbBalanceEntity.getTotalConvertMoneyDiv100());
		jsonResponse.putData("totalConsumMoney", simpleHbBalanceEntity.getTotalConsumMoneyDiv100());
		return jsonResponse;
	}

	/**
	 * 查询粮票余额，List方式返回，其中一个是全国通用，另一个是仅体验店使用
	 * @author wenfq 2017-09-18
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryBalanceList.json")
	@ResponseBody
	public JsonResponse qryBalanceList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		BigInteger userId = new BigInteger("5260130");

		//2.交互

		List<SimpleHbBalanceEntity> simpleHbBalanceEntityList = redenvelopeService.getHbBalanceEntityList(userId);
		jsonResponse.putData("list", simpleHbBalanceEntityList);

		//3.结果处理
		return jsonResponse;
	}
	
	@RequestMapping("/qryBalanceWithPercent.json")
	@ResponseBody
	public JsonResponse qryBalanceWithPercent(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		Double toPayAmount = ParamUtils.getDouble(request, "toPayAmount");
//        BigInteger userId = new BigInteger("5260130");
		Integer orderType = ParamUtils.getInteger(request, "orderType", 1);
        List<Long> merchantIds = JSON.parseArray(request.getParameter("merchantIds"), Long.class);//商品中包含哪些供应商

		//2.交互
		Long experienceStoreId = Long.valueOf(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Experience_Store_Id));
		int type = 0;//type=1体验店，type=0全国
		for(int i = 0; merchantIds !=null && i < merchantIds.size(); i++){
			if(experienceStoreId.longValue() == merchantIds.get(i).longValue()) {
				type = 1;
				break;
			}
		}

		SimpleHbBalanceEntity simpleHbBalanceEntity = redenvelopeService.getAllBalanceCollectInfo(userId, type);
		if (type == 1) {
			if (simpleHbBalanceEntity.getBalance() <= 0//如果体验店的粮票用完，取全国通用粮票
					|| merchantIds.size() > 1 ) { //既有全国商品又有体验店商品，只能用全国粮票
				simpleHbBalanceEntity = redenvelopeService.getAllBalanceCollectInfo(userId, 0);
			} else if (merchantIds.size() == 1) {//订单全是体验店的商品，全国粮票也可用，但优先使用体验店粮票
				SimpleHbBalanceEntity simpleHbBalanceEntity4AllContry = redenvelopeService.getAllBalanceCollectInfo(userId, 0);
				simpleHbBalanceEntity.setBalance(simpleHbBalanceEntity4AllContry.getBalance() + simpleHbBalanceEntity.getBalance());
			}
		}

		Double payPercent = payConfigService.getPayConfigHbPercent(orderType);
		//3.结果处理
		BigDecimal resShow = PayConfigUtil.calculateMaxCouponAmount(toPayAmount,payPercent);
		BigDecimal balDes = simpleHbBalanceEntity.getBalanceDiv100();
		if(resShow.compareTo(balDes)>0){
			resShow = balDes;
		}
		jsonResponse.putData("balance", balDes.doubleValue());
		jsonResponse.putData("ext_hbPercent", payPercent);
		jsonResponse.putData("ext_balancePercent",resShow.doubleValue());//针对待支付金额获取最大可优惠金额
		return jsonResponse; 
	}
	
	/**
	 * 查询粮票兑换记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryRecord2hb.json")
	@ResponseBody
	public JsonResponse qryRecord2hb(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		PageModel pageModel = ControllerUtils.getPageModel(request);
		int type = ParamUtils.getInt(request, "type", 0); //0全国通用，1体验店专用
		//2.交互
		List<PayRedEnvelopeEntity> payRedList = redenvelopeService.getRecord2hbRecord(userId, type, pageModel);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(PayRedEnvelopeEntity tmpEntity:payRedList){
			Map<String,Object> recMap = new HashMap<String, Object>();
			recMap.put("type", 0);//0兑换，1消费
			recMap.put("id", tmpEntity.getId());
			recMap.put("dateTime", tmpEntity.getFromTime());
			recMap.put("dateTimeLong", DateUtil.timeToLong(tmpEntity.getFromTime()));
			if(tmpEntity.getFromType() == RedenvelopeDict.PayRedEnvelope_FromType.SM_ACTION.intValue()){
				recMap.put("discount","参与活动获得");
			}else if(tmpEntity.getFromType() == RedenvelopeDict.PayRedEnvelope_FromType.Experience_Store.intValue()){
				recMap.put("discount","优选体验店充值");
			}else {
				recMap.put("discount", tmpEntity.getSingalDetail().getDiscountInfo());
			}

			{
				Map<String,Object> tmpUserMap = commEntityConvertService.baseUser2MapForHbRecord(tmpEntity.getConverterUser());
				recMap.putAll(tmpUserMap);
			}
			recMap.put("convertMoney", tmpEntity.getAmountTotalDiv100());
			
			if(RedenvelopeDict.PayRedEnvelope_FromType.PRIZE_RECORD.compareTo(tmpEntity.getFromType())==0){//抽奖附加字符串
				recMap.put("left_up", tmpEntity.getFromTime());//2015-5-19
				recMap.put("right_up", "兑换人:  "+UserShowNameUtil.getUserShowName(tmpEntity.getConverterUser()));//兑换人:  name
				recMap.put("left_bottom", "兑换折扣："+tmpEntity.getSingalDetail().getDiscountInfo());//
				recMap.put("right_bottom", "兑换金额："+tmpEntity.getAmountTotalDiv100());//兑换金额
			}else if(RedenvelopeDict.PayRedEnvelope_FromType.SM_ACTION.compareTo(tmpEntity.getFromType())==0){//神码行动附加字符串
				recMap.put("left_up", tmpEntity.getFromTime());//2015-5-19
				recMap.put("right_up", "获取人:  "+UserShowNameUtil.getUserShowName(tmpEntity.getConverterUser()));//兑换人:  name
				recMap.put("left_bottom", "参与活动");//神马活动，被借用为市场活动了 addedy by wenfq 2016-08-04
				recMap.put("right_bottom", "粮票金额:  "+ tmpEntity.getAmountTotalDiv100());//兑换金额
			}else if(tmpEntity.getFromType().compareTo(4)==0){
				recMap.put("left_up", tmpEntity.getFromTime());//2015-5-19
				recMap.put("right_up", "兑换人:  "+UserShowNameUtil.getUserShowName(tmpEntity.getConverterUser()));//兑换人:  name
				recMap.put("left_bottom", "退款成功");//
				recMap.put("right_bottom", "退款金额："+tmpEntity.getAmountTotalDiv100());//退款金额
			}else if(tmpEntity.getFromType().compareTo(RedenvelopeDict.PayRedEnvelope_FromType.Experience_Store) == 0){
				recMap.put("left_up", tmpEntity.getFromTime());//2017-09-18
				recMap.put("right_up", "兑换人:  "+UserShowNameUtil.getUserShowName(tmpEntity.getConverterUser()));
				recMap.put("left_bottom", "优选体验店充值");
				recMap.put("right_bottom", "粮票金额："+tmpEntity.getAmountTotalDiv100());
			}
			resList.add(recMap);
		}
		jsonResponse = ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
		return jsonResponse;
	}
	
	/**
	 * 查询粮票消费记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryRecordConsum.json")
	@ResponseBody
	public JsonResponse qryRecordConsum(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		PageModel pageModel = ControllerUtils.getPageModel(request);
		int type = ParamUtils.getInt(request, "type", 0); //0全国通用，1体验店专用

        //2.交互
		List<PayRedEnvelopeDetailEntity> comsumList = redenvelopeService.getHbConsumRecord(userId, type, pageModel);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(PayRedEnvelopeDetailEntity tmpEntity:comsumList){
			Map<String,Object> recMap = new HashMap<String, Object>();
			recMap.put("type", 1);//0兑换，1消费
			recMap.put("id", tmpEntity.getId());
			recMap.put("dateTime", tmpEntity.getConsumTime());
			recMap.put("dateTimeLong", DateUtil.timeToLong(tmpEntity.getConsumTime()));
			recMap.put("consumInfo", tmpEntity.fetchConsumInfo());
			recMap.put("descDetail", tmpEntity.fetchConsumInfo());//公共
			{
				Map<String,Object> tmpUserMap = commEntityConvertService.baseUser2MapForHbRecord(tmpEntity.getConsumerUser());
				recMap.putAll(tmpUserMap);
//				recMap.put("userId", tmpEntity.getConsumerUser().getId());
//				recMap.put("userHuaId", tmpEntity.getConsumerUser().getHuaId());
//				recMap.put("userName", tmpEntity.getConsumerUser().getRealName());
			}
			recMap.put("consumMoney", tmpEntity.getAnountDiv100());
			
			{//附加字符串
				recMap.put("left_up", tmpEntity.getConsumTime());//2015-5-19
				recMap.put("right_up", "消费人:  "+UserShowNameUtil.getUserShowName(tmpEntity.getConsumerUser()));//兑换人:  name
				recMap.put("left_bottom", "使用类型:  "+tmpEntity.fetchConsumInfo());//
				recMap.put("right_bottom", "消费金额:  "+tmpEntity.getAnountDiv100());//兑换金额
			}
			resList.add(recMap);
		}
		jsonResponse = ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
		return jsonResponse;
	}
	
	@RequestMapping("/qryRecordBoth.json")
	@ResponseBody
	public JsonResponse qryRecordBoth(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		userId = new BigInteger("40006");
		//2.交互
		List<HbConvertConsumEntity> bothList = redenvelopeService.getHbConvertConsumBothRecord(userId,pageModel);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(HbConvertConsumEntity tmpEntity:bothList){
			Map<String,Object> recMap = new HashMap<String, Object>();
			recMap.put("type", tmpEntity.getTableType());
			recMap.put("id", tmpEntity.getId());
			recMap.put("dateTime", tmpEntity.getTime());
			recMap.put("dateTimeLong", DateUtil.timeToLong(tmpEntity.getTime()));
			recMap.put("descDetail", tmpEntity.getDescDetail());//公共
			{
				Map<String,Object> tmpUserMap = commEntityConvertService.baseUser2MapForHbRecord(tmpEntity.getUser());
				recMap.putAll(tmpUserMap);
//				recMap.put("userId", tmpEntity.getUser().getId());
//				recMap.put("userHuaId",tmpEntity.getUser().getHuaId());
//				recMap.put("userName", tmpEntity.getUser().getRealName());
			}
			recMap.put("amount", tmpEntity.getAmountDiv100());//公共
			resList.add(recMap);
		}
		jsonResponse = ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
		return jsonResponse;
	}
	
	/**
	 * 查询粮票兑换规则
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryHbConvertRule.json")
	@ResponseBody
	public JsonResponse qryHbConvertRule(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		List<HbConvertRuleDescEntity> convertRuleList=redenvelopeService.getHbConvertRule();
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		UserHasTRoom currUserHasTRoom = discount2hbRule.getDefaultUserHasTRoom(userId);
		for(HbConvertRuleDescEntity tmpEntity:convertRuleList){
			Map<String,Object> resMap = new HashMap<String, Object>();
			resMap.put("leftStr", tmpEntity.getLeftStr());
			resMap.put("rightStr", tmpEntity.getRightStr(currUserHasTRoom,discount2hbRule));
			resList.add(resMap);
		}
		if(SpecialUserChekUtil.checkIsSpecialUser(currUserHasTRoom)){
			Map<String,Object> resMap = new HashMap<String, Object>();
			resMap.put("leftStr", "您已成为20人活动认证用户");
			resMap.put("rightStr", "当前管理费为"+PriceUtil.div100(currUserHasTRoom.getPlanPropertyAmount())+"元");
			resList.add(resMap);
		}else{
			
		}
//		{
//			Map<String,Object> resMap = new HashMap<String, Object>();
//			resMap.put("leftStr", "非签约小区：20人活动");
//			resMap.put("rightStr", "管理费x(1-折扣数),例如管理费100元，折扣数为8.5折，则可兑换15元.");
//			resList.add(resMap);
//		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
}