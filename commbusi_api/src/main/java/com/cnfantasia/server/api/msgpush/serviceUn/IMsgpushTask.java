/**   
* Filename:    IMsgpushTask.java   
* @version:    1.0  
* Create at:   2014年9月22日 上午10:25:45   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月22日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.msgpush.serviceUn;

import java.math.BigInteger;


/**
 * Filename:    IMsgpushTask.java
 * @version:    1.0.0
 * Create at:   2014年9月22日 上午10:25:45
 * Description:消息发送任务
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月22日       shiyl             1.0             1.0 Version
 */
public interface IMsgpushTask {
	
	/**
	 * 定时任务自动发送
	 */
	public void autoSendTask();

	public int sendSignalByMsgId(BigInteger msgId);

	public int sendSignalByUserHasMsgId(BigInteger userHasMsgId);
	
}
