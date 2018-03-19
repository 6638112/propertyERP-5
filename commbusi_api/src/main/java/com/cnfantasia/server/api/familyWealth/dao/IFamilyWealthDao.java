/**   
* Filename:    IFamilyWealthDao.java   
* @version:    1.0  
* Create at:   2015年4月29日 上午2:51:24   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.familyWealth.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.familyWealth.entity.FamilyWealthOptionEntity;

/**
 * Filename:    IFamilyWealthDao.java
 * @version:    1.0.0
 * Create at:   2015年4月29日 上午2:51:24
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月29日       shiyl             1.0             1.0 Version
 */
public interface IFamilyWealthDao {

	/**
	 * 查询财富选项列表
	 * @param userId
	 * @return
	 */
	public List<FamilyWealthOptionEntity> selectFamilyWealthOptionList(BigInteger userId);
	
}
