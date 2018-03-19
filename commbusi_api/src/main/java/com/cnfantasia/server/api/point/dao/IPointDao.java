/**   
* Filename:    IPointDao.java   
* @version:    1.0  
* Create at:   2014年12月30日 下午1:44:14   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.point.dao;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.pointSourceRecord.entity.PointSourceRecord;

/**
 * Filename:    IPointDao.java
 * @version:    1.0.0
 * Create at:   2014年12月30日 下午1:44:14
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月30日       shiyl             1.0             1.0 Version
 */
public interface IPointDao {
	
	/**
	 * 增加积分
	 * @param userId
	 * @param value
	 * @return
	 */
	public Integer addPointData(BigInteger userId,Long value);
	
	/**
	 * 扣除积分
	 * @param userId
	 * @param value
	 * @return
	 */
	public Integer costPointData(BigInteger userId,Long value);
	
	/**
	 * 查询最近一次获取对应类别的积分信息
	 * @param userId
	 * @param type
	 * @return
	 */
	public PointSourceRecord selectLastPointSourceRecord(BigInteger userId,Integer type);
	
}
