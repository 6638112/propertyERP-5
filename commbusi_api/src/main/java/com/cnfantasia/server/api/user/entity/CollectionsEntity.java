/**   
* Filename:    CollectionsEntity.java   
* @version:    1.0  
* Create at:   2014年5月23日 上午8:40:00   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.user.entity;

import com.cnfantasia.server.domainbase.collections.entity.Collections;

/**
 * Filename:    CollectionsEntity.java
 * @version:    1.0.0
 * Create at:   2014年5月23日 上午8:40:00
 * Description:收藏信息实体
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月23日       shiyl             1.0             1.0 Version
 */
public class CollectionsEntity extends Collections{
	private static final long serialVersionUID = 1L;
	
	/**具体被收藏的信息对象,此处只能使用Object对象类型*/
	private Object  singalDetail;

	public Object getSingalDetail() {
		return singalDetail;
	}

	public void setSingalDetail(Object singalDetail) {
		this.singalDetail = singalDetail;
	}
	
}
