/**   
* Filename:    CommentsHasTCommentsPointsEntity.java   
* @version:    1.0  
* Create at:   2015年2月2日 上午6:43:52   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年2月2日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.comments.entity;

import com.cnfantasia.server.domainbase.commentsHasTCommentsPoints.entity.CommentsHasTCommentsPoints;
import com.cnfantasia.server.domainbase.commentsPoints.entity.CommentsPoints;

/**
 * Filename:    CommentsHasTCommentsPointsEntity.java
 * @version:    1.0.0
 * Create at:   2015年2月2日 上午6:43:52
 * Description:评论包含评分项
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年2月2日       shiyl             1.0             1.0 Version
 */
public class CommentsHasTCommentsPointsEntity extends CommentsHasTCommentsPoints{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 具体评分项信息
	 */
	private CommentsPoints commentsPoints;
	public CommentsPoints getCommentsPoints() {
		return commentsPoints;
	}
	public void setCommentsPoints(CommentsPoints commentsPoints) {
		this.commentsPoints = commentsPoints;
	}
	
	
}
