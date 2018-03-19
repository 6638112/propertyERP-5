/**   
* Filename:    SimpleRoomInfo.java   
* @version:    1.0  
* Create at:   2014年12月22日 上午3:51:00   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.room.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Filename:    SimpleRoomInfo.java
 * @version:    1.0.0
 * Create at:   2014年12月22日 上午3:51:00
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月22日       shiyl             1.0             1.0 Version
 */
public class SimpleRoomInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private BigInteger roomId;
	private String nowTime;
	
	public SimpleRoomInfo(){}
	public SimpleRoomInfo(BigInteger roomId,String nowTime){
		this.roomId = roomId;
		this.nowTime = nowTime;
	}
	
	public BigInteger getRoomId() {
		return roomId;
	}
	public void setRoomId(BigInteger roomId) {
		this.roomId = roomId;
	}
	public String getNowTime() {
		return nowTime;
	}
	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}
	
}