/**   
* Filename:    PlotpropertyController.java   
* @version:    1.0  
* Create at:   2014年8月13日 上午7:48:00   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月13日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.plotproperty.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.plotproperty.constant.FinanceConfig;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyConstant;
import com.cnfantasia.server.api.plotproperty.entity.FinanceAgent;
import com.cnfantasia.server.api.plotproperty.entity.FinanceBuyEntity;
import com.cnfantasia.server.api.plotproperty.entity.FinanceReqEntity;
import com.cnfantasia.server.api.plotproperty.entity.Propfee;
import com.cnfantasia.server.api.plotproperty.service.FinanceService;
import com.cnfantasia.server.api.plotproperty.service.IPlotpropertyService;
import com.cnfantasia.server.api.plotproperty.util.FinanceDeductionRunnable;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.room.entity.AddressBlockEntity;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.RealRoomEntity;
import com.cnfantasia.server.api.room.util.RoomEntityConvertUtil;
import com.cnfantasia.server.api.user.entity.UserEntity;
import com.cnfantasia.server.api.user.service.IUserService;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.TimeOutRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.propertySoftwareDock.base.dao.IPropertySoftwareDockDao;
import com.propertySoftwareDock.base.entity.RealRoomSoftwareInfo;


/**
 * Filename:    FinanceController.java
 * @version:    1.0.0
 * Description:金融理财Controller
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年09月15日       yewj             1.0             1.0 Version
 */
@RequestMapping("/finance")
@Controller
public class FinanceController extends BaseController{
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	private FinanceService financeService;
	
	private IUserService userService;
	
	private IPlotpropertyService plotpropertyService;

	@Resource
	private IPropertySoftwareDockDao propertySoftwareDockDao;

	private static String FINANCE_URL = "http://m2.hhnian.com:8002/thirdparty/auth.do";
	
	private static String FINANCE_URL_TEST = "http://api.linlile.com.cn:8080/API310/finance/returnBuyTest.html";
	
//	private static String FINANCE_URL = "http://www.baidu.com";
	
	private static String APP_KEY;
	
	private static String TOKEN;
	
	/**
	 * 缴物业费
	 * @throws IOException 
	 */
	@RequestMapping(value="/index.html")
	@ResponseBody
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ModelAndView modelAndView = new ModelAndView();
		logger.debug("financeController sessionKey:" + request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY) + ",userId:" + ParamUtils.getLong(request, "", 0L));
//		if(APP_KEY == null || TOKEN == null) {
			APP_KEY = CnfantasiaCommbusiUtil.getSysParaValue("financeAppKey");
			TOKEN = CnfantasiaCommbusiUtil.getSysParaValue("financeToken");
			FINANCE_URL = CnfantasiaCommbusiUtil.getSysParaValue("financeUrl");
//		}
		
		try {
			FinanceReqEntity financeReq = getFinalReq(request);
			financeService.insertFinanceReq(financeReq);
			request.setAttribute("financeUrl", FINANCE_URL);
			request.setAttribute("appKey", APP_KEY);
			request.setAttribute("token", TOKEN);
			request.setAttribute("liberateNum", financeReq.getLiberateNum());
			request.setAttribute("roomId", financeReq.getRoomId());
			request.setAttribute("realName", financeReq.getRealName());
			request.setAttribute("mobile", financeReq.getMobile());
			request.setAttribute("province", financeReq.getProvince());
			request.setAttribute("city", financeReq.getCity());
			request.setAttribute("prefecture", financeReq.getPrefecture());
			request.setAttribute("residential", financeReq.getResidential());
			request.setAttribute("building", financeReq.getBuilding());
			request.setAttribute("roomNum", financeReq.getRoomNum());
			request.setAttribute("propertyFees", financeReq.getPropertyFees());
			request.setAttribute("channel", 3);
			
//			StringBuilder url = new StringBuilder();
//			url.append(CnfantasiaCommbusiUtil.getSysParaValue("financeUrl")).append("?appKey=").append(CnfantasiaCommbusiUtil.getSysParaValue("financeAppKey"))
//						.append("&token=").append(CnfantasiaCommbusiUtil.getSysParaValue("financeToken"))
//						.append("&liberateNum=").append(financeReq.getLiberateNum())
//						.append("&roomId=").append(financeReq.getRoomId()).append("&realName=").append(financeReq.getRealName()).append("&mobile=")
//						.append(financeReq.getMobile()).append("&province=").append(financeReq.getProvince())
//						.append("&city=").append(financeReq.getCity()).append("&prefecture=").append(financeReq.getPrefecture())
//						.append("&residential=").append(financeReq.getResidential())
//						.append("&building=").append(financeReq.getBuilding())
//						.append("&roomNum=").append(financeReq.getRoomNum())
//						.append("&propertyFees=").append(financeReq.getPropertyFees())
//						.append("&channel=3");
//			response.sendRedirect(url.toString());
//			return null;
		} catch(TimeOutRuntimeException e) {
			logger.error(e.getMessage(), e);
			request.setAttribute("msg", "登录信息过期，请重新登录！");
		} catch(BusinessRuntimeException ee) {
			logger.error(ee.getMessage(), ee);
			if("FinanceController.getFinalReq.isPerid".compareTo(ee.getErrCode())==0){
				request.setAttribute("msg", "对不起,周期物业费暂不支持物业宝！");
			}else{
				request.setAttribute("msg", "系统异常，正在处理，请稍候再试！");
			}
		} catch(Exception e2) {
			logger.error(e2.getMessage(), e2);
			request.setAttribute("msg", "系统异常，正在处理，请稍候再试！");
		}
		
		modelAndView.setViewName("/finance/index");
		return modelAndView;
		
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println("<HTML>");
//		out.println("<HEAD>\n");
//		out.println("<TITLE>理财购买</TITLE>\n");
//		out.println("</HEAD>\n");
//		out.println("<BODY Onload=\"document.forms[0].submit()\">");
//		out.println("<FORM METHOD=\"POST\" ACTION=\"" + FINANCE_URL + "\">");
//		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"appKey\" " + "VALUE=\"" + APP_KEY + "\">");
//		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"token\" " + "VALUE=\"" + TOKEN + "\">");
//		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"liberateNum\" " + "VALUE=\"" + financeReq.getLiberateNum() + "\">");
//		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"roomId\" " + "VALUE=\"" + financeReq.getRoomId() + "\">");
//		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"realName\" " + "VALUE=\"" + financeReq.getRealName() + "\">");
//		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"mobile\" " + "VALUE=\"" + financeReq.getMobile() + "\">");
//		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"province\" " + "VALUE=\"" + financeReq.getProvince() + "\">");
//		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"city\" " + "VALUE=\"" + financeReq.getCity() + "\">");
//		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"prefecture\" " + "VALUE=\"" + financeReq.getPrefecture() + "\">");
//		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"residential\" " + "VALUE=\"" + financeReq.getResidential() + "\">");
//		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"building\" " + "VALUE=\"" + financeReq.getBuilding() + "\">");
//		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"roomNum\" " + "VALUE=\"" + financeReq.getRoomNum() + "\">");
//		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"propertyFees\" " + "VALUE=\"" + financeReq.getPropertyFees() + "\">");
//		out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"channel\" " + "VALUE=\"3\">");
//		out.println("</FORM></BODY></HTML>");
//		out.close();
	}
	
	private FinanceReqEntity getFinalReq(HttpServletRequest request) {
		BigInteger userId = UserContext.getOperIdBigInteger();
		if(userId == null) {
			logger.debug("financeController user is null!sessionKey:" + request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY) + ",userId:" + ParamUtils.getLong(request, "", 0L));
			userId = ParamUtils.getBigInteger(request, "userId", null);
		}
		if(userId == null) {
			throw new TimeOutRuntimeException();
		}
		UserEntity userEntity = userService.getUserById(userId);
		Long realRoomId = userEntity.getDefaultRoomEntity().gettRealRoomFId().longValue();
		Long propertyFee = financeService.getPropertyFee(realRoomId);
		
		RealRoomEntity realRoom = userEntity.getDefaultRoomEntity().getRealRoomEntity();
		GroupBuildingEntity groupBuilding = realRoom.getBuildingEntity().getGroupBuildingEntity();
		AddressBlockEntity addressBlock = groupBuilding.getAddressBlockEntity();
		//syl-add-2015-12-21 16:38:48增加周期缴费不支持物业宝的判断
		if(groupBuilding.getPayPeriodEnd()!=null&&groupBuilding.getPayPeriodEnd().compareTo(PlotpropertyConstant.PERID_GBCFG_LIMITEND_DEFAULT)==0){
			throw new BusinessRuntimeException("FinanceController.getFinalReq.isPerid");
		}
		FinanceReqEntity financeReq = new FinanceReqEntity();
		financeReq.setLiberateNum(userEntity.getHuaId());
		financeReq.setRoomId(realRoomId);
		
		String realName = userEntity.getRealName() != null ? userEntity.getRealName() : userEntity.getNickName();
		if(realName == null) {
			realName = userEntity.getMobile();
		}
		financeReq.setRealName(realName);
		financeReq.setMobile(userEntity.getMobile());
		
		financeReq.setRoomNum(RoomEntityConvertUtil.getRealRoomShowName(realRoom));
		financeReq.setBuilding(RoomEntityConvertUtil.getBuildingShowName(realRoom.getBuildingEntity()));
		financeReq.setResidential(groupBuilding.getName());
		financeReq.setPrefecture(addressBlock.getName());
		financeReq.setCity(addressBlock.getAddressCityEntity().getName());
		financeReq.setProvince(addressBlock.getAddressCityEntity().getAddressProvinceEntity().getName());
		financeReq.setPropertyFees(BigDecimalUtil.div100(propertyFee, 0, BigDecimal.ROUND_UP));
		financeReq.setSourceClick(ParamUtils.getString(request, "sourceClick", null));
		
		return financeReq;
	}
	
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
					logger.info("returnBuy:-参数：" + paramName + "=" + paramValue);
					reqParamMap.put(paramName, paramValue);
				}
			}
		}
		JSONObject jsonObject = new JSONObject(reqParamMap);
		logger.info("returnBuy:-JSON:" + jsonObject.toString());
	    
		try {
//			if(APP_KEY == null || TOKEN == null) {
				APP_KEY = CnfantasiaCommbusiUtil.getSysParaValue("financeAppKey");
				TOKEN = CnfantasiaCommbusiUtil.getSysParaValue("financeToken");
//			}
			
			String appKey = ParamUtils.getString(request, "appKey", null);
			String token = ParamUtils.getString(request, "token", null);
			Long roomId = ParamUtils.getLong(request, "roomId", null);
			String liberateNum = ParamUtils.getString(request, "liberateNum", null);
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
				financeBuyEntity.setRoomId(roomId);
				financeBuyEntity.setHudId(liberateNum);
				financeBuyEntity.setBuyPerson(buyPerson);
				financeBuyEntity.setBuyMobile(buyMobile);
				financeBuyEntity.setBuyIdNo(buyIdNo);
				financeBuyEntity.setDeductionBeginTime(deductionBeginTime);
				financeBuyEntity.setDeductionEndTime(deductionEndTime);
				financeBuyEntity.setBuyMoney(buyMoney);
				financeBuyEntity.setBuyTime(buyTime);
				
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("liberateNum", liberateNum);
				paramMap.put("roomId", roomId);
				FinanceReqEntity financeReq = financeService.getFinanceReqForResp(paramMap);
				financeBuyEntity.setFinaceReqId(financeReq.getId());
				financeBuyEntity.setWyMoney(financeReq.getPropertyFees());
				
				FinanceAgent financeAgent = financeService.getFinanceAgent(paramMap);
				financeBuyEntity.setChannelPartnerId(financeAgent.getChannelPartnerId());
				financeBuyEntity.setPropertyCompanyId(financeAgent.getPropertyCompanyId());
				
				//得到开始时间的当月第一天，结束时间的下一个月第一天
				financeBuyEntity.setDeductionBeginDate(DateUtils.getFirstDayOfMonth(deductionBeginTime));
				financeBuyEntity.setDeductionEndDate(DateUtils.getFirstDayOfNextMonth(deductionEndTime));
				
				int month = DateUtils.getDiffMonths(financeBuyEntity.getDeductionBeginDate(), financeBuyEntity.getDeductionEndDate());
				financeBuyEntity.setDeductionCount(month);
				returnMoney.divide(new BigDecimal(month), 5, BigDecimal.ROUND_DOWN);
				financeBuyEntity.setReturnMoney(returnMoney);
				
				financeBuyEntity.setWyRate(new BigDecimal(CnfantasiaCommbusiUtil.getSysParaValue("wyRate")));
				financeBuyEntity.setChannelRate(new BigDecimal(CnfantasiaCommbusiUtil.getSysParaValue("channelRate")));
				financeBuyEntity.setAddTm(new Date());
				financeBuyEntity.setFinanceType(FinanceConfig.FinanceType.FINANCE_PLOTPROPERTY);// 设置为物业宝类型
				
//				financeService.insertFinanceBuy(financeBuyEntity);
				if(financeService.isExistFinanceBuyCount(financeBuyEntity)) {
					respMap.put("code", "2");
				} else {
					financeService.saveReturnBuyFinance(financeBuyEntity);
					//生成抵扣信息
					FutureTask<Boolean> task = new FutureTask<Boolean>(new FinanceDeductionRunnable(financeService, orderNo, roomId, Long.valueOf(liberateNum)));
					new Thread(task).start();
					
					respMap.put("code", "1");
				}
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

	public void setFinanceService(FinanceService financeService) {
		this.financeService = financeService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/indexTest.html")
	@ResponseBody
	public ModelAndView indexTest(HttpServletRequest request, HttpServletResponse response) throws IOException{
//		if(APP_KEY == null || TOKEN == null) {
			APP_KEY = CnfantasiaCommbusiUtil.getSysParaValue("financeAppKey");
			TOKEN = CnfantasiaCommbusiUtil.getSysParaValue("financeToken");
			FINANCE_URL_TEST = CnfantasiaCommbusiUtil.getSysParaValue("financeUrlTest");
//		}
		
		FinanceReqEntity financeReq = getFinalReq(request);
		financeService.insertFinanceReq(financeReq);
		
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println("<HTML>");
//		out.println("<HEAD>\n");
//		out.println("<TITLE>理财购买</TITLE>\n");
//		out.println("</HEAD>\n");
////		out.println("<BODY Onload=\"document.forms[0].submit()\">");
//		out.println("<BODY>");
//		out.println("<FORM METHOD=\"POST\" ACTION=\"" + FINANCE_URL_TEST + "\">");
//		out.println("花号：<INPUT TYPE=\"input\" NAME=\"liberateNum\" " + "VALUE=\"" + financeReq.getLiberateNum() + "\"><br/>");
//		out.println("房间ID：<INPUT TYPE=\"input\" NAME=\"roomId\" " + "VALUE=\"" + financeReq.getRoomId() + "\"><br/>");
//		out.println("姓名：<INPUT TYPE=\"input\" NAME=\"realName\" " + "VALUE=\"" + financeReq.getRealName() + "\"><br/>");
//		out.println("电话：<INPUT TYPE=\"input\" NAME=\"mobile\" " + "VALUE=\"" + financeReq.getMobile() + "\"><br/>");
//		out.println("省名：<INPUT TYPE=\"input\" NAME=\"province\" " + "VALUE=\"" + financeReq.getProvince() + "\"><br/>");
//		out.println("城市名：<INPUT TYPE=\"input\" NAME=\"city\" " + "VALUE=\"" + financeReq.getCity() + "\"><br/>");
//		out.println("区：<INPUT TYPE=\"input\" NAME=\"prefecture\" " + "VALUE=\"" + financeReq.getPrefecture() + "\"><br/>");
//		out.println("小区：<INPUT TYPE=\"input\" NAME=\"residential\" " + "VALUE=\"" + financeReq.getResidential() + "\"><br/>");
//		out.println("楼栋：<INPUT TYPE=\"input\" NAME=\"building\" " + "VALUE=\"" + financeReq.getBuilding() + "\"><br/>");
//		out.println("房间号：<INPUT TYPE=\"input\" NAME=\"roomNum\" " + "VALUE=\"" + financeReq.getRoomNum() + "\"><br/>");
//		out.println("物业费：<INPUT TYPE=\"input\" NAME=\"propertyFees\" " + "VALUE=\"" + financeReq.getPropertyFees() + "\"><br/>");
//		out.println("<INPUT TYPE=\"submit\" NAME=\"submit\" " + "VALUE=\"提交\"><br/>");
//		out.println("</FORM></BODY></HTML>");
//		out.close();
		
		StringBuilder url = new StringBuilder();
		String apiPath = (String) request.getSession().getServletContext().getAttribute("apiPath");
		
		url.append(apiPath).append("/finance/returnBuyTest.html").append("?liberateNum=").append(financeReq.getLiberateNum())
					.append("&roomId=").append(financeReq.getRoomId()).append("&realName=").append(financeReq.getRealName()).append("&mobile=")
					.append(financeReq.getMobile()).append("&province=").append(financeReq.getProvince())
					.append("&city=").append(financeReq.getCity()).append("&prefecture=").append(financeReq.getPrefecture())
					.append("&residential=").append(financeReq.getResidential())
					.append("&building=").append(financeReq.getBuilding())
					.append("&roomNum=").append(financeReq.getRoomNum())
					.append("&propertyFees=").append(financeReq.getPropertyFees());
		response.sendRedirect(url.toString());
		
		return null;
	}
	
	@RequestMapping("/returnBuyTest.html")
	public ModelAndView returnBuyTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, String> respMap = new HashMap<String, String>();
		Date curDate = new Date();
			
		Long roomId = ParamUtils.getLong(request, "roomId", null);
		String liberateNum = ParamUtils.getString(request, "liberateNum", null);
		String buyPerson = ParamUtils.getString(request, "realName", null);
		String buyMobile = ParamUtils.getString(request, "mobile", null);
		String buyIdNo = ParamUtils.getString(request, "buyIdNo", "44123456789123456");
		String orderNo = ParamUtils.getString(request, "orderNo", DateUtils.convertDateToStr(curDate, "yyyyMMddHHmmss"));
		Date deductionBeginTime = ParamUtils.getDate(request, "deductionBeginTime", getDayOfNextMonth(), "yyyy-MM");
		Date deductionEndTime = ParamUtils.getDate(request, "deductionEndTime", getDayOfNextYear(), "yyyy-MM");
		BigDecimal propertyFees = ParamUtils.getBigDecimal(request, "propertyFees", null);
		
		BigDecimal temp = propertyFees.multiply(BigDecimal.valueOf(12));
		BigDecimal buyMoney = temp.divide(BigDecimal.valueOf(0.07), 2);
		if((buyMoney.intValue() % 1000 ) != 0) {
			int i = buyMoney.intValue() / 1000 + 1;
			buyMoney = BigDecimal.valueOf(i * 1000L);
		}
		BigDecimal returnMoney = buyMoney.multiply(BigDecimal.valueOf(0.02));
//		returnMoney = BigDecimalUtil.round(returnMoney, 2);
		
		Date buyTime = ParamUtils.getDate(request, "buyTime", curDate, "yyyy-MM-dd HH:mm:ss");
		
		FinanceBuyEntity financeBuyEntity = new FinanceBuyEntity();
		financeBuyEntity.setOrderNo(orderNo);
		financeBuyEntity.setRoomId(roomId);
		financeBuyEntity.setHudId(liberateNum);
		financeBuyEntity.setBuyPerson(buyPerson);
		financeBuyEntity.setBuyMobile(buyMobile);
		financeBuyEntity.setBuyIdNo(buyIdNo);
		financeBuyEntity.setDeductionBeginTime(deductionBeginTime);
		financeBuyEntity.setDeductionEndTime(deductionEndTime);
		financeBuyEntity.setBuyMoney(buyMoney);
		financeBuyEntity.setBuyTime(buyTime);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("liberateNum", liberateNum);
		paramMap.put("roomId", roomId);
		FinanceReqEntity financeReq = financeService.getFinanceReqForResp(paramMap);
		
		financeBuyEntity.setFinaceReqId(financeReq.getId());
		financeBuyEntity.setWyMoney(financeReq.getPropertyFees());
		
		FinanceAgent financeAgent = financeService.getFinanceAgent(paramMap);
		financeBuyEntity.setChannelPartnerId(financeAgent.getChannelPartnerId());
		financeBuyEntity.setPropertyCompanyId(financeAgent.getPropertyCompanyId());
		
		//得到开始时间的当月第一天，结束时间的下一个月第一天
		financeBuyEntity.setDeductionBeginDate(DateUtils.getFirstDayOfMonth(deductionBeginTime));
		financeBuyEntity.setDeductionEndDate(DateUtils.getFirstDayOfNextMonth(deductionEndTime));
		
		int month = DateUtils.getDiffMonths(financeBuyEntity.getDeductionBeginDate(), financeBuyEntity.getDeductionEndDate());
		financeBuyEntity.setDeductionCount(month);
		returnMoney.divide(new BigDecimal(month), 5, BigDecimal.ROUND_DOWN);
		financeBuyEntity.setReturnMoney(returnMoney);
		
		financeBuyEntity.setWyRate(new BigDecimal(CnfantasiaCommbusiUtil.getSysParaValue("wyRate")));
		financeBuyEntity.setChannelRate(new BigDecimal(CnfantasiaCommbusiUtil.getSysParaValue("channelRate")));
		financeBuyEntity.setAddTm(new Date());
		financeBuyEntity.setFinanceType(FinanceConfig.FinanceType.FINANCE_PLOTPROPERTY);
		
//		financeService.insertFinanceBuy(financeBuyEntity);
		financeService.saveReturnBuyFinance(financeBuyEntity);
		
		//生成抵扣信息
		FutureTask<Boolean> task = new FutureTask<Boolean>(new FinanceDeductionRunnable(financeService, orderNo, roomId, Long.valueOf(liberateNum)));
		new Thread(task).start();
		
		respMap.put("code", "1");
		modelAndView.setViewName("/finance/test");
		
		return modelAndView;
	}
	
	/**
	 * 跳理财
	 * @throws IOException 
	 */
	@RequestMapping(value="/licai.html")
	@ResponseBody
	public ModelAndView licai(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ModelAndView modelAndView = new ModelAndView();
		logger.debug("financeController sessionKey:" + request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY) + ",userId:" + ParamUtils.getLong(request, "", 0L));
		APP_KEY = CnfantasiaCommbusiUtil.getSysParaValue("financeAppKey");
		TOKEN = CnfantasiaCommbusiUtil.getSysParaValue("financeToken");
		String licaiUrl = ParamUtils.getString(request, "url", null);
		if(licaiUrl == null) {
			licaiUrl = CnfantasiaCommbusiUtil.getSysParaValue("licaiUrl");
		} else {
			licaiUrl = URLDecoder.decode(licaiUrl, "UTF-8");
		}
		//正式地址：http://m.hehenian.com/thirdparty/bindUser.do
		//测试地址：http://m2.hhnian.com:8006/thirdparty/bindUser.do
		
		if(licaiUrl == null) {
			licaiUrl = "http://m2.hhnian.com:8006/thirdparty/bindUser.do";
		}
		
		BigInteger userId = UserContext.getOperIdBigInteger();
		if(userId == null) {
			logger.debug("financeController user is null!sessionKey:" + request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY) + ",userId:" + ParamUtils.getLong(request, "", 0L));
			userId = ParamUtils.getBigInteger(request, "userId", null);
		}
//		if(userId == null) {
//			throw new TimeOutRuntimeException();
//		}
		if(userId == null) {
//    		appFuncBar.setLinkUrl("https://resource.linlile.com.cn/docs/prev_finance/index.htm");
//    		response.sendRedirect("https://resource.linlile.com.cn/docs/prev_finance/index.htm");
    		String resourcePath = (String) request.getSession().getServletContext().getAttribute("resourcePathHttps");
    		response.sendRedirect(resourcePath + "/prev_finance/index.htm");
			return null;
    	}
		
		UserEntity userEntity = userService.getUserById(userId);
		try {
			request.setAttribute("licaiUrl", licaiUrl);
			request.setAttribute("appKey", APP_KEY);
			request.setAttribute("token", TOKEN);
			request.setAttribute("uid", userEntity.getHuaId());
			request.setAttribute("mobilephone", userEntity.getMobile());
			request.setAttribute("channel", 8);
			
    		StringBuilder url = new StringBuilder();
    		url.append(CnfantasiaCommbusiUtil.getSysParaValue("licaiUrl")).append("?appKey=")
    				.append(CnfantasiaCommbusiUtil.getSysParaValue("financeAppKey")).append("&token=").append(CnfantasiaCommbusiUtil.getSysParaValue("financeToken"))
    				.append("&uid=").append(userEntity.getHuaId()).append("&mobilephone=").append(userEntity.getMobile()).append("&channel=8");

			response.sendRedirect(url.toString());
			
			
//			response.setContentType("text/html");
//			PrintWriter out = response.getWriter();
//			out.println("<HTML>");
//			out.println("<HEAD>\n");
//			out.println("<TITLE>理财购买</TITLE>\n");
//			out.println("</HEAD>\n");
//			out.println("<BODY Onload=\"document.forms[0].submit()\">");
//			out.println("<FORM METHOD=\"POST\" ACTION=\"" + CnfantasiaCommbusiUtil.getSysParaValue("licaiUrl") + "\">");
//			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"appKey\" " + "VALUE=\"" + CnfantasiaCommbusiUtil.getSysParaValue("financeAppKey") + "\">");
//			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"token\" " + "VALUE=\"" + CnfantasiaCommbusiUtil.getSysParaValue("financeToken") + "\">");
//			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"uid\" " + "VALUE=\"" + userEntity.getHuaId() + "\">");
//			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"mobilephone\" " + "VALUE=\"" + userEntity.getMobile() + "\">");
//			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"channel\" " + "VALUE=\"8\">");
//			out.println("</FORM></BODY></HTML>");
//			out.close();
			
			return null;
		} catch(TimeOutRuntimeException e) {
			logger.error(e.getMessage(), e);
			request.setAttribute("msg", "登录信息过期，请重新登录！");
		} catch(BusinessRuntimeException ee) {
			logger.error(ee.getMessage(), ee);
			request.setAttribute("msg", "系统异常，正在处理，请稍候再试！");
		} catch(Exception e2) {
			logger.error(e2.getMessage(), e2);
			request.setAttribute("msg", "系统异常，正在处理，请稍候再试！");
		}
		
		modelAndView.setViewName("/finance/licai");
		return modelAndView;
	}
	
	/**
	 * 49.1.	物业宝购买信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/wybBuyInfo.json")
	@ResponseBody
	public JsonResponse wybBuyInfo(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		// 查看小区是否校验，是否可以缴费，是否导入过账单
		Propfee propfee = plotpropertyService.getPropertyFeeAndCount(paramMap);
		UserEntity userEntity = userService.getUserById(userId);
		BigInteger realRoomId = userEntity.getDefaultRoomEntity().gettRealRoomFId();
		//如果查到当前门牌是物业软件对接的小区门牌，则不能买物业宝
		RealRoomSoftwareInfo realRoomSoftwareInfo = propertySoftwareDockDao.getRealRoomSoftwareInfo(realRoomId);
		if(propfee == null || realRoomSoftwareInfo != null) {
			// 当前门牌能不能购买物业宝==>{0：不能购买；1：能购买}
			jsonResponse.putData("canBuyWyb", 0);
			// 1当前门牌不能缴费
			// 2当前门牌能缴费，但当前门牌没有购买物业宝或者购买的物业宝已过期
			// 3当前门牌能缴费，且当前门牌购买的物业宝在有效期内。
			jsonResponse.putData("buyInfo", 1);
			// 1当前门牌不能缴费
			// 2当前门牌能缴费，但当前门牌没有购买物业宝或者购买的物业宝已过期
			// 3当前门牌能缴费，且当前门牌购买的物业宝在有效期内。
			jsonResponse.putData("hasBuyed", 0);
			// 是否导入过账单===>{0：没有导入账单；1：导入过账单}
			jsonResponse.putData("importBill", 0);
			// 购买物业宝的跳转地址
			jsonResponse.putData("url", CnfantasiaCommbusiUtil.getSysParaValue("wybUrl"));
			return jsonResponse;
		} else {
			if(propfee.getPayPeriodEnd()!=null && propfee.getPayPeriodEnd().compareTo(PlotpropertyConstant.PERID_GBCFG_LIMITEND_DEFAULT) == 0){
				jsonResponse.putData("canBuyWyb", 0);
				jsonResponse.putData("buyInfo", 1);
				jsonResponse.putData("hasBuyed", 0);
				jsonResponse.putData("importBill", 0);
				jsonResponse.putData("url", CnfantasiaCommbusiUtil.getSysParaValue("wybUrl"));
				return jsonResponse;
			}
			
			if(propfee.getPropfeeCanpay() != null && propfee.getPropfeeCanpay() == 1) {
				jsonResponse.putData("canBuyWyb", 1);
				
				if(propfee.getDeductionCount() != null && propfee.getHasDeductionCount() != null && propfee.getDeductionCount() > propfee.getHasDeductionCount()) {
					jsonResponse.putData("buyInfo", 3);
				} else {
					jsonResponse.putData("buyInfo", 2);
				}
				if(propfee.getTotalPrice() != null && propfee.getTotalPrice() > 0) {
					jsonResponse.putData("importBill", 1);
				} else {
					jsonResponse.putData("importBill", 0);
				}
			} else {
				jsonResponse.putData("buyInfo", 1);
				jsonResponse.putData("importBill", 0);
			}
			
			if(propfee.getDeductionCount() != null && propfee.getHasDeductionCount() != null && propfee.getDeductionCount() > propfee.getHasDeductionCount()) {
				jsonResponse.putData("hasBuyed", 1);
				
				String freeCopy = "免缴" + DateUtils.formatYearAndMonth2(propfee.getDeductionBeginDate()) + "－" + DateUtils.formatYearAndMonth2(propfee.getDeductionEndDate()) + "物业费";
				jsonResponse.putData("freeCopy", freeCopy);
			} else {
				jsonResponse.putData("hasBuyed", 0);
			}
		}

		// wybDedu：“抵扣至2017年12月”
		jsonResponse.putData("wybDedu", getWybDedu(realRoomId));
		// freeStatus：“已免本月物业费”
//			jsonResponse.putData("freeStatus", getFreeStatus(realRoomId));
		jsonResponse.putData("url", CnfantasiaCommbusiUtil.getSysParaValue("wybUrl"));
		
		return jsonResponse;
	}
	
	/**
	 * 获取物业宝抵扣至月份
	 * 
	 * @param realRoomId
	 * @return
	 */
	private String getWybDedu(BigInteger realRoomId){
		Date deduMonth = financeService.getWYBDeduMonth(realRoomId);
		
		if(deduMonth!=null){
			return DateUtils.convertDateToStr(deduMonth, "抵扣至 yyyy年MM月");
		} else{
			return "";
		}
	}
	
	/**
	 * 获取物业宝是否已抵扣本月账单
	 * 
	 * @param realRoomId
	 * @return
	 */
//	private String getFreeStatus(BigInteger realRoomId){
//		if(plotpropertyService.getWYBPayStatus(realRoomId)>0){
//			return "已免物业费"; 
//		} else {
//			return ""; 
//		}
//	}
	
	
	private Date getDayOfNextMonth() {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.MONTH, 1);
		return calendar1.getTime();
	}
	
	private Date getDayOfNextYear() {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.YEAR, 1);
		return calendar1.getTime();
	}

	public void setPlotpropertyService(IPlotpropertyService plotpropertyService) {
		this.plotpropertyService = plotpropertyService;
	}
	
	
//	@RequestMapping(value="/getRedirect.html")
//	@ResponseBody
//	public ModelAndView getRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException{
//		if(QSHQ_URL == null) {
//			QSHQ_URL = "http://m2.hhnian.com:8002/thirdparty/auth.do";
//		}
////		ModelAndView modelAndView = new ModelAndView("redirect:" + QSHQ_URL);
////		
////		//1.搜集参数
////		modelAndView.addObject("message","保存用户成功！");
//		
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//			out.println("<HTML>");
//			out.println("<HEAD>\n");
//			out.println("<TITLE>Access rights validated</TITLE>\n");
//			out.println("</HEAD>\n");
//			out.println("<BODY Onload=\"document.forms[0].submit()\">");
//			out.println("<FORM METHOD=\"POST\" ACTION=\"" + QSHQ_URL + "\">");
//			out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"loginResult\" " + "VALUE=\"test\">");
//			out.println("</FORM></BODY></HTML>");
//		out.close();
//		
//		return null;
//	response.setContentType("text/html");
//	PrintWriter out = response.getWriter();
//	out.println("{\"test\":\"test\"}");
//	return "{\"test\":\"test\"}";
//	}
	
	
}
