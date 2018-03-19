/**   
* Filename:    TypePriorityUtil.java   
* @version:    1.0  
* Create at:   2015年8月31日 下午5:18:35   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月31日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.microblogQueue.util;

import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    TypePriorityUtil.java
 * @version:    1.0.0
 * Create at:   2015年8月31日 下午5:18:35
 * Description:类别优先级工具类别
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月31日       shiyl             1.0             1.0 Version
 */
public class TypePriorityUtil {
	private static final Long MAX_PRIORITY = Long.MAX_VALUE;
	private static final Long STEP = 1000L;
	
	/**
	 * 根据编码动态获取优先级
	 * @param typeCode
	 * @param defaultPriority
	 * @param sendCount
	 * @return
	 */
	public static Long getDymPriority(String typeCode,Long defaultPriority,Long sendCount){
		Long resPriority = MAX_PRIORITY;
		if(!StringUtils.isEmpty(typeCode)&&defaultPriority!=null){
			if(sendCount==null){sendCount = 0L;}
			resPriority = defaultPriority;
			resPriority+=sendCount*STEP;
		}
		return resPriority;
	}
	
	
}
