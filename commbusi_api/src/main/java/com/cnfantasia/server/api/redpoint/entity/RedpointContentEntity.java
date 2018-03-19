/**   
* Filename:    RedpointContentEntity.java   
* @version:    1.0  
* Create at:   2015年3月26日 上午2:40:25   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redpoint.entity;

import com.cnfantasia.server.api.redpoint.constant.RedpointDict;
import com.cnfantasia.server.domainbase.redpointContent.entity.RedpointContent;

/**
 * Filename:    RedpointContentEntity.java
 * @version:    1.0.0
 * Create at:   2015年3月26日 上午2:40:25
 * Description:红点内容实体类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月26日       shiyl             1.0             1.0 Version
 */
public class RedpointContentEntity extends RedpointContent{
	private static final long serialVersionUID = 1L;

	private String dataStr = "";

	public boolean fetchClickStatus(){
		boolean res = true;//默认已点
		if(getClickStatus()!=null&&getClickStatus().compareTo(RedpointDict.RedpointContent_clickStatus.NOT_CLICK)==0){
			res = false;
		}
		return res;
	}

	public String getDataStr() {
		return dataStr;
	}

	public void setDataStr(String dataStr) {
		this.dataStr = dataStr;
	}
}
