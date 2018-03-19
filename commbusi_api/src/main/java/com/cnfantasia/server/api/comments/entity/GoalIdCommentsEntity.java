/**   
* Filename:    GoalIdCommentsEntity.java   
* @version:    1.0  
* Create at:   2015年3月17日 上午8:56:42   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月17日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.comments.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * Filename:    GoalIdCommentsEntity.java
 * @version:    1.0.0
 * Create at:   2015年3月17日 上午8:56:42
 * Description: 单个目标对象包含的评论列表信息
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月17日       shiyl             1.0             1.0 Version
 */
public class GoalIdCommentsEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**目标Id*/
	private BigInteger goalId;
	/**评论列表*/
	private List<CommentsEntity> commentsEntityList;
	
	public BigInteger getGoalId() {
		return goalId;
	}
	public void setGoalId(BigInteger goalId) {
		this.goalId = goalId;
	}
	
	public List<CommentsEntity> getCommentsEntityList() {
		return commentsEntityList;
	}
	public void setCommentsEntityList(List<CommentsEntity> commentsEntityList) {
		this.commentsEntityList = commentsEntityList;
	}
	
}
