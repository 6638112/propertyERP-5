package com.cnfantasia.server.api.plotproperty.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.access.entity.MonthCarInfo;
import com.cnfantasia.server.api.access.service.IAccessService;
import com.cnfantasia.server.api.access.service.ThirdCarFactory;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.plotproperty.constant.FinanceConfig;
import com.cnfantasia.server.api.plotproperty.entity.FinanceAgent;
import com.cnfantasia.server.api.plotproperty.entity.FinanceBuyEntity;
import com.cnfantasia.server.api.plotproperty.entity.FinanceReqEntity;
import com.cnfantasia.server.api.plotproperty.service.FinanceService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.room.service.IGroupBuildingService;
import com.cnfantasia.server.api.user.entity.UserEntity;
import com.cnfantasia.server.api.user.service.IUserService;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.TimeOutRuntimeException;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.HttpClientUtil;
import com.cnfantasia.server.common.utils.ParamUtils;


/**
 * Filename:    FinanceCarController.java
 * @version:    1.0.0
 * Description: 停车宝Controller
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2016年03月28日       yewj             1.0             1.0 Version
 */
@RequestMapping("/financeCar")
@Controller
public class FinanceCarController extends BaseController{
	
	private static Log logger = LogFactory.getLog(FinanceCarController.class);
	private static String FINANCE_CAR_URL = null; // "http://m2.hhnian.com:8002/thirdparty/auth.do";
	private static String PARKING_URL = null;//"http://m2.hhnian.com:8006/parking/access.do";
	private static String APP_KEY = null;
	private static String TOKEN = null;
	@Resource
	private FinanceService financeService;
	@Resource
	private IUserService userService;
	@Resource
	private IAccessService accessService;
	@Resource
	private IGroupBuildingService groupBuildingService;
	@Resource
	private ThirdCarFactory thirdCarFactory;

	/**
	 * 解放区登录停车宝凭证数据，该接口由解放区发起请求
	 * 
	 * @param request carNum、userId（userId可从session取）
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/index.html")
	public ModelAndView index(String carNum, HttpServletRequest request, HttpServletResponse response) throws IOException{
		BigInteger userId = UserContext.getOperIdBigInteger();
		if(userId == null) {
			logger.debug("FinanceCarController user is null!sessionKey:" + request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY) + ",userId:" + ParamUtils.getLong(request, "", 0L));
			userId = ParamUtils.getBigInteger(request, "userId", null);
		}
		if(userId == null) {
			throw new TimeOutRuntimeException();
		}
		
		ModelAndView modelAndView = new ModelAndView();
		logger.debug("FinanceCarController sessionKey:" + request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY) + ",userId:" + ParamUtils.getLong(request, "", 0L));
		
		String carNo = request.getParameter("CarNo");// android与ios不知什么时候开始传的参数名不一样，所以要处理
		if(StringUtils.isEmpty(carNum) && StringUtils.isEmpty(carNo)){ 
			request.setAttribute("msg", "车牌号不能为空！");
		} else {
			if(StringUtils.isEmpty(carNum)){
				carNum = carNo;
			}
			APP_KEY = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.FINANCE_CAR_APP_KEY);
			TOKEN = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.FINANCE_CAR_TOKEN);
			FINANCE_CAR_URL = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.FINANCE_CAR_URL);
			
			try {
				FinanceReqEntity financeReq = getFinalReq(userId, carNum, request.getSession().getId());
				financeService.insertFinanceReq(financeReq);
				request.setAttribute("financeUrl", FINANCE_CAR_URL);// form表单跳转地址
				request.setAttribute("appKey", APP_KEY);
				request.setAttribute("token", TOKEN);
				request.setAttribute("liberateNum", financeReq.getLiberateNum());
				request.setAttribute("licensePlate", financeReq.getLicensePlate());//车牌号
				request.setAttribute("realName", financeReq.getRealName()); //真实姓名
				request.setAttribute("mobile", financeReq.getMobile());
				request.setAttribute("cityName", financeReq.getCity());
				
				request.setAttribute("prefecture", financeReq.getPrefecture());
				request.setAttribute("province", financeReq.getProvince());
				
				request.setAttribute("residential", financeReq.getResidential());
				request.setAttribute("building", financeReq.getBuilding());
				request.setAttribute("roomNum", financeReq.getRoomNum());
				request.setAttribute("parkingFee", financeReq.getParkingFee());
			} catch(TimeOutRuntimeException e) {
				logger.error(e.getMessage(), e);
				request.setAttribute("msg", "登录信息过期，请重新登录！");
			} catch(BusinessRuntimeException ee) {
				logger.error(ee.getMessage(), ee);
				if("FinanceController.getFinalReq.isPerid".compareTo(ee.getErrCode())==0){
					request.setAttribute("msg", "对不起,周期停车费暂不支持停车宝！");
				}else{
					request.setAttribute("msg", "系统异常，正在处理，请稍候再试！");
				}
			} catch(Exception e2) {
				logger.error(e2.getMessage(), e2);
				request.setAttribute("msg", "系统异常，正在处理，请稍候再试！");
			}
		}
		
		modelAndView.setViewName("/finance/indexCar");
		return modelAndView;
	}
	
	/**
	 * 返回FinanceReqEntity对象
	 * 
	 * @param userId 用户id
	 * @param licensePlate 车牌号
	 * @return
	 */
	private FinanceReqEntity getFinalReq(BigInteger userId, String licensePlate, String sessionId) {
		Map<String, Object> infoMap = accessService.queryCarInfoByCardNum(licensePlate);
		accessService.forbiddenOuterCarPay(infoMap.get("from"));
		
		Long parkingFee = Long.parseLong(String.valueOf(infoMap.get("fee")));// 每月停车费
		Object gbIdObj = infoMap.get("gbId");     // 小区id
		if(null!=gbIdObj){
			MonthCarInfo monthCarInfo = null;
			try {
				monthCarInfo = thirdCarFactory.getOneMonthCarInfo(new BigInteger(gbIdObj.toString()), licensePlate);
			} catch (Exception e1) {}
			if(monthCarInfo!=null && monthCarInfo.isState()) {
				parkingFee = monthCarInfo.getRealAmt();
			}
		}
		
		UserEntity userEntity = userService.getUserById(userId);
		
//		RealRoomEntity realRoom = userEntity.getDefaultRoomEntity().getRealRoomEntity();
//		GroupBuildingEntity groupBuilding = realRoom.getBuildingEntity().getGroupBuildingEntity();
//		AddressBlockEntity addressBlock = groupBuilding.getAddressBlockEntity();
		
		FinanceReqEntity financeReq = new FinanceReqEntity();
		financeReq.setLiberateNum(userEntity.getHuaId());
		financeReq.setLicensePlate(licensePlate);
		String realName = userEntity.getRealName() != null ? userEntity.getRealName() : userEntity.getNickName();
		if(realName == null) {
			realName = userEntity.getMobile();
		}
		financeReq.setRealName(realName);
		financeReq.setMobile(userEntity.getMobile());
//		financeReq.setRoomNum(RoomEntityConvertUtil.getRealRoomShowName(realRoom));
//		financeReq.setBuilding(RoomEntityConvertUtil.getBuildingShowName(realRoom.getBuildingEntity()));
		if(gbIdObj!=null){
			Map<String, Object> groupBuildingInfoMap = groupBuildingService.queryGroupBuildingInfoById(new BigInteger(gbIdObj.toString()));
			financeReq.setResidential(groupBuildingInfoMap.get("gbName")+""); // 小区 
			financeReq.setPrefecture(groupBuildingInfoMap.get("blockName")+"");// 区
			financeReq.setCity(groupBuildingInfoMap.get("cityName")+"");// 市
			financeReq.setProvince(groupBuildingInfoMap.get("provinceName")+"");// 省
		}
		
		financeReq.setParkingFee(BigDecimalUtil.div100(BigDecimal.valueOf(parkingFee)));
		return financeReq;
	}
	
	/**
	 * 解放区购买通知接口，该接口由钱生花发起请求
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/returnBuy.json")
	@ResponseBody
	public Object returnBuy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String> respMap = new HashMap<String, String>();
		
		Map<String, String> reqParamMap = new HashMap<String, String>();
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();

			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
					reqParamMap.put(paramName, paramValue);
				}
			}
		}
		JSONObject jsonObject = new JSONObject(reqParamMap);
		logger.info("returnBuy:-JSON:" + jsonObject.toString());
	    
		try {
			APP_KEY = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.FINANCE_CAR_APP_KEY);
			TOKEN = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.FINANCE_CAR_TOKEN);
			
			String appKey = ParamUtils.getString(request, "appKey", null);
			String token = ParamUtils.getString(request, "token", null);
			String licensePlate = ParamUtils.getString(request, "licensePlate", null);
			//String liberateNum = ParamUtils.getString(request, "liberateNum", null);
			String buyPerson = ParamUtils.getString(request, "buyPerson", null);
			String buyMobile = ParamUtils.getString(request, "buyMobile", null);
			String buyIdNo = ParamUtils.getString(request, "buyIdNo", null);
			String orderNo = ParamUtils.getString(request, "orderNo", null);
			Date deductionBeginTime = ParamUtils.getDate(request, "deductionBeginTime", null, "yyyy-MM");
			Date deductionEndTime = ParamUtils.getDate(request, "deductionEndTime", null, "yyyy-MM");
			BigDecimal returnMoney = ParamUtils.getBigDecimal(request, "returnMoney", null);
			BigDecimal buyMoney = ParamUtils.getBigDecimal(request, "buyMoney", null);
			Date buyTime = ParamUtils.getDate(request, "buyTime", null, "yyyy-MM-dd HH:mm:ss");
			
			if(returnMoney == null || returnMoney.intValue() == 0) {
				returnMoney = buyMoney.multiply(BigDecimal.valueOf(0.02));
			}
			
			respMap.put("token", token);
			if(APP_KEY.equals(appKey) && TOKEN.equals(token)) {
				FinanceBuyEntity financeBuyEntity = new FinanceBuyEntity();
				financeBuyEntity.setOrderNo(orderNo);
				financeBuyEntity.setLicensePlate(licensePlate);
//				financeBuyEntity.setHudId(liberateNum); //钱生花传回来的解放号是错误的，不用。
				financeBuyEntity.setBuyPerson(buyPerson);
				financeBuyEntity.setBuyMobile(buyMobile);
				financeBuyEntity.setBuyIdNo(buyIdNo);
				financeBuyEntity.setDeductionBeginTime(deductionBeginTime);
				financeBuyEntity.setDeductionEndTime(deductionEndTime);
				financeBuyEntity.setBuyMoney(buyMoney);
				financeBuyEntity.setBuyTime(buyTime);
				
				Map<String, Object> paramMap = new HashMap<String, Object>();
//				paramMap.put("liberateNum", liberateNum);
				paramMap.put("licensePlate", licensePlate);
				FinanceReqEntity financeReq = financeService.getFinanceReqForCar(paramMap);
				financeBuyEntity.setFinaceReqId(financeReq.getId());
				financeBuyEntity.setParkingFee(financeReq.getParkingFee());
				financeBuyEntity.setHudId(financeReq.getLiberateNum()); //钱生花传回来的解放号是错误的，不用。
				{
					if(financeReq.getLiberateNum()!=null && financeReq.getLiberateNum().trim().length()>0){
						UserEntity userEntity = userService.getUserById(new BigInteger(financeReq.getLiberateNum()));
						Long realRoomId = userEntity.getDefaultRoomEntity().gettRealRoomFId().longValue();
						financeBuyEntity.setRoomId(realRoomId);
					}
				}
				
				
				Map<String, Object> infoMap = accessService.queryCarInfoByCardNum(licensePlate);
				Object gbId = infoMap.get("gbId");     // 小区id
				if(gbId!=null){
					paramMap.clear();
					paramMap.put("gbId", gbId);
					FinanceAgent financeAgent = financeService.getFinanceAgent2(paramMap);
					if(financeAgent!=null){
						financeBuyEntity.setChannelPartnerId(financeAgent.getChannelPartnerId());
						financeBuyEntity.setPropertyCompanyId(financeAgent.getPropertyCompanyId());
					}
				}
				
				//得到开始时间的当月第一天，结束时间的下一个月第一天
				financeBuyEntity.setDeductionBeginDate(DateUtils.getFirstDayOfMonth(deductionBeginTime));
				financeBuyEntity.setDeductionEndDate(DateUtils.getFirstDayOfNextMonth(deductionEndTime));
				
				int month = DateUtils.getDiffMonths(financeBuyEntity.getDeductionBeginDate(), financeBuyEntity.getDeductionEndDate());
				financeBuyEntity.setDeductionCount(month);
				returnMoney.divide(new BigDecimal(month), 5, BigDecimal.ROUND_DOWN);
				financeBuyEntity.setReturnMoney(returnMoney);
				
				financeBuyEntity.setAddTm(new Date());
				financeBuyEntity.setFinanceType(FinanceConfig.FinanceType.FINANCE_CAR);// 设置为停车宝类型
				
				financeService.saveReturnBuyFinanceForCar(financeBuyEntity, new BigInteger(infoMap.get("carId").toString()), new BigInteger(gbId.toString()));
				// 查询每月停车费fee和carid
//				Map<String, Object> infoMap = accessService.queryCarInfoByCardNum(licensePlate);
//				double parkingFeePerMonth = Double.parseDouble(infoMap.get("fee").toString());     // 每月停车费
//				BigInteger carId = new BigInteger(infoMap.get("carId").toString());
//				BigInteger userId = new BigInteger(liberateNum);
//				Integer subChannelId = HeaderManager.getSubChannelIdLong().intValue();
//				int payNum = DateUtils.getDiffMonths(financeBuyEntity.getDeductionBeginDate(), financeBuyEntity.getDeductionEndDate());
//				double totalPayFee = parkingFeePerMonth*payNum;
//				
//				BigInteger orderId = accessService.payCarKeyOrder(carId, totalPayFee, payNum, userId, null);// null:subChannelId
//				commPayService.paySuccessOperate(orderId, EbuyDict.EbuyOrder_Type.CarKey_Bill);
				
				respMap.put("code", "1");
			} else {
				respMap.put("code", "0");
			}
		} catch(Exception e) {
			respMap.put("code", "-1");
			logger.error(e.getMessage(), e);
		}
		
		JSONObject resJson = new JSONObject(respMap);
		
		logger.debug("returnBuy resJson:" + resJson.toString());
		return resJson.toString();
	}
	
	/**
	 * 获取当天收益 
	 * @param parkingFee 每月停车费
	 * @return
	 */
	public static Object getCurrentDayIncome(long parkingFee){
		APP_KEY = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.FINANCE_CAR_APP_KEY);
		TOKEN = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.FINANCE_CAR_TOKEN);
		PARKING_URL = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.PARKING_URL);
		
        Map<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("appKey", APP_KEY);
        parameters.put("token", TOKEN);
        parameters.put("parkingFee", parkingFee);
        String result = "";
		try {
			byte[] resultByte = HttpClientUtil.doPost(PARKING_URL, parameters);
			result = new String(resultByte);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		return JSON.parse(result);
	}

}
