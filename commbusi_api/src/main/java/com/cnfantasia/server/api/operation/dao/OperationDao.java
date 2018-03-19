/**   
* Filename:    OperationDao.java   
* @version:    1.0  
* Create at:   2015年4月23日 上午9:14:35   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.operation.entity.OperationConstantDataEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;

/**
 * Filename:    OperationDao.java
 * @version:    1.0.0
 * Create at:   2015年4月23日 上午9:14:35
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月23日       shiyl             1.0             1.0 Version
 */
public class OperationDao extends AbstractBaseDao implements IOperationDao{

	@Override
	public List<OperationConstantDataEntity> selectOperationConstantDataMulti(List<String> codeList) {
		Map<String,Object> tmpMap = new HashMap<String,Object>();
		tmpMap.put("codeList", codeList);
		return sqlSession.selectList("operation.select_OperationConstantData_Multi", tmpMap);
	}
	
}
