/**   
* Filename:    CommentsPointsAvgEntity.java   
* @version:    1.0  
* Create at:   2015年2月6日 上午3:21:19   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年2月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.comments.entity;

import com.cnfantasia.server.domainbase.commentsPoints.entity.CommentsPoints;

/**
 * Filename:    CommentsPointsAvgEntity.java
 * @version:    1.0.0
 * Create at:   2015年2月6日 上午3:21:19
 * Description: 单个数据项对应的所有评分项列表平均取值
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年2月6日       shiyl             1.0             1.0 Version
 */
public class CommentsPointsAvgEntity extends CommentsPoints{
	private static final long serialVersionUID = 1L;
	
	/**评分项平均取值*/
	private Double avgPoints;
	public Double getAvgPoints() {
		return avgPoints;
	}
	public void setAvgPoints(Double avgPoints) {
		this.avgPoints = avgPoints;
	}
	
}
