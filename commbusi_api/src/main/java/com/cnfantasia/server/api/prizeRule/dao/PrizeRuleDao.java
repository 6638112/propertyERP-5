/**   
* Filename:    PrizeRuleDao.java   
* @version:    1.0  
* Create at:   2014年8月29日 上午6:23:50   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prizeRule.dao;

import java.util.List;

import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleDrawOnlinedaysEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateAreaEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateConfigEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;


/**
 * Filename:    PrizeRuleDao.java
 * @version:    1.0.0
 * Create at:   2014年8月29日 上午6:23:50
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月29日       shiyl             1.0             1.0 Version
 */
public class PrizeRuleDao extends AbstractBaseDao implements IPrizeRuleDao{

	@Override
	public List<PrizeRuleGenerateAreaEntity> selectPrizeRuleGenerateAreaList() {
		return sqlSession.selectList("prizeRule.select_PrizeRule_GenerateArea_List");
	}

	@Override
	public List<PrizeRuleDrawOnlinedaysEntity> selectPrizeRuleDrawOnlinedaysList() {
		return sqlSession.selectList("prizeRule.select_PrizeRule_Draw_Onlinedays_List");
	}

	@Override
	public List<PrizeRuleGenerateConfigEntity> selectPrizeRuleGenerateUsercountList() {
		return sqlSession.selectList("prizeRule.select_PrizeRule_Generate_Usercount_List");
	}
	
	
}
