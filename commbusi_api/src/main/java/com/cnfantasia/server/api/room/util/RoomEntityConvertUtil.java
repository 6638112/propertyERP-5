/**   
* Filename:    RoomEntityConvertUtil.java   
* @version:    1.0  
* Create at:   2015年2月11日 上午1:33:38   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年2月11日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.room.util;

import java.util.regex.Pattern;

import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;

/**
 * Filename:    RoomEntityConvertUtil.java
 * @version:    1.0.0
 * Create at:   2015年2月11日 上午1:33:38
 * Description: 门牌相关实体类的信息转换
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年2月11日       shiyl             1.0             1.0 Version
 */
public class RoomEntityConvertUtil {
	
	public static String getBuildingShowName(Building building){
		if(building==null){return null;}
		String name = building==null?null:building.getName();
//		if(name!=null&&isNumeric(name)){
//			name = name+"栋";
//		}
		return name;
	}
	
	public static String getBuildingShowName(String building) {
		if(building==null) {
			return null;
		}
//		if(building!=null&&isNumeric(building)){
//			building = building+"栋";
//		}
		return building;
	}
	
	public static String getRealRoomShowName(RealRoom realRoom){
		if(realRoom==null){return null;}
		StringBuffer nameSbf = new StringBuffer();
		if(!StringUtils.isEmpty(realRoom.getUnitName())&&!StringUtils.isEmpty(realRoom.getNumTail())){
//			if(isNumeric(realRoom.getUnitName())){
//				nameSbf.append(realRoom.getUnitName()+"单元");
//			}else{
				nameSbf.append(realRoom.getUnitName());
				nameSbf.append("-");
//			}
			nameSbf.append(realRoom.getNumTail());
		}else{
			nameSbf.append(realRoom.getNum());
		}
		return nameSbf.toString();
	}
	
	private static boolean isNumeric(String str){
    Pattern pattern = Pattern.compile("[0-9]*");
    return pattern.matcher(str).matches();   
 } 
	
}
