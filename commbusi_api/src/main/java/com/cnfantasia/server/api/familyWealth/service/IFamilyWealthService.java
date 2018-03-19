/**   
* Filename:    IFamilyWealthService.java   
* @version:    1.0  
* Create at:   2015年4月29日 上午2:50:56   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.familyWealth.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.familyWealth.entity.FamilyWealthOptionEntity;

/**
 * Filename:    IFamilyWealthService.java
 * @version:    1.0.0
 * Create at:   2015年4月29日 上午2:50:56
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月29日       shiyl             1.0             1.0 Version
 */
public interface IFamilyWealthService {

	/**
	 * 查询财富选项列表
	 * @param userId
	 * @return
	 */
	public List<FamilyWealthOptionEntity> getFamilyWealthOptionList(BigInteger userId,Boolean checkIsLightApp);

}
