/**   
* Filename:    CommentsPointsParamEntity.java   
* @version:    1.0  
* Create at:   2015年2月2日 上午6:29:11   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年2月2日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.comments.entity;

import java.math.BigInteger;

/**
 * Filename:    CommentsPointsParamEntity.java
 * @version:    1.0.0
 * Create at:   2015年2月2日 上午6:29:11
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年2月2日       shiyl             1.0             1.0 Version
 */
public class CommentsPointsParamEntity {
	/**评分项Id*/
	private BigInteger commentsPointsId;
	/**评分项取值*/
	private Double pointsValue;
	
	public CommentsPointsParamEntity(BigInteger commentsPointsId,Double pointsValue){
		this.commentsPointsId = commentsPointsId;
		this.pointsValue = pointsValue;
	}
	
	public BigInteger getCommentsPointsId() {
		return commentsPointsId;
	}
	public void setCommentsPointsId(BigInteger commentsPointsId) {
		this.commentsPointsId = commentsPointsId;
	}
	public Double getPointsValue() {
		return pointsValue;
	}
	public void setPointsValue(Double pointsValue) {
		this.pointsValue = pointsValue;
	}
	
}
