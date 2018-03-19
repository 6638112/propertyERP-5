/**   
* Filename:    ISuggestService.java   
* @version:    1.0  
* Create at:   2014年11月14日 上午9:12:17   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月14日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.suggest.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.commFeedbackLabel.entity.CommFeedbackLabel;

/**
 * Filename:    ISuggestService.java
 * @version:    1.0.0
 * Create at:   2014年11月14日 上午9:12:17
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月14日       shiyl             1.0             1.0 Version
 */
public interface ISuggestService {
	/**
	 * 查询意见标签列表
	 * @return
	 */
	public List<CommFeedbackLabel> getCommFeedbackLabelList();
	
	/**
	 * 发布意见
	 * @param userId
	 * @param detail
	 * @param contect
	 * @param commFeedbackLabelId
	 */
	public void submitSuggest(BigInteger userId, String detail, String contect, BigInteger commFeedbackLabelId);
}
