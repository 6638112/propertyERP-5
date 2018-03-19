/**   
* Filename:    MessagePushGroupEntity.java   
* @version:    1.0  
* Create at:   2014年9月29日 上午9:45:09   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.msgpush.entity;

import java.io.Serializable;
import java.math.BigInteger;

import com.cnfantasia.server.api.notice.constant.NoticeDict;

/**
 * Filename:    MessagePushGroupEntity.java
 * @version:    1.0.0
 * Create at:   2014年9月29日 上午9:45:09
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月29日       shiyl             1.0             1.0 Version
 */
public class MessagePushGroupEntity extends AbstractMessagePushBaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Integer fetchSendStatus() {
		return NoticeDict.Message_SendStatus.UnDo;
	}

	@Override
	public BigInteger fetchUserMsgRelaId() {
		return null;
	}

}
