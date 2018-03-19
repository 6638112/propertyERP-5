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
package com.cnfantasia.server.ms.pub.header;

import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.ms.pub.session.SessionManager;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    HeaderManager.java
 * @version:    1.0.0
 * Create at:   2014年5月6日 上午12:58:20
 * Description:请求头信息管理
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月6日       shiyl             1.0             1.0 Version
 */
public class HeaderManager {
//	public static final String HEADER_ATTR_NAME_CHANNEL = HeaderManager.class.getName()+"channelId";
	public static final String HEADER_ATTR_NAME_SUB_CHANNEL = HeaderManager.class.getName()+"subChannelId";
	public static final String HEADER_ATTR_NAME_DEVICE_ID = HeaderManager.class.getName()+"deviceId";
	
//	public static String getChannelId(){
//		HttpServletRequest request = SessionManager.getRequest();
//		if(request==null){return null;}
//		return (String)request.getAttribute(HEADER_ATTR_NAME_CHANNEL);
//	}
	/**
	 * 获取渠道Id
	 * @return
	 */
	public static String getSubChannelId(){
		HttpServletRequest request = SessionManager.getRequest();
		if(request==null){return null;}
		String subChannel = (String)request.getAttribute(HEADER_ATTR_NAME_SUB_CHANNEL);
		if(StringUtils.isEmpty(subChannel)){
			throw new ValidateRuntimeException("HeaderManager.getSubChannelId.isEmpty");
		}
		return subChannel;
	}
	public static Long getSubChannelIdLong(){
		String id=getSubChannelId();
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return Long.parseLong(id);
	}
	/**
	 * 获取设备Id
	 * @return
	 */
	public static String getDeviceId(){
		HttpServletRequest request = SessionManager.getRequest();
		if(request==null){return null;}
		String deviceId=(String)request.getAttribute(HEADER_ATTR_NAME_DEVICE_ID);
		if(StringUtils.isEmpty(deviceId)){
			throw new ValidateRuntimeException("HeaderManager.getDeviceId.isEmpty");
		}
		return deviceId;
	}
}
