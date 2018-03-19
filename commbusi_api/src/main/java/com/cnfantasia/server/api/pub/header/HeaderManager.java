/**   
 * Filename:    HeaderManager.java   
 * @version:    1.0  
 * Create at:   2014年5月6日 上午12:58:20   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年5月6日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.pub.header;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.session.SessionManager;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename: HeaderManager.java
 * 
 * @version: 1.0.0 Create at: 2014年5月6日 上午12:58:20 Description:请求头信息管理
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年5月6日 shiyl 1.0 1.0 Version
 */
public class HeaderManager {

	/**
	 * 获取渠道Id
	 * 
	 * @return
	 */
	public static String getSubChannelId() {
		HttpServletRequest request = SessionManager.getRequest();
		if (request == null) {
			return null;
		}
		String subChannel = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
		if (StringUtils.isEmpty(subChannel)) {
			throw new ValidateRuntimeException("HeaderManager.getSubChannelId.isEmpty");
		}
		return subChannel;
	}

	public static Long getSubChannelIdLong() {
		String id = getSubChannelId();
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		return Long.parseLong(id);
	}

	public static Long getWlAppType(){
		HttpServletRequest request = SessionManager.getRequest();
		String subChannel = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
		Long subChannelId = null;
		if(!org.apache.commons.lang.StringUtils.isEmpty(subChannel)){
			subChannelId = Long.valueOf(subChannel);
		}
		if(subChannelId!=null&&subChannelId.compareTo(HeaderConstant.SubChannelId.Wl_Light_App)==0){
			return EbuyDict.WlAppType.Wl_Light_App;
		}else if(subChannelId!=null&&subChannelId.compareTo(HeaderConstant.SubChannelId.Jfq_Light_App)==0){//解放区微信轻应用
			return EbuyDict.WlAppType.Jfq_Light_App;
		}
		return EbuyDict.WlAppType.Jfq;
	}

	/**
	 * 获取设备Id
	 * 
	 * @return
	 */
	public static String getDeviceId() {
		HttpServletRequest request = SessionManager.getRequest();
		if (request == null) {
			return null;
		}
		String deviceId = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_DEVICE_ID);
		if (StringUtils.isEmpty(deviceId)) {
//			throw new ValidateRuntimeException("HeaderManager.getDeviceId.isEmpty");
			return null;
		}
		return deviceId;
	}
	
	public static String getVersion(){
		HttpServletRequest request = SessionManager.getRequest();
		if (request == null) {
			return null;
		}
		String version = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION);
//		if (StringUtils.isEmpty(version)) {
//			throw new ValidateRuntimeException("HeaderManager.getVersion.isEmpty");
//		}
		return version;
	}
	
	public static Integer getVersionNum() {
		String version = getVersion();
		if(version != null) {
			version = version.replace(".", "").trim();
			if(version.length() > 3) {
				version = version.substring(0, 3);
			}
			Integer versionNum = Integer.valueOf(version);
			return versionNum;
		}
		return null;
	}

	/**
	 * 不要再使用此方法，请调用commonRoomService.getGroupBuildingIdByUserId(userId) 
	 * @author wenfq 
	 */
	@Deprecated 
	public static BigInteger getGbId() {
		HttpServletRequest request = SessionManager.getRequest();
		if (request == null) {
			return null;
		}
		BigInteger gbId = null;
		try {
			if(request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_GBID) !=null) {
				gbId = new BigInteger(request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_GBID));
				request.getSession().setAttribute("gbId", gbId);
			} else if(ParamUtils.getBigInteger(request, "gbId", null) != null) {
				gbId = ParamUtils.getBigInteger(request, "gbId", null);
				request.getSession().setAttribute("gbId", gbId);
			} else {
				gbId = (BigInteger) request.getSession().getAttribute("gbId");
			}
		} catch (Exception e) {
			System.out.println("gbId值类型错误");
		}
		return gbId;
	}
	
	public static Integer getSignAndFound() {
		Integer signAndFound = -1;
		HttpServletRequest request = SessionManager.getRequest();
		if (request == null) {
			return null;
		}
		try {
			if(request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SIGN_AND_FOUND) !=null)
				signAndFound = new Integer(request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SIGN_AND_FOUND));
		} catch (Exception e) {
			System.out.println("gbId值类型错误");
		}
		return signAndFound;
	}
	
}
