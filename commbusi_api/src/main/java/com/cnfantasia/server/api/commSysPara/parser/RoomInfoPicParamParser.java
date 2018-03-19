/**   
* Filename:    RoomInfoPicParamParser.java   
* @version:    1.0  
* Create at:   2014年5月26日 上午9:23:32   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commSysPara.parser;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.room.entity.RoomInfoPicParamEntity;

/**
 * Filename:    RoomInfoPicParamParser.java
 * @version:    1.0.0
 * Create at:   2014年5月26日 上午9:23:32
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月26日       shiyl             1.0             1.0 Version
 */
public class RoomInfoPicParamParser extends AbstractSysParamParser{

	@SuppressWarnings("unchecked")
	@Override
	protected RoomInfoPicParamEntity doParse(String sysParamValue) {
		String[] roomInfoPicConfig =sysParamValue.split(";");
		String picBasePath = roomInfoPicConfig[0];//图片跟路径
		String goalFileType = roomInfoPicConfig[1];//图片格式
		Integer height = Integer.parseInt(roomInfoPicConfig[2]);//高
		Integer width = Integer.parseInt(roomInfoPicConfig[3]);//宽
		RoomInfoPicParamEntity roomInfoPicParamEntity= new RoomInfoPicParamEntity(picBasePath, goalFileType, height, width);
		return roomInfoPicParamEntity;
	}

	@Override
	protected String getSysParamKey() {
		return SysParamKey.ROOM_INFO_PIC_CONFIG;
	}
	
}
