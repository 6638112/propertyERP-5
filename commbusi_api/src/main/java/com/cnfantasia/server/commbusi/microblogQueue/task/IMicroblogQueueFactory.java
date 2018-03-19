/**   
* Filename:    IMicroblogQueueFactory.java   
* @version:    1.0  
* Create at:   2015年8月25日 下午7:18:46   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.microblogQueue.task;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.microblogQueue.entity.MicroblogQueue;

/**
 * Filename:    IMicroblogQueueFactory.java
 * @version:    1.0.0
 * Create at:   2015年8月25日 下午7:18:46
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月25日       shiyl             1.0             1.0 Version
 */
public interface IMicroblogQueueFactory {

	/**
	 * 创建属于行政市级别的微博队列数据
	 * @param text
	 * @param sourceType
	 * @param isTiming
	 * @param addressCityId
	 * @param levelCode
	 * @return
	 */
	public MicroblogQueue createMicroblogQueueByAddressCity(String text, int sourceType, int isTiming, BigInteger addressCityId,
			Long levelCode);

	/**
	 * 创建属于小区级别的微博队列数据
	 * @param text
	 * @param sourceType
	 * @param isTiming
	 * @param groupbuildingId
	 * @param levelCode
	 * @return
	 */
	public MicroblogQueue createMicroblogQueueByGB(String text, int sourceType, int isTiming, BigInteger groupbuildingId,
			Long levelCode);
	public MicroblogQueue createMicroblogQueueByGB(String text, int sourceType, int isTiming, BigInteger groupbuildingId,
			Long levelCode,String linkDetailJson);
}
