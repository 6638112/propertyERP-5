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
import java.math.BigInteger;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.plotproperty.entity.FinanceReqEntity;
import com.cnfantasia.server.api.plotproperty.service.FinanceService;
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
import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.common.exception.TimeOutRuntimeException;
import com.cnfantasia.server.common.utils.HttpUtil;
import com.cnfantasia.server.common.utils.ParamUtils;


/**
 * Filename:    FinanceHydController.java
 * @version:    1.0.0
 * Description:金融理财FinanceHydController
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2016年07月12日       yewj             1.0             1.0 Version
 */
@RequestMapping("/financeHyd")
@Controller
public class FinanceHydController extends BaseController{
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Resource
	private FinanceService financeService;
	
	@Resource
	private IUserService userService;
	
	private static String FINANCE_HYD_URL = "http://test.hehuayidai.com:3150"; //接口地址
	
	private static String APP_ID = "42017e76334243f1b793b7f7e307dad1-20160628";
	
	private static String SUB_SYSTEM = "jiefangqu";
	
	private static String GET_TOKEN_ADDR = "/user/getToken.do"; //拿token接口
	
	private static String LOGIN_ADDR = "/user/channelLogin.do"; //登录接口
	
	private static String SECRET_KEY = "CFB&#$*FHDHSK#*$"; //私钥
	
	/**
	 * @throws IOException 
	 */
	@RequestMapping(value="/index.html")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException{
		logger.debug("financeController sessionKey:" + request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY) + ",userId:" + ParamUtils.getLong(request, "", 0L));
		FinanceReqEntity financeReq = getFinalReq(request);
		financeService.insertFinanceReq(financeReq);
		
		FINANCE_HYD_URL = CnfantasiaCommbusiUtil.getSysParaValue("financeHydUrl");
		SECRET_KEY = CnfantasiaCommbusiUtil.getSysParaValue("financeHydKey");
		APP_ID = CnfantasiaCommbusiUtil.getSysParaValue("financeHydAppid");
		
		StringBuilder reqParamBuild = new StringBuilder();
		reqParamBuild.append("mobile").append(financeReq.getMobile()).append("source45thirdOrderNo0appId").append(APP_ID).append(SECRET_KEY);
		String sign = Md5Util.md5(reqParamBuild.toString());
		
		HttpUtil httpUtil = new HttpUtil();
		httpUtil.addParameter("mobile", financeReq.getMobile());
		httpUtil.addParameter("source", "45");
		httpUtil.addParameter("thirdOrderNo", "0");
		httpUtil.addParameter("appId", APP_ID);
		httpUtil.addParameter("sign", sign);
		String returnStr = httpUtil.post(FINANCE_HYD_URL + GET_TOKEN_ADDR, 5000);
		
		JSONObject jsonObject = JSONObject.parseObject(returnStr);
		String token = (String) jsonObject.get("token");
		long ts = System.currentTimeMillis();
		
		httpUtil.clearParameter();
		httpUtil.addParameter("mobile", financeReq.getMobile());
		httpUtil.addParameter("source", "45"); //用户来源平台固定为45
		httpUtil.addParameter("realName", financeReq.getRealName());
		httpUtil.addParameter("idNo", ""); //身份证号
		httpUtil.addParameter("houseNo", financeReq.getBuilding() + financeReq.getRoomNum()); //门牌号
		httpUtil.addParameter("address", financeReq.getProvince() + financeReq.getCity() + financeReq.getPrefecture()); //小区详细地址
		httpUtil.addParameter("cname", financeReq.getResidential()); //小区名
		httpUtil.addParameter("cid", financeReq.getRoomId().toString()); //小区id 我们传的是房间ID就可以了，返回也当房间ID处理
		httpUtil.addParameter("cUserId", financeReq.getLiberateNum()); //解放区用户ID
		httpUtil.addParameter("subSystem", SUB_SYSTEM); //接入的系统，固定值45
		httpUtil.addParameter("appId", APP_ID);
		httpUtil.addParameter("token", token);
		httpUtil.addParameter("ts", String.valueOf(ts)); //长整性的时间戳
		
		reqParamBuild.setLength(0);
		reqParamBuild.append("mobile").append(financeReq.getMobile()).append("source45")
				.append("realName").append(financeReq.getRealName()).append("idNo")
				.append("houseNo").append(financeReq.getBuilding()).append(financeReq.getRoomNum()).append("address").append(financeReq.getProvince()).append(financeReq.getCity()).append(financeReq.getPrefecture())
				.append("cname").append(financeReq.getResidential()).append("cid").append(financeReq.getRoomId().toString())
				.append("cUserId").append(financeReq.getLiberateNum()).append("subSystem").append(SUB_SYSTEM)
				.append("appId").append(APP_ID).append("token").append(token)
				.append("ts").append(String.valueOf(ts)).append(SECRET_KEY);
		
		sign = Md5Util.md5(reqParamBuild.toString());
		httpUtil.addParameter("sign", sign);
		
		returnStr = httpUtil.post(FINANCE_HYD_URL + LOGIN_ADDR, 5000);
		jsonObject = JSONObject.parseObject(returnStr);
		String applyUrl = jsonObject.getString("applyUrl");
		applyUrl = URLDecoder.decode(applyUrl, "UTF-8");
		
		return new ModelAndView("redirect:" + applyUrl);
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
		financeReq.setPropertyFees(BigDecimalUtil.div100(propertyFee));
		financeReq.setSourceClick("花易贷-借贷");
		
		return financeReq;
	}
	
}
