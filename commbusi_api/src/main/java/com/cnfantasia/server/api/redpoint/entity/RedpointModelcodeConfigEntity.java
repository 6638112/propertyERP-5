/**   
* Filename:    RedpointModelcodeConfigEntity.java   
* @version:    1.0  
* Create at:   2015年3月26日 上午6:25:57   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redpoint.entity;

import com.cnfantasia.server.domainbase.redpointModelcodeConfig.entity.RedpointModelcodeConfig;

/**
 * Filename:    RedpointModelcodeConfigEntity.java
 * @version:    1.0.0
 * Create at:   2015年3月26日 上午6:25:57
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月26日       shiyl             1.0             1.0 Version
 */
public class RedpointModelcodeConfigEntity extends RedpointModelcodeConfig{
	private static final long serialVersionUID = 1L;
	
	private Integer depth;
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	
	
}
