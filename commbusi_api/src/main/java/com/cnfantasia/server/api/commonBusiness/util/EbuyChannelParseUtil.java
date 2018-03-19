/**   
* Filename:    EbuyChannelParseUtil.java   
* @version:    1.0  
* Create at:   2015年4月27日 上午6:48:25   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月27日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.util;

import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    EbuyChannelParseUtil.java
 * @version:    1.0.0
 * Create at:   2015年4月27日 上午6:48:25
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月27日       shiyl             1.0             1.0 Version
 */
public class EbuyChannelParseUtil {
	/**
	 * 判断是否为轻应用
	 * @param request
	 * @return true 为轻应用，false表示为app
	 */
	public static boolean parseIsLightApp(HttpServletRequest request){
		String subChannel = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
		Long subChannelId = null;
		if(!StringUtils.isEmpty(subChannel)){
			subChannelId = Long.valueOf(subChannel);
		}
		if(subChannelId!=null&&subChannelId.compareTo(HeaderConstant.SubChannelId.Jfq_Light_App)==0){
			return true;
		}
		return false;
	}
	
	/**
	 * syl-add 2015-4-27 11:11:33 
	 * 增加判断解放区来源的细分 
	 */
	public static Integer parseJfq_SubType_ByHeader(HttpServletRequest request){
		String subChannel = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
		Long subChannelId = null;
		if(!StringUtils.isEmpty(subChannel)){
			subChannelId = Long.valueOf(subChannel);
		}
		if(subChannelId!=null&&subChannelId.compareTo(HeaderConstant.SubChannelId.Jfq_Light_App)==0){
			return EbuyDict.JfqApp_SubType.Jfq_Light_App;
		}
		return EbuyDict.JfqApp_SubType.Jfq_Client;
	}
	
	/**
	 * wenfq 2015-12-28
	 * 增加判断解放区来源的细分 
	 */
	public static Integer parseJfq_SubType_BySubChannel(String subChannel){
		Long subChannelId = null;
		if(!StringUtils.isEmpty(subChannel)){
			subChannelId = Long.valueOf(subChannel);
		}
		if(subChannelId!=null&&subChannelId.compareTo(HeaderConstant.SubChannelId.Jfq_Light_App)==0){
			return EbuyDict.JfqApp_SubType.Jfq_Light_App;
		}
		return EbuyDict.JfqApp_SubType.Jfq_Client;
	}
	
	public static Double fetchPayPercent(Integer jfqSubType,ISysParamManager sysParamManager){
		Double payPercent = null;
		if(jfqSubType!=null&&jfqSubType.compareTo(EbuyDict.JfqApp_SubType.Jfq_Light_App)==0){//解放区轻应用方面
			payPercent = Double.valueOf(sysParamManager.getSysParaValue(SysParamKey.Coupon_User_Percent_Jfq_LightApp));
		}else{
			payPercent = Double.valueOf(sysParamManager.getSysParaValue(SysParamKey.Coupon_User_Percent));
		}
		return payPercent;
	}
	
	/**
	 * 判断是否为轻应用
	 * @param request
	 * @return true 为轻应用，false表示为app
	 */
	public static boolean parseIsLightApp(Integer jfqSubType){
		boolean isLightApp = false;
		if(jfqSubType!=null&&jfqSubType.compareTo(EbuyDict.JfqApp_SubType.Jfq_Light_App)==0){//解放区轻应用方面
			isLightApp = true;
		}else{
			isLightApp = false;
		}
		return isLightApp;
	}
	
}
