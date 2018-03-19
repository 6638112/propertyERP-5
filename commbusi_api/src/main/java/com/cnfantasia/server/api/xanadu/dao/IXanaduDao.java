/**   
* Filename:    IXanaduDao.java   
* @version:    1.0  
* Create at:   2014年12月1日 上午3:34:05   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月1日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.xanadu.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.userXanaduRecord.entity.UserXanaduRecord;

/**
 * Filename:    IXanaduDao.java
 * @version:    1.0.0
 * Create at:   2014年12月1日 上午3:34:05
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月1日       shiyl             1.0             1.0 Version
 */
public interface IXanaduDao {

	/**
	 * 查询用户在世外桃源的记录数
	 * @param userId
	 * @return
	 */
	public Integer selectXanaduTrueCount(BigInteger userId);
	
	/**
	 * 查询用户世外桃源的记录数
	 * @param userId
	 * @return
	 */
	public List<UserXanaduRecord> selectXanaduRecordList(BigInteger userId);
}
