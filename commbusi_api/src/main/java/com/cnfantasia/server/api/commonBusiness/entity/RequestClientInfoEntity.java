/**   
* Filename:    RequestClientInfoEntity.java   
* @version:    1.0  
* Create at:   2015年4月28日 上午6:38:24   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.entity;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.version.entity.AppVersionEntity;
import com.cnfantasia.server.api.version.util.VersionTransferUtil;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    RequestClientInfoEntity.java
 * @version:    1.0.0
 * Create at:   2015年4月28日 上午6:38:24
 * Description:请求的客户端信息实体类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月28日       shiyl             1.0             1.0 Version
 */
public class RequestClientInfoEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**渠道Id*/
	private String subChannelId;
	/**设备Id*/
	private String deviceId;
	/**用户sessionKey*/
	private String sessionKey;
	/**客户端版本号version*/
	private Long versionLong;
	
	public RequestClientInfoEntity(HttpServletRequest request){
		subChannelId = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
		deviceId = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_DEVICE_ID);
		sessionKey = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY);
		String version = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION);
		if(!StringUtils.isEmpty(version)){
			versionLong = VersionTransferUtil.versionStr2Long(version);
		}else{
			versionLong = VersionTransferUtil.versionStr2Long(AppVersionEntity.DEFAULT_START_VERSION);
		}
	}
	
	public Long getVersionLong(){
		return versionLong;
	}
	
	public Long getSubChannelIdLong(){
		Long subChannelIdLong = null;
		if(!StringUtils.isEmpty(subChannelId)){
			subChannelIdLong = Long.valueOf(subChannelId);
		}
		return subChannelIdLong;
	}
	
	/**
	 * 获取设备类别
	 * @return
	 */
	public Long getDeviceType(){
		return getSubChannelIdLong();
	}
	
	public String getDeviceId() {
		return deviceId;
	}

	public String getSessionKey() {
		return sessionKey;
	}
	
	/**
	 * 检查是否需要新增意外惊喜
	 * @param requestClientInfoEntity
	 * @param supriseGiftForDB
	 * @param goalUserType
	 * @param goalUserId
	 * @param prizeRecordAddId
	 * @return
	 */
	public static boolean checkSupriseGiftCanAdd(RequestClientInfoEntity requestClientInfoEntity){
		boolean res = false;
		//版本判断处理
		Long subChannelId = requestClientInfoEntity==null?null:requestClientInfoEntity.getSubChannelIdLong();
		Long version = requestClientInfoEntity==null?null:requestClientInfoEntity.getVersionLong();
		if(subChannelId!=null&&subChannelId.compareTo(HeaderConstant.SubChannelId.Jfq_Light_App)==0){//解放区轻应用渠道的 直接通过
			res = true;
		}else if(version!=null){//其他渠道的 版本是2.0.0及以上的通过
			Long activeVersion = VersionTransferUtil.versionStr2Long("2.0.0");
			if(version.compareTo(activeVersion)>=0){//版本符合要求 则存入数据库
				res = true;
			}
		}
		return res;
	}
	
}
