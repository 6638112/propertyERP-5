/**   
* Filename:    IOperationDao.java   
* @version:    1.0  
* Create at:   2015年4月23日 上午9:14:26   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.dao;

import java.util.List;

import com.cnfantasia.server.api.operation.entity.OperationConstantDataEntity;

/**
 * Filename:    IOperationDao.java
 * @version:    1.0.0
 * Create at:   2015年4月23日 上午9:14:26
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月23日       shiyl             1.0             1.0 Version
 */
public interface IOperationDao {

	/**
	 * 根据编码查询对应的文案信息
	 * @param codeList
	 * @return
	 */
	public List<OperationConstantDataEntity> selectOperationConstantDataMulti(List<String> codeList);

}
