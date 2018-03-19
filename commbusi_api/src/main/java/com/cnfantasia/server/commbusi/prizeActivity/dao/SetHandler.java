/**   
* Filename:    SetHandler.java   
* @version:    1.0  
* Create at:   2015年9月16日 上午11:32:22   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.dao;

import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import com.cnfantasia.server.commbusi.prizeActivity.entity.GiftUqMarkCode;

/**
 * Filename:    SetHandler.java
 * @version:    1.0.0
 * Create at:   2015年9月16日 上午11:32:22
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月16日       shiyl             1.0             1.0 Version
 */
public class SetHandler implements ResultHandler{
	private Set<GiftUqMarkCode> resultSet = new HashSet<GiftUqMarkCode>();
	@Override
	public void handleResult(ResultContext context) {
		GiftUqMarkCode tmp = (GiftUqMarkCode)context.getResultObject();
		resultSet.add(tmp);
	}
	public Set<GiftUqMarkCode> getResultSet() {
		return resultSet;
	}
	
}
