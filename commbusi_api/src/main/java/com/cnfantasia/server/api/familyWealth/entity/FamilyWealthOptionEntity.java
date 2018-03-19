/**   
* Filename:    FamilyWealthOptionEntity.java   
* @version:    1.0  
* Create at:   2015年5月5日 上午8:32:29   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年5月5日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.familyWealth.entity;

import com.cnfantasia.server.domainbase.familyWealthOption.entity.FamilyWealthOption;

/**
 * Filename:    FamilyWealthOptionEntity.java
 * @version:    1.0.0
 * Create at:   2015年5月5日 上午8:32:29
 * Description:家庭财富选项实体类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年5月5日       shiyl             1.0             1.0 Version
 */
public class FamilyWealthOptionEntity extends FamilyWealthOption{
	private static final long serialVersionUID = 1L;
	
	/**取值*/
	private String valueStr;
	/**底部左侧文本*/
	private String bottomLeft;
	/**底部右侧文本*/
	private String bottomRight;
	
	public String getValueStr() {
		return valueStr;
	}
	public void setValueStr(String valueStr) {
		this.valueStr = valueStr;
	}
	public String getBottomLeft() {
		return bottomLeft;
	}
	public void setBottomLeft(String bottomLeft) {
		this.bottomLeft = bottomLeft;
	}
	public String getBottomRight() {
		return bottomRight;
	}
	public void setBottomRight(String bottomRight) {
		this.bottomRight = bottomRight;
	}
	
	/**是否为空门牌*/
	private Boolean isEmptyRoom;
	public Boolean getIsEmptyRoom() {
		return isEmptyRoom;
	}
	public void setIsEmptyRoom(Boolean isEmptyRoom) {
		this.isEmptyRoom = isEmptyRoom;
	}
	
	
}
