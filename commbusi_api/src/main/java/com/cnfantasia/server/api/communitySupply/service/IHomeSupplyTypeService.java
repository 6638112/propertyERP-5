/**   
* Filename:    IHomeSupplyTypeService.java   
* @version:    1.0  
* Create at:   2015年8月17日 下午12:21:28   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月17日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.service;

import java.math.BigInteger;

import com.cnfantasia.server.api.communitySupply.entity.Level3ListMapEntity;

/**
 * Filename:    IHomeSupplyTypeService.java
 * @version:    1.0.0
 * Create at:   2015年8月17日 下午12:21:28
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月17日       shiyl             1.0             1.0 Version
 */
public interface IHomeSupplyTypeService {

//	/**
//	 * 获取首页图标展示Level01
//	 * @param version
//	 * @return
//	 */
//	public List<Map<String, Object>> getLevel00List(HomeTypeVersion version);
//	
//	/**
//	 * 获取首页图标展示Level01
//	 * @param version
//	 * @return
//	 */
//	public List<Map<String, Object>> getLevel01List(HomeTypeVersion version);
//
//	/**
//	 * 获取首页图标展示Level02
//	 * @param version
//	 * @return
//	 */
//	public List<Map<String, Object>> getLevel02List(HomeTypeVersion version);
	
	public Level3ListMapEntity getSupplyTypeAll();
	public Level3ListMapEntity getSupplyTypeList4Url();
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public Level3ListMapEntity getSupplyTypeList3Level(BigInteger userId);
	/**
	 * 根据地理位置获取收益图标
	 * @param userId
	 * @param countryId
	 * @param provinceId
	 * @param cityId
	 * @param blockId
	 * @param gbId
	 * @return
	 */
	public Level3ListMapEntity getSupplyTypeList3Level(BigInteger userId, BigInteger countryId, BigInteger provinceId,
			BigInteger cityId, BigInteger blockId, BigInteger gbId);
	
}
