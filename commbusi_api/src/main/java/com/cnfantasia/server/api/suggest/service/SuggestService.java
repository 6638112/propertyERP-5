/**   
* Filename:    SuggestService.java   
* @version:    1.0  
* Create at:   2014年11月14日 上午9:12:46   
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

import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.commFeedback.dao.ICommFeedbackBaseDao;
import com.cnfantasia.server.domainbase.commFeedback.entity.CommFeedback;
import com.cnfantasia.server.domainbase.commFeedbackLabel.dao.ICommFeedbackLabelBaseDao;
import com.cnfantasia.server.domainbase.commFeedbackLabel.entity.CommFeedbackLabel;

/**
 * Filename:    SuggestService.java
 * @version:    1.0.0
 * Create at:   2014年11月14日 上午9:12:46
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月14日       shiyl             1.0             1.0 Version
 */
public class SuggestService implements ISuggestService{
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private ICommFeedbackBaseDao commFeedbackBaseDao;
	public void setCommFeedbackBaseDao(ICommFeedbackBaseDao commFeedbackBaseDao) {
		this.commFeedbackBaseDao = commFeedbackBaseDao;
	}
	
	private ICommFeedbackLabelBaseDao commFeedbackLabelBaseDao;
	public void setCommFeedbackLabelBaseDao(ICommFeedbackLabelBaseDao commFeedbackLabelBaseDao) {
		this.commFeedbackLabelBaseDao = commFeedbackLabelBaseDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	@Override
	public List<CommFeedbackLabel> getCommFeedbackLabelList() {
		return commFeedbackLabelBaseDao.selectCommFeedbackLabelByCondition(null, true);
	}

	@Override
	public void submitSuggest(BigInteger userId, String detail,String contect,BigInteger commFeedbackLabelId) {
		BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_comm_feedback);
		String nowTime = dualDao.getNowTime();
		CommFeedback commFeedbackAdd =new CommFeedback();
		commFeedbackAdd.setContect(contect);
		commFeedbackAdd.setCreateTime(nowTime);
		commFeedbackAdd.setDetail(detail);
		commFeedbackAdd.setId(addId);
		commFeedbackAdd.settCommFeedbackLabelFId(commFeedbackLabelId);
		commFeedbackAdd.setUserId(userId);
		Integer resCount = commFeedbackBaseDao.insertCommFeedback(commFeedbackAdd);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("SuggestService.submitSuggest.failed");
		}
	}
	
}
