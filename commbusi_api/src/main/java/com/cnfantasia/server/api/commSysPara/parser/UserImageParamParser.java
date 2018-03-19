/**   
* Filename:    UserImageParamParser.java   
* @version:    1.0  
* Create at:   2014年5月26日 上午8:44:28   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commSysPara.parser;

import java.util.Arrays;
import java.util.List;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.user.entity.UserImageParamEntity;

/**
 * Filename:    UserImageParamParser.java
 * @version:    1.0.0
 * Create at:   2014年5月26日 上午8:44:28
 * Description: 解析用户图片配置信息的处理类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月26日       shiyl             1.0             1.0 Version
 */
public class UserImageParamParser extends AbstractSysParamParser{
	@SuppressWarnings("unchecked")
	@Override
	protected UserImageParamEntity doParse(String sysParamValue) {
		String[] userImgaeStr = sysParamValue.split(";");
		String basePath = userImgaeStr[0];
		Long maxSize = Long.parseLong(userImgaeStr[1])*1024;//k转为字节
		List<String> supportFormates = Arrays.asList(userImgaeStr[2].split(","));
		UserImageParamEntity userImageParamEntity = new UserImageParamEntity(basePath, maxSize, supportFormates);
		return userImageParamEntity;
	}

	@Override
	protected String getSysParamKey() {
		return SysParamKey.USER_IMAGE_PROFILE_CONFIG;
	}
	
}
